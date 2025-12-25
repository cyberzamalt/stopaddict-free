package com.stopaddict

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "StopAddict.db"
        private const val DATABASE_VERSION = 4
        private const val TAG = "DatabaseHelper"

        // Table consommations
        private const val TABLE_CONSOMMATIONS = "consommations"
        private const val COL_ID = "id"
        private const val COL_TYPE = "type"
        private const val COL_QUANTITE = "quantite"
        private const val COL_DATE_HEURE = "date_heure"
        private const val COL_ACTIF = "actif"

        // Table habitudes
        private const val TABLE_HABITUDES = "habitudes"
        private const val COL_MAX_JOURNALIER = "max_journalier"

        // Table dates_objectifs
        private const val TABLE_DATES = "dates_objectifs"
        private const val COL_DATE_REDUCTION = "date_reduction"
        private const val COL_DATE_ARRET = "date_arret"
        private const val COL_DATE_REUSSITE = "date_reussite"

        // Table couts
        private const val TABLE_COUTS = "couts"
        private const val COL_PRIX_PAQUET = "prix_paquet"
        private const val COL_NB_CIGARETTES = "nb_cigarettes"
        private const val COL_PRIX_TABAC = "prix_tabac"
        private const val COL_PRIX_TABAC_TUBE = "prix_tabac_tube"
        private const val COL_PRIX_FEUILLES = "prix_feuilles"
        private const val COL_NB_FEUILLES = "nb_feuilles"
        private const val COL_PRIX_FILTRES = "prix_filtres"
        private const val COL_NB_FILTRES = "nb_filtres"
        private const val COL_PRIX_TUBES = "prix_tubes"
        private const val COL_NB_TUBES = "nb_tubes"
        private const val COL_PRIX_GRAMME = "prix_gramme"
        private const val COL_GRAMME_PAR_JOINT = "gramme_par_joint"
        private const val COL_PRIX_VERRE = "prix_verre"
        private const val COL_UNITE_CL = "unite_cl"

        // Table preferences
        private const val TABLE_PREFERENCES = "preferences"
        private const val COL_PRENOM = "prenom"
        private const val COL_LANGUE = "langue"
        private const val COL_DEVISE = "devise"
        private const val COL_CATEGORIES_ACTIVES = "categories_actives"

        // Préférences supplémentaires
        private const val COL_MODE_CIGARETTE = "mode_cigarette"
        private const val COL_GRAMME_PAR_JOINT_PREF = "gramme_par_joint"
        private const val COL_UNITE_CL_ALCOOL_GLOBAL = "unite_cl_alcool_global"
        private const val COL_UNITE_CL_BIERE = "unite_cl_biere"
        private const val COL_UNITE_CL_LIQUEUR = "unite_cl_liqueur"
        private const val COL_UNITE_CL_ALCOOL_FORT = "unite_cl_alcool_fort"

        // 🔹 Nouveau : prefs liées au roulage / tubage
        private const val COL_NB_CIGARETTES_ROULEES_PREF = "nb_cigarettes_roulees"
        private const val COL_NB_CIGARETTES_TUBEES_PREF = "nb_cigarettes_tubees"

        // Types de consommations
        const val TYPE_CIGARETTE = "cigarette"
        const val TYPE_JOINT = "joint"
        const val TYPE_ALCOOL_GLOBAL = "alcool_global"
        const val TYPE_BIERE = "biere"
        const val TYPE_LIQUEUR = "liqueur"
        const val TYPE_ALCOOL_FORT = "alcool_fort"
    }

    override fun onCreate(db: SQLiteDatabase) {
        try {
            // Table consommations
            db.execSQL("""
                CREATE TABLE $TABLE_CONSOMMATIONS (
                    $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                    $COL_TYPE TEXT NOT NULL,
                    $COL_QUANTITE INTEGER NOT NULL DEFAULT 1,
                    $COL_DATE_HEURE TEXT NOT NULL,
                    $COL_ACTIF INTEGER NOT NULL DEFAULT 1
                )
            """)

            // Table habitudes
            db.execSQL("""
                CREATE TABLE $TABLE_HABITUDES (
                    $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                    $COL_TYPE TEXT NOT NULL UNIQUE,
                    $COL_MAX_JOURNALIER REAL DEFAULT 0
                )
            """)

            // Table dates_objectifs
            db.execSQL("""
                CREATE TABLE $TABLE_DATES (
                    $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                    $COL_TYPE TEXT NOT NULL UNIQUE,
                    $COL_DATE_REDUCTION TEXT,
                    $COL_DATE_ARRET TEXT,
                    $COL_DATE_REUSSITE TEXT
                )
            """)

            // Table couts (une ligne par type)
            db.execSQL("""
                CREATE TABLE $TABLE_COUTS (
                    $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                    $COL_TYPE TEXT NOT NULL UNIQUE,
                    $COL_PRIX_PAQUET REAL DEFAULT 0,
                    $COL_NB_CIGARETTES INTEGER DEFAULT 0,
                    $COL_PRIX_TABAC REAL DEFAULT 0,
                    $COL_PRIX_FEUILLES REAL DEFAULT 0,
                    $COL_PRIX_TABAC_TUBE REAL DEFAULT 0,
                    $COL_NB_FEUILLES INTEGER DEFAULT 0,
                    $COL_PRIX_FILTRES REAL DEFAULT 0,
                    $COL_NB_FILTRES INTEGER DEFAULT 0,
                    $COL_PRIX_TUBES REAL DEFAULT 0,
                    $COL_NB_TUBES INTEGER DEFAULT 0,
                    $COL_PRIX_GRAMME REAL DEFAULT 0,
                    $COL_GRAMME_PAR_JOINT REAL DEFAULT 0,
                    $COL_PRIX_VERRE REAL DEFAULT 0,
                    $COL_UNITE_CL INTEGER DEFAULT 0
                )
            """)
            
        // Table preferences
            db.execSQL("""
    CREATE TABLE $TABLE_PREFERENCES (
        $COL_ID INTEGER PRIMARY KEY CHECK ($COL_ID = 1),
        $COL_PRENOM TEXT DEFAULT '',
        $COL_LANGUE TEXT DEFAULT 'FR',
        $COL_DEVISE TEXT DEFAULT 'EUR',
        $COL_CATEGORIES_ACTIVES TEXT DEFAULT '{"cigarette":true,"joint":true,"alcool_global":true,"biere":false,"liqueur":false,"alcool_fort":false}',
        $COL_MODE_CIGARETTE TEXT DEFAULT 'classique',
        $COL_GRAMME_PAR_JOINT_PREF TEXT DEFAULT '0',
        $COL_UNITE_CL_ALCOOL_GLOBAL TEXT DEFAULT '0',
        $COL_UNITE_CL_BIERE TEXT DEFAULT '0',
        $COL_UNITE_CL_LIQUEUR TEXT DEFAULT '0',
        $COL_UNITE_CL_ALCOOL_FORT TEXT DEFAULT '0',
        $COL_NB_CIGARETTES_ROULEES_PREF TEXT DEFAULT '0',
        $COL_NB_CIGARETTES_TUBEES_PREF TEXT DEFAULT '0'
    )
""")

            // Insertion préférences par défaut
            db.execSQL("INSERT INTO $TABLE_PREFERENCES ($COL_ID) VALUES (1)")

            // Initialisation habitudes et dates pour chaque type
            val types = listOf(TYPE_CIGARETTE, TYPE_JOINT, TYPE_ALCOOL_GLOBAL, TYPE_BIERE, TYPE_LIQUEUR, TYPE_ALCOOL_FORT)
            types.forEach { type ->
                db.execSQL("INSERT INTO $TABLE_HABITUDES ($COL_TYPE) VALUES ('$type')")
                db.execSQL("INSERT INTO $TABLE_DATES ($COL_TYPE) VALUES ('$type')")
                db.execSQL("INSERT INTO $TABLE_COUTS ($COL_TYPE) VALUES ('$type')")
            }

            Log.d(TAG, "Base de données créée avec succès")
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, "Erreur création base de données", e)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        try {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_CONSOMMATIONS")
            db.execSQL("DROP TABLE IF EXISTS $TABLE_HABITUDES")
            db.execSQL("DROP TABLE IF EXISTS $TABLE_DATES")
            db.execSQL("DROP TABLE IF EXISTS $TABLE_COUTS")
            db.execSQL("DROP TABLE IF EXISTS $TABLE_PREFERENCES")
            onCreate(db)
            Log.d(TAG, "Base de données mise à jour: v$oldVersion -> v$newVersion")
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, "Erreur mise à jour base de données", e)
        }
    }
    
    // ==================== CONSOMMATIONS ====================

    fun ajouterConsommation(type: String, quantite: Int = 1, dateSpecifique: String? = null): Boolean {
        return try {
            val db = writableDatabase
            val dateHeure = if (dateSpecifique != null) {
                "$dateSpecifique 12:00:00"
            } else {
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
            }
            
            val values = ContentValues().apply {
                put(COL_TYPE, type)
                put(COL_QUANTITE, quantite)
                put(COL_DATE_HEURE, dateHeure)
                put(COL_ACTIF, 1)
            }
            
            val result = db.insert(TABLE_CONSOMMATIONS, null, values)
            Log.d(TAG, "Consommation ajoutée: $type x$quantite à $dateHeure")
            result != -1L
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, "Erreur ajout consommation", e)
            false
        }
    }

    fun getProfilCompletionPercent(categoriesActives: Map<String, Boolean>): Int {
    val actives = categoriesActives.filterValues { it }.keys
    if (actives.isEmpty()) return 0

    val totalBlocs = actives.size * 3
    var blocsRemplis = 0

    for (type in actives) {

        // 1) COÛTS
        val couts = getCouts(type)
        if (couts.values.any { it > 0.0 }) blocsRemplis++

        // 2) HABITUDES
        if (getMaxJournalier(type) > 0) blocsRemplis++

        // 3) DATES
        val dates = getDatesObjectifs(type)
        if (dates.values.any { !it.isNullOrEmpty() }) blocsRemplis++
    }

    return ((blocsRemplis * 100) / totalBlocs).coerceIn(0, 100)
}

    fun retirerConsommation(type: String, dateSpecifique: String? = null): Boolean {
        return try {
            val db = writableDatabase
            val datePattern = if (dateSpecifique != null) {
                dateSpecifique
            } else {
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            }
            
            // Récupérer la dernière consommation du jour spécifié
            val cursor = db.query(
                TABLE_CONSOMMATIONS,
                arrayOf(COL_ID),
                "$COL_TYPE = ? AND $COL_DATE_HEURE LIKE ? AND $COL_ACTIF = 1",
                arrayOf(type, "$datePattern%"),
                null, null,
                "$COL_DATE_HEURE DESC",
                "1"
            )
            
            var success = false
            if (cursor.moveToFirst()) {
                val id = cursor.getInt(0)
                success = db.delete(TABLE_CONSOMMATIONS, "$COL_ID = ?", arrayOf(id.toString())) > 0
                Log.d(TAG, "Consommation retirée: $type (ID: $id)")
            }
            cursor.close()
            success
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, "Erreur retrait consommation", e)
            false
        }
    }

    fun getConsommationsJour(date: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())): Map<String, Int> {
        return try {
            val db = readableDatabase
            val result = mutableMapOf<String, Int>()
            
            val cursor = db.query(
                TABLE_CONSOMMATIONS,
                arrayOf(COL_TYPE, "SUM($COL_QUANTITE) as total"),
                "$COL_DATE_HEURE LIKE ? AND $COL_ACTIF = 1",
                arrayOf("$date%"),
                COL_TYPE, null, null
            )
            
            while (cursor.moveToNext()) {
                val type = cursor.getString(0)
                val total = cursor.getInt(1)
                result[type] = total
            }
            cursor.close()
            Log.d(TAG, "Consommations du $date: $result")
            result
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, "Erreur lecture consommations jour", e)
            emptyMap()
        }
    }

    fun getConsommationsSemaine(): Map<String, List<Int>> {
    Log.d(TAG, "getConsommationsSemaine() appelé - période = 7 jours")
    val result = getConsommationsPeriode(7)
    Log.d(
        TAG,
        "getConsommationsSemaine() terminé - types=${result.keys} nbJours=${result.values.firstOrNull()?.size ?: 0}"
    )
    return result
}

    fun getConsommationsMois(): Map<String, List<Int>> {
    Log.d(TAG, "getConsommationsMois() appelé - période = 30 jours")
    val result = getConsommationsPeriode(30)
    Log.d(
        TAG,
        "getConsommationsMois() terminé - types=${result.keys} nbJours=${result.values.firstOrNull()?.size ?: 0}"
    )
    return result
}

    fun getConsommationsAnnee(): Map<String, List<Int>> {
    Log.d(TAG, "getConsommationsAnnee() appelé - période = 365 jours")
    val result = getConsommationsPeriode(365)
    Log.d(
        TAG,
        "getConsommationsAnnee() terminé - types=${result.keys} nbJours=${result.values.firstOrNull()?.size ?: 0}"
    )
    return result
}

    private fun getConsommationsPeriode(jours: Int): Map<String, List<Int>> {
        return try {
            val db = readableDatabase
            val result = mutableMapOf<String, MutableList<Int>>()
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val calendar = Calendar.getInstance()
            
            // Initialiser toutes les catégories avec des zéros
            listOf(TYPE_CIGARETTE, TYPE_JOINT, TYPE_ALCOOL_GLOBAL, TYPE_BIERE, TYPE_LIQUEUR, TYPE_ALCOOL_FORT).forEach { type ->
                result[type] = MutableList(jours) { 0 }
            }
            
            // Récupérer les données
            for (i in 0 until jours) {
                calendar.time = Date()
                calendar.add(Calendar.DAY_OF_YEAR, -i)
                val date = dateFormat.format(calendar.time)
                
                val cursor = db.query(
                    TABLE_CONSOMMATIONS,
                    arrayOf(COL_TYPE, "SUM($COL_QUANTITE) as total"),
                    "$COL_DATE_HEURE LIKE ? AND $COL_ACTIF = 1",
                    arrayOf("$date%"),
                    COL_TYPE, null, null
                )
                
                while (cursor.moveToNext()) {
                    val type = cursor.getString(0)
                    val total = cursor.getInt(1)
                    result[type]?.set(jours - 1 - i, total)
                }
                cursor.close()
            }
            
            Log.d(TAG, "Consommations période $jours jours récupérées")
            result
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, "Erreur lecture consommations période", e)
            emptyMap()
        }
    }

    // ==================== HABITUDES ====================

    fun setMaxJournalier(type: String, max: Double): Boolean {
    return try {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_MAX_JOURNALIER, max)
        }
        val result = db.update(TABLE_HABITUDES, values, "$COL_TYPE = ?", arrayOf(type))
        Log.d(TAG, "Max journalier mis à jour pour $type: $max")
        result > 0
    } catch (e: Exception) {
        StopAddictLogger.e(TAG, "Erreur update max journalier", e)
        false
    }
}

