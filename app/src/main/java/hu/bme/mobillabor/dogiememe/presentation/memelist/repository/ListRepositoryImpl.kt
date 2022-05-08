package hu.bme.mobillabor.dogiememe.presentation.memelist.repository

import hu.bme.mobillabor.dogiememe.api.network.ApiResult
import hu.bme.mobillabor.dogiememe.api.network.NetworkProvider
import hu.bme.mobillabor.dogiememe.datasource.AppDao
import hu.bme.mobillabor.dogiememe.model.Meme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListRepositoryImpl(
    private val networkProvider: NetworkProvider,
    private val persistenceProvider: AppDao,
    override val coroutineContext: CoroutineContext = Dispatchers.IO,
): ListRepository, CoroutineScope {

    override val memeList= MutableSharedFlow<List<Meme>>(1 , 10)

    override fun fetchMemeList() {
        launch(coroutineContext) {

            memeList.tryEmit(persistenceProvider.getMemeList())

            val response = networkProvider.fetchMemes()
            if(response is ApiResult.Success){
                memeList.tryEmit(response.data)
                persistenceProvider.updateMemeList(response.data)
            }
        }
    }

}