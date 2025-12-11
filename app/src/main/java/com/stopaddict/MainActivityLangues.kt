package com.stopaddict

import android.util.Log

object MainActivityLangues {

    private const val TAG = "MainActivityLangues"

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
        // Titres onglets
        "tab_accueil" to "Accueil",
        "tab_stats" to "Stats",
        "tab_calendrier" to "Calendrier",
        "tab_habitudes" to "Habitudes & Volonté",
        "tab_reglages" to "Réglages",
        
                // Dialog avertissement majorité
        "warning_title" to "⚠️ Avertissement - Public majeur(e) (18+)",
        "warning_message" to """
            ⚠️ AVERTISSEMENT LÉGAL
            
            L’utilisation de cette application implique la lecture attentive du présent avertissement.
            L’utilisateur est invité à prendre connaissance de l’ensemble des informations suivantes avant toute utilisation.
            
            1. VÉRIFICATION DE MAJORITÉ ET RESPECT DES LÉGISLATIONS LOCALES
            L’utilisateur doit vérifier qu’il est majeur selon les lois en vigueur dans son pays de résidence et dans tout pays dans lequel il se trouve lorsqu’il utilise l’application.
            Les âges légaux concernant le tabac, le cannabis ou l’alcool varient d’un pays à l’autre.
            Exemple : une personne majeure en France à 18 ans peut se trouver mineure au regard des lois américaines concernant la consommation d’alcool.
            
            Il appartient exclusivement à l’utilisateur de vérifier les lois, les interdictions, les obligations et les restrictions applicables dans chaque pays ou territoire concernant :
            - l’usage de l’application,
            - la possession ou consommation de cigarettes,
            - l’usage de cannabis selon les réglementations locales,
            - la consommation d’alcool,
            - toute autre pratique réglementée.
            
            L’application ne saurait être considérée comme encourageant, facilitant, incitant ou recommandant la consommation de substances réglementées dans aucun pays.
            
            2. ABSENCE D’INCITATION À CONSOMMER
            L’utilisation de l’application ne présume pas que l’utilisateur soit fumeur, consommateur d’alcool ou de cannabis.
            L’application n’incite, ne recommande, ne valorise ni ne normalise la consommation de cigarettes, de joints ou d’alcool.
            
            L’application a pour seul objectif :
            - d’aider à compter, visualiser et analyser,
            - de sensibiliser l’utilisateur à sa consommation, ses habitudes et ses dépenses,
            - d’encourager la réduction ou l’arrêt.
            
            La vraie réussite est de refuser une cigarette, un joint ou un verre d’alcool, voire de ne jamais commencer.
            Si vous ne fumez pas et ne buvez pas, continuez à préserver votre santé : tabac, cannabis et alcool sont nocifs pour la santé et peuvent nuire au comportement social.
            
            3. RESPONSABILITÉ DE L’UTILISATEUR
            L’usage de l’application est entièrement sous la responsabilité de l’utilisateur, ou de ses responsables légaux s’il est mineur.
            Le développeur ne pourra être tenu responsable en cas de :
            - mauvaise utilisation,
            - détournement,
            - interprétation erronée,
            - non-respect des lois locales,
            - décision prise sur la base des données affichées.
            
            L’application ne peut en aucun cas être utilisée pour engager une responsabilité pénale ou civile, ni comme preuve dans un contexte judiciaire, que ce soit pour l’utilisateur ou contre le développeur.
            
            4. PROTECTION DES MINEURS ET VIGILANCE PARENTALE
            Même si l’application ne collecte aucune donnée personnelle, il est rappelé qu’il est essentiel de surveiller les usages numériques des mineurs.
            Parents, tuteurs et responsables légaux doivent veiller :
            - à la santé,
            - au bien-être mental,
            - au comportement social,
            - à l’usage modéré des outils numériques.
            
            5. DONNÉES PERSONNELLES ET CONFIDENTIALITÉ
            Aucune inscription n’est requise.
            Aucune donnée personnelle n’est collectée, stockée ou transmise.
            Si l’utilisateur saisit un prénom, celui-ci est utilisé uniquement dans l’application et dans les fichiers exportés/importés stockés sur son propre téléphone.
            
            Il est de la responsabilité de l’utilisateur :
            - de protéger son smartphone,
            - de maintenir ses logiciels de sécurité,
            - d’adopter de bonnes pratiques de protection des données.
            
            6. PUBLICITÉS ET RÉMUNÉRATION
            La version gratuite contient un bandeau publicitaire destiné à financer le développement.
            La version premium permet une utilisation sans publicité.
            
            Cette application est le fruit d’un travail personnel d’un créateur indépendant.
            Les publicités servent uniquement à soutenir le développement, la maintenance et l’amélioration de l’application.
            
            7. NEUTRALITÉ, INDÉPENDANCE ET ABSENCE DE LIENS EXTÉRIEURS
            Le développeur est un particulier indépendant.
            L’application n’a aucun lien avec :
            - l’État ou les administrations,
            - les entreprises du tabac,
            - les producteurs d’alcool,
            - les industries du cannabis,
            - toute organisation criminelle ou cartel.
            
            L’application ne vise à remplacer ou concurrencer aucune autre application portant un nom similaire.
            
            8. PROPRIÉTÉ INTELLECTUELLE
            Le nom, la conception, le contenu, les textes et les fonctionnalités de l’application sont protégés par le droit d’auteur.
            Toute reproduction, copie ou redistribution non autorisée est interdite.
            
            Même sans dépôt officiel payant, les droits d’auteur sont automatiquement appliqués selon la loi.
            
            9. LIMITES TECHNIQUES, COMPATIBILITÉS ET RISQUES
            L’application peut rencontrer des bugs, erreurs ou pertes de données selon :
            - le modèle du téléphone,
            - la version d’Android,
            - l’état du système,
            - le stockage disponible,
            - l’environnement logiciel.
            
            Le développeur ne peut être tenu responsable en cas de :
            - perte de données,
            - dysfonctionnement matériel ou logiciel,
            - corruption de fichiers,
            - incompatibilité partielle ou totale.
            
            La version gratuite permet de tester l’application avant tout achat.
            Aucun remboursement n’est possible.
            
            Un changement de smartphone ou de compte Google peut nécessiter de racheter l’application selon les règles du Play Store.
            
            10. DÉVELOPPEMENT ET RÔLE DE L’IA
            L’application a été développée grâce à un travail personnel et à l’utilisation d’outils d’intelligence artificielle pour optimiser certains textes et portions de code.
            Le créateur reste l’auteur final de l’ensemble du projet.
                    """.trimIndent(),
                    "warning_resources_link" to "📞 Ressources et numéros utiles",
                    "warning_checkbox_age" to "☑️ Je suis majeur(e)",
                    "warning_checkbox_noshow" to "Ne plus afficher ce message",
                    "warning_btn_quit" to "Quitter",
                    "warning_btn_accept" to "J'accepte et continuer",
            
