package com.stopaddict

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import java.util.Locale

class ConfigLangue(private val context: Context) {

    companion object {
        private const val PREFS_NAME = "stopaddict_prefs"
        private const val KEY_LANGUE = "langue"

        // Langues disponibles (doit matcher ReglagesFragment + ReglagesLangues)
        val LANGUES_DISPONIBLES = arrayOf(
            "FR", "EN", "ES", "PT", "DE", "IT", "RU", "AR", "HI", "JA",
            "NL", "ZH", "ZHT"
        )

        // Mapping code -> Locale
        val LOCALE_MAP: Map<String, Locale> = mapOf(
            "FR" to Locale("fr"),
            "EN" to Locale("en"),
            "ES" to Locale("es"),
            "PT" to Locale("pt"),
            "DE" to Locale("de"),
            "IT" to Locale("it"),
            "RU" to Locale("ru"),
            "AR" to Locale("ar"),
            "HI" to Locale("hi"),
            "JA" to Locale("ja"),
            "NL" to Locale("nl"),
            // Chinois simplifié / traditionnel
            "ZH" to Locale.SIMPLIFIED_CHINESE,   // zh-CN
            "ZHT" to Locale.TRADITIONAL_CHINESE  // zh-TW
        )

        // Pour couvrir le cas où MainActivity appelle ConfigLangue.initialiserLangue(...)
        fun initialiserLangue(context: Context) {
            ConfigLangue(context).initialiserLangue()
        }
    }

    fun getLangue(): String {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_LANGUE, "FR") ?: "FR"
    }

    fun setLangue(code: String) {
        val safe = if (LANGUES_DISPONIBLES.contains(code)) code else "FR"
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_LANGUE, safe)
            .apply()

        appliquerLocale(safe)
    }

    // Pour couvrir le cas où MainActivity fait configLangue.initialiserLangue()
    fun initialiserLangue() {
        appliquerLocale(getLangue())
    }

    private fun appliquerLocale(code: String) {
        val locale = LOCALE_MAP[code] ?: Locale("fr")
        Locale.setDefault(locale)

        val res = context.resources
        val config = Configuration(res.configuration)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocale(locale)
        } else {
            @Suppress("DEPRECATION")
            config.locale = locale
        }

        @Suppress("DEPRECATION")
        res.updateConfiguration(config, res.displayMetrics)
    }
}
