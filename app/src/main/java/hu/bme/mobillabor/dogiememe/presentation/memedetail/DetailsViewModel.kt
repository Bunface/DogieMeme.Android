package hu.bme.mobillabor.dogiememe.presentation.memedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.mobillabor.dogiememe.presentation.memedetail.repository.DetailsRepository
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailRepository: DetailsRepository,
    private val state: SavedStateHandle
): ViewModel() {
    private val memeId = state.get<String>("memeId") ?: ""

    val meme = detailRepository.meme.asLiveData()

    fun fetchMeme(){
        detailRepository.fetchMemeDetail(memeId)
    }
}