package com.noatnoat.feature_favourite.domain.usecase

import com.noatnoat.feature_favourite.domain.repository.FavouriteMoviesRepository


class CheckMovieInFavouriteList(private val movieRepository: FavouriteMoviesRepository) {
    suspend operator fun invoke(movieId: String): Boolean {
        return movieRepository.checkMovieInFavouriteList(movieId)
    }
}