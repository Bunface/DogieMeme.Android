package hu.bme.mobillabor.dogiememe.api.network

import hu.bme.mobillabor.dogiememe.model.Meme

interface NetworkProvider {
    suspend fun fetchMemes(): ApiResult<List<Meme>>
}