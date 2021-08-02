package com.italo.remind_me.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Alarm(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val name: String?,
    val triggerTime: Long,
    val repeat: Boolean,
) : Serializable
