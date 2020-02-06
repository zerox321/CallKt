package com.eslam.callkt.ui.home.detail

import android.app.NotificationManager
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.eslam.callkt.notification.sendNotification
import com.eslam.callkt.room.MyDao
import com.eslam.callkt.util.call_action

class DetailViewModel(dao: MyDao, clientId: Int) : ViewModel() {
    val clientData = dao.getClientData(clientId)

    fun notifyAction(v: View) {
              val name:String?=clientData.value?.name
        val phone:String?=clientData.value?.mobile
        val notificationManager = ContextCompat.getSystemService(
            v.context,
                NotificationManager::class.java
            ) as NotificationManager

            notificationManager.sendNotification(
                name!!,
                phone!!,
                "https://homefix.app/sfa/public/images/avatar.png",
                v.context
            )
    }
    fun callAction(v: View) {
//        val name:String?=clientData.value?.name
        val phone:String?=clientData.value?.mobile

        call_action(v.context,phone!!)
//
    }
}
