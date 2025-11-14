package com.stopaddict

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds

class PubManager(private val context: Context) {

    companion object {
        private const val TAG = "PubManager"
        
        // ID bloc d'annonces réel (depuis Google AdMob)
        private const val AD_UNIT_ID = "ca-app-pub-7887334791636153/3618295935"
        
        // Pour tests uniquement (décommenter en dev)
        // private const val AD_UNIT_ID = "ca-app-pub-3940256099942544/6300978111" // Test ID
    }

    private var adView: AdView? = null
    private var isInitialized = false

    /**
     * Initialise le SDK AdMob
     */
    init {
        try {
            MobileAds.initialize(context) { initializationStatus ->
                isInitialized = true
                Log.d(TAG, "AdMob initialisé: ${initializationStatus.adapterStatusMap}")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Erreur initialisation AdMob: ${e.message}")
        }
    }

    /**
     * Charge et affiche le bandeau publicitaire
     */
    fun loadBanner(container: FrameLayout) {
        if (adView != null) {
            Log.w(TAG, "Bandeau déjà chargé")
            return
        }

        try {
            // Créer AdView
            adView = AdView(context).apply {
                adUnitId = AD_UNIT_ID
                setAdSize(AdSize.BANNER) // 320x50dp
                
                // Listener pour logs
                adListener = object : com.google.android.gms.ads.AdListener() {
                    override fun onAdLoaded() {
                        Log.d(TAG, "Bandeau pub chargé")
                    }
                    
                    override fun onAdFailedToLoad(error: LoadAdError) {
                        Log.e(TAG, "Erreur chargement pub: ${error.message}")
                    }
                    
                    override fun onAdOpened() {
                        Log.d(TAG, "Pub ouverte")
                    }
                    
                    override fun onAdClosed() {
                        Log.d(TAG, "Pub fermée")
                    }
                }
            }

            // Ajouter au container
            container.addView(adView)

            // Charger la pub
            val adRequest = AdRequest.Builder().build()
            adView?.loadAd(adRequest)
            
            Log.d(TAG, "Chargement bandeau pub lancé")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur création bandeau pub: ${e.message}")
        }
    }

    /**
     * Détruit la vue publicitaire (appelé onDestroy)
     */
    fun destroy() {
        try {
            adView?.destroy()
            adView = null
            Log.d(TAG, "Bandeau pub détruit")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur destruction pub: ${e.message}")
        }
    }

    /**
     * Met en pause la pub (appelé onPause)
     */
    fun pause() {
        try {
            adView?.pause()
            Log.d(TAG, "Bandeau pub en pause")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur pause pub: ${e.message}")
        }
    }

    /**
     * Reprend la pub (appelé onResume)
     */
    fun resume() {
        try {
            adView?.resume()
            Log.d(TAG, "Bandeau pub repris")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur reprise pub: ${e.message}")
        }
    }
}
