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
    private lateinit var profilProgress: ProgressBar
    private lateinit var txtProfilRestant: TextView
    
    private lateinit var txtMoisAnnee: TextView
    private lateinit var btnPrecedent: Button
    private lateinit var btnAujourdhui: Button
    private lateinit var btnSuivant: Button
    private lateinit var gridCalendrier: GridLayout
    
    private val currentCalendar = Calendar.getInstance()
    private val categoriesActives = mutableMapOf<String, Boolean>()

    // Hauteurs (dp) pour les cases du calendrier
    private val CELL_MIN_HEIGHT_DP = 72
    private val CELL_MID_HEIGHT_DP = 108
    private val CELL_MAX_HEIGHT_DP = 140

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
        container.orientation = LinearLayout.VERTICAL

        txtProfilStatus = view.findViewById(R.id.calendrier_profil_status)
        profilProgress = view.findViewById(R.id.calendrier_profil_progress)
        txtProfilRestant = view.findViewById(R.id.calendrier_txt_profil_restant)
                
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
        
        addLegendeItem(legendeContainer, Color.parseColor("#FFFFFF"), trad["legende_vert"] ?: "Aucune consommation")
        addLegendeItem(legendeContainer, Color.parseColor("#E3F2FD"), trad["legende_bleu"] ?: "Faible consommation (1–5)")
        addLegendeItem(legendeContainer, Color.parseColor("#FFF3E0"), trad["legende_orange"] ?: "Consommation modérée (6–15)")
        addLegendeItem(legendeContainer, Color.parseColor("#FFCDD2"), trad["legende_rouge"] ?: "Consommation élevée (16+)")

        val legendeObjectifs = TextView(requireContext()).apply {
                text = trad["legende_objectifs"] ?: "Objectifs :"
                textSize = 14f
                setTypeface(null, android.graphics.Typeface.BOLD)
                setPadding(0, 15, 0, 5)
            }
            legendeContainer.addView(legendeObjectifs)
            
            addLegendeItem(legendeContainer, Color.TRANSPARENT, "🐢 ${trad["legende_reduction"] ?: "Réduction"}")
            addLegendeItem(legendeContainer, Color.TRANSPARENT, "🛑 ${trad["legende_arret"] ?: "Arrêt"}")
            addLegendeItem(legendeContainer, Color.TRANSPARENT, "✅ ${trad["legende_reussite"] ?: "Objectif atteint"}")

        val legendeConso = TextView(requireContext()).apply {
                text = trad["legende_conso"] ?: "Consommations :"
                textSize = 14f
                setTypeface(null, android.graphics.Typeface.BOLD)
                setPadding(0, 15, 0, 5)
            }
            legendeContainer.addView(legendeConso)
            
            addLegendeItem(legendeContainer, Color.TRANSPARENT, "🚬 ${trad["label_cigarettes"] ?: "Cigarettes"}")
            addLegendeItem(legendeContainer, Color.TRANSPARENT, "🌿 ${trad["label_joints"] ?: "Joints"}")
            addLegendeItem(legendeContainer, Color.TRANSPARENT, "🍺 ${trad["label_bieres"] ?: "Bières"}")
            addLegendeItem(legendeContainer, Color.TRANSPARENT, "🍷 ${trad["label_liqueurs"] ?: "Liqueurs"}")
            addLegendeItem(legendeContainer, Color.TRANSPARENT, "🥃 ${trad["label_alcool_fort"] ?: "Alcool fort"}")
            addLegendeItem(legendeContainer, Color.TRANSPARENT, "🥃G ${trad["label_alcool_global"] ?: "Alcool global"}")
    
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
            val type = object : com.google.gson.reflect.TypeToken<Map<String, Boolean>>() {}.type
            val map: Map<String, Boolean> = gson.fromJson(json, type)
            
            categoriesActives.clear()
            categoriesActives.putAll(map)
        } catch (e: Exception) {
            Log.e(TAG, "Erreur load categories", e)
        }
    }

    private fun updateProfilStatus() {
    try {
        // --- POURCENTAGE (centralisé) ---
        val percent = dbHelper.getProfilCompletionPercent(categoriesActives)

        // --- AFFICHAGE (inchangé) ---
        txtProfilStatus.text =
            if (percent == 100)
                (trad["profil_complet"] ?: "Profil: Complet ✓") + " 100%"
            else
                (trad["profil_incomplet"] ?: "Profil: Incomplet") + " $percent%"

        val iconRes = if (percent == 100)
            R.drawable.ic_status_complete
        else
            R.drawable.ic_status_incomplete

        txtProfilStatus.setCompoundDrawablesWithIntrinsicBounds(iconRes, 0, 0, 0)

        profilProgress.progress = percent
        txtProfilRestant.visibility = View.INVISIBLE

    } catch (e: Exception) {
        Log.e(TAG, "Erreur updateProfilStatus (progression)", e)
    }
}

    private fun dp(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

    // "Score" de contenu d'une journée : sert uniquement à choisir une hauteur
// IMPORTANT : ici on ne touche PLUS aux objectifs (sinon ça ralentit)
private fun computeDayContentScore(dateStr: String): Int {
    var score = 0

    // Consommations actives > 0 : 1 ligne par conso
    if (categoriesActives["cigarette"] == true && dbHelper.getConsommationParDate("cigarette", dateStr) > 0) score++
    if (categoriesActives["joint"] == true && dbHelper.getConsommationParDate("joint", dateStr) > 0) score++
    if (categoriesActives["alcool_global"] == true && dbHelper.getConsommationParDate("alcool_global", dateStr) > 0) score++
    if (categoriesActives["biere"] == true && dbHelper.getConsommationParDate("biere", dateStr) > 0) score++
    if (categoriesActives["liqueur"] == true && dbHelper.getConsommationParDate("liqueur", dateStr) > 0) score++
    if (categoriesActives["alcool_fort"] == true && dbHelper.getConsommationParDate("alcool_fort", dateStr) > 0) score++

    return score
}

    private fun weekHeightDp(weekMaxScore: Int): Int {
        return when {
            weekMaxScore <= 0 -> CELL_MIN_HEIGHT_DP
            weekMaxScore <= 2 -> CELL_MID_HEIGHT_DP
            else -> CELL_MAX_HEIGHT_DP
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
        val params = GridLayout.LayoutParams().apply {
            width = 0
            height = ViewGroup.LayoutParams.WRAP_CONTENT
            columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
        }
        headerView.layoutParams = params
        gridCalendrier.addView(headerView)
    }

            
            val cal = currentCalendar.clone() as Calendar
            cal.set(Calendar.DAY_OF_MONTH, 1)
            var firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY
            if (firstDayOfWeek < 0) firstDayOfWeek += 7
            
            val daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
            
            // Espaces vides
    for (i in 0 until firstDayOfWeek) {
        val emptyView = TextView(requireContext()).apply {
            text = ""
            setPadding(4, 4, 4, 4)
        }
        val params = GridLayout.LayoutParams().apply {
            width = 0
            height = ViewGroup.LayoutParams.WRAP_CONTENT
            columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
        }
        emptyView.layoutParams = params
        gridCalendrier.addView(emptyView)
    }           
            // Jours du mois
            val dateFormatJour = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

                        // --- Pré-calcul hauteur par semaine (ligne) ---
            // Index de cellule (en jours) depuis le début de la grille (sans compter l'entête)
            // On inclut les cases vides du début pour rester aligné.
            val weekMaxScores = mutableMapOf<Int, Int>()  // weekIndex -> maxScore

            // Pré-calcul des dates d'objectifs (réduction / arrêt / réussite) pour les catégories actives
            val datesReduction = mutableSetOf<String>()
            val datesArret = mutableSetOf<String>()
            val datesReussite = mutableSetOf<String>()
            
            categoriesActives.forEach { (type, active) ->
                if (active) {
                    val dates = dbHelper.getDatesObjectifs(type)
                    dates["date_reduction"]?.takeIf { it.isNotEmpty() }?.let { datesReduction.add(it) }
                    dates["date_arret"]?.takeIf { it.isNotEmpty() }?.let { datesArret.add(it) }
                    dates["date_reussite"]?.takeIf { it.isNotEmpty() }?.let { datesReussite.add(it) }
                }
            }

                        // --- PASS 1 : calcul du max de contenu par semaine (avant de dessiner) ---
            for (d in 1..daysInMonth) {
                cal.set(Calendar.DAY_OF_MONTH, d)
                val ds = dateFormatJour.format(cal.time)

                val cellIndex = (firstDayOfWeek + (d - 1))
                val w = cellIndex / 7

                var s = computeDayContentScore(ds)

                // Objectifs = +1 ligne si au moins un objectif ce jour (sans refaire d'accès DB)
                if (datesReduction.contains(ds) || datesArret.contains(ds) || datesReussite.contains(ds)) {
                    s += 1
                }
                val prev = weekMaxScores[w] ?: 0
                if (s > prev) weekMaxScores[w] = s
            }
            
                for (day in 1..daysInMonth) {
                    cal.set(Calendar.DAY_OF_MONTH, day)
                    val dateStr = dateFormatJour.format(cal.time)
                    // weekIndex = 0 pour la première ligne de jours du mois (après les vides initiaux)
                    val cellIndexFromMonthStart = (firstDayOfWeek + (day - 1))
                    val weekIndex = cellIndexFromMonthStart / 7

                    
                    // Total consommations du jour
                    var totalDay = 0
                    categoriesActives.forEach { (type, active) ->
                        if (active) {
                            totalDay += dbHelper.getConsommationParDate(type, dateStr)
                        }
                    }
                
                                        // --- Lignes affichées dans la case (1 ligne = 1 icône + nombre, seulement si > 0) ---
                    val lines = mutableListOf<String>()

                    val cCig = if (categoriesActives["cigarette"] == true) dbHelper.getConsommationParDate("cigarette", dateStr) else 0
                    val cJoi = if (categoriesActives["joint"] == true) dbHelper.getConsommationParDate("joint", dateStr) else 0
                    val cAlg = if (categoriesActives["alcool_global"] == true) dbHelper.getConsommationParDate("alcool_global", dateStr) else 0
                    val cBie = if (categoriesActives["biere"] == true) dbHelper.getConsommationParDate("biere", dateStr) else 0
                    val cLiq = if (categoriesActives["liqueur"] == true) dbHelper.getConsommationParDate("liqueur", dateStr) else 0
                    val cFor = if (categoriesActives["alcool_fort"] == true) dbHelper.getConsommationParDate("alcool_fort", dateStr) else 0

                    // Empêche les retours à la ligne au milieu (Word Joiner)
                    val WJ = "\u2060"
                    
                    // ...
                    if (cCig > 0) lines.add("🚬${WJ}$cCig")
                    if (cJoi > 0) lines.add("🌿${WJ}$cJoi")
                    if (cBie > 0) lines.add("🍺${WJ}$cBie")
                    if (cLiq > 0) lines.add("🍷${WJ}$cLiq")
                    if (cFor > 0) lines.add("🥃${WJ}$cFor")
                    
                    // IMPORTANT : alcool global → colle TOUT : 🥃 + G + nombre
                    if (cAlg > 0) lines.add("🥃G\u00A0$cAlg")

                    val isReduction = datesReduction.contains(dateStr)
                    val isArret = datesArret.contains(dateStr)
                    val isReussite = datesReussite.contains(dateStr)


                    // Objectifs (icônes uniquement, pas de texte)
                    val WJ = "\u2060"
                    if (isReduction) lines.add("🐢${WJ}Ral")
                    if (isArret) lines.add("🛑${WJ}Ar")
                    if (isReussite) lines.add("✅${WJ}Réu")

                    // Texte final : 1ère ligne = jour, puis lignes de contenu
                    val finalLabel = buildString {
                        append(day)
                        if (lines.isNotEmpty()) {
                            append("\n")
                            append(lines.joinToString("\n"))
                        }
                    }                    

                    val dayView = TextView(requireContext()).apply {
                        text = finalLabel
                        textSize = 14f
                        setPadding(8, 12, 8, 12)
                        gravity = android.view.Gravity.TOP or android.view.Gravity.CENTER_HORIZONTAL
                        maxLines = 7
                
                        // Couleurs SOBRES selon total
                        val bgColor = when {
                            totalDay == 0 -> Color.parseColor("#FFFFFF")         // aucune conso : fond neutre
                            totalDay in 1..5 -> Color.parseColor("#E3F2FD")      // bleu très pâle
                            totalDay in 6..15 -> Color.parseColor("#FFF3E0")     // orange très pâle
                            else -> Color.parseColor("#FFCDD2")                  // rouge pâle
                        }
                        setBackgroundColor(bgColor)
                
                        // Bordure pour aujourd'hui
                        if (dateStr == today) {
                            setBackgroundResource(android.R.drawable.editbox_background)
                            setTextColor(Color.parseColor("#1976D2"))
                            setTypeface(null, android.graphics.Typeface.BOLD)
                        }
                
                        setOnClickListener {
                            showDayDialog(dateStr)
                        }
                    }
                    val params = GridLayout.LayoutParams().apply {
                        width = 0
                        val hDp = weekHeightDp(weekMaxScores[weekIndex] ?: 0)
                        height = dp(hDp)
                        columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                    }
                    dayView.layoutParams = params
                    gridCalendrier.addView(dayView)
                }            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur update calendar", e)
        }
    }

    private fun showDayDialog(dateStr: String) {
        try {
            val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            val parsedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateStr)
            
            // Si la date est invalide, on sort proprement
            if (parsedDate == null) {
                Log.e(TAG, "showDayDialog: date invalide pour dateStr=$dateStr")
                return
            }
            
            val dateFormatted = dateFormat.format(parsedDate)
            
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
                    
                    val labelTexte = when (type) {
    "cigarette" -> trad["label_cigarettes"] ?: "Cigarettes"
    "joint" -> trad["label_joints"] ?: "Joints"
    "alcool_global" -> trad["label_alcool_global"] ?: "Alcool global"
    "biere" -> trad["label_bieres"] ?: "Bières"
    "liqueur" -> trad["label_liqueurs"] ?: "Liqueurs"
    "alcool_fort" -> trad["label_alcool_fort"] ?: "Alcool fort"
    else -> type
}

val emoji = when (type) {
    "cigarette" -> "🚬"
    "joint" -> "🌿"
    "alcool_global" -> "🥃G"
    "biere" -> "🍺"
    "liqueur" -> "🍷"
    "alcool_fort" -> "🥃"
    else -> "•"
}

val typeLabel = TextView(requireContext()).apply {
    text = "$emoji $labelTexte"
    textSize = 14f
    setTypeface(null, android.graphics.Typeface.BOLD)
    setPadding(0, 14, 0, 6)
}
container.addView(typeLabel)

val editText = EditText(requireContext()).apply {
    setText(currentCount.toString())
    inputType = android.text.InputType.TYPE_CLASS_NUMBER
    hint = "0"

    // style proche screenshot
    setPadding(24, 18, 24, 18)
    setBackgroundResource(android.R.drawable.edit_text)
    gravity = android.view.Gravity.CENTER
    setSingleLine(true)

    layoutParams = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    ).apply {
        setMargins(0, 0, 0, 12)
    }
}
container.addView(editText)
editFields[type] = editText
                }
            }
            
            val scrollView = ScrollView(requireContext())
            scrollView.addView(container)
            
            val dialog = android.app.AlertDialog.Builder(requireContext())
    .setView(scrollView)
    .setPositiveButton(trad["btn_sauvegarder"] ?: "Enregistrer") { _, _ ->
        saveConsommationsForDate(dateStr, editFields)
    }
    .setNegativeButton(trad["btn_annuler"] ?: "Annuler", null)
    .create()

dialog.setOnShowListener {
    val btnOk = dialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE)
    val btnCancel = dialog.getButton(android.app.AlertDialog.BUTTON_NEGATIVE)

    // vert sauvegarder
    btnOk.setBackgroundColor(Color.parseColor("#43A047"))
    btnOk.setTextColor(Color.WHITE)

    // gris annuler
    btnCancel.setBackgroundColor(Color.parseColor("#E0E0E0"))
    btnCancel.setTextColor(Color.parseColor("#212121"))
}

dialog.show()

                
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

            val todayStr = java.time.LocalDate.now()
                .format(java.time.format.DateTimeFormatter.ISO_DATE)
            
            if (dateStr == todayStr) {
                (activity as? MainActivity)?.refreshHeaderResumeJour()
            }
            
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
