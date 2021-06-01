package com.pensource.oxygrant.di

import com.pensource.oxygrant.util.DefaultTimeUtil
import com.pensource.oxygrant.util.TimeUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideTimeUtil(): TimeUtil {
        return DefaultTimeUtil()
    }
}