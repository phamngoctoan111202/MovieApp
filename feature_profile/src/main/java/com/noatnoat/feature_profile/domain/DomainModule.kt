package com.noatnoat.feature_profile.domain

import com.noatnoat.feature_profile.domain.usecase.GetUserInfoUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val domainModule = module {
    singleOf(::GetUserInfoUseCase)
}