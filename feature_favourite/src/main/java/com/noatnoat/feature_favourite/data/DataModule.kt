package com.noatnoat.feature_favourite.data

import com.noatnoat.feature_favourite.data.datasource.firestore.FavouriteFirestoreDataSource
import com.noatnoat.feature_favourite.data.datasource.firestore.service.FavouriteMovieFirestoreService
import com.noatnoat.feature_favourite.data.datasource.firestore.service.FavouriteMovieFirestoreServiceImpl
import com.noatnoat.feature_favourite.data.repository.FavouriteMoviesRepositoryImpl
import com.noatnoat.feature_favourite.domain.repository.FavouriteMoviesRepository
import org.koin.dsl.module

internal val dataModule = module {
    single<FavouriteMoviesRepository> { FavouriteMoviesRepositoryImpl(get()) }

    single { FavouriteFirestoreDataSource(get()) }

    single<FavouriteMovieFirestoreService> { FavouriteMovieFirestoreServiceImpl(get()) }

}
