package com.stopaddict

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * PlaceholderFragment.kt
 * Fragment temporaire pour les onglets non implémentés
 * À SUPPRIMER quand CalendrierFragment et HabitudesFragment seront créés
 */
class PlaceholderFragment : Fragment() {

    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_POSITION = "position"

        fun newInstance(title: String, position: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putInt(ARG_POSITION, position)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Layout temporaire simple
        val textView = TextView(context).apply {
            val title = arguments?.getString(ARG_TITLE) ?: "Onglet"
            val position = arguments?.getInt(ARG_POSITION) ?: -1
            
            text = "📍 $title\n\n⚠️ Fragment en cours de développement\n\nPosition: $position"
            textSize = 18f
            setPadding(32, 32, 32, 32)
            gravity = android.view.Gravity.CENTER
        }
        
        return textView
    }
}
