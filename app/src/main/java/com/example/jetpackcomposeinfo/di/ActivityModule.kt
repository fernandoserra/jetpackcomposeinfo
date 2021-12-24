package com.example.jetpackcomposeinfo.di

import com.example.jetpackcomposeinfo.data.remote.DataSource
import com.example.jetpackcomposeinfo.data.remote.DataSourceImpl
import com.example.jetpackcomposeinfo.domain.Repository
import com.example.jetpackcomposeinfo.domain.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * @Author: Fernando Serra
 */

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract  fun bindRepositoryContentImpl(repositoryContentImpl: RepositoryImpl): Repository

    @Binds
    abstract  fun bindDataSourceImpl(dataSourceImpl: DataSourceImpl): DataSource

}