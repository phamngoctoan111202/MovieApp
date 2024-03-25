package com.noatnoat.feature_favourite.presentation

import com.noatnoat.feature_favourite.presentation.screen.detailscreen.FavouriteDetailViewModel
import com.noatnoat.feature_favourite.presentation.screen.favouritescreen.FavouriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val presentationModule = module {
    viewModel { FavouriteViewModel(get(), get()) }
    viewModel {FavouriteDetailViewModel(get(), get(), get(), get())}
}
