package hu.bme.mobillabor.dogiememe.dataprovidertests.persistence

import hu.bme.mobillabor.dogiememe.datasource.AppDao
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class PosterDaoTest : LocalDatabase() {

  private lateinit var posterDao: AppDao

  fun init() {
    posterDao = db.getDao()
  }

  fun insertAndLoadMemeListTest() {

  }
}
