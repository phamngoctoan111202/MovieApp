package com.noatnoat.feature_home.domain.usecase

import com.noatnoat.feature_home.domain.model.MovieCard
import com.noatnoat.feature_home.domain.repository.MovieRepository

class GetMoviesByGenreUseCase(
    private val movieRepository: MovieRepository

) {

    suspend fun invoke(movieType: String?,
                       year: Int?,
                       categories: String?,
                       country: String?,
                       exclusiveSub: Boolean?,
                       minViews: Int?,
                       limit: Long?): List<MovieCard> {

        return movieRepository.getFilteredBasicsMovieFromRepository(movieType, year, categories, country, exclusiveSub, minViews, limit)
    }
}



