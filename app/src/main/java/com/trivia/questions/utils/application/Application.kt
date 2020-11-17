package com.trivia.questions.utils.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


 class Application: Application() {
     override fun onCreate() {
         super.onCreate()
//        AppCompatDelegate.MODE_NIGHT_YES
         startKoin {
             androidLogger(Level.NONE)
             androidContext(this@Application)
             modules(listOf(appModules, viewModelModules))
         }
     }

 }