/** Compat anciens appels (ReglagesFragment, etc.) */
fun setMaxJournalier(type: String, max: Int): Boolean {
    return setMaxJournalier(type, max.toDouble())
}

    fun getMaxJournalier(type: String): Double {
    return try {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_HABITUDES,
            arrayOf(COL_MAX_JOURNALIER),
            "$COL_TYPE = ?",
            arrayOf(type),
            null,
            null,
            null
        )

        var max = 0.0
        if (cursor.moveToFirst()) {
            max = cursor.getDouble(0)
        }
        cursor.close()
        max
    } catch (e: Exception) {
        StopAddictLogger.e(TAG, "Erreur lecture max journalier", e)
        0.0
    }
}

    // ==================== DATES OBJECTIFS ====================

fun setDatesObjectifs(
    type: String,
    dateReduction: String?,
    dateArret: String?,
    dateReussite: String?
): Boolean {
    return try {
        val db = writableDatabase
        val values = ContentValues().apply {
            // Réduction
            if (!dateReduction.isNullOrBlank()) {
                put(COL_DATE_REDUCTION, dateReduction)
            } else {
                putNull(COL_DATE_REDUCTION)
            }

            // Arrêt
            if (!dateArret.isNullOrBlank()) {
                put(COL_DATE_ARRET, dateArret)
            } else {
                putNull(COL_DATE_ARRET)
            }

            // Réussite
            if (!dateReussite.isNullOrBlank()) {
                put(COL_DATE_REUSSITE, dateReussite)
            } else {
                putNull(COL_DATE_REUSSITE)
            }
        }

        val result = db.update(TABLE_DATES, values, "$COL_TYPE = ?", arrayOf(type))
        Log.d(
            TAG,
            "Dates objectifs mises à jour pour $type : " +
                    "reduction=$dateReduction, arret=$dateArret, reussite=$dateReussite ($result ligne(s))"
        )
        result > 0
    } catch (e: Exception) {
        StopAddictLogger.e(TAG, "Erreur setDatesObjectifs", e)
        false
    }
}

    fun getDatesObjectifs(type: String): Map<String, String?> {
        return try {
            val db = readableDatabase
            val cursor = db.query(
                TABLE_DATES,
                arrayOf(COL_DATE_REDUCTION, COL_DATE_ARRET, COL_DATE_REUSSITE),
                "$COL_TYPE = ?",
                arrayOf(type),
                null, null, null
            )
            
            val result = mutableMapOf<String, String?>()
            if (cursor.moveToFirst()) {
                result["date_reduction"] = cursor.getString(0)
                result["date_arret"] = cursor.getString(1)
                result["date_reussite"] = cursor.getString(2)
            }
            cursor.close()
            result
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, "Erreur lecture dates objectifs", e)
            emptyMap()
        }
    }

    // ==================== COÛTS ====================

    fun setCouts(
    type: String,
    prixPaquet: Double,
    nbCigarettes: Double,
    prixTabac: Double,
    prixFeuilles: Double,
    nbFeuilles: Double,
    prixFiltres: Double,
    nbFiltres: Double,
    prixTubes: Double,
    nbTubes: Double,
    prixTabacTubes: Double,
    prixVerre: Double
): Boolean {
    return try {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_PRIX_PAQUET, prixPaquet)
            put(COL_NB_CIGARETTES, nbCigarettes.toInt())
            put(COL_PRIX_TABAC, prixTabac)
            put(COL_PRIX_TABAC_TUBE, prixTabacTubes)       // 🔹 nouveau
            put(COL_PRIX_FEUILLES, prixFeuilles)
            put(COL_NB_FEUILLES, nbFeuilles.toInt())
            put(COL_PRIX_FILTRES, prixFiltres)
            put(COL_NB_FILTRES, nbFiltres.toInt())
            put(COL_PRIX_TUBES, prixTubes)
            put(COL_NB_TUBES, nbTubes.toInt())

            if (type == TYPE_JOINT) {
                // Pour les joints : dernier param = prix du gramme
                put(COL_PRIX_GRAMME, prixVerre)
                put(COL_PRIX_VERRE, 0.0)
            } else {
                // Pour les autres types : dernier param = prix du verre
                put(COL_PRIX_VERRE, prixVerre)
            }
        }

        val result = db.update(TABLE_COUTS, values, "$COL_TYPE = ?", arrayOf(type))
        Log.d(TAG, "Coûts mis à jour pour $type: $result ligne(s)")
        result > 0
    } catch (e: Exception) {
        StopAddictLogger.e(TAG, "Erreur setCouts", e)
        false
    }
}
    
    fun getCouts(type: String): Map<String, Double> {
        return try {
            val db = readableDatabase
            val cursor = db.query(TABLE_COUTS, null, "$COL_TYPE = ?", arrayOf(type), null, null, null)
            
            val result = mutableMapOf<String, Double>()
            if (cursor.moveToFirst()) {
                for (i in 0 until cursor.columnCount) {
                    val columnName = cursor.getColumnName(i)
                    if (columnName != COL_ID && columnName != COL_TYPE) {
                        result[columnName] = cursor.getDouble(i)
                    }
                }
            }
            cursor.close()
            result
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, "Erreur lecture coûts", e)
            emptyMap()
        }
    }

    // ==================== PRÉFÉRENCES ====================

    fun setPreference(key: String, value: String): Boolean {
        return try {
            val db = writableDatabase
            val values = ContentValues().apply {
                put(key, value)
            }
            val result = db.update(TABLE_PREFERENCES, values, "$COL_ID = 1", null)
            Log.d(TAG, "Préférence mise à jour: $key = $value")
            result > 0
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, "Erreur update préférence", e)
            false
        }
    }

    fun getPreference(key: String, default: String = ""): String {
        return try {
            val db = readableDatabase
            val cursor = db.query(TABLE_PREFERENCES, arrayOf(key), "$COL_ID = 1", null, null, null, null)
            
            var value = default
            if (cursor.moveToFirst()) {
                value = cursor.getString(0) ?: default
            }
            cursor.close()
            value
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, "Erreur lecture préférence", e)
            default
        }
    }

    // ==================== RAZ ====================

    fun razJour(): Boolean {
        return try {
            val db = writableDatabase
            val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            val result = db.delete(TABLE_CONSOMMATIONS, "$COL_DATE_HEURE LIKE ?", arrayOf("$today%"))
            Log.d(TAG, "RAZ du jour effectuée: $result lignes supprimées")
            result >= 0
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, "Erreur RAZ jour", e)
            false
        }
    }

    fun razHistorique(): Boolean {
        return try {
            val db = writableDatabase
            db.delete(TABLE_CONSOMMATIONS, null, null)
            Log.d(TAG, "RAZ historique effectuée")
            true
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, "Erreur RAZ historique", e)
            false
        }
    }

    fun razUsine(): Boolean {
        return try {
            val db = writableDatabase
            db.delete(TABLE_CONSOMMATIONS, null, null)
            db.delete(TABLE_HABITUDES, null, null)
            db.delete(TABLE_DATES, null, null)
            db.delete(TABLE_COUTS, null, null)

           db.execSQL("""
    UPDATE $TABLE_PREFERENCES SET 
        $COL_PRENOM = '',
        $COL_LANGUE = 'FR',
        $COL_DEVISE = 'EUR',
        $COL_CATEGORIES_ACTIVES = '{"cigarette":true,"joint":true,"alcool_global":true,"biere":false,"liqueur":false,"alcool_fort":false}',
        $COL_MODE_CIGARETTE = 'classique',
        $COL_GRAMME_PAR_JOINT_PREF = '0',
        $COL_UNITE_CL_ALCOOL_GLOBAL = '0',
        $COL_UNITE_CL_BIERE = '0',
        $COL_UNITE_CL_LIQUEUR = '0',
        $COL_UNITE_CL_ALCOOL_FORT = '0',
        $COL_NB_CIGARETTES_ROULEES_PREF = '0',
        $COL_NB_CIGARETTES_TUBEES_PREF = '0'
""")

            // Réinitialiser les lignes par défaut
            val types = listOf(TYPE_CIGARETTE, TYPE_JOINT, TYPE_ALCOOL_GLOBAL, TYPE_BIERE, TYPE_LIQUEUR, TYPE_ALCOOL_FORT)
            types.forEach { type ->
                db.execSQL("INSERT INTO $TABLE_HABITUDES ($COL_TYPE) VALUES ('$type')")
                db.execSQL("INSERT INTO $TABLE_DATES ($COL_TYPE) VALUES ('$type')")
                db.execSQL("INSERT INTO $TABLE_COUTS ($COL_TYPE) VALUES ('$type')")
            }
            
            Log.d(TAG, "RAZ usine effectuée")
            true
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, "Erreur RAZ usine", e)
            false
        }
    }

    // ==================== MÉTHODES ADDITIONNELLES ====================

    fun getConsommationParDate(type: String, date: String): Int {
        return try {
            val db = readableDatabase
            val cursor = db.rawQuery(
                "SELECT SUM($COL_QUANTITE) FROM $TABLE_CONSOMMATIONS WHERE $COL_TYPE = ? AND $COL_DATE_HEURE LIKE ? AND $COL_ACTIF = 1",
                arrayOf(type, "$date%")
            )
            
            var total = 0
            if (cursor.moveToFirst()) {
                total = cursor.getInt(0)
            }
            cursor.close()
            
            Log.d(TAG, "Consommation $type du $date: $total")
            total
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, "Erreur getConsommationParDate", e)
            0
        }
    }

    fun supprimerConsommationsJour(type: String, date: String): Boolean {
        return try {
            val db = writableDatabase
            val result = db.delete(TABLE_CONSOMMATIONS, "$COL_TYPE = ? AND $COL_DATE_HEURE LIKE ?", arrayOf(type, "$date%"))
            Log.d(TAG, "Suppression consommations du jour pour $type: $result lignes supprimées")
            result >= 0
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, "Erreur supprimerConsommationsJour", e)
            false
        }
    }

    fun supprimerToutesConsommations(type: String): Boolean {
        return try {
            val db = writableDatabase
            val result = db.delete(TABLE_CONSOMMATIONS, "$COL_TYPE = ?", arrayOf(type))
            Log.d(TAG, "Suppression toutes consommations pour $type: $result lignes supprimées")
            result >= 0
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, "Erreur supprimerToutesConsommations", e)
            false
        }
    }

    fun getToutesConsommations(type: String): List<Map<String, Any>> {
        return try {
            val db = readableDatabase
            val cursor = db.query(
                TABLE_CONSOMMATIONS, 
                null, 
                "$COL_TYPE = ? AND $COL_ACTIF = 1", 
                arrayOf(type), 
                null, null, 
                "$COL_DATE_HEURE DESC"
            )
            
            val liste = mutableListOf<Map<String, Any>>()
            while (cursor.moveToNext()) {
                val map = mutableMapOf<String, Any>()
                for (i in 0 until cursor.columnCount) {
                    val columnName = cursor.getColumnName(i)
                    val value = cursor.getString(i) ?: ""
                    map[columnName] = value
                }
                liste.add(map)
            }
            cursor.close()
            
            Log.d(TAG, "Récupération de ${liste.size} consommations pour $type")
            liste
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, "Erreur getToutesConsommations", e)
            emptyList()
        }
    }

    // ==================== EXPORT/IMPORT JSON ====================

    fun exportJSON(): String {
        return try {
            val db = readableDatabase
            val export = JSONObject()

            // Consommations
            val consoCursor = db.query(TABLE_CONSOMMATIONS, null, null, null, null, null, null)
            val consoArray = JSONArray()
            while (consoCursor.moveToNext()) {
                val obj = JSONObject()
                for (i in 0 until consoCursor.columnCount) {
                    obj.put(consoCursor.getColumnName(i), consoCursor.getString(i))
                }
                consoArray.put(obj)
            }
            consoCursor.close()
            export.put("consommations", consoArray)

            // Habitudes
            val habCursor = db.query(TABLE_HABITUDES, null, null, null, null, null, null)
            val habArray = JSONArray()
            while (habCursor.moveToNext()) {
                val obj = JSONObject()
                for (i in 0 until habCursor.columnCount) {
                    obj.put(habCursor.getColumnName(i), habCursor.getString(i))
                }
                habArray.put(obj)
            }
            habCursor.close()
            export.put("habitudes", habArray)

            // Dates
            val datesCursor = db.query(TABLE_DATES, null, null, null, null, null, null)
            val datesArray = JSONArray()
            while (datesCursor.moveToNext()) {
                val obj = JSONObject()
                for (i in 0 until datesCursor.columnCount) {
                    obj.put(datesCursor.getColumnName(i), datesCursor.getString(i))
                }
                datesArray.put(obj)
            }
            datesCursor.close()
            export.put("dates", datesArray)

            // Coûts
            val coutsCursor = db.query(TABLE_COUTS, null, null, null, null, null, null)
            val coutsArray = JSONArray()
            while (coutsCursor.moveToNext()) {
                val obj = JSONObject()
                for (i in 0 until coutsCursor.columnCount) {
                    obj.put(coutsCursor.getColumnName(i), coutsCursor.getString(i))
                }
                coutsArray.put(obj)
            }
            coutsCursor.close()
            export.put("couts", coutsArray)

            // Préférences
            val prefCursor = db.query(TABLE_PREFERENCES, null, null, null, null, null, null)
            if (prefCursor.moveToFirst()) {
                val prefObj = JSONObject()
                for (i in 0 until prefCursor.columnCount) {
                    prefObj.put(prefCursor.getColumnName(i), prefCursor.getString(i))
                }
                export.put("preferences", prefObj)
            }
            prefCursor.close()

            Log.d(TAG, "Export JSON réussi")
            export.toString(2)
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, "Erreur export JSON", e)
            ""
        }
    }

   fun importJSON(jsonString: String): Boolean {
    return try {
        val json = JSONObject(jsonString)
        val db = writableDatabase

        // 1) Réinitialiser les données (structure conservée)
        razUsine()

        // 2) Importer les consommations
        if (json.has("consommations")) {
            val consoArray = json.getJSONArray("consommations")
            for (i in 0 until consoArray.length()) {
                val obj = consoArray.getJSONObject(i)
                val values = ContentValues()
                val keys = obj.keys()

                while (keys.hasNext()) {
                    val key = keys.next()
                    if (key != COL_ID) {
                        if (obj.isNull(key)) {
                            values.putNull(key)
                        } else {
                            // Pas de defaultValue = null → pas de problème de "Nothing?"
                            val v = obj.optString(key)
                            values.put(key, v)
                        }
                    }
                }

                db.insert(TABLE_CONSOMMATIONS, null, values)
            }
        }

        // 3) Importer les habitudes (max journaliers)
        if (json.has("habitudes")) {
            val habArray = json.getJSONArray("habitudes")
            for (i in 0 until habArray.length()) {
                val obj = habArray.getJSONObject(i)
                val type = obj.optString(COL_TYPE, "")
                if (type.isBlank()) continue

                val values = ContentValues()
                if (obj.has(COL_MAX_JOURNALIER)) {
                    val maxStr = obj.optString(COL_MAX_JOURNALIER, "0")
                    val max = maxStr.replace(',', '.').toDoubleOrNull() ?: 0.0
                    values.put(COL_MAX_JOURNALIER, max)
                }

                if (values.size() > 0) {
                    db.update(TABLE_HABITUDES, values, "$COL_TYPE = ?", arrayOf(type))
                }
            }
        }

        // 4) Importer les dates objectifs
        if (json.has("dates")) {
            val datesArray = json.getJSONArray("dates")
            for (i in 0 until datesArray.length()) {
                val obj = datesArray.getJSONObject(i)
                val type = obj.optString(COL_TYPE, "")
                if (type.isBlank()) continue

                val values = ContentValues().apply {
                    if (obj.has(COL_DATE_REDUCTION)) {
                        val v = obj.optString(COL_DATE_REDUCTION, "")
                        if (v.isNotBlank()) {
                            put(COL_DATE_REDUCTION, v)
                        } else {
                            putNull(COL_DATE_REDUCTION)
                        }
                    }
                    if (obj.has(COL_DATE_ARRET)) {
                        val v = obj.optString(COL_DATE_ARRET, "")
                        if (v.isNotBlank()) {
                            put(COL_DATE_ARRET, v)
                        } else {
                            putNull(COL_DATE_ARRET)
                        }
                    }
                    if (obj.has(COL_DATE_REUSSITE)) {
                        val v = obj.optString(COL_DATE_REUSSITE, "")
                        if (v.isNotBlank()) {
                            put(COL_DATE_REUSSITE, v)
                        } else {
                            putNull(COL_DATE_REUSSITE)
                        }
                    }
                }

                if (values.size() > 0) {
                    db.update(TABLE_DATES, values, "$COL_TYPE = ?", arrayOf(type))
                }
            }
        }

        // 5) Importer les coûts
        if (json.has("couts")) {
            val coutsArray = json.getJSONArray("couts")
            for (i in 0 until coutsArray.length()) {
                val obj = coutsArray.getJSONObject(i)
                val type = obj.optString(COL_TYPE, "")
                if (type.isBlank()) continue

                val values = ContentValues()
                val keys = obj.keys()
                while (keys.hasNext()) {
                    val key = keys.next()
                    if (key != COL_ID && key != COL_TYPE) {
                        val raw = obj.optString(key, "0")
                        val num = raw.replace(',', '.').toDoubleOrNull()
                        if (num != null) {
                            values.put(key, num)
                        }
                    }
                }

                if (values.size() > 0) {
                    db.update(TABLE_COUTS, values, "$COL_TYPE = ?", arrayOf(type))
                }
            }
        }

        // 6) Importer les préférences (langue, monnaies, réglages divers)
        if (json.has("preferences")) {
            val prefObj = json.getJSONObject("preferences")
            val values = ContentValues()
            val keys = prefObj.keys()

            while (keys.hasNext()) {
                val key = keys.next()
                if (key != COL_ID) {
                    if (prefObj.isNull(key)) {
                        values.putNull(key)
                    } else {
                        // Ici aussi : pas de defaultValue = null
                        val raw = prefObj.optString(key)
                        values.put(key, raw)
                    }
                }
            }

            if (values.size() > 0) {
                db.update(TABLE_PREFERENCES, values, "$COL_ID = 1", null)
            }
        }

        Log.d(TAG, "Import JSON réussi (consommations + habitudes + dates + coûts + préférences)")
        true
    } catch (e: Exception) {
        StopAddictLogger.e(TAG, "Erreur import JSON", e)
        false
    }
}
   
    // ==================== NETTOYAGE HISTORIQUE (5 ANS) ====================

    fun cleanOldData() {
        try {
            val db = writableDatabase
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.YEAR, -5)
            val dateLimit = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
            
            val deleted = db.delete(TABLE_CONSOMMATIONS, "$COL_DATE_HEURE < ?", arrayOf(dateLimit))
            Log.d(TAG, "Nettoyage données >5 ans: $deleted lignes supprimées")
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, "Erreur nettoyage historique", e)
        }
    }
}
