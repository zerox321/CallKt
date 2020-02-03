package com.eslam.callkt

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast

@Suppress("DEPRECATION")
class PhoneStateReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        try {
            val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
            val incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
            if (state == TelephonyManager.EXTRA_STATE_RINGING) {
                Toast.makeText(context, "Incoming Call State", Toast.LENGTH_SHORT).show()
                Toast.makeText(context, "Ringing State Number is -" + incomingNumber!!, Toast.LENGTH_SHORT).show()
                Log.e("PhoneStateReceiver  ", "Ringing State Number is  : $incomingNumber")
//                NotificationManager.sendNotification(incomingNumber,context)

            }
            if (state == TelephonyManager.EXTRA_STATE_OFFHOOK) {
                Toast.makeText(context, "Call Received State", Toast.LENGTH_SHORT).show()
            }
            if (state == TelephonyManager.EXTRA_STATE_IDLE) {
                Toast.makeText(context, "Call Idle State", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}