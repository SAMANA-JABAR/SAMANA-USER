package com.aditprayogo.samana_user.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.aditprayogo.core.data.remote.response.AuthResponse
import com.aditprayogo.core.state.LoaderState
import com.aditprayogo.samana_user.databinding.ActivityLoginBinding
import com.aditprayogo.samana_user.presentation.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initObservers()
        initBtn()
    }

    private fun initBtn() {
        binding.btnLogin.setOnClickListener {
            val nik = binding.outlinedTextFieldNik.editText?.text.toString()
            val password = binding.outlinedTextFieldPassword.editText?.text.toString()
            loginViewModel.login(nik, password)
        }
    }

    private fun initObservers() {
        with(loginViewModel) {
            state.observe(this@LoginActivity, {
                handleLoaderState(it)
            })
            error.observe(this@LoginActivity, {
                Toast.makeText(this@LoginActivity, it.toString(), Toast.LENGTH_SHORT).show()
            })
            networkError.observe(this@LoginActivity, {
                Toast.makeText(
                    this@LoginActivity,
                    "Please Retry your internet connection",
                    Toast.LENGTH_SHORT
                ).show()
            })
            loginData.observe(this@LoginActivity, {
                lifecycleScope.launch {
                    saveUserPreferences(
                        it.nik,
                        it.password
                    )
                }
                Toast.makeText(this@LoginActivity, "Login Berhasil ${it.nik}", Toast.LENGTH_SHORT)
                    .show()
                Log.d("dataPASDASD", "initObservers: ${it.nik}, ${it.password}")
            })
        }
    }

    private fun handleLoaderState(it: LoaderState) {
        if (it is LoaderState.ShowLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
        }
    }
}