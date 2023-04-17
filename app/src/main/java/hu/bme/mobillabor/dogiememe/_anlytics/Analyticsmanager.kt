package hu.bme.mobillabor.dogiememe._anlytics

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

object AnalyticsManager {
    var firebase: FirebaseAnalytics? = null
    fun setUpFirebase(context: Context){
        firebase = FirebaseAnalytics.getInstance(context)
    }

    fun track(event: FirebaseEvent, context: Context?){
        val bundle = Bundle()
        bundle.putString(event.id, event.value)
        firebase?.logEvent(event.name, bundle)
    }
}

abstract class FirebaseEvent : FirebaseAnalytics.Event(){
    abstract val id: String
    abstract val value: String
    abstract val name: String
}

class MemeListOpened() : FirebaseEvent(){
    override val id = "meme_list_opened"
    override val value = ""
    override val name = "MemeListOpened"
}

class MemeDetailOpened(memeId: String): FirebaseEvent(){
    override val id = "meme_detail_opened"
    override val value = memeId
    override val name = "MemeDetailOpened"
}