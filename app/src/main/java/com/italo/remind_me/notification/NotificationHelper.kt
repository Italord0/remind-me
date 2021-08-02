package com.italo.remind_me.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.italo.remind_me.MainActivity
import com.italo.remind_me.R
import com.italo.remind_me.data.Alarm

object NotificationHelper {
    fun createNotification(context: Context, alarm: Alarm) {
        val intentOpen = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }

        val openPending =
            PendingIntent.getActivity(context, 0, intentOpen, PendingIntent.FLAG_UPDATE_CURRENT)

        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannelCompat.Builder("NOTIFICATION CHANNEL", importance)
            .setName("Channel Notification")
            .setDescription("Channel Notification")
            .setLightsEnabled(true)
            .setVibrationEnabled(true)
            .setVibrationPattern(longArrayOf(0, 100, 200, 300))
            .setShowBadge(true)

        NotificationManagerCompat.from(context).createNotificationChannel(channel.build())

        val builder = NotificationCompat.Builder(context, "NOTIFICATION CHANNEL")
            .setSmallIcon(R.drawable.ic_time)
            .setContentTitle(context.getString(R.string.app_name))
            .setContentText(alarm.name)
            .setAutoCancel(true)
            .setContentIntent(openPending)

        NotificationManagerCompat.from(context).notify(0, builder.build())
    }
}
