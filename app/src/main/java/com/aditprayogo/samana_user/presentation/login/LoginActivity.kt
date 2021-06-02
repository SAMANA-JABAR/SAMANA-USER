package com.aditprayogo.samana_user.presentation.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isEmpty
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.aditprayogo.core.data.UserPreferences
import com.aditprayogo.core.state.LoaderState
import com.aditprayogo.core.utils.*
import com.aditprayogo.samana_user.R
import com.aditprayogo.samana_user.databinding.ActivityLoginBinding
import com.aditprayogo.samana_user.presentation.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("DEPRECATION")
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
        validateLogin()
    }

    private fun initPreference() {
        userPreferences.nik.asLiveData().observe(this, {
            val activity = if (it == null) LoginActivity::class.java else HomeActivity::class.java
            if (activity == LoginActivity::class.java) {
                return@observe
            } else {
                startNewActivityAndClear(HomeActivity::class.java)
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
                    showAlertDialog(getString(R.string.user_not_found))
                }
            })
            networkError.observe(this@LoginActivity, {
                toast(getString(R.string.cek_connection))
            })
            loginData.observe(this@LoginActivity, { data ->
                lifecycleScope.launch {
                    data.nik?.let { nik ->
                        data.password?.let { password ->
                            loginViewModel.saveUserPreferences(
                                nik,
                                password,
                                data.nama!!
                            )
                        }
                        startNewActivityAndClear(HomeActivity::class.java)
                    }
                }
            })
        }
    }

    private fun performLogin() {
        with(binding) {
            btnLogin.setOnClickListener {
                val nik = outlinedTextFieldNik.editText?.text.toString()
                val password = outlinedTextFieldPassword.editText?.text.toString()
                loginViewModel.login(nik, password)
            }
        }
    }

    private fun validateLogin(){
        with(binding) {
            if (outlinedTextFieldNik.editText?.text.toString().trim().isEmpty()) {
                setButtonToGrey(true)
            }

            if (outlinedTextFieldPassword.editText?.text.toString().trim().isEmpty()) {
                setButtonToGrey(true)
            }
            /**
             * Validate text field nik
             */
            outlinedTextFieldNik.editText?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(text: CharSequence, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(
                    text: CharSequence,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    when (text.isEmpty() || outlinedTextFieldPassword.editText?.text.toString().trim().isEmpty()) {
                        true -> {
                            outlinedTextFieldNik.editText?.requestFocus()
                            setButtonToGrey(true)
                        }
                        false -> {
                            setButtonToGrey(false)
                        }
                    }
                }
                override fun afterTextChanged(p0: Editable?) {}
            })

            /**
             * Validate text field password
             */
            outlinedTextFieldPassword.editText?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(text: CharSequence, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(
                    text: CharSequence,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    when (text.isEmpty() || outlinedTextFieldNik.editText?.text.toString().trim().isEmpty()) {
                        true -> {
                            outlinedTextFieldPassword.editText?.requestFocus()
                            setButtonToGrey(true)
                        }
                        false -> {
                            setButtonToGrey(false)
                        }
                    }
                }
                override fun afterTextChanged(p0: Editable?) {}
            })
        }
    }

    private fun handleLoaderState(it: LoaderState) {
        if (it is LoaderState.ShowLoading) {
            binding.loadingView.setVisible()
            setButtonToGrey(true)
        } else {
            binding.loadingView.setGone()
            setButtonToGrey(false)
        }
    }

    private fun setButtonToGrey(state: Boolean) {
        with(binding) {
            btnLogin.apply {
                if (state) {
                    setBackgroundColor(resources.getColor(R.color.colorShimmer))
                    setTextColor(resources.getColor(R.color.white))
                    isEnabled = false

                } else {
                    setBackgroundColor(resources.getColor(R.color.bluePrimary))
                    btnLogin.setTextColor(resources.getColor(R.color.white))
                    btnLogin.isEnabled = true
                }
            }
        }
    }
}