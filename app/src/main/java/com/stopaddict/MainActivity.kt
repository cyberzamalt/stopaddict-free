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
                text = "⚠️ Avertissement - Public majeur(e) (18+)"
                textSize = 18f
                setTypeface(null, android.graphics.Typeface.BOLD)
                setPadding(0, 0, 0, 20)
                gravity = Gravity.CENTER
            }
            container.addView(titleText)
            
            // Message principal
            val messageText = TextView(this).apply {
                text = "Stop Addict est une application d'auto-suivi et d'aide à la réduction/arrêt des consommations (tabac, alcool, cannabis).\n\n" +
                       "Réservée aux personnes de 18 ans et plus, ayant dépassé la majorité du pays de résidence ou du pays visité.\n\n" +
                       "Ne fait pas la promotion de ces produits.\n\n" +
                       "Ne remplace pas un accompagnement médical, psychologique ou social. En cas de difficulté, consultez un professionnel.\n\n" +
                       "Utilisez Stop Addict de façon responsable."
                textSize = 14f
                setPadding(0, 0, 0, 20)
            }
            container.addView(messageText)
            
            // Lien ressources utiles
            val linkText = TextView(this).apply {
                text = "📞 Ressources et numéros utiles"
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
                text = "☑️ Je suis majeur(e), j'ai 18 ans ou plus"
                textSize = 15f
                setPadding(0, 10, 0, 10)
            }
            container.addView(checkboxAge)
            
            // CASE 2 : Ne plus afficher
            val checkboxNoShow = CheckBox(this).apply {
                text = "Ne plus afficher ce message"
                setPadding(0, 10, 0, 20)
            }
            container.addView(checkboxNoShow)
            
            val builder = AlertDialog.Builder(this)
            builder.setView(container)
            
            // Bouton Quitter
            builder.setNegativeButton("Quitter") { _, _ ->
                finish()
            }
            
            // Bouton J'accepte et continuer - DESACTIVE par défaut
            builder.setPositiveButton("J'accepte et continuer", null)
            
            builder.setCancelable(false)
            val dialog = builder.create()
            
            dialog.setOnShowListener {
                val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                positiveButton.isEnabled = false // Désactivé par défaut
                
                // Activer le bouton uniquement si case majorité cochée
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
            val ressources = """
                📞 Ressources et numéros utiles
                
                🚨 Urgences : 112 (UE) / 15 (FR - SAMU)
                
                🇫🇷 FRANCE
                • Tabac Info Service : 39 89
                  → tabac-info-service.fr
                • Alcool Info Service : 0 980 980 930
                  → alcool-info-service.fr
                • Drogues Info Service : 0 800 23 13 13
                  → drogues-info-service.fr
                
                🌍 Consulte les ressources locales dans ton pays si tu n'es pas en France.
            """.trimIndent()
            
            AlertDialog.Builder(this)
                .setTitle("📞 Besoin d'aide ?")
                .setMessage(ressources)
                .setPositiveButton("Fermer", null)
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
        val langue = configLangue.getLangue()
        return when (position) {
            0 -> if (langue == "FR") "Accueil" else "Home"
            1 -> if (langue == "FR") "Stats" else "Stats"
            2 -> if (langue == "FR") "Calendrier" else "Calendar"
            3 -> if (langue == "FR") "Habitudes & Volonté" else "Habits & Will"
            4 -> if (langue == "FR") "Réglages" else "Settings"
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
            // TextView style terminal noir/vert
            val textView = TextView(this).apply {
                setBackgroundColor(Color.BLACK)
                setTextColor(Color.rgb(0, 255, 0)) // Vert terminal
                setPadding(20, 20, 20, 20)
                textSize = 11f
                typeface = android.graphics.Typeface.MONOSPACE
                
                val logs = StringBuilder()
                logs.append("═══════════════════════════════════\n")
                logs.append("      CONSOLE DEBUG STOPADDICT     \n")
                logs.append("═══════════════════════════════════\n\n")
                logs.append("Version: ${if (isVersionGratuite) "Gratuite" else "Payante"}\n")
                logs.append("Langue: ${configLangue.getLangue()}\n")
                logs.append("Date: ${SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Date())}\n")
                logs.append("Build: DEBUG\n")
                logs.append("Device: ${android.os.Build.MODEL}\n")
                logs.append("Android: ${android.os.Build.VERSION.RELEASE}\n\n")
                
                // Logs base de données
                try {
                    val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                    logs.append("--- État Application ---\n")
                    logs.append("Age accepté: ${prefs.getBoolean(PREF_AGE_ACCEPTED, false)}\n")
                    logs.append("Warning shown: ${prefs.getBoolean(PREF_WARNING_SHOWN, false)}\n\n")
                } catch (e: Exception) {
                    logs.append("Erreur lecture prefs: ${e.message}\n\n")
                }
                
                logs.append("--- Logs Database ---\n")
                try {
                    val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                    val consos = dbHelper.getConsommationsJour(today)
                    logs.append("Consommations jour:\n")
                    if (consos.isEmpty()) {
                        logs.append("  Aucune consommation\n")
                    } else {
                        consos.forEach { (type, count) ->
                            logs.append("  $type: $count\n")
                        }
                    }
                } catch (e: Exception) {
                    logs.append("Erreur lecture DB: ${e.message}\n")
                }
                
                logs.append("\n═══════════════════════════════════\n")
                logs.append("     Logs sélectionnables ✓        \n")
                logs.append("═══════════════════════════════════\n")
                
                text = logs.toString()
                setTextIsSelectable(true) // Permettre sélection/copie
            }
            
            val scrollView = ScrollView(this).apply {
                setBackgroundColor(Color.BLACK)
            }
            scrollView.addView(textView)
            
            consoleDialog = AlertDialog.Builder(this)
                .setView(scrollView)
                .setPositiveButton("Fermer") { dialog, _ ->
                    dialog.dismiss()
                    consoleVisible = false
                }
                .create()
            
            // Style de la dialog en noir
            consoleDialog?.window?.setBackgroundDrawableResource(android.R.color.black)
            consoleDialog?.setOnShowListener {
                consoleDialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(Color.rgb(0, 255, 0))
            }
            
            consoleDialog?.show()
            
            // Forcer en plein écran, en haut
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
