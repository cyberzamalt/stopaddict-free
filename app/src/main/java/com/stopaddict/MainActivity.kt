package com.stopaddict

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var headerTextView: TextView
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var pubManager: PubManager
    private var clickCount = 0
    private var lastClickTime = 0L
    private var consoleVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialisation
        headerTextView = findViewById(R.id.header_text)
        bottomNav = findViewById(R.id.bottom_navigation)

        // Vérification avertissement majorité
        checkAgeWarning()

        // Configuration navigation
        setupBottomNavigation()

        // Affichage date/heure
        updateDateTime()

        // Console debug (5 clics)
        headerTextView.setOnClickListener {
            handleConsoleDebug()
        }

        // Initialisation pub AdMob
        pubManager = PubManager(this)
        pubManager.loadBanner(findViewById(R.id.ad_container))

        // Fragment par défaut : Accueil
        if (savedInstanceState == null) {
            loadFragment(AccueilFragment())
        }
    }

    private fun checkAgeWarning() {
        val prefs = getSharedPreferences("StopAddict", MODE_PRIVATE)
        val warningShown = prefs.getBoolean("warning_shown", false)

        if (!warningShown) {
            showAgeWarningDialog()
        }
    }

    private fun showAgeWarningDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_age_warning, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        // Gestion boutons (voir layout XML pour IDs)
        dialogView.findViewById<View>(R.id.btn_quit).setOnClickListener {
            finish()
        }

        dialogView.findViewById<View>(R.id.btn_accept).setOnClickListener {
            val checkbox = dialogView.findViewById<View>(R.id.checkbox_age)
            val checkboxNoShow = dialogView.findViewById<View>(R.id.checkbox_no_show)
            
            // Logique validation (simplifié, à compléter dans le layout)
            getSharedPreferences("StopAddict", MODE_PRIVATE)
                .edit()
                .putBoolean("warning_shown", true)
                .apply()
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun setupBottomNavigation() {
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_accueil -> loadFragment(AccueilFragment())
                R.id.nav_stats -> loadFragment(StatsFragment())
                R.id.nav_calendrier -> loadFragment(CalendrierFragment())
                R.id.nav_habitudes -> loadFragment(HabitudesFragment())
                R.id.nav_reglages -> loadFragment(ReglagesFragment())
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
        return true
    }

    private fun updateDateTime() {
        val dateFormat = SimpleDateFormat("EEEE dd MMMM yyyy - HH:mm", Locale.getDefault())
        val currentDate = dateFormat.format(Date())
        headerTextView.text = "StopAddict\n$currentDate"
    }

    private fun handleConsoleDebug() {
        val currentTime = System.currentTimeMillis()

        // Reset si > 2 secondes entre clics
        if (currentTime - lastClickTime > 2000) {
            clickCount = 0
        }

        clickCount++
        lastClickTime = currentTime

        if (clickCount == 5) {
            toggleConsoleDebug()
            clickCount = 0
        }
    }

    private fun toggleConsoleDebug() {
        consoleVisible = !consoleVisible
        
        if (consoleVisible) {
            // Afficher console (à implémenter avec overlay)
            showConsoleOverlay()
        } else {
            // Masquer console
            hideConsoleOverlay()
        }
    }

    private fun showConsoleOverlay() {
        // TODO: Implémenter popup console avec logs
        // Pour l'instant, simple toast
        android.widget.Toast.makeText(this, "Console Debug Activée", android.widget.Toast.LENGTH_SHORT).show()
    }

    private fun hideConsoleOverlay() {
        android.widget.Toast.makeText(this, "Console Debug Désactivée", android.widget.Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        updateDateTime()
    }
}
