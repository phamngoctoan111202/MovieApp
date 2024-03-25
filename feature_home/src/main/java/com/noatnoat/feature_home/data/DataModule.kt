package com.noatnoat.feature_home.data


import com.noatnoat.feature_home.data.datasource.firestore.FirestoreDataSource
import com.noatnoat.feature_home.data.datasource.firestore.service.MovieFirestoreService
import com.noatnoat.feature_home.data.datasource.firestore.service.MovieFirestoreServiceImpl
import com.noatnoat.feature_home.data.repository.MovieRepositoryImpl
import com.noatnoat.feature_home.domain.repository.MovieRepository
import org.koin.dsl.module


internal val dataModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
//    single {
//        Room.databaseBuilder(
//            get(),
//            AlbumDatabase::class.java,
//            "Albums.db",
//        ).build()
//    }
    single { FirestoreDataSource(get()) }

    single<MovieFirestoreService> { MovieFirestoreServiceImpl(get()) }
//    single { get<MovieDatabase>().albums() }
}