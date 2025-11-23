package com.stopaddict

/**
 * Logger "par classe" qui délègue tout au logger central StopAddictLogger.
 *
 * Usage existant conservé :
 *   private val logger = AppLogger("MainActivity")
 *   logger.d("message")
 *   logger.e("message", e)
 *
 * => Rien à changer dans les fichiers qui l’utilisent.
 */
class AppLogger(private val tag: String) {

    fun d(message: String) {
        StopAddictLogger.d(tag, message)
    }

    fun e(message: String, throwable: Throwable? = null) {
        StopAddictLogger.e(tag, message, throwable)
    }

    fun w(message: String) {
        StopAddictLogger.w(tag, message)
    }

    fun i(message: String) {
        StopAddictLogger.i(tag, message)
    }
}
