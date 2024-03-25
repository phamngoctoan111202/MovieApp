package com.noatnoat.feature_home.domain.usecase

import com.noatnoat.feature_home.domain.repository.MovieRepository

class DeleteMoviesFromFavouriteList(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(movieId: String) {
        movieRepository.removeMovieFromFavouriteList(movieId)
    }
}