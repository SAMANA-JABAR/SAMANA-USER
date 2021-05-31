package com.aditprayogo.samana_user.presentation.social_assistance

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.aditprayogo.core.data.UserPreferences
import com.aditprayogo.core.domain.model.DashboardData
import com.aditprayogo.core.state.LoaderState
import com.aditprayogo.samana_user.R
import com.aditprayogo.samana_user.databinding.FragmentSocialAssistanceBinding
import com.aditprayogo.samana_user.presentation.input_assistance.InputDataActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SocialAssistanceFragment : Fragment() {

    private val binding: FragmentSocialAssistanceBinding by lazy {
        FragmentSocialAssistanceBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var userPreferences : UserPreferences

    private val socialAssistanceViewModel by viewModels<SocialAssistanceViewModel>()

    private var nik : String? = null

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
        swipeToFetchData()
        initObservers()

    }

    private fun swipeToFetchData() {
        binding.refreshLayout.setOnRefreshListener {
            nik?.let { socialAssistanceViewModel.getUserDashboard(it) }
            binding.refreshLayout.isRefreshing = false
        }
    }

    private fun initObservers() {
        with(socialAssistanceViewModel) {
            dashboardData.observe(viewLifecycleOwner, {
                handleDashboardData(it)
            })
        }
    }

    private fun handleDashboardData(data: DashboardData) {
        with(binding) {
            txtName.text = data.nama
            txtNik.text = nik
            txtStatusInfo.text = data.status
        }
    }

    private fun initUi() {
        with(binding) {
            lifecycleScope.launch {
                userPreferences.nik.asLiveData().observe(viewLifecycleOwner, {
                    nik = it
                    nik?.let { socialAssistanceViewModel.getUserDashboard(it) }
                })
            }
            cardCekHistory.setOnClickListener {
               findNavController().navigate(R.id.action_socialAssistanceFragment_to_historyAssistanceFragment)
            }
            cardInput.setOnClickListener {
                activity?.startActivity(Intent(context, InputDataActivity::class.java))
            }
        }
    }

}