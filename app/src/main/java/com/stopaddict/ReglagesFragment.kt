package com.stopaddict

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import android.view.inputmethod.EditorInfo
import java.text.SimpleDateFormat
import androidx.core.content.ContextCompat
import kotlin.math.roundToInt
import android.graphics.Typeface
import java.util.*

class ReglagesFragment : Fragment() {

    companion object {
        private const val TAG = "ReglagesFragment"
        private const val REQUEST_CODE_EXPORT = 1001
        private const val REQUEST_CODE_IMPORT = 1002
        private const val PREF_MODE_CIGARETTE = "mode_cigarette"
        private const val PREF_NB_CIGARETTES_ROULEES = "nb_cigarettes_roulees"
        private const val PREF_NB_CIGARETTES_TUBEES = "nb_cigarettes_tubees"
    }

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var configLangue: ConfigLangue
    private lateinit var exportLimiter: ExportLimiter
    private lateinit var trad: Map<String, String>
    
    private lateinit var txtProfilComplet: TextView
    private lateinit var profilProgress: ProgressBar
    private lateinit var txtProfilRestant: TextView
    
    private lateinit var editPrenom: EditText
    private lateinit var spinnerLangue: Spinner
    private lateinit var spinnerDevise: Spinner
    
    // Catégories
    private lateinit var switchCigarette: CheckBox
    private lateinit var switchJoint: CheckBox
    private lateinit var switchAlcoolGlobal: CheckBox
    private lateinit var switchBiere: CheckBox
    private lateinit var switchLiqueur: CheckBox
    private lateinit var switchAlcoolFort: CheckBox
    
    // Cigarettes - 3 modes
    private lateinit var radioCigarettesClassiques: RadioButton
    private lateinit var radioCigarettesRouler: RadioButton
    private lateinit var radioCigarettesTubeuse: RadioButton
    
    // Paquet classique
    private lateinit var editPrixPaquet: EditText
    private lateinit var editNbCigarettes: EditText

    // Nombre de cigarettes fabriquées (roulées / tubées)
    private lateinit var editNbCigarettesRoulees: EditText
    private lateinit var editNbCigarettesTubees: EditText
    
    // À rouler
    private lateinit var editPrixTabac: EditText
    private lateinit var editPrixFeuilles: EditText
    private lateinit var editNbFeuilles: EditText
    private lateinit var editPrixFiltres: EditText
    private lateinit var editNbFiltres: EditText
    
    // À tuber
    private lateinit var editPrixTabacTubes: EditText
    private lateinit var editPrixTubes: EditText
    private lateinit var editNbTubes: EditText
    
    // Joints
    private lateinit var editPrixGramme: EditText
    private lateinit var editGrammeParJoint: EditText
    private lateinit var editPrixFeuillesJoint: EditText
    private lateinit var editNbFeuillesJoint: EditText
    
    // Alcools
    private lateinit var editPrixVerreGlobal: EditText
    private lateinit var editUniteCLGlobal: EditText
    private lateinit var editPrixVerreBiere: EditText
    private lateinit var editUniteCLBiere: EditText
    private lateinit var editPrixVerreLiqueur: EditText
    private lateinit var editUniteCLLiqueur: EditText
    private lateinit var editPrixVerreAlcoolFort: EditText
    private lateinit var editUniteCLAlcoolFort: EditText
    
