package hu.bme.mobillabor.dogiememe.api.service.dto

import hu.bme.mobillabor.dogiememe.model.Meme

data class MemeListResponseDto (
    val success: Boolean,
    val data: MemeListResponseData
)

data class MemeListResponseData(
    val memes: List<MemeDto>
)

fun MemeListResponseDto.mapToMemeList(): List<Meme>{
    return this.data.memes.map { it.mapToMeme() }
}