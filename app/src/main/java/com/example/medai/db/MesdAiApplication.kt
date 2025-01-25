package com.example.medai.db

import android.app.Application
import com.google.firebase.FirebaseApp

class MedAiApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Firebase
        FirebaseApp.initializeApp(this)
    }
}