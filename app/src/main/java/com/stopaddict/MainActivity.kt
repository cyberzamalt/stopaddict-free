package com.stopaddict

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
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
        
        Log.d(TAG, "MainActivity onCreate")
        
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
            Log.e(TAG, "ERREUR onCreate", e)
            Toast.makeText(this, "Erreur: ${e.message}", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun initializeViews() {
        headerTextView = findViewById(R.id.main_header_title)
        tabLayout = findViewById(R.id.main_tab_layout)
        viewPager = findViewById(R.id.main_view_pager)
        adContainer = findViewById(R.id.main_ad_container)
    }

    private fun setupHeader() {
        updateDateTime()
        headerTextView.setOnClickListener {
            handleConsoleDebugClick()
        }
    }

    private fun updateDateTime() {
        try {
            val dateFormat = SimpleDateFormat("EEEE dd MMMM yyyy - HH:mm", Locale.getDefault())
            val currentDateTime = dateFormat.format(Date())
            headerTextView.text = "Stop Addict\n$currentDateTime"
        } catch (e: Exception) {
            headerTextView.text = "Stop Addict"
        }
    }

    private fun checkAgeWarningStatus(): Boolean {
        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        return prefs.getBoolean(PREF_WARNING_SHOWN, false) && 
               prefs.getBoolean(PREF_AGE_ACCEPTED, false)
    }

    private fun showAgeWarningDialog() {
        try {
            val container = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(40, 40, 40, 20)
            }
            
            // Titre
            val titleText = TextView(this).apply {
                text = trad["warning_title"] ?: ""
                textSize = 18f
                setTypeface(null, android.graphics.Typeface.BOLD)
                setPadding(0, 0, 0, 20)
                gravity = Gravity.CENTER
            }
            container.addView(titleText)
            
            // Message principal
            val messageText = TextView(this).apply {
                text = trad["warning_message"] ?: ""
                textSize = 14f
                setPadding(0, 0, 0, 20)
            }
            container.addView(messageText)
            
            // Lien ressources utiles
            val linkText = TextView(this).apply {
                text = trad["warning_resources_link"] ?: ""
                textSize = 14f
                setTextColor(getColor(android.R.color.holo_blue_dark))
                setPadding(0, 0, 0, 30)
                setOnClickListener {
                    showRessourcesUtiles()
                }
            }
            container.addView(linkText)
            
            // CASE 1 : Je suis majeur(e) - OBLIGATOIRE
            val checkboxAge = CheckBox(this).apply {
                text = trad["warning_checkbox_age"] ?: ""
                textSize = 15f
                setPadding(0, 10, 0, 10)
            }
            container.addView(checkboxAge)
            
            // CASE 2 : Ne plus afficher
            val checkboxNoShow = CheckBox(this).apply {
                text = trad["warning_checkbox_noshow"] ?: ""
                setPadding(0, 10, 0, 20)
            }
            container.addView(checkboxNoShow)
            
            val builder = AlertDialog.Builder(this)
            builder.setView(container)
            
            // Bouton Quitter
            builder.setNegativeButton(trad["warning_btn_quit"] ?: "Quit") { _, _ ->
                finish()
            }
            
            // Bouton J'accepte et continuer - DESACTIVE par défaut
            builder.setPositiveButton(trad["warning_btn_accept"] ?: "Accept", null)
            
            builder.setCancelable(false)
            val dialog = builder.create()
            
            dialog.setOnShowListener {
                val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                positiveButton.isEnabled = false
                
                checkboxAge.setOnCheckedChangeListener { _, isChecked ->
                    positiveButton.isEnabled = isChecked
                }
                
                positiveButton.setOnClickListener {
                    if (checkboxAge.isChecked) {
                        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                        prefs.edit().apply {
                            putBoolean(PREF_AGE_ACCEPTED, true)
                            if (checkboxNoShow.isChecked) {
                                putBoolean(PREF_WARNING_SHOWN, true)
                            }
                            apply()
                        }
                        dialog.dismiss()
                        initializeMainContent()
                    }
                }
            }
            
            dialog.show()
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur dialog", e)
            initializeMainContent()
        }
    }
    
    private fun showRessourcesUtiles() {
        try {
            AlertDialog.Builder(this)
                .setTitle(trad["resources_title"] ?: "Help")
                .setMessage(trad["resources_content"] ?: "")
                .setPositiveButton(trad["resources_btn_close"] ?: "Close", null)
                .show()
        } catch (e: Exception) {
            Log.e(TAG, "Erreur affichage ressources", e)
        }
    }

    private fun initializeMainContent() {
        try {
            setupTabLayoutAndViewPager()
            
            if (isVersionGratuite) {
                adContainer.visibility = View.VISIBLE
            } else {
                adContainer.visibility = View.GONE
            }
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur init content", e)
        }
    }

    private fun setupTabLayoutAndViewPager() {
        val fragmentAdapter = FragmentAdapter(this)
        viewPager.adapter = fragmentAdapter
        viewPager.isUserInputEnabled = true
        viewPager.offscreenPageLimit = 2
        
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
        
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateDateTime()
            }
        })
    }

    private fun getTabTitle(position: Int): String {
        return when (position) {
            0 -> trad["tab_accueil"] ?: "Home"
            1 -> trad["tab_stats"] ?: "Stats"
            2 -> trad["tab_calendrier"] ?: "Calendar"
            3 -> trad["tab_habitudes"] ?: "Habits"
            4 -> trad["tab_reglages"] ?: "Settings"
            else -> ""
        }
    }

    private fun handleConsoleDebugClick() {
        val currentTime = System.currentTimeMillis()
        
        if (currentTime - lastConsoleClickTime > 2000) {
            consoleClickCount = 1
        } else {
            consoleClickCount++
        }
        
        lastConsoleClickTime = currentTime
        
        if (consoleClickCount >= 5) {
            consoleClickCount = 0
            
            if (consoleVisible) {
                consoleDialog?.dismiss()
                consoleVisible = false
            } else {
                showConsoleDebugDialog()
                consoleVisible = true
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
            Log.e(TAG, "Erreur console", e)
        }
    }

    fun refreshData() {
        updateDateTime()
    }

    override fun onResume() {
        super.onResume()
        updateDateTime()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        consoleDialog?.dismiss()
        if (::dbHelper.isInitialized) {
            dbHelper.close()
        }
    }
}
