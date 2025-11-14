package com.stopaddict

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import org.json.JSONObject
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class ReglagesFragment : Fragment() {

    companion object {
        private const val TAG = "ReglagesFragment"
        private const val EXPORT_FILENAME = "stopaddict_backup.json"
    }

    // Database et config
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var configLangue: ConfigLangue
    private lateinit var exportLimiter: ExportLimiter

    // UI Elements - Profil
    private lateinit var editPrenom: EditText
    private lateinit var spinnerLangue: Spinner
    private lateinit var spinnerDevise: Spinner
    private lateinit var btnSauvegarderProfil: Button

    // UI Elements - Cases catégories
    private lateinit var checkCigarettes: CheckBox
    private lateinit var checkJoints: CheckBox
    private lateinit var checkAlcoolGlobal: CheckBox
    private lateinit var checkBieres: CheckBox
    private lateinit var checkLiqueurs: CheckBox
    private lateinit var checkAlcoolFort: CheckBox

    // UI Elements - Formulaires coûts cigarettes
    private lateinit var containerCoutsCigarettes: LinearLayout
    private lateinit var radioCigarettesClassiques: RadioButton
    private lateinit var radioCigarettesRouler: RadioButton
    private lateinit var radioCigarettesTubeuse: RadioButton
    private lateinit var editPrixPaquet: EditText
    private lateinit var editNbCigarettes: EditText
    private lateinit var editPrixTabac: EditText
    private lateinit var editPrixFeuilles: EditText
    private lateinit var editNbFeuilles: EditText
    private lateinit var editPrixFiltres: EditText
    private lateinit var editNbFiltres: EditText
    private lateinit var editPrixTubes: EditText
    private lateinit var editNbTubes: EditText

    // UI Elements - Formulaires coûts joints
    private lateinit var containerCoutsJoints: LinearLayout
    private lateinit var editPrixGramme: EditText
    private lateinit var editGrammeParJoint: EditText

    // UI Elements - Formulaires coûts alcools
    private lateinit var containerCoutsAlcoolGlobal: LinearLayout
    private lateinit var containerCoutsBieres: LinearLayout
    private lateinit var containerCoutsLiqueurs: LinearLayout
    private lateinit var containerCoutsAlcoolFort: LinearLayout
    private lateinit var editPrixVerreGlobal: EditText
    private lateinit var editUniteCLGlobal: EditText
    private lateinit var editPrixVerreBiere: EditText
    private lateinit var editUniteCLBiere: EditText
    private lateinit var editPrixVerreLiqueur: EditText
    private lateinit var editUniteCLLiqueur: EditText
    private lateinit var editPrixVerreAlcoolFort: EditText
    private lateinit var editUniteCLAlcoolFort: EditText

    // UI Elements - Habitudes
    private lateinit var editMaxCigarettes: EditText
    private lateinit var editMaxJoints: EditText
    private lateinit var editMaxAlcoolGlobal: EditText
    private lateinit var editMaxBieres: EditText
    private lateinit var editMaxLiqueurs: EditText
    private lateinit var editMaxAlcoolFort: EditText

    // UI Elements - Dates objectifs
    private lateinit var btnDateReductionCigarettes: Button
    private lateinit var btnDateArretCigarettes: Button
    private lateinit var btnDateReussiteCigarettes: Button
    private lateinit var btnDateReductionJoints: Button
    private lateinit var btnDateArretJoints: Button
    private lateinit var btnDateReussiteJoints: Button
    private lateinit var btnDateReductionAlcoolGlobal: Button
    private lateinit var btnDateArretAlcoolGlobal: Button
    private lateinit var btnDateReussiteAlcoolGlobal: Button
    // (même pattern pour bières, liqueurs, alcool fort)

    // UI Elements - RAZ et Export/Import
    private lateinit var btnRazJour: Button
    private lateinit var btnRazHistorique: Button
    private lateinit var btnRazUsine: Button
    private lateinit var btnExporter: Button
    private lateinit var btnImporter: Button

    // UI Elements - Bandeau profil
    private lateinit var txtProfilComplet: TextView
    private lateinit var txtTotalAujourdhui: TextView

    // Variables état
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
        val view = inflater.inflate(R.layout.fragment_reglages, container, false)

        try {
            // Initialisation database et config
            dbHelper = DatabaseHelper(requireContext())
            configLangue = ConfigLangue(requireContext())
            exportLimiter = ExportLimiter(requireContext())

            // Initialisation des vues
            initViews(view)

            // Chargement données
            loadCategoriesActives()
            loadProfil()
            loadCouts()
            loadHabitudes()
            loadDates()

            // Configuration listeners
            setupListeners()

            // Mise à jour UI
            updateProfilStatus()

            Log.d(TAG, "ReglagesFragment initialisé avec succès")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur initialisation ReglagesFragment: ${e.message}")
            Toast.makeText(requireContext(), "Erreur chargement Réglages", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun initViews(view: View) {
        try {
            // Profil
            editPrenom = view.findViewById(R.id.reglages_edit_prenom)
            spinnerLangue = view.findViewById(R.id.reglages_spinner_langue)
            spinnerDevise = view.findViewById(R.id.reglages_spinner_devise)
            btnSauvegarderProfil = view.findViewById(R.id.reglages_btn_sauvegarder_profil)

            // Cases catégories
            checkCigarettes = view.findViewById(R.id.reglages_check_cigarettes)
            checkJoints = view.findViewById(R.id.reglages_check_joints)
            checkAlcoolGlobal = view.findViewById(R.id.reglages_check_alcool_global)
            checkBieres = view.findViewById(R.id.reglages_check_bieres)
            checkLiqueurs = view.findViewById(R.id.reglages_check_liqueurs)
            checkAlcoolFort = view.findViewById(R.id.reglages_check_alcool_fort)

            // Conteneurs coûts
            containerCoutsCigarettes = view.findViewById(R.id.reglages_container_couts_cigarettes)
            containerCoutsJoints = view.findViewById(R.id.reglages_container_couts_joints)
            containerCoutsAlcoolGlobal = view.findViewById(R.id.reglages_container_couts_alcool_global)
            containerCoutsBieres = view.findViewById(R.id.reglages_container_couts_bieres)
            containerCoutsLiqueurs = view.findViewById(R.id.reglages_container_couts_liqueurs)
            containerCoutsAlcoolFort = view.findViewById(R.id.reglages_container_couts_alcool_fort)

            // Formulaires coûts cigarettes
            radioCigarettesClassiques = view.findViewById(R.id.reglages_radio_classiques)
            radioCigarettesRouler = view.findViewById(R.id.reglages_radio_rouler)
            radioCigarettesTubeuse = view.findViewById(R.id.reglages_radio_tubeuse)
            editPrixPaquet = view.findViewById(R.id.reglages_edit_prix_paquet)
            editNbCigarettes = view.findViewById(R.id.reglages_edit_nb_cigarettes)
            editPrixTabac = view.findViewById(R.id.reglages_edit_prix_tabac)
            editPrixFeuilles = view.findViewById(R.id.reglages_edit_prix_feuilles)
            editNbFeuilles = view.findViewById(R.id.reglages_edit_nb_feuilles)
            editPrixFiltres = view.findViewById(R.id.reglages_edit_prix_filtres)
            editNbFiltres = view.findViewById(R.id.reglages_edit_nb_filtres)
            editPrixTubes = view.findViewById(R.id.reglages_edit_prix_tubes)
            editNbTubes = view.findViewById(R.id.reglages_edit_nb_tubes)

            // Formulaires coûts joints
            editPrixGramme = view.findViewById(R.id.reglages_edit_prix_gramme)
            editGrammeParJoint = view.findViewById(R.id.reglages_edit_gramme_par_joint)

            // Formulaires coûts alcools
            editPrixVerreGlobal = view.findViewById(R.id.reglages_edit_prix_verre_global)
            editUniteCLGlobal = view.findViewById(R.id.reglages_edit_unite_cl_global)
            editPrixVerreBiere = view.findViewById(R.id.reglages_edit_prix_verre_biere)
            editUniteCLBiere = view.findViewById(R.id.reglages_edit_unite_cl_biere)
            editPrixVerreLiqueur = view.findViewById(R.id.reglages_edit_prix_verre_liqueur)
            editUniteCLLiqueur = view.findViewById(R.id.reglages_edit_unite_cl_liqueur)
            editPrixVerreAlcoolFort = view.findViewById(R.id.reglages_edit_prix_verre_alcool_fort)
            editUniteCLAlcoolFort = view.findViewById(R.id.reglages_edit_unite_cl_alcool_fort)

            // Habitudes
            editMaxCigarettes = view.findViewById(R.id.reglages_edit_max_cigarettes)
            editMaxJoints = view.findViewById(R.id.reglages_edit_max_joints)
            editMaxAlcoolGlobal = view.findViewById(R.id.reglages_edit_max_alcool_global)
            editMaxBieres = view.findViewById(R.id.reglages_edit_max_bieres)
            editMaxLiqueurs = view.findViewById(R.id.reglages_edit_max_liqueurs)
            editMaxAlcoolFort = view.findViewById(R.id.reglages_edit_max_alcool_fort)

            // Dates objectifs (cigarettes)
            btnDateReductionCigarettes = view.findViewById(R.id.reglages_btn_date_reduction_cigarettes)
            btnDateArretCigarettes = view.findViewById(R.id.reglages_btn_date_arret_cigarettes)
            btnDateReussiteCigarettes = view.findViewById(R.id.reglages_btn_date_reussite_cigarettes)
            // (initialiser les autres dates pour joints, alcools...)

            // RAZ et Export/Import
            btnRazJour = view.findViewById(R.id.reglages_btn_raz_jour)
            btnRazHistorique = view.findViewById(R.id.reglages_btn_raz_historique)
            btnRazUsine = view.findViewById(R.id.reglages_btn_raz_usine)
            btnExporter = view.findViewById(R.id.reglages_btn_exporter)
            btnImporter = view.findViewById(R.id.reglages_btn_importer)

            // Bandeau profil
            txtProfilComplet = view.findViewById(R.id.reglages_txt_profil_complet)
            txtTotalAujourdhui = view.findViewById(R.id.reglages_txt_total_aujourdhui)

            // Configurer spinners
            setupSpinners()

            Log.d(TAG, "Vues initialisées avec succès")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur initialisation vues: ${e.message}")
            throw e
        }
    }

    private fun setupSpinners() {
        try {
            // Spinner langues
            val langues = ConfigLangue.LANGUES_DISPONIBLES.values.toList()
            val languesAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, langues)
            languesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerLangue.adapter = languesAdapter

            // Spinner devises
            val devises = SymbolesDevises.DEVISES_DISPONIBLES.keys.toList()
            val devisesAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, devises)
            devisesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerDevise.adapter = devisesAdapter

            Log.d(TAG, "Spinners configurés")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur configuration spinners: ${e.message}")
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

            // Appliquer aux checkboxes
            checkCigarettes.isChecked = categoriesActives[DatabaseHelper.TYPE_CIGARETTE] ?: true
            checkJoints.isChecked = categoriesActives[DatabaseHelper.TYPE_JOINT] ?: true
            checkAlcoolGlobal.isChecked = categoriesActives[DatabaseHelper.TYPE_ALCOOL_GLOBAL] ?: true
            checkBieres.isChecked = categoriesActives[DatabaseHelper.TYPE_BIERE] ?: false
            checkLiqueurs.isChecked = categoriesActives[DatabaseHelper.TYPE_LIQUEUR] ?: false
            checkAlcoolFort.isChecked = categoriesActives[DatabaseHelper.TYPE_ALCOOL_FORT] ?: false

            // Visibilité conteneurs
            updateContainersVisibility()

            Log.d(TAG, "Catégories actives chargées: $categoriesActives")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur chargement catégories actives: ${e.message}")
        }
    }
    private fun loadProfil() {
        try {
            // Prénom
            val prenom = dbHelper.getPreference("prenom", "")
            editPrenom.setText(prenom)

            // Langue
            val langue = configLangue.getLangue()
            val langueIndex = ConfigLangue.LANGUES_DISPONIBLES.keys.toList().indexOf(langue)
            if (langueIndex >= 0) {
                spinnerLangue.setSelection(langueIndex)
            }

            // Devise
            val devise = dbHelper.getPreference("devise", "€")
            val deviseIndex = SymbolesDevises.DEVISES_DISPONIBLES.keys.toList().indexOf(devise)
            if (deviseIndex >= 0) {
                spinnerDevise.setSelection(deviseIndex)
            }

            Log.d(TAG, "Profil chargé: $prenom - $langue - $devise")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur chargement profil: ${e.message}")
        }
    }

    private fun loadCouts() {
        try {
            // Cigarettes
            val coutsCigarettes = dbHelper.getCouts(DatabaseHelper.TYPE_CIGARETTE)
            editPrixPaquet.setText(coutsCigarettes["prix_paquet"]?.toString() ?: "")
            editNbCigarettes.setText(coutsCigarettes["nb_cigarettes"]?.toInt()?.toString() ?: "20")
            editPrixTabac.setText(coutsCigarettes["prix_tabac"]?.toString() ?: "")
            editPrixFeuilles.setText(coutsCigarettes["prix_feuilles"]?.toString() ?: "")
            editNbFeuilles.setText(coutsCigarettes["nb_feuilles"]?.toInt()?.toString() ?: "")
            editPrixFiltres.setText(coutsCigarettes["prix_filtres"]?.toString() ?: "")
            editNbFiltres.setText(coutsCigarettes["nb_filtres"]?.toInt()?.toString() ?: "")
            editPrixTubes.setText(coutsCigarettes["prix_tubes"]?.toString() ?: "")
            editNbTubes.setText(coutsCigarettes["nb_tubes"]?.toInt()?.toString() ?: "")

            // Joints
            val coutsJoints = dbHelper.getCouts(DatabaseHelper.TYPE_JOINT)
            editPrixGramme.setText(coutsJoints["prix_gramme"]?.toString() ?: "")
            editGrammeParJoint.setText(coutsJoints["gramme_par_joint"]?.toString() ?: "")

            // Alcools
            val coutsAlcoolGlobal = dbHelper.getCouts(DatabaseHelper.TYPE_ALCOOL_GLOBAL)
            editPrixVerreGlobal.setText(coutsAlcoolGlobal["prix_verre"]?.toString() ?: "")
            editUniteCLGlobal.setText(coutsAlcoolGlobal["unite_cl"]?.toInt()?.toString() ?: "")

            val coutsBieres = dbHelper.getCouts(DatabaseHelper.TYPE_BIERE)
            editPrixVerreBiere.setText(coutsBieres["prix_verre"]?.toString() ?: "")
            editUniteCLBiere.setText(coutsBieres["unite_cl"]?.toInt()?.toString() ?: "")

            val coutsLiqueurs = dbHelper.getCouts(DatabaseHelper.TYPE_LIQUEUR)
            editPrixVerreLiqueur.setText(coutsLiqueurs["prix_verre"]?.toString() ?: "")
            editUniteCLLiqueur.setText(coutsLiqueurs["unite_cl"]?.toInt()?.toString() ?: "")

            val coutsAlcoolFort = dbHelper.getCouts(DatabaseHelper.TYPE_ALCOOL_FORT)
            editPrixVerreAlcoolFort.setText(coutsAlcoolFort["prix_verre"]?.toString() ?: "")
            editUniteCLAlcoolFort.setText(coutsAlcoolFort["unite_cl"]?.toInt()?.toString() ?: "")

            Log.d(TAG, "Coûts chargés")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur chargement coûts: ${e.message}")
        }
    }

    private fun loadHabitudes() {
        try {
            editMaxCigarettes.setText(dbHelper.getMaxJournalier(DatabaseHelper.TYPE_CIGARETTE).toString())
            editMaxJoints.setText(dbHelper.getMaxJournalier(DatabaseHelper.TYPE_JOINT).toString())
            editMaxAlcoolGlobal.setText(dbHelper.getMaxJournalier(DatabaseHelper.TYPE_ALCOOL_GLOBAL).toString())
            editMaxBieres.setText(dbHelper.getMaxJournalier(DatabaseHelper.TYPE_BIERE).toString())
            editMaxLiqueurs.setText(dbHelper.getMaxJournalier(DatabaseHelper.TYPE_LIQUEUR).toString())
            editMaxAlcoolFort.setText(dbHelper.getMaxJournalier(DatabaseHelper.TYPE_ALCOOL_FORT).toString())

            Log.d(TAG, "Habitudes chargées")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur chargement habitudes: ${e.message}")
        }
    }

    private fun loadDates() {
        try {
            // Cigarettes
            val datesCigarettes = dbHelper.getDatesObjectifs(DatabaseHelper.TYPE_CIGARETTE)
            btnDateReductionCigarettes.text = datesCigarettes["reduction"] ?: "Choisir date"
            btnDateArretCigarettes.text = datesCigarettes["arret"] ?: "Choisir date"
            btnDateReussiteCigarettes.text = datesCigarettes["reussite"] ?: "Choisir date"

            // (même pattern pour joints, alcools...)

            Log.d(TAG, "Dates chargées")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur chargement dates: ${e.message}")
        }
    }

    private fun updateContainersVisibility() {
        try {
            containerCoutsCigarettes.visibility = if (categoriesActives[DatabaseHelper.TYPE_CIGARETTE] == true) View.VISIBLE else View.GONE
            containerCoutsJoints.visibility = if (categoriesActives[DatabaseHelper.TYPE_JOINT] == true) View.VISIBLE else View.GONE
            containerCoutsAlcoolGlobal.visibility = if (categoriesActives[DatabaseHelper.TYPE_ALCOOL_GLOBAL] == true) View.VISIBLE else View.GONE
            containerCoutsBieres.visibility = if (categoriesActives[DatabaseHelper.TYPE_BIERE] == true) View.VISIBLE else View.GONE
            containerCoutsLiqueurs.visibility = if (categoriesActives[DatabaseHelper.TYPE_LIQUEUR] == true) View.VISIBLE else View.GONE
            containerCoutsAlcoolFort.visibility = if (categoriesActives[DatabaseHelper.TYPE_ALCOOL_FORT] == true) View.VISIBLE else View.GONE

            Log.d(TAG, "Visibilité conteneurs mise à jour")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur mise à jour visibilité: ${e.message}")
        }
    }

    private fun setupListeners() {
        try {
            // Bouton sauvegarder profil
            btnSauvegarderProfil.setOnClickListener {
                saveProfil()
            }

            // Cases catégories
            checkCigarettes.setOnCheckedChangeListener { _, isChecked ->
                toggleCategorie(DatabaseHelper.TYPE_CIGARETTE, isChecked)
            }
            checkJoints.setOnCheckedChangeListener { _, isChecked ->
                toggleCategorie(DatabaseHelper.TYPE_JOINT, isChecked)
            }
            checkAlcoolGlobal.setOnCheckedChangeListener { _, isChecked ->
                toggleCategorie(DatabaseHelper.TYPE_ALCOOL_GLOBAL, isChecked)
            }
            checkBieres.setOnCheckedChangeListener { _, isChecked ->
                toggleCategorie(DatabaseHelper.TYPE_BIERE, isChecked)
            }
            checkLiqueurs.setOnCheckedChangeListener { _, isChecked ->
                toggleCategorie(DatabaseHelper.TYPE_LIQUEUR, isChecked)
            }
            checkAlcoolFort.setOnCheckedChangeListener { _, isChecked ->
                toggleCategorie(DatabaseHelper.TYPE_ALCOOL_FORT, isChecked)
            }

            // Radio buttons cigarettes
            radioCigarettesClassiques.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) updateCigarettesFormVisibility("classiques")
            }
            radioCigarettesRouler.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) updateCigarettesFormVisibility("rouler")
            }
            radioCigarettesTubeuse.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) updateCigarettesFormVisibility("tubeuse")
            }

            // Boutons dates (cigarettes)
            btnDateReductionCigarettes.setOnClickListener {
                showDatePicker(DatabaseHelper.TYPE_CIGARETTE, "reduction")
            }
            btnDateArretCigarettes.setOnClickListener {
                showDatePicker(DatabaseHelper.TYPE_CIGARETTE, "arret")
            }
            btnDateReussiteCigarettes.setOnClickListener {
                showDatePicker(DatabaseHelper.TYPE_CIGARETTE, "reussite")
            }

            // Boutons RAZ
            btnRazJour.setOnClickListener { confirmRaz("jour") }
            btnRazHistorique.setOnClickListener { confirmRaz("historique") }
            btnRazUsine.setOnClickListener { confirmRaz("usine") }

            // Boutons Export/Import
            btnExporter.setOnClickListener { exportData() }
            btnImporter.setOnClickListener { importData() }

            Log.d(TAG, "Listeners configurés")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur configuration listeners: ${e.message}")
        }
    }

    private fun saveProfil() {
        try {
            // Prénom
            val prenom = editPrenom.text.toString()
            dbHelper.setPreference("prenom", prenom)

            // Langue
            val langueIndex = spinnerLangue.selectedItemPosition
            val langueCode = ConfigLangue.LANGUES_DISPONIBLES.keys.toList()[langueIndex]
            configLangue.setLangue(langueCode)

            // Devise
            val deviseIndex = spinnerDevise.selectedItemPosition
            val devise = SymbolesDevises.DEVISES_DISPONIBLES.keys.toList()[deviseIndex]
            dbHelper.setPreference("devise", devise)

            // Sauvegarder coûts
            saveCouts()

            // Sauvegarder habitudes
            saveHabitudes()

            Toast.makeText(requireContext(), "Profil sauvegardé", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Profil sauvegardé: $prenom - $langueCode - $devise")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur sauvegarde profil: ${e.message}")
            Toast.makeText(requireContext(), "Erreur sauvegarde: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveCouts() {
        try {
            // Cigarettes (selon type sélectionné)
            if (radioCigarettesClassiques.isChecked) {
                dbHelper.setCout(DatabaseHelper.TYPE_CIGARETTE, "prix_paquet", editPrixPaquet.text.toString().toDoubleOrNull() ?: 0.0)
                dbHelper.setCout(DatabaseHelper.TYPE_CIGARETTE, "nb_cigarettes", editNbCigarettes.text.toString().toDoubleOrNull() ?: 20.0)
            } else if (radioCigarettesRouler.isChecked) {
                dbHelper.setCout(DatabaseHelper.TYPE_CIGARETTE, "prix_tabac", editPrixTabac.text.toString().toDoubleOrNull() ?: 0.0)
                dbHelper.setCout(DatabaseHelper.TYPE_CIGARETTE, "prix_feuilles", editPrixFeuilles.text.toString().toDoubleOrNull() ?: 0.0)
                dbHelper.setCout(DatabaseHelper.TYPE_CIGARETTE, "nb_feuilles", editNbFeuilles.text.toString().toDoubleOrNull() ?: 0.0)
                dbHelper.setCout(DatabaseHelper.TYPE_CIGARETTE, "prix_filtres", editPrixFiltres.text.toString().toDoubleOrNull() ?: 0.0)
                dbHelper.setCout(DatabaseHelper.TYPE_CIGARETTE, "nb_filtres", editNbFiltres.text.toString().toDoubleOrNull() ?: 0.0)
            } else if (radioCigarettesTubeuse.isChecked) {
                dbHelper.setCout(DatabaseHelper.TYPE_CIGARETTE, "prix_tabac", editPrixTabac.text.toString().toDoubleOrNull() ?: 0.0)
                dbHelper.setCout(DatabaseHelper.TYPE_CIGARETTE, "prix_tubes", editPrixTubes.text.toString().toDoubleOrNull() ?: 0.0)
                dbHelper.setCout(DatabaseHelper.TYPE_CIGARETTE, "nb_tubes", editNbTubes.text.toString().toDoubleOrNull() ?: 0.0)
            }

            // Joints
            dbHelper.setCout(DatabaseHelper.TYPE_JOINT, "prix_gramme", editPrixGramme.text.toString().toDoubleOrNull() ?: 0.0)
            dbHelper.setCout(DatabaseHelper.TYPE_JOINT, "gramme_par_joint", editGrammeParJoint.text.toString().toDoubleOrNull() ?: 0.0)

            // Alcools
            dbHelper.setCout(DatabaseHelper.TYPE_ALCOOL_GLOBAL, "prix_verre", editPrixVerreGlobal.text.toString().toDoubleOrNull() ?: 0.0)
            dbHelper.setCout(DatabaseHelper.TYPE_ALCOOL_GLOBAL, "unite_cl", editUniteCLGlobal.text.toString().toDoubleOrNull() ?: 0.0)
            
            dbHelper.setCout(DatabaseHelper.TYPE_BIERE, "prix_verre", editPrixVerreBiere.text.toString().toDoubleOrNull() ?: 0.0)
            dbHelper.setCout(DatabaseHelper.TYPE_BIERE, "unite_cl", editUniteCLBiere.text.toString().toDoubleOrNull() ?: 0.0)
            
            dbHelper.setCout(DatabaseHelper.TYPE_LIQUEUR, "prix_verre", editPrixVerreLiqueur.text.toString().toDoubleOrNull() ?: 0.0)
            dbHelper.setCout(DatabaseHelper.TYPE_LIQUEUR, "unite_cl", editUniteCLLiqueur.text.toString().toDoubleOrNull() ?: 0.0)
            
            dbHelper.setCout(DatabaseHelper.TYPE_ALCOOL_FORT, "prix_verre", editPrixVerreAlcoolFort.text.toString().toDoubleOrNull() ?: 0.0)
            dbHelper.setCout(DatabaseHelper.TYPE_ALCOOL_FORT, "unite_cl", editUniteCLAlcoolFort.text.toString().toDoubleOrNull() ?: 0.0)

            Log.d(TAG, "Coûts sauvegardés")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur sauvegarde coûts: ${e.message}")
        }
    }

    private fun saveHabitudes() {
        try {
            dbHelper.setMaxJournalier(DatabaseHelper.TYPE_CIGARETTE, editMaxCigarettes.text.toString().toIntOrNull() ?: 0)
            dbHelper.setMaxJournalier(DatabaseHelper.TYPE_JOINT, editMaxJoints.text.toString().toIntOrNull() ?: 0)
            dbHelper.setMaxJournalier(DatabaseHelper.TYPE_ALCOOL_GLOBAL, editMaxAlcoolGlobal.text.toString().toIntOrNull() ?: 0)
            dbHelper.setMaxJournalier(DatabaseHelper.TYPE_BIERE, editMaxBieres.text.toString().toIntOrNull() ?: 0)
            dbHelper.setMaxJournalier(DatabaseHelper.TYPE_LIQUEUR, editMaxLiqueurs.text.toString().toIntOrNull() ?: 0)
            dbHelper.setMaxJournalier(DatabaseHelper.TYPE_ALCOOL_FORT, editMaxAlcoolFort.text.toString().toIntOrNull() ?: 0)

            Log.d(TAG, "Habitudes sauvegardées")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur sauvegarde habitudes: ${e.message}")
        }
    }

    private fun toggleCategorie(type: String, isActive: Boolean) {
        try {
            categoriesActives[type] = isActive

            // Gérer exclusion alcool_global vs alcools spécifiques
            if (type == DatabaseHelper.TYPE_ALCOOL_GLOBAL && isActive) {
                categoriesActives[DatabaseHelper.TYPE_BIERE] = false
                categoriesActives[DatabaseHelper.TYPE_LIQUEUR] = false
                categoriesActives[DatabaseHelper.TYPE_ALCOOL_FORT] = false
                checkBieres.isChecked = false
                checkLiqueurs.isChecked = false
                checkAlcoolFort.isChecked = false
            } else if ((type == DatabaseHelper.TYPE_BIERE || type == DatabaseHelper.TYPE_LIQUEUR || type == DatabaseHelper.TYPE_ALCOOL_FORT) && isActive) {
                categoriesActives[DatabaseHelper.TYPE_ALCOOL_GLOBAL] = false
                checkAlcoolGlobal.isChecked = false
            }

            // Sauvegarder
            val json = JSONObject().apply {
                put("cigarette", categoriesActives[DatabaseHelper.TYPE_CIGARETTE])
                put("joint", categoriesActives[DatabaseHelper.TYPE_JOINT])
                put("alcool_global", categoriesActives[DatabaseHelper.TYPE_ALCOOL_GLOBAL])
                put("biere", categoriesActives[DatabaseHelper.TYPE_BIERE])
                put("liqueur", categoriesActives[DatabaseHelper.TYPE_LIQUEUR])
                put("alcool_fort", categoriesActives[DatabaseHelper.TYPE_ALCOOL_FORT])
            }.toString()
            dbHelper.setPreference("categories_actives", json)

            updateContainersVisibility()
            Log.d(TAG, "Catégorie $type basculée: $isActive")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur toggle catégorie $type: ${e.message}")
        }
    }

    private fun updateCigarettesFormVisibility(type: String) {
        // Masquer tous les champs d'abord
        // Puis afficher selon type (à implémenter dans le layout)
        Log.d(TAG, "Type cigarettes changé: $type")
    }
    private fun showDatePicker(type: String, typeDate: String) {
        try {
            val calendar = Calendar.getInstance()
            
            // Si date déjà renseignée, partir de cette date
            val dates = dbHelper.getDatesObjectifs(type)
            val dateStr = dates[typeDate]
            if (!dateStr.isNullOrEmpty()) {
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                sdf.parse(dateStr)?.let { calendar.time = it }
            }

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    val selectedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
                    saveDate(type, typeDate, selectedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            
            datePickerDialog.show()
            Log.d(TAG, "DatePicker affiché pour $type - $typeDate")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur affichage DatePicker: ${e.message}")
        }
    }

    private fun saveDate(type: String, typeDate: String, date: String) {
        try {
            val success = dbHelper.setDateObjectif(type, typeDate, date)
            if (success) {
                // Mettre à jour bouton
                when (type) {
                    DatabaseHelper.TYPE_CIGARETTE -> {
                        when (typeDate) {
                            "reduction" -> btnDateReductionCigarettes.text = date
                            "arret" -> btnDateArretCigarettes.text = date
                            "reussite" -> btnDateReussiteCigarettes.text = date
                        }
                    }
                    DatabaseHelper.TYPE_JOINT -> {
                        when (typeDate) {
                            "reduction" -> btnDateReductionJoints.text = date
                            "arret" -> btnDateArretJoints.text = date
                            "reussite" -> btnDateReussiteJoints.text = date
                        }
                    }
                    // (même pattern pour alcools...)
                }
                
                Toast.makeText(requireContext(), "Date enregistrée", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Date sauvegardée: $type - $typeDate = $date")
            } else {
                Toast.makeText(requireContext(), "Erreur sauvegarde date", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Erreur sauvegarde date: ${e.message}")
        }
    }

    private fun confirmRaz(type: String) {
        try {
            val (titre, message) = when (type) {
                "jour" -> Pair("RAZ du jour", "Supprimer toutes les consommations d'aujourd'hui ?")
                "historique" -> Pair("RAZ de l'historique", "Supprimer TOUT l'historique de consommation ? (Cette action est irréversible)")
                "usine" -> Pair("RAZ d'usine", "TOUT réinitialiser (profil, consommations, paramètres) ? (Cette action est irréversible)")
                else -> return
            }

            AlertDialog.Builder(requireContext())
                .setTitle(titre)
                .setMessage(message)
                .setPositiveButton("Confirmer") { _, _ ->
                    executeRaz(type)
                }
                .setNegativeButton("Annuler", null)
                .show()

            Log.d(TAG, "Confirmation RAZ affichée: $type")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur confirmation RAZ: ${e.message}")
        }
    }

    private fun executeRaz(type: String) {
        try {
            val success = when (type) {
                "jour" -> dbHelper.razJour()
                "historique" -> dbHelper.razHistorique()
                "usine" -> dbHelper.razUsine()
                else -> false
            }

            if (success) {
                Toast.makeText(requireContext(), "RAZ effectuée", Toast.LENGTH_SHORT).show()
                
                // Recharger données
                if (type == "usine") {
                    loadCategoriesActives()
                    loadProfil()
                    loadCouts()
                    loadHabitudes()
                    loadDates()
                }
                
                updateProfilStatus()
                Log.d(TAG, "RAZ $type effectuée avec succès")
            } else {
                Toast.makeText(requireContext(), "Erreur RAZ", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Erreur exécution RAZ $type: ${e.message}")
            Toast.makeText(requireContext(), "Erreur: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun exportData() {
        try {
            // Vérifier limite version gratuite
            if (!exportLimiter.canExport()) {
                val remaining = exportLimiter.getRemainingExports()
                Toast.makeText(
                    requireContext(),
                    "Limite atteinte: $remaining export(s) restant(s) aujourd'hui (version gratuite)",
                    Toast.LENGTH_LONG
                ).show()
                Log.w(TAG, "Export refusé: limite atteinte")
                return
            }

            // Exporter données
            val json = dbHelper.exportJSON()
            if (json.isEmpty()) {
                Toast.makeText(requireContext(), "Erreur export: données vides", Toast.LENGTH_SHORT).show()
                return
            }

            // Écrire dans fichier
            val file = File(requireContext().getExternalFilesDir(null), EXPORT_FILENAME)
            file.writeText(json)

            // Enregistrer export
            exportLimiter.recordExport()

            // Partager fichier
            val uri = FileProvider.getUriForFile(
                requireContext(),
                "${requireContext().packageName}.fileprovider",
                file
            )

            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "application/json"
                putExtra(Intent.EXTRA_STREAM, uri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            startActivity(Intent.createChooser(shareIntent, "Exporter sauvegarde"))
            
            Toast.makeText(requireContext(), "Export réussi", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Export réussi: ${file.absolutePath}")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur export: ${e.message}")
            Toast.makeText(requireContext(), "Erreur export: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun importData() {
        try {
            // Vérifier limite version gratuite
            if (!exportLimiter.canImport()) {
                val remaining = exportLimiter.getRemainingImports()
                Toast.makeText(
                    requireContext(),
                    "Limite atteinte: $remaining import(s) restant(s) aujourd'hui (version gratuite)",
                    Toast.LENGTH_LONG
                ).show()
                Log.w(TAG, "Import refusé: limite atteinte")
                return
            }

            // Sélectionner fichier (simplifié ici, devrait ouvrir file picker)
            val file = File(requireContext().getExternalFilesDir(null), EXPORT_FILENAME)
            if (!file.exists()) {
                Toast.makeText(requireContext(), "Aucun fichier de sauvegarde trouvé", Toast.LENGTH_SHORT).show()
                return
            }

            // Confirmer import
            AlertDialog.Builder(requireContext())
                .setTitle("Importer sauvegarde")
                .setMessage("Toutes les données actuelles seront remplacées. Continuer ?")
                .setPositiveButton("Confirmer") { _, _ ->
                    executeImport(file)
                }
                .setNegativeButton("Annuler", null)
                .show()

            Log.d(TAG, "Confirmation import affichée")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur import: ${e.message}")
            Toast.makeText(requireContext(), "Erreur import: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun executeImport(file: File) {
        try {
            val json = file.readText()
            val success = dbHelper.importJSON(json)

            if (success) {
                // Enregistrer import
                exportLimiter.recordImport()

                // Recharger toutes les données
                loadCategoriesActives()
                loadProfil()
                loadCouts()
                loadHabitudes()
                loadDates()
                updateProfilStatus()

                Toast.makeText(requireContext(), "Import réussi", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Import réussi depuis: ${file.absolutePath}")
            } else {
                Toast.makeText(requireContext(), "Erreur import: données invalides", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Erreur exécution import: ${e.message}")
            Toast.makeText(requireContext(), "Erreur: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun updateProfilStatus() {
        try {
            // Vérifier si profil complet
            var isComplete = true

            // Vérifier coûts
            var hasCouts = false
            categoriesActives.forEach { (type, active) ->
                if (active) {
                    val couts = dbHelper.getCouts(type)
                    if (couts.values.any { it > 0 }) {
                        hasCouts = true
                    }
                }
            }
            if (!hasCouts) isComplete = false

            // Vérifier habitudes
            var hasHabitudes = false
            categoriesActives.forEach { (type, active) ->
                if (active) {
                    val max = dbHelper.getMaxJournalier(type)
                    if (max > 0) {
                        hasHabitudes = true
                    }
                }
            }
            if (!hasHabitudes) isComplete = false

            // Vérifier dates
            var hasDates = false
            categoriesActives.forEach { (type, active) ->
                if (active) {
                    val dates = dbHelper.getDatesObjectifs(type)
                    if (dates.values.any { !it.isNullOrEmpty() }) {
                        hasDates = true
                    }
                }
            }
            if (!hasDates) isComplete = false

            // Mise à jour texte
            txtProfilComplet.text = if (isComplete) "Profil: Complet ✓" else "Profil: Incomplet"

            // Total aujourd'hui
            val consosJour = dbHelper.getConsommationsJour()
            val totalJour = consosJour.values.sum()
            txtTotalAujourdhui.text = totalJour.toString()

            Log.d(TAG, "Profil: ${if (isComplete) "Complet" else "Incomplet"} - Total: $totalJour")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur mise à jour profil: ${e.message}")
        }
    }
    override fun onResume() {
        super.onResume()
        try {
            // Recharger données (synchro si modif depuis autre onglet)
            loadCategoriesActives()
            loadProfil()
            loadCouts()
            loadHabitudes()
            loadDates()
            updateProfilStatus()
            Log.d(TAG, "Fragment resumed - données rechargées")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur onResume: ${e.message}")
        }
    }

    override fun onPause() {
        super.onPause()
        try {
            Log.d(TAG, "Fragment paused")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur onPause: ${e.message}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        try {
            Log.d(TAG, "Fragment détruit")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur onDestroyView: ${e.message}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            Log.d(TAG, "Fragment destroyed")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur onDestroy: ${e.message}")
        }
    }

    /**
     * Fonction appelée depuis MainActivity ou autres fragments
     * pour rafraîchir les données (synchro live)
     */
    fun refreshData() {
        try {
            loadCategoriesActives()
            loadProfil()
            loadCouts()
            loadHabitudes()
            loadDates()
            updateProfilStatus()
            Log.d(TAG, "Données rafraîchies manuellement")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur refresh data: ${e.message}")
        }
    }

    /**
     * Valide les champs avant sauvegarde
     */
    private fun validateFields(): Boolean {
        return try {
            // Vérifier prénom (optionnel)
            // Vérifier coûts (au moins un champ rempli si catégorie active)
            var hasValidCout = false
            
            if (categoriesActives[DatabaseHelper.TYPE_CIGARETTE] == true) {
                if (editPrixPaquet.text.toString().isNotEmpty() || 
                    editPrixTabac.text.toString().isNotEmpty()) {
                    hasValidCout = true
                }
            }
            
            if (categoriesActives[DatabaseHelper.TYPE_JOINT] == true) {
                if (editPrixGramme.text.toString().isNotEmpty()) {
                    hasValidCout = true
                }
            }
            
            if (categoriesActives[DatabaseHelper.TYPE_ALCOOL_GLOBAL] == true) {
                if (editPrixVerreGlobal.text.toString().isNotEmpty()) {
                    hasValidCout = true
                }
            }

            if (!hasValidCout) {
                Toast.makeText(
                    requireContext(),
                    "Veuillez renseigner au moins un coût",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }

            true
        } catch (e: Exception) {
            Log.e(TAG, "Erreur validation: ${e.message}")
            false
        }
    }

    /**
     * Réinitialise un formulaire spécifique
     */
    private fun resetForm(type: String) {
        try {
            when (type) {
                DatabaseHelper.TYPE_CIGARETTE -> {
                    editPrixPaquet.setText("")
                    editNbCigarettes.setText("20")
                    editPrixTabac.setText("")
                    editPrixFeuilles.setText("")
                    editNbFeuilles.setText("")
                    editPrixFiltres.setText("")
                    editNbFiltres.setText("")
                    editPrixTubes.setText("")
                    editNbTubes.setText("")
                }
                DatabaseHelper.TYPE_JOINT -> {
                    editPrixGramme.setText("")
                    editGrammeParJoint.setText("")
                }
                DatabaseHelper.TYPE_ALCOOL_GLOBAL -> {
                    editPrixVerreGlobal.setText("")
                    editUniteCLGlobal.setText("")
                }
                DatabaseHelper.TYPE_BIERE -> {
                    editPrixVerreBiere.setText("")
                    editUniteCLBiere.setText("")
                }
                DatabaseHelper.TYPE_LIQUEUR -> {
                    editPrixVerreLiqueur.setText("")
                    editUniteCLLiqueur.setText("")
                }
                DatabaseHelper.TYPE_ALCOOL_FORT -> {
                    editPrixVerreAlcoolFort.setText("")
                    editUniteCLAlcoolFort.setText("")
                }
            }
            Log.d(TAG, "Formulaire $type réinitialisé")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur reset formulaire $type: ${e.message}")
        }
    }

    /**
     * Affiche un résumé des économies potentielles
     */
    private fun showEconomiesSummary() {
        try {
            var economiesJour = 0.0
            var economiesSemaine = 0.0
            var economiesMois = 0.0
            var economiesAnnee = 0.0

            categoriesActives.forEach { (type, active) ->
                if (active) {
                    val maxHabitude = dbHelper.getMaxJournalier(type)
                    if (maxHabitude > 0) {
                        val couts = dbHelper.getCouts(type)
                        val prixUnitaire = when (type) {
                            DatabaseHelper.TYPE_CIGARETTE -> {
                                val prixPaquet = couts["prix_paquet"] ?: 0.0
                                val nbCigarettes = couts["nb_cigarettes"] ?: 20.0
                                if (prixPaquet > 0 && nbCigarettes > 0) {
                                    prixPaquet / nbCigarettes
                                } else 0.0
                            }
                            DatabaseHelper.TYPE_JOINT -> {
                                val prixGramme = couts["prix_gramme"] ?: 0.0
                                val grammeParJoint = couts["gramme_par_joint"] ?: 0.0
                                prixGramme * grammeParJoint
                            }
                            else -> couts["prix_verre"] ?: 0.0
                        }
                        
                        val coutJour = prixUnitaire * maxHabitude
                        economiesJour += coutJour
                        economiesSemaine += coutJour * 7
                        economiesMois += coutJour * 30
                        economiesAnnee += coutJour * 365
                    }
                }
            }

            val devise = dbHelper.getPreference("devise", "€")
            val message = """
                Si vous arrêtiez complètement:
                
                Jour: ${String.format("%.2f", economiesJour)}$devise
                Semaine: ${String.format("%.2f", economiesSemaine)}$devise
                Mois: ${String.format("%.2f", economiesMois)}$devise
                Année: ${String.format("%.2f", economiesAnnee)}$devise
            """.trimIndent()

            AlertDialog.Builder(requireContext())
                .setTitle("Économies potentielles")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show()

            Log.d(TAG, "Résumé économies affiché")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur affichage économies: ${e.message}")
        }
    }
}
