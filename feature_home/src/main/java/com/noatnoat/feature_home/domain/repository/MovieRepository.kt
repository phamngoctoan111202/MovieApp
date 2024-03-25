package com.noatnoat.feature_home.domain.repository

import com.noatnoat.feature_home.domain.model.MovieDetail
import com.noatnoat.feature_home.domain.model.MovieCard

interface MovieRepository {
     suspend fun getFilteredBasicsMovieFromRepository(movieType: String?,
                              year: Int?,
                              categories: String?,
                              country: String?,
                              exclusiveSub: Boolean?,
                              minViews: Int?,
                              limit: Long?) : List<MovieCard>

    suspend fun getMovieDetailFromRepository(movieId: String): MovieDetail

    suspend fun addMovieToFavouriteList(movieId: String)

    suspend fun removeMovieFromFavouriteList(movieId: String)

    suspend fun checkMovieInFavouriteList(movieId: String) : Boolean
}