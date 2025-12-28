package com.stopaddict

object CalendrierLangues {

    private const val TAG = "CalendrierLangues"

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

        // AJOUTS
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
        "titre" to "Calendrier",
        
        "label_cigarettes" to "Cigarettes",
        "label_joints" to "Joints",
        "label_alcool_global" to "Alcool global",
        "label_bieres" to "Bières",
        "label_liqueurs" to "Liqueurs",
        "label_alcool_fort" to "Alcool fort",
        
        "profil_complet" to "Profil: Complet ✓",
        "profil_incomplet" to "Profil: Incomplet",
        "total_jour" to "Total jour",
        
        "btn_mois_precedent" to "◀ Préc",
        "btn_mois_suivant" to "Suiv ▶",
        "btn_aujourdhui" to "Aujourd'hui",
        
        "jour_0" to "L",
        "jour_1" to "M",
        "jour_2" to "M",
        "jour_3" to "J",
        "jour_4" to "V",
        "jour_5" to "S",
        "jour_6" to "D",
        
        "legende_titre" to "Légende:",
        "legende_vert" to "Aucune consommation",
        "legende_bleu" to "Faible consommation (1–5)",
        "legende_orange" to "Consommation modérée (6–15)",
        "legende_rouge" to "Consommation élevée (16+)",
        
        "legende_objectifs" to "Objectifs :",
        "legende_reduction" to "Réduction",
        "legende_arret" to "Arrêt",
        "legende_reussite" to "Objectif atteint",
        
        "legende_conso" to "Consommations :",

        "sauvegarde_ok" to "Enregistré",
        
        "dialog_titre" to "Consommations du",
        "btn_sauvegarder" to "Sauvegarder",
        "btn_annuler" to "Annuler",
        "msg_err_generic" to "Une erreur est survenue",
        "msg_sauvegarde_ok" to "Sauvegardé"
    )

    // ==================== ENGLISH ====================
private val TRADUCTIONS_EN = mapOf(
    "titre" to "Calendar",

    "label_cigarettes" to "Cigarettes",
    "label_joints" to "Joints",
    "label_alcool_global" to "Alcohol (global)",
    "label_bieres" to "Beers",
    "label_liqueurs" to "Liquors",
    "label_alcool_fort" to "Spirits",

    "profil_complet" to "Profile: Complete ✓",
    "profil_incomplet" to "Profile: Incomplete",
    "total_jour" to "Day total",

    "btn_mois_precedent" to "◀ Prev",
    "btn_mois_suivant" to "Next ▶",
    "btn_aujourdhui" to "Today",

    "jour_0" to "M",
    "jour_1" to "T",
    "jour_2" to "W",
    "jour_3" to "T",
    "jour_4" to "F",
    "jour_5" to "S",
    "jour_6" to "S",

    // ----- LÉGENDE GÉNÉRALE -----
    "legende_titre" to "Legend:",
    "legende_vert" to "No consumption",
    "legende_bleu" to "Low consumption (1–5)",
    "legende_orange" to "Moderate consumption (6–15)",
    "legende_rouge" to "High consumption (16+)",

    // ----- LÉGENDE OBJECTIFS -----
    "legende_objectifs" to "Goals:",
    "legende_reduction" to "Reduction",
    "legende_arret" to "Stop",
    "legende_reussite" to "Goal achieved",

    // ----- LÉGENDE CONSOMMATIONS -----
    "legende_conso" to "Consumptions:",

    // ----- DIALOG -----
    "dialog_titre" to "Consumption of",
    "btn_sauvegarder" to "Save",
    "btn_annuler" to "Cancel",
    "msg_sauvegarde_ok" to "Saved",
    "sauvegarde_ok" to "Saved",
    "msg_err_generic" to "An error occurred"
)
    
      // ==================== ESPAÑOL ====================
