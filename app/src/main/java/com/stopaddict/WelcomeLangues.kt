package com.stopaddict

object WelcomeLangues {

    private const val TAG = "WelcomeLangues"

    /**
     * Petites traductions UI de la popup "Bienvenue"
     */
    fun getTraductions(codeLangue: String): Map<String, String> {
        return when (codeLangue) {
            "FR" -> TRADUCTIONS_FR
            "EN" -> TRADUCTIONS_EN
            "ES" -> TRADUCTIONS_ES
            "PT" -> TRADUCTIONS_PT
            "DE" -> TRADUCTIONS_DE
            "IT" -> TRADUCTIONS_IT
            "RU" -> TRADUCTIONS_RU
            "AR" -> TRADUCTIONS_AR
            "HI" -> TRADUCTIONS_HI
            "JA" -> TRADUCTIONS_JA
            "NL" -> TRADUCTIONS_NL
            "ZHS" -> TRADUCTIONS_ZHS
            "ZHT" -> TRADUCTIONS_ZHT
            else -> {
                StopAddictLogger.w(TAG, "Langue inconnue: $codeLangue, fallback FR")
                TRADUCTIONS_FR
            }
        }
    }

    /**
     * Liste des 45 messages (1 sera tiré aléatoirement à chaque lancement)
     * Si une langue n'est pas encore traduite, fallback FR.
     */
    fun getMessages(codeLangue: String): List<String> {
        return when (codeLangue) {
            "FR" -> MESSAGES_FR
            "EN" -> MESSAGES_EN
            "ES" -> MESSAGES_ES
            "PT" -> MESSAGES_PT
            "DE" -> MESSAGES_DE
            "IT" -> MESSAGES_IT
            "RU" -> MESSAGES_RU
            "AR" -> MESSAGES_AR
            "HI" -> MESSAGES_HI
            "JA" -> MESSAGES_JA
            "NL" -> MESSAGES_NL
            "ZHS" -> MESSAGES_ZHS
            "ZHT" -> MESSAGES_ZHT
            else -> {
                StopAddictLogger.w(TAG, "Langue inconnue: $codeLangue, fallback messages FR")
                MESSAGES_FR
            }
        }
    }

    // ==================== FRANÇAIS ====================
    private val TRADUCTIONS_FR = mapOf(
        "welcome_title" to "Bienvenue",
        "welcome_checkbox_hide" to "Ne plus afficher le message d’accueil",
        "welcome_ok" to "OK"
    )

