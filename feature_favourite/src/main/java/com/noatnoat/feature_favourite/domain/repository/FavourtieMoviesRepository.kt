package com.noatnoat.feature_favourite.domain.repository

import com.noatnoat.feature_favourite.domain.model.FavouriteMovieCard
import com.noatnoat.feature_favourite.domain.model.FavouriteMovieDetail

interface FavouriteMoviesRepository {
    suspend fun getFavouriteMovies(): List<FavouriteMovieCard>

    suspend fun getMovieDetailFromRepository(movieId: String): FavouriteMovieDetail

    suspend fun addMovieToFavouriteList(movieId: String)

    suspend fun removeMovieFromFavouriteList(movieId: String)

    suspend fun checkMovieInFavouriteList(movieId: String) : Boolean
}