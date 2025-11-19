package com.stopaddict

import android.util.Log

/**
 * CalendrierLangues.kt
 * Traductions pour l'onglet Calendrier
 * Supporte 10 langues : FR, EN, ES, PT, DE, IT, RU, AR, HI, JA
 */
object CalendrierLangues {

    private const val TAG = "CalendrierLangues"

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
        // Navigation mois
        "btn_mois_precedent" to "◀ Précédent",
        "btn_mois_suivant" to "Suivant ▶",
        
        // Actions rapides
        "btn_aujourdhui" to "Aujourd'hui",
        "btn_hier" to "Hier",
        "btn_demain" to "Demain",
        
        // Jours de la semaine (abrégés)
        "dim" to "Dim",
        "lun" to "Lun",
        "mar" to "Mar",
        "mer" to "Mer",
        "jeu" to "Jeu",
        "ven" to "Ven",
        "sam" to "Sam",
        
        // Mois de l'année
        "janvier" to "Janvier",
        "fevrier" to "Février",
        "mars" to "Mars",
        "avril" to "Avril",
        "mai" to "Mai",
        "juin" to "Juin",
        "juillet" to "Juillet",
        "aout" to "Août",
        "septembre" to "Septembre",
        "octobre" to "Octobre",
        "novembre" to "Novembre",
        "decembre" to "Décembre",
        
        // Bandeau profil
        "profil_complet" to "Profil complet",
        "profil_incomplet" to "Profil incomplet",
        "total_aujourdhui" to "Total aujourd'hui",
        
        // Légende
        "titre_legende" to "Légende",
        "label_cigarettes" to "Cigarettes",
        "label_joints" to "Joints",
        "label_alcool" to "Alcool",
        "label_alcool_global" to "Alcool global",
        "label_bieres" to "Bières",
        "label_liqueurs" to "Liqueurs",
        "label_alcool_fort" to "Alcool fort",
        "date_reduction" to "Date réduction",
        "date_arret" to "Date arrêt",
        "date_reussite" to "Date réussite",
        
        // Intensité consommation
        "intensite_consommation" to "Intensité de consommation",
        "faible" to "Faible",
        "moyenne" to "Moyenne",
        "elevee" to "Élevée",
        "tres_elevee" to "Très élevée",
        
