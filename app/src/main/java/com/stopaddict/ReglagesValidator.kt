package com.stopaddict

import android.content.Context
import android.widget.EditText

/**
 * ReglagesValidator.kt
 * Validation centralisée des formulaires Réglages
 * Gère coûts (cigarettes 3 types, joints, alcools 4 types) et habitudes
 */
class ReglagesValidator(private val context: Context) {

    private val TAG = "ReglagesValidator"

    /**
     * Résultat de validation
     */
    data class ValidationResult(
        val isValid: Boolean,
        val errorMessage: String = ""
    )

    /**
     * Valide un champ prix (décimal positif avec centimes)
     * @param editText: Champ à valider
     * @param nomChamp: Nom du champ pour message erreur
     * @param obligatoire: Si true, le champ ne peut pas être vide
     * @return ValidationResult
     */
    fun validerPrix(editText: EditText, nomChamp: String, obligatoire: Boolean = true): ValidationResult {
        val texte = editText.text.toString().trim()

        // Vérifier si vide
        if (texte.isEmpty()) {
            return if (obligatoire) {
                ValidationResult(false, "Le champ '$nomChamp' est obligatoire")
            } else {
                ValidationResult(true)
            }
        }

        // Vérifier format décimal
        val prix = texte.toDoubleOrNull()
        if (prix == null) {
            StopAddictLogger.w(TAG, "Format invalide pour $nomChamp: $texte")
            return ValidationResult(false, "Le prix '$nomChamp' doit être un nombre valide (ex: 12.50)")
        }

        // Vérifier positif
        if (prix < 0) {
            StopAddictLogger.w(TAG, "Prix négatif pour $nomChamp: $prix")
            return ValidationResult(false, "Le prix '$nomChamp' ne peut pas être négatif")
        }

        // Vérifier limite raisonnable (max 1000€)
        if (prix > 1000) {
            StopAddictLogger.w(TAG, "Prix trop élevé pour $nomChamp: $prix")
            return ValidationResult(false, "Le prix '$nomChamp' semble trop élevé (max 1000)")
        }

        StopAddictLogger.d(TAG, "Prix validé: $nomChamp = $prix")
        return ValidationResult(true)
    }

    /**
     * Valide un champ quantité (entier positif)
     * @param editText: Champ à valider
     * @param nomChamp: Nom du champ pour message erreur
     * @param obligatoire: Si true, le champ ne peut pas être vide
     * @return ValidationResult
     */
    fun validerQuantite(editText: EditText, nomChamp: String, obligatoire: Boolean = true): ValidationResult {
        val texte = editText.text.toString().trim()

        // Vérifier si vide
        if (texte.isEmpty()) {
            return if (obligatoire) {
                ValidationResult(false, "Le champ '$nomChamp' est obligatoire")
            } else {
                ValidationResult(true)
            }
        }

        // Vérifier format entier
        val quantite = texte.toIntOrNull()
        if (quantite == null) {
            StopAddictLogger.w(TAG, "Format invalide pour $nomChamp: $texte")
            return ValidationResult(false, "La quantité '$nomChamp' doit être un nombre entier (ex: 20)")
        }

        // Vérifier positif et non nul
        if (quantite <= 0) {
            StopAddictLogger.w(TAG, "Quantité nulle ou négative pour $nomChamp: $quantite")
            return ValidationResult(false, "La quantité '$nomChamp' doit être supérieure à 0")
        }

        // Vérifier limite raisonnable (max 1000 unités)
        if (quantite > 1000) {
            StopAddictLogger.w(TAG, "Quantité trop élevée pour $nomChamp: $quantite")
            return ValidationResult(false, "La quantité '$nomChamp' semble trop élevée (max 1000)")
        }

        StopAddictLogger.d(TAG, "Quantité validée: $nomChamp = $quantite")
        return ValidationResult(true)
    }

    /**
     * Valide un champ centilitres (décimal positif)
     */
    fun validerCentilitres(editText: EditText, nomChamp: String, obligatoire: Boolean = true): ValidationResult {
        val texte = editText.text.toString().trim()

        if (texte.isEmpty()) {
            return if (obligatoire) {
                ValidationResult(false, "Le champ '$nomChamp' est obligatoire")
            } else {
                ValidationResult(true)
            }
        }

        val cl = texte.toDoubleOrNull()
        if (cl == null) {
            return ValidationResult(false, "Les centilitres '$nomChamp' doivent être un nombre valide (ex: 33.0)")
        }

        if (cl <= 0) {
            return ValidationResult(false, "Les centilitres '$nomChamp' doivent être supérieurs à 0")
        }

        if (cl > 500) {
            return ValidationResult(false, "Les centilitres '$nomChamp' semblent trop élevés (max 500cl = 5L)")
        }

        StopAddictLogger.d(TAG, "Centilitres validés: $nomChamp = $cl")
        return ValidationResult(true)
    }

    /**
     * Valide un champ grammes (décimal positif, petites valeurs)
     */
    fun validerGrammes(editText: EditText, nomChamp: String, obligatoire: Boolean = true): ValidationResult {
        val texte = editText.text.toString().trim()

        if (texte.isEmpty()) {
            return if (obligatoire) {
                ValidationResult(false, "Le champ '$nomChamp' est obligatoire")
            } else {
                ValidationResult(true)
            }
        }

        val grammes = texte.toDoubleOrNull()
        if (grammes == null) {
            return ValidationResult(false, "Les grammes '$nomChamp' doivent être un nombre valide (ex: 0.5)")
        }

        if (grammes <= 0) {
            return ValidationResult(false, "Les grammes '$nomChamp' doivent être supérieurs à 0")
        }

        // Pour cannabis: max 10g par joint (très large)
        if (grammes > 10) {
            return ValidationResult(false, "Les grammes '$nomChamp' semblent trop élevés (max 10g)")
        }

        StopAddictLogger.d(TAG, "Grammes validés: $nomChamp = $grammes")
        return ValidationResult(true)
    }

