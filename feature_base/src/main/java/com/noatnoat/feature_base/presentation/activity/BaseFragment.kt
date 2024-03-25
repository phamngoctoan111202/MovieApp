package com.noatnoat.feature_base.presentation.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import timber.log.Timber

open class BaseFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("onCreate ${javaClass.simpleName}") // Ghi lại thông báo khi fragment được tạo ra
    }
}

//Là một lớp mở rộng từ Fragment.
//Trong phương thức onCreate(), sử dụng Timber.d() để ghi lại thông báo khi một fragment được tạo ra.
