package com.stopaddict

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

/**
 * HabitudesFragment.kt - Partie 1/3
 * Gère les habitudes (max par catégorie) et volonté (dates d'arrêt)
 * Zone Habitudes : max cigarettes, joints, alcools
 * Zone Volonté : 3 dates par catégorie (réduction, arrêt, réussite)
 */
class HabitudesFragment : Fragment() {

    companion object {
        private const val TAG = "HabitudesFragment"
        
        // Clés pour les habitudes
        private const val KEY_MAX_CIGARETTES = "max_cigarettes"
        private const val KEY_MAX_JOINTS = "max_joints"
        private const val KEY_MAX_ALCOOL_GLOBAL = "max_alcool_global"
        private const val KEY_MAX_BIERES = "max_bieres"
        private const val KEY_MAX_LIQUEURS = "max_liqueurs"
        private const val KEY_MAX_ALCOOL_FORT = "max_alcool_fort"
        
        // Clés pour les dates
        private const val DATE_REDUCTION = "_date_reduction"
        private const val DATE_ARRET = "_date_arret"
        private const val DATE_REUSSITE = "_date_reussite"
    }

    // Database et config
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var configLangue: ConfigLangue
    private lateinit var habitudesLangues: HabitudesLangues
    
    // UI Elements - Header
    private lateinit var headerTitle: TextView
    private lateinit var bandeauProfil: LinearLayout
    private lateinit var txtProfilStatus: TextView
    private lateinit var txtTotalJour: TextView
    
    // UI Elements - Zone Habitudes
    private lateinit var containerHabitudes: LinearLayout
    private lateinit var inputMaxCigarettes: EditText
    private lateinit var inputMaxJoints: EditText
    private lateinit var inputMaxAlcoolGlobal: EditText
    private lateinit var inputMaxBieres: EditText
    private lateinit var inputMaxLiqueurs: EditText
    private lateinit var inputMaxAlcoolFort: EditText
    private lateinit var btnSauvegarderHabitudes: Button
    
    // UI Elements - Zone Volonté
    private lateinit var containerVolonte: LinearLayout
    
    // Maps pour stocker les références aux boutons de dates
    private val dateButtons = mutableMapOf<String, Button>()
    
    // Console debug
    private var consoleClickCount = 0
    private var lastConsoleClickTime = 0L
    
