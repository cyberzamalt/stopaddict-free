package com.stopaddict

object AccueilLangues {

    private const val TAG = "AccueilLangues"

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
        "NL" -> TRADUCTIONS_NL
        "ZHS" -> TRADUCTIONS_ZHS
        "ZHT" -> TRADUCTIONS_ZHT
        else -> {
            StopAddictLogger.w(TAG, "Langue inconnue: $codeLangue, fallback FR")
            TRADUCTIONS_FR
        }
    }
}


    // ==================== FRANÇAIS ====================
    private val TRADUCTIONS_FR = mapOf(
        // Labels catégories
        "label_cigarettes" to "Cigarettes",
        "label_joints" to "Joints",
        "label_alcool_global" to "Alcool global",
        "label_bieres" to "Bières",
        "label_liqueurs" to "Liqueurs",
        "label_alcool_fort" to "Alcool fort",
        
        // Bandeau profil
        "profil_complet" to "Profil: Complet ✓",
        "profil_incomplet" to "Profil: Incomplet",
        "total_aujourdhui" to "Total aujourd'hui:",
        
        // Conseils génériques (toujours disponibles)
        "conseil_generique_1" to "Chaque cigarette non fumée ajoute 11 minutes à votre vie.",
        "conseil_generique_2" to "L'exercice physique aide à réduire l'envie.",
        "conseil_generique_3" to "Boire de l'eau réduit les sensations de manque.",
        "conseil_generique_4" to "Entourez-vous de personnes soutenantes.",
        "conseil_generique_5" to "Chaque pas compte dans votre parcours.",
        "conseil_generique_6" to "La motivation est la clé du succès.",
        
        // Conseils cas 1: Rien
        "conseil_cas1_1" to "Bienvenue! Configurez vos habitudes pour un suivi personnalisé.",
        "conseil_cas1_2" to "Chaque pas compte dans votre parcours.",
        "conseil_cas1_3" to "La motivation est la clé du succès.",
        
        // Conseils cas 3: Coûts uniquement
        "conseil_cas3_1" to "Réduire votre consommation vous fera économiser.",
        "conseil_cas3_2" to "L'argent économisé peut servir à vos projets.",
        "conseil_cas3_3" to "Ces économies s'accumulent jour après jour.",
        
        // Conseils cas 4: Habitudes uniquement
        "conseil_cas4_1" to "Suivre vos habitudes aide à progresser.",
        "conseil_cas4_2" to "Fixez-vous des objectifs réalistes.",
        
        // Conseils cas 5: Dates uniquement
        "conseil_cas5_1" to "Votre objectif se rapproche!",
        "conseil_cas5_2" to "Restez concentré sur votre date.",
        
        // Comparaison habitudes
        "habitudes_moins" to "Vous consommez moins que d'habitude, bravo!",
        "habitudes_egal" to "Vous êtes dans vos habitudes.",
        "habitudes_plus" to "Vous dépassez vos habitudes, attention!",
        "habitudes_suivre" to "Suivez vos habitudes pour progresser.",
        
        // Messages dates
        "date_jours_restants" to "Plus que %d jours avant votre date d'arrêt!",
        "date_aujourdhui" to "C'est aujourd'hui votre date d'arrêt, courage!",
        "date_depassee" to "Vous avez dépassé votre date d'arrêt, continuez!",
        "date_reduction" to "Date de réduction: dans %d jours!",
        "date_rapproche" to "Votre objectif se rapproche!",
        
        // Messages économies
        "economies_jour" to "Vous économisez %.2f aujourd'hui!",
        "economies_reelles" to "Économies réelles: %.2f vs vos habitudes!",
        "economies_accumulent" to "Ces économies s'accumulent jour après jour.",

        "btn_ok" to "Fermer",
        
        // Messages erreurs
        "erreur_chargement" to "Erreur de chargement",
        "erreur_ajout" to "Erreur ajout consommation",
        "erreur_retrait" to "Impossible de retirer, aucune consommation pour aujourd'hui",
        "erreur_generale" to "Erreur: %s"
    )

    // ==================== ENGLISH ====================
    private val TRADUCTIONS_EN = mapOf(
        // Labels catégories
        "label_cigarettes" to "Cigarettes",
        "label_joints" to "Joints",
        "label_alcool_global" to "Alcohol overall",
        "label_bieres" to "Beers",
        "label_liqueurs" to "Liquors",
        "label_alcool_fort" to "Spirits",
        
        // Bandeau profil
        "profil_complet" to "Profile: Complete ✓",
        "profil_incomplet" to "Profile: Incomplete",
        "total_aujourdhui" to "Total today:",
        
        // Conseils génériques
        "conseil_generique_1" to "Each cigarette not smoked adds 11 minutes to your life.",
        "conseil_generique_2" to "Physical exercise helps reduce cravings.",
        "conseil_generique_3" to "Drinking water reduces withdrawal symptoms.",
        "conseil_generique_4" to "Surround yourself with supportive people.",
        "conseil_generique_5" to "Every step counts in your journey.",
        "conseil_generique_6" to "Motivation is the key to success.",
        
        // Conseils cas 1: Rien
        "conseil_cas1_1" to "Welcome! Configure your habits for personalized tracking.",
        "conseil_cas1_2" to "Every step counts in your journey.",
        "conseil_cas1_3" to "Motivation is the key to success.",
        
        // Conseils cas 3: Coûts uniquement
        "conseil_cas3_1" to "Reducing your consumption will save you money.",
        "conseil_cas3_2" to "The money saved can be used for your projects.",
        "conseil_cas3_3" to "These savings add up day after day.",
        
        // Conseils cas 4: Habitudes uniquement
        "conseil_cas4_1" to "Tracking your habits helps you progress.",
        "conseil_cas4_2" to "Set realistic goals.",
        
        // Conseils cas 5: Dates uniquement
        "conseil_cas5_1" to "Your goal is approaching!",
        "conseil_cas5_2" to "Stay focused on your date.",
        
        // Comparaison habitudes
        "habitudes_moins" to "You're consuming less than usual, great job!",
        "habitudes_egal" to "You're within your usual habits.",
        "habitudes_plus" to "You're exceeding your habits, be careful!",
        "habitudes_suivre" to "Track your habits to progress.",
        
        // Messages dates
        "date_jours_restants" to "Only %d days until your quit date!",
        "date_aujourdhui" to "Today is your quit date, stay strong!",
        "date_depassee" to "You've passed your quit date, keep going!",
        "date_reduction" to "Reduction date: in %d days!",
        "date_rapproche" to "Your goal is approaching!",
        
        // Messages économies
        "economies_jour" to "You're saving £%.2f today!",
        "economies_reelles" to "Real savings: £%.2f vs your habits!",
        "economies_accumulent" to "These savings add up quickly.",

                "btn_ok" to "OK",
        
        // Messages erreurs
        "erreur_chargement" to "Loading error",
        "erreur_ajout" to "Error adding consumption",
        "erreur_retrait" to "Cannot remove, no consumption for today",
        "erreur_generale" to "Error: %s"
    )

    // ==================== ESPAÑOL ====================
    private val TRADUCTIONS_ES = mapOf(
        // Labels catégories
        "label_cigarettes" to "Cigarrillos",
        "label_joints" to "Porros",
        "label_alcool_global" to "Alcohol global",
        "label_bieres" to "Cervezas",
        "label_liqueurs" to "Licores",
        "label_alcool_fort" to "Aguardiente",
        
        // Bandeau profil
        "profil_complet" to "Perfil: Completo ✓",
        "profil_incomplet" to "Perfil: Incompleto",
        "total_aujourdhui" to "Total hoy:",
        
        // Conseils génériques
        "conseil_generique_1" to "Cada cigarrillo no fumado añade 11 minutos a tu vida.",
        "conseil_generique_2" to "El ejercicio físico ayuda a reducir las ganas.",
        "conseil_generique_3" to "Beber agua reduce los síntomas de abstinencia.",
        "conseil_generique_4" to "Rodéate de personas que te apoyen.",
        "conseil_generique_5" to "Cada paso cuenta en tu camino.",
        "conseil_generique_6" to "La motivación es la clave del éxito.",
        
        // Conseils cas 1: Rien
        "conseil_cas1_1" to "¡Bienvenido! Configura tus hábitos para un seguimiento personalizado.",
        "conseil_cas1_2" to "Cada paso cuenta en tu camino.",
        "conseil_cas1_3" to "La motivación es la clave del éxito.",
        
        // Conseils cas 3: Coûts uniquement
        "conseil_cas3_1" to "Reducir tu consumo te hará ahorrar dinero.",
        "conseil_cas3_2" to "El dinero ahorrado puede servir para tus proyectos.",
        "conseil_cas3_3" to "Estos ahorros se acumulan día tras día.",
        
        // Conseils cas 4: Habitudes uniquement
        "conseil_cas4_1" to "Seguir tus hábitos te ayuda a progresar.",
        "conseil_cas4_2" to "Establece objetivos realistas.",
        
        // Conseils cas 5: Dates uniquement
        "conseil_cas5_1" to "¡Tu objetivo se acerca!",
        "conseil_cas5_2" to "Mantente concentrado en tu fecha.",
        
        // Comparaison habitudes
        "habitudes_moins" to "Estás consumiendo menos de lo habitual, ¡muy bien!",
        "habitudes_egal" to "Estás dentro de tus hábitos habituales.",
        "habitudes_plus" to "Estás excediendo tus hábitos, ¡cuidado!",
        "habitudes_suivre" to "Sigue tus hábitos para progresar.",
        
        // Messages dates
        "date_jours_restants" to "¡Solo quedan %d días para tu fecha de abandono!",
        "date_aujourdhui" to "¡Hoy es tu fecha de abandono, mantente fuerte!",
        "date_depassee" to "Has superado tu fecha de abandono, ¡sigue así!",
        "date_reduction" to "Fecha de reducción: ¡en %d días!",
        "date_rapproche" to "¡Tu objetivo se acerca!",
        
        // Messages économies
        "economies_jour" to "¡Estás ahorrando %.2f hoy!",
        "economies_reelles" to "Ahorros reales: %.2f vs tus hábitos!",
        "economies_accumulent" to "Estos ahorros se acumulan rápidamente.",

                "btn_ok" to "Cerrar",
        
        // Messages erreurs
        "erreur_chargement" to "Error de carga",
        "erreur_ajout" to "Error al añadir consumo",
        "erreur_retrait" to "No se puede retirar, no hay consumo para hoy",
        "erreur_generale" to "Error: %s"
    )

    // ==================== PORTUGUÊS ====================
    private val TRADUCTIONS_PT = mapOf(
        // Labels catégories
        "label_cigarettes" to "Cigarros",
        "label_joints" to "Baseados",
        "label_alcool_global" to "Álcool global",
        "label_bieres" to "Cervejas",
        "label_liqueurs" to "Licores",
        "label_alcool_fort" to "Destilados",
        
        // Bandeau profil
        "profil_complet" to "Perfil: Completo ✓",
        "profil_incomplet" to "Perfil: Incompleto",
        "total_aujourdhui" to "Total hoje:",
        
        // Conseils génériques
        "conseil_generique_1" to "Cada cigarro não fumado adiciona 11 minutos à sua vida.",
        "conseil_generique_2" to "O exercício físico ajuda a reduzir a vontade.",
        "conseil_generique_3" to "Beber água reduz os sintomas de abstinência.",
        "conseil_generique_4" to "Cerque-se de pessoas que te apoiam.",
        "conseil_generique_5" to "Cada passo conta na sua jornada.",
        "conseil_generique_6" to "A motivação é a chave do sucesso.",
        
        // Conseils cas 1: Rien
        "conseil_cas1_1" to "Bem-vindo! Configure seus hábitos para um acompanhamento personalizado.",
        "conseil_cas1_2" to "Cada passo conta na sua jornada.",
        "conseil_cas1_3" to "A motivação é a chave do sucesso.",
        
        // Conseils cas 3: Coûts uniquement
        "conseil_cas3_1" to "Reduzir seu consumo vai fazer você economizar.",
        "conseil_cas3_2" to "O dinheiro economizado pode servir para seus projetos.",
        "conseil_cas3_3" to "Essas economias se acumulam dia após dia.",
        
        // Conseils cas 4: Habitudes uniquement
        "conseil_cas4_1" to "Acompanhar seus hábitos ajuda a progredir.",
        "conseil_cas4_2" to "Estabeleça objetivos realistas.",
        
        // Conseils cas 5: Dates uniquement
        "conseil_cas5_1" to "Seu objetivo está se aproximando!",
        "conseil_cas5_2" to "Mantenha o foco na sua data.",
        
        // Comparaison habitudes
        "habitudes_moins" to "Você está consumindo menos que o habitual, parabéns!",
        "habitudes_egal" to "Você está dentro dos seus hábitos habituais.",
        "habitudes_plus" to "Você está excedendo seus hábitos, cuidado!",
        "habitudes_suivre" to "Acompanhe seus hábitos para progredir.",
        
        // Messages dates
        "date_jours_restants" to "Faltam apenas %d dias para sua data de parada!",
        "date_aujourdhui" to "Hoje é sua data de parada, mantenha-se forte!",
        "date_depassee" to "Você passou sua data de parada, continue assim!",
        "date_reduction" to "Data de redução: em %d dias!",
        "date_rapproche" to "Seu objetivo está se aproximando!",
        
        // Messages économies
        "economies_jour" to "Você está economizando R$%.2f hoje!",
        "economies_reelles" to "Economias reais: R$%.2f vs seus hábitos!",
        "economies_accumulent" to "Essas economias se acumulam rapidamente.",

                "btn_ok" to "Fechar",
        
        // Messages erreurs
        "erreur_chargement" to "Erro de carregamento",
        "erreur_ajout" to "Erro ao adicionar consumo",
        "erreur_retrait" to "Não é possível remover, nenhum consumo para hoje",
        "erreur_generale" to "Erro: %s"
        
    )
    // ==================== DEUTSCH ====================
    private val TRADUCTIONS_DE = mapOf(
        // Labels catégories
        "label_cigarettes" to "Zigaretten",
        "label_joints" to "Joints",
        "label_alcool_global" to "Alkohol gesamt",
        "label_bieres" to "Biere",
        "label_liqueurs" to "Liköre",
        "label_alcool_fort" to "Spirituosen",
        
        // Bandeau profil
        "profil_complet" to "Profil: Vollständig ✓",
        "profil_incomplet" to "Profil: Unvollständig",
        "total_aujourdhui" to "Heute insgesamt:",
        
        // Conseils génériques
        "conseil_generique_1" to "Jede nicht gerauchte Zigarette verlängert Ihr Leben um 11 Minuten.",
        "conseil_generique_2" to "Körperliche Bewegung hilft, Verlangen zu reduzieren.",
        "conseil_generique_3" to "Wasser trinken reduziert Entzugserscheinungen.",
        "conseil_generique_4" to "Umgeben Sie sich mit unterstützenden Menschen.",
        "conseil_generique_5" to "Jeder Schritt zählt auf Ihrem Weg.",
        "conseil_generique_6" to "Motivation ist der Schlüssel zum Erfolg.",
        
        // Conseils cas 1: Rien
        "conseil_cas1_1" to "Willkommen! Konfigurieren Sie Ihre Gewohnheiten für eine personalisierte Verfolgung.",
        "conseil_cas1_2" to "Jeder Schritt zählt auf Ihrem Weg.",
        "conseil_cas1_3" to "Motivation ist der Schlüssel zum Erfolg.",
        
        // Conseils cas 3: Coûts uniquement
        "conseil_cas3_1" to "Ihren Konsum zu reduzieren wird Ihnen Geld sparen.",
        "conseil_cas3_2" to "Das gesparte Geld kann für Ihre Projekte verwendet werden.",
        "conseil_cas3_3" to "Diese Ersparnisse häufen sich Tag für Tag an.",
        
        // Conseils cas 4: Habitudes uniquement
        "conseil_cas4_1" to "Das Verfolgen Ihrer Gewohnheiten hilft Ihnen Fortschritte zu machen.",
        "conseil_cas4_2" to "Setzen Sie sich realistische Ziele.",
        
        // Conseils cas 5: Dates uniquement
        "conseil_cas5_1" to "Ihr Ziel nähert sich!",
        "conseil_cas5_2" to "Bleiben Sie auf Ihr Datum konzentriert.",
        
        // Comparaison habitudes
        "habitudes_moins" to "Sie konsumieren weniger als üblich, großartig!",
        "habitudes_egal" to "Sie sind in Ihren üblichen Gewohnheiten.",
        "habitudes_plus" to "Sie überschreiten Ihre Gewohnheiten, Vorsicht!",
        "habitudes_suivre" to "Verfolgen Sie Ihre Gewohnheiten, um Fortschritte zu machen.",
        
        // Messages dates
        "date_jours_restants" to "Nur noch %d Tage bis zu Ihrem Aufhördatum!",
        "date_aujourdhui" to "Heute ist Ihr Aufhördatum, bleiben Sie stark!",
        "date_depassee" to "Sie haben Ihr Aufhördatum überschritten, machen Sie weiter!",
        "date_reduction" to "Reduzierungsdatum: in %d Tagen!",
        "date_rapproche" to "Ihr Ziel nähert sich!",
        
        // Messages économies
        "economies_jour" to "Sie sparen heute %.2f!",
        "economies_reelles" to "Echte Ersparnisse: %.2f€ vs Ihre Gewohnheiten!",
        "economies_accumulent" to "Diese Ersparnisse häufen sich schnell an.",

                "btn_ok" to "Schließen",
        
        // Messages erreurs
        "erreur_chargement" to "Ladefehler",
        "erreur_ajout" to "Fehler beim Hinzufügen des Konsums",
        "erreur_retrait" to "Kann nicht abgezogen werden, kein Konsum für heute",
        "erreur_generale" to "Fehler: %s"
    )

    // ==================== ITALIANO ====================
    private val TRADUCTIONS_IT = mapOf(
        // Labels catégories
        "label_cigarettes" to "Sigarette",
        "label_joints" to "Spinelli",
        "label_alcool_global" to "Alcol globale",
        "label_bieres" to "Birre",
        "label_liqueurs" to "Liquori",
        "label_alcool_fort" to "Superalcolici",
        
        // Bandeau profil
        "profil_complet" to "Profilo: Completo ✓",
        "profil_incomplet" to "Profilo: Incompleto",
        "total_aujourdhui" to "Totale oggi:",
        
        // Conseils génériques
        "conseil_generique_1" to "Ogni sigaretta non fumata aggiunge 11 minuti alla tua vita.",
        "conseil_generique_2" to "L'esercizio fisico aiuta a ridurre la voglia.",
        "conseil_generique_3" to "Bere acqua riduce i sintomi di astinenza.",
        "conseil_generique_4" to "Circondati di persone che ti sostengono.",
        "conseil_generique_5" to "Ogni passo conta nel tuo percorso.",
        "conseil_generique_6" to "La motivazione è la chiave del successo.",
        
        // Conseils cas 1: Rien
        "conseil_cas1_1" to "Benvenuto! Configura le tue abitudini per un monitoraggio personalizzato.",
        "conseil_cas1_2" to "Ogni passo conta nel tuo percorso.",
        "conseil_cas1_3" to "La motivazione è la chiave del successo.",
        
        // Conseils cas 3: Coûts uniquement
        "conseil_cas3_1" to "Ridurre il tuo consumo ti farà risparmiare denaro.",
        "conseil_cas3_2" to "Il denaro risparmiato può essere usato per i tuoi progetti.",
        "conseil_cas3_3" to "Questi risparmi si accumulano giorno dopo giorno.",
        
        // Conseils cas 4: Habitudes uniquement
        "conseil_cas4_1" to "Seguire le tue abitudini ti aiuta a progredire.",
        "conseil_cas4_2" to "Stabilisci obiettivi realistici.",
        
        // Conseils cas 5: Dates uniquement
        "conseil_cas5_1" to "Il tuo obiettivo si avvicina!",
        "conseil_cas5_2" to "Rimani concentrato sulla tua data.",
        
        // Comparaison habitudes
        "habitudes_moins" to "Stai consumando meno del solito, ottimo lavoro!",
        "habitudes_egal" to "Sei nelle tue abitudini abituali.",
        "habitudes_plus" to "Stai superando le tue abitudini, attenzione!",
        "habitudes_suivre" to "Segui le tue abitudini per progredire.",
        
        // Messages dates
        "date_jours_restants" to "Solo %d giorni fino alla tua data di smettere!",
        "date_aujourdhui" to "Oggi è la tua data di smettere, rimani forte!",
        "date_depassee" to "Hai superato la tua data di smettere, continua così!",
        "date_reduction" to "Data di riduzione: tra %d giorni!",
        "date_rapproche" to "Il tuo obiettivo si avvicina!",
        
        // Messages économies
        "economies_jour" to "Stai risparmiando %.2f oggi!",
        "economies_reelles" to "Risparmi reali: %.2f€ vs le tue abitudini!",
        "economies_accumulent" to "Questi risparmi si accumulano rapidamente.",

                "btn_ok" to "Chiudi",
        
        // Messages erreurs
        "erreur_chargement" to "Errore di caricamento",
        "erreur_ajout" to "Errore nell'aggiunta del consumo",
        "erreur_retrait" to "Impossibile rimuovere, nessun consumo per oggi",
        "erreur_generale" to "Errore: %s"
    )

    // ==================== РУССКИЙ ====================
    private val TRADUCTIONS_RU = mapOf(
        // Labels catégories
        "label_cigarettes" to "Сигареты",
        "label_joints" to "Косяки",
        "label_alcool_global" to "Алкоголь общий",
        "label_bieres" to "Пиво",
        "label_liqueurs" to "Ликёры",
        "label_alcool_fort" to "Крепкий алкоголь",
        
        // Bandeau profil
        "profil_complet" to "Профиль: Полный ✓",
        "profil_incomplet" to "Профиль: Неполный",
        "total_aujourdhui" to "Всего сегодня:",
        
        // Conseils génériques
        "conseil_generique_1" to "Каждая невыкуренная сигарета добавляет 11 минут к вашей жизни.",
        "conseil_generique_2" to "Физические упражнения помогают уменьшить тягу.",
        "conseil_generique_3" to "Питьё воды уменьшает симптомы абстиненции.",
        "conseil_generique_4" to "Окружите себя поддерживающими людьми.",
        "conseil_generique_5" to "Каждый шаг важен на вашем пути.",
        "conseil_generique_6" to "Мотивация - ключ к успеху.",
        
        // Conseils cas 1: Rien
        "conseil_cas1_1" to "Добро пожаловать! Настройте свои привычки для персонализированного отслеживания.",
        "conseil_cas1_2" to "Каждый шаг важен на вашем пути.",
        "conseil_cas1_3" to "Мотивация - ключ к успеху.",
        
        // Conseils cas 3: Coûts uniquement
        "conseil_cas3_1" to "Сокращение потребления сэкономит вам деньги.",
        "conseil_cas3_2" to "Сэкономленные деньги можно использовать для ваших проектов.",
        "conseil_cas3_3" to "Эти сбережения накапливаются день за днём.",
        
        // Conseils cas 4: Habitudes uniquement
        "conseil_cas4_1" to "Отслеживание привычек помогает вам прогрессировать.",
        "conseil_cas4_2" to "Ставьте реалистичные цели.",
        
        // Conseils cas 5: Dates uniquement
        "conseil_cas5_1" to "Ваша цель приближается!",
        "conseil_cas5_2" to "Оставайтесь сосредоточенными на своей дате.",
        
        // Comparaison habitudes
        "habitudes_moins" to "Вы потребляете меньше обычного, отличная работа!",
        "habitudes_egal" to "Вы в рамках своих обычных привычек.",
        "habitudes_plus" to "Вы превышаете свои привычки, будьте осторожны!",
        "habitudes_suivre" to "Отслеживайте свои привычки для прогресса.",
        
        // Messages dates
        "date_jours_restants" to "Только %d дней до даты отказа!",
        "date_aujourdhui" to "Сегодня ваша дата отказа, будьте сильными!",
        "date_depassee" to "Вы прошли дату отказа, продолжайте!",
        "date_reduction" to "Дата сокращения: через %d дней!",
        "date_rapproche" to "Ваша цель приближается!",
        
        // Messages économies
        "economies_jour" to "Вы экономите %.2f сегодня!",
        "economies_reelles" to "Реальная экономия: %.2f₽ vs ваши привычки!",
        "economies_accumulent" to "Эти сбережения быстро накапливаются.",

                "btn_ok" to "Закрыть",
        
        // Messages erreurs
        "erreur_chargement" to "Ошибка загрузки",
        "erreur_ajout" to "Ошибка при добавлении потребления",
        "erreur_retrait" to "Нельзя уменьшить: нет потребления за сегодня",
        "erreur_generale" to "Ошибка: %s"
    )
    
    // ==================== العربية (ARABE) ====================
    private val TRADUCTIONS_AR = mapOf(
        // Labels catégories
        "label_cigarettes" to "السجائر",
        "label_joints" to "المفاصل",
        "label_alcool_global" to "الكحول العام",
        "label_bieres" to "البيرة",
        "label_liqueurs" to "المشروبات الكحولية",
        "label_alcool_fort" to "الكحول القوي",
        
        // Bandeau profil
        "profil_complet" to "✓ الملف: كامل",
        "profil_incomplet" to "الملف: غير كامل",
        "total_aujourdhui" to ":المجموع اليوم",
        
        // Conseils génériques
        "conseil_generique_1" to "كل سيجارة غير مدخنة تضيف 11 دقيقة لحياتك.",
        "conseil_generique_2" to "التمارين الرياضية تساعد على تقليل الرغبة.",
        "conseil_generique_3" to "شرب الماء يقلل من أعراض الانسحاب.",
        "conseil_generique_4" to "أحط نفسك بأشخاص داعمين.",
        "conseil_generique_5" to "كل خطوة مهمة في رحلتك.",
        "conseil_generique_6" to "الدافع هو مفتاح النجاح.",
        
        // Conseils cas 1: Rien
        "conseil_cas1_1" to "مرحبا! قم بتكوين عاداتك للمتابعة الشخصية.",
        "conseil_cas1_2" to "كل خطوة مهمة في رحلتك.",
        "conseil_cas1_3" to "الدافع هو مفتاح النجاح.",
        
        // Conseils cas 3: Coûts uniquement
        "conseil_cas3_1" to "تقليل استهلاكك سيوفر لك المال.",
        "conseil_cas3_2" to "يمكن استخدام الأموال الموفرة لمشاريعك.",
        "conseil_cas3_3" to "هذه المدخرات تتراكم يوماً بعد يوم.",
        
        // Conseils cas 4: Habitudes uniquement
        "conseil_cas4_1" to "تتبع عاداتك يساعدك على التقدم.",
        "conseil_cas4_2" to "حدد أهدافاً واقعية.",
        
        // Conseils cas 5: Dates uniquement
        "conseil_cas5_1" to "!هدفك يقترب",
        "conseil_cas5_2" to "ابق مركزاً على موعدك.",
        
        // Comparaison habitudes
        "habitudes_moins" to "!أنت تستهلك أقل من المعتاد، عمل رائع",
        "habitudes_egal" to "أنت في إطار عاداتك المعتادة.",
        "habitudes_plus" to "!أنت تتجاوز عاداتك، كن حذراً",
        "habitudes_suivre" to "تتبع عاداتك للتقدم.",
        
        // Messages dates
        "date_jours_restants" to "!فقط %d أيام حتى تاريخ الإقلاع",
        "date_aujourdhui" to "!اليوم هو تاريخ إقلاعك، كن قوياً",
        "date_depassee" to "!لقد تجاوزت تاريخ الإقلاع، استمر",
        "date_reduction" to "!تاريخ التخفيض: في %d أيام",
        "date_rapproche" to "!هدفك يقترب",
        
        // Messages économies
        "economies_jour" to "!أنت توفر %.2f﷼ اليوم",
        "economies_reelles" to "!وفورات حقيقية: %.2f﷼ مقابل عاداتك",
        "economies_accumulent" to "هذه المدخرات تتراكم بسرعة.",

                "btn_ok" to "إغلاق",
        
        // Messages erreurs
        "erreur_chargement" to "خطأ في التحميل",
        "erreur_ajout" to "خطأ في إضافة الاستهلاك",
        "erreur_retrait" to "لا يمكن الإزالة، لا يوجد استهلاك لليوم",
        "erreur_generale" to "خطأ: %s"
    )

    // ==================== हिन्दी (HINDI) ====================
    private val TRADUCTIONS_HI = mapOf(
        // Labels catégories
        "label_cigarettes" to "सिगरेट",
        "label_joints" to "जोड़",
        "label_alcool_global" to "शराब कुल",
        "label_bieres" to "बीयर",
        "label_liqueurs" to "शराब",
        "label_alcool_fort" to "मजबूत शराब",
        
        // Bandeau profil
        "profil_complet" to "प्रोफ़ाइल: पूर्ण ✓",
        "profil_incomplet" to "प्रोफ़ाइल: अपूर्ण",
        "total_aujourdhui" to "आज कुल:",
        
        // Conseils génériques
        "conseil_generique_1" to "प्रत्येक बिना पिया हुआ सिगरेट आपके जीवन में 11 मिनट जोड़ता है।",
        "conseil_generique_2" to "शारीरिक व्यायाम इच्छा को कम करने में मदद करता है।",
        "conseil_generique_3" to "पानी पीने से वापसी के लक्षण कम होते हैं।",
        "conseil_generique_4" to "अपने आप को सहायक लोगों से घेरें।",
        "conseil_generique_5" to "आपकी यात्रा में हर कदम मायने रखता है।",
        "conseil_generique_6" to "प्रेरणा सफलता की कुंजी है।",
        
        // Conseils cas 1: Rien
        "conseil_cas1_1" to "स्वागत है! व्यक्तिगत ट्रैकिंग के लिए अपनी आदतें कॉन्फ़िगर करें।",
        "conseil_cas1_2" to "आपकी यात्रा में हर कदम मायने रखता है।",
        "conseil_cas1_3" to "प्रेरणा सफलता की कुंजी है।",
        
        // Conseils cas 3: Coûts uniquement
        "conseil_cas3_1" to "अपनी खपत कम करने से आपको पैसे बचेंगे।",
        "conseil_cas3_2" to "बचाए गए पैसे आपकी परियोजनाओं के लिए उपयोग किए जा सकते हैं।",
        "conseil_cas3_3" to "ये बचत दिन-ब-दिन जमा होती हैं।",
        
        // Conseils cas 4: Habitudes uniquement
        "conseil_cas4_1" to "अपनी आदतों को ट्रैक करने से आपको प्रगति करने में मदद मिलती है।",
        "conseil_cas4_2" to "यथार्थवादी लक्ष्य निर्धारित करें।",
        
        // Conseils cas 5: Dates uniquement
        "conseil_cas5_1" to "आपका लक्ष्य नजदीक आ रहा है!",
        "conseil_cas5_2" to "अपनी तिथि पर केंद्रित रहें।",
        
        // Comparaison habitudes
        "habitudes_moins" to "आप सामान्य से कम खपत कर रहे हैं, बढ़िया काम!",
        "habitudes_egal" to "आप अपनी सामान्य आदतों के भीतर हैं।",
        "habitudes_plus" to "आप अपनी आदतों से अधिक हैं, सावधान रहें!",
        "habitudes_suivre" to "प्रगति के लिए अपनी आदतों को ट्रैक करें।",
        
        // Messages dates
        "date_jours_restants" to "आपकी छोड़ने की तिथि तक केवल %d दिन!",
        "date_aujourdhui" to "आज आपकी छोड़ने की तिथि है, मजबूत रहें!",
        "date_depassee" to "आपने अपनी छोड़ने की तिथि पार कर ली है, जारी रखें!",
        "date_reduction" to "कमी की तिथि: %d दिनों में!",
        "date_rapproche" to "आपका लक्ष्य नजदीक आ रहा है!",
        
        // Messages économies
        "economies_jour" to "आप आज ₹%.2f बचा रहे हैं!",
        "economies_reelles" to "वास्तविक बचत: ₹%.2f बनाम आपकी आदतें!",
        "economies_accumulent" to "ये बचत तेजी से जमा होती हैं।",

                "btn_ok" to "बंद करें",
        
        // Messages erreurs
        "erreur_chargement" to "लोड करने में त्रुटि",
        "erreur_ajout" to "खपत जोड़ने में त्रुटि",
        "erreur_retrait" to "घटाना संभव नहीं, आज के लिए कोई खपत नहीं है",
        "erreur_generale" to "त्रुटि: %s"
    )

    // ==================== 日本語 (JAPONAIS) ====================
    private val TRADUCTIONS_JA = mapOf(
        // Labels catégories
        "label_cigarettes" to "タバコ",
        "label_joints" to "ジョイント",
        "label_alcool_global" to "アルコール全般",
        "label_bieres" to "ビール",
        "label_liqueurs" to "リキュール",
        "label_alcool_fort" to "強いアルコール",
        
        // Bandeau profil
        "profil_complet" to "プロフィール：完全 ✓",
        "profil_incomplet" to "プロフィール：不完全",
        "total_aujourdhui" to "今日の合計：",
        
        // Conseils génériques
        "conseil_generique_1" to "吸わなかったタバコ1本ごとに、あなたの人生に11分が追加されます。",
        "conseil_generique_2" to "身体運動は欲求を減らすのに役立ちます。",
        "conseil_generique_3" to "水を飲むと離脱症状が軽減されます。",
        "conseil_generique_4" to "支えてくれる人々に囲まれてください。",
        "conseil_generique_5" to "あなたの旅のすべてのステップが重要です。",
        "conseil_generique_6" to "モチベーションは成功の鍵です。",
        
        // Conseils cas 1: Rien
        "conseil_cas1_1" to "ようこそ！パーソナライズされた追跡のために習慣を設定してください。",
        "conseil_cas1_2" to "あなたの旅のすべてのステップが重要です。",
        "conseil_cas1_3" to "モチベーションは成功の鍵です。",
        
        // Conseils cas 3: Coûts uniquement
        "conseil_cas3_1" to "消費を減らすとお金が節約できます。",
        "conseil_cas3_2" to "節約したお金はプロジェクトに使えます。",
        "conseil_cas3_3" to "これらの節約は日々積み重なっていきます。",
        
        // Conseils cas 4: Habitudes uniquement
        "conseil_cas4_1" to "習慣を追跡することで進歩に役立ちます。",
        "conseil_cas4_2" to "現実的な目標を設定してください。",
        
        // Conseils cas 5: Dates uniquement
        "conseil_cas5_1" to "あなたの目標が近づいています！",
        "conseil_cas5_2" to "あなたの日付に集中し続けてください。",
        
        // Comparaison habitudes
        "habitudes_moins" to "いつもより消費が少ないです、素晴らしい仕事です！",
        "habitudes_egal" to "あなたは通常の習慣の範囲内です。",
        "habitudes_plus" to "あなたは習慣を超えています、注意してください！",
        "habitudes_suivre" to "進歩のために習慣を追跡してください。",
        
        // Messages dates
        "date_jours_restants" to "禁煙日まであと%d日だけです！",
        "date_aujourdhui" to "今日はあなたの禁煙日です、強くいてください！",
        "date_depassee" to "禁煙日を過ぎました、続けてください！",
        "date_reduction" to "削減日：%d日後！",
        "date_rapproche" to "あなたの目標が近づいています！",
        
        // Messages économies
        "economies_jour" to "今日¥%.2f節約しています！",
        "economies_reelles" to "実際の節約：¥%.2f対あなたの習慣！",
        "economies_accumulent" to "これらの節約は急速に積み重なります。",

                "btn_ok" to "閉じる",
        
        // Messages erreurs
        "erreur_chargement" to "読み込みエラー",
        "erreur_ajout" to "消費追加エラー",
        "erreur_retrait" to "減らせません。本日の消費がありません。",
        "erreur_generale" to "エラー：%s"
    )

    // ==================== NEDERLANDS ====================
