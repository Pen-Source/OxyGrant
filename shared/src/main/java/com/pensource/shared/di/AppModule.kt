package com.pensource.shared.di

import com.google.firebase.auth.FirebaseAuth
import com.pensource.shared.data.supply.FirebaseSupplyDataSource
import com.pensource.shared.data.supply.FirebaseSupplyRepository
import com.pensource.shared.data.supply.SupplyDataSource
import com.pensource.shared.data.supply.SupplyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideSupplyDataSource(): SupplyDataSource {
        return FirebaseSupplyDataSource()
    }

    @Singleton
    @Provides
    fun provideSupplyRepository(): SupplyRepository {
        return FirebaseSupplyRepository()
    }
}