package com.italo.remind_me.ui.create

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.italo.remind_me.alarm.AlarmHelper
import com.italo.remind_me.data.Alarm
import com.italo.remind_me.databinding.CreateFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar

@AndroidEntryPoint
class CreateFragment() : Fragment() {

    private var _binding: CreateFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CreateViewModel by viewModels()

    private var selectedCalendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        setHasOptionsMenu(true)
        _binding = CreateFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            alarmInsert.observe(
                viewLifecycleOwner,
                {
                    println(it)
                    createAlarm(it)
                    view.findNavController().navigateUp()
                }
            )
        }
        binding.btnSaveAlarm.setOnClickListener {
            with(viewModel) {
                insertAlarm(getAlarm())
            }
        }
        binding.tilAlertTime.editText?.setOnClickListener {
            showTimePicker()
        }
    }

    private fun getAlarm(): Alarm {
        return Alarm(
            id = 0,
            name = binding.tilAlertTitle.editText?.text.toString(),
            triggerTime = selectedCalendar.time.time
        )
    }

    private fun createAlarm(it: Alarm) {
        AlarmHelper.scheduleRTC(
            requireContext(),
            AlarmHelper.getAlarmManager(requireContext()),
            it,
            false
        )
    }

    private fun showTimePicker() {
        val calendar: Calendar = Calendar.getInstance()
        val mHour = calendar.get(Calendar.HOUR_OF_DAY)
        val mMinute = calendar.get(Calendar.MINUTE)
        val sdf = SimpleDateFormat("HH:mm")
        val timePickerDialog = TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->
                selectedCalendar.apply {
                    set(Calendar.HOUR_OF_DAY, hourOfDay)
                    set(Calendar.MINUTE, minute)
                    set(Calendar.SECOND, 0)
                }
                binding.tilAlertTime.editText?.setText(sdf.format(selectedCalendar.time))
            },
            mHour,
            mMinute,
            true
        )
        timePickerDialog.show()
    }
}