private val TRADUCTIONS_NL = mapOf(
    // Labels catégories
    "label_cigarettes" to "Sigaretten",
    "label_joints" to "Joints",
    "label_alcool_global" to "Alcohol (globaal)",
    "label_bieres" to "Bieren",
    "label_liqueurs" to "Likeuren",
    "label_alcool_fort" to "Sterke alcohol",

    // Bandeau profil
    "profil_complet" to "Profiel: Volledig ✓",
    "profil_incomplet" to "Profiel: Onvolledig",
    "total_aujourdhui" to "Totaal vandaag:",

    // Conseils génériques
    "conseil_generique_1" to "Elke niet-gerookte sigaret voegt 11 minuten toe aan je leven.",
    "conseil_generique_2" to "Lichaamsbeweging helpt de drang te verminderen.",
    "conseil_generique_3" to "Water drinken vermindert ontwenningsgevoelens.",
    "conseil_generique_4" to "Omring jezelf met ondersteunende mensen.",
    "conseil_generique_5" to "Elke stap telt in jouw traject.",
    "conseil_generique_6" to "Motivatie is de sleutel tot succes.",

    // Conseils cas 1
    "conseil_cas1_1" to "Welkom! Stel je gewoonten in voor een gepersonaliseerde opvolging.",
    "conseil_cas1_2" to "Elke stap telt in jouw traject.",
    "conseil_cas1_3" to "Motivatie is de sleutel tot succes.",

    // Conseils cas 3
    "conseil_cas3_1" to "Je consumptie verminderen bespaart je geld.",
    "conseil_cas3_2" to "Het bespaarde geld kan dienen voor je projecten.",
    "conseil_cas3_3" to "Deze besparingen stapelen zich dag na dag op.",

    // Conseils cas 4
    "conseil_cas4_1" to "Je gewoonten volgen helpt je vooruitgang te boeken.",
    "conseil_cas4_2" to "Stel realistische doelen.",

    // Conseils cas 5
    "conseil_cas5_1" to "Je doel komt dichterbij!",
    "conseil_cas5_2" to "Blijf gefocust op je datum.",

    // Comparaison habitudes
    "habitudes_moins" to "Je gebruikt minder dan gewoonlijk, goed gedaan!",
    "habitudes_egal" to "Je zit binnen je gebruikelijke gewoonten.",
    "habitudes_plus" to "Je overschrijdt je gewoonten, let op!",
    "habitudes_suivre" to "Volg je gewoonten om vooruitgang te boeken.",

    // Messages dates
    "date_jours_restants" to "Nog %d dagen tot je stopdatum!",
    "date_aujourdhui" to "Vandaag is je stopdatum, houd vol!",
    "date_depassee" to "Je stopdatum is voorbij, blijf doorgaan!",
    "date_reduction" to "Reductiedatum: over %d dagen!",
    "date_rapproche" to "Je doel komt dichterbij!",

    // Messages économies
    "economies_jour" to "Je bespaart vandaag %.2f!",
    "economies_reelles" to "Werkelijke besparingen: %.2f t.o.v. je gewoonten!",
    "economies_accumulent" to "Deze besparingen stapelen zich dag na dag op.",

    "btn_ok" to "Sluiten",

    // Messages erreurs
    "erreur_chargement" to "Laadfout",
    "erreur_ajout" to "Fout bij toevoegen van consumptie",
    "erreur_retrait" to "Kan niet verwijderen, geen consumptie vandaag",
    "erreur_generale" to "Fout: %s"
)

    // ==================== 中文（简体） ====================
