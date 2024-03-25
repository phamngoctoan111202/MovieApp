package com.noatnoat.movieapp.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.noatnoat.movieapp.R
import com.noatnoat.movieapp.databinding.RegisterLayoutBinding

class ActivityRegister : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        val binding: RegisterLayoutBinding = DataBindingUtil.setContentView(this, R.layout.register_layout)

        binding.buttonRegister.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            val userName = binding.editTextUserName.text.toString()

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

                createAccount(email, password, userName)
//                finish()
            }
        }

        binding.backButton.setOnClickListener {
            finish()
        }

    }



    private fun createAccount(email: String, password: String, userName: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val userId = task.result?.user?.uid

                    val db = FirebaseFirestore.getInstance()
                    val userMap = hashMapOf(
                        "userId" to userId,
                        "userName" to userName,
                        "email" to email,
                        // Không nên lưu trữ mật khẩu trong Firestore vì lý do bảo mật
                        "favouriteMovies" to arrayListOf<String>(),
                        "watchedMovies" to arrayListOf<String>(),
                        "searchedMovies" to arrayListOf<String>(),
                        "recommendedMovies" to arrayListOf<String>()
                    )

                    Log.d("test", "createAccount: RUN")
                    // Sử dụng userId!! với cẩn thận để tránh NullPointerException
                    db.collection("users")
                        .document(userId!!)
                        .set(userMap)
                        .addOnSuccessListener {
                            Toast.makeText(
                                baseContext,
                                resources.getString(R.string.registration_successful),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(
                                baseContext,
                                resources.getString(R.string.registration_failed),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                } else {
                    Toast.makeText(
                        baseContext,
                        resources.getString(R.string.authentication_failed),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }


}
