package com.stopaddict

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import java.util.*

class ConfigLangue(private val context: Context) {

    companion object {
    private const val TAG = "ReglagesFragment"
    private const val REQUEST_CODE_EXPORT = 1001
    private const val REQUEST_CODE_IMPORT = 1002
    private const val PREF_MODE_CIGARETTE = "mode_cigarette"
    private const val PREF_NB_CIGARETTES_ROULEES = "nb_cigarettes_roulees"
    private const val PREF_NB_CIGARETTES_TUBEES = "nb_cigarettes_tubees"

    // Langues supportées
    private val LANGUES_CODES = arrayOf(
        "FR", "EN", "ES", "PT", "DE", "IT", "RU", "AR", "HI", "JA",
        "ZH_CN", "ZH_TW", "NL"
    )

    // Devises affichées
    private val DEVISES_AFFICHEES = arrayOf(
        "EUR (€)", "USD ($)", "GBP (£)", "JPY (¥)",
        "CHF (CHF)", "CAD (C$)", "AUD (A$)",
        "BRL (R$)", "INR (₹)", "RUB (₽)"
    )
}

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    /**
     * Récupère la langue actuelle
     */
    fun getLangue(): String {
        val langue = prefs.getString(KEY_LANGUE, "FR") ?: "FR"
        StopAddictLogger.d(TAG, "Langue actuelle: $langue")
        return langue
    }

    /**
     * Définit une nouvelle langue et redémarre l'application
     */
    fun setLangue(codeLangue: String) {
        if (!LANGUES_DISPONIBLES.containsKey(codeLangue)) {
            StopAddictLogger.e(TAG, "Code langue invalide: $codeLangue")
            return
        }

        try {
            // Sauvegarder dans SharedPreferences
            prefs.edit().putString(KEY_LANGUE, codeLangue).apply()
            StopAddictLogger.d(TAG, "Langue sauvegardée: $codeLangue")

            // Appliquer la locale immédiatement
            appliquerLocale(codeLangue)

            // Redémarrer l'application
            redemarrerApplication()
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, "Erreur changement langue", e)
        }
    }

    /**
     * Applique la locale au contexte
     */
     @Suppress("DEPRECATION")
    fun appliquerLocale(codeLangue: String = getLangue()) {
        try {
            val localeCode = LOCALE_MAP[codeLangue] ?: "fr"

            val locale = when (codeLangue) {
                "ZHS" -> Locale("zh", "CN") // chinois simplifié
                "ZHT" -> Locale("zh", "TW") // chinois traditionnel
                else  -> Locale(localeCode) // cas standards
            }
            
            Locale.setDefault(locale)

            val config = Configuration(context.resources.configuration)
            config.setLocale(locale)

            // Support RTL pour arabe
            if (codeLangue == "AR") {
                config.setLayoutDirection(locale)
            }

            context.resources.updateConfiguration(config, context.resources.displayMetrics)
            StopAddictLogger.d(TAG, "Locale appliquée: $localeCode")
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, "Erreur application locale", e)
        }
    }

    /**
     * Redémarre l'application pour appliquer la nouvelle langue
     */
    private fun redemarrerApplication() {
        try {
            val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
            intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            
            // Fermer l'activité actuelle
            if (context is MainActivity) {
                context.finish()
            }
            
            StopAddictLogger.d(TAG, "Application redémarrée pour changement de langue")
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, "Erreur redémarrage application", e)
        }
    }

    /**
     * Initialise la langue au démarrage de l'application
     */
    fun initialiserLangue() {
        val langue = getLangue()
        appliquerLocale(langue)
        StopAddictLogger.d(TAG, "Langue initialisée: $langue")
    }

    /**
     * Vérifie si une langue est RTL (Right-to-Left)
     */
    fun isRTL(codeLangue: String = getLangue()): Boolean {
        return codeLangue == "AR"
    }
}
