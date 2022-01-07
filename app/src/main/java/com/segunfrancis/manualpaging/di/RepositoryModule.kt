package com.segunfrancis.manualpaging.di

import com.segunfrancis.manualpaging.data.repository.IManualPagingRepository
import com.segunfrancis.manualpaging.data.repository.ManualPagingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RepositoryModule {

    @Binds
    fun bindManualPagingRepository(repository: ManualPagingRepository): IManualPagingRepository
}
