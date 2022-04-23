package hu.bme.mobillabor.dogiememe.api.service

import hu.bme.mobillabor.dogiememe.api.service.dto.MemeListResponseDto
import retrofit2.http.GET

interface ApiService {
    @GET("get_memes")
    suspend fun fetchMemes(): MemeListResponseDto
}