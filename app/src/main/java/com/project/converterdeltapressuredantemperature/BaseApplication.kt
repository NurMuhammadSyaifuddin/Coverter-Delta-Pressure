package com.project.converterdeltapressuredantemperature

import android.app.Application
import com.project.converterdeltapressuredantemperature.ui.di.preferenceModule
import com.project.converterdeltapressuredantemperature.ui.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(viewModelModule, preferenceModule)
        }
    }

}