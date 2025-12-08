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

        // Préférences liées aux cigarettes
        private const val PREF_MODE_CIGARETTE = "mode_cigarette"
        private const val PREF_NB_CIGARETTES_ROULEES = "nb_cigarettes_roulees"
        private const val PREF_NB_CIGARETTES_TUBEES = "nb_cigarettes_tubees"

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
    private lateinit var trad: Map<String, String>

    // UI Elements - Graphiques
    private lateinit var chartConsommation: LineChart
    private lateinit var chartCouts: LineChart

    // UI Elements - Boutons période
    private lateinit var btnJour: Button
    private lateinit var btnSemaine: Button
    private lateinit var btnMois: Button
    private lateinit var btnAnnee: Button

    // UI Elements - Zone titres graphiques
    private lateinit var txtTitreConso: TextView
    private lateinit var txtTitreCouts: TextView

    // UI Elements - Bandeau profil
    private lateinit var txtProfilComplet: TextView
    private lateinit var txtTotalAujourdhui: TextView

    // UI Elements - Tableau résumé (labels colonnes)
    private lateinit var headerColJour: TextView
    private lateinit var headerColSemaine: TextView
    private lateinit var headerColMois: TextView
    private lateinit var headerColAnnee: TextView

    // UI Elements - Tableau résumé (labels lignes)
    private lateinit var rowLabelCigarettes: TextView
    private lateinit var rowLabelJoints: TextView
    private lateinit var rowLabelAlcoolGlobal: TextView
    private lateinit var rowLabelBieres: TextView
    private lateinit var rowLabelLiqueurs: TextView
    private lateinit var rowLabelAlcoolFort: TextView
    private lateinit var rowLabelEconomies: TextView
    private lateinit var rowLabelDepenses: TextView

    // UI Elements - Tableau résumé (cellules)
    private lateinit var cellCigJour: TextView
    private lateinit var cellCigSemaine: TextView
    private lateinit var cellCigMois: TextView
    private lateinit var cellCigAnnee: TextView

    private lateinit var cellJointJour: TextView
    private lateinit var cellJointSemaine: TextView
    private lateinit var cellJointMois: TextView
    private lateinit var cellJointAnnee: TextView

    private lateinit var cellAlcoolGlobalJour: TextView
    private lateinit var cellAlcoolGlobalSemaine: TextView
    private lateinit var cellAlcoolGlobalMois: TextView
    private lateinit var cellAlcoolGlobalAnnee: TextView

    private lateinit var cellBiereJour: TextView
    private lateinit var cellBiereSemaine: TextView
    private lateinit var cellBiereMois: TextView
    private lateinit var cellBiereAnnee: TextView

    private lateinit var cellLiqueurJour: TextView
    private lateinit var cellLiqueurSemaine: TextView
    private lateinit var cellLiqueurMois: TextView
    private lateinit var cellLiqueurAnnee: TextView

    private lateinit var cellAlcoolFortJour: TextView
    private lateinit var cellAlcoolFortSemaine: TextView
    private lateinit var cellAlcoolFortMois: TextView
    private lateinit var cellAlcoolFortAnnee: TextView

    private lateinit var cellEconomiesJour: TextView
    private lateinit var cellEconomiesSemaine: TextView
    private lateinit var cellEconomiesMois: TextView
    private lateinit var cellEconomiesAnnee: TextView

    private lateinit var cellDepensesJour: TextView
    private lateinit var cellDepensesSemaine: TextView
    private lateinit var cellDepensesMois: TextView
    private lateinit var cellDepensesAnnee: TextView

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
            trad = StatsLangues.getTraductions(configLangue.getLangue())

            // Initialisation des vues
            initViews(view)

            // Chargement catégories actives
            loadCategoriesActives()

            // Configuration des listeners
            setupListeners()

            // Affichage initial (période jour)
            updateGraphiques()
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

            // Appliquer traductions aux boutons
            btnJour.text = trad["btn_jour"] ?: "Jour"
            btnSemaine.text = trad["btn_semaine"] ?: "Semaine"
            btnMois.text = trad["btn_mois"] ?: "Mois"
            btnAnnee.text = trad["btn_annee"] ?: "Année"

            // Titres graphiques
            txtTitreConso = view.findViewById(R.id.stats_txt_titre_conso)
            txtTitreCouts = view.findViewById(R.id.stats_txt_titre_couts)
            txtTitreConso.text = trad["titre_graphique_consommation"] ?: "Graphique Consommation"
            txtTitreCouts.text = trad["titre_graphique_couts"] ?: "Graphique Coûts et Économies"

            // En-têtes colonnes tableau
            headerColJour = view.findViewById(R.id.stats_header_jour)
            headerColSemaine = view.findViewById(R.id.stats_header_semaine)
            headerColMois = view.findViewById(R.id.stats_header_mois)
            headerColAnnee = view.findViewById(R.id.stats_header_annee)

            headerColJour.text =
                trad["calculs_periode_jour"] ?: trad["btn_jour"] ?: "Jour"
            headerColSemaine.text =
                trad["calculs_periode_semaine"] ?: trad["btn_semaine"] ?: "Semaine"
            headerColMois.text =
                trad["calculs_periode_mois"] ?: trad["btn_mois"] ?: "Mois"
            headerColAnnee.text =
                trad["calculs_periode_annee"] ?: trad["btn_annee"] ?: "Année"

            // Labels lignes tableau
            rowLabelCigarettes = view.findViewById(R.id.stats_row_label_cigarettes)
            rowLabelJoints = view.findViewById(R.id.stats_row_label_joints)
            rowLabelAlcoolGlobal = view.findViewById(R.id.stats_row_label_alcool_global)
            rowLabelBieres = view.findViewById(R.id.stats_row_label_bieres)
            rowLabelLiqueurs = view.findViewById(R.id.stats_row_label_liqueurs)
            rowLabelAlcoolFort = view.findViewById(R.id.stats_row_label_alcool_fort)
            rowLabelEconomies = view.findViewById(R.id.stats_row_label_economies)
            rowLabelDepenses = view.findViewById(R.id.stats_row_label_depenses)

            rowLabelCigarettes.text = trad["label_cigarettes"] ?: "Cigarettes"
            rowLabelJoints.text = trad["label_joints"] ?: "Joints"
            rowLabelAlcoolGlobal.text = trad["label_alcool_global"] ?: "Alcool global"
            rowLabelBieres.text = trad["label_bieres"] ?: "Bières"
            rowLabelLiqueurs.text = trad["label_liqueurs"] ?: "Liqueurs"
            rowLabelAlcoolFort.text = trad["label_alcool_fort"] ?: "Alcool fort"
            rowLabelEconomies.text = trad["label_economies"] ?: "Économies"
            rowLabelDepenses.text = trad["label_depenses"] ?: "Dépenses"

            // Cellules tableau
            cellCigJour = view.findViewById(R.id.stats_cigarettes_jour)
            cellCigSemaine = view.findViewById(R.id.stats_cigarettes_semaine)
            cellCigMois = view.findViewById(R.id.stats_cigarettes_mois)
            cellCigAnnee = view.findViewById(R.id.stats_cigarettes_annee)

            cellJointJour = view.findViewById(R.id.stats_joints_jour)
            cellJointSemaine = view.findViewById(R.id.stats_joints_semaine)
            cellJointMois = view.findViewById(R.id.stats_joints_mois)
            cellJointAnnee = view.findViewById(R.id.stats_joints_annee)

            cellAlcoolGlobalJour = view.findViewById(R.id.stats_alcool_global_jour)
            cellAlcoolGlobalSemaine = view.findViewById(R.id.stats_alcool_global_semaine)
            cellAlcoolGlobalMois = view.findViewById(R.id.stats_alcool_global_mois)
            cellAlcoolGlobalAnnee = view.findViewById(R.id.stats_alcool_global_annee)

            cellBiereJour = view.findViewById(R.id.stats_bieres_jour)
            cellBiereSemaine = view.findViewById(R.id.stats_bieres_semaine)
            cellBiereMois = view.findViewById(R.id.stats_bieres_mois)
            cellBiereAnnee = view.findViewById(R.id.stats_bieres_annee)

            cellLiqueurJour = view.findViewById(R.id.stats_liqueurs_jour)
            cellLiqueurSemaine = view.findViewById(R.id.stats_liqueurs_semaine)
            cellLiqueurMois = view.findViewById(R.id.stats_liqueurs_mois)
            cellLiqueurAnnee = view.findViewById(R.id.stats_liqueurs_annee)

            cellAlcoolFortJour = view.findViewById(R.id.stats_alcool_fort_jour)
            cellAlcoolFortSemaine = view.findViewById(R.id.stats_alcool_fort_semaine)
            cellAlcoolFortMois = view.findViewById(R.id.stats_alcool_fort_mois)
            cellAlcoolFortAnnee = view.findViewById(R.id.stats_alcool_fort_annee)

            cellEconomiesJour = view.findViewById(R.id.stats_economies_jour)
            cellEconomiesSemaine = view.findViewById(R.id.stats_economies_semaine)
            cellEconomiesMois = view.findViewById(R.id.stats_economies_mois)
            cellEconomiesAnnee = view.findViewById(R.id.stats_economies_annee)

            cellDepensesJour = view.findViewById(R.id.stats_depenses_jour)
            cellDepensesSemaine = view.findViewById(R.id.stats_depenses_semaine)
            cellDepensesMois = view.findViewById(R.id.stats_depenses_mois)
            cellDepensesAnnee = view.findViewById(R.id.stats_depenses_annee)

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
            chart.description.isEnabled = false
            chart.setNoDataText(trad["aucune_donnee"] ?: "No chart data available")
            chart.setTouchEnabled(true)
            chart.isDragEnabled = true
            chart.setScaleEnabled(true)
            chart.setPinchZoom(true)
            chart.setDrawGridBackground(false)

            val xAxis = chart.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(true)
            xAxis.granularity = 1f
            xAxis.textColor = Color.BLACK

            val yAxisLeft = chart.axisLeft
            yAxisLeft.setDrawGridLines(true)
            yAxisLeft.granularity = 1f
            yAxisLeft.axisMinimum = 0f
            yAxisLeft.textColor = Color.BLACK

            chart.axisRight.isEnabled = false

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
                categoriesActives[DatabaseHelper.TYPE_CIGARETTE] =
                    jsonObj.optBoolean("cigarette", true)
                categoriesActives[DatabaseHelper.TYPE_JOINT] =
                    jsonObj.optBoolean("joint", true)
                categoriesActives[DatabaseHelper.TYPE_ALCOOL_GLOBAL] =
                    jsonObj.optBoolean("alcool_global", true)
                categoriesActives[DatabaseHelper.TYPE_BIERE] =
                    jsonObj.optBoolean("biere", false)
                categoriesActives[DatabaseHelper.TYPE_LIQUEUR] =
                    jsonObj.optBoolean("liqueur", false)
                categoriesActives[DatabaseHelper.TYPE_ALCOOL_FORT] =
                    jsonObj.optBoolean("alcool_fort", false)
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
            }

            btnSemaine.setOnClickListener {
                periodeActive = PERIODE_SEMAINE
                updateButtonsState()
                updateGraphiques()
            }

            btnMois.setOnClickListener {
                periodeActive = PERIODE_MOIS
                updateButtonsState()
                updateGraphiques()
            }

            btnAnnee.setOnClickListener {
                periodeActive = PERIODE_ANNEE
                updateButtonsState()
                updateGraphiques()
            }

            Log.d(TAG, "Listeners configurés")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur configuration listeners: ${e.message}")
        }
    }

    private fun updateButtonsState() {
        try {
            btnJour.isEnabled = true
            btnSemaine.isEnabled = true
            btnMois.isEnabled = true
            btnAnnee.isEnabled = true

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
            updateResumeTable()
            Log.d(TAG, "Graphiques + tableau mis à jour pour période: $periodeActive")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur mise à jour graphiques: ${e.message}")
        }
    }

    private fun updateGraphiqueConsommation() {
        try {
            val dataSets = mutableListOf<LineDataSet>()

            val donnees = when (periodeActive) {
                PERIODE_JOUR -> getConsommationsJourDispatch()
                PERIODE_SEMAINE -> dbHelper.getConsommationsSemaine()
                PERIODE_MOIS -> dbHelper.getConsommationsMois()
                PERIODE_ANNEE -> dbHelper.getConsommationsAnnee()
                else -> emptyMap()
            }

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

            categoriesActives.forEach { (type, active) ->
                if (active) {
                    val maxHabitude = dbHelper.getMaxJournalier(type)
                    if (maxHabitude > 0) {
                        val values = donnees[type] ?: listOf()
                        val limiteEntries = values.indices.map { index ->
                            Entry(index.toFloat(), maxHabitude.toFloat())
                        }

                        if (limiteEntries.isNotEmpty()) {
                            val limiteDataSet = LineDataSet(
                                limiteEntries,
                                "Limite ${getLabelCategorie(type)}"
                            )
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

            if (dataSets.isNotEmpty()) {
                val lineData = LineData(dataSets as List<com.github.mikephil.charting.interfaces.datasets.ILineDataSet>)
                chartConsommation.data = lineData
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
     * Dispatch horaire pour période JOUR
     */
    private fun getConsommationsJourDispatch(): Map<String, List<Int>> {
        return try {
            val result = mutableMapOf<String, MutableList<Int>>()
            val consosJour = dbHelper.getConsommationsJour()
            val nbTranches = 4

            categoriesActives.forEach { (type, active) ->
                if (active) {
                    val total = consosJour[type] ?: 0
                    val parTranche = if (total > 0) total / nbTranches else 0
                    val reste = if (total > 0) total % nbTranches else 0

                    val values = MutableList(nbTranches) { parTranche }
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
                        when (value.toInt()) {
                            0 -> "00-07"
                            1 -> "07-14"
                            2 -> "14-21"
                            3 -> "21-00"
                            else -> ""
                        }
                    }
                    PERIODE_SEMAINE -> {
                        val jours = arrayOf("Lun", "Mar", "Mer", "Jeu", "Ven", "Sam", "Dim")
                        if (value.toInt() in jours.indices) jours[value.toInt()] else ""
                    }
                    PERIODE_MOIS -> {
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
                        val mois = arrayOf(
                            "Jan", "Fév", "Mar", "Avr", "Mai", "Juin",
                            "Juil", "Aoû", "Sep", "Oct", "Nov", "Déc"
                        )
                        if (value.toInt() in mois.indices) mois[value.toInt()] else ""
                    }
                    else -> value.toInt().toString()
                }
            }
        }
    }

    private fun getLabelCategorie(type: String): String {
        return when (type) {
            DatabaseHelper.TYPE_CIGARETTE -> trad["label_cigarettes"] ?: "Cigarettes"
            DatabaseHelper.TYPE_JOINT -> trad["label_joints"] ?: "Joints"
            DatabaseHelper.TYPE_ALCOOL_GLOBAL -> trad["label_alcool_global"] ?: "Alcool global"
            DatabaseHelper.TYPE_BIERE -> trad["label_bieres"] ?: "Bières"
            DatabaseHelper.TYPE_LIQUEUR -> trad["label_liqueurs"] ?: "Liqueurs"
            DatabaseHelper.TYPE_ALCOOL_FORT -> trad["label_alcool_fort"] ?: "Alcool fort"
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

            val donnees = when (periodeActive) {
                PERIODE_JOUR -> getConsommationsJourDispatch()
                PERIODE_SEMAINE -> dbHelper.getConsommationsSemaine()
                PERIODE_MOIS -> dbHelper.getConsommationsMois()
                PERIODE_ANNEE -> dbHelper.getConsommationsAnnee()
                else -> emptyMap()
            }

            val couts = calculerCouts(donnees)
            val economies = calculerEconomies(donnees)

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

            if (dataSets.isNotEmpty()) {
                val lineData = LineData(dataSets as List<com.github.mikephil.charting.interfaces.datasets.ILineDataSet>)
                chartCouts.data = lineData
                chartCouts.xAxis.valueFormatter = getXAxisFormatter()
                chartCouts.invalidate()
                Log.d(TAG, "Graphique coûts mis à jour: ${dataSets.size} datasets")
            } else {
                chartCouts.clear()
                Log.w(TAG, "Aucune donnée pour graphique coûts")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Erreur mise à jour graphique coûts: ${e.message}")
        }
    }

    private fun calculerCouts(donnees: Map<String, List<Int>>): List<Double> {
        return try {
            val nbPoints = donnees.values.firstOrNull()?.size ?: 0
            if (nbPoints == 0) return emptyList()

            val couts = MutableList(nbPoints) { 0.0 }

            categoriesActives.forEach { (type, active) ->
                if (active) {
                    val values = donnees[type] ?: listOf()
                    val coutsType = dbHelper.getCouts(type)
                    val prixUnitaire = calculerPrixUnitaire(type, coutsType)

                    values.forEachIndexed { index, quantite ->
                        couts[index] += prixUnitaire * quantite
                    }
                }
            }

            Log.d(TAG, "Coûts calculés: ${couts.sum()} ${getDeviseSymbol()} total")
            couts
        } catch (e: Exception) {
            Log.e(TAG, "Erreur calcul coûts: ${e.message}")
            emptyList()
        }
    }

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
                            if (quantite < maxHabitude) {
                                val diff = maxHabitude - quantite
                                economies[index] += prixUnitaire * diff
                            }
                        }
                    }
                }
            }

            Log.d(TAG, "Économies calculées: ${economies.sum()} ${getDeviseSymbol()} total")
            economies
        } catch (e: Exception) {
            Log.e(TAG, "Erreur calcul économies: ${e.message}")
            emptyList()
        }
    }

        private fun calculerPrixUnitaire(type: String, couts: Map<String, Double>): Double {
        return try {
            when (type) {
                DatabaseHelper.TYPE_CIGARETTE -> {
                    // On adapte le calcul en fonction du mode choisi dans les réglages
                    val modeCig = dbHelper.getPreference(PREF_MODE_CIGARETTE, "classique")

                    when (modeCig) {
                        "rouler" -> {
                            val prixTabac = couts["prix_tabac"] ?: 0.0
                            val prixFeuilles = couts["prix_feuilles"] ?: 0.0
                            val nbFeuilles = couts["nb_feuilles"] ?: 0.0
                            val prixFiltres = couts["prix_filtres"] ?: 0.0
                            val nbFiltres = couts["nb_filtres"] ?: 0.0

                            // Nouveau : nombre de cigarettes réellement fabriquées (préférences)
                            val nbCigRouleesStr =
                                dbHelper.getPreference(PREF_NB_CIGARETTES_ROULEES, "0")
                            val nbCigRoulees =
                                nbCigRouleesStr.replace(",", ".").toDoubleOrNull() ?: 0.0

                            // Si l'utilisateur a renseigné le nombre de cigarettes fabriquées
                            val coutTotal = prixTabac + prixFeuilles + prixFiltres
                            if (coutTotal > 0 && nbCigRoulees > 0) {
                                coutTotal / nbCigRoulees
                            } else {
                                // ⚠️ Fallback : ancien calcul au cas où l'utilisateur n'a rien mis
                                val nbCigarettes = couts["nb_cigarettes"] ?: 0.0
                                val coutTabac =
                                    if (prixTabac > 0 && nbCigarettes > 0) prixTabac / nbCigarettes else 0.0
                                val coutFeuilles =
                                    if (prixFeuilles > 0 && nbFeuilles > 0) prixFeuilles / nbFeuilles else 0.0
                                val coutFiltres =
                                    if (prixFiltres > 0 && nbFiltres > 0) prixFiltres / nbFiltres else 0.0

                                val total = coutTabac + coutFeuilles + coutFiltres
                                if (total > 0) total else 0.0
                            }
                        }

                        "tuber" -> {
                            val prixTabacTubes = couts["prix_tabac_tube"] ?: 0.0
                            val prixTubes = couts["prix_tubes"] ?: 0.0
                            val nbTubes = couts["nb_tubes"] ?: 0.0

                            // Nouveau : nombre de cigarettes réellement fabriquées (préférences)
                            val nbCigTubeesStr =
                                dbHelper.getPreference(PREF_NB_CIGARETTES_TUBEES, "0")
                            val nbCigTubees =
                                nbCigTubeesStr.replace(",", ".").toDoubleOrNull() ?: 0.0

                            val coutTotal = prixTabacTubes + prixTubes
                            if (coutTotal > 0 && nbCigTubees > 0) {
                                coutTotal / nbCigTubees
                            } else {
                                // ⚠️ Fallback : ancien calcul avec nb_cigarettes + nb_tubes
                                val nbCigarettes = couts["nb_cigarettes"] ?: 0.0
                                val coutTabac =
                                    if (prixTabacTubes > 0 && nbCigarettes > 0) prixTabacTubes / nbCigarettes else 0.0
                                val coutTubes =
                                    if (prixTubes > 0 && nbTubes > 0) prixTubes / nbTubes else 0.0

                                val total = coutTabac + coutTubes
                                if (total > 0) total else 0.0
                            }
                        }

                        else -> {
                            // Mode "classique" (paquet standard)
                            val prixPaquet = couts["prix_paquet"] ?: 0.0
                            val nbCigarettes = couts["nb_cigarettes"] ?: 0.0
                            if (prixPaquet > 0 && nbCigarettes > 0) prixPaquet / nbCigarettes else 0.0
                        }
                    }
                }

                DatabaseHelper.TYPE_JOINT -> {
                    // Prix du gramme de produit (stocké dans la table couts)
                    val prixGramme = couts["prix_gramme"] ?: 0.0

                    // Grammes par joint : stocké dans les préférences (et pas dans la table couts)
                    val grammeParJointStr = dbHelper.getPreference("gramme_par_joint", "0")
                    val grammeParJoint =
                        grammeParJointStr.replace(",", ".").toDoubleOrNull() ?: 0.0

                    // Optionnel : prise en compte des feuilles longues si tu les utilises pour les joints
                    val prixFeuillesJoint = couts["prix_feuilles"] ?: 0.0
                    val nbFeuillesJoint = couts["nb_feuilles"] ?: 0.0
                    val coutFeuilleParJoint =
                        if (prixFeuillesJoint > 0 && nbFeuillesJoint > 0) prixFeuillesJoint / nbFeuillesJoint else 0.0

                    if (prixGramme <= 0 || grammeParJoint <= 0) {
                        0.0
                    } else {
                        // Coût = produit + éventuellement une feuille par joint
                        (prixGramme * grammeParJoint) + coutFeuilleParJoint
                    }
                }

                DatabaseHelper.TYPE_ALCOOL_GLOBAL,
                DatabaseHelper.TYPE_BIERE,
                DatabaseHelper.TYPE_LIQUEUR,
                DatabaseHelper.TYPE_ALCOOL_FORT -> {
                    couts["prix_verre"] ?: 0.0
                }

                else -> 0.0
            }
        } catch (e: Exception) {
            Log.e(TAG, "Erreur calcul prix unitaire $type: ${e.message}")
            0.0
        }
    }
        
    private fun getDeviseSymbol(): String {
        val code = dbHelper.getPreference("devise", "EUR")
        return when (code) {
            "EUR" -> "€"
            "USD" -> "$"
            "GBP" -> "£"
            "JPY" -> "¥"
            "CHF" -> "CHF"
            "CAD" -> "C$"
            "AUD" -> "A$"
            "BRL" -> "R$"
            "INR" -> "₹"
            "RUB" -> "₽"
            else -> code
        }
    }

    /**
     * Remplit le tableau résumé en haut (Cigs/Joints/Alcool... x Jour/Semaine/Mois/Année)
     */
    private fun updateResumeTable() {
        try {
            // --- Consommations brutes par période ---
            val consosJour = dbHelper.getConsommationsJour()
            val consosSemaine = dbHelper.getConsommationsSemaine()
            val consosMois = dbHelper.getConsommationsMois()
            val consosAnnee = dbHelper.getConsommationsAnnee()

            fun totalJour(type: String): Int =
                (consosJour[type] ?: 0)

            fun totalFromMap(map: Map<String, List<Int>>, type: String): Int =
                map[type]?.sum() ?: 0

            fun setIntCell(tv: TextView, active: Boolean, value: Int) {
                tv.text = if (!active) "-" else value.toString()
            }

            // Cigarettes
            val cigActive = categoriesActives[DatabaseHelper.TYPE_CIGARETTE] == true
            setIntCell(cellCigJour, cigActive, totalJour(DatabaseHelper.TYPE_CIGARETTE))
            setIntCell(cellCigSemaine, cigActive, totalFromMap(consosSemaine, DatabaseHelper.TYPE_CIGARETTE))
            setIntCell(cellCigMois, cigActive, totalFromMap(consosMois, DatabaseHelper.TYPE_CIGARETTE))
            setIntCell(cellCigAnnee, cigActive, totalFromMap(consosAnnee, DatabaseHelper.TYPE_CIGARETTE))

            // Joints
            val jointActive = categoriesActives[DatabaseHelper.TYPE_JOINT] == true
            setIntCell(cellJointJour, jointActive, totalJour(DatabaseHelper.TYPE_JOINT))
            setIntCell(cellJointSemaine, jointActive, totalFromMap(consosSemaine, DatabaseHelper.TYPE_JOINT))
            setIntCell(cellJointMois, jointActive, totalFromMap(consosMois, DatabaseHelper.TYPE_JOINT))
            setIntCell(cellJointAnnee, jointActive, totalFromMap(consosAnnee, DatabaseHelper.TYPE_JOINT))

            // Alcool global
            val alcoolGlobalActive = categoriesActives[DatabaseHelper.TYPE_ALCOOL_GLOBAL] == true
            setIntCell(cellAlcoolGlobalJour, alcoolGlobalActive, totalJour(DatabaseHelper.TYPE_ALCOOL_GLOBAL))
            setIntCell(cellAlcoolGlobalSemaine, alcoolGlobalActive, totalFromMap(consosSemaine, DatabaseHelper.TYPE_ALCOOL_GLOBAL))
            setIntCell(cellAlcoolGlobalMois, alcoolGlobalActive, totalFromMap(consosMois, DatabaseHelper.TYPE_ALCOOL_GLOBAL))
            setIntCell(cellAlcoolGlobalAnnee, alcoolGlobalActive, totalFromMap(consosAnnee, DatabaseHelper.TYPE_ALCOOL_GLOBAL))

            // Bières
            val biereActive = categoriesActives[DatabaseHelper.TYPE_BIERE] == true
            setIntCell(cellBiereJour, biereActive, totalJour(DatabaseHelper.TYPE_BIERE))
            setIntCell(cellBiereSemaine, biereActive, totalFromMap(consosSemaine, DatabaseHelper.TYPE_BIERE))
            setIntCell(cellBiereMois, biereActive, totalFromMap(consosMois, DatabaseHelper.TYPE_BIERE))
            setIntCell(cellBiereAnnee, biereActive, totalFromMap(consosAnnee, DatabaseHelper.TYPE_BIERE))

            // Liqueurs
            val liqueurActive = categoriesActives[DatabaseHelper.TYPE_LIQUEUR] == true
            setIntCell(cellLiqueurJour, liqueurActive, totalJour(DatabaseHelper.TYPE_LIQUEUR))
            setIntCell(cellLiqueurSemaine, liqueurActive, totalFromMap(consosSemaine, DatabaseHelper.TYPE_LIQUEUR))
            setIntCell(cellLiqueurMois, liqueurActive, totalFromMap(consosMois, DatabaseHelper.TYPE_LIQUEUR))
            setIntCell(cellLiqueurAnnee, liqueurActive, totalFromMap(consosAnnee, DatabaseHelper.TYPE_LIQUEUR))

            // Alcool fort
            val alcoolFortActive = categoriesActives[DatabaseHelper.TYPE_ALCOOL_FORT] == true
            setIntCell(cellAlcoolFortJour, alcoolFortActive, totalJour(DatabaseHelper.TYPE_ALCOOL_FORT))
            setIntCell(cellAlcoolFortSemaine, alcoolFortActive, totalFromMap(consosSemaine, DatabaseHelper.TYPE_ALCOOL_FORT))
            setIntCell(cellAlcoolFortMois, alcoolFortActive, totalFromMap(consosMois, DatabaseHelper.TYPE_ALCOOL_FORT))
            setIntCell(cellAlcoolFortAnnee, alcoolFortActive, totalFromMap(consosAnnee, DatabaseHelper.TYPE_ALCOOL_FORT))

            // --- Coûts & Économies par période ---
            fun setMoneyCell(tv: TextView, value: Double) {
                val symbol = getDeviseSymbol()
                tv.text = if (value <= 0.0) {
                    "0 $symbol"
                } else {
                    String.format("%.2f %s", value, symbol)
                }
            }

            // Jour : on réutilise un dispatch en 4 points, puis on somme
            val jourDispatch = getConsommationsJourDispatch()
            val coutsJour = calculerCouts(jourDispatch).sum()
            val ecoJour = calculerEconomies(jourDispatch).sum()

            val coutsSemaine = calculerCouts(consosSemaine).sum()
            val ecoSemaine = calculerEconomies(consosSemaine).sum()

            val coutsMois = calculerCouts(consosMois).sum()
            val ecoMois = calculerEconomies(consosMois).sum()

            val coutsAnnee = calculerCouts(consosAnnee).sum()
            val ecoAnnee = calculerEconomies(consosAnnee).sum()

            setMoneyCell(cellDepensesJour, coutsJour)
            setMoneyCell(cellDepensesSemaine, coutsSemaine)
            setMoneyCell(cellDepensesMois, coutsMois)
            setMoneyCell(cellDepensesAnnee, coutsAnnee)

            setMoneyCell(cellEconomiesJour, ecoJour)
            setMoneyCell(cellEconomiesSemaine, ecoSemaine)
            setMoneyCell(cellEconomiesMois, ecoMois)
            setMoneyCell(cellEconomiesAnnee, ecoAnnee)

            Log.d(TAG, "Tableau résumé mis à jour")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur updateResumeTable: ${e.message}")
        }
    }

    private fun updateProfilStatus() {
        try {
            val hasPrenom = dbHelper.getPreference("prenom", "").isNotEmpty()

            var hasCouts = false
            categoriesActives.forEach { (type, active) ->
                if (active) {
                    val couts = dbHelper.getCouts(type)
                    if (couts.values.any { it > 0.0 }) {
                        hasCouts = true
                        return@forEach
                    }
                }
            }

            var hasHabitudes = false
            categoriesActives.forEach { (type, active) ->
                if (active && dbHelper.getMaxJournalier(type) > 0) {
                    hasHabitudes = true
                    return@forEach
                }
            }

            var hasDates = false
            categoriesActives.forEach { (type, active) ->
                if (active) {
                    val dates = dbHelper.getDatesObjectifs(type)
                    if (dates.values.any { it?.isNotEmpty() == true }) {
                        hasDates = true
                        return@forEach
                    }
                }
            }

            val isComplet = hasPrenom && hasCouts && hasHabitudes && hasDates

            txtProfilComplet.text = if (isComplet) {
                trad["profil_complet"] ?: trad["profil_complet_complet"] ?: "Profil: Complet ✓"
            } else {
                trad["profil_incomplet"] ?: trad["profil_complet_incomplet"] ?: "Profil: Incomplet"
            }

            val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
            val today = dateFormat.format(java.util.Date())
            var totalJour = 0.0

            categoriesActives.forEach { (type, active) ->
                if (active) {
                    val nbUnites = dbHelper.getConsommationParDate(type, today)
                    val couts = dbHelper.getCouts(type)
                    val prixUnitaire = calculerPrixUnitaire(type, couts)
                    totalJour += nbUnites * prixUnitaire
                }
            }

            val deviseSymbol = getDeviseSymbol()
            val rawLabelTotal =
                trad["profil_total_jour"] ?: trad["total_aujourdhui"] ?: "Total aujourd'hui"
            val labelTotal = rawLabelTotal.trimEnd(':', ' ')
            txtTotalAujourdhui.text =
                "$labelTotal: %.2f %s".format(totalJour, deviseSymbol)

            Log.d(
                TAG,
                "Profil: ${if (isComplet) "Complet" else "Incomplet"} - Total jour: $totalJour"
            )
        } catch (e: Exception) {
            Log.e(TAG, "Erreur updateProfilStatus stats", e)
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            loadCategoriesActives()
            updateGraphiques()
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

    fun refreshData() {
        try {
            loadCategoriesActives()
            updateGraphiques()
            updateProfilStatus()
            Log.d(TAG, "Données rafraîchies manuellement")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur refresh data: ${e.message}")
        }
    }

    fun setPeriode(periode: String) {
        try {
            if (periode in listOf(PERIODE_JOUR, PERIODE_SEMAINE, PERIODE_MOIS, PERIODE_ANNEE)) {
                periodeActive = periode
                updateButtonsState()
                updateGraphiques()
                Log.d(TAG, "Période changée: $periode")
            } else {
                Log.w(TAG, "Période invalide: $periode")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Erreur changement période: ${e.message}")
        }
    }
}
