package com.zillennium.utswap.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.NameNotFoundException
import android.net.Uri
import android.widget.Toast
import com.zillennium.utswap.UTSwapApp


fun intentOtherApp(content: Context, packagePlayStore: String, packageAppGallery: String?) {
        var intent: Intent? = content.packageManager?.getLaunchIntentForPackage(packagePlayStore)
        if (intent != null) {
            // We found the activity now start the activity
            content.startActivity(intent)
        } else {
            try {
                // Bring user to the market or let them choose an app?
                intent = Intent(Intent.ACTION_VIEW)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.data = Uri.parse("market://details?id=$packagePlayStore")
                content.startActivity(intent)
            } catch (ignored: ActivityNotFoundException) {
                if(packageAppGallery != null){
                    try {
                        intent = Intent(Intent.ACTION_VIEW)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        intent.data = Uri.parse("appmarket://details?id=$packageAppGallery")
                        content.startActivity(intent)
                    } catch (ignored: ActivityNotFoundException) {
                        Toast.makeText(UTSwapApp.instance, "Your Phone not have App Market", Toast.LENGTH_SHORT).show()
                        intent = Intent(Intent.ACTION_VIEW)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        intent.data = Uri.parse("https://play.google.com/store/apps/details?id=$packagePlayStore")
                        content.startActivity(intent)
                    }
                }else{
                    Toast.makeText(UTSwapApp.instance, "Your Phone not have App Market", Toast.LENGTH_SHORT).show()
                }
            }


        }
}