package com.noatnoat.feature_home.data.repository

import com.noatnoat.feature_home.domain.model.MovieDetail
import com.noatnoat.feature_home.data.datasource.firestore.FirestoreDataSource
import com.noatnoat.feature_home.domain.model.MovieCard
import com.noatnoat.feature_home.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val firestoreDataSource: FirestoreDataSource,
//    private val roomDataSource: MovieDatabase
) : MovieRepository {

    override suspend fun getFilteredBasicsMovieFromRepository(
        movieType: String?,
        year: Int?,
        categories: String?,
        country: String?,
        exclusiveSub: Boolean?,
        minViews: Int?,
        limit: Long?
    ): List<MovieCard> {

        return firestoreDataSource.getBasicMovieWithFilters(
            movieType,
            year,
            categories,
            country,
            exclusiveSub,
            minViews,
            limit
        )
    }

    override suspend fun getMovieDetailFromRepository(movieId: String): MovieDetail {
        return firestoreDataSource.getDetailedMovie(movieId)
    }


    override suspend fun addMovieToFavouriteList(movieId: String) {
        firestoreDataSource.addMovietoFavouriteList(movieId)
    }

    override suspend fun removeMovieFromFavouriteList(movieId: String) {
        firestoreDataSource.removeMovieFromFavouriteList(movieId)
    }

    override suspend fun checkMovieInFavouriteList(movieId: String) : Boolean {
        return firestoreDataSource.checkMovieInFavouriteList(movieId)
    }


}
