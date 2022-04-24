package hu.bme.mobillabor.dogiememe._di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.mobillabor.dogiememe.api.network.NetworkProvider
import hu.bme.mobillabor.dogiememe.datasource.AppDao
import hu.bme.mobillabor.dogiememe.datasource.AppDatabase
import hu.bme.mobillabor.dogiememe.presentation.memedetail.repository.DetailsRepository
import hu.bme.mobillabor.dogiememe.presentation.memedetail.repository.DetailsRepositoryImpl
import hu.bme.mobillabor.dogiememe.presentation.memelist.repository.ListRepository
import hu.bme.mobillabor.dogiememe.presentation.memelist.repository.ListRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideListRepository(
        networkProvider: NetworkProvider,
        persistenceProvider: AppDao
    ): ListRepository {
        return ListRepositoryImpl(
            networkProvider,
            persistenceProvider
        )
    }

    @Provides
    @Singleton
    fun provideDetailRepository(
        networkProvider: NetworkProvider,
        persistenceProvider: AppDao
    ): DetailsRepository {
        return DetailsRepositoryImpl(
            networkProvider,
            persistenceProvider
        )
    }
}