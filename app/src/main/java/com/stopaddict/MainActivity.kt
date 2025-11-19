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
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Avertissement")
            builder.setMessage("Stop Addict est réservé aux personnes majeures (18 ans et plus).")
            builder.setPositiveButton("J'accepte") { _, _ ->
                val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                prefs.edit().apply {
                    putBoolean(PREF_AGE_ACCEPTED, true)
                    putBoolean(PREF_WARNING_SHOWN, true)
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
            3 -> if (langue == "FR") "Habitudes" else "Habits"
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
