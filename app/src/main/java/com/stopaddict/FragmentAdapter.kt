package com.stopaddict

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
// import android.util.Log  // supprimé: logs exportables via StopAddictLogger

/**
 * FragmentAdapter.kt
 * Adapter pour ViewPager2 gérant les 5 onglets de l'application
 * Accueil - Stats - Calendrier - Habitudes & Volonté - Réglages
 */
class FragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    companion object {
        private const val TAG = "FragmentAdapter"
        private const val NUM_TABS = 5
    }

    /**
     * Nombre total d'onglets
     */
    override fun getItemCount(): Int = NUM_TABS

    /**
     * Création des fragments selon la position
     */
    override fun createFragment(position: Int): Fragment {
        StopAddictLogger.d(TAG, "Création fragment position: $position")
        
        return when (position) {
            0 -> {
                StopAddictLogger.d(TAG, "→ AccueilFragment créé")
                AccueilFragment()
            }
            1 -> {
                StopAddictLogger.d(TAG, "→ StatsFragment créé")
                StatsFragment()
            }
            2 -> {
                StopAddictLogger.d(TAG, "→ CalendrierFragment créé")
                CalendrierFragment()
            }
            3 -> {
                StopAddictLogger.d(TAG, "→ HabitudesFragment créé")
                HabitudesFragment()
            }
            4 -> {
                StopAddictLogger.d(TAG, "→ ReglagesFragment créé")
                ReglagesFragment()
            }
            else -> {
                StopAddictLogger.e(TAG, "❌ Position invalide: $position")
                throw IllegalArgumentException("Position invalide: $position")
            }
        }
    }
}
