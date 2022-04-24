package hu.bme.mobillabor.dogiememe.dataprovidertests.persistence

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import hu.bme.mobillabor.dogiememe.datasource.AppDatabase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
abstract class LocalDatabase {
  lateinit var db: AppDatabase

  @Before
  fun initDB() {
    db = Room.inMemoryDatabaseBuilder(getApplicationContext(), AppDatabase::class.java)
      .allowMainThreadQueries()
      .build()
  }

  @After
  fun closeDB() {
    db.close()
  }
}
