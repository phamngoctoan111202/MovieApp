package com.noatnoat.feature_favourite

import com.noatnoat.feature_favourite.data.dataModule
import com.noatnoat.feature_favourite.domain.domainModule
import com.noatnoat.feature_favourite.presentation.presentationModule

val FeatureFavouriteModules = listOf(
    presentationModule,
    domainModule,
    dataModule
)
