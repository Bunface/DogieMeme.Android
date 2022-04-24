package hu.bme.mobillabor.dogiememe.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.bme.mobillabor.dogiememe.model.Meme

@Database(entities = [Meme::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): AppDao
}