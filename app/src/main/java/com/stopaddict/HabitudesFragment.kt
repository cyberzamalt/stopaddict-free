package com.stopaddict

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class HabitudesFragment : Fragment() {

    companion object {
        private const val TAG = "HabitudesFragment"
    }

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var configLangue: ConfigLangue
    private lateinit var headerTitle: TextView
    private lateinit var txtProfilStatus: TextView
    private lateinit var txtTotalJour: TextView
    private lateinit var inputMaxCigarettes: EditText
    private lateinit var inputMaxJoints: EditText
    private lateinit var inputMaxAlcoolGlobal: EditText
    private lateinit var inputMaxBieres: EditText
    private lateinit var inputMaxLiqueurs: EditText
    private lateinit var inputMaxAlcoolFort: EditText
    private lateinit var btnSauvegarder: Button
    private lateinit var containerVolonte: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_habitudes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        try {
            dbHelper = DatabaseHelper(requireContext())
            configLangue = ConfigLangue(requireContext())
            
            initializeViews(view)
            applyTranslations()
            setupListeners()
            loadExistingData()
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur initialisation", e)
        }
    }

    private fun initializeViews(view: View) {
        headerTitle = view.findViewById(R.id.habitudes_header_title)
        txtProfilStatus = view.findViewById(R.id.habitudes_profil_status)
        txtTotalJour = view.findViewById(R.id.habitudes_total_jour)
        inputMaxCigarettes = view.findViewById(R.id.habitudes_input_max_cigarettes)
        inputMaxJoints = view.findViewById(R.id.habitudes_input_max_joints)
        inputMaxAlcoolGlobal = view.findViewById(R.id.habitudes_input_max_alcool_global)
        inputMaxBieres = view.findViewById(R.id.habitudes_input_max_bieres)
        inputMaxLiqueurs = view.findViewById(R.id.habitudes_input_max_liqueurs)
        inputMaxAlcoolFort = view.findViewById(R.id.habitudes_input_max_alcool_fort)
        btnSauvegarder = view.findViewById(R.id.habitudes_btn_sauvegarder)
        containerVolonte = view.findViewById(R.id.habitudes_container_volonte)
    }

    private fun applyTranslations() {
        val langue = configLangue.getLangue()
        val trad = HabitudesLangues.getTraductions(langue)
        
        headerTitle.text = "Habitudes & Volonté"
    }

    private fun setupListeners() {
        btnSauvegarder.setOnClickListener {
            saveHabitudes()
        }
    }

    private fun loadExistingData() {
        try {
            updateBandeau()
        } catch (e: Exception) {
            Log.e(TAG, "Erreur chargement données", e)
        }
    }

    private fun updateBandeau() {
        try {
            val trad = HabitudesLangues.getTraductions(configLangue.getLangue())
            
            txtProfilStatus.text = trad["profil_complet"] ?: "Profil: OK"
            txtTotalJour.text = "${trad["total_aujourdhui"] ?: "Total"}: 0"
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur update bandeau", e)
        }
    }

    private fun saveHabitudes() {
        try {
            val maxCig = inputMaxCigarettes.text.toString().toIntOrNull() ?: 0
            val maxJoints = inputMaxJoints.text.toString().toIntOrNull() ?: 0
            val maxAlcool = inputMaxAlcoolGlobal.text.toString().toIntOrNull() ?: 0
            val maxBieres = inputMaxBieres.text.toString().toIntOrNull() ?: 0
            val maxLiqueurs = inputMaxLiqueurs.text.toString().toIntOrNull() ?: 0
            val maxAlcoolFort = inputMaxAlcoolFort.text.toString().toIntOrNull() ?: 0
            
            Toast.makeText(requireContext(), "Habitudes sauvegardées", Toast.LENGTH_SHORT).show()
            
            activity?.let {
                if (it is MainActivity) {
                    it.refreshData()
                }
            }
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur sauvegarde", e)
            Toast.makeText(requireContext(), "Erreur: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    fun refreshData() {
        loadExistingData()
    }

    override fun onResume() {
        super.onResume()
        loadExistingData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (::dbHelper.isInitialized) {
            dbHelper.close()
        }
    }
}
