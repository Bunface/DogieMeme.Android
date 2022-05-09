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

fun getMemeTestList(): List<Meme>{
    return listOf(
        Meme(
            id = "Meme1",
            title = "Meme funny",
            imageUrl = "www.meme.hu",
            creator = "Hello",
            redditLink = "ww.meme.hu"
        ),
        Meme(
            id = "Meme2",
            title = "Meme funny",
            imageUrl = "www.meme.hu",
            creator = "Hello",
            redditLink = "ww.meme.hu"
        ),
        Meme(
            id = "Meme3",
            title = "Meme funny",
            imageUrl = "www.meme.hu",
            creator = "Hello",
            redditLink = "ww.meme.hu"
        ),
    )

}
fun getMemeTestListFirstId(): String {
    return getMemeTestList().first().id
}