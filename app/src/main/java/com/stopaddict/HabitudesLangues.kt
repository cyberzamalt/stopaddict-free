package com.stopaddict

object HabitudesLangues {

    private const val TAG = "HabitudesLangues"

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
                StopAddictLogger.w(TAG, "Langue inconnue: $codeLangue, fallback FR")
                TRADUCTIONS_FR
            }
        }
    }

    // ==================== FRANÇAIS ====================
    private val TRADUCTIONS_FR = mapOf(
        "titre" to "Habitudes & Volonté",
        "titre_habitudes" to "Habitudes",
        "titre_volonte" to "Volonté",
        
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
        "total_aujourdhui" to "Total aujourd'hui",
        
        // Hints section Habitudes
        "hint_max_cigarettes" to "Max cigarettes par jour",
        "hint_max_joints" to "Max joints par jour",
        "hint_max_alcool_global" to "Max alcool global par jour",
        "hint_max_bieres" to "Max bières par jour",
        "hint_max_liqueurs" to "Max liqueurs par jour",
        "hint_max_alcool_fort" to "Max alcool fort par jour",
        
        // Section Volonté
        "desc_volonte" to "Définissez 3 dates par catégorie : Réduction, Arrêt, Réussite",
        
        // Labels dates
        "label_date_reduction" to "Date de réduction",
        "label_date_arret" to "Date d'arrêt",
        "label_date_reussite" to "Date de réussite",
        
        // Boutons
        "btn_sauvegarder" to "Sauvegarder",
        "btn_selectionner_date" to "Sélectionner une date",
        "btn_effacer" to "Effacer",

        // Messages
        "msg_err_chargement_habitudes" to "Erreur chargement Habitudes",
        "msg_reset_habitudes" to "Habitudes réinitialisées — Cliquez sur Sauvegarder pour appliquer",
        "msg_reset_dates" to "Dates réinitialisées — Cliquez sur Sauvegarder pour appliquer",
        "msg_err_reset" to "Erreur lors de la réinitialisation",
        "msg_sauvegarde_habitudes_ok" to "Habitudes sauvegardées",
        "msg_sauvegarde_dates_ok" to "Dates sauvegardées",

        "msg_err_generic" to "Une erreur est survenue"
    )

    // ==================== ENGLISH ====================
    private val TRADUCTIONS_EN = mapOf(
        "titre" to "Habits & Willpower",
        "titre_habitudes" to "Habits",
        "titre_volonte" to "Willpower",
        
        "label_cigarettes" to "Cigarettes",
        "label_joints" to "Joints",
        "label_alcool_global" to "Alcohol (global)",
        "label_bieres" to "Beers",
        "label_liqueurs" to "Liquors",
        "label_alcool_fort" to "Spirits",
        
        "profil_complet" to "Profile: Complete ✓",
        "profil_incomplet" to "Profile: Incomplete",
        "total_aujourdhui" to "Total today",
        
        "hint_max_cigarettes" to "Max cigarettes per day",
        "hint_max_joints" to "Max joints per day",
        "hint_max_alcool_global" to "Max alcohol per day",
        "hint_max_bieres" to "Max beers per day",
        "hint_max_liqueurs" to "Max liquors per day",
        "hint_max_alcool_fort" to "Max spirits per day",
        
        "desc_volonte" to "Set 3 dates per category: Reduction, Quit, Success",
        
        "label_date_reduction" to "Reduction date",
        "label_date_arret" to "Quit date",
        "label_date_reussite" to "Success date",
        
        "btn_sauvegarder" to "Save",
        "btn_selectionner_date" to "Select a date",
        "btn_effacer" to "Clear",
                
                // Messages
        "msg_err_chargement_habitudes" to "Error loading Habits",
        "msg_reset_habitudes" to "Habits reset — Tap Save to apply",
        "msg_reset_dates" to "Dates reset — Tap Save to apply",
        "msg_err_reset" to "Error during reset",
        "msg_sauvegarde_habitudes_ok" to "Habits saved",
        "msg_sauvegarde_dates_ok" to "Dates saved",

        "msg_err_generic" to "An error occurred"
    )

    // ==================== ESPAÑOL ====================
    private val TRADUCTIONS_ES = mapOf(
        "titre" to "Hábitos y Voluntad",
        "titre_habitudes" to "Hábitos",
        "titre_volonte" to "Voluntad",
        
        "label_cigarettes" to "Cigarrillos",
        "label_joints" to "Porros",
        "label_alcool_global" to "Alcohol (global)",
        "label_bieres" to "Cervezas",
        "label_liqueurs" to "Licores",
        "label_alcool_fort" to "Licores fuertes",
        
        "profil_complet" to "Perfil: Completo ✓",
        "profil_incomplet" to "Perfil: Incompleto",
        "total_aujourdhui" to "Total hoy",
        
        "hint_max_cigarettes" to "Máx cigarrillos por día",
        "hint_max_joints" to "Máx porros por día",
        "hint_max_alcool_global" to "Máx alcohol por día",
        "hint_max_bieres" to "Máx cervezas por día",
        "hint_max_liqueurs" to "Máx licores por día",
        "hint_max_alcool_fort" to "Máx licores fuertes por día",
        
        "desc_volonte" to "Define 3 fechas por categoría: Reducción, Parada, Éxito",
        
        "label_date_reduction" to "Fecha de reducción",
        "label_date_arret" to "Fecha de parada",
        "label_date_reussite" to "Fecha de éxito",
        
        "btn_sauvegarder" to "Guardar",
        "btn_selectionner_date" to "Seleccionar una fecha",
        "btn_effacer" to "Borrar",
                
        "msg_err_chargement_habitudes" to "Error al cargar Hábitos",
        "msg_reset_habitudes" to "Hábitos reiniciados — Pulsa Guardar para aplicar",
        "msg_reset_dates" to "Fechas reiniciadas — Pulsa Guardar para aplicar",
        "msg_err_reset" to "Error durante el reinicio",
        "msg_sauvegarde_habitudes_ok" to "Hábitos guardados",
        "msg_sauvegarde_dates_ok" to "Fechas guardadas",

        "msg_err_generic" to "Se produjo un error"
    )

    // ==================== PORTUGUÊS ====================
    private val TRADUCTIONS_PT = mapOf(
        "titre" to "Hábitos e Vontade",
        "titre_habitudes" to "Hábitos",
        "titre_volonte" to "Vontade",

        
        "label_cigarettes" to "Cigarros",
        "label_joints" to "Baseados",
        "label_alcool_global" to "Álcool (global)",
        "label_bieres" to "Cervejas",
        "label_liqueurs" to "Licores",
        "label_alcool_fort" to "Bebidas fortes",
        
        "profil_complet" to "Perfil: Completo ✓",
        "profil_incomplet" to "Perfil: Incompleto",
        "total_aujourdhui" to "Total hoje",
        
        "hint_max_cigarettes" to "Máx cigarros por dia",
        "hint_max_joints" to "Máx baseados por dia",
        "hint_max_alcool_global" to "Máx álcool por dia",
        "hint_max_bieres" to "Máx cervejas por dia",
        "hint_max_liqueurs" to "Máx licores por dia",
        "hint_max_alcool_fort" to "Máx bebidas fortes por dia",
        
        "desc_volonte" to "Defina 3 datas por categoria: Redução, Parada, Sucesso",
        
        "label_date_reduction" to "Data de redução",
        "label_date_arret" to "Data de parada",
        "label_date_reussite" to "Data de sucesso",
        
        "btn_sauvegarder" to "Salvar",
        "btn_selectionner_date" to "Selecionar uma data",
        "btn_effacer" to "Apagar",
                
        "msg_err_chargement_habitudes" to "Erro ao carregar Hábitos",
        "msg_reset_habitudes" to "Hábitos redefinidos — Toque em Salvar para aplicar",
        "msg_reset_dates" to "Datas redefinidas — Toque em Salvar para aplicar",
        "msg_err_reset" to "Erro durante a redefinição",
        "msg_sauvegarde_habitudes_ok" to "Hábitos salvos",
        "msg_sauvegarde_dates_ok" to "Datas salvas",

        "msg_err_generic" to "Ocorreu um erro"
    )

    // ==================== DEUTSCH ====================
    private val TRADUCTIONS_DE = mapOf(
        "titre" to "Gewohnheiten & Willenskraft",
        "titre_habitudes" to "Gewohnheiten",
        "titre_volonte" to "Willenskraft",
        
        "label_cigarettes" to "Zigaretten",
        "label_joints" to "Joints",
        "label_alcool_global" to "Alkohol (global)",
        "label_bieres" to "Biere",
        "label_liqueurs" to "Liköre",
        "label_alcool_fort" to "Spirituosen",
        
        "profil_complet" to "Profil: Vollständig ✓",
        "profil_incomplet" to "Profil: Unvollständig",
        "total_aujourdhui" to "Total heute",
        
        "hint_max_cigarettes" to "Max Zigaretten pro Tag",
        "hint_max_joints" to "Max Joints pro Tag",
        "hint_max_alcool_global" to "Max Alkohol pro Tag",
        "hint_max_bieres" to "Max Biere pro Tag",
        "hint_max_liqueurs" to "Max Liköre pro Tag",
        "hint_max_alcool_fort" to "Max Spirituosen pro Tag",
        
        "desc_volonte" to "Legen Sie 3 Daten pro Kategorie fest: Reduktion, Stopp, Erfolg",
        
        "label_date_reduction" to "Reduktionsdatum",
        "label_date_arret" to "Stoppdatum",
        "label_date_reussite" to "Erfolgsdatum",
        
        "btn_sauvegarder" to "Speichern",
        "btn_selectionner_date" to "Ein Datum auswählen",
        "btn_effacer" to "Löschen",
        
        "msg_err_chargement_habitudes" to "Fehler beim Laden der Gewohnheiten",
        "msg_reset_habitudes" to "Gewohnheiten zurückgesetzt — Zum Anwenden Speichern tippen",
        "msg_reset_dates" to "Daten zurückgesetzt — Zum Anwenden Speichern tippen",
        "msg_err_reset" to "Fehler beim Zurücksetzen",
        "msg_sauvegarde_habitudes_ok" to "Gewohnheiten gespeichert",
        "msg_sauvegarde_dates_ok" to "Daten gespeichert",

        "msg_err_generic" to "Ein Fehler ist aufgetreten"
    )

    // ==================== ITALIANO ====================
    private val TRADUCTIONS_IT = mapOf(
        "titre" to "Abitudini e Volontà",
        "titre_habitudes" to "Abitudini",
        "titre_volonte" to "Volontà",
        
        "label_cigarettes" to "Sigarette",
        "label_joints" to "Canne",
        "label_alcool_global" to "Alcol (globale)",
        "label_bieres" to "Birre",
        "label_liqueurs" to "Liquori",
        "label_alcool_fort" to "Superalcolici",
        
        "profil_complet" to "Profilo: Completo ✓",
        "profil_incomplet" to "Profilo: Incompleto",
        "total_aujourdhui" to "Totale oggi",
        
        "hint_max_cigarettes" to "Max sigarette al giorno",
        "hint_max_joints" to "Max canne al giorno",
        "hint_max_alcool_global" to "Max alcol al giorno",
        "hint_max_bieres" to "Max birre al giorno",
        "hint_max_liqueurs" to "Max liquori al giorno",
        "hint_max_alcool_fort" to "Max superalcolici al giorno",
        
        "desc_volonte" to "Imposta 3 date per categoria: Riduzione, Stop, Successo",
        
        "label_date_reduction" to "Data di riduzione",
        "label_date_arret" to "Data di stop",
        "label_date_reussite" to "Data di successo",
        
        "btn_sauvegarder" to "Salva",
        "btn_selectionner_date" to "Seleziona una data",
        "btn_effacer" to "Cancella",
        
        "msg_err_chargement_habitudes" to "Errore nel caricamento delle Abitudini",
        "msg_reset_habitudes" to "Abitudini reimpostate — Tocca Salva per applicare",
        "msg_reset_dates" to "Date reimpostate — Tocca Salva per applicare",
        "msg_err_reset" to "Errore durante il ripristino",
        "msg_sauvegarde_habitudes_ok" to "Abitudini salvate",
        "msg_sauvegarde_dates_ok" to "Date salvate",

        "msg_err_generic" to "Si è verificato un errore"
    )

    // ==================== РУССКИЙ ====================
    private val TRADUCTIONS_RU = mapOf(
        "titre" to "Привычки и Воля",
        "titre_habitudes" to "Привычки",
        "titre_volonte" to "Воля",
        
        "label_cigarettes" to "Сигареты",
        "label_joints" to "Косяки",
        "label_alcool_global" to "Алкоголь (общий)",
        "label_bieres" to "Пиво",
        "label_liqueurs" to "Ликёры",
        "label_alcool_fort" to "Крепкий алкоголь",
        
        "profil_complet" to "Профиль: Полный ✓",
        "profil_incomplet" to "Профиль: Неполный",
        "total_aujourdhui" to "Всего сегодня",
        
        "hint_max_cigarettes" to "Макс сигарет в день",
        "hint_max_joints" to "Макс косяков в день",
        "hint_max_alcool_global" to "Макс алкоголя в день",
        "hint_max_bieres" to "Макс пива в день",
        "hint_max_liqueurs" to "Макс ликёров в день",
        "hint_max_alcool_fort" to "Макс крепкого алкоголя в день",
        
        "desc_volonte" to "Установите 3 даты на категорию: Сокращение, Прекращение, Успех",
        
        "label_date_reduction" to "Дата сокращения",
        "label_date_arret" to "Дата прекращения",
        "label_date_reussite" to "Дата успеха",
        
        "btn_sauvegarder" to "Сохранить",
        "btn_selectionner_date" to "Выбрать дату",
        "btn_effacer" to "Очистить",
        
        "msg_err_chargement_habitudes" to "Ошибка загрузки привычек",
        "msg_reset_habitudes" to "Привычки сброшены — Нажмите «Сохранить», чтобы применить",
        "msg_reset_dates" to "Даты сброшены — Нажмите «Сохранить», чтобы применить",
        "msg_err_reset" to "Ошибка при сбросе",
        "msg_sauvegarde_habitudes_ok" to "Привычки сохранены",
        "msg_sauvegarde_dates_ok" to "Даты сохранены",

        "msg_err_generic" to "Произошла ошибка"
    )

    // ==================== العربية ====================
    private val TRADUCTIONS_AR = mapOf(
        "titre" to "العادات والإرادة",
        "titre_habitudes" to "العادات",
        "titre_volonte" to "الإرادة",
        
        "label_cigarettes" to "السجائر",
        "label_joints" to "الحشيش",
        "label_alcool_global" to "الكحول (عام)",
        "label_bieres" to "البيرة",
        "label_liqueurs" to "المشروبات الكحولية",
        "label_alcool_fort" to "الكحول القوي",
        
        "profil_complet" to "الملف الشخصي: كامل ✓",
        "profil_incomplet" to "الملف الشخصي: غير كامل",
        "total_aujourdhui" to "الإجمالي اليوم",
        
        "hint_max_cigarettes" to "الحد الأقصى للسجائر في اليوم",
        "hint_max_joints" to "الحد الأقصى للحشيش في اليوم",
        "hint_max_alcool_global" to "الحد الأقصى للكحول في اليوم",
        "hint_max_bieres" to "الحد الأقصى للبيرة في اليوم",
        "hint_max_liqueurs" to "الحد الأقصى للمشروبات في اليوم",
        "hint_max_alcool_fort" to "الحد الأقصى للكحول القوي في اليوم",
        
        "desc_volonte" to "حدد 3 تواريخ لكل فئة: التخفيض، التوقف، النجاح",
        
        "label_date_reduction" to "تاريخ التخفيض",
        "label_date_arret" to "تاريخ التوقف",
        "label_date_reussite" to "تاريخ النجاح",
        
        "btn_sauvegarder" to "حفظ",
        "btn_selectionner_date" to "اختر تاريخًا",
        "btn_effacer" to "حذف",
        
        "msg_err_chargement_habitudes" to "خطأ في تحميل العادات",
        "msg_reset_habitudes" to "تمت إعادة ضبط العادات — اضغط حفظ للتطبيق",
        "msg_reset_dates" to "تمت إعادة ضبط التواريخ — اضغط حفظ للتطبيق",
        "msg_err_reset" to "خطأ أثناء إعادة الضبط",
        "msg_sauvegarde_habitudes_ok" to "تم حفظ العادات",
        "msg_sauvegarde_dates_ok" to "تم حفظ التواريخ",

        "msg_err_generic" to "حدث خطأ"
    )

    // ==================== हिन्दी ====================
    private val TRADUCTIONS_HI = mapOf(
        "titre" to "आदतें और इच्छाशक्ति",
        "titre_habitudes" to "आदतें",
        "titre_volonte" to "इच्छाशक्ति",
        
        "label_cigarettes" to "सिगरेट",
        "label_joints" to "जॉइंट",
        "label_alcool_global" to "शराब (सामान्य)",
        "label_bieres" to "बीयर",
        "label_liqueurs" to "शराब",
        "label_alcool_fort" to "मजबूत शराब",
        
        "profil_complet" to "प्रोफ़ाइल: पूर्ण ✓",
        "profil_incomplet" to "प्रोफ़ाइल: अधूरा",
        "total_aujourdhui" to "आज कुल",
        
        "hint_max_cigarettes" to "प्रति दिन अधिकतम सिगरेट",
        "hint_max_joints" to "प्रति दिन अधिकतम जॉइंट",
        "hint_max_alcool_global" to "प्रति दिन अधिकतम शराब",
        "hint_max_bieres" to "प्रति दिन अधिकतम बीयर",
        "hint_max_liqueurs" to "प्रति दिन अधिकतम शराब",
        "hint_max_alcool_fort" to "प्रति दिन अधिकतम मजबूत शराब",
        
        "desc_volonte" to "प्रति श्रेणी 3 तिथियां निर्धारित करें: कमी, रुकावट, सफलता",
        
        "label_date_reduction" to "कमी की तिथि",
        "label_date_arret" to "रुकावट की तिथि",
        "label_date_reussite" to "सफलता की तिथि",
        
        "btn_sauvegarder" to "सहेजें",
        "btn_selectionner_date" to "एक तिथि चुनें",
        "btn_effacer" to "हटाएँ",
        
        "msg_err_chargement_habitudes" to "आदतें लोड करने में त्रुटि",
        "msg_reset_habitudes" to "आदतें रीसेट — लागू करने के लिए सहेजें दबाएँ",
        "msg_reset_dates" to "तिथियाँ रीसेट — लागू करने के लिए सहेजें दबाएँ",
        "msg_err_reset" to "रीसेट के दौरान त्रुटि",
        "msg_sauvegarde_habitudes_ok" to "आदतें सहेजी गईं",
        "msg_sauvegarde_dates_ok" to "तिथियाँ सहेजी गईं",

        "msg_err_generic" to "एक त्रुटि हुई"
        )

    // ==================== 日本語 ====================
    private val TRADUCTIONS_JA = mapOf(
        "titre" to "習慣と意志力",
        "titre_habitudes" to "習慣",
        "titre_volonte" to "意志",

        "label_cigarettes" to "タバコ",
        "label_joints" to "ジョイント",
        "label_alcool_global" to "アルコール（全体）",
        "label_bieres" to "ビール",
        "label_liqueurs" to "リキュール",
        "label_alcool_fort" to "強いアルコール",
        
        "profil_complet" to "プロフィール: 完了 ✓",
        "profil_incomplet" to "プロフィール: 不完全",
        "total_aujourdhui" to "今日の合計",
        
        "hint_max_cigarettes" to "1日の最大タバコ数",
        "hint_max_joints" to "1日の最大ジョイント数",
        "hint_max_alcool_global" to "1日の最大アルコール量",
        "hint_max_bieres" to "1日の最大ビール数",
        "hint_max_liqueurs" to "1日の最大リキュール数",
        "hint_max_alcool_fort" to "1日の最大強いアルコール数",
        
        "desc_volonte" to "カテゴリごとに3つの日付を設定：削減、停止、成功",
        
        "label_date_reduction" to "削減日",
        "label_date_arret" to "停止日",
        "label_date_reussite" to "成功日",
        
        "btn_sauvegarder" to "保存",
        "btn_selectionner_date" to "日付を選択",
        "btn_effacer" to "消去",
        
        "msg_err_chargement_habitudes" to "習慣の読み込みエラー",
        
        "msg_reset_habitudes" to "習慣をリセット — 適用するには保存をタップ",
        "msg_reset_dates" to "日付をリセット — 適用するには保存をタップ",
        "msg_err_reset" to "リセット中にエラー",
        "msg_sauvegarde_habitudes_ok" to "習慣を保存しました",
        "msg_sauvegarde_dates_ok" to "日付を保存しました",

        "msg_err_generic" to "エラーが発生しました"
    )
    // ==================== NEDERLANDS ====================
