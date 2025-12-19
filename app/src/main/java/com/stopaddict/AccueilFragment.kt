package com.stopaddict

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.content.ActivityNotFoundException
import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import android.content.Intent
import android.net.Uri
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

    // Délai anti-spam pour la mise à jour des conseils après interaction utilisateur (10s)
    private const val CONSEIL_ANTISPAM_DELAY = 10_000L

    // Préférences liées aux cigarettes (mêmes clés que StatsFragment / ReglagesFragment)
    private const val PREF_MODE_CIGARETTE = "mode_cigarette"
    private const val PREF_NB_CIGARETTES_ROULEES = "nb_cigarettes_roulees"
    private const val PREF_NB_CIGARETTES_TUBEES = "nb_cigarettes_tubees"
}

    // Database
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var configLangue: ConfigLangue
    private lateinit var trad: Map<String, String>
    private val logger = AppLogger("AccueilFragment")
    
    // UI Elements - Compteurs
    private lateinit var txtTotalJour: TextView
    private lateinit var txtTotalJourLabel: TextView
    private lateinit var txtCigarettes: TextView
    private lateinit var txtJoints: TextView
    private lateinit var txtAlcoolGlobal: TextView
    private lateinit var txtBieres: TextView
    private lateinit var txtLiqueurs: TextView
    private lateinit var txtAlcoolFort: TextView

    private lateinit var bandeauProfil: View

    private lateinit var imgConseil: ImageView

    // UI Elements - Boutons +/-
    private lateinit var btnPlusCigarettes: androidx.appcompat.widget.AppCompatImageButton
    private lateinit var btnMoinsCigarettes: androidx.appcompat.widget.AppCompatImageButton
    private lateinit var btnPlusJoints: androidx.appcompat.widget.AppCompatImageButton
    private lateinit var btnMoinsJoints: androidx.appcompat.widget.AppCompatImageButton
    private lateinit var btnPlusAlcoolGlobal: androidx.appcompat.widget.AppCompatImageButton
    private lateinit var btnMoinsAlcoolGlobal: androidx.appcompat.widget.AppCompatImageButton
    private lateinit var btnPlusBieres: androidx.appcompat.widget.AppCompatImageButton
    private lateinit var btnMoinsBieres: androidx.appcompat.widget.AppCompatImageButton
    private lateinit var btnPlusLiqueurs: androidx.appcompat.widget.AppCompatImageButton
    private lateinit var btnMoinsLiqueurs: androidx.appcompat.widget.AppCompatImageButton
    private lateinit var btnPlusAlcoolFort: androidx.appcompat.widget.AppCompatImageButton
    private lateinit var btnMoinsAlcoolFort: androidx.appcompat.widget.AppCompatImageButton

    // UI Elements - Cases à cocher
    private lateinit var checkCigarettes: CheckBox
    private lateinit var checkJoints: CheckBox
    private lateinit var checkAlcoolGlobal: CheckBox
    private lateinit var checkBieres: CheckBox
    private lateinit var checkLiqueurs: CheckBox
    private lateinit var checkAlcoolFort: CheckBox

    // UI Elements - Bandeau profil (progression) et conseils
    private lateinit var txtProfilComplet: TextView
    private lateinit var profilProgress: ProgressBar
    private lateinit var txtProfilRestant: TextView
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

    // Anti-spam pour la mise à jour des conseils après interaction utilisateur
    private var lastUserInteractionTime: Long = 0L
    private var conseilAntispamRunnable: Runnable? = null

    private enum class ConseilCategorie { SANTE, SPORT, EAU, SOCIAL, OBJECTIF, ECONOMIE, HABITUDE, GENERIQUE }

    private data class ConseilItem(
        val texte: String,
        val categorie: ConseilCategorie
    )

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
            txtTotalJourLabel = view.findViewById(R.id.accueil_txt_total_jour_label)
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
            bandeauProfil = view.findViewById(R.id.accueil_bandeau_profil)
            txtConseil = view.findViewById(R.id.accueil_txt_conseil)
            imgConseil = view.findViewById(R.id.accueil_img_conseil)
            profilProgress = view.findViewById(R.id.accueil_profil_progress)
            txtProfilRestant = view.findViewById(R.id.accueil_txt_profil_restant)

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

            // Ajout des icônes (emoji) comme dans le header et Stats – sans impacter la traduction
            checkCigarettes.text   = "🚬 " + (trad["label_cigarettes"] ?: "Cigarettes")
            checkJoints.text       = "🌿 " + (trad["label_joints"] ?: "Joints")
            checkAlcoolGlobal.text = "🥃G " + (trad["label_alcool_global"] ?: "Alcool global")
            checkBieres.text       = "🍺 " + (trad["label_bieres"] ?: "Bières")
            checkLiqueurs.text     = "🍷 " + (trad["label_liqueurs"] ?: "Liqueurs")
            checkAlcoolFort.text   = "🥃 " + (trad["label_alcool_fort"] ?: "Alcool fort")

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
                (activity as? MainActivity)?.refreshHeaderResumeJour()
            }
        }
        btnMoinsCigarettes.setOnClickListener {
            if (categoriesActives[DatabaseHelper.TYPE_CIGARETTE] == true) {
                retirerConsommation(DatabaseHelper.TYPE_CIGARETTE)
                (activity as? MainActivity)?.refreshHeaderResumeJour()
            }
        }
        checkCigarettes.setOnCheckedChangeListener { _, isChecked ->
            toggleCategorie(DatabaseHelper.TYPE_CIGARETTE, isChecked)
        }

        // Joints
        btnPlusJoints.setOnClickListener {
            if (categoriesActives[DatabaseHelper.TYPE_JOINT] == true) {
                ajouterConsommation(DatabaseHelper.TYPE_JOINT)
                (activity as? MainActivity)?.refreshHeaderResumeJour()
            }
        }
        btnMoinsJoints.setOnClickListener {
            if (categoriesActives[DatabaseHelper.TYPE_JOINT] == true) {
                retirerConsommation(DatabaseHelper.TYPE_JOINT)
                (activity as? MainActivity)?.refreshHeaderResumeJour()
            }
        }
        checkJoints.setOnCheckedChangeListener { _, isChecked ->
            toggleCategorie(DatabaseHelper.TYPE_JOINT, isChecked)
        }

        // Alcool Global
        btnPlusAlcoolGlobal.setOnClickListener {
            if (categoriesActives[DatabaseHelper.TYPE_ALCOOL_GLOBAL] == true) {
                ajouterConsommation(DatabaseHelper.TYPE_ALCOOL_GLOBAL)
                (activity as? MainActivity)?.refreshHeaderResumeJour()
            }
        }
        btnMoinsAlcoolGlobal.setOnClickListener {
            if (categoriesActives[DatabaseHelper.TYPE_ALCOOL_GLOBAL] == true) {
                retirerConsommation(DatabaseHelper.TYPE_ALCOOL_GLOBAL)
                (activity as? MainActivity)?.refreshHeaderResumeJour()
            }
        }
        checkAlcoolGlobal.setOnCheckedChangeListener { _, isChecked ->
            toggleCategorie(DatabaseHelper.TYPE_ALCOOL_GLOBAL, isChecked)
        }

        // Bières
        btnPlusBieres.setOnClickListener {
            if (categoriesActives[DatabaseHelper.TYPE_BIERE] == true) {
                ajouterConsommation(DatabaseHelper.TYPE_BIERE)
                (activity as? MainActivity)?.refreshHeaderResumeJour()
            }
        }
        btnMoinsBieres.setOnClickListener {
            if (categoriesActives[DatabaseHelper.TYPE_BIERE] == true) {
                retirerConsommation(DatabaseHelper.TYPE_BIERE)
                (activity as? MainActivity)?.refreshHeaderResumeJour()
            }
        }
        checkBieres.setOnCheckedChangeListener { _, isChecked ->
            toggleCategorie(DatabaseHelper.TYPE_BIERE, isChecked)
        }

        // Liqueurs
        btnPlusLiqueurs.setOnClickListener {
            if (categoriesActives[DatabaseHelper.TYPE_LIQUEUR] == true) {
                ajouterConsommation(DatabaseHelper.TYPE_LIQUEUR)
                (activity as? MainActivity)?.refreshHeaderResumeJour()
            }
        }
        btnMoinsLiqueurs.setOnClickListener {
            if (categoriesActives[DatabaseHelper.TYPE_LIQUEUR] == true) {
                retirerConsommation(DatabaseHelper.TYPE_LIQUEUR)
                (activity as? MainActivity)?.refreshHeaderResumeJour()
            }
        }
        checkLiqueurs.setOnCheckedChangeListener { _, isChecked ->
            toggleCategorie(DatabaseHelper.TYPE_LIQUEUR, isChecked)
        }

        // Alcool Fort
        btnPlusAlcoolFort.setOnClickListener {
            if (categoriesActives[DatabaseHelper.TYPE_ALCOOL_FORT] == true) {
                ajouterConsommation(DatabaseHelper.TYPE_ALCOOL_FORT)
                (activity as? MainActivity)?.refreshHeaderResumeJour()
            }
        }
        btnMoinsAlcoolFort.setOnClickListener {
            if (categoriesActives[DatabaseHelper.TYPE_ALCOOL_FORT] == true) {
                retirerConsommation(DatabaseHelper.TYPE_ALCOOL_FORT)
                (activity as? MainActivity)?.refreshHeaderResumeJour()
            }
        }
        checkAlcoolFort.setOnCheckedChangeListener { _, isChecked ->
            toggleCategorie(DatabaseHelper.TYPE_ALCOOL_FORT, isChecked)
        }

        // Bouton version sans pub (accueil)
        btnPremiumAccueil.setOnClickListener { ouvrirVersionPremium() }

        Log.d(TAG, "Listeners configurés avec succès")
    } catch (e: Exception) {
        Log.e(TAG, "Erreur configuration listeners: ${e.message}")
    }
}
    
    private fun ouvrirVersionPremium() {
    val premiumPackage = "com.stopaddict.premium" // TODO: remplacer par le vrai package

    try {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$premiumPackage")))
    } catch (e: ActivityNotFoundException) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$premiumPackage")))
        } catch (e2: Exception) {
            // Plus de popup : juste un log (ou toast si tu préfères)
            Log.e(TAG, "Impossible d'ouvrir le store", e2)
        }
    } catch (e: Exception) {
        Log.e(TAG, "Impossible d'ouvrir le store", e)
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
            planifierMiseAJourConseilAntiSpam()
            
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
                planifierMiseAJourConseilAntiSpam()
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
            planifierMiseAJourConseilAntiSpam()
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
            txtTotalJourLabel.text = trad["total_aujourdhui"] ?: "Total aujourd'hui:"
            txtTotalJour.text = totalJour.toString()

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
        fun applyStyle(btn: View, enabled: Boolean) {
    btn.isEnabled = enabled
    btn.alpha = if (enabled) 1.0f else 0.25f
}

        val cigEnabled = categoriesActives[DatabaseHelper.TYPE_CIGARETTE] ?: true
        applyStyle(btnPlusCigarettes,  cigEnabled)
        applyStyle(btnMoinsCigarettes, cigEnabled)

        val jointEnabled = categoriesActives[DatabaseHelper.TYPE_JOINT] ?: true
        applyStyle(btnPlusJoints,  jointEnabled)
        applyStyle(btnMoinsJoints, jointEnabled)

        val alcoolEnabled = categoriesActives[DatabaseHelper.TYPE_ALCOOL_GLOBAL] ?: true
        applyStyle(btnPlusAlcoolGlobal,  alcoolEnabled)
        applyStyle(btnMoinsAlcoolGlobal, alcoolEnabled)

        val biereEnabled = categoriesActives[DatabaseHelper.TYPE_BIERE] ?: false
        applyStyle(btnPlusBieres,  biereEnabled)
        applyStyle(btnMoinsBieres, biereEnabled)

        val liqueurEnabled = categoriesActives[DatabaseHelper.TYPE_LIQUEUR] ?: false
        applyStyle(btnPlusLiqueurs,  liqueurEnabled)
        applyStyle(btnMoinsLiqueurs, liqueurEnabled)

        val fortEnabled = categoriesActives[DatabaseHelper.TYPE_ALCOOL_FORT] ?: false
        applyStyle(btnPlusAlcoolFort,  fortEnabled)
        applyStyle(btnMoinsAlcoolFort, fortEnabled)

        Log.d(TAG, "Visibilité boutons mise à jour")
    } catch (e: Exception) {
        Log.e(TAG, "Erreur mise à jour visibilité boutons: ${e.message}")
    }
}
        
    private fun updateProfilStatus() {
    try {
        // --- POURCENTAGE (centralisé) ---
        val percent = dbHelper.getProfilCompletionPercent(categoriesActives)

        // --- AFFICHAGE (inchangé) ---
        txtProfilComplet.text =
            if (percent == 100)
                (trad["profil_complet"] ?: "Profil: Complet ✓") + " 100%"
            else
                (trad["profil_incomplet"] ?: "Profil: Incomplet") + " $percent%"

        val iconRes = if (percent == 100)
            R.drawable.ic_status_complete
        else
            R.drawable.ic_status_incomplete

        txtProfilComplet.setCompoundDrawablesWithIntrinsicBounds(iconRes, 0, 0, 0)

        profilProgress.progress = percent
        txtProfilRestant.visibility = View.GONE

    } catch (e: Exception) {
        Log.e(TAG, "Erreur mise à jour profil (progression): ${e.message}")
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

        /**
     * Planifie une mise à jour des conseils après une pause de CONSEIL_ANTISPAM_DELAY
     * si l'utilisateur n'a plus interagi (ajout/retrait/catégorie) pendant ce délai.
     */
    private fun planifierMiseAJourConseilAntiSpam() {
        try {
            // Annuler un éventuel ancien runnable anti-spam
            conseilAntispamRunnable?.let {
                logger.d("planifierMiseAJourConseilAntiSpam: ancien runnable anti-spam trouvé -> removeCallbacks")
                conseilHandler.removeCallbacks(it)
            }

            // Mémoriser le moment de la dernière interaction utilisateur
            lastUserInteractionTime = System.currentTimeMillis()

            // Nouveau runnable qui vérifiera si le délai est bien écoulé avant de mettre à jour le conseil
            conseilAntispamRunnable = Runnable {
                try {
                    val now = System.currentTimeMillis()
                    val elapsed = now - lastUserInteractionTime
                    logger.d("planifierMiseAJourConseilAntiSpam: runnable déclenché (elapsed=${elapsed} ms)")

                    if (elapsed >= CONSEIL_ANTISPAM_DELAY) {
                        logger.d("planifierMiseAJourConseilAntiSpam: délai atteint -> updateConseil()")
                        updateConseil()
                    } else {
                        logger.d("planifierMiseAJourConseilAntiSpam: délai non atteint, aucune mise à jour du conseil")
                    }
                } catch (e: Exception) {
                    logger.e("planifierMiseAJourConseilAntiSpam: erreur dans le runnable anti-spam", e)
                }
            }

            logger.d("planifierMiseAJourConseilAntiSpam: programmation du runnable dans ${CONSEIL_ANTISPAM_DELAY} ms")
            conseilHandler.postDelayed(conseilAntispamRunnable!!, CONSEIL_ANTISPAM_DELAY)
        } catch (e: Exception) {
            logger.e("planifierMiseAJourConseilAntiSpam: erreur lors de la planification anti-spam", e)
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

            txtConseil.text = conseil.texte
            
            val iconRes = when (conseil.categorie) {
                ConseilCategorie.EAU -> R.drawable.ic_conseil_eau
                ConseilCategorie.SPORT -> R.drawable.ic_conseil_sport
                ConseilCategorie.SOCIAL -> R.drawable.ic_conseil_social
                ConseilCategorie.ECONOMIE -> R.drawable.ic_conseil_economie
                ConseilCategorie.HABITUDE -> R.drawable.ic_conseil_habitude
                ConseilCategorie.OBJECTIF -> R.drawable.ic_conseil_objectif
                else -> R.drawable.ic_conseil_generique
            }
            
            imgConseil.setImageResource(iconRes)
            imgConseil.visibility = View.VISIBLE

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
): ConseilItem {

    logger.d("genererConseil: start - hasPrenom=$hasPrenom, hasCouts=$hasCouts, hasHabitudes=$hasHabitudes, hasDates=$hasDates, prenom=\"$prenom\"")

    val conseils = mutableListOf<ConseilItem>()

    // helper local : évite de répéter ConseilItem(...) partout
    fun add(cat: ConseilCategorie, txt: String) {
        conseils.add(ConseilItem(txt, cat))
    }

    when {
        // Cas 1: Rien
        !hasPrenom && !hasCouts && !hasHabitudes && !hasDates -> {
            logger.d("genererConseil: cas 1 = aucun élément renseigné")
            add(ConseilCategorie.GENERIQUE, trad["conseil_cas1_1"] ?: "Bienvenue!")
            add(ConseilCategorie.GENERIQUE, trad["conseil_cas1_2"] ?: "Chaque pas compte")
            add(ConseilCategorie.GENERIQUE, trad["conseil_cas1_3"] ?: "Motivation!")
        }

        // Cas 2: Prénom uniquement
        hasPrenom && !hasCouts && !hasHabitudes && !hasDates -> {
            logger.d("genererConseil: cas 2 = prénom uniquement")
            add(ConseilCategorie.GENERIQUE, "$prenom, " + (trad["conseil_cas1_1"] ?: "Bienvenue! Ajoutez vos coûts pour voir vos économies."))
            add(ConseilCategorie.GENERIQUE, "$prenom, " + (trad["conseil_generique_6"] ?: "restez déterminé dans votre parcours!"))
            add(ConseilCategorie.GENERIQUE, "$prenom, " + (trad["conseil_generique_5"] ?: "chaque jour est une nouvelle opportunité."))
        }

        // Cas 3: Coûts uniquement
        !hasPrenom && hasCouts && !hasHabitudes && !hasDates -> {
            logger.d("genererConseil: cas 3 = coûts uniquement")
            val economies = calculerEconomiesJour()
            logger.d("genererConseil: cas 3 -> économies jour = $economies")
            if (economies > 0) {
                add(ConseilCategorie.ECONOMIE, String.format(trad["economies_jour"] ?: "Vous économisez %.2f€ aujourd'hui!", economies))
                add(ConseilCategorie.ECONOMIE, trad["conseil_cas3_3"] ?: "Économies")
            } else {
                add(ConseilCategorie.ECONOMIE, trad["conseil_cas3_1"] ?: "Économisez")
            }
            add(ConseilCategorie.ECONOMIE, trad["conseil_cas3_2"] ?: "Projets")
        }

        // Cas 4: Habitudes uniquement
        !hasPrenom && !hasCouts && hasHabitudes && !hasDates -> {
            logger.d("genererConseil: cas 4 = habitudes uniquement")
            val comparaison = comparerHabitudes()
            logger.d("genererConseil: cas 4 -> comparaison=\"$comparaison\"")
            add(ConseilCategorie.HABITUDE, comparaison)
            add(ConseilCategorie.HABITUDE, trad["conseil_cas4_1"] ?: "Habitudes")
            add(ConseilCategorie.OBJECTIF, trad["conseil_cas4_2"] ?: "Objectifs")
        }

        // Cas 5: Dates uniquement
        !hasPrenom && !hasCouts && !hasHabitudes && hasDates -> {
            logger.d("genererConseil: cas 5 = dates uniquement")
            val conseilDate = genererConseilDate()
            logger.d("genererConseil: cas 5 -> conseilDate=\"$conseilDate\"")
            add(ConseilCategorie.OBJECTIF, conseilDate)
            add(ConseilCategorie.OBJECTIF, trad["conseil_cas5_1"] ?: "Objectif proche!")
            add(ConseilCategorie.OBJECTIF, trad["conseil_cas5_2"] ?: "Restez concentré sur votre date.")
        }

        // Cas 6: Prénom + Coûts
        hasPrenom && hasCouts && !hasHabitudes && !hasDates -> {
            logger.d("genererConseil: cas 6 = prénom + coûts")
            val economies = calculerEconomiesJour()
            logger.d("genererConseil: cas 6 -> économies jour = $economies")
            if (economies > 0) {
                add(ConseilCategorie.ECONOMIE, "$prenom, " + String.format(trad["economies_jour"] ?: "vous économisez %.2f€ aujourd'hui!", economies))
                add(ConseilCategorie.ECONOMIE, "$prenom, " + (trad["economies_accumulent"] ?: "ces économies s'accumulent rapidement!"))
            } else {
                add(ConseilCategorie.ECONOMIE, "$prenom, " + (trad["conseil_cas3_1"] ?: "réduire maintenant générera des économies."))
            }
        }

        // Cas 7: Prénom + Habitudes
        hasPrenom && !hasCouts && hasHabitudes && !hasDates -> {
            logger.d("genererConseil: cas 7 = prénom + habitudes")
            val comparaison = comparerHabitudes()
            logger.d("genererConseil: cas 7 -> comparaison=\"$comparaison\"")
            add(ConseilCategorie.HABITUDE, "$prenom, $comparaison")
            add(ConseilCategorie.GENERIQUE, "$prenom, " + (trad["conseil_generique_5"] ?: "vous progressez bien!"))
        }

        // Cas 8: Prénom + Dates
        hasPrenom && !hasCouts && !hasHabitudes && hasDates -> {
            logger.d("genererConseil: cas 8 = prénom + dates")
            val conseilDate = genererConseilDate()
            logger.d("genererConseil: cas 8 -> conseilDate=\"$conseilDate\"")
            add(ConseilCategorie.OBJECTIF, "$prenom, $conseilDate")
            add(ConseilCategorie.OBJECTIF, "$prenom, " + (trad["date_rapproche"] ?: "votre objectif approche, tenez bon!"))
        }

        // Cas 9: Coûts + Habitudes
        !hasPrenom && hasCouts && hasHabitudes && !hasDates -> {
            logger.d("genererConseil: cas 9 = coûts + habitudes")
            val economies = calculerEconomiesReelles()
            logger.d("genererConseil: cas 9 -> économies réelles = $economies")
            if (economies > 0) {
                add(ConseilCategorie.ECONOMIE, String.format(trad["economies_reelles"] ?: "Économies réelles: %.2f€ vs vos habitudes!", economies))
                add(ConseilCategorie.GENERIQUE, trad["conseil_generique_5"] ?: "Vous faites mieux que prévu, bravo!")
            } else {
                add(ConseilCategorie.HABITUDE, trad["habitudes_egal"] ?: "Vous consommez selon vos habitudes.")
            }
        }

        // Cas 10: Coûts + Dates
        !hasPrenom && hasCouts && !hasHabitudes && hasDates -> {
            logger.d("genererConseil: cas 10 = coûts + dates")
            val economies = calculerEconomiesJour()
            val conseilDate = genererConseilDate()
            logger.d("genererConseil: cas 10 -> économies=$economies, conseilDate=\"$conseilDate\"")
            add(ConseilCategorie.ECONOMIE, String.format(trad["economies_jour"] ?: "Vous économisez %.2f€ aujourd'hui!", economies) + " - " + conseilDate)
            add(ConseilCategorie.ECONOMIE, trad["economies_accumulent"] ?: "Votre porte-monnaie vous remercie!")
        }

        // Cas 11: Habitudes + Dates
        !hasPrenom && !hasCouts && hasHabitudes && hasDates -> {
            logger.d("genererConseil: cas 11 = habitudes + dates")
            val comparaison = comparerHabitudes()
            val conseilDate = genererConseilDate()
            logger.d("genererConseil: cas 11 -> comparaison=\"$comparaison\", conseilDate=\"$conseilDate\"")
            add(ConseilCategorie.HABITUDE, "$comparaison - $conseilDate")
            add(ConseilCategorie.OBJECTIF, trad["conseil_generique_6"] ?: "Restez fidèle à vos objectifs!")
        }

        // Cas 12: Prénom + Coûts + Habitudes
        hasPrenom && hasCouts && hasHabitudes && !hasDates -> {
            logger.d("genererConseil: cas 12 = prénom + coûts + habitudes")
            val economies = calculerEconomiesReelles()
            logger.d("genererConseil: cas 12 -> économies réelles = $economies")
            if (economies > 0) {
                add(
                    ConseilCategorie.ECONOMIE,
                    "$prenom, " + String.format(trad["economies_reelles"] ?: "économies réelles: %.2f€ vs habitudes!", economies)
                )
                add(ConseilCategorie.GENERIQUE, "$prenom, " + (trad["conseil_cas5_1"] ?: "vous êtes sur la bonne voie!"))
            } else {
                add(ConseilCategorie.GENERIQUE, "$prenom, " + (trad["conseil_generique_6"] ?: "maintenez vos efforts!"))
            }
        }

        // Cas 13: Prénom + Coûts + Dates
        hasPrenom && hasCouts && !hasHabitudes && hasDates -> {
            logger.d("genererConseil: cas 13 = prénom + coûts + dates")
            val economies = calculerEconomiesJour()
            val conseilDate = genererConseilDate()
            logger.d("genererConseil: cas 13 -> économies=$economies, conseilDate=\"$conseilDate\"")
            add(
                ConseilCategorie.ECONOMIE,
                "$prenom, " + String.format(trad["economies_jour"] ?: "vous économisez %.2f€ aujourd'hui!", economies) + " - " + conseilDate
            )
            add(ConseilCategorie.OBJECTIF, "$prenom, " + (trad["date_rapproche"] ?: "votre objectif approche, tenez bon!"))
        }

        // Cas 14: Prénom + Habitudes + Dates
        hasPrenom && !hasCouts && hasHabitudes && hasDates -> {
            logger.d("genererConseil: cas 14 = prénom + habitudes + dates")
            val comparaison = comparerHabitudes()
            val conseilDate = genererConseilDate()
            logger.d("genererConseil: cas 14 -> comparaison=\"$comparaison\", conseilDate=\"$conseilDate\"")
            add(ConseilCategorie.HABITUDE, "$prenom, $comparaison - $conseilDate")
            add(ConseilCategorie.GENERIQUE, "$prenom, " + (trad["conseil_generique_6"] ?: "continuez ainsi!"))
        }

        // Cas 15: Coûts + Habitudes + Dates
        !hasPrenom && hasCouts && hasHabitudes && hasDates -> {
            logger.d("genererConseil: cas 15 = coûts + habitudes + dates")
            val economies = calculerEconomiesReelles()
            val conseilDate = genererConseilDate()
            logger.d("genererConseil: cas 15 -> économies=$economies, conseilDate=\"$conseilDate\"")
            add(
                ConseilCategorie.ECONOMIE,
                String.format(trad["economies_reelles"] ?: "Économies réelles: %.2f€ vs vos habitudes!", economies) + " - " + conseilDate
            )
            add(ConseilCategorie.GENERIQUE, trad["conseil_generique_5"] ?: "Vous progressez sur tous les fronts!")
        }

        // Cas 16: COMPLET (Prénom + Coûts + Habitudes + Dates)
        hasPrenom && hasCouts && hasHabitudes && hasDates -> {
            logger.d("genererConseil: cas 16 = COMPLET (prénom + coûts + habitudes + dates)")
            val economies = calculerEconomiesReelles()
            val conseilDate = genererConseilDate()
            logger.d("genererConseil: cas 16 -> économies=$economies, conseilDate=\"$conseilDate\"")
            add(
                ConseilCategorie.ECONOMIE,
                "$prenom, " + String.format(trad["economies_reelles"] ?: "économies réelles: %.2f€ vs vos habitudes!", economies) + " - " + conseilDate
            )
            add(ConseilCategorie.GENERIQUE, "$prenom, " + (trad["conseil_generique_6"] ?: "profil complet, continuez ainsi!"))
            add(ConseilCategorie.GENERIQUE, "$prenom, " + (trad["conseil_generique_6"] ?: "vous êtes un exemple de motivation!"))
        }
    }

    // Conseils génériques (toujours)
    add(ConseilCategorie.SANTE, trad["conseil_generique_1"] ?: "Chaque cigarette non fumée ajoute 11 minutes à votre vie.")
    add(ConseilCategorie.SPORT, trad["conseil_generique_2"] ?: "L'exercice physique aide à réduire l'envie.")
    add(ConseilCategorie.EAU, trad["conseil_generique_3"] ?: "Boire de l'eau réduit les sensations de manque.")
    add(ConseilCategorie.SOCIAL, trad["conseil_generique_4"] ?: "Entourez-vous de personnes soutenantes.")

    val conseilFinal = conseils.random()
    logger.d("genererConseil: fin - ${conseils.size} candidats, conseil choisi = \"${conseilFinal.texte}\"")

    return conseilFinal
}

            /**
     * Calcul du prix unitaire par type de consommation.
     * Identique à StatsFragment pour garder la cohérence profonde.
     */
    private fun calculerPrixUnitaire(type: String, couts: Map<String, Double>): Double {
        return try {
            when (type) {
                DatabaseHelper.TYPE_CIGARETTE -> {
                    // On adapte le calcul en fonction du mode choisi dans les réglages
                    val modeCig = dbHelper.getPreference(PREF_MODE_CIGARETTE, "classique")

                    when (modeCig) {
                        "rouler" -> {
                            val prixTabac = couts["prix_tabac"] ?: 0.0
                            val prixFeuilles = couts["prix_feuilles"] ?: 0.0
                            val nbFeuilles = couts["nb_feuilles"] ?: 0.0
                            val prixFiltres = couts["prix_filtres"] ?: 0.0
                            val nbFiltres = couts["nb_filtres"] ?: 0.0

                            // Nombre de cigarettes réellement fabriquées
                            val nbCigRouleesStr =
                                dbHelper.getPreference(PREF_NB_CIGARETTES_ROULEES, "0")
                            val nbCigRoulees =
                                nbCigRouleesStr.replace(",", ".").toDoubleOrNull() ?: 0.0

                            val coutTotal = prixTabac + prixFeuilles + prixFiltres
                            if (coutTotal > 0 && nbCigRoulees > 0) {
                                coutTotal / nbCigRoulees
                            } else {
                                // Fallback : ancien calcul
                                val nbCigarettes = couts["nb_cigarettes"] ?: 0.0
                                val coutTabac =
                                    if (prixTabac > 0 && nbCigarettes > 0) prixTabac / nbCigarettes else 0.0
                                val coutFeuilles =
                                    if (prixFeuilles > 0 && nbFeuilles > 0) prixFeuilles / nbFeuilles else 0.0
                                val coutFiltres =
                                    if (prixFiltres > 0 && nbFiltres > 0) prixFiltres / nbFiltres else 0.0

                                val total = coutTabac + coutFeuilles + coutFiltres
                                if (total > 0) total else 0.0
                            }
                        }

                        "tuber" -> {
                            val prixTabacTubes = couts["prix_tabac_tube"] ?: 0.0
                            val prixTubes = couts["prix_tubes"] ?: 0.0
                            val nbTubes = couts["nb_tubes"] ?: 0.0

                            val nbCigTubeesStr =
                                dbHelper.getPreference(PREF_NB_CIGARETTES_TUBEES, "0")
                            val nbCigTubees =
                                nbCigTubeesStr.replace(",", ".").toDoubleOrNull() ?: 0.0

                            val coutTotal = prixTabacTubes + prixTubes
                            if (coutTotal > 0 && nbCigTubees > 0) {
                                coutTotal / nbCigTubees
                            } else {
                                // Fallback : ancien calcul
                                val nbCigarettes = couts["nb_cigarettes"] ?: 0.0
                                val coutTabac =
                                    if (prixTabacTubes > 0 && nbCigarettes > 0) prixTabacTubes / nbCigarettes else 0.0
                                val coutTubes =
                                    if (prixTubes > 0 && nbTubes > 0) prixTubes / nbTubes else 0.0

                                val total = coutTabac + coutTubes
                                if (total > 0) total else 0.0
                            }
                        }

                        else -> {
                            // Mode "classique" (paquet standard)
                            val prixPaquet = couts["prix_paquet"] ?: 0.0
                            val nbCigarettes = couts["nb_cigarettes"] ?: 0.0
                            if (prixPaquet > 0 && nbCigarettes > 0) prixPaquet / nbCigarettes else 0.0
                        }
                    }
                }

                DatabaseHelper.TYPE_JOINT -> {
                    val prixGramme = couts["prix_gramme"] ?: 0.0
                    val grammeParJointStr = dbHelper.getPreference("gramme_par_joint", "0")
                    val grammeParJoint =
                        grammeParJointStr.replace(",", ".").toDoubleOrNull() ?: 0.0

                    val prixFeuillesJoint = couts["prix_feuilles"] ?: 0.0
                    val nbFeuillesJoint = couts["nb_feuilles"] ?: 0.0
                    val coutFeuilleParJoint =
                        if (prixFeuillesJoint > 0 && nbFeuillesJoint > 0) prixFeuillesJoint / nbFeuillesJoint else 0.0

                    if (prixGramme <= 0 || grammeParJoint <= 0) {
                        0.0
                    } else {
                        (prixGramme * grammeParJoint) + coutFeuilleParJoint
                    }
                }

                DatabaseHelper.TYPE_ALCOOL_GLOBAL,
                DatabaseHelper.TYPE_BIERE,
                DatabaseHelper.TYPE_LIQUEUR,
                DatabaseHelper.TYPE_ALCOOL_FORT -> {
                    couts["prix_verre"] ?: 0.0
                }

                else -> 0.0
            }
        } catch (e: Exception) {
            Log.e(TAG, "Erreur calcul prix unitaire $type: ${e.message}")
            0.0
        }
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

                                // Utilise la même logique que StatsFragment pour le prix unitaire
                val prixUnitaire = calculerPrixUnitaire(type, couts)
                if (prixUnitaire > 0.0 && consommation > 0) {
                    economies += prixUnitaire * consommation
                }
            }
        }
    } catch (e: Exception) {
        Log.e(TAG, "Erreur calcul économies jour: ${e.message}", e)
    }
    // On renvoie un Double arrondi à 2 décimales, sans passer par une String
    val arrondi = Math.round(economies * 100.0) / 100.0
    Log.d(TAG, "calculerEconomiesJour -> economies=$economies, arrondi=$arrondi")
    return arrondi
}

 private fun calculerEconomiesReelles(): Double {
    var economies = 0.0
    try {
        categoriesActives.forEach { (type, active) ->
            if (active) {
                val maxHabitude = dbHelper.getMaxJournalier(type) // Double (dans ton DBHelper)
                if (maxHabitude > 0) {
                    val couts = dbHelper.getCouts(type)
                    val prixUnitaire = calculerPrixUnitaire(type, couts)

                    val consommationInt = when (type) {
                        DatabaseHelper.TYPE_CIGARETTE -> cigarettesCount
                        DatabaseHelper.TYPE_JOINT -> jointsCount
                        DatabaseHelper.TYPE_ALCOOL_GLOBAL -> alcoolGlobalCount
                        DatabaseHelper.TYPE_BIERE -> bieresCount
                        DatabaseHelper.TYPE_LIQUEUR -> liqueursCount
                        DatabaseHelper.TYPE_ALCOOL_FORT -> alcoolFortCount
                        else -> 0
                    }

                    val consoD = consommationInt.toDouble()

                    // Economies "réelles" = différence vs habitudes (peut être négatif si tu dépasses)
                    if (prixUnitaire > 0.0) {
                        economies += (maxHabitude - consoD) * prixUnitaire
                    }
                }
            }
        }
    } catch (e: Exception) {
        Log.e(TAG, "Erreur calcul économies réelles: ${e.message}", e)
    }

    val arrondi = Math.round(economies * 100.0) / 100.0
    Log.d(TAG, "calculerEconomiesReelles -> economies=$economies, arrondi=$arrondi")
    return arrondi
}

    private fun comparerHabitudes(): String {
    return try {
        categoriesActives.forEach { (type, active) ->
            if (active) {
                val maxHabitude = dbHelper.getMaxJournalier(type) // Double
                if (maxHabitude > 0) {

                    val consommationInt = when (type) {
                        DatabaseHelper.TYPE_CIGARETTE -> cigarettesCount
                        DatabaseHelper.TYPE_JOINT -> jointsCount
                        DatabaseHelper.TYPE_ALCOOL_GLOBAL -> alcoolGlobalCount
                        DatabaseHelper.TYPE_BIERE -> bieresCount
                        DatabaseHelper.TYPE_LIQUEUR -> liqueursCount
                        DatabaseHelper.TYPE_ALCOOL_FORT -> alcoolFortCount
                        else -> 0
                    }

                    val consoD = consommationInt.toDouble()

                    return when {
                        consoD < maxHabitude ->
                            trad["habitudes_moins"] ?: "Vous consommez moins que d'habitude, bravo!"

                        kotlin.math.abs(consoD - maxHabitude) < 0.000001 ->
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

                    // Vérifier date d'arrêt (clé alignée avec DatabaseHelper : date_arret)
                    dates["date_arret"]?.let { dateStr ->
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
                                            "Plus que $diffDays jours avant votre date d'arrêt !"
                                        }
                                    }
                                    diffDays == 0 -> {
                                        trad["date_aujourdhui"]
                                            ?: "C'est aujourd'hui votre date d'arrêt, courage !"
                                    }
                                    else -> {
                                        trad["date_depassee"]
                                            ?: "Vous avez dépassé votre date d'arrêt, continuez !"
                                    }
                                }
                            }
                        }
                    }

                    // Vérifier date de réduction (clé alignée avec DatabaseHelper : date_reduction)
                    dates["date_reduction"]?.let { dateStr ->
                        if (dateStr.isNotEmpty()) {
                            val dateReduction = dateFormat.parse(dateStr)
                            if (dateReduction != null) {
                                val diffDays = ((dateReduction.time - today.time) / (1000 * 60 * 60 * 24)).toInt()
                                if (diffDays >= 0) {
                                    val pattern = trad["date_reduction"]
                                    return if (pattern != null) {
                                        String.format(pattern, diffDays)
                                    } else {
                                        "Date de réduction : dans $diffDays jours !"
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Si aucune date exploitable trouvée
            trad["date_rapproche"] ?: "Votre objectif se rapproche !"
        } catch (e: Exception) {
            Log.e(TAG, "Erreur génération conseil date: ${e.message}")
            trad["date_rapproche"] ?: "Votre objectif se rapproche !"
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

        // Arrêter également le runnable anti-spam si présent
        conseilAntispamRunnable?.let { conseilHandler.removeCallbacks(it) }
        conseilAntispamRunnable = null

        Log.d(TAG, "Fragment détruit - rotation conseils et anti-spam arrêtés")
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
}
