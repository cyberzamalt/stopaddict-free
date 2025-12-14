package com.stopaddict

import android.util.Log

object StatsLangues {

    private const val TAG = "StatsLangues"

    /**
     * Retourne toutes les traductions pour une langue donnée
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
            else -> {
                Log.w(TAG, "Langue inconnue: $codeLangue, fallback FR")
                TRADUCTIONS_FR
            }
        }
    }

    // ==================== FRANÇAIS ====================
    private val TRADUCTIONS_FR = mapOf(
        // Titres sections
        "titre_graphique_consommation" to "Consommation",
        "titre_graphique_couts" to "Coûts & Économies",
        
        // Boutons période
        "btn_jour" to "Jour",
        "btn_semaine" to "Semaine",
        "btn_mois" to "Mois",
        "btn_annee" to "Année",
        
        // Labels catégories
        "label_cigarettes" to "Cigarettes",
        "label_joints" to "Joints",
        "label_alcool_global" to "Alcool global",
        "label_bieres" to "Bières",
        "label_liqueurs" to "Liqueurs",
        "label_alcool_fort" to "Alcool fort",
        "label_limite" to "Limite",
        "label_couts" to "Coûts",
        "label_economies" to "Économies",
        "label_depenses" to "Dépenses",
        
        // Labels axes X (jour)
        "axe_x_jour_0" to "00-07",
        "axe_x_jour_1" to "07-14",
        "axe_x_jour_2" to "14-21",
        "axe_x_jour_3" to "21-00",
        
        // Labels axes X (semaine)
        "axe_x_semaine_0" to "Lun",
        "axe_x_semaine_1" to "Mar",
        "axe_x_semaine_2" to "Mer",
        "axe_x_semaine_3" to "Jeu",
        "axe_x_semaine_4" to "Ven",
        "axe_x_semaine_5" to "Sam",
        "axe_x_semaine_6" to "Dim",
        
        // Labels axes X (mois)
        "axe_x_mois_0" to "1",
        "axe_x_mois_1" to "6",
        "axe_x_mois_2" to "11",
        "axe_x_mois_3" to "16",
        "axe_x_mois_4" to "21",
        "axe_x_mois_5" to "26",
        "axe_x_mois_6" to "31",
        
        // Labels axes X (année)
        "axe_x_annee_0" to "Jan",
        "axe_x_annee_1" to "Fév",
        "axe_x_annee_2" to "Mar",
        "axe_x_annee_3" to "Avr",
        "axe_x_annee_4" to "Mai",
        "axe_x_annee_5" to "Juin",
        "axe_x_annee_6" to "Juil",
        "axe_x_annee_7" to "Aoû",
        "axe_x_annee_8" to "Sep",
        "axe_x_annee_9" to "Oct",
        "axe_x_annee_10" to "Nov",
        "axe_x_annee_11" to "Déc",

                // Alias axes jours (nouveau formatter StatsFragment)
        "axe_jour_lun" to "Lun",
        "axe_jour_mar" to "Mar",
        "axe_jour_mer" to "Mer",
        "axe_jour_jeu" to "Jeu",
        "axe_jour_ven" to "Ven",
        "axe_jour_sam" to "Sam",
        "axe_jour_dim" to "Dim",

        // Alias axes mois (nouveau formatter StatsFragment)
        "axe_mois_jan" to "Jan",
        "axe_mois_fev" to "Fév",
        "axe_mois_mar" to "Mar",
        "axe_mois_avr" to "Avr",
        "axe_mois_mai" to "Mai",
        "axe_mois_juin" to "Juin",
        "axe_mois_juil" to "Juil",
        "axe_mois_aou" to "Aoû",
        "axe_mois_sep" to "Sep",
        "axe_mois_oct" to "Oct",
        "axe_mois_nov" to "Nov",
        "axe_mois_dec" to "Déc",
        
        // Zone calculs
        "calculs_periode_jour" to "◷ Jour",
        "calculs_periode_semaine" to "▦ Semaine",
        "calculs_periode_mois" to "▣ Mois",
        "calculs_periode_annee" to "▢ Année",
        "calculs_unites" to "unités",
        "calculs_depenses" to "dépensés",
        "calculs_economies" to "économisés",
        
        // Bandeau profil
        "profil_complet" to "Profil: Complet ✓",
        "profil_incomplet" to "Profil: Incomplet",
        "total_aujourdhui" to "Total aujourd'hui:",
        "profil_complet_complet" to "Profil: Complet ✓",
        "profil_complet_incomplet" to "Profil: Incomplet",
        "profil_total_jour" to "Total aujourd'hui:",
        
        // Messages
        "erreur_chargement" to "Erreur chargement Stats",
        "aucune_donnee" to "Aucune donnée disponible"
    )

    // ==================== ENGLISH ====================
    private val TRADUCTIONS_EN = mapOf(
        // Titres sections
        "titre_graphique_consommation" to "Consumption",
        "titre_graphique_couts" to "Costs & Savings",
        
        // Boutons période
        "btn_jour" to "Day",
        "btn_semaine" to "Week",
        "btn_mois" to "Month",
        "btn_annee" to "Year",
        
        // Labels catégories
        "label_cigarettes" to "Cigarettes",
        "label_joints" to "Joints",
        "label_alcool_global" to "Alcohol overall",
        "label_bieres" to "Beers",
        "label_liqueurs" to "Liquors",
        "label_alcool_fort" to "Spirits",
        "label_limite" to "Limit",
        "label_couts" to "Costs",
        "label_economies" to "Savings",
        "label_depenses" to "Spending",
        
        // Labels axes X (jour)
        "axe_x_jour_0" to "00-07",
        "axe_x_jour_1" to "07-14",
        "axe_x_jour_2" to "14-21",
        "axe_x_jour_3" to "21-00",
        
        // Labels axes X (semaine)
        "axe_x_semaine_0" to "Mon",
        "axe_x_semaine_1" to "Tue",
        "axe_x_semaine_2" to "Wed",
        "axe_x_semaine_3" to "Thu",
        "axe_x_semaine_4" to "Fri",
        "axe_x_semaine_5" to "Sat",
        "axe_x_semaine_6" to "Sun",
        
        // Labels axes X (mois)
        "axe_x_mois_0" to "1",
        "axe_x_mois_1" to "6",
        "axe_x_mois_2" to "11",
        "axe_x_mois_3" to "16",
        "axe_x_mois_4" to "21",
        "axe_x_mois_5" to "26",
        "axe_x_mois_6" to "31",
        
        // Labels axes X (année)
        "axe_x_annee_0" to "Jan",
        "axe_x_annee_1" to "Feb",
        "axe_x_annee_2" to "Mar",
        "axe_x_annee_3" to "Apr",
        "axe_x_annee_4" to "May",
        "axe_x_annee_5" to "Jun",
        "axe_x_annee_6" to "Jul",
        "axe_x_annee_7" to "Aug",
        "axe_x_annee_8" to "Sep",
        "axe_x_annee_9" to "Oct",
        "axe_x_annee_10" to "Nov",
        "axe_x_annee_11" to "Dec",

                // Alias axes jours (nouveau formatter StatsFragment)
        "axe_jour_lun" to "Mon",
        "axe_jour_mar" to "Tue",
        "axe_jour_mer" to "Wed",
        "axe_jour_jeu" to "Thu",
        "axe_jour_ven" to "Fri",
        "axe_jour_sam" to "Sat",
        "axe_jour_dim" to "Sun",

        // Alias axes mois (nouveau formatter StatsFragment)
        "axe_mois_jan" to "Jan",
        "axe_mois_fev" to "Feb",
        "axe_mois_mar" to "Mar",
        "axe_mois_avr" to "Apr",
        "axe_mois_mai" to "May",
        "axe_mois_juin" to "Jun",
        "axe_mois_juil" to "Jul",
        "axe_mois_aou" to "Aug",
        "axe_mois_sep" to "Sep",
        "axe_mois_oct" to "Oct",
        "axe_mois_nov" to "Nov",
        "axe_mois_dec" to "Dec",
        
        // Zone calculs
        "calculs_periode_jour" to "◷ Day",
        "calculs_periode_semaine" to "▦ Week",
        "calculs_periode_mois" to "▣ Month",
        "calculs_periode_annee" to "▢ Year",
        "calculs_unites" to "units",
        "calculs_depenses" to "spent",
        "calculs_economies" to "saved",
        
        // Bandeau profil
        "profil_complet" to "Profile: Complete ✓",
        "profil_incomplet" to "Profile: Incomplete",
        "total_aujourdhui" to "Total today:",
        "profil_complet_complet" to "Profile: Complete ✓",
        "profil_complet_incomplet" to "Profile: Incomplete",
        "profil_total_jour" to "Total today:",

        // Messages
        "erreur_chargement" to "Error loading Stats",
        "aucune_donnee" to "No data available"
    )

    // ==================== ESPAÑOL ====================
    private val TRADUCTIONS_ES = mapOf(
        // Titres sections
        "titre_graphique_consommation" to "Consumo",
        "titre_graphique_couts" to "Costos y Ahorros",
        
        // Boutons période
        "btn_jour" to "Día",
        "btn_semaine" to "Semana",
        "btn_mois" to "Mes",
        "btn_annee" to "Año",
        
        // Labels catégories
        "label_cigarettes" to "Cigarrillos",
        "label_joints" to "Porros",
        "label_alcool_global" to "Alcohol global",
        "label_bieres" to "Cervezas",
        "label_liqueurs" to "Licores",
        "label_alcool_fort" to "Aguardiente",
        "label_limite" to "Límite",
        "label_couts" to "Costos",
        "label_economies" to "Ahorros",
        "label_depenses" to "Gastos",
        
        // Labels axes X (jour)
        "axe_x_jour_0" to "00-07",
        "axe_x_jour_1" to "07-14",
        "axe_x_jour_2" to "14-21",
        "axe_x_jour_3" to "21-00",
        
        // Labels axes X (semaine)
        "axe_x_semaine_0" to "Lun",
        "axe_x_semaine_1" to "Mar",
        "axe_x_semaine_2" to "Mié",
        "axe_x_semaine_3" to "Jue",
        "axe_x_semaine_4" to "Vie",
        "axe_x_semaine_5" to "Sáb",
        "axe_x_semaine_6" to "Dom",
        
        // Labels axes X (mois)
        "axe_x_mois_0" to "1",
        "axe_x_mois_1" to "6",
        "axe_x_mois_2" to "11",
        "axe_x_mois_3" to "16",
        "axe_x_mois_4" to "21",
        "axe_x_mois_5" to "26",
        "axe_x_mois_6" to "31",
        
        // Labels axes X (année)
        "axe_x_annee_0" to "Ene",
        "axe_x_annee_1" to "Feb",
        "axe_x_annee_2" to "Mar",
        "axe_x_annee_3" to "Abr",
        "axe_x_annee_4" to "May",
        "axe_x_annee_5" to "Jun",
        "axe_x_annee_6" to "Jul",
        "axe_x_annee_7" to "Ago",
        "axe_x_annee_8" to "Sep",
        "axe_x_annee_9" to "Oct",
        "axe_x_annee_10" to "Nov",
        "axe_x_annee_11" to "Dic",

                // Alias axes jours (nouveau formatter StatsFragment)
        "axe_jour_lun" to "Lun",
        "axe_jour_mar" to "Mar",
        "axe_jour_mer" to "Mié",
        "axe_jour_jeu" to "Jue",
        "axe_jour_ven" to "Vie",
        "axe_jour_sam" to "Sáb",
        "axe_jour_dim" to "Dom",

        // Alias axes mois (nouveau formatter StatsFragment)
        "axe_mois_jan" to "Ene",
        "axe_mois_fev" to "Feb",
        "axe_mois_mar" to "Mar",
        "axe_mois_avr" to "Abr",
        "axe_mois_mai" to "May",
        "axe_mois_juin" to "Jun",
        "axe_mois_juil" to "Jul",
        "axe_mois_aou" to "Ago",
        "axe_mois_sep" to "Sep",
        "axe_mois_oct" to "Oct",
        "axe_mois_nov" to "Nov",
        "axe_mois_dec" to "Dic",
        
        // Zone calculs
        "calculs_periode_jour" to "◷ Día",
        "calculs_periode_semaine" to "▦ Semana",
        "calculs_periode_mois" to "▣ Mes",
        "calculs_periode_annee" to "▢ Año",
        "calculs_unites" to "unidades",
        "calculs_depenses" to "gastados",
        "calculs_economies" to "ahorrados",
        
        // Bandeau profil
        "profil_complet" to "Perfil: Completo ✓",
        "profil_incomplet" to "Perfil: Incompleto",
        "total_aujourdhui" to "Total hoy:",
        "profil_complet_complet" to "Perfil: Completo ✓",
        "profil_complet_incomplet" to "Perfil: Incompleto",
        "profil_total_jour" to "Total hoy:",
        
        // Messages
        "erreur_chargement" to "Error al cargar Estadísticas",
        "aucune_donnee" to "No hay datos disponibles"
    )

    // ==================== PORTUGUÊS ====================
    private val TRADUCTIONS_PT = mapOf(
        // Titres sections
        "titre_graphique_consommation" to "Consumo",
        "titre_graphique_couts" to "Custos e Economias",
        
        // Boutons période
        "btn_jour" to "Dia",
        "btn_semaine" to "Semana",
        "btn_mois" to "Mês",
        "btn_annee" to "Ano",
        
        // Labels catégories
        "label_cigarettes" to "Cigarros",
        "label_joints" to "Baseados",
        "label_alcool_global" to "Álcool global",
        "label_bieres" to "Cervejas",
        "label_liqueurs" to "Licores",
        "label_alcool_fort" to "Destilados",
        "label_limite" to "Limite",
        "label_couts" to "Custos",
        "label_economies" to "Economias",
        "label_depenses" to "Gastos",

        // Labels axes X (jour)
        "axe_x_jour_0" to "00-07",
        "axe_x_jour_1" to "07-14",
        "axe_x_jour_2" to "14-21",
        "axe_x_jour_3" to "21-00",
        
        // Labels axes X (semaine)
        "axe_x_semaine_0" to "Seg",
        "axe_x_semaine_1" to "Ter",
        "axe_x_semaine_2" to "Qua",
        "axe_x_semaine_3" to "Qui",
        "axe_x_semaine_4" to "Sex",
        "axe_x_semaine_5" to "Sáb",
        "axe_x_semaine_6" to "Dom",
        
        // Labels axes X (mois)
        "axe_x_mois_0" to "1",
        "axe_x_mois_1" to "6",
        "axe_x_mois_2" to "11",
        "axe_x_mois_3" to "16",
        "axe_x_mois_4" to "21",
        "axe_x_mois_5" to "26",
        "axe_x_mois_6" to "31",
        
        // Labels axes X (année)
        "axe_x_annee_0" to "Jan",
        "axe_x_annee_1" to "Fev",
        "axe_x_annee_2" to "Mar",
        "axe_x_annee_3" to "Abr",
        "axe_x_annee_4" to "Mai",
        "axe_x_annee_5" to "Jun",
        "axe_x_annee_6" to "Jul",
        "axe_x_annee_7" to "Ago",
        "axe_x_annee_8" to "Set",
        "axe_x_annee_9" to "Out",
        "axe_x_annee_10" to "Nov",
        "axe_x_annee_11" to "Dez",

                // Alias axes jours (nouveau formatter StatsFragment)
        "axe_jour_lun" to "Seg",
        "axe_jour_mar" to "Ter",
        "axe_jour_mer" to "Qua",
        "axe_jour_jeu" to "Qui",
        "axe_jour_ven" to "Sex",
        "axe_jour_sam" to "Sáb",
        "axe_jour_dim" to "Dom",

        // Alias axes mois (nouveau formatter StatsFragment)
        "axe_mois_jan" to "Jan",
        "axe_mois_fev" to "Fev",
        "axe_mois_mar" to "Mar",
        "axe_mois_avr" to "Abr",
        "axe_mois_mai" to "Mai",
        "axe_mois_juin" to "Jun",
        "axe_mois_juil" to "Jul",
        "axe_mois_aou" to "Ago",
        "axe_mois_sep" to "Set",
        "axe_mois_oct" to "Out",
        "axe_mois_nov" to "Nov",
        "axe_mois_dec" to "Dez",
        
        // Zone calculs
        "calculs_periode_jour" to "◷ Dia",
        "calculs_periode_semaine" to "▦ Semana",
        "calculs_periode_mois" to "▣ Mês",
        "calculs_periode_annee" to "▢ Ano",
        "calculs_unites" to "unidades",
        "calculs_depenses" to "gastos",
        "calculs_economies" to "economizados",
        
        // Bandeau profil
        // Bandeau profil
        "profil_complet" to "Perfil: Completo ✓",
        "profil_incomplet" to "Perfil: Incompleto",
        "total_aujourdhui" to "Total hoje:",
        "profil_complet_complet" to "Perfil: Completo ✓",
        "profil_complet_incomplet" to "Perfil: Incompleto",
        "profil_total_jour" to "Total hoje:",
        
        // Messages
        "erreur_chargement" to "Erro ao carregar Estatísticas",
        "aucune_donnee" to "Nenhum dado disponível"
    )
    // ==================== DEUTSCH ====================
    private val TRADUCTIONS_DE = mapOf(
        // Titres sections
        "titre_graphique_consommation" to "Verbrauch",
        "titre_graphique_couts" to "Kosten & Ersparnisse",
        
        // Boutons période
        "btn_jour" to "Tag",
        "btn_semaine" to "Woche",
        "btn_mois" to "Monat",
        "btn_annee" to "Jahr",
        
        // Labels catégories
        "label_cigarettes" to "Zigaretten",
        "label_joints" to "Joints",
        "label_alcool_global" to "Alkohol gesamt",
        "label_bieres" to "Biere",
        "label_liqueurs" to "Liköre",
        "label_alcool_fort" to "Spirituosen",
        "label_limite" to "Grenze",
        "label_couts" to "Kosten",
        "label_economies" to "Ersparnisse",
        "label_depenses" to "Ausgaben",
        
        // Labels axes X (jour)
        "axe_x_jour_0" to "00-07",
        "axe_x_jour_1" to "07-14",
        "axe_x_jour_2" to "14-21",
        "axe_x_jour_3" to "21-00",
        
        // Labels axes X (semaine)
        "axe_x_semaine_0" to "Mon",
        "axe_x_semaine_1" to "Die",
        "axe_x_semaine_2" to "Mit",
        "axe_x_semaine_3" to "Don",
        "axe_x_semaine_4" to "Fre",
        "axe_x_semaine_5" to "Sam",
        "axe_x_semaine_6" to "Son",
        
        // Labels axes X (mois)
        "axe_x_mois_0" to "1",
        "axe_x_mois_1" to "6",
        "axe_x_mois_2" to "11",
        "axe_x_mois_3" to "16",
        "axe_x_mois_4" to "21",
        "axe_x_mois_5" to "26",
        "axe_x_mois_6" to "31",
        
        // Labels axes X (année)
        "axe_x_annee_0" to "Jan",
        "axe_x_annee_1" to "Feb",
        "axe_x_annee_2" to "Mär",
        "axe_x_annee_3" to "Apr",
        "axe_x_annee_4" to "Mai",
        "axe_x_annee_5" to "Jun",
        "axe_x_annee_6" to "Jul",
        "axe_x_annee_7" to "Aug",
        "axe_x_annee_8" to "Sep",
        "axe_x_annee_9" to "Okt",
        "axe_x_annee_10" to "Nov",
        "axe_x_annee_11" to "Dez",

                // Alias axes jours (nouveau formatter StatsFragment)
        "axe_jour_lun" to "Mon",
        "axe_jour_mar" to "Die",
        "axe_jour_mer" to "Mit",
        "axe_jour_jeu" to "Don",
        "axe_jour_ven" to "Fre",
        "axe_jour_sam" to "Sam",
        "axe_jour_dim" to "Son",

        // Alias axes mois (nouveau formatter StatsFragment)
        "axe_mois_jan" to "Jan",
        "axe_mois_fev" to "Feb",
        "axe_mois_mar" to "Mär",
        "axe_mois_avr" to "Apr",
        "axe_mois_mai" to "Mai",
        "axe_mois_juin" to "Jun",
        "axe_mois_juil" to "Jul",
        "axe_mois_aou" to "Aug",
        "axe_mois_sep" to "Sep",
        "axe_mois_oct" to "Okt",
        "axe_mois_nov" to "Nov",
        "axe_mois_dec" to "Dez",
        
        // Zone calculs
        "calculs_periode_jour" to "◷ Tag",
        "calculs_periode_semaine" to "▦ Woche",
        "calculs_periode_mois" to "▣ Monat",
        "calculs_periode_annee" to "▢ Jahr",
        "calculs_unites" to "Einheiten",
        "calculs_depenses" to "ausgegeben",
        "calculs_economies" to "gespart",
        
        // Bandeau profil
        "profil_complet" to "Profil: Vollständig ✓",
        "profil_incomplet" to "Profil: Unvollständig",
        "total_aujourdhui" to "Heute insgesamt:",
        "profil_complet_complet" to "Profil: Vollständig ✓",
        "profil_complet_incomplet" to "Profil: Unvollständig",
        "profil_total_jour" to "Heute insgesamt:",
        
        // Messages
        "erreur_chargement" to "Fehler beim Laden der Statistiken",
        "aucune_donnee" to "Keine Daten verfügbar"
    )

    // ==================== ITALIANO ====================
    private val TRADUCTIONS_IT = mapOf(
        // Titres sections
        "titre_graphique_consommation" to "Consumo",
        "titre_graphique_couts" to "Costi e Risparmi",
        
        // Boutons période
        "btn_jour" to "Giorno",
        "btn_semaine" to "Settimana",
        "btn_mois" to "Mese",
        "btn_annee" to "Anno",
        
        // Labels catégories
        "label_cigarettes" to "Sigarette",
        "label_joints" to "Spinelli",
        "label_alcool_global" to "Alcol globale",
        "label_bieres" to "Birre",
        "label_liqueurs" to "Liquori",
        "label_alcool_fort" to "Superalcolici",
        "label_limite" to "Limite",
        "label_couts" to "Costi",
        "label_economies" to "Risparmi",
        "label_depenses" to "Spese",
        
        // Labels axes X (jour)
        "axe_x_jour_0" to "00-07",
        "axe_x_jour_1" to "07-14",
        "axe_x_jour_2" to "14-21",
        "axe_x_jour_3" to "21-00",
        
        // Labels axes X (semaine)
        "axe_x_semaine_0" to "Lun",
        "axe_x_semaine_1" to "Mar",
        "axe_x_semaine_2" to "Mer",
        "axe_x_semaine_3" to "Gio",
        "axe_x_semaine_4" to "Ven",
        "axe_x_semaine_5" to "Sab",
        "axe_x_semaine_6" to "Dom",
        
        // Labels axes X (mois)
        "axe_x_mois_0" to "1",
        "axe_x_mois_1" to "6",
        "axe_x_mois_2" to "11",
        "axe_x_mois_3" to "16",
        "axe_x_mois_4" to "21",
        "axe_x_mois_5" to "26",
        "axe_x_mois_6" to "31",
        
        // Labels axes X (année)
        "axe_x_annee_0" to "Gen",
        "axe_x_annee_1" to "Feb",
        "axe_x_annee_2" to "Mar",
        "axe_x_annee_3" to "Apr",
        "axe_x_annee_4" to "Mag",
        "axe_x_annee_5" to "Giu",
        "axe_x_annee_6" to "Lug",
        "axe_x_annee_7" to "Ago",
        "axe_x_annee_8" to "Set",
        "axe_x_annee_9" to "Ott",
        "axe_x_annee_10" to "Nov",
        "axe_x_annee_11" to "Dic",

                // Alias axes jours (nouveau formatter StatsFragment)
        "axe_jour_lun" to "Lun",
        "axe_jour_mar" to "Mar",
        "axe_jour_mer" to "Mer",
        "axe_jour_jeu" to "Gio",
        "axe_jour_ven" to "Ven",
        "axe_jour_sam" to "Sab",
        "axe_jour_dim" to "Dom",

        // Alias axes mois (nouveau formatter StatsFragment)
        "axe_mois_jan" to "Gen",
        "axe_mois_fev" to "Feb",
        "axe_mois_mar" to "Mar",
        "axe_mois_avr" to "Apr",
        "axe_mois_mai" to "Mag",
        "axe_mois_juin" to "Giu",
        "axe_mois_juil" to "Lug",
        "axe_mois_aou" to "Ago",
        "axe_mois_sep" to "Set",
        "axe_mois_oct" to "Ott",
        "axe_mois_nov" to "Nov",
        "axe_mois_dec" to "Dic",
        
        // Zone calculs
        "calculs_periode_jour" to "◷ Giorno",
        "calculs_periode_semaine" to "▦ Settimana",
        "calculs_periode_mois" to "▣ Mese",
        "calculs_periode_annee" to "▢ Anno",
        "calculs_unites" to "unità",
        "calculs_depenses" to "spesi",
        "calculs_economies" to "risparmiati",
        
        // Bandeau profil
        "profil_complet" to "Profilo: Completo ✓",
        "profil_incomplet" to "Profilo: Incompleto",
        "total_aujourdhui" to "Totale oggi:",
        "profil_complet_complet" to "Profilo: Completo ✓",
        "profil_complet_incomplet" to "Profilo: Incompleto",
        "profil_total_jour" to "Totale oggi:",
        
        // Messages
        "erreur_chargement" to "Errore nel caricamento delle Statistiche",
        "aucune_donnee" to "Nessun dato disponibile"
    )

    // ==================== РУССКИЙ ====================
    private val TRADUCTIONS_RU = mapOf(
        // Titres sections
        "titre_graphique_consommation" to "Потребление",
        "titre_graphique_couts" to "Расходы и Сбережения",
        
        // Boutons période
        "btn_jour" to "День",
        "btn_semaine" to "Неделя",
        "btn_mois" to "Месяц",
        "btn_annee" to "Год",
        
        // Labels catégories
        "label_cigarettes" to "Сигареты",
        "label_joints" to "Косяки",
        "label_alcool_global" to "Алкоголь общий",
        "label_bieres" to "Пиво",
        "label_liqueurs" to "Ликёры",
        "label_alcool_fort" to "Крепкий алкоголь",
        "label_limite" to "Лимит",
        "label_couts" to "Расходы",
        "label_economies" to "Сбережения",
        "label_depenses" to "Расходы",
        
        // Labels axes X (jour)
        "axe_x_jour_0" to "00-07",
        "axe_x_jour_1" to "07-14",
        "axe_x_jour_2" to "14-21",
        "axe_x_jour_3" to "21-00",
        
        // Labels axes X (semaine)
        "axe_x_semaine_0" to "Пн",
        "axe_x_semaine_1" to "Вт",
        "axe_x_semaine_2" to "Ср",
        "axe_x_semaine_3" to "Чт",
        "axe_x_semaine_4" to "Пт",
        "axe_x_semaine_5" to "Сб",
        "axe_x_semaine_6" to "Вс",
        
        // Labels axes X (mois)
        "axe_x_mois_0" to "1",
        "axe_x_mois_1" to "6",
        "axe_x_mois_2" to "11",
        "axe_x_mois_3" to "16",
        "axe_x_mois_4" to "21",
        "axe_x_mois_5" to "26",
        "axe_x_mois_6" to "31",
        
        // Labels axes X (année)
        "axe_x_annee_0" to "Янв",
        "axe_x_annee_1" to "Фев",
        "axe_x_annee_2" to "Мар",
        "axe_x_annee_3" to "Апр",
        "axe_x_annee_4" to "Май",
        "axe_x_annee_5" to "Июн",
        "axe_x_annee_6" to "Июл",
        "axe_x_annee_7" to "Авг",
        "axe_x_annee_8" to "Сен",
        "axe_x_annee_9" to "Окт",
        "axe_x_annee_10" to "Ноя",
        "axe_x_annee_11" to "Дек",

                // Alias axes jours (nouveau formatter StatsFragment)
        "axe_jour_lun" to "Пн",
        "axe_jour_mar" to "Вт",
        "axe_jour_mer" to "Ср",
        "axe_jour_jeu" to "Чт",
        "axe_jour_ven" to "Пт",
        "axe_jour_sam" to "Сб",
        "axe_jour_dim" to "Вс",

        // Alias axes mois (nouveau formatter StatsFragment)
        "axe_mois_jan" to "Янв",
        "axe_mois_fev" to "Фев",
        "axe_mois_mar" to "Мар",
        "axe_mois_avr" to "Апр",
        "axe_mois_mai" to "Май",
        "axe_mois_juin" to "Июн",
        "axe_mois_juil" to "Июл",
        "axe_mois_aou" to "Авг",
        "axe_mois_sep" to "Сен",
        "axe_mois_oct" to "Окт",
        "axe_mois_nov" to "Ноя",
        "axe_mois_dec" to "Дек",
        
        // Zone calculs
        "calculs_periode_jour" to "◷ День",
        "calculs_periode_semaine" to "▦ Неделя",
        "calculs_periode_mois" to "▣ Месяц",
        "calculs_periode_annee" to "▢ Год",
        "calculs_unites" to "единиц",
        "calculs_depenses" to "потрачено",
        "calculs_economies" to "сэкономлено",
        
       // Bandeau profil
        "profil_complet" to "Профиль: Полный ✓",
        "profil_incomplet" to "Профиль: Неполный",
        "total_aujourdhui" to "Всего сегодня:",
        "profil_complet_complet" to "Профиль: Полный ✓",
        "profil_complet_incomplet" to "Профиль: Неполный",
        "profil_total_jour" to "Всего сегодня:",
                
        // Messages
        "erreur_chargement" to "Ошибка загрузки Статистики",
        "aucune_donnee" to "Нет доступных данных"
    )
    // ==================== العربية (ARABE) ====================
    private val TRADUCTIONS_AR = mapOf(
        // Titres sections
        "titre_graphique_consommation" to "الاستهلاك",
        "titre_graphique_couts" to "التكاليف والمدخرات",
        
        // Boutons période
        "btn_jour" to "يوم",
        "btn_semaine" to "أسبوع",
        "btn_mois" to "شهر",
        "btn_annee" to "سنة",
        
        // Labels catégories
        "label_cigarettes" to "السجائر",
        "label_joints" to "المفاصل",
        "label_alcool_global" to "الكحول العام",
        "label_bieres" to "البيرة",
        "label_liqueurs" to "المشروبات الكحولية",
        "label_alcool_fort" to "الكحول القوي",
        "label_limite" to "الحد",
        "label_couts" to "التكاليف",
        "label_economies" to "المدخرات",
        "label_depenses" to "المصروفات",
        
        // Labels axes X (jour)
        "axe_x_jour_0" to "00-07",
        "axe_x_jour_1" to "07-14",
        "axe_x_jour_2" to "14-21",
        "axe_x_jour_3" to "21-00",
        
        // Labels axes X (semaine)
        "axe_x_semaine_0" to "الاثنين",
        "axe_x_semaine_1" to "الثلاثاء",
        "axe_x_semaine_2" to "الأربعاء",
        "axe_x_semaine_3" to "الخميس",
        "axe_x_semaine_4" to "الجمعة",
        "axe_x_semaine_5" to "السبت",
        "axe_x_semaine_6" to "الأحد",
        
        // Labels axes X (mois)
        "axe_x_mois_0" to "1",
        "axe_x_mois_1" to "6",
        "axe_x_mois_2" to "11",
        "axe_x_mois_3" to "16",
        "axe_x_mois_4" to "21",
        "axe_x_mois_5" to "26",
        "axe_x_mois_6" to "31",
        
        // Labels axes X (année)
        "axe_x_annee_0" to "يناير",
        "axe_x_annee_1" to "فبراير",
        "axe_x_annee_2" to "مارس",
        "axe_x_annee_3" to "أبريل",
        "axe_x_annee_4" to "مايو",
        "axe_x_annee_5" to "يونيو",
        "axe_x_annee_6" to "يوليو",
        "axe_x_annee_7" to "أغسطس",
        "axe_x_annee_8" to "سبتمبر",
        "axe_x_annee_9" to "أكتوبر",
        "axe_x_annee_10" to "نوفمبر",
        "axe_x_annee_11" to "ديسمبر",

                // Alias axes jours (nouveau formatter StatsFragment)
        "axe_jour_lun" to "الاثنين",
        "axe_jour_mar" to "الثلاثاء",
        "axe_jour_mer" to "الأربعاء",
        "axe_jour_jeu" to "الخميس",
        "axe_jour_ven" to "الجمعة",
        "axe_jour_sam" to "السبت",
        "axe_jour_dim" to "الأحد",

        // Alias axes mois (nouveau formatter StatsFragment)
        "axe_mois_jan" to "يناير",
        "axe_mois_fev" to "فبراير",
        "axe_mois_mar" to "مارس",
        "axe_mois_avr" to "أبريل",
        "axe_mois_mai" to "مايو",
        "axe_mois_juin" to "يونيو",
        "axe_mois_juil" to "يوليو",
        "axe_mois_aou" to "أغسطس",
        "axe_mois_sep" to "سبتمبر",
        "axe_mois_oct" to "أكتوبر",
        "axe_mois_nov" to "نوفمبر",
        "axe_mois_dec" to "ديسمبر",
        
        // Zone calculs
        "calculs_periode_jour" to "◷ يوم",
        "calculs_periode_semaine" to "▦ أسبوع",
        "calculs_periode_mois" to "▣ شهر",
        "calculs_periode_annee" to "▢ سنة",
        "calculs_unites" to "وحدات",
        "calculs_depenses" to "منفق",
        "calculs_economies" to "محفوظ",
        
        // Bandeau profil
        "profil_complet" to "✓ الملف: كامل",
        "profil_incomplet" to "الملف: غير كامل",
        "total_aujourdhui" to ":المجموع اليوم",
        "profil_complet_complet" to "✓ الملف: كامل",
        "profil_complet_incomplet" to "الملف: غير كامل",
        "profil_total_jour" to ":المجموع اليوم",
        
        // Messages
        "erreur_chargement" to "خطأ في تحميل الإحصائيات",
        "aucune_donnee" to "لا توجد بيانات متاحة"
    )

    // ==================== हिन्दी (HINDI) ====================
    private val TRADUCTIONS_HI = mapOf(
        // Titres sections
        "titre_graphique_consommation" to "खपत",
        "titre_graphique_couts" to "लागत और बचत",
        
        // Boutons période
        "btn_jour" to "दिन",
        "btn_semaine" to "सप्ताह",
        "btn_mois" to "महीना",
        "btn_annee" to "वर्ष",
        
        // Labels catégories
        "label_cigarettes" to "सिगरेट",
        "label_joints" to "जोड़",
        "label_alcool_global" to "शराब कुल",
        "label_bieres" to "बीयर",
        "label_liqueurs" to "शराब",
        "label_alcool_fort" to "मजबूत शराब",
        "label_limite" to "सीमा",
        "label_couts" to "लागत",
        "label_economies" to "बचत",
        "label_depenses" to "खर्च",
        
        // Labels axes X (jour)
        "axe_x_jour_0" to "00-07",
        "axe_x_jour_1" to "07-14",
        "axe_x_jour_2" to "14-21",
        "axe_x_jour_3" to "21-00",
        
        // Labels axes X (semaine)
        "axe_x_semaine_0" to "सोम",
        "axe_x_semaine_1" to "मंगल",
        "axe_x_semaine_2" to "बुध",
        "axe_x_semaine_3" to "गुरु",
        "axe_x_semaine_4" to "शुक्र",
        "axe_x_semaine_5" to "शनि",
        "axe_x_semaine_6" to "रवि",
        
        // Labels axes X (mois)
        "axe_x_mois_0" to "1",
        "axe_x_mois_1" to "6",
        "axe_x_mois_2" to "11",
        "axe_x_mois_3" to "16",
        "axe_x_mois_4" to "21",
        "axe_x_mois_5" to "26",
        "axe_x_mois_6" to "31",
        
        // Labels axes X (année)
        "axe_x_annee_0" to "जन",
        "axe_x_annee_1" to "फर",
        "axe_x_annee_2" to "मार",
        "axe_x_annee_3" to "अप्र",
        "axe_x_annee_4" to "मई",
        "axe_x_annee_5" to "जून",
        "axe_x_annee_6" to "जुल",
        "axe_x_annee_7" to "अग",
        "axe_x_annee_8" to "सित",
        "axe_x_annee_9" to "अक्ट",
        "axe_x_annee_10" to "नव",
        "axe_x_annee_11" to "दिस",

                 // Alias axes jours (nouveau formatter StatsFragment)
        "axe_jour_lun" to "सोम",
        "axe_jour_mar" to "मंगल",
        "axe_jour_mer" to "बुध",
        "axe_jour_jeu" to "गुरु",
        "axe_jour_ven" to "शुक्र",
        "axe_jour_sam" to "शनि",
        "axe_jour_dim" to "रवि",

        // Alias axes mois (nouveau formatter StatsFragment)
        "axe_mois_jan" to "जन",
        "axe_mois_fev" to "फर",
        "axe_mois_mar" to "मार",
        "axe_mois_avr" to "अप्र",
        "axe_mois_mai" to "मई",
        "axe_mois_juin" to "जून",
        "axe_mois_juil" to "जुल",
        "axe_mois_aou" to "अग",
        "axe_mois_sep" to "सित",
        "axe_mois_oct" to "अक्ट",
        "axe_mois_nov" to "नव",
        "axe_mois_dec" to "दिस",
        
        // Zone calculs
        "calculs_periode_jour" to "◷ दिन",
        "calculs_periode_semaine" to "▦ सप्ताह",
        "calculs_periode_mois" to "▣ महीना",
        "calculs_periode_annee" to "▢ वर्ष",
        "calculs_unites" to "इकाइयां",
        "calculs_depenses" to "खर्च",
        "calculs_economies" to "बचाया",
        
        // Bandeau profil
        "profil_complet" to "प्रोफ़ाइल: पूर्ण ✓",
        "profil_incomplet" to "प्रोफ़ाइल: अपूर्ण",
        "total_aujourdhui" to "आज कुल:",
        "profil_complet_complet" to "प्रोफ़ाइल: पूर्ण ✓",
        "profil_complet_incomplet" to "प्रोफ़ाइल: अपूर्ण",
        "profil_total_jour" to "आज कुल:",
        
        // Messages
        "erreur_chargement" to "आँकड़े लोड करने में त्रुटि",
        "aucune_donnee" to "कोई डेटा उपलब्ध नहीं"
    )

    // ==================== 日本語 (JAPONAIS) ====================
    private val TRADUCTIONS_JA = mapOf(
        // Titres sections
        "titre_graphique_consommation" to "消費",
        "titre_graphique_couts" to "費用と節約",
        
        // Boutons période
        "btn_jour" to "日",
        "btn_semaine" to "週",
        "btn_mois" to "月",
        "btn_annee" to "年",
        
        // Labels catégories
        "label_cigarettes" to "タバコ",
        "label_joints" to "ジョイント",
        "label_alcool_global" to "アルコール全般",
        "label_bieres" to "ビール",
        "label_liqueurs" to "リキュール",
        "label_alcool_fort" to "強いアルコール",
        "label_limite" to "制限",
        "label_couts" to "費用",
        "label_economies" to "節約",
        "label_depenses" to "支出",
        
        // Labels axes X (jour)
        "axe_x_jour_0" to "00-07",
        "axe_x_jour_1" to "07-14",
        "axe_x_jour_2" to "14-21",
        "axe_x_jour_3" to "21-00",
        
        // Labels axes X (semaine)
        "axe_x_semaine_0" to "月",
        "axe_x_semaine_1" to "火",
        "axe_x_semaine_2" to "水",
        "axe_x_semaine_3" to "木",
        "axe_x_semaine_4" to "金",
        "axe_x_semaine_5" to "土",
        "axe_x_semaine_6" to "日",
        
        // Labels axes X (mois)
        "axe_x_mois_0" to "1",
        "axe_x_mois_1" to "6",
        "axe_x_mois_2" to "11",
        "axe_x_mois_3" to "16",
        "axe_x_mois_4" to "21",
        "axe_x_mois_5" to "26",
        "axe_x_mois_6" to "31",
        
        // Labels axes X (année)
        "axe_x_annee_0" to "1月",
        "axe_x_annee_1" to "2月",
        "axe_x_annee_2" to "3月",
        "axe_x_annee_3" to "4月",
        "axe_x_annee_4" to "5月",
        "axe_x_annee_5" to "6月",
        "axe_x_annee_6" to "7月",
        "axe_x_annee_7" to "8月",
        "axe_x_annee_8" to "9月",
        "axe_x_annee_9" to "10月",
        "axe_x_annee_10" to "11月",
        "axe_x_annee_11" to "12月",

                // Alias axes jours (nouveau formatter StatsFragment)
        "axe_jour_lun" to "月",
        "axe_jour_mar" to "火",
        "axe_jour_mer" to "水",
        "axe_jour_jeu" to "木",
        "axe_jour_ven" to "金",
        "axe_jour_sam" to "土",
        "axe_jour_dim" to "日",

        // Alias axes mois (nouveau formatter StatsFragment)
        "axe_mois_jan" to "1月",
        "axe_mois_fev" to "2月",
        "axe_mois_mar" to "3月",
        "axe_mois_avr" to "4月",
        "axe_mois_mai" to "5月",
        "axe_mois_juin" to "6月",
        "axe_mois_juil" to "7月",
        "axe_mois_aou" to "8月",
        "axe_mois_sep" to "9月",
        "axe_mois_oct" to "10月",
        "axe_mois_nov" to "11月",
        "axe_mois_dec" to "12月",
        
        // Zone calculs
        "calculs_periode_jour" to "◷ 日",
        "calculs_periode_semaine" to "▦ 週",
        "calculs_periode_mois" to "▣ 月",
        "calculs_periode_annee" to "▢ 年",
        "calculs_unites" to "単位",
        "calculs_depenses" to "支出",
        "calculs_economies" to "節約",
        
        // Bandeau profil
        "profil_complet" to "プロフィール：完全 ✓",
        "profil_incomplet" to "プロフィール：不完全",
        "total_aujourdhui" to "今日の合計：",
        "profil_complet_complet" to "プロフィール：完全 ✓",
        "profil_complet_incomplet" to "プロフィール：不完全",
        "profil_total_jour" to "今日の合計：",
                
        // Messages
        "erreur_chargement" to "統計の読み込みエラー",
        "aucune_donnee" to "データがありません"
    )

    /**
     * Fonction helper pour récupérer une traduction spécifique
     */
    fun getTexte(key: String, codeLangue: String): String {
        return try {
            val traductions = getTraductions(codeLangue)
            traductions[key] ?: key
        } catch (e: Exception) {
            Log.e(TAG, "Erreur récupération texte $key: ${e.message}")
            key
        }
    }

    /**
     * Fonction helper pour récupérer un label d'axe X
     */
    fun getAxeXLabel(periode: String, index: Int, codeLangue: String): String {
        return try {
            val key = when (periode) {
                "jour" -> "axe_x_jour_$index"
                "semaine" -> "axe_x_semaine_$index"
                "mois" -> "axe_x_mois_$index"
                "annee" -> "axe_x_annee_$index"
                else -> ""
            }
            getTexte(key, codeLangue)
        } catch (e: Exception) {
            Log.e(TAG, "Erreur récupération label axe X: ${e.message}")
            index.toString()
        }
    }
}
