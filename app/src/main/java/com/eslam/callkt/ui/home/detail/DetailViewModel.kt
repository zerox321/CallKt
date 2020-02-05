package com.eslam.callkt.ui.home.detail

import android.app.NotificationManager
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.eslam.callkt.notification.sendNotification
import com.eslam.callkt.room.MyDao

class DetailViewModel(dao: MyDao, clientId: Int) : ViewModel() {
    val clientData = dao.getClientData(clientId)
    fun callAction(v: View,name:String?,phone:String?) {
//        call_action(v.context,phone!!)
        val notificationManager = ContextCompat.getSystemService(
            v.context,
                NotificationManager::class.java
            ) as NotificationManager

            notificationManager.sendNotification(
                name!!,
                phone!!,
                v.context
            )
    }
}
