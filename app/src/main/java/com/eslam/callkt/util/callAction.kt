package com.eslam.callkt.util


import android.content.Context
import android.content.Intent
import android.net.Uri


fun call_action(context: Context, phone: String) {
    val intent = Intent(Intent.ACTION_DIAL)
    intent.data = Uri.parse("tel:$phone")
    context.startActivity(Intent.createChooser(intent, "Select an app"))
}
