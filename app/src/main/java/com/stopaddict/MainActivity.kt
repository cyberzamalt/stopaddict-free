package com.stopaddict

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.text.SimpleDateFormat
import java.util.*
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds


class MainActivity : AppCompatActivity() {

    companion object {
        private const val PREFS_NAME = "StopAddict"
        private const val PREF_WARNING_SHOWN = "warning_majorite_shown"
        private const val PREF_AGE_ACCEPTED = "age_18_accepted"
    }

    private val logger = AppLogger("MainActivity")

    private lateinit var headerTextView: TextView
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var adContainer: FrameLayout
    private lateinit var adView: AdView
    private lateinit var configLangue: ConfigLangue
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var trad: Map<String, String>

        // Bandeau résumé jour sous le header principal
    private lateinit var headerResumeScroll: HorizontalScrollView
    private lateinit var headerResumeContainer: LinearLayout
    private lateinit var headerResumeLabel: TextView
    private lateinit var headerResumeCigarette: TextView
    private lateinit var headerResumeJoint: TextView
    private lateinit var headerResumeAlcoolGlobal: TextView
    private lateinit var headerResumeBiere: TextView
    private lateinit var headerResumeLiqueur: TextView
    private lateinit var headerResumeAlcoolFort: TextView
    
    private var consoleClickCount = 0
    private var lastConsoleClickTime = 0L
    private var consoleVisible = false
    private var consoleDialog: AlertDialog? = null
    private val isVersionGratuite = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        logger.d("MainActivity onCreate")
        
