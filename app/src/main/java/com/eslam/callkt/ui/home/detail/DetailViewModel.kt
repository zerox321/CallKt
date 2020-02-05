package com.eslam.callkt.ui.home.detail

import android.view.View
import androidx.lifecycle.ViewModel
import com.eslam.callkt.room.MyDao
import com.eslam.callkt.util.call_action

class DetailViewModel(dao: MyDao, clientId: Int) : ViewModel() {
    val clientData = dao.getClientData(clientId)
    fun callAction(v: View,phone:String?) {
        call_action(v.context,phone!!)
    }
}
