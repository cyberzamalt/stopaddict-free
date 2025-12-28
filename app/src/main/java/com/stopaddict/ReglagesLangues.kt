package com.stopaddict

object ReglagesLangues {

    private const val TAG = "ReglagesLangues"

     /**
     * Retourne toutes les traductions pour une langue donnée
     */
    fun getTraductions(langue: String): Map<String, String> {
    val base = TRADUCTIONS_FR

    val specific = when (langue.uppercase()) {
        "EN"  -> TRADUCTIONS_EN
        "ES"  -> TRADUCTIONS_ES
        "PT"  -> TRADUCTIONS_PT
        "DE"  -> TRADUCTIONS_DE
        "IT"  -> TRADUCTIONS_IT
        "RU"  -> TRADUCTIONS_RU
        "AR"  -> TRADUCTIONS_AR
        "HI"  -> TRADUCTIONS_HI
        "JA"  -> TRADUCTIONS_JA
        "NL"  -> TRADUCTIONS_NL
        "ZH"  -> TRADUCTIONS_ZH      // Chinois (simplifié)
        "ZHT" -> TRADUCTIONS_ZHT     // Chinois (traditionnel)
        else  -> TRADUCTIONS_FR
    }

    // base + specific => si une clé manque dans la langue, on retombe sur le FR
    return base + specific
}

    // ==================== FRANÇAIS ====================
    val TRADUCTIONS_FR: Map<String, String> = linkedMapOf(
        // Titres sections
        "titre" to "Réglages",
        "titre_profil" to "Profil",
        "titre_categories" to "Catégories / Coûts",
        "titre_couts_cigarettes" to "Coûts cigarettes",
        "titre_couts_joints" to "Coûts joints",
        "titre_couts_alcool" to "Coûts alcool",
        "titre_habitudes" to "Habitudes quotidiennes",
        "titre_dates_objectifs" to "Dates objectifs",
        "titre_raz_export" to "RAZ & Export/Import",

        // Labels profil
        "label_prenom" to "Prénom",
        "label_langue" to "Langue",
        "label_devise" to "Devise",
        "hint_prenom" to "Entrer votre prénom",
        "btn_sauvegarder_profil" to "Sauvegarder",

        // Labels catégories
        "label_cigarettes" to "Cigarettes",
        "label_joints" to "Joints (Cannabis)",
        "label_alcool_global" to "Alcool global",
        "label_bieres" to "Bières",
        "label_liqueurs" to "Liqueurs",
        "label_alcool_fort" to "Alcool fort",

        // Formulaires cigarettes
        "radio_classiques" to "Cigarettes paquet classique",
        "radio_rouler" to "Cigarettes à rouler",
        "radio_tubeuse" to "Cigarettes à tuber",
        "label_prix_paquet" to "Prix du paquet",
        "label_nb_cigarettes" to "Nombre de cigarettes",
        "label_prix_tabac" to "Prix du tabac",
        "label_prix_feuilles" to "Prix des feuilles",
        "label_nb_feuilles" to "Nombre de feuilles",
        "label_prix_filtres" to "Prix du sachet de filtres",
        "label_nb_filtres" to "Nombre de filtres",
        "label_prix_tubes" to "Prix des tubes",
        "label_nb_tubes" to "Nombre de tubes",

        // Formulaires joints
        "label_prix_gramme" to "Prix du gramme",
        "label_gramme_par_joint" to "Grammes par joint",
        "label_prix_feuilles_longues" to "Prix des feuilles longues",
        "label_nb_feuilles_longues" to "Nombre de feuilles longues",

        // Formulaires alcool
        "label_prix_verre" to "Prix du verre",
        "label_unite_cl" to "Unité (cL)",

        // Habitudes
        "label_max_journalier" to "Objectif maximum par jour",

        // Dates
        "label_date_reduction" to "Date de réduction",
        "label_date_arret" to "Date d'arrêt",
        "label_date_reussite" to "Date de réussite",
        "btn_choisir_date" to "Choisir une date",

        // Messages limites / infos
        "msg_export_limite" to "Pour accéder à l'exportation, passez à la version sans publicité pour en profiter :-)",
        "msg_import_limite" to "Limite atteinte. %d import(s) restant(s) aujourd'hui.",
        "msg_limite_export" to "Limite atteinte: %d export(s) restant(s) aujourd'hui (version gratuite)",
        "msg_limite_import" to "Limite atteinte: %d import(s) restant(s) aujourd'hui (version gratuite)",

        // Boutons RAZ
        "btn_raz_jour" to "RAZ du jour",
        "btn_raz_historique" to "RAZ historique",
        "btn_raz_usine" to "RAZ d'usine",

        // Export/Import
        "btn_exporter" to "Exporter",
        "btn_importer" to "Importer",
        "btn_export_logs" to "Exporter les logs",

        // Confirmations
        "confirm_raz_jour_titre" to "RAZ du jour",
        "confirm_raz_jour_message" to "Supprimer toutes les consommations d'aujourd'hui ?",
        "confirm_raz_historique_titre" to "RAZ de l'historique",
        "confirm_raz_historique_message" to "Supprimer TOUT l'historique de consommation ? (Cette action est irréversible)",
        "confirm_raz_usine_titre" to "RAZ d'usine",
        "confirm_raz_usine_message" to "TOUT réinitialiser (profil, consommations, paramètres) ? (Cette action est irréversible)",
        "confirm_import_titre" to "Importer sauvegarde",
        "confirm_import_message" to "Toutes les données actuelles seront remplacées. Continuer ?",
        "confirm_default" to "Confirmer ?",

        // Succès/erreurs
        "msg_profil_sauvegarde" to "Profil sauvegardé",
        "msg_couts_sauvegardes" to "Coûts sauvegardés",
        "msg_date_enregistree" to "Date enregistrée",
        "msg_raz_effectuee" to "RAZ effectuée",
        "msg_export_reussi" to "Export réussi",
        "msg_import_reussi" to "Import réussi",
        "msg_import_termine" to "Import terminé avec succès",
        "msg_import_termine_succes" to "Import terminé avec succès",
        "msg_erreur_sauvegarde" to "Erreur sauvegarde",
        "msg_erreur_date" to "Erreur sauvegarde date",
        "msg_erreur_raz" to "Erreur RAZ",
        "msg_erreur_export" to "Erreur export",
        "msg_erreur_import" to "Erreur import",
        "msg_import_erreur" to "Erreur lors de l'import : %s",
        "msg_donnees_invalides" to "Erreur import: données invalides",
        "msg_aucune_sauvegarde" to "Aucun fichier de sauvegarde trouvé",
        "msg_champs_obligatoires" to "Veuillez renseigner au moins un coût",
        "msg_erreur_prefix" to "Erreur : %s",

        // Bandeau profil
        "profil_complet" to "Profil: Complet ✓",
        "profil_incomplet" to "Profil: Incomplet",
        "total_aujourdhui" to "Total aujourd'hui:",

        // Économies
        "titre_economies" to "Économies potentielles",
        "economies_si_arret" to "Si vous arrêtiez complètement:",
        "economies_jour" to "Jour",
        "economies_semaine" to "Semaine",
        "economies_mois" to "Mois",
        "economies_annee" to "Année",

        // Dialog
        "btn_confirmer" to "Confirmer",
        "btn_annuler" to "Annuler",
        "btn_ok" to "Fermer",

        // À propos
        "titre_a_propos" to "À propos",
        "voir_avertissement" to "Voir l'avertissement",
        "btn_manuel" to "Manuel d'utilisation",
        "btn_cgv" to "Conditions générales de vente (CGV)",
        "btn_mentions_legales" to "Mentions légales",
        "btn_contact" to "Contact support",
        "btn_maj" to "Dernières mises à jour",
        "maj_titre" to "Dernières mises à jour",
        "maj_contenu" to "Déploiement V1",

        // Premium
        "btn_premium" to "Version sans publicité",
        "premium_titre" to "Version sans publicité",
        "premium_contenu" to "La version sans publicité sera bientôt disponible.\n\nElle supprimera les bandeaux pubs et les limitations.",

        // RAZ libellés + confirmations OK
        "raz_sauvegarde" to "RAZ & sauvegarde",
        "raz_jour" to "RAZ jour",
        "raz_historique" to "RAZ historique",
        "raz_usine" to "RAZ réglages usine",
        "raz_jour_ok" to "RAZ jour effectué",
        "raz_historique_ok" to "RAZ historique effectué",
        "raz_usine_ok" to "RAZ usine effectué",

        // Unités cL
        "unite_cl_global" to "Unité en cL",
        "unite_cl_biere" to "Unité en cL",
        "unite_cl_liqueur" to "Unité en cL",
        "unite_cl_alcool_fort" to "Unité en cL",

        // Sauvegarde coûts + logs
        "btn_sauvegarder_couts" to "Sauvegarder les coûts",
        "msg_export_logs_impossible" to "Impossible d’exporter les logs",

        // Support / store
        "support_email_subject" to "StopAddict - Support",
        "support_email_error" to "Impossible d'ouvrir l'application e-mail : %s",
        "msg_open_store_impossible" to "Impossible d'ouvrir le store",

        // Divers
        "sauvegarde_ok" to "Sauvegardé"
    )

    // ==================== ENGLISH ====================
    val TRADUCTIONS_EN: Map<String, String> = linkedMapOf(
        // Titres sections
        "titre" to "Settings",
        "titre_profil" to "Profile",
        "titre_categories" to "Categories / Costs",
        "titre_couts_cigarettes" to "Cigarettes costs",
        "titre_couts_joints" to "Joints costs",
        "titre_couts_alcool" to "Alcohol costs",
        "titre_habitudes" to "Daily habits",
        "titre_dates_objectifs" to "Goal dates",
        "titre_raz_export" to "Reset & Export/Import",

        // Labels profil
        "label_prenom" to "First name",
        "label_langue" to "Language",
        "label_devise" to "Currency",
        "hint_prenom" to "Enter your first name",
        "btn_sauvegarder_profil" to "Save",

        // Labels catégories
        "label_cigarettes" to "Cigarettes",
        "label_joints" to "Joints (Cannabis)",
        "label_alcool_global" to "Alcohol overall",
        "label_bieres" to "Beers",
        "label_liqueurs" to "Liquors",
        "label_alcool_fort" to "Spirits",

        // Formulaires cigarettes
        "radio_classiques" to "Regular cigarettes (pack)",
        "radio_rouler" to "Rolling tobacco",
        "radio_tubeuse" to "Tube machine",
        "label_prix_paquet" to "Pack price",
        "label_nb_cigarettes" to "Number of cigarettes",
        "label_prix_tabac" to "Tobacco price",
        "label_prix_feuilles" to "Papers price",
        "label_nb_feuilles" to "Number of papers",
        "label_prix_filtres" to "Filters price",
        "label_nb_filtres" to "Number of filters",
        "label_prix_tubes" to "Tubes price",
        "label_nb_tubes" to "Number of tubes",

        // Formulaires joints
        "label_prix_gramme" to "Price per gram",
        "label_gramme_par_joint" to "Grams per joint",
        "label_prix_feuilles_longues" to "Long papers price",
        "label_nb_feuilles_longues" to "Number of long papers",

        // Formulaires alcool
        "label_prix_verre" to "Drink price",
        "label_unite_cl" to "Unit (cL)",

        // Habitudes
        "label_max_journalier" to "Maximum daily goal",

        // Dates
        "label_date_reduction" to "Reduction date",
        "label_date_arret" to "Quit date",
        "label_date_reussite" to "Success date",
        "btn_choisir_date" to "Choose a date",

        // Messages limites / infos
        "msg_export_limite" to "To access export, upgrade to the ad-free version to enjoy it :-)",
        "msg_import_limite" to "Limit reached. %d import(s) remaining today.",
        "msg_limite_export" to "Limit reached: %d export(s) remaining today (free version)",
        "msg_limite_import" to "Limit reached: %d import(s) remaining today (free version)",

        // Boutons RAZ
        "btn_raz_jour" to "Reset today",
        "btn_raz_historique" to "Reset history",
        "btn_raz_usine" to "Factory reset",

        // Export/Import
        "btn_exporter" to "Export",
        "btn_importer" to "Import",
        "btn_export_logs" to "Export logs",

        // Confirmations
        "confirm_raz_jour_titre" to "Reset today",
        "confirm_raz_jour_message" to "Delete all today's consumption?",
        "confirm_raz_historique_titre" to "Reset history",
        "confirm_raz_historique_message" to "Delete ALL consumption history? (This action is irreversible)",
        "confirm_raz_usine_titre" to "Factory reset",
        "confirm_raz_usine_message" to "Reset EVERYTHING (profile, consumption, settings)? (This action is irreversible)",
        "confirm_import_titre" to "Import backup",
        "confirm_import_message" to "All current data will be replaced. Continue?",
        "confirm_default" to "Confirm?",

        // Succès/erreurs
        "msg_profil_sauvegarde" to "Profile saved",
        "msg_couts_sauvegardes" to "Costs saved",
        "msg_date_enregistree" to "Date saved",
        "msg_raz_effectuee" to "Reset completed",
        "msg_export_reussi" to "Export successful",
        "msg_import_reussi" to "Import successful",
        "msg_import_termine" to "Import completed successfully",
        "msg_import_termine_succes" to "Import completed successfully",
        "msg_erreur_sauvegarde" to "Save error",
        "msg_erreur_date" to "Date save error",
        "msg_erreur_raz" to "Reset error",
        "msg_erreur_export" to "Export error",
        "msg_erreur_import" to "Import error",
        "msg_import_erreur" to "Import error: %s",
        "msg_donnees_invalides" to "Import error: invalid data",
        "msg_aucune_sauvegarde" to "No backup file found",
        "msg_champs_obligatoires" to "Please enter at least one cost",
        "msg_erreur_prefix" to "Error: %s",

        // Bandeau profil
        "profil_complet" to "Profile: Complete ✓",
        "profil_incomplet" to "Profile: Incomplete",
        "total_aujourdhui" to "Total today:",

        // Économies
        "titre_economies" to "Potential savings",
        "economies_si_arret" to "If you quit completely:",
        "economies_jour" to "Day",
        "economies_semaine" to "Week",
        "economies_mois" to "Month",
        "economies_annee" to "Year",

        // Dialog
        "btn_confirmer" to "Confirm",
        "btn_annuler" to "Cancel",
        "btn_ok" to "Close",

        // About
        "titre_a_propos" to "About",
        "voir_avertissement" to "View warning",
        "btn_manuel" to "User manual",
        "btn_cgv" to "Terms and conditions",
        "btn_mentions_legales" to "Legal notice",
        "btn_contact" to "Contact support",
        "btn_maj" to "Latest updates",
        "maj_titre" to "Latest updates",
        "maj_contenu" to "Deployment V1",

        // Premium
        "btn_premium" to "Ad-free version",
        "premium_titre" to "Ad-free version",
        "premium_contenu" to "The ad-free version will be available soon.\n\nIt will remove banner ads and limits.",

        // Reset libellés + confirmations OK
        "raz_sauvegarde" to "Reset & backup",
        "raz_jour" to "Reset day",
        "raz_historique" to "Reset history",
        "raz_usine" to "Factory reset",
        "raz_jour_ok" to "Day reset done",
        "raz_historique_ok" to "History reset done",
        "raz_usine_ok" to "Factory reset done",

        // Unités cL
        "unite_cl_global" to "Unit (cL)",
        "unite_cl_biere" to "Unit (cL)",
        "unite_cl_liqueur" to "Unit (cL)",
        "unite_cl_alcool_fort" to "Unit (cL)",

        // Sauvegarde coûts + logs
        "btn_sauvegarder_couts" to "Save costs",
        "msg_export_logs_impossible" to "Unable to export logs",

        // Support / store
        "support_email_subject" to "StopAddict - Support",
        "support_email_error" to "Unable to open the email app: %s",
        "msg_open_store_impossible" to "Unable to open the store",

        // Divers
        "sauvegarde_ok" to "Saved"
    )
    
    // ==================== ESPAÑOL ====================
private val TRADUCTIONS_ES = mapOf(
    // Titres sections
    "titre" to "Ajustes",
    "titre_profil" to "Perfil",
    "titre_categories" to "Categorías activas",
    "titre_couts_cigarettes" to "Costes de cigarrillos",
    "titre_couts_joints" to "Costes de porros",
    "titre_couts_alcool" to "Costes de alcohol",
    "titre_habitudes" to "Hábitos diarios",
    "titre_dates_objectifs" to "Fechas objetivo",
    "titre_raz_export" to "Reiniciar y Exportar/Importar",

    // Labels profil
    "label_prenom" to "Nombre",
    "label_langue" to "Idioma",
    "label_devise" to "Moneda",
    "btn_sauvegarder_profil" to "Guardar perfil",

    // Labels catégories
    "label_cigarettes" to "Cigarrillos",
    "label_joints" to "Porros",
    "label_alcool_global" to "Alcohol total",
    "label_bieres" to "Cervezas",
    "label_liqueurs" to "Licores",
    "label_alcool_fort" to "Bebidas fuertes",

    // Labels formulaires cigarettes
    "radio_classiques" to "Cigarrillos normales",
    "radio_rouler" to "Tabaco de liar",
    "radio_tubeuse" to "Máquina de tubos",
    "label_prix_paquet" to "Precio del paquete",
    "label_nb_cigarettes" to "Número de cigarrillos",
    "label_prix_tabac" to "Precio del tabaco",
    "label_prix_feuilles" to "Precio de los papeles",
    "label_nb_feuilles" to "Número de papeles",
    "label_prix_filtres" to "Precio de los filtros",
    "label_nb_filtres" to "Número de filtros",
    "label_prix_tubes" to "Precio de los tubos",
    "label_nb_tubes" to "Número de tubos",

    // Labels formulaires joints
    "label_prix_gramme" to "Precio por gramo",
    "label_gramme_par_joint" to "Gramos por porro",

    // Labels formulaires alcool
    "label_prix_verre" to "Precio de la bebida",
    "label_unite_cl" to "Unidad (cL)",

    // Labels habitudes
    "label_max_journalier" to "Máximo diario",

    // Labels dates
    "label_date_reduction" to "Fecha de reducción",
    "label_date_arret" to "Fecha de abandono",
    "label_date_reussite" to "Fecha de éxito",
    "btn_choisir_date" to "Elegir fecha",

    "msg_export_limite" to "Para acceder a la exportación, cambia a la versión sin anuncios para disfrutarla :-)",
    "msg_import_limite" to "Límite alcanzado. Quedan %d importación(es) hoy.",

    // Boutons RAZ
    "btn_raz_jour" to "Reiniciar hoy",
    "btn_raz_historique" to "Reiniciar historial",
    "btn_raz_usine" to "Restablecer de fábrica",

    // Boutons Export/Import
    "btn_exporter" to "Exportar",
    "btn_importer" to "Importar",
    "btn_export_logs" to "Exportar registros",

    // Messages confirmations
    "confirm_raz_jour_titre" to "Reiniciar hoy",
    "confirm_raz_jour_message" to "¿Eliminar todos los consumos de hoy?",
    "confirm_raz_historique_titre" to "Reiniciar historial",
    "confirm_raz_historique_message" to "¿Eliminar TODO el historial de consumo? (Esta acción es irreversible)",
    "confirm_raz_usine_titre" to "Restablecer de fábrica",
    "confirm_raz_usine_message" to "¿Reiniciar TODO (perfil, consumos, ajustes)? (Esta acción es irreversible)",
    "confirm_import_titre" to "Importar copia de seguridad",
    "confirm_import_message" to "Todos los datos actuales serán reemplazados. ¿Continuar?",

    // Messages succès/erreur
    "msg_profil_sauvegarde" to "Perfil guardado",
    "msg_date_enregistree" to "Fecha guardada",
    "msg_raz_effectuee" to "Reinicio completado",
    "msg_export_reussi" to "Exportación exitosa",
    "msg_import_reussi" to "Importación exitosa",
    "msg_erreur_sauvegarde" to "Error al guardar",
    "msg_erreur_date" to "Error al guardar la fecha",
    "msg_erreur_raz" to "Error al reiniciar",
    "msg_erreur_export" to "Error de exportación",
    "msg_erreur_import" to "Error de importación",
    "msg_limite_export" to "Límite alcanzado: quedan %d exportación(es) hoy (versión gratuita)",
    "msg_limite_import" to "Límite alcanzado: quedan %d importación(es) hoy (versión gratuita)",
    "msg_aucune_sauvegarde" to "No se encontró ningún archivo de copia",
    "msg_donnees_invalides" to "Error de importación: datos inválidos",
    "msg_champs_obligatoires" to "Introduce al menos un coste",

    // Bandeau profil
    "profil_complet" to "Perfil: Completo ✓",
    "profil_incomplet" to "Perfil: Incompleto",
    "total_aujourdhui" to "Total hoy:",

    // Économies potentielles
    "titre_economies" to "Ahorros potenciales",
    "economies_si_arret" to "Si dejaras por completo:",
    "economies_jour" to "Día",
    "economies_semaine" to "Semana",
    "economies_mois" to "Mes",
    "economies_annee" to "Año",

    // Boutons dialog
    "btn_confirmer" to "Confirmar",
    "btn_annuler" to "Cancelar",
    "btn_ok" to "OK",

    // About
    "titre_a_propos" to "Acerca de",
    "voir_avertissement" to "Ver advertencia",
    "btn_manuel" to "Manual de usuario",
    "btn_cgv" to "Términos y condiciones",
    "btn_mentions_legales" to "Aviso legal",
    "btn_contact" to "Contactar soporte",
    "btn_maj" to "Últimas actualizaciones",
    "maj_titre" to "Últimas actualizaciones",
    "maj_contenu" to "Despliegue V1",
    "btn_premium" to "Versión sin anuncios",
    "premium_titre" to "Versión sin anuncios",
    "premium_contenu" to "La versión sin anuncios estará disponible pronto.\n\nEliminará los banners y las limitaciones.",
    "hint_prenom" to "Introduce tu nombre",

    // Reset
    "raz_sauvegarde" to "Reinicio y Copia",
    "raz_jour" to "Reinicio del día",
    "raz_historique" to "Reinicio del historial",
    "raz_usine" to "Restablecer de fábrica",
    "raz_jour_ok" to "Reinicio del día realizado",
    "raz_historique_ok" to "Reinicio del historial realizado",
    "raz_usine_ok" to "Restablecimiento de fábrica realizado",

    // Units in cl for alcohols
    "unite_cl_global"      to "Unidad (cL)",
    "unite_cl_biere"       to "Unidad (cL)",
    "unite_cl_liqueur"     to "Unidad (cL)",
    "unite_cl_alcool_fort" to "Unidad (cL)",

    "btn_sauvegarder_couts" to "Guardar costes",
    "msg_export_logs_impossible" to "No se pudieron exportar los registros",
    "msg_import_termine" to "Importación completada con éxito",
    "msg_import_erreur" to "Error de importación: %s",

    "label_max_journalier" to "Objetivo máximo diario",
    "label_date_reduction" to "Fecha de reducción",
    "label_date_arret" to "Fecha de abandono",
    "label_date_reussite" to "Fecha de éxito",
    "btn_choisir_date" to "Elegir una fecha",
    "msg_export_limite" to "Para acceder a la exportación, actualiza a la versión sin anuncios para disfrutarla :-)",

    "label_prix_feuilles_longues" to "Precio de papeles largos",
    "label_nb_feuilles_longues"   to "Número de papeles largos"
)

// ==================== PORTUGUÊS ====================
private val TRADUCTIONS_PT = mapOf(
    // Titres sections
    "titre" to "Definições",
    "titre_profil" to "Perfil",
    "titre_categories" to "Categorias ativas",
    "titre_couts_cigarettes" to "Custos de cigarros",
    "titre_couts_joints" to "Custos de charros",
    "titre_couts_alcool" to "Custos de álcool",
    "titre_habitudes" to "Hábitos diários",
    "titre_dates_objectifs" to "Datas objetivo",
    "titre_raz_export" to "Repor e Exportar/Importar",

    // Labels profil
    "label_prenom" to "Nome",
    "label_langue" to "Idioma",
    "label_devise" to "Moeda",
    "btn_sauvegarder_profil" to "Guardar perfil",

    // Labels catégories
    "label_cigarettes" to "Cigarros",
    "label_joints" to "Charros",
    "label_alcool_global" to "Álcool total",
    "label_bieres" to "Cervejas",
    "label_liqueurs" to "Licores",
    "label_alcool_fort" to "Bebidas espirituosas",

    // Labels formulaires cigarettes
    "radio_classiques" to "Cigarros normais",
    "radio_rouler" to "Tabaco de enrolar",
    "radio_tubeuse" to "Máquina de tubos",
    "label_prix_paquet" to "Preço do maço",
    "label_nb_cigarettes" to "Número de cigarros",
    "label_prix_tabac" to "Preço do tabaco",
    "label_prix_feuilles" to "Preço das mortalhas",
    "label_nb_feuilles" to "Número de mortalhas",
    "label_prix_filtres" to "Preço dos filtros",
    "label_nb_filtres" to "Número de filtros",
    "label_prix_tubes" to "Preço dos tubos",
    "label_nb_tubes" to "Número de tubos",

    // Labels formulaires joints
    "label_prix_gramme" to "Preço por grama",
    "label_gramme_par_joint" to "Gramas por charro",

    // Labels formulaires alcool
    "label_prix_verre" to "Preço da bebida",
    "label_unite_cl" to "Unidade (cL)",

    // Labels habitudes
    "label_max_journalier" to "Máximo diário",

    // Labels dates
    "label_date_reduction" to "Data de redução",
    "label_date_arret" to "Data de parar",
    "label_date_reussite" to "Data de sucesso",
    "btn_choisir_date" to "Escolher data",

    "msg_export_limite" to "Para aceder à exportação, mude para a versão sem anúncios para usufruir :-)",
    "msg_import_limite" to "Limite atingido. Restam %d importação(ões) hoje.",

    // Boutons RAZ
    "btn_raz_jour" to "Repor hoje",
    "btn_raz_historique" to "Repor histórico",
    "btn_raz_usine" to "Reposição de fábrica",

    // Boutons Export/Import
    "btn_exporter" to "Exportar",
    "btn_importer" to "Importar",
    "btn_export_logs" to "Exportar registos",

    // Messages confirmations
    "confirm_raz_jour_titre" to "Repor hoje",
    "confirm_raz_jour_message" to "Eliminar todos os consumos de hoje?",
    "confirm_raz_historique_titre" to "Repor histórico",
    "confirm_raz_historique_message" to "Eliminar TODO o histórico de consumo? (Esta ação é irreversível)",
    "confirm_raz_usine_titre" to "Reposição de fábrica",
    "confirm_raz_usine_message" to "Repor TUDO (perfil, consumos, definições)? (Esta ação é irreversível)",
    "confirm_import_titre" to "Importar cópia de segurança",
    "confirm_import_message" to "Todos os dados atuais serão substituídos. Continuar?",

    // Messages succès/erreur
    "msg_profil_sauvegarde" to "Perfil guardado",
    "msg_date_enregistree" to "Data guardada",
    "msg_raz_effectuee" to "Reposição concluída",
    "msg_export_reussi" to "Exportação bem-sucedida",
    "msg_import_reussi" to "Importação bem-sucedida",
    "msg_erreur_sauvegarde" to "Erro ao guardar",
    "msg_erreur_date" to "Erro ao guardar a data",
    "msg_erreur_raz" to "Erro ao repor",
    "msg_erreur_export" to "Erro de exportação",
    "msg_erreur_import" to "Erro de importação",
    "msg_limite_export" to "Limite atingido: restam %d exportação(ões) hoje (versão gratuita)",
    "msg_limite_import" to "Limite atingido: restam %d importação(ões) hoje (versão gratuita)",
    "msg_aucune_sauvegarde" to "Nenhum ficheiro de cópia encontrado",
    "msg_donnees_invalides" to "Erro de importação: dados inválidos",
    "msg_champs_obligatoires" to "Introduza pelo menos um custo",

    // Bandeau profil
    "profil_complet" to "Perfil: Completo ✓",
    "profil_incomplet" to "Perfil: Incompleto",
    "total_aujourdhui" to "Total hoje:",

    // Économies potentielles
    "titre_economies" to "Poupanças potenciais",
    "economies_si_arret" to "Se parasse completamente:",
    "economies_jour" to "Dia",
    "economies_semaine" to "Semana",
    "economies_mois" to "Mês",
    "economies_annee" to "Ano",

    // Boutons dialog
    "btn_confirmer" to "Confirmar",
    "btn_annuler" to "Cancelar",
    "btn_ok" to "OK",

    // About
    "titre_a_propos" to "Sobre",
    "voir_avertissement" to "Ver aviso",
    "btn_manuel" to "Manual do utilizador",
    "btn_cgv" to "Termos e condições",
    "btn_mentions_legales" to "Aviso legal",
    "btn_contact" to "Contactar suporte",
    "btn_maj" to "Últimas atualizações",
    "maj_titre" to "Últimas atualizações",
    "maj_contenu" to "Implementação V1",
    "btn_premium" to "Versão sem anúncios",
    "premium_titre" to "Versão sem anúncios",
    "premium_contenu" to "A versão sem anúncios estará disponível em breve.\n\nIrá remover banners e limitações.",
    "hint_prenom" to "Introduza o seu nome",

    // Reset
    "raz_sauvegarde" to "Reposição e Cópia",
    "raz_jour" to "Reposição do dia",
    "raz_historique" to "Reposição do histórico",
    "raz_usine" to "Reposição de fábrica",
    "raz_jour_ok" to "Reposição do dia concluída",
    "raz_historique_ok" to "Reposição do histórico concluída",
    "raz_usine_ok" to "Reposição de fábrica concluída",

    // Units in cl for alcohols
    "unite_cl_global"      to "Unidade (cL)",
    "unite_cl_biere"       to "Unidade (cL)",
    "unite_cl_liqueur"     to "Unidade (cL)",
    "unite_cl_alcool_fort" to "Unidade (cL)",

    "btn_sauvegarder_couts" to "Guardar custos",
    "msg_export_logs_impossible" to "Não foi possível exportar os registos",
    "msg_import_termine" to "Importação concluída com sucesso",
    "msg_import_erreur" to "Erro de importação: %s",

    "label_max_journalier" to "Objetivo máximo diário",
    "label_date_reduction" to "Data de redução",
    "label_date_arret" to "Data de parar",
    "label_date_reussite" to "Data de sucesso",
    "btn_choisir_date" to "Escolher uma data",
    "msg_export_limite" to "Para aceder à exportação, atualize para a versão sem anúncios para usufruir :-)",

    "label_prix_feuilles_longues" to "Preço de mortalhas longas",
    "label_nb_feuilles_longues"   to "Número de mortalhas longas"
)


