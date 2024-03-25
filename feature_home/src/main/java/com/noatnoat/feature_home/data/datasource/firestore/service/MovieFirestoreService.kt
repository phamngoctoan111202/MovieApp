package com.noatnoat.feature_home.data.datasource.firestore.service

import com.noatnoat.feature_home.data.datasource.firestore.model.MovieDetailFirestoreModel
import com.noatnoat.feature_home.data.datasource.firestore.model.BasicMovieFirestoreModel

interface MovieFirestoreService {
    suspend fun fetchBasicMovieInfoFromFirestore(movieType: String?,
                          year: Int?,
                          categories: String?,
                          country: String?,
                          exclusiveSub: Boolean?,
                          minViews: Int?,
                          limit: Long?): List<BasicMovieFirestoreModel>

    suspend fun fetchDetailedMovieInfoFromFirestore(movieId: String): MovieDetailFirestoreModel

    suspend fun addMovietoFavouriteList(movieId: String)

    suspend fun removeMovieFromFavouriteList(movieId: String)

    suspend fun isMovieInFavouriteList(movieId: String): Boolean
}