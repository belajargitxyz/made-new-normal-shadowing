package alfianyabdullah.submission.made

import com.google.firebase.analytics.ktx.ParametersBuilder
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase

object AppAnalytics {
    fun pushEvent(eventName: String, action: ParametersBuilder.() -> Unit) {
        Firebase.analytics.logEvent(eventName) {
            action()
        }
    }
}