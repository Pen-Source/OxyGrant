package com.pensource.shared.di

import com.google.firebase.firestore.FirebaseFirestore
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

    @Singleton
    @Provides
    fun provideSupplyDataSource(
        firestore: FirebaseFirestore
    ): SupplyDataSource {
        return FirebaseSupplyDataSource(firestore)
    }

    @Singleton
    @Provides
    fun provideSupplyRepository(): SupplyRepository {
        return FirebaseSupplyRepository()
    }

    fun provideFirebaseAuth() {

    }
}