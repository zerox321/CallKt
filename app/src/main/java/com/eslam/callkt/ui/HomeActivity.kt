package com.eslam.callkt.ui

import android.app.NotificationManager
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.eslam.callkt.R
import com.eslam.callkt.notification.createNotificationChannel
import com.eslam.callkt.notification.sendNotification
import com.eslam.callkt.util.checkPermisions

import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        fab.setOnClickListener { view ->

            val notificationManager = ContextCompat.getSystemService(
                view.context,
                NotificationManager::class.java
            ) as NotificationManager

            notificationManager.sendNotification(
                "Name",
                "phone",
                "description",
                view.context
            )
        }
        checkPermisions(this)

        createNotificationChannel(
            getString(R.string.notification_channel_id),
            getString(R.string.notification_channel_name),
            this
        )

        setSupportActionBar(toolbar)

    }

}
