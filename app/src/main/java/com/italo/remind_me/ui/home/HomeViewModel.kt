package com.italo.remind_me.ui.home

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
class HomeViewModel @Inject constructor(
    private val alarmRepository: AlarmRepository
) : ViewModel() {

    private val _alarmLiveData = MutableLiveData<List<Alarm>>()
    val alarmLiveData: LiveData<List<Alarm>>
        get() = _alarmLiveData

    fun getAlarmsFromDb() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = withContext(Dispatchers.IO) {
                alarmRepository.getAlarms()
            }
            _alarmLiveData.postValue(result)
        }
    }
}
