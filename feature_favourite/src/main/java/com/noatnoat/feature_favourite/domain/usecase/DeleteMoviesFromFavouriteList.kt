package com.noatnoat.feature_favourite.domain.usecase

import com.noatnoat.feature_favourite.domain.repository.FavouriteMoviesRepository

class DeleteMoviesFromFavouriteList(private val movieRepository: FavouriteMoviesRepository) {
    suspend operator fun invoke(movieId: String) {
        movieRepository.removeMovieFromFavouriteList(movieId)
    }
}