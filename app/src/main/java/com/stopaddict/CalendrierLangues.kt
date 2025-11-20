package com.stopaddict

import android.util.Log

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
            else -> {
                Log.w(TAG, "Langue inconnue: $codeLangue, fallback FR")
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
        "legende_orange" to "Consommation modérée (1-5)",
        "legende_rouge" to "Consommation élevée (6+)",
        
        "dialog_titre" to "Consommations du",
        "btn_sauvegarder" to "Sauvegarder",
        "btn_annuler" to "Annuler",
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
        
        "legende_titre" to "Legend:",
        "legende_vert" to "No consumption",
        "legende_orange" to "Moderate consumption (1-5)",
        "legende_rouge" to "High consumption (6+)",
        
        "dialog_titre" to "Consumption of",
        "btn_sauvegarder" to "Save",
        "btn_annuler" to "Cancel",
        "msg_sauvegarde_ok" to "Saved"
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
        "legende_orange" to "Consumo moderado (1-5)",
        "legende_rouge" to "Consumo alto (6+)",
        
        "dialog_titre" to "Consumo del",
        "btn_sauvegarder" to "Guardar",
        "btn_annuler" to "Cancelar",
        "msg_sauvegarde_ok" to "Guardado"
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
        "legende_orange" to "Consumo moderado (1-5)",
        "legende_rouge" to "Consumo alto (6+)",
        
        "dialog_titre" to "Consumo de",
        "btn_sauvegarder" to "Salvar",
        "btn_annuler" to "Cancelar",
        "msg_sauvegarde_ok" to "Salvo"
    )

    // ==================== DEUTSCH ====================
    private val TRADUCTIONS_DE = mapOf(
        "titre" to "Kalender",
        
        "label_cigarettes" to "Zigaretten",
        "label_joints" to "Joints",
        "label_alcool_global" to "Alkohol (global)",
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
        
        "legende_titre" to "Legende:",
        "legende_vert" to "Kein Konsum",
        "legende_orange" to "Mäßiger Konsum (1-5)",
        "legende_rouge" to "Hoher Konsum (6+)",
        
        "dialog_titre" to "Konsum vom",
        "btn_sauvegarder" to "Speichern",
        "btn_annuler" to "Abbrechen",
        "msg_sauvegarde_ok" to "Gespeichert"
    )

    // ==================== ITALIANO ====================
    private val TRADUCTIONS_IT = mapOf(
        "titre" to "Calendario",
        
        "label_cigarettes" to "Sigarette",
        "label_joints" to "Canne",
        "label_alcool_global" to "Alcol (globale)",
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
        
        "legende_titre" to "Legenda:",
        "legende_vert" to "Nessun consumo",
        "legende_orange" to "Consumo moderato (1-5)",
        "legende_rouge" to "Consumo elevato (6+)",
        
        "dialog_titre" to "Consumo del",
        "btn_sauvegarder" to "Salva",
        "btn_annuler" to "Annulla",
        "msg_sauvegarde_ok" to "Salvato"
    )

    // ==================== РУССКИЙ ====================
    private val TRADUCTIONS_RU = mapOf(
        "titre" to "Календарь",
        
        "label_cigarettes" to "Сигареты",
        "label_joints" to "Косяки",
        "label_alcool_global" to "Алкоголь (общий)",
        "label_bieres" to "Пиво",
        "label_liqueurs" to "Ликёры",
        "label_alcool_fort" to "Крепкий алкоголь",
        
        "profil_complet" to "Профиль: Полный ✓",
        "profil_incomplet" to "Профиль: Неполный",
        "total_jour" to "Всего за день",
        
        "btn_mois_precedent" to "◀ Пред",
        "btn_mois_suivant" to "След ▶",
        "btn_aujourdhui" to "Сегодня",
        
        "jour_0" to "П",
        "jour_1" to "В",
        "jour_2" to "С",
        "jour_3" to "Ч",
        "jour_4" to "П",
        "jour_5" to "С",
        "jour_6" to "В",
        
        "legende_titre" to "Легенда:",
        "legende_vert" to "Нет потребления",
        "legende_orange" to "Умеренное потребление (1-5)",
        "legende_rouge" to "Высокое потребление (6+)",
        
        "dialog_titre" to "Потребление",
        "btn_sauvegarder" to "Сохранить",
        "btn_annuler" to "Отмена",
        "msg_sauvegarde_ok" to "Сохранено"
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
        "legende_orange" to "استهلاك معتدل (1-5)",
        "legende_rouge" to "استهلاك عالي (6+)",
        
        "dialog_titre" to "استهلاك يوم",
        "btn_sauvegarder" to "حفظ",
        "btn_annuler" to "إلغاء",
        "msg_sauvegarde_ok" to "تم الحفظ"
    )

    // ==================== हिन्दी ====================
    private val TRADUCTIONS_HI = mapOf(
        "titre" to "कैलेंडर",
        
        "label_cigarettes" to "सिगरेट",
        "label_joints" to "जॉइंट",
        "label_alcool_global" to "शराब (सामान्य)",
        "label_bieres" to "बीयर",
        "label_liqueurs" to "शराब",
        "label_alcool_fort" to "मजबूत शराब",
        
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
        
        "legende_titre" to "किंवदंती:",
        "legende_vert" to "कोई खपत नहीं",
        "legende_orange" to "मध्यम खपत (1-5)",
        "legende_rouge" to "उच्च खपत (6+)",
        
        "dialog_titre" to "की खपत",
        "btn_sauvegarder" to "सहेजें",
        "btn_annuler" to "रद्द करें",
        "msg_sauvegarde_ok" to "सहेजा गया"
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
        "legende_orange" to "適度な消費（1-5）",
        "legende_rouge" to "高消費（6+）",
        
        "dialog_titre" to "の消費",
        "btn_sauvegarder" to "保存",
        "btn_annuler" to "キャンセル",
        "msg_sauvegarde_ok" to "保存されました"
    )
}
