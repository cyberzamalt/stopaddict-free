package com.stopaddict

import android.util.Log

object ReglagesLangues {

    private const val TAG = "ReglagesLangues"

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
        "titre" to "Réglages",
        "titre_profil" to "Profil",
        "titre_categories" to "Catégories",
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
        "btn_sauvegarder_profil" to "Sauvegarder profil",

        // Labels catégories
        "label_cigarettes" to "Cigarettes",
        "label_joints" to "Joints",
        "label_alcool_global" to "Alcool global",
        "label_bieres" to "Bières",
        "label_liqueurs" to "Liqueurs",
        "label_alcool_fort" to "Alcool fort",

        // Labels formulaires cigarettes
        "radio_classiques" to "Cigarettes classiques",
        "radio_rouler" to "À rouler",
        "radio_tubeuse" to "Tubeuse",
        "label_prix_paquet" to "Prix du paquet",
        "label_nb_cigarettes" to "Nombre de cigarettes",
        "label_prix_tabac" to "Prix tabac",
        "label_prix_feuilles" to "Prix feuilles",
        "label_nb_feuilles" to "Nombre de feuilles",
        "label_prix_filtres" to "Prix filtres",
        "label_nb_filtres" to "Nombre de filtres",
        "label_prix_tubes" to "Prix tubes",
        "label_nb_tubes" to "Nombre de tubes",

        // Labels formulaires joints
        "label_prix_gramme" to "Prix du gramme",
        "label_gramme_par_joint" to "Grammes par joint",

        // Labels formulaires alcool
        "label_prix_verre" to "Prix du verre",
        "label_unite_cl" to "Unité (cL)",

        // Labels habitudes
        "label_max_journalier" to "Maximum journalier",

        // Labels dates
        "label_date_reduction" to "Date réduction",
        "label_date_arret" to "Date arrêt",
        "label_date_reussite" to "Date réussite",
        "btn_choisir_date" to "Choisir date",

        "msg_export_limite" to "Pour accéder à l'exportation, passez à la version sans publicité pour en profiter :-)",
        "msg_import_limite" to "Limite atteinte. %d import(s) restant(s) aujourd'hui.",
    
        
        // Boutons RAZ
        "btn_raz_jour" to "RAZ du jour",
        "btn_raz_historique" to "RAZ historique",
        "btn_raz_usine" to "RAZ d'usine",

        // Boutons Export/Import
        "btn_exporter" to "Exporter",
        "btn_importer" to "Importer",
        "btn_export_logs" to "Exporter les logs",

        // Messages confirmations
        "confirm_raz_jour_titre" to "RAZ du jour",
        "confirm_raz_jour_message" to "Supprimer toutes les consommations d'aujourd'hui ?",
        "confirm_raz_historique_titre" to "RAZ de l'historique",
        "confirm_raz_historique_message" to "Supprimer TOUT l'historique de consommation ? (Cette action est irréversible)",
        "confirm_raz_usine_titre" to "RAZ d'usine",
        "confirm_raz_usine_message" to "TOUT réinitialiser (profil, consommations, paramètres) ? (Cette action est irréversible)",
        "confirm_import_titre" to "Importer sauvegarde",
        "confirm_import_message" to "Toutes les données actuelles seront remplacées. Continuer ?",

        // Messages succès/erreur
        "msg_profil_sauvegarde" to "Profil sauvegardé",
        "msg_date_enregistree" to "Date enregistrée",
        "msg_raz_effectuee" to "RAZ effectuée",
        "msg_export_reussi" to "Export réussi",
        "msg_import_reussi" to "Import réussi",
        "msg_erreur_sauvegarde" to "Erreur sauvegarde",
        "msg_erreur_date" to "Erreur sauvegarde date",
        "msg_erreur_raz" to "Erreur RAZ",
        "msg_erreur_export" to "Erreur export",
        "msg_erreur_import" to "Erreur import",
        "msg_limite_export" to "Limite atteinte: %d export(s) restant(s) aujourd'hui (version gratuite)",
        "msg_limite_import" to "Limite atteinte: %d import(s) restant(s) aujourd'hui (version gratuite)",
        "msg_aucune_sauvegarde" to "Aucun fichier de sauvegarde trouvé",
        "msg_donnees_invalides" to "Erreur import: données invalides",
        "msg_champs_obligatoires" to "Veuillez renseigner au moins un coût",

        // Bandeau profil
        "profil_complet" to "Profil: Complet ✓",
        "profil_incomplet" to "Profil: Incomplet",
        "total_aujourdhui" to "Total aujourd'hui:",

        // Économies potentielles
        "titre_economies" to "Économies potentielles",
        "economies_si_arret" to "Si vous arrêtiez complètement:",
        "economies_jour" to "Jour",
        "economies_semaine" to "Semaine",
        "economies_mois" to "Mois",
        "economies_annee" to "Année",

        // Boutons dialog
        "btn_confirmer" to "Confirmer",
        "btn_annuler" to "Annuler",
        "btn_ok" to "OK",

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
        "btn_premium" to "Version sans publicité",
        "premium_titre" to "Version sans publicité",
        "premium_contenu" to "La version sans publicité sera bientôt disponible.\n\nElle supprimera les bandeaux pubs et les limitations.",
        "hint_prenom" to "Entrer votre prénom",

        // RAZ
        "raz_sauvegarde" to "RAZ et Sauvegarde",
        "raz_jour" to "RAZ jour",
        "raz_historique" to "RAZ historique",
        "raz_usine" to "RAZ réglages usine",
        "raz_jour_ok" to "RAZ jour effectué",
        "raz_historique_ok" to "RAZ historique effectué",
        "raz_usine_ok" to "RAZ usine effectué",
            
        // Unités en cL pour les différents alcools
        "unite_cl_global"      to "Unité en cL",
        "unite_cl_biere"       to "Unité en cL",
        "unite_cl_liqueur"     to "Unité en cL",
        "unite_cl_alcool_fort" to "Unité en cL",

