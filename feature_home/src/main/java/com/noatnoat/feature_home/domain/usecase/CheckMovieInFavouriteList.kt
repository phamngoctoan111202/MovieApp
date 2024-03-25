package com.noatnoat.feature_home.domain.usecase

import com.noatnoat.feature_home.domain.repository.MovieRepository

class CheckMovieInFavouriteList(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(movieId: String): Boolean {
        return movieRepository.checkMovieInFavouriteList(movieId)
    }
}