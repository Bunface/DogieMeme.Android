package hu.bme.mobillabor.dogiememe.presentation.memedetail.repository

import hu.bme.mobillabor.dogiememe.model.Meme
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {
    fun fetchMemeDetail(id: String)
    val meme: Flow<Meme>
}