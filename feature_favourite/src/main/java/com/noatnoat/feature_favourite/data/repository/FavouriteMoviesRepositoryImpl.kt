package com.noatnoat.feature_favourite.data.repository

import com.noatnoat.feature_favourite.data.datasource.firestore.FavouriteFirestoreDataSource
import com.noatnoat.feature_favourite.domain.model.FavouriteMovieCard
import com.noatnoat.feature_favourite.domain.model.FavouriteMovieDetail
import com.noatnoat.feature_favourite.domain.repository.FavouriteMoviesRepository


class FavouriteMoviesRepositoryImpl(
    private val dataSource: FavouriteFirestoreDataSource
) : FavouriteMoviesRepository {

     override suspend fun getFavouriteMovies(): List<FavouriteMovieCard> {
        return dataSource.getFavouriteMovies()
    }

    override suspend fun getMovieDetailFromRepository(movieId: String): FavouriteMovieDetail {
        return dataSource.getFavouriteDetailedMovie(movieId)
    }

    override suspend fun addMovieToFavouriteList(movieId: String) {
        dataSource.addMovietoFavouriteList(movieId)
    }

    override suspend fun removeMovieFromFavouriteList(movieId: String) {
        dataSource.removeMovieFromFavouriteList(movieId)
    }

    override suspend fun checkMovieInFavouriteList(movieId: String) : Boolean {
        return dataSource.checkMovieInFavouriteList(movieId)
    }



}
