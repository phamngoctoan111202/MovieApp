package com.noatnoat.feature_favourite.data.datasource.firestore.model

import com.noatnoat.feature_favourite.domain.model.FavouriteMovieCard

data class FavouriteMovieCardFirestoreModel(
    val _id: String = "",
    val name: String = "",
    val thumb_url: String = "null",
    val poster_url: String? = null,
    val trailer_url: String? = null,
) {
    fun toDomainModel(): FavouriteMovieCard {
        return FavouriteMovieCard(
            id = this._id,
            name = this.name,
            thumbUrl = this.thumb_url,
            posterUrl = this.poster_url,
            trailerUrl = this.trailer_url,
        )
    }
}
