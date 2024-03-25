package com.noatnoat.feature_profile.data.datasource.firebase.service

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.noatnoat.feature_base.data.firebase.constants.FirebaseConstants
import com.noatnoat.feature_profile.data.datasource.firebase.model.UserFirestoreModel
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


class UserFirestoreServiceImpl(val context : Context) : UserFirestoreService {
    private val db = FirebaseFirestore.getInstance()
    private val userCollection = db.collection(FirebaseConstants.USERS_COLLECTION) // Thay đổi từ MOVIES_COLLECTION sang "users"


    override suspend fun getUserInfo(): UserFirestoreModel {
        val sharedPref = context.getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        val userId = sharedPref.getString("userId", null)
        return userId?.let { fetchUserFirestoreModel(it) } ?: UserFirestoreModel()
    }

    private suspend fun fetchUserFirestoreModel(userId: String): UserFirestoreModel? {
        return suspendCancellableCoroutine { continuation ->
            userCollection.whereEqualTo("userId", userId).get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        val userFirestoreModel = document.toObject(UserFirestoreModel::class.java)
                        continuation.resume(userFirestoreModel)
                    }
                }
                .addOnFailureListener { e ->
                    continuation.resumeWithException(e)
                }
        }
    }

}