    // ==================== DEUTSCH ====================
private val TRADUCTIONS_DE = mapOf(
    // Titres sections
    "titre" to "Einstellungen",
    "titre_profil" to "Profil",
    "titre_categories" to "Aktive Kategorien",
    "titre_couts_cigarettes" to "Zigarettenkosten",
    "titre_couts_joints" to "Joint-Kosten",
    "titre_couts_alcool" to "Alkoholkosten",
    "titre_habitudes" to "Tägliche Gewohnheiten",
    "titre_dates_objectifs" to "Zieldaten",
    "titre_raz_export" to "Zurücksetzen & Export/Import",

    // Labels profil
    "label_prenom" to "Vorname",
    "label_langue" to "Sprache",
    "label_devise" to "Währung",
    "btn_sauvegarder_profil" to "Profil speichern",
    "hint_prenom" to "Geben Sie Ihren Vornamen ein",

    // Labels catégories
    "label_cigarettes" to "Zigaretten",
    "label_joints" to "Joints",
    "label_alcool_global" to "Alkohol gesamt",
    "label_bieres" to "Biere",
    "label_liqueurs" to "Liköre",
    "label_alcool_fort" to "Spirituosen",

    // Labels formulaires cigarettes
    "radio_classiques" to "Normale Zigaretten",
    "radio_rouler" to "Selbstgedreht",
    "radio_tubeuse" to "Stopfmaschine",
    "label_prix_paquet" to "Packungspreis",
    "label_nb_cigarettes" to "Anzahl Zigaretten",
    "label_prix_tabac" to "Tabakpreis",
    "label_prix_feuilles" to "Blättchenpreis",
    "label_nb_feuilles" to "Anzahl Blättchen",
    "label_prix_filtres" to "Filterpreis",
    "label_nb_filtres" to "Anzahl Filter",
    "label_prix_tubes" to "Hülsenpreis",
    "label_nb_tubes" to "Anzahl Hülsen",

    // Labels formulaires joints
    "label_prix_gramme" to "Preis pro Gramm",
    "label_gramme_par_joint" to "Gramm pro Joint",
    "label_prix_feuilles_longues" to "Preis der langen Blättchen",
    "label_nb_feuilles_longues" to "Anzahl der langen Blättchen",

    // Labels formulaires alcool
    "label_prix_verre" to "Getränkpreis",
    "label_unite_cl" to "Einheit (cL)",

    // Labels habitudes
    "label_max_journalier" to "Tägliches Maximum",

    // Labels dates
    "label_date_reduction" to "Reduzierungsdatum",
    "label_date_arret" to "Aufhördatum",
    "label_date_reussite" to "Erfolgsdatum",
    "btn_choisir_date" to "Datum wählen",

    // Limites Export/Import
    "msg_export_limite" to "Um den Export zu nutzen, wechseln Sie zur werbefreien Version :-)",
    "msg_import_limite" to "Limit erreicht. %d Import(e) verbleiben heute.",

    // Boutons RAZ
    "btn_raz_jour" to "Heute zurücksetzen",
    "btn_raz_historique" to "Verlauf zurücksetzen",
    "btn_raz_usine" to "Werkseinstellungen",

    // Boutons Export/Import
    "btn_exporter" to "Exportieren",
    "btn_importer" to "Importieren",
    "btn_export_logs" to "Logs exportieren",

    // Messages confirmations
    "confirm_raz_jour_titre" to "Heute zurücksetzen",
    "confirm_raz_jour_message" to "Alle heutigen Konsumeinträge löschen?",
    "confirm_raz_historique_titre" to "Verlauf zurücksetzen",
    "confirm_raz_historique_message" to "ALLE Konsumhistorie löschen? (Diese Aktion ist irreversibel)",
    "confirm_raz_usine_titre" to "Werkseinstellungen",
    "confirm_raz_usine_message" to "ALLES zurücksetzen (Profil, Konsum, Einstellungen)? (Diese Aktion ist irreversibel)",
    "confirm_import_titre" to "Backup importieren",
    "confirm_import_message" to "Alle aktuellen Daten werden ersetzt. Fortfahren?",
    "confirm_default" to "Bestätigen?",

    // Messages succès/erreur
    "msg_profil_sauvegarde" to "Profil gespeichert",
    "msg_date_enregistree" to "Datum gespeichert",
    "msg_raz_effectuee" to "Zurücksetzen abgeschlossen",
    "msg_export_reussi" to "Export erfolgreich",
    "msg_import_reussi" to "Import erfolgreich",
    "msg_erreur_sauvegarde" to "Speicherfehler",
    "msg_erreur_date" to "Datumsspeicherfehler",
    "msg_erreur_raz" to "Zurücksetzungsfehler",
    "msg_erreur_export" to "Exportfehler",
    "msg_erreur_import" to "Importfehler",
    "msg_limite_export" to "Limit erreicht: %d Export(e) heute übrig (kostenlose Version)",
    "msg_limite_import" to "Limit erreicht: %d Import(e) heute übrig (kostenlose Version)",
    "msg_aucune_sauvegarde" to "Keine Backup-Datei gefunden",
    "msg_donnees_invalides" to "Importfehler: ungültige Daten",
    "msg_champs_obligatoires" to "Bitte geben Sie mindestens einen Preis ein",
    "msg_export_logs_impossible" to "Logs können nicht exportiert werden",
    "msg_import_termine" to "Import erfolgreich abgeschlossen",
    "msg_import_termine_succes" to "Import erfolgreich abgeschlossen",
    "msg_import_erreur" to "Importfehler: %s",
    "msg_erreur_prefix" to "Fehler: %s",
    "sauvegarde_ok" to "Gespeichert",
    "msg_open_store_impossible" to "Store kann nicht geöffnet werden",

    // Bandeau profil
    "profil_complet" to "Profil: Vollständig ✓",
    "profil_incomplet" to "Profil: Unvollständig",
    "total_aujourdhui" to "Heute insgesamt:",

    // Économies potentielles
    "titre_economies" to "Mögliche Ersparnisse",
    "economies_si_arret" to "Wenn Sie komplett aufhören würden:",
    "economies_jour" to "Tag",
    "economies_semaine" to "Woche",
    "economies_mois" to "Monat",
    "economies_annee" to "Jahr",

    // Boutons dialog
    "btn_confirmer" to "Bestätigen",
    "btn_annuler" to "Abbrechen",
    "btn_ok" to "OK",

    // À propos
    "titre_a_propos" to "Über",
    "voir_avertissement" to "Warnung anzeigen",
    "btn_manuel" to "Benutzerhandbuch",
    "btn_cgvژه" to "Allgemeine Geschäftsbedingungen",
    "btn_cgv" to "Allgemeine Geschäftsbedingungen",
    "btn_mentions_legales" to "Impressum",
    "btn_contact" to "Support kontaktieren",
    "btn_maj" to "Letzte Aktualisierungen",
    "maj_titre" to "Letzte Aktualisierungen",
    "maj_contenu" to "Bereitstellung V1",
    "btn_premium" to "Werbefreie Version",
    "premium_titre" to "Werbefreie Version",
    "premium_contenu" to "Die werbefreie Version ist bald verfügbar.\n\nSie entfernt Bannerwerbung und Einschränkungen.",

    // Reset (libellés)
    "raz_sauvegarde" to "Zurücksetzen und Sicherung",
    "raz_jour" to "Tag zurücksetzen",
    "raz_historique" to "Verlauf zurücksetzen",
    "raz_usine" to "Werkseinstellungen",
    "raz_jour_ok" to "Tag zurückgesetzt",
    "raz_historique_ok" to "Verlauf zurückgesetzt",
    "raz_usine_ok" to "Auf Werkseinstellungen zurückgesetzt",

    // Units in cl for alcohols
    "unite_cl_global" to "Einheit (cl)",
    "unite_cl_biere" to "Einheit (cl)",
    "unite_cl_liqueur" to "Einheit (cl)",
    "unite_cl_alcool_fort" to "Einheit (cl)",

    // Boutons divers
    "btn_sauvegarder_couts" to "Kosten speichern",

    // Support email
    "support_email_subject" to "StopAddict - Support",
    "support_email_error" to "E-Mail-App kann nicht geöffnet werden: %s"
)


// ==================== ITALIANO ====================
private val TRADUCTIONS_IT = mapOf(
    // Titres sections
    "titre" to "Impostazioni",
    "titre_profil" to "Profilo",
    "titre_categories" to "Categorie attive",
    "titre_couts_cigarettes" to "Costi sigarette",
    "titre_couts_joints" to "Costi spinelli",
    "titre_couts_alcool" to "Costi alcol",
    "titre_habitudes" to "Abitudini quotidiane",
    "titre_dates_objectifs" to "Date obiettivi",
    "titre_raz_export" to "Ripristino ed Esporta/Importa",

    // Labels profil
    "label_prenom" to "Nome",
    "label_langue" to "Lingua",
    "label_devise" to "Valuta",
    "btn_sauvegarder_profil" to "Salva profilo",
    "hint_prenom" to "Inserisci il tuo nome",

    // Labels catégories
    "label_cigarettes" to "Sigarette",
    "label_joints" to "Spinelli",
    "label_alcool_global" to "Alcol totale",
    "label_bieres" to "Birre",
    "label_liqueurs" to "Liquori",
    "label_alcool_fort" to "Superalcolici",

    // Labels formulaires cigarettes
    "radio_classiques" to "Sigarette classiche",
    "radio_rouler" to "Da rollare",
    "radio_tubeuse" to "Macchina per tubi",
    "label_prix_paquet" to "Prezzo pacchetto",
    "label_nb_cigarettes" to "Numero di sigarette",
    "label_prix_tabac" to "Prezzo tabacco",
    "label_prix_feuilles" to "Prezzo cartine",
    "label_nb_feuilles" to "Numero di cartine",
    "label_prix_filtres" to "Prezzo filtri",
    "label_nb_filtres" to "Numero di filtri",
    "label_prix_tubes" to "Prezzo tubi",
    "label_nb_tubes" to "Numero di tubi",

    // Labels formulaires joints
    "label_prix_gramme" to "Prezzo per grammo",
    "label_gramme_par_joint" to "Grammi per spinello",
    "label_prix_feuilles_longues" to "Prezzo delle cartine lunghe",
    "label_nb_feuilles_longues" to "Numero di cartine lunghe",

    // Labels formulaires alcool
    "label_prix_verre" to "Prezzo bicchiere",
    "label_unite_cl" to "Unità (cL)",

    // Labels habitudes
    "label_max_journalier" to "Massimo giornaliero",

    // Labels dates
    "label_date_reduction" to "Data riduzione",
    "label_date_arret" to "Data di stop",
    "label_date_reussite" to "Data successo",
    "btn_choisir_date" to "Scegli data",

    // Limites Export/Import
    "msg_export_limite" to "Per accedere all'esportazione, passa alla versione senza pubblicità per usufruirne :-)",
    "msg_import_limite" to "Limite raggiunto. Rimangono %d importazioni oggi.",

    // Boutons RAZ
    "btn_raz_jour" to "Ripristina oggi",
    "btn_raz_historique" to "Ripristina storico",
    "btn_raz_usine" to "Ripristino di fabbrica",

    // Boutons Export/Import
    "btn_exporter" to "Esporta",
    "btn_importer" to "Importa",
    "btn_export_logs" to "Esporta log",

    // Messages confirmations
    "confirm_raz_jour_titre" to "Ripristina oggi",
    "confirm_raz_jour_message" to "Eliminare tutto il consumo di oggi?",
    "confirm_raz_historique_titre" to "Ripristina storico",
    "confirm_raz_historique_message" to "Eliminare TUTTO lo storico consumo? (Questa azione è irreversibile)",
    "confirm_raz_usine_titre" to "Ripristino di fabbrica",
    "confirm_raz_usine_message" to "Ripristinare TUTTO (profilo, consumo, impostazioni)? (Questa azione è irreversibile)",
    "confirm_import_titre" to "Importa backup",
    "confirm_import_message" to "Tutti i dati attuali saranno sostituiti. Continuare?",
    "confirm_default" to "Confermare?",

    // Messages succès/erreur
    "msg_profil_sauvegarde" to "Profilo salvato",
    "msg_date_enregistree" to "Data salvata",
    "msg_raz_effectuee" to "Ripristino completato",
    "msg_export_reussi" to "Esportazione riuscita",
    "msg_import_reussi" to "Importazione riuscita",
    "msg_erreur_sauvegarde" to "Errore salvataggio",
    "msg_erreur_date" to "Errore salvataggio data",
    "msg_erreur_raz" to "Errore ripristino",
    "msg_erreur_export" to "Errore esportazione",
    "msg_erreur_import" to "Errore importazione",
    "msg_limite_export" to "Limite raggiunto: %d esportazione/i rimanente/i oggi (versione gratuita)",
    "msg_limite_import" to "Limite raggiunto: %d importazione/i rimanente/i oggi (versione gratuita)",
    "msg_aucune_sauvegarde" to "Nessun file di backup trovato",
    "msg_donnees_invalides" to "Errore importazione: dati non validi",
    "msg_champs_obligatoires" to "Per favore inserisci almeno un costo",
    "msg_export_logs_impossible" to "Impossibile esportare i log",
    "msg_import_termine" to "Import completato con successo",
    "msg_import_termine_succes" to "Import completato con successo",
    "msg_import_erreur" to "Errore importazione: %s",
    "msg_erreur_prefix" to "Errore: %s",
    "sauvegarde_ok" to "Salvato",
    "msg_open_store_impossible" to "Impossibile aprire lo store",

    // Bandeau profil
    "profil_complet" to "Profilo: Completo ✓",
    "profil_incomplet" to "Profilo: Incompleto",
    "total_aujourdhui" to "Totale oggi:",

    // Économies potentielles
    "titre_economies" to "Risparmi potenziali",
    "economies_si_arret" to "Se smettessi completamente:",
    "economies_jour" to "Giorno",
    "economies_semaine" to "Settimana",
    "economies_mois" to "Mese",
    "economies_annee" to "Anno",

    // Boutons dialog
    "btn_confirmer" to "Conferma",
    "btn_annuler" to "Annulla",
    "btn_ok" to "OK",

    // About
    "titre_a_propos" to "Informazioni",
    "voir_avertissement" to "Visualizza avviso",
    "btn_manuel" to "Manuale utente",
    "btn_cgv" to "Termini e condizioni",
    "btn_mentions_legales" to "Note legali",
    "btn_contact" to "Contatta supporto",
    "btn_maj" to "Ultimi aggiornamenti",
    "maj_titre" to "Ultimi aggiornamenti",
    "maj_contenu" to "Rilascio V1",
    "btn_premium" to "Versione senza pubblicità",
    "premium_titre" to "Versione senza pubblicità",
    "premium_contenu" to "La versione senza pubblicità sarà presto disponibile.\n\nRimuoverà i banner pubblicitari e i limiti.",

    // Reset (libellés)
    "raz_sauvegarde" to "Ripristino e Backup",
    "raz_jour" to "Ripristino giorno",
    "raz_historique" to "Ripristino cronologia",
    "raz_usine" to "Ripristino di fabbrica",
    "raz_jour_ok" to "Giorno ripristinato",
    "raz_historique_ok" to "Cronologia ripristinata",
    "raz_usine_ok" to "Ripristino di fabbrica completato",

    // Units in cl for alcohols
    "unite_cl_global" to "Unità (cl)",
    "unite_cl_biere" to "Unità (cl)",
    "unite_cl_liqueur" to "Unità (cl)",
    "unite_cl_alcool_fort" to "Unità (cl)",

    // Boutons divers
    "btn_sauvegarder_couts" to "Salva costi",

    // Support email
    "support_email_subject" to "StopAddict - Supporto",
    "support_email_error" to "Impossibile aprire l'app e-mail: %s"
)


// ==================== РУССКИЙ ====================
private val TRADUCTIONS_RU = mapOf(
    // Titres sections
    "titre" to "Настройки",
    "titre_profil" to "Профиль",
    "titre_categories" to "Активные категории",
    "titre_couts_cigarettes" to "Расходы на сигареты",
    "titre_couts_joints" to "Расходы на косяки",
    "titre_couts_alcool" to "Расходы на алкоголь",
    "titre_habitudes" to "Ежедневные привычки",
    "titre_dates_objectifs" to "Даты целей",
    "titre_raz_export" to "Сброс и Экспорт/Импорт",

    // Labels profil
    "label_prenom" to "Имя",
    "label_langue" to "Язык",
    "label_devise" to "Валюта",
    "btn_sauvegarder_profil" to "Сохранить профиль",
    "hint_prenom" to "Введите ваше имя",

    // Labels catégories
    "label_cigarettes" to "Сигареты",
    "label_joints" to "Косяки",
    "label_alcool_global" to "Алкоголь общий",
    "label_bieres" to "Пиво",
    "label_liqueurs" to "Ликёры",
    "label_alcool_fort" to "Крепкий алкоголь",

    // Labels formulaires cigarettes
    "radio_classiques" to "Обычные сигареты",
    "radio_rouler" to "Самокрутки",
    "radio_tubeuse" to "Гильзовочная машина",
    "label_prix_paquet" to "Цена пачки",
    "label_nb_cigarettes" to "Количество сигарет",
    "label_prix_tabac" to "Цена табака",
    "label_prix_feuilles" to "Цена бумаги",
    "label_nb_feuilles" to "Количество бумаги",
    "label_prix_filtres" to "Цена фильтров",
    "label_nb_filtres" to "Количество фильтров",
    "label_prix_tubes" to "Цена гильз",
    "label_nb_tubes" to "Количество гильз",

    // Labels formulaires joints
    "label_prix_gramme" to "Цена за грамм",
    "label_gramme_par_joint" to "Граммов на косяк",
    "label_prix_feuilles_longues" to "Цена длинной бумаги",
    "label_nb_feuilles_longues" to "Количество длинной бумаги",

    // Labels formulaires alcool
    "label_prix_verre" to "Цена напитка",
    "label_unite_cl" to "Единица (cL)",

    // Labels habitudes
    "label_max_journalier" to "Ежедневный максимум",

    // Labels dates
    "label_date_reduction" to "Дата сокращения",
    "label_date_arret" to "Дата отказа",
    "label_date_reussite" to "Дата успеха",
    "btn_choisir_date" to "Выбрать дату",

    // Limites Export/Import
    "msg_export_limite" to "Чтобы пользоваться экспортом, перейдите на версию без рекламы :-)",
    "msg_import_limite" to "Достигнут лимит. Осталось %d импорт(ов) на сегодня.",

    // Boutons RAZ
    "btn_raz_jour" to "Сбросить сегодня",
    "btn_raz_historique" to "Сбросить историю",
    "btn_raz_usine" to "Заводской сброс",

    // Boutons Export/Import
    "btn_exporter" to "Экспорт",
    "btn_importer" to "Импорт",
    "btn_export_logs" to "Экспорт логов",

    // Messages confirmations
    "confirm_raz_jour_titre" to "Сбросить сегодня",
    "confirm_raz_jour_message" to "Удалить всё сегодняшнее потребление?",
    "confirm_raz_historique_titre" to "Сбросить историю",
    "confirm_raz_historique_message" to "Удалить ВСЮ историю потребления? (Это действие необратимо)",
    "confirm_raz_usine_titre" to "Заводской сброс",
    "confirm_raz_usine_message" to "Сбросить ВСЁ (профиль, потребление, настройки)? (Это действие необратимо)",
    "confirm_import_titre" to "Импорт резервной копии",
    "confirm_import_message" to "Все текущие данные будут заменены. Продолжить?",
    "confirm_default" to "Подтвердить?",

    // Messages succès/erreur
    "msg_profil_sauvegarde" to "Профиль сохранён",
    "msg_date_enregistree" to "Дата сохранена",
    "msg_raz_effectuee" to "Сброс завершён",
    "msg_export_reussi" to "Экспорт выполнен",
    "msg_import_reussi" to "Импорт выполнен",
    "msg_erreur_sauvegarde" to "Ошибка сохранения",
    "msg_erreur_date" to "Ошибка сохранения даты",
    "msg_erreur_raz" to "Ошибка сброса",
    "msg_erreur_export" to "Ошибка экспорта",
    "msg_erreur_import" to "Ошибка импорта",
    "msg_limite_export" to "Достигнут лимит: осталось %d экспорт(ов) сегодня (бесплатная версия)",
    "msg_limite_import" to "Достигнут лимит: осталось %d импорт(ов) сегодня (бесплатная версия)",
    "msg_aucune_sauvegarde" to "Файл резервной копии не найден",
    "msg_donnees_invalides" to "Ошибка импорта: недопустимые данные",
    "msg_champs_obligatoires" to "Пожалуйста, введите хотя бы одну стоимость",
    "msg_export_logs_impossible" to "Не удалось экспортировать логи",
    "msg_import_termine" to "Импорт успешно завершён",
    "msg_import_termine_succes" to "Импорт успешно завершён",
    "msg_import_erreur" to "Ошибка импорта: %s",
    "msg_erreur_prefix" to "Ошибка: %s",
    "sauvegarde_ok" to "Сохранено",
    "msg_open_store_impossible" to "Не удалось открыть магазин",

    // Bandeau profil
    "profil_complet" to "Профиль: Полный ✓",
    "profil_incomplet" to "Профиль: Неполный",
    "total_aujourdhui" to "Всего сегодня:",

    // Économies potentielles
    "titre_economies" to "Потенциальная экономия",
    "economies_si_arret" to "Если вы полностью бросите:",
    "economies_jour" to "День",
    "economies_semaine" to "Неделя",
    "economies_mois" to "Месяц",
    "economies_annee" to "Год",

    // Boutons dialog
    "btn_confirmer" to "Подтвердить",
    "btn_annuler" to "Отмена",
    "btn_ok" to "OK",

    // About
    "titre_a_propos" to "О приложении",
    "voir_avertissement" to "Просмотр предупреждения",
    "btn_manuel" to "Руководство пользователя",
    "btn_cgv" to "Условия использования",
    "btn_mentions_legales" to "Правовая информация",
    "btn_contact" to "Связаться с поддержкой",
    "btn_maj" to "Последние обновления",
    "maj_titre" to "Последние обновления",
    "maj_contenu" to "Релиз V1",
    "btn_premium" to "Версия без рекламы",
    "premium_titre" to "Версия без рекламы",
    "premium_contenu" to "Версия без рекламы скоро будет доступна.\n\nОна уберёт баннерную рекламу и ограничения.",

    // Reset (libellés)
    "raz_sauvegarde" to "Сброс и резервное копирование",
    "raz_jour" to "Сброс дня",
    "raz_historique" to "Сброс истории",
    "raz_usine" to "Заводской сброс",
    "raz_jour_ok" to "День сброшен",
    "raz_historique_ok" to "История сброшена",
    "raz_usine_ok" to "Выполнен заводской сброс",

    // Units in cl for alcohols
    "unite_cl_global" to "Единица (cl)",
    "unite_cl_biere" to "Единица (cl)",
    "unite_cl_liqueur" to "Единица (cl)",
    "unite_cl_alcool_fort" to "Единица (cl)",

    // Boutons divers
    "btn_sauvegarder_couts" to "Сохранить цены",

    // Support email
    "support_email_subject" to "StopAddict - Поддержка",
    "support_email_error" to "Не удалось открыть почтовое приложение: %s"
)

