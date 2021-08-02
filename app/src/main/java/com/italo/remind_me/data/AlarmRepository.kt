package com.italo.remind_me.data

import com.italo.remind_me.dao.AlarmDao
import javax.inject.Inject

class AlarmRepository @Inject constructor(
    private val dao: AlarmDao,
) {
    fun insertAlarm(alarm: Alarm): Long {
        return dao.insertAlarm(alarm)
    }

    fun getAlarms() = dao.getAll()

    fun deleteAlarm(alarm: Alarm) = dao.deleteAlarm(alarm)
}
