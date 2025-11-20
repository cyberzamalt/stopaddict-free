package com.stopaddict

import android.app.AlertDialog
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

            // Configuration listeners
            setupListeners()

            // Mise à jour UI
            updateProfilStatus()

            Log.d(TAG, "ReglagesFragment initialisé avec succès")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur initialisation ReglagesFragment: ${e.message}", e)
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
            Log.e(TAG, "Erreur initialisation vues: ${e.message}", e)
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
            Log.e(TAG, "Erreur configuration spinners: ${e.message}", e)
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

            updateCoutsContainersVisibility()

            Log.d(TAG, "Catégories actives chargées: $categoriesActives")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur chargement catégories actives: ${e.message}", e)
        }
    }

    private fun loadProfil() {
        try {
            // Charger prénom
            val prenom = dbHelper.getPreference("prenom", "")
            editPrenom.setText(prenom)

            // Charger langue
            val langue = configLangue.getLangue()
            val langueIndex = ConfigLangue.LANGUES_DISPONIBLES.keys.indexOf(langue)
            if (langueIndex >= 0) {
                spinnerLangue.setSelection(langueIndex)
            }

            // Charger devise
            val devise = dbHelper.getPreference("devise", "EUR")
            val deviseIndex = SymbolesDevises.DEVISES_DISPONIBLES.keys.indexOf(devise)
            if (deviseIndex >= 0) {
                spinnerDevise.setSelection(deviseIndex)
            }

            Log.d(TAG, "Profil chargé: prenom=$prenom, langue=$langue, devise=$devise")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur chargement profil: ${e.message}", e)
        }
    }

    // FIN PARTIE 1/3
    // COPIER LA PARTIE 2/3 JUSTE APRÈS CETTE LIGNE
    // DÉBUT PARTIE 2/3
    // COLLER JUSTE APRÈS LA PARTIE 1/3

    private fun loadCouts() {
        try {
            // Charger coûts cigarettes
            val coutsCig = dbHelper.getCouts(DatabaseHelper.TYPE_CIGARETTE)
            if (coutsCig["prix_paquet"] ?: 0.0 > 0) {
                radioCigarettesClassiques.isChecked = true
                editPrixPaquet.setText(coutsCig["prix_paquet"].toString())
                editNbCigarettes.setText(coutsCig["nb_cigarettes"]?.toInt().toString())
            } else if (coutsCig["prix_tabac"] ?: 0.0 > 0) {
                radioCigarettesRouler.isChecked = true
                editPrixTabac.setText(coutsCig["prix_tabac"].toString())
                editPrixFeuilles.setText(coutsCig["prix_feuilles"].toString())
                editNbFeuilles.setText(coutsCig["nb_feuilles"]?.toInt().toString())
            } else if (coutsCig["prix_tubes"] ?: 0.0 > 0) {
                radioCigarettesTubeuse.isChecked = true
                editPrixTabac.setText(coutsCig["prix_tabac"].toString())
                editPrixTubes.setText(coutsCig["prix_tubes"].toString())
                editNbTubes.setText(coutsCig["nb_tubes"]?.toInt().toString())
                editPrixFiltres.setText(coutsCig["prix_filtres"].toString())
                editNbFiltres.setText(coutsCig["nb_filtres"]?.toInt().toString())
            }

            // Charger coûts joints
            val coutsJoints = dbHelper.getCouts(DatabaseHelper.TYPE_JOINT)
            editPrixGramme.setText(coutsJoints["prix_gramme"].toString())
            editGrammeParJoint.setText(coutsJoints["gramme_par_joint"].toString())

            // Charger coûts alcool global
            val coutsAlcoolGlobal = dbHelper.getCouts(DatabaseHelper.TYPE_ALCOOL_GLOBAL)
            editPrixVerreGlobal.setText(coutsAlcoolGlobal["prix_verre"].toString())
            editUniteCLGlobal.setText(coutsAlcoolGlobal["unite_cl"].toString())

            // Charger coûts bières
            val coutsBieres = dbHelper.getCouts(DatabaseHelper.TYPE_BIERE)
            editPrixVerreBiere.setText(coutsBieres["prix_verre"].toString())
            editUniteCLBiere.setText(coutsBieres["unite_cl"].toString())

            // Charger coûts liqueurs
            val coutsLiqueurs = dbHelper.getCouts(DatabaseHelper.TYPE_LIQUEUR)
            editPrixVerreLiqueur.setText(coutsLiqueurs["prix_verre"].toString())
            editUniteCLLiqueur.setText(coutsLiqueurs["unite_cl"].toString())

            // Charger coûts alcool fort
            val coutsAlcoolFort = dbHelper.getCouts(DatabaseHelper.TYPE_ALCOOL_FORT)
            editPrixVerreAlcoolFort.setText(coutsAlcoolFort["prix_verre"].toString())
            editUniteCLAlcoolFort.setText(coutsAlcoolFort["unite_cl"].toString())

            Log.d(TAG, "Coûts chargés")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur chargement coûts: ${e.message}", e)
        }
    }

    private fun setupListeners() {
        try {
            // Profil
            btnSauvegarderProfil.setOnClickListener {
                saveProfil()
            }

            // Cases catégories
            checkCigarettes.setOnCheckedChangeListener { _, isChecked ->
                categoriesActives[DatabaseHelper.TYPE_CIGARETTE] = isChecked
                saveCategoriesActives()
                updateCoutsContainersVisibility()
            }

            checkJoints.setOnCheckedChangeListener { _, isChecked ->
                categoriesActives[DatabaseHelper.TYPE_JOINT] = isChecked
                saveCategoriesActives()
                updateCoutsContainersVisibility()
            }

            checkAlcoolGlobal.setOnCheckedChangeListener { _, isChecked ->
                categoriesActives[DatabaseHelper.TYPE_ALCOOL_GLOBAL] = isChecked
                saveCategoriesActives()
                updateCoutsContainersVisibility()
                
                // Si alcool global activé, désactiver sous-catégories
                if (isChecked) {
                    checkBieres.isChecked = false
                    checkLiqueurs.isChecked = false
                    checkAlcoolFort.isChecked = false
                }
            }

            checkBieres.setOnCheckedChangeListener { _, isChecked ->
                categoriesActives[DatabaseHelper.TYPE_BIERE] = isChecked
                saveCategoriesActives()
                updateCoutsContainersVisibility()
                
                // Si bière activée, désactiver alcool global
                if (isChecked && checkAlcoolGlobal.isChecked) {
                    checkAlcoolGlobal.isChecked = false
                }
            }

            checkLiqueurs.setOnCheckedChangeListener { _, isChecked ->
                categoriesActives[DatabaseHelper.TYPE_LIQUEUR] = isChecked
                saveCategoriesActives()
                updateCoutsContainersVisibility()
                
                // Si liqueur activée, désactiver alcool global
                if (isChecked && checkAlcoolGlobal.isChecked) {
                    checkAlcoolGlobal.isChecked = false
                }
            }

            checkAlcoolFort.setOnCheckedChangeListener { _, isChecked ->
                categoriesActives[DatabaseHelper.TYPE_ALCOOL_FORT] = isChecked
                saveCategoriesActives()
                updateCoutsContainersVisibility()
                
                // Si alcool fort activé, désactiver alcool global
                if (isChecked && checkAlcoolGlobal.isChecked) {
                    checkAlcoolGlobal.isChecked = false
                }
            }

            // Radio cigarettes
            radioCigarettesClassiques.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) updateCigarettesFormVisibility()
            }

            radioCigarettesRouler.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) updateCigarettesFormVisibility()
            }

            radioCigarettesTubeuse.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) updateCigarettesFormVisibility()
            }

            // Boutons RAZ
            btnRazJour.setOnClickListener {
                showRazDialog("jour")
            }

            btnRazHistorique.setOnClickListener {
                showRazDialog("historique")
            }

            btnRazUsine.setOnClickListener {
                showRazDialog("usine")
            }

            // Boutons Export/Import
            btnExporter.setOnClickListener {
                exportData()
            }

            btnImporter.setOnClickListener {
                Toast.makeText(requireContext(), "Import à implémenter", Toast.LENGTH_SHORT).show()
            }

            Log.d(TAG, "Listeners configurés")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur configuration listeners: ${e.message}", e)
        }
    }

    private fun updateCoutsContainersVisibility() {
        try {
            containerCoutsCigarettes.visibility = if (categoriesActives[DatabaseHelper.TYPE_CIGARETTE] == true) View.VISIBLE else View.GONE
            containerCoutsJoints.visibility = if (categoriesActives[DatabaseHelper.TYPE_JOINT] == true) View.VISIBLE else View.GONE
            containerCoutsAlcoolGlobal.visibility = if (categoriesActives[DatabaseHelper.TYPE_ALCOOL_GLOBAL] == true) View.VISIBLE else View.GONE
            containerCoutsBieres.visibility = if (categoriesActives[DatabaseHelper.TYPE_BIERE] == true) View.VISIBLE else View.GONE
            containerCoutsLiqueurs.visibility = if (categoriesActives[DatabaseHelper.TYPE_LIQUEUR] == true) View.VISIBLE else View.GONE
            containerCoutsAlcoolFort.visibility = if (categoriesActives[DatabaseHelper.TYPE_ALCOOL_FORT] == true) View.VISIBLE else View.GONE
            
            updateCigarettesFormVisibility()
            
            Log.d(TAG, "Visibilité conteneurs coûts mise à jour")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur mise à jour visibilité: ${e.message}", e)
        }
    }

    private fun updateCigarettesFormVisibility() {
        try {
            when {
                radioCigarettesClassiques.isChecked -> {
                    editPrixPaquet.visibility = View.VISIBLE
                    editNbCigarettes.visibility = View.VISIBLE
                    editPrixTabac.visibility = View.GONE
                    editPrixFeuilles.visibility = View.GONE
                    editNbFeuilles.visibility = View.GONE
                    editPrixFiltres.visibility = View.GONE
                    editNbFiltres.visibility = View.GONE
                    editPrixTubes.visibility = View.GONE
                    editNbTubes.visibility = View.GONE
                }
                radioCigarettesRouler.isChecked -> {
                    editPrixPaquet.visibility = View.GONE
                    editNbCigarettes.visibility = View.GONE
                    editPrixTabac.visibility = View.VISIBLE
                    editPrixFeuilles.visibility = View.VISIBLE
                    editNbFeuilles.visibility = View.VISIBLE
                    editPrixFiltres.visibility = View.GONE
                    editNbFiltres.visibility = View.GONE
                    editPrixTubes.visibility = View.GONE
                    editNbTubes.visibility = View.GONE
                }
                radioCigarettesTubeuse.isChecked -> {
                    editPrixPaquet.visibility = View.GONE
                    editNbCigarettes.visibility = View.GONE
                    editPrixTabac.visibility = View.VISIBLE
                    editPrixFeuilles.visibility = View.GONE
                    editNbFeuilles.visibility = View.GONE
                    editPrixFiltres.visibility = View.VISIBLE
                    editNbFiltres.visibility = View.VISIBLE
                    editPrixTubes.visibility = View.VISIBLE
                    editNbTubes.visibility = View.VISIBLE
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Erreur mise à jour formulaire cigarettes: ${e.message}", e)
        }
    }

    private fun saveProfil() {
        try {
            // Sauvegarder prénom
            val prenom = editPrenom.text.toString().trim()
            dbHelper.setPreference("prenom", prenom)

            // Sauvegarder langue
            val langueIndex = spinnerLangue.selectedItemPosition
            val langueCode = ConfigLangue.LANGUES_DISPONIBLES.keys.toList()[langueIndex]
            configLangue.setLangue(langueCode)

            // Sauvegarder devise
            val deviseIndex = spinnerDevise.selectedItemPosition
            val devise = SymbolesDevises.DEVISES_DISPONIBLES.keys.toList()[deviseIndex]
            dbHelper.setPreference("devise", devise)

            Toast.makeText(requireContext(), "Profil sauvegardé", Toast.LENGTH_SHORT).show()
            
            updateProfilStatus()

            // Recharger l'activité pour appliquer la langue
            activity?.recreate()

            Log.d(TAG, "Profil sauvegardé: prenom=$prenom, langue=$langueCode, devise=$devise")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur sauvegarde profil: ${e.message}", e)
            Toast.makeText(requireContext(), "Erreur: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveCategoriesActives() {
        try {
            val json = JSONObject().apply {
                put("cigarette", categoriesActives[DatabaseHelper.TYPE_CIGARETTE] ?: true)
                put("joint", categoriesActives[DatabaseHelper.TYPE_JOINT] ?: true)
                put("alcool_global", categoriesActives[DatabaseHelper.TYPE_ALCOOL_GLOBAL] ?: true)
                put("biere", categoriesActives[DatabaseHelper.TYPE_BIERE] ?: false)
                put("liqueur", categoriesActives[DatabaseHelper.TYPE_LIQUEUR] ?: false)
                put("alcool_fort", categoriesActives[DatabaseHelper.TYPE_ALCOOL_FORT] ?: false)
            }
            dbHelper.setPreference("categories_actives", json.toString())
            
            Log.d(TAG, "Catégories actives sauvegardées: $categoriesActives")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur sauvegarde catégories actives: ${e.message}", e)
        }
    }

    private fun saveCouts() {
        try {
            // Sauvegarder coûts cigarettes
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
                    dbHelper.setCouts(DatabaseHelper.TYPE_CIGARETTE, 0.0, 0.0, prixTabac, prixFeuilles, nbFeuilles, 0.0, 0.0, 0.0, 0.0, 0.0)
                }
                radioCigarettesTubeuse.isChecked -> {
                    val prixTabac = editPrixTabac.text.toString().toDoubleOrNull() ?: 0.0
                    val prixTubes = editPrixTubes.text.toString().toDoubleOrNull() ?: 0.0
                    val nbTubes = editNbTubes.text.toString().toDoubleOrNull() ?: 100.0
                    val prixFiltres = editPrixFiltres.text.toString().toDoubleOrNull() ?: 0.0
                    val nbFiltres = editNbFiltres.text.toString().toDoubleOrNull() ?: 100.0
                    dbHelper.setCouts(DatabaseHelper.TYPE_CIGARETTE, 0.0, 0.0, prixTabac, 0.0, 0.0, prixFiltres, nbFiltres, prixTubes, nbTubes, 0.0)
                }
            }

            // Sauvegarder coûts joints
            val prixGramme = editPrixGramme.text.toString().toDoubleOrNull() ?: 0.0
            val grammeParJoint = editGrammeParJoint.text.toString().toDoubleOrNull() ?: 0.5
            dbHelper.setCouts(DatabaseHelper.TYPE_JOINT, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, prixGramme)
            dbHelper.setPreference("gramme_par_joint", grammeParJoint.toString())

            // Sauvegarder coûts alcool global
            val prixVerreGlobal = editPrixVerreGlobal.text.toString().toDoubleOrNull() ?: 0.0
            val uniteCLGlobal = editUniteCLGlobal.text.toString().toDoubleOrNull() ?: 25.0
            dbHelper.setCouts(DatabaseHelper.TYPE_ALCOOL_GLOBAL, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, prixVerreGlobal)
            dbHelper.setPreference("unite_cl_alcool_global", uniteCLGlobal.toString())

            // Sauvegarder coûts bières
            val prixVerreBiere = editPrixVerreBiere.text.toString().toDoubleOrNull() ?: 0.0
            val uniteCLBiere = editUniteCLBiere.text.toString().toDoubleOrNull() ?: 25.0
            dbHelper.setCouts(DatabaseHelper.TYPE_BIERE, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, prixVerreBiere)
            dbHelper.setPreference("unite_cl_biere", uniteCLBiere.toString())

            // Sauvegarder coûts liqueurs
            val prixVerreLiqueur = editPrixVerreLiqueur.text.toString().toDoubleOrNull() ?: 0.0
            val uniteCLLiqueur = editUniteCLLiqueur.text.toString().toDoubleOrNull() ?: 4.0
            dbHelper.setCouts(DatabaseHelper.TYPE_LIQUEUR, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, prixVerreLiqueur)
            dbHelper.setPreference("unite_cl_liqueur", uniteCLLiqueur.toString())

            // Sauvegarder coûts alcool fort
            val prixVerreAlcoolFort = editPrixVerreAlcoolFort.text.toString().toDoubleOrNull() ?: 0.0
            val uniteCLAlcoolFort = editUniteCLAlcoolFort.text.toString().toDoubleOrNull() ?: 4.0
            dbHelper.setCouts(DatabaseHelper.TYPE_ALCOOL_FORT, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, prixVerreAlcoolFort)
            dbHelper.setPreference("unite_cl_alcool_fort", uniteCLAlcoolFort.toString())

            Toast.makeText(requireContext(), "Coûts sauvegardés", Toast.LENGTH_SHORT).show()
            
            updateProfilStatus()

            Log.d(TAG, "Coûts sauvegardés")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur sauvegarde coûts: ${e.message}", e)
            Toast.makeText(requireContext(), "Erreur: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    // FIN PARTIE 2/3
    // COPIER LA PARTIE 3/3 JUSTE APRÈS CETTE LIGNE
    // DÉBUT PARTIE 3/3 FINALE
    // COLLER JUSTE APRÈS LA PARTIE 2/3

    private fun showRazDialog(type: String) {
        try {
            val message = when (type) {
                "jour" -> "Supprimer toutes les consommations d'aujourd'hui ?"
                "historique" -> "Supprimer tout l'historique de consommations ?\n\n⚠️ Cette action est irréversible !"
                "usine" -> "Réinitialiser l'application aux paramètres d'usine ?\n\n⚠️ Toutes vos données seront perdues !"
                else -> "Confirmer la suppression ?"
            }

            AlertDialog.Builder(requireContext())
                .setTitle("⚠️ Confirmation")
                .setMessage(message)
                .setPositiveButton("Confirmer") { _, _ ->
                    executeRaz(type)
                }
                .setNegativeButton("Annuler", null)
                .show()

        } catch (e: Exception) {
            Log.e(TAG, "Erreur affichage dialog RAZ: ${e.message}", e)
        }
    }

    private fun executeRaz(type: String) {
        try {
            when (type) {
                "jour" -> {
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val today = dateFormat.format(Date())
                    
                    categoriesActives.forEach { (typeCategorie, _) ->
                        dbHelper.supprimerConsommationsJour(typeCategorie, today)
                    }
                    
                    Toast.makeText(requireContext(), "Consommations du jour supprimées", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "RAZ jour effectué")
                }
                
                "historique" -> {
                    categoriesActives.forEach { (typeCategorie, _) ->
                        dbHelper.supprimerToutesConsommations(typeCategorie)
                    }
                    
                    Toast.makeText(requireContext(), "Historique supprimé", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "RAZ historique effectué")
                }
                
                "usine" -> {
                    // Supprimer toutes les données
                    categoriesActives.forEach { (typeCategorie, _) ->
                        dbHelper.supprimerToutesConsommations(typeCategorie)
                        dbHelper.setCouts(typeCategorie, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
                        dbHelper.setMaxJournalier(typeCategorie, 0)
                        dbHelper.setDatesObjectifs(typeCategorie, "", "", "")
                    }
                    
                    // Réinitialiser préférences
                    dbHelper.setPreference("prenom", "")
                    dbHelper.setPreference("devise", "EUR")
                    configLangue.setLangue("FR")
                    
                    // Réinitialiser catégories actives
                    categoriesActives = mutableMapOf(
                        DatabaseHelper.TYPE_CIGARETTE to true,
                        DatabaseHelper.TYPE_JOINT to true,
                        DatabaseHelper.TYPE_ALCOOL_GLOBAL to true,
                        DatabaseHelper.TYPE_BIERE to false,
                        DatabaseHelper.TYPE_LIQUEUR to false,
                        DatabaseHelper.TYPE_ALCOOL_FORT to false
                    )
                    saveCategoriesActives()
                    
                    Toast.makeText(requireContext(), "Réinitialisation complète effectuée", Toast.LENGTH_LONG).show()
                    
                    // Recharger l'activité
                    activity?.recreate()
                    
                    Log.d(TAG, "RAZ usine effectué")
                }
            }
            
            updateProfilStatus()
            
            // Notifier MainActivity pour refresh autres fragments
            activity?.let {
                if (it is MainActivity) {
                    it.refreshData()
                }
            }
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur exécution RAZ $type: ${e.message}", e)
            Toast.makeText(requireContext(), "Erreur: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun exportData() {
        try {
            // Vérifier limite export (version gratuite)
            val isVersionGratuite = true // À adapter selon version
            if (isVersionGratuite && !exportLimiter.peutExporter()) {
                Toast.makeText(
                    requireContext(),
                    "Limite d'export atteinte (1/jour en version gratuite)",
                    Toast.LENGTH_LONG
                ).show()
                return
            }

            // Créer JSON export
            val exportJson = JSONObject().apply {
                put("version", "1.0")
                put("date_export", SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date()))
                
                // Profil
                put("prenom", dbHelper.getPreference("prenom", ""))
                put("langue", configLangue.getLangue())
                put("devise", dbHelper.getPreference("devise", "EUR"))
                
                // Catégories actives
                put("categories_actives", JSONObject(dbHelper.getPreference("categories_actives", "{}")))
                
                // Consommations par catégorie
                val consommations = JSONObject()
                categoriesActives.forEach { (type, _) ->
                    val consommationsType = dbHelper.getToutesConsommations(type)
                    consommations.put(type, consommationsType)
                }
                put("consommations", consommations)
                
                // Coûts par catégorie
                val couts = JSONObject()
                categoriesActives.forEach { (type, _) ->
                    val coutsType = dbHelper.getCouts(type)
                    couts.put(type, JSONObject(coutsType as Map<*, *>))
                }
                put("couts", couts)
                
                // Habitudes par catégorie
                val habitudes = JSONObject()
                categoriesActives.forEach { (type, _) ->
                    habitudes.put(type, dbHelper.getMaxJournalier(type))
                }
                put("habitudes", habitudes)
                
                // Dates objectifs par catégorie
                val dates = JSONObject()
                categoriesActives.forEach { (type, _) ->
                    val datesType = dbHelper.getDatesObjectifs(type)
                    dates.put(type, JSONObject(datesType))
                }
                put("dates_objectifs", dates)
            }

            // Écrire fichier
            val file = File(requireContext().getExternalFilesDir(null), EXPORT_FILENAME)
            file.writeText(exportJson.toString(2))

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

            startActivity(Intent.createChooser(shareIntent, "Exporter les données"))

            // Enregistrer export
            if (isVersionGratuite) {
                exportLimiter.enregistrerExport()
            }

            Toast.makeText(requireContext(), "Export réussi", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Export effectué: ${file.absolutePath}")

        } catch (e: Exception) {
            Log.e(TAG, "Erreur export: ${e.message}", e)
            Toast.makeText(requireContext(), "Erreur export: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun updateProfilStatus() {
        try {
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
                "Profil: Complet ✓"
            } else {
                "Profil: Incomplet"
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

            txtTotalAujourdhui.text = "Total aujourd'hui: $total"

            Log.d(TAG, "Statut profil mis à jour: complet=$isComplet, total=$total")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur update profil status: ${e.message}", e)
        }
    }

    fun refreshData() {
        try {
            loadCategoriesActives()
            loadProfil()
            loadCouts()
            updateProfilStatus()
            Log.d(TAG, "Données rafraîchies")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur refresh data: ${e.message}", e)
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            loadCategoriesActives()
            loadProfil()
            loadCouts()
            updateProfilStatus()
        } catch (e: Exception) {
            Log.e(TAG, "Erreur onResume: ${e.message}", e)
        }
    }

    override fun onPause() {
        super.onPause()
        try {
            // Sauvegarder coûts automatiquement
            saveCouts()
            Log.d(TAG, "Fragment mis en pause - coûts sauvegardés")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur onPause: ${e.message}", e)
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
// FIN DU FICHIER - ReglagesFragment.kt est maintenant complet
