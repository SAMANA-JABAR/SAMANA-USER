package com.aditprayogo.samana_user.presentation.change_profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aditprayogo.samana_user.R
import com.aditprayogo.samana_user.databinding.FragmentChangeProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangeProfileFragment : Fragment() {

    private val binding : FragmentChangeProfileBinding by lazy {
        FragmentChangeProfileBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

}