package hu.bme.mobillabor.dogiememe.dataprovidertests.persistence

import hu.bme.mobillabor.dogiememe.datasource.AppDao
import hu.bme.mobillabor.dogiememe.model.getMemeTestList
import hu.bme.mobillabor.dogiememe.model.getMemeTestListFirstId
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [29])
class AppDaoTest : LocalDatabase() {

  private lateinit var appDao: AppDao

  @Before
  fun init(){
    appDao = db.getDao()
  }

  @Test
  fun testDao() = runBlocking{
    //dao.updateMemeList() test
    val testData = getMemeTestList()
    appDao.updateMemeList(testData)

    //dao.getMemeList() test
    val testDataPassedThroughDB = appDao.getMemeList()
    assertThat(testDataPassedThroughDB.toString(), `is`(testData.toString()))

    //dao.getMeme() test
    val firstTestDataFromDB = appDao.getMeme(getMemeTestListFirstId())
    assertThat(firstTestDataFromDB.toString(), `is`(testData.first().toString()))
  }
}
