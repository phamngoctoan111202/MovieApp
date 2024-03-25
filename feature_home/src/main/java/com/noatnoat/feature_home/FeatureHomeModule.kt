package com.noatnoat.feature_home

import com.noatnoat.feature_home.data.dataModule
import com.noatnoat.feature_home.domain.domainModule
import com.noatnoat.movie_application.feature_home.presentation.screen.presentationModule

val featureHomeModules = listOf(
    presentationModule,
    domainModule,
    dataModule
)
