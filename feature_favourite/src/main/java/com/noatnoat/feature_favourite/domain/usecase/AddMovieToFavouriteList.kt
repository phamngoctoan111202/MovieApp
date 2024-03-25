package com.noatnoat.feature_favourite.domain.usecase

import com.noatnoat.feature_favourite.domain.repository.FavouriteMoviesRepository

class AddMovieToFavouriteList(private val movieRepository: FavouriteMoviesRepository) {
    suspend operator fun invoke(movieId: String) {
        movieRepository.addMovieToFavouriteList(movieId)
    }
}