    // ==================== العربية (ARABE) ====================
private val TRADUCTIONS_AR = mapOf(
    // Titres sections
    "titre" to "الإعدادات",
    "titre_profil" to "الملف الشخصي",
    "titre_categories" to "الفئات النشطة",
    "titre_couts_cigarettes" to "تكاليف السجائر",
    "titre_couts_joints" to "تكاليف الجوانت",
    "titre_couts_alcool" to "تكاليف الكحول",
    "titre_habitudes" to "العادات اليومية",
    "titre_dates_objectifs" to "تواريخ الأهداف",
    "titre_raz_export" to "إعادة التعيين والتصدير/الاستيراد",

    // Labels profil
    "label_prenom" to "الاسم الأول",
    "label_langue" to "اللغة",
    "label_devise" to "العملة",
    "btn_sauvegarder_profil" to "حفظ الملف الشخصي",
    "hint_prenom" to "أدخل اسمك الأول",

    // Labels catégories
    "label_cigarettes" to "السجائر",
    "label_joints" to "جوانت",
    "label_alcool_global" to "الكحول (إجمالي)",
    "label_bieres" to "البيرة",
    "label_liqueurs" to "المشروبات الكحولية (ليكيور)",
    "label_alcool_fort" to "المشروبات الروحية",

    // Labels formulaires cigarettes
    "radio_classiques" to "سجائر عادية",
    "radio_rouler" to "لفّ يدوي",
    "radio_tubeuse" to "آلة تعبئة الأنابيب",
    "label_prix_paquet" to "سعر العلبة",
    "label_nb_cigarettes" to "عدد السجائر",
    "label_prix_tabac" to "سعر التبغ",
    "label_prix_feuilles" to "سعر أوراق اللف",
    "label_nb_feuilles" to "عدد أوراق اللف",
    "label_prix_filtres" to "سعر الفلاتر",
    "label_nb_filtres" to "عدد الفلاتر",
    "label_prix_tubes" to "سعر الأنابيب",
    "label_nb_tubes" to "عدد الأنابيب",

    // Joints
    "label_prix_gramme" to "السعر لكل غرام",
    "label_gramme_par_joint" to "غرام لكل جوانت",
    "label_prix_feuilles_longues" to "سعر أوراق اللف الطويلة",
    "label_nb_feuilles_longues" to "عدد أوراق اللف الطويلة",

    // Alcool
    "label_prix_verre" to "سعر المشروب",
    "label_unite_cl" to "الوحدة (cL)",

    // Habitudes
    "label_max_journalier" to "الحد الأقصى اليومي",

    // Dates
    "label_date_reduction" to "تاريخ التقليل",
    "label_date_arret" to "تاريخ التوقف",
    "label_date_reussite" to "تاريخ النجاح",
    "btn_choisir_date" to "اختر تاريخًا",

    // Limitations free / premium
    "msg_export_limite" to "لاستخدام التصدير، انتقل إلى النسخة بدون إعلانات للاستفادة منه :-)",
    "msg_import_limite" to "تم بلوغ الحد. تبقّى %d عملية استيراد اليوم.",
    "msg_limite_export" to "تم بلوغ الحد: تبقّى %d تصدير اليوم (النسخة المجانية)",
    "msg_limite_import" to "تم بلوغ الحد: تبقّى %d استيراد اليوم (النسخة المجانية)",

    // Boutons RAZ
    "btn_raz_jour" to "إعادة تعيين اليوم",
    "btn_raz_historique" to "إعادة تعيين السجل",
    "btn_raz_usine" to "إعادة ضبط المصنع",

    // Export/Import
    "btn_exporter" to "تصدير",
    "btn_importer" to "استيراد",
    "btn_export_logs" to "تصدير السجلات",
    "msg_export_logs_impossible" to "تعذر تصدير السجلات",
    "msg_import_termine" to "اكتمل الاستيراد بنجاح",
    "msg_import_erreur" to "خطأ أثناء الاستيراد: %s",

    // Confirmations
    "confirm_raz_jour_titre" to "إعادة تعيين اليوم",
    "confirm_raz_jour_message" to "حذف كل استهلاك اليوم؟",
    "confirm_raz_historique_titre" to "إعادة تعيين السجل",
    "confirm_raz_historique_message" to "حذف كل سجل الاستهلاك؟ (هذا الإجراء لا رجعة فيه)",
    "confirm_raz_usine_titre" to "إعادة ضبط المصنع",
    "confirm_raz_usine_message" to "إعادة تعيين كل شيء (الملف، الاستهلاك، الإعدادات)؟ (هذا الإجراء لا رجعة فيه)",
    "confirm_import_titre" to "استيراد نسخة احتياطية",
    "confirm_import_message" to "سيتم استبدال جميع البيانات الحالية. هل تريد المتابعة؟",
    "confirm_default" to "تأكيد؟",

    // Messages succès/erreur
    "msg_profil_sauvegarde" to "تم حفظ الملف الشخصي",
    "msg_date_enregistree" to "تم حفظ التاريخ",
    "msg_raz_effectuee" to "تمت إعادة التعيين",
    "msg_export_reussi" to "نجح التصدير",
    "msg_import_reussi" to "نجح الاستيراد",
    "msg_erreur_sauvegarde" to "خطأ في الحفظ",
    "msg_erreur_date" to "خطأ في حفظ التاريخ",
    "msg_erreur_raz" to "خطأ في إعادة التعيين",
    "msg_erreur_export" to "خطأ في التصدير",
    "msg_erreur_import" to "خطأ في الاستيراد",
    "msg_aucune_sauvegarde" to "لم يتم العثور على ملف نسخة احتياطية",
    "msg_donnees_invalides" to "خطأ في الاستيراد: بيانات غير صالحة",
    "msg_champs_obligatoires" to "الرجاء إدخال تكلفة واحدة على الأقل",
    "msg_erreur_prefix" to "خطأ: %s",
    "sauvegarde_ok" to "تم الحفظ",

    // Bandeau profil
    "profil_complet" to "الملف الشخصي: مكتمل ✓",
    "profil_incomplet" to "الملف الشخصي: غير مكتمل",
    "total_aujourdhui" to "الإجمالي اليوم:",

    // Économies potentielles
    "titre_economies" to "الوفورات المحتملة",
    "economies_si_arret" to "إذا توقفت تمامًا:",
    "economies_jour" to "يوم",
    "economies_semaine" to "أسبوع",
    "economies_mois" to "شهر",
    "economies_annee" to "سنة",

    // Boutons dialog
    "btn_confirmer" to "تأكيد",
    "btn_annuler" to "إلغاء",
    "btn_ok" to "موافق",

    // À propos
    "titre_a_propos" to "حول",
    "voir_avertissement" to "عرض التحذير",
    "btn_manuel" to "دليل المستخدم",
    "btn_cgv" to "الشروط والأحكام",
    "btn_mentions_legales" to "إشعار قانوني",
    "btn_contact" to "الاتصال بالدعم",
    "btn_maj" to "آخر التحديثات",
    "maj_titre" to "آخر التحديثات",
    "maj_contenu" to "إطلاق V1",
    "btn_premium" to "نسخة بدون إعلانات",
    "premium_titre" to "نسخة بدون إعلانات",
    "premium_contenu" to "ستتوفر النسخة بدون إعلانات قريبًا.\n\nستزيل الإعلانات والقيود.",

    // Support / store
    "support_email_subject" to "StopAddict - الدعم",
    "support_email_error" to "تعذر فتح تطبيق البريد الإلكتروني: %s",
    "msg_open_store_impossible" to "تعذر فتح المتجر",

    // Reset labels (section)
    "raz_sauvegarde" to "إعادة التعيين والنسخ الاحتياطي",
    "raz_jour" to "إعادة تعيين اليوم",
    "raz_historique" to "إعادة تعيين السجل",
    "raz_usine" to "إعادة ضبط المصنع",
    "raz_jour_ok" to "تمت إعادة تعيين اليوم",
    "raz_historique_ok" to "تمت إعادة تعيين السجل",
    "raz_usine_ok" to "تمت إعادة الضبط للمصنع",

    // Units cL
    "unite_cl_global" to "الوحدة (cL)",
    "unite_cl_biere" to "الوحدة (cL)",
    "unite_cl_liqueur" to "الوحدة (cL)",
    "unite_cl_alcool_fort" to "الوحدة (cL)",

    // Bouton save costs
    "btn_sauvegarder_couts" to "حفظ التكاليف"
)


// ==================== हिन्दी (HINDI) ====================
private val TRADUCTIONS_HI = mapOf(
    // Titres sections
    "titre" to "सेटिंग्स",
    "titre_profil" to "प्रोफ़ाइल",
    "titre_categories" to "सक्रिय श्रेणियाँ",
    "titre_couts_cigarettes" to "सिगरेट की लागत",
    "titre_couts_joints" to "जॉइंट्स की लागत",
    "titre_couts_alcool" to "शराब की लागत",
    "titre_habitudes" to "दैनिक आदतें",
    "titre_dates_objectifs" to "लक्ष्य तिथियाँ",
    "titre_raz_export" to "रीसेट और एक्सपोर्ट/इम्पोर्ट",

    // Labels profil
    "label_prenom" to "नाम",
    "label_langue" to "भाषा",
    "label_devise" to "मुद्रा",
    "btn_sauvegarder_profil" to "प्रोफ़ाइल सहेजें",
    "hint_prenom" to "अपना नाम दर्ज करें",

    // Labels catégories
    "label_cigarettes" to "सिगरेट",
    "label_joints" to "जॉइंट्स",
    "label_alcool_global" to "शराब (कुल)",
    "label_bieres" to "बीयर",
    "label_liqueurs" to "लिकर (मीठी शराब)",
    "label_alcool_fort" to "हार्ड लिकर",

    // Labels formulaires cigarettes
    "radio_classiques" to "सामान्य सिगरेट",
    "radio_rouler" to "रोलिंग (हाथ से)",
    "radio_tubeuse" to "ट्यूब मशीन",
    "label_prix_paquet" to "पैकेट की कीमत",
    "label_nb_cigarettes" to "सिगरेट की संख्या",
    "label_prix_tabac" to "तंबाकू की कीमत",
    "label_prix_feuilles" to "पेपर की कीमत",
    "label_nb_feuilles" to "पेपर की संख्या",
    "label_prix_filtres" to "फ़िल्टर की कीमत",
    "label_nb_filtres" to "फ़िल्टर की संख्या",
    "label_prix_tubes" to "ट्यूब की कीमत",
    "label_nb_tubes" to "ट्यूब की संख्या",

    // Joints
    "label_prix_gramme" to "प्रति ग्राम कीमत",
    "label_gramme_par_joint" to "प्रति जॉइंट ग्राम",
    "label_prix_feuilles_longues" to "लंबे रोलिंग पेपर की कीमत",
    "label_nb_feuilles_longues" to "लंबे रोलिंग पेपर की संख्या",

    // Alcool
    "label_prix_verre" to "ड्रिंक की कीमत",
    "label_unite_cl" to "इकाई (cL)",

    // Habitudes
    "label_max_journalier" to "दैनिक अधिकतम",

    // Dates
    "label_date_reduction" to "कमी की तिथि",
    "label_date_arret" to "छोड़ने की तिथि",
    "label_date_reussite" to "सफलता की तिथि",
    "btn_choisir_date" to "तिथि चुनें",

    // Limitations free / premium
    "msg_export_limite" to "एक्सपोर्ट के लिए, बिना-विज्ञापन संस्करण में अपग्रेड करें :-)",
    "msg_import_limite" to "सीमा पूरी हो गई। आज %d इम्पोर्ट शेष हैं।",
    "msg_limite_export" to "सीमा पहुँची: आज %d एक्सपोर्ट शेष (मुफ़्त संस्करण)",
    "msg_limite_import" to "सीमा पहुँची: आज %d इम्पोर्ट शेष (मुफ़्त संस्करण)",

    // Boutons RAZ
    "btn_raz_jour" to "आज रीसेट करें",
    "btn_raz_historique" to "इतिहास रीसेट करें",
    "btn_raz_usine" to "फ़ैक्टरी रीसेट",

    // Export/Import
    "btn_exporter" to "एक्सपोर्ट",
    "btn_importer" to "इम्पोर्ट",
    "btn_export_logs" to "लॉग्स एक्सपोर्ट करें",
    "msg_export_logs_impossible" to "लॉग्स एक्सपोर्ट नहीं हो सके",
    "msg_import_termine" to "इम्पोर्ट सफलतापूर्वक पूरा हुआ",
    "msg_import_erreur" to "इम्पोर्ट त्रुटि: %s",

    // Confirmations
    "confirm_raz_jour_titre" to "आज रीसेट करें",
    "confirm_raz_jour_message" to "आज की सभी खपत हटाएँ?",
    "confirm_raz_historique_titre" to "इतिहास रीसेट करें",
    "confirm_raz_historique_message" to "पूरा खपत इतिहास हटाएँ? (यह क्रिया अपरिवर्तनीय है)",
    "confirm_raz_usine_titre" to "फ़ैक्टरी रीसेट",
    "confirm_raz_usine_message" to "सब कुछ रीसेट करें (प्रोफ़ाइल, खपत, सेटिंग्स)? (यह क्रिया अपरिवर्तनीय है)",
    "confirm_import_titre" to "बैकअप इम्पोर्ट करें",
    "confirm_import_message" to "सारा वर्तमान डेटा बदल दिया जाएगा। जारी रखें?",
    "confirm_default" to "पुष्टि करें?",

    // Messages succès/erreur
    "msg_profil_sauvegarde" to "प्रोफ़ाइल सहेजी गई",
    "msg_date_enregistree" to "तिथि सहेजी गई",
    "msg_raz_effectuee" to "रीसेट पूरा हुआ",
    "msg_export_reussi" to "एक्सपोर्ट सफल",
    "msg_import_reussi" to "इम्पोर्ट सफल",
    "msg_erreur_sauvegarde" to "सहेजने में त्रुटि",
    "msg_erreur_date" to "तिथि सहेजने में त्रुटि",
    "msg_erreur_raz" to "रीसेट त्रुटि",
    "msg_erreur_export" to "एक्सपोर्ट त्रुटि",
    "msg_erreur_import" to "इम्पोर्ट त्रुटि",
    "msg_aucune_sauvegarde" to "कोई बैकअप फ़ाइल नहीं मिली",
    "msg_donnees_invalides" to "इम्पोर्ट त्रुटि: अमान्य डेटा",
    "msg_champs_obligatoires" to "कृपया कम से कम एक लागत दर्ज करें",
    "msg_erreur_prefix" to "त्रुटि: %s",
    "sauvegarde_ok" to "सहेजा गया",

    // Bandeau profil
    "profil_complet" to "प्रोफ़ाइल: पूर्ण ✓",
    "profil_incomplet" to "प्रोफ़ाइल: अपूर्ण",
    "total_aujourdhui" to "आज कुल:",

    // Économies potentielles
    "titre_economies" to "संभावित बचत",
    "economies_si_arret" to "यदि आप पूरी तरह से छोड़ दें:",
    "economies_jour" to "दिन",
    "economies_semaine" to "सप्ताह",
    "economies_mois" to "महीना",
    "economies_annee" to "वर्ष",

    // Boutons dialog
    "btn_confirmer" to "पुष्टि करें",
    "btn_annuler" to "रद्द करें",
    "btn_ok" to "ठीक है",

    // À propos
    "titre_a_propos" to "परिचय",
    "voir_avertissement" to "चेतावनी देखें",
    "btn_manuel" to "यूज़र मैनुअल",
    "btn_cgv" to "नियम और शर्तें",
    "btn_mentions_legales" to "कानूनी सूचना",
    "btn_contact" to "सपोर्ट से संपर्क करें",
    "btn_maj" to "नवीनतम अपडेट",
    "maj_titre" to "नवीनतम अपडेट",
    "maj_contenu" to "डिप्लॉयमेंट V1",
    "btn_premium" to "विज्ञापन-मुक्त संस्करण",
    "premium_titre" to "विज्ञापन-मुक्त संस्करण",
    "premium_contenu" to "विज्ञापन-मुक्त संस्करण जल्द ही उपलब्ध होगा।\n\nयह बैनर विज्ञापनों और सीमाओं को हटा देगा।",

    // Support / store
    "support_email_subject" to "StopAddict - सपोर्ट",
    "support_email_error" to "ईमेल ऐप नहीं खुल सका: %s",
    "msg_open_store_impossible" to "स्टोर नहीं खुल सका",

    // Reset labels (section)
    "raz_sauvegarde" to "रीसेट और बैकअप",
    "raz_jour" to "दिन रीसेट",
    "raz_historique" to "इतिहास रीसेट",
    "raz_usine" to "फ़ैक्टरी रीसेट",
    "raz_jour_ok" to "दिन रीसेट हो गया",
    "raz_historique_ok" to "इतिहास रीसेट हो गया",
    "raz_usine_ok" to "फ़ैक्टरी रीसेट पूरा",

    // Units cL
    "unite_cl_global" to "इकाई (cL)",
    "unite_cl_biere" to "इकाई (cL)",
    "unite_cl_liqueur" to "इकाई (cL)",
    "unite_cl_alcool_fort" to "इकाई (cL)",

    // Bouton save costs
    "btn_sauvegarder_couts" to "लागत सहेजें"
)


// ==================== 日本語 (JAPONAIS) ====================
private val TRADUCTIONS_JA = mapOf(
    // Titres sections
    "titre" to "設定",
    "titre_profil" to "プロフィール",
    "titre_categories" to "有効なカテゴリ",
    "titre_couts_cigarettes" to "タバコの費用",
    "titre_couts_joints" to "ジョイントの費用",
    "titre_couts_alcool" to "アルコールの費用",
    "titre_habitudes" to "毎日の習慣",
    "titre_dates_objectifs" to "目標日",
    "titre_raz_export" to "リセットとエクスポート/インポート",

    // Labels profil
    "label_prenom" to "名前",
    "label_langue" to "言語",
    "label_devise" to "通貨",
    "btn_sauvegarder_profil" to "プロフィールを保存",
    "hint_prenom" to "名前を入力してください",

    // Labels catégories
    "label_cigarettes" to "タバコ",
    "label_joints" to "ジョイント",
    "label_alcool_global" to "アルコール（全体）",
    "label_bieres" to "ビール",
    "label_liqueurs" to "リキュール",
    "label_alcool_fort" to "スピリッツ",

    // Labels formulaires cigarettes
    "radio_classiques" to "通常のタバコ",
    "radio_rouler" to "手巻き",
    "radio_tubeuse" to "チューブマシン",
    "label_prix_paquet" to "パック価格",
    "label_nb_cigarettes" to "本数",
    "label_prix_tabac" to "たばこ葉の価格",
    "label_prix_feuilles" to "ペーパーの価格",
    "label_nb_feuilles" to "ペーパー枚数",
    "label_prix_filtres" to "フィルター価格",
    "label_nb_filtres" to "フィルター数",
    "label_prix_tubes" to "チューブ価格",
    "label_nb_tubes" to "チューブ数",

    // Joints
    "label_prix_gramme" to "グラム単価",
    "label_gramme_par_joint" to "1本あたりのグラム",
    "label_prix_feuilles_longues" to "ロングペーパーの価格",
    "label_nb_feuilles_longues" to "ロングペーパーの枚数",

    // Alcool
    "label_prix_verre" to "ドリンク価格",
    "label_unite_cl" to "単位（cL）",

    // Habitudes
    "label_max_journalier" to "1日の最大",

    // Dates
    "label_date_reduction" to "削減日",
    "label_date_arret" to "中止日",
    "label_date_reussite" to "成功日",
    "btn_choisir_date" to "日付を選択",

    // Limitations free / premium
    "msg_export_limite" to "エクスポートを利用するには、広告なし版にアップグレードしてください :-)",
    "msg_import_limite" to "上限に達しました。本日はあと%d回インポートできます。",
    "msg_limite_export" to "制限に達しました：本日残り%d回エクスポート（無料版）",
    "msg_limite_import" to "制限に達しました：本日残り%d回インポート（無料版）",

    // Boutons RAZ
    "btn_raz_jour" to "今日をリセット",
    "btn_raz_historique" to "履歴をリセット",
    "btn_raz_usine" to "工場出荷時リセット",

    // Export/Import
    "btn_exporter" to "エクスポート",
    "btn_importer" to "インポート",
    "btn_export_logs" to "ログをエクスポート",
    "msg_export_logs_impossible" to "ログをエクスポートできません",
    "msg_import_termine" to "インポートが正常に完了しました",
    "msg_import_erreur" to "インポートエラー: %s",

    // Confirmations
    "confirm_raz_jour_titre" to "今日をリセット",
    "confirm_raz_jour_message" to "今日の消費をすべて削除しますか？",
    "confirm_raz_historique_titre" to "履歴をリセット",
    "confirm_raz_historique_message" to "消費履歴をすべて削除しますか？（元に戻せません）",
    "confirm_raz_usine_titre" to "工場出荷時リセット",
    "confirm_raz_usine_message" to "すべてをリセットしますか（プロフィール、消費、設定）？（元に戻せません）",
    "confirm_import_titre" to "バックアップをインポート",
    "confirm_import_message" to "現在のデータはすべて置き換えられます。続行しますか？",
    "confirm_default" to "確認しますか？",

    // Messages succès/erreur
    "msg_profil_sauvegarde" to "プロフィールを保存しました",
    "msg_date_enregistree" to "日付を保存しました",
    "msg_raz_effectuee" to "リセット完了",
    "msg_export_reussi" to "エクスポート成功",
    "msg_import_reussi" to "インポート成功",
    "msg_erreur_sauvegarde" to "保存エラー",
    "msg_erreur_date" to "日付保存エラー",
    "msg_erreur_raz" to "リセットエラー",
    "msg_erreur_export" to "エクスポートエラー",
    "msg_erreur_import" to "インポートエラー",
    "msg_aucune_sauvegarde" to "バックアップファイルが見つかりません",
    "msg_donnees_invalides" to "インポートエラー：無効なデータ",
    "msg_champs_obligatoires" to "少なくとも1つの費用を入力してください",
    "msg_erreur_prefix" to "エラー：%s",
    "sauvegarde_ok" to "保存しました",

    // Bandeau profil
    "profil_complet" to "プロフィール：完全 ✓",
    "profil_incomplet" to "プロフィール：不完全",
    "total_aujourdhui" to "今日の合計：",

    // Économies potentielles
    "titre_economies" to "潜在的な節約",
    "economies_si_arret" to "完全にやめた場合：",
    "economies_jour" to "日",
    "economies_semaine" to "週",
    "economies_mois" to "月",
    "economies_annee" to "年",

    // Boutons dialog
    "btn_confirmer" to "確認",
    "btn_annuler" to "キャンセル",
    "btn_ok" to "OK",

    // À propos
    "titre_a_propos" to "情報",
    "voir_avertissement" to "警告を表示",
    "btn_manuel" to "ユーザーマニュアル",
    "btn_cgv" to "利用規約",
    "btn_mentions_legales" to "法的通知",
    "btn_contact" to "サポートに連絡",
    "btn_maj" to "最新のアップデート",
    "maj_titre" to "最新のアップデート",
    "maj_contenu" to "リリース V1",
    "btn_premium" to "広告なし版",
    "premium_titre" to "広告なし版",
    "premium_contenu" to "広告なし版はまもなく利用可能になります。\n\nバナー広告と制限が解除されます。",

    // Support / store
    "support_email_subject" to "StopAddict - サポート",
    "support_email_error" to "メールアプリを開けません：%s",
    "msg_open_store_impossible" to "ストアを開けません",

    // Reset labels (section)
    "raz_sauvegarde" to "リセットとバックアップ",
    "raz_jour" to "今日をリセット",
    "raz_historique" to "履歴をリセット",
    "raz_usine" to "工場出荷時設定にリセット",
    "raz_jour_ok" to "今日をリセットしました",
    "raz_historique_ok" to "履歴をリセットしました",
    "raz_usine_ok" to "工場出荷時設定にリセットしました",

    // Units cL
    "unite_cl_global" to "単位（cL）",
    "unite_cl_biere" to "単位（cL）",
    "unite_cl_liqueur" to "単位（cL）",
    "unite_cl_alcool_fort" to "単位（cL）",

    // Bouton save costs
    "btn_sauvegarder_couts" to "費用を保存"
)
// ==================== 中文（简体 / ZH-CN） ====================
private val TRADUCTIONS_ZH_CN = mapOf(
    // Section titles
    "titre" to "设置",
    "titre_profil" to "个人资料",
    "titre_categories" to "启用的类别",
    "titre_couts_cigarettes" to "香烟成本",
    "titre_couts_joints" to "烟卷成本",
    "titre_couts_alcool" to "酒精成本",
    "titre_habitudes" to "每日习惯",
    "titre_dates_objectifs" to "目标日期",
    "titre_raz_export" to "重置与导出/导入",

    // Profile labels
    "label_prenom" to "名字",
    "label_langue" to "语言",
    "label_devise" to "货币",
    "btn_sauvegarder_profil" to "保存资料",

    // Categories labels
    "label_cigarettes" to "香烟",
    "label_joints" to "烟卷",
    "label_alcool_global" to "酒精（总计）",
    "label_bieres" to "啤酒",
    "label_liqueurs" to "利口酒",
    "label_alcool_fort" to "烈酒",

    // Cigarettes form labels
    "radio_classiques" to "普通香烟",
    "radio_rouler" to "手卷烟",
    "radio_tubeuse" to "卷管机",
    "label_prix_paquet" to "每包价格",
    "label_nb_cigarettes" to "香烟数量",
    "label_prix_tabac" to "烟草价格",
    "label_prix_feuilles" to "卷纸价格",
    "label_nb_feuilles" to "卷纸数量",
    "label_prix_filtres" to "滤嘴价格",
    "label_nb_filtres" to "滤嘴数量",
    "label_prix_tubes" to "烟管价格",
    "label_nb_tubes" to "烟管数量",

    // Joints form labels
    "label_prix_gramme" to "每克价格",
    "label_gramme_par_joint" to "每支用量（克）",
    "label_prix_feuilles_longues" to "长卷纸价格",
    "label_nb_feuilles_longues" to "长卷纸数量",

    // Alcohol form labels
    "label_prix_verre" to "每杯价格",
    "label_unite_cl" to "单位 (cL)",

    // Habits
    "label_max_journalier" to "每日上限",

    // Dates
    "label_date_reduction" to "减少日期",
    "label_date_arret" to "戒断日期",
    "label_date_reussite" to "成功日期",
    "btn_choisir_date" to "选择日期",

    // Free/Premium limits
    "msg_export_limite" to "要使用导出功能，请升级到无广告版本 :-)",
    "msg_import_limite" to "已达到限制。今天还可导入 %d 次。",
    "msg_limite_export" to "已达到限制：今天还可导出 %d 次（免费版）",
    "msg_limite_import" to "已达到限制：今天还可导入 %d 次（免费版）",

    // Reset buttons
    "btn_raz_jour" to "重置今天",
    "btn_raz_historique" to "重置历史",
    "btn_raz_usine" to "恢复出厂设置",

    // Export/Import buttons
    "btn_exporter" to "导出",
    "btn_importer" to "导入",
    "btn_export_logs" to "导出日志",

    // Confirmations
    "confirm_raz_jour_titre" to "重置今天",
    "confirm_raz_jour_message" to "删除今天所有记录？",
    "confirm_raz_historique_titre" to "重置历史",
    "confirm_raz_historique_message" to "删除所有历史记录？（此操作不可撤销）",
    "confirm_raz_usine_titre" to "恢复出厂设置",
    "confirm_raz_usine_message" to "重置所有内容（资料、记录、设置）？（此操作不可撤销）",
    "confirm_import_titre" to "导入备份",
    "confirm_import_message" to "当前所有数据将被替换。继续？",
    "confirm_default" to "确认？",

    // Success / Error messages
    "msg_profil_sauvegarde" to "资料已保存",
    "msg_date_enregistree" to "日期已保存",
    "msg_raz_effectuee" to "重置完成",
    "msg_export_reussi" to "导出成功",
    "msg_import_reussi" to "导入成功",
    "msg_erreur_sauvegarde" to "保存错误",
    "msg_erreur_date" to "日期保存错误",
    "msg_erreur_raz" to "重置错误",
    "msg_erreur_export" to "导出错误",
    "msg_erreur_import" to "导入错误",
    "msg_aucune_sauvegarde" to "未找到备份文件",
    "msg_donnees_invalides" to "导入错误：数据无效",
    "msg_champs_obligatoires" to "请至少填写一项费用",
    "msg_import_termine" to "导入已成功完成",
    "msg_import_termine_succes" to "导入已成功完成",
    "msg_import_erreur" to "导入错误：%s",
    "msg_erreur_prefix" to "错误：%s",
    "sauvegarde_ok" to "已保存",

    // Banner / Profile header
    "profil_complet" to "资料：完整 ✓",
    "profil_incomplet" to "资料：不完整",
    "total_aujourdhui" to "今日总计：",

    // Savings
    "titre_economies" to "潜在节省",
    "economies_si_arret" to "如果你完全停止：",
    "economies_jour" to "天",
    "economies_semaine" to "周",
    "economies_mois" to "月",
    "economies_annee" to "年",

    // Dialog buttons
    "btn_confirmer" to "确认",
    "btn_annuler" to "取消",
    "btn_ok" to "OK",

    // About
    "titre_a_propos" to "关于",
    "voir_avertissement" to "查看警告",
    "btn_manuel" to "使用手册",
    "btn_cgv" to "条款与条件",
    "btn_mentions_legales" to "法律声明",
    "btn_contact" to "联系支持",
    "btn_maj" to "最新更新",
    "maj_titre" to "最新更新",
    "maj_contenu" to "发布 V1",
    "btn_premium" to "无广告版本",
    "premium_titre" to "无广告版本",
    "premium_contenu" to "无广告版本即将推出。\n\n它将移除广告横幅与限制。",
    "hint_prenom" to "请输入你的名字",

    // Reset & backup labels
    "raz_sauvegarde" to "重置与备份",
    "raz_jour" to "重置当天",
    "raz_historique" to "重置历史",
    "raz_usine" to "恢复出厂设置",
    "raz_jour_ok" to "当天已重置",
    "raz_historique_ok" to "历史已重置",
    "raz_usine_ok" to "已恢复出厂设置",

    // Units
    "unite_cl_global" to "单位 (cl)",
    "unite_cl_biere" to "单位 (cl)",
    "unite_cl_liqueur" to "单位 (cl)",
    "unite_cl_alcool_fort" to "单位 (cl)",

    // Extra keys seen in FR
    "btn_sauvegarder_couts" to "保存费用",
    "msg_export_logs_impossible" to "无法导出日志",
    "support_email_subject" to "StopAddict - 支持",
    "support_email_error" to "无法打开电子邮件应用：%s",
    "msg_open_store_impossible" to "无法打开商店"
)


// ==================== 中文（繁體 / ZH-TW） ====================
private val TRADUCTIONS_ZH_TW = mapOf(
    // Section titles
    "titre" to "設定",
    "titre_profil" to "個人檔案",
    "titre_categories" to "啟用的類別",
    "titre_couts_cigarettes" to "香菸成本",
    "titre_couts_joints" to "捲菸成本",
    "titre_couts_alcool" to "酒精成本",
    "titre_habitudes" to "每日習慣",
    "titre_dates_objectifs" to "目標日期",
    "titre_raz_export" to "重設與匯出/匯入",

    // Profile labels
    "label_prenom" to "名字",
    "label_langue" to "語言",
    "label_devise" to "貨幣",
    "btn_sauvegarder_profil" to "儲存檔案",

    // Categories labels
    "label_cigarettes" to "香菸",
    "label_joints" to "捲菸",
    "label_alcool_global" to "酒精（總計）",
    "label_bieres" to "啤酒",
    "label_liqueurs" to "利口酒",
    "label_alcool_fort" to "烈酒",

    // Cigarettes form labels
    "radio_classiques" to "一般香菸",
    "radio_rouler" to "手捲菸",
    "radio_tubeuse" to "填管機",
    "label_prix_paquet" to "一包價格",
    "label_nb_cigarettes" to "香菸數量",
    "label_prix_tabac" to "菸草價格",
    "label_prix_feuilles" to "捲紙價格",
    "label_nb_feuilles" to "捲紙數量",
    "label_prix_filtres" to "濾嘴價格",
    "label_nb_filtres" to "濾嘴數量",
    "label_prix_tubes" to "菸管價格",
    "label_nb_tubes" to "菸管數量",

    // Joints form labels
    "label_prix_gramme" to "每克價格",
    "label_gramme_par_joint" to "每支用量（克）",
    "label_prix_feuilles_longues" to "長捲紙價格",
    "label_nb_feuilles_longues" to "長捲紙數量",

    // Alcohol form labels
    "label_prix_verre" to "每杯價格",
    "label_unite_cl" to "單位 (cL)",

    // Habits
    "label_max_journalier" to "每日上限",

    // Dates
    "label_date_reduction" to "減少日期",
    "label_date_arret" to "戒斷日期",
    "label_date_reussite" to "成功日期",
    "btn_choisir_date" to "選擇日期",

    // Free/Premium limits
    "msg_export_limite" to "要使用匯出功能，請升級到無廣告版本 :-)",
    "msg_import_limite" to "已達限制。今天還可匯入 %d 次。",
    "msg_limite_export" to "已達限制：今天還可匯出 %d 次（免費版）",
    "msg_limite_import" to "已達限制：今天還可匯入 %d 次（免費版）",

    // Reset buttons
    "btn_raz_jour" to "重設今天",
    "btn_raz_historique" to "重設歷史",
    "btn_raz_usine" to "恢復出廠設定",

    // Export/Import buttons
    "btn_exporter" to "匯出",
    "btn_importer" to "匯入",
    "btn_export_logs" to "匯出日誌",

    // Confirmations
    "confirm_raz_jour_titre" to "重設今天",
    "confirm_raz_jour_message" to "刪除今天所有紀錄？",
    "confirm_raz_historique_titre" to "重設歷史",
    "confirm_raz_historique_message" to "刪除所有歷史紀錄？（此操作無法復原）",
    "confirm_raz_usine_titre" to "恢復出廠設定",
    "confirm_raz_usine_message" to "重設所有內容（檔案、紀錄、設定）？（此操作無法復原）",
    "confirm_import_titre" to "匯入備份",
    "confirm_import_message" to "目前所有資料將被取代。繼續？",
    "confirm_default" to "確認？",

    // Success / Error messages
    "msg_profil_sauvegarde" to "檔案已儲存",
    "msg_date_enregistree" to "日期已儲存",
    "msg_raz_effectuee" to "重設完成",
    "msg_export_reussi" to "匯出成功",
    "msg_import_reussi" to "匯入成功",
    "msg_erreur_sauvegarde" to "儲存錯誤",
    "msg_erreur_date" to "日期儲存錯誤",
    "msg_erreur_raz" to "重設錯誤",
    "msg_erreur_export" to "匯出錯誤",
    "msg_erreur_import" to "匯入錯誤",
    "msg_aucune_sauvegarde" to "找不到備份檔案",
    "msg_donnees_invalides" to "匯入錯誤：資料無效",
    "msg_champs_obligatoires" to "請至少輸入一項費用",
    "msg_import_termine" to "匯入已成功完成",
    "msg_import_termine_succes" to "匯入已成功完成",
    "msg_import_erreur" to "匯入錯誤：%s",
    "msg_erreur_prefix" to "錯誤：%s",
    "sauvegarde_ok" to "已儲存",

    // Banner / Profile header
    "profil_complet" to "檔案：完整 ✓",
    "profil_incomplet" to "檔案：不完整",
    "total_aujourdhui" to "今日總計：",

    // Savings
    "titre_economies" to "潛在節省",
    "economies_si_arret" to "若你完全停止：",
    "economies_jour" to "天",
    "economies_semaine" to "週",
    "economies_mois" to "月",
    "economies_annee" to "年",

    // Dialog buttons
    "btn_confirmer" to "確認",
    "btn_annuler" to "取消",
    "btn_ok" to "OK",

    // About
    "titre_a_propos" to "關於",
    "voir_avertissement" to "查看警告",
    "btn_manuel" to "使用手冊",
    "btn_cgv" to "條款與條件",
    "btn_mentions_legales" to "法律聲明",
    "btn_contact" to "聯絡支援",
    "btn_maj" to "最新更新",
    "maj_titre" to "最新更新",
    "maj_contenu" to "發布 V1",
    "btn_premium" to "無廣告版本",
    "premium_titre" to "無廣告版本",
    "premium_contenu" to "無廣告版本即將推出。\n\n將移除廣告與限制。",
    "hint_prenom" to "請輸入你的名字",

    // Reset & backup labels
    "raz_sauvegarde" to "重設與備份",
    "raz_jour" to "重設當天",
    "raz_historique" to "重設歷史",
    "raz_usine" to "恢復出廠設定",
    "raz_jour_ok" to "當天已重設",
    "raz_historique_ok" to "歷史已重設",
    "raz_usine_ok" to "已恢復出廠設定",

    // Units
    "unite_cl_global" to "單位 (cl)",
    "unite_cl_biere" to "單位 (cl)",
    "unite_cl_liqueur" to "單位 (cl)",
    "unite_cl_alcool_fort" to "單位 (cl)",

    // Extra keys seen in FR
    "btn_sauvegarder_couts" to "儲存費用",
    "msg_export_logs_impossible" to "無法匯出日誌",
    "support_email_subject" to "StopAddict - 支援",
    "support_email_error" to "無法開啟電子郵件應用：%s",
    "msg_open_store_impossible" to "無法開啟商店"
)

// Aliases utilisés par getTraductions()
private val TRADUCTIONS_ZH = TRADUCTIONS_ZH_CN      // Chinois simplifié
private val TRADUCTIONS_ZHT = TRADUCTIONS_ZH_TW     // Chinois traditionnel
    

// ==================== Nederlands (NL) ====================
private val TRADUCTIONS_NL = mapOf(
    // Section titles
    "titre" to "Instellingen",
    "titre_profil" to "Profiel",
    "titre_categories" to "Actieve categorieën",
    "titre_couts_cigarettes" to "Kosten sigaretten",
    "titre_couts_joints" to "Kosten joints",
    "titre_couts_alcool" to "Kosten alcohol",
    "titre_habitudes" to "Dagelijkse gewoonten",
    "titre_dates_objectifs" to "Doeldatums",
    "titre_raz_export" to "Reset & Export/Import",

    // Profile labels
    "label_prenom" to "Voornaam",
    "label_langue" to "Taal",
    "label_devise" to "Valuta",
    "btn_sauvegarder_profil" to "Profiel opslaan",

    // Categories labels
    "label_cigarettes" to "Sigaretten",
    "label_joints" to "Joints",
    "label_alcool_global" to "Alcohol totaal",
    "label_bieres" to "Bier",
    "label_liqueurs" to "Likeuren",
    "label_alcool_fort" to "Sterke drank",

    // Cigarettes form labels
    "radio_classiques" to "Gewone sigaretten",
    "radio_rouler" to "Shag (rollen)",
    "radio_tubeuse" to "Stopmachine",
    "label_prix_paquet" to "Prijs per pakje",
    "label_nb_cigarettes" to "Aantal sigaretten",
    "label_prix_tabac" to "Prijs tabak",
    "label_prix_feuilles" to "Prijs vloei",
    "label_nb_feuilles" to "Aantal vloei",
    "label_prix_filtres" to "Prijs filters",
    "label_nb_filtres" to "Aantal filters",
    "label_prix_tubes" to "Prijs hulzen",
    "label_nb_tubes" to "Aantal hulzen",

    // Joints form labels
    "label_prix_gramme" to "Prijs per gram",
    "label_gramme_par_joint" to "Gram per joint",
    "label_prix_feuilles_longues" to "Prijs lange vloei",
    "label_nb_feuilles_longues" to "Aantal lange vloei",

    // Alcohol form labels
    "label_prix_verre" to "Prijs per drankje",
    "label_unite_cl" to "Eenheid (cL)",

    // Habits
    "label_max_journalier" to "Dagelijks maximum",

    // Dates
    "label_date_reduction" to "Datum verminderen",
    "label_date_arret" to "Stopdatum",
    "label_date_reussite" to "Datum succes",
    "btn_choisir_date" to "Datum kiezen",

    // Free/Premium limits
    "msg_export_limite" to "Voor export: stap over naar de advertentievrije versie :-)",
    "msg_import_limite" to "Limiet bereikt. Vandaag nog %d import(s) over.",
    "msg_limite_export" to "Limiet bereikt: vandaag nog %d export(s) over (gratis versie)",
    "msg_limite_import" to "Limiet bereikt: vandaag nog %d import(s) over (gratis versie)",

    // Reset buttons
    "btn_raz_jour" to "Vandaag resetten",
    "btn_raz_historique" to "Geschiedenis resetten",
    "btn_raz_usine" to "Fabrieksreset",

    // Export/Import buttons
    "btn_exporter" to "Exporteren",
    "btn_importer" to "Importeren",
    "btn_export_logs" to "Logs exporteren",

    // Confirmations
    "confirm_raz_jour_titre" to "Vandaag resetten",
    "confirm_raz_jour_message" to "Alle consumpties van vandaag verwijderen?",
    "confirm_raz_historique_titre" to "Geschiedenis resetten",
    "confirm_raz_historique_message" to "ALLE consumptiegeschiedenis verwijderen? (Deze actie is onomkeerbaar)",
    "confirm_raz_usine_titre" to "Fabrieksreset",
    "confirm_raz_usine_message" to "ALLES resetten (profiel, consumpties, instellingen)? (Deze actie is onomkeerbaar)",
    "confirm_import_titre" to "Back-up importeren",
    "confirm_import_message" to "Alle huidige gegevens worden vervangen. Doorgaan?",
    "confirm_default" to "Bevestigen?",

    // Success / Error messages
    "msg_profil_sauvegarde" to "Profiel opgeslagen",
    "msg_date_enregistree" to "Datum opgeslagen",
    "msg_raz_effectuee" to "Reset voltooid",
    "msg_export_reussi" to "Export geslaagd",
    "msg_import_reussi" to "Import geslaagd",
    "msg_erreur_sauvegarde" to "Opslaan mislukt",
    "msg_erreur_date" to "Datum opslaan mislukt",
    "msg_erreur_raz" to "Reset mislukt",
    "msg_erreur_export" to "Export mislukt",
    "msg_erreur_import" to "Import mislukt",
    "msg_aucune_sauvegarde" to "Geen back-upbestand gevonden",
    "msg_donnees_invalides" to "Importfout: ongeldige gegevens",
    "msg_champs_obligatoires" to "Vul minimaal één kost in",
    "msg_import_termine" to "Import succesvol voltooid",
    "msg_import_termine_succes" to "Import succesvol voltooid",
    "msg_import_erreur" to "Importfout: %s",
    "msg_erreur_prefix" to "Fout: %s",
    "sauvegarde_ok" to "Opgeslagen",

    // Banner / Profile header
    "profil_complet" to "Profiel: Compleet ✓",
    "profil_incomplet" to "Profiel: Onvolledig",
    "total_aujourdhui" to "Totaal vandaag:",

    // Savings
    "titre_economies" to "Mogelijke besparingen",
    "economies_si_arret" to "Als je volledig zou stoppen:",
    "economies_jour" to "Dag",
    "economies_semaine" to "Week",
    "economies_mois" to "Maand",
    "economies_annee" to "Jaar",

    // Dialog buttons
    "btn_confirmer" to "Bevestigen",
    "btn_annuler" to "Annuleren",
    "btn_ok" to "OK",

    // About
    "titre_a_propos" to "Over",
    "voir_avertissement" to "Waarschuwing bekijken",
    "btn_manuel" to "Gebruikershandleiding",
    "btn_cgv" to "Algemene voorwaarden",
    "btn_mentions_legales" to "Juridische vermelding",
    "btn_contact" to "Contact opnemen met support",
    "btn_maj" to "Laatste updates",
    "maj_titre" to "Laatste updates",
    "maj_contenu" to "Uitrol V1",
    "btn_premium" to "Advertentievrije versie",
    "premium_titre" to "Advertentievrije versie",
    "premium_contenu" to "De advertentievrije versie is binnenkort beschikbaar.\n\nDeze verwijdert banners en beperkingen.",
    "hint_prenom" to "Voer je voornaam in",

    // Reset & backup labels
    "raz_sauvegarde" to "Reset en back-up",
    "raz_jour" to "Dag resetten",
    "raz_historique" to "Geschiedenis resetten",
    "raz_usine" to "Fabrieksreset",
    "raz_jour_ok" to "Dag gereset",
    "raz_historique_ok" to "Geschiedenis gereset",
    "raz_usine_ok" to "Fabrieksreset voltooid",

    // Units
    "unite_cl_global" to "Eenheid (cl)",
    "unite_cl_biere" to "Eenheid (cl)",
    "unite_cl_liqueur" to "Eenheid (cl)",
    "unite_cl_alcool_fort" to "Eenheid (cl)",

    // Extra keys seen in FR
    "btn_sauvegarder_couts" to "Kosten opslaan",
    "msg_export_logs_impossible" to "Kan logs niet exporteren",
    "support_email_subject" to "StopAddict - Support",
    "support_email_error" to "Kan e-mailapp niet openen: %s",
    "msg_open_store_impossible" to "Kan de store niet openen"
)
    /**
     * Fonction helper pour récupérer une traduction spécifique
     */
    fun getTexte(key: String, codeLangue: String): String {
        return try {
            val traductions = getTraductions(codeLangue)
            traductions[key] ?: key
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, "Erreur récupération texte $key", e)
            key
        }
    }

    /**
     * Fonction helper pour formater les messages avec paramètres
     */
    fun formatMessage(key: String, codeLangue: String, vararg args: Any): String {
        return try {
            val traductions = getTraductions(codeLangue)
            val template = traductions[key] ?: key
            String.format(template, *args)
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, "Erreur formatage message $key", e)
            key
        }
    }

    // ==================== CONTENU MANUEL ====================

    fun getManuel(langue: String): String {
        return when (langue) {
            "FR" -> """
                MANUEL D’UTILISATION – STOPADDICT
                
                Bienvenue dans StopAddict. Cette application a été conçue pour vous permettre de suivre votre consommation de cigarettes, de joints et d’alcool de manière simple, visuelle et informative. Elle ne remplace pas un avis médical et n’incite en aucun cas à consommer. Elle sert uniquement de compteur évolué, d’outil d’estimation et de sensibilisation.
                
                1. PRÉSENTATION GÉNÉRALE
                StopAddict fonctionne entièrement hors ligne, sans inscription et sans collecte de données. Toutes les informations que vous saisissez sont stockées sur votre appareil. Vous pouvez utiliser l’application quotidiennement pour visualiser vos consommations, votre budget, vos habitudes, vos progrès et vos objectifs.
                
                L’application se compose de plusieurs onglets :
                • Accueil : les compteurs du jour et les conseils adaptatifs.
                • Coûts : paramétrage précis du prix de chaque consommation.
                • Habitudes : saisie de vos seuils journaliers habituels.
                • Dates : définition de vos objectifs (arrêt ou réduction).
                • Agenda : résumé quotidien de toutes vos consommations.
                • Stats : graphiques d’évolution et estimations.
                • Réglages : langue, thème, profil, informations, premium.
                
                2. FONCTIONNEMENT DE L’ONGLET ACCUEIL
                La page d’accueil affiche les catégories de consommation que vous avez activées. Pour chaque catégorie, deux boutons + et – permettent d’ajouter ou de retirer une unité. Les valeurs enregistrées sont automatiquement sauvegardées pour la journée en cours.
                
                Vous pouvez activer ou désactiver les catégories : cigarettes, joints, alcool global, bières, liqueurs, alcools forts. La désactivation d’une catégorie masque les boutons mais conserve l’historique.
                
                Le total du jour est affiché en haut. Il se met à jour en temps réel.
                
                3. CONSEILS PERSONNALISÉS
                La zone « Conseil » propose automatiquement des messages adaptés à votre situation. L’application analyse uniquement :
                • la présence ou non d’un prénom,
                • vos coûts,
                • vos habitudes,
                • vos dates objectives,
                • votre consommation du jour.
                
                Les conseils se mettent à jour régulièrement, mais une protection anti-spam empêche leur changement trop rapide lorsque vous ajoutez plusieurs consommations d’affilée.
                
                4. FONCTIONNEMENT DU MODULE « COÛTS »
                Cet onglet vous permet d’indiquer le coût réel de vos consommations :
                • Cigarettes classiques : prix du paquet et nombre de cigarettes.
                • Cigarettes à rouler : prix du tabac, des feuilles, des filtres, nombre d’unités par paquet, nombre de cigarettes réellement fabriquées.
                • Cigarettes à tuber : prix du tabac à tubes, prix des tubes, nombre de tubes, nombre de cigarettes fabriquées.
                • Joints : prix au gramme, grammage par joint, prix des feuilles.
                • Alcool : prix par verre pour chaque type d’alcool.
                
                Les prix unitaires sont automatiquement calculés selon votre mode (classique / rouler / tuber). Ces coûts sont ensuite utilisés dans l’onglet Stats pour estimer vos dépenses et économies.
                
                5. MODULE « HABITUDES »
                Vous pouvez indiquer combien vous consommez habituellement par jour, pour chaque catégorie. Cela permet à l’application d’évaluer :
                • si vous êtes en dessous de vos habitudes,
                • si vous êtes dans votre moyenne,
                • si vous dépassez vos seuils.
                
                Ces valeurs servent également au calcul des économies « réelles » (par rapport à vos habitudes, pas seulement au total consommé).
                
                6. MODULE « DATES »
                Vous pouvez définir deux types d’objectifs :
                • Date d’arrêt : objectif de stopper totalement une catégorie.
                • Date de réduction : objectif de réduire progressivement.
                
                Si une date est définie, l’application calcule automatiquement le nombre de jours restants, la date du jour d’objectif ou le dépassement éventuel.
                
                7. ONGLET AGENDA
                L’agenda affiche l’historique de vos consommations jour par jour. Il permet de visualiser rapidement :
                • ce que vous avez consommé,
                • dans quelles quantités,
                • l’évolution de vos comportements.
                
                L’agenda est mis à jour automatiquement chaque jour. Les données restent stockées uniquement dans votre appareil.
                
                8. ONGLET STATS
                Les statistiques permettent d’afficher :
                • votre courbe journalière,
                • votre courbe hebdomadaire,
                • votre courbe mensuelle,
                • votre courbe annuelle.
                
                Les graphiques affichent également :
                • le total des unités consommées,
                • leur coût estimé selon vos paramètres,
                • vos économies potentielles,
                • vos économies réelles par rapport à vos habitudes.
                
                Les axes, valeurs et légendes sont adaptés automatiquement en fonction de la période choisie.
                
                9. EXPORTS ET IMPORTS
                Dans la version gratuite :
                • l’export n’est pas disponible,
                • l’import n’est pas disponible.
                
                Si vous essayez d’exporter ou importer, un message s’affiche :
                « Cette fonctionnalité est disponible uniquement dans la version premium. »
                
                Les fichiers d’export, lorsqu’ils sont activés en version premium, contiennent uniquement vos données locales. Aucun envoi en ligne n’est effectué.
                
                10. VERSION GRATUITE VS VERSION PREMIUM
                Version gratuite :
                • publicités affichées,
                • aucune fonction d’import/export,
                • toutes les autres fonctionnalités actives.
                
                Version premium :
                • aucune publicité,
                • import/export de vos données,
                • utilisation illimitée,
                • soutien au développeur indépendant.
                
                Un changement de smartphone peut nécessiter un nouvel achat selon les règles du Play Store.
                
                11. LANGUES ET TRADUCTIONS
                L’application s’adapte automatiquement à la langue choisie dans les paramètres. Toutes les fonctionnalités principales et les messages sont traduits. Le manuel, les CGV et les mentions légales peuvent être disponibles uniquement en français.
                
                12. CONFIDENTIALITÉ
                StopAddict ne collecte aucune donnée personnelle, ne crée aucun compte et ne transmet aucune information. Toutes vos données sont exclusivement stockées dans votre appareil. Vous êtes responsable de leur protection, sauvegarde et suppression.
                
                13. LIMITES TECHNIQUES
                Selon votre appareil, certains comportements peuvent varier :
                • affichage,
                • performance,
                • comportement des animations,
                • stockage local,
                • compatibilité partielle selon la version Android.
                
                L’application peut rencontrer des bugs ou anomalies. Aucune garantie absolue de fonctionnement n’est fournie.
                
                14. CONSEILS DE BONNE UTILISATION
                L’application ne remplace pas un professionnel de santé. Si vous souhaitez réduire ou arrêter votre consommation, il est conseillé de contacter un médecin, un tabacologue ou un organisme d’aide spécialisé.
                
                Il est recommandé d’adopter une hygiène de vie saine :
                • sport,
                • sommeil régulier,
                • alimentation équilibrée,
                • réduction progressive des produits nocifs.
                
                15. INDÉPENDANCE ET PROPRIÉTÉ
                L’application a été créée par un développeur indépendant et ne dépend d’aucune entreprise du tabac, de l’alcool, du cannabis, ni d’aucun organisme public ou privé.
                
                Les textes, fonctionnalités, images et contenus sont protégés par le droit d’auteur.
                Toute reproduction ou copie non autorisée est interdite.
                
                16. MISES À JOUR ET PROVENANCE TECHNIQUE
                L’application a été développée grâce à un travail personnel important ainsi qu’à l’utilisation ponctuelle d’outils d’intelligence artificielle pour assister la rédaction et la structure. Le développeur reste entièrement responsable du code final.
                
                Certaines mises à jour peuvent modifier ou améliorer les fonctionnalités, sans garantie de disponibilité permanente.
                
                17. ACCEPTATION
                L’utilisation de StopAddict implique l’acceptation intégrale du présent manuel.
                """.trimIndent()

                        "EN" -> """
            USER GUIDE – STOPADDICT
            
            Welcome to StopAddict. This app is designed to help you track your consumption of cigarettes, joints and alcohol in a simple, visual and informative way. It does not replace medical advice and does not encourage consumption. It is only an advanced counter, an estimation tool and an awareness tool.
            
            1. OVERVIEW
            StopAddict works entirely offline, with no sign-up and no data collection. All information you enter is stored on your device. You can use the app daily to see your consumption, budget, habits, progress and goals.
            
            The app is divided into several tabs:
            • Home: daily counters and adaptive tips.
            • Costs: detailed configuration of the price of each consumption.
            • Habits: entry of your usual daily thresholds.
            • Dates: definition of your goals (quit or reduction).
            • Agenda: daily summary of all your consumptions.
            • Stats: evolution charts and estimations.
            • Settings: language, theme, profile, information, premium.
            
            2. HOME TAB
            The home screen shows the consumption categories you have enabled. For each category, two + and – buttons let you add or remove one unit. Values are automatically saved for the current day.
            
            You can enable or disable categories: cigarettes, joints, global alcohol, beers, liqueurs, spirits. Disabling a category hides the buttons but keeps the history.
            
            The daily total is displayed at the top and updated in real time.
            
            3. PERSONALISED TIPS
            The “Tip” area automatically shows messages adapted to your situation. The app only analyses:
            • whether or not a first name is set,
            • your costs,
            • your habits,
            • your target dates,
            • your consumption of the day.
            
            Tips are updated regularly, but an anti-spam protection prevents them from changing too quickly when you add many consumptions in a row.
            
            4. “COSTS” MODULE
            This tab lets you define the real cost of your consumptions:
            • Factory-made cigarettes: pack price and number of cigarettes.
            • Roll-your-own: price of tobacco, papers and filters, number of units per pack, number of cigarettes you actually roll.
            • Tubes: price of tobacco for tubes, price of tubes, number of tubes, number of cigarettes made.
            • Joints: price per gram, grams per joint, price of papers.
            • Alcohol: price per drink for each type of alcohol.
            
            Unit prices are automatically calculated according to your mode (factory-made / roll-your-own / tubes). These costs are then used in the Stats tab to estimate your spending and savings.
            
            5. “HABITS” MODULE
            You can enter how much you usually consume per day for each category. This allows the app to evaluate:
            • whether you are below your usual level,
            • whether you are around your usual average,
            • whether you are exceeding your thresholds.
            
            These values are also used to calculate your “real” savings (compared to your habits, not just to zero consumption).
            
            6. “DATES” MODULE
            You can define two types of goals:
            • Quit date: aim to completely stop a category.
            • Reduction date: aim to reduce gradually.
            
            If a date is set, the app automatically calculates the number of days remaining, the target date or any delay.
            
            7. AGENDA TAB
            The agenda shows the history of your consumptions day by day. It lets you quickly see:
            • what you consumed,
            • in what quantities,
            • how your behaviour evolves.
            
            The agenda is automatically updated every day. Data stays stored only on your device.
            
            8. STATS TAB
            The statistics tab displays:
            • your daily curve,
            • your weekly curve,
            • your monthly curve,
            • your yearly curve.
            
            The charts also display:
            • total units consumed,
            • their estimated cost based on your settings,
            • your potential savings,
            • your real savings compared to your habits.
            
            Axes, values and legends are automatically adapted to the selected period.
            
            9. EXPORTS AND IMPORTS
            In the free version:
            • export is not available,
            • import is not available.
            
            If you try to export or import, a message is displayed:
            “This feature is only available in the premium version.”
            
            Export files, when enabled in the premium version, only contain your local data. No online transfer is performed.
            
            10. FREE VERSION VS PREMIUM VERSION
            Free version:
            • ads displayed,
            • no import/export functions,
            • all other features active.
            
            Premium version:
            • no ads,
            • import/export of your data,
            • unlimited use,
            • support for the independent developer.
            
            Changing smartphone may require a new purchase depending on Play Store rules.
            
            11. LANGUAGES AND TRANSLATIONS
            The app adapts to the language chosen in the settings. All main features and messages are translated. The manual, CGV and legal notice may be available in French only, depending on future updates.
            
            12. PRIVACY
            StopAddict does not collect any personal data, does not create any account and does not transmit any information. All your data is stored exclusively on your device. You are responsible for its protection, backup and deletion.
            
            13. TECHNICAL LIMITS
            Depending on your device, some behaviours may vary:
            • display,
            • performance,
            • animations,
            • local storage,
            • partial compatibility depending on Android version.
            
            The app may encounter bugs or issues. No absolute guarantee of perfect operation is provided.
            
            14. GOOD PRACTICES
            The app does not replace a health professional. If you want to reduce or stop your consumption, it is recommended to contact a doctor, tobacco specialist or support organisation.
            
            It is recommended to adopt a healthy lifestyle:
            • physical activity,
            • regular sleep,
            • balanced diet,
            • gradual reduction of harmful products.
            
            15. INDEPENDENCE AND OWNERSHIP
            The app was created by an independent developer and is not linked to any tobacco, alcohol or cannabis company, nor to any public or private organisation.
            
            Texts, features, images and content are protected by copyright.
            Any reproduction or unauthorised copy is forbidden.
            
            16. UPDATES AND TECHNICAL ORIGIN
            The app was developed through significant personal work and occasional use of AI tools to assist in writing and structure. The developer remains fully responsible for the final code.
            
            Some updates may change or improve features, without any guarantee of permanent availability.
            
            17. ACCEPTANCE
            Using StopAddict implies full acceptance of this manual.
        """.trimIndent()

            "ES" -> """
            MANUAL DE USO – STOPADDICT
            
            Bienvenido/a a StopAddict. Esta aplicación está diseñada para ayudarte a seguir tu consumo de cigarrillos, porros y alcohol de manera simple, visual e informativa. No sustituye un consejo médico ni incentiva en ningún caso el consumo. Su función es únicamente servir como contador avanzado, herramienta de estimación y apoyo a la concienciación.
            
            1. PRESENTACIÓN GENERAL
            StopAddict funciona completamente sin conexión, sin registro y sin recopilación de datos. Toda la información introducida se almacena en tu dispositivo. Puedes usar la aplicación a diario para visualizar tus consumos, tu presupuesto, tus hábitos, tus progresos y tus objetivos.
            
            La aplicación se compone de varias pestañas:
            • Inicio: contadores del día y consejos adaptativos.
            • Costes: configuración detallada del precio de cada consumo.
            • Hábitos: registro de tus niveles diarios habituales.
            • Fechas: definición de tus objetivos (reducción o abandono).
            • Agenda: resumen diario de todos tus consumos.
            • Estadísticas: gráficos y estimaciones.
            • Ajustes: idioma, tema, información y versión premium.
            
            2. FUNCIONAMIENTO DE LA PESTAÑA INICIO
            La pantalla de inicio muestra las categorías de consumo activadas. Para cada categoría, los botones + y – permiten añadir o restar una unidad. Los valores se guardan automáticamente para el día en curso.
            
            Puedes activar o desactivar:
            • cigarrillos,
            • porros,
            • alcohol global,
            • cervezas,
            • licores,
            • alcoholes fuertes.
            
            El total del día se actualiza en tiempo real.
            
            3. CONSEJOS PERSONALIZADOS
            La sección “Consejo” ofrece mensajes automáticos adaptados a tu situación. La aplicación analiza únicamente:
            • la presencia o no de un nombre,
            • tus costes,
            • tus hábitos,
            • tus fechas objetivo,
            • tu consumo del día.
            
            Una protección anti-spam evita cambios demasiado frecuentes.
            
            4. MÓDULO “COSTES”
            Permite indicar el coste real:
            • Cigarrillos clásicos: precio del paquete y número de cigarrillos.
            • Tabaco de liar: precio del tabaco, papel, filtros, unidades por paquete y cigarrillos producidos.
            • Tubos: precio del tabaco y tubos, unidades y cigarrillos producidos.
            • Porros: precio por gramo, gramos por porro y papel.
            • Alcohol: precio por unidad de cada tipo.
            
            Los precios unitarios se calculan automáticamente y se usan en Estadísticas.
            
            5. MÓDULO “HÁBITOS”
            Permite indicar cuántas unidades consumes habitualmente por día. Esto sirve para evaluar:
            • si estás por debajo de tus hábitos,
            • si estás dentro de tu media,
            • si los superas.
            
            También sirve para calcular ahorros “reales”.
            
            6. MÓDULO “FECHAS”
            Puedes definir:
            • fecha de abandono,
            • fecha de reducción.
            
            La aplicación calcula automáticamente días restantes o retrasos.
            
            7. PESTAÑA AGENDA
            Muestra el historial diario de consumos:
            • cantidades,
            • fechas,
            • evolución.
            
            Los datos permanecen en el dispositivo.
            
            8. PESTAÑA ESTADÍSTICAS
            Incluye:
            • curva diaria,
            • semanal,
            • mensual,
            • anual.
            
            Los gráficos muestran:
            • unidades consumidas,
            • coste estimado,
            • ahorros potenciales,
            • ahorros reales según tus hábitos.
            
            9. EXPORTACIONES E IMPORTACIONES
            En la versión gratuita:
            • exportar no está disponible,
            • importar no está disponible.
            
            Si intentas usarlos:
            «Esta función está disponible solo en la versión premium.»
            
            10. VERSIÓN GRATIS VS PREMIUM
            Versión gratuita:
            • publicidad,
            • sin importación/exportación,
            • todas las demás funciones activas.
            
            Versión premium:
            • sin publicidad,
            • importación/exportación,
            • uso ilimitado,
            • apoyo al desarrollador.
            
            Un cambio de teléfono puede requerir una nueva compra según Google Play.
            
            11. IDIOMAS Y TRADUCCIONES
            La aplicación se adapta al idioma elegido. El manual, las CGV y los avisos legales pueden estar disponibles solo en francés.
            
            12. CONFIDENCIALIDAD
            StopAddict no recopila datos. Todo se almacena localmente. Tú eres responsable de tus archivos exportados.
            
            13. LIMITACIONES TÉCNICAS
            Dependiendo del dispositivo:
            • rendimiento,
            • almacenamiento,
            • compatibilidad,
            pueden variar.
            
            No se garantiza un funcionamiento perfecto.
            
            14. BUENAS PRÁCTICAS
            Se recomienda:
            • consultar un profesional de salud,
            • practicar deporte,
            • dormir bien,
            • alimentarse de forma equilibrada.
            
            15. INDEPENDENCIA Y PROPIEDAD
            Aplicación creada por un desarrollador independiente. Contenidos protegidos por derechos de autor.
            
            16. ACTUALIZACIONES
            Las actualizaciones pueden modificar funciones sin garantía de continuidad.
            
            17. ACEPTACIÓN
            El uso de StopAddict implica aceptar este manual.
            """.trimIndent()

            "PT" -> """
            MANUAL DE UTILIZAÇÃO – STOPADDICT
            
            Bem-vindo(a) ao StopAddict. Este aplicativo foi criado para ajudar você a acompanhar seu consumo de cigarros, baseados e álcool de forma simples, visual e informativa. Ele não substitui orientação médica e não incentiva o consumo. Serve apenas como contador avançado, ferramenta de estimativa e conscientização.
            
            1. APRESENTAÇÃO GERAL
            StopAddict funciona totalmente offline, sem cadastro e sem coleta de dados. Todas as informações inseridas ficam armazenadas no seu dispositivo. Você pode usar o app diariamente para visualizar seus consumos, seus gastos, seus hábitos, seu progresso e seus objetivos.
            
            O aplicativo possui várias abas:
            • Início: contadores do dia e conselhos adaptativos.  
            • Custos: configuração dos preços de cada consumo.  
            • Hábitos: registro dos seus níveis diários habituais.  
            • Datas: definição de metas (redução ou parada).  
            • Agenda: resumo diário completo.  
            • Estatísticas: gráficos de evolução e estimativas.  
            • Configurações: idioma, tema, informações e versão premium.
            
            2. FUNCIONAMENTO DA ABA INÍCIO
            A tela inicial mostra as categorias ativadas. Os botões + e – permitem adicionar ou remover unidades. Os valores são salvos automaticamente para o dia atual.
            
            Você pode ativar ou desativar:
            • cigarros,  
            • baseados,  
            • álcool global,  
            • cervejas,  
            • licores,  
            • destilados.
            
            O total do dia é atualizado em tempo real.
            
            3. CONSELHOS PERSONALIZADOS
            A seção “Conselho” exibe mensagens automáticas adaptadas à sua situação. O app analisa apenas:
            • a presença ou não de um nome,  
            • seus custos,  
            • seus hábitos,  
            • suas metas,  
            • seu consumo diário.
            
            Um sistema anti-spam impede a mudança exagerada de conselhos.
            
            4. MÓDULO “CUSTOS”
            Permite definir o custo real de cada consumo:
            • Cigarros comuns: preço do maço e quantidade.  
            • Tabaco de enrolar: preço do tabaco, papel, filtros, unidades e cigarros produzidos.  
            • Tubos: preço dos tubos, tabaco e quantidade produzida.  
            • Baseados: preço por grama, gramas por baseado, papel.  
            • Bebidas alcoólicas: preço por dose de cada tipo.
            
            Os custos unitários são calculados automaticamente e usados nas Estatísticas.
            
            5. MÓDULO “HÁBITOS”
            Registre quantas unidades você costuma consumir por dia. Isso permite avaliar:
            • se está abaixo dos hábitos,  
            • dentro da média,  
            • ou acima dos limites.
            
            Também permite calcular economias “reais”.
            
            6. MÓDULO “DATAS”
            Você pode definir:
            • data de parada,  
            • data de redução.
            
            O aplicativo calcula automaticamente dias restantes e atrasos.
            
            7. ABA AGENDA
            Mostra o histórico diário:
            • quantidades,  
            • datas,  
            • evolução dos comportamentos.
            
            Todos os dados permanecem no dispositivo.
            
            8. ABA ESTATÍSTICAS
            Inclui:
            • gráfico diário,  
            • semanal,  
            • mensal,  
            • anual.
            
            Os gráficos exibem:
            • unidades consumidas,  
            • custo estimado,  
            • economias potenciais,  
            • economias reais.
            
            9. EXPORTAÇÃO E IMPORTAÇÃO
            Na versão gratuita:
            • exportar não está disponível,  
            • importar não está disponível.
            
            Se tentar utilizar:
            «Esta funcionalidade está disponível apenas na versão premium.»
            
            10. VERSÃO GRATUITA VS PREMIUM
            Versão gratuita:
            • publicidade,  
            • sem importação/exportação,  
            • demais funções ativas.
            
            Versão premium:
            • sem anúncios,  
            • importação/exportação,  
            • uso ilimitado,  
            • apoio ao desenvolvedor.
            
            A troca de celular pode exigir nova compra segundo o Google Play.
            
            11. IDIOMAS E TRADUÇÕES
            O app adapta-se ao idioma escolhido. O manual, as CGV e os avisos legais podem estar disponíveis apenas em francês.
            
            12. PRIVACIDADE
            StopAddict não coleta dados e não envia nada para servidores. Você é o responsável pelos arquivos exportados.
            
            13. LIMITAÇÕES TÉCNICAS
            Dependendo do aparelho:
            • desempenho,  
            • armazenamento,  
            • compatibilidade  
            podem variar.
            
            Nenhuma garantia de funcionamento perfeito.
            
            14. BOAS PRÁTICAS
            Recomenda-se:
            • consultar um profissional,  
            • praticar exercícios,  
            • dormir bem,  
            • manter alimentação saudável.
            
            15. INDEPENDÊNCIA E DIREITOS AUTORAIS
            Aplicativo criado por desenvolvedor independente. Conteúdo protegido por direitos autorais.
            
            16. ATUALIZAÇÕES
            Atualizações podem modificar funções sem garantia de continuidade.
            
            17. ACEITAÇÃO
            O uso implica aceitar este manual.
            """.trimIndent()

            "DE" -> """
            BENUTZERHANDBUCH – STOPADDICT
        
            Willkommen bei StopAddict. Diese Anwendung wurde entwickelt, um dir zu helfen, deinen Konsum
            von Zigaretten, Joints und Alkohol einfach, visuell und informativ zu verfolgen. Sie ersetzt
            keine medizinische Beratung und regt nicht zum Konsum an. Sie dient ausschließlich als
            erweitertes Zählwerkzeug sowie als Mittel zur Sensibilisierung.
        
            1. ALLGEMEINE PRÄSENTATION
            StopAddict funktioniert vollständig offline, ohne Registrierung und ohne Datenerfassung.
            Alle eingegebenen Informationen werden ausschließlich auf deinem Gerät gespeichert. Du kannst
            die App täglich nutzen, um deine Konsummengen, dein Budget, deine Gewohnheiten, deine
            Fortschritte und deine Ziele zu visualisieren.
        
            Die App besteht aus mehreren Bereichen:
            • Startseite: Tageszähler und adaptive Tipps  
            • Kosten: detaillierte Preisparameter deiner Konsumarten  
            • Gewohnheiten: tägliche Verbrauchsgrenzen  
            • Ziele: Reduktions- und Stoppdaten  
            • Agenda: tägliche Übersicht deiner Konsumhistorie  
            • Statistik: Entwicklungsdiagramme und Kostenschätzungen  
            • Einstellungen: Sprache, Profil, Informationen, Premium-Funktionen  
        
            2. FUNKTIONSWEISE DER STARTSEITE
            Die Startseite zeigt die von dir aktivierten Kategorien. Mit den Tasten + und – kannst du
            Einheiten hinzufügen oder entfernen. Alles wird automatisch für den aktuellen Tag gespeichert.
        
            Kategorien: Zigaretten, Joints, Gesamtalkohol, Bier, Likör, Spirituosen.
        
            3. PERSONALISIERTE TIPPS
            Der Bereich „Tipp“ passt sich automatisch deinem Profil, deinen Kosten, Gewohnheiten,
            Zieldaten und deinem Tageskonsum an. Ein Anti-Spam-System verhindert zu schnelle Änderungen.
        
            4. MODUL „KOSTEN“
            Hier kannst du den realen Preis deiner Konsumarten festlegen:
            • Zigaretten: Preis der Packung und Anzahl  
            • Selbstgedrehte: Tabakpreis, Papier, Filter, Anzahl fertiger Zigaretten  
            • Stopfzigaretten: Tabak, Hülsen, Stückzahl  
            • Joints: Preis pro Gramm, Gramm pro Joint, Papier  
            • Alkohol: Preis pro Glas  
        
            5. MODUL „GEWOHNHEITEN“
            Du kannst angeben, wie viel du gewöhnlich pro Tag konsumierst. Dadurch kann die App:
            • unterdurchschnittlichen Konsum erkennen  
            • Durchschnittswerte identifizieren  
            • Überschreitungen anzeigen  
        
            6. MODUL „ZIELE“
            Zwei Zieltypen sind möglich:
            • Stoppdatum  
            • Reduktionsdatum  
        
            Die App berechnet automatisch verbleibende Tage oder Überschreitungen.
        
            7. AGENDA
            Zeigt deinen täglichen Konsumverlauf. Ideal, um Trends und Muster zu erkennen.
        
            8. STATISTIKEN
            Zeigt:
            • tägliche Kurven  
            • Wochen-, Monats- und Jahresdiagramme  
            • Gesamteinheiten  
            • geschätzte Kosten  
            • potenzielle und reale Einsparungen  
        
            9. EXPORT/IMPORT
            In der kostenlosen Version:
            • kein Export  
            • kein Import  
        
            In der Premium-Version sind Export und Import verfügbar.
        
            10. GRATIS VS PREMIUM VERSION
            Gratisversion:
            • Werbung  
            • kein Import/Export  
        
            Premiumversion:
            • werbefrei  
            • Import/Export  
            • unbegrenzte Nutzung  
            • Unterstützung des unabhängigen Entwicklers  
        
            11. SPRACHEN
            Die App passt sich automatisch der eingestellten Sprache an. Rechtstexte können nur
            auf Französisch verfügbar sein.
        
            12. DATENSCHUTZ
            Keine Datensammlung, kein Konto, keine Übermittlung. Alles bleibt lokal gespeichert.
        
            13. TECHNISCHE EINSCHRÄNKUNGEN
            Unterschiede können auftreten hinsichtlich:
            • Darstellung  
            • Performance  
            • Animationen  
            • Speicherverhalten  
        
            14. NUTZUNGSEMPFEHLUNGEN
            StopAddict ersetzt keine medizinische Betreuung. Bei Reduktions- oder Stoppwunsch wird
            eine professionelle Begleitung empfohlen.
        
            15. UNABHÄNGIGKEIT
            Alle Inhalte sind urheberrechtlich geschützt. Keine Verbindung zu Tabak-, Alkohol- oder
            Cannabisindustrien.
        
            16. UPDATES
            Updates können Funktionen verändern oder verbessern.
        
            17. ANNAHME
            Die Nutzung von StopAddict impliziert die vollständige Anerkennung dieses Handbuchs.
        """.trimIndent()

            "IT" -> """
            MANUALE D’USO – STOPADDICT
        
            Benvenuto in StopAddict. Questa applicazione è progettata per aiutarti a monitorare
            il consumo di sigarette, joint e alcol in modo semplice, visivo e informativo.
            Non sostituisce un parere medico e non incoraggia in alcun modo il consumo.
            Serve esclusivamente come strumento avanzato di monitoraggio e sensibilizzazione.
        
            1. PRESENTAZIONE GENERALE
            StopAddict funziona completamente offline, senza registrazione e senza raccolta
            di dati personali. Tutte le informazioni inserite rimangono salvate solo sul tuo
            dispositivo. Puoi usare l’app ogni giorno per visualizzare consumi, budget, abitudini,
            progressi e obiettivi.
        
            L’app è composta da diverse sezioni:
            • Home: contatori del giorno e consigli adattivi  
            • Costi: impostazione dettagliata dei prezzi  
            • Abitudini: limiti giornalieri abituali  
            • Date: definizione degli obiettivi (riduzione o stop)  
            • Agenda: riepilogo quotidiano dei consumi  
            • Statistiche: grafici e stime economiche  
            • Impostazioni: lingua, profilo, informazioni, premium  
        
            2. FUNZIONAMENTO DELLA HOME
            La Home mostra le categorie attivate. Con i pulsanti + e – puoi aggiungere o togliere unità.
            I valori sono salvati automaticamente per il giorno corrente.
        
            3. CONSIGLI PERSONALIZZATI
            La sezione “Consiglio” propone messaggi adattati al profilo, ai costi, alle abitudini,
            alle date-obiettivo e al consumo giornaliero. Un sistema anti-spam evita cambi troppo rapidi.
        
            4. MODULO “COSTI”
            Puoi indicare il costo reale dei tuoi consumi:
            • Sigarette: prezzo del pacchetto e quantità  
            • Rollate: tabacco, cartine, filtri e numero di sigarette prodotte  
            • Sigarette da tubi: tabacco, tubi e quantità  
            • Joint: prezzo per grammo, grammi per joint, cartine  
            • Alcol: prezzo per bicchiere  
        
            5. MODULO “ABITUDINI”
            Indichi quanto consumi abitualmente al giorno. L’app può così valutare:
            • se sei sotto la tua media  
            • se sei nella norma  
            • se superi i limiti abituali  
        
            6. MODULO “DATE”
            Due tipi di obiettivi:
            • data di stop  
            • data di riduzione  
        
            7. AGENDA
            Mostra lo storico giornaliero dei consumi per identificare abitudini e tendenze.
        
            8. STATISTICHE
            Mostra:
            • curve giornaliere  
            • settimanali, mensili e annuali  
            • costi stimati  
            • risparmi potenziali e reali  
        
            9. EXPORT E IMPORT
            Versione gratuita:
            • nessun export  
            • nessun import  
        
            Versione premium:
            • export/import attivi  
        
            10. VERSIONE GRATUITA VS PREMIUM
            Gratuita:
            • pubblicità  
            • nessun export/import  
        
            Premium:
            • senza pubblicità  
            • export/import  
            • supporto allo sviluppatore  
        
            11. LINGUE
            L’app si adatta automaticamente alla lingua selezionata.
        
            12. PRIVACY
            Nessuna raccolta dati, nessun server, nessun account. Tutto rimane nel dispositivo.
        
            13. LIMITI TECNICI
            Possono verificarsi differenze di comportamento in base al dispositivo.
        
            14. CONSIGLI DI UTILIZZO
            L’app non sostituisce un professionista della salute.
        
            15. INDIPENDENZA
            Tutti i contenuti sono protetti da copyright.
        
            16. AGGIORNAMENTI
            Gli aggiornamenti possono modificare o migliorare l’app.
        
            17. ACCETTAZIONE
            L’uso di StopAddict implica piena accettazione del presente manuale.
        """.trimIndent()

            "RU" -> """
            РУКОВОДСТВО ПОЛЬЗОВАТЕЛЯ – STOPADDICT
        
            Добро пожаловать в StopAddict. Это приложение создано для того, чтобы вы могли
            легко и наглядно отслеживать потребление сигарет, косяков и алкоголя. Приложение
            не заменяет медицинскую консультацию и никоим образом не побуждает к употреблению.
            Оно служит только инструментом мониторинга, анализа стоимости и повышения осознанности.
        
            1. ОБЩАЯ ИНФОРМАЦИЯ
            StopAddict работает полностью офлайн, без регистрации и без сбора данных.
            Все введённые данные хранятся только на вашем устройстве.
        
            Приложение состоит из следующих разделов:
            • Главная – счётчики за день и адаптивные советы  
            • Стоимость – точная настройка цены каждой единицы  
            • Привычки – ваши обычные дневные пороги  
            • Даты – цели по снижению или прекращению  
            • Ежедневник – ваши записи по дням  
            • Статистика – графики и финансовые оценки  
            • Настройки – язык, тема, информация, premium  
        
            2. ГЛАВНЫЙ ЭКРАН
            Показывает активированные категории. Кнопки + и – позволяют увеличивать
            и уменьшать значения. Данные автоматически сохраняются за текущий день.
        
            3. ПЕРСОНАЛИЗИРОВАННЫЕ СОВЕТЫ
            Раздел «Совет» предлагает сообщения, адаптированные под ваше имя, стоимость,
            привычки, цели и текущий прогресс. Система анти-спама не допускает слишком
            быстрых изменений советов.
        
            4. РАЗДЕЛ «СТОИМОСТЬ»
            Вы можете указать реальную цену ваших потреблений:
            • Сигареты – цена пачки и количество  
            • Самокрутки – табак, бумага, фильтры, количество самокруток  
            • Сигареты-тубы – табак, тубы, количество  
            • Косяки – цена за грамм, вес одного косяка  
            • Алкоголь – цена за бокал для каждого вида напитка  
        
            5. РАЗДЕЛ «ПРИВЫЧКИ»
            Позволяет указать ваше обычное дневное потребление. На основе этого приложение
            оценивает, ниже ли вы нормы, в пределах средней или превышаете порог.
        
            6. РАЗДЕЛ «ДАТЫ»
            Два типа целей:
            • дата полного прекращения  
            • дата постепенного уменьшения  
        
            7. ЕЖЕДНЕВНИК
            Отображает историю потребления по дням для анализа поведения.
        
            8. СТАТИСТИКА
            Показывает:
            • дневные графики  
            • недельные, месячные и годовые тренды  
            • стоимость потребления  
            • потенциальную и реальную экономию  
        
            9. ЭКСПОРТ И ИМПОРТ
            Бесплатная версия:
            • нет экспорта  
            • нет импорта  
        
            Premium:
            • экспорт и импорт доступны  
        
            10. БЕСПЛАТНАЯ VS PREMIUM
            Бесплатная:
            • есть реклама  
            • нет экспорта / импорта  
        
            Premium:
            • без рекламы  
            • экспорт / импорт  
            • поддержка независимого разработчика  
        
            11. ЯЗЫКИ
            Приложение автоматически подстраивается под выбранный язык.
        
            12. КОНФИДЕНЦИАЛЬНОСТЬ
            Приложение не собирает данные, не отправляет их на сервер и не требует учётной записи.
        
            13. ТЕХНИЧЕСКИЕ ОГРАНИЧЕНИЯ
            Поведение может отличаться в зависимости от устройства.
        
            14. РЕКОМЕНДАЦИИ
            Приложение не заменяет специалистов по здоровью.
        
            15. НЕЗАВИСИМОСТЬ
            Все материалы защищены авторским правом.
        
            16. ОБНОВЛЕНИЯ
            Возможны обновления, меняющие или улучшающие функциональность.
        
            17. ПРИНЯТИЕ УСЛОВИЙ
            Использование приложения означает полное принятие данного руководства.
        """.trimIndent()

            "AR" -> """
            دليل الاستخدام – StopAddict
            
            مرحباً بك في StopAddict. صُمّم هذا التطبيق لمساعدتك على تتبّع استهلاكك للسجائر
            والحشيش والكحول بطريقة بسيطة وواضحة. هذا التطبيق لا يُعدّ بديلاً عن الاستشارة الطبية
            ولا يشجّع بأي شكل على الاستهلاك. إنه مجرد أداة مراقبة وتحليل وتوعية.
            
            1) نظرة عامة
            يعمل StopAddict بالكامل دون اتصال بالإنترنت، دون تسجيل، ودون جمع بيانات.
            جميع المعلومات المخزّنة تبقى على جهازك فقط.
            
            الأقسام الرئيسية للتطبيق:
            • الصفحة الرئيسية: عدّاد يومي ونصائح تكيفية  
            • التكاليف: ضبط السعر الحقيقي لكل استهلاك  
            • العادات: تحديد متوسطك اليومي المعتاد  
            • التواريخ: أهداف التخفيض أو الإيقاف  
            • الأجندة: استهلاكك حسب كل يوم  
            • الإحصائيات: رسوم بيانية وتقديرات مالية  
            • الإعدادات: اللغة، المظهر، المعلومات، النسخة المميزة  
            
            2) الصفحة الرئيسية
            يعرض التطبيق الفئات التي قمت بتفعيلها. زرّا + و– يتيحان زيادة أو تقليل الاستهلاك.
            يتم حفظ بيانات اليوم تلقائياً.
            
            3) النصائح المخصّصة
            يقوم التطبيق بتوليد نصائح استناداً إلى:
            • اسمك (اختياري)  
            • تكاليفك  
            • عاداتك  
            • أهدافك  
            • سلوكك اليومي  
            
            نظام مضاد للرسائل المتكررة يمنع تغيّر النصائح بسرعة كبيرة.
            
            4) قسم التكاليف
            يمكنك إدخال التكلفة الحقيقية لاستهلاكك:
            • السجائر العادية  
            • السجائر الملفوفة  
            • السجائر بأنابيب  
            • الحشيش (الكمية والسعر)  
            • الكحول بأنواعه المختلفة  
            
            يتم حساب السعر الوحدوي تلقائياً وفق إعداداتك.
            
            5) قسم العادات
            يساعدك على فهم ما إذا كان يومك:
            • أقل من المعتاد  
            • في المتوسط  
            • فوق حدّك اليومي  
            
            6) قسم التواريخ
            يمكنك تحديد:
            • تاريخ الإيقاف التام  
            • تاريخ تخفيض تدريجي  
            
            7) الأجندة
            تعرض تاريخ استهلاكك يومًا بيوم.
            
            8) الإحصائيات
            توفّر لك:
            • رسوم يومية  
            • أسبوعية  
            • شهرية  
            • سنوية  
            إضافةً إلى حساب التكلفة والاقتصاد المحتمل والحقيقي.
            
            9) التصدير والاستيراد
            في النسخة المجانية:
            • لا يوجد تصدير  
            • لا يوجد استيراد  
            
            في النسخة المميزة:
            • جميع وظائف التصدير/الاستيراد مفعّلة  
            
            10) النسخة المجانية مقابل النسخة المميزة
            النسخة المجانية:
            • تحتوي على إعلانات  
            • دون استيراد/تصدير  
            
            النسخة المميزة:
            • بلا إعلانات  
            • استيراد وتصدير  
            • دعم للمطوّر المستقل  
            
            11) اللغات
            يتكيّف التطبيق تلقائياً مع اللغة المختارة.
            
            12) الخصوصية
            لا يجمع التطبيق أية بيانات ولا يرسل أي شيء إلى خادم خارجي.
            
            13) القيود التقنية
            قد يختلف السلوك حسب نوع الجهاز وإصدار أندرويد.
            
            14) نصائح صحية
            لا يبدل التطبيق الاستشارة الطبية أو النفسية بأي شكل.
            
            15) الاستقلالية
            جميع المحتويات محمية بحقوق النشر.
            
            16) التحديثات
            قد تصدر تحديثات لتحسين الميزات دون ضمان الاستمرارية.
            
            17) قبول الشروط
            استخدام التطبيق يعني قبول هذا الدليل كاملاً.
            """.trimIndent()

            "HI" -> """
            उपयोग मैनुअल – StopAddict
            
            StopAddict में आपका स्वागत है। यह ऐप आपकी दैनिक सिगरेट, जॉइंट और शराब की खपत को
            सरल, दृश्य और उपयोगी तरीके से ट्रैक करने के लिए बनाया गया है। यह कोई चिकित्सा सलाह
            नहीं है और किसी भी प्रकार से खपत को प्रोत्साहित नहीं करता। यह केवल एक ट्रैकिंग और
            जागरूकता उपकरण है।
            
            1) सामान्य प्रस्तुति
            StopAddict पूरी तरह ऑफलाइन काम करता है — बिना खाते, बिना सर्वर और बिना डेटा संग्रह के।
            सारी जानकारी केवल आपके फोन में संग्रहीत रहती है।
            
            ऐप के मुख्य सेक्शन:
            • होम: दैनिक काउंटर और स्वचालित सलाह  
            • लागत: प्रत्येक खपत की वास्तविक कीमत निर्धारित करें  
            • आदतें: आपका सामान्य दैनिक स्तर  
            • लक्ष्य तिथियाँ: कमी या छोड़ने के उद्देश्य  
            • कैलेंडर: दिन-प्रतिदिन का इतिहास  
            • आँकड़े: ग्राफ़ और लागत/बचत अनुमान  
            • सेटिंग्स: भाषा, थीम, प्रोफ़ाइल, जानकारी, प्रीमियम  
            
            2) होम स्क्रीन
            आप जिन श्रेणियों को सक्रिय करते हैं उन्हें ऐप प्रदर्शित करता है। + और – बटन से आप
            खपत जोड़ या घटा सकते हैं। दिन का कुल स्वतः सहेजा जाता है।
            
            3) व्यक्तिगत सलाह
            सलाह आपके नाम (यदि दर्ज किया गया हो), लागत, आदतों, लक्ष्यों और दिन के व्यवहार के
            आधार पर उत्पन्न होती है। तेज़ बदलाव रोकने के लिए एंटी-स्पैम सिस्टम मौजूद है।
            
            4) लागत मॉड्यूल
            आप प्रत्येक प्रकार की खपत की वास्तविक लागत दर्ज कर सकते हैं:
            • सिगरेट (पैक/रोलिंग/ट्यूबिंग)  
            • जॉइंट: ग्राम और मूल्य  
            • शराब: प्रत्येक पेय का मूल्य  
            
            यूनिट मूल्य स्वतः गणना होता है।
            
            5) आदतें
            यह बताता है कि आपका दिन:
            • सामान्य से कम  
            • औसत  
            • आपकी सीमा से अधिक है या नहीं  
            
            यह वास्तविक बचत की गणना में भी उपयोग होता है।
            
            6) तिथि लक्ष्य
            आप दो प्रकार के लक्ष्य निर्धारित कर सकते हैं:
            • पूर्ण रूप से छोड़ने की तिथि  
            • क्रमिक कमी की तिथि  
            
            7) कैलेंडर
            आपकी दैनिक खपत का इतिहास दिखाता है।
            
            8) आँकड़े
            आप देख सकते हैं:
            • दैनिक ग्राफ़  
            • साप्ताहिक  
            • मासिक  
            • वार्षिक  
            
            साथ ही:
            • कुल यूनिट्स  
            • लागत  
            • संभावित बचत  
            • वास्तविक बचत  
            
            9) निर्यात और आयात
            मुफ़्त संस्करण:
            • निर्यात उपलब्ध नहीं  
            • आयात उपलब्ध नहीं  
            
            प्रीमियम संस्करण:
            • दोनों सुविधाएँ उपलब्ध  
            
            10) मुफ़्त बनाम प्रीमियम
            मुफ़्त संस्करण:
            • विज्ञापन शामिल  
            • कोई निर्यात/आयात नहीं  
            
            प्रीमियम:
            • बिना विज्ञापन  
            • पूर्ण आयात/निर्यात  
            • स्वतंत्र डेवलपर का समर्थन  
            
            11) भाषाएँ
            ऐप स्वचालित रूप से आपकी चुनी हुई भाषा के अनुसार समायोजित होता है।
            
            12) गोपनीयता
            StopAddict कोई व्यक्तिगत डेटा एकत्र नहीं करता और न ही इंटरनेट पर भेजता है।
            
            13) तकनीकी सीमाएँ
            उपकरण और एंड्रॉइड संस्करण के अनुसार व्यवहार भिन्न हो सकता है।
            
            14) स्वास्थ्य सलाह
            यदि आप छोड़ना चाहते हैं, तो किसी चिकित्सक या विशेषज्ञ से परामर्श लेना उचित है।
            
            15) स्वतंत्रता
            सभी सामग्री कॉपीराइट द्वारा संरक्षित है।
            
            16) अपडेट
            अपडेट जारी हो सकते हैं लेकिन स्थायी समर्थन की गारंटी नहीं है।
            
            17) स्वीकृति
            ऐप का उपयोग इस मैनुअल की पूर्ण स्वीकृति को दर्शाता है।
            """.trimIndent()

            "JA" -> """
            利用マニュアル – StopAddict
            
            StopAddict へようこそ。本アプリは、あなたの喫煙（紙巻き・手巻き・ジョイント）、
            アルコール消費量をシンプルかつ視覚的に記録するためのツールです。
            医療行為ではなく、消費を促すものでもありません。
            
            1) 概要
            StopAddict は完全オフラインで動作し、アカウント登録もデータ収集もありません。
            すべての情報はあなたの端末のみに保存されます。
            
            アプリには以下のタブがあります：
            • ホーム：当日のカウンターと自動アドバイス  
            • コスト設定：各消費の価格設定  
            • 習慣：通常の一日の目安  
            • 目標日：減量・禁煙／禁酒目標  
            • カレンダー：毎日の履歴  
            • 統計：グラフとコスト・節約額の推定  
            • 設定：言語、テーマ、情報、プレミアム  
            
            2) ホーム画面
            有効化したカテゴリー（タバコ、ジョイント、アルコールなど）が表示されます。
            「＋」「−」を押すことで当日の消費数を増減できます。
            
            3) アドバイス機能
            ユーザー名（任意）、コスト設定、習慣、目標日、当日の消費量に基づき、
            自動でアドバイスが表示されます。
            
            4) コスト設定
            各カテゴリーの消費単価を入力できます：
            • 紙巻きタバコ（1箱の価格、本数）  
            • 手巻きタバコ（タバコ葉、紙、フィルター、実際に巻く本数）  
            • チュービングタバコ（チューブ・タバコの価格、本数）  
            • ジョイント（1g あたりの価格、1本の量）  
            • アルコール（1杯あたりの価格）  
            
            5) 習慣設定
            通常の一日の消費量を設定し、その日の消費が：
            • いつもより少ない  
            • 平均的  
            • 超えている  
            かを判断します。
            
            6) 目標日
            禁煙・禁酒または減量目標の日付を設定できます。
            
            7) カレンダー
            毎日の消費履歴を視覚的に確認できます。
            
            8) 統計
            表示されるグラフ：
            • 日別  
            • 週別  
            • 月別  
            • 年別  
            
            合計消費量、計算されたコスト、節約額なども表示されます。
            
            9) エクスポート・インポート
            無料版：
            • エクスポート不可  
            • インポート不可  
            （試すと「この機能はプレミアム版限定です」と表示）
            
            プレミアム版：
            • 両機能が利用可能  
            
            10) 無料版とプレミアム版
            無料版：
            • 広告あり  
            • エクスポート・インポートなし  
            
            プレミアム版：
            • 広告なし  
            • データのエクスポート／インポート  
            • 開発者のサポート  
            
            11) 言語
            アプリは自動的に選択された言語に合わせて表示が切り替わります。
            
            12) プライバシー
            StopAddict は個人情報を一切収集せず、データは端末にのみ保存されます。
            
            13) 技術的制限
            動作は端末や Android 版により異なる場合があります。
            
            14) 健康に関する注意
            禁煙・禁酒を希望する場合は、医師または専門機関への相談を推奨します。
            
            15) 独立開発
            アプリ内のすべての内容は著作権により保護されています。
            
            16) アップデート
            アップデートは提供される場合がありますが、継続的なサポートは保証されません。
            
            17) 同意
            アプリの利用をもって本マニュアルへの同意とみなされます。
            """.trimIndent()

            "NL" -> """
                GEBRUIKSHANDLEIDING – STOPADDICT
                
                Welkom bij StopAddict. Deze applicatie is ontworpen om je te helpen je consumptie van sigaretten, joints en alcohol op een eenvoudige, visuele en informatieve manier te volgen. De app vervangt geen medisch advies en moedigt op geen enkele manier consumptie aan. Het is uitsluitend een geavanceerde teller, een schattings- en bewustwordingsinstrument.
                
                1. ALGEMENE PRESENTATIE
                StopAddict werkt volledig offline, zonder registratie en zonder gegevensverzameling. Alle informatie die je invoert wordt lokaal op je apparaat opgeslagen. Je kunt de app dagelijks gebruiken om je consumptie, budget, gewoonten, vooruitgang en doelen te visualiseren.
                
                De applicatie bestaat uit verschillende tabbladen:
                • Start: dagtellers en adaptieve adviezen.
                • Kosten: nauwkeurige instelling van de prijs per consumptie.
                • Gewoonten: invoer van je gebruikelijke dagelijkse limieten.
                • Datums: vastleggen van je doelen (stoppen of verminderen).
                • Agenda: dagelijks overzicht van alle consumpties.
                • Statistieken: evolutie­grafieken en schattingen.
                • Instellingen: taal, profiel, informatie, premium.
                
                2. WERKING VAN HET STARTTABBLAD
                De startpagina toont de geactiveerde consumptiecategorieën. Voor elke categorie zijn er + en − knoppen om een eenheid toe te voegen of te verwijderen. De ingevoerde waarden worden automatisch opgeslagen voor de huidige dag.
                
                Je kunt categorieën activeren of deactiveren: sigaretten, joints, globale alcohol, bier, likeuren en sterke drank. Het deactiveren van een categorie verbergt de knoppen maar behoudt de geschiedenis.
                
                Het dagtotaal wordt bovenaan weergegeven en in realtime bijgewerkt.
                
                3. GEPERSONALISEERDE ADVIEZEN
                De zone “Advies” toont automatisch berichten aangepast aan jouw situatie. De applicatie analyseert uitsluitend:
                • de aanwezigheid van een voornaam,
                • je kosten,
                • je gewoonten,
                • je doeldata,
                • je consumptie van de dag.
                
                De adviezen worden regelmatig vernieuwd, met een anti-spam­beveiliging om te snelle wijzigingen te voorkomen.
                
                4. MODULE “KOSTEN”
                In dit tabblad kun je de werkelijke kosten van je consumpties invoeren:
                • Klassieke sigaretten: prijs per pak en aantal sigaretten.
                • Rol­sigaretten: prijs van tabak, vloei, filters en aantal eenheden.
                • Tubesigaretten: prijs van tabak, tubes en aantal sigaretten.
                • Joints: prijs per gram, gram per joint, prijs van vloei.
                • Alcohol: prijs per glas voor elk type alcohol.
                
                De eenheidsprijzen worden automatisch berekend en gebruikt in het tabblad Statistieken.
                
                5. MODULE “GEWOONTEN”
                Je kunt aangeven hoeveel je normaal per dag consumeert per categorie. Hierdoor kan de app bepalen of je onder, gelijk aan of boven je gebruikelijke niveau zit.
                
                Deze waarden worden ook gebruikt om de “echte” besparingen te berekenen.
                
                6. MODULE “DATUMS”
                Je kunt twee soorten doelen instellen:
                • Stopdatum: volledig stoppen.
                • Reductiedatum: geleidelijk verminderen.
                
                Indien een datum is ingesteld, berekent de app automatisch het aantal resterende dagen of eventuele overschrijding.
                
                7. AGENDA
                De agenda toont je consumptiegeschiedenis per dag en helpt je je gedrag visueel te volgen. De gegevens blijven uitsluitend lokaal opgeslagen.
                
                8. STATISTIEKEN
                De statistieken tonen:
                • dagelijkse,
                • wekelijkse,
                • maandelijkse,
                • jaarlijkse evolutie.
                
                De grafieken bevatten ook kosten, potentiële en werkelijke besparingen. De assen passen zich automatisch aan.
                
                9. EXPORT EN IMPORT
                In de gratis versie:
                • export niet beschikbaar,
                • import niet beschikbaar.
                
                In de premiumversie zijn export en import beschikbaar via lokale JSON-bestanden.
                
                10. GRATIS VS PREMIUM
                Gratis versie:
                • advertenties,
                • geen export/import.
                
                Premium versie:
                • geen advertenties,
                • onbeperkte export/import,
                • ondersteuning van de onafhankelijke ontwikkelaar.
                
                11. TALEN
                De app past zich aan de gekozen taal aan. Sommige lange teksten zijn mogelijk alleen in het Frans beschikbaar.
                
                12. PRIVACY
                StopAddict verzamelt geen persoonlijke gegevens. Alles blijft lokaal op je apparaat.
                
                13. TECHNISCHE BEPERKINGEN
                Gedrag kan variëren afhankelijk van het toestel. Bugs zijn mogelijk.
                
                14. AANBEVELINGEN
                StopAddict vervangt geen gezondheidsprofessional. Raadpleeg bij behoefte een specialist.
                
                15. EIGENDOM
                StopAddict is een onafhankelijke applicatie. Alle inhoud is auteursrechtelijk beschermd.
                
                16. UPDATES
                Functies kunnen worden aangepast of verbeterd zonder garantie op permanente beschikbaarheid.
                
                17. AANVAARDING
                Het gebruik van StopAddict impliceert volledige aanvaarding van deze handleiding.
                """.trimIndent()

                "ZH" -> """
                使用手册 – STOPADDICT
                
                欢迎使用 StopAddict。本应用旨在帮助你以简单、直观且信息化的方式跟踪香烟、大麻和酒精的消费情况。该应用不构成医疗建议，也不鼓励任何形式的消费，仅作为高级计数、估算和意识提升工具。
                
                1. 总体介绍
                StopAddict 完全离线运行，无需注册，也不收集任何数据。你输入的所有信息仅存储在你的设备上。
                
                应用包含以下页面：
                • 首页：当日计数与动态建议
                • 成本：消费价格设置
                • 习惯：每日习惯阈值
                • 日期：减少或停止目标
                • 日历：每日消费汇总
                • 统计：趋势图与估算
                • 设置：语言、资料、信息
                
                2. 首页功能
                首页显示已启用的消费类别。每个类别都有 + / − 按钮用于增加或减少单位，数据会自动保存。
                
                禁用类别不会删除历史数据，仅隐藏操作。
                
                3. 个性化建议
                建议根据以下信息动态生成：
                • 是否填写姓名
                • 成本信息
                • 习惯
                • 目标日期
                • 当日消费
                
                4. 成本模块
                可设置每种消费的真实价格，用于统计和节省计算。
                
                5. 习惯模块
                输入你的日常习惯，用于比较和真实节省计算。
                
                6. 日期模块
                可设置减少或停止日期，应用会自动计算剩余天数。
                
                7. 日历
                显示每日消费记录，仅存储在本地。
                
                8. 统计
                提供日、周、月、年图表，以及成本和节省估算。
                
                9. 导出与导入
                免费版不支持导出和导入。
                高级版支持本地 JSON 文件。
                
                10. 免费版与高级版
                免费版：含广告
                高级版：无广告，支持导入导出
                
                11. 语言
                应用支持多语言，部分长文本可能仅提供法语版本。
                
                12. 隐私
                不收集、不上传任何个人数据。
                
                13. 技术限制
                不同设备可能存在差异或异常。
                
                14. 使用建议
                本应用不能替代医疗或专业建议。
                
                15. 所有权
                所有内容受版权保护。
                
                16. 更新
                功能可能随更新调整。
                
                17. 接受
                使用 StopAddict 即表示你接受本手册内容。
                """.trimIndent()

                "ZHT" -> """
                使用手冊 – STOPADDICT
                
                歡迎使用 StopAddict。本應用程式旨在幫助你以簡單、直觀且資訊化的方式追蹤香菸、大麻與酒精的使用情況。本應用不構成醫療建議，也不鼓勵任何消費行為。
                
                1. 總體介紹
                StopAddict 完全離線運作，無需註冊，也不會收集任何資料。所有輸入的資訊僅儲存在你的裝置中。
                
                應用包含以下頁面：
                • 首頁：每日計數與動態建議
                • 成本：消費價格設定
                • 習慣：每日習慣上限
                • 日期：減量或停止目標
                • 行事曆：每日消費摘要
                • 統計：趨勢圖與估算
                • 設定：語言、個人資訊
                
                2. 首頁功能
                首頁顯示已啟用的消費類別。每個類別都有 + / − 按鈕，資料會自動儲存。
                
                停用類別不會刪除歷史資料。
                
                3. 個人化建議
                建議會根據：
                • 是否填寫姓名
                • 成本
                • 習慣
                • 目標日期
                • 當日消費
                
                4. 成本模組
                可設定每種消費的實際價格，用於統計分析。
                
                5. 習慣模組
                設定每日習慣，用於比較與節省計算。
                
                6. 日期模組
                可設定減量或停止日期，系統會自動計算。
                
                7. 行事曆
                顯示每日消費紀錄，資料僅存於本機。
                
                8. 統計
                提供日、週、月、年圖表與成本分析。
                
                9. 匯出與匯入
                免費版不支援。
                進階版支援本機 JSON。
                
                10. 免費版與進階版
                免費版：含廣告
                進階版：無廣告、支援匯出匯入
                
                11. 語言
                部分長文本可能僅提供法語版本。
                
                12. 隱私
                不收集任何個人資料。
                
                13. 技術限制
                不同裝置可能有差異。
                
                14. 使用建議
                本應用不取代醫療或專業建議。
                
                15. 版權
                所有內容受著作權保護。
                
                16. 更新
                功能可能隨更新調整。
                
                17. 接受
                使用 StopAddict 即表示你接受本手冊內容。
                """.trimIndent()

            else -> getManuel("FR") // Fallback FR pour autres langues
        }
    }

    // ==================== CONTENU CGV ====================

    fun getCGV(langue: String): String {
        return when (langue) {
        "FR" -> """
            CONDITIONS GÉNÉRALES DE VENTE (CGV) – STOP ADDICT
            
            Les présentes Conditions Générales de Vente encadrent l’utilisation de la version gratuite et de la version premium de l’application StopAddict. L’installation, l’utilisation ou l’achat de la version premium implique l’acceptation pleine et entière des dispositions ci-dessous.
            
            1. OBJET DE L’APPLICATION
            StopAddict est une application ludique et informative permettant de suivre sa consommation quotidienne de cigarettes, de joints et d’alcool, ainsi que ses dépenses, habitudes et objectifs.
            L’application ne constitue en aucun cas :
            • un outil médical,
            • un service thérapeutique,
            • un dispositif de diagnostic,
            • un substitut à un accompagnement professionnel.
            
            Elle n’encourage ni la consommation, ni l’achat, ni l’usage de substances réglementées.
            
            2. VERSION GRATUITE
            La version gratuite de StopAddict offre l’accès aux fonctionnalités principales :
            • suivi journalier,
            • conseils personnalisés,
            • configuration des coûts,
            • habitudes et dates d’objectifs,
            • agenda,
            • statistiques.
            
            La version gratuite contient un bandeau publicitaire.
            Les fonctions d’import et d’export des données ne sont pas disponibles dans la version gratuite.
            Toute tentative d’accès à ces fonctions affichera le message :
            « Cette fonctionnalité est disponible uniquement dans la version premium. »
            
            3. VERSION PREMIUM
            La version premium de StopAddict propose :
            • suppression totale des publicités,
            • accès complet aux fonctions d’import/export,
            • soutien au développeur indépendant.
            
            L’achat de la version premium ne donne lieu à aucun abonnement : il s’agit d’un paiement unique via le Google Play Store.
            
            4. CONDITIONS D’ACHAT
            L’achat de la version premium se fait exclusivement via le Google Play Store selon les règles fixées par Google.
            Le développeur ne collecte aucune information personnelle concernant l’achat.
            
            Il appartient à l’utilisateur :
            • d’utiliser un compte Google valide,
            • d’être majeur selon la législation locale,
            • de vérifier les conditions du Play Store dans son pays.
            
            5. ABSENCE DE REMBOURSEMENT
            Compte tenu :
            • de la disponibilité d’une version gratuite permettant de tester l’application,
            • des limitations techniques liées aux différents modèles de smartphones,
            • des variations entre les versions Android,
            • de la nature non matérielle du produit numérique,
            
            aucun remboursement ne peut être accordé après l’achat de la version premium.
            
            Cette politique s’applique même en cas :
            • d’incompatibilité avec l’appareil,
            • de bug, anomalie ou problème d’affichage,
            • de perte de données,
            • de changement de téléphone,
            • de changement de compte Google,
            • de réinitialisation du smartphone.
            
            Certains appareils ou configurations peuvent empêcher le Play Store de reconnaître automatiquement un achat antérieur. Dans ce cas, un nouvel achat peut être exigé par Google selon ses propres règles.
            
            6. RESPONSABILITÉ DE L’UTILISATEUR
            L’utilisateur est entièrement responsable :
            • de l’usage de l’application,
            • de la saisie, de l’exactitude et de la gestion de ses données,
            • du respect des lois locales liées au tabac, à l’alcool et au cannabis,
            • de la protection de son appareil et de son stockage.
            
            L’utilisateur reconnaît que les données enregistrées dans l’application :
            • ne constituent pas une preuve,
            • ne peuvent être utilisées à des fins judiciaires,
            • ne peuvent engager la responsabilité du développeur.
            
            7. LIMITES TECHNIQUES ET DYSFONCTIONNEMENTS
            L’application peut rencontrer des variations selon les appareils, versions d’Android ou environnements logiciels. Aucun fonctionnement parfait ou garanti n’est assuré.
            
            Le développeur ne peut être tenu responsable en cas de :
            • bug informatique,
            • perte de données,
            • fichier d’export corrompu (version premium),
            • dysfonctionnement matériel ou logiciel du smartphone,
            • incompatibilité totale ou partielle,
            • arrêt du développement ou absence de mises à jour.
            
            8. DONNÉES PERSONNELLES ET CONFIDENTIALITÉ
            StopAddict n’effectue aucune collecte de données.
            Aucune information personnelle n’est transmise à un serveur.
            Aucune inscription n’est demandée.
            Toutes les données restent stockées localement dans l’appareil.
            
            Les fichiers exportés (version premium) sont sous la seule responsabilité de l’utilisateur.
            L’utilisateur doit veiller à :
            • protéger son téléphone,
            • protéger ses fichiers,
            • éviter les partages involontaires,
            • respecter la confidentialité de ses informations.
            
            9. INDÉPENDANCE ET ABSENCE DE LIENS EXTÉRIEURS
            Le développeur de StopAddict est un particulier indépendant.
            L’application n’a aucun lien avec :
            • les industries du tabac,
            • les producteurs d’alcool,
            • les entreprises liées au cannabis,
            • l’État ou une administration,
            • des organisations criminelles ou cartels.
            
            L’application ne remplace, n’imite et ne copie aucune autre application portant un nom similaire.
            Tous les éléments sont originaux et protégés par le droit d’auteur.
            
            10. PROPRIÉTÉ INTELLECTUELLE
            L’ensemble du contenu de StopAddict (textes, images, structures, fonctionnalités, code, design) est protégé par le droit d’auteur.
            Toute reproduction, modification, redistribution ou utilisation non autorisée est interdite.
            
            Même sans dépôt officiel, les droits d’auteur existent dès la création selon la loi.
            
            11. MISES À JOUR
            Des mises à jour peuvent être proposées pour améliorer l’application.
            Le développeur n’est pas tenu de :
            • maintenir une compatibilité permanente,
            • corriger tous les bugs,
            • assurer un suivi continu.
            
            Aucune garantie de disponibilité ou de pérennité du service n’est fournie.
            
            12. ACCEPTATION DES CGV
            En installant, utilisant ou achetant la version premium de StopAddict, l’utilisateur reconnaît avoir lu, compris et accepté l’intégralité des présentes Conditions Générales de Vente.
            """.trimIndent()

                    "EN" -> """
            TERMS OF SALE (CGV) – STOP ADDICT
            
            These Terms of Sale govern the use of the free and premium versions of the StopAddict application. Installing, using or purchasing the premium version implies full acceptance of the provisions below.
            
            1. PURPOSE OF THE APPLICATION
            StopAddict is a playful and informative app that allows you to track your daily consumption of cigarettes, joints and alcohol, as well as your expenses, habits and goals.
            The app is not:
            • a medical tool,
            • a therapeutic service,
            • a diagnostic device,
            • a substitute for professional support.
            
            It does not encourage consumption, purchase or use of regulated substances.
            
            2. FREE VERSION
            The free version of StopAddict gives access to the main features:
            • daily tracking,
            • personalised tips,
            • cost configuration,
            • habits and goals,
            • agenda,
            • statistics.
            
            The free version contains an advertising banner.
            Import and export functions are not available in the free version.
            Any attempt to access these functions will show the message:
            “This feature is only available in the premium version.”
            
            3. PREMIUM VERSION
            The premium version of StopAddict offers:
            • full removal of ads,
            • full access to import/export functions,
            • support for the independent developer.
            
            The premium version is a one-time purchase via Google Play Store. No subscription is charged by the developer.
            
            4. PURCHASE CONDITIONS
            The premium purchase is made exclusively via Google Play Store under Google’s rules.
            The developer does not collect any personal information about the purchase.
            
            The user is responsible for:
            • using a valid Google account,
            • being of legal age according to local legislation,
            • checking the Play Store terms in their country.
            
            5. NO REFUNDS
            Considering:
            • the availability of a free version to test the app,
            • technical limitations linked to different devices,
            • differences between Android versions,
            • the non-material nature of the digital product,
            
            no refund can be granted after purchasing the premium version.
            
            This policy applies even in case of:
            • incompatibility with the device,
            • bug, anomaly or display issue,
            • data loss,
            • phone change,
            • Google account change,
            • phone reset.
            
            Some devices or configurations may prevent the Play Store from automatically recognising a previous purchase. In such cases, a new purchase may be required by Google under its own rules.
            
            6. USER RESPONSIBILITY
            The user is fully responsible for:
            • use of the app,
            • entry, accuracy and management of their data,
            • compliance with local laws regarding tobacco, alcohol and cannabis,
            • protection of their device and storage.
            
            The user acknowledges that the data recorded in the app:
            • does not constitute proof,
            • cannot be used for legal purposes,
            • cannot engage the liability of the developer.
            
            7. TECHNICAL LIMITS AND MALFUNCTIONS
            The app may behave differently depending on devices, Android versions or software environments. No perfect operation or guaranteed compatibility is ensured.
            
            The developer cannot be held liable in case of:
            • software bug,
            • data loss,
            • corrupted export file (premium),
            • hardware or software malfunction of the smartphone,
            • total or partial incompatibility,
            • end of development or absence of updates.
            
            8. PERSONAL DATA AND PRIVACY
            StopAddict does not collect any data.
            No information is sent to a remote server.
            No sign-up is required.
            All data remains stored locally on the user’s device.
            
            Exported files (premium version) are under the sole responsibility of the user.
            The user must:
            • protect their phone,
            • protect their files,
            • avoid unwanted sharing,
            • respect the confidentiality of their information.
            
            9. INDEPENDENCE AND NO EXTERNAL LINKS
            StopAddict is developed by an independent individual.
            The app has no link with:
            • the tobacco industry,
            • alcohol producers,
            • cannabis-related companies,
            • the State or any administration,
            • criminal organisations or cartels.
            
            The app does not replace, imitate or copy any other app with a similar name.
            All elements are original and protected by copyright.
            
            10. INTELLECTUAL PROPERTY
            All content of StopAddict (texts, images, structure, features, code, design) is protected by copyright.
            Any reproduction, modification, redistribution or unauthorised use is forbidden.
            
            Even without official registration, copyright exists from creation under applicable law.
            
            11. UPDATES
            Updates may be released to improve the app.
            The developer is not required to:
            • maintain permanent compatibility,
            • fix all bugs,
            • ensure continuous support.
            
            No guarantee of availability or continuity of the service is provided.
            
            12. ACCEPTANCE OF THE TERMS
            By installing, using or purchasing the premium version of StopAddict, the user acknowledges having read, understood and accepted all these Terms of Sale.
        """.trimIndent()

            "ES" -> """
            CONDICIONES GENERALES DE VENTA (CGV) – STOPADDICT
            
            Las presentes condiciones regulan el uso de la versión gratuita y premium de StopAddict. Instalar o usar la aplicación implica aceptar íntegramente lo siguiente.
            
            1. OBJETO
            StopAddict es una aplicación informativa que permite seguir el consumo diario de cigarrillos, porros y alcohol. No es:
            • una herramienta médica,
            • un servicio terapéutico,
            • un diagnóstico,
            • un sustituto de acompañamiento profesional.
            
            2. VERSIÓN GRATUITA
            Incluye:
            • seguimiento diario,
            • consejos personalizados,
            • configuración de costes,
            • hábitos y fechas,
            • agenda,
            • estadísticas.
            
            La versión gratuita contiene un banner publicitario.  
            Las funciones de importación y exportación no están disponibles.
            
            Cualquier intento muestra:
            «Esta función está disponible solo en la versión premium.»
            
            3. VERSIÓN PREMIUM
            Incluye:
            • eliminación total de publicidad,
            • importación/exportación,
            • apoyo al desarrollador independiente.
            
            Un pago único a través de Google Play Store.
            
            4. CONDICIONES DE COMPRA
            La compra depende de las reglas de Google Play.  
            El desarrollador no recibe ni almacena datos personales.
            
            El usuario debe:
            • utilizar una cuenta Google válida,
            • ser mayor según la legislación local,
            • respetar reglas locales.
            
            5. AUSENCIA DE REEMBOLSO
            No se efectúan reembolsos debido a:
            • existencia de versión gratuita,
            • diferencias entre dispositivos,
            • variaciones Android,
            • naturaleza digital del producto.
            
            No se reembolsa en caso de:
            • incompatibilidad,
            • bug,
            • pérdida de datos,
            • cambio de teléfono o cuenta Google.
            
            6. RESPONSABILIDAD DEL USUARIO
            El usuario es responsable:
            • del uso de la aplicación,
            • de sus datos,
            • del respeto de las leyes locales,
            • de la protección de su dispositivo.
            
            Los datos no constituyen prueba ni pueden usarse judicialmente.
            
            7. LIMITACIONES TÉCNICAS
            No se garantiza:
            • compatibilidad total,
            • ausencia de bug,
            • continuidad del servicio.
            
            8. DATOS PERSONALES
            StopAddict no recopila datos ni crea cuentas.  
            Todo queda almacenado localmente.
            
            Los archivos exportados son responsabilidad del usuario.
            
            9. INDEPENDENCIA
            El desarrollador es independiente. No existe relación con industrias de tabaco, alcohol, cannabis ni organismos oficiales.
            
            10. PROPIEDAD INTELECTUAL
            Todo el contenido está protegido por derechos de autor.
            
            11. ACTUALIZACIONES
            Pueden modificar funciones sin obligación de mantenimiento.
            
            12. ACEPTACIÓN
            El uso implica aceptación de las CGV.
            """.trimIndent()

            "PT" -> """
            CONDIÇÕES GERAIS DE VENDA (CGV) – STOPADDICT
            
            Estas condições regulam o uso da versão gratuita e premium do StopAddict. Instalar ou utilizar o app significa aceitar integralmente o conteúdo abaixo.
            
            1. OBJETO
            StopAddict é um aplicativo informativo para acompanhar o consumo diário de cigarros, baseados e álcool. Não é:
            • ferramenta médica,  
            • serviço terapêutico,  
            • diagnóstico,  
            • substituto de acompanhamento profissional.
            
            2. VERSÃO GRATUITA
            Inclui:
            • acompanhamento diário,  
            • conselhos personalizados,  
            • configuração de custos,  
            • hábitos e metas,  
            • agenda,  
            • estatísticas.
            
            A versão gratuita contém publicidade.  
            As funções de importação e exportação não estão disponíveis.
            
            Mensagem exibida:
            «Esta funcionalidade está disponível apenas na versão premium.»
            
            3. VERSÃO PREMIUM
            Inclui:
            • remoção total de anúncios,  
            • importação/exportação,  
            • apoio ao desenvolvedor.
            
            Pagamento único via Google Play Store.
            
            4. CONDIÇÕES DE COMPRA
            A compra segue as regras do Google Play.  
            O desenvolvedor não recebe nem armazena dados pessoais do usuário.
            
            O usuário deve:
            • utilizar conta Google válida,  
            • ser maior conforme a legislação,  
            • respeitar regras locais.
            
            5. AUSÊNCIA DE REEMBOLSO
            Não há reembolso devido a:
            • existência da versão gratuita,  
            • diferenças entre aparelhos,  
            • variações Android,  
            • natureza digital do produto.
            
            Também não há reembolso em caso de:
            • incompatibilidade,  
            • bugs,  
            • perda de dados,  
            • troca de aparelho ou conta Google.
            
            6. RESPONSABILIDADE DO USUÁRIO
            O usuário é responsável:
            • pelo uso do app,  
            • pelos dados inseridos,  
            • pelo cumprimento das leis locais,  
            • pela proteção do dispositivo.
            
            Os registros não têm valor legal e não servem como prova.
            
            7. LIMITAÇÕES TÉCNICAS
            Não se garante:
            • compatibilidade total,  
            • ausência de falhas,  
            • continuidade do serviço.
            
            8. DADOS PESSOAIS
            StopAddict não coleta dados e não cria contas.  
            Tudo permanece no dispositivo.
            
            Arquivos exportados são responsabilidade do usuário.
            
            9. INDEPENDÊNCIA
            O desenvolvedor é independente, sem vínculo com indústrias de tabaco, álcool, cannabis ou autoridades públicas.
            
            10. PROPRIEDADE INTELECTUAL
            Todo o conteúdo é protegido por direitos autorais.
            
            11. ATUALIZAÇÕES
            Podem modificar funções sem obrigação de manutenção.
            
            12. ACEITAÇÃO
            O uso implica aceitar as CGV.
            """.trimIndent()

            "DE" -> """
            ALLGEMEINE VERKAUFSBEDINGUNGEN (AGB) – STOP ADDICT
        
            Die folgenden Bedingungen regeln die Nutzung der kostenlosen und der Premium-Version der App
            StopAddict. Die Installation, Nutzung oder der Kauf der Premium-Version bedeutet die vollständige
            Zustimmung zu diesen Bedingungen.
        
            1. ZWECK DER ANWENDUNG
            StopAddict ist ein informatives und spielerisches Werkzeug zur Verfolgung des täglichen
            Konsums von Zigaretten, Joints und Alkohol. Die App ist kein:
            • medizinisches Gerät  
            • therapeutisches Werkzeug  
            • Diagnoseinstrument  
            • Ersatz für professionelle Begleitung  
        
            2. KOSTENLOSE VERSION
            Die kostenlose Version bietet:
            • Tageszähler  
            • personalisierte Tipps  
            • Kostenparameter  
            • Gewohnheiten und Ziele  
            • Agenda  
            • Statistiken  
            • Werbung  
        
            Import/Export ist gesperrt.
        
            3. PREMIUM-VERSION
            Premium bietet:
            • keine Werbung  
            • Import/Export  
            • Unterstützung des Entwicklers  
        
            Einmaliger Kauf über den Google Play Store.
        
            4. KAUFBEDINGUNGEN
            Die Zahlung erfolgt ausschließlich über den Play Store. Keine persönlichen Daten werden
            gesammelt.
        
            Verantwortung des Nutzers:
            • gültiges Google-Konto  
            • Volljährigkeit nach lokalem Recht  
            • Überprüfung der geltenden Play-Store-Bedingungen  
        
            5. KEINE RÜCKERSTATTUNG
            Aufgrund:
            • der vorhandenen kostenlosen Testversion  
            • technischer Einschränkungen verschiedener Geräte  
            • der Natur des digitalen Produkts  
        
            sind Rückerstattungen ausgeschlossen, auch bei:
            • Inkompatibilität  
            • Bugs oder Datenverlust  
            • Gerätewechsel  
            • Zurücksetzen des Smartphones  
        
            6. VERANTWORTUNG DES NUTZERS
            Der Nutzer ist verantwortlich für:
            • die Nutzung  
            • die Genauigkeit der Daten  
            • die Einhaltung lokaler Gesetze  
            • den Schutz seines Geräts  
        
            7. TECHNISCHE GRENZEN
            Unterschiedliches Verhalten je nach Gerät möglich. Keine Funktionsgarantie.
        
            8. DATENSCHUTZ
            StopAddict sammelt keine Daten und sendet nichts an Server. Alles bleibt lokal gespeichert.
            Exportierte Dateien liegen in der Verantwortung des Nutzers.
        
            9. UNABHÄNGIGKEIT
            Kein Bezug zu Tabak-, Alkohol-, Cannabisindustrien oder staatlichen Institutionen.
        
            10. URHEBERRECHT
            Inhalte sind geschützt. Jede nicht autorisierte Nutzung ist untersagt.
        
            11. UPDATES
            Updates können erscheinen, ohne Garantie auf langfristige Unterstützung.
        
            12. ANNAHME DER AGB
            Durch Nutzung oder Kauf erkennt der Nutzer diese AGB vollständig an.
        """.trimIndent()

            "IT" -> """
            CONDIZIONI GENERALI DI VENDITA (CGV) – STOP ADDICT
        
            Le presenti condizioni regolano l’utilizzo della versione gratuita e della versione premium
            dell’app StopAddict. L’installazione, l’uso o l’acquisto della versione premium comportano
            l’accettazione integrale delle condizioni seguenti.
        
            1. SCOPO DELL’APPLICAZIONE
            StopAddict è uno strumento informativo e personale di monitoraggio del consumo di sigarette,
            joint e alcol. Non è:
            • un dispositivo medico  
            • uno strumento diagnostico  
            • un servizio terapeutico  
            • un incentivo al consumo  
        
            2. VERSIONE GRATUITA
            Include:
            • monitoraggio giornaliero  
            • consigli personalizzati  
            • impostazioni dei costi  
            • abitudini e date obiettivo  
            • agenda  
            • statistiche  
            • pubblicità  
        
            Le funzioni di esportazione e importazione non sono disponibili.
        
            3. VERSIONE PREMIUM
            Include:
            • rimozione delle pubblicità  
            • export/import attivi  
            • supporto allo sviluppatore  
        
            Pagamento unico tramite Google Play Store.
        
            4. CONDIZIONI DI ACQUISTO
            Il pagamento avviene esclusivamente tramite Play Store.
            Nessun dato personale viene raccolto.
        
            5. ASSENZA DI RIMBORSO
            Nessun rimborso possibile dopo l’acquisto, anche in caso di:
            • incompatibilità  
            • bug o problemi grafici  
            • perdita di dati  
            • cambio dispositivo  
            • reset o nuovo account Google  
        
            6. RESPONSABILITÀ DELL’UTENTE
            L’utente è responsabile di:
            • utilizzo dell’app  
            • accuratezza dei dati  
            • rispetto delle leggi locali  
            • protezione del dispositivo  
        
            7. LIMITI TECNICI
            Il funzionamento può variare in base al dispositivo. Nessuna garanzia di compatibilità totale.
        
            8. DATI PERSONALI
            Nessuna raccolta, nessun invio, nessun account. Tutto rimane in locale.
        
            9. INDIPENDENZA
            Nessun legame con industrie del tabacco, dell’alcol o della cannabis.
        
            10. PROPRIETÀ INTELLETTUALE
            Tutti i contenuti sono protetti da copyright.
        
            11. AGGIORNAMENTI
            Possono modificare o migliorare l’app senza garanzia di continuità.
        
            12. ACCETTAZIONE
            Utilizzando l’app, l’utente accetta integralmente le presenti condizioni.
        """.trimIndent()

            "RU" -> """
            ОБЩИЕ УСЛОВИЯ ПРОДАЖИ (CGV) – STOP ADDICT
        
            Данные условия регулируют использование бесплатной и премиум-версии StopAddict.
            Установка, использование или покупка премиум-версии означает полное принятие условий.
        
            1. НАЗНАЧЕНИЕ ПРИЛОЖЕНИЯ
            StopAddict – инструмент для личного мониторинга употребления сигарет, косяков и алкоголя.
            Это НЕ:
            • медицинский инструмент  
            • диагностическое средство  
            • терапевтическая услуга  
            • стимул к употреблению  
        
            2. БЕСПЛАТНАЯ ВЕРСИЯ
            Включает:
            • дневной учёт  
            • персональные советы  
            • настройки стоимости  
            • привычки и цели  
            • ежедневник  
            • статистику  
            • рекламный баннер  
        
            Экспорт / импорт недоступны.
        
            3. ВЕРСИЯ PREMIUM
            Включает:
            • отсутствие рекламы  
            • экспорт и импорт  
            • поддержку независимого разработчика  
        
            Покупается один раз через Google Play Store.
        
            4. УСЛОВИЯ ПОКУПКИ
            Оплата проходит через Play Store.
            Личные данные не собираются.
        
            5. НЕТ ВОЗВРАТА
            Возврат невозможен даже в случае:
            • несовместимости устройства  
            • ошибок отображения или багов  
            • потери данных  
            • смены телефона или аккаунта Google  
        
            6. ОТВЕТСТВЕННОСТЬ ПОЛЬЗОВАТЕЛЯ
            Пользователь несёт полную ответственность за:
            • использование приложения  
            • точность вводимых данных  
            • соблюдение местных законов  
            • защиту устройства  
        
            7. ТЕХНИЧЕСКИЕ ОГРАНИЧЕНИЯ
            Нет гарантии полной совместимости или отсутствия ошибок.
        
            8. ПЕРСОНАЛЬНЫЕ ДАННЫЕ
            Нет сбора, нет отправки, нет учётных записей. Всё хранится локально.
        
            9. НЕЗАВИСИМОСТЬ
            Нет связи с индустриями табака, алкоголя или каннабиса.
        
            10. АВТОРСКИЕ ПРАВА
            Все материалы защищены законом.
        
            11. ОБНОВЛЕНИЯ
            Возможны улучшения без обязательства по длительной поддержке.
        
            12. ПРИНЯТИЕ УСЛОВИЙ
            Используя приложение, пользователь полностью принимает данные условия.
        """.trimIndent()

                "AR" -> """
                شروط البيع العامة (CGV) – StopAddict
                
                تنظّم هذه الشروط استخدام النسخة المجانية والنسخة المميزة من تطبيق StopAddict.
                تثبيت التطبيق أو استخدامه أو شراء النسخة المميزة يعني الموافقة الكاملة على الشروط.
                
                1) هدف التطبيق
                StopAddict هو أداة شخصية لتتبّع استهلاك السجائر والحشيش والكحول.
                وهو ليس:
                • أداة طبية  
                • وسيلة تشخيص  
                • علاجاً  
                • تشجيعاً على الاستهلاك  
                
                2) النسخة المجانية
                تشمل:
                • تتبّع يومي  
                • نصائح مخصّصة  
                • ضبط التكاليف  
                • قسم العادات والأهداف  
                • الأجندة  
                • الإحصائيات  
                • الإعلانات  
                
                وظائف التصدير/الاستيراد غير متاحة.
                
                3) النسخة المميزة
                تشمل:
                • إزالة الإعلانات  
                • تصدير واستيراد البيانات  
                • دعم المطوّر المستقل  
                
                الشراء يتم دفعة واحدة عبر Google Play.
                
                4) شروط الشراء
                يتم الدفع عبر متجر Play. لا يجمع المطوّر أي معلومات شخصية.
                
                5) عدم وجود استرجاع
                لا يمكن استرجاع المبلغ حتى في الحالات التالية:
                • عدم توافق الجهاز  
                • الأخطاء أو الأعطال  
                • فقدان البيانات  
                • تغيير الهاتف أو حساب Google  
                
                6) مسؤولية المستخدم
                المستخدم مسؤول بالكامل عن:
                • استخدام التطبيق  
                • دقة البيانات  
                • احترام القوانين المحلية  
                • حماية جهازه  
                
                7) القيود التقنية
                لا ضمان لعمل التطبيق بشكل مثالي على كل الأجهزة.
                
                8) البيانات الشخصية
                لا يجمع التطبيق أي بيانات ولا يتطلب حساباً.
                كل المعلومات محفوظة على جهاز المستخدم.
                
                9) الاستقلالية
                لا علاقة للتطبيق بأي صناعة أو جهة حكومية أو تجارية.
                
                10) حقوق النشر
                جميع المحتويات محمية بالقانون.
                
                11) التحديثات
                قد يتم إصدار تحديثات دون التزام باستمرار الدعم.
                
                12) قبول الشروط
                استخدام التطبيق يعني الموافقة الكاملة على هذه الشروط.
                """.trimIndent()

                "HI" -> """
                बिक्री की सामान्य शर्तें (CGV) – StopAddict
                
                ये शर्तें StopAddict के मुफ्त और प्रीमियम संस्करणों के उपयोग को नियंत्रित करती हैं।
                ऐप को इंस्टॉल करना, उपयोग करना या प्रीमियम खरीदना इन शर्तों की पूर्ण स्वीकृति है।
                
                1) ऐप का उद्देश्य
                StopAddict एक व्यक्तिगत ट्रैकिंग उपकरण है। यह नहीं है:
                • चिकित्सा उपकरण  
                • निदान उपकरण  
                • उपचार  
                • खपत को प्रोत्साहन  
                
                2) मुफ्त संस्करण
                शामिल सुविधाएँ:
                • दैनिक ट्रैकिंग  
                • व्यक्तिगत सलाह  
                • लागत सेटिंग  
                • आदतें और लक्ष्य  
                • कैलेंडर  
                • आँकड़े  
                • विज्ञापन  
                
                निर्यात/आयात उपलब्ध नहीं होते।
                
                3) प्रीमियम संस्करण
                शामिल सुविधाएँ:
                • सभी विज्ञापन हटाए जाते हैं  
                • निर्यात और आयात सक्रिय होते हैं  
                • डेवलपर का समर्थन  
                
                यह Google Play पर एकमात्र भुगतान है — कोई सदस्यता नहीं।
                
                4) खरीद की शर्तें
                खरीद Google Play Store के माध्यम से की जाती है।
                डेवलपर कोई व्यक्तिगत डेटा एकत्र नहीं करता।
                
                5) धनवापसी नहीं
                प्रीमियम खरीद के बाद धनवापसी नहीं की जा सकती — यहाँ तक कि:
                • असंगत डिवाइस  
                • बग या त्रुटि  
                • डेटा खोना  
                • फोन बदलना या Google खाता बदलना  
                
                6) उपयोगकर्ता की जिम्मेदारी
                उपयोगकर्ता जिम्मेदार है:
                • ऐप के उपयोग के लिए  
                • डेटा की सटीकता के लिए  
                • स्थानीय कानूनों के पालन के लिए  
                • अपने डिवाइस की सुरक्षा के लिए  
                
                7) तकनीकी सीमाएँ
                ऐप सभी उपकरणों पर पूर्ण रूप से काम करने की गारंटी नहीं देता।
                
                8) गोपनीयता
                कोई डेटा एकत्र, संग्रहीत या इंटरनेट पर भेजा नहीं जाता।
                सभी जानकारी केवल डिवाइस में रहती है।
                
                9) स्वतंत्रता
                ऐप का किसी उद्योग या सरकारी संस्था से कोई संबंध नहीं है।
                
                10) बौद्धिक संपदा
                ऐप की सभी सामग्री कॉपीराइट द्वारा संरक्षित है।
                
                11) अपडेट
                अपडेट जारी हो सकते हैं लेकिन समर्थन की कोई गारंटी नहीं है।
                
                12) स्वीकृति
                ऐप का उपयोग इन CGV की पूरी स्वीकृति है।
                """.trimIndent()

                "JA" -> """
                利用規約（CGV）– StopAddict
                
                本規約は StopAddict の無料版およびプレミアム版の利用条件を定めるものです。
                アプリのインストール・利用・プレミアム版購入をもって、本規約に全面的に同意したものとみなします。
                
                1) アプリの目的
                StopAddict は個人的な消費記録ツールであり、以下には該当しません：
                • 医療機器  
                • 診断ツール  
                • 治療サービス  
                • 消費の推奨  
                
                2) 無料版
                無料版では以下が利用可能です：
                • 日次トラッキング  
                • アドバイス機能  
                • コスト設定  
                • 習慣・目標  
                • カレンダー  
                • 統計  
                • 広告表示  
                
                エクスポート・インポートは利用不可です。
                
                3) プレミアム版
                プレミアム版では：
                • 広告なし  
                • エクスポート・インポート利用可能  
                • 開発者支援  
                
                Google Play による「買い切り」であり、サブスクリプションではありません。
                
                4) 購入条件
                プレミアム版の購入は Play Store の規定に従います。
                開発者は購入に関する個人情報を収集しません。
                
                5) 返金不可
                以下の場合も返金できません：
                • 端末との非互換  
                • バグ・不具合  
                • データ消失  
                • スマホ変更  
                • Google アカウント変更  
                • 初期化後に購入が認識されない場合  
                
                6) 利用者の責任
                利用者は以下に責任を負います：
                • アプリの使用方法  
                • 入力データの正確性  
                • 法令遵守（タバコ・アルコール・大麻等）  
                • 端末の保護  
                
                7) 技術的制限
                端末や Android バージョンにより動作が異なる可能性があります。
                
                8) プライバシー
                データ収集なし、サーバー送信なし、アカウントなし。
                すべての情報は端末内に保存されます。
                
                9) 独立性
                産業（タバコ・アルコール・大麻）、政府、医療機関などとは一切関係ありません。
                
                10) 著作権
                アプリ内のすべての内容（文章・画像・コード等）は著作権により保護されています。
                
                11) アップデート
                アップデートが提供される場合がありますが、恒常的なサポートは保証されません。
                
                12) 同意
                本アプリの使用をもって、本 CGV の受諾となります。
                """.trimIndent()

                "NL" -> """
            ALGEMENE VERKOOPSVOORWAARDEN (AVV) – STOPADDICT
            
            Deze Algemene Verkoopvoorwaarden regelen het gebruik van de gratis versie en de premiumversie van de StopAddict-applicatie. Installatie, gebruik of aankoop van de premiumversie impliceert volledige en onvoorwaardelijke aanvaarding van onderstaande bepalingen.
            
            1. DOEL VAN DE APPLICATIE
            StopAddict is een informatieve en speelse applicatie waarmee je je dagelijkse consumptie van sigaretten, joints en alcohol kunt volgen, evenals je uitgaven, gewoonten en doelstellingen.
            De applicatie is in geen geval:
            • een medisch hulpmiddel,
            • een therapeutische dienst,
            • een diagnostisch instrument,
            • een vervanging voor professionele begeleiding.
            
            De applicatie moedigt geen consumptie, aankoop of gebruik van gereguleerde stoffen aan.
            
            2. GRATIS VERSIE
            De gratis versie van StopAddict biedt toegang tot de belangrijkste functies:
            • dagelijkse opvolging,
            • gepersonaliseerde adviezen,
            • kostenconfiguratie,
            • gewoonten en doeldata,
            • agenda,
            • statistieken.
            
            De gratis versie bevat een advertentiebanner.
            Import- en exportfuncties zijn niet beschikbaar in de gratis versie.
            Elke poging om deze functies te gebruiken toont het bericht:
            “Deze functie is alleen beschikbaar in de premiumversie.”
            
            3. PREMIUMVERSIE
            De premiumversie van StopAddict biedt:
            • volledige verwijdering van advertenties,
            • volledige toegang tot import- en exportfuncties,
            • ondersteuning van de onafhankelijke ontwikkelaar.
            
            De aankoop van de premiumversie is een eenmalige betaling via de Google Play Store en geen abonnement.
            
            4. AANKOOPVOORWAARDEN
            De aankoop van de premiumversie gebeurt uitsluitend via de Google Play Store volgens de regels van Google.
            De ontwikkelaar verzamelt geen persoonlijke aankoopgegevens.
            
            Het is de verantwoordelijkheid van de gebruiker:
            • een geldig Google-account te gebruiken,
            • meerderjarig te zijn volgens lokale wetgeving,
            • de Play Store-voorwaarden in zijn/haar land te controleren.
            
            5. GEEN TERUGBETALING
            Gezien:
            • het bestaan van een gratis versie om de applicatie te testen,
            • technische beperkingen van verschillende smartphones,
            • verschillen tussen Android-versies,
            • de immateriële aard van het digitale product,
            
            kan na aankoop van de premiumversie geen terugbetaling worden toegekend.
            
            Dit geldt ook in geval van:
            • incompatibiliteit met het apparaat,
            • bugs, fouten of weergaveproblemen,
            • gegevensverlies,
            • verandering van telefoon,
            • verandering van Google-account,
            • reset van het toestel.
            
            In sommige gevallen kan de Play Store een eerdere aankoop niet automatisch herkennen, waardoor een nieuwe aankoop vereist kan zijn volgens de regels van Google.
            
            6. VERANTWOORDELIJKHEID VAN DE GEBRUIKER
            De gebruiker is volledig verantwoordelijk voor:
            • het gebruik van de applicatie,
            • de invoer, juistheid en het beheer van zijn/haar gegevens,
            • de naleving van lokale wetten omtrent tabak, alcohol en cannabis,
            • de beveiliging van het toestel en de opslag.
            
            De gebruiker erkent dat de opgeslagen gegevens:
            • geen juridisch bewijs vormen,
            • niet voor gerechtelijke doeleinden kunnen worden gebruikt,
            • geen aansprakelijkheid van de ontwikkelaar kunnen inroepen.
            
            7. TECHNISCHE BEPERKINGEN EN STORINGEN
            De applicatie kan variëren afhankelijk van apparaat, Android-versie of softwareomgeving. Er wordt geen perfecte of gegarandeerde werking beloofd.
            
            De ontwikkelaar kan niet aansprakelijk worden gesteld voor:
            • softwarebugs,
            • gegevensverlies,
            • beschadigde exportbestanden (premiumversie),
            • hardware- of softwarestoringen,
            • volledige of gedeeltelijke incompatibiliteit,
            • stopzetting van ontwikkeling of uitblijven van updates.
            
            8. PERSOONSGEGEVENS EN PRIVACY
            StopAddict verzamelt geen gegevens.
            Er worden geen persoonsgegevens naar een server verzonden.
            Geen registratie is vereist.
            Alle gegevens blijven lokaal op het apparaat.
            
            Geëxporteerde bestanden (premiumversie) vallen onder de volledige verantwoordelijkheid van de gebruiker.
            
            9. ONAFHANKELIJKHEID
            De ontwikkelaar van StopAddict is een onafhankelijke particulier.
            De applicatie heeft geen enkele band met:
            • de tabaksindustrie,
            • alcoholproducenten,
            • cannabisgerelateerde bedrijven,
            • de overheid,
            • criminele organisaties.
            
            Alle inhoud is origineel en auteursrechtelijk beschermd.
            
            10. INTELLECTUEEL EIGENDOM
            Alle inhoud van StopAddict is beschermd door het auteursrecht.
            Elke ongeoorloofde reproductie of verspreiding is verboden.
            
            11. UPDATES
            Updates kunnen worden aangeboden zonder verplichting tot permanente compatibiliteit of ondersteuning.
            
            12. AANVAARDING
            Door installatie, gebruik of aankoop van de premiumversie erkent de gebruiker deze Algemene Verkoopvoorwaarden te hebben gelezen en aanvaard.
            """.trimIndent()

            "ZH" -> """
            一般销售条款（CGV）– STOPADDICT
            
            本一般销售条款规范 StopAddict 应用免费版与高级版的使用。安装、使用或购买高级版即表示用户完全接受以下条款。
            
            1. 应用目的
            StopAddict 是一款信息性与辅助性的应用，用于记录香烟、大麻及酒精的日常消费，以及相关支出、习惯和目标。
            本应用不属于：
            • 医疗工具，
            • 治疗服务，
            • 诊断设备，
            • 专业陪伴的替代品。
            
            应用不鼓励任何形式的消费或购买。
            
            2. 免费版
            免费版提供以下主要功能：
            • 每日追踪，
            • 个性化建议，
            • 成本设置，
            • 习惯与目标日期，
            • 日历，
            • 统计。
            
            免费版包含广告横幅。
            不支持数据导入与导出。
            
            3. 高级版
            高级版提供：
            • 完全移除广告，
            • 完整导入与导出功能，
            • 支持独立开发者。
            
            高级版为一次性购买，不是订阅。
            
            4. 购买条件
            购买仅通过 Google Play Store 进行。
            开发者不收集任何购买相关的个人信息。
            
            5. 不退款政策
            鉴于产品为数字内容，购买后不提供退款。
            
            6. 用户责任
            用户对应用的使用及数据管理负全部责任。
            
            7. 技术限制
            应用可能因设备或系统不同而存在差异。
            
            8. 数据与隐私
            不收集、不传输任何个人数据，所有信息仅存储于本地。
            
            9. 独立性
            应用与任何烟草、酒精、大麻产业或政府机构无关。
            
            10. 知识产权
            所有内容均受版权保护。
            
            11. 更新
            更新可能随时进行，开发者不保证持续支持。
            
            12. 接受条款
            使用或购买 StopAddict 即表示你接受本条款。
            """.trimIndent()

            "ZHT" -> """
            一般銷售條款（CGV）– STOPADDICT
            
            本一般銷售條款規範 StopAddict 應用免費版與進階版的使用。安裝、使用或購買進階版即表示使用者完全接受以下條款。
            
            1. 應用目的
            StopAddict 為一款資訊性應用，用於追蹤香菸、大麻與酒精的每日使用情況，以及相關支出、習慣與目標。
            本應用不屬於：
            • 醫療工具，
            • 治療服務，
            • 診斷設備，
            • 專業輔導的替代品。
            
            應用不鼓勵任何消費或購買行為。
            
            2. 免費版
            免費版提供：
            • 每日追蹤，
            • 個人化建議，
            • 成本設定，
            • 習慣與目標日期，
            • 行事曆，
            • 統計。
            
            免費版包含廣告。
            不支援資料匯入與匯出。
            
            3. 進階版
            進階版提供：
            • 完全移除廣告，
            • 完整匯入與匯出功能，
            • 支持獨立開發者。
            
            進階版為一次性購買，非訂閱制。
            
            4. 購買條件
            僅能透過 Google Play Store 購買。
            
            5. 不退款政策
            因數位產品特性，購買後不提供退款。
            
            6. 使用者責任
            使用者需自行負責應用使用及資料管理。
            
            7. 技術限制
            不同裝置與系統可能造成差異。
            
            8. 資料與隱私
            不收集、不傳送任何個人資料，所有資料皆儲存在本機。
            
            9. 獨立性
            本應用與任何菸酒或政府機構無關。
            
            10. 智慧財產權
            所有內容皆受著作權保護。
            
            11. 更新
            功能可能隨更新調整，無永久支援保證。
            
            12. 條款接受
            使用或購買 StopAddict 即表示你已閱讀並接受本條款。
            """.trimIndent()

            else -> getCGV("FR") // Fallback FR
        }
    }

    // ==================== CONTENU MENTIONS LÉGALES ====================

    fun getMentionsLegales(langue: String): String {
        return when (langue) {
            "FR" -> """
            MENTIONS LÉGALES & POLITIQUE DE DONNÉES – STOPADDICT
            
            1. ÉDITEUR DE L’APPLICATION
            StopAddict est une application développée par :
            Développement : StopAddict – Développeur indépendant.
            Aucun lien institutionnel, commercial ou juridique avec une entreprise du tabac, de l’alcool, du cannabis, un organisme public, un gouvernement, un établissement médical, une industrie ou un cartel.
            
            2. OBJET DE L’APPLICATION
            StopAddict est un outil ludique, informatif et personnel ayant pour objectif d’aider l’utilisateur à suivre sa consommation quotidienne de cigarettes, de joints et d’alcool.
            L’application ne constitue pas :
            • un dispositif médical,
            • un outil de diagnostic,
            • une aide thérapeutique,
            • un service de santé,
            • une incitation à la consommation,
            • un encouragement à acheter ou utiliser des substances.
            
            L’utilisation est strictement personnelle et volontaire.
            
            3. MAJORITÉ & LÉGISLATIONS INTERNATIONALES
            L’application est destinée aux personnes majeures selon :
            • la loi du pays de résidence,
            • et la loi du pays où l’utilisateur se trouve physiquement au moment de l’usage.
            
            Les règles de majorité peuvent varier (exemple : alcool → différent entre la France et les États-Unis). L’utilisateur doit vérifier la législation applicable concernant :
            • le tabac,
            • l’alcool,
            • le cannabis et dérivés,
            • l’usage de dispositifs de suivi personnels.
            
            L’éditeur ne peut être tenu responsable d’un usage illégal dans un pays, ni d’une méconnaissance des lois locales.
            
            4. NEUTRALITÉ & NON-INCITATION
            StopAddict :
            • ne fait pas la promotion des cigarettes, de l’alcool ou des joints,
            • ne valorise pas la consommation,
            • n’encourage pas l’achat de substances,
            • ne suggère aucun comportement à risque.
            
            La véritable réussite est l’absence de consommation, la réduction ou l’arrêt.
            
            5. RESPONSABILITÉ DE L’UTILISATEUR
            L’utilisateur est entièrement responsable :
            • de l’usage de l’application,
            • de ses données enregistrées,
            • des paramètres qu’il saisit,
            • de ses comportements personnels,
            • du respect des lois locales.
            
            L’application ne peut en aucun cas servir de preuve judiciaire.
            Les enregistrements internes ne peuvent pas être utilisés pour incriminer l’utilisateur ou le développeur.
            
            En cas de mauvaise utilisation, seule la responsabilité de l’utilisateur est engagée.
            
            6. PROTECTION DES DONNÉES & CONFIDENTIALITÉ
            StopAddict ne collecte aucune donnée personnelle.
            Aucun compte n’est requis.
            Aucune information n’est transmise vers un serveur externe.
            Aucun suivi, analyse, tracking, publicité ciblée ou profilage n’est effectué.
            
            Toutes les données restent exclusivement stockées sur l’appareil de l’utilisateur.
            Les éventuels fichiers exportés (version premium) sont placés sous la seule responsabilité de l’utilisateur.
            
            Il est recommandé de :
            • protéger l’accès à son téléphone,
            • sécuriser les fichiers exportés,
            • éviter l’exposition de données sensibles,
            • maintenir un environnement numérique sécurisé.
            
            7. SÉCURITÉ, PRÉVENTION & VIGILANCE
            L’application conseille de surveiller l’usage des appareils numériques par les enfants et adolescents.
            Elle rappelle l’importance :
            • de la santé,
            • du sommeil,
            • du sport,
            • de l’alimentation,
            • du soutien social et familial.
            
            StopAddict n’est pas conçu pour remplacer ces bonnes pratiques.
            
            8. PUBLICITÉ & VERSION PREMIUM
            La version gratuite contient un bandeau publicitaire conçu pour soutenir financièrement le développement.
            La version premium supprime toutes les publicités et active les fonctions d’import/export.
            
            La version premium est un achat unique, soumis aux règles du Google Play Store.
            Le téléchargement sur un nouvel appareil peut nécessiter un nouvel achat selon la politique de Google.
            
            Aucun remboursement ne peut être effectué par le développeur, même en cas :
            • d’incompatibilité,
            • de dysfonctionnement matériel ou logiciel,
            • de perte de données,
            • de bug ou anomalie,
            • de changement de smartphone.
            
            9. GARANTIES & LIMITES TECHNIQUES
            L’application est fournie « telle quelle ».
            Aucune garantie :
            • de fonctionnement parfait,
            • d’absence de bug,
            • de compatibilité totale,
            • de continuité du service,
            • de mise à jour régulière.
            
            Le développeur ne peut être tenu responsable en cas de :
            • perte de données,
            • incompatibilité,
            • problème système du smartphone,
            • corruption d’un fichier d’export,
            • arrêt du développement futur.
            
            10. PROPRIÉTÉ INTELLECTUELLE
            L’ensemble de l’application (code, textes, images, fonctionnalités, organisation, design) est protégé par le droit d’auteur.
            Toute reproduction, modification, réutilisation ou diffusion non autorisée est interdite.
            
            Même sans dépôt administratif, les droits d’auteur existent automatiquement à la création.
            
            11. INDÉPENDANCE & TRANSPARENCE
            StopAddict a été conçu par un développeur indépendant, sans influence commerciale ou institutionnelle.
            L’application n’a aucun lien avec :
            • l’État,
            • les industries du tabac,
            • les industries de l’alcool,
            • le secteur du cannabis,
            • des organisations criminelles,
            • un organisme médical ou une association.
            
            L’application ne cherche pas à imiter ou remplacer une autre application existante.
            Son nom ne constitue pas une appropriation de produits similaires.
            
            12. ORIGINE TECHNIQUE & CRÉATION
            L’application a été développée grâce à un travail personnel important, complété par l’usage d’outils d’assistance logicielle, notamment des intelligences artificielles permettant de structurer du texte ou du code.
            Le développeur reste seul responsable du produit final.
            
            13. CONTACT & SUPPORT
            L’application étant développée par un indépendant, aucun support continu n’est garanti.
            Aucun service client professionnel n’est proposé.
            Les retours ou suggestions peuvent être envoyés via la page du développeur sur le Google Play Store.
            
            14. ACCEPTATION DES MENTIONS LÉGALES
            L’utilisation de l’application implique l’acceptation intégrale et sans réserve des présentes mentions légales et de la politique de données.

            AVERTISSEMENT IMPORTANT : Cette application ne constitue pas un avis médical, psychologique ou professionnel. Les informations fournies sont générales, non personnalisées et uniquement destinées à un usage personnel et informatif. StopAddict n’est pas un dispositif médical, ne remplace pas un diagnostic professionnel, un traitement, une thérapie, ni les services d’un professionnel de santé.

            En utilisant l’application, vous acceptez que votre utilisation se fait sous votre seule responsabilité. Les lois applicables sont exclusivement les lois françaises. Tout différend devra être porté devant les tribunaux français. Si vous résidez hors de France, il vous appartient de vérifier la conformité de l’utilisation de cette application avec les lois locales de votre pays.
            
            StopAddict, son créateur et ses partenaires ne pourront être tenus responsables de tout dommage direct ou indirect lié à l’utilisation de cette application, y compris mais sans s’y limiter : la santé, les choix personnels, l'interprétation des données, l'arrêt ou la réduction de consommation, ou toute conséquence liée à vos décisions personnelles.
            
            En continuant l’utilisation, vous reconnaissez avoir lu, compris et accepté cet avertissement international.
            """.trimIndent()

                    "EN" -> """
            LEGAL NOTICE & DATA POLICY – STOPADDICT
            
            1. APP PUBLISHER
            StopAddict is an application developed by:
            Development: StopAddict – Independent developer.
            No institutional, commercial or legal link with tobacco, alcohol or cannabis companies, any public body, government, medical institution, industry or cartel.
            
            2. PURPOSE OF THE APPLICATION
            StopAddict is a playful, informative and personal tool to help users track their daily consumption of cigarettes, joints and alcohol.
            The app is not:
            • a medical device,
            • a diagnostic tool,
            • a therapeutic service,
            • a health service,
            • an incentive to consume,
            • an encouragement to buy or use substances.
            
            Use is strictly personal and voluntary.
            
            3. AGE & INTERNATIONAL LEGISLATION
            The app is intended for adults according to:
            • the law of the country of residence,
            • and the law of the country where the user is physically located when using it.
            
            Age limits may vary (e.g. alcohol rules differ between countries). The user must check applicable laws regarding:
            • tobacco,
            • alcohol,
            • cannabis and derivatives,
            • use of personal tracking tools.
            
            The publisher cannot be held responsible for illegal use in a given country or for ignorance of local laws.
            
            4. NEUTRALITY & NON-INCITATION
            StopAddict:
            • does not promote cigarettes, alcohol or joints,
            • does not glorify consumption,
            • does not encourage purchase of substances,
            • does not suggest risky behaviours.
            
            Real success is absence of consumption, reduction or quitting.
            
            5. USER RESPONSIBILITY
            The user is fully responsible for:
            • use of the app,
            • data recorded,
            • parameters entered,
            • personal behaviours,
            • compliance with local laws.
            
            The app cannot be used as legal evidence.
            Internal records cannot be used to incriminate the user or the developer.
            
            In case of misuse, only the user is responsible.
            
            6. DATA PROTECTION & PRIVACY
            StopAddict does not collect any personal data.
            No account is required.
            No information is sent to an external server.
            No tracking, profiling, targeted advertising or analytics are performed.
            
            All data remains stored exclusively on the user’s device.
            Any exported files (premium version) are under the sole responsibility of the user.
            
            It is recommended to:
            • protect access to the phone,
            • secure exported files,
            • avoid exposing sensitive data,
            • maintain a safe digital environment.
            
            7. SAFETY, PREVENTION & AWARENESS
            The app reminds users to pay attention to screen time and device use, especially for minors.
            It emphasises the importance of:
            • health,
            • sleep,
            • physical activity,
            • nutrition,
            • social and family support.
            
            StopAddict is not designed to replace these good practices.
            
            8. ADVERTISING & PREMIUM VERSION
            The free version contains an ad banner used to financially support development.
            The premium version removes all ads and enables import/export functions.
            
            The premium version is a one-time purchase, under Google Play Store rules.
            Downloading again on a new device may require a new purchase according to Google’s policy.
            
            No refund can be made by the developer, even in case of:
            • incompatibility,
            • hardware or software malfunction,
            • data loss,
            • bug or anomaly,
            • phone change.
            
            9. WARRANTIES & TECHNICAL LIMITS
            The app is provided “as is”.
            No guarantee of:
            • perfect operation,
            • absence of bugs,
            • full compatibility,
            • continuity of the service,
            • regular updates.
            
            The developer cannot be held liable in case of:
            • data loss,
            • incompatibility,
            • system issue on the smartphone,
            • corrupted export file,
            • end of development.
            
            10. INTELLECTUAL PROPERTY
            The entire app (code, texts, images, features, structure, design) is protected by copyright.
            Any reproduction, modification, reuse or unauthorised distribution is forbidden.
            
            Even without administrative registration, copyright exists automatically upon creation.
            
            11. INDEPENDENCE & TRANSPARENCY
            StopAddict is designed by an independent developer, without commercial or institutional influence.
            The app has no link with:
            • the State,
            • tobacco industry,
            • alcohol industry,
            • cannabis sector,
            • criminal organisations,
            • a medical organisation or association.
            
            The app does not aim to imitate or replace any existing app.
            Its name is not an appropriation of similar products.
            
            12. TECHNICAL ORIGIN & CREATION
            The app was developed through significant personal work, with occasional help from software assistance tools, including AI to structure text or code.
            The developer remains solely responsible for the final product.
            
            13. CONTACT & SUPPORT
            As the app is developed by an independent developer, no continuous support is guaranteed.
            No professional customer service is provided.
            Feedback or suggestions can be sent via the developer page on Google Play Store.
            
            14. ACCEPTANCE OF THE LEGAL NOTICE
            Use of the app implies full and unconditional acceptance of this legal notice and data policy.

            IMPORTANT DISCLAIMER: This application does not constitute medical, psychological, or professional advice. The information provided is general, non-personalized, and intended solely for personal and informational use. StopAddict is not a medical device and does not replace professional diagnosis, treatment, therapy, or any healthcare service.

            By using the application, you agree that your use is entirely at your own risk. The applicable law is exclusively French law. Any dispute must be brought before French courts. If you reside outside France, it is your responsibility to ensure that the use of this application complies with your country’s local laws.
            
            StopAddict, its creator, and its partners cannot be held liable for any direct or indirect damage resulting from the use of this application, including but not limited to: health consequences, personal choices, interpretation of data, reduction or cessation of consumption, or any consequence related to your personal decisions.
            
            By continuing to use the application, you acknowledge that you have read, understood, and accepted this international disclaimer.

        """.trimIndent()

            "ES" -> """
            AVISO LEGAL Y POLÍTICA DE DATOS – STOPADDICT
            
            1. EDITOR
            StopAddict es una aplicación desarrollada por un creador independiente.  
            No existe relación comercial con industrias del tabaco, alcohol, cannabis, organismos públicos o entidades médicas.
            
            2. OBJETO
            StopAddict es una herramienta personal e informativa para seguir consumos diarios.  
            No es:
            • un dispositivo médico,
            • un diagnóstico,
            • una terapia,
            • una incitación al consumo.
            
            3. MAYORÍA Y LEGISLACIÓN
            La app es solo para mayores según:
            • la ley del país de residencia,
            • la ley del país donde se usa.
            
            El usuario debe respetar las normas locales sobre tabaco, alcohol, cannabis y dispositivos de seguimiento.
            
            4. NEUTRALIDAD
            StopAddict:
            • no promueve consumo,
            • no incentiva la compra,
            • no fomenta conductas de riesgo.
            
            5. RESPONSABILIDAD DEL USUARIO
            El usuario es responsable:
            • del uso,
            • de sus datos,
            • del cumplimiento legal.
            
            La app no puede usarse como prueba judicial.
            
            6. DATOS Y PRIVACIDAD
            No se recoge ningún dato.  
            No hay cuentas ni envíos a servidores.  
            Todo se almacena en el dispositivo.
            
            Los archivos exportados son responsabilidad del usuario.
            
            7. SEGURIDAD
            La app recuerda la importancia de:
            • salud,
            • sueño,
            • deporte,
            • alimentación,
            • apoyo social.
            
            8. PUBLICIDAD & PREMIUM
            La versión gratuita incluye publicidad.  
            La versión premium la elimina e incluye importación/exportación.
            
            Un cambio de smartphone puede requerir una nueva compra según Google.
            
            No hay reembolsos en caso de:
            • incompatibilidad,
            • bugs,
            • pérdida de datos.
            
            9. GARANTÍAS
            La app se proporciona “tal cual”, sin garantía de funcionamiento perfecto.
            
            10. PROPIEDAD INTELECTUAL
            Todo el contenido está protegido por derechos de autor.
            
            11. ACTUALIZACIONES
            Pueden mejorar o modificar funciones sin garantía de continuidad.
            
            12. CONTACTO
            Los comentarios pueden enviarse vía Google Play.
            
            13. ACEPTACIÓN
            El uso implica la aceptación del presente aviso legal.

            14. ACEPTACIÓN DEL AVISO LEGAL
            El uso de la aplicación implica la plena y total aceptación, sin reservas, del presente aviso legal y de la política de datos.

            ADVERTENCIA IMPORTANTE: Esta aplicación no constituye asesoramiento médico, psicológico ni profesional. La información proporcionada es general, no personalizada y destinada únicamente a un uso personal e informativo. StopAddict no es un dispositivo médico y no sustituye un diagnóstico profesional, tratamiento, terapia ni servicios de salud.

            Al utilizar la aplicación, usted acepta que su uso se realiza bajo su exclusiva responsabilidad. La ley aplicable es exclusivamente la ley francesa. Cualquier disputa deberá presentarse ante los tribunales franceses. Si reside fuera de Francia, es su responsabilidad comprobar que el uso de esta aplicación cumple con las leyes locales de su país.
            
            StopAddict, su creador y sus socios no serán responsables de ningún daño directo o indirecto derivado del uso de esta aplicación, incluidos, entre otros: consecuencias para la salud, decisiones personales, interpretación de datos, reducción o interrupción del consumo o cualquier consecuencia relacionada con sus decisiones personales.
            
            Al continuar utilizando la aplicación, usted reconoce que ha leído, comprendido y aceptado esta advertencia internacional.

            """.trimIndent()

            "PT" -> """
            AVISO LEGAL E POLÍTICA DE DADOS – STOPADDICT
            
            1. EDITOR
            StopAddict foi desenvolvido por um criador independente.  
            Não possui vínculo comercial com indústrias de tabaco, álcool, cannabis, entidades públicas ou médicas.
            
            2. OBJETO
            StopAddict é uma ferramenta pessoal e informativa para acompanhar consumos diários.  
            Não é:
            • dispositivo médico,  
            • diagnóstico,  
            • terapia,  
            • incentivo ao consumo.
            
            3. MAIORIDADE & LEIS LOCAIS
            O app é destinado apenas a maiores conforme:
            • a lei do país de residência,  
            • a lei do país onde o app é utilizado.
            
            O usuário deve respeitar as leis locais relacionadas a tabaco, álcool, cannabis e ferramentas de monitoramento.
            
            4. NEUTRALIDADE
            StopAddict:
            • não promove consumo,  
            • não incentiva compras,  
            • não valoriza comportamentos de risco.
            
            5. RESPONSABILIDADE DO USUÁRIO
            O usuário é responsável:
            • pelo uso,  
            • pelos dados,  
            • pelo cumprimento legal.
            
            Os registros não podem ser usados como prova legal.
            
            6. PRIVACIDADE
            StopAddict não coleta nenhum dado.  
            Não há criação de conta.  
            Nada é enviado a servidores.
            
            Todos os dados ficam no dispositivo. Arquivos exportados são responsabilidade do usuário.
            
            7. SEGURANÇA
            O app lembra a importância de:
            • saúde,  
            • sono,  
            • atividade física,  
            • alimentação,  
            • apoio social.
            
            8. PUBLICIDADE & PREMIUM
            Versão gratuita contém anúncios.  
            Versão premium remove anúncios e ativa importação/exportação.
            
            Troca de celular pode exigir nova compra segundo regras do Google Play.
            
            9. GARANTIAS & LIMITAÇÕES
            O app é fornecido “no estado em que se encontra”, sem garantia de:
            • funcionamento perfeito,  
            • ausência de bugs,  
            • compatibilidade total.
            
            10. PROPRIEDADE INTELECTUAL
            Todo o aplicativo é protegido por direitos autorais.
            
            11. ATUALIZAÇÕES
            Podem alterar funcionalidades sem garantia de continuidade.
            
            12. CONTATO
            Comentários podem ser enviados pela página do desenvolvedor no Google Play.
            
            13. ACEITAÇÃO
            O uso implica aceitar integralmente este aviso legal.

            14. ACEITAÇÃO DOS AVISOS LEGAIS
            A utilização da aplicação implica a aceitação integral e sem reservas do presente aviso legal e da política de dados.

            AVISO IMPORTANTE: Este aplicativo não constitui aconselhamento médico, psicológico ou profissional. As informações fornecidas são gerais, não personalizadas e destinadas apenas ao uso pessoal e informativo. StopAddict não é um dispositivo médico e não substitui diagnóstico profissional, tratamento, terapia ou serviços de saúde.
            
            Ao utilizar o aplicativo, você concorda que o uso é de sua inteira responsabilidade. A lei aplicável é exclusivamente a lei francesa. Qualquer disputa deverá ser levada aos tribunais franceses. Se você reside fora da França, é sua responsabilidade verificar se o uso deste aplicativo está em conformidade com as leis locais de seu país.
            
            StopAddict, seu criador e seus parceiros não poderão ser responsabilizados por quaisquer danos diretos ou indiretos decorrentes do uso deste aplicativo, incluindo, mas não se limitando a: consequências para a saúde, escolhas pessoais, interpretação de dados, redução ou interrupção do consumo ou qualquer consequência relacionada às suas decisões pessoais.
            
            Ao continuar utilizando o aplicativo, você confirma que leu, compreendeu e aceitou este aviso internacional.

            """.trimIndent()

            "DE" -> """
            IMPRESSUM & DATENSCHUTZERKLÄRUNG – STOPADDICT
        
            1. ENTWICKLER
            StopAddict wurde von einem unabhängigen Entwickler erstellt. Keine Verbindung zu Tabak-,
            Alkohol-, Cannabisindustrien oder öffentlichen Organisationen.
        
            2. ZWECK
            StopAddict dient der persönlichen Nachverfolgung des Konsums von Zigaretten, Joints und
            Alkohol. Die Anwendung ist kein medizinisches Tool, keine Diagnosehilfe und keine
            Konsumanregung.
        
            3. VOLLJÄHRIGKEIT
            Die Nutzung ist nur Volljährigen gestattet, basierend auf:
            • dem Recht des Wohnsitzlandes  
            • dem Recht des Landes, in dem sich der Nutzer befindet  
        
            4. NEUTRALITÄT
            StopAddict:
            • macht keine Werbung für Substanzen  
            • ermutigt nicht zum Kauf oder Konsum  
            • fördert gesundheitliche Vorsicht und Reduktion  
        
            5. VERANTWORTUNG DES NUTZERS
            Der Nutzer ist für die Nutzung und die von ihm eingegebenen Daten verantwortlich.
            Die App kann nicht als Beweis in rechtlichen Verfahren dienen.
        
            6. DATENSCHUTZ
            StopAddict sammelt keine Daten, erstellt keine Konten und überträgt keine Informationen an
            Server. Alles bleibt lokal auf dem Gerät gespeichert.
        
            Exportierte Dateien liegen vollständig in der Verantwortung des Nutzers.
        
            7. WERBUNG & PREMIUM
            Die kostenlose Version enthält Werbung. Die Premium-Version entfernt diese und aktiviert
            Import/Export-Funktionen.
        
            8. TECHNISCHE GRENZEN
            Keine Garantie für:
            • perfekte Funktion  
            • vollständige Kompatibilität  
            • Datenverlustfreiheit  
        
            9. URHEBERRECHT
            Alle Inhalte sind urheberrechtlich geschützt. Kopieren, Verändern oder Verbreiten ohne
            Zustimmung ist untersagt.
        
            10. UNABHÄNGIGKEIT
            StopAddict wird unabhängig entwickelt, ohne externe Finanzierung oder institutionellen Einfluss.
        
            11. UPDATES
            Updates können erscheinen, ohne Verpflichtung zur dauerhaften Wartung.
        
            12. KONTAKT
            Vorschläge und Rückmeldungen können über die Entwicklerseite im Google Play Store gesendet
            werden.
        
            13. ANNAHME
            Die Nutzung der App bedeutet die vollständige Anerkennung dieses rechtlichen Hinweises und
            der Datenschutzrichtlinien.

            14. ANNAHME DES RECHTLICHEN HINWEISES
            Die Nutzung der App setzt die vollständige und vorbehaltlose Anerkennung dieses Impressums und der Datenschutzrichtlinie voraus.

            WICHTIGER HINWEIS: Diese Anwendung stellt keinen medizinischen, psychologischen oder professionellen Rat dar. Die bereitgestellten Informationen sind allgemein, nicht personalisiert und ausschließlich für den persönlichen und informativen Gebrauch bestimmt. StopAddict ist kein medizinisches Gerät und ersetzt keine professionelle Diagnose, Behandlung, Therapie oder Gesundheitsdienstleistung.

            Durch die Nutzung der Anwendung erklären Sie sich damit einverstanden, dass die Nutzung ausschließlich auf eigene Verantwortung erfolgt. Es gilt ausschließlich französisches Recht. Streitigkeiten sind vor französischen Gerichten auszutragen. Wenn Sie außerhalb Frankreichs leben, liegt es in Ihrer Verantwortung zu prüfen, ob die Nutzung dieser Anwendung mit den lokalen Gesetzen Ihres Landes übereinstimmt.
            
            StopAddict, der Entwickler und seine Partner übernehmen keinerlei Haftung für direkte oder indirekte Schäden, die durch die Nutzung dieser Anwendung entstehen, einschließlich, aber nicht beschränkt auf: gesundheitliche Folgen, persönliche Entscheidungen, Dateninterpretation, Reduzierung oder Beendigung des Konsums oder andere Folgen, die sich aus Ihren persönlichen Entscheidungen ergeben.
            
            Durch die weitere Nutzung der Anwendung bestätigen Sie, dass Sie diesen internationalen Hinweis gelesen, verstanden und akzeptiert haben.

        """.trimIndent()

            "IT" -> """
            NOTE LEGALI E POLITICA DEI DATI – STOPADDICT
        
            1. SVILUPPATORE
            StopAddict è sviluppata da un autore indipendente, senza legami con industrie del tabacco,
            dell’alcol o della cannabis, né con istituzioni pubbliche.
        
            2. SCOPO DELL’APPLICAZIONE
            StopAddict è uno strumento informativo per monitorare il consumo personale. Non è:
            • un dispositivo medico  
            • un aiuto terapeutico  
            • uno strumento diagnostico  
            • un incentivo alla consumazione  
        
            3. MAGGIORE ETÀ
            L’app è destinata esclusivamente a persone maggiorenni secondo:
            • la legge del Paese di residenza  
            • la legge del Paese in cui ci si trova fisicamente  
        
            4. NEUTRALITÀ
            L’app non promuove né incoraggia l’uso di alcuna sostanza.
        
            5. RESPONSABILITÀ DELL’UTENTE
            L’utente è pienamente responsabile dell’uso dell’app e dei dati inseriti.
        
            6. DATI E PRIVACY
            Nessuna raccolta di dati, nessun server, nessun account. Tutte le informazioni restano sul
            dispositivo dell’utente.
        
            I file esportati sono sotto la sua esclusiva responsabilità.
        
            7. PUBBLICITÀ E PREMIUM
            La versione gratuita contiene pubblicità.
            La versione premium le rimuove e abilita import/export.
        
            8. LIMITI TECNICI
            Nessuna garanzia di:
            • funzionamento perfetto  
            • compatibilità totale  
            • assenza di bug o perdite di dati  
        
            9. PROPRIETÀ INTELLETTUALE
            Tutti i contenuti sono protetti dal diritto d’autore.
        
            10. INDIPENDENZA
            Lo sviluppo è completamente autonomo e senza influenze esterne.
        
            11. AGGIORNAMENTI
            Possono essere forniti aggiornamenti senza garanzia di continuità.
        
            12. CONTATTO
            Suggerimenti e commenti possono essere inviati tramite la pagina dello sviluppatore
            sul Google Play Store.
        
            13. ACCETTAZIONE
            L’utilizzo dell’app implica l’accettazione totale delle presenti note legali.

            14. ACCETTAZIONE DELLE NOTE LEGALI
            L’uso dell’applicazione implica l’accettazione piena e senza riserve delle presenti note legali e della politica sui dati.

            AVVISO IMPORTANTE: Questa applicazione non costituisce consulenza medica, psicologica o professionale. Le informazioni fornite sono generali, non personalizzate e destinate esclusivamente a un uso personale e informativo. StopAddict non è un dispositivo medico e non sostituisce diagnosi, trattamenti, terapie o servizi sanitari professionali.

            Utilizzando l’applicazione, accetti che il suo utilizzo avvenga sotto la tua esclusiva responsabilità. La legge applicabile è esclusivamente quella francese. Qualsiasi controversia deve essere sottoposta ai tribunali francesi. Se risiedi fuori dalla Francia, è tua responsabilità verificare la conformità dell’uso dell’app alle leggi locali del tuo paese.
            
            StopAddict, il suo creatore e i suoi partner non potranno essere ritenuti responsabili per danni diretti o indiretti derivanti dall’uso dell’applicazione, inclusi, ma non limitati a: conseguenze sulla salute, scelte personali, interpretazione dei dati, riduzione o cessazione del consumo o qualsiasi conseguenza derivante dalle tue decisioni personali.
            
            Continuando a utilizzare l’app, dichiari di aver letto, compreso e accettato questo avviso internazionale.

        """.trimIndent()

                "RU" -> """
                ЮРИДИЧЕСКАЯ ИНФОРМАЦИЯ И ПОЛИТИКА ДАННЫХ – STOPADDICT
            
                1. РАЗРАБОТЧИК
                StopAddict создан независимым разработчиком и не связан с компаниями табачной,
                алкогольной или конопляной индустрии, а также с государственными учреждениями.
            
                2. НАЗНАЧЕНИЕ ПРИЛОЖЕНИЯ
                StopAddict – личный инструмент для мониторинга потребления. Это не:
                • медицинское средство  
                • терапия  
                • диагностика  
                • стимул к употреблению  
            
                3. ТРЕБОВАНИЕ О СОВЕРШЕННОЛЕТИИ
                Использование разрешено только совершеннолетним согласно:
                • закону страны проживания  
                • закону страны пребывания  
            
                4. НЕЙТРАЛЬНОСТЬ
                Приложение не поощряет и не пропагандирует употребление веществ.
            
                5. ОТВЕТСТВЕННОСТЬ
                Пользователь несёт полную ответственность за свои действия и введённые данные.
            
                6. КОНФИДЕНЦИАЛЬНОСТЬ
                Никакие данные не собираются, не передаются и не анализируются.
                Все данные хранятся только на устройстве пользователя.
            
                7. PREMIUM И РЕКЛАМА
                Бесплатная версия содержит рекламу.
                Премиум-версия убирает рекламу и включает импорт / экспорт.
            
                8. ТЕХНИЧЕСКИЕ ОГРАНИЧЕНИЯ
                Нет гарантии:
                • полной совместимости  
                • отсутствия ошибок  
                • сохранности данных  
            
                9. АВТОРСКИЕ ПРАВА
                Все тексты, изображения и функции защищены законом об авторском праве.
            
                10. НЕЗАВИСИМОСТЬ
                Разработчик работает автономно, без внешних влияний.
            
                11. ОБНОВЛЕНИЯ
                Возможны обновления без гарантии постоянной поддержки.
            
                12. ОБРАТНАЯ СВЯЗЬ
                Отзывы можно оставить через страницу разработчика в Google Play Store.
            
                13. ПРИНЯТИЕ
                Использование приложения означает полное принятие данных юридических условий.

                14. ПРИНЯТИЕ ПРАВОВОЙ ИНФОРМАЦИИ
                Использование приложения означает полное и безоговорочное принятие настоящих юридических условий и политики обработки данных.

                ВАЖНОЕ ПРЕДУПРЕЖДЕНИЕ: Это приложение не является медицинской, психологической или профессиональной консультацией. Предоставляемая информация носит общий характер, не является персонализированной и предназначена исключительно для личного и информационного использования. StopAddict не является медицинским устройством и не заменяет профессиональную диагностику, лечение, терапию или медицинские услуги.

                Используя приложение, вы соглашаетесь, что делаете это полностью на свой страх и риск. Применяется исключительно французское законодательство. Любые споры подлежат рассмотрению во французских судах. Если вы проживаете за пределами Франции, вы несёте ответственность за соблюдение местных законов вашей страны.
                
                StopAddict, его создатель и партнёры не несут ответственности за любой прямой или косвенный ущерб, возникший в результате использования приложения, включая, но не ограничиваясь: последствиями для здоровья, личными решениями, интерпретацией данных, сокращением или прекращением потребления, а также любыми другими результатами ваших личных действий.
                
                Продолжая использование приложения, вы подтверждаете, что прочитали, поняли и приняли это международное предупреждение.

            """.trimIndent()

                "AR" -> """
                الشروط القانونية وسياسة البيانات – StopAddict
                
                1) المطوّر
                StopAddict هو تطبيق أنشأه مطوّر مستقل، دون أي صلة مع صناعات السجائر أو الكحول
                أو الحشيش أو أي جهة حكومية أو طبية.
                
                2) هدف التطبيق
                StopAddict هو أداة شخصية لمتابعة الاستهلاك، وليس:
                • أداة طبية  
                • تشخيصاً  
                • علاجاً  
                • تشجيعاً على الاستهلاك  
                
                3) شرط السن القانوني
                الاستخدام مخصّص فقط للبالغين وفق:
                • قانون بلد الإقامة  
                • وقانون البلد الذي يتواجد فيه المستخدم  
                
                4) الحياد وعدم التحفيز
                لا يشجع التطبيق على الاستهلاك ولا يروّج للمواد الضارة.
                
                5) مسؤولية المستخدم
                المستخدم يتحمل كامل المسؤولية عن البيانات والسلوك والاستخدام.
                
                6) الخصوصية
                لا يجمع التطبيق أي بيانات ولا يرسل أي معلومة إلى الإنترنت.
                كل البيانات محلية على جهاز المستخدم.
                
                7) الإعلانات والنسخة المميزة
                النسخة المجانية تحتوي على إعلانات.
                النسخة المميزة تحذف الإعلانات وتفتح التصدير/الاستيراد.
                
                8) القيود التقنية
                لا ضمان لعمل التطبيق دون أخطاء أو فقدان بيانات أو توافق كامل.
                
                9) حقوق النشر
                كل الصور والنصوص والمحتويات محمية بالقانون.
                
                10) الاستقلالية
                التطبيق مستقل تماماً وغير تابع لأي جهة خارجية.
                
                11) التحديثات
                قد يتم إصدار تحديثات دون التزام بضمان الاستمرارية.
                
                12) التواصل
                يمكن إرسال الملاحظات عبر صفحة المطوّر في متجر Google Play.
                
                13) القبول
                استخدام التطبيق يعني الموافقة الكاملة على هذه الشروط القانونية.

                14. القبول بالإشعارات القانونية
                إن استخدام هذا التطبيق يعني قبولًا كاملاً وبدون أي تحفظ لهذه الإشعارات القانونية ولسياسة البيانات.

                تحذير مهم: هذا التطبيق لا يقدّم أي استشارة طبية أو نفسية أو مهنية. المعلومات المتوفرة عامة وغير مخصصة، ومقدمة للاستخدام الشخصي والمعلوماتي فقط. StopAddict ليس جهازًا طبيًا ولا يبدل التشخيص أو العلاج أو المتابعة المقدمة من المتخصصين في الرعاية الصحية.

                باستخدامك للتطبيق، فإنك تقر بأن الاستخدام يتم على مسؤوليتك بالكامل. القانون المعمول به هو القانون الفرنسي فقط، وأي نزاع يجب رفعه أمام المحاكم الفرنسية. إذا كنت تقيم خارج فرنسا، فإن مسؤولية التحقق من توافق استخدام التطبيق مع قوانين بلدك تقع عليك وحدك.
                
                لا يتحمل StopAddict أو منشئه أو شركاؤه أي مسؤولية عن أي ضرر مباشر أو غير مباشر ناتج عن استخدام التطبيق، بما في ذلك — على سبيل المثال لا الحصر — الآثار الصحية، القرارات الشخصية، تفسير البيانات، التقليل أو التوقف عن الاستهلاك، أو أي نتيجة ناتجة عن اختياراتك الشخصية.
                
                باستمرارك في استخدام التطبيق، فإنك تقر بأنك قرأت هذا التحذير الدولي وفهمته ووافقت عليه.

                """.trimIndent()

                "HI" -> """
                कानूनी उल्लेख और डेटा नीति – StopAddict
                
                1) डेवलपर
                StopAddict एक स्वतंत्र डेवलपर द्वारा बनाया गया ऐप है।
                इसका किसी तंबाकू, शराब, कैनाबिस, सरकारी संस्था या चिकित्सा संगठन से कोई संबंध नहीं है।
                
                2) ऐप का उद्देश्य
                StopAddict एक व्यक्तिगत ट्रैकिंग उपकरण है।
                यह नहीं है:
                • चिकित्सा उपकरण  
                • निदान उपकरण  
                • उपचार  
                • खपत को प्रोत्साहन  
                
                3) कानूनी आयु
                ऐप केवल उन उपयोगकर्ताओं के लिए है जो:
                • अपने देश में वयस्क हैं  
                • और जहाँ वे ऐप का उपयोग करते हैं वहाँ की स्थानीय कानूनी आयु का पालन करते हैं  
                
                4) तटस्थता
                ऐप खपत को बढ़ावा नहीं देता और न ही किसी जोखिमपूर्ण व्यवहार को प्रोत्साहित करता है।
                
                5) उपयोगकर्ता की जिम्मेदारी
                उपयोगकर्ता अपने डेटा और व्यवहार का पूरी तरह से जिम्मेदार है।
                
                6) गोपनीयता
                कोई डेटा एकत्र नहीं किया जाता और न ही सर्वर पर भेजा जाता है।
                सारी जानकारी डिवाइस पर ही रहती है।
                
                7) विज्ञापन और प्रीमियम
                मुफ़्त संस्करण में विज्ञापन हैं।
                प्रीमियम संस्करण विज्ञापन हटाता है और निर्यात/आयात सक्रिय करता है।
                
                8) तकनीकी सीमाएँ
                ऐप "जैसा है" आधार पर प्रदान किया जाता है — बिना किसी गारंटी के।
                
                9) कॉपीराइट
                सभी सामग्री (टेक्स्ट, चित्र, संरचना, कोड) कानून द्वारा संरक्षित है।
                
                10) स्वतंत्रता
                ऐप किसी बाहरी संगठन से संबद्ध नहीं है।
                
                11) अपडेट
                अद्यतन जारी हो सकते हैं बिना निरंतर समर्थन की गारंटी के।
                
                12) संपर्क
                Google Play Store की डेवलपर पेज के माध्यम से सुझाव भेजे जा सकते हैं।
                
                13) स्वीकृति
                ऐप का उपयोग इन कानूनी शर्तों की पूर्ण स्वीकृति है।

                14. कानूनी शर्तों की स्वीकृति
                एप्लिकेशन का उपयोग करने का अर्थ है कि आप इन कानूनी शर्तों और डेटा नीति को पूर्ण रूप से और बिना किसी आरक्षण के स्वीकार करते हैं।

                महत्वपूर्ण चेतावनी: यह ऐप किसी भी प्रकार की चिकित्सीय, मनोवैज्ञानिक या पेशेवर सलाह प्रदान नहीं करता है। दी गई जानकारी सामान्य है, व्यक्तिगत नहीं है, और केवल व्यक्तिगत और सूचनात्मक उपयोग के लिए है। StopAddict कोई चिकित्सा उपकरण नहीं है और यह पेशेवर निदान, उपचार, थेरेपी या स्वास्थ्य सेवा का विकल्प नहीं है।

                इस ऐप का उपयोग करके, आप सहमत होते हैं कि उपयोग पूरी तरह से आपकी अपनी ज़िम्मेदारी पर है। लागू कानून केवल फ्रांसीसी कानून है। किसी भी विवाद का समाधान केवल फ्रांसीसी अदालतों में किया जाएगा। यदि आप फ्रांस के बाहर रहते हैं, तो अपने देश के स्थानीय कानूनों का अनुपालन सुनिश्चित करना आपकी स्वयं की ज़िम्मेदारी है।
                
                StopAddict, इसके निर्माता और साझेदार ऐप के उपयोग से होने वाले किसी भी प्रत्यक्ष या अप्रत्यक्ष नुकसान के लिए ज़िम्मेदार नहीं होंगे, जिनमें शामिल है — लेकिन सीमित नहीं — स्वास्थ्य परिणाम, व्यक्तिगत निर्णय, डेटा की व्याख्या, उपभोग में कमी या बंद होना, तथा आपकी व्यक्तिगत पसंद से उत्पन्न कोई भी परिणाम।
                
                ऐप का उपयोग जारी रखते हुए, आप पुष्टि करते हैं कि आपने इस अंतरराष्ट्रीय चेतावनी को पढ़ा, समझा और स्वीकार किया है।

                """.trimIndent()

                "JA" -> """
                法的通知およびデータポリシー – StopAddict
                
                1) 開発者
                StopAddict は独立系開発者によって作成されたアプリです。
                タバコ、アルコール、大麻産業、政府機関、医療機関などとの関係はありません。
                
                2) アプリの目的
                StopAddict は喫煙・ジョイント・アルコールの消費量を個人的に記録するための
                ツールであり、以下には該当しません：
                • 医療機器  
                • 診断ツール  
                • 治療サービス  
                • 消費の推奨  
                
                3) 年齢制限
                本アプリは、利用者の：
                • 居住国の成年年齢  
                • 利用場所の現地法  
                の双方で成年である場合にのみ使用できます。
                
                4) 中立性
                StopAddict は消費を推奨せず、危険行動を促しません。
                
                5) 利用者の責任
                アプリの利用、入力データ、行動はすべて利用者本人の責任です。
                
                6) データ保護とプライバシー
                StopAddict は：
                • 個人情報を収集しない  
                • サーバーに送信しない  
                • アカウントを作成させない  
                
                すべてのデータは端末にのみ保存されます。
                
                7) 広告とプレミアム版
                無料版：広告あり  
                プレミアム版：広告なし、エクスポート／インポート利用可能  
                
                新しい端末で再ダウンロードする際、Google の規定により再購入が必要な
                場合があります。
                
                8) 技術的制限
                アプリは「現状のまま」提供され、完全な動作保証はありません。
                
                9) 著作権
                アプリの全内容は著作権により保護されています。
                
                10) 独立性
                いかなる企業・政府機関・医療組織とも提携していません。
                
                11) 技術的背景
                アプリは開発者個人の作業を中心に、文章構成等に AI ツールを補助的に使用
                して制作されました。
                
                12) 連絡
                Google Play Store の開発者ページから意見を送信できます。
                
                13) 同意
                アプリを利用することで、本法的通知への同意を示します。

                14. 法的通知の承諾
                本アプリを利用することにより、お客様は本法的通知およびデータポリシーの内容を条件なしに完全に受け入れたものとみなされます。

                重要な注意事項：本アプリは医療的・心理的・専門的アドバイスを提供するものではありません。提供される情報は一般的なものであり、個別の状況に基づくものではなく、個人的・情報提供目的のみを対象としています。StopAddict は医療機器ではなく、専門的な診断、治療、療法、または医療サービスの代替にはなりません。
                
                本アプリを使用することで、利用者はすべて自己責任で利用することに同意したものとみなされます。適用される法律はフランス法のみとし、すべての紛争はフランスの裁判所で解決されます。フランス国外に居住している場合、本アプリの使用が居住国の法律に適合しているかどうかを確認する責任は利用者にあります。
                
                StopAddict、その開発者およびパートナーは、本アプリの使用によって生じた直接的または間接的な損害について、一切の責任を負いません。これには、健康への影響、個人的判断、データ解釈、消費の減少または中止、その他あなたの個人的決定に関連する結果が含まれますが、これらに限定されません。
                
                アプリの利用を継続することで、あなたはこの国際的注意事項を読み、理解し、同意したものとみなされます。

                """.trimIndent()

                "NL" -> """
            JURIDISCHE VERMELDINGEN & GEGEVENSBELEID – STOPADDICT
            
            1. UITGEVER VAN DE APPLICATIE
            StopAddict is een applicatie ontwikkeld door:
            Ontwikkeling: StopAddict – Onafhankelijke ontwikkelaar.
            Er bestaat geen institutionele, commerciële of juridische band met de tabaksindustrie, alcoholindustrie, cannabisindustrie, een overheidsinstantie, een regering, een medische instelling, een industrie of een kartel.
            
            2. DOEL VAN DE APPLICATIE
            StopAddict is een speels, informatief en persoonlijk hulpmiddel bedoeld om gebruikers te helpen hun dagelijkse consumptie van sigaretten, joints en alcohol te volgen.
            De applicatie is geen:
            • medisch hulpmiddel,
            • diagnostisch instrument,
            • therapeutische ondersteuning,
            • gezondheidsdienst,
            • aansporing tot consumptie,
            • aanmoediging om gereguleerde stoffen te kopen of te gebruiken.
            
            Het gebruik is strikt persoonlijk en vrijwillig.
            
            3. MEERDERJARIGHEID & INTERNATIONALE WETGEVING
            De applicatie is bedoeld voor meerderjarige personen volgens:
            • de wetgeving van het land van verblijf,
            • en de wetgeving van het land waar de gebruiker zich fysiek bevindt tijdens het gebruik.
            
            De regels omtrent meerderjarigheid kunnen verschillen (bijvoorbeeld alcohol → verschillend tussen Frankrijk en de Verenigde Staten).
            De gebruiker dient zelf de toepasselijke wetgeving te controleren met betrekking tot:
            • tabak,
            • alcohol,
            • cannabis en afgeleiden,
            • het gebruik van persoonlijke volgtoepassingen.
            
            De uitgever kan niet aansprakelijk worden gesteld voor illegaal gebruik in een land of voor onwetendheid van lokale wetten.
            
            4. NEUTRALITEIT & GEEN AANMOEDIGING
            StopAddict:
            • promoot geen sigaretten, alcohol of joints,
            • verheerlijkt geen consumptie,
            • moedigt geen aankoop van stoffen aan,
            • suggereert geen risicovol gedrag.
            
            Het uiteindelijke doel is vermindering, stoppen of volledige afwezigheid van consumptie.
            
            5. VERANTWOORDELIJKHEID VAN DE GEBRUIKER
            De gebruiker is volledig verantwoordelijk voor:
            • het gebruik van de applicatie,
            • de opgeslagen gegevens,
            • de ingevoerde instellingen,
            • zijn of haar persoonlijk gedrag,
            • de naleving van lokale wetgeving.
            
            De applicatie kan in geen geval dienen als juridisch bewijs.
            Interne registraties kunnen niet worden gebruikt om de gebruiker of de ontwikkelaar te incrimineren.
            
            In geval van misbruik ligt de volledige verantwoordelijkheid bij de gebruiker.
            
            6. GEGEVENSBESCHERMING & PRIVACY
            StopAddict verzamelt geen persoonlijke gegevens.
            Er is geen account vereist.
            Er worden geen gegevens naar externe servers verzonden.
            Er vindt geen tracking, analyse, profilering of gerichte reclame plaats.
            
            Alle gegevens blijven uitsluitend opgeslagen op het apparaat van de gebruiker.
            Eventueel geëxporteerde bestanden (premiumversie) vallen onder de volledige verantwoordelijkheid van de gebruiker.
            
            Het wordt aanbevolen om:
            • de toegang tot het toestel te beveiligen,
            • geëxporteerde bestanden te beschermen,
            • blootstelling van gevoelige gegevens te vermijden,
            • een veilige digitale omgeving te behouden.
            
            7. VEILIGHEID, PREVENTIE & WAARSCHUWING
            De applicatie raadt aan het gebruik van digitale apparaten door kinderen en adolescenten te controleren.
            Ze benadrukt het belang van:
            • gezondheid,
            • slaap,
            • lichaamsbeweging,
            • voeding,
            • sociale en familiale ondersteuning.
            
            StopAddict vervangt deze goede praktijken niet.
            
            8. ADVERTENTIES & PREMIUMVERSIE
            De gratis versie bevat een advertentiebanner ter ondersteuning van de ontwikkeling.
            De premiumversie verwijdert alle advertenties en activeert import- en exportfuncties.
            
            De premiumversie is een eenmalige aankoop en valt onder de regels van de Google Play Store.
            Installatie op een nieuw apparaat kan een nieuwe aankoop vereisen volgens het beleid van Google.
            
            Er kan geen terugbetaling worden verleend door de ontwikkelaar, zelfs niet in geval van:
            • incompatibiliteit,
            • hardware- of softwarestoringen,
            • gegevensverlies,
            • bugs of anomalieën,
            • verandering van smartphone.
            
            9. GARANTIES & TECHNISCHE BEPERKINGEN
            De applicatie wordt geleverd “zoals ze is”.
            Er wordt geen garantie gegeven voor:
            • perfecte werking,
            • afwezigheid van bugs,
            • volledige compatibiliteit,
            • continuïteit van de dienst,
            • regelmatige updates.
            
            De ontwikkelaar kan niet aansprakelijk worden gesteld voor:
            • gegevensverlies,
            • incompatibiliteit,
            • systeemproblemen van het toestel,
            • corruptie van een exportbestand,
            • stopzetting van verdere ontwikkeling.
            
            10. INTELLECTUEEL EIGENDOM
            De volledige applicatie (code, teksten, afbeeldingen, functies, structuur en design) is beschermd door het auteursrecht.
            Elke ongeoorloofde reproductie, wijziging of verspreiding is verboden.
            
            11. ONAFHANKELIJKHEID & TRANSPARANTIE
            StopAddict is ontworpen door een onafhankelijke ontwikkelaar zonder commerciële of institutionele invloed.
            De applicatie heeft geen banden met:
            • de staat,
            • tabaksindustrieën,
            • alcoholindustrieën,
            • de cannabissector,
            • criminele organisaties,
            • medische instellingen of verenigingen.
            
            12. TECHNISCHE OORSPRONG & CREATIE
            De applicatie werd ontwikkeld door persoonlijk werk, aangevuld met het gebruik van softwarehulpmiddelen, waaronder kunstmatige intelligentie ter ondersteuning van tekst- en codestructuur.
            De ontwikkelaar blijft volledig verantwoordelijk voor het eindproduct.
            
            13. CONTACT & ONDERSTEUNING
            Aangezien de applicatie door een onafhankelijke ontwikkelaar wordt gemaakt, is er geen continue ondersteuning gegarandeerd.
            Er wordt geen professionele klantenservice aangeboden.
            Feedback kan worden verzonden via de ontwikkelaarspagina op Google Play Store.
            
            14. AANVAARDING
            Het gebruik van de applicatie impliceert volledige en onvoorwaardelijke aanvaarding van deze juridische vermeldingen en het gegevensbeleid.
            
            BELANGRIJKE WAARSCHUWING: Deze applicatie vormt geen medisch, psychologisch of professioneel advies. Alle informatie is algemeen en uitsluitend bedoeld voor persoonlijk en informatief gebruik.
            
            Door de applicatie te gebruiken, erkent de gebruiker dat hij of zij dit onder eigen verantwoordelijkheid doet. Het toepasselijk recht is uitsluitend het Franse recht. Elk geschil valt onder de bevoegdheid van de Franse rechtbanken.
            """.trimIndent()

            "ZH" -> """
            法律声明与数据政策 – STOPADDICT
            
            1. 应用开发者
            StopAddict 由独立开发者开发。
            与烟草、酒精、大麻行业、政府机构、医疗机构或任何组织无任何商业或法律关系。
            
            2. 应用目的
            StopAddict 是一款用于记录香烟、大麻和酒精日常使用情况的个人信息工具。
            本应用不是：
            • 医疗设备，
            • 诊断工具，
            • 治疗服务，
            • 健康服务，
            • 消费鼓励。
            
            使用完全出于个人自愿。
            
            3. 法定年龄与国际法规
            本应用仅适用于符合所在地法律规定的成年人。
            用户有责任遵守当地关于烟草、酒精和大麻的法律。
            
            4. 中立性
            StopAddict 不推广、不鼓励任何形式的消费行为。
            
            5. 用户责任
            用户对应用的使用及其数据承担全部责任。
            应用数据不得作为法律证据。
            
            6. 数据保护与隐私
            不收集、不传输任何个人数据。
            所有数据仅存储在用户设备中。
            
            7. 广告与高级版
            免费版包含广告。
            高级版移除广告并启用导入导出功能。
            
            8. 技术限制
            应用按“现状”提供，不保证无错误或持续更新。
            
            9. 知识产权
            所有内容均受版权保护。
            
            10. 接受
            使用 StopAddict 即表示你接受本法律声明。
            """.trimIndent()

            "ZHT" -> """
            法律聲明與資料政策 – STOPADDICT
            
            1. 應用開發者
            StopAddict 由獨立開發者開發。
            與菸酒、大麻產業、政府或醫療機構無任何商業或法律關係。
            
            2. 應用目的
            StopAddict 為個人資訊工具，用於追蹤香菸、大麻與酒精的每日使用情況。
            本應用不是：
            • 醫療設備，
            • 診斷工具，
            • 治療服務，
            • 健康服務，
            • 消費鼓勵。
            
            使用完全出於個人自願。
            
            3. 法定年齡與國際法規
            本應用僅限符合所在地法律規定之成年人使用。
            使用者須自行確認並遵守當地法律。
            
            4. 中立性
            StopAddict 不鼓勵或推廣任何消費行為。
            
            5. 使用者責任
            使用者須自行負責應用使用及資料管理。
            資料不得作為法律證據。
            
            6. 資料保護與隱私
            不收集、不傳送任何個人資料。
            所有資料僅儲存在使用者裝置中。
            
            7. 廣告與進階版
            免費版包含廣告。
            進階版移除廣告並提供匯入匯出功能。
            
            8. 技術限制
            應用以「現狀」提供，不保證無錯誤或持續更新。
            
            9. 智慧財產權
            所有內容皆受著作權保護。
            
            10. 接受
            使用 StopAddict 即表示你接受本法律聲明。
            """.trimIndent()

            else -> getMentionsLegales("FR") // Fallback FR
        }
    }
}