        "label_prix_feuilles_longues" to "Prix des feuilles longues",
        "label_nb_feuilles_longues" to "Nombre de feuilles longues"
    )

    // ==================== ENGLISH ====================
    private val TRADUCTIONS_EN = mapOf(
        // Titres sections
        "titre" to "Settings",
        "titre_profil" to "Profile",
        "titre_categories" to "Active categories",
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
        "btn_sauvegarder_profil" to "Save profile",

        // Labels catégories
        "label_cigarettes" to "Cigarettes",
        "label_joints" to "Joints",
        "label_alcool_global" to "Alcohol overall",
        "label_bieres" to "Beers",
        "label_liqueurs" to "Liquors",
        "label_alcool_fort" to "Spirits",

        // Labels formulaires cigarettes
        "radio_classiques" to "Regular cigarettes",
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

        // Labels formulaires joints
        "label_prix_gramme" to "Price per gram",
        "label_gramme_par_joint" to "Grams per joint",

        // Labels formulaires alcool
        "label_prix_verre" to "Drink price",
        "label_unite_cl" to "Unit (cL)",

        // Labels habitudes
        "label_max_journalier" to "Daily maximum",

        // Labels dates
        "label_date_reduction" to "Reduction date",
        "label_date_arret" to "Quit date",
        "label_date_reussite" to "Success date",
        "btn_choisir_date" to "Choose date",

        "msg_export_limite" to "To access export, switch to the ad-free version to enjoy it :-)",
                "msg_import_limite" to "Limit reached. %d import(s) remaining today.",

        // Boutons RAZ
        "btn_raz_jour" to "Reset today",
        "btn_raz_historique" to "Reset history",
        "btn_raz_usine" to "Factory reset",

        // Boutons Export/Import
        "btn_exporter" to "Export",
        "btn_importer" to "Import",
        "btn_export_logs" to "Export logs",

        // Messages confirmations
        "confirm_raz_jour_titre" to "Reset today",
        "confirm_raz_jour_message" to "Delete all today's consumption?",
        "confirm_raz_historique_titre" to "Reset history",
        "confirm_raz_historique_message" to "Delete ALL consumption history? (This action is irreversible)",
        "confirm_raz_usine_titre" to "Factory reset",
        "confirm_raz_usine_message" to "Reset EVERYTHING (profile, consumption, settings)? (This action is irreversible)",
        "confirm_import_titre" to "Import backup",
        "confirm_import_message" to "All current data will be replaced. Continue?",

        // Messages succès/erreur
        "msg_profil_sauvegarde" to "Profile saved",
        "msg_date_enregistree" to "Date saved",
        "msg_raz_effectuee" to "Reset completed",
        "msg_export_reussi" to "Export successful",
        "msg_import_reussi" to "Import successful",
        "msg_erreur_sauvegarde" to "Save error",
        "msg_erreur_date" to "Date save error",
        "msg_erreur_raz" to "Reset error",
        "msg_erreur_export" to "Export error",
        "msg_erreur_import" to "Import error",
        "msg_limite_export" to "Limit reached: %d export(s) remaining today (free version)",
        "msg_limite_import" to "Limit reached: %d import(s) remaining today (free version)",
        "msg_aucune_sauvegarde" to "No backup file found",
        "msg_donnees_invalides" to "Import error: invalid data",
        "msg_champs_obligatoires" to "Please enter at least one cost",

        // Bandeau profil
        "profil_complet" to "Profile: Complete ✓",
        "profil_incomplet" to "Profile: Incomplete",
        "total_aujourdhui" to "Total today:",

        // Économies potentielles
        "titre_economies" to "Potential savings",
        "economies_si_arret" to "If you quit completely:",
        "economies_jour" to "Day",
        "economies_semaine" to "Week",
        "economies_mois" to "Month",
        "economies_annee" to "Year",

        // Boutons dialog
        "btn_confirmer" to "Confirm",
        "btn_annuler" to "Cancel",
        "btn_ok" to "OK",

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
        "btn_premium" to "Ad-free version",
        "premium_titre" to "Ad-free version",
        "premium_contenu" to "The ad-free version will be available soon.\n\nIt will remove banner ads and limits.",
        "hint_prenom" to "Enter your first name",

        // Reset
        "raz_sauvegarde" to "Reset and Backup",
        "raz_jour" to "Reset day",
        "raz_historique" to "Reset history",
        "raz_usine" to "Factory reset",
        "raz_jour_ok" to "Day reset done",
        "raz_historique_ok" to "History reset done",
        "raz_usine_ok" to "Factory reset done",

        // Units in cl for alcohols
        "unite_cl_global"      to "Unit (cl)",
        "unite_cl_biere"       to "Unit (cl)",
        "unite_cl_liqueur"     to "Unit (cl)",
        "unite_cl_alcool_fort" to "Unit (cl)",

        "label_prix_feuilles_longues" to "Long papers price",
        "label_nb_feuilles_longues"   to "Number of long papers"
    )

    // ==================== ESPAÑOL ====================
    private val TRADUCTIONS_ES = mapOf(
        // Titres sections
        "titre" to "Configuración",
        "titre_categories" to "Categorías activas",
        "titre_couts_cigarettes" to "Costos cigarrillos",
        "titre_couts_joints" to "Costos porros",
        "titre_couts_alcool" to "Costos alcohol",
        "titre_habitudes" to "Hábitos diarios",
        "titre_dates_objectifs" to "Fechas objetivos",
        "titre_raz_export" to "Restablecer y Exportar/Importar",

        // Labels profil
        "label_prenom" to "Nombre",
        "label_langue" to "Idioma",
        "label_devise" to "Moneda",
        "btn_sauvegarder_profil" to "Guardar perfil",

        // Labels catégories
        "label_cigarettes" to "Cigarrillos",
        "label_joints" to "Porros",
        "label_alcool_global" to "Alcohol global",
        "label_bieres" to "Cervezas",
        "label_liqueurs" to "Licores",
        "label_alcool_fort" to "Aguardiente",

        // Labels formulaires cigarettes
        "radio_classiques" to "Cigarrillos clásicos",
        "radio_rouler" to "Para liar",
        "radio_tubeuse" to "Entubadora",
        "label_prix_paquet" to "Precio del paquete",
        "label_nb_cigarettes" to "Número de cigarrillos",
        "label_prix_tabac" to "Precio tabaco",
        "label_prix_feuilles" to "Precio papeles",
        "label_nb_feuilles" to "Número de papeles",
        "label_prix_filtres" to "Precio filtros",
        "label_nb_filtres" to "Número de filtros",
        "label_prix_tubes" to "Precio tubos",
        "label_nb_tubes" to "Número de tubos",

        // Labels formulaires joints
        "label_prix_gramme" to "Precio por gramo",
        "label_gramme_par_joint" to "Gramos por porro",

        // Labels formulaires alcool
        "label_prix_verre" to "Precio por copa",
        "label_unite_cl" to "Unidad (cL)",

        // Labels habitudes
        "label_max_journalier" to "Máximo diario",

        // Labels dates
        "label_date_reduction" to "Fecha reducción",
        "label_date_arret" to "Fecha abandono",
        "label_date_reussite" to "Fecha éxito",
        "btn_choisir_date" to "Elegir fecha",

        "msg_export_limite" to "Para acceder a la exportación, pásate a la versión sin anuncios para disfrutarla :-)",
                "msg_import_limite" to "Límite alcanzado. Quedan %d importaciones hoy.",

        // Boutons RAZ
        "btn_raz_jour" to "Restablecer hoy",
        "btn_raz_historique" to "Restablecer historial",
        "btn_raz_usine" to "Restablecimiento de fábrica",

        // Boutons Export/Import
        "btn_exporter" to "Exportar",
        "btn_importer" to "Importar",
        "btn_export_logs" to "Exportar registros",

        // Messages confirmations
        "confirm_raz_jour_titre" to "Restablecer hoy",
        "confirm_raz_jour_message" to "¿Eliminar todo el consumo de hoy?",
        "confirm_raz_historique_titre" to "Restablecer historial",
        "confirm_raz_historique_message" to "¿Eliminar TODO el historial de consumo? (Esta acción es irreversible)",
        "confirm_raz_usine_titre" to "Restablecimiento de fábrica",
        "confirm_raz_usine_message" to "¿Restablecer TODO (perfil, consumo, configuración)? (Esta acción es irreversible)",
        "confirm_import_titre" to "Importar copia de seguridad",
        "confirm_import_message" to "Todos los datos actuales serán reemplazados. ¿Continuar?",

        // Messages succès/erreur
        "msg_profil_sauvegarde" to "Perfil guardado",
        "msg_date_enregistree" to "Fecha guardada",
        "msg_raz_effectuee" to "Restablecimiento completado",
        "msg_export_reussi" to "Exportación exitosa",
        "msg_import_reussi" to "Importación exitosa",
        "msg_erreur_sauvegarde" to "Error al guardar",
        "msg_erreur_date" to "Error al guardar fecha",
        "msg_erreur_raz" to "Error de restablecimiento",
        "msg_erreur_export" to "Error de exportación",
        "msg_erreur_import" to "Error de importación",
        "msg_limite_export" to "Límite alcanzado: %d exportación(es) restante(s) hoy (versión gratuita)",
        "msg_limite_import" to "Límite alcanzado: %d importación(es) restante(s) hoy (versión gratuita)",
        "msg_aucune_sauvegarde" to "No se encontró archivo de copia de seguridad",
        "msg_donnees_invalides" to "Error de importación: datos inválidos",
        "msg_champs_obligatoires" to "Por favor ingrese al menos un costo",

        // Bandeau profil
        "profil_complet" to "Perfil: Completo ✓",
        "profil_incomplet" to "Perfil: Incompleto",
        "total_aujourdhui" to "Total hoy:",

        // Économies potentielles
        "titre_economies" to "Ahorros potenciales",
        "economies_si_arret" to "Si dejaras completamente:",
        "economies_jour" to "Día",
        "economies_semaine" to "Semana",
        "economies_mois" to "Mes",
        "economies_annee" to "Año",

        // Boutons dialog
        "btn_confirmer" to "Confirmar",
        "btn_annuler" to "Cancelar",
        "btn_ok" to "OK",

        // Acerca de
        "titre_a_propos" to "Acerca de",
        "voir_avertissement" to "Ver advertencia",
        "btn_manuel" to "Manual de usuario",
        "btn_cgv" to "Términos y condiciones",
        "btn_mentions_legales" to "Aviso legal",
        "btn_contact" to "Contactar soporte",
        "btn_maj" to "Últimas actualizaciones",
        "maj_titre" to "Últimas actualizaciones",
        "maj_contenu" to "Lanzamiento V1",
        "btn_premium" to "Versión sin publicidad",
        "premium_titre" to "Versión sin publicidad",
        "premium_contenu" to "La versión sin publicidad estará disponible pronto.\n\nEliminará los anuncios y las limitaciones.",
        "hint_prenom" to "Ingrese su nombre",

        // Restablecer
        "raz_sauvegarde" to "Restablecer y Copia de seguridad",
        "raz_jour" to "Restablecer día",
        "raz_historique" to "Restablecer historial",
        "raz_usine" to "Restablecimiento de fábrica",
        "raz_jour_ok" to "Día restablecido",
        "raz_historique_ok" to "Historial restablecido",
        "raz_usine_ok" to "Restablecimiento de fábrica completado",

        "unite_cl_global"      to "Unidad (cl)",
        "unite_cl_biere"       to "Unidad (cl)",
        "unite_cl_liqueur"     to "Unidad (cl)",
        "unite_cl_alcool_fort" to "Unidad (cl)",

        "label_prix_feuilles_longues" to "Precio de papeles largos",
        "label_nb_feuilles_longues"   to "Número de papeles largos"
    )

    // ==================== PORTUGUÊS ====================
    private val TRADUCTIONS_PT = mapOf(
        // Titres sections
        "titre" to "Configurações",
        "titre_categories" to "Categorias ativas",
        "titre_couts_cigarettes" to "Custos cigarros",
        "titre_couts_joints" to "Custos baseados",
        "titre_couts_alcool" to "Custos álcool",
        "titre_habitudes" to "Hábitos diários",
        "titre_dates_objectifs" to "Datas objetivos",
        "titre_raz_export" to "Redefinir e Exportar/Importar",

        // Labels profil
        "label_prenom" to "Nome",
        "label_langue" to "Idioma",
        "label_devise" to "Moeda",
        "btn_sauvegarder_profil" to "Salvar perfil",

        // Labels catégories
        "label_cigarettes" to "Cigarros",
        "label_joints" to "Baseados",
        "label_alcool_global" to "Álcool global",
        "label_bieres" to "Cervejas",
        "label_liqueurs" to "Licores",
        "label_alcool_fort" to "Destilados",

        // Labels formulaires cigarettes
        "radio_classiques" to "Cigarros clássicos",
        "radio_rouler" to "Para enrolar",
        "radio_tubeuse" to "Entubadora",
        "label_prix_paquet" to "Preço do maço",
        "label_nb_cigarettes" to "Número de cigarros",
        "label_prix_tabac" to "Preço tabaco",
        "label_prix_feuilles" to "Preço papéis",
        "label_nb_feuilles" to "Número de papéis",
        "label_prix_filtres" to "Preço filtros",
        "label_nb_filtres" to "Número de filtros",
        "label_prix_tubes" to "Preço tubos",
        "label_nb_tubes" to "Número de tubos",

        // Labels formulaires joints
        "label_prix_gramme" to "Preço por grama",
        "label_gramme_par_joint" to "Gramas por baseado",

        // Labels formulaires alcool
        "label_prix_verre" to "Preço por dose",
        "label_unite_cl" to "Unidade (cL)",

        // Labels habitudes
        "label_max_journalier" to "Máximo diário",

        // Labels dates
        "label_date_reduction" to "Data redução",
        "label_date_arret" to "Data parada",
        "label_date_reussite" to "Data sucesso",
        "btn_choisir_date" to "Escolher data",

        "msg_export_limite" to "Para aceder à exportação, mude para a versão sem publicidade :-)",
                "msg_import_limite" to "Limite atingido. Restam %d importações hoje.",

        // Boutons RAZ
        "btn_raz_jour" to "Redefinir hoje",
        "btn_raz_historique" to "Redefinir histórico",
        "btn_raz_usine" to "Redefinição de fábrica",

        // Boutons Export/Import
        "btn_exporter" to "Exportar",
        "btn_importer" to "Importar",
        "btn_export_logs" to "Exportar registros",

        // Messages confirmations
        "confirm_raz_jour_titre" to "Redefinir hoje",
        "confirm_raz_jour_message" to "Excluir todo o consumo de hoje?",
        "confirm_raz_historique_titre" to "Redefinir histórico",
        "confirm_raz_historique_message" to "Excluir TODO o histórico de consumo? (Esta ação é irreversível)",
        "confirm_raz_usine_titre" to "Redefinição de fábrica",
        "confirm_raz_usine_message" to "Redefinir TUDO (perfil, consumo, configurações)? (Esta ação é irreversível)",
        "confirm_import_titre" to "Importar backup",
        "confirm_import_message" to "Todos os dados atuais serão substituídos. Continuar?",

        // Messages succès/erreur
        "msg_profil_sauvegarde" to "Perfil salvo",
        "msg_date_enregistree" to "Data salva",
        "msg_raz_effectuee" to "Redefinição concluída",
        "msg_export_reussi" to "Exportação bem-sucedida",
        "msg_import_reussi" to "Importação bem-sucedida",
        "msg_erreur_sauvegarde" to "Erro ao salvar",
        "msg_erreur_date" to "Erro ao salvar data",
        "msg_erreur_raz" to "Erro de redefinição",
        "msg_erreur_export" to "Erro de exportação",
        "msg_erreur_import" to "Erro de importação",
        "msg_limite_export" to "Limite atingido: %d exportação(ões) restante(s) hoje (versão gratuita)",
        "msg_limite_import" to "Limite atingido: %d importação(ões) restante(s) hoje (versão gratuita)",
        "msg_aucune_sauvegarde" to "Nenhum arquivo de backup encontrado",
        "msg_donnees_invalides" to "Erro de importação: dados inválidos",
        "msg_champs_obligatoires" to "Por favor insira pelo menos um custo",

        // Bandeau profil
        "profil_complet" to "Perfil: Completo ✓",
        "profil_incomplet" to "Perfil: Incompleto",
        "total_aujourdhui" to "Total hoje:",

        // Économies potentielles
        "titre_economies" to "Economias potenciais",
        "economies_si_arret" to "Se você parasse completamente:",
        "economies_jour" to "Dia",
        "economies_semaine" to "Semana",
        "economies_mois" to "Mês",
        "economies_annee" to "Ano",

        // Boutons dialog
        "btn_confirmer" to "Confirmar",
        "btn_annuler" to "Cancelar",
        "btn_ok" to "OK",

        // Sobre
        "titre_a_propos" to "Sobre",
        "voir_avertissement" to "Ver aviso",
        "btn_manuel" to "Manual do usuário",
        "btn_cgv" to "Termos e condições",
        "btn_mentions_legales" to "Aviso legal",
        "btn_contact" to "Contatar suporte",
        "btn_maj" to "Últimas atualizações",
        "maj_titre" to "Últimas atualizações",
        "maj_contenu" to "Lançamento V1",
        "btn_premium" to "Versão sem publicidade",
        "premium_titre" to "Versão sem publicidade",
        "premium_contenu" to "A versão sem publicidade estará disponível em breve.\n\nEla removerá os anúncios e as limitações.",
        "hint_prenom" to "Digite seu nome",

        // Redefinir
        "raz_sauvegarde" to "Redefinir e Backup",
        "raz_jour" to "Redefinir dia",
        "raz_historique" to "Redefinir histórico",
        "raz_usine" to "Redefinição de fábrica",
        "raz_jour_ok" to "Dia redefinido",
        "raz_historique_ok" to "Histórico redefinido",
        "raz_usine_ok" to "Redefinição de fábrica concluída",

        "unite_cl_global"      to "Unidade (cl)",
        "unite_cl_biere"       to "Unidade (cl)",
        "unite_cl_liqueur"     to "Unidade (cl)",
        "unite_cl_alcool_fort" to "Unidade (cl)",

    "label_prix_feuilles_longues" to "Preço das folhas longas",
    "label_nb_feuilles_longues" to "Número de folhas longas"
    )

    // ==================== DEUTSCH ====================
    private val TRADUCTIONS_DE = mapOf(
        // Titres sections
        "titre" to "Einstellungen",
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

        "msg_export_limite" to "Um den Export zu nutzen, wechseln Sie zur werbefreien Version :-)",
                "msg_import_limite" to "Limit erreicht. %d Importe verbleiben heute.",
        
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

        // Über
        "titre_a_propos" to "Über",
        "voir_avertissement" to "Warnung anzeigen",
        "btn_manuel" to "Benutzerhandbuch",
        "btn_cgv" to "Allgemeine Geschäftsbedingungen",
        "btn_mentions_legales" to "Impressum",
        "btn_contact" to "Support kontaktieren",
        "btn_maj" to "Letzte Aktualisierungen",
        "maj_titre" to "Letzte Aktualisierungen",
        "maj_contenu" to "Bereitstellung V1",
        "btn_premium" to "Werbefreie Version",
        "premium_titre" to "Werbefreie Version",
        "premium_contenu" to "Die werbefreie Version ist bald verfügbar.\n\nSie entfernt Bannerwerbung und Einschränkungen.",
        "hint_prenom" to "Geben Sie Ihren Vornamen ein",

        // Zurücksetzen
        "raz_sauvegarde" to "Zurücksetzen und Sicherung",
        "raz_jour" to "Tag zurücksetzen",
        "raz_historique" to "Verlauf zurücksetzen",
        "raz_usine" to "Werkseinstellungen",
        "raz_jour_ok" to "Tag zurückgesetzt",
        "raz_historique_ok" to "Verlauf zurückgesetzt",
        "raz_usine_ok" to "Auf Werkseinstellungen zurückgesetzt",

        "unite_cl_global"      to "Einheit (cl)",
        "unite_cl_biere"       to "Einheit (cl)",
        "unite_cl_liqueur"     to "Einheit (cl)",
        "unite_cl_alcool_fort" to "Einheit (cl)",

    "label_prix_feuilles_longues" to "Preis der langen Blättchen",
    "label_nb_feuilles_longues" to "Anzahl der langen Blättchen"
    )

    // ==================== ITALIANO ====================
    private val TRADUCTIONS_IT = mapOf(
        // Titres sections
        "titre" to "Impostazioni",
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

        // Labels catégories
        "label_cigarettes" to "Sigarette",
        "label_joints" to "Spinelli",
        "label_alcool_global" to "Alcol globale",
        "label_bieres" to "Birre",
        "label_liqueurs" to "Liquori",
        "label_alcool_fort" to "Superalcolici",

        // Labels formulaires cigarettes
        "radio_classiques" to "Sigarette classiche",
        "radio_rouler" to "Da rollare",
        "radio_tubeuse" to "Con tubo",
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

        // Labels formulaires alcool
        "label_prix_verre" to "Prezzo bicchiere",
        "label_unite_cl" to "Unità (cL)",

        // Labels habitudes
        "label_max_journalier" to "Massimo giornaliero",

        // Labels dates
        "label_date_reduction" to "Data riduzione",
        "label_date_arret" to "Data smettere",
        "label_date_reussite" to "Data successo",
        "btn_choisir_date" to "Scegli data",

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

        // Informazioni
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
        "hint_prenom" to "Inserisci il tuo nome",

        // Ripristina
        "raz_sauvegarde" to "Ripristina e Backup",
        "raz_jour" to "Ripristina giorno",
        "raz_historique" to "Ripristina cronologia",
        "raz_usine" to "Ripristino di fabbrica",
        "raz_jour_ok" to "Giorno ripristinato",
        "raz_historique_ok" to "Cronologia ripristinata",
        "raz_usine_ok" to "Ripristino di fabbrica completato",

        "unite_cl_global"      to "Unità (cl)",
        "unite_cl_biere"       to "Unità (cl)",
        "unite_cl_liqueur"     to "Unità (cl)",
        "unite_cl_alcool_fort" to "Unità (cl)",
        
    "label_prix_feuilles_longues" to "Prezzo delle cartine lunghe",
    "label_nb_feuilles_longues" to "Numero di cartine lunghe"
    )

    // ==================== РУССКИЙ ====================
    private val TRADUCTIONS_RU = mapOf(
        // Titres sections
        "titre" to "Настройки",
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
        "label_prix_feuilles" to "Цена бумаг",
        "label_nb_feuilles" to "Количество бумаг",
        "label_prix_filtres" to "Цена фильтров",
        "label_nb_filtres" to "Количество фильтров",
        "label_prix_tubes" to "Цена гильз",
        "label_nb_tubes" to "Количество гильз",

        // Labels formulaires joints
        "label_prix_gramme" to "Цена за грамм",
        "label_gramme_par_joint" to "Граммов на косяк",

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

        "msg_export_limite" to "Чтобы пользоваться экспортом, перейдите на версию без рекламы :-)",
                "msg_import_limite" to "Достигнут лимит. Осталось %d импорт(ов) на сегодня.",

        // Boutons RAZ
        "btn_raz_jour" to "Сбросить сегодня",
        "btn_raz_historique" to "Сбросить историю",
        "btn_raz_usine" to "Заводской сброс",

        // Boutons Export/Import
        "btn_exporter" to "Экспорт",
        "btn_importer" to "Импорт",
        "btn_export_logs" to "Экспорт журналов",

        // Messages confirmations
        "confirm_raz_jour_titre" to "Сбросить сегодня",
        "confirm_raz_jour_message" to "Удалить все сегодняшнее потребление?",
        "confirm_raz_historique_titre" to "Сбросить историю",
        "confirm_raz_historique_message" to "Удалить ВСЮ историю потребления? (Это действие необратимо)",
        "confirm_raz_usine_titre" to "Заводской сброс",
        "confirm_raz_usine_message" to "Сбросить ВСЁ (профиль, потребление, настройки)? (Это действие необратимо)",
        "confirm_import_titre" to "Импорт резервной копии",
        "confirm_import_message" to "Все текущие данные будут заменены. Продолжить?",

        // Messages succès/erreur
        "msg_profil_sauvegarde" to "Профиль сохранен",
        "msg_date_enregistree" to "Дата сохранена",
        "msg_raz_effectuee" to "Сброс завершен",
        "msg_export_reussi" to "Экспорт успешен",
        "msg_import_reussi" to "Импорт успешен",
        "msg_erreur_sauvegarde" to "Ошибка сохранения",
        "msg_erreur_date" to "Ошибка сохранения даты",
        "msg_erreur_raz" to "Ошибка сброса",
        "msg_erreur_export" to "Ошибка экспорта",
        "msg_erreur_import" to "Ошибка импорта",
        "msg_limite_export" to "Достигнут лимит: %d экспорт(ов) осталось сегодня (бесплатная версия)",
        "msg_limite_import" to "Достигнут лимит: %d импорт(ов) осталось сегодня (бесплатная версия)",
        "msg_aucune_sauvegarde" to "Файл резервной копии не найден",
        "msg_donnees_invalides" to "Ошибка импорта: недопустимые данные",
        "msg_champs_obligatoires" to "Пожалуйста, введите хотя бы одну стоимость",

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

        // О программе
        "titre_a_propos" to "О программе",
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
        "hint_prenom" to "Введите ваше имя",

        // Сброс
        "raz_sauvegarde" to "Сброс и резервное копирование",
        "raz_jour" to "Сброс дня",
        "raz_historique" to "Сброс истории",
        "raz_usine" to "Сброс к заводским настройкам",
        "raz_jour_ok" to "День сброшен",
        "raz_historique_ok" to "История сброшена",
        "raz_usine_ok" to "Выполнен сброс к заводским настройкам",

        "unite_cl_global"      to "Единица (мл)",
        "unite_cl_biere"       to "Единица (мл)",
        "unite_cl_liqueur"     to "Единица (мл)",
        "unite_cl_alcool_fort" to "Единица (мл)",

    "label_prix_feuilles_longues" to "Цена длинных бумажек",
    "label_nb_feuilles_longues" to "Количество длинных бумажек"
    )

    // ==================== العربية (ARABE) ====================
    private val TRADUCTIONS_AR = mapOf(
        // Titres sections
        "titre" to "الإعدادات",
        "titre_categories" to "الفئات النشطة",
        "titre_couts_cigarettes" to "تكاليف السجائر",
        "titre_couts_joints" to "تكاليف المفاصل",
        "titre_couts_alcool" to "تكاليف الكحول",
        "titre_habitudes" to "العادات اليومية",
        "titre_dates_objectifs" to "تواريخ الأهداف",
        "titre_raz_export" to "إعادة التعيين والتصدير/الاستيراد",

        // Labels profil
        "label_prenom" to "الاسم الأول",
        "label_langue" to "اللغة",
        "label_devise" to "العملة",
        "btn_sauvegarder_profil" to "حفظ الملف الشخصي",

        // Labels catégories
        "label_cigarettes" to "السجائر",
        "label_joints" to "المفاصل",
        "label_alcool_global" to "الكحول العام",
        "label_bieres" to "البيرة",
        "label_liqueurs" to "المشروبات الكحولية",
        "label_alcool_fort" to "الكحول القوي",

        // Labels formulaires cigarettes
        "radio_classiques" to "سجائر عادية",
        "radio_rouler" to "للف",
        "radio_tubeuse" to "آلة الأنابيب",
        "label_prix_paquet" to "سعر الباقة",
        "label_nb_cigarettes" to "عدد السجائر",
        "label_prix_tabac" to "سعر التبغ",
        "label_prix_feuilles" to "سعر الأوراق",
        "label_nb_feuilles" to "عدد الأوراق",
        "label_prix_filtres" to "سعر المرشحات",
        "label_nb_filtres" to "عدد المرشحات",
        "label_prix_tubes" to "سعر الأنابيب",
        "label_nb_tubes" to "عدد الأنابيب",

        // Labels formulaires joints
        "label_prix_gramme" to "السعر لكل جرام",
        "label_gramme_par_joint" to "جرامات لكل مفصل",

        // Labels formulaires alcool
        "label_prix_verre" to "سعر الكأس",
        "label_unite_cl" to "(cL) الوحدة",

        // Labels habitudes
        "label_max_journalier" to "الحد الأقصى اليومي",

        // Labels dates
        "label_date_reduction" to "تاريخ التخفيض",
        "label_date_arret" to "تاريخ الإقلاع",
        "label_date_reussite" to "تاريخ النجاح",
        "btn_choisir_date" to "اختر التاريخ",

        "msg_export_limite" to "للوصول إلى التصدير، انتقل إلى النسخة بدون إعلانات للاستفادة من هذه الميزة :-)",
                "msg_import_limite" to "تم بلوغ الحد. تبقّى %d عملية استيراد اليوم.",

        // Boutons RAZ
        "btn_raz_jour" to "إعادة تعيين اليوم",
        "btn_raz_historique" to "إعادة تعيين السجل",
        "btn_raz_usine" to "إعادة ضبط المصنع",

        // Boutons Export/Import
        "btn_exporter" to "تصدير",
        "btn_importer" to "استيراد",
        "btn_export_logs" to "تصدير السجلات",

        // Messages confirmations
        "confirm_raz_jour_titre" to "إعادة تعيين اليوم",
        "confirm_raz_jour_message" to "حذف كل استهلاك اليوم؟",
        "confirm_raz_historique_titre" to "إعادة تعيين السجل",
        "confirm_raz_historique_message" to "حذف كل سجل الاستهلاك؟ (هذا الإجراء لا رجعة فيه)",
        "confirm_raz_usine_titre" to "إعادة ضبط المصنع",
        "confirm_raz_usine_message" to "إعادة تعيين كل شيء (الملف، الاستهلاك، الإعدادات)؟ (هذا الإجراء لا رجعة فيه)",
        "confirm_import_titre" to "استيراد النسخة الاحتياطية",
        "confirm_import_message" to "سيتم استبدال جميع البيانات الحالية. هل تريد المتابعة؟",

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
        "msg_limite_export" to "تم بلوغ الحد: %d تصدير متبقي اليوم (نسخة مجانية)",
        "msg_limite_import" to "تم بلوغ الحد: %d استيراد متبقي اليوم (نسخة مجانية)",
        "msg_aucune_sauvegarde" to "لم يتم العثور على ملف نسخ احتياطي",
        "msg_donnees_invalides" to "خطأ في الاستيراد: بيانات غير صالحة",
        "msg_champs_obligatoires" to "الرجاء إدخال تكلفة واحدة على الأقل",

        // Bandeau profil
        "profil_complet" to "✓ الملف: كامل",
        "profil_incomplet" to "الملف: غير كامل",
        "total_aujourdhui" to ":المجموع اليوم",

        // Économies potentielles
        "titre_economies" to "الوفورات المحتملة",
        "economies_si_arret" to ":إذا توقفت تماماً",
        "economies_jour" to "يوم",
        "economies_semaine" to "أسبوع",
        "economies_mois" to "شهر",
        "economies_annee" to "سنة",

        // Boutons dialog
        "btn_confirmer" to "تأكيد",
        "btn_annuler" to "إلغاء",
        "btn_ok" to "موافق",

        // حول
        "titre_a_propos" to "حول",
        "voir_avertissement" to "عرض التحذير",
        "btn_manuel" to "دليل المستخدم",
        "btn_cgv" to "الشروط والأحكام",
        "btn_mentions_legales" to "الإشعار القانوني",
        "btn_contact" to "الاتصال بالدعم",
        "btn_maj" to "آخر التحديثات",
        "maj_titre" to "آخر التحديثات",
        "maj_contenu" to "نشر الإصدار V1",
        "btn_premium" to "الإصدار بدون إعلانات",
        "premium_titre" to "الإصدار بدون إعلانات",
        "premium_contenu" to "الإصدار بدون إعلانات سيكون متاحًا قريبًا.\n\nسيزيل الإعلانات والقيود.",
        "hint_prenom" to "أدخل اسمك الأول",

        // إعادة تعيين
        "raz_sauvegarde" to "إعادة التعيين والنسخ الاحتياطي",
        "raz_jour" to "إعادة تعيين اليوم",
        "raz_historique" to "إعادة تعيين السجل",
        "raz_usine" to "إعادة التعيين إلى إعدادات المصنع",
        "raz_jour_ok" to "تم إعادة تعيين اليوم",
        "raz_historique_ok" to "تم إعادة تعيين السجل",
        "raz_usine_ok" to "تمت إعادة التعيين إلى إعدادات المصنع",

        "unite_cl_global"      to "الوحدة (سنتيلتر)",
        "unite_cl_biere"       to "الوحدة (سنتيلتر)",
        "unite_cl_liqueur"     to "الوحدة (سنتيلتر)",
        "unite_cl_alcool_fort" to "الوحدة (سنتيلتر)",

    "label_prix_feuilles_longues" to "سعر أوراق اللف الطويلة",
    "label_nb_feuilles_longues" to "عدد أوراق اللف الطويلة"
    )

    // ==================== हिन्दी (HINDI) ====================
    private val TRADUCTIONS_HI = mapOf(
        // Titres sections
        "titre" to "सेटिंग्स",
        "titre_categories" to "सक्रिय श्रेणियां",
        "titre_couts_cigarettes" to "सिगरेट की लागत",
        "titre_couts_joints" to "जोड़ों की लागत",
        "titre_couts_alcool" to "शराब की लागत",
        "titre_habitudes" to "दैनिक आदतें",
        "titre_dates_objectifs" to "लक्ष्य तिथियां",
        "titre_raz_export" to "रीसेट और निर्यात/आयात",

        // Labels profil
        "label_prenom" to "नाम",
        "label_langue" to "भाषा",
        "label_devise" to "मुद्रा",
        "btn_sauvegarder_profil" to "प्रोफ़ाइल सहेजें",

        // Labels catégories
        "label_cigarettes" to "सिगरेट",
        "label_joints" to "जोड़",
        "label_alcool_global" to "शराब कुल",
        "label_bieres" to "बीयर",
        "label_liqueurs" to "शराब",
        "label_alcool_fort" to "मजबूत शराब",

        // Labels formulaires cigarettes
        "radio_classiques" to "नियमित सिगरेट",
        "radio_rouler" to "लपेटने के लिए",
        "radio_tubeuse" to "ट्यूब मशीन",
        "label_prix_paquet" to "पैकेट की कीमत",
        "label_nb_cigarettes" to "सिगरेट की संख्या",
        "label_prix_tabac" to "तंबाकू की कीमत",
        "label_prix_feuilles" to "कागजों की कीमत",
        "label_nb_feuilles" to "कागजों की संख्या",
        "label_prix_filtres" to "फिल्टर की कीमत",
        "label_nb_filtres" to "फिल्टर की संख्या",
        "label_prix_tubes" to "ट्यूब की कीमत",
        "label_nb_tubes" to "ट्यूब की संख्या",

        // Labels formulaires joints
        "label_prix_gramme" to "प्रति ग्राम कीमत",
        "label_gramme_par_joint" to "प्रति जोड़ ग्राम",

        // Labels formulaires alcool
        "label_prix_verre" to "पेय की कीमत",
        "label_unite_cl" to "इकाई (cL)",

        // Labels habitudes
        "label_max_journalier" to "दैनिक अधिकतम",

        // Labels dates
        "label_date_reduction" to "कमी की तिथि",
        "label_date_arret" to "छोड़ने की तिथि",
        "label_date_reussite" to "सफलता की तिथि",
        "btn_choisir_date" to "तिथि चुनें",

        "msg_export_limite" to "एक्सपोर्ट करने के लिए बिना विज्ञापन वाले वर्शन पर जाएँ :-)",
                "msg_import_limite" to "सीमा पूरी हो गई। आज %d आयात शेष हैं।",

        // Boutons RAZ
        "btn_raz_jour" to "आज रीसेट करें",
        "btn_raz_historique" to "इतिहास रीसेट करें",
        "btn_raz_usine" to "फ़ैक्टरी रीसेट",

        // Boutons Export/Import
        "btn_exporter" to "निर्यात",
        "btn_importer" to "आयात",
        "btn_export_logs" to "लॉग निर्यात करें",

        // Messages confirmations
        "confirm_raz_jour_titre" to "आज रीसेट करें",
        "confirm_raz_jour_message" to "आज की सभी खपत हटाएं?",
        "confirm_raz_historique_titre" to "इतिहास रीसेट करें",
        "confirm_raz_historique_message" to "सभी खपत इतिहास हटाएं? (यह क्रिया अपरिवर्तनीय है)",
        "confirm_raz_usine_titre" to "फ़ैक्टरी रीसेट",
        "confirm_raz_usine_message" to "सब कुछ रीसेट करें (प्रोफ़ाइल, खपत, सेटिंग्स)? (यह क्रिया अपरिवर्तनीय है)",
        "confirm_import_titre" to "बैकअप आयात करें",
        "confirm_import_message" to "सभी वर्तमान डेटा बदल दिया जाएगा। जारी रखें?",

        // Messages succès/erreur
        "msg_profil_sauvegarde" to "प्रोफ़ाइल सहेजा गया",
        "msg_date_enregistree" to "तिथि सहेजी गई",
        "msg_raz_effectuee" to "रीसेट पूर्ण",
        "msg_export_reussi" to "निर्यात सफल",
        "msg_import_reussi" to "आयात सफल",
        "msg_erreur_sauvegarde" to "सहेजने में त्रुटि",
        "msg_erreur_date" to "तिथि सहेजने में त्रुटि",
        "msg_erreur_raz" to "रीसेट त्रुटि",
        "msg_erreur_export" to "निर्यात त्रुटि",
        "msg_erreur_import" to "आयात त्रुटि",
        "msg_limite_export" to "सीमा पहुंची: आज %d निर्यात शेष (मुफ़्त संस्करण)",
        "msg_limite_import" to "सीमा पहुंची: आज %d आयात शेष (मुफ़्त संस्करण)",
        "msg_aucune_sauvegarde" to "कोई बैकअप फ़ाइल नहीं मिली",
        "msg_donnees_invalides" to "आयात त्रुटि: अमान्य डेटा",
        "msg_champs_obligatoires" to "कृपया कम से कम एक लागत दर्ज करें",

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

        // के बारे में
        "titre_a_propos" to "के बारे में",
        "voir_avertissement" to "चेतावनी देखें",
        "btn_manuel" to "उपयोगकर्ता मैनुअल",
        "btn_cgv" to "नियम और शर्तें",
        "btn_mentions_legales" to "कानूनी सूचना",
        "btn_contact" to "सहायता से संपर्क करें",
        "btn_maj" to "नवीनतम अपडेट",
        "maj_titre" to "नवीनतम अपडेट",
        "maj_contenu" to "परिनियोजन V1",
        "btn_premium" to "विज्ञापन-मुक्त संस्करण",
        "premium_titre" to "विज्ञापन-मुक्त संस्करण",
        "premium_contenu" to "विज्ञापन-मुक्त संस्करण जल्दी ही उपलब्ध होगा।\n\nयह बैनर विज्ञापनों और सीमाओं को हटा देगा।",
        "hint_prenom" to "अपना नाम दर्ज करें",

        // रीसेट
        "raz_sauvegarde" to "रीसेट और बैकअप",
        "raz_jour" to "दिन रीसेट करें",
        "raz_historique" to "इतिहास रीसेट करें",
        "raz_usine" to "फ़ैक्टरी रीसेट",
        "raz_jour_ok" to "दिन रीसेट हो गया",
        "raz_historique_ok" to "इतिहास रीसेट हो गया",
        "raz_usine_ok" to "फ़ैक्टरी रीसेट पूर्ण",

        "unite_cl_global"      to "इकाई (cl)",
        "unite_cl_biere"       to "इकाई (cl)",
        "unite_cl_liqueur"     to "इकाई (cl)",
        "unite_cl_alcool_fort" to "इकाई (cl)",

    "label_prix_feuilles_longues" to "लंबे रोलिंग पेपर की कीमत",
    "label_nb_feuilles_longues" to "लंबे रोलिंग पेपर की संख्या"
    )

    // ==================== 日本語 (JAPONAIS) ====================
    private val TRADUCTIONS_JA = mapOf(
        // Titres sections
        "titre" to "設定",
        "titre_categories" to "アクティブカテゴリ",
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

        // Labels catégories
        "label_cigarettes" to "タバコ",
        "label_joints" to "ジョイント",
        "label_alcool_global" to "アルコール全般",
        "label_bieres" to "ビール",
        "label_liqueurs" to "リキュール",
        "label_alcool_fort" to "強いアルコール",

        // Labels formulaires cigarettes
        "radio_classiques" to "通常のタバコ",
        "radio_rouler" to "手巻き",
        "radio_tubeuse" to "チューブマシン",
        "label_prix_paquet" to "パック価格",
        "label_nb_cigarettes" to "タバコの本数",
        "label_prix_tabac" to "タバコ価格",
        "label_prix_feuilles" to "紙の価格",
        "label_nb_feuilles" to "紙の枚数",
        "label_prix_filtres" to "フィルター価格",
        "label_nb_filtres" to "フィルターの数",
        "label_prix_tubes" to "チューブ価格",
        "label_nb_tubes" to "チューブの数",

        // Labels formulaires joints
        "label_prix_gramme" to "グラム当たりの価格",
        "label_gramme_par_joint" to "ジョイント当たりのグラム",

        // Labels formulaires alcool
        "label_prix_verre" to "ドリンク価格",
        "label_unite_cl" to "単位（cL）",

        // Labels habitudes
        "label_max_journalier" to "1日の最大",

        // Labels dates
        "label_date_reduction" to "削減日",
        "label_date_arret" to "禁煙日",
        "label_date_reussite" to "成功日",
        "btn_choisir_date" to "日付を選択",

        "msg_export_limite" to "エクスポート機能を利用するには、広告なしバージョンにアップグレードしてください :-)",
                "msg_import_limite" to "上限に達しました。本日はあと%d回インポートできます。",

        // Boutons RAZ
        "btn_raz_jour" to "今日をリセット",
        "btn_raz_historique" to "履歴をリセット",
        "btn_raz_usine" to "工場出荷時リセット",

        // Boutons Export/Import
        "btn_exporter" to "エクスポート",
        "btn_importer" to "インポート",
        "btn_export_logs" to "ログをエクスポート",

        // Messages confirmations
        "confirm_raz_jour_titre" to "今日をリセット",
        "confirm_raz_jour_message" to "今日のすべての消費を削除しますか？",
        "confirm_raz_historique_titre" to "履歴をリセット",
        "confirm_raz_historique_message" to "すべての消費履歴を削除しますか？（この操作は元に戻せません）",
        "confirm_raz_usine_titre" to "工場出荷時リセット",
        "confirm_raz_usine_message" to "すべてをリセットしますか（プロフィール、消費、設定）？（この操作は元に戻せません）",
        "confirm_import_titre" to "バックアップをインポート",
        "confirm_import_message" to "すべての現在のデータが置き換えられます。続行しますか？",

        // Messages succès/erreur
        "msg_profil_sauvegarde" to "プロフィールが保存されました",
        "msg_date_enregistree" to "日付が保存されました",
        "msg_raz_effectuee" to "リセット完了",
        "msg_export_reussi" to "エクスポート成功",
        "msg_import_reussi" to "インポート成功",
        "msg_erreur_sauvegarde" to "保存エラー",
        "msg_erreur_date" to "日付保存エラー",
        "msg_erreur_raz" to "リセットエラー",
        "msg_erreur_export" to "エクスポートエラー",
        "msg_erreur_import" to "インポートエラー",
        "msg_limite_export" to "制限に達しました：本日残り%dエクスポート（無料版）",
        "msg_limite_import" to "制限に達しました：本日残り%dインポート（無料版）",
        "msg_aucune_sauvegarde" to "バックアップファイルが見つかりません",
        "msg_donnees_invalides" to "インポートエラー：無効なデータ",
        "msg_champs_obligatoires" to "少なくとも1つの費用を入力してください",

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

        // について
        "titre_a_propos" to "について",
        "voir_avertissement" to "警告を表示",
        "btn_manuel" to "ユーザーマニュアル",
        "btn_cgv" to "利用規約",
        "btn_mentions_legales" to "法的通知",
        "btn_contact" to "サポートに連絡",
        "btn_maj" to "最新のアップデート",
        "maj_titre" to "最新のアップデート",
        "maj_contenu" to "リリース V1",
        "btn_premium" to "広告なしバージョン",
        "premium_titre" to "広告なしバージョン",
        "premium_contenu" to "広告なしバージョンはまもなく利用可能になります。\n\nバナー広告と制限が解除されます。",
        "hint_prenom" to "名前を入力してください",

        // リセット
        "raz_sauvegarde" to "リセットとバックアップ",
        "raz_jour" to "今日をリセット",
        "raz_historique" to "履歴をリセット",
        "raz_usine" to "工場出荷時の設定にリセット",
        "raz_jour_ok" to "今日をリセットしました",
        "raz_historique_ok" to "履歴をリセットしました",
        "raz_usine_ok" to "工場出荷時の設定にリセットしました",

        "unite_cl_global"      to "単位（cl）",
        "unite_cl_biere"       to "単位（cl）",
        "unite_cl_liqueur"     to "単位（cl）",
        "unite_cl_alcool_fort" to "単位（cl）",

    "label_prix_feuilles_longues" to "ロングペーパーの価格",
    "label_nb_feuilles_longues" to "ロングペーパーの枚数"
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
     * Fonction helper pour formater les messages avec paramètres
     */
    fun formatMessage(key: String, codeLangue: String, vararg args: Any): String {
        return try {
            val traductions = getTraductions(codeLangue)
            val template = traductions[key] ?: key
            String.format(template, *args)
        } catch (e: Exception) {
            Log.e(TAG, "Erreur formatage message $key: ${e.message}")
            key
        }
    }

    // ==================== CONTENU MANUEL ====================

    fun getManuel(langue: String): String {
        return when (langue) {
            "FR" -> """
Manuel d'utilisation - Stop Addict

Objectif : t'aider à suivre et réduire/arrêter tes consommations (tabac, alcool, cannabis), sans incitation.

1) Écran Accueil
- Ajoute tes consommations (cigarettes, joints, verres d'alcool) avec +/-.
- Vois ton total du jour et conseils personnalisés.
- Active/désactive les catégories selon tes besoins.

2) Statistiques
- Graphiques semaine/mois/année pour visualiser ton évolution.
- Compare avec tes objectifs.

3) Calendrier
- Vue mensuelle de tes consommations.
- Légende couleur pour repérer les jours à risque.
- Clique sur un jour pour ajouter/modifier.

4) Habitudes & Volonté
- Définis tes objectifs quotidiens max par catégorie.
- Dates clés : réduction, arrêt, réussite.

5) Réglages
- Configure les coûts (tabac, alcool...).
- Choisis ta langue et devise.
- Export/Import de tes données en JSON.
- RAZ jour/historique/usine si besoin.

6) Support
Contact : stopmauvaiseshabitudes@gmail.com (délai 72h ouvrées).

7) Confidentialité
Toutes tes données restent sur ton appareil. Aucun serveur, aucune collecte.

8) Rappel santé
App réservée aux 18+. Ne remplace pas un suivi médical/psychologique.
            """.trimIndent()

            else -> getManuel("FR") // Fallback FR pour autres langues
        }
    }

    // ==================== CONTENU CGV ====================

    fun getCGV(langue: String): String {
        return when (langue) {
            "FR" -> """
Conditions Générales de Vente (CGV) - Stop Addict

Entrée en vigueur : 15 septembre 2025

1) Objet
Stop Addict est une application mobile d'auto-suivi des consommations (tabac, alcool, cannabis). Elle ne fait pas la promotion de ces produits.

2) Accès à l'application
- Version gratuite : accès illimité avec publicités (Google AdMob).
- Version payante : achat unique, sans pub, toutes fonctionnalités.

3) Données personnelles
- Stockage local uniquement (sur ton appareil).
- Aucune transmission à des serveurs externes.
- Export/suppression possibles à tout moment.

4) Responsabilités
- L'app est un outil d'aide, pas un dispositif médical.
- Ne remplace pas un suivi par un professionnel de santé.
- Aucune garantie de résultat.

5) Modifications
Nous pouvons modifier ces CGV. Les changements seront notifiés dans l'app ou via le Store.

6) Contact
stopmauvaiseshabitudes@gmail.com (délai cible 72h ouvrées).

7) Loi applicable
En l'absence d'entreprise constituée, ces CGV sont à titre informatif. La loi applicable dépendra de ta localisation et des règles du Store.
            """.trimIndent()

            else -> getCGV("FR") // Fallback FR
        }
    }

    // ==================== CONTENU MENTIONS LÉGALES ====================

    fun getMentionsLegales(langue: String): String {
        return when (langue) {
            "FR" -> """
Mentions Légales - Stop Addict

Éditeur : À compléter (auto-entrepreneur - ouverture prévue décembre).

Contact : stopmauvaiseshabitudes@gmail.com

Hébergement : Application distribuée via Google Play Store. Données utilisateurs stockées localement sur l'appareil.

Protection des données : Aucune collecte côté serveur. Les données que tu saisis restent sur l'appareil. Export/RAZ disponibles.

Avertissement santé : Réservé aux 18+. L'app n'incite pas à la consommation et ne remplace pas un accompagnement médical/psychologique/social.

Entrée en vigueur : 15 septembre 2025
            """.trimIndent()

            else -> getMentionsLegales("FR") // Fallback FR
        }
    }

    // ==================== CONTENU AVERTISSEMENT ====================

    fun getAvertissement(langue: String): String {
        return when (langue) {
            "FR" -> """
⚠️ Avertissement - Public majeur(e) (18+)

Stop Addict est une application d'auto-suivi et d'aide à la réduction/arrêt des consommations (tabac, alcool, cannabis).

✓ Réservée aux personnes de 18 ans et plus, ayant dépassé la majorité du pays de résidence ou du pays visité.

✓ Ne fait pas la promotion de ces produits.

✓ Ne remplace pas un accompagnement médical, psychologique ou social. En cas de difficulté, consultez un professionnel.

✓ Utilisez Stop Addict de façon responsable.

📞 Ressources utiles :
• Urgences : 112 (UE) / 15 (FR - SAMU)
• Tabac Info Service (FR) : 39 89
• Alcool Info Service (FR) : 0 980 980 930
• Drogues Info Service (FR) : 0 800 23 13 13

Consultez les ressources locales dans votre pays si vous n'êtes pas en France.
            """.trimIndent()

            else -> getAvertissement("FR") // Fallback FR
        }
    }
}
