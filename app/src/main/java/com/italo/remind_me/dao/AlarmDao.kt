package com.italo.remind_me.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.italo.remind_me.data.Alarm

@Dao
interface AlarmDao {

    @Query("SELECT * FROM alarm")
    fun getAll(): List<Alarm>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAlarm(alarm: Alarm)

    @Delete
    fun deleteAlarm(alarm: Alarm)
}
