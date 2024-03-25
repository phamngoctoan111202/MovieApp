package com.noatnoat.movie_application.feature_home.presentation.screen

import com.noatnoat.feature_home.presentation.screen.detailscreen.DetailViewModel
import com.noatnoat.feature_home.presentation.screen.homescreen.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

internal val presentationModule = module {
    viewModel { HomeViewModel(get(),get()) }
    viewModel { DetailViewModel(get(), get(), get(), get()) }
    // viewModel { SearchViewModel(get()) }
}