                    // Dialog ressources
                    "resources_title" to "📞 Ressources et numéros utiles",
                    "resources_content" to """
            📞 RESSOURCES UTILES – AIDE, SOUTIEN, INFORMATIONS
            
            Ce module regroupe plusieurs ressources d’aide, d’information ou de soutien pour les personnes confrontées au tabac, à l’alcool, au cannabis ou à d’autres difficultés. Les numéros ci-dessous peuvent varier selon les pays, et certaines lignes ne sont accessibles qu’à partir de zones géographiques spécifiques. L’utilisateur doit vérifier la disponibilité locale des numéros ou services.
            
            1. TABAC – ARRÊT, CONSEILS, SOUTIEN
            • France – Tabac Info Service : 39 89
              Ligne officielle d’accompagnement à l’arrêt du tabac. Conseillers spécialisés, suivi personnalisé, informations sur les substituts nicotiniques et les méthodes d’arrêt.
            • Canada – QuitNow : 1 877 455 2233
            • Belgique – Tabac Stop : 0800 111 00
            • Suisse – Stop Tabac : 0848 000 181
            • Informations internationales : se référer aux lignes d’aide locales ou aux dispositifs de santé publique du pays.
            
            2. ALCOOL – AIDE, PRÉVENTION, SOUTIEN
            • France – Alcool Info Service : 0 980 980 930
              Anonyme et gratuit. Conseils, écoute, soutien, orientation. Disponible 7j/7.
            • Canada – Alcooliques Anonymes : 1 877 404 2242
            • Belgique – Ligne Drogues & Alcool : 078 15 15 15
            • Suisse – Addiction Suisse : 021 321 29 11
            
            3. CANNABIS – INFORMATION & ACCOMPAGNEMENT
            • France – Drogues Info Service : 0 800 23 13 13
              Informations officielles sur les substances, les risques et les aides disponibles.
            • Belgique – Infor-Drogues : 02 227 52 52
            • Suisse – Ligne Drogue : 0848 133 133
            
            4. DÉTRESSE, URGENCES PSYCHOLOGIQUES & SOUTIEN ÉMOTIONNEL
            Certains usages excessifs de tabac, d’alcool ou de cannabis peuvent masquer une souffrance psychologique ou sociale. En cas de détresse, plusieurs numéros sont disponibles.
            
            • France – Suicide Écoute : 01 45 39 40 00
            • France – Numéro national de prévention du suicide : 3114
            • France – SOS Amitié : 09 72 39 40 50
            • Belgique – Télé-Accueil : 107
            • Suisse – La Main Tendue : 143
            • Canada – Service de prévention du suicide : 1 833 456 4566
            
            5. URGENCES
            Les numéros d’urgence varient selon les pays. Exemples :
            • Union Européenne – 112
            • France – Samu 15, Police 17, Pompiers 18, Urgence unique 112
            • États-Unis – 911
            • Canada – 911
            L’utilisateur est invité à vérifier les numéros officiels du pays dans lequel il se trouve.
            
            6. UTILISATION RESPONSABLE ET INFORMATIONS COMPLÉMENTAIRES
            Les ressources présentées n’ont aucun lien avec StopAddict. Elles sont fournies uniquement à titre informatif. L’utilisateur reste libre de les contacter ou non.
            Le recours à des professionnels de santé est recommandé en cas de difficultés physiques, psychologiques, sociales ou familiales.
            
            7. RAPPEL IMPORTANT
            L’application n’a pas pour vocation à diagnostiquer, traiter ou prévenir une maladie.
            Elle ne remplace pas l’avis d’un professionnel de santé.
            En cas d’urgence ou de danger immédiat, contacter les services d’urgence du pays où l’on se trouve.
        """.trimIndent(),
        "resources_btn_close" to "Fermer",
        
