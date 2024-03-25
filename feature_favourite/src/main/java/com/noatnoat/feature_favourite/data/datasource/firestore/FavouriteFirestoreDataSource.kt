package com.noatnoat.feature_favourite.data.datasource.firestore

import com.noatnoat.feature_favourite.data.datasource.firestore.service.FavouriteMovieFirestoreService
import com.noatnoat.feature_favourite.domain.model.FavouriteMovieCard
import com.noatnoat.feature_favourite.domain.model.FavouriteMovieDetail

class FavouriteFirestoreDataSource(private val favouriteMovieFirestoreService: FavouriteMovieFirestoreService) {

    suspend fun getFavouriteMovies(): List<FavouriteMovieCard> {
        return favouriteMovieFirestoreService.fetchFavouriteMoviesFromFirestore()
            .map { it.toDomainModel() }
    }

    suspend fun getFavouriteDetailedMovie(movieId: String): FavouriteMovieDetail {
        return favouriteMovieFirestoreService.fetchDetailedMovieInfoFromFirestore(movieId).toDomainModel()
    }

    suspend fun addMovietoFavouriteList(movieId: String) {
        favouriteMovieFirestoreService.addMovietoFavouriteList(movieId)
    }

    suspend fun removeMovieFromFavouriteList(movieId: String) {
        favouriteMovieFirestoreService.removeMovieFromFavouriteList(movieId)
    }

    suspend fun checkMovieInFavouriteList(movieId: String) : Boolean {
        return favouriteMovieFirestoreService.isMovieInFavouriteList(movieId)
    }


}
