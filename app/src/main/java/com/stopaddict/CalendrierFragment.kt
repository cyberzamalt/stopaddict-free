package com.stopaddict

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

/**
 * CalendrierFragment.kt - Partie 1/3
 * Affiche un calendrier mensuel avec les consommations
 * Navigation entre les mois et légende visuelle
 */
class CalendrierFragment : Fragment() {

    companion object {
        private const val TAG = "CalendrierFragment"
        
        // Couleurs pour intensité consommation
        private const val COLOR_NONE = "#FFFFFF"
        private const val COLOR_LOW = "#E8F5E9"      // Vert très clair
        private const val COLOR_MEDIUM = "#FFF3E0"    // Orange clair  
        private const val COLOR_HIGH = "#FFEBEE"      // Rouge clair
        private const val COLOR_VERY_HIGH = "#EF9A9A" // Rouge moyen
        
        // Symboles pour la légende
        private const val SYMBOL_CIGARETTE = "🚬"
        private const val SYMBOL_JOINT = "🌿"
        private const val SYMBOL_ALCOOL = "🍺"
        private const val SYMBOL_DATE_REDUCTION = "📉"
        private const val SYMBOL_DATE_ARRET = "🛑"
        private const val SYMBOL_DATE_REUSSITE = "✅"
    }

    // Database et config
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var configLangue: ConfigLangue
    private lateinit var calendrierLangues: CalendrierLangues
    
    // UI Elements - Header
    private lateinit var headerTitle: TextView
    private lateinit var bandeauProfil: LinearLayout
    private lateinit var txtProfilStatus: TextView
    private lateinit var txtTotalJour: TextView
    
    // UI Elements - Navigation
    private lateinit var btnMoisPrecedent: Button
    private lateinit var txtMoisAnnee: TextView
    private lateinit var btnMoisSuivant: Button
    
    // UI Elements - Calendrier
    private lateinit var gridCalendrier: GridLayout
    private lateinit var containerLegende: LinearLayout
    
    // UI Elements - Actions rapides
    private lateinit var btnAujourdhui: Button
    private lateinit var btnHier: Button
    private lateinit var btnDemain: Button
    
    // Variables d'état
    private var currentCalendar = Calendar.getInstance()
    private var selectedDate: Date? = null
    private val consommationsMap = mutableMapOf<String, MutableMap<String, Int>>() // date -> type -> quantité
    