private val TRADUCTIONS_NL = mapOf(
    "titre" to "Gewoonten & Wilskracht",
    "titre_habitudes" to "Gewoonten",
    "titre_volonte" to "Wilskracht",

    // Labels catégories
    "label_cigarettes" to "Sigaretten",
    "label_joints" to "Joints",
    "label_alcool_global" to "Alcohol (totaal)",
    "label_bieres" to "Bieren",
    "label_liqueurs" to "Likeuren",
    "label_alcool_fort" to "Sterke drank",

    // Bandeau profil
    "profil_complet" to "Profiel: Volledig ✓",
    "profil_incomplet" to "Profiel: Onvolledig",
    "total_aujourdhui" to "Totaal vandaag",

    // Hints section Habitudes
    "hint_max_cigarettes" to "Max sigaretten per dag",
    "hint_max_joints" to "Max joints per dag",
    "hint_max_alcool_global" to "Max alcohol per dag",
    "hint_max_bieres" to "Max bieren per dag",
    "hint_max_liqueurs" to "Max likeuren per dag",
    "hint_max_alcool_fort" to "Max sterke drank per dag",

    // Section Volonté
    "desc_volonte" to "Stel 3 datums per categorie in: Vermindering, Stop, Succes",

    // Labels dates
    "label_date_reduction" to "Datum van vermindering",
    "label_date_arret" to "Stopdatum",
    "label_date_reussite" to "Succesdatum",

    // Boutons
    "btn_sauvegarder" to "Opslaan",
    "btn_selectionner_date" to "Selecteer een datum",
    "btn_effacer" to "Wissen",

    // Messages
    "msg_err_chargement_habitudes" to "Fout bij het laden van Gewoonten",
    "msg_reset_habitudes" to "Gewoonten gereset — Tik op Opslaan om toe te passen",
    "msg_reset_dates" to "Datums gereset — Tik op Opslaan om toe te passen",
    "msg_err_reset" to "Fout tijdens het resetten",
    "msg_sauvegarde_habitudes_ok" to "Gewoonten opgeslagen",
    "msg_sauvegarde_dates_ok" to "Datums opgeslagen",

    "msg_err_generic" to "Er is een fout opgetreden"
)

