package com.aditprayogo.samana_user.presentation.social_assistance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.aditprayogo.samana_user.R
import com.aditprayogo.samana_user.databinding.FragmentSocialAssistanceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SocialAssistanceFragment : Fragment() {

    private val binding: FragmentSocialAssistanceBinding by lazy {
        FragmentSocialAssistanceBinding.inflate(layoutInflater)
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
        initUi()
    }

    private fun initUi() {
        with(binding) {
            cardCekHistory.setOnClickListener {
                val directions =
                    SocialAssistanceFragmentDirections.actionSocialAssistanceFragmentToHistoryAssistanceFragment()
                findNavController().navigate(directions)
            }
            cardInput.setOnClickListener {
                val directions =
                    SocialAssistanceFragmentDirections.actionSocialAssistanceFragmentToInputAssistanceFragment()
                findNavController().navigate(directions)
            }
        }
    }

}