    private val categoriesActives = mutableMapOf<String, Boolean>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return try {
            val view = inflater.inflate(R.layout.fragment_reglages, container, false)
            
            dbHelper = DatabaseHelper(requireContext())
            configLangue = ConfigLangue(requireContext())
            exportLimiter = ExportLimiter(requireContext())
            trad = ReglagesLangues.getTraductions(configLangue.getLangue())
            
            bindUIFromXml(view)
            initializeUI(view) 
            loadData()
            
            Log.d(TAG, "ReglagesFragment créé")
            view
        } catch (e: Exception) {
            Log.e(TAG, "Erreur onCreateView", e)
            Toast.makeText(requireContext(), "Erreur: ${e.message}", Toast.LENGTH_SHORT).show()
            null
        }
    }

    private fun bindUIFromXml(view: View) {

    // Bandeau profil (en haut)
    txtProfilComplet = view.findViewById(R.id.reglages_txt_profil_complet)
    profilProgress = view.findViewById(R.id.reglages_profil_progress)
    txtProfilRestant = view.findViewById(R.id.reglages_txt_profil_restant)
}

    private fun initializeUI(view: View) {
        val container = view.findViewById<LinearLayout>(R.id.fragment_container)
        container.removeAllViews()
        container.orientation = LinearLayout.VERTICAL
        
        // ScrollView pour tout
        val scrollView = ScrollView(requireContext()).apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        
        val contentContainer = LinearLayout(requireContext()).apply {
             orientation = LinearLayout.VERTICAL
            setPadding(0, 0, 0, 0)
        }
        
        //Header
        //addHeader(view)
        
        // Personnalisation
        addPersonnalisationSection(contentContainer)
        
        // Coûts
        addCoutsSection(contentContainer)
        
        // À propos
        addAProposSection(contentContainer)
        
        // RAZ et Sauvegarde
        addRAZSection(contentContainer)
        
       scrollView.addView(contentContainer)
        container.addView(scrollView)
    }
    
    private fun addHeader(root: View) {

    txtProfilComplet = root.findViewById(R.id.reglages_txt_profil_complet)
    profilProgress = root.findViewById(R.id.reglages_profil_progress)
    txtProfilRestant = root.findViewById(R.id.reglages_txt_profil_restant)

    updateProfilStatus()
}
    
    private fun addPersonnalisationSection(container: LinearLayout) {
    addSectionTitle(container, trad["titre_profil"] ?: "Personnalisation")

    fun dp(v: Int): Int = (v * resources.displayMetrics.density).toInt()

    fun roundedStrokeBg(
        fillColorInt: Int,
        strokeColorInt: Int,
        strokeWidthDp: Int,
        radiusDp: Int
    ): android.graphics.drawable.GradientDrawable {
        return android.graphics.drawable.GradientDrawable().apply {
            shape = android.graphics.drawable.GradientDrawable.RECTANGLE
            cornerRadius = dp(radiusDp).toFloat()
            setColor(fillColorInt)
            setStroke(dp(strokeWidthDp), strokeColorInt)
        }
    }

    fun applyWhiteFieldStyleWithBlueStroke(view: View) {
        // Fond blanc + coins arrondis + liseré bleu + padding + marge verticale
        view.background = roundedStrokeBg(
            fillColorInt = Color.WHITE,
            strokeColorInt = Color.parseColor("#1976D2"),
            strokeWidthDp = 2,
            radiusDp = 10
        )
        view.setPadding(dp(14), dp(14), dp(14), dp(14))

        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(0, 0, 0, dp(14))
        }
        view.layoutParams = lp
    }

    // Prénom (avec emoji dans le label)
    addLabel(container, "✏️ " + (trad["label_prenom"] ?: "Prénom"))
    editPrenom = EditText(requireContext()).apply {
        hint = trad["hint_prenom"] ?: "Entrer votre prénom"
        inputType = InputType.TYPE_CLASS_TEXT
        setTextColor(Color.BLACK)
        setHintTextColor(Color.parseColor("#9E9E9E"))
        // évite les tints Material qui donnent parfois une sensation “bleutée”
        backgroundTintList = null
    }
    applyWhiteFieldStyleWithBlueStroke(editPrenom)
    container.addView(editPrenom)

    // Langue (avec emoji dans le label)
    addLabel(container, "🌐 " + (trad["label_langue"] ?: "Langue"))
    spinnerLangue = Spinner(requireContext()).apply {
        backgroundTintList = null
    }
    val langues = arrayOf("FR", "EN", "ES", "PT", "DE", "IT", "RU", "AR", "HI", "JA")
    spinnerLangue.adapter = ArrayAdapter(
        requireContext(),
        android.R.layout.simple_spinner_item,
        langues
    ).apply {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }
    applyWhiteFieldStyleWithBlueStroke(spinnerLangue)
    container.addView(spinnerLangue)

    // Devise (avec emoji dans le label)
    addLabel(container, "💰 " + (trad["label_devise"] ?: "Devise"))
    spinnerDevise = Spinner(requireContext()).apply {
        backgroundTintList = null
    }
    val devises = arrayOf("EUR (€)", "USD ($)", "GBP (£)", "JPY (¥)", "CHF", "CAD", "AUD", "BRL", "INR", "RUB")
    spinnerDevise.adapter = ArrayAdapter(
        requireContext(),
        android.R.layout.simple_spinner_item,
        devises
    ).apply {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }
    applyWhiteFieldStyleWithBlueStroke(spinnerDevise)
    container.addView(spinnerDevise)

    // Bouton sauvegarder profil : emoji + texte (pas d'icône Android noir/blanc)
    val btnSavePerso = Button(requireContext()).apply {
        text = "💾 " + (trad["btn_sauvegarder_profil"] ?: "Sauvegarder")
        isAllCaps = false
        textSize = 13f
        setTextColor(Color.parseColor("#1976D2"))
        setPadding(dp(14), dp(12), dp(14), dp(12))

        background = android.graphics.drawable.GradientDrawable().apply {
            shape = android.graphics.drawable.GradientDrawable.RECTANGLE
            cornerRadius = dp(10).toFloat()
            setColor(Color.parseColor("#F2F2F2"))
        }

        layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        setOnClickListener { savePersonnalisation() }
    }
    container.addView(btnSavePerso)
}

    private fun addCoutsSection(container: LinearLayout) {
        addSectionTitle(container, trad["titre_categories"] ?: "Coûts")
        
        // CIGARETTES
        addCigarettesSection(container)
        
        // JOINTS
        addJointsSection(container)
        
        // ALCOOL GLOBAL
        addAlcoolGlobalSection(container)
        
        // BIÈRES
        addBieresSection(container)
        
        // LIQUEURS
        addLiqueursSection(container)
        
        // ALCOOL FORT
        addAlcoolFortSection(container)
        
        // Bouton sauvegarder coûts
        val btnSaveCouts = Button(requireContext()).apply {
            text = trad["btn_sauvegarder_profil"] ?: "Sauvegarder les coûts"
            setOnClickListener { saveCouts() }
        }
        container.addView(btnSaveCouts)
    }

    private fun addCigarettesSection(container: LinearLayout) {
        val cigaretteCard = createCard()
        
        // Switch cigarette
        switchCigarette = CheckBox(requireContext()).apply {
            text = trad["label_cigarettes"] ?: "Cigarettes"
            textSize = 16f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setOnCheckedChangeListener { _, isChecked ->
                categoriesActives["cigarette"] = isChecked
                saveCategoriesActives()
            }
        }
        cigaretteCard.addView(switchCigarette)
        
        // RadioGroup pour 3 modes
        val radioGroup = RadioGroup(requireContext())
        
        // Mode 1: Paquet classique
        radioCigarettesClassiques = RadioButton(requireContext()).apply {
            text = "📦 ${trad["radio_classiques"] ?: "Cigarettes paquet classique"}"
            isChecked = true
        }
        radioGroup.addView(radioCigarettesClassiques)
        
        // Champs paquet
        val paquetContainer = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(30, 10, 0, 10)
        }
        addLabel(paquetContainer, (trad["label_prix_paquet"] ?: "Prix du paquet") + " (" + getDeviseSymbol() + ")")
        editPrixPaquet = createMoneyEditText()
        paquetContainer.addView(editPrixPaquet)
        
        addLabel(paquetContainer, trad["label_nb_cigarettes"] ?: "Nombre de cigarettes par paquet")
        editNbCigarettes = createNumberEditText()
        paquetContainer.addView(editNbCigarettes)
        radioGroup.addView(paquetContainer)
        
        // Mode 2: À rouler
        radioCigarettesRouler = RadioButton(requireContext()).apply {
            text = "🌿 ${trad["radio_rouler"] ?: "Cigarettes à rouler"}"
        }
        radioGroup.addView(radioCigarettesRouler)
        
        val roulerContainer = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(30, 10, 0, 10)
        }
        addLabel(roulerContainer, (trad["label_prix_tabac"] ?: "Prix du tabac à rouler") + " (" + getDeviseSymbol() + ")")
        editPrixTabac = createMoneyEditText()
        roulerContainer.addView(editPrixTabac)
        
        addLabel(roulerContainer, (trad["label_prix_feuilles"] ?: "Prix des feuilles à rouler") + " (" + getDeviseSymbol() + ")")
        editPrixFeuilles = createMoneyEditText()
        roulerContainer.addView(editPrixFeuilles)
        
        addLabel(roulerContainer, trad["label_nb_feuilles"] ?: "Nombre de feuilles")
        editNbFeuilles = createNumberEditText()
        roulerContainer.addView(editNbFeuilles)
        
        addLabel(roulerContainer, (trad["label_prix_filtres"] ?: "Prix du sachet de filtres") + " (" + getDeviseSymbol() + ")")
        editPrixFiltres = createMoneyEditText()
        roulerContainer.addView(editPrixFiltres)
        
        addLabel(roulerContainer, trad["label_nb_filtres"] ?: "Nombre de filtres")
        editNbFiltres = createNumberEditText()
        roulerContainer.addView(editNbFiltres)

        // Nouveau : nombre de cigarettes fabriquées avec ce paquet (rouler)
        addLabel(
            roulerContainer,
            trad["label_nb_cigarettes"]
                ?: "Nombre de cigarettes"
        )
        editNbCigarettesRoulees = createNumberEditText()
        roulerContainer.addView(editNbCigarettesRoulees)

        radioGroup.addView(roulerContainer)
        
                // Mode 3: À tuber
        radioCigarettesTubeuse = RadioButton(requireContext()).apply {
            text = "🚬 ${trad["radio_tubeuse"] ?: "Cigarettes à tuber"}"
        }
        radioGroup.addView(radioCigarettesTubeuse)
        
        val tuberContainer = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(30, 10, 0, 10)
        }

        // Prix du tabac à tuber
        addLabel(tuberContainer, (trad["label_prix_tabac"] ?: "Prix du tabac pour tubeuse") + " (" + getDeviseSymbol() + ")")
        editPrixTabacTubes = createMoneyEditText()
        tuberContainer.addView(editPrixTabacTubes)

        // Prix des tubes
        addLabel(tuberContainer, (trad["label_prix_tubes"] ?: "Prix des tubes") + " (" + getDeviseSymbol() + ")")
        editPrixTubes = createMoneyEditText()
        tuberContainer.addView(editPrixTubes)
                // Nombre de tubes
        addLabel(tuberContainer, trad["label_nb_tubes"] ?: "Nombre de tubes")
        editNbTubes = createNumberEditText()
        tuberContainer.addView(editNbTubes)

        // Nouveau : nombre de cigarettes fabriquées avec ce paquet (tuber)
        addLabel(
        tuberContainer,
        trad["label_nb_cigarettes"]
            ?: "Nombre de cigarettes"
            )
        editNbCigarettesTubees = createNumberEditText()
        tuberContainer.addView(editNbCigarettesTubees)

        radioGroup.addView(tuberContainer)
        // Assurer qu'un seul mode de cigarettes est sélectionné à la fois

        radioCigarettesClassiques.setOnCheckedChangeListener { _, isChecked ->
    if (isChecked) {
        radioCigarettesRouler.isChecked = false
        radioCigarettesTubeuse.isChecked = false
    }
}

radioCigarettesRouler.setOnCheckedChangeListener { _, isChecked ->
    if (isChecked) {
        radioCigarettesClassiques.isChecked = false
        radioCigarettesTubeuse.isChecked = false
    }
}

