package com.noatnoat.feature_profile.domain.usecase

import com.noatnoat.feature_profile.domain.model.User
import com.noatnoat.feature_profile.domain.repository.UserRepository

class GetUserInfoUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(): User {
        return userRepository.getUserInfoFromRepository()
    }
}