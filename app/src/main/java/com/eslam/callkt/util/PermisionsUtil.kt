package com.eslam.callkt.util

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

fun checkPermissions(activity: Activity){
    if (ActivityCompat.checkSelfPermission(
            activity,
            Manifest.permission.READ_PHONE_STATE
        ) != PackageManager.PERMISSION_GRANTED ||
        ActivityCompat.checkSelfPermission(
            activity,
            Manifest.permission.CALL_PHONE
        ) != PackageManager.PERMISSION_GRANTED ||
        ActivityCompat.checkSelfPermission(
            activity,
            Manifest.permission.READ_CALL_LOG
        ) != PackageManager.PERMISSION_GRANTED
    )
    // Check Permissions Now
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_CALL_LOG
            ),
            0
        )
}