package hu.bme.mobillabor.dogiememe.dataprovidertests.network

import hu.bme.mobillabor.dogiememe.api.network.ApiResult
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

class ApiResponseTest {

  @Test
  fun exception() {
    val exception = Exception("foo")
    val apiResponse = ApiResult.Error(exception)
    assertThat(apiResponse.exception.message, `is`("foo"))
  }

  @Test
  fun success() {
    val apiResponse = ApiResult.Success(1)
    if (apiResponse is ApiResult.Success) {
      assertThat(apiResponse.data, `is`("foo"))
    }
  }
}
