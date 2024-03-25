package com.noatnoat.feature_favourite.domain.model

data class FavouriteMovieCard(
    val id: String = "",
    val name: String = "",
    val thumbUrl: String = "null",
    val posterUrl: String? = null,
    val trailerUrl: String? = null,

)
