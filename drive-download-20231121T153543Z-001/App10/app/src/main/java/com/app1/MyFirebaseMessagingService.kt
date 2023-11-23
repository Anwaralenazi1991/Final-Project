package com.app1

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * Created by Mohamed Fadel
 * Date: 4/11/2020.
 * email: mohamedfadel91@gmail.com.
 */
class MyFirebaseMessagingService: FirebaseMessagingService(){
    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

        if(p0.data!=null) {
            val number = p0.data.get("number")
            val status = p0.data.get("status")

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    "APP",
                    "NotificationDemo",
                    NotificationManager.IMPORTANCE_HIGH
                )
                notificationManager.createNotificationChannel(channel)
            }

            var builder = NotificationCompat.Builder(this, "APP")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Order Status")
                .setContentText("Order no. " + number + " " + status)
                .setPriority(NotificationCompat.PRIORITY_HIGH)

            val notification: Notification = builder.build()

            notificationManager.notify(0, notification)
        }


    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }
}