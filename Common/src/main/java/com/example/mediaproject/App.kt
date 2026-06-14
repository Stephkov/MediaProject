package com.example.mediaproject

import android.app.Application
import com.example.mediaproject.utilities.SharedPreferencesManager

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPreferencesManager.init(this)
    }
}