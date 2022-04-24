package hu.bme.mobillabor.dogiememe.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hu.bme.mobillabor.dogiememe.model.Meme

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMemeList(memeList: List<Meme>)

    @Query("SELECT * FROM Meme WHERE id = :id_")
    suspend fun getMeme(id_: Long): Meme?

    @Query("SELECT * FROM Meme")
    suspend fun getMemeList(): List<Meme>
}