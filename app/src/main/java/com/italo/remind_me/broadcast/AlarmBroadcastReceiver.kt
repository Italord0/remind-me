package com.italo.remind_me.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.italo.remind_me.data.Alarm
import com.italo.remind_me.data.AlarmRepository
import com.italo.remind_me.notification.NotificationHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
open class AlarmBroadcastReceiver : BroadcastReceiver() {
    @Inject
    lateinit var repository: AlarmRepository

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != "sync") {
            println("chegou")
            val alarm =
                Alarm(
                    intent.getIntExtra("ALARM_ID", 0),
                    intent.getStringExtra("ALARM_NAME"), 0,
                    intent.getBooleanExtra("ALARM_REPEAT", false)
                )
            println("alarm no receiver $alarm")
            NotificationHelper.createNotification(context, alarm)
            CoroutineScope(Dispatchers.Main).launch {
                withContext(Dispatchers.IO) {
                    if (alarm.repeat.not()) {
                        repository.deleteAlarm(alarm)
                    }
                }
                context.sendBroadcast(Intent("sync"))
            }
        }
    }
}
