package com.noatnoat.movieapp.presentation

import android.app.Application
import android.content.Context
import com.google.android.material.color.DynamicColors
import com.google.firebase.FirebaseApp
import com.noatnoat.feature_base.featureBaseModules
import com.noatnoat.feature_download.FeatureDownloadModule
import com.noatnoat.feature_favourite.FeatureFavouriteModules
import com.noatnoat.feature_home.featureHomeModules
import com.noatnoat.feature_profile.FeatureProfileModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

class MovieApplication : Application() {

    companion object {
        lateinit var applicationContext: Context
    }


    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)

        initKoin()
        initTimber()
        initDynamicColorScheme()
    }

    private fun initDynamicColorScheme() {
        DynamicColors.applyToActivitiesIfAvailable(this)
    }

    private fun initKoin() {
//        GlobalContext.startKoin {
//            androidLogger()
//            androidContext(this@MovieApplication)
////
////            modules(featureHomeModules)
////            modules(baseModule)
//        }

        startKoin {
            androidLogger()
            androidContext(this@MovieApplication)
            modules(featureHomeModules)
            modules(featureBaseModules)
            modules(FeatureFavouriteModules)
            modules(FeatureProfileModule)
            modules(FeatureDownloadModule)
        }

    }

    private fun initTimber() {

    }
}
