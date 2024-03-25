package com.noatnoat.feature_base.data

import com.noatnoat.feature_base.data.auth.SharedPreferencesUserManager
import com.noatnoat.feature_base.data.auth.UserManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


internal val dataModule = module {
    single<UserManager> { SharedPreferencesUserManager(androidContext()) }
}
