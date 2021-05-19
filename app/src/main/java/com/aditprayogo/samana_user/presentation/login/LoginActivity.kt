package com.aditprayogo.samana_user.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aditprayogo.samana_user.R
import com.aditprayogo.samana_user.databinding.ActivityLoginBinding
import com.aditprayogo.samana_user.presentation.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener(this)
    }

    private fun validateLogin(): Boolean {
        binding.apply {
            val nikInput = outlinedTextFieldNik.editText?.text.toString()
            val passwordInput = outlinedTextFieldPassword.editText?.text.toString()

            if (!nikInput.equals("Wakwau")) {
                outlinedTextFieldNik.error = "Nik tidak sesuai"
                return false

            }
            if (!passwordInput.equals("Wakwau")) {
                outlinedTextFieldPassword.error = "Password tidak sesuai"
                return false

            }
            if (passwordInput.isEmpty() && nikInput.isEmpty()) {
                outlinedTextFieldPassword.error = "Tolong isi form Password"
                outlinedTextFieldNik.error = "Tolong isi form NIK"
                return false
            }
            if (!nikInput.equals("Wakwau") && !passwordInput.equals("Wakwau")) {
                outlinedTextFieldNik.error = "Nik tidak sesuai"
                outlinedTextFieldPassword.error = "Password tidak sesuai"
                return false
            }
            return true
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnLogin -> {
                if (validateLogin()) {
                    startActivity(Intent(this, HomeActivity::class.java))
                } else {
                    return
                }
            }
        }
    }


}