radioCigarettesTubeuse.setOnCheckedChangeListener { _, isChecked ->
    if (isChecked) {
        radioCigarettesClassiques.isChecked = false
        radioCigarettesRouler.isChecked = false
    }
}

        cigaretteCard.addView(radioGroup)
        container.addView(cigaretteCard)
    }

    private fun addJointsSection(container: LinearLayout) {
        val jointCard = createCard()
        
        switchJoint = CheckBox(requireContext()).apply {
            text = trad["label_joints"] ?: "Joints (Cannabis)"
            textSize = 16f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setOnCheckedChangeListener { _, isChecked ->
                categoriesActives["joint"] = isChecked
                saveCategoriesActives()
            }
        }
        jointCard.addView(switchJoint)
        
        addLabel(jointCard, (trad["label_prix_gramme"] ?: "Prix du gramme") + " (" + getDeviseSymbol() + ")")
        editPrixGramme = createMoneyEditText()
        jointCard.addView(editPrixGramme)
        
        addLabel(jointCard, trad["label_gramme_par_joint"] ?: "Grammes par joint")
        editGrammeParJoint = EditText(requireContext()).apply {
            hint = "0.0"
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            setPadding(20, 20, 20, 20)
        }
        jointCard.addView(editGrammeParJoint)
        
        addLabel(jointCard, (trad["label_prix_feuilles_longues"] ?: "Prix des feuilles longues") + " (" + getDeviseSymbol() + ")")
        editPrixFeuillesJoint = createMoneyEditText()
        jointCard.addView(editPrixFeuillesJoint)
        
        addLabel(jointCard, trad["label_nb_feuilles_longues"] ?: "Nombre de feuilles longues")
        editNbFeuillesJoint = createNumberEditText()
        jointCard.addView(editNbFeuillesJoint)
        
        container.addView(jointCard)
    }

    private fun addAlcoolGlobalSection(container: LinearLayout) {
        val alcoolCard = createCard()
        
        switchAlcoolGlobal = CheckBox(requireContext()).apply {
            text = trad["label_alcool_global"] ?: "Alcool Global"
            textSize = 16f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setOnCheckedChangeListener { _, isChecked ->
                categoriesActives["alcool_global"] = isChecked
                // Désactiver sous-catégories si global désactivé
                if (!isChecked) {
                    switchBiere.isChecked = false
                    switchLiqueur.isChecked = false
                    switchAlcoolFort.isChecked = false
                }
                saveCategoriesActives()
            }
        }
        alcoolCard.addView(switchAlcoolGlobal)
        
        addLabel(alcoolCard, (trad["label_prix_verre"] ?: "Prix du verre (alcool global)") + " (" + getDeviseSymbol() + ")")
        editPrixVerreGlobal = createMoneyEditText()
        alcoolCard.addView(editPrixVerreGlobal)
        
        addLabel(alcoolCard, trad["unite_cl_global"] ?: "Unité en cL")
        editUniteCLGlobal = EditText(requireContext()).apply {
            hint = "00"
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            setPadding(20, 20, 20, 20)
        }
        alcoolCard.addView(editUniteCLGlobal)
        
        container.addView(alcoolCard)
    }

    private fun addBieresSection(container: LinearLayout) {
        val biereCard = createCard()
        
        switchBiere = CheckBox(requireContext()).apply {
            text = trad["label_bieres"] ?: "Bières"
            textSize = 16f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setOnCheckedChangeListener { _, isChecked ->
                categoriesActives["biere"] = isChecked
                saveCategoriesActives()
            }
        }
        biereCard.addView(switchBiere)
        
        addLabel(biereCard, (trad["label_prix_verre"] ?: "Prix du verre de bière") + " (" + getDeviseSymbol() + ")")
        editPrixVerreBiere = createMoneyEditText()
        biereCard.addView(editPrixVerreBiere)
        
        addLabel(biereCard, trad["unite_cl_biere"] ?: "Unité en cL")
        editUniteCLBiere = EditText(requireContext()).apply {
            hint = "0"
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            setPadding(20, 20, 20, 20)
        }
        biereCard.addView(editUniteCLBiere)
        
        container.addView(biereCard)
    }

    private fun addLiqueursSection(container: LinearLayout) {
        val liqueurCard = createCard()
        
        switchLiqueur = CheckBox(requireContext()).apply {
            text = trad["label_liqueurs"] ?: "Liqueurs"
            textSize = 16f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setOnCheckedChangeListener { _, isChecked ->
                categoriesActives["liqueur"] = isChecked
                saveCategoriesActives()
            }
        }
        liqueurCard.addView(switchLiqueur)
        
        addLabel(liqueurCard, (trad["label_prix_verre"] ?: "Prix du verre de liqueur") + " (" + getDeviseSymbol() + ")")
        editPrixVerreLiqueur = createMoneyEditText()
        liqueurCard.addView(editPrixVerreLiqueur)
        
        addLabel(liqueurCard, trad["unite_cl_liqueur"] ?: "Unité en cL")
        editUniteCLLiqueur = EditText(requireContext()).apply {
            hint = "0"
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            setPadding(20, 20, 20, 20)
        }
        liqueurCard.addView(editUniteCLLiqueur)
        
        container.addView(liqueurCard)
    }

    private fun addAlcoolFortSection(container: LinearLayout) {
        val alcoolFortCard = createCard()
        
        switchAlcoolFort = CheckBox(requireContext()).apply {
            text = trad["label_alcool_fort"] ?: "Alcool Fort"
            textSize = 16f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setOnCheckedChangeListener { _, isChecked ->
                categoriesActives["alcool_fort"] = isChecked
                saveCategoriesActives()
            }
        }
        alcoolFortCard.addView(switchAlcoolFort)
        
        addLabel(alcoolFortCard, (trad["label_prix_verre"] ?: "Prix du verre d'alcool fort") + " (" + getDeviseSymbol() + ")")
        editPrixVerreAlcoolFort = createMoneyEditText()
        alcoolFortCard.addView(editPrixVerreAlcoolFort)
        
        addLabel(alcoolFortCard, trad["unite_cl_alcool_fort"] ?: "Unité en cL")
        editUniteCLAlcoolFort = EditText(requireContext()).apply {
            hint = "0"
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            setPadding(20, 20, 20, 20)
        }
        alcoolFortCard.addView(editUniteCLAlcoolFort)
        
        container.addView(alcoolFortCard)
    }

    private fun savePersonnalisation() {
        try {
            val prenom = editPrenom.text.toString()
            dbHelper.setPreference("prenom", prenom)
            
            val langue = spinnerLangue.selectedItem.toString()
            configLangue.setLangue(langue)
            
            val devise = spinnerDevise.selectedItem.toString().split(" ")[0]
            dbHelper.setPreference("devise", devise)
            
            Toast.makeText(requireContext(), trad["sauvegarde_ok"] ?: "Sauvegardé", Toast.LENGTH_SHORT).show()
            
            // Recharger l'activité pour appliquer la langue
            activity?.recreate()
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur save perso", e)
            Toast.makeText(requireContext(), "Erreur: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveCouts() {
    try {
        // 1) On récupère les anciens coûts pour ne pas écraser les modes non modifiés
        val anciensCoutsCig = dbHelper.getCouts(DatabaseHelper.TYPE_CIGARETTE)

        var prixPaquet = anciensCoutsCig["prix_paquet"] ?: 0.0
        var nbCigarettes = anciensCoutsCig["nb_cigarettes"] ?: 0.0
        var prixTabac = anciensCoutsCig["prix_tabac"] ?: 0.0
        var prixFeuilles = anciensCoutsCig["prix_feuilles"] ?: 0.0
        var nbFeuilles = anciensCoutsCig["nb_feuilles"] ?: 0.0
        var prixFiltres = anciensCoutsCig["prix_filtres"] ?: 0.0
        var nbFiltres = anciensCoutsCig["nb_filtres"] ?: 0.0
        var prixTubes = anciensCoutsCig["prix_tubes"] ?: 0.0
        var nbTubes = anciensCoutsCig["nb_tubes"] ?: 0.0
        var prixTabacTubes = anciensCoutsCig["prix_tabac_tube"] ?: 0.0
        var modeCig = "classique"

        fun parseDouble(text: String): Double =
            text.replace(",", ".").toDoubleOrNull() ?: 0.0

        // 2) On met à jour UNIQUEMENT le mode sélectionné
        if (radioCigarettesClassiques.isChecked) {
            modeCig = "classique"
            prixPaquet = parseDouble(editPrixPaquet.text.toString())
            nbCigarettes = parseDouble(editNbCigarettes.text.toString())

        } else if (radioCigarettesRouler.isChecked) {
            modeCig = "rouler"
            prixTabac = parseDouble(editPrixTabac.text.toString())
            prixFeuilles = parseDouble(editPrixFeuilles.text.toString())
            nbFeuilles = parseDouble(editNbFeuilles.text.toString())
            prixFiltres = parseDouble(editPrixFiltres.text.toString())
            nbFiltres = parseDouble(editNbFiltres.text.toString())

        } else if (radioCigarettesTubeuse.isChecked) {
            modeCig = "tuber"
            prixTabacTubes = parseDouble(editPrixTabacTubes.text.toString())
            prixTubes = parseDouble(editPrixTubes.text.toString())
            nbTubes = parseDouble(editNbTubes.text.toString())
        }

                // Sauvegarde des nombres de cigarettes fabriquées (rouler / tuber) dans les préférences
        val nbCigRoulees = parseDouble(editNbCigarettesRoulees.text.toString())
        val nbCigTubees = parseDouble(editNbCigarettesTubees.text.toString())

        dbHelper.setPreference(PREF_NB_CIGARETTES_ROULEES, nbCigRoulees.toString())
        dbHelper.setPreference(PREF_NB_CIGARETTES_TUBEES, nbCigTubees.toString())
        
        // 3) On sauvegarde TOUT en une fois, avec la nouvelle colonne
        dbHelper.setCouts(
            DatabaseHelper.TYPE_CIGARETTE,
            prixPaquet,
            nbCigarettes,
            prixTabac,
            prixFeuilles,
            nbFeuilles,
            prixFiltres,
            nbFiltres,
            prixTubes,
            nbTubes,
            prixTabacTubes,
            0.0 // prixVerre pour les cigarettes (inutile)
        )

        // On mémorise le mode choisi
        dbHelper.setPreference(PREF_MODE_CIGARETTE, modeCig)

        // JOINTS
        val prixGramme = editPrixGramme.text.toString().toDoubleOrNull() ?: 0.0
        val grammeParJoint = editGrammeParJoint.text.toString().toDoubleOrNull() ?: 0.0
        val prixFeuillesJoint = editPrixFeuillesJoint.text.toString().toDoubleOrNull() ?: 0.0
        val nbFeuillesJoint = editNbFeuillesJoint.text.toString().toDoubleOrNull() ?: 0.0

        dbHelper.setCouts(
            DatabaseHelper.TYPE_JOINT,
            0.0, 0.0,
            0.0,
            prixFeuillesJoint, nbFeuillesJoint,
            0.0, 0.0,
            0.0, 0.0,
            0.0,
            prixGramme          // dernier param = prix du gramme
        )
        dbHelper.setPreference("gramme_par_joint", grammeParJoint.toString())

        // ALCOOL GLOBAL
        val prixVerreGlobal = editPrixVerreGlobal.text.toString().toDoubleOrNull() ?: 0.0
        val uniteCLGlobal = editUniteCLGlobal.text.toString().toDoubleOrNull() ?: 0.0
        dbHelper.setCouts(
            DatabaseHelper.TYPE_ALCOOL_GLOBAL,
            0.0, 0.0,
            0.0, 0.0, 0.0,
            0.0, 0.0,
            0.0, 0.0,
            0.0,
            prixVerreGlobal
        )
        dbHelper.setPreference("unite_cl_alcool_global", uniteCLGlobal.toString())

        // BIÈRES
        val prixVerreBiere = editPrixVerreBiere.text.toString().toDoubleOrNull() ?: 0.0
        val uniteCLBiere = editUniteCLBiere.text.toString().toDoubleOrNull() ?: 0.0
        dbHelper.setCouts(
            DatabaseHelper.TYPE_BIERE,
            0.0, 0.0,
            0.0, 0.0, 0.0,
            0.0, 0.0,
            0.0, 0.0,
            0.0,
            prixVerreBiere
        )
        dbHelper.setPreference("unite_cl_biere", uniteCLBiere.toString())

        // LIQUEURS
        val prixVerreLiqueur = editPrixVerreLiqueur.text.toString().toDoubleOrNull() ?: 0.0
        val uniteCLLiqueur = editUniteCLLiqueur.text.toString().toDoubleOrNull() ?: 0.0
        dbHelper.setCouts(
            DatabaseHelper.TYPE_LIQUEUR,
            0.0, 0.0,
            0.0, 0.0, 0.0,
            0.0, 0.0,
            0.0, 0.0,
            0.0,
            prixVerreLiqueur
        )
        dbHelper.setPreference("unite_cl_liqueur", uniteCLLiqueur.toString())

        // ALCOOL FORT
        val prixVerreAlcoolFort = editPrixVerreAlcoolFort.text.toString().toDoubleOrNull() ?: 0.0
        val uniteCLAlcoolFort = editUniteCLAlcoolFort.text.toString().toDoubleOrNull() ?: 0.0
        dbHelper.setCouts(
            DatabaseHelper.TYPE_ALCOOL_FORT,
            0.0, 0.0,
            0.0, 0.0, 0.0,
            0.0, 0.0,
            0.0, 0.0,
            0.0,
            prixVerreAlcoolFort
        )
        dbHelper.setPreference("unite_cl_alcool_fort", uniteCLAlcoolFort.toString())

        Toast.makeText(
            requireContext(),
            trad["msg_profil_sauvegarde"] ?: "Coûts sauvegardés",
            Toast.LENGTH_SHORT
        ).show()

        updateProfilStatus()

    } catch (e: Exception) {
        Log.e(TAG, "Erreur save coûts", e)
        Toast.makeText(requireContext(), "Erreur: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}

    private fun saveCategoriesActives() {
        try {
            val json = com.google.gson.Gson().toJson(categoriesActives)
            dbHelper.setPreference("categories_actives", json)
            Log.d(TAG, "Catégories actives sauvegardées: $json")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur save catégories", e)
        }
    }

    private fun addAProposSection(container: LinearLayout) {
    addSectionTitle(container, trad["titre_a_propos"] ?: "À propos")

    val aProposCard = createCard()

    // Lien 1: Réafficher avertissement
    addLinkButton(
        aProposCard,
        "⚠️ ${trad["voir_avertissement"] ?: "Voir l'avertissement"}"
    ) {
    (activity as? MainActivity)?.showAgeWarningDialog(fromSettings = true)
    }

    // Lien 2: Manuel d'utilisation
    addLinkButton(aProposCard, "📖 ${trad["btn_manuel"] ?: "Manuel d'utilisation"}") {
        showManuelDialog()
    }

    // Lien 3: CGV
    addLinkButton(aProposCard, "📄 ${trad["btn_cgv"] ?: "Conditions générales de vente (CGV)"}") {
        showCGVDialog()
    }

    // Lien 4: Mentions légales
    addLinkButton(aProposCard, "⚖️ ${trad["btn_mentions_legales"] ?: "Mentions légales"}") {
        showMentionsLegalesDialog()
    }

    // Lien 5: Version sans publicité
    addLinkButton(aProposCard, "🪙 ${trad["btn_premium"] ?: "Version sans publicité"}") {
        ouvrirVersionPremium()
    }

    // Lien 6: Dernières mises à jour
    addLinkButton(aProposCard, "🛠️ ${trad["btn_maj"] ?: "Dernières mises à jour"}") {
        showMisesAJourDialog()
    }

    // Lien 7: Contact support
    addLinkButton(aProposCard, "✉️ ${trad["btn_contact"] ?: "Contact support"}") {
        sendEmail()
    }

    container.addView(aProposCard)
}
    private fun ouvrirVersionPremium() {
    val premiumPackage = "com.stopaddict.premium" // TODO: remplacer par le vrai package

    try {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$premiumPackage")))
    } catch (e: ActivityNotFoundException) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$premiumPackage")))
        } catch (e2: Exception) {
            Log.e(TAG, "Impossible d'ouvrir le store", e2)
        }
    } catch (e: Exception) {
        Log.e(TAG, "Impossible d'ouvrir le store", e)
    }
}
    
    
    private fun addRAZSection(container: LinearLayout) {

    fun dp(v: Int): Int = (v * resources.displayMetrics.density).toInt()

    fun roundedBg(colorInt: Int, radiusDp: Int): android.graphics.drawable.GradientDrawable {
        return android.graphics.drawable.GradientDrawable().apply {
            shape = android.graphics.drawable.GradientDrawable.RECTANGLE
            cornerRadius = dp(radiusDp).toFloat()
            setColor(colorInt)
        }
    }

    fun styleRazButton(btn: Button) {
        btn.background = roundedBg(Color.parseColor("#F2F2F2"), 10)
        btn.setTextColor(Color.parseColor("#1976D2"))
        btn.isAllCaps = false
        btn.textSize = 13f
        btn.setPadding(dp(14), dp(12), dp(14), dp(12))
        btn.minHeight = dp(44)
    }

    // Carte blanche arrondie (spécifique à cette section)
    val card = LinearLayout(requireContext()).apply {
        orientation = LinearLayout.VERTICAL
        layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(0, 0, 0, dp(16))
        }
        background = roundedBg(Color.WHITE, 14)
        setPadding(dp(16), dp(16), dp(16), dp(16))
    }

    val title = TextView(requireContext()).apply {
        text = trad["raz_sauvegarde"] ?: "RAZ & sauvegarde"
        textSize = 18f
        setTextColor(Color.parseColor("#1976D2"))
        setTypeface(null, Typeface.BOLD)
        setPadding(0, 0, 0, dp(12))
    }
    card.addView(title)

    // Ligne boutons RAZ (jour / historique / usine) -> 3 colonnes
    val razLayout = LinearLayout(requireContext()).apply {
        orientation = LinearLayout.HORIZONTAL
        weightSum = 3f
        layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
    }

    val btnRAZJour = Button(requireContext()).apply {
        text = "🗑️ " + (trad["btn_raz_jour"] ?: "RAZ du jour")
        layoutParams = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1f
        ).apply {
            setMargins(0, 0, dp(8), 0)
        }
        styleRazButton(this)
        // ✅ On utilise la fonction déjà présente showRAZConfirmation("jour")
        setOnClickListener { showRAZConfirmation("jour") }
    }

    val btnRAZHistorique = Button(requireContext()).apply {
        text = "🧹 " + (trad["btn_raz_historique"] ?: "RAZ historique")
        layoutParams = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1f
        ).apply {
            setMargins(0, 0, dp(8), 0)
        }
        styleRazButton(this)
        // ✅ Confirmation RAZ historique
        setOnClickListener { showRAZConfirmation("historique") }
    }

    val btnRAZUsine = Button(requireContext()).apply {
        text = "⚠️ " + (trad["btn_raz_usine"] ?: "RAZ d'usine")
        layoutParams = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1f
        )
        styleRazButton(this)
        // ✅ Confirmation RAZ usine
        setOnClickListener { showRAZConfirmation("usine") }
    }

    razLayout.addView(btnRAZJour)
    razLayout.addView(btnRAZHistorique)
    razLayout.addView(btnRAZUsine)
    card.addView(razLayout)

    // Espace entre lignes
    card.addView(Space(requireContext()).apply {
        layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            dp(10)
        )
    })

    // Ligne Export / Import JSON -> 2 colonnes
    val exportImportLayout = LinearLayout(requireContext()).apply {
        orientation = LinearLayout.HORIZONTAL
        weightSum = 2f
        layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
    }

    val btnExporter = Button(requireContext()).apply {
        text = "📤 " + (trad["btn_exporter"] ?: "Exporter")
        layoutParams = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1f
        ).apply {
            setMargins(0, 0, dp(8), 0)
        }
        styleRazButton(this)
        // ✅ Utilise la fonction exportData() déjà définie plus bas
        setOnClickListener { exportData() }
    }

    val btnImporter = Button(requireContext()).apply {
        text = "📥 " + (trad["btn_importer"] ?: "Importer")
        layoutParams = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1f
        )
        styleRazButton(this)
        // ✅ Utilise importData() déjà définie plus bas
        setOnClickListener { importData() }
    }

    exportImportLayout.addView(btnExporter)
    exportImportLayout.addView(btnImporter)
    card.addView(exportImportLayout)

    // Espace entre lignes
    card.addView(Space(requireContext()).apply {
        layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            dp(10)
        )
    })

    // Bouton "Exporter les logs" -> pleine largeur
    val btnExportLogs = Button(requireContext()).apply {
        text = "🧾 " + (trad["btn_export_logs"] ?: "Exporter les logs")
        layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        styleRazButton(this)

        setOnClickListener {
            (activity as? MainActivity)?.exportAllLogsFromSettings()
                ?: Toast.makeText(
                    requireContext(),
                    "Impossible d’exporter les logs",
                    Toast.LENGTH_SHORT
                ).show()
        }
    }

    card.addView(btnExportLogs)

    // On ajoute la carte complète au container de la page
    container.addView(card)
}

    private fun showManuelDialog() {
        val manuel = ReglagesLangues.getManuel(configLangue.getLangue())
        val scrollView = ScrollView(requireContext())
        val textView = TextView(requireContext()).apply {
            text = manuel
            setPadding(30, 30, 30, 30)
            textSize = 14f
            setTextIsSelectable(true)
        }
        scrollView.addView(textView)
        
        AlertDialog.Builder(requireContext())
            .setTitle("📖 ${trad["btn_manuel"] ?: "Manuel d'utilisation"}")
            .setView(scrollView)
            .setPositiveButton(trad["btn_ok"] ?: "Fermer", null)
            .show()
    }

    private fun showCGVDialog() {
        val cgv = ReglagesLangues.getCGV(configLangue.getLangue())
        val scrollView = ScrollView(requireContext())
        val textView = TextView(requireContext()).apply {
            text = cgv
            setPadding(30, 30, 30, 30)
            textSize = 14f
            setTextIsSelectable(true)
        }
        scrollView.addView(textView)
        
        AlertDialog.Builder(requireContext())
            .setTitle("📄 ${trad["btn_cgv"] ?: "CGV"}")
            .setView(scrollView)
            .setPositiveButton(trad["btn_ok"] ?: "Fermer", null)
            .show()
    }

            private fun showMentionsLegalesDialog() {
                    val mentions = ReglagesLangues.getMentionsLegales(configLangue.getLangue())
                    val tv = TextView(requireContext()).apply {
                text = mentions
                textSize = 14f
                setPadding(50, 30, 50, 30)
            }
        AlertDialog.Builder(requireContext())
            .setTitle("⚖️ ${trad["btn_mentions_legales"] ?: "Mentions légales"}")
            .setView(tv)
            .setPositiveButton(trad["btn_ok"] ?: "Fermer", null)
            .show()
    }

                private fun showMisesAJourDialog() {
        val titre = trad["maj_titre"] ?: "Dernières mises à jour"
        val contenu = trad["maj_contenu"] ?: "Déploiement V1"
        val tv = TextView(requireContext()).apply {
                text = contenu
                textSize = 14f
                setPadding(50, 30, 50, 30)
                setTextIsSelectable(true)
            }

        AlertDialog.Builder(requireContext())
            .setTitle(titre)
            .setView(tv)
            .setPositiveButton(trad["btn_ok"] ?: "Fermer", null)
            .show()
    }

    private fun sendEmail() {
        try {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:stopmauvaiseshabitudes@gmail.com")
                putExtra(Intent.EXTRA_SUBJECT, "StopAddict - Support")
            }
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Erreur: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showRAZConfirmation(type: String) {
        val message = when (type) {
            "jour" -> trad["confirm_raz_jour_titre"] ?: "Effacer les consommations du jour ?"
            "historique" -> trad["confirm_raz_historique_titre"] ?: "Effacer TOUT l'historique ?"
            "usine" -> trad["confirm_raz_usine_titre"] ?: "TOUT réinitialiser (historique + réglages) ?"
            else -> "Confirmer ?"
        }
        
        AlertDialog.Builder(requireContext())
            .setTitle(trad["btn_confirmer"] ?: "Confirmation")
            .setMessage(message)
            .setPositiveButton(trad["btn_confirmer"] ?: "Confirmer") { _, _ ->
                executeRAZ(type)
            }
            .setNegativeButton(trad["btn_annuler"] ?: "Annuler", null)
            .show()
    }

    private fun executeRAZ(type: String) {
        try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val today = dateFormat.format(Date())
            
            when (type) {
                "jour" -> {
                    categoriesActives.forEach { (typeCategorie, _) ->
                        dbHelper.supprimerConsommationsJour(typeCategorie, today)
                    }
                    Toast.makeText(requireContext(), trad["raz_jour_ok"] ?: "RAZ jour effectué", Toast.LENGTH_SHORT).show()
                }
                "historique" -> {
                    categoriesActives.forEach { (typeCategorie, _) ->
                        dbHelper.supprimerToutesConsommations(typeCategorie)
                    }
                    Toast.makeText(requireContext(), trad["raz_historique_ok"] ?: "RAZ historique effectué", Toast.LENGTH_SHORT).show()
                }
                "usine" -> {
                    categoriesActives.forEach { (typeCategorie, _) ->
                        dbHelper.supprimerToutesConsommations(typeCategorie)
                        dbHelper.setCouts(
                        typeCategorie,
                        0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                        0.0, 0.0, 0.0, 0.0, 0.0
                    )

                        dbHelper.setMaxJournalier(typeCategorie, 0)
                        dbHelper.setDatesObjectifs(typeCategorie, "", "", "")
                    }
                    dbHelper.setPreference("prenom", "")
                    dbHelper.setPreference("devise", "EUR")
                    configLangue.setLangue("FR")
                    
                    Toast.makeText(requireContext(), trad["raz_usine_ok"] ?: "RAZ usine effectué", Toast.LENGTH_SHORT).show()
                    activity?.recreate()
                }
            }
            
            updateProfilStatus()
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur RAZ", e)
            Toast.makeText(requireContext(), "Erreur: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
    
        @Suppress("DEPRECATION")
        private fun exportData() {
            try {
                    if (!exportLimiter.peutExporter()) {
            val msg = trad["msg_export_limite"]
                ?: "Pour accéder à l'exportation, passez à la version sans publicité pour en profiter :-)"
            Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
        return
    }
        
            val fileName = "stopaddict_${SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())}.json"
            
            val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "application/json"
                putExtra(Intent.EXTRA_TITLE, fileName)
            }
            startActivityForResult(intent, REQUEST_CODE_EXPORT)
            
            exportLimiter.enregistrerExport()
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur export", e)
            Toast.makeText(requireContext(), "Erreur: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun buildExportJSON(): String {
        val export = JSONObject()
        
        // Préférences
        export.put("prenom", dbHelper.getPreference("prenom", ""))
        export.put("langue", configLangue.getLangue())
        export.put("devise", dbHelper.getPreference("devise", "EUR"))
        export.put("categories_actives", dbHelper.getPreference("categories_actives", "{}"))
        export.put(PREF_MODE_CIGARETTE, dbHelper.getPreference(PREF_MODE_CIGARETTE, "classique"))
        export.put(PREF_NB_CIGARETTES_ROULEES, dbHelper.getPreference(PREF_NB_CIGARETTES_ROULEES, "0"))
        export.put(PREF_NB_CIGARETTES_TUBEES, dbHelper.getPreference(PREF_NB_CIGARETTES_TUBEES, "0"))
        export.put("gramme_par_joint", dbHelper.getPreference("gramme_par_joint", "0"))
        export.put("unite_cl_alcool_global", dbHelper.getPreference("unite_cl_alcool_global", "0"))
        export.put("unite_cl_biere", dbHelper.getPreference("unite_cl_biere", "0"))
        export.put("unite_cl_liqueur", dbHelper.getPreference("unite_cl_liqueur", "0"))
        export.put("unite_cl_alcool_fort", dbHelper.getPreference("unite_cl_alcool_fort", "0"))
        
        // Consommations par catégorie
        val consommations = JSONObject()
        categoriesActives.forEach { (type, _) ->
            val consommationsType = dbHelper.getToutesConsommations(type)
            consommations.put(type, consommationsType)
        }
        export.put("consommations", consommations)
        
        // Coûts, habitudes, dates pour chaque type
        val couts = JSONObject()
        val habitudes = JSONObject()
        val dates = JSONObject()
        
        categoriesActives.forEach { (type, _) ->
            couts.put(type, dbHelper.getCouts(type))
            habitudes.put(type, dbHelper.getMaxJournalier(type))
            dates.put(type, dbHelper.getDatesObjectifs(type))
        }
        
        export.put("couts", couts)
        export.put("habitudes", habitudes)
        export.put("dates", dates)
        
        return export.toString(2)
    }

        @Suppress("DEPRECATION")
    private fun importData() {
        try {
            if (!exportLimiter.peutImporter()) {

                // Si import désactivé (version free), on réutilise le message premium déjà traduit de l'export
                if (exportLimiter.importDesactive()) {
                    val msg = trad["msg_export_limite"]
                        ?: "Pour accéder à l'importation, passez à la version sans publicité pour en profiter"
                    Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
                    return
                }

                // Sinon (limite > 0), on garde le message avec compteur
                val remaining = exportLimiter.getRemainingImports()
                val template = trad["msg_import_limite"]
                    ?: "Limite atteinte. %d import(s) restant(s) aujourd'hui."
                val msg = String.format(template, remaining)

                Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
                return
            }

            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "application/json"
            }
            startActivityForResult(intent, REQUEST_CODE_IMPORT)

            exportLimiter.enregistrerImport()

        } catch (e: Exception) {
            Log.e(TAG, "Erreur import", e)
            Toast.makeText(requireContext(), "Erreur: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    @Suppress("DEPRECATION")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK || data == null) return

        when (requestCode) {
            REQUEST_CODE_EXPORT -> {
                data.data?.let { uri ->
                    try {
                        val outputStream = requireContext().contentResolver.openOutputStream(uri)
                        val writer = OutputStreamWriter(outputStream)
                        writer.write(buildExportJSON())
                        writer.close()
                        Toast.makeText(
                            requireContext(),
                            trad["msg_export_reussi"] ?: "Export réussi",
                            Toast.LENGTH_SHORT
                        ).show()
                    } catch (e: Exception) {
                        Log.e(TAG, "Erreur écriture export", e)
                        Toast.makeText(requireContext(), "Erreur: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            REQUEST_CODE_IMPORT -> {
                data.data?.let { uri ->
                    try {
                        val inputStream = requireContext().contentResolver.openInputStream(uri)
                        val reader = BufferedReader(InputStreamReader(inputStream))
                        val jsonString = reader.readText()
                        reader.close()

                        processImportJSON(jsonString)
                        Toast.makeText(
                            requireContext(),
                            trad["msg_import_reussi"] ?: "Import réussi",
                            Toast.LENGTH_SHORT
                        ).show()
                        loadData()

                    } catch (e: Exception) {
                        Log.e(TAG, "Erreur lecture import", e)
                        Toast.makeText(requireContext(), "Erreur: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun processImportJSON(jsonString: String) {
    try {
        val json = JSONObject(jsonString)

        // 1) Réinitialiser proprement la base pour éviter les mélanges
        dbHelper.razUsine()

        // 2) Restaurer les préférences / profil
        val prenom = json.optString("prenom", "")
        dbHelper.setPreference("prenom", prenom)

        val langue = json.optString("langue", "FR")
        configLangue.setLangue(langue)

        val devise = json.optString("devise", "EUR")
        dbHelper.setPreference("devise", devise)

        val categoriesActivesStr = json.optString("categories_actives", "{}")
        dbHelper.setPreference("categories_actives", categoriesActivesStr)

        // ✅ Restaurer préférences liées aux coûts / modes
        dbHelper.setPreference(PREF_MODE_CIGARETTE, json.optString(PREF_MODE_CIGARETTE, "classique"))
        dbHelper.setPreference(PREF_NB_CIGARETTES_ROULEES, json.optString(PREF_NB_CIGARETTES_ROULEES, "0"))
        dbHelper.setPreference(PREF_NB_CIGARETTES_TUBEES, json.optString(PREF_NB_CIGARETTES_TUBEES, "0"))
        
        dbHelper.setPreference("gramme_par_joint", json.optString("gramme_par_joint", "0"))
        dbHelper.setPreference("unite_cl_alcool_global", json.optString("unite_cl_alcool_global", "0"))
        dbHelper.setPreference("unite_cl_biere", json.optString("unite_cl_biere", "0"))
        dbHelper.setPreference("unite_cl_liqueur", json.optString("unite_cl_liqueur", "0"))
        dbHelper.setPreference("unite_cl_alcool_fort", json.optString("unite_cl_alcool_fort", "0"))

        // Mettre à jour la map locale des catégories actives
        try {
            val jsonCat = JSONObject(categoriesActivesStr)
          categoriesActives["cigarette"] = jsonCat.optBoolean("cigarette", true)
categoriesActives["joint"] = jsonCat.optBoolean("joint", true)
categoriesActives["alcool_global"] = jsonCat.optBoolean("alcool_global", true)
categoriesActives["biere"] = jsonCat.optBoolean("biere", false)
categoriesActives["liqueur"] = jsonCat.optBoolean("liqueur", false)
categoriesActives["alcool_fort"] = jsonCat.optBoolean("alcool_fort", false)
        } catch (e: Exception) {
            Log.e(TAG, "Erreur parse categories_actives à l'import: ${e.message}")
        }

        // 3) Restaurer les coûts par catégorie (ordre cohérent avec saveCouts()/loadCouts())
        val coutsObj = json.optJSONObject("couts")
        if (coutsObj != null) {
        
            val types = listOf(
                DatabaseHelper.TYPE_CIGARETTE,
                DatabaseHelper.TYPE_JOINT,
                DatabaseHelper.TYPE_ALCOOL_GLOBAL,
                DatabaseHelper.TYPE_BIERE,
                DatabaseHelper.TYPE_LIQUEUR,
                DatabaseHelper.TYPE_ALCOOL_FORT
            )

        types.forEach { type ->
            val objType = coutsObj.optJSONObject(type) ?: return@forEach
    
            val prixPaquet = objType.optDouble("prix_paquet", 0.0)
            val nbCigarettes = objType.optDouble("nb_cigarettes", 0.0)
    
            val prixTabac = objType.optDouble("prix_tabac", 0.0)
            val prixFeuilles = objType.optDouble("prix_feuilles", 0.0)
            val nbFeuilles = objType.optDouble("nb_feuilles", 0.0)
    
            val prixFiltres = objType.optDouble("prix_filtres", 0.0)
            val nbFiltres = objType.optDouble("nb_filtres", 0.0)
    
            val prixTubes = objType.optDouble("prix_tubes", 0.0)
            val nbTubes = objType.optDouble("nb_tubes", 0.0)
    
            val prixTabacTube = objType.optDouble("prix_tabac_tube", 0.0)
    
            val prixVerre = objType.optDouble("prix_verre", 0.0)
            val prixGramme = objType.optDouble("prix_gramme", 0.0)
    
            // ✅ On conserve ton principe actuel : pour JOINT, le dernier param sert de "prix_gramme"
            val dernierParam = if (type == DatabaseHelper.TYPE_JOINT) prixGramme else prixVerre
    
            dbHelper.setCouts(
                type,
                prixPaquet,
                nbCigarettes,
                prixTabac,
                prixFeuilles,
                nbFeuilles,
                prixFiltres,
                nbFiltres,
                prixTubes,
                nbTubes,
                prixTabacTube,
                dernierParam
               )
        }
}

        // 4) Restaurer les habitudes (max par jour)
        val habitudesObj = json.optJSONObject("habitudes")
        if (habitudesObj != null) {
            val types = listOf(
                DatabaseHelper.TYPE_CIGARETTE,
                DatabaseHelper.TYPE_JOINT,
                DatabaseHelper.TYPE_ALCOOL_GLOBAL,
                DatabaseHelper.TYPE_BIERE,
                DatabaseHelper.TYPE_LIQUEUR,
                DatabaseHelper.TYPE_ALCOOL_FORT
            )
            types.forEach { type ->
                val max = habitudesObj.optInt(type, 0)
                dbHelper.setMaxJournalier(type, max)
            }
        }

        // 5) Restaurer les dates d'objectifs
        val datesObj = json.optJSONObject("dates")
        if (datesObj != null) {
            val types = listOf(
                DatabaseHelper.TYPE_CIGARETTE,
                DatabaseHelper.TYPE_JOINT,
                DatabaseHelper.TYPE_ALCOOL_GLOBAL,
                DatabaseHelper.TYPE_BIERE,
                DatabaseHelper.TYPE_LIQUEUR,
                DatabaseHelper.TYPE_ALCOOL_FORT
            )
            types.forEach { type ->
                val objDate = datesObj.optJSONObject(type)
                if (objDate != null) {
                    val dateReduction =
                        objDate.optString("date_reduction", objDate.optString("reduction", ""))
                    val dateArret =
                        objDate.optString("date_arret", objDate.optString("arret", ""))
                    val dateReussite =
                        objDate.optString("date_reussite", objDate.optString("reussite", ""))

                    dbHelper.setDatesObjectifs(
                        type,
                        dateReduction,
                        dateArret,
                        dateReussite
                    )
                }
            }
        }

        // 6) Restaurer les consommations
        val consosObj = json.optJSONObject("consommations")
        if (consosObj != null) {
            val keys = consosObj.keys()
            while (keys.hasNext()) {
                val typeKey = keys.next()
                val arr = consosObj.optJSONArray(typeKey) ?: continue
                for (i in 0 until arr.length()) {
                    val cObj = arr.optJSONObject(i) ?: continue
                    val type = cObj.optString("type", typeKey)
                    val quantite = cObj.optInt("quantite", 0)
                    if (quantite <= 0) continue

                    val dateHeure = cObj.optString("date_heure", "")
                    val dateSpec = if (dateHeure.length >= 10) {
                        dateHeure.substring(0, 10)
                    } else {
                        null
                    }

                    dbHelper.ajouterConsommation(type, quantite, dateSpec)
                }
            }
        }

        Log.d(TAG, "Import JSON complet (profil + données) terminé")

        Toast.makeText(
            requireContext(),
            "Import terminé avec succès",
            Toast.LENGTH_LONG
        ).show()

    } catch (e: Exception) {
        Log.e(TAG, "Erreur lors de l'import JSON: ${e.message}", e)
        Toast.makeText(
            requireContext(),
            "Erreur lors de l'import: ${e.message}",
            Toast.LENGTH_LONG
        ).show()
    }
}

    private fun loadData() {
        try {
            // Charger personnalisation
            editPrenom.setText(dbHelper.getPreference("prenom", ""))
            
            val langue = configLangue.getLangue()
            val langues = arrayOf("FR", "EN", "ES", "PT", "DE", "IT", "RU", "AR", "HI", "JA")
            spinnerLangue.setSelection(langues.indexOf(langue).coerceAtLeast(0))
            
            val devise = dbHelper.getPreference("devise", "EUR")
            val devisesAffichees = arrayOf("EUR (€)", "USD ($)", "GBP (£)", "JPY (¥)", "CHF", "CAD", "AUD", "BRL", "INR", "RUB")
            val idx = devisesAffichees.indexOfFirst { it.startsWith(devise) }
            spinnerDevise.setSelection(if (idx >= 0) idx else 0)
            
            // Charger catégories actives
            val json = dbHelper.getPreference("categories_actives", """{"cigarette":true,"joint":true,"alcool_global":true,"biere":false,"liqueur":false,"alcool_fort":false}""")
            val gson = com.google.gson.Gson()
            val type = object : com.google.gson.reflect.TypeToken<Map<String, Boolean>>() {}.type
            val map: Map<String, Boolean> = gson.fromJson(json, type)
            
            categoriesActives.clear()
            categoriesActives.putAll(map)
            
            switchCigarette.isChecked = categoriesActives["cigarette"] ?: true
            switchJoint.isChecked = categoriesActives["joint"] ?: true
            switchAlcoolGlobal.isChecked = categoriesActives["alcool_global"] ?: true
            switchBiere.isChecked = categoriesActives["biere"] ?: false
            switchLiqueur.isChecked = categoriesActives["liqueur"] ?: false
            switchAlcoolFort.isChecked = categoriesActives["alcool_fort"] ?: false
            
            // Charger coûts
            loadCouts()

            // ➕ AJOUT : re-sélectionner le mode de cigarettes mémorisé
        val modeCig = dbHelper.getPreference(PREF_MODE_CIGARETTE, "classique")
    when (modeCig) {
            "rouler" -> radioCigarettesRouler.isChecked = true
            "tuber" -> radioCigarettesTubeuse.isChecked = true
        else    -> radioCigarettesClassiques.isChecked = true
    }
            
            updateProfilStatus()
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur load data", e)
        }
    }

            private fun loadCouts() {
        // Cigarettes
                val coutsCig = dbHelper.getCouts(DatabaseHelper.TYPE_CIGARETTE)
        editPrixPaquet.setText(coutsCig["prix_paquet"]?.toString() ?: "0")
        editNbCigarettes.setText(coutsCig["nb_cigarettes"]?.toInt()?.toString() ?: "0")

        // À rouler
        editPrixTabac.setText(coutsCig["prix_tabac"]?.toString() ?: "0")
        editPrixFeuilles.setText(coutsCig["prix_feuilles"]?.toString() ?: "0")
        editNbFeuilles.setText(coutsCig["nb_feuilles"]?.toInt()?.toString() ?: "0")
        editPrixFiltres.setText(coutsCig["prix_filtres"]?.toString() ?: "0")
        editNbFiltres.setText(coutsCig["nb_filtres"]?.toInt()?.toString() ?: "0")

                // Nombre de cigarettes fabriquées avec ce paquet (rouler)
        val nbCigRouleesPref = dbHelper.getPreference(PREF_NB_CIGARETTES_ROULEES, "0")
        editNbCigarettesRoulees.setText(nbCigRouleesPref)

    // À tuber
        editPrixTabacTubes.setText(coutsCig["prix_tabac_tube"]?.toString() ?: "0")
        editPrixTubes.setText(coutsCig["prix_tubes"]?.toString() ?: "0")
        editNbTubes.setText(coutsCig["nb_tubes"]?.toInt()?.toString() ?: "0")

            // Nombre de cigarettes fabriquées avec ce paquet (tuber)
        val nbCigTubeesPref = dbHelper.getPreference(PREF_NB_CIGARETTES_TUBEES, "0")
        editNbCigarettesTubees.setText(nbCigTubeesPref)
        
        // Joints
        val coutsJoint = dbHelper.getCouts(DatabaseHelper.TYPE_JOINT)
        editPrixGramme.setText(coutsJoint["prix_gramme"]?.toString() ?: "0")
        editGrammeParJoint.setText(dbHelper.getPreference("gramme_par_joint", "0"))
        editPrixFeuillesJoint.setText(coutsJoint["prix_feuilles"]?.toString() ?: "0")
        editNbFeuillesJoint.setText(coutsJoint["nb_feuilles"]?.toInt()?.toString() ?: "0")
        
        // Alcools
        val coutsGlobal = dbHelper.getCouts(DatabaseHelper.TYPE_ALCOOL_GLOBAL)
        editPrixVerreGlobal.setText(coutsGlobal["prix_verre"]?.toString() ?: "0")
        editUniteCLGlobal.setText(dbHelper.getPreference("unite_cl_alcool_global", "0"))
        
        val coutsBiere = dbHelper.getCouts(DatabaseHelper.TYPE_BIERE)
        editPrixVerreBiere.setText(coutsBiere["prix_verre"]?.toString() ?: "0")
        editUniteCLBiere.setText(dbHelper.getPreference("unite_cl_biere", "0"))
        
        val coutsLiqueur = dbHelper.getCouts(DatabaseHelper.TYPE_LIQUEUR)
        editPrixVerreLiqueur.setText(coutsLiqueur["prix_verre"]?.toString() ?: "0")
        editUniteCLLiqueur.setText(dbHelper.getPreference("unite_cl_liqueur", "0"))
        
        val coutsAlcoolFort = dbHelper.getCouts(DatabaseHelper.TYPE_ALCOOL_FORT)
        editPrixVerreAlcoolFort.setText(coutsAlcoolFort["prix_verre"]?.toString() ?: "0")
        editUniteCLAlcoolFort.setText(dbHelper.getPreference("unite_cl_alcool_fort", "0"))
    }

    private fun updateProfilStatus() {
    try {
        // --- CALCUL CENTRALISÉ ---
        val percent = dbHelper.getProfilCompletionPercent(categoriesActives)

        // --- AFFICHAGE (INCHANGÉ) ---
        txtProfilComplet.text =
            if (percent == 100)
                (trad["profil_complet"] ?: "Profil: Complet ✓") + " 100%"
            else
                (trad["profil_incomplet"] ?: "Profil: Incomplet") + " $percent%"

        val iconRes =
            if (percent == 100) R.drawable.ic_status_complete
            else R.drawable.ic_status_incomplete

        txtProfilComplet.setCompoundDrawablesWithIntrinsicBounds(iconRes, 0, 0, 0)
        txtProfilComplet.compoundDrawablePadding = 8.dp

        profilProgress.progress = percent
        txtProfilRestant.visibility = View.GONE

    } catch (e: Exception) {
        Log.e(TAG, "Erreur updateProfilStatus (progression)", e)
    }
}

    // Fonctions utilitaires
    private fun createCard(): LinearLayout {
        return LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(20, 20, 20, 20)
            setBackgroundColor(Color.parseColor("#F5F5F5"))
            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 10, 0, 10)
            layoutParams = params
        }
    }

    private fun addSectionTitle(container: LinearLayout, title: String) {
        val titleView = TextView(requireContext()).apply {
            text = title
            textSize = 18f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setPadding(0, 30, 0, 10)
            setTextColor(Color.parseColor("#1976D2"))
        }
        container.addView(titleView)
    }

    private fun addLabel(container: LinearLayout, label: String) {
        val labelView = TextView(requireContext()).apply {
            text = label
            textSize = 14f
            setPadding(0, 10, 0, 5)
            setTypeface(null, android.graphics.Typeface.BOLD)
        }
        container.addView(labelView)
    }

        private fun getDeviseSymbol(): String {
        val code = dbHelper.getPreference("devise", "EUR")
        return when (code) {
            "EUR" -> "€"
            "USD" -> "$"
            "GBP" -> "£"
            "JPY" -> "¥"
            "CHF" -> "CHF"
            "CAD" -> "C$"
            "AUD" -> "A$"
            "BRL" -> "R$"
            "INR" -> "₹"
            "RUB" -> "₽"
            else  -> code
        }
    }

    private fun createMoneyEditText(): EditText {
    return EditText(requireContext()).apply {
        hint = "0.00"
        // Clavier numérique avec décimales
        inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        // Action principale du clavier = Suivant (Next)
        imeOptions = EditorInfo.IME_ACTION_NEXT
        // Une seule ligne pour éviter les retours à la ligne
        setSingleLine(true)
        setPadding(20, 20, 20, 20)
    }
}
    private fun createNumberEditText(): EditText {
    return EditText(requireContext()).apply {
        hint = "0"
        // Clavier numérique entier
        inputType = InputType.TYPE_CLASS_NUMBER
        // Action principale du clavier = Suivant (Next)
        imeOptions = EditorInfo.IME_ACTION_NEXT
        setSingleLine(true)
        setPadding(20, 20, 20, 20)
    }
}
    private fun addLinkButton(container: LinearLayout, text: String, onClick: () -> Unit) {
        val button = Button(requireContext()).apply {
            this.text = text
            gravity = android.view.Gravity.START or android.view.Gravity.CENTER_VERTICAL
            setBackgroundColor(Color.TRANSPARENT)
            setTextColor(Color.parseColor("#1976D2"))
            setPadding(20, 30, 20, 30)
            setOnClickListener { onClick() }
        }
        container.addView(button)
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }
        private fun parseDouble(text: String): Double {
        if (text.isBlank()) return 0.0
        return text.replace(',', '.').toDoubleOrNull() ?: 0.0
    }

private val Int.dp: Int
    get() = (this * resources.displayMetrics.density).roundToInt()

}

