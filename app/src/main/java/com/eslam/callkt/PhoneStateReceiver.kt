package com.eslam.callkt

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.eslam.callkt.internet.RetrofitAPI
import com.eslam.callkt.notification.sendNotification
import com.eslam.callkt.util.Pref.Companion.getStr
import kotlinx.coroutines.*

@Suppress("DEPRECATION")

class PhoneStateReceiver : BroadcastReceiver() {
    private val tag = PhoneStateReceiver::class.java.simpleName
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onReceive(context: Context, intent: Intent) {
        try {
            val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
            val incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
            if (state == TelephonyManager.EXTRA_STATE_RINGING) {
                Toast.makeText(context, "Incoming Call State", Toast.LENGTH_SHORT).show()
                Toast.makeText(context, "Ringing State Number is -" + incomingNumber!!, Toast.LENGTH_SHORT).show()
                Log.e("PhoneStateReceiver  ", "Ringing State Number is  : $incomingNumber")

                uiScope.launch {

                    try {
                        Log.e(tag, " start  $incomingNumber")

                        val baseURL = BuildConfig.BaseLink

                        callServer(context, baseURL, incomingNumber)

                    } catch (ex: Throwable) {

                        try {
                            val off = getStr(context, "offline")
                            callServer(context, off, incomingNumber)
                        } catch (ex: Throwable) {

                        }

                    }

                }

            }
            if (state == TelephonyManager.EXTRA_STATE_OFFHOOK) {
                Toast.makeText(context, "Call Received State", Toast.LENGTH_SHORT).show()
            }
            if (state == TelephonyManager.EXTRA_STATE_IDLE) {
                Toast.makeText(context, "Call Idle State", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }


    }

    private suspend fun callServer(context: Context, baseURL: String, phone: String?) {
        if (phone == null) return
        Log.e(tag, "callServer baseURL  $baseURL")

        val user = getUserTask(baseURL, phone)

        Log.e(tag, "callServer type  ${user.type}")

        if (user.type == "success")
            showNotification(context, phone, user.data!!.name!!)
        else
            showNotification(context, phone, user.data!!.title!!)

    }

    private fun showNotification(context: Context, phone: String, title: String) {

        Log.e(tag, "showNotification phone  $phone , title  $title")

        val notificationManager = ContextCompat.getSystemService(
            context,
            NotificationManager::class.java
        ) as NotificationManager

        notificationManager.sendNotification(
            title,
            phone,
            "https://homefix.app/sfa/public/images/avatar.png",
            context
        )
    }

    private suspend fun getUserTask(baseURL: String, phoneValue: String) =
        withContext(Dispatchers.IO) {
            RetrofitAPI.getClient(baseURL).getUserInfoAsync(
                "checkNumberInfo",
                phoneValue

            ).await()
        }

}