private val TRADUCTIONS_ES = mapOf(
    "titre" to "Calendario",

    "label_cigarettes" to "Cigarrillos",
    "label_joints" to "Porros",
    "label_alcool_global" to "Alcohol (global)",
    "label_bieres" to "Cervezas",
    "label_liqueurs" to "Licores",
    "label_alcool_fort" to "Licores fuertes",

    "profil_complet" to "Perfil: Completo ✓",
    "profil_incomplet" to "Perfil: Incompleto",
    "total_jour" to "Total del día",

    "btn_mois_precedent" to "◀ Ant",
    "btn_mois_suivant" to "Sig ▶",
    "btn_aujourdhui" to "Hoy",

    "jour_0" to "L",
    "jour_1" to "M",
    "jour_2" to "X",
    "jour_3" to "J",
    "jour_4" to "V",
    "jour_5" to "S",
    "jour_6" to "D",

    "legende_titre" to "Leyenda:",
    "legende_vert" to "Sin consumo",
    "legende_bleu" to "Consumo bajo (1–5)",
    "legende_orange" to "Consumo moderado (6–15)",
    "legende_rouge" to "Consumo alto (16+)",

    "legende_objectifs" to "Objetivos:",
    "legende_reduction" to "Reducción",
    "legende_arret" to "Dejarlo",
    "legende_reussite" to "Objetivo logrado",

    "legende_conso" to "Consumos:",

    "dialog_titre" to "Consumo del",
    "btn_sauvegarder" to "Guardar",
    "btn_annuler" to "Cancelar",

    // Utilisé dans CalendrierFragment (Toast)
    "sauvegarde_ok" to "Guardado",

    // Ancienne clé (si d'autres écrans l'utilisent encore)
    "msg_sauvegarde_ok" to "Guardado",
    "msg_err_generic" to "Se ha producido un error"
)

     // ==================== PORTUGUÊS ====================
private val TRADUCTIONS_PT = mapOf(
    "titre" to "Calendário",

    "label_cigarettes" to "Cigarros",
    "label_joints" to "Baseados",
    "label_alcool_global" to "Álcool (global)",
    "label_bieres" to "Cervejas",
    "label_liqueurs" to "Licores",
    "label_alcool_fort" to "Bebidas fortes",

    "profil_complet" to "Perfil: Completo ✓",
    "profil_incomplet" to "Perfil: Incompleto",
    "total_jour" to "Total do dia",

    "btn_mois_precedent" to "◀ Ant",
    "btn_mois_suivant" to "Próx ▶",
    "btn_aujourdhui" to "Hoje",

    "jour_0" to "S",
    "jour_1" to "T",
    "jour_2" to "Q",
    "jour_3" to "Q",
    "jour_4" to "S",
    "jour_5" to "S",
    "jour_6" to "D",

    "legende_titre" to "Legenda:",
    "legende_vert" to "Sem consumo",
    "legende_bleu" to "Consumo baixo (1–5)",
    "legende_orange" to "Consumo moderado (6–15)",
    "legende_rouge" to "Consumo alto (16+)",

    "legende_objectifs" to "Objetivos:",
    "legende_reduction" to "Redução",
    "legende_arret" to "Parar",
    "legende_reussite" to "Objetivo alcançado",

    "legende_conso" to "Consumos:",

    "dialog_titre" to "Consumo de",
    "btn_sauvegarder" to "Salvar",
    "btn_annuler" to "Cancelar",
    "sauvegarde_ok" to "Salvo",
    "msg_err_generic" to "Ocorreu um erro"
)

