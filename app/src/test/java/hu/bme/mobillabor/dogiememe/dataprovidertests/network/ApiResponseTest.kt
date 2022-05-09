package hu.bme.mobillabor.dogiememe.dataprovidertests.network

import hu.bme.mobillabor.dogiememe.api.network.ApiResult
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.File

@RunWith(JUnit4::class)
class ApiResponseTest {

  @Test
  fun success() {
    val apiResponse = ApiResult.Success(1)
    assertThat(apiResponse.data, `is`(1))
  }

  @Test
  fun exception() {
    val exception = Exception("foo")
    val apiResponse = ApiResult.Error(exception)
    assertThat(apiResponse.exception.message, `is`("foo"))
  }

}
