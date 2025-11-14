package com.stopaddict

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

/**
 * ReglagesDatePicker.kt
 * Gestionnaire centralisé des DatePicker pour les dates d'objectifs
 * Gère les 6 catégories × 3 types de dates (réduction, arrêt, réussite)
 */
class ReglagesDatePicker(private val context: Context) {

    private val TAG = "ReglagesDatePicker"
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    // Callback pour retourner la date sélectionnée
    interface DateCallback {
        fun onDateSelected(categorie: String, typeDate: String, date: String)
        fun onDateError(error: String)
    }

    /**
     * Affiche un DatePicker pour une catégorie et type de date
     * @param categorie: cigarettes, joints, alcool_global, bieres, liqueurs, alcool_fort
     * @param typeDate: reduction, arret, reussite
     * @param dateActuelle: Date actuelle ou null si pas encore définie
     * @param callback: Interface pour retourner le résultat
     */
    fun afficherDatePicker(
        categorie: String,
        typeDate: String,
        dateActuelle: String?,
        callback: DateCallback
    ) {
        try {
            Log.d(TAG, "Affichage DatePicker - Catégorie: $categorie, Type: $typeDate")

            // Parser la date actuelle si elle existe
            val calendar = Calendar.getInstance()
            if (dateActuelle != null && dateActuelle.isNotEmpty()) {
                try {
                    val parsedDate = dateFormat.parse(dateActuelle)
                    if (parsedDate != null) {
                        calendar.time = parsedDate
                    }
                } catch (e: Exception) {
                    Log.w(TAG, "Erreur parsing date actuelle: $dateActuelle", e)
                    // Garder la date d'aujourd'hui par défaut
                }
            }

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Créer le DatePickerDialog
            val datePickerDialog = DatePickerDialog(
                context,
                { _, selectedYear, selectedMonth, selectedDay ->
                    // Formater la date sélectionnée
                    val selectedCalendar = Calendar.getInstance()
                    selectedCalendar.set(selectedYear, selectedMonth, selectedDay)
                    val dateSelectionnee = dateFormat.format(selectedCalendar.time)

                    // Valider la date selon le type
                    if (validerDate(typeDate, selectedCalendar)) {
                        Log.d(TAG, "Date valide sélectionnée: $dateSelectionnee")
                        callback.onDateSelected(categorie, typeDate, dateSelectionnee)
                    } else {
                        val errorMsg = when (typeDate) {
                            "reduction" -> "La date de réduction ne peut pas être dans le futur"
                            "arret" -> "La date d'arrêt ne peut pas être dans le futur"
                            else -> "Date invalide"
                        }
                        Log.w(TAG, errorMsg)
                        callback.onDateError(errorMsg)
                    }
                },
                year,
                month,
                day
            )

            // Configurer les dates min/max selon le type
            configurerLimites(datePickerDialog, typeDate)

            // Afficher le dialog
            datePickerDialog.show()

        } catch (e: Exception) {
            Log.e(TAG, "Erreur affichage DatePicker", e)
            callback.onDateError("Erreur lors de l'affichage du calendrier: ${e.message}")
        }
    }

    /**
     * Configure les limites de dates selon le type
     */
    private fun configurerLimites(dialog: DatePickerDialog, typeDate: String) {
        val datePicker = dialog.datePicker
        val aujourdhui = Calendar.getInstance().timeInMillis

        when (typeDate) {
            "reduction", "arret" -> {
                // Pour réduction et arrêt: pas de date future
                datePicker.maxDate = aujourdhui
                Log.d(TAG, "Limite max = aujourd'hui pour type: $typeDate")
            }
            "reussite" -> {
                // Pour réussite: peut être dans le futur
                // Limiter à 10 ans dans le futur
                val futureMax = Calendar.getInstance()
                futureMax.add(Calendar.YEAR, 10)
                datePicker.maxDate = futureMax.timeInMillis
                Log.d(TAG, "Limite max = +10 ans pour type: $typeDate")
            }
        }

        // Date minimale: 10 ans dans le passé (raisonnable pour historique)
        val passeMin = Calendar.getInstance()
        passeMin.add(Calendar.YEAR, -10)
        datePicker.minDate = passeMin.timeInMillis
    }

    /**
     * Valide une date sélectionnée selon le type
     */
    private fun validerDate(typeDate: String, dateSelectionnee: Calendar): Boolean {
        val aujourdhui = Calendar.getInstance()
        
        // Réinitialiser les heures pour comparer uniquement les dates
        aujourdhui.set(Calendar.HOUR_OF_DAY, 0)
        aujourdhui.set(Calendar.MINUTE, 0)
        aujourdhui.set(Calendar.SECOND, 0)
        aujourdhui.set(Calendar.MILLISECOND, 0)
        
        dateSelectionnee.set(Calendar.HOUR_OF_DAY, 0)
        dateSelectionnee.set(Calendar.MINUTE, 0)
        dateSelectionnee.set(Calendar.SECOND, 0)
        dateSelectionnee.set(Calendar.MILLISECOND, 0)

        return when (typeDate) {
            "reduction", "arret" -> {
                // Ne peut pas être dans le futur
                !dateSelectionnee.after(aujourdhui)
            }
            "reussite" -> {
                // Peut être n'importe quand (passé ou futur)
                true
            }
            else -> {
                Log.w(TAG, "Type de date inconnu: $typeDate")
                false
            }
        }
    }

    /**
     * Efface une date (retourne null)
     */
    fun effacerDate(categorie: String, typeDate: String, callback: DateCallback) {
        Log.d(TAG, "Effacement date - Catégorie: $categorie, Type: $typeDate")
        callback.onDateSelected(categorie, typeDate, "")
    }

    /**
     * Parse une date string en Calendar (utile pour validations)
     */
    fun parseDate(dateString: String): Calendar? {
        return try {
            val date = dateFormat.parse(dateString)
            if (date != null) {
                val calendar = Calendar.getInstance()
                calendar.time = date
                calendar
            } else null
        } catch (e: Exception) {
            Log.e(TAG, "Erreur parsing date: $dateString", e)
            null
        }
    }

    /**
     * Formate une date Calendar en String
     */
    fun formatDate(calendar: Calendar): String {
        return dateFormat.format(calendar.time)
    }
}