        // Console debug
        "console_title" to "CONSOLE DEBUG STOPADDICT",
        "console_version" to "Version",
        "console_version_free" to "Gratuite",
        "console_version_paid" to "Payante",
        "console_langue" to "Langue",
        "console_date" to "Date",
        "console_build" to "Build",
        "console_device" to "Device",
        "console_android" to "Android",
        "console_app_state" to "État Application",
        "console_age_accepted" to "Age accepté",
        "console_warning_shown" to "Warning shown",
        "console_error_prefs" to "Erreur lecture prefs",
        "console_logs_db" to "Logs Database",
        "console_consos_jour" to "Consommations jour",
        "console_no_conso" to "Aucune consommation",
        "console_error_db" to "Erreur lecture DB",
        "console_logs_selectable" to "Logs sélectionnables ✓",
        "console_btn_close" to "Fermer"
    )

    // ==================== ENGLISH ====================
    private val TRADUCTIONS_EN = mapOf(
        "tab_accueil" to "Home",
        "tab_stats" to "Stats",
        "tab_calendrier" to "Calendar",
        "tab_habitudes" to "Habits & Will",
        "tab_reglages" to "Settings",
        
        "warning_title" to "⚠️ Warning - Adults Only (18+)",
        "warning_message" to "Stop Addict is a self-monitoring and assistance app for reducing/stopping consumption (tobacco, alcohol, cannabis).\n\n" +
                              "Reserved for people aged 18 and over, having reached the age of majority in their country of residence or country visited.\n\n" +
                              "Does not promote these products.\n\n" +
                              "Does not replace medical, psychological or social support. In case of difficulty, consult a professional.\n\n" +
                              "Use Stop Addict responsibly.",
        "warning_resources_link" to "📞 Resources and helplines",
        "warning_checkbox_age" to "☑️ I am an adult, I am 18 years old or older",
        "warning_checkbox_noshow" to "Do not show this message again",
        "warning_btn_quit" to "Quit",
        "warning_btn_accept" to "I accept and continue",
        
        "resources_title" to "📞 Need help?",
        "resources_content" to "📞 Resources and helplines\n\n" +
                               "🚨 Emergency: 112 (EU) / 911 (US/CA)\n\n" +
                               "🇬🇧 UK\n" +
                               "• NHS Smoking: 0300 123 1044\n" +
                               "• Alcohol helpline: 0300 123 1110\n" +
                               "• FRANK drugs: 0300 123 6600\n\n" +
                               "🌍 Check local resources in your country.",
        "resources_btn_close" to "Close",
        
        "console_title" to "DEBUG CONSOLE STOPADDICT",
        "console_version" to "Version",
        "console_version_free" to "Free",
        "console_version_paid" to "Paid",
        "console_langue" to "Language",
        "console_date" to "Date",
        "console_build" to "Build",
        "console_device" to "Device",
        "console_android" to "Android",
        "console_app_state" to "App State",
        "console_age_accepted" to "Age accepted",
        "console_warning_shown" to "Warning shown",
        "console_error_prefs" to "Error reading prefs",
        "console_logs_db" to "Database Logs",
        "console_consos_jour" to "Daily consumptions",
        "console_no_conso" to "No consumption",
        "console_error_db" to "Error reading DB",
        "console_logs_selectable" to "Selectable logs ✓",
        "console_btn_close" to "Close"
    )

    // ==================== ESPAÑOL ====================
    private val TRADUCTIONS_ES = mapOf(
        "tab_accueil" to "Inicio",
        "tab_stats" to "Estadísticas",
        "tab_calendrier" to "Calendario",
        "tab_habitudes" to "Hábitos y Voluntad",
        "tab_reglages" to "Ajustes",
        
        "warning_title" to "⚠️ Advertencia - Solo adultos (18+)",
        "warning_message" to "Stop Addict es una aplicación de automonitoreo y ayuda para reducir/detener el consumo (tabaco, alcohol, cannabis).\n\n" +
                              "Reservada para personas de 18 años o más, que hayan alcanzado la mayoría de edad en su país de residencia o país visitado.\n\n" +
                              "No promueve estos productos.\n\n" +
                              "No reemplaza el apoyo médico, psicológico o social. En caso de dificultad, consulte a un profesional.\n\n" +
                              "Use Stop Addict de manera responsable.",
        "warning_resources_link" to "📞 Recursos y líneas de ayuda",
        "warning_checkbox_age" to "☑️ Soy adulto, tengo 18 años o más",
        "warning_checkbox_noshow" to "No mostrar este mensaje de nuevo",
        "warning_btn_quit" to "Salir",
        "warning_btn_accept" to "Acepto y continúo",
        
        "resources_title" to "📞 ¿Necesitas ayuda?",
        "resources_content" to "📞 Recursos y líneas de ayuda\n\n" +
                               "🚨 Emergencias: 112 (UE) / 911 (América)\n\n" +
                               "🇪🇸 ESPAÑA\n" +
                               "• Tabaquismo: 900 111 000\n" +
                               "• Alcohol: 900 161 515\n" +
                               "• Drogas: 900 16 15 15\n\n" +
                               "🌍 Consulta los recursos locales en tu país.",
        "resources_btn_close" to "Cerrar",
        
        "console_title" to "CONSOLA DEBUG STOPADDICT",
        "console_version" to "Versión",
        "console_version_free" to "Gratuita",
        "console_version_paid" to "Pagada",
        "console_langue" to "Idioma",
        "console_date" to "Fecha",
        "console_build" to "Build",
        "console_device" to "Dispositivo",
        "console_android" to "Android",
        "console_app_state" to "Estado aplicación",
        "console_age_accepted" to "Edad aceptada",
        "console_warning_shown" to "Advertencia mostrada",
        "console_error_prefs" to "Error lectura prefs",
        "console_logs_db" to "Logs Base de datos",
        "console_consos_jour" to "Consumos diarios",
        "console_no_conso" to "Sin consumo",
        "console_error_db" to "Error lectura BD",
        "console_logs_selectable" to "Logs seleccionables ✓",
        "console_btn_close" to "Cerrar"
    )

    // ==================== PORTUGUÊS ====================
    private val TRADUCTIONS_PT = mapOf(
        "tab_accueil" to "Início",
        "tab_stats" to "Estatísticas",
        "tab_calendrier" to "Calendário",
        "tab_habitudes" to "Hábitos e Vontade",
        "tab_reglages" to "Configurações",
        
        "warning_title" to "⚠️ Aviso - Apenas adultos (18+)",
        "warning_message" to "Stop Addict é um aplicativo de automonitoramento e ajuda para reduzir/parar o consumo (tabaco, álcool, cannabis).\n\n" +
                              "Reservado para pessoas com 18 anos ou mais, tendo atingido a maioridade em seu país de residência ou país visitado.\n\n" +
                              "Não promove esses produtos.\n\n" +
                              "Não substitui acompanhamento médico, psicológico ou social. Em caso de dificuldade, consulte um profissional.\n\n" +
                              "Use Stop Addict de forma responsável.",
        "warning_resources_link" to "📞 Recursos e linhas de ajuda",
        "warning_checkbox_age" to "☑️ Sou adulto, tenho 18 anos ou mais",
        "warning_checkbox_noshow" to "Não mostrar esta mensagem novamente",
        "warning_btn_quit" to "Sair",
        "warning_btn_accept" to "Aceito e continuo",
        
        "resources_title" to "📞 Precisa de ajuda?",
        "resources_content" to "📞 Recursos e linhas de ajuda\n\n" +
                               "🚨 Emergências: 112 (UE) / 192 (BR - SAMU)\n\n" +
                               "🇧🇷 BRASIL\n" +
                               "• Tabagismo: 0800 722 6001\n" +
                               "• CVV: 188 (apoio emocional)\n" +
                               "• CAPS-AD (álcool/drogas)\n\n" +
                               "🌍 Consulte os recursos locais no seu país.",
        "resources_btn_close" to "Fechar",
        
        "console_title" to "CONSOLE DEBUG STOPADDICT",
        "console_version" to "Versão",
        "console_version_free" to "Gratuita",
        "console_version_paid" to "Paga",
        "console_langue" to "Idioma",
        "console_date" to "Data",
        "console_build" to "Build",
        "console_device" to "Dispositivo",
        "console_android" to "Android",
        "console_app_state" to "Estado aplicação",
        "console_age_accepted" to "Idade aceita",
        "console_warning_shown" to "Aviso mostrado",
        "console_error_prefs" to "Erro leitura prefs",
        "console_logs_db" to "Logs Base de dados",
        "console_consos_jour" to "Consumos diários",
        "console_no_conso" to "Sem consumo",
        "console_error_db" to "Erro leitura BD",
        "console_logs_selectable" to "Logs selecionáveis ✓",
        "console_btn_close" to "Fechar"
    )

    // ==================== DEUTSCH ====================
    private val TRADUCTIONS_DE = mapOf(
        "tab_accueil" to "Startseite",
        "tab_stats" to "Statistiken",
        "tab_calendrier" to "Kalender",
        "tab_habitudes" to "Gewohnheiten & Wille",
        "tab_reglages" to "Einstellungen",
        
        "warning_title" to "⚠️ Warnung - Nur für Erwachsene (18+)",
        "warning_message" to "Stop Addict ist eine Selbstüberwachungs- und Hilfs-App zur Reduzierung/Beendigung des Konsums (Tabak, Alkohol, Cannabis).\n\n" +
                              "Reserviert für Personen ab 18 Jahren, die in ihrem Wohnsitzland oder besuchten Land volljährig sind.\n\n" +
                              "Bewirbt diese Produkte nicht.\n\n" +
                              "Ersetzt keine medizinische, psychologische oder soziale Unterstützung. Bei Schwierigkeiten konsultieren Sie einen Fachmann.\n\n" +
                              "Verwenden Sie Stop Addict verantwortungsvoll.",
        "warning_resources_link" to "📞 Ressourcen und Hilfslinien",
        "warning_checkbox_age" to "☑️ Ich bin erwachsen, ich bin 18 Jahre oder älter",
        "warning_checkbox_noshow" to "Diese Nachricht nicht mehr anzeigen",
        "warning_btn_quit" to "Beenden",
        "warning_btn_accept" to "Ich akzeptiere und fahre fort",
        
        "resources_title" to "📞 Brauchen Sie Hilfe?",
        "resources_content" to "📞 Ressourcen und Hilfslinien\n\n" +
                               "🚨 Notfall: 112 (EU) / 110 (DE)\n\n" +
                               "🇩🇪 DEUTSCHLAND\n" +
                               "• Rauchfrei: 0800 8 31 31 31\n" +
                               "• Sucht & Drogen: 01806 31 30 31\n" +
                               "• Telefonseelsorge: 0800 111 0 111\n\n" +
                               "🌍 Prüfen Sie lokale Ressourcen in Ihrem Land.",
        "resources_btn_close" to "Schließen",
        
        "console_title" to "DEBUG-KONSOLE STOPADDICT",
        "console_version" to "Version",
        "console_version_free" to "Kostenlos",
        "console_version_paid" to "Bezahlt",
        "console_langue" to "Sprache",
        "console_date" to "Datum",
        "console_build" to "Build",
        "console_device" to "Gerät",
        "console_android" to "Android",
        "console_app_state" to "App-Status",
        "console_age_accepted" to "Alter akzeptiert",
        "console_warning_shown" to "Warnung angezeigt",
        "console_error_prefs" to "Fehler beim Lesen der Prefs",
        "console_logs_db" to "Datenbank-Logs",
        "console_consos_jour" to "Täglicher Verbrauch",
        "console_no_conso" to "Kein Verbrauch",
        "console_error_db" to "Fehler beim Lesen der DB",
        "console_logs_selectable" to "Auswählbare Logs ✓",
        "console_btn_close" to "Schließen"
    )

    // ==================== ITALIANO ====================
    private val TRADUCTIONS_IT = mapOf(
        "tab_accueil" to "Home",
        "tab_stats" to "Statistiche",
        "tab_calendrier" to "Calendario",
        "tab_habitudes" to "Abitudini e Volontà",
        "tab_reglages" to "Impostazioni",
        
        "warning_title" to "⚠️ Avviso - Solo adulti (18+)",
        "warning_message" to "Stop Addict è un'app di auto-monitoraggio e aiuto per ridurre/interrompere il consumo (tabacco, alcol, cannabis).\n\n" +
                              "Riservata a persone di 18 anni o più, che hanno raggiunto la maggiore età nel loro paese di residenza o paese visitato.\n\n" +
                              "Non promuove questi prodotti.\n\n" +
                              "Non sostituisce il supporto medico, psicologico o sociale. In caso di difficoltà, consultare un professionista.\n\n" +
                              "Usa Stop Addict in modo responsabile.",
        "warning_resources_link" to "📞 Risorse e linee di aiuto",
        "warning_checkbox_age" to "☑️ Sono adulto, ho 18 anni o più",
        "warning_checkbox_noshow" to "Non mostrare più questo messaggio",
        "warning_btn_quit" to "Esci",
        "warning_btn_accept" to "Accetto e continuo",
        
        "resources_title" to "📞 Hai bisogno di aiuto?",
        "resources_content" to "📞 Risorse e linee di aiuto\n\n" +
                               "🚨 Emergenza: 112 (UE) / 118 (IT - urgenza)\n\n" +
                               "🇮🇹 ITALIA\n" +
                               "• Istituto Superiore Sanità: 800 554 088\n" +
                               "• Telefono Verde Alcol: 800 632 000\n" +
                               "• SerD (Servizi Dipendenze)\n\n" +
                               "🌍 Consulta le risorse locali nel tuo paese.",
        "resources_btn_close" to "Chiudi",
        
        "console_title" to "CONSOLE DEBUG STOPADDICT",
        "console_version" to "Versione",
        "console_version_free" to "Gratuita",
        "console_version_paid" to "A pagamento",
        "console_langue" to "Lingua",
        "console_date" to "Data",
        "console_build" to "Build",
        "console_device" to "Dispositivo",
        "console_android" to "Android",
        "console_app_state" to "Stato applicazione",
        "console_age_accepted" to "Età accettata",
        "console_warning_shown" to "Avviso mostrato",
        "console_error_prefs" to "Errore lettura prefs",
        "console_logs_db" to "Log Database",
        "console_consos_jour" to "Consumi giornalieri",
        "console_no_conso" to "Nessun consumo",
        "console_error_db" to "Errore lettura DB",
        "console_logs_selectable" to "Log selezionabili ✓",
        "console_btn_close" to "Chiudi"
    )

    // ==================== РУССКИЙ ====================
    private val TRADUCTIONS_RU = mapOf(
        "tab_accueil" to "Главная",
        "tab_stats" to "Статистика",
        "tab_calendrier" to "Календарь",
        "tab_habitudes" to "Привычки и Воля",
        "tab_reglages" to "Настройки",
        
        "warning_title" to "⚠️ Предупреждение - Только для взрослых (18+)",
        "warning_message" to "Stop Addict - это приложение для самоконтроля и помощи в сокращении/прекращении потребления (табак, алкоголь, каннабис).\n\n" +
                              "Предназначено для лиц старше 18 лет, достигших совершеннолетия в стране проживания или посещаемой стране.\n\n" +
                              "Не пропагандирует эти продукты.\n\n" +
                              "Не заменяет медицинскую, психологическую или социальную поддержку. В случае трудностей обратитесь к специалисту.\n\n" +
                              "Используйте Stop Addict ответственно.",
        "warning_resources_link" to "📞 Ресурсы и телефоны помощи",
        "warning_checkbox_age" to "☑️ Я взрослый, мне 18 лет или больше",
        "warning_checkbox_noshow" to "Больше не показывать это сообщение",
        "warning_btn_quit" to "Выход",
        "warning_btn_accept" to "Принимаю и продолжаю",
        
        "resources_title" to "📞 Нужна помощь?",
        "resources_content" to "📞 Ресурсы и телефоны помощи\n\n" +
                               "🚨 Скорая помощь: 112 (EU) / 103 (RU)\n\n" +
                               "🇷🇺 РОССИЯ\n" +
                               "• Телефон доверия: 8-800-2000-122\n" +
                               "• Нарко-стоп: 8-800-333-44-44\n" +
                               "• Анонимная помощь\n\n" +
                               "🌍 Проверьте местные ресурсы в вашей стране.",
        "resources_btn_close" to "Закрыть",
        
        "console_title" to "КОНСОЛЬ ОТЛАДКИ STOPADDICT",
        "console_version" to "Версия",
        "console_version_free" to "Бесплатная",
        "console_version_paid" to "Платная",
        "console_langue" to "Язык",
        "console_date" to "Дата",
        "console_build" to "Сборка",
        "console_device" to "Устройство",
        "console_android" to "Android",
        "console_app_state" to "Состояние приложения",
        "console_age_accepted" to "Возраст принят",
        "console_warning_shown" to "Предупреждение показано",
        "console_error_prefs" to "Ошибка чтения настроек",
        "console_logs_db" to "Логи базы данных",
        "console_consos_jour" to "Ежедневное потребление",
        "console_no_conso" to "Нет потребления",
        "console_error_db" to "Ошибка чтения БД",
        "console_logs_selectable" to "Выбираемые логи ✓",
        "console_btn_close" to "Закрыть"
    )

    // ==================== العربية ====================
    private val TRADUCTIONS_AR = mapOf(
        "tab_accueil" to "الرئيسية",
        "tab_stats" to "الإحصائيات",
        "tab_calendrier" to "التقويم",
        "tab_habitudes" to "العادات والإرادة",
        "tab_reglages" to "الإعدادات",
        
        "warning_title" to "⚠️ تحذير - للبالغين فقط (18+)",
        "warning_message" to "Stop Addict هو تطبيق للمراقبة الذاتية والمساعدة في تقليل/إيقاف الاستهلاك (التبغ، الكحول، القنب).\n\n" +
                              "مخصص للأشخاص الذين يبلغون 18 عامًا أو أكثر، وبلغوا سن الرشد في بلد إقامتهم أو البلد الذي يزورونه.\n\n" +
                              "لا يروج لهذه المنتجات.\n\n" +
                              "لا يحل محل الدعم الطبي أو النفسي أو الاجتماعي. في حالة الصعوبة، استشر أخصائيًا.\n\n" +
                              "استخدم Stop Addict بمسؤولية.",
        "warning_resources_link" to "📞 الموارد وخطوط المساعدة",
        "warning_checkbox_age" to "☑️ أنا بالغ، عمري 18 عامًا أو أكثر",
        "warning_checkbox_noshow" to "لا تظهر هذه الرسالة مرة أخرى",
        "warning_btn_quit" to "خروج",
        "warning_btn_accept" to "أوافق وأواصل",
        
        "resources_title" to "📞 هل تحتاج مساعدة؟",
        "resources_content" to "📞 الموارد وخطوط المساعدة\n\n" +
                               "🚨 الطوارئ: 112 (EU)\n\n" +
                               "🌍 تحقق من الموارد المحلية في بلدك\n" +
                               "• خطوط المساعدة النفسية\n" +
                               "• مراكز الإدمان\n" +
                               "• الدعم الاجتماعي",
        "resources_btn_close" to "إغلاق",
        
        "console_title" to "وحدة التحكم STOPADDICT",
        "console_version" to "الإصدار",
        "console_version_free" to "مجاني",
        "console_version_paid" to "مدفوع",
        "console_langue" to "اللغة",
        "console_date" to "التاريخ",
        "console_build" to "البناء",
        "console_device" to "الجهاز",
        "console_android" to "أندرويد",
        "console_app_state" to "حالة التطبيق",
        "console_age_accepted" to "العمر مقبول",
        "console_warning_shown" to "التحذير معروض",
        "console_error_prefs" to "خطأ في قراءة التفضيلات",
        "console_logs_db" to "سجلات قاعدة البيانات",
        "console_consos_jour" to "الاستهلاك اليومي",
        "console_no_conso" to "لا يوجد استهلاك",
        "console_error_db" to "خطأ في قراءة قاعدة البيانات",
        "console_logs_selectable" to "سجلات قابلة للتحديد ✓",
        "console_btn_close" to "إغلاق"
    )

    // ==================== हिन्दी ====================
    private val TRADUCTIONS_HI = mapOf(
        "tab_accueil" to "होम",
        "tab_stats" to "आंकड़े",
        "tab_calendrier" to "कैलेंडर",
        "tab_habitudes" to "आदतें और इच्छाशक्ति",
        "tab_reglages" to "सेटिंग्स",
        
        "warning_title" to "⚠️ चेतावनी - केवल वयस्कों के लिए (18+)",
        "warning_message" to "Stop Addict एक स्व-निगरानी और सहायता ऐप है जो उपभोग (तंबाकू, शराब, भांग) को कम करने/बंद करने में मदद करता है।\n\n" +
                              "18 वर्ष या उससे अधिक उम्र के लोगों के लिए आरक्षित है, जो अपने निवास देश या दौरा किए गए देश में वयस्कता की आयु तक पहुंच चुके हैं।\n\n" +
                              "इन उत्पादों को बढ़ावा नहीं देता है।\n\n" +
                              "चिकित्सा, मनोवैज्ञानिक या सामाजिक सहायता का विकल्प नहीं है। कठिनाई की स्थिति में, एक पेशेवर से परामर्श लें।\n\n" +
                              "Stop Addict का जिम्मेदारी से उपयोग करें।",
        "warning_resources_link" to "📞 संसाधन और हेल्पलाइन",
        "warning_checkbox_age" to "☑️ मैं वयस्क हूं, मैं 18 वर्ष या उससे अधिक का हूं",
        "warning_checkbox_noshow" to "यह संदेश फिर से न दिखाएं",
        "warning_btn_quit" to "बाहर निकलें",
        "warning_btn_accept" to "मैं स्वीकार करता हूं और जारी रखता हूं",
        
        "resources_title" to "📞 मदद चाहिए?",
        "resources_content" to "📞 संसाधन और हेल्पलाइन\n\n" +
                               "🚨 आपातकाल: 112\n\n" +
                               "🇮🇳 भारत\n" +
                               "• राष्ट्रीय हेल्पलाइन: 1800-11-0031\n" +
                               "• मानसिक स्वास्थ्य: 08046110007\n" +
                               "• नशा मुक्ति केंद्र\n\n" +
                               "🌍 अपने देश में स्थानीय संसाधनों की जांच करें।",
        "resources_btn_close" to "बंद करें",
        
        "console_title" to "डीबग कंसोल STOPADDICT",
        "console_version" to "संस्करण",
        "console_version_free" to "मुफ्त",
        "console_version_paid" to "सशुल्क",
        "console_langue" to "भाषा",
        "console_date" to "तारीख",
        "console_build" to "बिल्ड",
        "console_device" to "डिवाइस",
        "console_android" to "एंड्रॉइड",
        "console_app_state" to "ऐप स्थिति",
        "console_age_accepted" to "आयु स्वीकृत",
        "console_warning_shown" to "चेतावनी दिखाई गई",
        "console_error_prefs" to "प्राथमिकताएं पढ़ने में त्रुटि",
        "console_logs_db" to "डेटाबेस लॉग",
        "console_consos_jour" to "दैनिक उपभोग",
        "console_no_conso" to "कोई उपभोग नहीं",
        "console_error_db" to "डेटाबेस पढ़ने में त्रुटि",
        "console_logs_selectable" to "चयन योग्य लॉग ✓",
        "console_btn_close" to "बंद करें"
    )

    // ==================== 日本語 ====================
    private val TRADUCTIONS_JA = mapOf(
        "tab_accueil" to "ホーム",
        "tab_stats" to "統計",
        "tab_calendrier" to "カレンダー",
        "tab_habitudes" to "習慣と意志",
        "tab_reglages" to "設定",
        
        "warning_title" to "⚠️ 警告 - 成人向け (18+)",
        "warning_message" to "Stop Addictは、消費（タバコ、アルコール、大麻）の削減/停止を支援する自己監視アプリです。\n\n" +
                              "居住国または訪問国で成年に達した18歳以上の方を対象としています。\n\n" +
                              "これらの製品を宣伝するものではありません。\n\n" +
                              "医療、心理、社会的サポートの代替品ではありません。困難な場合は、専門家にご相談ください。\n\n" +
                              "Stop Addictを責任を持って使用してください。",
        "warning_resources_link" to "📞 リソースとヘルプライン",
        "warning_checkbox_age" to "☑️ 私は成人です、18歳以上です",
        "warning_checkbox_noshow" to "このメッセージを再度表示しない",
        "warning_btn_quit" to "終了",
        "warning_btn_accept" to "同意して続行",
        
        "resources_title" to "📞 助けが必要ですか？",
        "resources_content" to "📞 リソースとヘルプライン\n\n" +
                               "🚨 緊急: 110 / 119\n\n" +
                               "🇯🇵 日本\n" +
                               "• こころの健康相談: 0570-064-556\n" +
                               "• いのちの電話: 0570-783-556\n" +
                               "• 各自治体の相談窓口\n\n" +
                               "🌍 お住まいの国の地域リソースを確認してください。",
        "resources_btn_close" to "閉じる",
        
        "console_title" to "デバッグコンソール STOPADDICT",
        "console_version" to "バージョン",
        "console_version_free" to "無料",
        "console_version_paid" to "有料",
        "console_langue" to "言語",
        "console_date" to "日付",
        "console_build" to "ビルド",
        "console_device" to "デバイス",
        "console_android" to "Android",
        "console_app_state" to "アプリの状態",
        "console_age_accepted" to "年齢承認済み",
        "console_warning_shown" to "警告表示済み",
        "console_error_prefs" to "設定読み込みエラー",
        "console_logs_db" to "データベースログ",
        "console_consos_jour" to "1日の消費",
        "console_no_conso" to "消費なし",
        "console_error_db" to "データベース読み込みエラー",
        "console_logs_selectable" to "選択可能なログ ✓",
        "console_btn_close" to "閉じる"
    )
}
