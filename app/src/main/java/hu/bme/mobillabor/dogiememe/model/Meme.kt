package hu.bme.mobillabor.dogiememe.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.concurrent.Immutable


@Entity
@Immutable
data class Meme (
    @PrimaryKey val id: String,
    val title: String,
    val imageUrl: String,
    val creator: String?,
    val redditLink: String?
        )