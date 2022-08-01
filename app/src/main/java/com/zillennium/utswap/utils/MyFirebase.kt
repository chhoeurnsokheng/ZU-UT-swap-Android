package com.zillennium.utswap.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.text.HtmlCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.zillennium.utswap.R
import me.leolin.shortcutbadger.ShortcutBadger
import java.util.*


class MyFirebase : FirebaseMessagingService() {

    private val channelId = "com.zillennium.utswap"

    override fun onMessageReceived(message: RemoteMessage) {

        if (message.data.isNotEmpty()) {
            val data: Map<String, String> = message.data
            var title = data["title"]
            var message = data["body"]
            var id = data["id"]
        }
    }

    override fun onNewToken(token: String) {
        SharePreferences.setDeviceToken(token, this)
    }

    private fun displayNotification(
        title: String,
        message: String,
        intent: Intent,
        badgeCount: Int
    ) {
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
            val channel: NotificationChannel = NotificationChannel(channelId, name, importance)
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