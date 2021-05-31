package com.aditprayogo.samana_user.presentation.input_assistance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aditprayogo.samana_user.R
import com.aditprayogo.samana_user.databinding.FragmentInputAssistanceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputAssistanceFragment : Fragment() {

    private val binding : FragmentInputAssistanceBinding by lazy {
        FragmentInputAssistanceBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        with(binding) {
            etDate.setText("ASDASDASD")
            etDate.isEnabled = false
        }
    }
}