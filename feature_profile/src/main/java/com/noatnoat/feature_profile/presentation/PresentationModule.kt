package com.noatnoat.feature_profile.presentation

import com.noatnoat.feature_profile.presentation.screen.profilescreen.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val presentationModule = module {
    viewModel { ProfileViewModel(get()) }

}