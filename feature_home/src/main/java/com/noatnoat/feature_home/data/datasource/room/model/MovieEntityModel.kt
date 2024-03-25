package com.noatnoat.movie_application.feature_home.data.datasource.room.model

data class MovieEntityModel (
    val id: String,
    val name: String?,
    val thumbUrl: String?,
    val originName: String?,
    val type: String?,
    val posterUrl: String?,
    val time: String?,
    val episodeCurrent: String?,
    val episodeTotal: String?,
    val quality: String?,
    val lang: String?,
    val year: Int?,
    val view: Int?,
    val country: String?,
    val category: List<String>?
)