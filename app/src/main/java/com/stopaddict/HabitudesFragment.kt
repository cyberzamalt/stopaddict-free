package com.stopaddict

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class HabitudesFragment : Fragment() {

    companion object {
        private const val TAG = "HabitudesFragment"
    }

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var configLangue: ConfigLangue
    private var trad: Map<String, String> = emptyMap()
    
    private lateinit var txtProfilStatus: TextView
    private lateinit var profilProgress: ProgressBar
    private lateinit var txtProfilRestant: TextView
    
    // Section Habitudes
    private lateinit var inputMaxCigarettes: EditText
    private lateinit var inputMaxJoints: EditText
    private lateinit var inputMaxAlcoolGlobal: EditText
    private lateinit var inputMaxBieres: EditText
    private lateinit var inputMaxLiqueurs: EditText    
    private lateinit var inputMaxAlcoolFort: EditText
    private lateinit var txtTitreHabitudes: TextView

    // Périodes (Jour / Semaine / Mois) - 1 par catégorie
    private lateinit var periodCigarettes: RadioGroup
    private lateinit var periodJoints: RadioGroup
    private lateinit var periodAlcoolGlobal: RadioGroup
    private lateinit var periodBieres: RadioGroup
    private lateinit var periodLiqueurs: RadioGroup
    private lateinit var periodAlcoolFort: RadioGroup    
    
    private lateinit var txtTitreVolonte: TextView
    
    private lateinit var btnSauvegarderHabitudes: Button
    private lateinit var btnEffacerHabitudes: Button
    private lateinit var btnSauvegarderDates: Button
    private lateinit var btnEffacerDates: Button
    
    private lateinit var containerVolonte: LinearLayout

    // Map des boutons dates par catégorie
    private val btnDatesMap = mutableMapOf<String, MutableMap<String, Button>>()
    
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
        return inflater.inflate(R.layout.fragment_habitudes, container, false)
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
            loadExistingData()
            buildVolonteSection()
            
            Log.d(TAG, "HabitudesFragment initialisé avec succès")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur initialisation: ${e.message}", e)
            Toast.makeText(
                requireContext(),
                trad["msg_err_chargement_habitudes"] ?: "Erreur chargement Habitudes",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun initializeViews(view: View) {
        try {
            txtProfilStatus = view.findViewById(R.id.habitudes_profil_status)
            profilProgress = view.findViewById(R.id.habitudes_profil_progress)
            txtProfilRestant = view.findViewById(R.id.habitudes_txt_profil_restant)
            
            inputMaxCigarettes = view.findViewById(R.id.habitudes_input_max_cigarettes)
            inputMaxJoints = view.findViewById(R.id.habitudes_input_max_joints)
            inputMaxAlcoolGlobal = view.findViewById(R.id.habitudes_input_max_alcool_global)
            inputMaxBieres = view.findViewById(R.id.habitudes_input_max_bieres)
            inputMaxLiqueurs = view.findViewById(R.id.habitudes_input_max_liqueurs)
            inputMaxAlcoolFort = view.findViewById(R.id.habitudes_input_max_alcool_fort)

            periodCigarettes = view.findViewById(R.id.habitudes_period_cigarettes)
            periodJoints = view.findViewById(R.id.habitudes_period_joints)
            periodAlcoolGlobal = view.findViewById(R.id.habitudes_period_alcool_global)
            periodBieres = view.findViewById(R.id.habitudes_period_bieres)
            periodLiqueurs = view.findViewById(R.id.habitudes_period_liqueurs)
            periodAlcoolFort = view.findViewById(R.id.habitudes_period_alcool_fort)

            txtTitreHabitudes = view.findViewById(R.id.habitudes_txt_titre_habitudes)
            txtTitreVolonte = view.findViewById(R.id.habitudes_txt_titre_volonte)
            
            btnSauvegarderHabitudes = view.findViewById(R.id.habitudes_btn_sauvegarder_habitudes)
            btnEffacerHabitudes = view.findViewById(R.id.habitudes_btn_effacer_habitudes)
            
            btnSauvegarderDates = view.findViewById(R.id.habitudes_btn_sauvegarder_dates)
            btnEffacerDates = view.findViewById(R.id.habitudes_btn_effacer_dates)
            
            containerVolonte = view.findViewById(R.id.habitudes_container_volonte)
            
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
            trad = emptyMap()
            val langue = configLangue.getLangue()
            trad = HabitudesLangues.getTraductions(langue)
            
            inputMaxCigarettes.hint = trad["hint_max_cigarettes"] ?: "Max cigarettes par jour"
            inputMaxJoints.hint = trad["hint_max_joints"] ?: "Max joints par jour"
            inputMaxAlcoolGlobal.hint = trad["hint_max_alcool_global"] ?: "Max alcool global par jour"
            inputMaxBieres.hint = trad["hint_max_bieres"] ?: "Max bières par jour"
            inputMaxLiqueurs.hint = trad["hint_max_liqueurs"] ?: "Max liqueurs par jour"
            inputMaxAlcoolFort.hint = trad["hint_max_alcool_fort"] ?: "Max alcool fort par jour"
            txtTitreHabitudes.text = trad["titre_habitudes"] ?: "Habitudes"
            txtTitreVolonte.text = trad["titre_volonte"] ?: "Volonté"
            
            btnEffacerHabitudes.text = trad["btn_effacer"] ?: "Effacer"
            btnSauvegarderHabitudes.text = trad["btn_sauvegarder"] ?: "Sauvegarder"
            
            btnEffacerDates.text = trad["btn_effacer"] ?: "Effacer"
            btnSauvegarderDates.text = trad["btn_sauvegarder"] ?: "Sauvegarder"
            
            Log.d(TAG, "Traductions appliquées pour langue: $langue")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur application traductions: ${e.message}", e)
        }
    }

private fun setupListeners() {

    // Sauvegarder Habitudes uniquement
    btnSauvegarderHabitudes.setOnClickListener {
        saveHabitudesOnly()
    }

    // Effacer Habitudes (UI seulement)
    btnEffacerHabitudes.setOnClickListener {
        try {
            inputMaxCigarettes.setText("")
            inputMaxJoints.setText("")
            inputMaxAlcoolGlobal.setText("")
            inputMaxBieres.setText("")
            inputMaxLiqueurs.setText("")
            inputMaxAlcoolFort.setText("")

            periodCigarettes.clearCheck()
            periodJoints.clearCheck()
            periodAlcoolGlobal.clearCheck()
            periodBieres.clearCheck()
            periodLiqueurs.clearCheck()
            periodAlcoolFort.clearCheck()

            Toast.makeText(
                requireContext(),
                trad["msg_reset_habitudes"] ?: "Habitudes réinitialisées — Cliquez sur Sauvegarder pour appliquer",
                Toast.LENGTH_LONG
            ).show()

        } catch (e: Exception) {
            Log.e(TAG, "Erreur RAZ habitudes: ${e.message}", e)
            Toast.makeText(
                requireContext(),
                trad["msg_err_reset"] ?: "Erreur lors de la réinitialisation",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // Sauvegarder Dates uniquement
    btnSauvegarderDates.setOnClickListener {
        saveDatesOnly()
    }

    // Effacer Dates (UI seulement)
    btnEffacerDates.setOnClickListener {
        try {
            val placeholder = trad["btn_selectionner_date"] ?: "Sélectionner une date"
            btnDatesMap.forEach { (_, datesButtons) ->
                datesButtons.values.forEach { button ->
                    button.text = placeholder
                }
            }

            Toast.makeText(
                requireContext(),
                trad["msg_reset_dates"] ?: "Dates réinitialisées — Cliquez sur Sauvegarder pour appliquer",
                Toast.LENGTH_LONG
            ).show()

        } catch (e: Exception) {
            Log.e(TAG, "Erreur RAZ dates: ${e.message}", e)
            Toast.makeText(
                requireContext(),
                trad["msg_err_reset"] ?: "Erreur lors de la réinitialisation",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
        
    private fun loadExistingData() {
    try {
        val maxCig = dbHelper.getMaxJournalier(DatabaseHelper.TYPE_CIGARETTE)
        val maxJoints = dbHelper.getMaxJournalier(DatabaseHelper.TYPE_JOINT)
        val maxAlcoolGlobal = dbHelper.getMaxJournalier(DatabaseHelper.TYPE_ALCOOL_GLOBAL)
        val maxBieres = dbHelper.getMaxJournalier(DatabaseHelper.TYPE_BIERE)
        val maxLiqueurs = dbHelper.getMaxJournalier(DatabaseHelper.TYPE_LIQUEUR)
        val maxAlcoolFort = dbHelper.getMaxJournalier(DatabaseHelper.TYPE_ALCOOL_FORT)

        val cigTxt = formatDaily(maxCig)
        val jointsTxt = formatDaily(maxJoints)
        val alcoolGlobalTxt = formatDaily(maxAlcoolGlobal)
        val bieresTxt = formatDaily(maxBieres)
        val liqueursTxt = formatDaily(maxLiqueurs)
        val alcoolFortTxt = formatDaily(maxAlcoolFort)

        if (cigTxt.isNotEmpty()) inputMaxCigarettes.setText(cigTxt)
        if (jointsTxt.isNotEmpty()) inputMaxJoints.setText(jointsTxt)
        if (alcoolGlobalTxt.isNotEmpty()) inputMaxAlcoolGlobal.setText(alcoolGlobalTxt)
        if (bieresTxt.isNotEmpty()) inputMaxBieres.setText(bieresTxt)
        if (liqueursTxt.isNotEmpty()) inputMaxLiqueurs.setText(liqueursTxt)
        if (alcoolFortTxt.isNotEmpty()) inputMaxAlcoolFort.setText(alcoolFortTxt)

        updateBandeau()
        Log.d(TAG, "Données existantes chargées")
    } catch (e: Exception) {
        Log.e(TAG, "Erreur chargement données: ${e.message}", e)
    }
}

    private fun updateBandeau() {
    try {
        val percent = dbHelper.getProfilCompletionPercent(categoriesActives)

        // --- AFFICHAGE ---
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
        txtProfilRestant.visibility = View.VISIBLE

    } catch (e: Exception) {
        Log.e(TAG, "Erreur update bandeau (progression)", e)
    }
}
    
    private fun buildVolonteSection() {
        try {
            containerVolonte.removeAllViews()
            
            val trad = HabitudesLangues.getTraductions(configLangue.getLangue())
            
            // Description
            val description = TextView(requireContext()).apply {
                text = trad["desc_volonte"] ?: "Définissez 3 dates par catégorie : Réduction, Arrêt, Réussite"
                textSize = 14f
                setPadding(0, 0, 0, 20)
            }
            containerVolonte.addView(description)
            
            // Pour chaque catégorie active, créer section dates
            categoriesActives.forEach { (type, active) ->
                if (active) {
                    buildCategorieVolonte(type, trad)
                }
            }
            
            Log.d(TAG, "Section Volonté construite")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur construction section Volonté: ${e.message}", e)
        }
    }
    
    private fun buildCategorieVolonte(type: String, trad: Map<String, String>) {
        try {
            // Container catégorie
            val containerCategorie = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(16, 16, 16, 16)
                setBackgroundResource(android.R.drawable.dialog_holo_light_frame)
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(0, 0, 0, 16)
                layoutParams = params
            }
            
            // Titre catégorie
            val titreCategorie = TextView(requireContext()).apply {
                text = getNomCategorie(type, trad)
                textSize = 16f
                setTypeface(null, android.graphics.Typeface.BOLD)
                setPadding(0, 0, 0, 12)
            }
            containerCategorie.addView(titreCategorie)
            
            // Charger dates existantes
                val dates = dbHelper.getDatesObjectifs(type)
                
                // Initialiser map pour cette catégorie
                btnDatesMap[type] = mutableMapOf()
                
                // Date Réduction
                val btnDateReduction = createDateButton(
                    trad["label_date_reduction"] ?: "Date de réduction",
                    dates["date_reduction"] ?: "",
                    type,
                    "reduction"
                )
                containerCategorie.addView(btnDateReduction)
                
                // Date Arrêt
                val btnDateArret = createDateButton(
                    trad["label_date_arret"] ?: "Date d'arrêt",
                    dates["date_arret"] ?: "",
                    type,
                    "arret"
                )
                containerCategorie.addView(btnDateArret)
                
                // Date Réussite
                val btnDateReussite = createDateButton(
                    trad["label_date_reussite"] ?: "Date de réussite",
                    dates["date_reussite"] ?: "",
                    type,
                    "reussite"
                )
                containerCategorie.addView(btnDateReussite)
            
                containerVolonte.addView(containerCategorie)
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur construction catégorie volonté $type: ${e.message}", e)
        }
    }
    
    private fun createDateButton(label: String, dateStr: String, type: String, dateType: String): LinearLayout {
        val container = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(0, 8, 0, 8)
        }
        
        // Label
        val labelView = TextView(requireContext()).apply {
            text = label
            textSize = 14f
            setPadding(0, 0, 0, 4)
        }
        container.addView(labelView)
        
        // Bouton date
        val btnDate = Button(requireContext()).apply {
            text = if (dateStr.isEmpty()) trad["btn_selectionner_date"] ?: "Sélectionner une date" else dateStr
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            setOnClickListener {
                showDatePicker(type, dateType, this)
            }
        }
        
        // Stocker référence
        if (!btnDatesMap.containsKey(type)) {
            btnDatesMap[type] = mutableMapOf()
        }
        btnDatesMap[type]!![dateType] = btnDate
        
        container.addView(btnDate)
        
        return container
    }
    
    private fun showDatePicker(type: String, dateType: String, button: Button) {
        try {
            val calendar = Calendar.getInstance()
            
            // Si date existante, partir de là
            val currentText = button.text.toString()
            val placeholder = trad["btn_selectionner_date"] ?: "Sélectionner une date"
                if (currentText != placeholder) {
                try {
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val date = dateFormat.parse(currentText)
                    if (date != null) {
                        calendar.time = date
                    }
                } catch (e: Exception) {
                    Log.w(TAG, "Date invalide dans bouton: $currentText")
                }
            }
            
            DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    val dateStr = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
                    button.text = dateStr
                    Log.d(TAG, "Date sélectionnée: $dateStr pour $type - $dateType")
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur affichage DatePicker: ${e.message}", e)
            Toast.makeText(
                requireContext(),
                trad["msg_err_generic"] ?: "Une erreur est survenue",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    
    private fun getNomCategorie(type: String, trad: Map<String, String>): String {
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

private fun safeDouble(input: EditText): Double {
    val v = input.text.toString().trim()
    return v.replace(',', '.').toDoubleOrNull() ?: 0.0
}

private fun periodDivisor(checkedId: Int, jourId: Int, semaineId: Int, moisId: Int): Double {
    return when (checkedId) {
        jourId -> 1.0
        semaineId -> 7.0
        moisId -> 30.0
        else -> 1.0 // aucun choix = comportement historique = "jour"
    }
}

private fun normalizeToDaily(value: Double, checkedId: Int, jourId: Int, semaineId: Int, moisId: Int): Double {
    if (value <= 0.0) return 0.0
    val d = periodDivisor(checkedId, jourId, semaineId, moisId)
    return value / d
}

private fun formatDaily(v: Double): String {
    if (v <= 0.0) return ""
    val asInt = v.toInt()
    return if (kotlin.math.abs(v - asInt.toDouble()) < 0.000001) asInt.toString() else String.format(Locale.US, "%.3f", v).trimEnd('0').trimEnd('.')
}
    
private fun saveHabitudesOnly() {
    try {
        val cigRaw = safeDouble(inputMaxCigarettes)
        val jointsRaw = safeDouble(inputMaxJoints)
        val alcoolGlobalRaw = safeDouble(inputMaxAlcoolGlobal)
        val bieresRaw = safeDouble(inputMaxBieres)
        val liqueursRaw = safeDouble(inputMaxLiqueurs)
        val alcoolFortRaw = safeDouble(inputMaxAlcoolFort)

        val cigDaily = normalizeToDaily(
            cigRaw,
            periodCigarettes.checkedRadioButtonId,
            R.id.habitudes_cigarettes_jour,
            R.id.habitudes_cigarettes_semaine,
            R.id.habitudes_cigarettes_mois
        )

        val jointsDaily = normalizeToDaily(
            jointsRaw,
            periodJoints.checkedRadioButtonId,
            R.id.habitudes_joints_jour,
            R.id.habitudes_joints_semaine,
            R.id.habitudes_joints_mois
        )

        val alcoolGlobalDaily = normalizeToDaily(
            alcoolGlobalRaw,
            periodAlcoolGlobal.checkedRadioButtonId,
            R.id.habitudes_alcool_global_jour,
            R.id.habitudes_alcool_global_semaine,
            R.id.habitudes_alcool_global_mois
        )

        val bieresDaily = normalizeToDaily(
            bieresRaw,
            periodBieres.checkedRadioButtonId,
            R.id.habitudes_bieres_jour,
            R.id.habitudes_bieres_semaine,
            R.id.habitudes_bieres_mois
        )

        val liqueursDaily = normalizeToDaily(
            liqueursRaw,
            periodLiqueurs.checkedRadioButtonId,
            R.id.habitudes_liqueurs_jour,
            R.id.habitudes_liqueurs_semaine,
            R.id.habitudes_liqueurs_mois
        )

        val alcoolFortDaily = normalizeToDaily(
            alcoolFortRaw,
            periodAlcoolFort.checkedRadioButtonId,
            R.id.habitudes_alcool_fort_jour,
            R.id.habitudes_alcool_fort_semaine,
            R.id.habitudes_alcool_fort_mois
        )

        dbHelper.setMaxJournalier(DatabaseHelper.TYPE_CIGARETTE, cigDaily)
        dbHelper.setMaxJournalier(DatabaseHelper.TYPE_JOINT, jointsDaily)
        dbHelper.setMaxJournalier(DatabaseHelper.TYPE_ALCOOL_GLOBAL, alcoolGlobalDaily)
        dbHelper.setMaxJournalier(DatabaseHelper.TYPE_BIERE, bieresDaily)
        dbHelper.setMaxJournalier(DatabaseHelper.TYPE_LIQUEUR, liqueursDaily)
        dbHelper.setMaxJournalier(DatabaseHelper.TYPE_ALCOOL_FORT, alcoolFortDaily)

        Toast.makeText(
            requireContext(),
            trad["msg_sauvegarde_habitudes_ok"] ?: "Habitudes sauvegardées",
            Toast.LENGTH_SHORT
        ).show()

        updateBandeau()
        (activity as? MainActivity)?.refreshData()

    } catch (e: Exception) {
        Log.e(TAG, "Erreur sauvegarde habitudes: ${e.message}", e)
        Toast.makeText(
            requireContext(),
            trad["msg_err_generic"] ?: "Une erreur est survenue",
            Toast.LENGTH_SHORT
        ).show()
    }
}

private fun saveDatesOnly() {
    try {
        val placeholder = trad["btn_selectionner_date"] ?: "Sélectionner une date"

        fun safeDate(btn: Button): String {
            val t = btn.text.toString().trim()
            return if (t.isNotEmpty() && t != placeholder) t else ""
        }

        btnDatesMap.forEach { (type, datesButtons) ->
            val dReduction = datesButtons["reduction"]?.let { safeDate(it) } ?: ""
            val dArret = datesButtons["arret"]?.let { safeDate(it) } ?: ""
            val dReussite = datesButtons["reussite"]?.let { safeDate(it) } ?: ""

            dbHelper.setDatesObjectifs(type, dReduction, dArret, dReussite)
            Log.d(TAG, "Dates sauvegardées pour $type : R=$dReduction A=$dArret S=$dReussite")
        }

        Toast.makeText(
            requireContext(),
            trad["msg_sauvegarde_dates_ok"] ?: "Dates sauvegardées",
            Toast.LENGTH_SHORT
        ).show()

        updateBandeau()
        (activity as? MainActivity)?.refreshData()

    } catch (e: Exception) {
        Log.e(TAG, "Erreur sauvegarde dates: ${e.message}", e)
        Toast.makeText(
            requireContext(),
            trad["msg_err_generic"] ?: "Une erreur est survenue",
            Toast.LENGTH_SHORT
        ).show()
    }
}

    fun refreshData() {
        try {
            loadCategoriesActives()
            loadExistingData()
            buildVolonteSection()
            Log.d(TAG, "Données rafraîchies")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur refresh data: ${e.message}", e)
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            loadCategoriesActives()
            loadExistingData()
            buildVolonteSection()
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
















