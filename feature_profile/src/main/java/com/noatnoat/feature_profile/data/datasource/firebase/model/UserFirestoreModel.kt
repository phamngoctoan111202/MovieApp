package com.noatnoat.feature_profile.data.datasource.firebase.model

import com.noatnoat.feature_profile.domain.model.User

class UserFirestoreModel (
    var userId: String = "",
    var userName: String = "",
    var email: String = "",
    var avatarUrl: String = ""
) {
    fun toDomain(): User {
        return User(
            userId = this.userId,
            userName = this.userName,
            email = this.email,
            avatarUrl = this.avatarUrl
        )
    }
}
