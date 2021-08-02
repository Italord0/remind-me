package com.italo.remind_me.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.italo.remind_me.data.Alarm
import com.italo.remind_me.data.AlarmRepository
import com.italo.remind_me.notification.NotificationHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
open class AlarmBroadcastReceiver : BroadcastReceiver() {
    @Inject
    lateinit var repository: AlarmRepository

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != "sync") {
            println("chegou")
            var alarm =
                Alarm(
                    intent.getIntExtra("ALARM_ID", 0),
                    intent.getStringExtra("ALARM_NAME"),
                    21
                )
            println("alarm no receiver $alarm")
            NotificationHelper.createNotification(context, alarm)
            CoroutineScope(Dispatchers.Main).launch {
                withContext(Dispatchers.IO) {
                    repository.deleteAlarm(alarm)
                }
                context.sendBroadcast(Intent("sync"))
            }
        }
    }
}
