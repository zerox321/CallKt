package com.eslam.callkt.notification

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build

fun createNotificationChannel(channelId: String, channelName: String, activity: Activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationChannel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_HIGH
        )
            .apply {
                setShowBadge(false)
            }

        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.RED
        notificationChannel.enableVibration(true)
        notificationChannel.description = channelName

        val notificationManager = activity.getSystemService(
            NotificationManager::class.java
        )
        notificationManager?.createNotificationChannel(notificationChannel)

    }
}