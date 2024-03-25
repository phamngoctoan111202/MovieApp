package com.noatnoat.feature_base.presentation

import com.noatnoat.feature_base.presentation.nav.NavManager
import org.koin.dsl.module

internal val presentationModule = module {
    single { NavManager() }
}
