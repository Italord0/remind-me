package com.italo.remind_me.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.italo.remind_me.dao.AlarmDao
import com.italo.remind_me.data.Alarm

@Database(entities = [Alarm::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun alarmDao(): AlarmDao
}
