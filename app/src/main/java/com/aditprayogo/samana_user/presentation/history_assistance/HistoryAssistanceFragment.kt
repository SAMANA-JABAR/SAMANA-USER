package com.aditprayogo.samana_user.presentation.history_assistance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aditprayogo.samana_user.R
import com.aditprayogo.samana_user.databinding.FragmentHistoryAssistanceBinding

class HistoryAssistanceFragment : Fragment() {

    private val binding : FragmentHistoryAssistanceBinding by lazy {
        FragmentHistoryAssistanceBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

}