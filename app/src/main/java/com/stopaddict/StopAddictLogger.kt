package com.stopaddict

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object StopAddictLogger {

    // Taille max du buffer mémoire
    private const val MAX_LOG_ENTRIES = 2000

    enum class LogLevel {
        DEBUG, INFO, WARN, ERROR
    }

    data class LogEntry(
        val timestamp: Long,
        val level: LogLevel,
        val tag: String,
        val message: String,
        val throwable: Throwable? = null
    )

    // Buffer FIFO en mémoire
    private val logBuffer = ArrayDeque<LogEntry>()

    @Synchronized
    fun log(
        level: LogLevel,
        tag: String,
        message: String,
        throwable: Throwable? = null
    ) {
        // 1) On garde une trace en mémoire
        val entry = LogEntry(
            timestamp = System.currentTimeMillis(),
            level = level,
            tag = tag,
            message = message,
            throwable = throwable
        )
        logBuffer.addLast(entry)

        // On garde la taille sous contrôle
        while (logBuffer.size > MAX_LOG_ENTRIES) {
            logBuffer.removeFirst()
        }

        // 2) On relaie vers Logcat (comportement Android classique)
        when (level) {
            LogLevel.DEBUG -> {
                if (throwable != null) {
                    Log.d(tag, message, throwable)
                } else {
                    Log.d(tag, message)
                }
            }
            LogLevel.INFO -> {
                if (throwable != null) {
                    Log.i(tag, message, throwable)
                } else {
                    Log.i(tag, message)
                }
            }
            LogLevel.WARN -> {
                if (throwable != null) {
                    Log.w(tag, message, throwable)
                } else {
                    Log.w(tag, message)
                }
            }
            LogLevel.ERROR -> {
                if (throwable != null) {
                    Log.e(tag, message, throwable)
                } else {
                    Log.e(tag, message)
                }
            }
        }
    }

    // Raccourcis pratiques
    fun d(tag: String, message: String) {
        log(LogLevel.DEBUG, tag, message, null)
    }

    fun i(tag: String, message: String) {
        log(LogLevel.INFO, tag, message, null)
    }

    fun w(tag: String, message: String) {
        log(LogLevel.WARN, tag, message, null)
    }

    fun e(tag: String, message: String, throwable: Throwable? = null) {
        log(LogLevel.ERROR, tag, message, throwable)
    }

    /**
     * Retourne tous les logs formatés en texte brut,
     * prêt à être affiché dans une console ou exporté.
     */
    @Synchronized
    fun getAllLogsAsText(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
        val sb = StringBuilder()

        for (entry in logBuffer) {
            val time = sdf.format(Date(entry.timestamp))
            val levelChar = when (entry.level) {
                LogLevel.DEBUG -> "D"
                LogLevel.INFO -> "I"
                LogLevel.WARN -> "W"
                LogLevel.ERROR -> "E"
            }

            sb.append("[$time][$levelChar/${entry.tag}] ${entry.message}\n")

            if (entry.throwable != null) {
                sb.append(Log.getStackTraceString(entry.throwable))
                sb.append("\n")
            }
        }

        return sb.toString()
    }

    /**
     * Vide complètement le buffer mémoire.
     */
    @Synchronized
    fun clear() {
        logBuffer.clear()
    }
}
