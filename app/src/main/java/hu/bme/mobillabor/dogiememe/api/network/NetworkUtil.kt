package hu.bme.mobillabor.dogiememe.api.network

suspend fun <T : Any> safeApiCall(
    call: suspend () -> T
): ApiResult<T> = try {
    ApiResult.Success<T>(call.invoke())
} catch (e: Exception) {
    ApiResult.Error(e)
}

suspend fun <T : Any, R : Any> ApiResult<T>.safeMapResultTo(
    map: suspend (T) -> R
): ApiResult<R> = when (this) {
    is ApiResult.Success -> {
        try {
            ApiResult.Success(map(data))
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }
    is ApiResult.Error -> ApiResult.Error(exception)
}