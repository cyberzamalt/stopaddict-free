package com.stopaddict

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
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
            val dialogView = layoutInflater.inflate(android.R.layout.simple_list_item_1, null)
            val container = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(40, 40, 40, 20)
            }
            
            // Message principal
            val messageText = TextView(this).apply {
                text = "Stop Addict est réservé aux personnes majeures (18 ans et plus).\n\nCette application est un outil d'aide et ne remplace pas un suivi médical."
                textSize = 16f
                setPadding(0, 0, 0, 20)
            }
            container.addView(messageText)
            
            // Lien ressources utiles
            val linkText = TextView(this).apply {
                text = "📞 Ressources et numéros utiles"
                textSize = 14f
                setTextColor(getColor(android.R.color.holo_blue_dark))
                setPadding(0, 0, 0, 20)
                setOnClickListener {
                    showRessourcesUtiles()
                }
            }
            container.addView(linkText)
            
            // Checkbox ne plus afficher
            val checkboxNoShow = CheckBox(this).apply {
                text = "Ne plus afficher ce message"
                setPadding(0, 10, 0, 0)
            }
            container.addView(checkboxNoShow)
            
            val builder = AlertDialog.Builder(this)
            builder.setTitle("⚠️ Avertissement")
            builder.setView(container)
            builder.setPositiveButton("J'ai 18 ans ou plus") { _, _ ->
                val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                prefs.edit().apply {
                    putBoolean(PREF_AGE_ACCEPTED, true)
                    if (checkboxNoShow.isChecked) {
                        putBoolean(PREF_WARNING_SHOWN, true)
                    }
                    apply()
                }
                initializeMainContent()
            }
            builder.setNegativeButton("Quitter") { _, _ ->
                finish()
            }
            builder.setCancelable(false)
            builder.show()
        } catch (e: Exception) {
            Log.e(TAG, "Erreur dialog", e)
            initializeMainContent()
        }
    }
    
    private fun showRessourcesUtiles() {
        try {
            val ressources = """
                🇫🇷 FRANCE
                • Tabac Info Service : 39 89
                • Alcool Info Service : 0 980 980 930
                • Drogues Info Service : 0 800 23 13 13
                
                🇧🇪 BELGIQUE
                • Tabacstop : 0800 111 00
                • Alcooliques Anonymes : 078 15 25 56
                • Infor-Drogues : 02 227 52 52
                
                🇨🇭 SUISSE
                • Ligne stop-tabac : 0848 000 181
                • Alcooliques Anonymes : 0848 848 846
                • Infodrog : 031 376 04 01
                
                🇨🇦 CANADA
                • J'ARRÊTE : 1 866 527 7383
                • Drogue : aide et référence : 1 800 265 2626
                
                En cas d'urgence, contactez les services d'urgence de votre pays.
            """.trimIndent()
            
            AlertDialog.Builder(this)
                .setTitle("📞 Ressources utiles")
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
            val textView = TextView(this).apply {
                setPadding(20, 20, 20, 20)
                textSize = 12f
                
                val logs = StringBuilder()
                logs.append("═══ CONSOLE DEBUG ═══\n\n")
                logs.append("Version: ${if (isVersionGratuite) "Gratuite" else "Payante"}\n")
                logs.append("Langue: ${configLangue.getLangue()}\n")
                logs.append("Date: ${SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Date())}\n")
                
                text = logs.toString()
            }
            
            val scrollView = ScrollView(this)
            scrollView.addView(textView)
            
            consoleDialog = AlertDialog.Builder(this)
                .setTitle("Console Debug")
                .setView(scrollView)
                .setPositiveButton("Fermer") { dialog, _ ->
                    dialog.dismiss()
                    consoleVisible = false
                }
                .create()
            
            consoleDialog?.show()
            
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
