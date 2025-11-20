package com.stopaddict

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class CalendrierFragment : Fragment() {

    companion object {
        private const val TAG = "CalendrierFragment"
    }

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var configLangue: ConfigLangue
    private lateinit var headerTitle: TextView
    private lateinit var txtProfilStatus: TextView
    private lateinit var txtTotalJour: TextView
    private lateinit var btnMoisPrecedent: Button
    private lateinit var txtMoisAnnee: TextView
    private lateinit var btnMoisSuivant: Button
    private lateinit var gridCalendrier: GridLayout
    private lateinit var containerLegende: LinearLayout
    private lateinit var btnAujourdhui: Button
    private lateinit var btnHier: Button
    private lateinit var btnDemain: Button
    private var currentCalendar = Calendar.getInstance()
    
    // Catégories actives
    private var categoriesActives = mutableMapOf(
        DatabaseHelper.TYPE_CIGARETTE to true,
        DatabaseHelper.TYPE_JOINT to true,
        DatabaseHelper.TYPE_ALCOOL_GLOBAL to true,
        DatabaseHelper.TYPE_BIERE to false,
        DatabaseHelper.TYPE_LIQUEUR to false,
        DatabaseHelper.TYPE_ALCOOL_FORT to false
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calendrier, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        try {
            dbHelper = DatabaseHelper(requireContext())
            configLangue = ConfigLangue(requireContext())
            
            initializeViews(view)
            loadCategoriesActives()
            applyTranslations()
            setupListeners()
            loadDataAndRefresh()
            
            Log.d(TAG, "CalendrierFragment initialisé avec succès")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur initialisation: ${e.message}", e)
            Toast.makeText(requireContext(), "Erreur chargement Calendrier", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initializeViews(view: View) {
        try {
            headerTitle = view.findViewById(R.id.calendrier_header_title)
            txtProfilStatus = view.findViewById(R.id.calendrier_profil_status)
            txtTotalJour = view.findViewById(R.id.calendrier_total_jour)
            btnMoisPrecedent = view.findViewById(R.id.calendrier_btn_mois_precedent)
            txtMoisAnnee = view.findViewById(R.id.calendrier_txt_mois_annee)
            btnMoisSuivant = view.findViewById(R.id.calendrier_btn_mois_suivant)
            gridCalendrier = view.findViewById(R.id.calendrier_grid_mois)
            containerLegende = view.findViewById(R.id.calendrier_container_legende)
            btnAujourdhui = view.findViewById(R.id.calendrier_btn_aujourdhui)
            btnHier = view.findViewById(R.id.calendrier_btn_hier)
            btnDemain = view.findViewById(R.id.calendrier_btn_demain)
            
            Log.d(TAG, "Vues initialisées avec succès")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur initialisation vues: ${e.message}", e)
            throw e
        }
    }
    
    private fun loadCategoriesActives() {
        try {
            val json = dbHelper.getPreference("categories_actives", "{}")
            if (json.isNotEmpty()) {
                val jsonObj = JSONObject(json)
                categoriesActives[DatabaseHelper.TYPE_CIGARETTE] = jsonObj.optBoolean("cigarette", true)
                categoriesActives[DatabaseHelper.TYPE_JOINT] = jsonObj.optBoolean("joint", true)
                categoriesActives[DatabaseHelper.TYPE_ALCOOL_GLOBAL] = jsonObj.optBoolean("alcool_global", true)
                categoriesActives[DatabaseHelper.TYPE_BIERE] = jsonObj.optBoolean("biere", false)
                categoriesActives[DatabaseHelper.TYPE_LIQUEUR] = jsonObj.optBoolean("liqueur", false)
                categoriesActives[DatabaseHelper.TYPE_ALCOOL_FORT] = jsonObj.optBoolean("alcool_fort", false)
            }
            Log.d(TAG, "Catégories actives chargées: $categoriesActives")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur chargement catégories actives: ${e.message}", e)
        }
    }

    private fun applyTranslations() {
        try {
            val langue = configLangue.getLangue()
            val trad = CalendrierLangues.getTraductions(langue)
            
            headerTitle.text = trad["titre"] ?: "Calendrier"
            btnMoisPrecedent.text = trad["btn_mois_precedent"] ?: "◀ Préc"
            btnMoisSuivant.text = trad["btn_mois_suivant"] ?: "Suiv ▶"
            btnAujourdhui.text = trad["btn_aujourdhui"] ?: "Aujourd'hui"
            btnHier.text = trad["btn_hier"] ?: "Hier"
            btnDemain.text = trad["btn_demain"] ?: "Demain"
            
            Log.d(TAG, "Traductions appliquées pour langue: $langue")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur application traductions: ${e.message}", e)
        }
    }

    private fun setupListeners() {
        btnMoisPrecedent.setOnClickListener {
            currentCalendar.add(Calendar.MONTH, -1)
            updateCalendar()
        }
        
        btnMoisSuivant.setOnClickListener {
            currentCalendar.add(Calendar.MONTH, 1)
            updateCalendar()
        }
        
        btnAujourdhui.setOnClickListener {
            currentCalendar = Calendar.getInstance()
            updateCalendar()
        }
        
        btnHier.setOnClickListener {
            currentCalendar.add(Calendar.DAY_OF_MONTH, -1)
            updateCalendar()
        }
        
        btnDemain.setOnClickListener {
            currentCalendar.add(Calendar.DAY_OF_MONTH, 1)
            updateCalendar()
        }
    }

    private fun loadDataAndRefresh() {
        updateBandeau()
        updateCalendar()
        updateLegende()
    }

    private fun updateBandeau() {
        try {
            val trad = CalendrierLangues.getTraductions(configLangue.getLangue())
            
            // Vérifier profil complet
            val hasPrenom = dbHelper.getPreference("prenom", "").isNotEmpty()
            val hasCouts = categoriesActives.any { (type, active) ->
                if (active) {
                    val couts = dbHelper.getCouts(type)
                    couts.values.any { it > 0.0 }
                } else false
            }
            val hasHabitudes = categoriesActives.any { (type, active) ->
                active && dbHelper.getMaxJournalier(type) > 0
            }
            val hasDates = categoriesActives.any { (type, active) ->
                if (active) {
                    val dates = dbHelper.getDatesObjectifs(type)
                    dates.values.any { it.isNotEmpty() }
                } else false
            }
            
            val isComplet = hasPrenom && hasCouts && hasHabitudes && hasDates
            txtProfilStatus.text = if (isComplet) {
                trad["profil_complet"] ?: "Profil: Complet ✓"
            } else {
                trad["profil_incomplet"] ?: "Profil: Incomplet"
            }
            
            // Total du jour sélectionné
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val dateStr = dateFormat.format(currentCalendar.time)
            var total = 0
            
            categoriesActives.forEach { (type, active) ->
                if (active) {
                    total += dbHelper.getConsommationParDate(type, dateStr)
                }
            }
            
            txtTotalJour.text = "${trad["total_jour"] ?: "Total jour"}: $total"
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur update bandeau: ${e.message}", e)
        }
    }

    private fun updateCalendar() {
        try {
            val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
            txtMoisAnnee.text = dateFormat.format(currentCalendar.time)
            
            gridCalendrier.removeAllViews()
            
            // Ajouter entêtes jours de la semaine
            val joursHeader = arrayOf("L", "M", "M", "J", "V", "S", "D")
            joursHeader.forEach { jour ->
                val headerView = TextView(requireContext()).apply {
                    text = jour
                    textSize = 14f
                    gravity = android.view.Gravity.CENTER
                    setPadding(4, 4, 4, 4)
                    setTypeface(null, android.graphics.Typeface.BOLD)
                }
                gridCalendrier.addView(headerView)
            }
            
            val cal = currentCalendar.clone() as Calendar
            cal.set(Calendar.DAY_OF_MONTH, 1)
            var firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY
            if (firstDayOfWeek < 0) firstDayOfWeek += 7
            
            val daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
            
            // Espaces vides avant le 1er jour
            for (i in 0 until firstDayOfWeek) {
                gridCalendrier.addView(TextView(requireContext()).apply {
                    text = ""
                    setPadding(4, 4, 4, 4)
                })
            }
            
            // Jours du mois
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            
            for (day in 1..daysInMonth) {
                cal.set(Calendar.DAY_OF_MONTH, day)
                val dateStr = dateFormat.format(cal.time)
                
                // Calculer total consommations du jour
                var totalDay = 0
                categoriesActives.forEach { (type, active) ->
                    if (active) {
                        totalDay += dbHelper.getConsommationParDate(type, dateStr)
                    }
                }
                
                val dayView = TextView(requireContext()).apply {
                    text = day.toString()
                    textSize = 16f
                    setPadding(8, 16, 8, 16)
                    gravity = android.view.Gravity.CENTER
                    
                    // Couleur selon consommation
                    when {
                        totalDay == 0 -> {
                            setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.holo_green_light))
                        }
                        totalDay <= 5 -> {
                            setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.holo_orange_light))
                        }
                        else -> {
                            setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.holo_red_light))
                        }
                    }
                    
                    // Bordure si aujourd'hui
                    if (dateStr == today) {
                        setTypeface(null, android.graphics.Typeface.BOLD)
                        setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
                    }
                    
                    // Click listener
                    setOnClickListener {
                        showDayDialog(dateStr, day)
                    }
                }
                gridCalendrier.addView(dayView)
            }
            
            updateBandeau()
            
            Log.d(TAG, "Calendrier mis à jour")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur update calendar: ${e.message}", e)
        }
    }
    
    private fun showDayDialog(dateStr: String, day: Int) {
        try {
            val trad = CalendrierLangues.getTraductions(configLangue.getLangue())
            
            // Créer layout dialog
            val dialogView = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(40, 40, 40, 40)
            }
            
            // Titre
            val titre = TextView(requireContext()).apply {
                text = "${trad["dialog_titre"] ?: "Consommations du"} $day"
                textSize = 18f
                setTypeface(null, android.graphics.Typeface.BOLD)
                setPadding(0, 0, 0, 20)
            }
            dialogView.addView(titre)
            
            // Map pour stocker les compteurs
            val compteurs = mutableMapOf<String, Int>()
            val textViews = mutableMapOf<String, TextView>()
            
            // Créer compteur pour chaque catégorie active
            categoriesActives.forEach { (type, active) ->
                if (active) {
                    val count = dbHelper.getConsommationParDate(type, dateStr)
                    compteurs[type] = count
                    
                    val containerCategorie = LinearLayout(requireContext()).apply {
                        orientation = LinearLayout.HORIZONTAL
                        setPadding(0, 8, 0, 8)
                        gravity = android.view.Gravity.CENTER_VERTICAL
                    }
                    
                    // Label
                    val label = TextView(requireContext()).apply {
                        text = getNomCategorie(type)
                        textSize = 16f
                        layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
                    }
                    containerCategorie.addView(label)
                    
                    // Bouton -
                    val btnMoins = Button(requireContext()).apply {
                        text = "-"
                        layoutParams = LinearLayout.LayoutParams(80, LinearLayout.LayoutParams.WRAP_CONTENT)
                        setOnClickListener {
                            if (compteurs[type]!! > 0) {
                                compteurs[type] = compteurs[type]!! - 1
                                textViews[type]?.text = compteurs[type].toString()
                            }
                        }
                    }
                    containerCategorie.addView(btnMoins)
                    
                    // Compteur
                    val txtCount = TextView(requireContext()).apply {
                        text = count.toString()
                        textSize = 20f
                        gravity = android.view.Gravity.CENTER
                        layoutParams = LinearLayout.LayoutParams(60, LinearLayout.LayoutParams.WRAP_CONTENT)
                    }
                    textViews[type] = txtCount
                    containerCategorie.addView(txtCount)
                    
                    // Bouton +
                    val btnPlus = Button(requireContext()).apply {
                        text = "+"
                        layoutParams = LinearLayout.LayoutParams(80, LinearLayout.LayoutParams.WRAP_CONTENT)
                        setOnClickListener {
                            compteurs[type] = compteurs[type]!! + 1
                            textViews[type]?.text = compteurs[type].toString()
                        }
                    }
                    containerCategorie.addView(btnPlus)
                    
                    dialogView.addView(containerCategorie)
                }
            }
            
            // Dialog
            AlertDialog.Builder(requireContext())
                .setView(dialogView)
                .setPositiveButton(trad["btn_sauvegarder"] ?: "Sauvegarder") { _, _ ->
                    sauvegarderConsommationsJour(dateStr, compteurs)
                }
                .setNegativeButton(trad["btn_annuler"] ?: "Annuler", null)
                .show()
                
        } catch (e: Exception) {
            Log.e(TAG, "Erreur affichage dialog jour: ${e.message}", e)
            Toast.makeText(requireContext(), "Erreur: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun sauvegarderConsommationsJour(dateStr: String, compteurs: Map<String, Int>) {
        try {
            val trad = CalendrierLangues.getTraductions(configLangue.getLangue())
            
            compteurs.forEach { (type, newCount) ->
                val currentCount = dbHelper.getConsommationParDate(type, dateStr)
                val diff = newCount - currentCount
                
                if (diff > 0) {
                    // Ajouter
                    repeat(diff) {
                        dbHelper.ajouterConsommation(type, dateStr)
                    }
                } else if (diff < 0) {
                    // Retirer
                    repeat(-diff) {
                        dbHelper.retirerConsommation(type, dateStr)
                    }
                }
            }
            
            updateCalendar()
            Toast.makeText(requireContext(), trad["msg_sauvegarde_ok"] ?: "Sauvegardé", Toast.LENGTH_SHORT).show()
            
            Log.d(TAG, "Consommations sauvegardées pour $dateStr")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur sauvegarde consommations: ${e.message}", e)
            Toast.makeText(requireContext(), "Erreur sauvegarde: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun getNomCategorie(type: String): String {
        val trad = CalendrierLangues.getTraductions(configLangue.getLangue())
        return when (type) {
            DatabaseHelper.TYPE_CIGARETTE -> trad["label_cigarettes"] ?: "Cigarettes"
            DatabaseHelper.TYPE_JOINT -> trad["label_joints"] ?: "Joints"
            DatabaseHelper.TYPE_ALCOOL_GLOBAL -> trad["label_alcool_global"] ?: "Alcool global"
            DatabaseHelper.TYPE_BIERE -> trad["label_bieres"] ?: "Bières"
            DatabaseHelper.TYPE_LIQUEUR -> trad["label_liqueurs"] ?: "Liqueurs"
            DatabaseHelper.TYPE_ALCOOL_FORT -> trad["label_alcool_fort"] ?: "Alcool fort"
            else -> type
        }
    }
    
    private fun updateLegende() {
        try {
            containerLegende.removeAllViews()
            
            val trad = CalendrierLangues.getTraductions(configLangue.getLangue())
            
            // Titre légende
            val titreLeg = TextView(requireContext()).apply {
                text = trad["legende_titre"] ?: "Légende:"
                textSize = 16f
                setTypeface(null, android.graphics.Typeface.BOLD)
                setPadding(0, 0, 0, 12)
            }
            containerLegende.addView(titreLeg)
            
            // Vert = 0
            val legendeVert = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.HORIZONTAL
                setPadding(0, 4, 0, 4)
            }
            legendeVert.addView(View(requireContext()).apply {
                setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.holo_green_light))
                layoutParams = LinearLayout.LayoutParams(40, 40).apply {
                    setMargins(0, 0, 12, 0)
                }
            })
            legendeVert.addView(TextView(requireContext()).apply {
                text = trad["legende_vert"] ?: "Aucune consommation"
                textSize = 14f
            })
            containerLegende.addView(legendeVert)
            
            // Orange = 1-5
            val legendeOrange = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.HORIZONTAL
                setPadding(0, 4, 0, 4)
            }
            legendeOrange.addView(View(requireContext()).apply {
                setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.holo_orange_light))
                layoutParams = LinearLayout.LayoutParams(40, 40).apply {
                    setMargins(0, 0, 12, 0)
                }
            })
            legendeOrange.addView(TextView(requireContext()).apply {
                text = trad["legende_orange"] ?: "Consommation modérée (1-5)"
                textSize = 14f
            })
            containerLegende.addView(legendeOrange)
            
            // Rouge = 6+
            val legendeRouge = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.HORIZONTAL
                setPadding(0, 4, 0, 4)
            }
            legendeRouge.addView(View(requireContext()).apply {
                setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.holo_red_light))
                layoutParams = LinearLayout.LayoutParams(40, 40).apply {
                    setMargins(0, 0, 12, 0)
                }
            })
            legendeRouge.addView(TextView(requireContext()).apply {
                text = trad["legende_rouge"] ?: "Consommation élevée (6+)"
                textSize = 14f
            })
            containerLegende.addView(legendeRouge)
            
            Log.d(TAG, "Légende mise à jour")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur update légende: ${e.message}", e)
        }
    }

    fun refreshData() {
        try {
            loadCategoriesActives()
            loadDataAndRefresh()
            Log.d(TAG, "Données rafraîchies")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur refresh data: ${e.message}", e)
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            loadCategoriesActives()
            loadDataAndRefresh()
        } catch (e: Exception) {
            Log.e(TAG, "Erreur onResume: ${e.message}", e)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        try {
            if (::dbHelper.isInitialized) {
                dbHelper.close()
            }
            Log.d(TAG, "Fragment détruit")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur onDestroyView: ${e.message}", e)
        }
    }
}
