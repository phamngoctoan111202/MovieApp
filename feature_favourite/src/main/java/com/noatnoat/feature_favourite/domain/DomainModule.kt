package com.noatnoat.feature_favourite.domain

import com.noatnoat.feature_favourite.domain.usecase.AddMovieToFavouriteList
import com.noatnoat.feature_favourite.domain.usecase.CheckMovieInFavouriteList
import com.noatnoat.feature_favourite.domain.usecase.DeleteMoviesFromFavouriteList
import com.noatnoat.feature_favourite.domain.usecase.GetFavouriteMovieDetailUsecase
import com.noatnoat.feature_favourite.domain.usecase.GetFavouriteMoviesUsecase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.dsl.single

internal val domainModule = module {
    singleOf(:: GetFavouriteMoviesUsecase)

    single {
        GetFavouriteMovieDetailUsecase(get())
    }

    single {
        AddMovieToFavouriteList(get())
    }

    single {
        CheckMovieInFavouriteList(get())
    }

    single {
        DeleteMoviesFromFavouriteList(get())
    }
}