    /**
     * 45 messages FR (vouvoiement + pictos texte)
     */
    private val MESSAGES_FR = listOf(
        "★ Bienvenue ! Vous venez de faire un premier pas concret.",
        "💡 Astuce : explorez les onglets, vous verrez vite ce que l’app peut vous apporter.",
        "🎯 Objectif : un jour à la fois. Chaque effort compte.",
        "✓ Plus vous renseignez d’informations, plus le suivi devient utile.",
        "🌿 Pensez à respirer : l’envie passe souvent en quelques minutes.",
        "⚡ Rappel : l’app est un outil d’auto-suivi, pas un jugement.",
        "★ Vous pouvez activer/désactiver des catégories sans perdre vos données.",
        "💡 Essayez d’ajouter vos coûts : vous verrez l’impact financier plus clairement.",
        "🎯 Fixez une habitude réaliste : mieux vaut stable que parfait.",
        "✓ Vous pouvez exporter vos données quand vous le souhaitez.",
        "🌿 Hydratez-vous : cela aide souvent à calmer les sensations de manque.",
        "⚡ Petit rappel : la progression n’est pas toujours linéaire, c’est normal.",
        "★ Pensez à consulter l’onglet Réglages pour personnaliser l’application.",
        "💡 Conseil : commencez simple, puis affinez (habitudes, coûts, dates).",
        "🎯 Une victoire = une unité en moins. C’est déjà un progrès.",
        "✓ Vos données restent sur l’appareil : pas de compte, pas de serveur.",
        "🌿 Si vous rechutez, reprenez simplement le suivi : l’important est de continuer.",
        "⚡ Astuce : notez vos habitudes pour comparer votre journée à votre “référence”.",
        "★ Utilisez le calendrier pour visualiser votre régularité.",
        "💡 En version “alcool global”, les sous-types (bière/liqueur/fort) sont désactivés.",
        "🎯 Vous pouvez choisir l’inverse : bières/liqueurs/alcool fort au lieu d’alcool global.",
        "✓ Le suivi est plus parlant sur plusieurs jours : laissez le temps faire son travail.",
        "🌿 Pensez à bouger un peu : marcher aide souvent à réduire l’envie.",
        "⚡ Rappel : une baisse progressive est déjà une stratégie efficace.",
        "★ Mettez à jour vos habitudes si vos objectifs évoluent.",
        "💡 Vous pouvez réactiver une catégorie plus tard : rien n’est effacé.",
        "🎯 Votre constance vaut plus que la perfection.",
        "✓ Le bouton RAZ du jour supprime uniquement la journée en cours.",
        "🌿 Le RAZ historique supprime tout l’historique : à utiliser avec prudence.",
        "⚡ Avant un import, vérifiez que le fichier vient bien de votre export StopAddict.",
        "★ Pensez à ajuster la devise si vous êtes à l’étranger.",
        "💡 Changez la langue si besoin : l’application redémarre pour l’appliquer.",
        "🎯 Un suivi honnête vous aide plus qu’un suivi “parfait”.",
        "✓ Comparez coûts réels et habitudes : c’est motivant quand ça baisse.",
        "🌿 Un verre d’eau, une respiration, puis une décision.",
        "⚡ Vous pouvez commencer sans rien configurer : le compteur fonctionne déjà.",
        "★ Les réglages enrichissent l’app, mais ne sont pas obligatoires.",
        "💡 Le graphique devient plus parlant quand les coûts et habitudes sont remplis.",
        "🎯 Chaque journée suivie est une donnée utile pour progresser.",
        "✓ Votre motivation peut varier : continuez malgré tout.",
        "🌿 Prenez soin de vous : le but est la réduction, pas la culpabilité.",
        "⚡ Un petit pas aujourd’hui, un grand résultat demain.",
        "★ Revenez demain : la régularité crée les changements durables.",
        "💡 Si vous voulez, masquez ce message dans Réglages > Personnalisation."
    )

    // ==================== AUTRES LANGUES ====================
    // Pour compilation immédiate : on peut temporairement fallback sur FR
    // Puis remplacer progressivement par de vraies traductions.

    private val TRADUCTIONS_EN = TRADUCTIONS_FR
    private val TRADUCTIONS_ES = TRADUCTIONS_FR
    private val TRADUCTIONS_PT = TRADUCTIONS_FR
    private val TRADUCTIONS_DE = TRADUCTIONS_FR
    private val TRADUCTIONS_IT = TRADUCTIONS_FR
    private val TRADUCTIONS_RU = TRADUCTIONS_FR
    private val TRADUCTIONS_AR = TRADUCTIONS_FR
    private val TRADUCTIONS_HI = TRADUCTIONS_FR
    private val TRADUCTIONS_JA = TRADUCTIONS_FR
    private val TRADUCTIONS_NL = TRADUCTIONS_FR
    private val TRADUCTIONS_ZHS = TRADUCTIONS_FR
    private val TRADUCTIONS_ZHT = TRADUCTIONS_FR

    private val MESSAGES_EN = MESSAGES_FR
    private val MESSAGES_ES = MESSAGES_FR
    private val MESSAGES_PT = MESSAGES_FR
    private val MESSAGES_DE = MESSAGES_FR
    private val MESSAGES_IT = MESSAGES_FR
    private val MESSAGES_RU = MESSAGES_FR
    private val MESSAGES_AR = MESSAGES_FR
    private val MESSAGES_HI = MESSAGES_FR
    private val MESSAGES_JA = MESSAGES_FR
    private val MESSAGES_NL = MESSAGES_FR
    private val MESSAGES_ZHS = MESSAGES_FR
    private val MESSAGES_ZHT = MESSAGES_FR
}
