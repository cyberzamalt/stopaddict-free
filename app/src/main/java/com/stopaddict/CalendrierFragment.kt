package com.stopaddict

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

class CalendrierFragment : Fragment() {

    companion object {
        private const val TAG = "CalendrierFragment"
    }

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var configLangue: ConfigLangue
    private lateinit var headerTitle: TextView
    private lateinit var txtProfilStatus: TextView
    private lateinit var txtTotalJour: TextView
    private lateinit var btnMoisPrecedent: Button
    private lateinit var txtMoisAnnee: TextView
    private lateinit var btnMoisSuivant: Button
    private lateinit var gridCalendrier: GridLayout
    private lateinit var containerLegende: LinearLayout
    private lateinit var btnAujourdhui: Button
    private lateinit var btnHier: Button
    private lateinit var btnDemain: Button
    private var currentCalendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calendrier, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        try {
            dbHelper = DatabaseHelper(requireContext())
            configLangue = ConfigLangue(requireContext())
            
            initializeViews(view)
            applyTranslations()
            setupListeners()
            loadDataAndRefresh()
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur initialisation", e)
        }
    }

    private fun initializeViews(view: View) {
        headerTitle = view.findViewById(R.id.calendrier_header_title)
        txtProfilStatus = view.findViewById(R.id.calendrier_profil_status)
        txtTotalJour = view.findViewById(R.id.calendrier_total_jour)
        btnMoisPrecedent = view.findViewById(R.id.calendrier_btn_mois_precedent)
        txtMoisAnnee = view.findViewById(R.id.calendrier_txt_mois_annee)
        btnMoisSuivant = view.findViewById(R.id.calendrier_btn_mois_suivant)
        gridCalendrier = view.findViewById(R.id.calendrier_grid_mois)
        containerLegende = view.findViewById(R.id.calendrier_container_legende)
        btnAujourdhui = view.findViewById(R.id.calendrier_btn_aujourdhui)
        btnHier = view.findViewById(R.id.calendrier_btn_hier)
        btnDemain = view.findViewById(R.id.calendrier_btn_demain)
    }

    private fun applyTranslations() {
        val langue = configLangue.getLangue()
        val trad = CalendrierLangues.getTraductions(langue)
        
        btnMoisPrecedent.text = trad["btn_mois_precedent"]
        btnMoisSuivant.text = trad["btn_mois_suivant"]
        btnAujourdhui.text = trad["btn_aujourdhui"]
        btnHier.text = trad["btn_hier"]
        btnDemain.text = trad["btn_demain"]
    }

    private fun setupListeners() {
        btnMoisPrecedent.setOnClickListener {
            currentCalendar.add(Calendar.MONTH, -1)
            updateCalendar()
        }
        
        btnMoisSuivant.setOnClickListener {
            currentCalendar.add(Calendar.MONTH, 1)
            updateCalendar()
        }
        
        btnAujourdhui.setOnClickListener {
            currentCalendar = Calendar.getInstance()
            updateCalendar()
        }
        
        btnHier.setOnClickListener {
            currentCalendar.add(Calendar.DAY_OF_MONTH, -1)
            updateCalendar()
        }
        
        btnDemain.setOnClickListener {
            currentCalendar.add(Calendar.DAY_OF_MONTH, 1)
            updateCalendar()
        }
    }

    private fun loadDataAndRefresh() {
        updateBandeau()
        updateCalendar()
    }

    private fun updateBandeau() {
        try {
            val trad = CalendrierLangues.getTraductions(configLangue.getLangue())
            
            txtProfilStatus.text = trad["profil_complet"] ?: "Profil: OK"
            txtTotalJour.text = "${trad["total_aujourdhui"] ?: "Total"}: 0"
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur update bandeau", e)
        }
    }

    private fun updateCalendar() {
        try {
            val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
            txtMoisAnnee.text = dateFormat.format(currentCalendar.time)
            
            gridCalendrier.removeAllViews()
            
            val cal = currentCalendar.clone() as Calendar
            cal.set(Calendar.DAY_OF_MONTH, 1)
            val firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK)
            val daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
            
            for (i in 1 until firstDayOfWeek) {
                gridCalendrier.addView(TextView(requireContext()).apply {
                    text = ""
                })
            }
            
            for (day in 1..daysInMonth) {
                val dayView = TextView(requireContext()).apply {
                    text = day.toString()
                    setPadding(8, 8, 8, 8)
                    gravity = android.view.Gravity.CENTER
                    setOnClickListener {
                        Toast.makeText(requireContext(), "Jour $day", Toast.LENGTH_SHORT).show()
                    }
                }
                gridCalendrier.addView(dayView)
            }
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur update calendar", e)
        }
    }

    fun refreshData() {
        loadDataAndRefresh()
    }

    override fun onResume() {
        super.onResume()
        loadDataAndRefresh()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (::dbHelper.isInitialized) {
            dbHelper.close()
        }
    }
}