    // Console debug
    private var consoleClickCount = 0
    private var lastConsoleClickTime = 0L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "═══════════════════════════════════════════")
        Log.d(TAG, "CalendrierFragment onCreateView")
        Log.d(TAG, "═══════════════════════════════════════════")
        
        return inflater.inflate(R.layout.fragment_calendrier, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        try {
            // Initialiser les composants
            initializeComponents()
            
            // Initialiser les vues
            initializeViews(view)
            
            // Configurer les listeners
            setupListeners()
            
            // Charger les données et afficher le calendrier
            loadDataAndRefresh()
            
            Log.d(TAG, "✓ CalendrierFragment initialisé avec succès")
            
        } catch (e: Exception) {
            Log.e(TAG, "❌ Erreur initialisation CalendrierFragment", e)
            showError("Erreur initialisation: ${e.message}")
        }
    }

    /**
     * Initialise les composants (DB, langues)
     */
    private fun initializeComponents() {
        try {
            dbHelper = DatabaseHelper(requireContext())
            configLangue = ConfigLangue(requireContext())
            calendrierLangues = CalendrierLangues()
            
            Log.d(TAG, "✓ Composants initialisés - Langue: ${configLangue.getLangue()}")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur initialisation composants", e)
            throw e
        }
    }

    /**
     * Initialise toutes les vues
     */
    private fun initializeViews(view: View) {
        try {
            // Header
            headerTitle = view.findViewById(R.id.calendrier_header_title)
            bandeauProfil = view.findViewById(R.id.calendrier_bandeau_profil)
            txtProfilStatus = view.findViewById(R.id.calendrier_profil_status)
            txtTotalJour = view.findViewById(R.id.calendrier_total_jour)
            
            // Navigation mois
            btnMoisPrecedent = view.findViewById(R.id.calendrier_btn_mois_precedent)
            txtMoisAnnee = view.findViewById(R.id.calendrier_txt_mois_annee)
            btnMoisSuivant = view.findViewById(R.id.calendrier_btn_mois_suivant)
            
            // Calendrier et légende
            gridCalendrier = view.findViewById(R.id.calendrier_grid_mois)
            containerLegende = view.findViewById(R.id.calendrier_container_legende)
            
            // Boutons actions rapides
            btnAujourdhui = view.findViewById(R.id.calendrier_btn_aujourdhui)
            btnHier = view.findViewById(R.id.calendrier_btn_hier)
            btnDemain = view.findViewById(R.id.calendrier_btn_demain)
            
            Log.d(TAG, "✓ Vues initialisées")
            
            // Appliquer les traductions
            applyTranslations()
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur initialisation vues", e)
            throw e
        }
    }

    /**
     * Applique les traductions selon la langue
     */
    private fun applyTranslations() {
        val langue = configLangue.getLangue()
        val trad = calendrierLangues.getTraductions(langue)
        
        // Boutons navigation
        btnMoisPrecedent.text = trad["btn_mois_precedent"]
        btnMoisSuivant.text = trad["btn_mois_suivant"]
        
        // Actions rapides
        btnAujourdhui.text = trad["btn_aujourdhui"]
        btnHier.text = trad["btn_hier"]
        btnDemain.text = trad["btn_demain"]
        
        Log.d(TAG, "✓ Traductions appliquées pour langue: $langue")
    }

    /**
     * Configure tous les listeners
     */
    private fun setupListeners() {
        try {
            // Header - Console debug (5 clics)
            headerTitle.setOnClickListener {
                handleConsoleDebugClick()
            }
            
            // Navigation mois
            btnMoisPrecedent.setOnClickListener {
                naviguerMoisPrecedent()
            }
            
            btnMoisSuivant.setOnClickListener {
                naviguerMoisSuivant()
            }
            
            // Actions rapides
            btnAujourdhui.setOnClickListener {
                selectDate(Date())
            }
            
            btnHier.setOnClickListener {
                val cal = Calendar.getInstance()
                cal.add(Calendar.DAY_OF_MONTH, -1)
                selectDate(cal.time)
            }
            
            btnDemain.setOnClickListener {
                val cal = Calendar.getInstance()
                cal.add(Calendar.DAY_OF_MONTH, 1)
                selectDate(cal.time)
            }
            
            Log.d(TAG, "✓ Listeners configurés")
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur configuration listeners", e)
        }
    }

    /**
     * Charge les données et rafraîchit l'affichage
     */
    private fun loadDataAndRefresh() {
        try {
            // Mettre à jour le header
            updateHeader()
            
            // Charger les consommations du mois
            loadMonthData()
            
            // Afficher le calendrier
            buildCalendarGrid()
            
            // Afficher la légende
            buildLegend()
            
            Log.d(TAG, "✓ Données chargées et affichage rafraîchi")
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur chargement données", e)
        }
    }

    /**
     * Met à jour le header (date/heure, profil, total)
     */
    private fun updateHeader() {
        try {
            // Date et heure
            val dateFormat = SimpleDateFormat("EEEE dd MMMM yyyy - HH:mm", Locale.getDefault())
            val currentDateTime = dateFormat.format(Date())
            headerTitle.text = "Stop Addict\n$currentDateTime"
            
            // Statut profil
            updateProfilStatus()
            
            // Total du jour
            updateTotalJour()
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur mise à jour header", e)
        }
    }

    /**
     * Met à jour le statut du profil
     */
    private fun updateProfilStatus() {
        try {
            val trad = calendrierLangues.getTraductions(configLangue.getLangue())
            
            // Vérifier si profil complet
            val couts = dbHelper.getCouts()
            val habitudes = dbHelper.getHabitudes()
            val dates = dbHelper.getDatesArret()
            
            val hasCoûts = couts.values.any { it > 0 }
            val hasHabitudes = habitudes.values.any { it > 0 }
            val hasDates = dates.values.any { it.isNotEmpty() }
            
            if (hasCoûts && hasHabitudes && hasDates) {
                txtProfilStatus.text = trad["profil_complet"]
                txtProfilStatus.setTextColor(Color.GREEN)
            } else {
                txtProfilStatus.text = trad["profil_incomplet"]
                txtProfilStatus.setTextColor(Color.ORANGE)
            }
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur mise à jour profil", e)
        }
    }

    /**
     * Met à jour le total du jour
     */
    private fun updateTotalJour() {
        try {
            val stats = dbHelper.getStatistiquesJour(Date())
            val total = stats.values.sum()
            
            val trad = calendrierLangues.getTraductions(configLangue.getLangue())
            txtTotalJour.text = "${trad["total_aujourdhui"]} $total"
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur mise à jour total jour", e)
            txtTotalJour.text = "Total: 0"
        }
    }

    /**
     * Charge les données de consommation du mois
     */
    private fun loadMonthData() {
        try {
            consommationsMap.clear()
            
            // Obtenir le premier et dernier jour du mois
            val firstDay = Calendar.getInstance()
            firstDay.time = currentCalendar.time
            firstDay.set(Calendar.DAY_OF_MONTH, 1)
            
            val lastDay = Calendar.getInstance()
            lastDay.time = currentCalendar.time
            lastDay.set(Calendar.DAY_OF_MONTH, lastDay.getActualMaximum(Calendar.DAY_OF_MONTH))
            
            // Format pour les clés de date
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            
            // Parcourir tous les jours du mois
            val cal = Calendar.getInstance()
            cal.time = firstDay.time
            
            while (!cal.after(lastDay)) {
                val dateKey = dateFormat.format(cal.time)
                val dayStats = dbHelper.getStatistiquesJour(cal.time)
                
                if (dayStats.isNotEmpty()) {
                    consommationsMap[dateKey] = dayStats.toMutableMap()
                }
                
                cal.add(Calendar.DAY_OF_MONTH, 1)
            }
            
            Log.d(TAG, "✓ Données du mois chargées: ${consommationsMap.size} jours avec consommations")
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur chargement données mois", e)
        }
    }

    /**
     * Construit la grille du calendrier
     */
    private fun buildCalendarGrid() {
        try {
            gridCalendrier.removeAllViews()
            gridCalendrier.columnCount = 7 // 7 jours par semaine
            
            val trad = calendrierLangues.getTraductions(configLangue.getLangue())
            
            // Mettre à jour le titre du mois
            val monthYearFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
            txtMoisAnnee.text = monthYearFormat.format(currentCalendar.time)
            
            // Ajouter les en-têtes des jours
            val daysOfWeek = arrayOf(
                trad["dim"], trad["lun"], trad["mar"], trad["mer"],
                trad["jeu"], trad["ven"], trad["sam"]
            )
            
            for (day in daysOfWeek) {
                val textView = TextView(context).apply {
                    text = day
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    setTypeface(typeface, Typeface.BOLD)
                    setPadding(8, 8, 8, 8)
                    setBackgroundResource(android.R.drawable.dialog_holo_light_frame)
                }
                gridCalendrier.addView(textView)
            }
            
            // Calculer le premier jour du mois
            val firstDayOfMonth = Calendar.getInstance()
            firstDayOfMonth.time = currentCalendar.time
            firstDayOfMonth.set(Calendar.DAY_OF_MONTH, 1)
            
            // Obtenir le jour de la semaine du premier jour (0=Dimanche, 6=Samedi)
            val firstDayOfWeek = firstDayOfMonth.get(Calendar.DAY_OF_WEEK) - 1
            
            // Ajouter des cellules vides avant le premier jour
            for (i in 0 until firstDayOfWeek) {
                val emptyView = View(context)
                emptyView.layoutParams = GridLayout.LayoutParams().apply {
                    width = 0
                    height = 100
                    columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                }
                gridCalendrier.addView(emptyView)
            }
            
            // Ajouter les jours du mois
            val maxDay = currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val today = Calendar.getInstance()
            
            for (day in 1..maxDay) {
                val dayCalendar = Calendar.getInstance()
                dayCalendar.time = currentCalendar.time
                dayCalendar.set(Calendar.DAY_OF_MONTH, day)
                
                val dateKey = dateFormat.format(dayCalendar.time)
                val dayCell = createDayCell(day, dateKey, dayCalendar, today)
                
                gridCalendrier.addView(dayCell)
            }
            
            Log.d(TAG, "✓ Grille calendrier construite")
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur construction grille", e)
        }
    }

    /**
     * Crée une cellule de jour pour le calendrier
     */
    private fun createDayCell(
        dayNumber: Int,
        dateKey: String,
        dayCalendar: Calendar,
        todayCalendar: Calendar
    ): LinearLayout {
        return LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = GridLayout.LayoutParams().apply {
                width = 0
                height = GridLayout.LayoutParams.WRAP_CONTENT
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                setMargins(2, 2, 2, 2)
            }
            
            // Couleur de fond selon intensité consommation
            val bgColor = getDayBackgroundColor(dateKey)
            setBackgroundColor(Color.parseColor(bgColor))
            
            // Bordure pour aujourd'hui
            val isToday = isSameDay(dayCalendar, todayCalendar)
            if (isToday) {
                background = ContextCompat.getDrawable(context, android.R.drawable.dialog_holo_light_frame)
                setBackgroundColor(Color.parseColor(bgColor))
            }
            
            // Numéro du jour
            val dayText = TextView(context).apply {
                text = dayNumber.toString()
                textAlignment = View.TEXT_ALIGNMENT_CENTER
                setTypeface(typeface, if (isToday) Typeface.BOLD else Typeface.NORMAL)
                textSize = 16f
            }
            addView(dayText)
            
            // Symboles des consommations
            val consumption = consommationsMap[dateKey]
            if (consumption != null && consumption.isNotEmpty()) {
                val symbolsText = TextView(context).apply {
                    text = getDaySymbols(consumption)
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textSize = 10f
                }
                addView(symbolsText)
            }
            
            // Listener pour sélectionner le jour
            setOnClickListener {
                selectDate(dayCalendar.time)
            }
            
            // Padding
            setPadding(4, 4, 4, 4)
        }
    }

    /**
     * Détermine la couleur de fond d'un jour selon l'intensité
     */
    private fun getDayBackgroundColor(dateKey: String): String {
        val consumption = consommationsMap[dateKey] ?: return COLOR_NONE
        
        val total = consumption.values.sum()
        
        return when {
            total == 0 -> COLOR_NONE
            total <= 5 -> COLOR_LOW
            total <= 10 -> COLOR_MEDIUM
            total <= 20 -> COLOR_HIGH
            else -> COLOR_VERY_HIGH
        }
    }

    /**
     * Obtient les symboles à afficher pour un jour
     */
    private fun getDaySymbols(consumption: Map<String, Int>): String {
        val symbols = mutableListOf<String>()
        
        if (consumption["cigarettes"] ?: 0 > 0) symbols.add(SYMBOL_CIGARETTE)
        if (consumption["joints"] ?: 0 > 0) symbols.add(SYMBOL_JOINT)
        if ((consumption["alcool_global"] ?: 0) > 0 ||
            (consumption["bieres"] ?: 0) > 0 ||
            (consumption["liqueurs"] ?: 0) > 0 ||
            (consumption["alcool_fort"] ?: 0) > 0) {
            symbols.add(SYMBOL_ALCOOL)
        }
        
        return symbols.joinToString("")
    }

    // ══════════════════════════════════════════════════
    // FIN PARTIE 1
    // Suite dans Partie 2 : Légende, navigation, sélection
    // ══════════════════════════════════════════════════
}
// ══════════════════════════════════════════════════
    // PARTIE 2/2 - SUITE DE CalendrierFragment.kt
    // Légende, navigation, sélection, console debug
    // ══════════════════════════════════════════════════

    /**
     * Construit la légende visuelle
     */
    private fun buildLegend() {
        try {
            containerLegende.removeAllViews()
            
            val trad = calendrierLangues.getTraductions(configLangue.getLangue())
            
            // Titre de la légende
            val titleView = TextView(context).apply {
                text = trad["titre_legende"]
                setTypeface(typeface, Typeface.BOLD)
                textSize = 16f
                setPadding(0, 8, 0, 8)
            }
            containerLegende.addView(titleView)
            
            // Items de la légende
            val legendItems = listOf(
                SYMBOL_CIGARETTE to trad["label_cigarettes"],
                SYMBOL_JOINT to trad["label_joints"],
                SYMBOL_ALCOOL to trad["label_alcool"],
                SYMBOL_DATE_REDUCTION to trad["date_reduction"],
                SYMBOL_DATE_ARRET to trad["date_arret"],
                SYMBOL_DATE_REUSSITE to trad["date_reussite"]
            )
            
            for ((symbol, label) in legendItems) {
                val itemLayout = LinearLayout(context).apply {
                    orientation = LinearLayout.HORIZONTAL
                    setPadding(16, 4, 16, 4)
                }
                
                // Symbole
                val symbolView = TextView(context).apply {
                    text = symbol
                    textSize = 20f
                    setPadding(0, 0, 8, 0)
                }
                itemLayout.addView(symbolView)
                
                // Label
                val labelView = TextView(context).apply {
                    text = label
                    textSize = 14f
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        gravity = Gravity.CENTER_VERTICAL
                    }
                }
                itemLayout.addView(labelView)
                
                containerLegende.addView(itemLayout)
            }
            
            // Légende des couleurs
            val colorTitle = TextView(context).apply {
                text = trad["intensite_consommation"]
                setTypeface(typeface, Typeface.BOLD)
                textSize = 14f
                setPadding(0, 16, 0, 8)
            }
            containerLegende.addView(colorTitle)
            
            val colorLegend = listOf(
                COLOR_LOW to trad["faible"],
                COLOR_MEDIUM to trad["moyenne"],
                COLOR_HIGH to trad["elevee"],
                COLOR_VERY_HIGH to trad["tres_elevee"]
            )
            
            val colorLayout = LinearLayout(context).apply {
                orientation = LinearLayout.HORIZONTAL
                setPadding(16, 4, 16, 4)
            }
            
            for ((color, label) in colorLegend) {
                val colorItem = LinearLayout(context).apply {
                    orientation = LinearLayout.VERTICAL
                    layoutParams = LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1f
                    )
                }
                
                // Carré de couleur
                val colorView = View(context).apply {
                    layoutParams = LinearLayout.LayoutParams(40, 40).apply {
                        gravity = Gravity.CENTER_HORIZONTAL
                        setMargins(4, 4, 4, 4)
                    }
                    setBackgroundColor(Color.parseColor(color))
                }
                colorItem.addView(colorView)
                
                // Label
                val colorLabel = TextView(context).apply {
                    text = label
                    textSize = 10f
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                }
                colorItem.addView(colorLabel)
                
                colorLayout.addView(colorItem)
            }
            
            containerLegende.addView(colorLayout)
            
            Log.d(TAG, "✓ Légende construite")
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur construction légende", e)
        }
    }

    /**
     * Navigue vers le mois précédent
     */
    private fun naviguerMoisPrecedent() {
        try {
            currentCalendar.add(Calendar.MONTH, -1)
            Log.d(TAG, "Navigation mois précédent: ${SimpleDateFormat("MM/yyyy", Locale.getDefault()).format(currentCalendar.time)}")
            
            loadDataAndRefresh()
            
            // Animation de transition
            gridCalendrier.alpha = 0f
            gridCalendrier.animate()
                .alpha(1f)
                .setDuration(300)
                .start()
                
        } catch (e: Exception) {
            Log.e(TAG, "Erreur navigation mois précédent", e)
        }
    }

    /**
     * Navigue vers le mois suivant
     */
    private fun naviguerMoisSuivant() {
        try {
            currentCalendar.add(Calendar.MONTH, 1)
            Log.d(TAG, "Navigation mois suivant: ${SimpleDateFormat("MM/yyyy", Locale.getDefault()).format(currentCalendar.time)}")
            
            loadDataAndRefresh()
            
            // Animation de transition
            gridCalendrier.alpha = 0f
            gridCalendrier.animate()
                .alpha(1f)
                .setDuration(300)
                .start()
                
        } catch (e: Exception) {
            Log.e(TAG, "Erreur navigation mois suivant", e)
        }
    }

    /**
     * Sélectionne une date et affiche les détails
     */
    private fun selectDate(date: Date) {
        try {
            selectedDate = date
            
            val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            val dateKey = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)
            
            Log.d(TAG, "Date sélectionnée: ${dateFormat.format(date)}")
            
            // Récupérer les données de cette date
            val consumption = consommationsMap[dateKey]
            
            if (consumption != null && consumption.isNotEmpty()) {
                showDateDetails(date, consumption)
            } else {
                val trad = calendrierLangues.getTraductions(configLangue.getLangue())
                Toast.makeText(
                    context,
                    "${trad["aucune_consommation"]} ${dateFormat.format(date)}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            
            // Mettre à jour le calendrier si nécessaire (changer de mois)
            val cal = Calendar.getInstance()
            cal.time = date
            
            if (cal.get(Calendar.MONTH) != currentCalendar.get(Calendar.MONTH) ||
                cal.get(Calendar.YEAR) != currentCalendar.get(Calendar.YEAR)) {
                
                currentCalendar.time = date
                loadDataAndRefresh()
            }
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur sélection date", e)
        }
    }

    /**
     * Affiche les détails d'une date
     */
    private fun showDateDetails(date: Date, consumption: Map<String, Int>) {
        try {
            val trad = calendrierLangues.getTraductions(configLangue.getLangue())
            val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            
            val details = StringBuilder()
            details.append("${trad["details_jour"]} ${dateFormat.format(date)}\n\n")
            
            // Détails des consommations
            consumption.forEach { (type, quantity) ->
                if (quantity > 0) {
                    val label = when (type) {
                        "cigarettes" -> trad["label_cigarettes"]
                        "joints" -> trad["label_joints"]
                        "alcool_global" -> trad["label_alcool_global"]
                        "bieres" -> trad["label_bieres"]
                        "liqueurs" -> trad["label_liqueurs"]
                        "alcool_fort" -> trad["label_alcool_fort"]
                        else -> type
                    }
                    details.append("• $label: $quantity\n")
                }
            }
            
            // Calculer le total
            val total = consumption.values.sum()
            details.append("\n${trad["total"]}: $total")
            
            // Afficher dans un dialog
            android.app.AlertDialog.Builder(requireContext())
                .setTitle(trad["details_consommation"])
                .setMessage(details.toString())
                .setPositiveButton(trad["fermer"]) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
                
        } catch (e: Exception) {
            Log.e(TAG, "Erreur affichage détails date", e)
        }
    }

    /**
     * Gère les clics pour la console debug (5 clics)
     */
    private fun handleConsoleDebugClick() {
        val currentTime = System.currentTimeMillis()
        
        if (currentTime - lastConsoleClickTime > 2000) {
            consoleClickCount = 1
            Log.d(TAG, "Console debug: premier clic")
        } else {
            consoleClickCount++
            Log.d(TAG, "Console debug: clic $consoleClickCount/5")
        }
        
        lastConsoleClickTime = currentTime
        
        if (consoleClickCount >= 5) {
            consoleClickCount = 0
            showConsoleDebug()
        }
    }

    /**
     * Affiche la console debug
     */
    private fun showConsoleDebug() {
        try {
            val logs = StringBuilder()
            logs.append("═══════ CONSOLE DEBUG CALENDRIER ═══════\n\n")
            
            // Infos générales
            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
            logs.append("📅 Date/Heure: ${dateFormat.format(Date())}\n")
            logs.append("🌍 Langue: ${configLangue.getLangue()}\n")
            logs.append("📆 Mois affiché: ${SimpleDateFormat("MM/yyyy", Locale.getDefault()).format(currentCalendar.time)}\n")
            
            if (selectedDate != null) {
                logs.append("📍 Date sélectionnée: ${dateFormat.format(selectedDate!!)}\n")
            }
            
            logs.append("\n─────── STATISTIQUES MOIS ───────\n")
            
            // Stats du mois
            var totalMois = 0
            var jourAvecConso = 0
            
            consommationsMap.forEach { (_, consumption) ->
                val dayTotal = consumption.values.sum()
                if (dayTotal > 0) {
                    jourAvecConso++
                    totalMois += dayTotal
                }
            }
            
            logs.append("📊 Jours avec consommation: $jourAvecConso\n")
            logs.append("📊 Total du mois: $totalMois\n")
            
            if (jourAvecConso > 0) {
                logs.append("📊 Moyenne par jour: ${totalMois / jourAvecConso}\n")
            }
            
            // Top 5 jours
            logs.append("\n─────── TOP 5 JOURS ───────\n")
            
            val sortedDays = consommationsMap.entries
                .map { it.key to it.value.values.sum() }
                .filter { it.second > 0 }
                .sortedByDescending { it.second }
                .take(5)
                
            sortedDays.forEach { (date, total) ->
                logs.append("• $date: $total\n")
            }
            
            logs.append("\n═════════════════════════════════════════\n")
            
            // Afficher le dialog
            val textView = TextView(context).apply {
                text = logs.toString()
                setPadding(20, 20, 20, 20)
                textSize = 12f
                setTextIsSelectable(true)
            }
            
            val scrollView = ScrollView(context).apply {
                addView(textView)
            }
            
            android.app.AlertDialog.Builder(requireContext())
                .setTitle("Console Debug")
                .setView(scrollView)
                .setPositiveButton("Fermer") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
                
            Log.d(TAG, "✓ Console debug affichée")
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur affichage console debug", e)
        }
    }

    /**
     * Vérifie si deux calendriers sont le même jour
     */
    private fun isSameDay(cal1: Calendar, cal2: Calendar): Boolean {
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
               cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
               cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH)
    }

    /**
     * Affiche un message d'erreur
     */
    private fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    /**
     * Rafraîchit les données publiquement (appelé depuis MainActivity)
     */
    fun refreshData() {
        Log.d(TAG, "Rafraîchissement demandé depuis MainActivity")
        loadDataAndRefresh()
    }

    // ══════════════════════════════════════════════════
    // CYCLE DE VIE
    // ══════════════════════════════════════════════════

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume - Rafraîchissement des données")
        loadDataAndRefresh()
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView - Nettoyage")
    }
}

