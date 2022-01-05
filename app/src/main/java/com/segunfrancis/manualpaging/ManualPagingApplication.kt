package com.segunfrancis.manualpaging

import android.app.Application
import timber.log.Timber

class ManualPagingApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