// ==================== DEUTSCH ====================
private val TRADUCTIONS_DE = mapOf(
    "titre" to "Kalender",

    "label_cigarettes" to "Zigaretten",
    "label_joints" to "Joints",
    "label_alcool_global" to "Alkohol gesamt",
    "label_bieres" to "Biere",
    "label_liqueurs" to "Liköre",
    "label_alcool_fort" to "Spirituosen",

    "profil_complet" to "Profil: Vollständig ✓",
    "profil_incomplet" to "Profil: Unvollständig",
    "total_jour" to "Tagessumme",

    "btn_mois_precedent" to "◀ Vor",
    "btn_mois_suivant" to "Weiter ▶",
    "btn_aujourdhui" to "Heute",

    "jour_0" to "M",
    "jour_1" to "D",
    "jour_2" to "M",
    "jour_3" to "D",
    "jour_4" to "F",
    "jour_5" to "S",
    "jour_6" to "S",

    // --- LÉGENDE COULEURS ---
    "legende_titre" to "Legende:",
    "legende_vert" to "Kein Konsum",
    "legende_bleu" to "Geringer Konsum (1–5)",
    "legende_orange" to "Mäßiger Konsum (6–15)",
    "legende_rouge" to "Hoher Konsum (16+)",

    // --- LÉGENDE OBJECTIFS ---
    "legende_objectifs" to "Ziele:",
    "legende_reduction" to "Reduktion",
    "legende_arret" to "Stopp",
    "legende_reussite" to "Ziel erreicht",

    // --- LÉGENDE CONSOMMATIONS ---
    "legende_conso" to "Konsum:",

    "dialog_titre" to "Konsum am",
    "btn_sauvegarder" to "Speichern",
    "btn_annuler" to "Abbrechen",
    "sauvegarde_ok" to "Gespeichert",
    "msg_err_generic" to "Ein Fehler ist aufgetreten"
)

    // ==================== ITALIANO ====================
private val TRADUCTIONS_IT = mapOf(
    "titre" to "Calendario",

    "label_cigarettes" to "Sigarette",
    "label_joints" to "Canne",
    "label_alcool_global" to "Alcol globale",
    "label_bieres" to "Birre",
    "label_liqueurs" to "Liquori",
    "label_alcool_fort" to "Superalcolici",

    "profil_complet" to "Profilo: Completo ✓",
    "profil_incomplet" to "Profilo: Incompleto",
    "total_jour" to "Totale giornaliero",

    "btn_mois_precedent" to "◀ Prec",
    "btn_mois_suivant" to "Succ ▶",
    "btn_aujourdhui" to "Oggi",

    "jour_0" to "L",
    "jour_1" to "M",
    "jour_2" to "M",
    "jour_3" to "G",
    "jour_4" to "V",
    "jour_5" to "S",
    "jour_6" to "D",

    // ----- LÉGENDE COULEURS -----
    "legende_titre" to "Legenda:",
    "legende_vert" to "Nessun consumo",
    "legende_bleu" to "Consumo basso (1–5)",
    "legende_orange" to "Consumo moderato (6–15)",
    "legende_rouge" to "Consumo elevato (16+)",

    // ----- LÉGENDE OBJECTIFS -----
    "legende_objectifs" to "Obiettivi:",
    "legende_reduction" to "Riduzione",
    "legende_arret" to "Stop",
    "legende_reussite" to "Obiettivo raggiunto",

    // ----- LÉGENDE CONSOMMATIONS -----
    "legende_conso" to "Consumi:",

    "dialog_titre" to "Consumo del",
    "btn_sauvegarder" to "Salva",
    "btn_annuler" to "Annulla",
    "msg_sauvegarde_ok" to "Salvato",
    "sauvegarde_ok" to "Salvato",
    "msg_err_generic" to "Si è verificato un errore"
)

    // ==================== РУССКИЙ ====================
