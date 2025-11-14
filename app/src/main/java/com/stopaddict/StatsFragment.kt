package com.stopaddict

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import org.json.JSONObject

class StatsFragment : Fragment() {

    companion object {
        private const val TAG = "StatsFragment"
        
        // Périodes
        private const val PERIODE_JOUR = "jour"
        private const val PERIODE_SEMAINE = "semaine"
        private const val PERIODE_MOIS = "mois"
        private const val PERIODE_ANNEE = "annee"
        
        // Couleurs graphiques (Material Design)
        private const val COLOR_CIGARETTES = Color.RED
        private const val COLOR_JOINTS = Color.GREEN
        private const val COLOR_ALCOOL_GLOBAL = Color.BLUE
        private const val COLOR_BIERES = Color.YELLOW
        private const val COLOR_LIQUEURS = Color.MAGENTA
        private const val COLOR_ALCOOL_FORT = Color.CYAN
        private const val COLOR_LIMITE = Color.GRAY
        private const val COLOR_COUTS = Color.RED
        private const val COLOR_ECONOMIES = Color.GREEN
    }

    // Database et config
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var configLangue: ConfigLangue

    // UI Elements - Graphiques
    private lateinit var chartConsommation: LineChart
    private lateinit var chartCouts: LineChart

    // UI Elements - Boutons période
    private lateinit var btnJour: Button
    private lateinit var btnSemaine: Button
    private lateinit var btnMois: Button
    private lateinit var btnAnnee: Button

    // UI Elements - Zone calculs
    private lateinit var txtCalculsJour: TextView
    private lateinit var txtCalculsSemaine: TextView
    private lateinit var txtCalculsMois: TextView
    private lateinit var txtCalculsAnnee: TextView

    // UI Elements - Bandeau profil
    private lateinit var txtProfilComplet: TextView
    private lateinit var txtTotalAujourdhui: TextView

    // Période active
    private var periodeActive = PERIODE_JOUR

    // Catégories actives
    private var categoriesActives = mutableMapOf(
        DatabaseHelper.TYPE_CIGARETTE to true,
        DatabaseHelper.TYPE_JOINT to true,
        DatabaseHelper.TYPE_ALCOOL_GLOBAL to true,
        DatabaseHelper.TYPE_BIERE to false,
        DatabaseHelper.TYPE_LIQUEUR to false,
        DatabaseHelper.TYPE_ALCOOL_FORT to false
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stats, container, false)