        try {
            configLangue = ConfigLangue(this)
            configLangue.initialiserLangue()
            trad = MainActivityLangues.getTraductions(configLangue.getLangue())
            
            setContentView(R.layout.activity_main)
            
            dbHelper = DatabaseHelper(this)
            
            initializeViews()
            setupHeader()
            
            if (!checkAgeWarningStatus()) {
                showAgeWarningDialog()
            } else {
                initializeMainContent()
            }
            
        } catch (e: Exception) {
            logger.e("ERREUR onCreate", e)
            Toast.makeText(this, "Erreur: ${e.message}", Toast.LENGTH_LONG).show()
            finish()
        }
    }
    
    private fun initializeViews() {
        headerTextView = findViewById(R.id.main_header_title)
        tabLayout = findViewById(R.id.main_tab_layout)
        viewPager = findViewById(R.id.main_view_pager)
        adContainer = findViewById(R.id.main_ad_container)

                headerResumeScroll = findViewById(R.id.header_resume_scroll)
        headerResumeContainer = findViewById(R.id.header_resume_container)
        headerResumeLabel = findViewById(R.id.header_resume_label)
        headerResumeCigarette = findViewById(R.id.header_resume_cigarette)
        headerResumeJoint = findViewById(R.id.header_resume_joint)
        headerResumeAlcoolGlobal = findViewById(R.id.header_resume_alcool_global)
        headerResumeBiere = findViewById(R.id.header_resume_biere)
        headerResumeLiqueur = findViewById(R.id.header_resume_liqueur)
        headerResumeAlcoolFort = findViewById(R.id.header_resume_alcool_fort)

        // Caché par défaut, on l’affichera seulement sur Stats & Calendrier
        headerResumeScroll.visibility = View.GONE

        logger.d(
            "initializeViews: headerTextView=$headerTextView, " +
            "tabLayout=$tabLayout, viewPager=$viewPager, adContainer=$adContainer"
        )
    }


        private fun setupHeader() {
    logger.d("setupHeader: called")

    updateDateTime()

    headerTextView.setOnClickListener {
        logger.d("setupHeader: header clicked -> opening console debug")
        handleConsoleDebugClick()
    }

    logger.d("setupHeader: header click listener initialized")
}

        
        private fun updateDateTime() {
    try {
        val dateFormat = SimpleDateFormat("EEEE dd MMMM yyyy - HH:mm", Locale.getDefault())
        val currentDateTime = dateFormat.format(Date())
        headerTextView.text = "Stop Addict\n$currentDateTime"

        logger.d("updateDateTime: header updated with \"$currentDateTime\"")
    } catch (e: Exception) {
        headerTextView.text = "Stop Addict"
        logger.e("updateDateTime: erreur format date, fallback appliqué", e)
    }
}
    
    private fun checkAgeWarningStatus(): Boolean {
    val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
    val warningShown = prefs.getBoolean(PREF_WARNING_SHOWN, false)
    val ageAccepted = prefs.getBoolean(PREF_AGE_ACCEPTED, false)
    val result = warningShown && ageAccepted

    logger.d("checkAgeWarningStatus: warningShown=$warningShown, ageAccepted=$ageAccepted, result=$result")

    return result
}

    private fun showAgeWarningDialog() {
    logger.d("showAgeWarningDialog: ouverture du pop-up d’avertissement majeurité...")

    try {
        val container = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 40, 40, 20)
        }
        logger.d("showAgeWarningDialog: container initialisé")

        val titleText = TextView(this).apply {
            text = trad["warning_title"] ?: ""
            textSize = 18f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setPadding(0, 0, 0, 20)
            gravity = Gravity.CENTER
        }
        container.addView(titleText)
        logger.d("showAgeWarningDialog: titre ajouté")

        val messageText = TextView(this).apply {
            text = trad["warning_message"] ?: ""
            textSize = 14f
            setPadding(0, 0, 0, 20)
        }
        container.addView(messageText)
        logger.d("showAgeWarningDialog: message principal ajouté")

        val linkText = TextView(this).apply {
            text = trad["warning_resources_link"] ?: ""
            textSize = 14f
            setTextColor(getColor(android.R.color.holo_blue_dark))
            setPadding(0, 0, 0, 30)
            setOnClickListener {
                logger.d("showAgeWarningDialog: clic sur 'ressources utiles'")
                showRessourcesUtiles()
            }
        }
        container.addView(linkText)
        logger.d("showAgeWarningDialog: lien ressources utiles ajouté")

        val checkboxAge = CheckBox(this).apply {
            text = trad["warning_checkbox_age"] ?: ""
            textSize = 15f
            setPadding(0, 10, 0, 10)
        }
        container.addView(checkboxAge)
        logger.d("showAgeWarningDialog: checkbox majeur(e) ajouté")

        val checkboxNoShow = CheckBox(this).apply {
            text = trad["warning_checkbox_noshow"] ?: ""
            setPadding(0, 10, 0, 20)
        }
        container.addView(checkboxNoShow)
        logger.d("showAgeWarningDialog: checkbox ne plus afficher ajouté")

        val builder = AlertDialog.Builder(this)
        builder.setView(container)

        builder.setNegativeButton(trad["warning_btn_quit"] ?: "Quit") { _, _ ->
            logger.d("showAgeWarningDialog: utilisateur a cliqué 'Quitter'")
            finish()
        }

        builder.setPositiveButton(trad["warning_btn_accept"] ?: "Accept", null)
        builder.setCancelable(false)

        val dialog = builder.create()

        dialog.setOnShowListener {
            logger.d("showAgeWarningDialog: dialog affiché, configuration des boutons...")

            val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            positiveButton.isEnabled = false

            checkboxAge.setOnCheckedChangeListener { _, isChecked ->
                logger.d("showAgeWarningDialog: checkbox âge -> $isChecked")
                positiveButton.isEnabled = isChecked
            }

            positiveButton.setOnClickListener {
                logger.d("showAgeWarningDialog: bouton 'Accepter' cliqué")

                if (checkboxAge.isChecked) {
                    val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

                    prefs.edit().apply {
                        putBoolean(PREF_AGE_ACCEPTED, true)
                        if (checkboxNoShow.isChecked) {
                            logger.d("showAgeWarningDialog: option 'ne plus afficher' activée")
                            putBoolean(PREF_WARNING_SHOWN, true)
                        }
                        apply()
                    }

                    logger.d("showAgeWarningDialog: préférences sauvegardées, fermeture dialog")
                    dialog.dismiss()
                    initializeMainContent()
                }
            }
        }

        dialog.show()
        logger.d("showAgeWarningDialog: dialog.show() exécuté")

    } catch (e: Exception) {
        logger.e("showAgeWarningDialog: erreur dans la création du dialog", e)
        initializeMainContent()
    }
}


    
private fun showRessourcesUtiles() {
    logger.d("showRessourcesUtiles: ouverture de la popup ressources utiles...")
    try {
        AlertDialog.Builder(this)
            .setTitle(trad["resources_title"] ?: "Help")
            .setMessage(trad["resources_content"] ?: "")
            .setPositiveButton(trad["resources_btn_close"] ?: "Close") { _, _ ->
                logger.d("showRessourcesUtiles: bouton Fermer cliqué")
            }
            .show()
        logger.d("showRessourcesUtiles: dialog affiché avec succès")
    } catch (e: Exception) {
        logger.e("showRessourcesUtiles: erreur affichage ressources", e)
    }
}