// ==================== 中文（简体）====================
private val TRADUCTIONS_ZHS = mapOf(
    "titre" to "习惯与意志力",
    "titre_habitudes" to "习惯",
    "titre_volonte" to "意志力",

    // Labels catégories
    "label_cigarettes" to "香烟",
    "label_joints" to "大麻烟卷",
    "label_alcool_global" to "酒精（总计）",
    "label_bieres" to "啤酒",
    "label_liqueurs" to "利口酒",
    "label_alcool_fort" to "烈酒",

    // Bandeau profil
    "profil_complet" to "档案：完整 ✓",
    "profil_incomplet" to "档案：不完整",
    "total_aujourdhui" to "今日总计",

    // Hints section Habitudes
    "hint_max_cigarettes" to "每日最多香烟数",
    "hint_max_joints" to "每日最多大麻烟卷数",
    "hint_max_alcool_global" to "每日最多酒精量",
    "hint_max_bieres" to "每日最多啤酒数",
    "hint_max_liqueurs" to "每日最多利口酒数",
    "hint_max_alcool_fort" to "每日最多烈酒数",

    // Section Volonté
    "desc_volonte" to "每个类别设置3个日期：减少、停止、成功",

    // Labels dates
    "label_date_reduction" to "减少日期",
    "label_date_arret" to "停止日期",
    "label_date_reussite" to "成功日期",

    // Boutons
    "btn_sauvegarder" to "保存",
    "btn_selectionner_date" to "选择日期",
    "btn_effacer" to "清除",

    // Messages
    "msg_err_chargement_habitudes" to "加载习惯时出错",
    "msg_reset_habitudes" to "习惯已重置 — 点击保存以应用",
    "msg_reset_dates" to "日期已重置 — 点击保存以应用",
    "msg_err_reset" to "重置时出错",
    "msg_sauvegarde_habitudes_ok" to "习惯已保存",
    "msg_sauvegarde_dates_ok" to "日期已保存",

    "msg_err_generic" to "发生错误"
)

