package com.noatnoat.feature_base.presentation.nav

import android.util.Log
import androidx.navigation.NavDirections

class NavManager {
    private var navEventListener: ((navDirections: NavDirections) -> Unit)? = null

    fun navigate(navDirections: NavDirections): Boolean {

        var isNavigationSuccessful = false
        navEventListener?.let {
            it.invoke(navDirections)
            isNavigationSuccessful = true
            Log.d("11111", "Navigation successful")
        } ?: Log.d("11111", "Navigation failed: navEventListener is null")

        return isNavigationSuccessful
    }

    fun setOnNavEvent(navEventListener: (navDirections: NavDirections) -> Unit) {
        this.navEventListener = navEventListener
    }
}
