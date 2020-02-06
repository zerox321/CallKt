package com.eslam.callkt.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.StrictMode
import androidx.core.app.NotificationCompat
import com.eslam.callkt.R
import com.eslam.callkt.ui.HomeActivity
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.*


// Notification ID.
private const val NOTIFICATION_ID = 0


fun NotificationManager.sendNotification(
    name: String,
    phone: String,
    avatar: String,
    applicationContext: Context
) {

    val contentIntent = Intent(applicationContext, HomeActivity::class.java)

    val contentPendingIntent = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

//    val eggImage = getBitmapFromURL(avatar)
    val eggImage = BitmapFactory.decodeResource(
        applicationContext.resources,
        R.drawable.ic_profle
    )
    val bigPicStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(eggImage)
        .bigLargeIcon(null)


    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.notification_channel_id)
    )


        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle(name)
        .setContentText(phone)
        .setContentIntent(contentPendingIntent)
        .setAutoCancel(true)
        .setStyle(bigPicStyle)
        .setLargeIcon(eggImage)
        .setPriority(NotificationCompat.PRIORITY_HIGH)

    notify(Random().nextInt(), builder.build())
}

fun NotificationManager.cancelNotifications() {
    cancelAll()
}

fun  getBitmapFromURL(strURL: String): Bitmap? {
    val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
    StrictMode.setThreadPolicy(policy)
    return try {
        val url = URL(strURL)
        val connection = url.openConnection() as HttpURLConnection
        connection.doInput = true
        connection.connect()
        val input = connection.inputStream
        BitmapFactory.decodeStream(input)
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }

}