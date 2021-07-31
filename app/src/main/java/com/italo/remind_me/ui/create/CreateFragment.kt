package com.italo.remind_me.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.italo.remind_me.databinding.CreateFragmentBinding

class CreateFragment : Fragment() {

    private var _binding: CreateFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CreateViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CreateFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}
