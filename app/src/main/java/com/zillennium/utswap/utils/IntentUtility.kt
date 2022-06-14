package com.zillennium.utswap.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

fun intentOtherApp(content: Context, packageName: String) {
        var intent: Intent? = content.packageManager?.getLaunchIntentForPackage(packageName)
        if (intent != null) {
            // We found the activity now start the activity
            content.startActivity(intent)
        } else {
            // Bring user to the market or let them choose an app?
            intent = Intent(Intent.ACTION_VIEW)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.data = Uri.parse("market://details?id=$packageName")
            content.startActivity(intent)
        }
}