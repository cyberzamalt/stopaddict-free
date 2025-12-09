package com.stopaddict

import android.util.Log

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
                Log.w(TAG, "Langue inconnue: $codeLangue, fallback FR")
                TRADUCTIONS_FR
            }
        }
    }

    // ==================== FRANÇAIS ====================
    private val TRADUCTIONS_FR = mapOf(
        "titre" to "Habitudes & Volonté",
        
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
        "titre_volonte" to "Zone Volonté - Dates Objectifs",
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
        "msg_sauvegarde_ok" to "Habitudes et dates sauvegardées"
    )

    // ==================== ENGLISH ====================
    private val TRADUCTIONS_EN = mapOf(
        "titre" to "Habits & Willpower",
        
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
        
        "titre_volonte" to "Willpower Zone - Goal Dates",
        "desc_volonte" to "Set 3 dates per category: Reduction, Quit, Success",
        
        "label_date_reduction" to "Reduction date",
        "label_date_arret" to "Quit date",
        "label_date_reussite" to "Success date",
        
        "btn_sauvegarder" to "Save",
        "btn_selectionner_date" to "Select a date",
        "btn_effacer" to "Clear",
        
        "msg_sauvegarde_ok" to "Habits and dates saved"
    )

    // ==================== ESPAÑOL ====================
    private val TRADUCTIONS_ES = mapOf(
        "titre" to "Hábitos y Voluntad",
        
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
        
        "titre_volonte" to "Zona Voluntad - Fechas Objetivo",
        "desc_volonte" to "Define 3 fechas por categoría: Reducción, Parada, Éxito",
        
        "label_date_reduction" to "Fecha de reducción",
        "label_date_arret" to "Fecha de parada",
        "label_date_reussite" to "Fecha de éxito",
        
        "btn_sauvegarder" to "Guardar",
        "btn_selectionner_date" to "Seleccionar una fecha",
        "btn_effacer" to "Borrar",
        
        "msg_sauvegarde_ok" to "Hábitos y fechas guardados"
    )

    // ==================== PORTUGUÊS ====================
    private val TRADUCTIONS_PT = mapOf(
        "titre" to "Hábitos e Vontade",
        
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
        
        "titre_volonte" to "Zona Vontade - Datas Objetivo",
        "desc_volonte" to "Defina 3 datas por categoria: Redução, Parada, Sucesso",
        
        "label_date_reduction" to "Data de redução",
        "label_date_arret" to "Data de parada",
        "label_date_reussite" to "Data de sucesso",
        
        "btn_sauvegarder" to "Salvar",
        "btn_selectionner_date" to "Selecionar uma data",
        "btn_effacer" to "Apagar",
        
        "msg_sauvegarde_ok" to "Hábitos e datas salvos"
    )

    // ==================== DEUTSCH ====================
    private val TRADUCTIONS_DE = mapOf(
        "titre" to "Gewohnheiten & Willenskraft",
        
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
        
        "titre_volonte" to "Willenskraft-Zone - Zieldaten",
        "desc_volonte" to "Legen Sie 3 Daten pro Kategorie fest: Reduktion, Stopp, Erfolg",
        
        "label_date_reduction" to "Reduktionsdatum",
        "label_date_arret" to "Stoppdatum",
        "label_date_reussite" to "Erfolgsdatum",
        
        "btn_sauvegarder" to "Speichern",
        "btn_selectionner_date" to "Ein Datum auswählen",
        "btn_effacer" to "Löschen",
        
        "msg_sauvegarde_ok" to "Gewohnheiten und Daten gespeichert"
    )

    // ==================== ITALIANO ====================
    private val TRADUCTIONS_IT = mapOf(
        "titre" to "Abitudini e Volontà",
        
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
        
        "titre_volonte" to "Zona Volontà - Date Obiettivo",
        "desc_volonte" to "Imposta 3 date per categoria: Riduzione, Stop, Successo",
        
        "label_date_reduction" to "Data di riduzione",
        "label_date_arret" to "Data di stop",
        "label_date_reussite" to "Data di successo",
        
        "btn_sauvegarder" to "Salva",
        "btn_selectionner_date" to "Seleziona una data",
        "btn_effacer" to "Cancella",
        
        "msg_sauvegarde_ok" to "Abitudini e date salvate"
    )

    // ==================== РУССКИЙ ====================
    private val TRADUCTIONS_RU = mapOf(
        "titre" to "Привычки и Воля",
        
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
        
        "titre_volonte" to "Зона Воли - Целевые Даты",
        "desc_volonte" to "Установите 3 даты на категорию: Сокращение, Прекращение, Успех",
        
        "label_date_reduction" to "Дата сокращения",
        "label_date_arret" to "Дата прекращения",
        "label_date_reussite" to "Дата успеха",
        
        "btn_sauvegarder" to "Сохранить",
        "btn_selectionner_date" to "Выбрать дату",
        "btn_effacer" to "Очистить",
        
        "msg_sauvegarde_ok" to "Привычки и даты сохранены"
    )

    // ==================== العربية ====================
    private val TRADUCTIONS_AR = mapOf(
        "titre" to "العادات والإرادة",
        
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
        
        "titre_volonte" to "منطقة الإرادة - تواريخ الأهداف",
        "desc_volonte" to "حدد 3 تواريخ لكل فئة: التخفيض، التوقف، النجاح",
        
        "label_date_reduction" to "تاريخ التخفيض",
        "label_date_arret" to "تاريخ التوقف",
        "label_date_reussite" to "تاريخ النجاح",
        
        "btn_sauvegarder" to "حفظ",
        "btn_selectionner_date" to "اختر تاريخًا",
        "btn_effacer" to "حذف",
        
        "msg_sauvegarde_ok" to "تم حفظ العادات والتواريخ"
    )

    // ==================== हिन्दी ====================
    private val TRADUCTIONS_HI = mapOf(
        "titre" to "आदतें और इच्छाशक्ति",
        
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
        
        "titre_volonte" to "इच्छाशक्ति क्षेत्र - लक्ष्य तिथियां",
        "desc_volonte" to "प्रति श्रेणी 3 तिथियां निर्धारित करें: कमी, रुकावट, सफलता",
        
        "label_date_reduction" to "कमी की तिथि",
        "label_date_arret" to "रुकावट की तिथि",
        "label_date_reussite" to "सफलता की तिथि",
        
        "btn_sauvegarder" to "सहेजें",
        "btn_selectionner_date" to "एक तिथि चुनें",
        "btn_effacer" to "हटाएँ",
        
        "msg_sauvegarde_ok" to "आदतें और तिथियां सहेजी गईं"
    )

    // ==================== 日本語 ====================
    private val TRADUCTIONS_JA = mapOf(
        "titre" to "習慣と意志力",
        
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
        
        "titre_volonte" to "意志力ゾーン - 目標日",
        "desc_volonte" to "カテゴリごとに3つの日付を設定：削減、停止、成功",
        
        "label_date_reduction" to "削減日",
        "label_date_arret" to "停止日",
        "label_date_reussite" to "成功日",
        
        "btn_sauvegarder" to "保存",
        "btn_selectionner_date" to "日付を選択",
        "btn_effacer" to "消去",
        
        "msg_sauvegarde_ok" to "習慣と日付が保存されました"
    )
}

