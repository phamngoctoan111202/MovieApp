package com.noatnoat.feature_base.data.auth

import android.content.Context

class SharedPreferencesUserManager(private val context: Context) : UserManager {
    private val sharedPreferences = context.getSharedPreferences("MyApp", Context.MODE_PRIVATE)

    override fun getUserId(): String? {
        return sharedPreferences.getString("userId", null)
    }
}