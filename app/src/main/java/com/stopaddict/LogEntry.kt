package com.stopaddict

/**
 * Représente une entrée de log interne.
 * (Actuellement optionnel : utile si on veut plus tard afficher/filtrer
 * les logs autrement que sous forme de texte brut.)
 */
data class LogEntry(
    val timestamp: Long,
    val level: String,   // ex : "DEBUG", "INFO", "WARN", "ERROR"
    val tag: String,     // ex : "MainActivity", "DatabaseHelper"
    val message: String,
    val throwable: String? = null
)
