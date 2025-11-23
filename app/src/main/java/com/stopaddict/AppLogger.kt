package com.stopaddict

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Collections

/**
 * Logger centralisé pour l'application.
 * - Envoie tout dans Logcat (Log.d / Log.e / etc.)
 * - Garde aussi une copie en mémoire pour la console interne.
 */
object AppLogger {

    private const val GLOBAL_TAG = "StopAddict"
    private const val MAX_ENTRIES = 1000  // on garde les N derniers logs

    // Liste thread-safe de chaînes de log déjà formatées
    private val logEntries = Collections.synchronizedList(mutableListOf<String>())

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())

    // ---------- API publique ----------

    fun d(tag: String, message: String) {
        try {
            Log.d(tag, message)
            addEntry("DEBUG", tag, message, null)
        } catch (_: Exception) {
            // on évite de faire planter l'app à cause du logger
        }
    }

    fun i(tag: String, message: String) {
        try {
            Log.i(tag, message)
            addEntry("INFO", tag, message, null)
        } catch (_: Exception) {
        }
    }

    fun w(tag: String, message: String) {
        try {
            Log.w(tag, message)
            addEntry("WARN", tag, message, null)
        } catch (_: Exception) {
        }
    }

    fun e(tag: String, message: String, throwable: Throwable? = null) {
        try {
            Log.e(tag, message, throwable)
            addEntry("ERROR", tag, message, throwable)
        } catch (_: Exception) {
        }
    }

    /**
     * Retourne tous les logs sous forme de gros texte,
     * prêt à être affiché dans la console ou copié-collé.
     */
    fun getLogsAsText(): String {
        return try {
            synchronized(logEntries) {
                if (logEntries.isEmpty()) {
                    "Aucun log disponible pour le moment."
                } else {
                    logEntries.joinToString("\n")
                }
            }
        } catch (e: Exception) {
            "Erreur AppLogger.getLogsAsText(): ${e.message}"
        }
    }

    /**
     * Efface tous les logs en mémoire (n’affecte pas Logcat).
     */
    fun clear() {
        try {
            synchronized(logEntries) {
                logEntries.clear()
            }
            Log.i(GLOBAL_TAG, "AppLogger.clear() : logs internes effacés")
        } catch (_: Exception) {
        }
    }

    // ---------- Interne ----------

    private fun addEntry(level: String, tag: String, message: String, throwable: Throwable?) {
        val timestamp = try {
            dateFormat.format(Date())
        } catch (_: Exception) {
            "0000-00-00 00:00:00.000"
        }

        val baseLine = "[$timestamp] [$level] [$tag] $message"

        val fullLine = if (throwable != null) {
            val stackTrace = throwable.stackTraceToString()
            "$baseLine\n$stackTrace"
        } else {
            baseLine
        }

        synchronized(logEntries) {
            logEntries.add(fullLine)
            if (logEntries.size > MAX_ENTRIES) {
                // on supprime les plus anciens
                val toRemove = logEntries.size - MAX_ENTRIES
                for (i in 0 until toRemove) {
                    if (logEntries.isNotEmpty()) {
                        logEntries.removeAt(0)
                    } else {
                        break
                    }
                }
            }
        }
    }
}
