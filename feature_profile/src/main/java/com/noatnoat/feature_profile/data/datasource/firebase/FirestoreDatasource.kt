package com.noatnoat.feature_profile.data.datasource.firebase

import com.noatnoat.feature_profile.data.datasource.firebase.model.UserFirestoreModel
import com.noatnoat.feature_profile.data.datasource.firebase.service.UserFirestoreService
import com.noatnoat.feature_profile.domain.model.User


class FirestoreDatasource(private val userFirestoreService : UserFirestoreService) {
    suspend fun getUserInfo(): User {
        return userFirestoreService.getUserInfo().toDomain()
    }
}