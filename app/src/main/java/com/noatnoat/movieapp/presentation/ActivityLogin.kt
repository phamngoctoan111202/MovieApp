package com.noatnoat.movieapp.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.noatnoat.movieapp.R
import com.noatnoat.movieapp.databinding.LoginLayoutBinding
import com.noatnoat.movieapp.databinding.RegisterLayoutBinding

class ActivityLogin : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        val userId = sharedPref.getString("userId", null)

        if (userId != null) {
            val intent = Intent(this, NavHostActivity::class.java)
            startActivity(intent)
            finish()
            return
        }


        val binding: LoginLayoutBinding = DataBindingUtil.setContentView(this, R.layout.login_layout)
        auth = FirebaseAuth.getInstance()

        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(
                    baseContext,
                    "Email hoặc mật khẩu không được để trống.",
                    Toast.LENGTH_SHORT,
                ).show()
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(
                    baseContext,
                    "Định dạng email không hợp lệ.",
                    Toast.LENGTH_SHORT,
                ).show()
            } else if (password.length < 6) {
                Toast.makeText(
                    baseContext,
                    "Mật khẩu phải có ít nhất 6 ký tự.",
                    Toast.LENGTH_SHORT,
                ).show()
            } else {
                signIn(email, password)
            }
        }

        binding.textViewRegister.setOnClickListener {
            val intent = Intent(this, ActivityRegister::class.java)
            startActivity(intent)
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
                    with (sharedPref.edit()) {
                        putString("userId", auth.currentUser?.uid)
                        apply()
                    }

                    Toast.makeText(
                        baseContext,
                        resources.getString(R.string.login_successful),
                        Toast.LENGTH_SHORT,
                    ).show()

                    val intent = Intent(this, NavHostActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        baseContext,
                        resources.getString(R.string.login_failed),
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }


}
