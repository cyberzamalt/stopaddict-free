package com.stopaddict

import android.content.Context
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
                StopAddictLogger.d(TAG, "AdMob initialisé: ${initializationStatus.adapterStatusMap}")
            }
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, StopAddictLogger.e(TAG, initialisation AdMob", e)
        }
    }

    /**
     * Charge et affiche le bandeau publicitaire
     */
    fun loadBanner(container: FrameLayout) {
        if (adView != null) {
            StopAddictLogger.w(TAG, "Bandeau déjà chargé")
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
                        StopAddictLogger.d(TAG, "Bandeau pub chargé")
                    }
                    
                    override fun onAdFailedToLoad(error: LoadAdError) {
                        StopAddictLogger.e(TAG, StopAddictLogger.e(TAG, chargement pub: ${error.message}")
                    }
                    
                    override fun onAdOpened() {
                        StopAddictLogger.d(TAG, "Pub ouverte")
                    }
                    
                    override fun onAdClosed() {
                        StopAddictLogger.d(TAG, "Pub fermée")
                    }
                }
            }

            // Ajouter au container
            container.addView(adView)

            // Charger la pub
            val adRequest = AdRequest.Builder().build()
            adView?.loadAd(adRequest)
            
            StopAddictLogger.d(TAG, "Chargement bandeau pub lancé")
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, "Erreur création bandeau pub", e)
        }
    }

    /**
     * Détruit la vue publicitaire (appelé onDestroy)
     */
    fun destroy() {
        try {
            adView?.destroy()
            adView = null
            StopAddictLogger.d(TAG, "Bandeau pub détruit")
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, StopAddictLogger.e(TAG, destruction pub", e)
        }
    }

    /**
     * Met en pause la pub (appelé onPause)
     */
    fun pause() {
        try {
            adView?.pause()
            StopAddictLogger.d(TAG, "Bandeau pub en pause")
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, StopAddictLogger.e(TAG, pause pub: ${e.message}")
        }
    }

    /**
     * Reprend la pub (appelé onResume)
     */
    fun resume() {
        try {
            adView?.resume()
            StopAddictLogger.d(TAG, "Bandeau pub repris")
        } catch (e: Exception) {
            StopAddictLogger.e(TAG, StopAddictLogger.e(TAG, reprise pub: ${e.message}")
        }
    }
}
