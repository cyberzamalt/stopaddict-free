package com.stopaddict

import android.content.Context
import java.util.Locale

class ConfigLangue(private val context: Context) {

    companion object {
        private const val PREFS_NAME = "stopaddict_prefs"
        private const val KEY_LANGUE = "langue"

        // Langues supportées (codes cohérents avec ReglagesFragment)
        private val LANGUES_DISPONIBLES = setOf(
            "FR", "EN", "ES", "PT", "DE", "IT", "RU", "AR", "HI", "JA",
            "NL", "ZH", "ZHT"
        )

        // Map code -> Locale
        private val LOCALE_MAP: Map<String, Locale> = mapOf(
            "FR" to Locale("fr", "FR"),
            "EN" to Locale.ENGLISH,
            "ES" to Locale("es", "ES"),
            "PT" to Locale("pt", "PT"),
            "DE" to Locale("de", "DE"),
            "IT" to Locale("it", "IT"),
            "RU" to Locale("ru", "RU"),
            "AR" to Locale("ar"),
            "HI" to Locale("hi", "IN"),
            "JA" to Locale.JAPANESE,
            "NL" to Locale("nl", "NL"),
            // ZH = chinois simplifié, ZHT = chinois traditionnel
            "ZH" to Locale.SIMPLIFIED_CHINESE,
            "ZHT" to Locale.TRADITIONAL_CHINESE
        )
    }

    fun getLangue(): String {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val saved = prefs.getString(KEY_LANGUE, "FR") ?: "FR"
        return if (LANGUES_DISPONIBLES.contains(saved)) saved else "FR"
    }

    fun setLangue(code: String) {
        val safe = if (LANGUES_DISPONIBLES.contains(code)) code else "FR"
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_LANGUE, safe).apply()
    }

    fun getLocale(): Locale {
        return LOCALE_MAP[getLangue()] ?: Locale("fr", "FR")
    }
}
