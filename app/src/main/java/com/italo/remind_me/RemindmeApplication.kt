package com.italo.remind_me

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RemindmeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
