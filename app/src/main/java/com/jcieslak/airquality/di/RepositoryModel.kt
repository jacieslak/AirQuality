package com.jcieslak.airquality.di

import com.jcieslak.airquality.data.repository.GiosRepository
import com.jcieslak.airquality.data.repository.GiosRepositoryImpl
import com.jcieslak.airquality.network.GiosService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModel {

    @Provides
    @ActivityRetainedScoped
    fun proviceGiosRepository(
        giosService: GiosService
    ): GiosRepository {
        return GiosRepositoryImpl(giosService)
    }
}