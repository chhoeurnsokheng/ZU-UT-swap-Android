package com.zillennium.utswap.module.notification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.text.HtmlCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.JsonObject
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.api.manager.ApiNotificationImp
import com.zillennium.utswap.screens.navbar.navbar.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.leolin.shortcutbadger.ShortcutBadger
import java.util.*

class MyFirebaseMessagingService : FirebaseMessagingService() {


    private val channelId = "com.zillennium.utswap"
    private var badgeCount = 0


    override fun onMessageReceived(message: RemoteMessage) {
        Log.d("dataPayload", message.notification.toString())
        if (message.notification != null) {
            displayNotification(
                message.notification?.title.toString(),
                HtmlCompat.fromHtml(
                    message.notification?.body.toString(),
                    HtmlCompat.FROM_HTML_MODE_COMPACT).toString()
            )
        }
//        val title = data["title"]
//        val body = data["body"]
//        if (data.containsKey("title") && data.containsKey("body")) {
//            displayNotification(data["title"].toString(), data["body"].toString(), 1)
//
//        }
    }

//    override fun onNewToken(token: String) {
//        Log.d("token", token)
//        SessionPreferences().DEVICE_TOKEN = token
//    }


    override fun handleIntent(intent: Intent?) {
        try {
            if (intent?.extras != null) {
                val builder = RemoteMessage.Builder("MyFirebaseMessagingService")
                for (key in intent.extras?.keySet() ?: arrayListOf()) {
                    builder.addData(key!!, intent.extras!![key].toString())
                }
//                getBadgeNumber()
                onMessageReceived(builder.build())
            } else {
                super.handleIntent(intent)
            }
        } catch (e: Exception) {
            super.handleIntent(intent)
        }
    }



    private fun displayNotification(
        title: String,
        message: String,
    ) {
        if (title == "KYC Approved" || title == "KYC Rejected") {
            SessionVariable.NOTIFICATION_LISTENER.postValue(true)
        }
        val intent = (Intent(this, MainActivity::class.java))
        intent.action = Intent.ACTION_MAIN
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        when (title) {
            "KYC Approved", "KYC Rejected" -> {
                intent.putExtra("dataIntent",  title)
            }
            "Fund Transfer" -> {
                intent.putExtra("dataIntent","Fund Transfer")
            }
            "Deposit Successful" -> {
                intent.putExtra("dataIntent","Deposit Successful")

            }

        }
        val dummyUniqueInt = Random().nextInt(100)
        var fullScreenPendingIntent: PendingIntent? = null
        fullScreenPendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(this, dummyUniqueInt, intent, PendingIntent.FLAG_MUTABLE)
        } else {
            PendingIntent.getActivity(
                this,
                dummyUniqueInt,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }

        val messageTitle =
            HtmlCompat.fromHtml("<strong>${title}<strong>", HtmlCompat.FROM_HTML_MODE_LEGACY)
        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(messageTitle)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setContentIntent(fullScreenPendingIntent)
            .setAutoCancel(true)
            .setContentText(message)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))

        val notificationManager = NotificationManagerCompat.from(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = getString(R.string.app_name)
            val description: String = getString(R.string.app_name)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelId, name, importance)

            channel.setShowBadge(true)
            channel.description = description
            notificationManager.createNotificationChannel(channel)
        }
        val incomingCallNotification: Notification = builder.build()


        val random = Random().nextInt(100)
        notificationManager.notify(random, incomingCallNotification)

        val param = JsonObject()
        param.addProperty("page", 1)
        ApiNotificationImp().notification(this, param).subscribe({
            if (it.status == 1) {
                badgeCount = it.data?.countGroupNoti?.toInt() ?: 0
                SessionVariable.BADGE_NUMBER.value = it.data?.countGroupNoti
                ShortcutBadger.applyCount(this, badgeCount)
                ShortcutBadger.applyNotification(applicationContext, incomingCallNotification, badgeCount)
            }
        },{

        })

    }

    private fun getBadgeNumber() {
        val param = JsonObject()
        param.addProperty("page", 1)
        ApiNotificationImp().notification(this, param).subscribe({
            if (it.status == 1) {
                badgeCount = it.data?.countGroupNoti?.toInt() ?: 0
            }
        },{

        })
    }


}