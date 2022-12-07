package com.dicoding.gotrip.di

import android.app.Application
import androidx.room.Room
import com.dicoding.gotrip.data.local.TourismDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application) = Room
        .databaseBuilder(application, TourismDatabase::class.java, "gotrip.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideDao(database: TourismDatabase) = database.tourismDao()
}