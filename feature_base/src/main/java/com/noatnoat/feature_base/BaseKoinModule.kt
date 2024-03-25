package com.noatnoat.feature_base
import com.noatnoat.feature_base.data.dataModule
import com.noatnoat.feature_base.domain.domainModule
import com.noatnoat.feature_base.presentation.presentationModule

val featureBaseModules = listOf(
    presentationModule,
    dataModule,
    domainModule
)
