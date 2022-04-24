package hu.bme.mobillabor.dogiememe.dataprovidertests.persistence

import hu.bme.mobillabor.dogiememe.datasource.AppDatabase
import org.junit.After
import org.junit.Before

abstract class LocalDatabase {
  lateinit var db: AppDatabase

  @Before
  fun initDB() {
  }

  @After
  fun closeDB() {
  }
}
