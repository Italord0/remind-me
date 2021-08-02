package com.italo.remind_me.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.italo.remind_me.R
import com.italo.remind_me.data.Alarm
import com.italo.remind_me.databinding.AlarmItemBinding
import java.text.SimpleDateFormat
import java.util.Date

class AlarmAdapter : RecyclerView.Adapter<AlarmAdapter.ViewHolder>() {

    private var alarms: List<Alarm> = ArrayList()
    val sdf = SimpleDateFormat("HH:mm")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.alarm_item, parent, false)
        val binding = AlarmItemBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val alarm = alarms[position]
        holder.tvAlarmTitle.text = alarm.name
        holder.tvAlarmTime.text = sdf.format(Date(alarm.triggerTime))
    }

    override fun getItemCount() = alarms.size

    fun setData(data: List<Alarm>) {
        alarms = data
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: AlarmItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        val tvAlarmTitle: TextView = itemView.alarmTitle
        val tvAlarmTime: TextView = itemView.alarmTime
    }
}
