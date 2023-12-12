package hr.foi.rampu.emedi

import android.app.Application

class Application : Application() {

    companion object {
        var textSize: Float = 12f
    }

    override fun onCreate() {
        super.onCreate()

    }
}
