package com.noatnoat.feature_profile.data.datasource

import com.noatnoat.feature_profile.data.datasource.firebase.FirestoreDatasource
import com.noatnoat.feature_profile.domain.model.User
import com.noatnoat.feature_profile.domain.repository.UserRepository

class UserRepositoryImpl( private val dataSource: FirestoreDatasource): UserRepository {

    override suspend fun getUserInfoFromRepository(): User {
        return dataSource.getUserInfo()
    }
}