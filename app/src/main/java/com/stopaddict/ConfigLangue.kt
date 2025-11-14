package com.stopaddict

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.util.Log
import java.util.*

class ConfigLangue(private val context: Context) {

    companion object {
        private const val PREFS_NAME = "StopAddict"
        private const val KEY_LANGUE = "langue"
        private const val TAG = "ConfigLangue"

        // Langues disponibles
        val LANGUES_DISPONIBLES = mapOf(
            "FR" to "Français",
            "EN" to "English",
            "ES" to "Español",
            "PT" to "Português",
            "DE" to "Deutsch",
            "IT" to "Italiano",
            "RU" to "Русский",
            "AR" to "العربية",
            "HI" to "हिन्दी",
            "JA" to "日本語"
        )

        // Correspondance codes langue Android
        private val LOCALE_MAP = mapOf(
            "FR" to "fr",
            "EN" to "en",
            "ES" to "es",
            "PT" to "pt",
            "DE" to "de",
            "IT" to "it",
            "RU" to "ru",
            "AR" to "ar",
            "HI" to "hi",
            "JA" to "ja"
        )
    }

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    /**
     * Récupère la langue actuelle
     */
    fun getLangue(): String {
        val langue = prefs.getString(KEY_LANGUE, "FR") ?: "FR"
        Log.d(TAG, "Langue actuelle: $langue")
        return langue
    }

    /**
     * Définit une nouvelle langue et redémarre l'application
     */
    fun setLangue(codeLangue: String) {
        if (!LANGUES_DISPONIBLES.containsKey(codeLangue)) {
            Log.e(TAG, "Code langue invalide: $codeLangue")
            return
        }

        try {
            // Sauvegarder dans SharedPreferences
            prefs.edit().putString(KEY_LANGUE, codeLangue).apply()
            Log.d(TAG, "Langue sauvegardée: $codeLangue")

            // Appliquer la locale immédiatement
            appliquerLocale(codeLangue)

            // Redémarrer l'application
            redemarrerApplication()
        } catch (e: Exception) {
            Log.e(TAG, "Erreur changement langue: ${e.message}")
        }
    }

    /**
     * Applique la locale au contexte
     */
    fun appliquerLocale(codeLangue: String = getLangue()) {
        try {
            val localeCode = LOCALE_MAP[codeLangue] ?: "fr"
            val locale = Locale(localeCode)
            Locale.setDefault(locale)

            val config = Configuration(context.resources.configuration)
            config.setLocale(locale)

            // Support RTL pour arabe
            if (codeLangue == "AR") {
                config.setLayoutDirection(locale)
            }

            context.resources.updateConfiguration(config, context.resources.displayMetrics)
            Log.d(TAG, "Locale appliquée: $localeCode")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur application locale: ${e.message}")
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
            
            Log.d(TAG, "Application redémarrée pour changement de langue")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur redémarrage application: ${e.message}")
        }
    }

    /**
     * Initialise la langue au démarrage de l'application
     */
    fun initialiserLangue() {
        val langue = getLangue()
        appliquerLocale(langue)
        Log.d(TAG, "Langue initialisée: $langue")
    }

    /**
     * Vérifie si une langue est RTL (Right-to-Left)
     */
    fun isRTL(codeLangue: String = getLangue()): Boolean {
        return codeLangue == "AR"
    }
}
