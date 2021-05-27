package com.aditprayogo.samana_user.presentation.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.aditprayogo.core.data.UserPreferences
import com.aditprayogo.core.utils.startNewActivity
import com.aditprayogo.samana_user.R
import com.aditprayogo.samana_user.databinding.FragmentProfileBinding
import com.aditprayogo.samana_user.presentation.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val binding: FragmentProfileBinding by lazy {
        FragmentProfileBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var userPreferences: UserPreferences

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
        initPreferences()
    }

    private fun initPreferences() {
        binding.btnLogout.setOnClickListener {
            lifecycleScope.launch {
                userPreferences.clear()
                activity?.startNewActivity(LoginActivity::class.java)
            }
        }
    }

    private fun initUi() {
        with(binding) {
            cardChangePassword.setOnClickListener {
                val direction =
                    ProfileFragmentDirections.actionProfileFragmentToChangeProfileFragment()
                findNavController().navigate(direction)
            }

        }
    }

}