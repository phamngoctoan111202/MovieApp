package com.noatnoat.feature_favourite.data.datasource.firestore.service

import com.noatnoat.feature_favourite.data.datasource.firestore.model.FavouriteMovieCardFirestoreModel
import com.noatnoat.feature_favourite.data.datasource.firestore.model.FavouriteMovieDetailFirestoreModel

interface FavouriteMovieFirestoreService {
    suspend fun fetchFavouriteMoviesFromFirestore() : List<FavouriteMovieCardFirestoreModel>
    suspend fun fetchDetailedMovieInfoFromFirestore(movieId: String): FavouriteMovieDetailFirestoreModel


    suspend fun addMovietoFavouriteList(movieId: String)

    suspend fun removeMovieFromFavouriteList(movieId: String)

    suspend fun isMovieInFavouriteList(movieId: String): Boolean
}