package hu.bme.mobillabor.dogiememe._di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.mobillabor.dogiememe.datasource.AppDao
import hu.bme.mobillabor.dogiememe.datasource.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(
                application.applicationContext,
                AppDatabase::class.java, "hu.bme.mobillabor.dogememe-database"
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideAppDao(appDatabase: AppDatabase): AppDao {
        return appDatabase.getDao()
    }
}