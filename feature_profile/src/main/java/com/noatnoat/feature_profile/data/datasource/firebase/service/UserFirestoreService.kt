package com.noatnoat.feature_profile.data.datasource.firebase.service

import com.noatnoat.feature_profile.data.datasource.firebase.model.UserFirestoreModel


interface UserFirestoreService {
   suspend fun getUserInfo() : UserFirestoreModel
}