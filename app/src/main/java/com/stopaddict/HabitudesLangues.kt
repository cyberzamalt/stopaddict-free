package com.stopaddict

import android.util.Log

/**
 * HabitudesLangues.kt
 * Traductions pour l'onglet Habitudes & Volonté
 * Supporte 10 langues : FR, EN, ES, PT, DE, IT, RU, AR, HI, JA
 */
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
        // Labels catégories
        "label_cigarettes" to "Cigarettes",
        "label_joints" to "Joints",
        "label_alcool_global" to "Alcool global",
        "label_bieres" to "Bières",
        "label_liqueurs" to "Liqueurs",
        "label_alcool_fort" to "Alcool fort",
        
        // Zone Habitudes
        "titre_zone_habitudes" to "Zone Habitudes",
        "hint_max_cigarettes" to "Max cigarettes par jour",
        "hint_max_joints" to "Max joints par jour",
        "hint_max_alcool_global" to "Max alcool global par jour",
        "hint_max_bieres" to "Max bières par jour",
        "hint_max_liqueurs" to "Max liqueurs par jour",
        "hint_max_alcool_fort" to "Max alcool fort par jour",
        
        // Zone Volonté
        "titre_zone_volonte" to "Zone Volonté",
        "date_reduction" to "Date de réduction",
        "date_arret" to "Date d'arrêt",
        "date_reussite" to "Date de réussite (je ne fume plus)",
        
        // Boutons
        "btn_sauvegarder" to "Sauvegarder",
        "selectionner_date" to "Sélectionner date",
        "oui" to "Oui",
        "non" to "Non",
        
        // Bandeau profil
        "profil_complet" to "Profil complet",
        "profil_incomplet" to "Profil incomplet",
        "total_aujourdhui" to "Total aujourd'hui",
        
        // Messages succès
        "date_sauvegardee" to "Date sauvegardée",
        "date_effacee" to "Date effacée",
        "habitudes_sauvegardees" to "Habitudes sauvegardées avec succès",
        
        // Messages erreurs
        "erreur_valeurs_negatives" to "Les valeurs ne peuvent pas être négatives",
        "erreur_valeurs_trop_elevees" to "Les valeurs doivent être inférieures à 1000",
        "erreur_date_reduction_future" to "La date de réduction ne peut pas être dans le futur",
        "erreur_date_arret_future" to "La date d'arrêt ne peut pas être dans le futur",
        "erreur_date_arret_avant_reduction" to "La date d'arrêt doit être après la date de réduction",
        "erreur_date_reussite_avant_arret" to "La date de réussite doit être après la date d'arrêt",
        "erreur_sauvegarde" to "Erreur lors de la sauvegarde",
        
        // Confirmations
        "confirmer_effacer_date" to "Effacer la date ?",
        "message_effacer_date" to "Voulez-vous vraiment effacer cette date ?"
    )

    // ==================== ENGLISH ====================
    private val TRADUCTIONS_EN = mapOf(
        // Labels catégories
        "label_cigarettes" to "Cigarettes",
        "label_joints" to "Joints",
        "label_alcool_global" to "Global alcohol",
        "label_bieres" to "Beers",
        "label_liqueurs" to "Liqueurs",
        "label_alcool_fort" to "Hard liquor",
        
        // Zone Habitudes
        "titre_zone_habitudes" to "Habits Zone",
        "hint_max_cigarettes" to "Max cigarettes per day",
        "hint_max_joints" to "Max joints per day",
        "hint_max_alcool_global" to "Max global alcohol per day",
        "hint_max_bieres" to "Max beers per day",
        "hint_max_liqueurs" to "Max liqueurs per day",
        "hint_max_alcool_fort" to "Max hard liquor per day",
        
        // Zone Volonté
        "titre_zone_volonte" to "Willpower Zone",
        "date_reduction" to "Reduction date",
        "date_arret" to "Quit date",
        "date_reussite" to "Success date (smoke-free)",
        
        // Boutons
        "btn_sauvegarder" to "Save",
        "selectionner_date" to "Select date",
        "oui" to "Yes",
        "non" to "No",
        
        // Bandeau profil
        "profil_complet" to "Complete profile",
        "profil_incomplet" to "Incomplete profile",
        "total_aujourdhui" to "Today's total",
        
        // Messages succès
        "date_sauvegardee" to "Date saved",
        "date_effacee" to "Date deleted",
        "habitudes_sauvegardees" to "Habits saved successfully",
        
        // Messages erreurs
        "erreur_valeurs_negatives" to "Values cannot be negative",
        "erreur_valeurs_trop_elevees" to "Values must be less than 1000",
        "erreur_date_reduction_future" to "Reduction date cannot be in the future",
        "erreur_date_arret_future" to "Quit date cannot be in the future",
        "erreur_date_arret_avant_reduction" to "Quit date must be after reduction date",
        "erreur_date_reussite_avant_arret" to "Success date must be after quit date",
        "erreur_sauvegarde" to "Error saving data",
        
        // Confirmations
        "confirmer_effacer_date" to "Delete date?",
        "message_effacer_date" to "Do you really want to delete this date?"
    )

    // ==================== ESPAÑOL ====================
    private val TRADUCTIONS_ES = mapOf(
        // Labels catégories
        "label_cigarettes" to "Cigarrillos",
        "label_joints" to "Porros",
        "label_alcool_global" to "Alcohol global",
        "label_bieres" to "Cervezas",
        "label_liqueurs" to "Licores",
        "label_alcool_fort" to "Alcohol fuerte",
        
        // Zone Habitudes
        "titre_zone_habitudes" to "Zona Hábitos",
        "hint_max_cigarettes" to "Máx cigarrillos por día",
        "hint_max_joints" to "Máx porros por día",
        "hint_max_alcool_global" to "Máx alcohol global por día",
        "hint_max_bieres" to "Máx cervezas por día",
        "hint_max_liqueurs" to "Máx licores por día",
        "hint_max_alcool_fort" to "Máx alcohol fuerte por día",
        
        // Zone Volonté
        "titre_zone_volonte" to "Zona Voluntad",
        "date_reduction" to "Fecha de reducción",
        "date_arret" to "Fecha de cese",
        "date_reussite" to "Fecha de éxito (no fumo más)",
        
        // Boutons
        "btn_sauvegarder" to "Guardar",
        "selectionner_date" to "Seleccionar fecha",
        "oui" to "Sí",
        "non" to "No",
        
        // Bandeau profil
        "profil_complet" to "Perfil completo",
        "profil_incomplet" to "Perfil incompleto",
        "total_aujourdhui" to "Total de hoy",
        
        // Messages succès
        "date_sauvegardee" to "Fecha guardada",
        "date_effacee" to "Fecha borrada",
        "habitudes_sauvegardees" to "Hábitos guardados con éxito",
        
        // Messages erreurs
        "erreur_valeurs_negatives" to "Los valores no pueden ser negativos",
        "erreur_valeurs_trop_elevees" to "Los valores deben ser inferiores a 1000",
        "erreur_date_reduction_future" to "La fecha de reducción no puede ser futura",
        "erreur_date_arret_future" to "La fecha de cese no puede ser futura",
        "erreur_date_arret_avant_reduction" to "La fecha de cese debe ser después de la reducción",
        "erreur_date_reussite_avant_arret" to "La fecha de éxito debe ser después del cese",
        "erreur_sauvegarde" to "Error al guardar",
        
        // Confirmations
        "confirmer_effacer_date" to "¿Borrar fecha?",
        "message_effacer_date" to "¿Realmente quiere borrar esta fecha?"
    )

    // ==================== PORTUGUÊS ====================
    private val TRADUCTIONS_PT = mapOf(
        // Labels catégories
        "label_cigarettes" to "Cigarros",
        "label_joints" to "Baseados",
        "label_alcool_global" to "Álcool global",
        "label_bieres" to "Cervejas",
        "label_liqueurs" to "Licores",
        "label_alcool_fort" to "Bebida forte",
        
        // Zone Habitudes
        "titre_zone_habitudes" to "Zona Hábitos",
        "hint_max_cigarettes" to "Máx cigarros por dia",
        "hint_max_joints" to "Máx baseados por dia",
        "hint_max_alcool_global" to "Máx álcool global por dia",
        "hint_max_bieres" to "Máx cervejas por dia",
        "hint_max_liqueurs" to "Máx licores por dia",
        "hint_max_alcool_fort" to "Máx bebida forte por dia",
        
        // Zone Volonté
        "titre_zone_volonte" to "Zona Vontade",
        "date_reduction" to "Data de redução",
        "date_arret" to "Data de parada",
        "date_reussite" to "Data de sucesso (não fumo mais)",
        
        // Boutons
        "btn_sauvegarder" to "Salvar",
        "selectionner_date" to "Selecionar data",
        "oui" to "Sim",
        "non" to "Não",
        
        // Bandeau profil
        "profil_complet" to "Perfil completo",
        "profil_incomplet" to "Perfil incompleto",
        "total_aujourdhui" to "Total de hoje",
        
        // Messages succès
        "date_sauvegardee" to "Data salva",
        "date_effacee" to "Data apagada",
        "habitudes_sauvegardees" to "Hábitos salvos com sucesso",
        
        // Messages erreurs
        "erreur_valeurs_negatives" to "Os valores não podem ser negativos",
        "erreur_valeurs_trop_elevees" to "Os valores devem ser inferiores a 1000",
        "erreur_date_reduction_future" to "A data de redução não pode ser futura",
        "erreur_date_arret_future" to "A data de parada não pode ser futura",
        "erreur_date_arret_avant_reduction" to "A data de parada deve ser após a redução",
        "erreur_date_reussite_avant_arret" to "A data de sucesso deve ser após a parada",
        "erreur_sauvegarde" to "Erro ao salvar",
        
        // Confirmations
        "confirmer_effacer_date" to "Apagar data?",
        "message_effacer_date" to "Realmente deseja apagar esta data?"
    )

    // ==================== DEUTSCH ====================
    private val TRADUCTIONS_DE = mapOf(
        // Labels catégories
        "label_cigarettes" to "Zigaretten",
        "label_joints" to "Joints",
        "label_alcool_global" to "Globaler Alkohol",
        "label_bieres" to "Biere",
        "label_liqueurs" to "Liköre",
        "label_alcool_fort" to "Starker Alkohol",
        
        // Zone Habitudes
        "titre_zone_habitudes" to "Gewohnheiten Zone",
        "hint_max_cigarettes" to "Max Zigaretten pro Tag",
        "hint_max_joints" to "Max Joints pro Tag",
        "hint_max_alcool_global" to "Max globaler Alkohol pro Tag",
        "hint_max_bieres" to "Max Biere pro Tag",
        "hint_max_liqueurs" to "Max Liköre pro Tag",
        "hint_max_alcool_fort" to "Max starker Alkohol pro Tag",
        
        // Zone Volonté
        "titre_zone_volonte" to "Willenskraft Zone",
        "date_reduction" to "Reduzierungsdatum",
        "date_arret" to "Aufhördatum",
        "date_reussite" to "Erfolgsdatum (rauchfrei)",
        
        // Boutons
        "btn_sauvegarder" to "Speichern",
        "selectionner_date" to "Datum auswählen",
        "oui" to "Ja",
        "non" to "Nein",
        
        // Bandeau profil
        "profil_complet" to "Vollständiges Profil",
        "profil_incomplet" to "Unvollständiges Profil",
        "total_aujourdhui" to "Heute insgesamt",
        
        // Messages succès
        "date_sauvegardee" to "Datum gespeichert",
        "date_effacee" to "Datum gelöscht",
        "habitudes_sauvegardees" to "Gewohnheiten erfolgreich gespeichert",
        
        // Messages erreurs
        "erreur_valeurs_negatives" to "Werte können nicht negativ sein",
        "erreur_valeurs_trop_elevees" to "Werte müssen unter 1000 sein",
        "erreur_date_reduction_future" to "Reduzierungsdatum kann nicht in der Zukunft liegen",
        "erreur_date_arret_future" to "Aufhördatum kann nicht in der Zukunft liegen",
        "erreur_date_arret_avant_reduction" to "Aufhördatum muss nach Reduzierung sein",
        "erreur_date_reussite_avant_arret" to "Erfolgsdatum muss nach Aufhördatum sein",
        "erreur_sauvegarde" to "Fehler beim Speichern",
        
        // Confirmations
        "confirmer_effacer_date" to "Datum löschen?",
        "message_effacer_date" to "Möchten Sie dieses Datum wirklich löschen?"
    )

    // ==================== ITALIANO ====================
    private val TRADUCTIONS_IT = mapOf(
        // Labels catégories
        "label_cigarettes" to "Sigarette",
        "label_joints" to "Spinelli",
        "label_alcool_global" to "Alcol globale",
        "label_bieres" to "Birre",
        "label_liqueurs" to "Liquori",
        "label_alcool_fort" to "Alcol forte",
        
        // Zone Habitudes
        "titre_zone_habitudes" to "Zona Abitudini",
        "hint_max_cigarettes" to "Max sigarette al giorno",
        "hint_max_joints" to "Max spinelli al giorno",
        "hint_max_alcool_global" to "Max alcol globale al giorno",
        "hint_max_bieres" to "Max birre al giorno",
        "hint_max_liqueurs" to "Max liquori al giorno",
        "hint_max_alcool_fort" to "Max alcol forte al giorno",
        
        // Zone Volonté
        "titre_zone_volonte" to "Zona Volontà",
        "date_reduction" to "Data di riduzione",
        "date_arret" to "Data di cessazione",
        "date_reussite" to "Data di successo (non fumo più)",
        
        // Boutons
        "btn_sauvegarder" to "Salva",
        "selectionner_date" to "Seleziona data",
        "oui" to "Sì",
        "non" to "No",
        
        // Bandeau profil
        "profil_complet" to "Profilo completo",
        "profil_incomplet" to "Profilo incompleto",
        "total_aujourdhui" to "Totale di oggi",
        
        // Messages succès
        "date_sauvegardee" to "Data salvata",
        "date_effacee" to "Data cancellata",
        "habitudes_sauvegardees" to "Abitudini salvate con successo",
        
        // Messages erreurs
        "erreur_valeurs_negatives" to "I valori non possono essere negativi",
        "erreur_valeurs_trop_elevees" to "I valori devono essere inferiori a 1000",
        "erreur_date_reduction_future" to "La data di riduzione non può essere futura",
        "erreur_date_arret_future" to "La data di cessazione non può essere futura",
        "erreur_date_arret_avant_reduction" to "La data di cessazione deve essere dopo la riduzione",
        "erreur_date_reussite_avant_arret" to "La data di successo deve essere dopo la cessazione",
        "erreur_sauvegarde" to "Errore nel salvataggio",
        
        // Confirmations
        "confirmer_effacer_date" to "Cancellare data?",
        "message_effacer_date" to "Vuoi davvero cancellare questa data?"
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
        
        // Zone Habitudes
        "titre_zone_habitudes" to "Зона привычек",
        "hint_max_cigarettes" to "Макс сигарет в день",
        "hint_max_joints" to "Макс косяков в день",
        "hint_max_alcool_global" to "Макс алкоголя в день",
        "hint_max_bieres" to "Макс пива в день",
        "hint_max_liqueurs" to "Макс ликёров в день",
        "hint_max_alcool_fort" to "Макс крепкого алкоголя в день",
        
        // Zone Volonté
        "titre_zone_volonte" to "Зона силы воли",
        "date_reduction" to "Дата сокращения",
        "date_arret" to "Дата прекращения",
        "date_reussite" to "Дата успеха (больше не курю)",
        
        // Boutons
        "btn_sauvegarder" to "Сохранить",
        "selectionner_date" to "Выбрать дату",
        "oui" to "Да",
        "non" to "Нет",
        
        // Bandeau profil
        "profil_complet" to "Полный профиль",
        "profil_incomplet" to "Неполный профиль",
        "total_aujourdhui" to "Всего сегодня",
        
        // Messages succès
        "date_sauvegardee" to "Дата сохранена",
        "date_effacee" to "Дата удалена",
        "habitudes_sauvegardees" to "Привычки успешно сохранены",
        
        // Messages erreurs
        "erreur_valeurs_negatives" to "Значения не могут быть отрицательными",
        "erreur_valeurs_trop_elevees" to "Значения должны быть менее 1000",
        "erreur_date_reduction_future" to "Дата сокращения не может быть в будущем",
        "erreur_date_arret_future" to "Дата прекращения не может быть в будущем",
        "erreur_date_arret_avant_reduction" to "Дата прекращения должна быть после сокращения",
        "erreur_date_reussite_avant_arret" to "Дата успеха должна быть после прекращения",
        "erreur_sauvegarde" to "Ошибка сохранения",
        
        // Confirmations
        "confirmer_effacer_date" to "Удалить дату?",
        "message_effacer_date" to "Вы действительно хотите удалить эту дату?"
    )

    // ==================== العربية ====================
    private val TRADUCTIONS_AR = mapOf(
        // Labels catégories
        "label_cigarettes" to "سجائر",
        "label_joints" to "لفائف",
        "label_alcool_global" to "كحول عام",
        "label_bieres" to "بيرة",
        "label_liqueurs" to "مشروبات كحولية",
        "label_alcool_fort" to "كحول قوي",
        
        // Zone Habitudes
        "titre_zone_habitudes" to "منطقة العادات",
        "hint_max_cigarettes" to "الحد الأقصى للسجائر في اليوم",
        "hint_max_joints" to "الحد الأقصى للفائف في اليوم",
        "hint_max_alcool_global" to "الحد الأقصى للكحول العام في اليوم",
        "hint_max_bieres" to "الحد الأقصى للبيرة في اليوم",
        "hint_max_liqueurs" to "الحد الأقصى للمشروبات الكحولية في اليوم",
        "hint_max_alcool_fort" to "الحد الأقصى للكحول القوي في اليوم",
        
        // Zone Volonté
        "titre_zone_volonte" to "منطقة الإرادة",
        "date_reduction" to "تاريخ التخفيض",
        "date_arret" to "تاريخ التوقف",
        "date_reussite" to "تاريخ النجاح (لم أعد أدخن)",
        
        // Boutons
        "btn_sauvegarder" to "حفظ",
        "selectionner_date" to "اختر التاريخ",
        "oui" to "نعم",
        "non" to "لا",
        
        // Bandeau profil
        "profil_complet" to "ملف كامل",
        "profil_incomplet" to "ملف غير مكتمل",
        "total_aujourdhui" to "المجموع اليوم",
        
        // Messages succès
        "date_sauvegardee" to "تم حفظ التاريخ",
        "date_effacee" to "تم حذف التاريخ",
        "habitudes_sauvegardees" to "تم حفظ العادات بنجاح",
        
        // Messages erreurs
        "erreur_valeurs_negatives" to "لا يمكن أن تكون القيم سالبة",
        "erreur_valeurs_trop_elevees" to "يجب أن تكون القيم أقل من 1000",
        "erreur_date_reduction_future" to "لا يمكن أن يكون تاريخ التخفيض في المستقبل",
        "erreur_date_arret_future" to "لا يمكن أن يكون تاريخ التوقف في المستقبل",
        "erreur_date_arret_avant_reduction" to "يجب أن يكون تاريخ التوقف بعد التخفيض",
        "erreur_date_reussite_avant_arret" to "يجب أن يكون تاريخ النجاح بعد التوقف",
        "erreur_sauvegarde" to "خطأ في الحفظ",
        
        // Confirmations
        "confirmer_effacer_date" to "حذف التاريخ؟",
        "message_effacer_date" to "هل تريد حقاً حذف هذا التاريخ؟"
    )

    // ==================== हिन्दी ====================
    private val TRADUCTIONS_HI = mapOf(
        // Labels catégories
        "label_cigarettes" to "सिगरेट",
        "label_joints" to "जोइंट्स",
        "label_alcool_global" to "वैश्विक शराब",
        "label_bieres" to "बीयर",
        "label_liqueurs" to "शराब",
        "label_alcool_fort" to "मजबूत शराब",
        
        // Zone Habitudes
        "titre_zone_habitudes" to "आदतें क्षेत्र",
        "hint_max_cigarettes" to "अधिकतम सिगरेट प्रति दिन",
        "hint_max_joints" to "अधिकतम जोइंट्स प्रति दिन",
        "hint_max_alcool_global" to "अधिकतम वैश्विक शराब प्रति दिन",
        "hint_max_bieres" to "अधिकतम बीयर प्रति दिन",
        "hint_max_liqueurs" to "अधिकतम शराब प्रति दिन",
        "hint_max_alcool_fort" to "अधिकतम मजबूत शराब प्रति दिन",
        
        // Zone Volonté
        "titre_zone_volonte" to "इच्छाशक्ति क्षेत्र",
        "date_reduction" to "कमी की तारीख",
        "date_arret" to "बंद करने की तारीख",
        "date_reussite" to "सफलता की तारीख (अब धूम्रपान नहीं)",
        
        // Boutons
        "btn_sauvegarder" to "सहेजें",
        "selectionner_date" to "तारीख चुनें",
        "oui" to "हाँ",
        "non" to "नहीं",
        
        // Bandeau profil
        "profil_complet" to "पूर्ण प्रोफ़ाइल",
        "profil_incomplet" to "अधूरी प्रोफ़ाइल",
        "total_aujourdhui" to "आज का कुल",
        
        // Messages succès
        "date_sauvegardee" to "तारीख सहेजी गई",
        "date_effacee" to "तारीख हटाई गई",
        "habitudes_sauvegardees" to "आदतें सफलतापूर्वक सहेजी गईं",
        
        // Messages erreurs
        "erreur_valeurs_negatives" to "मान ऋणात्मक नहीं हो सकते",
        "erreur_valeurs_trop_elevees" to "मान 1000 से कम होने चाहिए",
        "erreur_date_reduction_future" to "कमी की तारीख भविष्य में नहीं हो सकती",
        "erreur_date_arret_future" to "बंद करने की तारीख भविष्य में नहीं हो सकती",
        "erreur_date_arret_avant_reduction" to "बंद करने की तारीख कमी के बाद होनी चाहिए",
        "erreur_date_reussite_avant_arret" to "सफलता की तारीख बंद करने के बाद होनी चाहिए",
        "erreur_sauvegarde" to "सहेजने में त्रुटि",
        
        // Confirmations
        "confirmer_effacer_date" to "तारीख हटाएं?",
        "message_effacer_date" to "क्या आप वाकई इस तारीख को हटाना चाहते हैं?"
    )

    // ==================== 日本語 ====================
    private val TRADUCTIONS_JA = mapOf(
        // Labels catégories
        "label_cigarettes" to "タバコ",
        "label_joints" to "ジョイント",
        "label_alcool_global" to "グローバルアルコール",
        "label_bieres" to "ビール",
        "label_liqueurs" to "リキュール",
        "label_alcool_fort" to "強いアルコール",
        
        // Zone Habitudes
        "titre_zone_habitudes" to "習慣ゾーン",
        "hint_max_cigarettes" to "1日の最大タバコ数",
        "hint_max_joints" to "1日の最大ジョイント数",
        "hint_max_alcool_global" to "1日の最大グローバルアルコール",
        "hint_max_bieres" to "1日の最大ビール数",
        "hint_max_liqueurs" to "1日の最大リキュール数",
        "hint_max_alcool_fort" to "1日の最大強いアルコール数",
        
        // Zone Volonté
        "titre_zone_volonte" to "意志力ゾーン",
        "date_reduction" to "削減日",
        "date_arret" to "禁煙日",
        "date_reussite" to "成功日（もう吸いません）",
        
        // Boutons
        "btn_sauvegarder" to "保存",
        "selectionner_date" to "日付を選択",
        "oui" to "はい",
        "non" to "いいえ",
        
        // Bandeau profil
        "profil_complet" to "完全なプロフィール",
        "profil_incomplet" to "不完全なプロフィール",
        "total_aujourdhui" to "今日の合計",
        
        // Messages succès
        "date_sauvegardee" to "日付が保存されました",
        "date_effacee" to "日付が削除されました",
        "habitudes_sauvegardees" to "習慣が正常に保存されました",
        
        // Messages erreurs
        "erreur_valeurs_negatives" to "値は負にできません",
        "erreur_valeurs_trop_elevees" to "値は1000未満である必要があります",
        "erreur_date_reduction_future" to "削減日は未来にはできません",
        "erreur_date_arret_future" to "禁煙日は未来にはできません",
        "erreur_date_arret_avant_reduction" to "禁煙日は削減日の後である必要があります",
        "erreur_date_reussite_avant_arret" to "成功日は禁煙日の後である必要があります",
        "erreur_sauvegarde" to "保存エラー",
        
        // Confirmations
        "confirmer_effacer_date" to "日付を削除しますか？",
        "message_effacer_date" to "本当にこの日付を削除しますか？"
    )
}

// ══════════════════════════════════════════════════════════════════════
// FIN HabitudesLangues.kt
// Total: ~700 lignes
// 
// Contenu:
// - 10 langues complètes : FR, EN, ES, PT, DE, IT, RU, AR, HI, JA
// - Labels catégories (6)
// - Zone Habitudes : titre + 6 hints
// - Zone Volonté : titre + 3 types de dates
// - Boutons : sauvegarder, sélectionner, oui/non
// - Bandeau : profil complet/incomplet, total jour
// - Messages succès : dates et habitudes sauvegardées
// - Messages erreurs : validation dates et valeurs
// - Confirmations : effacement dates
// 
// Utilisation:
// val trad = HabitudesLangues.getTraductions("FR")
// button.text = trad["btn_sauvegarder"]
// ══════════════════════════════════════════════════════════════════════