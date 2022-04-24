package hu.bme.mobillabor.dogiememe.dataprovidertests.network

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

abstract class ApiAbstract<T> {
  lateinit var mockWebServer: MockWebServer

  @Throws(IOException::class)
  @Before
  fun mockServer() {
    mockWebServer = MockWebServer()
    mockWebServer.start()
  }

  @Throws(IOException::class)
  @After
  fun stopServer() {
    mockWebServer.shutdown()
  }


  fun createService(clazz: Class<T>): T {
    return Retrofit.Builder()
      .baseUrl(mockWebServer.url("/"))
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(clazz)
  }
}
