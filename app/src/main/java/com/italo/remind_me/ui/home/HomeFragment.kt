package com.italo.remind_me.ui.home

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.italo.remind_me.R
import com.italo.remind_me.adapter.AlarmAdapter
import com.italo.remind_me.broadcast.AlarmBroadcastReceiver
import com.italo.remind_me.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment() : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private val syncReceiver = object : AlarmBroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            super.onReceive(context, intent)
            viewModel.getAlarmsFromDb()
        }
    }

    private val alarmAdapter: AlarmAdapter by lazy {
        AlarmAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.registerReceiver(syncReceiver, IntentFilter("sync"))
        binding.alertRecyclerView.adapter = alarmAdapter
        binding.animationView.setAnimation("animations/animation_clock.json")
        viewModel.alarmLiveData.observe(
            viewLifecycleOwner,
            {
                if (it.isEmpty()) {
                    binding.animationView.isVisible = true
                    binding.emptyText.isVisible = true
                } else {
                    binding.animationView.isVisible = false
                    binding.emptyText.isVisible = false
                }
                alarmAdapter.setData(it)
            }
        )
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.createFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAlarmsFromDb()
    }
}
