package hu.bme.mobillabor.dogiememe.presentation.memelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bitraptors.recyclerview.genericlist.GenericListItem
import hu.bme.mobillabor.dogiememe.presentation.baseclasses.FragmentOutput
import hu.bme.mobillabor.dogiememe.presentation.baseclasses.UIAction
import hu.bme.mobillabor.dogiememe.presentation.memelist.cells.MemeCell
import hu.bme.mobillabor.dogiememe.presentation.memelist.cells.MemeItemClicked
import hu.bme.mobillabor.dogiememe.presentation.memelist.repository.ListRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val listRepository: ListRepository
) : ViewModel() {
    private val UIEvents = MutableSharedFlow<UIAction>(0, 10)
    private val _fragmentEvents = MutableSharedFlow<FragmentOutput>(0, 10)
    val fragmentEvents = _fragmentEvents.asLiveData()

    val memeList : LiveData<List<GenericListItem>> = listRepository.memeList.map{
            list -> list.take(20).map{MemeCell(it)}
    }.asLiveData()

    val delegates = listOf(
        MemeCell.getDelegate(UIEvents)
    ).toTypedArray()

    init {
        viewModelScope.launch {
            UIEvents.collect { event ->
                when(event){
                    is MemeItemClicked -> {
                        _fragmentEvents.tryEmit(NavigateToDetail(event.id))
                    }
                }
            }
        }
    }

    fun fetchMemeList(){
        listRepository.fetchMemeList()
    }
}

class NavigateToDetail(val memeId: String): FragmentOutput