private fun initializeMainContent() {
    logger.d("initializeMainContent: démarrage de l'initialisation du contenu principal")
    try {
        setupTabLayoutAndViewPager()
        logger.d("initializeMainContent: setupTabLayoutAndViewPager exécuté")

        if (isVersionGratuite) {
    adContainer.visibility = View.VISIBLE
    logger.d("initializeMainContent: version GRATUITE -> affichage du bandeau pub")

    // --- Initialisation AdMob + chargement bannière de TEST ---
    try {
        // Initialise le SDK (OK si appelé plusieurs fois)
        MobileAds.initialize(this) {}

        // On crée la bannière et on l'ajoute dans main_ad_container
        adView = AdView(this).apply {
            // ✅ ID DE TEST fournie par Google (à laisser pour tes tests)
            adUnitId = "ca-app-pub-3940256099942544/6300978111"
            setAdSize(AdSize.BANNER)
        }

        // On vide le container au cas où puis on ajoute la bannière
        adContainer.removeAllViews()
        adContainer.addView(adView)

        // On charge une pub de test
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        logger.d("initializeMainContent: bannière AdMob de test chargée")
    } catch (e: Exception) {
        logger.e("initializeMainContent: erreur init AdMob", e)
    }

} else {
    adContainer.visibility = View.GONE
    logger.d("initializeMainContent: version PAYANTE -> masquage du bandeau pub")
}

        logger.d("initializeMainContent: fin normale de l'initialisation")
    } catch (e: Exception) {
        logger.e("initializeMainContent: Erreur init content", e)
    }
}

private fun setupTabLayoutAndViewPager() {
    logger.d("setupTabLayoutAndViewPager: démarrage configuration ViewPager + TabLayout")

    val fragmentAdapter = FragmentAdapter(this)
    viewPager.adapter = fragmentAdapter
    viewPager.isUserInputEnabled = true
    viewPager.offscreenPageLimit = 2
    logger.d("setupTabLayoutAndViewPager: adapter assigné, userInputEnabled=true, offscreenPageLimit=2")

    TabLayoutMediator(tabLayout, viewPager) { tab, position ->
        val title = getTabTitle(position)

        // Vue personnalisée onglet : texte uniquement
        val tabView = LinearLayout(this@MainActivity).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
        }

        val textView = TextView(this@MainActivity).apply {
            text = title
            gravity = Gravity.CENTER
            textSize = 14f
            // 1 ligne pour tous, sauf Habitudes & Volontés (position 3) autorisé à 2 lignes
            maxLines = if (position == 3) 2 else 1
            setSingleLine(position != 3)
        }
        tabView.addView(textView)

        tab.customView = tabView

        logger.d("setupTabLayoutAndViewPager: onglet position=$position, title=$title")
    }.attach()
    logger.d("setupTabLayoutAndViewPager: TabLayoutMediator attaché")

    viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        logger.d("setupTabLayoutAndViewPager: onPageSelected -> position=$position, updateDateTime()")
        updateDateTime()
        updateHeaderResumeVisibility(position)
    }
})

logger.d("setupTabLayoutAndViewPager: callback onPageChange enregistré")
updateHeaderResumeVisibility(viewPager.currentItem)
}

    private fun updateHeaderResumeVisibility(position: Int) {
        // Onglet 1 = Stats, onglet 2 = Calendrier
        if (position == 1 || position == 2) {
            headerResumeScroll.visibility = View.VISIBLE
            updateHeaderResumeJour()
        } else {
            headerResumeScroll.visibility = View.GONE
        }
    }

    private fun updateHeaderResumeJour() {
        try {
            // Traductions spécifiques Stats (pour le mot "Jour")
            val tradStats = StatsLangues.getTraductions(configLangue.getLangue())
            val labelJour = tradStats["calculs_periode_jour"]
                ?: tradStats["btn_jour"]
                ?: "Jour"

            headerResumeLabel.text = "$labelJour :"

            // Date du jour au format utilisé par la DB
            val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            val consos = dbHelper.getConsommationsJour(today)

            fun getCount(key: String): Int {
                val value = consos[key]
                return when (value) {
                    is Number -> value.toInt()
                    else -> 0
                }
            }

            val cCigarette = getCount("cigarette")
            val cJoint = getCount("joint")
            val cAlcoolGlobal = getCount("alcool_global")
            val cBiere = getCount("biere")
            val cLiqueur = getCount("liqueur")
            val cAlcoolFort = getCount("alcool_fort")

            headerResumeCigarette.text = "🚬 $cCigarette"
            headerResumeJoint.text = "🌿 $cJoint"
            headerResumeAlcoolGlobal.text = "🥃G $cAlcoolGlobal"
            headerResumeBiere.text = "🍺 $cBiere"
            headerResumeLiqueur.text = "🍷 $cLiqueur"
            headerResumeAlcoolFort.text = "🥃 $cAlcoolFort"

            logger.d("updateHeaderResumeJour: jour=$today, ciga=$cCigarette, joint=$cJoint, alcoolGlobal=$cAlcoolGlobal, biere=$cBiere, liqueur=$cLiqueur, alcoolFort=$cAlcoolFort")
        } catch (e: Exception) {
            logger.e("updateHeaderResumeJour: erreur mise à jour bandeau jour", e)
        }
    }
 
