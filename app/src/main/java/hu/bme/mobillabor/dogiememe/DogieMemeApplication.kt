package hu.bme.mobillabor.dogiememe

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import hu.bme.mobillabor.dogiememe._anlytics.AnalyticsManager

@HiltAndroidApp
class DogieMemeApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AnalyticsManager.setUpFirebase(applicationContext)
    }
}