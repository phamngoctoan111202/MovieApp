package com.noatnoat.feature_home.data.datasource.firestore


import com.noatnoat.feature_home.domain.model.MovieDetail
import com.noatnoat.feature_home.data.datasource.firestore.service.MovieFirestoreService
import com.noatnoat.feature_home.domain.model.MovieCard

class FirestoreDataSource(private val service: MovieFirestoreService) {
    suspend fun getBasicMovieWithFilters(movieType: String?,
                                     year: Int?,
                                     categories: String?,
                                     country: String?,
                                     exclusiveSub: Boolean?,
                                     minViews: Int?,
                                     limit: Long?): List<MovieCard> {
        return service.fetchBasicMovieInfoFromFirestore(movieType, year, categories, country, exclusiveSub, minViews, limit).map { it.toDomainModel() }
    }

    suspend fun getDetailedMovie(movieId: String): MovieDetail {
        return service.fetchDetailedMovieInfoFromFirestore(movieId).toDomainModel()
    }

    suspend fun addMovietoFavouriteList(movieId: String) {
        service.addMovietoFavouriteList(movieId)
    }

    suspend fun removeMovieFromFavouriteList(movieId: String) {
        service.removeMovieFromFavouriteList(movieId)
    }

    suspend fun checkMovieInFavouriteList(movieId: String) : Boolean {
        return service.isMovieInFavouriteList(movieId)
    }

}
