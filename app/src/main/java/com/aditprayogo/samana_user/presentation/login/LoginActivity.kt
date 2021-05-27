package com.aditprayogo.samana_user.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.aditprayogo.core.state.LoaderState
import com.aditprayogo.core.utils.toast
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
        performLogin()
    }

    private fun initObservers() {
        with(loginViewModel) {
            state.observe(this@LoginActivity, {
                handleLoaderState(it)
            })
            error.observe(this@LoginActivity, {
                it?.let { it1 -> toast(it1) }
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
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    }
                    showDialog()
                }
            })
        }
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle("Error")
            setMessage("User Credential not found")
            setPositiveButton(
                "Ok"
            ) { dialog, id ->
                // User clicked Update Now button
                toast("Ok")
            }
            show()
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