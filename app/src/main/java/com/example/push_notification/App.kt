package com.example.push_notification

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.google.firebase.messaging.ktx.messaging

class App:Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this@App)
        Firebase.initialize(this@App)
        Firebase.messaging.isAutoInitEnabled = true
    }
}