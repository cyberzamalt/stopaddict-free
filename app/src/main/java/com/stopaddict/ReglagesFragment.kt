package com.stopaddict

import android.app.Activity
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
import java.text.SimpleDateFormat
import java.util.*

class ReglagesFragment : Fragment() {

    companion object {
        private const val TAG = "ReglagesFragment"
        private const val REQUEST_CODE_EXPORT = 1001
        private const val REQUEST_CODE_IMPORT = 1002
    }

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var configLangue: ConfigLangue
    private lateinit var exportLimiter: ExportLimiter
    private lateinit var trad: Map<String, String>
    
    private lateinit var txtProfilComplet: TextView
    private lateinit var txtTotalJour: TextView
    private lateinit var editPrenom: EditText
    private lateinit var spinnerLangue: Spinner
    private lateinit var spinnerDevise: Spinner
    
    // Catégories
    private lateinit var switchCigarette: Switch
    private lateinit var switchJoint: Switch
    private lateinit var switchAlcoolGlobal: Switch
    private lateinit var switchBiere: Switch
    private lateinit var switchLiqueur: Switch
    private lateinit var switchAlcoolFort: Switch
    
    // Cigarettes - 3 modes
    private lateinit var radioCigarettesClassiques: RadioButton
    private lateinit var radioCigarettesRouler: RadioButton
    private lateinit var radioCigarettesTubeuse: RadioButton
    
    // Paquet classique
    private lateinit var editPrixPaquet: EditText
    private lateinit var editNbCigarettes: EditText
    
    // À rouler
    private lateinit var editPrixTabac: EditText
    private lateinit var editPrixFeuilles: EditText
    private lateinit var editNbFeuilles: EditText
    private lateinit var editPrixFiltres: EditText
    private lateinit var editNbFiltres: EditText
    
