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
    private lateinit var trad: Map<String, String>
    
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
    private lateinit var btnSauvegarderHabitudes: Button
    private lateinit var btnEffacerHabitudes: Button
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
            Toast.makeText(requireContext(), "Erreur chargement Habitudes", Toast.LENGTH_SHORT).show()
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
            btnSauvegarderHabitudes = view.findViewById(R.id.habitudes_btn_sauvegarder)
            btnEffacerHabitudes = view.findViewById(R.id.habitudes_btn_effacer)
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
            val langue = configLangue.getLangue()
            trad = HabitudesLangues.getTraductions(langue)
            
            inputMaxCigarettes.hint = trad["hint_max_cigarettes"] ?: "Max cigarettes par jour"
            inputMaxJoints.hint = trad["hint_max_joints"] ?: "Max joints par jour"
            inputMaxAlcoolGlobal.hint = trad["hint_max_alcool_global"] ?: "Max alcool global par jour"
            inputMaxBieres.hint = trad["hint_max_bieres"] ?: "Max bières par jour"
            inputMaxLiqueurs.hint = trad["hint_max_liqueurs"] ?: "Max liqueurs par jour"
            inputMaxAlcoolFort.hint = trad["hint_max_alcool_fort"] ?: "Max alcool fort par jour"
            btnSauvegarderHabitudes.text = trad["btn_sauvegarder"] ?: "Sauvegarder"
            
            Log.d(TAG, "Traductions appliquées pour langue: $langue")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur application traductions: ${e.message}", e)
        }
    }

        private fun setupListeners() {
        // Bouton Sauvegarder : inchangé
        btnSauvegarderHabitudes.setOnClickListener {
            saveHabitudesAndDates()
        }

        // Nouveau : bouton Effacer
        btnEffacerHabitudes.setOnClickListener {
    try {
        // 1) Vider les champs "Max par jour"
        inputMaxCigarettes.setText("")
        inputMaxJoints.setText("")
        inputMaxAlcoolGlobal.setText("")
        inputMaxBieres.setText("")
        inputMaxLiqueurs.setText("")
        inputMaxAlcoolFort.setText("")

        // 2) Remettre les boutons de dates sur le texte par défaut
        val placeholder = trad["btn_selectionner_date"] ?: "Sélectionner une date"
        btnDatesMap.forEach { (_, datesButtons) ->
            datesButtons.values.forEach { button ->
                button.text = placeholder
            }
        }

        // 3) NE PAS sauvegarder ici !
        Toast.makeText(
            requireContext(),
            "Champs réinitialisés — Cliquez sur Sauvegarder pour appliquer",
            Toast.LENGTH_LONG
        ).show()

    } catch (e: Exception) {
        Log.e(TAG, "Erreur lors de la RAZ habitudes/dates: ${e.message}", e)
        Toast.makeText(
            requireContext(),
            "Erreur lors de la réinitialisation",
            Toast.LENGTH_SHORT
        ).show()
        }
    }
}
        
    private fun loadExistingData() {
        try {
            // Charger habitudes
            val maxCig = dbHelper.getMaxJournalier(DatabaseHelper.TYPE_CIGARETTE)
            val maxJoints = dbHelper.getMaxJournalier(DatabaseHelper.TYPE_JOINT)
            val maxAlcoolGlobal = dbHelper.getMaxJournalier(DatabaseHelper.TYPE_ALCOOL_GLOBAL)
            val maxBieres = dbHelper.getMaxJournalier(DatabaseHelper.TYPE_BIERE)
            val maxLiqueurs = dbHelper.getMaxJournalier(DatabaseHelper.TYPE_LIQUEUR)
            val maxAlcoolFort = dbHelper.getMaxJournalier(DatabaseHelper.TYPE_ALCOOL_FORT)
            
            if (maxCig > 0) inputMaxCigarettes.setText(maxCig.toString())
            if (maxJoints > 0) inputMaxJoints.setText(maxJoints.toString())
            if (maxAlcoolGlobal > 0) inputMaxAlcoolGlobal.setText(maxAlcoolGlobal.toString())
            if (maxBieres > 0) inputMaxBieres.setText(maxBieres.toString())
            if (maxLiqueurs > 0) inputMaxLiqueurs.setText(maxLiqueurs.toString())
            if (maxAlcoolFort > 0) inputMaxAlcoolFort.setText(maxAlcoolFort.toString())
            
            updateBandeau()
            
            Log.d(TAG, "Données existantes chargées")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur chargement données: ${e.message}", e)
        }
    }

    private fun updateBandeau() {
    try {
        // 3 blocs : coûts, habitudes, dates
        val totalBlocs = 3
        var blocsRemplis = 0

        // COÛTS
        val hasCouts = categoriesActives.any { (type, active) ->
            if (active) {
                val couts = dbHelper.getCouts(type)
                couts.values.any { it > 0.0 }
            } else false
        }
        if (hasCouts) blocsRemplis++

        // HABITUDES
        val hasHabitudes = categoriesActives.any { (type, active) ->
            active && dbHelper.getMaxJournalier(type) > 0
        }
        if (hasHabitudes) blocsRemplis++

        // DATES
        val hasDates = categoriesActives.any { (type, active) ->
            if (active) {
                val dates = dbHelper.getDatesObjectifs(type)
                dates.values.any { it?.isNotEmpty() == true }
            } else false
        }
        if (hasDates) blocsRemplis++

        // POURCENTAGE
        val percent = (blocsRemplis * 100) / totalBlocs

        // --- AFFICHAGE ---
        txtProfilStatus.text =
            if (percent == 100)
                (trad["profil_complet"] ?: "Profil: Complet ✓") + " 100%"
            else
                (trad["profil_incomplet"] ?: "Profil: Incomplet") + " $percent%"
        
        val iconRes = if (percent == 100) R.drawable.ic_check_black else R.drawable.ic_minus_black
        txtProfilStatus.setCompoundDrawablesWithIntrinsicBounds(iconRes, 0, 0, 0)
        
        profilProgress.progress = percent
        txtProfilRestant.visibility = View.GONE

    } catch (e: Exception) {
        Log.e(TAG, "Erreur update bandeau (progression)", e)
    }
}
    
    private fun buildVolonteSection() {
        try {
            containerVolonte.removeAllViews()
            
            val trad = HabitudesLangues.getTraductions(configLangue.getLangue())
            
            // Titre section
            val titreVolonte = TextView(requireContext()).apply {
                text = trad["titre_volonte"] ?: "Zone Volonté - Dates Objectifs"
                textSize = 18f
                setTypeface(null, android.graphics.Typeface.BOLD)
                setPadding(0, 0, 0, 16)
            }
            containerVolonte.addView(titreVolonte)
            
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
            if (currentText != trad["btn_selectionner_date"] ?: "Sélectionner une date") {
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
            Toast.makeText(requireContext(), "Erreur: ${e.message}", Toast.LENGTH_SHORT).show()
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
    
    private fun saveHabitudesAndDates() {
    try {
        val trad = HabitudesLangues.getTraductions(configLangue.getLangue())
        val placeholder = trad["btn_selectionner_date"] ?: "Sélectionner une date"

        // -----------------------------
        // 1) Sauvegarde des habitudes
        // -----------------------------
        fun safeInt(input: EditText): Int {
            val v = input.text.toString().trim()
            return v.toIntOrNull() ?: 0
        }

        val maxCig = safeInt(inputMaxCigarettes)
        val maxJoints = safeInt(inputMaxJoints)
        val maxAlcoolGlobal = safeInt(inputMaxAlcoolGlobal)
        val maxBieres = safeInt(inputMaxBieres)
        val maxLiqueurs = safeInt(inputMaxLiqueurs)
        val maxAlcoolFort = safeInt(inputMaxAlcoolFort)

        dbHelper.setMaxJournalier(DatabaseHelper.TYPE_CIGARETTE, maxCig)
        dbHelper.setMaxJournalier(DatabaseHelper.TYPE_JOINT, maxJoints)
        dbHelper.setMaxJournalier(DatabaseHelper.TYPE_ALCOOL_GLOBAL, maxAlcoolGlobal)
        dbHelper.setMaxJournalier(DatabaseHelper.TYPE_BIERE, maxBieres)
        dbHelper.setMaxJournalier(DatabaseHelper.TYPE_LIQUEUR, maxLiqueurs)
        dbHelper.setMaxJournalier(DatabaseHelper.TYPE_ALCOOL_FORT, maxAlcoolFort)

        // -----------------------------
        // 2) Sauvegarde des dates
        // -----------------------------
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
            trad["msg_sauvegarde_ok"] ?: "Habitudes et dates sauvegardées",
            Toast.LENGTH_SHORT
        ).show()

        updateBandeau()

        // Notifier MainActivity pour refresh
        (activity as? MainActivity)?.refreshData()

    } catch (e: Exception) {
        Log.e(TAG, "Erreur sauvegarde: ${e.message}", e)
        Toast.makeText(requireContext(), "Erreur: ${e.message}", Toast.LENGTH_SHORT).show()
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











