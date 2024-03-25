package com.noatnoat.feature_home.domain.usecase

import com.noatnoat.feature_home.domain.model.MovieDetail
import com.noatnoat.feature_home.domain.repository.MovieRepository

class GetMovieDetailsUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(movieId: String): MovieDetail {
        return movieRepository.getMovieDetailFromRepository(movieId)
    }
}