private val TRADUCTIONS_ZHS = mapOf(
    // Labels catégories
    "label_cigarettes" to "香烟",
    "label_joints" to "大麻",
    "label_alcool_global" to "酒精（总体）",
    "label_bieres" to "啤酒",
    "label_liqueurs" to "利口酒",
    "label_alcool_fort" to "烈性酒",

    // Bandeau profil
    "profil_complet" to "档案：完整 ✓",
    "profil_incomplet" to "档案：未完成",
    "total_aujourdhui" to "今日总计：",

    // Conseils génériques
    "conseil_generique_1" to "每少抽一支烟，寿命可延长11分钟。",
    "conseil_generique_2" to "运动有助于减少渴望。",
    "conseil_generique_3" to "喝水可以减轻戒断不适。",
    "conseil_generique_4" to "与支持你的人在一起。",
    "conseil_generique_5" to "每一步都很重要。",
    "conseil_generique_6" to "动力是成功的关键。",

    // Conseils cas 1
    "conseil_cas1_1" to "欢迎！请设置你的习惯以进行个性化跟踪。",
    "conseil_cas1_2" to "每一步都很重要。",
    "conseil_cas1_3" to "动力是成功的关键。",

    // Conseils cas 3
    "conseil_cas3_1" to "减少消费可以帮你省钱。",
    "conseil_cas3_2" to "节省下来的钱可以用于你的计划。",
    "conseil_cas3_3" to "这些节省会日积月累。",

    // Conseils cas 4
    "conseil_cas4_1" to "追踪你的习惯有助于进步。",
    "conseil_cas4_2" to "设定现实的目标。",

    // Conseils cas 5
    "conseil_cas5_1" to "你的目标越来越近了！",
    "conseil_cas5_2" to "专注于你的日期。",

    // Comparaison habitudes
    "habitudes_moins" to "你的消费比平时少，做得好！",
    "habitudes_egal" to "你保持在平常水平。",
    "habitudes_plus" to "你的消费超过平常，请注意！",
    "habitudes_suivre" to "坚持追踪你的习惯以取得进展。",

    // Messages dates
    "date_jours_restants" to "距离你的戒断日期还有 %d 天！",
    "date_aujourdhui" to "今天是你的戒断日期，加油！",
    "date_depassee" to "你已超过戒断日期，请继续坚持！",
    "date_reduction" to "减少日期：%d 天后！",
    "date_rapproche" to "你的目标正在接近！",

    // Messages économies
    "economies_jour" to "你今天节省了 %.2f！",
    "economies_reelles" to "实际节省：%.2f（相比你的习惯）！",
    "economies_accumulent" to "这些节省会每天不断累积。",

    "btn_ok" to "关闭",

    // Messages erreurs
    "erreur_chargement" to "加载错误",
    "erreur_ajout" to "添加消费时出错",
    "erreur_retrait" to "无法移除，今天没有消费记录",
    "erreur_generale" to "错误：%s"
)

    // ==================== 中文（繁體） ====================
