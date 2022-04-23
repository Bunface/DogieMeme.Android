package hu.bme.mobillabor.dogiememe.api.network

import hu.bme.mobillabor.dogiememe.api.service.ApiService
import hu.bme.mobillabor.dogiememe.api.service.dto.mapToMemeList
import hu.bme.mobillabor.dogiememe.model.Meme

class NetworkProviderImpl (
    private val apiService: ApiService
) : NetworkProvider {
    override suspend fun fetchMemes(): ApiResult<List<Meme>> = safeApiCall{
        apiService.fetchMemes()
    }.safeMapResultTo {
        it.mapToMemeList()
    }
}