package hu.bme.mobillabor.dogiememe.dataprovidertests.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.isNotNull
import hu.bme.mobillabor.dogiememe.api.service.ApiService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue

@RunWith(JUnit4::class)
class ApiServiceTest{

  private lateinit var service: ApiService
  private lateinit var server: MockWebServer

  @Before
  fun setUp() {
    server = MockWebServer()
    service = Retrofit.Builder()
      .baseUrl(server.url(""))//We will use MockWebServers url
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(ApiService::class.java)
  }

  private fun enqueueMockResponse(fileName: String) {
    javaClass.classLoader?.let {
      val inputStream = it.getResourceAsStream(fileName)
      val source = inputStream.source().buffer()
      val mockResponse = MockResponse()
      mockResponse.setBody(source.readString(Charsets.UTF_8))
      server.enqueue(mockResponse)
    }
  }

  @After
  fun tearDown() {
    server.shutdown()
  }

  @Test
  fun getSearchedResult_sentRequest_receivedExpected() {
    runBlocking {
      // Prepare fake response
      enqueueMockResponse("api-response/test-response.json")
      //Send Request to the MockServer
      val apiResponseData = service.fetchMemes().data
      //Request received by the mock server
      val request = server.takeRequest()
      assertThat(request.path, `is`("/get_memes"))
      assertThat(apiResponseData, `is`(notNullValue()))
    }
  }
}
