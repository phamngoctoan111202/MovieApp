package com.noatnoat.feature_profile.presentation.screen.profilescreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noatnoat.feature_profile.domain.model.User
import com.noatnoat.feature_profile.domain.usecase.GetUserInfoUseCase
import kotlinx.coroutines.launch

class ProfileViewModel(private val getUserInfoUseCase: GetUserInfoUseCase) : ViewModel() {
    internal val user: MutableLiveData<User> = MutableLiveData()

    fun getUserInfo() {
        viewModelScope.launch {
            val userInfo = getUserInfoUseCase.invoke()
            user.value = userInfo
        }
    }
}
