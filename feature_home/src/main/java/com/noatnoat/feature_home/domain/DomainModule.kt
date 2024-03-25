package com.noatnoat.feature_home.domain

import com.noatnoat.feature_home.domain.usecase.AddMovieToFavouriteList
import com.noatnoat.feature_home.domain.usecase.CheckMovieInFavouriteList
import com.noatnoat.feature_home.domain.usecase.DeleteMoviesFromFavouriteList
import com.noatnoat.feature_home.domain.usecase.GetMovieDetailsUseCase
import com.noatnoat.feature_home.domain.usecase.GetMoviesByGenreUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


internal val domainModule = module {
    singleOf(::GetMoviesByGenreUseCase)
    singleOf(::GetMovieDetailsUseCase)
    singleOf(::DeleteMoviesFromFavouriteList)
    singleOf(::AddMovieToFavouriteList)
    single { CheckMovieInFavouriteList(get()) }
//    single { AddMovieToFavouriteList(get()) }

}