private val TRADUCTIONS_ZHT = mapOf(
    // Labels catégories
    "label_cigarettes" to "香菸",
    "label_joints" to "大麻",
    "label_alcool_global" to "酒精（整體）",
    "label_bieres" to "啤酒",
    "label_liqueurs" to "利口酒",
    "label_alcool_fort" to "烈酒",

    // Bandeau profil
    "profil_complet" to "檔案：完整 ✓",
    "profil_incomplet" to "檔案：未完成",
    "total_aujourdhui" to "今日總計：",

    // Conseils génériques
    "conseil_generique_1" to "每少抽一支菸，可延長壽命11分鐘。",
    "conseil_generique_2" to "運動有助於減少渴望。",
    "conseil_generique_3" to "喝水可以減輕戒斷不適。",
    "conseil_generique_4" to "與支持你的人為伍。",
    "conseil_generique_5" to "每一步都很重要。",
    "conseil_generique_6" to "動力是成功的關鍵。",

    // Conseils cas 1
    "conseil_cas1_1" to "歡迎！請設定你的習慣以進行個人化追蹤。",
    "conseil_cas1_2" to "每一步都很重要。",
    "conseil_cas1_3" to "動力是成功的關鍵。",

    // Conseils cas 3
    "conseil_cas3_1" to "減少消費能幫助你省錢。",
    "conseil_cas3_2" to "省下的錢可以用於你的計畫。",
    "conseil_cas3_3" to "這些節省會日積月累。",

    // Conseils cas 4
    "conseil_cas4_1" to "追蹤你的習慣有助於進步。",
    "conseil_cas4_2" to "設定實際的目標。",

    // Conseils cas 5
    "conseil_cas5_1" to "你的目標越來越近了！",
    "conseil_cas5_2" to "專注於你的日期。",

    // Comparaison habitudes
    "habitudes_moins" to "你的消費比平時少，做得好！",
    "habitudes_egal" to "你維持在平常水準。",
    "habitudes_plus" to "你的消費超過平常，請注意！",
    "habitudes_suivre" to "持續追蹤你的習慣以取得進展。",

    // Messages dates
    "date_jours_restants" to "距離你的戒斷日期還有 %d 天！",
    "date_aujourdhui" to "今天是你的戒斷日期，加油！",
    "date_depassee" to "你已超過戒斷日期，請繼續堅持！",
    "date_reduction" to "減少日期：%d 天後！",
    "date_rapproche" to "你的目標正在接近！",

    // Messages économies
    "economies_jour" to "你今天節省了 %.2f！",
    "economies_reelles" to "實際節省：%.2f（相比你的習慣）！",
    "economies_accumulent" to "這些節省會每天不斷累積。",

    "btn_ok" to "關閉",

    // Messages erreurs
    "erreur_chargement" to "載入錯誤",
    "erreur_ajout" to "新增消費時發生錯誤",
    "erreur_retrait" to "無法移除，今天沒有消費記錄",
    "erreur_generale" to "錯誤：%s"
)

    /**
     * Fonction helper pour formater les messages avec paramètres
     * Exemple: formatMessage("date_jours_restants", 5) -> "Plus que 5 jours..."
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
}
