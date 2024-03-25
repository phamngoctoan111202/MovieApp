package com.noatnoat.feature_favourite.domain.usecase

import com.noatnoat.feature_favourite.domain.model.FavouriteMovieCard
import com.noatnoat.feature_favourite.domain.model.FavouriteMovieDetail
import com.noatnoat.feature_favourite.domain.repository.FavouriteMoviesRepository

class GetFavouriteMovieDetailUsecase(private val favouriteMoviesRepository: FavouriteMoviesRepository) {

    suspend operator fun invoke(movieId : String): FavouriteMovieDetail {
        return favouriteMoviesRepository.getMovieDetailFromRepository(movieId)
    }
}