// ==================== 中文（繁體）====================
private val TRADUCTIONS_ZHT = mapOf(
    "titre" to "習慣與意志力",
    "titre_habitudes" to "習慣",
    "titre_volonte" to "意志力",

    // Labels catégories
    "label_cigarettes" to "香菸",
    "label_joints" to "大麻菸捲",
    "label_alcool_global" to "酒精（總計）",
    "label_bieres" to "啤酒",
    "label_liqueurs" to "利口酒",
    "label_alcool_fort" to "烈酒",

    // Bandeau profil
    "profil_complet" to "檔案：完整 ✓",
    "profil_incomplet" to "檔案：不完整",
    "total_aujourdhui" to "今日總計",

    // Hints section Habitudes
    "hint_max_cigarettes" to "每日最多香菸數",
    "hint_max_joints" to "每日最多大麻菸捲數",
    "hint_max_alcool_global" to "每日最多酒精量",
    "hint_max_bieres" to "每日最多啤酒數",
    "hint_max_liqueurs" to "每日最多利口酒數",
    "hint_max_alcool_fort" to "每日最多烈酒數",

    // Section Volonté
    "desc_volonte" to "每個類別設定3個日期：減少、停止、成功",

    // Labels dates
    "label_date_reduction" to "減少日期",
    "label_date_arret" to "停止日期",
    "label_date_reussite" to "成功日期",

    // Boutons
    "btn_sauvegarder" to "儲存",
    "btn_selectionner_date" to "選擇日期",
    "btn_effacer" to "清除",

    // Messages
    "msg_err_chargement_habitudes" to "載入習慣時發生錯誤",
    "msg_reset_habitudes" to "習慣已重置 — 點擊儲存以套用",
    "msg_reset_dates" to "日期已重置 — 點擊儲存以套用",
    "msg_err_reset" to "重置時發生錯誤",
    "msg_sauvegarde_habitudes_ok" to "習慣已儲存",
    "msg_sauvegarde_dates_ok" to "日期已儲存",

    "msg_err_generic" to "發生錯誤"
)
}




