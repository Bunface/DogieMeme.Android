package hu.bme.mobillabor.dogiememe.api.service.dto

import hu.bme.mobillabor.dogiememe.model.Meme

data class MemeDto (
    val id: String,
    val name: String,
    val url: String,
)

fun MemeDto.mapToMeme(): Meme {
    return Meme(
        id = this.id,
        title = this.name,
        imageUrl = this.url,
        creator = "DogeMemer2000",
        redditLink = this.url
    )
}