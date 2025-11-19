package com.stopaddict

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.text.SimpleDateFormat
import java.util.*

/**
 * MainActivity.kt - Partie 1/2
 * Point d'entrée de l'application Stop Addict
 * Gère 5 onglets : Accueil, Stats, Calendrier, Habitudes & Volonté, Réglages
 * 
 * PARTIE 1 : Configuration, UI, avertissement majorité, TabLayout
 */
class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
        private const val PREFS_NAME = "StopAddict"
        private const val PREF_WARNING_SHOWN = "warning_majorite_shown"
        private const val PREF_AGE_ACCEPTED = "age_18_accepted"
        
        // URLs ressources utiles
        private const val URL_RESSOURCES_FR = "https://www.drogues-info-service.fr"
        private const val URL_RESSOURCES_EN = "https://www.samhsa.gov/find-help/national-helpline"
    }

    // UI Elements
    private lateinit var headerTextView: TextView
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var adContainer: FrameLayout
    
    // Managers
    private lateinit var configLangue: ConfigLangue
    private lateinit var pubManager: PubManager
    private lateinit var dbHelper: DatabaseHelper
    
    // Console debug
    private var consoleClickCount = 0
    private var lastConsoleClickTime = 0L
    private var consoleVisible = false
    private var consoleDialog: AlertDialog? = null
    
    // Version gratuite/payante
    private val isVersionGratuite = true // TODO: Gérer via BuildConfig ou variable
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        Log.d(TAG, "════════════════════════════════════════")
        Log.d(TAG, "MainActivity onCreate - Démarrage app")
        Log.d(TAG, "════════════════════════════════════════")
        
        try {
            // ÉTAPE 1 : Initialiser la langue AVANT setContentView
            Log.d(TAG, "ÉTAPE 1/7 : Initialisation langue")
            configLangue = ConfigLangue(this)
            configLangue.initialiserLangue()
            Log.d(TAG, "✓ Langue initialisée: ${configLangue.getLangue()}")
            
            // ÉTAPE 2 : Charger le layout
            Log.d(TAG, "ÉTAPE 2/7 : Chargement layout")
            setContentView(R.layout.activity_main)
            Log.d(TAG, "✓ Layout chargé")
            
            // ÉTAPE 3 : Initialiser les managers
            Log.d(TAG, "ÉTAPE 3/7 : Initialisation managers")
            dbHelper = DatabaseHelper(this)
            Log.d(TAG, "✓ DatabaseHelper initialisé")
            
            // ÉTAPE 4 : Initialiser les vues
            Log.d(TAG, "ÉTAPE 4/7 : Initialisation vues")
            initializeViews()
            Log.d(TAG, "✓ Vues initialisées")
            
            // ÉTAPE 5 : Configurer le header avec date/heure
            Log.d(TAG, "ÉTAPE 5/7 : Configuration header")
            setupHeader()
            Log.d(TAG, "✓ Header configuré")
            
            // ÉTAPE 6 : Vérifier avertissement majorité
            Log.d(TAG, "ÉTAPE 6/7 : Vérification avertissement majorité")
            if (!checkAgeWarningStatus()) {
                Log.d(TAG, "→ Affichage avertissement majorité requis")
                showAgeWarningDialog()
            } else {
                Log.d(TAG, "✓ Avertissement majorité déjà accepté")
                // ÉTAPE 7 : Initialiser les onglets et la pub
                initializeMainContent()
            }
            
        } catch (e: Exception) {
            Log.e(TAG, "ERREUR CRITIQUE lors du onCreate", e)
            Toast.makeText(this, "Erreur lors du démarrage: ${e.message}", Toast.LENGTH_LONG).show()
            finish()
        }
    }
    
    /**
     * Initialise les vues principales
     */
    private fun initializeViews() {
        try {
            headerTextView = findViewById(R.id.header_app_title)
            tabLayout = findViewById(R.id.tab_layout)
            viewPager = findViewById(R.id.view_pager)
            adContainer = findViewById(R.id.ad_container)
            
            Log.d(TAG, "Vues trouvées: header, tabLayout, viewPager, adContainer")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur initialisation vues", e)
            throw e
        }
    }
    
    /**
     * Configure le header : nom app + date/heure + console debug
     */
    private fun setupHeader() {
        try {
            // Mettre à jour date/heure
            updateDateTime()
            
            // Configurer le listener pour console debug (5 clics)
            headerTextView.setOnClickListener {
                handleConsoleDebugClick()
            }
            
            Log.d(TAG, "Header configuré avec console debug")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur configuration header", e)
        }
    }
    
    /**
     * Met à jour la date et l'heure dans le header
     */
    private fun updateDateTime() {
        try {
            val locale = Locale.getDefault()
            val dateFormat = SimpleDateFormat("EEEE dd MMMM yyyy - HH:mm", locale)
            val currentDateTime = dateFormat.format(Date())
            
            headerTextView.text = "Stop Addict\n$currentDateTime"
            
            Log.d(TAG, "DateTime mis à jour: $currentDateTime")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur mise à jour date/heure", e)
            headerTextView.text = "Stop Addict"
        }
    }
    
    /**
     * Vérifie si l'avertissement majorité a déjà été affiché et accepté
     */
    private fun checkAgeWarningStatus(): Boolean {
        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val warningShown = prefs.getBoolean(PREF_WARNING_SHOWN, false)
        val ageAccepted = prefs.getBoolean(PREF_AGE_ACCEPTED, false)
        
        Log.d(TAG, "Status avertissement - Affiché: $warningShown, Accepté: $ageAccepted")
        return warningShown && ageAccepted
    }
    
    /**
     * Affiche le dialog d'avertissement majorité
     * Contenu : Case 18+, Lien ressources, Case "ne plus afficher"
     */
    private fun showAgeWarningDialog() {
        try {
            Log.d(TAG, "Création dialog avertissement majorité")
            
            val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_age_warning, null)
            
            // Récupérer les vues du dialog
            val checkboxAge = dialogView.findViewById<CheckBox>(R.id.checkbox_age_18)
            val checkboxNoShow = dialogView.findViewById<CheckBox>(R.id.checkbox_no_show)
            val btnRessources = dialogView.findViewById<Button>(R.id.btn_ressources_utiles)
            val btnQuitter = dialogView.findViewById<Button>(R.id.btn_quitter)
            val btnAccepter = dialogView.findViewById<Button>(R.id.btn_accepter)
            
            // Créer le dialog
            val dialog = AlertDialog.Builder(this)
                .setView(dialogView)
                .setCancelable(false)
                .create()
            
            // Bouton Ressources utiles
            btnRessources.setOnClickListener {
                Log.d(TAG, "Clic sur Ressources utiles")
                val url = if (configLangue.getLangue() == "FR") URL_RESSOURCES_FR else URL_RESSOURCES_EN
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                } catch (e: Exception) {
                    Log.e(TAG, "Erreur ouverture URL ressources", e)
                    Toast.makeText(this, "Impossible d'ouvrir le lien", Toast.LENGTH_SHORT).show()
                }
            }
            
            // Bouton Quitter
            btnQuitter.setOnClickListener {
                Log.d(TAG, "Clic sur Quitter - Fermeture app")
                finish()
            }
            
            // Bouton Accepter
            btnAccepter.setOnClickListener {
                if (!checkboxAge.isChecked) {
                    Log.w(TAG, "Tentative acceptation sans cocher case 18+")
                    Toast.makeText(
                        this,
                        "Vous devez confirmer avoir 18 ans ou plus",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                
                Log.d(TAG, "Acceptation avertissement majorité")
                
                // Sauvegarder les préférences
                val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                prefs.edit().apply {
                    putBoolean(PREF_AGE_ACCEPTED, true)
                    if (checkboxNoShow.isChecked) {
                        putBoolean(PREF_WARNING_SHOWN, true)
                        Log.d(TAG, "Option 'ne plus afficher' activée")
                    }
                    apply()
                }
                
                dialog.dismiss()
                
                // ÉTAPE 7 : Initialiser le contenu principal
                Log.d(TAG, "ÉTAPE 7/7 : Initialisation contenu principal")
                initializeMainContent()
            }
            
            dialog.show()
            Log.d(TAG, "Dialog avertissement majorité affiché")
            
        } catch (e: Exception) {
            Log.e(TAG, "ERREUR CRITIQUE lors de l'affichage du dialog majorité", e)
            // En cas d'erreur, on permet quand même l'accès
            initializeMainContent()
        }
    }
    
    /**
     * Initialise le contenu principal après acceptation de l'avertissement
     * Configure TabLayout + ViewPager2 + Pub AdMob
     */
    private fun initializeMainContent() {
        try {
            Log.d(TAG, "Initialisation contenu principal")
            
            // Configurer TabLayout + ViewPager2
            setupTabLayoutAndViewPager()
            
            // Initialiser la publicité si version gratuite
            if (isVersionGratuite) {
                Log.d(TAG, "Version gratuite détectée - Chargement pub AdMob")
                initializeAdMob()
            } else {
                Log.d(TAG, "Version payante détectée - Masquage bandeau pub")
                adContainer.visibility = View.GONE
            }
            
            Log.d(TAG, "✓ Contenu principal initialisé avec succès")
            Log.d(TAG, "════════════════════════════════════════")
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur initialisation contenu principal", e)
            Toast.makeText(this, "Erreur d'initialisation: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
    
    // ════════════════════════════════════════
    // FIN PARTIE 1
    // Continuera dans Partie 2 avec :
    // - setupTabLayoutAndViewPager()
    // - initializeAdMob()
    // - handleConsoleDebugClick()
    // - showConsoleDebugDialog()
    // - refreshData()
    // - Cycle de vie (onResume, onPause, onDestroy)
    // ════════════════════════════════════════
}
// ══════════════════════════════════════════════════════════
    // PARTIE 2/2 - SUITE DE MainActivity.kt
    // TabLayout, ViewPager2, Console Debug, Pub, Synchronisation
    // ══════════════════════════════════════════════════════════

    /**
     * Configure TabLayout avec ViewPager2 pour les 5 onglets
     * Accueil - Stats - Calendrier - Habitudes & Volonté - Réglages
     */
    private fun setupTabLayoutAndViewPager() {
        try {
            Log.d(TAG, "Configuration TabLayout + ViewPager2")
            
            // Créer l'adapter pour ViewPager2
            val fragmentAdapter = FragmentAdapter(this)
            viewPager.adapter = fragmentAdapter
            
            // Configurer le comportement du ViewPager
            viewPager.isUserInputEnabled = true
            viewPager.offscreenPageLimit = 2 // Garder 2 onglets en mémoire de chaque côté
            
            // Lier TabLayout et ViewPager2
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                // Définir les icônes et titres selon l'onglet
                when (position) {
                    0 -> {
                        tab.text = getTabTitle(0)
                        tab.setIcon(R.drawable.ic_home)
                        Log.d(TAG, "Tab 0 configuré: Accueil")
                    }
                    1 -> {
                        tab.text = getTabTitle(1)
                        tab.setIcon(R.drawable.ic_stats)
                        Log.d(TAG, "Tab 1 configuré: Stats")
                    }
                    2 -> {
                        tab.text = getTabTitle(2)
                        tab.setIcon(R.drawable.ic_calendar)
                        Log.d(TAG, "Tab 2 configuré: Calendrier")
                    }
                    3 -> {
                        tab.text = getTabTitle(3)
                        tab.setIcon(R.drawable.ic_habits)
                        Log.d(TAG, "Tab 3 configuré: Habitudes & Volonté")
                    }
                    4 -> {
                        tab.text = getTabTitle(4)
                        tab.setIcon(R.drawable.ic_settings)
                        Log.d(TAG, "Tab 4 configuré: Réglages")
                    }
                }
            }.attach()
            
            // Listener pour changement d'onglet
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    Log.d(TAG, "Changement onglet: position $position")
                    
                    // Mettre à jour date/heure à chaque changement d'onglet
                    updateDateTime()
                    
                    // Synchroniser les données si nécessaire
                    when (position) {
                        0 -> refreshAccueilData()
                        1 -> refreshStatsData()
                        2 -> refreshCalendrierData()
                        3 -> refreshHabitudesData()
                        4 -> refreshReglagesData()
                    }
                }
            })
            
            Log.d(TAG, "✓ TabLayout + ViewPager2 configurés avec succès")
            
        } catch (e: Exception) {
            Log.e(TAG, "ERREUR configuration TabLayout/ViewPager2", e)
            throw e
        }
    }
    
    /**
     * Retourne le titre de l'onglet selon la langue
     */
    private fun getTabTitle(position: Int): String {
        return when (position) {
            0 -> when (configLangue.getLangue()) {
                "FR" -> "Accueil"
                "ES" -> "Inicio"
                "PT" -> "Início"
                "DE" -> "Startseite"
                "IT" -> "Home"
                "RU" -> "Главная"
                "AR" -> "الرئيسية"
                "HI" -> "होम"
                "JA" -> "ホーム"
                else -> "Home"
            }
            1 -> when (configLangue.getLangue()) {
                "FR" -> "Stats"
                "ES" -> "Estadísticas"
                "PT" -> "Estatísticas"
                "DE" -> "Statistiken"
                "IT" -> "Statistiche"
                "RU" -> "Статистика"
                "AR" -> "إحصائيات"
                "HI" -> "आंकड़े"
                "JA" -> "統計"
                else -> "Stats"
            }
            2 -> when (configLangue.getLangue()) {
                "FR" -> "Calendrier"
                "ES" -> "Calendario"
                "PT" -> "Calendário"
                "DE" -> "Kalender"
                "IT" -> "Calendario"
                "RU" -> "Календарь"
                "AR" -> "التقويم"
                "HI" -> "कैलेंडर"
                "JA" -> "カレンダー"
                else -> "Calendar"
            }
            3 -> when (configLangue.getLangue()) {
                "FR" -> "Habitudes"
                "ES" -> "Hábitos"
                "PT" -> "Hábitos"
                "DE" -> "Gewohnheiten"
                "IT" -> "Abitudini"
                "RU" -> "Привычки"
                "AR" -> "العادات"
                "HI" -> "आदतें"
                "JA" -> "習慣"
                else -> "Habits"
            }
            4 -> when (configLangue.getLangue()) {
                "FR" -> "Réglages"
                "ES" -> "Ajustes"
                "PT" -> "Configurações"
                "DE" -> "Einstellungen"
                "IT" -> "Impostazioni"
                "RU" -> "Настройки"
                "AR" -> "الإعدادات"
                "HI" -> "सेटिंग्स"
                "JA" -> "設定"
                else -> "Settings"
            }
            else -> ""
        }
    }
    
    /**
     * Initialise AdMob pour la version gratuite
     */
    private fun initializeAdMob() {
        try {
            Log.d(TAG, "Initialisation AdMob")
            
            pubManager = PubManager(this)
            pubManager.chargerBandeau(adContainer)
            
            // S'assurer que le conteneur est visible
            adContainer.visibility = View.VISIBLE
            
            Log.d(TAG, "✓ AdMob initialisé et bandeau chargé")
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur initialisation AdMob", e)
            // En cas d'erreur, masquer le conteneur
            adContainer.visibility = View.GONE
        }
    }
    
    /**
     * Gère les clics pour afficher la console debug (5 clics rapides)
     */
    private fun handleConsoleDebugClick() {
        val currentTime = System.currentTimeMillis()
        
        // Réinitialiser si plus de 2 secondes entre les clics
        if (currentTime - lastConsoleClickTime > 2000) {
            consoleClickCount = 1
            Log.d(TAG, "Console debug: premier clic")
        } else {
            consoleClickCount++
            Log.d(TAG, "Console debug: clic $consoleClickCount/5")
        }
        
        lastConsoleClickTime = currentTime
        
        // Afficher/masquer console après 5 clics
        if (consoleClickCount >= 5) {
            Log.d(TAG, "Console debug: activation!")
            consoleClickCount = 0
            
            if (consoleVisible) {
                // Masquer la console
                consoleDialog?.dismiss()
                consoleVisible = false
                Log.d(TAG, "Console debug masquée")
            } else {
                // Afficher la console
                showConsoleDebugDialog()
                consoleVisible = true
                Log.d(TAG, "Console debug affichée")
            }
        }
    }
    
    /**
     * Affiche le dialog de console debug (popup superposée)
     */
    private fun showConsoleDebugDialog() {
        try {
            Log.d(TAG, "Création console debug popup")
            
            val scrollView = ScrollView(this)
            val textView = TextView(this).apply {
                setPadding(20, 20, 20, 20)
                textSize = 12f
                setTextIsSelectable(true) // Permet de sélectionner et copier
                
                // Récupérer les logs
                val logs = StringBuilder()
                logs.append("═══════ CONSOLE DEBUG STOP ADDICT ═══════\n\n")
                logs.append("📱 Version: ${if (isVersionGratuite) "Gratuite (avec pub)" else "Payante"}\n")
                logs.append("🌍 Langue: ${configLangue.getLangue()}\n")
                logs.append("📅 Date/Heure: ${SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Date())}\n")
                logs.append("\n─────── STATISTIQUES BASE DE DONNÉES ───────\n")
                
                try {
                    // Stats du jour
                    val stats = dbHelper.getStatistiquesJour(Date())
                    logs.append("Aujourd'hui:\n")
                    logs.append("• Cigarettes: ${stats["cigarettes"] ?: 0}\n")
                    logs.append("• Joints: ${stats["joints"] ?: 0}\n")
                    logs.append("• Alcool: ${stats["alcool_global"] ?: 0} cl\n")
                    logs.append("• Bières: ${stats["bieres"] ?: 0}\n")
                    logs.append("• Liqueurs: ${stats["liqueurs"] ?: 0}\n")
                    logs.append("• Alcool fort: ${stats["alcool_fort"] ?: 0}\n")
                    
                    // Nombre total d'entrées
                    val totalEntrees = dbHelper.getHistoriqueConsommations(30).size
                    logs.append("\n📊 Entrées totales (30j): $totalEntrees\n")
                    
                } catch (e: Exception) {
                    logs.append("❌ Erreur lecture BDD: ${e.message}\n")
                }
                
                logs.append("\n─────── LOGS SYSTÈME ───────\n")
                logs.append("• Mémoire utilisée: ${Runtime.getRuntime().totalMemory() / 1024 / 1024} MB\n")
                logs.append("• Mémoire libre: ${Runtime.getRuntime().freeMemory() / 1024 / 1024} MB\n")
                
                logs.append("\n─────── CONFIGURATION ───────\n")
                logs.append("• Onglet actif: ${viewPager.currentItem}\n")
                logs.append("• Pub chargée: ${if (isVersionGratuite) "Oui" else "Non applicable"}\n")
                
                logs.append("\n═════════════════════════════════════════\n")
                logs.append("💡 Cliquez 5 fois pour fermer\n")
                
                text = logs.toString()
            }
            
            scrollView.addView(textView)
            
            // Créer le dialog popup
            consoleDialog = AlertDialog.Builder(this)
                .setTitle("Console Debug")
                .setView(scrollView)
                .setPositiveButton("Fermer") { dialog, _ ->
                    dialog.dismiss()
                    consoleVisible = false
                }
                .create()
            
            consoleDialog?.show()
            
            Log.d(TAG, "✓ Console debug affichée avec succès")
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur affichage console debug", e)
            Toast.makeText(this, "Erreur console: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
    
    /**
     * Méthodes de synchronisation des données par onglet
     */
    private fun refreshAccueilData() {
        Log.d(TAG, "Refresh données Accueil")
        // Le fragment Accueil se met à jour lui-même
    }
    
    private fun refreshStatsData() {
        Log.d(TAG, "Refresh données Stats")
        // Le fragment Stats se met à jour lui-même
    }
    
    private fun refreshCalendrierData() {
        Log.d(TAG, "Refresh données Calendrier")
        // Le fragment Calendrier se met à jour lui-même
    }
    
    private fun refreshHabitudesData() {
        Log.d(TAG, "Refresh données Habitudes")
        // Le fragment Habitudes se met à jour lui-même
    }
    
    private fun refreshReglagesData() {
        Log.d(TAG, "Refresh données Réglages")
        // Le fragment Réglages se met à jour lui-même
    }
    
    /**
     * Méthode publique pour forcer la synchronisation depuis les fragments
     */
    fun refreshData() {
        Log.d(TAG, "Synchronisation globale demandée")
        updateDateTime()
        
        // Rafraîchir l'onglet actif
        when (viewPager.currentItem) {
            0 -> refreshAccueilData()
            1 -> refreshStatsData()
            2 -> refreshCalendrierData()
            3 -> refreshHabitudesData()
            4 -> refreshReglagesData()
        }
    }
    
    // ══════════════════════════════════════════════════════════
    // CYCLE DE VIE
    // ══════════════════════════════════════════════════════════
    
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume - Mise à jour date/heure")
        updateDateTime()
        
        // Reprendre la pub si nécessaire
        if (isVersionGratuite && ::pubManager.isInitialized) {
            pubManager.onResume()
        }
    }
    
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
        
        // Mettre en pause la pub
        if (isVersionGratuite && ::pubManager.isInitialized) {
            pubManager.onPause()
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy - Nettoyage ressources")
        
        // Fermer la console si ouverte
        consoleDialog?.dismiss()
        
        // Détruire la pub
        if (isVersionGratuite && ::pubManager.isInitialized) {
            pubManager.onDestroy()
        }
        
        // Fermer la base de données
        if (::dbHelper.isInitialized) {
            dbHelper.close()
        }
        
        Log.d(TAG, "✓ Ressources nettoyées")
    }
}

// FIN MainActivity.kt - Version complète ~500 lignes