    /**
     * Valide un champ max journalier (entier positif, limite raisonnable)
     */
     
    fun validerMaxJournalier(editText: EditText, nomChamp: String): ValidationResult {
        val texte = editText.text.toString().trim()

        // Max journalier est optionnel
        if (texte.isEmpty()) {
            return ValidationResult(true)
        }

        val max = texte.toIntOrNull()
        if (max == null) {
            return ValidationResult(false, "Le max '$nomChamp' doit être un nombre entier (ex: 10)")
        }

        if (max <= 0) {
            return ValidationResult(false, "Le max '$nomChamp' doit être supérieur à 0")
        }

        // Limites raisonnables selon type
        val limiteMax = when {
            nomChamp.contains("cigarettes", ignoreCase = true) -> 100
            nomChamp.contains("joints", ignoreCase = true) -> 50
            else -> 50 // Alcools
        }

        if (max > limiteMax) {
            return ValidationResult(false, "Le max '$nomChamp' semble trop élevé (max $limiteMax)")
        }

        StopAddictLogger.d(TAG, "Max journalier validé: $nomChamp = $max")
        return ValidationResult(true)
    }

    /**
     * Valide un formulaire cigarettes classiques (paquet)
     */
    fun validerCigarettesClassiques(editPrix: EditText, editNb: EditText): ValidationResult {
        StopAddictLogger.d(TAG, "Validation cigarettes classiques")
        
        val validPrix = validerPrix(editPrix, "Prix du paquet")
        if (!validPrix.isValid) return validPrix

        val validNb = validerQuantite(editNb, "Nombre de cigarettes par paquet")
        if (!validNb.isValid) return validNb

        return ValidationResult(true)
    }

    /**
     * Valide un formulaire cigarettes roulées
     */
    fun validerCigarettesRoulees(
        editPrixTabac: EditText,
        editNbCigarettes: EditText,
        editPrixFeuilles: EditText,
        editNbFeuilles: EditText,
        editPrixFiltres: EditText,
        editNbFiltres: EditText
    ): ValidationResult {
        StopAddictLogger.d(TAG, "Validation cigarettes roulées")

        validerPrix(editPrixTabac, "Prix du tabac").let { if (!it.isValid) return it }
        validerQuantite(editNbCigarettes, "Nombre de cigarettes").let { if (!it.isValid) return it }
        validerPrix(editPrixFeuilles, "Prix des feuilles").let { if (!it.isValid) return it }
        validerQuantite(editNbFeuilles, "Nombre de feuilles").let { if (!it.isValid) return it }
        validerPrix(editPrixFiltres, "Prix des filtres").let { if (!it.isValid) return it }
        validerQuantite(editNbFiltres, "Nombre de filtres").let { if (!it.isValid) return it }

        return ValidationResult(true)
    }

    /**
     * Valide un formulaire cigarettes tubeuse
     */
    fun validerCigarettesTubeuse(
        editPrixTabac: EditText,
        editNbCigarettes: EditText,
        editPrixTubes: EditText,
        editNbTubes: EditText
    ): ValidationResult {
        StopAddictLogger.d(TAG, "Validation cigarettes tubeuse")

        validerPrix(editPrixTabac, "Prix du tabac").let { if (!it.isValid) return it }
        validerQuantite(editNbCigarettes, "Nombre de cigarettes").let { if (!it.isValid) return it }
        validerPrix(editPrixTubes, "Prix des tubes").let { if (!it.isValid) return it }
        validerQuantite(editNbTubes, "Nombre de tubes").let { if (!it.isValid) return it }

        return ValidationResult(true)
    }

    /**
     * Valide un formulaire joints
     */
    fun validerJoints(editPrixGramme: EditText, editGrammeParJoint: EditText): ValidationResult {
        StopAddictLogger.d(TAG, "Validation joints")

        val validPrix = validerPrix(editPrixGramme, "Prix du gramme")
        if (!validPrix.isValid) return validPrix

        val validGrammes = validerGrammes(editGrammeParJoint, "Grammes par joint")
        if (!validGrammes.isValid) return validGrammes

        return ValidationResult(true)
    }

    /**
     * Valide un formulaire alcool (prix verre + centilitres)
     */
    fun validerAlcool(editPrixVerre: EditText, editCL: EditText, typeAlcool: String): ValidationResult {
        StopAddictLogger.d(TAG, "Validation alcool: $typeAlcool")

        val validPrix = validerPrix(editPrixVerre, "Prix du verre $typeAlcool")
        if (!validPrix.isValid) return validPrix

        val validCL = validerCentilitres(editCL, "Unité (cl) $typeAlcool")
        if (!validCL.isValid) return validCL

        return ValidationResult(true)
    }

    /**
     * Extrait un Double depuis EditText (retourne 0.0 si vide/invalide)
     */
    fun extraireDouble(editText: EditText): Double {
        return editText.text.toString().trim().toDoubleOrNull() ?: 0.0
    }

    /**
     * Extrait un Int depuis EditText (retourne 0 si vide/invalide)
     */
    fun extraireInt(editText: EditText): Int {
        return editText.text.toString().trim().toIntOrNull() ?: 0
    }
}