        try {
            // Initialisation database et config
            dbHelper = DatabaseHelper(requireContext())
            configLangue = ConfigLangue(requireContext())

            // Initialisation des vues
            initViews(view)

            // Chargement catégories actives
            loadCategoriesActives()

            // Configuration des listeners
            setupListeners()

            // Affichage initial (période jour)
            updateGraphiques()
            updateCalculs()
            updateProfilStatus()

            Log.d(TAG, "StatsFragment initialisé avec succès")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur initialisation StatsFragment: ${e.message}")
            Toast.makeText(requireContext(), "Erreur chargement Stats", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun initViews(view: View) {
        try {
            // Graphiques
            chartConsommation = view.findViewById(R.id.stats_chart_consommation)
            chartCouts = view.findViewById(R.id.stats_chart_couts)

            // Boutons période
            btnJour = view.findViewById(R.id.stats_btn_jour)
            btnSemaine = view.findViewById(R.id.stats_btn_semaine)
            btnMois = view.findViewById(R.id.stats_btn_mois)
            btnAnnee = view.findViewById(R.id.stats_btn_annee)

            // Zone calculs
            txtCalculsJour = view.findViewById(R.id.stats_txt_calculs_jour)
            txtCalculsSemaine = view.findViewById(R.id.stats_txt_calculs_semaine)
            txtCalculsMois = view.findViewById(R.id.stats_txt_calculs_mois)
            txtCalculsAnnee = view.findViewById(R.id.stats_txt_calculs_annee)

            // Bandeau profil
            txtProfilComplet = view.findViewById(R.id.stats_txt_profil_complet)
            txtTotalAujourdhui = view.findViewById(R.id.stats_txt_total_aujourdhui)

            // Configuration graphiques
            configureChart(chartConsommation)
            configureChart(chartCouts)

            Log.d(TAG, "Vues initialisées avec succès")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur initialisation vues: ${e.message}")
            throw e
        }
    }

    private fun configureChart(chart: LineChart) {
        try {
            // Configuration générale
            chart.description.isEnabled = false
            chart.setTouchEnabled(true)
            chart.isDragEnabled = true
            chart.setScaleEnabled(true)
            chart.setPinchZoom(true)
            chart.setDrawGridBackground(false)

            // Axe X (horizontal)
            val xAxis = chart.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(true)
            xAxis.granularity = 1f
            xAxis.textColor = Color.BLACK

            // Axe Y gauche (vertical)
            val yAxisLeft = chart.axisLeft
            yAxisLeft.setDrawGridLines(true)
            yAxisLeft.granularity = 1f
            yAxisLeft.axisMinimum = 0f
            yAxisLeft.textColor = Color.BLACK

            // Axe Y droit (désactivé)
            chart.axisRight.isEnabled = false

            // Légende
            chart.legend.isEnabled = true
            chart.legend.textColor = Color.BLACK

            Log.d(TAG, "Graphique configuré")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur configuration graphique: ${e.message}")
        }
    }

    private fun loadCategoriesActives() {
        try {
            val json = dbHelper.getPreference("categories_actives", "{}")
            if (json.isNotEmpty()) {
                val jsonObj = JSONObject(json)
                categoriesActives[DatabaseHelper.TYPE_CIGARETTE] = jsonObj.optBoolean("cigarette", true)
                categoriesActives[DatabaseHelper.TYPE_JOINT] = jsonObj.optBoolean("joint", true)
                categoriesActives[DatabaseHelper.TYPE_ALCOOL_GLOBAL] = jsonObj.optBoolean("alcool_global", true)
                categoriesActives[DatabaseHelper.TYPE_BIERE] = jsonObj.optBoolean("biere", false)
                categoriesActives[DatabaseHelper.TYPE_LIQUEUR] = jsonObj.optBoolean("liqueur", false)
                categoriesActives[DatabaseHelper.TYPE_ALCOOL_FORT] = jsonObj.optBoolean("alcool_fort", false)
            }
            Log.d(TAG, "Catégories actives chargées: $categoriesActives")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur chargement catégories actives: ${e.message}")
        }
    }

    private fun setupListeners() {
        try {
            btnJour.setOnClickListener {
                periodeActive = PERIODE_JOUR
                updateButtonsState()
                updateGraphiques()
                updateCalculs()
            }

            btnSemaine.setOnClickListener {
                periodeActive = PERIODE_SEMAINE
                updateButtonsState()
                updateGraphiques()
                updateCalculs()
            }

            btnMois.setOnClickListener {
                periodeActive = PERIODE_MOIS
                updateButtonsState()
                updateGraphiques()
                updateCalculs()
            }

            btnAnnee.setOnClickListener {
                periodeActive = PERIODE_ANNEE
                updateButtonsState()
                updateGraphiques()
                updateCalculs()
            }

            Log.d(TAG, "Listeners configurés")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur configuration listeners: ${e.message}")
        }
    }

    private fun updateButtonsState() {
        try {
            // Réinitialiser tous les boutons
            btnJour.isEnabled = true
            btnSemaine.isEnabled = true
            btnMois.isEnabled = true
            btnAnnee.isEnabled = true

            // Désactiver bouton actif (visuel)
            when (periodeActive) {
                PERIODE_JOUR -> btnJour.isEnabled = false
                PERIODE_SEMAINE -> btnSemaine.isEnabled = false
                PERIODE_MOIS -> btnMois.isEnabled = false
                PERIODE_ANNEE -> btnAnnee.isEnabled = false
            }

            Log.d(TAG, "État boutons mis à jour: $periodeActive")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur mise à jour état boutons: ${e.message}")
        }
    }
    private fun updateGraphiques() {
        try {
            updateGraphiqueConsommation()
            updateGraphiqueCouts()
            Log.d(TAG, "Graphiques mis à jour pour période: $periodeActive")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur mise à jour graphiques: ${e.message}")
        }
    }

    private fun updateGraphiqueConsommation() {
        try {
            val dataSets = mutableListOf<LineDataSet>()
            
            // Récupérer les données selon période
            val donnees = when (periodeActive) {
                PERIODE_JOUR -> getConsommationsJourDispatch()
                PERIODE_SEMAINE -> dbHelper.getConsommationsSemaine()
                PERIODE_MOIS -> dbHelper.getConsommationsMois()
                PERIODE_ANNEE -> dbHelper.getConsommationsAnnee()
                else -> emptyMap()
            }

            // Créer dataset pour chaque catégorie active
            categoriesActives.forEach { (type, active) ->
                if (active) {
                    val values = donnees[type] ?: listOf()
                    if (values.isNotEmpty()) {
                        val entries = values.mapIndexed { index, value ->
                            Entry(index.toFloat(), value.toFloat())
                        }
                        
                        val dataSet = LineDataSet(entries, getLabelCategorie(type))
                        dataSet.color = getColorCategorie(type)
                        dataSet.setCircleColor(getColorCategorie(type))
                        dataSet.lineWidth = 2f
                        dataSet.circleRadius = 3f
                        dataSet.setDrawCircleHole(false)
                        dataSet.setDrawValues(true)
                        dataSet.valueTextSize = 9f
                        
                        dataSets.add(dataSet)
                    }
                }
            }

            // Ajouter lignes limites habitudes
            categoriesActives.forEach { (type, active) ->
                if (active) {
                    val maxHabitude = dbHelper.getMaxJournalier(type)
                    if (maxHabitude > 0) {
                        val values = donnees[type] ?: listOf()
                        val limiteEntries = values.indices.map { index ->
                            Entry(index.toFloat(), maxHabitude.toFloat())
                        }
                        
                        if (limiteEntries.isNotEmpty()) {
                            val limiteDataSet = LineDataSet(limiteEntries, "Limite ${getLabelCategorie(type)}")
                            limiteDataSet.color = COLOR_LIMITE
                            limiteDataSet.lineWidth = 1f
                            limiteDataSet.enableDashedLine(10f, 5f, 0f)
                            limiteDataSet.setDrawCircles(false)
                            limiteDataSet.setDrawValues(false)
                            
                            dataSets.add(limiteDataSet)
                        }
                    }
                }
            }

            // Appliquer au graphique
            if (dataSets.isNotEmpty()) {
                val lineData = LineData(dataSets as List<com.github.mikephil.charting.interfaces.datasets.ILineDataSet>)
                chartConsommation.data = lineData
                
                // Configurer labels X selon période
                chartConsommation.xAxis.valueFormatter = getXAxisFormatter()
                
                chartConsommation.invalidate()
                Log.d(TAG, "Graphique consommation mis à jour: ${dataSets.size} datasets")
            } else {
                chartConsommation.clear()
                Log.w(TAG, "Aucune donnée pour graphique consommation")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Erreur mise à jour graphique consommation: ${e.message}")
        }
    }

    /**
     * Dispatch horaire pour période JOUR selon logique Manuel:
     * Diviser nombre total par heures pour répartition uniforme
     * Exemple: 12 cigarettes sur 24h = 1 toutes les 2h
     */
    private fun getConsommationsJourDispatch(): Map<String, List<Int>> {
        return try {
            val result = mutableMapOf<String, MutableList<Int>>()
            val consosJour = dbHelper.getConsommationsJour()
            
            // 4 tranches horaires: 00-07, 07-14, 14-21, 21-00
            val nbTranches = 4
            
            categoriesActives.forEach { (type, active) ->
                if (active) {
                    val total = consosJour[type] ?: 0
                    val parTranche = if (total > 0) total / nbTranches else 0
                    val reste = if (total > 0) total % nbTranches else 0
                    
                    // Répartir uniformément
                    val values = MutableList(nbTranches) { parTranche }
                    // Ajouter le reste sur la première tranche
                    if (reste > 0) {
                        values[0] += reste
                    }
                    
                    result[type] = values
                }
            }
            
            Log.d(TAG, "Consommations jour dispatch calculées")
            result
        } catch (e: Exception) {
            Log.e(TAG, "Erreur dispatch consommations jour: ${e.message}")
            emptyMap()
        }
    }

    private fun getXAxisFormatter(): ValueFormatter {
        return object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return when (periodeActive) {
                    PERIODE_JOUR -> {
                        // 4 tranches: 00-07, 07-14, 14-21, 21-00
                        when (value.toInt()) {
                            0 -> "00-07"
                            1 -> "07-14"
                            2 -> "14-21"
                            3 -> "21-00"
                            else -> ""
                        }
                    }
                    PERIODE_SEMAINE -> {
                        // Jours de la semaine
                        val jours = arrayOf("Lun", "Mar", "Mer", "Jeu", "Ven", "Sam", "Dim")
                        if (value.toInt() in jours.indices) jours[value.toInt()] else ""
                    }
                    PERIODE_MOIS -> {
                        // Tranches: 1, 6, 11, 16, 21, 26, 31
                        when (value.toInt()) {
                            0 -> "1"
                            1 -> "6"
                            2 -> "11"
                            3 -> "16"
                            4 -> "21"
                            5 -> "26"
                            6 -> "31"
                            else -> ""
                        }
                    }
                    PERIODE_ANNEE -> {
                        // 12 mois
                        val mois = arrayOf("Jan", "Fév", "Mar", "Avr", "Mai", "Juin", "Juil", "Aoû", "Sep", "Oct", "Nov", "Déc")
                        if (value.toInt() in mois.indices) mois[value.toInt()] else ""
                    }
                    else -> value.toInt().toString()
                }
            }
        }
    }

    private fun getLabelCategorie(type: String): String {
        return when (type) {
            DatabaseHelper.TYPE_CIGARETTE -> "Cigarettes"
            DatabaseHelper.TYPE_JOINT -> "Joints"
            DatabaseHelper.TYPE_ALCOOL_GLOBAL -> "Alcool global"
            DatabaseHelper.TYPE_BIERE -> "Bières"
            DatabaseHelper.TYPE_LIQUEUR -> "Liqueurs"
            DatabaseHelper.TYPE_ALCOOL_FORT -> "Alcool fort"
            else -> type
        }
    }

    private fun getColorCategorie(type: String): Int {
        return when (type) {
            DatabaseHelper.TYPE_CIGARETTE -> COLOR_CIGARETTES
            DatabaseHelper.TYPE_JOINT -> COLOR_JOINTS
            DatabaseHelper.TYPE_ALCOOL_GLOBAL -> COLOR_ALCOOL_GLOBAL
            DatabaseHelper.TYPE_BIERE -> COLOR_BIERES
            DatabaseHelper.TYPE_LIQUEUR -> COLOR_LIQUEURS
            DatabaseHelper.TYPE_ALCOOL_FORT -> COLOR_ALCOOL_FORT
            else -> Color.BLACK
        }
    }
    private fun updateGraphiqueCouts() {
        try {
            val dataSets = mutableListOf<LineDataSet>()
            
            // Récupérer les données selon période
            val donnees = when (periodeActive) {
                PERIODE_JOUR -> getConsommationsJourDispatch()
                PERIODE_SEMAINE -> dbHelper.getConsommationsSemaine()
                PERIODE_MOIS -> dbHelper.getConsommationsMois()
                PERIODE_ANNEE -> dbHelper.getConsommationsAnnee()
                else -> emptyMap()
            }

            // Calculer coûts et économies
            val couts = calculerCouts(donnees)
            val economies = calculerEconomies(donnees)

            // Dataset coûts
            if (couts.isNotEmpty() && couts.any { it > 0 }) {
                val coutsEntries = couts.mapIndexed { index, value ->
                    Entry(index.toFloat(), value.toFloat())
                }
                
                val coutsDataSet = LineDataSet(coutsEntries, "Coûts")
                coutsDataSet.color = COLOR_COUTS
                coutsDataSet.setCircleColor(COLOR_COUTS)
                coutsDataSet.lineWidth = 2f
                coutsDataSet.circleRadius = 3f
                coutsDataSet.setDrawCircleHole(false)
                coutsDataSet.setDrawValues(true)
                coutsDataSet.valueTextSize = 9f
                
                dataSets.add(coutsDataSet)
            }

            // Dataset économies
            if (economies.isNotEmpty() && economies.any { it > 0 }) {
                val economiesEntries = economies.mapIndexed { index, value ->
                    Entry(index.toFloat(), value.toFloat())
                }
                
                val economiesDataSet = LineDataSet(economiesEntries, "Économies")
                economiesDataSet.color = COLOR_ECONOMIES
                economiesDataSet.setCircleColor(COLOR_ECONOMIES)
                economiesDataSet.lineWidth = 2f
                economiesDataSet.circleRadius = 3f
                economiesDataSet.setDrawCircleHole(false)
                economiesDataSet.setDrawValues(true)
                economiesDataSet.valueTextSize = 9f
                
                dataSets.add(economiesDataSet)
            }

            // Appliquer au graphique
            if (dataSets.isNotEmpty()) {
                val lineData = LineData(dataSets as List<com.github.mikephil.charting.interfaces.datasets.ILineDataSet>)
                chartCouts.data = lineData
                
                // Configurer labels X selon période
                chartCouts.xAxis.valueFormatter = getXAxisFormatter()
                
                chartCouts.invalidate()
                Log.d(TAG, "Graphique coûts mis à jour: ${dataSets.size} datasets")
            } else {
                // Garder graphique vide avec axes si aucune donnée
                chartCouts.clear()
                Log.w(TAG, "Aucune donnée pour graphique coûts")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Erreur mise à jour graphique coûts: ${e.message}")
        }
    }

    /**
     * Calcule les coûts réels par période
     */
    private fun calculerCouts(donnees: Map<String, List<Int>>): List<Double> {
        return try {
            val nbPoints = donnees.values.firstOrNull()?.size ?: 0
            if (nbPoints == 0) return emptyList()
            
            val couts = MutableList(nbPoints) { 0.0 }
            
            categoriesActives.forEach { (type, active) ->
                if (active) {
                    val values = donnees[type] ?: listOf()
                    val coutsType = dbHelper.getCouts(type)
                    
                    // Calcul prix unitaire
                    val prixUnitaire = calculerPrixUnitaire(type, coutsType)
                    
                    values.forEachIndexed { index, quantite ->
                        couts[index] += prixUnitaire * quantite
                    }
                }
            }
            
            Log.d(TAG, "Coûts calculés: ${couts.sum()}€ total")
            couts
        } catch (e: Exception) {
            Log.e(TAG, "Erreur calcul coûts: ${e.message}")
            emptyList()
        }
    }

    /**
     * Calcule les économies (si consommation < habitudes)
     */
    private fun calculerEconomies(donnees: Map<String, List<Int>>): List<Double> {
        return try {
            val nbPoints = donnees.values.firstOrNull()?.size ?: 0
            if (nbPoints == 0) return emptyList()
            
            val economies = MutableList(nbPoints) { 0.0 }
            
            categoriesActives.forEach { (type, active) ->
                if (active) {
                    val values = donnees[type] ?: listOf()
                    val maxHabitude = dbHelper.getMaxJournalier(type)
                    val coutsType = dbHelper.getCouts(type)
                    
                    if (maxHabitude > 0) {
                        val prixUnitaire = calculerPrixUnitaire(type, coutsType)
                        
                        values.forEachIndexed { index, quantite ->
                            // Économie = si consommation < habitude
                            if (quantite < maxHabitude) {
                                val diff = maxHabitude - quantite
                                economies[index] += prixUnitaire * diff
                            }
                        }
                    }
                }
            }
            
            Log.d(TAG, "Économies calculées: ${economies.sum()}€ total")
            economies
        } catch (e: Exception) {
            Log.e(TAG, "Erreur calcul économies: ${e.message}")
            emptyList()
        }
    }

    /**
     * Calcule le prix unitaire selon type de consommation
     */
    private fun calculerPrixUnitaire(type: String, couts: Map<String, Double>): Double {
        return try {
            when (type) {
                DatabaseHelper.TYPE_CIGARETTE -> {
                    // Prix paquet / nb cigarettes (simplifié, prend classiques par défaut)
                    val prixPaquet = couts["prix_paquet"] ?: 0.0
                    val nbCigarettes = couts["nb_cigarettes"] ?: 20.0
                    if (prixPaquet > 0 && nbCigarettes > 0) {
                        prixPaquet / nbCigarettes
                    } else {
                        0.0
                    }
                }
                DatabaseHelper.TYPE_JOINT -> {
                    // Prix gramme * gramme par joint
                    val prixGramme = couts["prix_gramme"] ?: 0.0
                    val grammeParJoint = couts["gramme_par_joint"] ?: 0.0
                    prixGramme * grammeParJoint
                }
                DatabaseHelper.TYPE_ALCOOL_GLOBAL,
                DatabaseHelper.TYPE_BIERE,
                DatabaseHelper.TYPE_LIQUEUR,
                DatabaseHelper.TYPE_ALCOOL_FORT -> {
                    // Prix verre
                    couts["prix_verre"] ?: 0.0
                }
                else -> 0.0
            }
        } catch (e: Exception) {
            Log.e(TAG, "Erreur calcul prix unitaire $type: ${e.message}")
            0.0
        }
    }

    private fun updateCalculs() {
        try {
            // Récupérer totaux pour chaque période
            val totauxJour = calculerTotauxPeriode(PERIODE_JOUR)
            val totauxSemaine = calculerTotauxPeriode(PERIODE_SEMAINE)
            val totauxMois = calculerTotauxPeriode(PERIODE_MOIS)
            val totauxAnnee = calculerTotauxPeriode(PERIODE_ANNEE)

            // Afficher
            txtCalculsJour.text = formatTotaux("Jour", totauxJour)
            txtCalculsSemaine.text = formatTotaux("Semaine", totauxSemaine)
            txtCalculsMois.text = formatTotaux("Mois", totauxMois)
            txtCalculsAnnee.text = formatTotaux("Année", totauxAnnee)

            Log.d(TAG, "Calculs mis à jour")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur mise à jour calculs: ${e.message}")
        }
    }

    private fun calculerTotauxPeriode(periode: String): Map<String, Any> {
        return try {
            val donnees = when (periode) {
                PERIODE_JOUR -> mapOf<String, List<Int>>().apply {
                    val consosJour = dbHelper.getConsommationsJour()
                    categoriesActives.forEach { (type, active) ->
                        if (active) {
                            (this as MutableMap)[type] = listOf(consosJour[type] ?: 0)
                        }
                    }
                }
                PERIODE_SEMAINE -> dbHelper.getConsommationsSemaine()
                PERIODE_MOIS -> dbHelper.getConsommationsMois()
                PERIODE_ANNEE -> dbHelper.getConsommationsAnnee()
                else -> emptyMap()
            }

            // Total consommations
            var totalConsos = 0
            donnees.forEach { (_, values) ->
                totalConsos += values.sum()
            }

            // Total coûts
            val couts = calculerCouts(donnees)
            val totalCouts = couts.sum()

            // Total économies
            val economies = calculerEconomies(donnees)
            val totalEconomies = economies.sum()

            mapOf(
                "consommations" to totalConsos,
                "couts" to totalCouts,
                "economies" to totalEconomies
            )
        } catch (e: Exception) {
            Log.e(TAG, "Erreur calcul totaux $periode: ${e.message}")
            mapOf(
                "consommations" to 0,
                "couts" to 0.0,
                "economies" to 0.0
            )
        }
    }

    private fun formatTotaux(periode: String, totaux: Map<String, Any>): String {
        return try {
            val devise = dbHelper.getPreference("devise", "€")
            val consos = totaux["consommations"] as? Int ?: 0
            val couts = totaux["couts"] as? Double ?: 0.0
            val economies = totaux["economies"] as? Double ?: 0.0

            "$periode: $consos unités | ${String.format("%.2f", couts)}$devise dépensés | ${String.format("%.2f", economies)}$devise économisés"
        } catch (e: Exception) {
            Log.e(TAG, "Erreur formatage totaux: ${e.message}")
            "$periode: Erreur"
        }
    }
    private fun updateProfilStatus() {
        try {
            // Vérifier si profil complet (coûts + habitudes + dates remplis)
            var isComplete = true

            // Vérifier coûts (au moins une catégorie active avec coût > 0)
            var hasCouts = false
            categoriesActives.forEach { (type, active) ->
                if (active) {
                    val couts = dbHelper.getCouts(type)
                    if (couts.values.any { it > 0 }) {
                        hasCouts = true
                    }
                }
            }
            if (!hasCouts) isComplete = false

            // Vérifier habitudes (au moins une catégorie active avec max > 0)
            var hasHabitudes = false
            categoriesActives.forEach { (type, active) ->
                if (active) {
                    val max = dbHelper.getMaxJournalier(type)
                    if (max > 0) {
                        hasHabitudes = true
                    }
                }
            }
            if (!hasHabitudes) isComplete = false

            // Vérifier dates (au moins une date renseignée)
            var hasDates = false
            categoriesActives.forEach { (type, active) ->
                if (active) {
                    val dates = dbHelper.getDatesObjectifs(type)
                    if (dates.values.any { !it.isNullOrEmpty() }) {
                        hasDates = true
                    }
                }
            }
            if (!hasDates) isComplete = false

            // Mise à jour texte
            txtProfilComplet.text = if (isComplete) "Profil: Complet ✓" else "Profil: Incomplet"

            // Total aujourd'hui
            val consosJour = dbHelper.getConsommationsJour()
            val totalJour = consosJour.values.sum()
            txtTotalAujourdhui.text = totalJour.toString()
            
            Log.d(TAG, "Profil: ${if (isComplete) "Complet" else "Incomplet"} - Total jour: $totalJour")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur mise à jour profil: ${e.message}")
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            // Recharger données (synchro si modif depuis autre onglet)
            loadCategoriesActives()
            updateGraphiques()
            updateCalculs()
            updateProfilStatus()
            Log.d(TAG, "Fragment resumed - données rechargées")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur onResume: ${e.message}")
        }
    }

    override fun onPause() {
        super.onPause()
        try {
            Log.d(TAG, "Fragment paused")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur onPause: ${e.message}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        try {
            // Cleanup graphiques
            chartConsommation.clear()
            chartCouts.clear()
            Log.d(TAG, "Fragment détruit - graphiques nettoyés")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur onDestroyView: ${e.message}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            Log.d(TAG, "Fragment destroyed")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur onDestroy: ${e.message}")
        }
    }

    /**
     * Fonction appelée depuis MainActivity ou autres fragments
     * pour rafraîchir les données (synchro live)
     */
    fun refreshData() {
        try {
            loadCategoriesActives()
            updateGraphiques()
            updateCalculs()
            updateProfilStatus()
            Log.d(TAG, "Données rafraîchies manuellement")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur refresh data: ${e.message}")
        }
    }

    /**
     * Change la période affichée (utile pour tests ou actions externes)
     */
    fun setPeriode(periode: String) {
        try {
            if (periode in listOf(PERIODE_JOUR, PERIODE_SEMAINE, PERIODE_MOIS, PERIODE_ANNEE)) {
                periodeActive = periode
                updateButtonsState()
                updateGraphiques()
                updateCalculs()
                Log.d(TAG, "Période changée: $periode")
            } else {
                Log.w(TAG, "Période invalide: $periode")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Erreur changement période: ${e.message}")
        }
    }
}
