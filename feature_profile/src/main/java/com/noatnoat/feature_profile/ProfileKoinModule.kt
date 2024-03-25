package com.noatnoat.feature_profile

import com.noatnoat.feature_profile.data.dataModule
import com.noatnoat.feature_profile.domain.domainModule
import com.noatnoat.feature_profile.presentation.presentationModule

val FeatureProfileModule = listOf(
    presentationModule,
    domainModule,
    dataModule
)
