package com.stopaddict

import android.content.Context
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class ExportLimiter(private val context: Context) {

    companion object {
        private const val PREFS_NAME = "StopAddict"
        private const val KEY_LAST_EXPORT_DATE = "last_export_date"
        private const val KEY_LAST_IMPORT_DATE = "last_import_date"
        private const val KEY_EXPORT_COUNT = "export_count_today"
        private const val KEY_IMPORT_COUNT = "import_count_today"
        private const val TAG = "ExportLimiter"
        
        // Limites version gratuite
        private const val MAX_EXPORT_PER_DAY = 0
        private const val MAX_IMPORT_PER_DAY = 0
    }

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    
    fun importDesactive(): Boolean = MAX_IMPORT_PER_DAY <= 0

    /**
     * Vérifie si l'utilisateur peut exporter aujourd'hui
     */
    fun canExport(): Boolean {
        return try {
            val today = dateFormat.format(Date())
            val lastExportDate = prefs.getString(KEY_LAST_EXPORT_DATE, "") ?: ""
            
            // Si dernière exportation n'est pas aujourd'hui, reset compteur
            if (lastExportDate != today) {
                prefs.edit()
                    .putString(KEY_LAST_EXPORT_DATE, today)
                    .putInt(KEY_EXPORT_COUNT, 0)
                    .apply()
                Log.d(TAG, "Nouveau jour détecté, compteur export réinitialisé")
            }
            
            val exportCount = prefs.getInt(KEY_EXPORT_COUNT, 0)
            val canExport = exportCount < MAX_EXPORT_PER_DAY
            
            Log.d(TAG, "Vérification export: $exportCount/$MAX_EXPORT_PER_DAY - Autorisé: $canExport")
            canExport
        } catch (e: Exception) {
            Log.e(TAG, "Erreur vérification export: ${e.message}")
            false
        }
    }

    /**
     * Enregistre une exportation
     */
    fun recordExport(): Boolean {
        return try {
            if (!canExport()) {
                Log.w(TAG, "Limite export atteinte pour aujourd'hui")
                return false
            }
            
            val today = dateFormat.format(Date())
            val currentCount = prefs.getInt(KEY_EXPORT_COUNT, 0)
            
            prefs.edit()
                .putString(KEY_LAST_EXPORT_DATE, today)
                .putInt(KEY_EXPORT_COUNT, currentCount + 1)
                .apply()
            
            Log.d(TAG, "Export enregistré: ${currentCount + 1}/$MAX_EXPORT_PER_DAY")
            true
        } catch (e: Exception) {
            Log.e(TAG, "Erreur enregistrement export: ${e.message}")
            false
        }
    }

    /**
     * Vérifie si l'utilisateur peut importer aujourd'hui
     */
    fun canImport(): Boolean {
        return try {
            val today = dateFormat.format(Date())
            val lastImportDate = prefs.getString(KEY_LAST_IMPORT_DATE, "") ?: ""
            
            // Si dernière importation n'est pas aujourd'hui, reset compteur
            if (lastImportDate != today) {
                prefs.edit()
                    .putString(KEY_LAST_IMPORT_DATE, today)
                    .putInt(KEY_IMPORT_COUNT, 0)
                    .apply()
                Log.d(TAG, "Nouveau jour détecté, compteur import réinitialisé")
            }
            
            val importCount = prefs.getInt(KEY_IMPORT_COUNT, 0)
            val canImport = importCount < MAX_IMPORT_PER_DAY
            
            Log.d(TAG, "Vérification import: $importCount/$MAX_IMPORT_PER_DAY - Autorisé: $canImport")
            canImport
        } catch (e: Exception) {
            Log.e(TAG, "Erreur vérification import: ${e.message}")
            false
        }
    }

    /**
     * Enregistre une importation
     */
    fun recordImport(): Boolean {
        return try {
            if (!canImport()) {
                Log.w(TAG, "Limite import atteinte pour aujourd'hui")
                return false
            }
            
            val today = dateFormat.format(Date())
            val currentCount = prefs.getInt(KEY_IMPORT_COUNT, 0)
            
            prefs.edit()
                .putString(KEY_LAST_IMPORT_DATE, today)
                .putInt(KEY_IMPORT_COUNT, currentCount + 1)
                .apply()
            
            Log.d(TAG, "Import enregistré: ${currentCount + 1}/$MAX_IMPORT_PER_DAY")
            true
        } catch (e: Exception) {
            Log.e(TAG, "Erreur enregistrement import: ${e.message}")
            false
        }
    }

    /**
     * Retourne le nombre d'exports restants aujourd'hui
     */
    fun getRemainingExports(): Int {
        val count = prefs.getInt(KEY_EXPORT_COUNT, 0)
        return (MAX_EXPORT_PER_DAY - count).coerceAtLeast(0)
    }

    /**
     * Retourne le nombre d'imports restants aujourd'hui
     */
    fun getRemainingImports(): Int {
        val count = prefs.getInt(KEY_IMPORT_COUNT, 0)
        return (MAX_IMPORT_PER_DAY - count).coerceAtLeast(0)
    }

    // ==================== ALIAS FRANÇAIS ====================

    /**
     * Alias français pour canExport()
     */
    fun peutExporter(): Boolean = canExport()

    /**
     * Alias français pour recordExport()
     */
    fun enregistrerExport(): Boolean = recordExport()

    /**
     * Alias français pour canImport()
     */
    fun peutImporter(): Boolean = canImport()

    /**
     * Alias français pour recordImport()
     */
    fun enregistrerImport(): Boolean = recordImport()
}
