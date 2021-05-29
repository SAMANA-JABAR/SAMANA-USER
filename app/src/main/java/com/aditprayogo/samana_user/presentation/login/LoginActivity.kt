package com.aditprayogo.samana_user.presentation.login

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.aditprayogo.core.data.UserPreferences
import com.aditprayogo.core.state.LoaderState
import com.aditprayogo.core.utils.showAlertDialog
import com.aditprayogo.core.utils.startNewActivity
import com.aditprayogo.core.utils.toast
import com.aditprayogo.samana_user.databinding.ActivityLoginBinding
import com.aditprayogo.samana_user.presentation.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val loginViewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initObservers()
        performLogin()
        initPreference()
    }

    private fun initPreference() {
        userPreferences.nik.asLiveData().observe(this, {
            val activity = if (it == null) LoginActivity::class.java else HomeActivity::class.java
            if (activity == LoginActivity::class.java) {
                return@observe
            } else {
                startNewActivity(HomeActivity::class.java)
            }
        })
    }

    private fun initObservers() {
        with(loginViewModel) {
            state.observe(this@LoginActivity, {
                handleLoaderState(it)
            })
            error.observe(this@LoginActivity, { errorResponse ->
                errorResponse?.let {
                    showAlertDialog("User tidak ditemukan")
                }
            })
            networkError.observe(this@LoginActivity, {
                toast("Please Retry your connection")
            })
            loginData.observe(this@LoginActivity, { data ->
                lifecycleScope.launch {
                    data.nik?.let { nik ->
                        data.password?.let { password ->
                            loginViewModel.saveUserPreferences(
                                nik,
                                password
                            )
                        }
                    }
                    data?.nik?.let {
                        startNewActivity(HomeActivity::class.java)
                    }
                }
            })
        }
    }

    private fun performLogin() {
        binding.btnLogin.setOnClickListener {
            val nik = binding.outlinedTextFieldNik.editText?.text.toString()
            val password = binding.outlinedTextFieldPassword.editText?.text.toString()
            loginViewModel.login(nik, password)
        }
    }

    private fun handleLoaderState(it: LoaderState) {
        if (it is LoaderState.ShowLoading) {
            binding.loadingView.visibility = View.VISIBLE
        } else {
            binding.loadingView.visibility = View.INVISIBLE
        }
    }
}