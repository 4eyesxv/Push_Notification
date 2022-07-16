package com.example.push_notification.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.push_notification.MainActivity
import com.example.push_notification.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val channelId = "notification_channel"
const val channelName = "com.example.push_notification"

class FCMService: FirebaseMessagingService() {

    override fun onMessageReceived(remote: RemoteMessage) {
        if (remote.getNotification() != null){
            generateNotification(remote.notification!!.title!!, remote.notification!!.body!!)
        }
    }

    fun getRemoteView(title: String, message: String): RemoteViews{
        val remoteView = RemoteViews("com.example.push_notification", R.layout.notification)

        remoteView.setTextViewText(R.id.title,title)
        remoteView.setTextViewText(R.id.message,message)
        remoteView.setImageViewResource(R.id.appLogo,R.drawable.ic_notifications)

        return remoteView
    }

    fun generateNotification(title: String, message: String){

        val intent = Intent(this,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this, 0,intent, PendingIntent.FLAG_ONE_SHOT)

        var builder: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.ic_notifications)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000,1000,1000,1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        builder = builder.setContent(getRemoteView(title,message))

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChanell = NotificationChannel(channelId, channelName,NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChanell)
        }

        notificationManager.notify(0,builder.build())

    }



    override fun onNewToken(token: String) {
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String?){
        Log.d("FCM", "sendRegistrationToServer($token)")
    }
}