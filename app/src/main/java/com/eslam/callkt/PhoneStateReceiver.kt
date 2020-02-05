package com.eslam.callkt

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.eslam.callkt.internet.RetrofitAPI
import com.eslam.callkt.notification.sendNotification
import com.eslam.callkt.util.Pref.Companion.getStr
import kotlinx.coroutines.*
import java.io.IOException

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

                uiScope.launch {

                    try {
                        Log.e(tag, " start  $incomingNumber")

                        val baseURL = BuildConfig.BaseLink

                        callServer(context, baseURL, incomingNumber)

                    } catch (ex: IOException) {

                        try {
                            val off = getStr(context, "offline")
                            callServer(context, off, incomingNumber)
                        } catch (ex: IOException) {

                        }

                    }

                }

            }
//            if (state == TelephonyManager.EXTRA_STATE_OFFHOOK) {
//            }
//            if (state == TelephonyManager.EXTRA_STATE_IDLE) {
//            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private suspend fun callServer(context: Context, baseURL: String, phone: String) {

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

