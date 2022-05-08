package hu.bme.mobillabor.dogiememe.presentation.memelist.cells

import android.view.View
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import hu.bitraptors.recyclerview.genericlist.GenericListItem
import hu.bme.mobillabor.dogiememe._util.loadUrl
import hu.bme.mobillabor.dogiememe.databinding.CellMemeBinding
import hu.bme.mobillabor.dogiememe.model.Meme
import hu.bme.mobillabor.dogiememe.presentation.baseclasses.UIAction
import kotlinx.coroutines.flow.MutableSharedFlow

class MemeCell (val meme: Meme) : GenericListItem {
    override fun getItemHash(): Int {
        return hashCode()
    }

    override fun getItemId(): String {
        return this::class.java.name
    }

    companion object {
        fun getDelegate(callback: MutableSharedFlow<UIAction>) =
            adapterDelegateViewBinding<MemeCell, GenericListItem, CellMemeBinding>(
                viewBinding = { layoutInflater, root ->
                    CellMemeBinding.inflate(layoutInflater, root, false)
                },
                block = {
                    bind{
                        binding.apply {
                            memeTitle.text = item.meme.title
                            memeImage.loadUrl(item.meme.imageUrl)
                        }
                    }
                    binding.apply {
                        root.setOnClickListener {
                            callback.tryEmit(MemeItemClicked(item.meme.id))
                        }
                    }
                }
            )
    }
}

class MemeItemClicked(val id: String): UIAction