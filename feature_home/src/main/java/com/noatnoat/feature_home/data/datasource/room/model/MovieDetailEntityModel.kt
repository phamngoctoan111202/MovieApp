package com.noatnoat.feature_home.data.datasource.room.model



class MovieDetailEntityModel(
    val id: String = "",
    val name: String = "",
    val content: String? = null,
    val type: String? = null,
    val thumbUrl: String = "null",
    val posterUrl: String? = null,
    val trailerUrl: String? = null,
    val episodeCurrent: String? = null,
    val episodeTotal: String? = null,
    val lang: String? = null,
    val year: Int? = null,
    val view: Int? = null,
    val country: String? = null,
    val category: List<String>? = null,
    val serverData: List<String>
)