package com.stopaddict

import android.util.Log

object SymbolesDevises {

    private const val TAG = "SymbolesDevises"

    // Liste complète des devises disponibles
    val DEVISES_DISPONIBLES = mapOf(
        "€" to "Euro",
        "$" to "Dollar US",
        "£" to "Livre Sterling",
        "¥" to "Yen",
        "₽" to "Rouble",
        "₹" to "Roupie",
        "R$" to "Real Brésilien",
        "د.إ" to "Dirham",
        "﷼" to "Riyal"
    )

    // Devise par défaut selon langue
    private val DEVISE_PAR_LANGUE = mapOf(
        "FR" to "€",
        "EN" to "$",
        "ES" to "€",
        "PT" to "R$",
        "DE" to "€",
        "IT" to "€",
        "RU" to "₽",
        "AR" to "﷼",
        "HI" to "₹",
        "JA" to "¥"
    )

    /**
     * Retourne le symbole de devise par défaut pour une langue donnée
     */
    fun getDeviseParLangue(codeLangue: String): String {
        val devise = DEVISE_PAR_LANGUE[codeLangue] ?: "€"
        Log.d(TAG, "Devise pour langue $codeLangue: $devise")
        return devise
    }

    /**
     * Vérifie si un symbole de devise existe
     */
    fun deviseExiste(symbole: String): Boolean {
        return DEVISES_DISPONIBLES.containsKey(symbole)
    }

    /**
     * Retourne le nom complet d'une devise
     */
    fun getNomDevise(symbole: String): String {
        return DEVISES_DISPONIBLES[symbole] ?: "Inconnue"
    }

    /**
     * Retourne la liste des symboles disponibles
     */
    fun getSymbolesDisponibles(): List<String> {
        return DEVISES_DISPONIBLES.keys.toList()
    }
}