    // À tuber
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
            setPadding(20, 10, 20, 20)
        }
        
        // Header
        addHeader(contentContainer)
        
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
    private fun addHeader(container: LinearLayout) {
        val header = TextView(requireContext()).apply {
            text = trad["titre"] ?: "Réglages"
            textSize = 24f
            setBackgroundColor(Color.parseColor("#00BCD4"))
            setTextColor(Color.WHITE)
            setPadding(20, 40, 20, 40)
            gravity = android.view.Gravity.CENTER
            setTypeface(null, android.graphics.Typeface.BOLD)
        }
        container.addView(header)
        
        // Zone profil
        val profilContainer = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.HORIZONTAL
            setPadding(20, 10, 20, 10)
            setBackgroundColor(Color.parseColor("#F5F5F5"))
        }
        
        txtProfilComplet = TextView(requireContext()).apply {
            text = trad["profil_incomplet"] ?: "Profil: Incomplet"
            textSize = 14f
            layoutParams = LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1f
            )
        }
        profilContainer.addView(txtProfilComplet)

        txtTotalJour = TextView(requireContext()).apply {
            text = "${trad["total_aujourdhui"] ?: "Total jour"}: 0"
            textSize = 14f
            gravity = android.view.Gravity.END
        }
        profilContainer.addView(txtTotalJour)
        container.addView(profilContainer)
    }

    private fun addPersonnalisationSection(container: LinearLayout) {
        addSectionTitle(container, trad["titre_profil"] ?: "Personnalisation")
        
        // Prénom
        addLabel(container, trad["label_prenom"] ?: "Prénom")
        editPrenom = EditText(requireContext()).apply {
            hint = trad["hint_prenom"] ?: "Entrer votre prénom"
            inputType = InputType.TYPE_CLASS_TEXT
            setPadding(20, 20, 20, 20)
        }
        container.addView(editPrenom)
        
        // Langue
        addLabel(container, trad["label_langue"] ?: "Langue")
        spinnerLangue = Spinner(requireContext())
        val langues = arrayOf("FR", "EN", "ES", "PT", "DE", "IT", "RU", "AR", "HI", "JA")
        spinnerLangue.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, langues)
        container.addView(spinnerLangue)
        
        // Devise
        addLabel(container, trad["label_devise"] ?: "Devise")
        spinnerDevise = Spinner(requireContext())
        val devises = arrayOf("EUR (€)", "USD ($)", "GBP (£)", "JPY (¥)", "CHF", "CAD", "AUD", "BRL", "INR", "RUB")
        spinnerDevise.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, devises)
        container.addView(spinnerDevise)
        
        // Bouton sauvegarder personnalisation
        val btnSavePerso = Button(requireContext()).apply {
            text = trad["btn_sauvegarder_profil"] ?: "Sauvegarder"
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
        switchCigarette = Switch(requireContext()).apply {
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
        addLabel(paquetContainer, trad["label_prix_paquet"] ?: "Prix du paquet")
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
        addLabel(roulerContainer, trad["label_prix_tabac"] ?: "Prix du tabac à rouler")
        editPrixTabac = createMoneyEditText()
        roulerContainer.addView(editPrixTabac)
        
        addLabel(roulerContainer, trad["label_prix_feuilles"] ?: "Prix des feuilles à rouler")
        editPrixFeuilles = createMoneyEditText()
        roulerContainer.addView(editPrixFeuilles)
        
        addLabel(roulerContainer, trad["label_nb_feuilles"] ?: "Nombre de feuilles")
        editNbFeuilles = createNumberEditText()
        roulerContainer.addView(editNbFeuilles)
        
        addLabel(roulerContainer, trad["label_prix_filtres"] ?: "Prix du sachet de filtres")
        editPrixFiltres = createMoneyEditText()
        roulerContainer.addView(editPrixFiltres)
        
        addLabel(roulerContainer, trad["label_nb_filtres"] ?: "Nombre de filtres")
        editNbFiltres = createNumberEditText()
        roulerContainer.addView(editNbFiltres)
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
        addLabel(tuberContainer, trad["label_prix_tabac"] ?: "Prix du tabac à tuber")
        // Réutilise editPrixTabac - on récupère la valeur selon le mode
        
        addLabel(tuberContainer, trad["label_prix_tubes"] ?: "Prix des tubes")
        editPrixTubes = createMoneyEditText()
        tuberContainer.addView(editPrixTubes)
        
        addLabel(tuberContainer, trad["label_nb_tubes"] ?: "Nombre de tubes")
        editNbTubes = createNumberEditText()
        tuberContainer.addView(editNbTubes)
        radioGroup.addView(tuberContainer)
        
        cigaretteCard.addView(radioGroup)
        container.addView(cigaretteCard)
    }

    private fun addJointsSection(container: LinearLayout) {
        val jointCard = createCard()
        
        switchJoint = Switch(requireContext()).apply {
            text = trad["label_joints"] ?: "Joints (Cannabis)"
            textSize = 16f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setOnCheckedChangeListener { _, isChecked ->
                categoriesActives["joint"] = isChecked
                saveCategoriesActives()
            }
        }
        jointCard.addView(switchJoint)
        
        addLabel(jointCard, trad["label_prix_gramme"] ?: "Prix du gramme")
        editPrixGramme = createMoneyEditText()
        jointCard.addView(editPrixGramme)
        
        addLabel(jointCard, trad["label_gramme_par_joint"] ?: "Grammes par joint")
        editGrammeParJoint = EditText(requireContext()).apply {
            hint = "0.5"
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            setPadding(20, 20, 20, 20)
        }
        jointCard.addView(editGrammeParJoint)
        
        addLabel(jointCard, trad["label_prix_feuilles_longues"] ?: "Prix des feuilles longues")
        editPrixFeuillesJoint = createMoneyEditText()
        jointCard.addView(editPrixFeuillesJoint)
        
        addLabel(jointCard, trad["label_nb_feuilles_longues"] ?: "Nombre de feuilles longues")
        editNbFeuillesJoint = createNumberEditText()
        jointCard.addView(editNbFeuillesJoint)
        
        container.addView(jointCard)
    }

    private fun addAlcoolGlobalSection(container: LinearLayout) {
        val alcoolCard = createCard()
        
        switchAlcoolGlobal = Switch(requireContext()).apply {
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
        
        addLabel(alcoolCard, trad["label_prix_verre"] ?: "Prix du verre (alcool global)")
        editPrixVerreGlobal = createMoneyEditText()
        alcoolCard.addView(editPrixVerreGlobal)
        
        addLabel(alcoolCard, trad["unite_cl_global"] ?: "Unité en cL")
        editUniteCLGlobal = EditText(requireContext()).apply {
            hint = "25"
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            setPadding(20, 20, 20, 20)
        }
        alcoolCard.addView(editUniteCLGlobal)
        
        container.addView(alcoolCard)
    }

    private fun addBieresSection(container: LinearLayout) {
        val biereCard = createCard()
        
        switchBiere = Switch(requireContext()).apply {
            text = trad["label_bieres"] ?: "Bières"
            textSize = 16f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setOnCheckedChangeListener { _, isChecked ->
                categoriesActives["biere"] = isChecked
                saveCategoriesActives()
            }
        }
        biereCard.addView(switchBiere)
        
        addLabel(biereCard, trad["label_prix_verre"] ?: "Prix du verre de bière")
        editPrixVerreBiere = createMoneyEditText()
        biereCard.addView(editPrixVerreBiere)
        
        addLabel(biereCard, trad["unite_cl_biere"] ?: "Unité en cL")
        editUniteCLBiere = EditText(requireContext()).apply {
            hint = "25"
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            setPadding(20, 20, 20, 20)
        }
        biereCard.addView(editUniteCLBiere)
        
        container.addView(biereCard)
    }

    private fun addLiqueursSection(container: LinearLayout) {
        val liqueurCard = createCard()
        
        switchLiqueur = Switch(requireContext()).apply {
            text = trad["label_liqueurs"] ?: "Liqueurs"
            textSize = 16f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setOnCheckedChangeListener { _, isChecked ->
                categoriesActives["liqueur"] = isChecked
                saveCategoriesActives()
            }
        }
        liqueurCard.addView(switchLiqueur)
        
        addLabel(liqueurCard, trad["label_prix_verre"] ?: "Prix du verre de liqueur")
        editPrixVerreLiqueur = createMoneyEditText()
        liqueurCard.addView(editPrixVerreLiqueur)
        
        addLabel(liqueurCard, trad["unite_cl_liqueur"] ?: "Unité en cL")
        editUniteCLLiqueur = EditText(requireContext()).apply {
            hint = "4"
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            setPadding(20, 20, 20, 20)
        }
        liqueurCard.addView(editUniteCLLiqueur)
        
        container.addView(liqueurCard)
    }

    private fun addAlcoolFortSection(container: LinearLayout) {
        val alcoolFortCard = createCard()
        
        switchAlcoolFort = Switch(requireContext()).apply {
            text = trad["label_alcool_fort"] ?: "Alcool Fort"
            textSize = 16f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setOnCheckedChangeListener { _, isChecked ->
                categoriesActives["alcool_fort"] = isChecked
                saveCategoriesActives()
            }
        }
        alcoolFortCard.addView(switchAlcoolFort)
        
        addLabel(alcoolFortCard, trad["label_prix_verre"] ?: "Prix du verre d'alcool fort")
        editPrixVerreAlcoolFort = createMoneyEditText()
        alcoolFortCard.addView(editPrixVerreAlcoolFort)
        
        addLabel(alcoolFortCard, trad["unite_cl_alcool_fort"] ?: "Unité en cL")
        editUniteCLAlcoolFort = EditText(requireContext()).apply {
            hint = "4"
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
            // Cigarettes selon le mode
            when {
                radioCigarettesClassiques.isChecked -> {
                    val prixPaquet = editPrixPaquet.text.toString().toDoubleOrNull() ?: 0.0
                    val nbCigarettes = editNbCigarettes.text.toString().toDoubleOrNull() ?: 20.0
                    dbHelper.setCouts(DatabaseHelper.TYPE_CIGARETTE, prixPaquet, nbCigarettes, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
                }
                radioCigarettesRouler.isChecked -> {
                    val prixTabac = editPrixTabac.text.toString().toDoubleOrNull() ?: 0.0
                    val prixFeuilles = editPrixFeuilles.text.toString().toDoubleOrNull() ?: 0.0
                    val nbFeuilles = editNbFeuilles.text.toString().toDoubleOrNull() ?: 32.0
                    val prixFiltres = editPrixFiltres.text.toString().toDoubleOrNull() ?: 0.0
                    val nbFiltres = editNbFiltres.text.toString().toDoubleOrNull() ?: 100.0
                    dbHelper.setCouts(DatabaseHelper.TYPE_CIGARETTE, 0.0, 0.0, prixTabac, prixFeuilles, nbFeuilles, prixFiltres, nbFiltres, 0.0, 0.0, 0.0)
                }
                radioCigarettesTubeuse.isChecked -> {
                    val prixTabac = editPrixTabac.text.toString().toDoubleOrNull() ?: 0.0
                    val prixTubes = editPrixTubes.text.toString().toDoubleOrNull() ?: 0.0
                    val nbTubes = editNbTubes.text.toString().toDoubleOrNull() ?: 100.0
                    dbHelper.setCouts(DatabaseHelper.TYPE_CIGARETTE, 0.0, 0.0, prixTabac, 0.0, 0.0, 0.0, 0.0, prixTubes, nbTubes, 0.0)
                }
            }

            // Joints
            val prixGramme = editPrixGramme.text.toString().toDoubleOrNull() ?: 0.0
            val grammeParJoint = editGrammeParJoint.text.toString().toDoubleOrNull() ?: 0.5
            val prixFeuillesJoint = editPrixFeuillesJoint.text.toString().toDoubleOrNull() ?: 0.0
            val nbFeuillesJoint = editNbFeuillesJoint.text.toString().toDoubleOrNull() ?: 32.0
            dbHelper.setCouts(DatabaseHelper.TYPE_JOINT, 0.0, 0.0, 0.0, prixFeuillesJoint, nbFeuillesJoint, 0.0, 0.0, 0.0, 0.0, prixGramme)
            dbHelper.setPreference("gramme_par_joint", grammeParJoint.toString())

            // Alcool global
            val prixVerreGlobal = editPrixVerreGlobal.text.toString().toDoubleOrNull() ?: 0.0
            val uniteCLGlobal = editUniteCLGlobal.text.toString().toDoubleOrNull() ?: 25.0
            dbHelper.setCouts(DatabaseHelper.TYPE_ALCOOL_GLOBAL, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, prixVerreGlobal)
            dbHelper.setPreference("unite_cl_alcool_global", uniteCLGlobal.toString())

            // Bières
            val prixVerreBiere = editPrixVerreBiere.text.toString().toDoubleOrNull() ?: 0.0
            val uniteCLBiere = editUniteCLBiere.text.toString().toDoubleOrNull() ?: 25.0
            dbHelper.setCouts(DatabaseHelper.TYPE_BIERE, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, prixVerreBiere)
            dbHelper.setPreference("unite_cl_biere", uniteCLBiere.toString())

            // Liqueurs
            val prixVerreLiqueur = editPrixVerreLiqueur.text.toString().toDoubleOrNull() ?: 0.0
            val uniteCLLiqueur = editUniteCLLiqueur.text.toString().toDoubleOrNull() ?: 4.0
            dbHelper.setCouts(DatabaseHelper.TYPE_LIQUEUR, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, prixVerreLiqueur)
            dbHelper.setPreference("unite_cl_liqueur", uniteCLLiqueur.toString())

            // Alcool fort
            val prixVerreAlcoolFort = editPrixVerreAlcoolFort.text.toString().toDoubleOrNull() ?: 0.0
            val uniteCLAlcoolFort = editUniteCLAlcoolFort.text.toString().toDoubleOrNull() ?: 4.0
            dbHelper.setCouts(DatabaseHelper.TYPE_ALCOOL_FORT, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, prixVerreAlcoolFort)
            dbHelper.setPreference("unite_cl_alcool_fort", uniteCLAlcoolFort.toString())

            Toast.makeText(requireContext(), trad["msg_profil_sauvegarde"] ?: "Coûts sauvegardés", Toast.LENGTH_SHORT).show()
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
        addLinkButton(aProposCard, "⚠️ ${trad["voir_avertissement"] ?: "Voir l'avertissement"}") {
            showAvertissementDialog()
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
        
        // Lien 5: Contact support
        addLinkButton(aProposCard, "✉️ ${trad["btn_contact"] ?: "Contact support"}") {
            sendEmail()
        }
        
        container.addView(aProposCard)
    }

    private fun addRAZSection(container: LinearLayout) {
        addSectionTitle(container, trad["raz_sauvegarde"] ?: "RAZ et Sauvegarde")
        
        val razCard = createCard()
        
        // RAZ jour
        val btnRAZJour = Button(requireContext()).apply {
            text = trad["raz_jour"] ?: "RAZ jour"
            setBackgroundColor(Color.parseColor("#FFA726"))
            setTextColor(Color.WHITE)
            setOnClickListener {
                showRAZConfirmation("jour")
            }
        }
        razCard.addView(btnRAZJour)
        
        // RAZ historique
        val btnRAZHistorique = Button(requireContext()).apply {
            text = trad["raz_historique"] ?: "RAZ historique"
            setBackgroundColor(Color.parseColor("#FF7043"))
            setTextColor(Color.WHITE)
            setOnClickListener {
                showRAZConfirmation("historique")
            }
        }
        razCard.addView(btnRAZHistorique)
        
        // RAZ usine
        val btnRAZUsine = Button(requireContext()).apply {
            text = trad["raz_usine"] ?: "RAZ réglages usine"
            setBackgroundColor(Color.parseColor("#D32F2F"))
            setTextColor(Color.WHITE)
            setOnClickListener {
                showRAZConfirmation("usine")
            }
        }
        razCard.addView(btnRAZUsine)
        
        // Export
        val btnExport = Button(requireContext()).apply {
            text = trad["btn_exporter"] ?: "Exporter"
            setBackgroundColor(Color.parseColor("#66BB6A"))
            setTextColor(Color.WHITE)
            setOnClickListener {
                exportData()
            }
        }
        razCard.addView(btnExport)
        
        // Import
        val btnImport = Button(requireContext()).apply {
            text = trad["btn_importer"] ?: "Importer"
            setBackgroundColor(Color.parseColor("#42A5F5"))
            setTextColor(Color.WHITE)
            setOnClickListener {
                importData()
            }
        }
        razCard.addView(btnImport)
        
        container.addView(razCard)
    }

    private fun showAvertissementDialog() {
        val message = ReglagesLangues.getAvertissement(configLangue.getLangue())
        AlertDialog.Builder(requireContext())
            .setTitle("⚠️ ${trad["btn_avertissement"] ?: "Avertissement"}")
            .setMessage(message)
            .setPositiveButton(trad["btn_ok"] ?: "Fermer", null)
            .show()
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
        AlertDialog.Builder(requireContext())
            .setTitle("⚖️ ${trad["btn_mentions_legales"] ?: "Mentions légales"}")
            .setMessage(mentions)
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
                        dbHelper.setCouts(typeCategorie, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
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

    private fun exportData() {
        try {
            if (!exportLimiter.peutExporter()) {
                val remaining = exportLimiter.getRemainingExports()
                Toast.makeText(requireContext(), "Limite atteinte. $remaining exports restants aujourd'hui", Toast.LENGTH_LONG).show()
                return
            }
            
            val jsonData = buildExportJSON()
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

    private fun importData() {
        try {
            if (!exportLimiter.peutImporter()) {
                val remaining = exportLimiter.getRemainingImports()
                Toast.makeText(requireContext(), "Limite atteinte. $remaining imports restants aujourd'hui", Toast.LENGTH_LONG).show()
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
                        Toast.makeText(requireContext(), trad["msg_export_reussi"] ?: "Export réussi", Toast.LENGTH_SHORT).show()
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
                        Toast.makeText(requireContext(), trad["msg_import_reussi"] ?: "Import réussi", Toast.LENGTH_SHORT).show()
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
        val json = JSONObject(jsonString)
        
        // Restaurer préférences
        dbHelper.setPreference("prenom", json.optString("prenom", ""))
        configLangue.setLangue(json.optString("langue", "FR"))
        dbHelper.setPreference("devise", json.optString("devise", "EUR"))
        dbHelper.setPreference("categories_actives", json.optString("categories_actives", "{}"))
        
        // TODO: Restaurer consommations, coûts, habitudes, dates
        Log.d(TAG, "Import terminé")
    }

    private fun loadData() {
        try {
            // Charger personnalisation
            editPrenom.setText(dbHelper.getPreference("prenom", ""))
            
            val langue = configLangue.getLangue()
            val langues = arrayOf("FR", "EN", "ES", "PT", "DE", "IT", "RU", "AR", "HI", "JA")
            spinnerLangue.setSelection(langues.indexOf(langue).coerceAtLeast(0))
            
            val devise = dbHelper.getPreference("devise", "EUR")
            val devises = arrayOf("EUR", "USD", "GBP", "JPY", "CHF", "CAD", "AUD", "BRL", "INR", "RUB")
            spinnerDevise.setSelection(devises.indexOf(devise).coerceAtLeast(0))
            
            // Charger catégories actives
            val json = dbHelper.getPreference("categories_actives", """{"cigarette":true,"joint":true,"alcool_global":true,"biere":false,"liqueur":false,"alcool_fort":false}""")
            val gson = com.google.gson.Gson()
            val map = gson.fromJson(json, Map::class.java) as Map<String, Boolean>
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
            
            updateProfilStatus()
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur load data", e)
        }
    }

            private fun loadCouts() {
        // Cigarettes
        val coutsCig = dbHelper.getCouts(DatabaseHelper.TYPE_CIGARETTE)
        editPrixPaquet.setText(coutsCig["prix_paquet"]?.toString() ?: "0")
        editNbCigarettes.setText(coutsCig["nb_cigarettes"]?.toInt()?.toString() ?: "20")
        editPrixTabac.setText(coutsCig["prix_tabac"]?.toString() ?: "0")
        editPrixFeuilles.setText(coutsCig["prix_feuilles"]?.toString() ?: "0")
        editNbFeuilles.setText(coutsCig["nb_feuilles"]?.toInt()?.toString() ?: "32")
        editPrixFiltres.setText(coutsCig["prix_filtres"]?.toString() ?: "0")
        editNbFiltres.setText(coutsCig["nb_filtres"]?.toInt()?.toString() ?: "100")
        editPrixTubes.setText(coutsCig["prix_tubes"]?.toString() ?: "0")
        editNbTubes.setText(coutsCig["nb_tubes"]?.toInt()?.toString() ?: "100")
        
        // Joints
        val coutsJoint = dbHelper.getCouts(DatabaseHelper.TYPE_JOINT)
        editPrixGramme.setText(coutsJoint["prix_verre"]?.toString() ?: "0")
        editGrammeParJoint.setText(dbHelper.getPreference("gramme_par_joint", "0.5"))
        editPrixFeuillesJoint.setText(coutsJoint["prix_feuilles"]?.toString() ?: "0")
        editNbFeuillesJoint.setText(coutsJoint["nb_feuilles"]?.toInt()?.toString() ?: "32")
        
        // Alcools
        val coutsGlobal = dbHelper.getCouts(DatabaseHelper.TYPE_ALCOOL_GLOBAL)
        editPrixVerreGlobal.setText(coutsGlobal["prix_verre"]?.toString() ?: "0")
        editUniteCLGlobal.setText(dbHelper.getPreference("unite_cl_alcool_global", "25"))
        
        val coutsBiere = dbHelper.getCouts(DatabaseHelper.TYPE_BIERE)
        editPrixVerreBiere.setText(coutsBiere["prix_verre"]?.toString() ?: "0")
        editUniteCLBiere.setText(dbHelper.getPreference("unite_cl_biere", "25"))
        
        val coutsLiqueur = dbHelper.getCouts(DatabaseHelper.TYPE_LIQUEUR)
        editPrixVerreLiqueur.setText(coutsLiqueur["prix_verre"]?.toString() ?: "0")
        editUniteCLLiqueur.setText(dbHelper.getPreference("unite_cl_liqueur", "4"))
        
        val coutsAlcoolFort = dbHelper.getCouts(DatabaseHelper.TYPE_ALCOOL_FORT)
        editPrixVerreAlcoolFort.setText(coutsAlcoolFort["prix_verre"]?.toString() ?: "0")
        editUniteCLAlcoolFort.setText(dbHelper.getPreference("unite_cl_alcool_fort", "4"))
    }

    private fun updateProfilStatus() {
        try {
            val langue = configLangue.getLangue()
            val trad = ReglagesLangues.getTraductions(langue)

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
                    dates.values.any { it?.isNotEmpty() == true }
                } else false
            }

            val isComplet = hasPrenom && hasCouts && hasHabitudes && hasDates

            txtProfilComplet.text = if (isComplet) {
                trad["profil_complet"] ?: "Profil: Complet ✓"
            } else {
                trad["profil_incomplet"] ?: "Profil: Incomplet"
            }

            // Total jour
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val today = dateFormat.format(Date())
            var total = 0
            categoriesActives.forEach { (type, active) ->
                if (active) {
                    total += dbHelper.getConsommationParDate(type, today)
                }
            }

            val labelTotalJour = trad["total_aujourdhui"] ?: "Total jour"
            txtTotalJour.text = "$labelTotalJour: $total"

        } catch (e: Exception) {
            Log.e(TAG, "Erreur updateProfilStatus: ${e.message}", e)
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

    private fun createMoneyEditText(): EditText {
        return EditText(requireContext()).apply {
            hint = "0.00"
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            setPadding(20, 20, 20, 20)
        }
    }

    private fun createNumberEditText(): EditText {
        return EditText(requireContext()).apply {
            hint = "0"
            inputType = InputType.TYPE_CLASS_NUMBER
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
}
