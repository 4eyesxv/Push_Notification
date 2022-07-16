package com.example.push_notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.push_notification.databinding.ActivityMainBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Log.i("FCM token", Firebase.messaging.token.toString())


    }
}