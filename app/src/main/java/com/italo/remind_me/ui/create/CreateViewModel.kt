package com.italo.remind_me.ui.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.italo.remind_me.data.Alarm
import com.italo.remind_me.data.AlarmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor(
    private val alarmRepository: AlarmRepository,
) : ViewModel() {

    private val _alarmInsert = MutableLiveData<Alarm>()
    val alarmInsert: LiveData<Alarm>
        get() = _alarmInsert

    fun insertAlarm(alarm: Alarm) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                alarm.id = alarmRepository.insertAlarm(alarm).toInt()
                _alarmInsert.postValue(alarm)
            }
        }
    }
}