    // État actuel
    private val habitudesValues = mutableMapOf<String, Int>()
    private val datesValues = mutableMapOf<String, String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "═══════════════════════════════════════════")
        Log.d(TAG, "HabitudesFragment onCreateView")
        Log.d(TAG, "═══════════════════════════════════════════")
        
        return inflater.inflate(R.layout.fragment_habitudes, container, false)
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
            
            // Charger les données existantes
            loadExistingData()
            
            // Mettre à jour l'affichage
            updateDisplay()
            
            Log.d(TAG, "✓ HabitudesFragment initialisé avec succès")
            
        } catch (e: Exception) {
            Log.e(TAG, "❌ Erreur initialisation HabitudesFragment", e)
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
            habitudesLangues = HabitudesLangues()
            
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
            headerTitle = view.findViewById(R.id.habitudes_header_title)
            bandeauProfil = view.findViewById(R.id.habitudes_bandeau_profil)
            txtProfilStatus = view.findViewById(R.id.habitudes_profil_status)
            txtTotalJour = view.findViewById(R.id.habitudes_total_jour)
            
            // Zone Habitudes
            containerHabitudes = view.findViewById(R.id.habitudes_container_habitudes)
            inputMaxCigarettes = view.findViewById(R.id.habitudes_input_max_cigarettes)
            inputMaxJoints = view.findViewById(R.id.habitudes_input_max_joints)
            inputMaxAlcoolGlobal = view.findViewById(R.id.habitudes_input_max_alcool_global)
            inputMaxBieres = view.findViewById(R.id.habitudes_input_max_bieres)
            inputMaxLiqueurs = view.findViewById(R.id.habitudes_input_max_liqueurs)
            inputMaxAlcoolFort = view.findViewById(R.id.habitudes_input_max_alcool_fort)
            btnSauvegarderHabitudes = view.findViewById(R.id.habitudes_btn_sauvegarder)
            
            // Zone Volonté
            containerVolonte = view.findViewById(R.id.habitudes_container_volonte)
            
            // Configurer les inputs
            configureInputs()
            
            // Construire la zone volonté dynamiquement
            buildVolonteSection()
            
            // Appliquer les traductions
            applyTranslations()
            
            Log.d(TAG, "✓ Vues initialisées")
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur initialisation vues", e)
            throw e
        }
    }

    /**
     * Configure les inputs pour accepter uniquement des nombres
     */
    private fun configureInputs() {
        val inputs = listOf(
            inputMaxCigarettes,
            inputMaxJoints,
            inputMaxAlcoolGlobal,
            inputMaxBieres,
            inputMaxLiqueurs,
            inputMaxAlcoolFort
        )
        
        inputs.forEach { input ->
            input.inputType = InputType.TYPE_CLASS_NUMBER
        }
    }

    /**
     * Construit dynamiquement la section Volonté avec les dates
     */
    private fun buildVolonteSection() {
        try {
            containerVolonte.removeAllViews()
            
            val trad = habitudesLangues.getTraductions(configLangue.getLangue())
            
            // Titre Zone Volonté
            val titleVolonte = TextView(context).apply {
                text = trad["titre_zone_volonte"]
                textSize = 20f
                setTypeface(typeface, android.graphics.Typeface.BOLD)
                setPadding(0, 16, 0, 16)
            }
            containerVolonte.addView(titleVolonte)
            
            // Catégories avec 3 dates chacune
            val categories = listOf(
                "cigarettes" to trad["label_cigarettes"],
                "joints" to trad["label_joints"],
                "alcool_global" to trad["label_alcool_global"],
                "bieres" to trad["label_bieres"],
                "liqueurs" to trad["label_liqueurs"],
                "alcool_fort" to trad["label_alcool_fort"]
            )
            
            for ((categoryKey, categoryLabel) in categories) {
                // Container pour la catégorie
                val categoryContainer = LinearLayout(context).apply {
                    orientation = LinearLayout.VERTICAL
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        setMargins(0, 16, 0, 16)
                    }
                    setBackgroundResource(android.R.drawable.dialog_holo_light_frame)
                    setPadding(16, 16, 16, 16)
                }
                
                // Titre de la catégorie
                val categoryTitle = TextView(context).apply {
                    text = categoryLabel
                    textSize = 18f
                    setTypeface(typeface, android.graphics.Typeface.BOLD)
                    setPadding(0, 0, 0, 8)
                }
                categoryContainer.addView(categoryTitle)
                
                // Les 3 dates
                val dateTypes = listOf(
                    DATE_REDUCTION to trad["date_reduction"],
                    DATE_ARRET to trad["date_arret"],
                    DATE_REUSSITE to trad["date_reussite"]
                )
                
                for ((dateType, dateLabel) in dateTypes) {
                    val dateRow = LinearLayout(context).apply {
                        orientation = LinearLayout.HORIZONTAL
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        ).apply {
                            setMargins(0, 4, 0, 4)
                        }
                    }
                    
                    // Label de la date
                    val label = TextView(context).apply {
                        text = dateLabel
                        textSize = 14f
                        layoutParams = LinearLayout.LayoutParams(
                            0,
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            1f
                        )
                    }
                    dateRow.addView(label)
                    
                    // Bouton pour sélectionner la date
                    val btnDate = Button(context).apply {
                        val key = "${categoryKey}${dateType}"
                        tag = key
                        text = trad["selectionner_date"]
                        textSize = 12f
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        setOnClickListener {
                            showDatePicker(key, this)
                        }
                    }
                    dateButtons[btnDate.tag.toString()] = btnDate
                    dateRow.addView(btnDate)
                    
                    // Bouton effacer
                    val btnClear = Button(context).apply {
                        text = "✕"
                        textSize = 12f
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        ).apply {
                            setMargins(8, 0, 0, 0)
                        }
                        setOnClickListener {
                            clearDate(btnDate.tag.toString())
                        }
                    }
                    dateRow.addView(btnClear)
                    
                    categoryContainer.addView(dateRow)
                }
                
                containerVolonte.addView(categoryContainer)
            }
            
            Log.d(TAG, "✓ Section Volonté construite")
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur construction section Volonté", e)
        }
    }

    /**
     * Applique les traductions
     */
    private fun applyTranslations() {
        try {
            val langue = configLangue.getLangue()
            val trad = habitudesLangues.getTraductions(langue)
            
            // Boutons
            btnSauvegarderHabitudes.text = trad["btn_sauvegarder"]
            
            // Hints pour les inputs
            inputMaxCigarettes.hint = trad["hint_max_cigarettes"]
            inputMaxJoints.hint = trad["hint_max_joints"]
            inputMaxAlcoolGlobal.hint = trad["hint_max_alcool_global"]
            inputMaxBieres.hint = trad["hint_max_bieres"]
            inputMaxLiqueurs.hint = trad["hint_max_liqueurs"]
            inputMaxAlcoolFort.hint = trad["hint_max_alcool_fort"]
            
            Log.d(TAG, "✓ Traductions appliquées pour langue: $langue")
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur application traductions", e)
        }
    }

    /**
     * Configure les listeners
     */
    private fun setupListeners() {
        try {
            // Header - Console debug (5 clics)
            headerTitle.setOnClickListener {
                handleConsoleDebugClick()
            }
            
            // Bouton sauvegarder habitudes
            btnSauvegarderHabitudes.setOnClickListener {
                saveHabitudes()
            }
            
            Log.d(TAG, "✓ Listeners configurés")
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur configuration listeners", e)
        }
    }

    /**
     * Charge les données existantes depuis la base
     */
    private fun loadExistingData() {
        try {
            // Charger les habitudes
            val habitudes = dbHelper.getHabitudes()
            
            inputMaxCigarettes.setText(habitudes[KEY_MAX_CIGARETTES]?.toString() ?: "")
            inputMaxJoints.setText(habitudes[KEY_MAX_JOINTS]?.toString() ?: "")
            inputMaxAlcoolGlobal.setText(habitudes[KEY_MAX_ALCOOL_GLOBAL]?.toString() ?: "")
            inputMaxBieres.setText(habitudes[KEY_MAX_BIERES]?.toString() ?: "")
            inputMaxLiqueurs.setText(habitudes[KEY_MAX_LIQUEURS]?.toString() ?: "")
            inputMaxAlcoolFort.setText(habitudes[KEY_MAX_ALCOOL_FORT]?.toString() ?: "")
            
            habitudesValues.putAll(habitudes)
            
            // Charger les dates
            val dates = dbHelper.getDatesArret()
            
            dates.forEach { (key, value) ->
                if (value.isNotEmpty()) {
                    datesValues[key] = value
                    val button = dateButtons[key]
                    button?.text = formatDate(value)
                }
            }
            
            Log.d(TAG, "✓ Données existantes chargées")
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur chargement données", e)
        }
    }

    // ══════════════════════════════════════════════════
    // FIN PARTIE 1
    // Suite dans Partie 2 : Gestion dates, sauvegarde
    // ══════════════════════════════════════════════════
}
// ══════════════════════════════════════════════════
    // PARTIE 2/3 : GESTION DATES + SAUVEGARDE
    // ══════════════════════════════════════════════════

    /**
     * Affiche le sélecteur de date
     */
    private fun showDatePicker(key: String, button: Button) {
        try {
            val calendar = Calendar.getInstance()
            
            // Si une date existe déjà, la charger
            datesValues[key]?.let { dateStr ->
                try {
                    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val date = sdf.parse(dateStr)
                    date?.let {
                        calendar.time = it
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Erreur parsing date existante: $dateStr", e)
                }
            }
            
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    // Date sélectionnée
                    val selectedDate = Calendar.getInstance().apply {
                        set(Calendar.YEAR, year)
                        set(Calendar.MONTH, month)
                        set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    }
                    
                    // Valider la date selon le type
                    if (validateDate(key, selectedDate)) {
                        saveDate(key, selectedDate, button)
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            
            datePickerDialog.show()
            
            Log.d(TAG, "DatePicker affiché pour: $key")
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur affichage DatePicker", e)
            showError("Erreur sélection date: ${e.message}")
        }
    }

    /**
     * Valide une date selon son type
     */
    private fun validateDate(key: String, selectedDate: Calendar): Boolean {
        try {
            val today = Calendar.getInstance()
            today.set(Calendar.HOUR_OF_DAY, 0)
            today.set(Calendar.MINUTE, 0)
            today.set(Calendar.SECOND, 0)
            today.set(Calendar.MILLISECOND, 0)
            
            val trad = habitudesLangues.getTraductions(configLangue.getLangue())
            
            when {
                // Date de réduction : ne peut pas être future
                key.contains(DATE_REDUCTION) -> {
                    if (selectedDate.after(today)) {
                        showError(trad["erreur_date_reduction_future"] ?: "Date de réduction ne peut pas être future")
                        return false
                    }
                }
                
                // Date d'arrêt : ne peut pas être future
                key.contains(DATE_ARRET) -> {
                    if (selectedDate.after(today)) {
                        showError(trad["erreur_date_arret_future"] ?: "Date d'arrêt ne peut pas être future")
                        return false
                    }
                    
                    // Vérifier que date arrêt >= date réduction
                    val reductionKey = key.replace(DATE_ARRET, DATE_REDUCTION)
                    datesValues[reductionKey]?.let { reductionStr ->
                        try {
                            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            val reductionDate = Calendar.getInstance().apply {
                                time = sdf.parse(reductionStr)!!
                            }
                            
                            if (selectedDate.before(reductionDate)) {
                                showError(trad["erreur_date_arret_avant_reduction"] ?: "Date d'arrêt doit être après réduction")
                                return false
                            }
                        } catch (e: Exception) {
                            Log.e(TAG, "Erreur validation date arrêt vs réduction", e)
                        }
                    }
                }
                
                // Date de réussite : peut être future
                key.contains(DATE_REUSSITE) -> {
                    // Vérifier que date réussite >= date arrêt
                    val arretKey = key.replace(DATE_REUSSITE, DATE_ARRET)
                    datesValues[arretKey]?.let { arretStr ->
                        try {
                            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            val arretDate = Calendar.getInstance().apply {
                                time = sdf.parse(arretStr)!!
                            }
                            
                            if (selectedDate.before(arretDate)) {
                                showError(trad["erreur_date_reussite_avant_arret"] ?: "Date de réussite doit être après arrêt")
                                return false
                            }
                        } catch (e: Exception) {
                            Log.e(TAG, "Erreur validation date réussite vs arrêt", e)
                        }
                    }
                }
            }
            
            return true
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur validation date", e)
            return false
        }
    }

    /**
     * Sauvegarde une date
     */
    private fun saveDate(key: String, date: Calendar, button: Button) {
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val dateStr = sdf.format(date.time)
            
            // Sauvegarder en base
            val success = dbHelper.saveDateArret(key, dateStr)
            
            if (success) {
                datesValues[key] = dateStr
                button.text = formatDate(dateStr)
                
                Log.d(TAG, "✓ Date sauvegardée: $key = $dateStr")
                
                val trad = habitudesLangues.getTraductions(configLangue.getLangue())
                Toast.makeText(
                    requireContext(),
                    trad["date_sauvegardee"] ?: "Date sauvegardée",
                    Toast.LENGTH_SHORT
                ).show()
                
                // Mettre à jour l'affichage
                updateDisplay()
            } else {
                Log.e(TAG, "❌ Échec sauvegarde date: $key")
                showError("Erreur sauvegarde date")
            }
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur sauvegarde date", e)
            showError("Erreur sauvegarde: ${e.message}")
        }
    }

    /**
     * Efface une date
     */
    private fun clearDate(key: String) {
        try {
            val trad = habitudesLangues.getTraductions(configLangue.getLangue())
            
            // Confirmation
            android.app.AlertDialog.Builder(requireContext())
                .setTitle(trad["confirmer_effacer_date"] ?: "Effacer date?")
                .setMessage(trad["message_effacer_date"] ?: "Voulez-vous effacer cette date?")
                .setPositiveButton(trad["oui"] ?: "Oui") { _, _ ->
                    // Effacer en base
                    val success = dbHelper.saveDateArret(key, "")
                    
                    if (success) {
                        datesValues.remove(key)
                        
                        val button = dateButtons[key]
                        button?.text = trad["selectionner_date"]
                        
                        Log.d(TAG, "✓ Date effacée: $key")
                        
                        Toast.makeText(
                            requireContext(),
                            trad["date_effacee"] ?: "Date effacée",
                            Toast.LENGTH_SHORT
                        ).show()
                        
                        updateDisplay()
                    } else {
                        Log.e(TAG, "❌ Échec effacement date: $key")
                        showError("Erreur effacement date")
                    }
                }
                .setNegativeButton(trad["non"] ?: "Non", null)
                .show()
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur effacement date", e)
            showError("Erreur effacement: ${e.message}")
        }
    }

    /**
     * Formate une date pour l'affichage
     */
    private fun formatDate(dateStr: String): String {
        return try {
            val sdfInput = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val sdfOutput = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val date = sdfInput.parse(dateStr)
            date?.let { sdfOutput.format(it) } ?: dateStr
        } catch (e: Exception) {
            Log.e(TAG, "Erreur formatage date: $dateStr", e)
            dateStr
        }
    }

    /**
     * Sauvegarde les habitudes
     */
    private fun saveHabitudes() {
        try {
            val trad = habitudesLangues.getTraductions(configLangue.getLangue())
            
            // Valider les entrées
            val maxCigarettes = inputMaxCigarettes.text.toString().toIntOrNull() ?: 0
            val maxJoints = inputMaxJoints.text.toString().toIntOrNull() ?: 0
            val maxAlcoolGlobal = inputMaxAlcoolGlobal.text.toString().toIntOrNull() ?: 0
            val maxBieres = inputMaxBieres.text.toString().toIntOrNull() ?: 0
            val maxLiqueurs = inputMaxLiqueurs.text.toString().toIntOrNull() ?: 0
            val maxAlcoolFort = inputMaxAlcoolFort.text.toString().toIntOrNull() ?: 0
            
            // Validation basique
            if (maxCigarettes < 0 || maxJoints < 0 || maxAlcoolGlobal < 0 ||
                maxBieres < 0 || maxLiqueurs < 0 || maxAlcoolFort < 0) {
                showError(trad["erreur_valeurs_negatives"] ?: "Les valeurs ne peuvent pas être négatives")
                return
            }
            
            if (maxCigarettes > 999 || maxJoints > 999 || maxAlcoolGlobal > 999 ||
                maxBieres > 999 || maxLiqueurs > 999 || maxAlcoolFort > 999) {
                showError(trad["erreur_valeurs_trop_elevees"] ?: "Les valeurs doivent être inférieures à 1000")
                return
            }
            
            // Préparer les données
            val habitudes = mapOf(
                KEY_MAX_CIGARETTES to maxCigarettes,
                KEY_MAX_JOINTS to maxJoints,
                KEY_MAX_ALCOOL_GLOBAL to maxAlcoolGlobal,
                KEY_MAX_BIERES to maxBieres,
                KEY_MAX_LIQUEURS to maxLiqueurs,
                KEY_MAX_ALCOOL_FORT to maxAlcoolFort
            )
            
            // Sauvegarder en base
            val success = dbHelper.saveHabitudes(habitudes)
            
            if (success) {
                habitudesValues.clear()
                habitudesValues.putAll(habitudes)
                
                Log.d(TAG, "✓ Habitudes sauvegardées: $habitudes")
                
                Toast.makeText(
                    requireContext(),
                    trad["habitudes_sauvegardees"] ?: "Habitudes sauvegardées",
                    Toast.LENGTH_SHORT
                ).show()
                
                // Mettre à jour l'affichage
                updateDisplay()
                
                // Notifier MainActivity pour mise à jour des autres fragments
                (activity as? MainActivity)?.onDataChanged()
                
            } else {
                Log.e(TAG, "❌ Échec sauvegarde habitudes")
                showError(trad["erreur_sauvegarde"] ?: "Erreur sauvegarde")
            }
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur sauvegarde habitudes", e)
            showError("Erreur: ${e.message}")
        }
    }

    /**
     * Gère les clics sur le header pour la console debug
     */
    private fun handleConsoleDebugClick() {
        try {
            val currentTime = System.currentTimeMillis()
            
            // Reset si délai trop long
            if (currentTime - lastConsoleClickTime > 2000) {
                consoleClickCount = 0
            }
            
            consoleClickCount++
            lastConsoleClickTime = currentTime
            
            if (consoleClickCount >= 5) {
                toggleConsoleDebug()
                consoleClickCount = 0
            }
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur gestion console debug", e)
        }
    }

    /**
     * Toggle console debug
     */
    private fun toggleConsoleDebug() {
        try {
            // À implémenter selon les besoins
            Log.d(TAG, "═══════════════════════════════════════════")
            Log.d(TAG, "CONSOLE DEBUG HABITUDES")
            Log.d(TAG, "═══════════════════════════════════════════")
            Log.d(TAG, "Habitudes: $habitudesValues")
            Log.d(TAG, "Dates: $datesValues")
            Log.d(TAG, "Langue: ${configLangue.getLangue()}")
            Log.d(TAG, "═══════════════════════════════════════════")
            
            Toast.makeText(
                requireContext(),
                "Console debug - voir logs",
                Toast.LENGTH_SHORT
            ).show()
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur toggle console", e)
        }
    }

    // ══════════════════════════════════════════════════
    // FIN PARTIE 2
    // Suite dans Partie 3 : Mise à jour affichage + utilitaires
    // ══════════════════════════════════════════════════
}
// ══════════════════════════════════════════════════
    // PARTIE 3/3 : MISE À JOUR AFFICHAGE + UTILITAIRES
    // ══════════════════════════════════════════════════

    /**
     * Met à jour tout l'affichage
     */
    private fun updateDisplay() {
        try {
            updateBandeauProfil()
            updateTotalJour()
            
            Log.d(TAG, "✓ Affichage mis à jour")
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur mise à jour affichage", e)
        }
    }

    /**
     * Met à jour le bandeau profil (complet/incomplet)
     */
    private fun updateBandeauProfil() {
        try {
            val trad = habitudesLangues.getTraductions(configLangue.getLangue())
            
            // Vérifier si le profil est complet
            val hasCouts = dbHelper.hasCouts()
            val hasHabitudes = hasHabitudesDefinies()
            val hasDates = hasDatesDefinies()
            
            val isComplete = hasCouts && hasHabitudes && hasDates
            
            if (isComplete) {
                txtProfilStatus.text = "✓ ${trad["profil_complet"]}"
                txtProfilStatus.setTextColor(Color.parseColor("#4CAF50")) // Vert
            } else {
                txtProfilStatus.text = "⚠ ${trad["profil_incomplet"]}"
                txtProfilStatus.setTextColor(Color.parseColor("#FF9800")) // Orange
            }
            
            Log.d(TAG, "Profil: ${if (isComplete) "complet" else "incomplet"}")
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur mise à jour bandeau profil", e)
        }
    }

    /**
     * Vérifie si des habitudes sont définies
     */
    private fun hasHabitudesDefinies(): Boolean {
        return habitudesValues.values.any { it > 0 }
    }

    /**
     * Vérifie si des dates sont définies
     */
    private fun hasDatesDefinies(): Boolean {
        return datesValues.isNotEmpty()
    }

    /**
     * Met à jour le total du jour
     */
    private fun updateTotalJour() {
        try {
            val trad = habitudesLangues.getTraductions(configLangue.getLangue())
            
            // Récupérer le total du jour depuis la base
            val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            val totaux = dbHelper.getTotalJour(today)
            
            val totalCigarettes = totaux["cigarettes"] ?: 0
            val totalJoints = totaux["joints"] ?: 0
            val totalAlcoolGlobal = totaux["alcool_global"] ?: 0
            val totalBieres = totaux["bieres"] ?: 0
            val totalLiqueurs = totaux["liqueurs"] ?: 0
            val totalAlcoolFort = totaux["alcool_fort"] ?: 0
            
            val grandTotal = totalCigarettes + totalJoints + totalAlcoolGlobal + 
                           totalBieres + totalLiqueurs + totalAlcoolFort
            
            txtTotalJour.text = "${trad["total_aujourdhui"]}: $grandTotal"
            
            Log.d(TAG, "Total jour: $grandTotal")
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur mise à jour total jour", e)
            txtTotalJour.text = "Total: 0"
        }
    }

    /**
     * Affiche un message d'erreur
     */
    private fun showError(message: String) {
        try {
            Toast.makeText(
                requireContext(),
                message,
                Toast.LENGTH_LONG
            ).show()
            
            Log.e(TAG, "Erreur affichée: $message")
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur affichage message erreur", e)
        }
    }

    /**
     * Méthode appelée par MainActivity pour synchroniser les données
     */
    fun refreshData() {
        try {
            Log.d(TAG, "refreshData() appelé")
            
            loadExistingData()
            updateDisplay()
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur refresh data", e)
        }
    }

    /**
     * Récupère les habitudes actuelles (pour export)
     */
    fun getHabitudesData(): Map<String, Int> {
        return habitudesValues.toMap()
    }

    /**
     * Récupère les dates actuelles (pour export)
     */
    fun getDatesData(): Map<String, String> {
        return datesValues.toMap()
    }

    /**
     * Importe des habitudes (depuis import JSON)
     */
    fun importHabitudes(data: Map<String, Int>) {
        try {
            val success = dbHelper.saveHabitudes(data)
            
            if (success) {
                habitudesValues.clear()
                habitudesValues.putAll(data)
                
                // Mettre à jour les inputs
                inputMaxCigarettes.setText(data[KEY_MAX_CIGARETTES]?.toString() ?: "")
                inputMaxJoints.setText(data[KEY_MAX_JOINTS]?.toString() ?: "")
                inputMaxAlcoolGlobal.setText(data[KEY_MAX_ALCOOL_GLOBAL]?.toString() ?: "")
                inputMaxBieres.setText(data[KEY_MAX_BIERES]?.toString() ?: "")
                inputMaxLiqueurs.setText(data[KEY_MAX_LIQUEURS]?.toString() ?: "")
                inputMaxAlcoolFort.setText(data[KEY_MAX_ALCOOL_FORT]?.toString() ?: "")
                
                updateDisplay()
                
                Log.d(TAG, "✓ Habitudes importées")
            }
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur import habitudes", e)
        }
    }

    /**
     * Importe des dates (depuis import JSON)
     */
    fun importDates(data: Map<String, String>) {
        try {
            data.forEach { (key, value) ->
                dbHelper.saveDateArret(key, value)
            }
            
            datesValues.clear()
            datesValues.putAll(data)
            
            // Mettre à jour les boutons
            data.forEach { (key, value) ->
                if (value.isNotEmpty()) {
                    val button = dateButtons[key]
                    button?.text = formatDate(value)
                }
            }
            
            updateDisplay()
            
            Log.d(TAG, "✓ Dates importées")
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur import dates", e)
        }
    }

    /**
     * Réinitialise toutes les données
     */
    fun resetAllData() {
        try {
            // Réinitialiser les habitudes
            val emptyHabitudes = mapOf(
                KEY_MAX_CIGARETTES to 0,
                KEY_MAX_JOINTS to 0,
                KEY_MAX_ALCOOL_GLOBAL to 0,
                KEY_MAX_BIERES to 0,
                KEY_MAX_LIQUEURS to 0,
                KEY_MAX_ALCOOL_FORT to 0
            )
            
            dbHelper.saveHabitudes(emptyHabitudes)
            habitudesValues.clear()
            
            // Vider les inputs
            inputMaxCigarettes.setText("")
            inputMaxJoints.setText("")
            inputMaxAlcoolGlobal.setText("")
            inputMaxBieres.setText("")
            inputMaxLiqueurs.setText("")
            inputMaxAlcoolFort.setText("")
            
            // Réinitialiser les dates
            datesValues.keys.forEach { key ->
                dbHelper.saveDateArret(key, "")
            }
            datesValues.clear()
            
            // Réinitialiser les boutons de dates
            val trad = habitudesLangues.getTraductions(configLangue.getLangue())
            dateButtons.values.forEach { button ->
                button.text = trad["selectionner_date"]
            }
            
            updateDisplay()
            
            Log.d(TAG, "✓ Toutes les données réinitialisées")
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur réinitialisation données", e)
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            Log.d(TAG, "onResume()")
            refreshData()
        } catch (e: Exception) {
            Log.e(TAG, "Erreur onResume", e)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            dbHelper.close()
            Log.d(TAG, "✓ DatabaseHelper fermé")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur fermeture DatabaseHelper", e)
        }
    }
}

// ══════════════════════════════════════════════════════════════════════
// FIN HabitudesFragment.kt
// Total: ~870 lignes
// 
// Fonctionnalités complètes:
// - Zone Habitudes: inputs max pour 6 catégories
// - Zone Volonté: 6 catégories × 3 dates (réduction/arrêt/réussite)
// - Validation dates selon type et cohérence
// - Sauvegarde/effacement dates avec confirmations
// - Console debug (5 clics sur header)
// - Bandeau profil (complet/incomplet)
// - Total du jour
// - Import/export pour synchronisation
// - Réinitialisation complète
// 
// Dépendances:
// - DatabaseHelper: getHabitudes(), saveHabitudes(), getDatesArret(), 
//                   saveDateArret(), hasCouts(), getTotalJour()
// - HabitudesLangues: traductions 10 langues
// - ConfigLangue: langue actuelle
// - MainActivity: onDataChanged()
// - R.layout.fragment_habitudes
// ══════════════════════════════════════════════════════════════════════