private val TRADUCTIONS_RU = mapOf(
    "titre" to "Календарь",

    // Libellés consommations
    "label_cigarettes" to "Сигареты",
    "label_joints" to "Косяки",
    "label_alcool_global" to "Алкоголь (общий)",
    "label_bieres" to "Пиво",
    "label_liqueurs" to "Ликёры",
    "label_alcool_fort" to "Крепкий алкоголь",

    // Profil
    "profil_complet" to "Профиль: Полный ✓",
    "profil_incomplet" to "Профиль: Неполный",
    "total_jour" to "Всего за день",

    // Navigation mois
    "btn_mois_precedent" to "◀ Пред",
    "btn_mois_suivant" to "След ▶",
    "btn_aujourdhui" to "Сегодня",

    // Jours
    "jour_0" to "П",
    "jour_1" to "В",
    "jour_2" to "С",
    "jour_3" to "Ч",
    "jour_4" to "П",
    "jour_5" to "С",
    "jour_6" to "В",

    // Légende – niveaux
    "legende_titre" to "Легенда:",
    "legende_vert" to "Нет потребления",
    "legende_bleu" to "Низкое потребление (1–5)",
    "legende_orange" to "Умеренное потребление (6–15)",
    "legende_rouge" to "Высокое потребление (16+)",

    // Légende – objectifs
    "legende_objectifs" to "Цели:",
    "legende_reduction" to "Снижение",
    "legende_arret" to "Остановка",
    "legende_reussite" to "Цель достигнута",

    // Légende – consommations
    "legende_conso" to "Потребление:",

    // Dialog
    "dialog_titre" to "Потребление",
    "btn_sauvegarder" to "Сохранить",
    "btn_annuler" to "Отмена",

    // Messages
    "sauvegarde_ok" to "Сохранено",
    "msg_err_generic" to "Произошла ошибка"
)

     // ==================== العربية ====================
private val TRADUCTIONS_AR = mapOf(
    "titre" to "التقويم",

    "label_cigarettes" to "السجائر",
    "label_joints" to "الحشيش",
    "label_alcool_global" to "الكحول (عام)",
    "label_bieres" to "البيرة",
    "label_liqueurs" to "المشروبات الكحولية",
    "label_alcool_fort" to "الكحول القوي",

    "profil_complet" to "الملف الشخصي: كامل ✓",
    "profil_incomplet" to "الملف الشخصي: غير كامل",
    "total_jour" to "إجمالي اليوم",

    "btn_mois_precedent" to "◀ السابق",
    "btn_mois_suivant" to "التالي ▶",
    "btn_aujourdhui" to "اليوم",

    "jour_0" to "إ",
    "jour_1" to "ث",
    "jour_2" to "أ",
    "jour_3" to "خ",
    "jour_4" to "ج",
    "jour_5" to "س",
    "jour_6" to "ح",

    "legende_titre" to "المفتاح:",
    "legende_vert" to "لا استهلاك",
    "legende_bleu" to "استهلاك منخفض (1–5)",
    "legende_orange" to "استهلاك معتدل (6–15)",
    "legende_rouge" to "استهلاك عالي (16+)",

    "legende_objectifs" to "الأهداف:",
    "legende_reduction" to "تقليل",
    "legende_arret" to "توقف",
    "legende_reussite" to "تم تحقيق الهدف",

    "legende_conso" to "الاستهلاك:",

    "dialog_titre" to "استهلاك يوم",
    "btn_sauvegarder" to "حفظ",
    "btn_annuler" to "إلغاء",
    "msg_sauvegarde_ok" to "تم الحفظ",
    "sauvegarde_ok" to "تم الحفظ",
    "msg_err_generic" to "حدث خطأ"
)

    // ==================== हिन्दी ====================
