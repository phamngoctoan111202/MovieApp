package com.noatnoat.feature_favourite.domain.usecase

import com.noatnoat.feature_favourite.domain.model.FavouriteMovieCard
import com.noatnoat.feature_favourite.domain.repository.FavouriteMoviesRepository

class GetFavouriteMoviesUsecase(private val favouriteMoviesRepository: FavouriteMoviesRepository) {

    suspend fun invoke(): List<FavouriteMovieCard> {
        return favouriteMoviesRepository.getFavouriteMovies()
    }
}
