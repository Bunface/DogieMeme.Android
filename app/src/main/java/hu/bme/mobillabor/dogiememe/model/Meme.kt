package hu.bme.mobillabor.dogiememe.model

data class Meme (
    val id: String,
    val title: String,
    val imageUrl: String,
    val creator: String?,
    val redditLink: String?
        )