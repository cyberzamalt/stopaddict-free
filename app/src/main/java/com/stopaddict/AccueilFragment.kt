package com.stopaddict

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class AccueilFragment : Fragment() {

    companion object {
        private const val TAG = "AccueilFragment"
        private const val CONSEIL_UPDATE_INTERVAL = 15000L // 15 secondes en millisecondes
    }

    // Database
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var configLangue: ConfigLangue
    private lateinit var trad: Map<String, String>
    private val logger = AppLogger("AccueilFragment")
    
    // UI Elements - Compteurs
    private lateinit var txtTotalJour: TextView
    private lateinit var txtCigarettes: TextView
    private lateinit var txtJoints: TextView
    private lateinit var txtAlcoolGlobal: TextView
    private lateinit var txtBieres: TextView
    private lateinit var txtLiqueurs: TextView
    private lateinit var txtAlcoolFort: TextView

    // UI Elements - Boutons +/-
    private lateinit var btnPlusCigarettes: Button
    private lateinit var btnMoinsCigarettes: Button
    private lateinit var btnPlusJoints: Button
    private lateinit var btnMoinsJoints: Button
    private lateinit var btnPlusAlcoolGlobal: Button
    private lateinit var btnMoinsAlcoolGlobal: Button
    private lateinit var btnPlusBieres: Button
    private lateinit var btnMoinsBieres: Button
    private lateinit var btnPlusLiqueurs: Button
    private lateinit var btnMoinsLiqueurs: Button
    private lateinit var btnPlusAlcoolFort: Button
    private lateinit var btnMoinsAlcoolFort: Button

    // UI Elements - Cases à cocher
    private lateinit var checkCigarettes: CheckBox
    private lateinit var checkJoints: CheckBox
    private lateinit var checkAlcoolGlobal: CheckBox
    private lateinit var checkBieres: CheckBox
    private lateinit var checkLiqueurs: CheckBox
    private lateinit var checkAlcoolFort: CheckBox

    // UI Elements - Bandeau profil et conseils
    private lateinit var txtProfilComplet: TextView
    private lateinit var txtTotalAujourdhui: TextView
    private lateinit var txtConseil: TextView

    // Conteneurs catégories
    private lateinit var containerCigarettes: LinearLayout
    private lateinit var containerJoints: LinearLayout
    private lateinit var containerAlcoolGlobal: LinearLayout
    private lateinit var containerBieres: LinearLayout
    private lateinit var containerLiqueurs: LinearLayout
    private lateinit var containerAlcoolFort: LinearLayout
        // Bouton version sans pub (accueil)
    private lateinit var btnPremiumAccueil: Button

    // Variables état
    private var cigarettesCount = 0
    private var jointsCount = 0
    private var alcoolGlobalCount = 0
    private var bieresCount = 0
    private var liqueursCount = 0
    private var alcoolFortCount = 0

    // Catégories actives
    private var categoriesActives = mutableMapOf(
        DatabaseHelper.TYPE_CIGARETTE to true,
        DatabaseHelper.TYPE_JOINT to true,
        DatabaseHelper.TYPE_ALCOOL_GLOBAL to true,
        DatabaseHelper.TYPE_BIERE to false,
        DatabaseHelper.TYPE_LIQUEUR to false,
        DatabaseHelper.TYPE_ALCOOL_FORT to false
    )

    // Handler pour rotation conseils
    private val conseilHandler = Handler(Looper.getMainLooper())
    private var conseilRunnable: Runnable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_accueil, container, false)

        try {
            // Initialisation database et config
            dbHelper = DatabaseHelper(requireContext())
            configLangue = ConfigLangue(requireContext())
            trad = AccueilLangues.getTraductions(configLangue.getLangue())

            // Initialisation des vues
            initViews(view)

            // Chargement catégories actives
            loadCategoriesActives()

            // Chargement consommations du jour
            loadConsommationsJour()

            // Configuration des listeners
            setupListeners()

            // Mise à jour affichage initial
            updateUI()

            // Démarrer rotation conseils
            startConseilRotation()

            updateConseil()

            Log.d(TAG, "AccueilFragment initialisé avec succès")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur initialisation AccueilFragment: ${e.message}")
            Toast.makeText(requireContext(), trad["erreur_chargement"] ?: "Erreur chargement", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun initViews(view: View) {
        try {
            // Compteurs
            txtTotalJour = view.findViewById(R.id.accueil_txt_total_jour)
            txtCigarettes = view.findViewById(R.id.accueil_txt_cigarettes)
            txtJoints = view.findViewById(R.id.accueil_txt_joints)
            txtAlcoolGlobal = view.findViewById(R.id.accueil_txt_alcool_global)
            txtBieres = view.findViewById(R.id.accueil_txt_bieres)
            txtLiqueurs = view.findViewById(R.id.accueil_txt_liqueurs)
            txtAlcoolFort = view.findViewById(R.id.accueil_txt_alcool_fort)

            // Boutons +
            btnPlusCigarettes = view.findViewById(R.id.accueil_btn_plus_cigarettes)
            btnPlusJoints = view.findViewById(R.id.accueil_btn_plus_joints)
            btnPlusAlcoolGlobal = view.findViewById(R.id.accueil_btn_plus_alcool_global)
            btnPlusBieres = view.findViewById(R.id.accueil_btn_plus_bieres)
            btnPlusLiqueurs = view.findViewById(R.id.accueil_btn_plus_liqueurs)
            btnPlusAlcoolFort = view.findViewById(R.id.accueil_btn_plus_alcool_fort)

            // Boutons -
            btnMoinsCigarettes = view.findViewById(R.id.accueil_btn_moins_cigarettes)
            btnMoinsJoints = view.findViewById(R.id.accueil_btn_moins_joints)
            btnMoinsAlcoolGlobal = view.findViewById(R.id.accueil_btn_moins_alcool_global)
            btnMoinsBieres = view.findViewById(R.id.accueil_btn_moins_bieres)
            btnMoinsLiqueurs = view.findViewById(R.id.accueil_btn_moins_liqueurs)
            btnMoinsAlcoolFort = view.findViewById(R.id.accueil_btn_moins_alcool_fort)

            // Cases à cocher
            checkCigarettes = view.findViewById(R.id.accueil_check_cigarettes)
            checkJoints = view.findViewById(R.id.accueil_check_joints)
            checkAlcoolGlobal = view.findViewById(R.id.accueil_check_alcool_global)
            checkBieres = view.findViewById(R.id.accueil_check_bieres)
            checkLiqueurs = view.findViewById(R.id.accueil_check_liqueurs)
            checkAlcoolFort = view.findViewById(R.id.accueil_check_alcool_fort)

            // Bandeau profil/conseils
            txtProfilComplet = view.findViewById(R.id.accueil_txt_profil_complet)
            txtTotalAujourdhui = view.findViewById(R.id.accueil_txt_total_aujourdhui)
            txtConseil = view.findViewById(R.id.accueil_txt_conseil)

            // Conteneurs catégories
            containerCigarettes = view.findViewById(R.id.accueil_container_cigarettes)
            containerJoints = view.findViewById(R.id.accueil_container_joints)
            containerAlcoolGlobal = view.findViewById(R.id.accueil_container_alcool_global)
            containerBieres = view.findViewById(R.id.accueil_container_bieres)
            containerLiqueurs = view.findViewById(R.id.accueil_container_liqueurs)
            containerAlcoolFort = view.findViewById(R.id.accueil_container_alcool_fort)

                        // Bouton version sans pub
            btnPremiumAccueil = view.findViewById(R.id.accueil_btn_premium)
            val tradReglages = ReglagesLangues.getTraductions(configLangue.getLangue())
            btnPremiumAccueil.text = tradReglages["btn_premium"] ?: "Version sans publicité"

            // Appliquer traductions aux CheckBox
            checkCigarettes.text = trad["label_cigarettes"] ?: "Cigarettes"
            checkJoints.text = trad["label_joints"] ?: "Joints"
            checkAlcoolGlobal.text = trad["label_alcool_global"] ?: "Alcool global"
            checkBieres.text = trad["label_bieres"] ?: "Bières"
            checkLiqueurs.text = trad["label_liqueurs"] ?: "Liqueurs"
            checkAlcoolFort.text = trad["label_alcool_fort"] ?: "Alcool fort"

            Log.d(TAG, "Vues initialisées avec succès")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur initialisation vues: ${e.message}")
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
            Log.e(TAG, "Erreur chargement catégories actives: ${e.message}")
        }
    }

    private fun loadConsommationsJour() {
        try {
            val consommations = dbHelper.getConsommationsJour()
            cigarettesCount = consommations[DatabaseHelper.TYPE_CIGARETTE] ?: 0
            jointsCount = consommations[DatabaseHelper.TYPE_JOINT] ?: 0
            alcoolGlobalCount = consommations[DatabaseHelper.TYPE_ALCOOL_GLOBAL] ?: 0
            bieresCount = consommations[DatabaseHelper.TYPE_BIERE] ?: 0
            liqueursCount = consommations[DatabaseHelper.TYPE_LIQUEUR] ?: 0
            alcoolFortCount = consommations[DatabaseHelper.TYPE_ALCOOL_FORT] ?: 0
            Log.d(TAG, "Consommations chargées: C=$cigarettesCount J=$jointsCount A=$alcoolGlobalCount B=$bieresCount L=$liqueursCount AF=$alcoolFortCount")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur chargement consommations: ${e.message}")
        }
    }
    private fun setupListeners() {
        try {
            // Cigarettes
            btnPlusCigarettes.setOnClickListener {
                if (categoriesActives[DatabaseHelper.TYPE_CIGARETTE] == true) {
                    ajouterConsommation(DatabaseHelper.TYPE_CIGARETTE)
                }
            }
            btnMoinsCigarettes.setOnClickListener {
                if (categoriesActives[DatabaseHelper.TYPE_CIGARETTE] == true) {
                    retirerConsommation(DatabaseHelper.TYPE_CIGARETTE)
                }
            }
            checkCigarettes.setOnCheckedChangeListener { _, isChecked ->
                toggleCategorie(DatabaseHelper.TYPE_CIGARETTE, isChecked)
            }

            // Joints
            btnPlusJoints.setOnClickListener {
                if (categoriesActives[DatabaseHelper.TYPE_JOINT] == true) {
                    ajouterConsommation(DatabaseHelper.TYPE_JOINT)
                }
            }
            btnMoinsJoints.setOnClickListener {
                if (categoriesActives[DatabaseHelper.TYPE_JOINT] == true) {
                    retirerConsommation(DatabaseHelper.TYPE_JOINT)
                }
            }
            checkJoints.setOnCheckedChangeListener { _, isChecked ->
                toggleCategorie(DatabaseHelper.TYPE_JOINT, isChecked)
            }

            // Alcool Global
            btnPlusAlcoolGlobal.setOnClickListener {
                if (categoriesActives[DatabaseHelper.TYPE_ALCOOL_GLOBAL] == true) {
                    ajouterConsommation(DatabaseHelper.TYPE_ALCOOL_GLOBAL)
                }
            }
            btnMoinsAlcoolGlobal.setOnClickListener {
                if (categoriesActives[DatabaseHelper.TYPE_ALCOOL_GLOBAL] == true) {
                    retirerConsommation(DatabaseHelper.TYPE_ALCOOL_GLOBAL)
                }
            }
            checkAlcoolGlobal.setOnCheckedChangeListener { _, isChecked ->
                toggleCategorie(DatabaseHelper.TYPE_ALCOOL_GLOBAL, isChecked)
            }

            // Bières
            btnPlusBieres.setOnClickListener {
                if (categoriesActives[DatabaseHelper.TYPE_BIERE] == true) {
                    ajouterConsommation(DatabaseHelper.TYPE_BIERE)
                }
            }
            btnMoinsBieres.setOnClickListener {
                if (categoriesActives[DatabaseHelper.TYPE_BIERE] == true) {
                    retirerConsommation(DatabaseHelper.TYPE_BIERE)
                }
            }
            checkBieres.setOnCheckedChangeListener { _, isChecked ->
                toggleCategorie(DatabaseHelper.TYPE_BIERE, isChecked)
            }

            // Liqueurs
            btnPlusLiqueurs.setOnClickListener {
                if (categoriesActives[DatabaseHelper.TYPE_LIQUEUR] == true) {
                    ajouterConsommation(DatabaseHelper.TYPE_LIQUEUR)
                }
            }
            btnMoinsLiqueurs.setOnClickListener {
                if (categoriesActives[DatabaseHelper.TYPE_LIQUEUR] == true) {
                    retirerConsommation(DatabaseHelper.TYPE_LIQUEUR)
                }
            }
            checkLiqueurs.setOnCheckedChangeListener { _, isChecked ->
                toggleCategorie(DatabaseHelper.TYPE_LIQUEUR, isChecked)
            }

            // Alcool Fort
            btnPlusAlcoolFort.setOnClickListener {
                if (categoriesActives[DatabaseHelper.TYPE_ALCOOL_FORT] == true) {
                    ajouterConsommation(DatabaseHelper.TYPE_ALCOOL_FORT)
                }
            }
            btnMoinsAlcoolFort.setOnClickListener {
                if (categoriesActives[DatabaseHelper.TYPE_ALCOOL_FORT] == true) {
                    retirerConsommation(DatabaseHelper.TYPE_ALCOOL_FORT)
                }
            }
            checkAlcoolFort.setOnCheckedChangeListener { _, isChecked ->
                toggleCategorie(DatabaseHelper.TYPE_ALCOOL_FORT, isChecked)
            }
            
                        // Bouton version sans pub (accueil)
            btnPremiumAccueil.setOnClickListener {
                showPremiumDialog()
            }

            Log.d(TAG, "Listeners configurés avec succès")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur configuration listeners: ${e.message}")
        }
    }

    private fun ajouterConsommation(type: String) {
        try {
            val success = dbHelper.ajouterConsommation(type)
            if (success) {
                // Incrémenter compteur local
                when (type) {
                    DatabaseHelper.TYPE_CIGARETTE -> cigarettesCount++
                    DatabaseHelper.TYPE_JOINT -> jointsCount++
                    DatabaseHelper.TYPE_ALCOOL_GLOBAL -> alcoolGlobalCount++
                    DatabaseHelper.TYPE_BIERE -> bieresCount++
                    DatabaseHelper.TYPE_LIQUEUR -> liqueursCount++
                    DatabaseHelper.TYPE_ALCOOL_FORT -> alcoolFortCount++
                }
                updateUI()
                updateConseil()
                Log.d(TAG, "Consommation ajoutée: $type")
            } else {
                Log.e(TAG, "Échec ajout consommation: $type")
                Toast.makeText(requireContext(), trad["erreur_ajout"] ?: "Erreur ajout", Toast.LENGTH_SHORT).show()    
            }
        } catch (e: Exception) {
            Log.e(TAG, "Erreur ajout consommation $type: ${e.message}")
            Toast.makeText(requireContext(), trad["erreur_generale"] ?: "Erreur", Toast.LENGTH_SHORT).show()
        }
    }

    private fun retirerConsommation(type: String) {
        try {
            // Vérifier qu'il y a au moins 1 élément à retirer
            val currentCount = when (type) {
                DatabaseHelper.TYPE_CIGARETTE -> cigarettesCount
                DatabaseHelper.TYPE_JOINT -> jointsCount
                DatabaseHelper.TYPE_ALCOOL_GLOBAL -> alcoolGlobalCount
                DatabaseHelper.TYPE_BIERE -> bieresCount
                DatabaseHelper.TYPE_LIQUEUR -> liqueursCount
                DatabaseHelper.TYPE_ALCOOL_FORT -> alcoolFortCount
                else -> 0
            }

            if (currentCount <= 0) {
                Log.w(TAG, trad["erreur_retrait"] ?: "Impossible de retirer")
                return
            }

            val success = dbHelper.retirerConsommation(type)
            if (success) {
                // Décrémenter compteur local
                when (type) {
                    DatabaseHelper.TYPE_CIGARETTE -> cigarettesCount--
                    DatabaseHelper.TYPE_JOINT -> jointsCount--
                    DatabaseHelper.TYPE_ALCOOL_GLOBAL -> alcoolGlobalCount--
                    DatabaseHelper.TYPE_BIERE -> bieresCount--
                    DatabaseHelper.TYPE_LIQUEUR -> liqueursCount--
                    DatabaseHelper.TYPE_ALCOOL_FORT -> alcoolFortCount--
                }
                updateUI()
                updateConseil()
                Log.d(TAG, "Consommation retirée: $type")
            } else {
                Log.e(TAG, "Échec retrait consommation: $type")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Erreur retrait consommation $type: ${e.message}")
            Toast.makeText(requireContext(), trad["erreur_generale"] ?: "Erreur", Toast.LENGTH_SHORT).show()
        }
    }

    private fun toggleCategorie(type: String, isActive: Boolean) {
        try {
            categoriesActives[type] = isActive

            // Sauvegarder dans database
            val json = JSONObject().apply {
                put("cigarette", categoriesActives[DatabaseHelper.TYPE_CIGARETTE])
                put("joint", categoriesActives[DatabaseHelper.TYPE_JOINT])
                put("alcool_global", categoriesActives[DatabaseHelper.TYPE_ALCOOL_GLOBAL])
                put("biere", categoriesActives[DatabaseHelper.TYPE_BIERE])
                put("liqueur", categoriesActives[DatabaseHelper.TYPE_LIQUEUR])
                put("alcool_fort", categoriesActives[DatabaseHelper.TYPE_ALCOOL_FORT])
            }.toString()

            dbHelper.setPreference("categories_actives", json)

            // Gérer exclusion alcool_global vs alcools spécifiques
            if (type == DatabaseHelper.TYPE_ALCOOL_GLOBAL && isActive) {
                // Désactiver alcools spécifiques
                categoriesActives[DatabaseHelper.TYPE_BIERE] = false
                categoriesActives[DatabaseHelper.TYPE_LIQUEUR] = false
                categoriesActives[DatabaseHelper.TYPE_ALCOOL_FORT] = false
                checkBieres.isChecked = false
                checkLiqueurs.isChecked = false
                checkAlcoolFort.isChecked = false
            } else if ((type == DatabaseHelper.TYPE_BIERE || type == DatabaseHelper.TYPE_LIQUEUR || type == DatabaseHelper.TYPE_ALCOOL_FORT) && isActive) {
                // Désactiver alcool global
                categoriesActives[DatabaseHelper.TYPE_ALCOOL_GLOBAL] = false
                checkAlcoolGlobal.isChecked = false
            }

            updateUI()
            updateConseil()
            Log.d(TAG, "Catégorie $type basculée: $isActive")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur toggle catégorie $type: ${e.message}")
        }
    }

    private fun updateUI() {
        try {
            // Mise à jour compteurs
            txtCigarettes.text = cigarettesCount.toString()
            txtJoints.text = jointsCount.toString()
            txtAlcoolGlobal.text = alcoolGlobalCount.toString()
            txtBieres.text = bieresCount.toString()
            txtLiqueurs.text = liqueursCount.toString()
            txtAlcoolFort.text = alcoolFortCount.toString()

            // Calcul total jour
            val totalJour = cigarettesCount + jointsCount + alcoolGlobalCount + bieresCount + liqueursCount + alcoolFortCount
            val labelTotal = trad["total_aujourdhui"] ?: "Total aujourd'hui"
            txtTotalJour.text = "$labelTotal $totalJour"
            txtTotalAujourdhui.text = totalJour.toString()

            // Mise à jour état des cases à cocher
            checkCigarettes.isChecked = categoriesActives[DatabaseHelper.TYPE_CIGARETTE] ?: true
            checkJoints.isChecked = categoriesActives[DatabaseHelper.TYPE_JOINT] ?: true
            checkAlcoolGlobal.isChecked = categoriesActives[DatabaseHelper.TYPE_ALCOOL_GLOBAL] ?: true
            checkBieres.isChecked = categoriesActives[DatabaseHelper.TYPE_BIERE] ?: false
            checkLiqueurs.isChecked = categoriesActives[DatabaseHelper.TYPE_LIQUEUR] ?: false
            checkAlcoolFort.isChecked = categoriesActives[DatabaseHelper.TYPE_ALCOOL_FORT] ?: false

            // Visibilité boutons selon catégories actives
            updateButtonsVisibility()

            // Mise à jour profil complet/incomplet
            updateProfilStatus()

            Log.d(TAG, "UI mise à jour - Total: $totalJour")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur mise à jour UI: ${e.message}")
        }
    }

    private fun updateButtonsVisibility() {
        try {
            // Les catégories restent visibles mais les boutons sont actifs/inactifs selon état
            btnPlusCigarettes.isEnabled = categoriesActives[DatabaseHelper.TYPE_CIGARETTE] ?: true
            btnMoinsCigarettes.isEnabled = categoriesActives[DatabaseHelper.TYPE_CIGARETTE] ?: true
            
            btnPlusJoints.isEnabled = categoriesActives[DatabaseHelper.TYPE_JOINT] ?: true
            btnMoinsJoints.isEnabled = categoriesActives[DatabaseHelper.TYPE_JOINT] ?: true
            
            btnPlusAlcoolGlobal.isEnabled = categoriesActives[DatabaseHelper.TYPE_ALCOOL_GLOBAL] ?: true
            btnMoinsAlcoolGlobal.isEnabled = categoriesActives[DatabaseHelper.TYPE_ALCOOL_GLOBAL] ?: true
            
            btnPlusBieres.isEnabled = categoriesActives[DatabaseHelper.TYPE_BIERE] ?: false
            btnMoinsBieres.isEnabled = categoriesActives[DatabaseHelper.TYPE_BIERE] ?: false
            
            btnPlusLiqueurs.isEnabled = categoriesActives[DatabaseHelper.TYPE_LIQUEUR] ?: false
            btnMoinsLiqueurs.isEnabled = categoriesActives[DatabaseHelper.TYPE_LIQUEUR] ?: false
            
            btnPlusAlcoolFort.isEnabled = categoriesActives[DatabaseHelper.TYPE_ALCOOL_FORT] ?: false
            btnMoinsAlcoolFort.isEnabled = categoriesActives[DatabaseHelper.TYPE_ALCOOL_FORT] ?: false

            Log.d(TAG, "Visibilité boutons mise à jour")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur mise à jour visibilité boutons: ${e.message}")
        }
    }

    private fun updateProfilStatus() {
        try {
            // Vérifier si profil complet (coûts + habitudes + dates remplis)
            var isComplete = true

            // Vérifier coûts (au moins une catégorie active avec coût > 0)
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

            // Vérifier habitudes (au moins une catégorie active avec max > 0)
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

            // Vérifier dates (au moins une date renseignée)
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

            // Mise à jour texte avec traductions
        val texteProfil = if (isComplete) {
        trad["profil_complet"] ?: "Profil: Complet ✓"
        } else {
        trad["profil_incomplet"] ?: "Profil: Incomplet"
        }
        txtProfilComplet.text = texteProfil

        Log.d(TAG, "Profil: ${if (isComplete) "Complet" else "Incomplet"}")

        } catch (e: Exception) {
            Log.e(TAG, "Erreur mise à jour profil: ${e.message}")
        }
    }
    
    private fun startConseilRotation() {
    logger.d("startConseilRotation: initialisation de la rotation de conseils (intervalle = $CONSEIL_UPDATE_INTERVAL ms)")

    try {
        // Annuler un éventuel ancien runnable
        conseilRunnable?.let {
            logger.d("startConseilRotation: ancien runnable trouvé -> removeCallbacks")
            conseilHandler.removeCallbacks(it)
        }

        // Nouveau runnable qui appelle updateConseil() régulièrement
        conseilRunnable = object : Runnable {
            override fun run() {
                try {
                    logger.d("startConseilRotation: exécution du Runnable -> appel updateConseil()")
                    updateConseil()
                    logger.d("startConseilRotation: reprogrammation du Runnable dans $CONSEIL_UPDATE_INTERVAL ms")
                } catch (e: Exception) {
                    logger.e("startConseilRotation: erreur pendant updateConseil", e)
                } finally {
                    conseilHandler.postDelayed(this, CONSEIL_UPDATE_INTERVAL)
                }
            }
        }

        // Premier lancement
        conseilHandler.post(conseilRunnable!!)
        logger.d("startConseilRotation: rotation conseils démarrée")
    } catch (e: Exception) {
        logger.e("startConseilRotation: erreur démarrage rotation conseils", e)
    }
}

    private fun updateConseil() {
        try {
            val prenom = dbHelper.getPreference("prenom", "")
            
            // Vérifier présence coûts
            var hasCouts = false
            categoriesActives.forEach { (type, active) ->
                if (active) {
                    val couts = dbHelper.getCouts(type)
                    if (couts.values.any { it > 0 }) {
                        hasCouts = true
                    }
                }
            }

            // Vérifier présence habitudes
            var hasHabitudes = false
            categoriesActives.forEach { (type, active) ->
                if (active) {
                    val max = dbHelper.getMaxJournalier(type)
                    if (max > 0) {
                        hasHabitudes = true
                    }
                }
            }

            // Vérifier présence dates
            var hasDates = false
            categoriesActives.forEach { (type, active) ->
                if (active) {
                    val dates = dbHelper.getDatesObjectifs(type)
                    if (dates.values.any { !it.isNullOrEmpty() }) {
                        hasDates = true
                    }
                }
            }

            // Déterminer combinaison (16 possibilités)
            val hasPrenom = prenom.isNotEmpty()
            val conseil = genererConseil(hasPrenom, hasCouts, hasHabitudes, hasDates, prenom)
            
            txtConseil.text = conseil
            Log.d(TAG, "Conseil mis à jour: P=$hasPrenom C=$hasCouts H=$hasHabitudes D=$hasDates")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur mise à jour conseil: ${e.message}")
            txtConseil.text = trad["conseil_generique_6"] ?: "Restez motivé!"
        }
    }

    private fun genererConseil(
    hasPrenom: Boolean,
    hasCouts: Boolean,
    hasHabitudes: Boolean,
    hasDates: Boolean,
    prenom: String
): String {
    logger.d("genererConseil: start - hasPrenom=$hasPrenom, hasCouts=$hasCouts, hasHabitudes=$hasHabitudes, hasDates=$hasDates, prenom=\"$prenom\"")

    // Banque de conseils selon combinaison (16 cas)
    val conseils = mutableListOf<String>()

    when {
        // Cas 1: Rien
        !hasPrenom && !hasCouts && !hasHabitudes && !hasDates -> {
            logger.d("genererConseil: cas 1 = aucun élément renseigné")
            conseils.add(trad["conseil_cas1_1"] ?: "Bienvenue!")
            conseils.add(trad["conseil_cas1_2"] ?: "Chaque pas compte")
            conseils.add(trad["conseil_cas1_3"] ?: "Motivation!")
        }
        // Cas 2: Prénom uniquement
        hasPrenom && !hasCouts && !hasHabitudes && !hasDates -> {
            logger.d("genererConseil: cas 2 = prénom uniquement")
            conseils.add("$prenom, " + (trad["conseil_cas1_1"] ?: "Bienvenue! Ajoutez vos coûts pour voir vos économies."))
            conseils.add("$prenom, " + (trad["conseil_generique_6"] ?: "restez déterminé dans votre parcours!"))
            conseils.add("$prenom, " + (trad["conseil_generique_5"] ?: "chaque jour est une nouvelle opportunité."))
        }
        // Cas 3: Coûts uniquement
        !hasPrenom && hasCouts && !hasHabitudes && !hasDates -> {
            logger.d("genererConseil: cas 3 = coûts uniquement")
            val economies = calculerEconomiesJour()
            logger.d("genererConseil: cas 3 -> économies jour = $economies")
            if (economies > 0) {
                conseils.add(String.format(trad["economies_jour"] ?: "Vous économisez %.2f€ aujourd'hui!", economies))
                conseils.add(trad["conseil_cas3_3"] ?: "Économies")
            } else {
                conseils.add(trad["conseil_cas3_1"] ?: "Économisez")
            }
            conseils.add(trad["conseil_cas3_2"] ?: "Projets")
        }
        // Cas 4: Habitudes uniquement
        !hasPrenom && !hasCouts && hasHabitudes && !hasDates -> {
            logger.d("genererConseil: cas 4 = habitudes uniquement")
            val comparaison = comparerHabitudes()
            logger.d("genererConseil: cas 4 -> comparaison=\"$comparaison\"")
            conseils.add(comparaison)
            conseils.add(trad["conseil_cas4_1"] ?: "Habitudes")
            conseils.add(trad["conseil_cas4_2"] ?: "Objectifs")
        }
        // Cas 5: Dates uniquement
        !hasPrenom && !hasCouts && !hasHabitudes && hasDates -> {
            logger.d("genererConseil: cas 5 = dates uniquement")
            val conseilDate = genererConseilDate()
            logger.d("genererConseil: cas 5 -> conseilDate=\"$conseilDate\"")
            conseils.add(conseilDate)
            conseils.add(trad["conseil_cas5_1"] ?: "Objectif proche!")
            conseils.add(trad["conseil_cas5_2"] ?: "Restez concentré sur votre date.")
        }
        // Cas 6: Prénom + Coûts
        hasPrenom && hasCouts && !hasHabitudes && !hasDates -> {
            logger.d("genererConseil: cas 6 = prénom + coûts")
            val economies = calculerEconomiesJour()
            logger.d("genererConseil: cas 6 -> économies jour = $economies")
            if (economies > 0) {
                conseils.add("$prenom, " + String.format(trad["economies_jour"] ?: "vous économisez %.2f€ aujourd'hui!", economies))
                conseils.add("$prenom, " + (trad["economies_accumulent"] ?: "ces économies s'accumulent rapidement!"))
            } else {
                conseils.add("$prenom, " + (trad["conseil_cas3_1"] ?: "réduire maintenant générera des économies."))
            }
        }
        // Cas 7: Prénom + Habitudes
        hasPrenom && !hasCouts && hasHabitudes && !hasDates -> {
            logger.d("genererConseil: cas 7 = prénom + habitudes")
            val comparaison = comparerHabitudes()
            logger.d("genererConseil: cas 7 -> comparaison=\"$comparaison\"")
            conseils.add("$prenom, $comparaison")
            conseils.add("$prenom, " + (trad["conseil_generique_5"] ?: "vous progressez bien!"))
        }
        // Cas 8: Prénom + Dates
        hasPrenom && !hasCouts && !hasHabitudes && hasDates -> {
            logger.d("genererConseil: cas 8 = prénom + dates")
            val conseilDate = genererConseilDate()
            logger.d("genererConseil: cas 8 -> conseilDate=\"$conseilDate\"")
            conseils.add("$prenom, $conseilDate")
            conseils.add("$prenom, " + (trad["date_rapproche"] ?: "votre objectif approche, tenez bon!"))
        }
        // Cas 9: Coûts + Habitudes
        !hasPrenom && hasCouts && hasHabitudes && !hasDates -> {
            logger.d("genererConseil: cas 9 = coûts + habitudes")
            val economies = calculerEconomiesReelles()
            logger.d("genererConseil: cas 9 -> économies réelles = $economies")
            if (economies > 0) {
                conseils.add(String.format(trad["economies_reelles"] ?: "Économies réelles: %.2f€ vs vos habitudes!", economies))
                conseils.add(trad["conseil_generique_5"] ?: "Vous faites mieux que prévu, bravo!")
            } else {
                conseils.add(trad["habitudes_egal"] ?: "Vous consommez selon vos habitudes.")
            }
        }
        // Cas 10: Coûts + Dates
        !hasPrenom && hasCouts && !hasHabitudes && hasDates -> {
            logger.d("genererConseil: cas 10 = coûts + dates")
            val economies = calculerEconomiesJour()
            val conseilDate = genererConseilDate()
            logger.d("genererConseil: cas 10 -> économies=$economies, conseilDate=\"$conseilDate\"")
            conseils.add(String.format(trad["economies_jour"] ?: "Vous économisez %.2f€ aujourd'hui!", economies) + " - " + conseilDate)
            conseils.add(trad["economies_accumulent"] ?: "Votre porte-monnaie vous remercie!")
        }
        // Cas 11: Habitudes + Dates
        !hasPrenom && !hasCouts && hasHabitudes && hasDates -> {
            logger.d("genererConseil: cas 11 = habitudes + dates")
            val comparaison = comparerHabitudes()
            val conseilDate = genererConseilDate()
            logger.d("genererConseil: cas 11 -> comparaison=\"$comparaison\", conseilDate=\"$conseilDate\"")
            conseils.add("$comparaison - $conseilDate")
            conseils.add(trad["conseil_generique_6"] ?: "Restez fidèle à vos objectifs!")
        }
        // Cas 12: Prénom + Coûts + Habitudes
        hasPrenom && hasCouts && hasHabitudes && !hasDates -> {
            logger.d("genererConseil: cas 12 = prénom + coûts + habitudes")
            val economies = calculerEconomiesReelles()
            logger.d("genererConseil: cas 12 -> économies réelles = $economies")
            if (economies > 0) {
                conseils.add(
                    "$prenom, " +
                        String.format(
                            trad["economies_reelles"] ?: "économies réelles: %.2f€ vs habitudes!",
                            economies
                        )
                )
                conseils.add("$prenom, " + (trad["conseil_cas5_1"] ?: "vous êtes sur la bonne voie!"))
            } else {
                conseils.add("$prenom, " + (trad["conseil_generique_6"] ?: "maintenez vos efforts!"))
            }
        }
        // Cas 13: Prénom + Coûts + Dates
        hasPrenom && hasCouts && !hasHabitudes && hasDates -> {
            logger.d("genererConseil: cas 13 = prénom + coûts + dates")
            val economies = calculerEconomiesJour()
            val conseilDate = genererConseilDate()
            logger.d("genererConseil: cas 13 -> économies=$economies, conseilDate=\"$conseilDate\"")
            conseils.add(
                "$prenom, " +
                    String.format(
                        trad["economies_jour"] ?: "vous économisez %.2f€ aujourd'hui!",
                        economies
                    ) +
                    " - " + conseilDate
            )
            conseils.add("$prenom, " + (trad["date_rapproche"] ?: "votre objectif approche, tenez bon!"))
        }
        // Cas 14: Prénom + Habitudes + Dates
        hasPrenom && !hasCouts && hasHabitudes && hasDates -> {
            logger.d("genererConseil: cas 14 = prénom + habitudes + dates")
            val comparaison = comparerHabitudes()
            val conseilDate = genererConseilDate()
            logger.d("genererConseil: cas 14 -> comparaison=\"$comparaison\", conseilDate=\"$conseilDate\"")
            conseils.add("$prenom, $comparaison - $conseilDate")
            conseils.add("$prenom, " + (trad["conseil_generique_6"] ?: "continuez ainsi!"))
        }
        // Cas 15: Coûts + Habitudes + Dates
        !hasPrenom && hasCouts && hasHabitudes && hasDates -> {
            logger.d("genererConseil: cas 15 = coûts + habitudes + dates")
            val economies = calculerEconomiesReelles()
            val conseilDate = genererConseilDate()
            logger.d("genererConseil: cas 15 -> économies=$economies, conseilDate=\"$conseilDate\"")
            conseils.add(String.format(trad["economies_jour"] ?: "Vous économisez %.2f€ aujourd'hui!", economies) + " - " + conseilDate)
            conseils.add(trad["conseil_generique_5"] ?: "Vous progressez sur tous les fronts!")
        }
        // Cas 16: COMPLET (Prénom + Coûts + Habitudes + Dates)
        hasPrenom && hasCouts && hasHabitudes && hasDates -> {
            logger.d("genererConseil: cas 16 = COMPLET (prénom + coûts + habitudes + dates)")
            val economies = calculerEconomiesReelles()
            val conseilDate = genererConseilDate()
            logger.d("genererConseil: cas 16 -> économies=$economies, conseilDate=\"$conseilDate\"")
            conseils.add(
                "$prenom, " +
                    String.format(
                        trad["economies_jour"] ?: "vous économisez %.2f€ aujourd'hui!",
                        economies
                    ) +
                    " - " + conseilDate
            )
            conseils.add("$prenom, " + (trad["conseil_generique_6"] ?: "profil complet, continuez ainsi!"))
            conseils.add("$prenom, " + (trad["conseil_generique_6"] ?: "vous êtes un exemple de motivation!"))
        }
    }

    // Ajouter conseils génériques (toujours pertinents)
    conseils.add(trad["conseil_generique_1"] ?: "Chaque cigarette non fumée ajoute 11 minutes à votre vie.")
    conseils.add(trad["conseil_generique_2"] ?: "L'exercice physique aide à réduire l'envie.")
    conseils.add(trad["conseil_generique_3"] ?: "Boire de l'eau réduit les sensations de manque.")
    conseils.add(trad["conseil_generique_4"] ?: "Entourez-vous de personnes soutenantes.")

    val conseilFinal = conseils.random()
    logger.d("genererConseil: fin - ${conseils.size} candidats, conseil choisi = \"$conseilFinal\"")

    return conseilFinal
}

    private fun calculerEconomiesJour(): Double {
        var economies = 0.0
        try {
            categoriesActives.forEach { (type, active) ->
                if (active) {
                    val couts = dbHelper.getCouts(type)
                    val consommation = when (type) {
                        DatabaseHelper.TYPE_CIGARETTE -> cigarettesCount
                        DatabaseHelper.TYPE_JOINT -> jointsCount
                        DatabaseHelper.TYPE_ALCOOL_GLOBAL -> alcoolGlobalCount
                        DatabaseHelper.TYPE_BIERE -> bieresCount
                        DatabaseHelper.TYPE_LIQUEUR -> liqueursCount
                        DatabaseHelper.TYPE_ALCOOL_FORT -> alcoolFortCount
                        else -> 0
                    }
                    
                    // Calcul coût simplifié (prix_paquet / nb_cigarettes) * consommation
                    val prixUnitaire = if (couts["prix_paquet"] ?: 0.0 > 0 && couts["nb_cigarettes"] ?: 0.0 > 0) {
                        (couts["prix_paquet"] ?: 0.0) / (couts["nb_cigarettes"] ?: 1.0)
                    } else {
                        couts["prix_verre"] ?: 0.0
                    }
                    
                    economies += prixUnitaire * consommation
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Erreur calcul économies jour: ${e.message}")
        }
        return String.format("%.2f", economies).toDouble()
    }

    private fun calculerEconomiesReelles(): Double {
        var economies = 0.0
        try {
            categoriesActives.forEach { (type, active) ->
                if (active) {
                    val maxHabitude = dbHelper.getMaxJournalier(type)
                    val consommation = when (type) {
                        DatabaseHelper.TYPE_CIGARETTE -> cigarettesCount
                        DatabaseHelper.TYPE_JOINT -> jointsCount
                        DatabaseHelper.TYPE_ALCOOL_GLOBAL -> alcoolGlobalCount
                        DatabaseHelper.TYPE_BIERE -> bieresCount
                        DatabaseHelper.TYPE_LIQUEUR -> liqueursCount
                        DatabaseHelper.TYPE_ALCOOL_FORT -> alcoolFortCount
                        else -> 0
                    }
                    
                    if (maxHabitude > consommation) {
                        val diff = maxHabitude - consommation
                        val couts = dbHelper.getCouts(type)
                        val prixUnitaire = if (couts["prix_paquet"] ?: 0.0 > 0 && couts["nb_cigarettes"] ?: 0.0 > 0) {
                            (couts["prix_paquet"] ?: 0.0) / (couts["nb_cigarettes"] ?: 1.0)
                        } else {
                            couts["prix_verre"] ?: 0.0
                        }
                        economies += prixUnitaire * diff
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Erreur calcul économies réelles: ${e.message}")
        }
        return String.format("%.2f", economies).toDouble()
    }

    private fun comparerHabitudes(): String {
    return try {
        categoriesActives.forEach { (type, active) ->
            if (active) {
                val maxHabitude = dbHelper.getMaxJournalier(type)
                if (maxHabitude > 0) {
                    val consommation = when (type) {
                        DatabaseHelper.TYPE_CIGARETTE -> cigarettesCount
                        DatabaseHelper.TYPE_JOINT -> jointsCount
                        DatabaseHelper.TYPE_ALCOOL_GLOBAL -> alcoolGlobalCount
                        DatabaseHelper.TYPE_BIERE -> bieresCount
                        DatabaseHelper.TYPE_LIQUEUR -> liqueursCount
                        DatabaseHelper.TYPE_ALCOOL_FORT -> alcoolFortCount
                        else -> 0
                    }

                    return when {
                        consommation < maxHabitude ->
                            trad["habitudes_moins"] ?: "Vous consommez moins que d'habitude, bravo!"
                        consommation == maxHabitude ->
                            trad["habitudes_egal"] ?: "Vous êtes dans vos habitudes."
                        else ->
                            trad["habitudes_plus"] ?: "Vous dépassez vos habitudes, attention!"
                    }
                }
            }
        }
        trad["habitudes_suivre"] ?: "Suivez vos habitudes pour progresser."
    } catch (e: Exception) {
        Log.e(TAG, "Erreur comparaison habitudes: ${e.message}")
        trad["habitudes_suivre"] ?: "Suivez vos habitudes pour progresser."
    }
}
    private fun genererConseilDate(): String {
    return try {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val today = Date()

        categoriesActives.forEach { (type, active) ->
            if (active) {
                val dates = dbHelper.getDatesObjectifs(type)

                // Vérifier date arrêt
                dates["arret"]?.let { dateStr ->
                    if (dateStr.isNotEmpty()) {
                        val dateArret = dateFormat.parse(dateStr)
                        if (dateArret != null) {
                            val diffDays = ((dateArret.time - today.time) / (1000 * 60 * 60 * 24)).toInt()
                            return when {
                                diffDays > 0 -> {
                                    val pattern = trad["date_jours_restants"]
                                    if (pattern != null) {
                                        String.format(pattern, diffDays)
                                    } else {
                                        "Plus que $diffDays jours avant votre date d'arrêt!"
                                    }
                                }
                                diffDays == 0 -> trad["date_aujourdhui"]
                                    ?: "C'est aujourd'hui votre date d'arrêt, courage!"
                                else -> trad["date_depassee"]
                                    ?: "Vous avez dépassé votre date d'arrêt, continuez!"
                            }
                        }
                    }
                }

                // Vérifier date réduction
                dates["reduction"]?.let { dateStr ->
                    if (dateStr.isNotEmpty()) {
                        val dateReduction = dateFormat.parse(dateStr)
                        if (dateReduction != null) {
                            val diffDays = ((dateReduction.time - today.time) / (1000 * 60 * 60 * 24)).toInt()
                            if (diffDays >= 0) {
                                val pattern = trad["date_reduction"]
                                return if (pattern != null) {
                                    String.format(pattern, diffDays)
                                } else {
                                    "Date de réduction: dans $diffDays jours!"
                                }
                            }
                        }
                    }
                }
            }
        }

        trad["date_rapproche"] ?: "Votre objectif se rapproche!"
    } catch (e: Exception) {
        Log.e(TAG, "Erreur génération conseil date: ${e.message}")
        trad["date_rapproche"] ?: "Votre objectif se rapproche!"
    }
}
    override fun onResume() {
        super.onResume()
        try {
            // Recharger consommations (synchro si modif depuis autre onglet)
            loadConsommationsJour()
            loadCategoriesActives()
            updateUI()
            updateConseil()
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
            // Arrêter rotation conseils
            conseilRunnable?.let { conseilHandler.removeCallbacks(it) }
            conseilRunnable = null
            Log.d(TAG, "Fragment détruit - rotation conseils arrêtée")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur onDestroyView: ${e.message}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            // Cleanup final si nécessaire
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
            loadConsommationsJour()
            loadCategoriesActives()
            updateUI()
            updateConseil()
            Log.d(TAG, "Données rafraîchies manuellement")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur refresh data: ${e.message}")
        }
    }

    /**
     * Fonction pour forcer mise à jour d'un conseil
     * (utile pour tests ou actions utilisateur spécifiques)
     */
        fun forceUpdateConseil() {
        try {
            updateConseil()
            Log.d(TAG, "Conseil mis à jour manuellement")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur force update conseil: ${e.message}")
        }
    }

    private fun showPremiumDialog() {
        val tradReglages = ReglagesLangues.getTraductions(configLangue.getLangue())
        val titre = tradReglages["premium_titre"] ?: "Version sans publicité"
        val contenu = tradReglages["premium_contenu"] ?: "La version sans publicité sera bientôt disponible."
        val btnOk = trad["btn_ok"] ?: "Fermer"

        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle(titre)
            .setMessage(contenu)
            .setPositiveButton(btnOk, null)
            .show()
    }
}
