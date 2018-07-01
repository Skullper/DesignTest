package design.skullper.com.designtest

import android.app.Application
import android.content.Context

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {

        @Volatile
        private var instance: App? = null

        val context: Context
            get() = instance!!.applicationContext
    }

}
