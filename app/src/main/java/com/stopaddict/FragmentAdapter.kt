package com.stopaddict

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import android.util.Log

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
        Log.d(TAG, "Création fragment position: $position")
        
        return when (position) {
            0 -> {
                Log.d(TAG, "→ AccueilFragment créé")
                AccueilFragment()
            }
            1 -> {
                Log.d(TAG, "→ StatsFragment créé")
                StatsFragment()
            }
            2 -> {
                // TODO: Remplacer par CalendrierFragment quand créé
                Log.w(TAG, "⚠️ CalendrierFragment pas encore créé - Placeholder temporaire")
                PlaceholderFragment.newInstance("Calendrier", position)
            }
            3 -> {
                // TODO: Remplacer par HabitudesFragment quand créé
                Log.w(TAG, "⚠️ HabitudesFragment pas encore créé - Placeholder temporaire")
                PlaceholderFragment.newInstance("Habitudes & Volonté", position)
            }
            4 -> {
                Log.d(TAG, "→ ReglagesFragment créé")
                ReglagesFragment()
            }
            else -> {
                Log.e(TAG, "❌ Position invalide: $position")
                throw IllegalArgumentException("Position invalide: $position")
            }
        }
    }
}