private val TRADUCTIONS_HI = mapOf(
    "titre" to "कैलेंडर",

    "label_cigarettes" to "सिगरेट",
    "label_joints" to "जॉइंट",
    "label_alcool_global" to "शराब (कुल)",
    "label_bieres" to "बीयर",
    "label_liqueurs" to "लिकर",
    "label_alcool_fort" to "कड़ी शराब",

    "profil_complet" to "प्रोफ़ाइल: पूर्ण ✓",
    "profil_incomplet" to "प्रोफ़ाइल: अधूरा",
    "total_jour" to "दिन का कुल",

    "btn_mois_precedent" to "◀ पिछला",
    "btn_mois_suivant" to "अगला ▶",
    "btn_aujourdhui" to "आज",

    "jour_0" to "सो",
    "jour_1" to "मं",
    "jour_2" to "बु",
    "jour_3" to "गु",
    "jour_4" to "शु",
    "jour_5" to "श",
    "jour_6" to "र",

    "legende_titre" to "लीजेंड:",
    "legende_vert" to "कोई खपत नहीं",
    "legende_bleu" to "कम खपत (1–5)",
    "legende_orange" to "मध्यम खपत (6–15)",
    "legende_rouge" to "अधिक खपत (16+)",

    "legende_objectifs" to "लक्ष्य:",
    "legende_reduction" to "कमी",
    "legende_arret" to "बंद",
    "legende_reussite" to "लक्ष्य पूरा",

    "legende_conso" to "खपत:",
    
    "dialog_titre" to "की खपत",
    "btn_sauvegarder" to "सहेजें",
    "btn_annuler" to "रद्द करें",

    // compat: selon la clé appelée dans le fragment
    "sauvegarde_ok" to "सहेजा गया",
    "msg_sauvegarde_ok" to "सहेजा गया",
    "msg_err_generic" to "एक त्रुटि हुई"
)

    // ==================== 日本語 ====================
