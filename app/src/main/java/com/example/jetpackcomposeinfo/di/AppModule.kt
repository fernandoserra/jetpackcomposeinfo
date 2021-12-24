package com.example.jetpackcomposeinfo.di

import android.content.Context
import androidx.room.Room
import com.example.jetpackcomposeinfo.application.AppConstants.DATABASE_NAME
import com.example.jetpackcomposeinfo.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * @Author: Fernando Serra
 */

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomInstance(@ApplicationContext context: Context)=
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,DATABASE_NAME)
            .build()

    @Singleton
    @Provides
    fun providerDao(db:AppDatabase) =db.teamDao()

}