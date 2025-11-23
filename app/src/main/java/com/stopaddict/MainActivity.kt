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

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
        private const val PREFS_NAME = "StopAddict"
        private const val PREF_WARNING_SHOWN = "warning_majorite_shown"
        private const val PREF_AGE_ACCEPTED = "age_18_accepted"
    }

    private val logger = AppLogger("MainActivity")   // 👈 AJOUTER ÇA

    private lateinit var headerTextView: TextView
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var adContainer: FrameLayout
    private lateinit var configLangue: ConfigLangue
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var trad: Map<String, String>
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
        tab.text = title
        logger.d("setupTabLayoutAndViewPager: onglet position=$position, title=$title")
    }.attach()
    logger.d("setupTabLayoutAndViewPager: TabLayoutMediator attaché")

    viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            logger.d("setupTabLayoutAndViewPager: onPageSelected -> position=$position, updateDateTime()")
            updateDateTime()
        }
    })
    logger.d("setupTabLayoutAndViewPager: callback onPageChange enregistré")
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
    logger.d("handleConsoleDebugClick: click detected, currentTime=$currentTime, lastConsoleClickTime=$lastConsoleClickTime, consoleClickCount=$consoleClickCount")

    if (currentTime - lastConsoleClickTime > 2000) {
        logger.d("handleConsoleDebugClick: more than 2000 ms since last click -> reset consoleClickCount to 1")
        consoleClickCount = 1
    } else {
        consoleClickCount++
        logger.d("handleConsoleDebugClick: click within 2000 ms -> increment consoleClickCount=$consoleClickCount")
    }

    lastConsoleClickTime = currentTime
    logger.d("handleConsoleDebugClick: lastConsoleClickTime updated to $lastConsoleTime")

    if (consoleClickCount >= 5) {
        logger.d("handleConsoleDebugClick: 5 clicks detected -> toggle console (current consoleVisible=$consoleVisible)")
        consoleClickCount = 0

        if (consoleVisible) {
            logger.d("handleConsoleDebugClick: console currently visible -> dismiss dialog")
            consoleDialog?.dismiss()
            consoleVisible = false
            logger.d("handleConsoleDebugClick: consoleVisible set to false")
        } else {
            logger.d("handleConsoleDebugClick: console currently hidden -> show dialog")
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
                
                val versionText = if (isVersionGratuite) {
                    trad["console_version_free"] ?: "Free"
                } else {
                    trad["console_version_paid"] ?: "Paid"
                }
                logs.append("${trad["console_version"] ?: "Version"}: $versionText\n")
                logs.append("${trad["console_langue"] ?: "Language"}: ${configLangue.getLangue()}\n")
                logs.append("${trad["console_date"] ?: "Date"}: ${SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Date())}\n")
                logs.append("${trad["console_build"] ?: "Build"}: DEBUG\n")
                logs.append("${trad["console_device"] ?: "Device"}: ${android.os.Build.MODEL}\n")
                logs.append("${trad["console_android"] ?: "Android"}: ${android.os.Build.VERSION.RELEASE}\n\n")
                
                try {
                    val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                    logs.append("--- ${trad["console_app_state"] ?: "App State"} ---\n")
                    logs.append("${trad["console_age_accepted"] ?: "Age accepted"}: ${prefs.getBoolean(PREF_AGE_ACCEPTED, false)}\n")
                    logs.append("${trad["console_warning_shown"] ?: "Warning shown"}: ${prefs.getBoolean(PREF_WARNING_SHOWN, false)}\n\n")
                } catch (e: Exception) {
                    logs.append("${trad["console_error_prefs"] ?: "Error reading prefs"}: ${e.message}\n\n")
                }
                
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
                consoleDialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(Color.rgb(0, 255, 0))
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

    if (::dbHelper.isInitialized) {
        logger.d("onDestroy: fermeture de la base de données")
        dbHelper.close()
    } else {
        logger.d("onDestroy: dbHelper non initialisé, rien à fermer")
    }
}