private fun getTabTitle(position: Int): String {
    val title = when (position) {
        0 -> trad["tab_accueil"] ?: "Home"
        1 -> trad["tab_stats"] ?: "Stats"
        2 -> trad["tab_calendrier"] ?: "Calendar"
        3 -> trad["tab_habitudes"] ?: "Habits"
        4 -> trad["tab_reglages"] ?: "Settings"
        else -> ""
    }
    logger.d("getTabTitle: position=$position -> title='$title'")
    return title
}

    private fun handleConsoleDebugClick() {
    val currentTime = System.currentTimeMillis()
    logger.d(
        "handleConsoleDebugClick: click detected, " +
        "currentTime=$currentTime, lastConsoleClickTime=$lastConsoleClickTime, consoleClickCount=$consoleClickCount"
    )

    if (currentTime - lastConsoleClickTime > 2000) {
        logger.d("handleConsoleDebugClick: > 2000 ms since last click -> reset consoleClickCount to 1")
        consoleClickCount = 1
    } else {
        consoleClickCount++
        logger.d("handleConsoleDebugClick: click within 2000 ms -> consoleClickCount=$consoleClickCount")
    }

    lastConsoleClickTime = currentTime
    logger.d("handleConsoleDebugClick: lastConsoleClickTime updated to $lastConsoleClickTime")

    if (consoleClickCount >= 5) {
        logger.d("handleConsoleDebugClick: 5 clicks detected -> toggle console (consoleVisible=$consoleVisible)")
        consoleClickCount = 0

        if (consoleVisible) {
            logger.d("handleConsoleDebugClick: console visible -> dismiss dialog")
            consoleDialog?.dismiss()
            consoleVisible = false
            logger.d("handleConsoleDebugClick: consoleVisible set to false")
        } else {
            logger.d("handleConsoleDebugClick: console hidden -> show dialog")
            showConsoleDebugDialog()
            consoleVisible = true
            logger.d("handleConsoleDebugClick: consoleVisible set to true")
        }
    }
}
    
    private fun showConsoleDebugDialog() {
    try {
        val textView = TextView(this).apply {
            setBackgroundColor(Color.BLACK)
            setTextColor(Color.rgb(0, 255, 0))
            setPadding(20, 20, 20, 20)
            textSize = 11f
            typeface = android.graphics.Typeface.MONOSPACE

            val logs = StringBuilder()
            logs.append("═══════════════════════════════════\n")
            logs.append("      ${trad["console_title"] ?: "DEBUG CONSOLE"}     \n")
            logs.append("═══════════════════════════════════\n\n")

            // --- Infos version / device ---
            val versionText = if (isVersionGratuite) {
                trad["console_version_free"] ?: "Free"
            } else {
                trad["console_version_paid"] ?: "Paid"
            }
            logs.append("${trad["console_version"] ?: "Version"}: $versionText\n")
            logs.append("${trad["console_langue"] ?: "Language"}: ${configLangue.getLangue()}\n")
            logs.append("${trad["console_date"] ?: "Date"}: ${
                SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(
                    Date()
                )
            }\n")
            logs.append("${trad["console_build"] ?: "Build"}: DEBUG\n")
            logs.append("${trad["console_device"] ?: "Device"}: ${android.os.Build.MODEL}\n")
            logs.append("${trad["console_android"] ?: "Android"}: ${android.os.Build.VERSION.RELEASE}\n\n")

            // --- État des prefs (âge, avertissement, etc.) ---
            try {
                val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                logs.append("--- ${trad["console_app_state"] ?: "App State"} ---\n")
                logs.append("${trad["console_age_accepted"] ?: "Age accepted"}: ${
                    prefs.getBoolean(
                        PREF_AGE_ACCEPTED,
                        false
                    )
                }\n")
                logs.append("${trad["console_warning_shown"] ?: "Warning shown"}: ${
                    prefs.getBoolean(
                        PREF_WARNING_SHOWN,
                        false
                    )
                }\n\n")
            } catch (e: Exception) {
                logs.append("${trad["console_error_prefs"] ?: "Error reading prefs"}: ${e.message}\n\n")
            }

            // --- Logs DB du jour ---
            logs.append("--- ${trad["console_logs_db"] ?: "Database Logs"} ---\n")
            try {
                val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                val consos = dbHelper.getConsommationsJour(today)
                logs.append("${trad["console_consos_jour"] ?: "Daily consumptions"}:\n")
                if (consos.isEmpty()) {
                    logs.append("  ${trad["console_no_conso"] ?: "No consumption"}\n")
                } else {
                    consos.forEach { (type, count) ->
                        logs.append("  $type: $count\n")
                    }
                }
            } catch (e: Exception) {
                logs.append("${trad["console_error_db"] ?: "Error reading DB"}: ${e.message}\n")
            }

            // --- 🔥 Logs internes centralisés StopAddictLogger ---
            logs.append("\n--- ${trad["console_internal_logs"] ?: "Internal logs"} ---\n")
            try {
                val internalLogs = StopAddictLogger.getAllLogsAsText()
                if (internalLogs.isBlank()) {
                    logs.append(
                        (trad["console_no_internal_logs"]
                            ?: "Aucun log interne pour le moment.") + "\n"
                    )
                } else {
                    logs.append(internalLogs).append("\n")
                }
            } catch (e: Exception) {
                logs.append("Erreur lecture logs internes: ${e.message}\n")
            }

            logs.append("\n═══════════════════════════════════\n")
            logs.append("     ${trad["console_logs_selectable"] ?: "Selectable logs ✓"}        \n")
            logs.append("═══════════════════════════════════\n")

            text = logs.toString()
            setTextIsSelectable(true)
        }

        val scrollView = ScrollView(this).apply {
            setBackgroundColor(Color.BLACK)
        }
        scrollView.addView(textView)

        consoleDialog = AlertDialog.Builder(this)
            .setView(scrollView)
            .setPositiveButton(trad["console_btn_close"] ?: "Close") { dialog, _ ->
                dialog.dismiss()
                consoleVisible = false
            }
            .create()

        consoleDialog?.window?.setBackgroundDrawableResource(android.R.color.black)
        consoleDialog?.setOnShowListener {
            consoleDialog?.getButton(AlertDialog.BUTTON_POSITIVE)
                ?.setTextColor(Color.rgb(0, 255, 0))
        }

        consoleDialog?.show()

        consoleDialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        consoleDialog?.window?.setGravity(Gravity.TOP)

    } catch (e: Exception) {
        logger.e("Erreur console", e)
    }
}

    fun refreshData() {
    logger.d("refreshData: called -> updating date/time")
    updateDateTime()
    logger.d("refreshData: finished updating date/time")
}

        fun exportAllLogsFromSettings() {
        try {
            val allLogs = StopAddictLogger.getAllLogsAsText()
            val safeText = if (allLogs.isBlank()) {
                "Aucun log interne pour le moment."
            } else {
                allLogs
            }

            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "StopAddict – Logs internes")
                putExtra(Intent.EXTRA_TEXT, safeText)
            }

            val chooser = Intent.createChooser(sendIntent, "Exporter les logs")
            startActivity(chooser)

            logger.d("exportAllLogsFromSettings: partage des logs lancé")

        } catch (e: Exception) {
            logger.e("exportAllLogsFromSettings: erreur lors de l'export", e)
            Toast.makeText(this, "Erreur export logs: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
    
        // --- Cycle de vie ---

    override fun onResume() {
    super.onResume()
    logger.d("onResume: activité reprise -> mise à jour de la date/heure")
    updateDateTime()
}

override fun onPause() {
    super.onPause()
    logger.d("onPause: activité mise en pause")
}


override fun onDestroy() {
    super.onDestroy()
    logger.d("onDestroy: destruction activité, nettoyage des ressources")

    consoleDialog?.dismiss()

    if (::adView.isInitialized) {
        adView.destroy()
    }

    if (::dbHelper.isInitialized) {
        logger.d("onDestroy: fermeture de la base de données")
        dbHelper.close()
    } else {
        logger.d("onDestroy: dbHelper non initialisé, rien à fermer")
    }
}
}
