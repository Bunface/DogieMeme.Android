package hu.bme.mobillabor.dogiememe.presentation.memedetail.repository

import hu.bme.mobillabor.dogiememe.api.network.ApiResult
import hu.bme.mobillabor.dogiememe.api.network.NetworkProvider
import hu.bme.mobillabor.dogiememe.datasource.AppDao
import hu.bme.mobillabor.dogiememe.model.Meme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailsRepositoryImpl(
    private val networkProvider: NetworkProvider,
    private val persistenceProvider: AppDao,
    override val coroutineContext: CoroutineContext = Dispatchers.IO,
):
DetailsRepository, CoroutineScope{
    override val meme = MutableSharedFlow<Meme>(1, 10)

    override fun fetchMemeDetail(id: String) {
        launch(coroutineContext) {

            persistenceProvider.getMeme(id)?.let{
                meme.tryEmit(it)
            }

            val response = networkProvider.fetchMemes()
            if(response is ApiResult.Success){
                response.data.firstOrNull{it.id == id}?.let{
                    meme.tryEmit(it)
                }
                persistenceProvider.updateMemeList(response.data)
            }
        }
    }

}