package com.noatnoat.feature_profile.data

import com.noatnoat.feature_profile.data.datasource.UserRepositoryImpl
import com.noatnoat.feature_profile.data.datasource.firebase.FirestoreDatasource
import com.noatnoat.feature_profile.data.datasource.firebase.service.UserFirestoreService
import com.noatnoat.feature_profile.data.datasource.firebase.service.UserFirestoreServiceImpl
import com.noatnoat.feature_profile.domain.repository.UserRepository
import org.koin.dsl.module


internal val dataModule = module {
    single<UserFirestoreService> { UserFirestoreServiceImpl(get()) }

    single<UserRepository> { UserRepositoryImpl(get()) }

    single { FirestoreDatasource(get()) }


}
