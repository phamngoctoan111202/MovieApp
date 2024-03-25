package com.noatnoat.feature_home.domain.model

data class MovieCard(
    val id: String = "",
    val name: String = "",
    val thumbUrl: String = "null",
    val originName: String? = null,
    val type: String? = null,
    val posterUrl: String? = null,
    val time: String? = null,
    val episodeCurrent: String? = null,
    val episodeTotal: String? = null,
    val quality: String? = null,
    val lang: String? = null,
    val year: Int? = null,
    val view: Int? = null,
    val country: String? = null,
    val category: List<String>? = null
)
