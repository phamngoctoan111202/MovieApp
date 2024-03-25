package com.noatnoat.feature_profile.domain.repository

import com.noatnoat.feature_profile.domain.model.User


interface UserRepository {
     suspend fun getUserInfoFromRepository() : User
}