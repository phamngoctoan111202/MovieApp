package com.noatnoat.feature_home.data.datasource.firestore.model

import com.noatnoat.movie_application.feature_home.data.datasource.room.model.MovieEntityModel
import com.noatnoat.feature_home.domain.model.MovieCard

data class BasicMovieFirestoreModel(
    val _id: String = "",
    val name: String = "",
    val thumb_url: String = "",
    val origin_name: String? = null,
    val type: String? = null,
    val poster_url: String? = null,
    val time: String? = null,
    val episode_current: String? = null,
    val episode_total: String? = null,
    val quality: String? = null,
    val lang: String? = null,
    val year: Int? = null,
    val view: Int? = null,
    val country: String? = null,
    val category: List<String>? = null
) {
    fun toEntityModel(): MovieEntityModel {
        return MovieEntityModel(
            id = this._id,
            name = this.name,
            thumbUrl = this.thumb_url,
            originName = this.origin_name,
            type = this.type,
            posterUrl = this.poster_url,
            time = this.time,
            episodeCurrent = this.episode_current,
            episodeTotal = this.episode_total,
            quality = this.quality,
            lang = this.lang,
            year = this.year,
            view = this.view,
            country = this.country,
            category = this.category
        )
    }

    fun toDomainModel(): MovieCard {
        return MovieCard(
            id = this._id,
            name = this.name,
            thumbUrl = this.thumb_url,
            originName = this.origin_name,
            type = this.type,
            posterUrl = this.poster_url,
            time = this.time,
            episodeCurrent = this.episode_current,
            episodeTotal = this.episode_total,
            quality = this.quality,
            lang = this.lang,
            year = this.year,
            view = this.view,
            country = this.country,
            category = this.category
        )
    }
}
