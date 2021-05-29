package com.aditprayogo.samana_user.presentation.change_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.aditprayogo.core.data.UserPreferences
import com.aditprayogo.core.state.LoaderState
import com.aditprayogo.core.utils.showAlertDialog
import com.aditprayogo.core.utils.startNewActivity
import com.aditprayogo.samana_user.databinding.FragmentChangePasswordBinding
import com.aditprayogo.samana_user.presentation.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ChangePasswordFragment : Fragment() {

    private val binding : FragmentChangePasswordBinding by lazy {
        FragmentChangePasswordBinding.inflate(layoutInflater)
    }

    private val changePasswordViewModel by viewModels<ChangePasswordViewModel>()

    @Inject
    lateinit var userPreferences: UserPreferences

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
        initNik()
        initObservers()
        initData()
    }

    private fun initNik() {
        lifecycleScope.launch {
            userPreferences.nik.asLiveData().observe(viewLifecycleOwner, {
                nik = it
            })
        }
    }

    private fun initData() {
        with(binding) {
            btnSimpan.setOnClickListener {
                val password = outlinedTextFieldCurrentPassword.editText?.text.toString()
                val newPassword = outlinedTextFieldNewPassword.editText?.text.toString()
                nik?.let { changePasswordViewModel.changePassword(it, password,newPassword) }
            }

        }
    }

    private fun initObservers() {
        with(changePasswordViewModel) {
            state.observe(viewLifecycleOwner, {
                handleLoaderState(it)
            })
            error.observe(viewLifecycleOwner, {
                it?.let { it1 -> activity?.showAlertDialog(it1) }
            })
            networkError.observe(viewLifecycleOwner, {
                activity?.showAlertDialog("Please change your internet connection")
            })
            passwordData.observe(viewLifecycleOwner, { data ->
                data.status?.let { activity?.showAlertDialog(it) }
                lifecycleScope.launch {
                    userPreferences.clear()
                    activity?.startNewActivity(LoginActivity::class.java)
                }
            })
        }
    }

    private fun handleLoaderState(state: LoaderState?) {
        if (state is LoaderState.ShowLoading){
            binding.loadingView.visibility = View.VISIBLE
        } else {
            binding.loadingView.visibility = View.INVISIBLE
        }
    }

}