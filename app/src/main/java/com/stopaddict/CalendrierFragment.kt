package com.stopaddict

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

class CalendrierFragment : Fragment() {

    companion object {
        private const val TAG = "CalendrierFragment"
    }

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var configLangue: ConfigLangue
    private lateinit var trad: Map<String, String>
    
    private lateinit var txtProfilStatus: TextView
    private lateinit var txtTotalJour: TextView
    private lateinit var txtMoisAnnee: TextView
    private lateinit var btnPrecedent: Button
    private lateinit var btnAujourdhui: Button
    private lateinit var btnSuivant: Button
    private lateinit var gridCalendrier: GridLayout
    
    private val currentCalendar = Calendar.getInstance()
    private val categoriesActives = mutableMapOf<String, Boolean>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return try {
            val view = inflater.inflate(R.layout.fragment_calendrier, container, false)
            
            dbHelper = DatabaseHelper(requireContext())
            configLangue = ConfigLangue(requireContext())
            trad = CalendrierLangues.getTraductions(configLangue.getLangue())
            
            initializeViews(view)
            loadCategoriesActives()
            updateProfilStatus()
            updateCalendar()
            
            Log.d(TAG, "CalendrierFragment créé")
            view
        } catch (e: Exception) {
            Log.e(TAG, "Erreur onCreateView", e)
            Toast.makeText(requireContext(), "Erreur: ${e.message}", Toast.LENGTH_SHORT).show()
            null
        }
    }

    private fun initializeViews(view: View) {
        val container = view.findViewById<LinearLayout>(R.id.fragment_container)
        container.removeAllViews()
        container.orientation = LinearLayout.VERTICAL
        
        // Header
        val header = TextView(requireContext()).apply {
            text = trad["titre"] ?: "Calendrier"
            textSize = 24f
            setBackgroundColor(Color.parseColor("#00BCD4"))
            setTextColor(Color.WHITE)
            setPadding(20, 40, 20, 40)
            gravity = android.view.Gravity.CENTER
            setTypeface(null, android.graphics.Typeface.BOLD)
        }
        container.addView(header)
        
        // Zone profil et total
        val profilContainer = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.HORIZONTAL
            setPadding(20, 10, 20, 10)
            setBackgroundColor(Color.parseColor("#F5F5F5"))
        }
        
        txtProfilStatus = TextView(requireContext()).apply {
            text = trad["profil_incomplet"] ?: "Profil: Incomplet"
            textSize = 14f
            layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
        }
        profilContainer.addView(txtProfilStatus)
        
        txtTotalJour = TextView(requireContext()).apply {
            text = "${trad["total_jour"] ?: "Total jour"}: 0"
            textSize = 14f
            gravity = android.view.Gravity.END
        }
        profilContainer.addView(txtTotalJour)
        container.addView(profilContainer)
        
        // Navigation mois
        val navContainer = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.HORIZONTAL
            setPadding(10, 20, 10, 10)
        }
        
        btnPrecedent = Button(requireContext()).apply {
            text = trad["btn_mois_precedent"] ?: "◀ Préc"
            layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
            setOnClickListener {
                currentCalendar.add(Calendar.MONTH, -1)
                updateCalendar()
            }
        }
        navContainer.addView(btnPrecedent)
        
        btnAujourdhui = Button(requireContext()).apply {
            text = trad["btn_aujourdhui"] ?: "Aujourd'hui"
            layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f).apply {
                setMargins(10, 0, 10, 0)
            }
            setOnClickListener {
                currentCalendar.time = Date()
                updateCalendar()
            }
        }
        navContainer.addView(btnAujourdhui)
        
        btnSuivant = Button(requireContext()).apply {
            text = trad["btn_mois_suivant"] ?: "Suiv ▶"
            layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
            setOnClickListener {
                currentCalendar.add(Calendar.MONTH, 1)
                updateCalendar()
            }
        }
        navContainer.addView(btnSuivant)
        container.addView(navContainer)
        
        // Mois et année
        txtMoisAnnee = TextView(requireContext()).apply {
            textSize = 20f
            gravity = android.view.Gravity.CENTER
            setPadding(0, 10, 0, 10)
            setTypeface(null, android.graphics.Typeface.BOLD)
        }
        container.addView(txtMoisAnnee)
        
        // Grille calendrier - AGRANDI
        gridCalendrier = GridLayout(requireContext()).apply {
            columnCount = 7
            setPadding(10, 10, 10, 10)
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                0,
                1f // Prend tout l'espace disponible
            )
        }
        container.addView(gridCalendrier)
        
        // Légende
        val legendeContainer = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(20, 10, 20, 20)
            setBackgroundColor(Color.parseColor("#F5F5F5"))
        }
        
        val legendeTitre = TextView(requireContext()).apply {
            text = trad["legende_titre"] ?: "Légende:"
            textSize = 16f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setPadding(0, 0, 0, 10)
        }
        legendeContainer.addView(legendeTitre)
        
        addLegendeItem(legendeContainer, Color.parseColor("#E8F5E9"), trad["legende_vert"] ?: "Aucune consommation")
        addLegendeItem(legendeContainer, Color.parseColor("#FFF9C4"), trad["legende_orange"] ?: "Consommation modérée (1-5)")
        addLegendeItem(legendeContainer, Color.parseColor("#FFCCBC"), trad["legende_orange"] ?: "Consommation modérée (1-5)")
        addLegendeItem(legendeContainer, Color.parseColor("#FFCDD2"), trad["legende_rouge"] ?: "Consommation élevée (6+)")
        
        container.addView(legendeContainer)
    }

    private fun addLegendeItem(container: LinearLayout, color: Int, text: String) {
        val item = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.HORIZONTAL
            setPadding(0, 5, 0, 5)
        }
        
        val colorBox = View(requireContext()).apply {
            setBackgroundColor(color)
            layoutParams = LinearLayout.LayoutParams(40, 40).apply {
                setMargins(0, 0, 10, 0)
            }
        }
        item.addView(colorBox)
        
        val label = TextView(requireContext()).apply {
            this.text = text
            textSize = 14f
        }
        item.addView(label)
        
        container.addView(item)
    }

    private fun loadCategoriesActives() {
        try {
            val json = dbHelper.getPreference("categories_actives", """{"cigarette":true,"joint":true,"alcool_global":true,"biere":false,"liqueur":false,"alcool_fort":false}""")
            val gson = com.google.gson.Gson()
            val map = gson.fromJson(json, Map::class.java) as Map<String, Boolean>
            categoriesActives.clear()
            categoriesActives.putAll(map)
        } catch (e: Exception) {
            Log.e(TAG, "Erreur load categories", e)
        }
    }

    private fun updateProfilStatus() {
        try {
            val prenom = dbHelper.getPreference("prenom", "")
            val hasPrenom = prenom.isNotEmpty()
            
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
                    dates.values.any { it?.isNotEmpty() == true }
                } else false
            }
            
            val isComplet = hasPrenom && hasCouts && hasHabitudes && hasDates
            txtProfilStatus.text = if (isComplet) {
                trad["profil_complet"] ?: "Profil: Complet ✓"
            } else {
                trad["profil_incomplet"] ?: "Profil: Incomplet"
            }
            
            // Total aujourd'hui
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val today = dateFormat.format(Date())
            var total = 0
            
            categoriesActives.forEach { (type, active) ->
                if (active) {
                    total += dbHelper.getConsommationParDate(type, today)
                }
            }
            
            txtTotalJour.text = "${trad["total_jour"] ?: "Total jour"}: $total"
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur update profil", e)
        }
    }

    private fun updateCalendar() {
        try {
            val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
            txtMoisAnnee.text = dateFormat.format(currentCalendar.time)
            
            gridCalendrier.removeAllViews()
            
            // Entêtes jours - couleur sobre
            val joursHeader = (0..6).map { trad["jour_$it"] ?: "?" }.toTypedArray()
            joursHeader.forEach { jour ->
                val headerView = TextView(requireContext()).apply {
                    text = jour
                    textSize = 14f
                    gravity = android.view.Gravity.CENTER
                    setPadding(4, 8, 4, 8)
                    setTypeface(null, android.graphics.Typeface.BOLD)
                    setTextColor(Color.parseColor("#455A64"))
                }
                gridCalendrier.addView(headerView)
            }
            
            val cal = currentCalendar.clone() as Calendar
            cal.set(Calendar.DAY_OF_MONTH, 1)
            var firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY
            if (firstDayOfWeek < 0) firstDayOfWeek += 7
            
            val daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
            
            // Espaces vides
            for (i in 0 until firstDayOfWeek) {
                gridCalendrier.addView(TextView(requireContext()).apply {
                    text = ""
                    setPadding(4, 4, 4, 4)
                })
            }
            
            // Jours du mois
            val dateFormatJour = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            
            for (day in 1..daysInMonth) {
                cal.set(Calendar.DAY_OF_MONTH, day)
                val dateStr = dateFormatJour.format(cal.time)
                
                // Total consommations du jour
                var totalDay = 0
                categoriesActives.forEach { (type, active) ->
                    if (active) {
                        totalDay += dbHelper.getConsommationParDate(type, dateStr)
                    }
                }
                
                val dayView = TextView(requireContext()).apply {
                    text = day.toString()
                    textSize = 16f
                    setPadding(8, 20, 8, 20)
                    gravity = android.view.Gravity.CENTER
                    
                    // Couleurs SOBRES selon total
                    val bgColor = when {
                        totalDay == 0 -> Color.parseColor("#E8F5E9") // Vert très pâle
                        totalDay in 1..5 -> Color.parseColor("#FFF9C4") // Jaune pâle
                        totalDay in 6..15 -> Color.parseColor("#FFCCBC") // Orange pâle
                        else -> Color.parseColor("#FFCDD2") // Rouge pâle
                    }
                    setBackgroundColor(bgColor)
                    
                    // Bordure pour aujourd'hui
                    if (dateStr == today) {
                        setBackgroundResource(android.R.drawable.editbox_background)
                        setTextColor(Color.parseColor("#1976D2"))
                        setTypeface(null, android.graphics.Typeface.BOLD)
                    }
                    
                    setOnClickListener {
                        showDayDialog(dateStr, totalDay)
                    }
                }
                gridCalendrier.addView(dayView)
            }
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur update calendar", e)
        }
    }

    private fun showDayDialog(dateStr: String, totalActuel: Int) {
        try {
            val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateStr)
            val dateFormatted = dateFormat.format(date)
            
            val container = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(20, 20, 20, 20)
            }
            
            val title = TextView(requireContext()).apply {
                text = dateFormatted
                textSize = 18f
                setTypeface(null, android.graphics.Typeface.BOLD)
                setPadding(0, 0, 0, 20)
            }
            container.addView(title)
            
            val editFields = mutableMapOf<String, EditText>()
            
            categoriesActives.forEach { (type, active) ->
                if (active) {
                    val currentCount = dbHelper.getConsommationParDate(type, dateStr)
                    
                    val typeLabel = TextView(requireContext()).apply {
                        text = when (type) {
                            "cigarette" -> trad["cigarettes"] ?: "Cigarettes"
                            "joint" -> trad["joints"] ?: "Joints"
                            "alcool_global" -> trad["alcool_global"] ?: "Alcool global"
                            "biere" -> trad["bieres"] ?: "Bières"
                            "liqueur" -> trad["liqueurs"] ?: "Liqueurs"
                            "alcool_fort" -> trad["alcool_fort"] ?: "Alcool fort"
                            else -> type
                        }
                        textSize = 14f
                        setPadding(0, 10, 0, 5)
                    }
                    container.addView(typeLabel)
                    
                    val editText = EditText(requireContext()).apply {
                        setText(currentCount.toString())
                        inputType = android.text.InputType.TYPE_CLASS_NUMBER
                        hint = "0"
                    }
                    container.addView(editText)
                    editFields[type] = editText
                }
            }
            
            val scrollView = ScrollView(requireContext())
            scrollView.addView(container)
            
            android.app.AlertDialog.Builder(requireContext())
                .setView(scrollView)
                .setPositiveButton(trad["enregistrer"] ?: "Enregistrer") { _, _ ->
                    saveConsommationsForDate(dateStr, editFields)
                }
                .setNegativeButton(trad["annuler"] ?: "Annuler", null)
                .show()
                
        } catch (e: Exception) {
            Log.e(TAG, "Erreur dialog jour", e)
        }
    }

    private fun saveConsommationsForDate(dateStr: String, editFields: Map<String, EditText>) {
        try {
            editFields.forEach { (type, editText) ->
                val newCount = editText.text.toString().toIntOrNull() ?: 0
                val currentCount = dbHelper.getConsommationParDate(type, dateStr)
                val diff = newCount - currentCount
                
                if (diff > 0) {
                    repeat(diff) {
                        dbHelper.ajouterConsommation(type, 1, dateStr)
                    }
                } else if (diff < 0) {
                    repeat(-diff) {
                        dbHelper.retirerConsommation(type, dateStr)
                    }
                }
            }
            
            updateCalendar()
            updateProfilStatus()
            Toast.makeText(requireContext(), trad["sauvegarde_ok"] ?: "Enregistré", Toast.LENGTH_SHORT).show()
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur save", e)
            Toast.makeText(requireContext(), "Erreur: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        loadCategoriesActives()
        updateProfilStatus()
        updateCalendar()
    }
}
