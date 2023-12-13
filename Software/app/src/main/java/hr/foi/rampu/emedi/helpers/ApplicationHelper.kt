package hr.foi.rampu.emedi.helpers

import android.app.Application
import hr.foi.rampu.emedi.helpers.TextSizeUtility

class ApplicationHelper : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize any application-level settings or utilities here
        TextSizeUtility.initialize(this)
    }
}