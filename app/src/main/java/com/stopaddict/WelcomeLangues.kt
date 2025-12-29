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

    private val TRADUCTIONS_EN = mapOf(
        "welcome_title" to "Welcome",
        "welcome_checkbox_hide" to "Don't show the welcome message again",
        "welcome_ok" to "OK"
    )
    
    private val TRADUCTIONS_ES = mapOf(
        "welcome_title" to "Bienvenido",
        "welcome_checkbox_hide" to "No volver a mostrar el mensaje de bienvenida",
        "welcome_ok" to "OK"
    )
    
    private val TRADUCTIONS_PT = mapOf(
        "welcome_title" to "Bem-vindo",
        "welcome_checkbox_hide" to "Não mostrar novamente a mensagem de boas-vindas",
        "welcome_ok" to "OK"
    )
    
    private val TRADUCTIONS_DE = mapOf(
        "welcome_title" to "Willkommen",
        "welcome_checkbox_hide" to "Willkommensnachricht nicht mehr anzeigen",
        "welcome_ok" to "OK"
    )
    
    private val TRADUCTIONS_IT = mapOf(
        "welcome_title" to "Benvenuto",
        "welcome_checkbox_hide" to "Non mostrare più il messaggio di benvenuto",
        "welcome_ok" to "OK"
    )
    
    private val TRADUCTIONS_RU = mapOf(
        "welcome_title" to "Добро пожаловать",
        "welcome_checkbox_hide" to "Больше не показывать приветственное сообщение",
        "welcome_ok" to "ОК"
    )
    
    private val TRADUCTIONS_AR = mapOf(
        "welcome_title" to "مرحبًا",
        "welcome_checkbox_hide" to "عدم عرض رسالة الترحيب مرة أخرى",
        "welcome_ok" to "حسنًا"
    )
    
    private val TRADUCTIONS_HI = mapOf(
        "welcome_title" to "स्वागत है",
        "welcome_checkbox_hide" to "स्वागत संदेश फिर से न दिखाएँ",
        "welcome_ok" to "ठीक है"
    )
    
    private val TRADUCTIONS_JA = mapOf(
        "welcome_title" to "ようこそ",
        "welcome_checkbox_hide" to "起動時のメッセージを今後表示しない",
        "welcome_ok" to "OK"
    )
    
    private val TRADUCTIONS_NL = mapOf(
        "welcome_title" to "Welkom",
        "welcome_checkbox_hide" to "Welkomstbericht niet meer tonen",
        "welcome_ok" to "OK"
    )
    
    private val TRADUCTIONS_ZHS = mapOf(
        "welcome_title" to "欢迎",
        "welcome_checkbox_hide" to "不再显示欢迎信息",
        "welcome_ok" to "确定"
    )
    
    private val TRADUCTIONS_ZHT = mapOf(
        "welcome_title" to "歡迎",
        "welcome_checkbox_hide" to "不再顯示歡迎訊息",
        "welcome_ok" to "確定"
    )

    private val MESSAGES_EN = listOf(
    "★ Welcome! You’ve just taken a concrete first step.",
    "💡 Tip: explore the tabs—you’ll quickly see what the app can do for you.",
    "🎯 Goal: one day at a time. Every effort counts.",
    "✓ The more information you enter, the more useful the tracking becomes.",
    "🌿 Remember to breathe: cravings often pass within a few minutes.",
    "⚡ Reminder: the app is a self-tracking tool, not a judgment.",
    "★ You can enable/disable categories without losing your data.",
    "💡 Try adding your costs: you’ll see the financial impact more clearly.",
    "🎯 Set a realistic habit: steady beats perfect.",
    "✓ You can export your data whenever you want.",
    "🌿 Stay hydrated: it often helps reduce withdrawal sensations.",
    "⚡ Quick reminder: progress isn’t always linear—that’s normal.",
    "★ Check the Settings tab to personalize the app.",
    "💡 Advice: start simple, then refine (habits, costs, dates).",
    "🎯 One win = one unit less. That’s already progress.",
    "✓ Your data stays on the device: no account, no server.",
    "🌿 If you relapse, simply resume tracking: the key is to keep going.",
    "⚡ Tip: note your habits to compare your day with your “baseline”.",
    "★ Use the calendar to visualize your consistency.",
    "💡 In “overall alcohol” mode, sub-types (beer/liqueur/spirits) are disabled.",
    "🎯 You can choose the opposite: beers/liqueurs/spirits instead of overall alcohol.",
    "✓ Tracking makes more sense over several days: give it time.",
    "🌿 Move a little: walking often helps reduce cravings.",
    "⚡ Reminder: a gradual reduction is already an effective strategy.",
    "★ Update your habits if your goals change.",
    "💡 You can re-enable a category later: nothing is deleted.",
    "🎯 Consistency matters more than perfection.",
    "✓ The “Reset today” button deletes only today’s entries.",
    "🌿 The full history reset deletes everything: use with caution.",
    "⚡ Before importing, make sure the file truly comes from your StopAddict export.",
    "★ Remember to adjust the currency if you’re abroad.",
    "💡 Change the language if needed: the app restarts to apply it.",
    "🎯 Honest tracking helps you more than “perfect” tracking.",
    "✓ Compare real costs and habits: it’s motivating when it goes down.",
    "🌿 A glass of water, a breath, then a decision.",
    "⚡ You can start without configuring anything: the counter already works.",
    "★ Settings improve the app, but they’re not mandatory.",
    "💡 The chart becomes clearer when costs and habits are filled in.",
    "🎯 Every tracked day is useful data to move forward.",
    "✓ Motivation can vary: keep going anyway.",
    "🌿 Take care of yourself: the goal is reduction, not guilt.",
    "⚡ A small step today, a big result tomorrow.",
    "★ Come back tomorrow: consistency creates lasting change.",
    "💡 If you want, hide this message in Settings > Personalization."
)

    private val MESSAGES_ES = listOf(
    "★ ¡Bienvenido! Acaba de dar un primer paso concreto.",
    "💡 Consejo: explore las pestañas, pronto verá lo que la aplicación puede aportarle.",
    "🎯 Objetivo: un día a la vez. Cada esfuerzo cuenta.",
    "✓ Cuanta más información introduzca, más útil será el seguimiento.",
    "🌿 Recuerde respirar: el deseo suele pasar en pocos minutos.",
    "⚡ Recordatorio: la aplicación es una herramienta de auto-seguimiento, no un juicio.",
    "★ Puede activar o desactivar categorías sin perder sus datos.",
    "💡 Intente añadir los costes: verá el impacto financiero con mayor claridad.",
    "🎯 Fije un hábito realista: la constancia vale más que la perfección.",
    "✓ Puede exportar sus datos cuando lo desee.",
    "🌿 Manténgase hidratado: a menudo ayuda a reducir las sensaciones de abstinencia.",
    "⚡ Recordatorio rápido: el progreso no siempre es lineal, es normal.",
    "★ Consulte la pestaña Ajustes para personalizar la aplicación.",
    "💡 Consejo: empiece de forma simple y luego ajuste (hábitos, costes, fechas).",
    "🎯 Una victoria = una unidad menos. Eso ya es un avance.",
    "✓ Sus datos permanecen en el dispositivo: sin cuenta, sin servidor.",
    "🌿 Si hay una recaída, retome el seguimiento: lo importante es continuar.",
    "⚡ Consejo: anote sus hábitos para comparar el día con su “referencia”.",
    "★ Use el calendario para visualizar su regularidad.",
    "💡 En el modo “alcohol global”, los subtipos (cerveza/licor/fuerte) están desactivados.",
    "🎯 Puede elegir lo contrario: cervezas/licores/alcohol fuerte en lugar de alcohol global.",
    "✓ El seguimiento es más significativo a lo largo de varios días: dé tiempo al proceso.",
    "🌿 Muévase un poco: caminar suele ayudar a reducir el deseo.",
    "⚡ Recordatorio: una reducción progresiva ya es una estrategia eficaz.",
    "★ Actualice sus hábitos si cambian sus objetivos.",
    "💡 Puede reactivar una categoría más adelante: nada se elimina.",
    "🎯 La constancia es más importante que la perfección.",
    "✓ El botón “Reinicio del día” elimina solo los datos del día actual.",
    "🌿 El reinicio del historial elimina todo: úselo con precaución.",
    "⚡ Antes de importar, verifique que el archivo proviene de su exportación StopAddict.",
    "★ Recuerde ajustar la moneda si se encuentra en el extranjero.",
    "💡 Cambie el idioma si es necesario: la aplicación se reinicia para aplicarlo.",
    "🎯 Un seguimiento honesto ayuda más que uno “perfecto”.",
    "✓ Compare los costes reales y los hábitos: es motivador cuando disminuyen.",
    "🌿 Un vaso de agua, una respiración y luego una decisión.",
    "⚡ Puede empezar sin configurar nada: el contador ya funciona.",
    "★ Los ajustes mejoran la aplicación, pero no son obligatorios.",
    "💡 El gráfico es más claro cuando los costes y hábitos están completos.",
    "🎯 Cada día registrado es un dato útil para avanzar.",
    "✓ La motivación puede variar: continúe de todos modos.",
    "🌿 Cuídese: el objetivo es reducir, no sentirse culpable.",
    "⚡ Un pequeño paso hoy, un gran resultado mañana.",
    "★ Vuelva mañana: la regularidad crea cambios duraderos.",
    "💡 Si lo desea, oculte este mensaje en Ajustes > Personalización."
)

    private val MESSAGES_PT = listOf(
    "★ Bem-vindo(a)! Você acaba de dar um primeiro passo concreto.",
    "💡 Dica: explore as abas, você verá rapidamente o que o aplicativo pode oferecer.",
    "🎯 Objetivo: um dia de cada vez. Cada esforço conta.",
    "✓ Quanto mais informações você registrar, mais útil será o acompanhamento.",
    "🌿 Lembre-se de respirar fundo: a vontade geralmente passa em alguns minutos.",
    "⚡ Lembrete: este aplicativo é uma ferramenta de autoacompanhamento, não um julgamento.",
    "★ Você pode ativar/desativar categorias sem perder seus dados.",
    "💡 Experimente adicionar os custos: você verá o impacto financeiro com mais clareza.",
    "🎯 Defina um hábito realista: constância é melhor do que perfeição.",
    "✓ Você pode exportar seus dados a qualquer momento.",
    "🌿 Hidrate-se: isso geralmente ajuda a reduzir a vontade.",
    "⚡ Pequeno lembrete: o progresso nem sempre é linear, e isso é normal.",
    "★ Consulte a aba Configurações para personalizar o aplicativo.",
    "💡 Conselho: comece simples e ajuste depois (hábitos, custos, datas).",
    "🎯 Uma vitória = uma unidade a menos. Isso já é progresso.",
    "✓ Seus dados permanecem no dispositivo: sem conta, sem servidor.",
    "🌿 Se houver recaída, retome o acompanhamento: o importante é continuar.",
    "⚡ Dica: registre seus hábitos para comparar seu dia com sua “referência”.",
    "★ Use o calendário para visualizar sua regularidade.",
    "💡 No modo “álcool total”, os subtipos (cerveja/licor/destilado) ficam desativados.",
    "🎯 Você também pode escolher o inverso: cerveja/licor/destilado em vez de álcool total.",
    "✓ O acompanhamento fica mais claro ao longo de vários dias.",
    "🌿 Mexa-se um pouco: caminhar ajuda a reduzir a vontade.",
    "⚡ Lembrete: reduzir gradualmente já é uma estratégia eficaz.",
    "★ Atualize seus hábitos se seus objetivos mudarem.",
    "💡 Você pode reativar uma categoria depois: nada é apagado.",
    "🎯 A sua constância vale mais do que a perfeição.",
    "✓ O botão Reset do dia remove apenas o dia atual.",
    "🌿 O reset do histórico apaga todo o histórico: use com cuidado.",
    "⚡ Antes de importar, verifique se o arquivo vem do seu export StopAddict.",
    "★ Não se esqueça de ajustar a moeda se estiver no exterior.",
    "💡 Ao mudar o idioma, o aplicativo será reiniciado para aplicar.",
    "🎯 Um acompanhamento honesto ajuda mais do que um acompanhamento “perfeito”.",
    "✓ Compare custos reais e hábitos: é motivador quando os números diminuem.",
    "🌿 Um copo de água, uma respiração, depois uma decisão.",
    "⚡ Você pode começar sem configurar nada: o contador já funciona.",
    "★ As configurações enriquecem o aplicativo, mas não são obrigatórias.",
    "💡 O gráfico fica mais claro quando custos e hábitos estão preenchidos.",
    "🎯 Cada dia registrado é um dado útil para progredir.",
    "✓ Sua motivação pode variar: continue mesmo assim.",
    "🌿 Cuide de você: o objetivo é reduzir, não sentir culpa.",
    "⚡ Um pequeno passo hoje, um grande resultado amanhã.",
    "★ Volte amanhã: a regularidade cria mudanças duradouras.",
    "💡 Se quiser, você pode ocultar esta mensagem em Configurações > Personalização."
)

    private val MESSAGES_DE = listOf(
    "★ Willkommen! Sie haben gerade einen konkreten ersten Schritt gemacht.",
    "💡 Tipp: Erkunden Sie die Registerkarten – Sie werden schnell sehen, was die App Ihnen bietet.",
    "🎯 Ziel: ein Tag nach dem anderen. Jede Anstrengung zählt.",
    "✓ Je mehr Informationen Sie eingeben, desto nützlicher wird die Nachverfolgung.",
    "🌿 Denken Sie ans Atmen: das Verlangen lässt oft nach wenigen Minuten nach.",
    "⚡ Erinnerung: Die App ist ein Werkzeug zur Selbstbeobachtung, kein Urteil.",
    "★ Sie können Kategorien aktivieren oder deaktivieren, ohne Ihre Daten zu verlieren.",
    "💡 Versuchen Sie, Ihre Kosten hinzuzufügen: So erkennen Sie die finanziellen Auswirkungen deutlicher.",
    "🎯 Setzen Sie sich eine realistische Gewohnheit: Beständigkeit ist besser als Perfektion.",
    "✓ Sie können Ihre Daten jederzeit exportieren.",
    "🌿 Ausreichend trinken hilft oft, Entzugserscheinungen zu lindern.",
    "⚡ Kurzer Hinweis: Fortschritt ist nicht immer linear – das ist normal.",
    "★ Schauen Sie in den Einstellungen vorbei, um die App zu personalisieren.",
    "💡 Tipp: Beginnen Sie einfach und verfeinern Sie später (Gewohnheiten, Kosten, Daten).",
    "🎯 Ein Erfolg = eine Einheit weniger. Das ist bereits ein Fortschritt.",
    "✓ Ihre Daten bleiben auf dem Gerät: kein Konto, kein Server.",
    "🌿 Wenn es zu einem Rückfall kommt, setzen Sie das Tracking fort – wichtig ist dranzubleiben.",
    "⚡ Tipp: Notieren Sie Ihre Gewohnheiten, um den Tag mit Ihrer „Referenz“ zu vergleichen.",
    "★ Nutzen Sie den Kalender, um Ihre Regelmäßigkeit zu visualisieren.",
    "💡 Im Modus „Gesamtalkohol“ sind Untertypen (Bier/Likör/Spirituosen) deaktiviert.",
    "🎯 Sie können auch das Gegenteil wählen: Bier/Likör/Spirituosen statt Gesamtalkohol.",
    "✓ Über mehrere Tage hinweg ist das Tracking aussagekräftiger – geben Sie dem Ganzen Zeit.",
    "🌿 Bewegen Sie sich etwas: Spazierengehen hilft oft, das Verlangen zu reduzieren.",
    "⚡ Erinnerung: Eine schrittweise Reduzierung ist bereits eine wirksame Strategie.",
    "★ Aktualisieren Sie Ihre Gewohnheiten, wenn sich Ihre Ziele ändern.",
    "💡 Sie können eine Kategorie später wieder aktivieren: Nichts wird gelöscht.",
    "🎯 Beständigkeit ist wichtiger als Perfektion.",
    "✓ Die Schaltfläche „Tages-Reset“ löscht nur den aktuellen Tag.",
    "🌿 Der Reset des Verlaufs löscht alles: Bitte mit Vorsicht verwenden.",
    "⚡ Prüfen Sie vor dem Import, ob die Datei wirklich aus Ihrem StopAddict-Export stammt.",
    "★ Denken Sie daran, die Währung anzupassen, wenn Sie im Ausland sind.",
    "💡 Wechseln Sie bei Bedarf die Sprache: Die App startet neu, um sie anzuwenden.",
    "🎯 Ehrliches Tracking hilft mehr als „perfektes“ Tracking.",
    "✓ Vergleichen Sie reale Kosten und Gewohnheiten: Es motiviert, wenn sie sinken.",
    "🌿 Ein Glas Wasser, ein Atemzug, dann eine Entscheidung.",
    "⚡ Sie können ohne Konfiguration starten: Der Zähler funktioniert bereits.",
    "★ Einstellungen verbessern die App, sind aber nicht zwingend erforderlich.",
    "💡 Das Diagramm wird aussagekräftiger, wenn Kosten und Gewohnheiten ausgefüllt sind.",
    "🎯 Jeder erfasste Tag ist eine nützliche Information für den Fortschritt.",
    "✓ Die Motivation kann schwanken: Machen Sie trotzdem weiter.",
    "🌿 Achten Sie auf sich: Ziel ist Reduktion, nicht Schuldgefühle.",
    "⚡ Ein kleiner Schritt heute, ein großes Ergebnis morgen.",
    "★ Kommen Sie morgen wieder: Regelmäßigkeit schafft nachhaltige Veränderungen.",
    "💡 Wenn Sie möchten, können Sie diese Nachricht unter Einstellungen > Personalisierung ausblenden."
)

    private val MESSAGES_IT = listOf(
    "★ Benvenuto! Hai appena fatto un primo passo concreto.",
    "💡 Consiglio: esplora le schede, capirai rapidamente cosa può offrirti l’app.",
    "🎯 Obiettivo: un giorno alla volta. Ogni sforzo conta.",
    "✓ Più informazioni inserisci, più il monitoraggio diventa utile.",
    "🌿 Ricorda di respirare: il desiderio spesso passa in pochi minuti.",
    "⚡ Promemoria: l’app è uno strumento di auto-monitoraggio, non un giudizio.",
    "★ Puoi attivare o disattivare le categorie senza perdere i tuoi dati.",
    "💡 Prova ad aggiungere i costi: vedrai più chiaramente l’impatto economico.",
    "🎯 Fissa un’abitudine realistica: la costanza è meglio della perfezione.",
    "✓ Puoi esportare i tuoi dati quando vuoi.",
    "🌿 Idratati: spesso aiuta a calmare le sensazioni di astinenza.",
    "⚡ Piccolo promemoria: i progressi non sono sempre lineari, ed è normale.",
    "★ Vai nella sezione Impostazioni per personalizzare l’applicazione.",
    "💡 Consiglio: inizia in modo semplice, poi affina (abitudini, costi, date).",
    "🎯 Una vittoria = un’unità in meno. È già un progresso.",
    "✓ I tuoi dati restano sul dispositivo: nessun account, nessun server.",
    "🌿 Se ricadi, riprendi semplicemente il monitoraggio: l’importante è continuare.",
    "⚡ Consiglio: annota le tue abitudini per confrontare la giornata con la tua “referenza”.",
    "★ Usa il calendario per visualizzare la tua regolarità.",
    "💡 Nella modalità “alcol globale”, i sottotipi (birra/liquori/superalcolici) sono disattivati.",
    "🎯 Puoi scegliere l’opposto: birra/liquori/superalcolici invece di alcol globale.",
    "✓ Il monitoraggio è più significativo su più giorni: lascia che il tempo faccia il suo lavoro.",
    "🌿 Muoviti un po’: camminare spesso aiuta a ridurre il desiderio.",
    "⚡ Promemoria: una riduzione progressiva è già una strategia efficace.",
    "★ Aggiorna le tue abitudini se i tuoi obiettivi cambiano.",
    "💡 Puoi riattivare una categoria più tardi: nulla viene cancellato.",
    "🎯 La costanza vale più della perfezione.",
    "✓ Il pulsante Reset del giorno elimina solo la giornata in corso.",
    "🌿 Il reset dello storico elimina tutto: usalo con cautela.",
    "⚡ Prima di un import, verifica che il file provenga dal tuo export StopAddict.",
    "★ Ricorda di regolare la valuta se sei all’estero.",
    "💡 Cambia lingua se necessario: l’app si riavvia per applicarla.",
    "🎯 Un monitoraggio onesto aiuta più di uno “perfetto”.",
    "✓ Confronta costi reali e abitudini: è motivante quando diminuiscono.",
    "🌿 Un bicchiere d’acqua, un respiro, poi una decisione.",
    "⚡ Puoi iniziare senza configurare nulla: il contatore funziona già.",
    "★ Le impostazioni arricchiscono l’app, ma non sono obbligatorie.",
    "💡 Il grafico diventa più chiaro quando costi e abitudini sono compilati.",
    "🎯 Ogni giornata monitorata è un dato utile per progredire.",
    "✓ La motivazione può variare: continua comunque.",
    "🌿 Prenditi cura di te: l’obiettivo è la riduzione, non il senso di colpa.",
    "⚡ Un piccolo passo oggi, un grande risultato domani.",
    "★ Torna domani: la regolarità crea cambiamenti duraturi.",
    "💡 Se vuoi, puoi nascondere questo messaggio in Impostazioni > Personalizzazione."
)

    private val MESSAGES_RU = listOf(
    "★ Добро пожаловать! Вы сделали первый конкретный шаг.",
    "💡 Совет: изучите вкладки — вы быстро поймёте, чем приложение может быть полезно.",
    "🎯 Цель: один день за раз. Каждое усилие имеет значение.",
    "✓ Чем больше информации вы вводите, тем полезнее становится отслеживание.",
    "🌿 Помните о дыхании: желание часто проходит через несколько минут.",
    "⚡ Напоминание: приложение — это инструмент самоконтроля, а не осуждения.",
    "★ Вы можете включать и отключать категории, не теряя данные.",
    "💡 Попробуйте добавить расходы — вы увидите финансовый эффект гораздо яснее.",
    "🎯 Ставьте реалистичную привычку: стабильность важнее совершенства.",
    "✓ Вы можете экспортировать свои данные в любое время.",
    "🌿 Пейте воду: это часто помогает снизить ощущение тяги.",
    "⚡ Небольшое напоминание: прогресс не всегда линейный — и это нормально.",
    "★ Загляните во вкладку «Настройки», чтобы персонализировать приложение.",
    "💡 Совет: начните с простого, затем уточняйте (привычки, расходы, даты).",
    "🎯 Одна победа = одна единица меньше. Это уже прогресс.",
    "✓ Ваши данные остаются на устройстве: без аккаунта, без сервера.",
    "🌿 Если произошёл срыв — просто продолжайте отслеживание. Главное — не останавливаться.",
    "⚡ Совет: записывайте привычки, чтобы сравнивать день с вашей «базовой» нормой.",
    "★ Используйте календарь, чтобы видеть регулярность.",
    "💡 В режиме «Общий алкоголь» подкатегории (пиво/ликёры/крепкий алкоголь) отключены.",
    "🎯 Вы можете выбрать обратное: пиво/ликёры/крепкий алкоголь вместо общего алкоголя.",
    "✓ Отслеживание становится нагляднее на протяжении нескольких дней.",
    "🌿 Немного движения помогает: прогулка часто снижает тягу.",
    "⚡ Напоминание: постепенное снижение — уже эффективная стратегия.",
    "★ Обновляйте привычки, если ваши цели меняются.",
    "💡 Вы можете снова активировать категорию позже — ничего не удаляется.",
    "🎯 Постоянство важнее совершенства.",
    "✓ Кнопка сброса дня удаляет только текущий день.",
    "🌿 Сброс истории удаляет всё — используйте с осторожностью.",
    "⚡ Перед импортом убедитесь, что файл получен из экспорта StopAddict.",
    "★ Не забудьте изменить валюту, если вы находитесь за границей.",
    "💡 При смене языка приложение перезапустится для применения изменений.",
    "🎯 Честное отслеживание полезнее «идеального».",
    "✓ Сравнивайте реальные расходы и привычки — это мотивирует, когда цифры снижаются.",
    "🌿 Стакан воды, вдох — и затем решение.",
    "⚡ Вы можете начать без настроек: счётчик уже работает.",
    "★ Настройки расширяют возможности приложения, но не обязательны.",
    "💡 График становится нагляднее, когда заполнены расходы и привычки.",
    "🎯 Каждый отслеженный день — полезные данные для прогресса.",
    "✓ Мотивация может меняться — продолжайте несмотря ни на что.",
    "🌿 Заботьтесь о себе: цель — сокращение, а не чувство вины.",
    "⚡ Маленький шаг сегодня — большой результат завтра.",
    "★ Возвращайтесь завтра: регулярность создаёт устойчивые изменения.",
    "💡 При желании вы можете скрыть это сообщение в Настройки > Персонализация."
)
    
    private val MESSAGES_AR = listOf(
    "★ مرحبًا! لقد اتخذت أول خطوة حقيقية.",
    "💡 نصيحة: استكشف الأقسام، وستفهم سريعًا ما يمكن أن يقدمه التطبيق.",
    "🎯 الهدف: يومًا بعد يوم. كل جهد له قيمة.",
    "✓ كلما أدخلت معلومات أكثر، أصبح التتبع أكثر فائدة.",
    "🌿 تذكر التنفس: الرغبة غالبًا ما تزول بعد بضع دقائق.",
    "⚡ تذكير: التطبيق أداة للمتابعة الذاتية، وليس للحكم.",
    "★ يمكنك تفعيل أو تعطيل الفئات دون فقدان بياناتك.",
    "💡 جرّب إضافة التكاليف، سترى التأثير المالي بشكل أوضح.",
    "🎯 حدّد عادة واقعية: الاستمرارية أهم من الكمال.",
    "✓ يمكنك تصدير بياناتك في أي وقت.",
    "🌿 اشرب الماء: غالبًا ما يساعد ذلك على تهدئة الشعور بالرغبة.",
    "⚡ تذكير صغير: التقدم ليس دائمًا خطيًا، وهذا طبيعي.",
    "★ تفقّد قسم الإعدادات لتخصيص التطبيق حسب احتياجاتك.",
    "💡 نصيحة: ابدأ ببساطة، ثم حسّن الإعدادات (العادات، التكاليف، التواريخ).",
    "🎯 انتصار واحد = وحدة أقل. هذا تقدم حقيقي.",
    "✓ بياناتك تبقى على جهازك: لا حساب، لا خادم.",
    "🌿 إذا حدثت انتكاسة، فقط واصل المتابعة. الأهم هو الاستمرار.",
    "⚡ نصيحة: دوّن عاداتك لمقارنة يومك بـ«المرجع» الخاص بك.",
    "★ استخدم التقويم لرؤية انتظامك.",
    "💡 في وضع «الكحول العام»، يتم تعطيل الأنواع الفرعية (بيرة/مشروبات/كحول قوي).",
    "🎯 يمكنك اختيار العكس: بيرة/مشروبات/كحول قوي بدل الكحول العام.",
    "✓ المتابعة تصبح أوضح على مدى عدة أيام.",
    "🌿 القليل من الحركة يساعد: المشي غالبًا يقلل الرغبة.",
    "⚡ تذكير: التقليل التدريجي هو استراتيجية فعّالة.",
    "★ حدّث عاداتك إذا تغيّرت أهدافك.",
    "💡 يمكنك إعادة تفعيل فئة لاحقًا: لا يتم حذف أي شيء.",
    "🎯 الاستمرارية أهم من الكمال.",
    "✓ زر إعادة تعيين اليوم يحذف اليوم الحالي فقط.",
    "🌿 إعادة تعيين السجل تحذف كل شيء: استخدمها بحذر.",
    "⚡ قبل الاستيراد، تأكد أن الملف ناتج عن تصدير StopAddict.",
    "★ لا تنسَ تعديل العملة إذا كنت في الخارج.",
    "💡 عند تغيير اللغة، يُعاد تشغيل التطبيق لتطبيق التغيير.",
    "🎯 المتابعة الصادقة أنفع من المتابعة «المثالية».",
    "✓ قارن التكاليف الفعلية والعادات: هذا محفّز عندما تنخفض الأرقام.",
    "🌿 كوب ماء، نفس عميق، ثم قرار.",
    "⚡ يمكنك البدء دون أي إعداد: العداد يعمل بالفعل.",
    "★ الإعدادات تعزّز التطبيق لكنها غير إلزامية.",
    "💡 يصبح الرسم البياني أوضح عند إدخال التكاليف والعادات.",
    "🎯 كل يوم تتم متابعته هو بيانات مفيدة للتقدم.",
    "✓ قد تتغير الدافعية: واصل رغم ذلك.",
    "🌿 اعتنِ بنفسك: الهدف هو التقليل، لا الشعور بالذنب.",
    "⚡ خطوة صغيرة اليوم، نتيجة كبيرة غدًا.",
    "★ عد غدًا: الاستمرارية تصنع التغييرات الدائمة.",
    "💡 إذا رغبت، يمكنك إخفاء هذه الرسالة من الإعدادات > التخصيص."
)

    private val MESSAGES_HI = listOf(
    "★ स्वागत है! आपने एक ठोस पहला कदम उठाया है।",
    "💡 सुझाव: टैब्स देखें, आपको जल्दी समझ आ जाएगा कि ऐप क्या कर सकता है।",
    "🎯 लक्ष्य: एक दिन में एक कदम। हर प्रयास मायने रखता है।",
    "✓ जितनी अधिक जानकारी आप भरते हैं, उतना ही उपयोगी ट्रैकिंग होती है।",
    "🌿 सांस लें: इच्छा अक्सर कुछ मिनटों में कम हो जाती है।",
    "⚡ याद रखें: यह ऐप आत्म-निगरानी का उपकरण है, निर्णय का नहीं।",
    "★ आप डेटा खोए बिना श्रेणियाँ चालू/बंद कर सकते हैं।",
    "💡 लागत जोड़ने की कोशिश करें: वित्तीय प्रभाव साफ़ दिखाई देगा।",
    "🎯 एक यथार्थवादी आदत तय करें: स्थिरता पूर्णता से बेहतर है।",
    "✓ आप कभी भी अपना डेटा निर्यात कर सकते हैं।",
    "🌿 पानी पिएँ: यह अक्सर cravings को शांत करने में मदद करता है।",
    "⚡ छोटा सा स्मरण: प्रगति हमेशा सीधी नहीं होती, यह सामान्य है।",
    "★ ऐप को अपने अनुसार ढालने के लिए सेटिंग्स टैब देखें।",
    "💡 सलाह: सरल शुरुआत करें, फिर धीरे-धीरे सुधार करें (आदतें, लागत, तिथियाँ)।",
    "🎯 एक जीत = एक यूनिट कम। यह भी प्रगति है।",
    "✓ आपका डेटा आपके डिवाइस पर रहता है: न खाता, न सर्वर।",
    "🌿 यदि आप फिसलते हैं, तो बस ट्रैकिंग जारी रखें: आगे बढ़ना ही महत्वपूर्ण है।",
    "⚡ सुझाव: अपनी आदतें नोट करें और दिन की तुलना अपने “रेफरेंस” से करें।",
    "★ अपनी नियमितता देखने के लिए कैलेंडर का उपयोग करें।",
    "💡 “कुल शराब” मोड में उप-प्रकार (बीयर/लिकर/हार्ड) निष्क्रिय होते हैं।",
    "🎯 आप उल्टा भी चुन सकते हैं: कुल शराब की जगह बीयर/लिकर/हार्ड।",
    "✓ कई दिनों में ट्रैकिंग अधिक स्पष्ट हो जाती है।",
    "🌿 थोड़ा हिलना-डुलना मदद करता है: चलना अक्सर craving कम करता है।",
    "⚡ याद रखें: धीरे-धीरे कम करना भी एक प्रभावी रणनीति है।",
    "★ यदि आपके लक्ष्य बदलें तो अपनी आदतें अपडेट करें।",
    "💡 आप बाद में किसी श्रेणी को फिर से सक्रिय कर सकते हैं: कुछ भी हटाया नहीं जाता।",
    "🎯 निरंतरता पूर्णता से अधिक महत्वपूर्ण है।",
    "✓ आज का RAZ बटन केवल आज का दिन हटाता है।",
    "🌿 इतिहास RAZ पूरा इतिहास हटाता है: सावधानी से उपयोग करें।",
    "⚡ आयात से पहले सुनिश्चित करें कि फ़ाइल StopAddict निर्यात से आई है।",
    "★ विदेश में हों तो मुद्रा समायोजित करना न भूलें।",
    "💡 भाषा बदलने पर ऐप परिवर्तन लागू करने के लिए पुनः शुरू होगा।",
    "🎯 ईमानदार ट्रैकिंग “परफेक्ट” ट्रैकिंग से अधिक मददगार है।",
    "✓ वास्तविक लागत और आदतों की तुलना करें: कम होने पर प्रेरणा मिलती है।",
    "🌿 एक गिलास पानी, एक सांस, फिर निर्णय।",
    "⚡ आप बिना किसी सेटअप के शुरू कर सकते हैं: काउंटर पहले से काम करता है।",
    "★ सेटिंग्स ऐप को बेहतर बनाती हैं, लेकिन अनिवार्य नहीं हैं।",
    "💡 जब लागत और आदतें भरी हों तो ग्राफ़ अधिक स्पष्ट होता है।",
    "🎯 हर ट्रैक किया गया दिन प्रगति के लिए उपयोगी डेटा है।",
    "✓ प्रेरणा बदल सकती है: फिर भी जारी रखें।",
    "🌿 अपना ध्यान रखें: लक्ष्य कम करना है, दोषी महसूस करना नहीं।",
    "⚡ आज एक छोटा कदम, कल बड़ा परिणाम।",
    "★ कल फिर आएँ: निरंतरता स्थायी बदलाव लाती है।",
    "💡 चाहें तो इस संदेश को सेटिंग्स > पर्सनलाइज़ेशन में छिपा सकते हैं।"
)

    private val MESSAGES_JA = listOf(
    "★ ようこそ！あなたは確かな第一歩を踏み出しました。",
    "💡 ヒント：タブを探索してみてください。アプリの機能がすぐに分かります。",
    "🎯 目標：一日一歩。すべての努力が大切です。",
    "✓ 入力する情報が多いほど、記録はより役立ちます。",
    "🌿 深呼吸しましょう：欲求は数分で落ち着くことが多いです。",
    "⚡ リマインダー：このアプリは自己管理のためのツールで、評価ではありません。",
    "★ データを失うことなく、カテゴリを有効/無効にできます。",
    "💡 費用を追加してみてください。金銭的な影響がより明確になります。",
    "🎯 現実的な習慣を設定しましょう。完璧さより継続が大切です。",
    "✓ データはいつでもエクスポートできます。",
    "🌿 水分補給を忘れずに。欲求を和らげる助けになります。",
    "⚡ 小さな注意：進歩は一直線ではありません。それが普通です。",
    "★ アプリをカスタマイズするには設定タブを確認してください。",
    "💡 アドバイス：まずはシンプルに、後から調整（習慣・費用・日付）。",
    "🎯 勝利＝1単位減。立派な前進です。",
    "✓ データは端末内に保存されます。アカウントもサーバーも不要です。",
    "🌿 もし失敗しても、記録を続けてください。続けることが大切です。",
    "⚡ ヒント：習慣を記録し、1日の状態を「基準」と比較しましょう。",
    "★ カレンダーで継続性を視覚的に確認できます。",
    "💡 「アルコール合計」モードでは下位分類（ビール/リキュール/強酒）は無効です。",
    "🎯 逆に、合計ではなくビール/リキュール/強酒を選ぶこともできます。",
    "✓ 数日分の記録で、より分かりやすくなります。",
    "🌿 少し体を動かしましょう。歩くことは欲求を減らす助けになります。",
    "⚡ リマインダー：段階的な減少も有効な戦略です。",
    "★ 目標が変わったら習慣を更新しましょう。",
    "💡 後からカテゴリを再有効化できます。何も削除されません。",
    "🎯 完璧さより継続が価値です。",
    "✓ 「今日のリセット」は今日分のみ削除します。",
    "🌿 「履歴リセット」は全履歴を削除します。注意して使用してください。",
    "⚡ インポート前に、StopAddictのエクスポートであることを確認してください。",
    "★ 海外では通貨の調整を忘れずに。",
    "💡 言語変更後、適用のためアプリは再起動します。",
    "🎯 正直な記録は「完璧な」記録より役立ちます。",
    "✓ 実際の費用と習慣を比較しましょう。減ると励みになります。",
    "🌿 水を一杯、深呼吸してから判断を。",
    "⚡ 何も設定せずに始められます。カウンターはすでに動いています。",
    "★ 設定は便利ですが必須ではありません。",
    "💡 費用と習慣を入力すると、グラフがより分かりやすくなります。",
    "🎯 記録した毎日は、前進のための有用なデータです。",
    "✓ モチベーションは変わります。それでも続けましょう。",
    "🌿 自分を大切に。目的は削減であり、罪悪感ではありません。",
    "⚡ 今日の小さな一歩が、明日の大きな成果に。",
    "★ また明日。継続が持続的な変化を生みます。",
    "💡 必要であれば、設定 > パーソナライズでこのメッセージを非表示にできます。"
)

    private val MESSAGES_NL = listOf(
    "★ Welkom! U heeft zojuist een concrete eerste stap gezet.",
    "💡 Tip: verken de tabbladen, u ziet snel wat de app kan bieden.",
    "🎯 Doel: dag per dag. Elke inspanning telt.",
    "✓ Hoe meer informatie u invoert, hoe nuttiger de opvolging wordt.",
    "🌿 Denk eraan diep adem te halen: de drang verdwijnt vaak binnen enkele minuten.",
    "⚡ Herinnering: deze app is een hulpmiddel voor zelfopvolging, geen oordeel.",
    "★ U kunt categorieën in- en uitschakelen zonder uw gegevens te verliezen.",
    "💡 Probeer kosten toe te voegen: u ziet de financiële impact duidelijker.",
    "🎯 Stel een realistische gewoonte in: stabiel is beter dan perfect.",
    "✓ U kunt uw gegevens op elk moment exporteren.",
    "🌿 Blijf gehydrateerd: dit helpt vaak om de drang te verminderen.",
    "⚡ Kleine herinnering: vooruitgang is niet altijd lineair, dat is normaal.",
    "★ Bekijk het tabblad Instellingen om de app te personaliseren.",
    "💡 Advies: begin eenvoudig en verfijn later (gewoonten, kosten, datums).",
    "🎯 Eén overwinning = één eenheid minder. Dat is al vooruitgang.",
    "✓ Uw gegevens blijven op het apparaat: geen account, geen server.",
    "🌿 Als u een terugval heeft, hervat gewoon de opvolging: doorgaan is het belangrijkste.",
    "⚡ Tip: noteer uw gewoonten om uw dag te vergelijken met uw “referentie”.",
    "★ Gebruik de kalender om uw regelmaat te visualiseren.",
    "💡 In de modus “alcohol totaal” zijn de subtypes (bier/likeur/sterk) uitgeschakeld.",
    "🎯 U kunt ook het omgekeerde kiezen: bier/likeur/sterk in plaats van alcohol totaal.",
    "✓ De opvolging wordt duidelijker over meerdere dagen.",
    "🌿 Beweeg even: wandelen helpt vaak om de drang te verminderen.",
    "⚡ Herinnering: geleidelijke vermindering is al een effectieve strategie.",
    "★ Werk uw gewoonten bij als uw doelen veranderen.",
    "💡 U kunt een categorie later opnieuw activeren: niets wordt verwijderd.",
    "🎯 Uw consistentie is waardevoller dan perfectie.",
    "✓ De knop Reset van vandaag verwijdert alleen de huidige dag.",
    "🌿 De historische reset verwijdert alle geschiedenis: voorzichtig gebruiken.",
    "⚡ Controleer vóór import dat het bestand van uw StopAddict-export komt.",
    "★ Vergeet niet de valuta aan te passen als u in het buitenland bent.",
    "💡 Wijzig de taal indien nodig: de app start opnieuw om toe te passen.",
    "🎯 Eerlijke opvolging helpt meer dan “perfecte” opvolging.",
    "✓ Vergelijk echte kosten en gewoonten: dat motiveert wanneer het daalt.",
    "🌿 Een glas water, een ademhaling, daarna een beslissing.",
    "⚡ U kunt beginnen zonder iets te configureren: de teller werkt al.",
    "★ Instellingen verrijken de app, maar zijn niet verplicht.",
    "💡 De grafiek wordt duidelijker wanneer kosten en gewoonten zijn ingevuld.",
    "🎯 Elke gevolgde dag is nuttige data om vooruitgang te boeken.",
    "✓ Uw motivatie kan variëren: blijf toch doorgaan.",
    "🌿 Zorg goed voor uzelf: het doel is vermindering, geen schuldgevoel.",
    "⚡ Een kleine stap vandaag, een groot resultaat morgen.",
    "★ Kom morgen terug: regelmaat zorgt voor duurzame veranderingen.",
    "💡 Als u wilt, kunt u dit bericht verbergen via Instellingen > Personalisatie."
)

    private val MESSAGES_ZHS = listOf(
    "★ 欢迎！您已经迈出了坚实的第一步。",
    "💡 提示：探索各个标签页，您会很快了解应用的功能。",
    "🎯 目标：一天一步。每一份努力都很重要。",
    "✓ 您填写的信息越多，跟踪就越有价值。",
    "🌿 记得深呼吸：欲望通常会在几分钟内减弱。",
    "⚡ 提醒：本应用是自我跟踪工具，而不是评判。",
    "★ 您可以启用/停用分类，而不会丢失数据。",
    "💡 试着添加花费：您会更清楚地看到经济影响。",
    "🎯 设定现实的习惯：稳定比完美更重要。",
    "✓ 您可以随时导出您的数据。",
    "🌿 记得补充水分：这通常有助于缓解渴望。",
    "⚡ 小提醒：进步并不总是线性的，这是正常的。",
    "★ 查看“设置”标签以个性化应用。",
    "💡 建议：先保持简单，再逐步完善（习惯、花费、日期）。",
    "🎯 一次胜利 = 减少一个单位，这已经是进步。",
    "✓ 您的数据保存在设备本地：无需账户，无服务器。",
    "🌿 若出现反复，只需继续记录：坚持最重要。",
    "⚡ 提示：记录您的习惯，与您的“参考”日进行比较。",
    "★ 使用日历来直观查看您的规律性。",
    "💡 在“酒精总量”模式下，子类型（啤酒/利口酒/烈酒）会被停用。",
    "🎯 您也可以选择相反方式：使用啤酒/利口酒/烈酒而非酒精总量。",
    "✓ 连续多天的记录会更有参考价值。",
    "🌿 适当活动一下：散步常常有助于降低欲望。",
    "⚡ 提醒：逐步减少本身就是有效策略。",
    "★ 当目标变化时，请更新您的习惯。",
    "💡 您可以稍后重新启用某个分类：不会删除任何内容。",
    "🎯 持续性比完美更有价值。",
    "✓ “今日重置”仅会删除当天的数据。",
    "🌿 “历史重置”会删除全部历史：请谨慎使用。",
    "⚡ 导入前请确认文件来自您的 StopAddict 导出。",
    "★ 出国时别忘了调整货币。",
    "💡 如需更改语言，应用将重启以生效。",
    "🎯 真实的记录比“完美”的记录更有帮助。",
    "✓ 对比实际花费与习惯：当数字下降时很有激励作用。",
    "🌿 喝一杯水，深呼吸，然后再做决定。",
    "⚡ 您无需任何配置即可开始：计数器已经在运行。",
    "★ 设置能丰富应用，但并非必需。",
    "💡 当填写了花费和习惯后，图表会更清晰。",
    "🎯 每一天的记录都是前进的有用数据。",
    "✓ 动力可能会起伏：即便如此也要继续。",
    "🌿 请善待自己：目标是减少，而不是内疚。",
    "⚡ 今天的一小步，明天的大成果。",
    "★ 明天再来：规律带来持久的改变。",
    "💡 如有需要，可在 设置 > 个性化 中隐藏此消息。"
)

    private val MESSAGES_ZHT = listOf(
    "★ 歡迎！您已踏出穩健的第一步。",
    "💡 提示：探索各個分頁，您很快就能了解應用程式的功能。",
    "🎯 目標：一天一步。每一份努力都很重要。",
    "✓ 您輸入的資訊越多，追蹤就越有價值。",
    "🌿 記得深呼吸：渴望通常會在幾分鐘內減弱。",
    "⚡ 提醒：本應用是自我追蹤工具，而非評判。",
    "★ 您可以啟用／停用分類，而不會遺失資料。",
    "💡 試著加入花費：您將更清楚看到金錢上的影響。",
    "🎯 設定實際可行的習慣：穩定勝過完美。",
    "✓ 您可以隨時匯出您的資料。",
    "🌿 記得補充水分：這常能幫助減輕渴望。",
    "⚡ 小提醒：進展不一定是線性的，這很正常。",
    "★ 查看「設定」分頁以個人化應用程式。",
    "💡 建議：先保持簡單，再逐步調整（習慣、花費、日期）。",
    "🎯 一次勝利＝減少一個單位，這已是進步。",
    "✓ 您的資料保存在裝置中：無需帳號，無伺服器。",
    "🌿 若有反覆，請繼續記錄：持續最重要。",
    "⚡ 提示：記錄您的習慣，將一天與您的「基準」做比較。",
    "★ 使用行事曆來視覺化您的規律性。",
    "💡 在「酒精總量」模式下，子類型（啤酒／利口酒／烈酒）會被停用。",
    "🎯 您也可以選擇相反方式：使用啤酒／利口酒／烈酒，而非酒精總量。",
    "✓ 連續多天的追蹤會更有參考價值。",
    "🌿 稍微活動一下：散步常能幫助降低渴望。",
    "⚡ 提醒：逐步減少本身就是有效策略。",
    "★ 當目標改變時，請更新您的習慣。",
    "💡 您可以稍後重新啟用分類：不會刪除任何內容。",
    "🎯 持續性比完美更有價值。",
    "✓ 「今日重置」只會刪除當天資料。",
    "🌿 「歷史重置」會刪除全部歷史：請謹慎使用。",
    "⚡ 匯入前請確認檔案來自您的 StopAddict 匯出。",
    "★ 出國時別忘了調整貨幣。",
    "💡 如需更改語言，應用程式將重新啟動以套用。",
    "🎯 真實的追蹤比「完美」的追蹤更有幫助。",
    "✓ 比較實際花費與習慣：當數字下降時很有激勵效果。",
    "🌿 喝一杯水，深呼吸，然後再做決定。",
    "⚡ 無需任何設定即可開始：計數器已在運作。",
    "★ 設定可豐富應用，但並非必需。",
    "💡 填寫花費與習慣後，圖表會更清楚。",
    "🎯 每一天的追蹤都是前進的有用資料。",
    "✓ 動力可能起伏：即便如此也請持續。",
    "🌿 請善待自己：目標是減少，而非內疚。",
    "⚡ 今日一小步，明日大成果。",
    "★ 明天再來：規律帶來長久的改變。",
    "💡 如需，可在 設定 > 個人化 中隱藏此訊息。"
)

}
