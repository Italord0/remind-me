package com.italo.remind_me.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.italo.remind_me.broadcast.AlarmBroadcastReceiver
import com.italo.remind_me.data.Alarm

object AlarmHelper {
    fun scheduleRTC(
        context: Context,
        alarmManager: AlarmManager,
        alarm: Alarm,
        repeat: Boolean,
    ) {
        val intent = Intent(context, AlarmBroadcastReceiver::class.java)
        println(alarm)
        intent.putExtra("ALARM_ID", alarm.id)
        intent.putExtra("ALARM_NAME", alarm.name)

        val pendingIntent =
            PendingIntent.getBroadcast(context, (0..2147483647).random(), intent, 0)

        if (repeat) {
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                alarm.triggerTime,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        } else {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC,
                alarm.triggerTime,
                pendingIntent
            )
        }

        Log.i("alarmHelper", "alarm criado para tempo em mils: ${alarm.triggerTime}")
    }

    fun getAlarmManager(context: Context): AlarmManager {
        return context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }
}
