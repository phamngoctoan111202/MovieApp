package com.noatnoat.movie_appliaction.feature_base.presentation.viewmodel

interface BaseAction<State> {
    fun reduce(state: State): State
}
