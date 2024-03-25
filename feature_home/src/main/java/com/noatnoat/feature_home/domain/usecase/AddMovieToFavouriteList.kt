package com.noatnoat.feature_home.domain.usecase

import com.noatnoat.feature_home.domain.repository.MovieRepository

class AddMovieToFavouriteList(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(movieId: String) {
        movieRepository.addMovieToFavouriteList(movieId)
    }
}