        // Messages
        "aucune_consommation" to "Aucune consommation le",
        "details_jour" to "Détails du",
        "fermer" to "Fermer"
    )

    // ==================== ENGLISH ====================
    private val TRADUCTIONS_EN = mapOf(
        // Navigation mois
        "btn_mois_precedent" to "◀ Previous",
        "btn_mois_suivant" to "Next ▶",
        
        // Actions rapides
        "btn_aujourdhui" to "Today",
        "btn_hier" to "Yesterday",
        "btn_demain" to "Tomorrow",
        
        // Jours de la semaine (abrégés)
        "dim" to "Sun",
        "lun" to "Mon",
        "mar" to "Tue",
        "mer" to "Wed",
        "jeu" to "Thu",
        "ven" to "Fri",
        "sam" to "Sat",
        
        // Mois de l'année
        "janvier" to "January",
        "fevrier" to "February",
        "mars" to "March",
        "avril" to "April",
        "mai" to "May",
        "juin" to "June",
        "juillet" to "July",
        "aout" to "August",
        "septembre" to "September",
        "octobre" to "October",
        "novembre" to "November",
        "decembre" to "December",
        
        // Bandeau profil
        "profil_complet" to "Complete profile",
        "profil_incomplet" to "Incomplete profile",
        "total_aujourdhui" to "Today's total",
        
        // Légende
        "titre_legende" to "Legend",
        "label_cigarettes" to "Cigarettes",
        "label_joints" to "Joints",
        "label_alcool" to "Alcohol",
        "label_alcool_global" to "Global alcohol",
        "label_bieres" to "Beers",
        "label_liqueurs" to "Liqueurs",
        "label_alcool_fort" to "Hard liquor",
        "date_reduction" to "Reduction date",
        "date_arret" to "Quit date",
        "date_reussite" to "Success date",
        
        // Intensité consommation
        "intensite_consommation" to "Consumption intensity",
        "faible" to "Low",
        "moyenne" to "Medium",
        "elevee" to "High",
        "tres_elevee" to "Very high",
        
        // Messages
        "aucune_consommation" to "No consumption on",
        "details_jour" to "Details for",
        "fermer" to "Close"
    )

    // ==================== ESPAÑOL ====================
    private val TRADUCTIONS_ES = mapOf(
        // Navigation mois
        "btn_mois_precedent" to "◀ Anterior",
        "btn_mois_suivant" to "Siguiente ▶",
        
        // Actions rapides
        "btn_aujourdhui" to "Hoy",
        "btn_hier" to "Ayer",
        "btn_demain" to "Mañana",
        
        // Jours de la semaine (abrégés)
        "dim" to "Dom",
        "lun" to "Lun",
        "mar" to "Mar",
        "mer" to "Mié",
        "jeu" to "Jue",
        "ven" to "Vie",
        "sam" to "Sáb",
        
        // Mois de l'année
        "janvier" to "Enero",
        "fevrier" to "Febrero",
        "mars" to "Marzo",
        "avril" to "Abril",
        "mai" to "Mayo",
        "juin" to "Junio",
        "juillet" to "Julio",
        "aout" to "Agosto",
        "septembre" to "Septiembre",
        "octobre" to "Octubre",
        "novembre" to "Noviembre",
        "decembre" to "Diciembre",
        
        // Bandeau profil
        "profil_complet" to "Perfil completo",
        "profil_incomplet" to "Perfil incompleto",
        "total_aujourdhui" to "Total de hoy",
        
        // Légende
        "titre_legende" to "Leyenda",
        "label_cigarettes" to "Cigarrillos",
        "label_joints" to "Porros",
        "label_alcool" to "Alcohol",
        "label_alcool_global" to "Alcohol global",
        "label_bieres" to "Cervezas",
        "label_liqueurs" to "Licores",
        "label_alcool_fort" to "Alcohol fuerte",
        "date_reduction" to "Fecha reducción",
        "date_arret" to "Fecha cese",
        "date_reussite" to "Fecha éxito",
        
        // Intensité consommation
        "intensite_consommation" to "Intensidad de consumo",
        "faible" to "Bajo",
        "moyenne" to "Medio",
        "elevee" to "Alto",
        "tres_elevee" to "Muy alto",
        
        // Messages
        "aucune_consommation" to "Sin consumo el",
        "details_jour" to "Detalles del",
        "fermer" to "Cerrar"
    )

    // ==================== PORTUGUÊS ====================
    private val TRADUCTIONS_PT = mapOf(
        // Navigation mois
        "btn_mois_precedent" to "◀ Anterior",
        "btn_mois_suivant" to "Próximo ▶",
        
        // Actions rapides
        "btn_aujourdhui" to "Hoje",
        "btn_hier" to "Ontem",
        "btn_demain" to "Amanhã",
        
        // Jours de la semaine (abrégés)
        "dim" to "Dom",
        "lun" to "Seg",
        "mar" to "Ter",
        "mer" to "Qua",
        "jeu" to "Qui",
        "ven" to "Sex",
        "sam" to "Sáb",
        
        // Mois de l'année
        "janvier" to "Janeiro",
        "fevrier" to "Fevereiro",
        "mars" to "Março",
        "avril" to "Abril",
        "mai" to "Maio",
        "juin" to "Junho",
        "juillet" to "Julho",
        "aout" to "Agosto",
        "septembre" to "Setembro",
        "octobre" to "Outubro",
        "novembre" to "Novembro",
        "decembre" to "Dezembro",
        
        // Bandeau profil
        "profil_complet" to "Perfil completo",
        "profil_incomplet" to "Perfil incompleto",
        "total_aujourdhui" to "Total de hoje",
        
        // Légende
        "titre_legende" to "Legenda",
        "label_cigarettes" to "Cigarros",
        "label_joints" to "Baseados",
        "label_alcool" to "Álcool",
        "label_alcool_global" to "Álcool global",
        "label_bieres" to "Cervejas",
        "label_liqueurs" to "Licores",
        "label_alcool_fort" to "Bebida forte",
        "date_reduction" to "Data redução",
        "date_arret" to "Data parada",
        "date_reussite" to "Data sucesso",
        
        // Intensité consommation
        "intensite_consommation" to "Intensidade de consumo",
        "faible" to "Baixo",
        "moyenne" to "Médio",
        "elevee" to "Alto",
        "tres_elevee" to "Muito alto",
        
        // Messages
        "aucune_consommation" to "Sem consumo em",
        "details_jour" to "Detalhes de",
        "fermer" to "Fechar"
    )

    // ==================== DEUTSCH ====================
    private val TRADUCTIONS_DE = mapOf(
        // Navigation mois
        "btn_mois_precedent" to "◀ Zurück",
        "btn_mois_suivant" to "Weiter ▶",
        
        // Actions rapides
        "btn_aujourdhui" to "Heute",
        "btn_hier" to "Gestern",
        "btn_demain" to "Morgen",
        
        // Jours de la semaine (abrégés)
        "dim" to "So",
        "lun" to "Mo",
        "mar" to "Di",
        "mer" to "Mi",
        "jeu" to "Do",
        "ven" to "Fr",
        "sam" to "Sa",
        
        // Mois de l'année
        "janvier" to "Januar",
        "fevrier" to "Februar",
        "mars" to "März",
        "avril" to "April",
        "mai" to "Mai",
        "juin" to "Juni",
        "juillet" to "Juli",
        "aout" to "August",
        "septembre" to "September",
        "octobre" to "Oktober",
        "novembre" to "November",
        "decembre" to "Dezember",
        
        // Bandeau profil
        "profil_complet" to "Vollständiges Profil",
        "profil_incomplet" to "Unvollständiges Profil",
        "total_aujourdhui" to "Heute insgesamt",
        
        // Légende
        "titre_legende" to "Legende",
        "label_cigarettes" to "Zigaretten",
        "label_joints" to "Joints",
        "label_alcool" to "Alkohol",
        "label_alcool_global" to "Globaler Alkohol",
        "label_bieres" to "Biere",
        "label_liqueurs" to "Liköre",
        "label_alcool_fort" to "Starker Alkohol",
        "date_reduction" to "Reduzierungsdatum",
        "date_arret" to "Aufhördatum",
        "date_reussite" to "Erfolgsdatum",
        
        // Intensité consommation
        "intensite_consommation" to "Konsumintensität",
        "faible" to "Niedrig",
        "moyenne" to "Mittel",
        "elevee" to "Hoch",
        "tres_elevee" to "Sehr hoch",
        
        // Messages
        "aucune_consommation" to "Kein Konsum am",
        "details_jour" to "Details vom",
        "fermer" to "Schließen"
    )

    // ==================== ITALIANO ====================
    private val TRADUCTIONS_IT = mapOf(
        // Navigation mois
        "btn_mois_precedent" to "◀ Precedente",
        "btn_mois_suivant" to "Successivo ▶",
        
        // Actions rapides
        "btn_aujourdhui" to "Oggi",
        "btn_hier" to "Ieri",
        "btn_demain" to "Domani",
        
        // Jours de la semaine (abrégés)
        "dim" to "Dom",
        "lun" to "Lun",
        "mar" to "Mar",
        "mer" to "Mer",
        "jeu" to "Gio",
        "ven" to "Ven",
        "sam" to "Sab",
        
        // Mois de l'année
        "janvier" to "Gennaio",
        "fevrier" to "Febbraio",
        "mars" to "Marzo",
        "avril" to "Aprile",
        "mai" to "Maggio",
        "juin" to "Giugno",
        "juillet" to "Luglio",
        "aout" to "Agosto",
        "septembre" to "Settembre",
        "octobre" to "Ottobre",
        "novembre" to "Novembre",
        "decembre" to "Dicembre",
        
        // Bandeau profil
        "profil_complet" to "Profilo completo",
        "profil_incomplet" to "Profilo incompleto",
        "total_aujourdhui" to "Totale di oggi",
        
        // Légende
        "titre_legende" to "Legenda",
        "label_cigarettes" to "Sigarette",
        "label_joints" to "Spinelli",
        "label_alcool" to "Alcol",
        "label_alcool_global" to "Alcol globale",
        "label_bieres" to "Birre",
        "label_liqueurs" to "Liquori",
        "label_alcool_fort" to "Alcol forte",
        "date_reduction" to "Data riduzione",
        "date_arret" to "Data cessazione",
        "date_reussite" to "Data successo",
        
        // Intensité consommation
        "intensite_consommation" to "Intensità di consumo",
        "faible" to "Basso",
        "moyenne" to "Medio",
        "elevee" to "Alto",
        "tres_elevee" to "Molto alto",
        
        // Messages
        "aucune_consommation" to "Nessun consumo il",
        "details_jour" to "Dettagli del",
        "fermer" to "Chiudi"
    )

    // ==================== РУССКИЙ ====================
    private val TRADUCTIONS_RU = mapOf(
        // Navigation mois
        "btn_mois_precedent" to "◀ Назад",
        "btn_mois_suivant" to "Вперёд ▶",
        
        // Actions rapides
        "btn_aujourdhui" to "Сегодня",
        "btn_hier" to "Вчера",
        "btn_demain" to "Завтра",
        
        // Jours de la semaine (abrégés)
        "dim" to "Вс",
        "lun" to "Пн",
        "mar" to "Вт",
        "mer" to "Ср",
        "jeu" to "Чт",
        "ven" to "Пт",
        "sam" to "Сб",
        
        // Mois de l'année
        "janvier" to "Январь",
        "fevrier" to "Февраль",
        "mars" to "Март",
        "avril" to "Апрель",
        "mai" to "Май",
        "juin" to "Июнь",
        "juillet" to "Июль",
        "aout" to "Август",
        "septembre" to "Сентябрь",
        "octobre" to "Октябрь",
        "novembre" to "Ноябрь",
        "decembre" to "Декабрь",
        
        // Bandeau profil
        "profil_complet" to "Полный профиль",
        "profil_incomplet" to "Неполный профиль",
        "total_aujourdhui" to "Всего сегодня",
        
        // Légende
        "titre_legende" to "Легенда",
        "label_cigarettes" to "Сигареты",
        "label_joints" to "Косяки",
        "label_alcool" to "Алкоголь",
        "label_alcool_global" to "Алкоголь общий",
        "label_bieres" to "Пиво",
        "label_liqueurs" to "Ликёры",
        "label_alcool_fort" to "Крепкий алкоголь",
        "date_reduction" to "Дата сокращения",
        "date_arret" to "Дата прекращения",
        "date_reussite" to "Дата успеха",
        
        // Intensité consommation
        "intensite_consommation" to "Интенсивность потребления",
        "faible" to "Низкая",
        "moyenne" to "Средняя",
        "elevee" to "Высокая",
        "tres_elevee" to "Очень высокая",
        
        // Messages
        "aucune_consommation" to "Нет потребления",
        "details_jour" to "Детали за",
        "fermer" to "Закрыть"
    )

    // ==================== العربية ====================
    private val TRADUCTIONS_AR = mapOf(
        // Navigation mois
        "btn_mois_precedent" to "◀ السابق",
        "btn_mois_suivant" to "التالي ▶",
        
        // Actions rapides
        "btn_aujourdhui" to "اليوم",
        "btn_hier" to "أمس",
        "btn_demain" to "غدا",
        
        // Jours de la semaine (abrégés)
        "dim" to "الأحد",
        "lun" to "الإثنين",
        "mar" to "الثلاثاء",
        "mer" to "الأربعاء",
        "jeu" to "الخميس",
        "ven" to "الجمعة",
        "sam" to "السبت",
        
        // Mois de l'année
        "janvier" to "يناير",
        "fevrier" to "فبراير",
        "mars" to "مارس",
        "avril" to "أبريل",
        "mai" to "مايو",
        "juin" to "يونيو",
        "juillet" to "يوليو",
        "aout" to "أغسطس",
        "septembre" to "سبتمبر",
        "octobre" to "أكتوبر",
        "novembre" to "نوفمبر",
        "decembre" to "ديسمبر",
        
        // Bandeau profil
        "profil_complet" to "ملف كامل",
        "profil_incomplet" to "ملف غير مكتمل",
        "total_aujourdhui" to "المجموع اليوم",
        
        // Légende
        "titre_legende" to "وسيلة الإيضاح",
        "label_cigarettes" to "سجائر",
        "label_joints" to "لفائف",
        "label_alcool" to "كحول",
        "label_alcool_global" to "كحول عام",
        "label_bieres" to "بيرة",
        "label_liqueurs" to "مشروبات كحولية",
        "label_alcool_fort" to "كحول قوي",
        "date_reduction" to "تاريخ التخفيض",
        "date_arret" to "تاريخ التوقف",
        "date_reussite" to "تاريخ النجاح",
        
        // Intensité consommation
        "intensite_consommation" to "كثافة الاستهلاك",
        "faible" to "منخفض",
        "moyenne" to "متوسط",
        "elevee" to "مرتفع",
        "tres_elevee" to "مرتفع جداً",
        
        // Messages
        "aucune_consommation" to "لا استهلاك في",
        "details_jour" to "تفاصيل",
        "fermer" to "إغلاق"
    )

    // ==================== हिन्दी ====================
    private val TRADUCTIONS_HI = mapOf(
        // Navigation mois
        "btn_mois_precedent" to "◀ पिछला",
        "btn_mois_suivant" to "अगला ▶",
        
        // Actions rapides
        "btn_aujourdhui" to "आज",
        "btn_hier" to "कल",
        "btn_demain" to "कल",
        
        // Jours de la semaine (abrégés)
        "dim" to "रवि",
        "lun" to "सोम",
        "mar" to "मंगल",
        "mer" to "बुध",
        "jeu" to "गुरु",
        "ven" to "शुक्र",
        "sam" to "शनि",
        
        // Mois de l'année
        "janvier" to "जनवरी",
        "fevrier" to "फरवरी",
        "mars" to "मार्च",
        "avril" to "अप्रैल",
        "mai" to "मई",
        "juin" to "जून",
        "juillet" to "जुलाई",
        "aout" to "अगस्त",
        "septembre" to "सितंबर",
        "octobre" to "अक्टूबर",
        "novembre" to "नवंबर",
        "decembre" to "दिसंबर",
        
        // Bandeau profil
        "profil_complet" to "पूर्ण प्रोफ़ाइल",
        "profil_incomplet" to "अधूरी प्रोफ़ाइल",
        "total_aujourdhui" to "आज का कुल",
        
        // Légende
        "titre_legende" to "चिह्न",
        "label_cigarettes" to "सिगरेट",
        "label_joints" to "जोइंट्स",
        "label_alcool" to "शराब",
        "label_alcool_global" to "वैश्विक शराब",
        "label_bieres" to "बीयर",
        "label_liqueurs" to "शराब",
        "label_alcool_fort" to "मजबूत शराब",
        "date_reduction" to "कमी की तारीख",
        "date_arret" to "बंद करने की तारीख",
        "date_reussite" to "सफलता की तारीख",
        
        // Intensité consommation
        "intensite_consommation" to "उपभोग की तीव्रता",
        "faible" to "कम",
        "moyenne" to "मध्यम",
        "elevee" to "उच्च",
        "tres_elevee" to "बहुत उच्च",
        
        // Messages
        "aucune_consommation" to "कोई उपभोग नहीं",
        "details_jour" to "विवरण",
        "fermer" to "बंद करें"
    )

    // ==================== 日本語 ====================
    private val TRADUCTIONS_JA = mapOf(
        // Navigation mois
        "btn_mois_precedent" to "◀ 前へ",
        "btn_mois_suivant" to "次へ ▶",
        
        // Actions rapides
        "btn_aujourdhui" to "今日",
        "btn_hier" to "昨日",
        "btn_demain" to "明日",
        
        // Jours de la semaine (abrégés)
        "dim" to "日",
        "lun" to "月",
        "mar" to "火",
        "mer" to "水",
        "jeu" to "木",
        "ven" to "金",
        "sam" to "土",
        
        // Mois de l'année
        "janvier" to "1月",
        "fevrier" to "2月",
        "mars" to "3月",
        "avril" to "4月",
        "mai" to "5月",
        "juin" to "6月",
        "juillet" to "7月",
        "aout" to "8月",
        "septembre" to "9月",
        "octobre" to "10月",
        "novembre" to "11月",
        "decembre" to "12月",
        
        // Bandeau profil
        "profil_complet" to "完全なプロフィール",
        "profil_incomplet" to "不完全なプロフィール",
        "total_aujourdhui" to "今日の合計",
        
        // Légende
        "titre_legende" to "凡例",
        "label_cigarettes" to "タバコ",
        "label_joints" to "ジョイント",
        "label_alcool" to "アルコール",
        "label_alcool_global" to "グローバルアルコール",
        "label_bieres" to "ビール",
        "label_liqueurs" to "リキュール",
        "label_alcool_fort" to "強いアルコール",
        "date_reduction" to "削減日",
        "date_arret" to "禁煙日",
        "date_reussite" to "成功日",
        
        // Intensité consommation
        "intensite_consommation" to "消費強度",
        "faible" to "低",
        "moyenne" to "中",
        "elevee" to "高",
        "tres_elevee" to "非常に高い",
        
        // Messages
        "aucune_consommation" to "消費なし",
        "details_jour" to "詳細",
        "fermer" to "閉じる"
    )
}

// ══════════════════════════════════════════════════════════════════════
// FIN CalendrierLangues.kt
// Total: ~700 lignes
// 
// Contenu:
// - 10 langues complètes : FR, EN, ES, PT, DE, IT, RU, AR, HI, JA
// - Navigation : boutons mois + actions rapides
// - Jours de la semaine (abrégés)
// - Mois de l'année (complets)
// - Bandeau : profil + total jour
// - Légende : 6 catégories + 3 types de dates
// - Intensité : 4 niveaux
// - Messages : consommation + détails
// 
// Utilisation:
// val trad = CalendrierLangues.getTraductions("FR")
// button.text = trad["btn_aujourdhui"]
// ══════════════════════════════════════════════════════════════════════