private val TRADUCTIONS_JA = mapOf(
    "titre" to "カレンダー",

    "label_cigarettes" to "タバコ",
    "label_joints" to "ジョイント",
    "label_alcool_global" to "アルコール（全体）",
    "label_bieres" to "ビール",
    "label_liqueurs" to "リキュール",
    "label_alcool_fort" to "強いアルコール",

    "profil_complet" to "プロフィール: 完了 ✓",
    "profil_incomplet" to "プロフィール: 不完全",
    "total_jour" to "1日の合計",

    "btn_mois_precedent" to "◀ 前",
    "btn_mois_suivant" to "次 ▶",
    "btn_aujourdhui" to "今日",

    "jour_0" to "月",
    "jour_1" to "火",
    "jour_2" to "水",
    "jour_3" to "木",
    "jour_4" to "金",
    "jour_5" to "土",
    "jour_6" to "日",

    "legende_titre" to "凡例:",
    "legende_vert" to "消費なし",
    "legende_bleu" to "低消費（1–5）",
    "legende_orange" to "中程度の消費（6–15）",
    "legende_rouge" to "高消費（16+）",

    "legende_objectifs" to "目標:",
    "legende_reduction" to "削減",
    "legende_arret" to "停止",
    "legende_reussite" to "達成",

    "legende_conso" to "消費:",

    "dialog_titre" to "の消費",
    "btn_sauvegarder" to "保存",
    "btn_annuler" to "キャンセル",
    "msg_sauvegarde_ok" to "保存されました",
    "sauvegarde_ok" to "保存されました",
    "msg_err_generic" to "エラーが発生しました"
)

    private val TRADUCTIONS_NL = mapOf(
    "titre" to "Kalender",

    "label_cigarettes" to "Sigaretten",
    "label_joints" to "Joints",
    "label_alcool_global" to "Alcohol totaal",
    "label_bieres" to "Bieren",
    "label_liqueurs" to "Likeuren",
    "label_alcool_fort" to "Sterke alcohol",

    "profil_complet" to "Profiel: Volledig ✓",
    "profil_incomplet" to "Profiel: Onvolledig",
    "total_jour" to "Totaal per dag",

    "btn_mois_precedent" to "◀ Vorige",
    "btn_mois_suivant" to "Volgende ▶",
    "btn_aujourdhui" to "Vandaag",

    "jour_0" to "Ma",
    "jour_1" to "Di",
    "jour_2" to "Wo",
    "jour_3" to "Do",
    "jour_4" to "Vr",
    "jour_5" to "Za",
    "jour_6" to "Zo",

    "legende_titre" to "Legenda:",
    "legende_vert" to "Geen consumptie",
    "legende_bleu" to "Lage consumptie (1–5)",
    "legende_orange" to "Matige consumptie (6–15)",
    "legende_rouge" to "Hoge consumptie (16+)",

    "legende_objectifs" to "Doelen:",
    "legende_reduction" to "Vermindering",
    "legende_arret" to "Stoppen",
    "legende_reussite" to "Doel behaald",

    "legende_conso" to "Consumpties:",

    "sauvegarde_ok" to "Opgeslagen",

    "dialog_titre" to "Consumpties van",
    "btn_sauvegarder" to "Opslaan",
    "btn_annuler" to "Annuleren",
    "msg_sauvegarde_ok" to "Opgeslagen",
    "msg_err_generic" to "Er is een fout opgetreden"
)

      private val TRADUCTIONS_ZHS = mapOf(
    "titre" to "日历",

    "label_cigarettes" to "香烟",
    "label_joints" to "大麻卷烟",
    "label_alcool_global" to "酒精总量",
    "label_bieres" to "啤酒",
    "label_liqueurs" to "利口酒",
    "label_alcool_fort" to "烈性酒",

    "profil_complet" to "档案：完整 ✓",
    "profil_incomplet" to "档案：未完整",
    "total_jour" to "当日总计",

    "btn_mois_precedent" to "◀ 上月",
    "btn_mois_suivant" to "下月 ▶",
    "btn_aujourdhui" to "今天",

    "jour_0" to "一",
    "jour_1" to "二",
    "jour_2" to "三",
    "jour_3" to "四",
    "jour_4" to "五",
    "jour_5" to "六",
    "jour_6" to "日",

    "legende_titre" to "图例：",
    "legende_vert" to "无消费",
    "legende_bleu" to "低消费（1–5）",
    "legende_orange" to "中等消费（6–15）",
    "legende_rouge" to "高消费（16+）",

    "legende_objectifs" to "目标：",
    "legende_reduction" to "减少",
    "legende_arret" to "停止",
    "legende_reussite" to "目标达成",

    "legende_conso" to "消费：",

    "sauvegarde_ok" to "已保存",

    "dialog_titre" to "当日消费",
    "btn_sauvegarder" to "保存",
    "btn_annuler" to "取消",
    "msg_sauvegarde_ok" to "已保存",
    "msg_err_generic" to "发生错误"
)

      private val TRADUCTIONS_ZHT = mapOf(
    "titre" to "日曆",

    "label_cigarettes" to "香菸",
    "label_joints" to "大麻捲菸",
    "label_alcool_global" to "酒精總量",
    "label_bieres" to "啤酒",
    "label_liqueurs" to "利口酒",
    "label_alcool_fort" to "烈酒",

    "profil_complet" to "檔案：完整 ✓",
    "profil_incomplet" to "檔案：未完整",
    "total_jour" to "每日總計",

    "btn_mois_precedent" to "◀ 上月",
    "btn_mois_suivant" to "下月 ▶",
    "btn_aujourdhui" to "今天",

    "jour_0" to "一",
    "jour_1" to "二",
    "jour_2" to "三",
    "jour_3" to "四",
    "jour_4" to "五",
    "jour_5" to "六",
    "jour_6" to "日",

    "legende_titre" to "圖例：",
    "legende_vert" to "無消費",
    "legende_bleu" to "低消費（1–5）",
    "legende_orange" to "中等消費（6–15）",
    "legende_rouge" to "高消費（16+）",

    "legende_objectifs" to "目標：",
    "legende_reduction" to "減少",
    "legende_arret" to "停止",
    "legende_reussite" to "目標達成",

    "legende_conso" to "消費：",

    "sauvegarde_ok" to "已儲存",

    "dialog_titre" to "當日消費",
    "btn_sauvegarder" to "儲存",
    "btn_annuler" to "取消",
    "msg_sauvegarde_ok" to "已儲存",
    "msg_err_generic" to "發生錯誤"
)
}
