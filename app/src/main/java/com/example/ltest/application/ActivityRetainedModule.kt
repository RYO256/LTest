package com.example.ltest.application

import com.example.ltest.data.GifsRepositoryImpl
import com.example.ltest.domain.repo.GifsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {
    @Binds
    abstract fun dataSource(impl: GifsRepositoryImpl): GifsRepository
}