package com.stopaddict

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
            "NL" -> TRADUCTIONS_NL
            "ZH" -> TRADUCTIONS_ZH
            "ZHT" -> TRADUCTIONS_ZHT
            else -> {
                StopAddictLogger.w(TAG, "Langue inconnue: $codeLangue, fallback FR")
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
        "warning_title" to "⚠️ Avertissement - Public majeur(e)",
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
                    "warning_btn_accept" to "J'accepte et je continue",
            
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
        "console_warning_shown" to "Avertissement affiché",
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
        
       // Dialog avertissement majorité
        "warning_title" to "⚠️ Warning – Adults Only",
        "warning_message" to """
                    ⚠️ LEGAL NOTICE
                    
                    Using this application implies that you have carefully read the following information.
                    The user must review all of the following elements before any use.
                    
                    1. AGE VERIFICATION AND COMPLIANCE WITH LOCAL LAWS
                    The user must confirm that they are of legal age according to the laws of their country of residence and in any country where they use the application.
                    Legal ages regarding tobacco, cannabis and alcohol vary significantly from one country to another.
                    Example: a person considered an adult at 18 in the United Kingdom or Canada may still be considered underage under U.S. alcohol regulations.
                    
                    It is the user’s sole responsibility to verify the laws, restrictions and obligations applicable in any country or region regarding:
                    - the use of the application,
                    - the possession or consumption of cigarettes,
                    - the use of cannabis according to local regulations,
                    - the consumption of alcohol,
                    - any other regulated practice.
                    
                    The application must not be considered as encouraging, facilitating, recommending or promoting the use of regulated substances in any country.
                    
                    2. NO ENCOURAGEMENT TO CONSUME
                    Using this application does not imply that the user is a smoker or a consumer of alcohol or cannabis.
                    The application does not encourage, recommend, glamorise or normalise the consumption of cigarettes, joints, or alcohol.
                    
                    The application’s purpose is solely to:
                    - help track, visualise and analyse usage,
                    - raise awareness about consumption, habits and spending,
                    - support reduction or cessation.
                    
                    Real success is refusing a cigarette, a joint, or a drink — or never starting at all.
                    If you do not smoke or drink, continue protecting your health: tobacco, cannabis and alcohol are harmful and can negatively affect behaviour and social interactions.
                    
                    3. USER RESPONSIBILITY
                    The use of the application is entirely under the user's responsibility, or that of their legal guardians if underage.
                    The developer cannot be held responsible for:
                    - misuse,
                    - diversion from intended use,
                    - incorrect interpretation,
                    - failure to comply with local laws,
                    - decisions made based on displayed data.
                    
                    The application cannot be used to establish civil or criminal liability, nor as evidence in any legal context, for or against the user or the developer.
                    
                    4. PROTECTION OF MINORS AND PARENTAL SUPERVISION
                    Even though the application collects no personal data, it is essential to remain vigilant regarding minors’ digital usage.
                    Parents, guardians and legal representatives should monitor:
                    - health and wellbeing,
                    - mental and emotional state,
                    - social behaviour,
                    - general use of digital tools.
                    
                    5. PERSONAL DATA AND PRIVACY
                    No registration is required.
                    No personal data is collected, stored or transmitted.
                    If a first name is entered, it is used only within the application or in exported/imported files stored on the user’s own device.
                    
                    It is the user's responsibility to:
                    - protect their smartphone,
                    - maintain security software,
                    - adopt good data protection practices.
                    
                    6. ADVERTISING AND FUNDING
                    The free version includes a banner advertisement to support development.
                    The premium version allows usage without ads.
                    
                    This application is the personal work of an independent creator.
                    Advertisements exist solely to support development, maintenance and improvements.
                    
                    7. NEUTRALITY, INDEPENDENCE AND NO EXTERNAL AFFILIATION
                    The developer is an independent individual.
                    The application has no ties with:
                    - governments or public institutions,
                    - tobacco companies,
                    - alcohol manufacturers,
                    - cannabis industries,
                    - any criminal organisation or cartel.
                    
                    The application is not intended to replace or compete with any other similarly named application.
                    
                    8. INTELLECTUAL PROPERTY
                    The name, design, text, content and features of the application are protected by copyright law.
                    Any reproduction, copying or redistribution without authorisation is prohibited.
                    
                    Copyright applies automatically, even without paid registration.
                    
                    9. TECHNICAL LIMITATIONS, COMPATIBILITY AND RISKS
                    The application may encounter bugs, errors or data loss depending on:
                    - device model,
                    - Android version,
                    - system condition,
                    - available storage,
                    - software environment.
                    
                    The developer cannot be held responsible for:
                    - data loss,
                    - hardware/software malfunction,
                    - corrupted files,
                    - partial or total incompatibility.
                    
                    The free version allows users to test the application before purchasing.
                    No refunds are possible.
                    
                    A change of smartphone or Google account may require repurchasing the application depending on Play Store policies.
                    
                    10. DEVELOPMENT AND ROLE OF AI
                    The application was built through personal work and the use of AI tools to optimize text and code.
                    The creator remains the sole author of the entire project.
                """.trimIndent(),
        "warning_resources_link" to "📞 Useful Help & Support Numbers",
        "warning_checkbox_age" to "☑️ I confirm I am of legal age",
        "warning_checkbox_noshow" to "Do not show this message again",
        "warning_btn_quit" to "Quit",
        "warning_btn_accept" to "I accept and continue",
        
        // Dialog ressources
        "resources_title" to "📞 Help & Useful Support Numbers",
        "resources_content" to """
                    📞 USEFUL RESOURCES – HELP, SUPPORT & INFORMATION
                    
                    This module provides official or widely recognised resources for people facing difficulties related to tobacco, alcohol, cannabis or emotional distress. Availability depends on your country or region.  
                    Always check the local availability of hotlines in your area.
                    
                    1. TOBACCO – QUITLINES, SUPPORT & COUNSELLING
                    • United States – 1-800-QUIT-NOW (1-800-784-8669)  
                      National quitline offering personalised support and cessation programs.
                    • United Kingdom – NHS Smokefree Helpline: 0300 123 1044  
                    • Canada – QuitNow: 1 877 455 2233  
                    • Australia – Quitline: 13 78 48  
                    • International – Check your local health authorities for national quitlines.
                    
                    2. ALCOHOL – HELP, PREVENTION & SUPPORT
                    • United States – SAMHSA National Helpline: 1-800-662-HELP (4357)  
                      Free, confidential, 24/7 support for alcohol and mental health issues.
                    • United Kingdom – Drinkline: 0300 123 1110  
                    • Canada – Alcoholics Anonymous: 1 877 404 2242  
                    • Australia – Alcohol Drug Information Service (ADIS): state numbers vary (example NSW: 1800 250 015)
                    
                    3. CANNABIS – INFORMATION & ASSISTANCE
                    • United States – SAMHSA Drug Information: 1-800-662-HELP  
                    • Canada – Canada Health Drug Info: 1-866-366-3667  
                    • United Kingdom – FRANK Drugs Helpline: 0300 123 6600  
                    • Australia – National Alcohol and Other Drugs Hotline: 1800 250 015
                    
                    4. EMOTIONAL DISTRESS, CRISIS & MENTAL HEALTH SUPPORT
                    Excessive consumption can hide psychological or emotional suffering.  
                    If you or someone you know is struggling, the following hotlines may help:
                    
                    • United States – Suicide & Crisis Lifeline: 988  
                    • United Kingdom – Samaritans: 116 123  
                    • Canada – Suicide Prevention Service: 1 833 456 4566  
                    • Australia – Lifeline: 13 11 14  
                    
                    5. EMERGENCY NUMBERS
                    Emergency numbers vary by country. Examples:
                    • United States – 911  
                    • Canada – 911  
                    • United Kingdom – 999 or 112  
                    • Australia – 000  
                    • European Union – 112  
                    Always verify the official emergency numbers of the country you are in.
                    
                    6. RESPONSIBLE USE & ADDITIONAL INFORMATION
                    These resources are not affiliated with StopAddict.  
                    They are provided solely for informational purposes.  
                    Consulting health professionals is recommended for any physical, emotional or social difficulties.
                    
                    7. IMPORTANT REMINDER
                    This application does not diagnose, treat or prevent any illness.  
                    It does not replace medical advice.  
                    In case of immediate danger or emergency, contact the official emergency services of your area.
                """.trimIndent(),
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
        
            // Dialog avertissement majorité
            "warning_title" to "⚠️ Advertencia – Solo para adultos",
            "warning_message" to """
                        ⚠️ AVISO LEGAL
                        
                        El uso de esta aplicación implica la lectura atenta del siguiente aviso.
                        El usuario debe leer toda la información antes de utilizar la aplicación.
                        
                        1. VERIFICACIÓN DE MAYORÍA DE EDAD Y CUMPLIMIENTO DE LAS LEYES LOCALES
                        El usuario debe confirmar que es mayor de edad según las leyes de su país de residencia y de cualquier país donde utilice la aplicación.
                        Las edades legales para tabaco, cannabis y alcohol varían entre países.
                        Ejemplo: una persona mayor de edad en España (18 años) puede considerarse menor según las leyes de alcohol en Estados Unidos.
                        
                        Es responsabilidad exclusiva del usuario verificar las leyes, restricciones y obligaciones del país o región respecto a:
                        - el uso de la aplicación,
                        - la posesión o consumo de cigarrillos,
                        - el uso de cannabis según la normativa local,
                        - el consumo de alcohol,
                        - cualquier otra actividad regulada.
                        
                        La aplicación no debe interpretarse como una invitación, facilitación o recomendación al consumo de sustancias reguladas.
                        
                        2. AUSENCIA DE INCITACIÓN AL CONSUMO
                        El uso de la aplicación no implica que el usuario sea fumador o consumidor de alcohol o cannabis.
                        La aplicación no incentiva, no recomienda, no glorifica ni normaliza el consumo de tabaco, cannabis o alcohol.
                        
                        El único propósito de la aplicación es:
                        - ayudar a contar, visualizar y analizar,
                        - sensibilizar sobre hábitos, consumos y gastos,
                        - apoyar la reducción o el abandono.
                        
                        El verdadero éxito es rechazar un cigarrillo, un porro o una bebida, o no empezar nunca.
                        Si no fumas ni bebes, continúa protegiendo tu salud: tabaco, cannabis y alcohol son nocivos.
                        
                        3. RESPONSABILIDAD DEL USUARIO
                        El uso de la aplicación es totalmente responsabilidad del usuario, o de sus tutores legales si es menor de edad.
                        El desarrollador no es responsable de:
                        - uso inadecuado,
                        - desvíos del uso previsto,
                        - interpretaciones erróneas,
                        - incumplimiento de leyes locales,
                        - decisiones basadas en los datos mostrados.
                        
                        La aplicación no puede utilizarse como prueba legal ni para atribuir responsabilidad civil o penal.
                        
                        4. PROTECCIÓN DE MENORES Y SUPERVISIÓN PARENTAL
                        Aunque la aplicación no recoge datos personales, es esencial vigilar el uso digital de los menores.
                        Padres y tutores deben supervisar:
                        - salud y bienestar,
                        - estado emocional,
                        - comportamiento social,
                        - uso de herramientas digitales.
                        
                        5. DATOS PERSONALES Y PRIVACIDAD
                        No se requiere registro.
                        No se recopilan, almacenan ni transmiten datos personales.
                        Si se introduce un nombre, solo se utiliza dentro de la aplicación o en archivos exportados/importados en el propio dispositivo.
                        
                        El usuario debe:
                        - proteger su teléfono,
                        - mantener medidas de seguridad,
                        - adoptar buenas prácticas de privacidad.
                        
                        6. PUBLICIDAD Y FINANCIACIÓN
                        La versión gratuita contiene un banner publicitario que financia el desarrollo.
                        La versión premium elimina los anuncios.
                        
                        Esta aplicación es el trabajo personal de un creador independiente.
                        Los anuncios sirven exclusivamente para mantener y mejorar la aplicación.
                        
                        7. NEUTRALIDAD, INDEPENDENCIA Y AUSENCIA DE VÍNCULOS EXTERNOS
                        El desarrollador es independiente.
                        La aplicación no tiene vínculos con:
                        - gobiernos o administraciones,
                        - industrias del tabaco,
                        - productores de alcohol,
                        - industria del cannabis,
                        - organizaciones criminales o cárteles.
                        
                        La aplicación no sustituye ni compite con otras aplicaciones con nombres similares.
                        
                        8. PROPIEDAD INTELECTUAL
                        El nombre, diseño, textos, contenidos y funciones están protegidos por derechos de autor.
                        Toda reproducción o distribución no autorizada está prohibida.
                        
                        9. LIMITACIONES TÉCNICAS Y RIESGOS
                        La aplicación puede presentar errores o pérdida de datos según:
                        - modelo del dispositivo,
                        - versión de Android,
                        - estado del sistema,
                        - almacenamiento disponible,
                        - entorno del software.
                        
                        El desarrollador no es responsable de:
                        - pérdida de datos,
                        - fallos técnicos,
                        - archivos dañados,
                        - incompatibilidades parciales o totales.
                        
                        La versión gratuita permite probar la aplicación antes de comprarla.
                        No se realizan reembolsos.
                        
                        Un cambio de dispositivo o cuenta de Google puede requerir una nueva compra según las reglas del Play Store.
                        
                        10. DESARROLLO Y USO DE IA
                        La aplicación fue creada mediante trabajo personal con apoyo ocasional de herramientas de inteligencia artificial.
                        El creador sigue siendo el autor único del proyecto.
                    """.trimIndent(),
            "warning_resources_link" to "📞 Recursos y líneas de ayuda",
            "warning_checkbox_age" to "☑️ Confirmo que soy mayor de edad",
            "warning_checkbox_noshow" to "No mostrar este mensaje de nuevo",
            "warning_btn_quit" to "Salir",
            "warning_btn_accept" to "Acepto y continúo",
            
            // Dialog ressources
            "resources_title" to "📞 Recursos y líneas de ayuda",
            "resources_content" to """
                        📞 RECURSOS ÚTILES – AYUDA, APOYO E INFORMACIÓN
                        
                        Este módulo reúne recursos oficiales o reconocidos para personas que enfrentan dificultades relacionadas con tabaco, alcohol, cannabis o malestar emocional.  
                        La disponibilidad depende del país o región.  
                        Verifica siempre los números locales de tu zona.
                        
                        1. TABACO – LÍNEAS DE APOYO, DEJAR DE FUMAR
                        • España – Línea de ayuda para dejar de fumar: 900 111 000  
                          Apoyo anónimo y gratuito para dejar el tabaco.
                        • México – Línea de la Vida: 800 911 2000  
                        • Argentina – Programa Nacional de Prevención: 0800 222 1002  
                        • Chile – Fono Drogas y Alcohol: 1412  
                        • Internacional – Consulta los servicios de salud locales.
                        
                        2. ALCOHOL – AYUDA Y PREVENCIÓN
                        • España – Servicio de Información sobre Alcohol: 900 161 515  
                        • México – Centros de Integración Juvenil: 800 911 2000  
                        • Argentina – Alcohólicos Anónimos: (011) 4325-1813  
                        • Colombia – Línea Psicoactiva: 018000 112 439  
                        
                        3. CANNABIS – INFORMACIÓN Y ACOMPAÑAMIENTO
                        • España – Plan Nacional sobre Drogas: 900 16 15 15  
                        • México – Línea de la Vida: 800 911 2000  
                        • Argentina – SEDRONAR: 141  
                        • Chile – SENDA Fono Drogas: 1412  
                        
                        4. MALTRATO EMOCIONAL, CRISIS Y SALUD MENTAL
                        • España – Teléfono de la Esperanza: 717 003 717  
                        • México – Línea de Atención Psicológica: 55 5259 8121  
                        • Argentina – Atención al Suicida: 135 (CABA y GBA)  
                        • Chile – Salud Responde: 600 360 7777  
                        • Colombia – Línea 106 – Atención emocional  
                        
                        5. EMERGENCIAS
                        • Unión Europea – 112  
                        • España – 112  
                        • México – 911  
                        • Argentina – 911  
                        • Chile – 131 (ambulancias), 133 (policía), 132 (bomberos)  
                        • Colombia – 123  
                        
                        6. USO RESPONSABLE Y INFORMACIÓN ADICIONAL
                        Estos recursos no están afiliados a StopAddict.  
                        Se ofrecen únicamente con fines informativos.  
                        Ante cualquier dificultad física, emocional o social, consulta a un profesional de la salud.
                        
                        7. RECORDATORIO IMPORTANTE
                        Esta aplicación no diagnostica, trata ni previene enfermedades.  
                        No sustituye la atención médica.  
                        En caso de emergencia o peligro inmediato, contacta los servicios oficiales de tu país.
                    """.trimIndent(),
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
        
        // Dialog avertissement majorité
        "warning_title" to "⚠️ Aviso – Apenas para adultos",
        "warning_message" to """
                    ⚠️ AVISO LEGAL
                    
                    O uso deste aplicativo implica a leitura atenta do aviso a seguir.
                    O usuário deve ler todas as informações antes de utilizar o aplicativo.
                    
                    1. VERIFICAÇÃO DE MAIORIDADE E CUMPRIMENTO DAS LEIS LOCAIS
                    O usuário deve confirmar que é maior de idade de acordo com as leis do seu país de residência e de qualquer país onde utilize o aplicativo.
                    As idades legais para tabaco, cannabis e álcool variam entre países.
                    Exemplo: uma pessoa maior de idade no Brasil ou Portugal pode ser considerada menor segundo as leis de álcool nos Estados Unidos.
                    
                    É responsabilidade exclusiva do usuário verificar as leis, restrições e obrigações do país ou região relacionadas:
                    - ao uso do aplicativo,
                    - à posse ou consumo de cigarros,
                    - ao uso de cannabis segundo a legislação local,
                    - ao consumo de álcool,
                    - a qualquer outra prática regulamentada.
                    
                    O aplicativo não incentiva, não facilita, não recomenda nem promove o consumo de substâncias regulamentadas.
                    
                    2. AUSÊNCIA DE INCENTIVO AO CONSUMO
                    O uso do aplicativo não pressupõe que o usuário seja fumante ou consumidor de álcool ou cannabis.
                    O aplicativo não incentiva, não recomenda, não glamouriza nem normaliza o consumo de tabaco, cannabis ou álcool.
                    
                    O objetivo do aplicativo é exclusivamente:
                    - ajudar a contar, visualizar e analisar,
                    - conscientizar sobre hábitos, consumo e gastos,
                    - apoiar a redução ou o abandono.
                    
                    O verdadeiro sucesso é recusar um cigarro, um baseado ou uma bebida — ou nunca começar.
                    Se você não fuma nem bebe, continue protegendo sua saúde: tabaco, cannabis e álcool são nocivos.
                    
                    3. RESPONSABILIDADE DO USUÁRIO
                    O uso do aplicativo é totalmente responsabilidade do usuário, ou de seus responsáveis legais caso seja menor.
                    O desenvolvedor não pode ser responsabilizado em caso de:
                    - uso inadequado,
                    - desvio da finalidade original,
                    - interpretação incorreta,
                    - descumprimento de leis locais,
                    - decisões tomadas com base nos dados exibidos.
                    
                    O aplicativo não pode ser utilizado como prova legal nem para atribuir responsabilidade civil ou penal.
                    
                    4. PROTEÇÃO DE MENORES E SUPERVISÃO PARENTAL
                    Embora o aplicativo não colete dados pessoais, é essencial supervisionar o uso digital por menores.
                    Pais e responsáveis devem observar:
                    - saúde e bem-estar,
                    - estado emocional,
                    - comportamento social,
                    - uso geral de ferramentas digitais.
                    
                    5. DADOS PESSOAIS E PRIVACIDADE
                    Não é necessário cadastro.
                    Nenhum dado pessoal é coletado, armazenado ou transmitido.
                    Se um nome é inserido, ele é usado apenas dentro do aplicativo ou em arquivos exportados/importados no próprio dispositivo.
                    
                    O usuário deve:
                    - proteger seu smartphone,
                    - manter medidas de segurança,
                    - adotar boas práticas de proteção de dados.
                    
                    6. PUBLICIDADE E FINANCIAMENTO
                    A versão gratuita contém um banner publicitário para financiar o desenvolvimento.
                    A versão premium remove todos os anúncios.
                    
                    Este aplicativo é fruto do trabalho pessoal de um criador independente.
                    A publicidade serve exclusivamente para manter e melhorar o aplicativo.
                    
                    7. NEUTRALIDADE, INDEPENDÊNCIA E AUSÊNCIA DE LIGAÇÕES EXTERNAS
                    O desenvolvedor é independente.
                    O aplicativo não possui vínculo com:
                    - governos ou administrações,
                    - indústrias do tabaco,
                    - produtores de álcool,
                    - indústrias de cannabis,
                    - organizações criminosas ou cartéis.
                    
                    O aplicativo não substitui nem concorre com outros aplicativos de nome semelhante.
                    
                    8. PROPRIEDADE INTELECTUAL
                    O nome, design, textos, conteúdos e funcionalidades são protegidos por direitos autorais.
                    A reprodução ou distribuição não autorizada é proibida.
                    
                    9. LIMITAÇÕES TÉCNICAS, COMPATIBILIDADE E RISCOS
                    O aplicativo pode apresentar erros, falhas ou perda de dados dependendo de:
                    - modelo do dispositivo,
                    - versão do Android,
                    - estado do sistema,
                    - armazenamento disponível,
                    - ambiente de software.
                    
                    O desenvolvedor não pode ser responsabilizado por:
                    - perda de dados,
                    - falhas técnicas,
                    - arquivos corrompidos,
                    - incompatibilidades parciais ou totais.
                    
                    A versão gratuita permite testar o aplicativo antes da compra.
                    Não há possibilidade de reembolso.
                    
                    Alterações de smartphone ou conta Google podem exigir nova compra, de acordo com as regras do Play Store.
                    
                    10. DESENVOLVIMENTO E USO DE IA
                    O aplicativo foi criado por meio de trabalho pessoal com apoio pontual de ferramentas de inteligência artificial.
                    O criador continua sendo o autor integral do projeto.
                """.trimIndent(),
        "warning_resources_link" to "📞 Recursos e linhas de ajuda",
        "warning_checkbox_age" to "☑️ Confirmo que sou maior de idade",
        "warning_checkbox_noshow" to "Não mostrar esta mensagem novamente",
        "warning_btn_quit" to "Sair",
        "warning_btn_accept" to "Aceito e continuo",
        
        // Dialog ressources
        "resources_title" to "📞 Recursos e linhas de ajuda",
        "resources_content" to """
                    📞 RECURSOS ÚTEIS – AJUDA, APOIO E INFORMAÇÕES
                    
                    Este módulo reúne recursos oficiais ou amplamente reconhecidos para pessoas com dificuldades relacionadas a tabaco, álcool, cannabis ou sofrimento emocional.  
                    A disponibilidade varia conforme o país ou região.  
                    Verifique sempre os números locais da sua área.
                    
                    1. TABACO – APOIO PARA PARAR DE FUMAR
                    • Brasil – Disque Saúde Tabagismo: 0800 722 6001  
                      Informações e apoio oficial para parar de fumar.
                    • Portugal – SNS / Linha Saúde 24: 808 24 24 24  
                    • Moçambique / Angola – consulte serviços locais de saúde.
                    
                    2. ÁLCOOL – AJUDA E PREVENÇÃO
                    • Brasil – CAPS AD (Álcool e Drogas): atendimento em unidades locais  
                    • Brasil – Alcoólicos Anônimos: 11 3105-9007 (exemplo SP)  
                    • Portugal – Linha Vida / SICAD: 1414  
                    • Angola – Linha SOS: consulte recursos locais
                    
                    3. CANNABIS – INFORMAÇÃO E ACOMPANHAMENTO
                    • Brasil – Disque Saúde / Drogas: 132  
                    • Portugal – SICAD – Informação sobre substâncias: 808 259 259  
                    • Comunidade lusófona – consulte serviços oficiais de saúde pública
                    
                    4. APOIO EMOCIONAL, CRISE E SAÚDE MENTAL
                    • Brasil – CVV (Centro de Valorização da Vida): 188  
                    • Portugal – SOS Voz Amiga: 213 544 545 / 912 802 669 / 963 524 660  
                    • Angola – Linha de apoio psicológico (varia por província)  
                    • Moçambique – Serviços de apoio psicológico locais  
                    
                    5. EMERGÊNCIAS
                    • União Europeia – 112  
                    • Brasil – 192 (SAMU), 190 (Polícia), 193 (Bombeiros)  
                    • Portugal – 112  
                    • Angola – 113 (Polícia), 115 (Bombeiros)  
                    • Moçambique – 117 (Emergências)  
                    
                    6. USO RESPONSÁVEL E INFORMAÇÕES COMPLEMENTARES
                    Estes recursos não possuem vínculo com o StopAddict.  
                    São fornecidos apenas para fins informativos.  
                    Consulte profissionais de saúde em caso de dificuldade física, emocional ou social.
                    
                    7. LEMBRETE IMPORTANTE
                    Este aplicativo não diagnostica, trata ou previne doenças.  
                    Não substitui orientação médica.  
                    Em caso de emergência, contate os serviços oficiais de seu país.
                """.trimIndent(),
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
        
        // Dialog avertissement majorité
        "warning_title" to "⚠️ Warnung – Nur für Erwachsene",
        "warning_message" to """
                    ⚠️ RECHTLICHER HINWEIS
                    
                    Die Nutzung dieser App setzt das sorgfältige Lesen des folgenden Hinweises voraus.
                    Der Nutzer muss alle Informationen aufmerksam prüfen, bevor er die Anwendung verwendet.
                    
                    1. ALTERSNACHWEIS UND EINHALTUNG DER LOKALEN GESETZE
                    Der Nutzer muss bestätigen, dass er gemäß den Gesetzen seines Wohnsitzlandes sowie jedes Landes, in dem er die App verwendet, volljährig ist.
                    Die gesetzlichen Altersgrenzen für Tabak, Cannabis und Alkohol unterscheiden sich je nach Land erheblich.
                    Beispiel: Eine volljährige Person in Deutschland (18 Jahre) kann nach US-Gesetzen bezüglich Alkoholkonsum als minderjährig gelten.
                    
                    Es liegt ausschließlich in der Verantwortung des Nutzers, die lokalen Gesetze, Einschränkungen und Verpflichtungen zu prüfen, die sich beziehen auf:
                    - die Nutzung der Anwendung,
                    - den Besitz oder Konsum von Zigaretten,
                    - den Gebrauch von Cannabis gemäß lokaler Vorschriften,
                    - den Alkoholkonsum,
                    - jede weitere regulierte Tätigkeit.
                    
                    Die App darf nicht als Unterstützung oder Förderung des Konsums regulierter Substanzen ausgelegt werden.
                    
                    2. KEINE ANREGUNG ZUM KONSUM
                    Die Nutzung der App bedeutet nicht, dass der Nutzer Raucher oder Konsument von Alkohol oder Cannabis ist.
                    Die App fördert, normalisiert oder verherrlicht nicht den Konsum von Tabak, Cannabis oder Alkohol.
                    
                    Die App dient ausschließlich dazu:
                    - den Konsum zu erfassen, zu visualisieren und zu analysieren,
                    - Bewusstsein für Gewohnheiten, Konsum und Ausgaben zu schaffen,
                    - die Reduktion oder den Ausstieg zu unterstützen.
                    
                    Der wahre Erfolg besteht darin, eine Zigarette, einen Joint oder ein alkoholisches Getränk abzulehnen – oder gar nicht erst damit anzufangen.
                    Wenn Sie nicht rauchen oder trinken, schützen Sie weiterhin Ihre Gesundheit: Tabak, Cannabis und Alkohol sind gesundheitsschädlich.
                    
                    3. VERANTWORTUNG DES NUTZERS
                    Die Nutzung der App liegt vollständig in der Verantwortung des Nutzers oder seiner gesetzlichen Vertreter, falls er minderjährig ist.
                    Der Entwickler kann nicht haftbar gemacht werden für:
                    - Missbrauch der Anwendung,
                    - zweckentfremdete Nutzung,
                    - falsche Interpretation,
                    - Nichteinhaltung lokaler Gesetze,
                    - Entscheidungen, die auf Basis der angezeigten Daten getroffen werden.
                    
                    Die App kann nicht zur rechtlichen Beweisführung oder zur Feststellung zivil- oder strafrechtlicher Verantwortung verwendet werden.
                    
                    4. SCHUTZ VON MINDERJÄHRIGEN UND ELTERLICHE AUFSICHT
                    Auch wenn die Anwendung keine personenbezogenen Daten sammelt, ist es wichtig, den digitalen Umgang von Minderjährigen zu überwachen.
                    Eltern und Erziehungsberechtigte sollten achten auf:
                    - Gesundheit und Wohlbefinden,
                    - emotionale Stabilität,
                    - soziales Verhalten,
                    - den Umgang mit digitalen Geräten.
                    
                    5. DATENSCHUTZ UND PRIVATSPHÄRE
                    Kein Benutzerkonto erforderlich.
                    Es werden keine personenbezogenen Daten gesammelt, gespeichert oder übermittelt.
                    Wenn ein Vorname eingegeben wird, wird er nur in der App oder in exportierten/importierten Dateien auf dem eigenen Gerät verwendet.
                    
                    Der Nutzer ist dafür verantwortlich:
                    - sein Smartphone zu schützen,
                    - Sicherheitssoftware aktuell zu halten,
                    - gute Datenschutzpraktiken anzuwenden.
                    
                    6. WERBUNG UND FINANZIERUNG
                    Die kostenlose Version enthält Werbebanner zur Finanzierung der Entwicklung.
                    Die Premium-Version entfernt alle Werbeanzeigen.
                    
                    Die App ist das persönliche Projekt eines unabhängigen Entwicklers.
                    Werbung dient ausschließlich der Weiterentwicklung, Wartung und Verbesserung der App.
                    
                    7. NEUTRALITÄT, UNABHÄNGIGKEIT UND KEINE EXTERNEN VERBINDUNGEN
                    Der Entwickler handelt vollständig unabhängig.
                    Die App steht in keinerlei Verbindung zu:
                    - staatlichen Einrichtungen,
                    - Tabakindustrie,
                    - Alkoholherstellern,
                    - Cannabisindustrie,
                    - kriminellen Organisationen oder Kartellen.
                    
                    Die App ersetzt oder konkurriert keine andere App mit ähnlichem Namen.
                    
                    8. URHEBERRECHT
                    Name, Design, Inhalte, Texte und Funktionen sind urheberrechtlich geschützt.
                    Jede unerlaubte Reproduktion oder Verbreitung ist untersagt.
                    
                    9. TECHNISCHE GRENZEN, KOMPATIBILITÄT UND RISIKEN
                    Die App kann Fehler oder Datenverlust verursachen, abhängig von:
                    - Gerätemodell,
                    - Android-Version,
                    - Systemzustand,
                    - verfügbarem Speicher,
                    - Softwareumgebung.
                    
                    Der Entwickler haftet nicht für:
                    - Datenverlust,
                    - Geräte- oder Softwarefehler,
                    - beschädigte Dateien,
                    - teilweise oder vollständige Inkompatibilität.
                    
                    Die kostenlose Version ermöglicht das Testen vor dem Kauf.
                    Rückerstattungen sind nicht möglich.
                    
                    Ein Wechsel des Smartphones oder Google-Kontos kann gemäß Play-Store-Regeln einen erneuten Kauf erfordern.
                    
                    10. ENTWICKLUNG UND EINSATZ VON KI
                    Die Anwendung wurde mit persönlicher Arbeit und gelegentlicher Unterstützung von KI-Tools entwickelt.
                    Der Entwickler bleibt der alleinige Autor des gesamten Projekts.
                """.trimIndent(),
        "warning_resources_link" to "📞 Ressourcen und Hilfslinien",
        "warning_checkbox_age" to "☑️ Ich bestätige, dass ich volljährig bin",
        "warning_checkbox_noshow" to "Diese Nachricht nicht mehr anzeigen",
        "warning_btn_quit" to "Beenden",
        "warning_btn_accept" to "Ich akzeptiere und fahre fort",
        
        // Dialog ressources
        "resources_title" to "📞 Ressourcen und Hilfslinien",
        "resources_content" to """
                    📞 NÜTZLICHE RESSOURCEN – HILFE, BERATUNG UND INFORMATIONEN
                    
                    Dieses Modul enthält offizielle oder weithin anerkannte Ressourcen für Menschen, die Unterstützung im Zusammenhang mit Tabak, Alkohol, Cannabis oder emotionalem Stress benötigen.  
                    Die Verfügbarkeit hängt vom jeweiligen Land oder der Region ab.  
                    Bitte prüfen Sie immer die lokalen Telefonnummern.
                    
                    1. TABAK – AUFHÖRHILFEN UND BERATUNG
                    • Deutschland – Rauchfrei Telefon: 0800 8 31 31 31  
                      Offizielle Unterstützung zum Rauchstopp.
                    • Österreich – Rauchfrei Telefon: 0800 810 013  
                    • Schweiz – Stop Smoking Helpline: 0848 000 181  
                    
                    2. ALKOHOL – HILFE UND PRÄVENTION
                    • Deutschland – Sucht & Drogen Hotline: 01806 313031  
                    • Österreich – Suchtinfo / Beratung: 1455  
                    • Schweiz – Alkoholhilfe / Suchtberatung: regionale Nummern
                    
                    3. CANNABIS – INFORMATION UND BERATUNG
                    • Deutschland – Drugscouts / Suchtberatung: regionale Angebote  
                    • Österreich – Check-it! Drogeninfo: lokale Zentren  
                    • Schweiz – Sucht Schweiz: 021 321 29 11  
                    
                    4. EMOTIONALE BELASTUNG, KRISEN UND PSYCHISCHE GESUNDHEIT
                    • Deutschland – TelefonSeelsorge: 0800 111 0 111 / 0800 111 0 222  
                    • Österreich – TelefonSeelsorge: 142  
                    • Schweiz – Die Dargebotene Hand: 143  
                    
                    5. NOTFALLNUMMERN
                    • Europäische Union – 112  
                    • Deutschland – 110 (Polizei), 112 (Feuerwehr/Rettung)  
                    • Österreich – 112 (EU), 133 (Polizei), 144 (Rettung)  
                    • Schweiz – 112 (EU), 117 (Polizei), 118 (Feuerwehr), 144 (Sanität)  
                    
                    6. VERANTWORTUNGSVOLLE NUTZUNG & WEITERE INFORMATIONEN
                    Diese Ressourcen sind nicht mit StopAddict verbunden.  
                    Sie dienen ausschließlich zu Informationszwecken.  
                    Bei körperlichen, emotionalen oder sozialen Problemen wenden Sie sich an Fachpersonal.
                    
                    7. WICHTIGER HINWEIS
                    Diese App diagnostiziert, behandelt oder verhindert keine Krankheiten.  
                    Sie ersetzt keine medizinische Beratung.  
                    Wenden Sie sich im Notfall an die offiziellen Rettungsdienste Ihres Landes.
                """.trimIndent(),
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
        
        // Dialog avertissement majorité
        "warning_title" to "⚠️ Avviso – Solo per adulti",
        "warning_message" to """
                    ⚠️ AVVISO LEGALE
                    
                    L’utilizzo di questa applicazione implica la lettura attenta del seguente avviso.
                    L’utente deve leggere tutte le informazioni prima di utilizzare l’app.
                    
                    1. VERIFICA DELLA MAGGIORE ETÀ E RISPETTO DELLE LEGGI LOCALI
                    L’utente deve confermare di essere maggiorenne secondo le leggi del proprio paese di residenza e di qualsiasi paese in cui utilizzi l’app.
                    Le età legali per tabacco, cannabis e alcol differiscono ampiamente tra i vari paesi.
                    Esempio: una persona maggiorenne in Italia (18 anni) può risultare minorenne per quanto riguarda le leggi sul consumo di alcol negli Stati Uniti.
                    
                    È esclusiva responsabilità dell’utente verificare le normative, i divieti e gli obblighi vigenti nel paese o nella regione relativi a:
                    - uso dell’app,
                    - possesso o consumo di sigarette,
                    - utilizzo di cannabis secondo le norme locali,
                    - consumo di alcol,
                    - qualsiasi altra pratica regolamentata.
                    
                    L’app non deve essere interpretata come un invito, un incoraggiamento o una facilitazione del consumo di sostanze regolamentate.
                    
                    2. ASSENZA DI INCITAMENTO AL CONSUMO
                    L’utilizzo dell’app non implica che l’utente sia fumatore o consumatore di alcol o cannabis.
                    L’app non incentiva, non normalizza e non glorifica il consumo di tabacco, cannabis o alcol.
                    
                    L’unico scopo dell’app è:
                    - aiutare a contare, visualizzare e analizzare,
                    - sensibilizzare sui propri comportamenti, consumi e spese,
                    - sostenere la riduzione o l’interruzione del consumo.
                    
                    Il vero successo è rifiutare una sigaretta, uno spinello o un bicchiere — o non iniziare mai.
                    Se non fumi e non bevi, continua a proteggere la tua salute: tabacco, cannabis e alcol sono dannosi.
                    
                    3. RESPONSABILITÀ DELL’UTENTE
                    L’uso dell’app è interamente sotto la responsabilità dell’utente, o dei suoi tutori legali se minorenne.
                    Lo sviluppatore non può essere ritenuto responsabile in caso di:
                    - uso improprio,
                    - utilizzo non conforme allo scopo previsto,
                    - interpretazioni errate,
                    - mancato rispetto delle leggi locali,
                    - decisioni prese sulla base dei dati visualizzati.
                    
                    L’app non può essere utilizzata per attribuire responsabilità civili o penali, né come prova in procedimenti legali.
                    
                    4. PROTEZIONE DEI MINORI E SUPERVISIONE PARENTALE
                    Sebbene l’app non raccolga dati personali, è essenziale monitorare l’utilizzo digitale dei minori.
                    Genitori e tutori dovrebbero prestare attenzione a:
                    - salute e benessere,
                    - stato emotivo,
                    - comportamento sociale,
                    - uso degli strumenti digitali.
                    
                    5. DATI PERSONALI E PRIVACY
                    Non è necessaria alcuna registrazione.
                    Nessun dato personale viene raccolto, memorizzato o trasmesso.
                    Se viene inserito un nome, esso è utilizzato solo all’interno dell’app o nei file esportati/importati sul dispositivo dell’utente.
                    
                    L’utente è responsabile di:
                    - proteggere il proprio smartphone,
                    - mantenere adeguate misure di sicurezza,
                    - adottare buone pratiche di tutela dei dati.
                    
                    6. PUBBLICITÀ E FINANZIAMENTO
                    La versione gratuita contiene un banner pubblicitario per finanziare lo sviluppo.
                    La versione premium permette l’utilizzo senza pubblicità.
                    
                    Questa applicazione è il risultato del lavoro personale di uno sviluppatore indipendente.
                    La pubblicità serve esclusivamente a supportare miglioramenti, manutenzione e sviluppo continuo.
                    
                    7. NEUTRALITÀ, INDIPENDENZA E ASSENZA DI COLLEGAMENTI ESTERNI
                    Lo sviluppatore è indipendente.
                    L’app non ha alcun legame con:
                    - enti pubblici,
                    - industria del tabacco,
                    - produttori di alcol,
                    - industria della cannabis,
                    - organizzazioni criminali o cartelli.
                    
                    L’app non sostituisce né compete con altre applicazioni dal nome simile.
                    
                    8. PROPRIETÀ INTELLETTUALE
                    Nome, design, testi, contenuti e funzioni sono protetti dal diritto d’autore.
                    È vietata ogni riproduzione o distribuzione non autorizzata.
                    
                    9. LIMITAZIONI TECNICHE, COMPATIBILITÀ E RISCHI
                    L’app può presentare bug, errori o perdite di dati in base a:
                    - modello del dispositivo,
                    - versione Android,
                    - stato del sistema,
                    - memoria disponibile,
                    - ambiente software.
                    
                    Lo sviluppatore non è responsabile per:
                    - perdita di dati,
                    - malfunzionamenti hardware/software,
                    - file danneggiati,
                    - incompatibilità totali o parziali.
                    
                    La versione gratuita consente di provare l’app prima dell’acquisto.
                    Non sono previsti rimborsi.
                    
                    Un cambio di smartphone o account Google può richiedere un nuovo acquisto secondo le regole del Play Store.
                    
                    10. SVILUPPO E UTILIZZO DI IA
                    L’app è stata sviluppata attraverso lavoro personale con supporto occasionale di strumenti di intelligenza artificiale.
                    Lo sviluppatore rimane l’unico autore dell’intero progetto.
                """.trimIndent(),
        "warning_resources_link" to "📞 Risorse e linee di aiuto",
        "warning_checkbox_age" to "☑️ Confermo di essere maggiorenne",
        "warning_checkbox_noshow" to "Non mostrare più questo messaggio",
        "warning_btn_quit" to "Esci",
        "warning_btn_accept" to "Accetto e continuo",
        
        // Dialog ressources
        "resources_title" to "📞 Risorse e linee di aiuto",
        "resources_content" to """
                    📞 RISORSE UTILI – AIUTO, SOSTEGNO E INFORMAZIONI
                    
                    Questo modulo raccoglie risorse ufficiali o ampiamente riconosciute per persone che affrontano difficoltà legate a tabacco, alcol, cannabis o sofferenza emotiva.  
                    La disponibilità varia a seconda del paese o della regione.  
                    Verificare sempre i numeri locali della propria zona.
                    
                    1. TABACCO – SUPPORTO PER SMETTERE DI FUMARE
                    • Italia – Istituto Superiore di Sanità / Telefono Verde Fumo: 800 554 088  
                    • Italia – SerD (Servizi per le Dipendenze): numeri locali  
                    • Svizzera italiana – Linea Stop Tabacco: 0848 000 181  
                    
                    2. ALCOL – AIUTO E PREVENZIONE
                    • Italia – Telefono Verde Alcol: 800 632 000  
                    • Italia – SerD / Alcologia: contatti territoriali  
                    • Svizzera – Servizi cantonali di consulenza sulle dipendenze
                    
                    3. CANNABIS – INFORMAZIONE E ASSISTENZA
                    • Italia – Dipendenze / SerD: centri territoriali  
                    • Svizzera – Informazioni su sostanze: 0848 133 133  
                    • San Marino – Servizi sanitari locali
                    
                    4. SOSTEGNO EMOTIVO, CRISI E SALUTE MENTALE
                    • Italia – Telefono Amico: 02 2327 2327  
                    • Italia – Samaritans Italia: numeri regionali  
                    • Svizzera – La Mano Tesa (Linea 143)  
                    
                    5. EMERGENZE
                    • Unione Europea – 112  
                    • Italia – 112 (Numero Unico), 118 (emergenza sanitaria), 115 (vigili del fuoco), 113 (polizia)  
                    • Svizzera – 112 (UE), 117 (polizia), 118 (vigili del fuoco), 144 (ambulanza)  
                    
                    6. USO RESPONSABILE E INFORMAZIONI AGGIUNTIVE
                    Queste risorse non sono affiliate a StopAddict.  
                    Sono fornite esclusivamente a scopo informativo.  
                    Per difficoltà fisiche, emotive o sociali è consigliato rivolgersi a un professionista.
                    
                    7. PROMEMORIA IMPORTANTE
                    Questa applicazione non diagnostica, tratta o previene malattie.  
                    Non sostituisce il parere medico.  
                    In caso di emergenza, contattare i servizi ufficiali del proprio paese.
                """.trimIndent(),
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
        
        // Dialog avertissement majorité
        "warning_title" to "⚠️ Предупреждение – Только для взрослых",
        "warning_message" to """
                    ⚠️ ЮРИДИЧЕСКОЕ ПРЕДУПРЕЖДЕНИЕ
                    
                    Использование данного приложения подразумевает внимательное прочтение следующей информации.
                    Пользователь должен ознакомиться со всеми пунктами перед началом использования.
                    
                    1. ПРОВЕРКА СОВЕРШЕННОЛЕТИЯ И СОБЛЮДЕНИЕ МЕСТНЫХ ЗАКОНОВ
                    Пользователь должен подтвердить, что он является совершеннолетним согласно законам своей страны проживания, а также любой страны, где используется приложение.
                    Законы, регулирующие возрастной доступ к табаку, каннабису и алкоголю, существенно отличаются в разных странах.
                    Пример: человек, достигший совершеннолетия в России или Казахстане, может считаться несовершеннолетним по законодательству США в отношении алкоголя.
                    
                    Пользователь обязан самостоятельно проверить действующие законы, ограничения и правила, относящиеся к:
                    - использованию приложения,
                    - владению или потреблению табака,
                    - употреблению каннабиса в соответствии с местным законодательством,
                    - потреблению алкоголя,
                    - любой другой регулируемой деятельности.
                    
                    Приложение не является призывом, не поощряет и не пропагандирует употребление каких-либо веществ.
                    
                    2. ОТСУТСТВИЕ ПРИЗЫВА К ПОТРЕБЛЕНИЮ
                    Использование приложения не означает, что пользователь является курильщиком или потребителем алкоголя/каннабиса.
                    Приложение не поощряет, не нормализует и не представляет в привлекательном виде потребление табака, алкоголя или каннабиса.
                    
                    Единственная цель приложения:
                    - помощь в подсчёте, визуализации и анализе потребления,
                    - повышение осознанности относительно привычек, расходов и последствий,
                    - поддержка в снижении или полном отказе.
                    
                    Настоящий успех — отказаться от сигареты, косяка или алкогольного напитка либо никогда не начинать.
                    Если вы не употребляете — сохраняйте своё здоровье: табак, алкоголь и каннабис вредны.
                    
                    3. ОТВЕТСТВЕННОСТЬ ПОЛЬЗОВАТЕЛЯ
                    Пользователь несёт полную ответственность за использование приложения, а несовершеннолетние — их законные представители.
                    Разработчик не несёт ответственности за:
                    - неправильное использование,
                    - интерпретацию данных,
                    - нарушение местных законов,
                    - решения, принятые на основе информации приложения.
                    
                    Приложение не может быть использовано в качестве юридического доказательства или основания для гражданской/уголовной ответственности.
                    
                    4. ЗАЩИТА НЕСОВЕРШЕННОЛЕТНИХ И РОДИТЕЛЬСКИЙ КОНТРОЛЬ
                    Несмотря на отсутствие сбора персональных данных, важно контролировать цифровую активность несовершеннолетних.
                    Родителям и опекунам рекомендуется следить за:
                    - здоровьем и самочувствием,
                    - эмоциональным состоянием,
                    - социальным поведением,
                    - использованием цифровых устройств.
                    
                    5. ПЕРСОНАЛЬНЫЕ ДАННЫЕ И КОНФИДЕНЦИАЛЬНОСТЬ
                    Регистрация не требуется.
                    Персональные данные не собираются, не хранятся и не передаются.
                    Введённое имя используется только внутри приложения и в экспортируемых/импортируемых файлах на устройстве пользователя.
                    
                    Пользователь обязан:
                    - защищать своё устройство,
                    - использовать актуальные средства безопасности,
                    - соблюдать рекомендации по защите личных данных.
                    
                    6. РЕКЛАМА И ФИНАНСИРОВАНИЕ
                    Бесплатная версия приложения содержит рекламный баннер.
                    Премиум-версия предоставляет использование без рекламы.
                    
                    Приложение является результатом работы независимого разработчика.
                    Реклама используется исключительно для поддержки разработки и улучшений.
                    
                    7. НЕЙТРАЛЬНОСТЬ, НЕЗАВИСИМОСТЬ И ОТСУТСТВИЕ СВЯЗЕЙ
                    Разработчик является независимым частным лицом.
                    Приложение не связано с:
                    - государственными структурами,
                    - табачными компаниями,
                    - производителями алкоголя,
                    - индустрией каннабиса,
                    - криминальными организациями.
                    
                    Приложение не предназначено для замены или конкуренции с любыми другими приложениями похожего названия.
                    
                    8. АВТОРСКИЕ ПРАВА
                    Название, дизайн, текст и функциональность защищены авторским правом.
                    Любое несанкционированное копирование или распространение запрещено.
                    
                    9. ТЕХНИЧЕСКИЕ ОГРАНИЧЕНИЯ И РИСКИ
                    Возможны ошибки, сбои или потеря данных в зависимости от:
                    - модели устройства,
                    - версии Android,
                    - состояния системы,
                    - доступной памяти,
                    - установленного ПО.
                    
                    Разработчик не несёт ответственности за:
                    - потерю данных,
                    - поломки устройства или ПО,
                    - поврежденные файлы,
                    - несовместимость частичную или полную.
                    
                    Бесплатная версия позволяет протестировать приложение перед покупкой.
                    Возврат средств невозможен.
                    
                    Смена устройства или аккаунта Google может потребовать повторной покупки согласно правилам Play Store.
                    
                    10. РАЗРАБОТКА И ИСПОЛЬЗОВАНИЕ ИИ
                    Приложение было создано вручную с отдельными элементами, оптимизированными средствами искусственного интеллекта.
                    Разработчик является единственным автором проекта.
                """.trimIndent(),
        "warning_resources_link" to "📞 Ресурсы и телефоны помощи",
        "warning_checkbox_age" to "☑️ Я подтверждаю, что являюсь совершеннолетним",
        "warning_checkbox_noshow" to "Больше не показывать это сообщение",
        "warning_btn_quit" to "Выход",
        "warning_btn_accept" to "Принимаю и продолжаю",
        
        // Dialog ressources
        "resources_title" to "📞 Ресурсы и телефоны помощи",
        "resources_content" to """
                    📞 ПОЛЕЗНЫЕ РЕСУРСЫ – ПОМОЩЬ, ПОДДЕРЖКА И ИНФОРМАЦИЯ
                    
                    Ниже приведены официальные и признанные службы поддержки для людей, сталкивающихся с трудностями, связанными с табаком, алкоголем, каннабисом или эмоциональным состоянием.  
                    Номера могут различаться в зависимости от региона.  
                    Всегда проверяйте актуальность местных телефонов.
                    
                    1. ТАБАК – ПОМОЩЬ В ОТКАЗЕ ОТ КУРЕНИЯ
                    • Россия – Линия помощи: 8-800-200-02-00 (антитабачные консультации)  
                    • Беларусь – Линия здоровья: 801 100 201 01  
                    • Казахстан – Национальная линия здоровья: 1406  
                    
                    2. АЛКОГОЛЬ – ПОДДЕРЖКА И ПРОФИЛАКТИКА
                    • Россия – Телефон доверия по зависимостям: 8-800-333-44-44  
                    • Беларусь – Центры профилактики зависимостей: региональные номера  
                    • Казахстан – Единая служба психологической поддержки: 111  
                    
                    3. КАННАБИС – ИНФОРМАЦИОННАЯ ПОДДЕРЖКА
                    • Россия – Центры наркологической помощи: местные телефоны  
                    • Беларусь – Службы помощи при зависимостях: региональные контакты  
                    • Казахстан – Служба консультирования 1406  
                    
                    4. ЭМОЦИОНАЛЬНАЯ ПОДДЕРЖКА И КРИЗИСНЫЕ СИТУАЦИИ
                    • Россия – Детский телефон доверия: 8-800-2000-122  
                    • Россия – Психологическая помощь: 8-800-100-49-94  
                    • Беларусь – Линия доверия: 801 100 123 45  
                    • Казахстан – Национальная линия доверия: 111  
                    
                    5. СЛУЖБЫ ЭКСТРЕННОЙ ПОМОЩИ
                    • Европейский Союз – 112  
                    • Россия – 112 (единый номер), 103 (скорая), 102 (полиция), 101 (пожарные)  
                    • Беларусь – 112, 103, 102, 101  
                    • Казахстан – 112, 103, 102, 101  
                    
                    6. ОТВЕТСТВЕННОЕ ИСПОЛЬЗОВАНИЕ И ДОПОЛНИТЕЛЬНАЯ ИНФОРМАЦИЯ
                    Эти ресурсы не связаны с приложением StopAddict.  
                    Они предоставлены исключительно для справочных целей.  
                    При физических, эмоциональных или социальных трудностях рекомендуется обращаться к специалистам.
                    
                    7. ВАЖНОЕ НАПОМИНАНИЕ
                    Приложение не диагностирует, не лечит и не предотвращает заболевания.  
                    Оно не заменяет консультацию врача.  
                    В случае угрозы жизни или опасности необходимо обращаться в экстренные службы.
                """.trimIndent(),
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
        
        // Dialog avertissement majorité
        "warning_title" to "⚠️ تحذير – للبالغين فقط",
        "warning_message" to """
                    ⚠️ إشعار قانوني
                    
                    يتطلب استخدام هذا التطبيق قراءة هذا التحذير بعناية.
                    يجب على المستخدم الإطلاع على جميع المعلومات التالية قبل الاستخدام.
                    
                    1. التحقق من السن القانونية والامتثال للقوانين المحلية
                    يجب على المستخدم التأكد من أنه بلغ سن الرشد القانوني وفق قوانين بلد إقامته، وكذلك أي بلد يستخدم فيه التطبيق.
                    تختلف الأعمار القانونية المتعلقة بالتبغ والقنب والكحول بشكل كبير بين الدول.
                    مثال: قد يُعتبر الشخص البالغ 18 عامًا راشدًا في مصر أو تونس، ولكنه يعتبر قاصرًا وفق قوانين استهلاك الكحول في الولايات المتحدة.
                    
                    تقع على عاتق المستخدم كامل المسؤولية في التحقق من القوانين السارية والمتعلقة بـ:
                    - استخدام التطبيق،
                    - حيازة أو استهلاك السجائر،
                    - استخدام القنب وفق التشريعات المحلية،
                    - استهلاك الكحول،
                    - أي ممارسات أخرى خاضعة للتنظيم.
                    
                    هذا التطبيق لا يشجع، ولا يروج، ولا يسهل استهلاك أي مواد خاضعة للرقابة.
                    
                    2. عدم التحفيز على الاستهلاك
                    استخدام التطبيق لا يعني أن المستخدم مدخن أو مستهلك للكحول أو القنب.
                    التطبيق لا يشجع ولا يطبع ولا يعرض بشكل إيجابي استهلاك السجائر أو الكحول أو القنب.
                    
                    الهدف الوحيد للتطبيق هو:
                    - المساعدة على العدّ، والعرض، والتحليل،
                    - زيادة الوعي بالعادات والاستهلاك والمصاريف،
                    - دعم التقليل أو الإيقاف التام عن الاستهلاك.
                    
                    النجاح الحقيقي هو رفض سيجارة أو سيجارة ملفوفة أو مشروب كحولي — أو عدم البدء بها أصلًا.
                    إذا كنت لا تدخن أو لا تشرب، فحافظ على صحتك: التبغ والكحول والقنب مواد ضارة.
                    
                    3. مسؤولية المستخدم
                    استخدام التطبيق يقع بالكامل تحت مسؤولية المستخدم أو أوليائه الشرعيين إن كان قاصرًا.
                    لا يتحمل المطوّر أي مسؤولية عن:
                    - سوء الاستخدام،
                    - الاستخدام غير المقصود،
                    - التفسير الخاطئ،
                    - عدم الامتثال للقوانين المحلية،
                    - أي قرارات مبنية على البيانات المعروضة.
                    
                    لا يمكن استخدام التطبيق كدليل قانوني أو لإثبات المسؤولية المدنية أو الجنائية.
                    
                    4. حماية القاصرين والرقابة الأبوية
                    بالرغم من أن التطبيق لا يجمع أي بيانات شخصية، من الضروري مراقبة استخدام القاصرين للأدوات الرقمية.
                    يجب على الآباء والأوصياء مراقبة:
                    - الصحة الجسدية،
                    - الحالة العاطفية،
                    - السلوك الاجتماعي،
                    - استخدام الأجهزة الرقمية.
                    
                    5. البيانات الشخصية والخصوصية
                    لا يتطلب التطبيق أي تسجيل.
                    لا يتم جمع أو تخزين أو إرسال أي بيانات شخصية.
                    إذا أدخل المستخدم اسمًا، فإنه يُستخدم فقط داخل التطبيق أو في الملفات المخزّنة على جهازه.
                    
                    يتحمل المستخدم مسؤولية:
                    - حماية هاتفه،
                    - استخدام أدوات الحماية والتحديث،
                    - اتباع ممارسات سليمة للخصوصية.
                    
                    6. الإعلانات والتمويل
                    النسخة المجانية تحتوي على إعلانات للمساعدة في تمويل التطوير.
                    النسخة المدفوعة تتيح الاستخدام دون إعلانات.
                    
                    هذا التطبيق هو عمل فردي لمطوّر مستقل.
                    الإعلانات موجودة فقط لدعم التطوير والصيانة والتحسينات.
                    
                    7. الحياد والاستقلالية وعدم وجود روابط خارجية
                    المطوّر مستقل تمامًا.
                    التطبيق غير مرتبط بـ:
                    - أي جهات حكومية،
                    - شركات التبغ،
                    - منتجي الكحول،
                    - صناعة القنب،
                    - أي منظمات إجرامية.
                    
                    التطبيق لا يستهدف منافسة أو استبدال أي تطبيق آخر مشابه في الاسم.
                    
                    8. الملكية الفكرية
                    الاسم، التصميم، النصوص، المحتوى، والوظائف محمية بحقوق النشر.
                    يمنع أي نسخ أو إعادة توزيع دون إذن.
                    
                    9. القيود التقنية والمخاطر المحتملة
                    قد يواجه التطبيق أخطاءً أو فقدانًا للبيانات تبعًا لـ:
                    - نوع الجهاز،
                    - إصدار نظام Android،
                    - حالة النظام،
                    - السعة التخزينية،
                    - بيئة البرامج.
                    
                    المطوّر غير مسؤول عن:
                    - فقدان البيانات،
                    - الأعطال التقنية،
                    - تلف الملفات،
                    - عدم التوافق الجزئي أو الكامل.
                    
                    النسخة المجانية تمكّن المستخدم من تجربة التطبيق قبل الشراء.
                    لا توجد إمكانية لاسترجاع الأموال.
                    
                    قد يتطلب تغيير الهاتف أو حساب Google شراءً جديدًا حسب سياسة متجر Play.
                    
                    10. التطوير ودور الذكاء الاصطناعي
                    تم تطوير التطبيق بجهد شخصي مع الاستعانة ببعض أدوات الذكاء الاصطناعي لتحسين النصوص أو أجزاء من الكود.
                    يبقى المطوّر هو المؤلف الوحيد للمشروع.
                """.trimIndent(),
        "warning_resources_link" to "📞 الموارد وخطوط المساعدة",
        "warning_checkbox_age" to "☑️ أؤكد أنني بلغت السن القانونية",
        "warning_checkbox_noshow" to "لا تظهر هذه الرسالة مرة أخرى",
        "warning_btn_quit" to "خروج",
        "warning_btn_accept" to "أوافق وأواصل",
        
        // Dialog ressources
        "resources_title" to "📞 الموارد وخطوط المساعدة",
        "resources_content" to """
                    📞 موارد مفيدة – خطوط المساعدة، الدعم، والمعلومات
                    
                    يحتوي هذا القسم على موارد رسمية أو موثوقة لمساعدة الأشخاص الذين يواجهون صعوبة في التعامل مع التبغ أو الكحول أو القنب أو الضغوط العاطفية.  
                    تختلف الأرقام حسب البلد، ويجب دائمًا التحقق من الأرقام المحلية.
                    
                    1. الإقلاع عن التدخين – دعم واستشارات
                    • المغرب – خط الإقلاع عن التدخين: 080 100 47 47  
                    • مصر – الدعم الصحي: 15335  
                    • تونس – المساعدة الهاتفية: 8010 1111  
                    • الجزائر – مراكز الإقلاع عن التدخين: أرقام محلية  
                    
                    2. الكحول – دعم ومساعدة
                    • المغرب – الخط الوطني للدعم النفسي والاجتماعي: 080 200 47 47  
                    • مصر – الخط الساخن للصحة النفسية: 080 888 07 00  
                    • تونس – خدمات الدعم النفسي: 8010 1111  
                    
                    3. القنب / الإدمان – معلومات ومراكز علاج
                    • مصر – الخط الساخن للإدمان: 16023  
                    • المغرب – مراكز علاج الإدمان: أرقام محلية  
                    • تونس – مراكز معالجة الإدمان (SerT): أرقام محلية  
                    
                    4. الدعم العاطفي، الأزمات، والصحة النفسية
                    • لبنان – خط الحياة: 1564  
                    • الإمارات – دعم الصحة النفسية: 8004673  
                    • السعودية – مركز الاستشارات النفسية: 920033360  
                    • المغرب – الخط الوطني للدعم النفسي: 080 200 47 47  
                    
                    5. الطوارئ
                    • الاتحاد الأوروبي – 112  
                    • المغرب – 190 (شرطة)، 150 (درك)، 150/155 (إسعاف)  
                    • مصر – 122 (شرطة)، 123 (إسعاف)  
                    • السعودية – 911  
                    • الإمارات – 999  
                    • تونس – 197 (شرطة)، 190 (طوارئ)، 198 (حماية مدنية)  
                    
                    6. استخدام مسؤول ومعلومات إضافية
                    هذه الموارد غير مرتبطة بتطبيق StopAddict.  
                    تُقدّم فقط لأغراض معلوماتية.  
                    عند مواجهة مشاكل صحية أو نفسية أو اجتماعية، يجب استشارة متخصص.
                    
                    7. تذكير مهم
                    هذا التطبيق لا يشخّص ولا يعالج ولا يمنع الأمراض.  
                    ولا يغني عن استشارة طبيب مختص.  
                    في حالة الطوارئ أو الخطر، يجب الاتصال بخدمات الطوارئ الرسمية.
                """.trimIndent(),
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
        
        // Dialog avertissement majorité
        "warning_title" to "⚠️ चेतावनी – केवल वयस्कों के लिए",
        "warning_message" to """
                    ⚠️ कानूनी चेतावनी
                    
                    इस ऐप का उपयोग करने से पहले नीचे दिए गए सभी बिंदुओं को ध्यान से पढ़ना आवश्यक है।
                    उपयोगकर्ता को ऐप का उपयोग करने से पहले सभी जानकारी समझनी चाहिए।
                    
                    1. वयस्कता की पुष्टि और स्थानीय कानूनों का पालन
                    उपयोगकर्ता को यह पुष्टि करनी चाहिए कि वह अपने निवास देश के कानूनों के अनुसार वयस्क है, और किसी भी देश में जहाँ वह ऐप का उपयोग करता है।
                    तंबाकू, शराब और भांग के लिए कानूनी उम्र विभिन्न देशों में अलग-अलग होती है।
                    उदाहरण: भारत में 18 वर्ष का व्यक्ति कुछ देशों (जैसे अमेरिका) में शराब के नियमों के अनुसार नाबालिग माना जा सकता है।
                    
                    उपयोगकर्ता की पूरी जिम्मेदारी है कि वह निम्न से संबंधित सभी स्थानीय कानूनों की पुष्टि करे:
                    - ऐप का उपयोग,
                    - सिगरेट का सेवन या स्वामित्व,
                    - स्थानीय कानूनों के अनुसार भांग का उपयोग,
                    - शराब का सेवन,
                    - कोई भी अन्य नियामित गतिविधि।
                    
                    ऐप किसी भी प्रकार के मादक पदार्थों के सेवन को प्रोत्साहित, समर्थन या बढ़ावा नहीं देता।
                    
                    2. सेवन के लिए कोई प्रोत्साहन नहीं
                    ऐप का उपयोग यह नहीं दर्शाता कि उपयोगकर्ता धूम्रपान करता है या शराब/भांग का सेवन करता है।
                    ऐप धूम्रपान, शराब या भांग के सेवन को सामान्य, आकर्षक या प्रोत्साहित नहीं करता।
                    
                    ऐप का मुख्य उद्देश्य है:
                    - सेवन को गिनना, दिखाना और विश्लेषण करना,
                    - आदतों, खर्चों और जोखिमों के प्रति जागरूकता बढ़ाना,
                    - सेवन कम करने या पूरी तरह छोड़ने में सहायता करना।
                    
                    वास्तविक सफलता है—सिगरेट, शराब या किसी भी नशीले पदार्थ को मना करना, या फिर कभी शुरू ही न करना।
                    यदि आप नहीं पीते या नहीं धूम्रपान करते, तो यह आपकी सेहत के लिए बेहतर है—तंबाकू, शराब और भांग हानिकारक होते हैं।
                    
                    3. उपयोगकर्ता की जिम्मेदारी
                    ऐप का उपयोग पूरी तरह से उपयोगकर्ता की जिम्मेदारी है।  
                    यदि उपयोगकर्ता नाबालिग है, तो उसके अभिभावकों की जिम्मेदारी है।
                    डेवलपर किसी भी स्थिति में जिम्मेदार नहीं है:
                    - गलत उपयोग,
                    - दुरुपयोग या गलत व्याख्या,
                    - कानूनों का उल्लंघन,
                    - ऐप के डेटा के आधार पर लिए गए निर्णय।
                    
                    ऐप किसी भी कानूनी प्रमाण या नागरिक/आपराधिक उत्तरदायित्व के लिए उपयोग नहीं किया जा सकता।
                    
                    4. नाबालिगों की सुरक्षा और अभिभावकीय निगरानी
                    ऐप कोई व्यक्तिगत डेटा एकत्र नहीं करता, फिर भी नाबालिगों की डिजिटल गतिविधि की निगरानी आवश्यक है।
                    अभिभावकों को ध्यान देना चाहिए:
                    - स्वास्थ्य और कल्याण,
                    - भावनात्मक स्थिति,
                    - सामाजिक व्यवहार,
                    - डिजिटल उपकरणों के उपयोग पर।
                    
                    5. व्यक्तिगत डेटा और गोपनीयता
                    ऐप में पंजीकरण की आवश्यकता नहीं है।
                    कोई भी निजी डेटा एकत्र, संग्रहीत या साझा नहीं किया जाता।
                    यदि कोई नाम दर्ज किया जाता है, तो वह केवल ऐप और उपयोगकर्ता के अपने डिवाइस में संग्रहीत फ़ाइलों में उपयोग होता है।
                    
                    उपयोगकर्ता स्वयं जिम्मेदार है:
                    - अपने फ़ोन की सुरक्षा के लिए,
                    - सुरक्षा सॉफ़्टवेयर और अपडेट के लिए,
                    - अच्छी डेटा-गोपनीयता आदतों का पालन करने के लिए।
                    
                    6. विज्ञापन और वित्तपोषण
                    मुफ्त संस्करण में विज्ञापन शामिल हैं, जो विकास को समर्थन देते हैं।
                    प्रीमियम संस्करण बिना विज्ञापन के उपयोग की अनुमति देता है।
                    
                    यह ऐप एक स्वतंत्र डेवलपर का व्यक्तिगत प्रोजेक्ट है।
                    विज्ञापन केवल रखरखाव और सुधार के लिए उपयोग किए जाते हैं।
                    
                    7. निष्पक्षता, स्वतंत्रता और बाहरी संबद्धताओं का अभाव
                    डेवलपर पूरी तरह स्वतंत्र है।
                    ऐप का कोई संबंध नहीं है:
                    - सरकारी एजेंसियों,
                    - तंबाकू उद्योग,
                    - शराब निर्माताओं,
                    - भांग उद्योग,
                    - किसी भी आपराधिक संगठन से।
                    
                    यह ऐप किसी भी समान नाम वाले ऐप से प्रतिस्पर्धा या उसका विकल्प नहीं है।
                    
                    8. बौद्धिक संपदा
                    ऐप का नाम, डिज़ाइन, सामग्री और सुविधाएँ कॉपीराइट द्वारा संरक्षित हैं।
                    किसी भी प्रकार की अनधिकृत नकल या वितरण निषिद्ध है।
                    
                    9. तकनीकी सीमाएँ और जोखिम
                    ऐप में त्रुटियाँ, डेटा हानि या समस्याएँ हो सकती हैं, यह निर्भर करता है:
                    - डिवाइस मॉडल,
                    - Android संस्करण,
                    - सिस्टम स्थिति,
                    - उपलब्ध संग्रहण,
                    - सॉफ़्टवेयर वातावरण।
                    
                    डेवलपर किसी भी स्थिति में जिम्मेदार नहीं है:
                    - डेटा हानि,
                    - डिवाइस/सॉफ़्टवेयर त्रुटियाँ,
                    - फ़ाइल भ्रष्टाचार,
                    - आंशिक या पूर्ण असंगतता।
                    
                    मुफ्त संस्करण खरीदने से पहले परीक्षण की अनुमति देता है।
                    रिफंड उपलब्ध नहीं हैं।
                    
                    फ़ोन या Google खाते को बदलने पर Play Store की नीति के अनुसार पुनः खरीदारी आवश्यक हो सकती है।
                    
                    10. विकास और कृत्रिम बुद्धिमत्ता की भूमिका
                    ऐप व्यक्तिगत मेहनत और कुछ AI टूल्स की सहायता से विकसित किया गया है।
                    डेवलपर इस परियोजना का एकमात्र लेखक है।
                """.trimIndent(),
        "warning_resources_link" to "📞 संसाधन और हेल्पलाइन",
        "warning_checkbox_age" to "☑️ मैं पुष्टि करता हूं कि मैं वयस्क हूं",
        "warning_checkbox_noshow" to "यह संदेश फिर से न दिखाएं",
        "warning_btn_quit" to "बाहर निकलें",
        "warning_btn_accept" to "मैं स्वीकार करता हूं और जारी रखता हूं",
        
        // Dialog ressources
        "resources_title" to "📞 संसाधन और हेल्पलाइन",
        "resources_content" to """
                    📞 उपयोगी संसाधन – सहायता, समर्थन और जानकारी
                    
                    यह अनुभाग तंबाकू, शराब, भांग या भावनात्मक कठिनाइयों से जूझ रहे लोगों के लिए विश्वसनीय और मान्यता प्राप्त संसाधन प्रदान करता है।  
                    हेल्पलाइन नंबर क्षेत्र के अनुसार बदल सकते हैं।  
                    हमेशा अपने स्थानीय क्षेत्र के आधिकारिक नंबरों की पुष्टि करें।
                    
                    1. तंबाकू – धूम्रपान छोड़ने में सहायता
                    • भारत – राष्ट्रीय तंबाकू परामर्श हेल्पलाइन: 1800-11-2356  
                    • भारत – Quitline सहायता: 011-22901701  
                    • नेपाल – Tobacco Quitline: 1660-01-21212
                    
                    2. शराब – सहायता और परामर्श
                    • भारत – मानसिक स्वास्थ्य/शराब सहायता हेल्पलाइन: 08046110007  
                    • भारत – Alcohol De-Addiction Centres: स्थानीय नंबर  
                    • नेपाल/बांग्लादेश – स्थानीय मानसिक स्वास्थ्य सेवाएँ
                    
                    3. भांग/नशा – जानकारी और सहायता
                    • भारत – राष्ट्रीय नशा मुक्ति हेल्पलाइन: 1800-11-0031  
                    • बांग्लादेश – Addiction Counselling: स्थानीय केंद्र  
                    • नेपाल – Substance Support Lines: क्षेत्रीय नंबर
                    
                    4. भावनात्मक संकट, मानसिक स्वास्थ्य और समर्थन
                    • भारत – KIRAN मानसिक स्वास्थ्य हेल्पलाइन: 1800-599-0019  
                    • भारत – Snehi Emotional Support: 9582208181  
                    • नेपाल – Suicide Prevention: 1166  
                    • बांग्लादेश – Mental Health Hotline: 09666-787801  
                    
                    5. आपातकालीन नंबर
                    • भारत – 112 (एकीकृत आपातकाल), 100 (पुलिस), 101 (फायर), 102/108 (एम्बुलेंस)  
                    • नेपाल – 100 (पुलिस), 101 (फायर), 102 (एम्बुलेंस)  
                    • बांग्लादेश – 999  
                    
                    6. जिम्मेदार उपयोग और अतिरिक्त जानकारी
                    ये संसाधन StopAddict से संबद्ध नहीं हैं।  
                    इन्हें केवल जानकारी के उद्देश्य से प्रदान किया गया है।  
                    शारीरिक, भावनात्मक या सामाजिक कठिनाइयों में विशेषज्ञ से परामर्श लेना आवश्यक है।
                    
                    7. महत्वपूर्ण अनुस्मारक
                    यह ऐप किसी भी बीमारी का निदान, उपचार या रोकथाम नहीं करता।  
                    यह पेशेवर चिकित्सकीय सलाह का विकल्प नहीं है।  
                    आपात स्थिति में, अपने देश की आधिकारिक आपातकालीन सेवाओं से संपर्क करें।
                """.trimIndent(),
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
        
        // Dialog avertissement majorité
        "warning_title" to "⚠️ 警告 – 成人向け",
        "warning_message" to """
                    ⚠️ 法的注意事項
                    
                    このアプリを使用する前に、以下の内容を必ずお読みください。
                    利用者は、使用開始前にすべての情報を理解する必要があります。
                    
                    1. 成年確認および各国の法律遵守
                    利用者は、自身が居住国または利用国の法律において成年に達していることを確認しなければなりません。
                    タバコ、アルコール、大麻に関する成年年齢は国によって大きく異なります。
                    例：日本で成人でも、国によってはアルコールに関する法的基準が異なる場合があります。
                    
                    利用者は以下に関する現地法を自行で確認する責任があります：
                    - アプリの使用
                    - タバコの所持および使用
                    - 現地法に基づく大麻の扱い
                    - アルコールの購入・消費
                    - 規制対象となるその他の行為
                    
                    本アプリは、これらの物質の使用を促したり、推奨したりするものではありません。
                    
                    2. 消費を促すものではありません
                    このアプリの利用は、喫煙・飲酒・大麻使用者であることを意味するものではありません。
                    本アプリは、喫煙、飲酒、大麻使用を美化・正当化・推奨するものではありません。
                    
                    アプリの目的は以下の通りです：
                    - 消費量の記録・可視化・分析
                    - 習慣・消費・費用に対する意識向上
                    - 消費量の削減または停止を支援すること
                    
                    本当の成功は、タバコ・酒・大麻を断ること、あるいは最初から始めないことです。
                    吸わない・飲まない人はそのまま健康を守ってください。  
                    これらの物質は身体に有害です。
                    
                    3. 利用者の責任
                    アプリの使用は利用者の自己責任です。
                    未成年者が使用する場合は、保護者が責任を負います。
                    
                    開発者は以下について一切責任を負いません：
                    - 誤使用・不適切な使用
                    - 情報の誤解釈
                    - 現地法違反
                    - 表示されたデータをもとにした判断
                    
                    アプリは法律的証拠として利用することはできません。
                    
                    4. 未成年保護と保護者の監督
                    個人情報は収集していませんが、未成年のデジタル利用には監督が必要です。
                    
                    保護者は以下を確認することが推奨されます：
                    - 健康状態
                    - 心の状態
                    - 社会的行動
                    - デジタル機器の利用状況
                    
                    5. 個人情報とプライバシー
                    アカウント登録は不要です。
                    個人データの収集・保存・送信は一切行われません。
                    入力された名前は、アプリ内またはユーザーの端末に保存されるファイルでのみ使用されます。
                    
                    利用者は以下の責任を負います：
                    - 端末の保護
                    - セキュリティ設定と更新
                    - 適切なデータ保護対策
                    
                    6. 広告と資金提供
                    無料版には広告が表示され、アプリの開発を支援しています。
                    プレミアム版では広告は完全に削除されます。
                    
                    本アプリは独立開発者による個人プロジェクトです。
                    広告収益は開発と改善のためにのみ使用されます。
                    
                    7. 中立性・独立性および外部との無関係
                    開発者は独立した個人です。
                    本アプリは以下と一切関係ありません：
                    - 政府機関
                    - タバコ産業
                    - アルコールメーカー
                    - 大麻産業
                    - 犯罪組織
                    
                    類似名の他アプリとは無関係であり、競合も代替も意図していません。
                    
                    8. 著作権について
                    アプリ名、デザイン、文章、機能は著作権により保護されています。
                    無断転載・配布は禁止されています。
                    
                    9. 技術的制限とリスク
                    デバイス、Androidバージョン、システム状態、ストレージ容量、環境によっては、
                    バグ・データ損失・動作不良などが発生する場合があります。
                    
                    開発者は以下に対し責任を負いません：
                    - データ損失
                    - 端末やソフトウェアの不具合
                    - ファイル破損
                    - 部分的または完全な非互換性
                    
                    無料版で購入前に動作を確認できます。
                    返金には対応していません。
                    
                    端末変更やGoogleアカウント変更によっては、Playストアの規定により再購入が必要となる場合があります。
                    
                    10. AI（人工知能）の利用について
                    このアプリは独自開発であり、一部文章や最適化にAIツールを利用していますが、
                    開発者は本プロジェクトの唯一の著作者です。
                """.trimIndent(),
        "warning_resources_link" to "📞 リソースとヘルプライン",
        "warning_checkbox_age" to "☑️ 成人であることを確認しました",
        "warning_checkbox_noshow" to "このメッセージを再表示しない",
        "warning_btn_quit" to "終了",
        "warning_btn_accept" to "同意して続行",
        
        // Dialog ressources
        "resources_title" to "📞 リソースとヘルプライン",
        "resources_content" to """
                    📞 役立つリソース – 支援、相談窓口、情報
                    
                    以下は、タバコ・アルコール・大麻・精神的ストレスに困っている方のための公式または信頼できる支援サービスです。  
                    地域によって番号が異なるため、お住まいの地域の情報を必ずご確認ください。
                    
                    1. 禁煙サポート
                    • 日本 – 禁煙支援窓口（自治体）：地域番号  
                    • 日本 – こころの健康相談統一ダイヤル：0570-064-556  
                    • 日本 – がん相談支援センター：地域窓口  
                    
                    2. アルコール依存・相談
                    • 日本 – 精神保健福祉センター（アルコール相談）：地域番号  
                    • 日本 – アルコール依存症相談窓口（病院・自治体）
                    
                    3. 大麻・薬物依存サポート
                    • 日本 – ダルク（DARC）支援施設：地域窓口  
                    • 日本 – 薬物依存相談（保健所）：地域番号  
                    
                    4. 心の健康・自殺予防・感情サポート
                    • 日本 – いのちの電話：0570-783-556  
                    • 日本 – よりそいホットライン：0120-279-338  
                    • 日本 – 子ども110番 / 若者支援窓口：地域番号  
                    
                    5. 緊急番号
                    • 日本 – 110（警察）、119（消防・救急）  
                    • 韓国 – 112（警察）、119（消防・救急）  
                    • シンガポール – 999（警察）、995（救急）、1777（非緊急医療）  
                    
                    6. 責任ある利用と追加情報
                    これらのリソースは StopAddict と提携していません。  
                    あくまでも参考情報として提供されています。  
                    身体的・精神的・社会的な問題がある場合は、必ず専門家に相談してください。
                    
                    7. 重要な注意事項
                    このアプリは病気の診断・治療・予防を行うものではありません。  
                    専門的な医療アドバイスの代替にはなりません。  
                    緊急時は必ず公式の緊急サービスに連絡してください。
                """.trimIndent(),
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

        // ==================== NEDERLANDS (NL) ====================
private val TRADUCTIONS_NL = mapOf(
    // Tabtitels
    "tab_accueil" to "Start",
    "tab_stats" to "Statistieken",
    "tab_calendrier" to "Kalender",
    "tab_habitudes" to "Gewoonten & Wilskracht",
    "tab_reglages" to "Instellingen",

    // Dialoog waarschuwing meerderjarigheid
    "warning_title" to "⚠️ Waarschuwing – Alleen voor volwassenen",
    "warning_message" to """
        ⚠️ JURIDISCHE WAARSCHUWING
        
        Het gebruik van deze applicatie houdt in dat u onderstaande waarschuwing aandachtig hebt gelezen.
        De gebruiker wordt uitgenodigd om alle volgende informatie door te nemen vóór elk gebruik.
        
        1. CONTROLE OP MEERDERJARIGHEID EN NALEVING VAN LOKALE WETGEVING
        De gebruiker moet controleren dat hij/zij meerderjarig is volgens de wetgeving van het land van verblijf én van elk land waar hij/zij zich bevindt op het moment van gebruik.
        Wettelijke leeftijden voor tabak, cannabis en alcohol verschillen per land.
        Voorbeeld: iemand die in Frankrijk op 18 jaar meerderjarig is, kan volgens de Amerikaanse wetgeving voor alcoholconsumptie als minderjarig worden beschouwd.
        
        Het is uitsluitend de verantwoordelijkheid van de gebruiker om de wetten, verboden, verplichtingen en beperkingen te controleren die gelden in elk land of gebied met betrekking tot:
        - het gebruik van de applicatie,
        - het bezit of gebruik van sigaretten,
        - het gebruik van cannabis volgens lokale regelgeving,
        - alcoholconsumptie,
        - elke andere gereguleerde praktijk.
        
        De applicatie mag in geen enkel land worden beschouwd als het aanmoedigen, vergemakkelijken, stimuleren of aanbevelen van het gebruik van gereguleerde middelen.
        
        2. GEEN AANMOEDIGING TOT CONSUMPTIE
        Het gebruik van de applicatie betekent niet dat de gebruiker een roker is of alcohol/cannabis gebruikt.
        De applicatie stimuleert niet, raadt niet aan, verheerlijkt niet en normaliseert niet het gebruik van sigaretten, joints of alcohol.
        
        De applicatie heeft uitsluitend als doel:
        - helpen tellen, visualiseren en analyseren,
        - de gebruiker bewust maken van consumptie, gewoonten en uitgaven,
        - reductie of stoppen ondersteunen.
        
        Echte winst is een sigaret, joint of glas alcohol weigeren — of zelfs nooit beginnen.
        Als u niet rookt en niet drinkt, blijf uw gezondheid beschermen: tabak, cannabis en alcohol zijn schadelijk voor de gezondheid en kunnen sociaal gedrag negatief beïnvloeden.
        
        3. VERANTWOORDELIJKHEID VAN DE GEBRUIKER
        Het gebruik van de applicatie gebeurt volledig onder verantwoordelijkheid van de gebruiker, of van diens wettelijke vertegenwoordigers als de gebruiker minderjarig is.
        De ontwikkelaar kan niet aansprakelijk worden gesteld in geval van:
        - onjuist gebruik,
        - misbruik of gebruik buiten het beoogde doel,
        - foutieve interpretatie,
        - niet-naleving van lokale wetgeving,
        - beslissingen die worden genomen op basis van de getoonde gegevens.
        
        De applicatie kan in geen geval worden gebruikt om strafrechtelijke of civielrechtelijke aansprakelijkheid vast te stellen, noch als bewijs in een juridische context, hetzij voor de gebruiker, hetzij tegen de ontwikkelaar.
        
        4. BESCHERMING VAN MINDERJARIGEN EN OUDERLIJK TOEZICHT
        Ook al verzamelt de applicatie geen persoonsgegevens, wordt eraan herinnerd dat het essentieel is om het digitale gebruik van minderjarigen te bewaken.
        Ouders, voogden en wettelijke vertegenwoordigers dienen te letten op:
        - gezondheid,
        - mentaal welzijn,
        - sociaal gedrag,
        - gematigd gebruik van digitale hulpmiddelen.
        
        5. PERSOONSGEGEVENS EN PRIVACY
        Er is geen registratie vereist.
        Er worden geen persoonsgegevens verzameld, opgeslagen of verzonden.
        Als de gebruiker een voornaam invoert, wordt deze uitsluitend gebruikt binnen de applicatie en in export-/importbestanden die op de eigen telefoon van de gebruiker worden opgeslagen.
        
        Het is de verantwoordelijkheid van de gebruiker om:
        - de smartphone te beveiligen,
        - beveiligingssoftware up-to-date te houden,
        - goede praktijken voor gegevensbescherming toe te passen.
        
        6. ADVERTENTIES EN FINANCIERING
        De gratis versie bevat een advertentiebanner om de ontwikkeling te financieren.
        De premium versie maakt gebruik zonder advertenties mogelijk.
        
        Deze applicatie is het resultaat van persoonlijk werk van een onafhankelijke maker.
        Advertenties dienen uitsluitend om ontwikkeling, onderhoud en verbetering van de applicatie te ondersteunen.
        
        7. NEUTRALITEIT, ONAFHANKELIJKHEID EN GEEN EXTERNE BANDEN
        De ontwikkelaar is een onafhankelijke particulier.
        De applicatie heeft geen banden met:
        - de staat of overheidsdiensten,
        - tabaksbedrijven,
        - alcoholproducenten,
        - de cannabisindustrie,
        - enige criminele organisatie of kartel.
        
        De applicatie is niet bedoeld om een andere applicatie met een vergelijkbare naam te vervangen of te beconcurreren.
        
        8. INTELLECTUELE EIGENDOM
        De naam, het ontwerp, de inhoud, de teksten en de functionaliteiten van de applicatie zijn beschermd door het auteursrecht.
        Elke reproductie, kopie of herdistributie zonder toestemming is verboden.
        
        Ook zonder officiële (betaalde) registratie geldt auteursrecht automatisch volgens de wet.
        
        9. TECHNISCHE BEPERKINGEN, COMPATIBILITEIT EN RISICO’S
        De applicatie kan bugs, fouten of gegevensverlies vertonen afhankelijk van:
        - het telefoonmodel,
        - de Android-versie,
        - de staat van het systeem,
        - beschikbare opslag,
        - de softwareomgeving.
        
        De ontwikkelaar kan niet aansprakelijk worden gesteld in geval van:
        - gegevensverlies,
        - hardware- of softwarestoringen,
        - beschadigde bestanden,
        - gedeeltelijke of volledige incompatibiliteit.
        
        De gratis versie laat toe om de applicatie te testen vóór aankoop.
        Terugbetaling is niet mogelijk.
        
        Een verandering van smartphone of Google-account kan ertoe leiden dat de applicatie opnieuw moet worden gekocht, afhankelijk van de regels van de Play Store.
        
        10. ONTWIKKELING EN ROL VAN AI
        De applicatie is ontwikkeld via persoonlijk werk en met gebruik van AI-tools om bepaalde teksten en delen van de code te optimaliseren.
        De maker blijft de uiteindelijke auteur en verantwoordelijke van het volledige project.
    """.trimIndent(),

    "warning_resources_link" to "📞 Hulpbronnen en nuttige telefoonnummers",
    "warning_checkbox_age" to "☑️ Ik bevestig dat ik meerderjarig ben",
    "warning_checkbox_noshow" to "Dit bericht niet meer tonen",
    "warning_btn_quit" to "Afsluiten",
    "warning_btn_accept" to "Ik accepteer en ga verder",

    // Dialoog hulpbronnen
    "resources_title" to "📞 Hulpbronnen en nuttige telefoonnummers",
    "resources_content" to """
        📞 NUTTIGE HULPBRONNEN – HULP, ONDERSTEUNING, INFORMATIE
        
        Deze module verzamelt verschillende hulp-, informatie- en ondersteuningsbronnen voor mensen die te maken hebben met tabak, alcohol, cannabis of andere moeilijkheden. De onderstaande nummers kunnen per land verschillen en sommige lijnen zijn alleen bereikbaar vanuit specifieke geografische zones. De gebruiker moet de lokale beschikbaarheid van nummers of diensten controleren.
        
        1. TABAK – STOPPEN, ADVIES, ONDERSTEUNING
        • Frankrijk – Tabac Info Service: 39 89
          Officiële hulplijn voor stoppen met roken. Gespecialiseerde adviseurs, persoonlijke opvolging, informatie over nicotinevervangers en stopmethoden.
        • Canada – QuitNow: 1 877 455 2233
        • België – Tabac Stop: 0800 111 00
        • Zwitserland – Stop Tabac: 0848 000 181
        • Internationale informatie: raadpleeg lokale hulplijnen of de volksgezondheidsdiensten van het land.
        
        2. ALCOHOL – HULP, PREVENTIE, ONDERSTEUNING
        • Frankrijk – Alcool Info Service: 0 980 980 930
          Anoniem en gratis. Advies, luisterend oor, ondersteuning, doorverwijzing. Beschikbaar 7 dagen per week.
        • Canada – Anonieme Alcoholisten: 1 877 404 2242
        • België – Drogues & Alcool-lijn: 078 15 15 15
        • Zwitserland – Addiction Suisse: 021 321 29 11
        
        3. CANNABIS – INFORMATIE & BEGELEIDING
        • Frankrijk – Drogues Info Service: 0 800 23 13 13
          Officiële informatie over middelen, risico’s en beschikbare hulp.
        • België – Infor-Drogues: 02 227 52 52
        • Zwitserland – Ligne Drogue: 0848 133 133
        
        4. NOOD, PSYCHOLOGISCHE CRISIS & EMOTIONELE STEUN
        Overmatig gebruik van tabak, alcohol of cannabis kan psychisch of sociaal lijden maskeren. In geval van nood zijn er verschillende nummers beschikbaar.
        
        • Frankrijk – Suicide Écoute: 01 45 39 40 00
        • Frankrijk – Nationaal nummer suïcidepreventie: 3114
        • Frankrijk – SOS Amitié: 09 72 39 40 50
        • België – Télé-Accueil: 107
        • Zwitserland – La Main Tendue: 143
        • Canada – Suicide Prevention Service: 1 833 456 4566
        
        5. NOODNUMMERS
        Noodnummers verschillen per land. Voorbeelden:
        • Europese Unie – 112
        • Frankrijk – Samu 15, Politie 17, Brandweer 18, Enkel noodnummer 112
        • Verenigde Staten – 911
        • Canada – 911
        De gebruiker wordt uitgenodigd om de officiële noodnummers te controleren van het land waarin hij/zij zich bevindt.
        
        6. VERANTWOORD GEBRUIK EN AANVULLENDE INFORMATIE
        De genoemde hulpbronnen hebben geen band met StopAddict. Ze worden enkel ter informatie verstrekt. De gebruiker blijft vrij om ze al dan niet te contacteren.
        Het raadplegen van gezondheidsprofessionals wordt aanbevolen bij fysieke, psychologische, sociale of familiale moeilijkheden.
        
        7. BELANGRIJKE HERINNERING
        De applicatie is niet bedoeld om een ziekte te diagnosticeren, te behandelen of te voorkomen.
        Ze vervangt geen medisch advies.
        Bij nood of onmiddellijk gevaar: neem contact op met de nooddiensten van het land waarin u zich bevindt.
    """.trimIndent(),
    "resources_btn_close" to "Sluiten",

    // Console debug
    "console_title" to "DEBUGCONSOLE STOPADDICT",
    "console_version" to "Versie",
    "console_version_free" to "Gratis",
    "console_version_paid" to "Betaald",
    "console_langue" to "Taal",
    "console_date" to "Datum",
    "console_build" to "Build",
    "console_device" to "Toestel",
    "console_android" to "Android",
    "console_app_state" to "App-status",
    "console_age_accepted" to "Leeftijd geaccepteerd",
    "console_warning_shown" to "Waarschuwing weergegeven",
    "console_error_prefs" to "Fout bij lezen prefs",
    "console_logs_db" to "Database-logs",
    "console_consos_jour" to "Dagelijkse consumpties",
    "console_no_conso" to "Geen consumptie",
    "console_error_db" to "Fout bij lezen DB",
    "console_logs_selectable" to "Selecteerbare logs ✓",
    "console_btn_close" to "Sluiten"
)

        // ==================== 中文 (ZH) ====================
private val TRADUCTIONS_ZH = mapOf(
    // Titres onglets
    "tab_accueil" to "首页",
    "tab_stats" to "统计",
    "tab_calendrier" to "日历",
    "tab_habitudes" to "习惯与意志",
    "tab_reglages" to "设置",

    // Dialog avertissement majorité
    "warning_title" to "⚠️ 警告 - 仅限成年人",
    "warning_message" to """
⚠️ 法律警示

使用本应用程序即表示您已认真阅读本警示内容。
在任何使用之前，建议用户完整了解以下全部信息。

1. 成年核验与遵守当地法律
用户必须核实自己在以下两种法律体系下均为成年人：
- 居住国法律；
- 使用本应用时用户实际所在国/地区法律。

关于烟草、大麻或酒精的法定年龄在不同国家/地区差异很大。
示例：在法国年满 18 岁被视为成年人，但在美国关于酒精消费的法律下可能仍被视为未成年人。

用户有唯一且排他的责任，在每个国家或地区核实与以下事项相关的法律、禁令、义务与限制：
- 本应用的使用；
- 香烟的持有或消费；
- 大麻的使用（依据当地法规）；
- 酒精的消费；
- 任何其他受监管的实践或行为。

本应用在任何国家/地区都不应被视为在鼓励、促成、煽动或推荐对受监管物质的消费。

2. 不鼓励消费
使用本应用并不推定用户必然是吸烟者、饮酒者或大麻使用者。
本应用不会鼓励、推荐、美化、合理化或“正常化”香烟、joint（大麻卷烟）或酒精的消费。

本应用的唯一目标是：
- 帮助记录、可视化并分析；
- 提升用户对自身消费、习惯与花费的认知；
- 鼓励减少或停止。

真正的成功，是拒绝一支香烟、一个 joint 或一杯酒，甚至是永远不要开始。
如果您不吸烟也不饮酒，请继续保护您的健康：烟草、大麻与酒精对健康有害，并可能影响社会行为。

3. 用户责任
本应用的使用完全由用户自行负责；若用户为未成年人，则由其法定监护人承担责任。
开发者不对以下情况承担责任：
- 不当使用；
- 规避用途或偏离预期用途；
- 错误解读；
- 不遵守当地法律；
- 基于应用显示数据做出的决定。

本应用在任何情况下都不得被用于追究刑事或民事责任，也不得作为司法环境中的证据，无论是用于用户自身，还是用于针对开发者。

4. 未成年人保护与家长监护
即使本应用不收集任何个人数据，也提醒用户：监护未成年人的数字使用行为至关重要。
父母、监护人及法定负责人应关注：
- 健康；
- 心理福祉；
- 社会行为；
- 适度使用数字工具。

5. 个人数据与保密
无需注册。
不收集、存储或传输任何个人数据。
若用户输入姓名，该姓名仅用于本应用内部，且仅会出现在导出/导入文件中（这些文件仍存储在用户自己的手机中）。

用户有责任：
- 保护其智能手机；
- 维护安全软件；
- 采用良好的数据保护实践。

6. 广告与收益
免费版包含广告横幅，用于资助开发。
高级版（premium）允许无广告使用。

本应用是独立创作者的个人成果。
广告仅用于支持本应用的开发、维护与改进。

7. 中立性、独立性与无外部关联
开发者为独立个人。
本应用与以下主体没有任何联系：
- 国家或行政机关；
- 烟草企业；
- 酒精生产商；
- 大麻相关产业；
- 任何犯罪组织或 cartel。

本应用无意替代或与任何名称相近的应用竞争。

8. 知识产权
本应用的名称、设计、内容、文本及功能受著作权保护。
未经授权的任何复制、拷贝或再分发均被禁止。

即便未进行付费登记，著作权也会依法自动产生并适用。

9. 技术限制、兼容性与风险
本应用可能会出现 bug、错误或数据丢失，原因可能包括：
- 手机型号；
- Android 版本；
- 系统状态；
- 可用存储空间；
- 软件环境。

开发者不对以下情况承担责任：
- 数据丢失；
- 硬件或软件故障；
- 文件损坏；
- 部分或完全不兼容。

免费版允许用户在购买前测试本应用。
购买后不提供退款。

更换手机或 Google 账号可能会导致需要重新购买，具体取决于 Play Store 的规则。

10. 开发与人工智能的角色
本应用通过个人工作开发，并借助人工智能工具优化部分文本与代码片段。
创作者仍为整个项目的最终作者并承担最终责任。
""".trimIndent(),

    "warning_resources_link" to "📞 资源与求助号码",
    "warning_checkbox_age" to "☑️ 我确认我已成年",
    "warning_checkbox_noshow" to "不再显示此消息",
    "warning_btn_quit" to "退出",
    "warning_btn_accept" to "我接受并继续",

    // Dialog ressources
    "resources_title" to "📞 资源与求助号码",
    "resources_content" to """
📞 实用资源 – 求助、支持与信息

本模块汇总若干与烟草、酒精、大麻或其他困难相关的帮助、信息或支持资源。以下号码可能因国家/地区不同而有所差异，且部分热线仅能从特定地理区域拨打。用户必须自行核实当地号码或服务的可用性。

1. 烟草 – 戒烟、建议与支持
• 法国 – Tabac Info Service：39 89
  官方戒烟支持热线。专业顾问、个性化跟进、尼古丁替代品与戒烟方法信息。
• 加拿大 – QuitNow：1 877 455 2233
• 比利时 – Tabac Stop：0800 111 00
• 瑞士 – Stop Tabac：0848 000 181
• 国际信息：请参考当地戒烟热线或所在国公共卫生机构的相关服务。

2. 酒精 – 帮助、预防与支持
• 法国 – Alcool Info Service：0 980 980 930
  匿名且免费。建议、倾听、支持与转介。每周 7 天开放。
• 加拿大 – 匿名戒酒会（Alcoholics Anonymous）：1 877 404 2242
• 比利时 – Drogues & Alcool 热线：078 15 15 15
• 瑞士 – Addiction Suisse：021 321 29 11

3. 大麻 – 信息与陪伴支持
• 法国 – Drogues Info Service：0 800 23 13 13
  关于物质、风险与可获得帮助的官方信息。
• 比利时 – Infor-Drogues：02 227 52 52
• 瑞士 – Ligne Drogue：0848 133 133

4. 痛苦、心理紧急情况与情绪支持
过度使用烟草、酒精或大麻有时可能掩盖心理或社会困境。如遇痛苦或危机，可联系以下号码：

• 法国 – Suicide Écoute：01 45 39 40 00
• 法国 – 国家自杀预防号码：3114
• 法国 – SOS Amitié：09 72 39 40 50
• 比利时 – Télé-Accueil：107
• 瑞士 – La Main Tendue：143
• 加拿大 – 自杀预防服务：1 833 456 4566

5. 紧急号码
紧急号码因国家/地区而异。示例：
• 欧盟 – 112
• 法国 – 急救 15，警察 17，消防 18，统一紧急 112
• 美国 – 911
• 加拿大 – 911
建议用户核实其所在国家/地区的官方紧急号码。

6. 负责任使用与补充信息
上述资源与 StopAddict 无任何关联，仅供信息参考。用户可自行决定是否联系。
如遇身体、心理、社会或家庭困难，建议寻求医疗或专业人士帮助。

7. 重要提醒
本应用无意诊断、治疗或预防任何疾病。
本应用不能替代专业医疗意见。
如遇紧急情况或即时危险，请联系您所在国家/地区的紧急救援服务。
""".trimIndent(),

    "resources_btn_close" to "关闭",

    // Console debug
    "console_title" to "STOPADDICT 调试控制台",
    "console_version" to "版本",
    "console_version_free" to "免费版",
    "console_version_paid" to "付费版",
    "console_langue" to "语言",
    "console_date" to "日期",
    "console_build" to "构建版本",
    "console_device" to "设备",
    "console_android" to "Android",
    "console_app_state" to "应用状态",
    "console_age_accepted" to "已确认成年",
    "console_warning_shown" to "警示已显示",
    "console_error_prefs" to "读取偏好设置出错",
    "console_logs_db" to "数据库日志",
    "console_consos_jour" to "当日消耗",
    "console_no_conso" to "无消耗记录",
    "console_error_db" to "读取数据库出错",
    "console_logs_selectable" to "日志可选择 ✓",
    "console_btn_close" to "关闭"
)

            private val TRADUCTIONS_ZHT = mapOf(
    // Titres onglets
    "tab_accueil" to "首頁",
    "tab_stats" to "統計",
    "tab_calendrier" to "日曆",
    "tab_habitudes" to "習慣與意志",
    "tab_reglages" to "設定",

    // Dialog avertissement majorité
    "warning_title" to "⚠️ 警告 - 僅限成年人",
    "warning_message" to """
⚠️ 法律警示

使用本應用程式即表示您已仔細閱讀本警示內容。
在任何使用之前，使用者被建議完整了解以下所有資訊。

1. 成年核驗與遵守當地法規
使用者必須核實自己在以下兩種法律體系下均為成年人：
- 居住國法規；
- 使用本應用時使用者實際所在國家/地區法規。

關於菸草、大麻或酒精的法定年齡因國家/地區而異。
例：在法國 18 歲視為成年人，但在美國關於酒精的法規下仍可能被視為未成年人。

使用者對於每個國家或地區的法律、禁令、義務與限制，負有唯一且排他的核實責任，尤其包括：
- 本應用的使用；
- 香菸的持有或消費；
- 大麻的使用（依當地法規）；
- 酒精的消費；
- 任何其他受規範的行為或實務。

本應用在任何國家/地區都不得被視為在鼓勵、促成、煽動或推薦消費受管制物質。

2. 不鼓勵消費
使用本應用並不推定使用者為吸菸者、飲酒者或大麻使用者。
本應用不會鼓勵、推薦、美化、合理化或「正常化」香菸、joint（大麻卷菸）或酒精的消費。

本應用的唯一目的為：
- 協助計數、視覺化與分析；
- 使使用者意識到自身的消費、習慣與支出；
- 鼓勵減量或戒除。

真正的成功，是拒絕一支香菸、一個 joint 或一杯酒，甚至是永遠不要開始。
若您不吸菸也不飲酒，請持續保護健康：菸草、大麻與酒精對健康有害，並可能影響社會行為。

3. 使用者責任
本應用的使用完全由使用者自行負責；若使用者為未成年人，則由其法定監護人負責。
開發者不對下列情況承擔責任：
- 不當使用；
- 偏離預期用途；
- 錯誤解讀；
- 不遵守當地法規；
- 依據應用顯示資料所作之決定。

本應用在任何情況下不得作為追究刑事或民事責任之工具，也不得作為司法情境中的證據，無論是為使用者或針對開發者。

4. 未成年人保護與家長監護
即使本應用不收集任何個人資料，仍提醒：監督未成年人的數位使用行為至關重要。
父母、監護人與法定負責人應關注：
- 健康；
- 心理福祉；
- 社會行為；
- 適度使用數位工具。

5. 個人資料與保密
無需註冊。
不收集、儲存或傳輸任何個人資料。
若使用者輸入姓名，該姓名僅用於本應用內部，並僅會出現在匯出/匯入檔案中（該檔案仍儲存在使用者自己的手機上）。

使用者有責任：
- 保護其智慧型手機；
- 維持安全防護軟體；
- 採取良好的資料保護實務。

6. 廣告與收益
免費版本包含廣告橫幅，用於資助開發。
付費版本（premium）可無廣告使用。

本應用為獨立創作者的個人成果。
廣告僅用於支持本應用的開發、維護與改進。

7. 中立性、獨立性與無外部關聯
開發者為獨立個人。
本應用與以下任何主體無任何關聯：
- 國家或行政機關；
- 菸草企業；
- 酒精製造商；
- 大麻相關產業；
- 任何犯罪組織或 cartel。

本應用無意取代或與任何名稱相近之應用競爭。

8. 智慧財產權
本應用的名稱、設計、內容、文字與功能受著作權保護。
任何未經授權的複製、拷貝或再散布均被禁止。

即使未進行付費登記，著作權亦依法律自動產生並適用。

9. 技術限制、相容性與風險
本應用可能出現 bug、錯誤或資料遺失，其原因可能包含：
- 手機型號；
- Android 版本；
- 系統狀態；
- 可用儲存空間；
- 軟體環境。

開發者不對下列情況負責：
- 資料遺失；
- 硬體或軟體故障；
- 檔案損毀；
- 部分或完全不相容。

免費版本允許使用者在購買前測試本應用。
購買後不提供退款。

更換手機或 Google 帳號可能導致需要重新購買，依 Play Store 規則而定。

10. 開發與人工智慧的角色
本應用透過個人工作開發，並使用人工智慧工具優化部分文字與程式碼片段。
創作者仍為整個專案之最終作者並承擔最終責任。
""".trimIndent(),

    "warning_resources_link" to "📞 資源與求助電話",
    "warning_checkbox_age" to "☑️ 我確認我已成年",
    "warning_checkbox_noshow" to "不再顯示此訊息",
    "warning_btn_quit" to "退出",
    "warning_btn_accept" to "我接受並繼續",

    // Dialog ressources
    "resources_title" to "📞 資源與求助電話",
    "resources_content" to """
📞 實用資源 – 求助、支持與資訊

本模組彙整多項與菸草、酒精、大麻或其他困難相關的協助、資訊或支持資源。以下電話可能因國家/地區而異，且部分專線僅能於特定地理區域撥打。使用者必須自行確認當地電話或服務是否可用。

1. 菸草 – 戒菸、建議與支持
• 法國 – Tabac Info Service：39 89
  官方戒菸支持專線。專業顧問、個人化追蹤、尼古丁替代品與戒菸方法資訊。
• 加拿大 – QuitNow：1 877 455 2233
• 比利時 – Tabac Stop：0800 111 00
• 瑞士 – Stop Tabac：0848 000 181
• 國際資訊：請參考所在地之戒菸專線或公共衛生機構服務。

2. 酒精 – 協助、預防與支持
• 法國 – Alcool Info Service：0 980 980 930
  匿名且免費。建議、傾聽、支持與轉介。每週 7 天開放。
• 加拿大 – 匿名戒酒會（Alcoholics Anonymous）：1 877 404 2242
• 比利時 – Drogues & Alcool 專線：078 15 15 15
• 瑞士 – Addiction Suisse：021 321 29 11

3. 大麻 – 資訊與陪伴支持
• 法國 – Drogues Info Service：0 800 23 13 13
  關於物質、風險及可獲得協助之官方資訊。
• 比利時 – Infor-Drogues：02 227 52 52
• 瑞士 – Ligne Drogue：0848 133 133

4. 痛苦、心理緊急情況與情緒支持
過度使用菸草、酒精或大麻，有時可能掩蓋心理或社會困境。如遇痛苦或危機，可聯絡以下電話：

• 法國 – Suicide Écoute：01 45 39 40 00
• 法國 – 國家自殺預防電話：3114
• 法國 – SOS Amitié：09 72 39 40 50
• 比利時 – Télé-Accueil：107
• 瑞士 – La Main Tendue：143
• 加拿大 – 自殺預防服務：1 833 456 4566

5. 緊急電話
緊急電話因國家/地區而異。示例：
• 歐盟 – 112
• 法國 – 急救 15，警察 17，消防 18，統一緊急 112
• 美國 – 911
• 加拿大 – 911
建議使用者查核其所在國家/地區之官方緊急電話。

6. 負責任使用與補充資訊
以上資源與 StopAddict 無任何關聯，僅供資訊參考。使用者可自行決定是否聯絡。
如遇身體、心理、社會或家庭困難，建議尋求醫療或專業人士協助。

7. 重要提醒
本應用不以診斷、治療或預防任何疾病為目的。
本應用不能取代專業醫療意見。
如遇緊急情況或立即危險，請聯絡您所在地之緊急救援服務。
""".trimIndent(),

    "resources_btn_close" to "關閉",

    // Console debug
"console_title" to "STOPADDICT 除錯主控台",
"console_version" to "版本",
"console_version_free" to "免費版",
"console_version_paid" to "付費版",
"console_langue" to "語言",
"console_date" to "日期",
"console_build" to "建置版本",
"console_device" to "裝置",
"console_android" to "Android",
"console_app_state" to "應用狀態",
"console_age_accepted" to "已確認成年",
"console_warning_shown" to "警示已顯示",
"console_error_prefs" to "讀取偏好設定錯誤",
"console_logs_db" to "資料庫日誌",
"console_consos_jour" to "當日消耗",
"console_no_conso" to "無消耗記錄",
"console_error_db" to "讀取資料庫錯誤",
"console_logs_selectable" to "日誌可選取 ✓",
"console_btn_close" to "關閉"
)
}
