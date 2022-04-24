/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hu.bme.mobillabor.dogiememe.dataprovidertests.network

import hu.bme.mobillabor.dogiememe.api.network.ApiResult
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
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