// ══════════════════════════════════════════════════
// Classe CalendrierLangues pour les traductions
// ══════════════════════════════════════════════════

class CalendrierLangues {
    
    fun getTraductions(langue: String): Map<String, String> {
        return when (langue) {
            "FR" -> mapOf(
                // Jours de la semaine
                "dim" to "Dim",
                "lun" to "Lun",
                "mar" to "Mar",
                "mer" to "Mer",
                "jeu" to "Jeu",
                "ven" to "Ven",
                "sam" to "Sam",
                
                // Labels
                "label_cigarettes" to "Cigarettes",
                "label_joints" to "Joints",
                "label_alcool" to "Alcool",
                "label_alcool_global" to "Alcool global",
                "label_bieres" to "Bières",
                "label_liqueurs" to "Liqueurs",
                "label_alcool_fort" to "Alcool fort",
                
                // Dates arrêt
                "date_reduction" to "Date réduction",
                "date_arret" to "Date d'arrêt",
                "date_reussite" to "Date réussite",
                
                // Boutons
                "btn_mois_precedent" to "◀",
                "btn_mois_suivant" to "▶",
                "btn_aujourdhui" to "Aujourd'hui",
                "btn_hier" to "Hier",
                "btn_demain" to "Demain",
                
                // Légende
                "titre_legende" to "Légende",
                "intensite_consommation" to "Intensité consommation :",
                "faible" to "Faible",
                "moyenne" to "Moyenne",
                "elevee" to "Élevée",
                "tres_elevee" to "Très élevée",
                
                // Profil
                "profil_complet" to "Profil: Complet ✓",
                "profil_incomplet" to "Profil: Incomplet",
                "total_aujourdhui" to "Total aujourd'hui:",
                
                // Messages
                "aucune_consommation" to "Aucune consommation le",
                "details_jour" to "Détails du",
                "details_consommation" to "Détails consommation",
                "total" to "Total",
                "fermer" to "Fermer"
            )
            
            "EN" -> mapOf(
                "dim" to "Sun",
                "lun" to "Mon",
                "mar" to "Tue",
                "mer" to "Wed",
                "jeu" to "Thu",
                "ven" to "Fri",
                "sam" to "Sat",
                "label_cigarettes" to "Cigarettes",
                "label_joints" to "Joints",
                "label_alcool" to "Alcohol",
                "btn_aujourdhui" to "Today",
                "btn_hier" to "Yesterday",
                "btn_demain" to "Tomorrow",
                "profil_complet" to "Profile: Complete ✓",
                "profil_incomplet" to "Profile: Incomplete",
                "total_aujourdhui" to "Today's total:",
                // ... autres traductions EN
                "fermer" to "Close"
            )
            
            // Ajouter ES, PT, DE, IT, RU, AR, HI, JA...
            
            else -> getTraductions("EN") // Fallback to English
        }
    }
}
