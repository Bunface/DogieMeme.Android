package hu.bme.mobillabor.dogiememe.presentation.memelist.repository

import hu.bme.mobillabor.dogiememe.model.Meme
import kotlinx.coroutines.flow.Flow

interface ListRepository {
    fun fetchMemeList()
    val memeList: Flow<List<Meme>>
}