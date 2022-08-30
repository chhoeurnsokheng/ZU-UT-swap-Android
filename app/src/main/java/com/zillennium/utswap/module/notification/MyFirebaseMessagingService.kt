package com.zillennium.utswap.module.notification

import android.R.id
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.text.HtmlCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.module.system.notification.NotificationActivity
import com.zillennium.utswap.screens.navbar.navbar.MainActivity
import me.leolin.shortcutbadger.ShortcutBadger
import java.util.*


class MyFirebaseMessagingService : FirebaseMessagingService() {


    private val channelId = "com.zillennium.utswap"

    override fun onMessageReceived(message: RemoteMessage) {
        Log.d("dataPaylaod", message.notification.toString())
        displayNotification(message.notification?.title.toString(), message.notification?.body.toString(), 1, message.notification?.icon.toString())
        super.onMessageReceived(message)
    }


    override fun onNewToken(token: String) {
        Log.d("token", token)
        SessionPreferences().DEVICE_TOKEN = token
    }

    private fun displayNotification(
        title: String,
        message: String,
        badgeCount: Int,
        icon: String
    ) {
        val intent = (Intent(this, MainActivity::class.java))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        when (title) {
            "KYC Approved", "KYC Rejected" -> {
                intent.putExtra("dataIntent",  "KYC")
            }
            "Fund Transfer" -> {
                intent.putExtra("dataIntent","Fund Transfer")
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
        ShortcutBadger.applyCount(this, badgeCount)
        ShortcutBadger.applyNotification(applicationContext, incomingCallNotification, badgeCount)

        val random = Random().nextInt(100)
        notificationManager.notify(random, incomingCallNotification)

    }

}