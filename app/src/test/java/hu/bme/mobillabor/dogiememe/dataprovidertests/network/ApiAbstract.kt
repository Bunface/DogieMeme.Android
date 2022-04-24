package hu.bme.mobillabor.dogiememe.dataprovidertests.network

import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

@RunWith(JUnit4::class)
abstract class ApiAbstract<T> {

  @Throws(IOException::class)
  @Before
  fun mockServer() {
  }

  @Throws(IOException::class)
  @After
  fun stopServer() {
  }


  fun createService(clazz: Class<T>): T {
    return Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(clazz)
  }
}
