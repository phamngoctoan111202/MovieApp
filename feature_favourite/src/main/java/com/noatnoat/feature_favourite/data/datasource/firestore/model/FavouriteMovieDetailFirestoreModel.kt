package com.noatnoat.feature_favourite.data.datasource.firestore.model

import com.noatnoat.feature_favourite.domain.model.FavouriteMovieCard
import com.noatnoat.feature_favourite.domain.model.FavouriteMovieDetail

class FavouriteMovieDetailFirestoreModel(
    val _id: String = "",
    val name: String = "",
    val content: String? = null,
    val type: String? = null,
    val thumb_url: String = "null",
    val poster_url: String? = null,
    val trailer_url: String? = null,
    val episode_current: String? = null,
    val episode_total: String? = null,
    val lang: String? = null,
    val year: Int? = null,
    val view: Int? = null,
    val country: String? = null,
    val category: List<String>? = null,
    val server_data: List<String> = emptyList()
) {
    fun toDomainModel(): FavouriteMovieDetail {
        return FavouriteMovieDetail(
            id = this._id,
            name = this.name,
            content = this.content,
            type = this.type,
            thumbUrl = this.thumb_url,
            posterUrl = this.poster_url,
            trailerUrl = this.trailer_url,
            episodeCurrent = this.episode_current,
            episodeTotal = this.episode_total,
            lang = this.lang,
            year = this.year,
            view = this.view,
            country = this.country,
            category = this.category,
            serverData = this.server_data
        )
    }
}