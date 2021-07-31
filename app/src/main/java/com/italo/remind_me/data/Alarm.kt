package com.italo.remind_me.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Alarm(@PrimaryKey val id: Int, val name: String, val triggerTime: Int)
