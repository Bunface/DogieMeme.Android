package hu.bme.mobillabor.dogiememe.presentation.memelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import hu.bitraptors.recyclerview.setupRecyclerView
import hu.bme.mobillabor.dogiememe.R
import hu.bme.mobillabor.dogiememe._anlytics.AnalyticsManager
import hu.bme.mobillabor.dogiememe._anlytics.MemeDetailOpened
import hu.bme.mobillabor.dogiememe._anlytics.MemeListOpened
import hu.bme.mobillabor.dogiememe._util.recylerview.setUpHeaderAnim
import hu.bme.mobillabor.dogiememe.databinding.FragmentListBinding
import hu.bme.mobillabor.dogiememe.model.Meme
import hu.bme.mobillabor.dogiememe.presentation.memelist.cells.MemeCell

@AndroidEntryPoint
class ListFragment: Fragment() {

    private lateinit var binding: FragmentListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val viewModel:ListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView()
        refreshOnCreated()
        setUpHeaderAnim(binding.headerTitle, binding.listHeader, binding.memeRecyclerView)
        subscribeToFragmentOutputs()
        AnalyticsManager.track(MemeListOpened(), context)
    }

    private fun refreshOnCreated(){
        viewModel.fetchMemeList()
    }

    private fun recyclerView(){
        this.setupRecyclerView(
            recyclerView = binding.memeRecyclerView,
            items = viewModel.memeList,
            delegates = viewModel.delegates
        )
    }

    private fun subscribeToFragmentOutputs(){
        viewModel.fragmentEvents.observe(viewLifecycleOwner){ output ->
            when(output){
                is NavigateToDetail -> {
                    AnalyticsManager.track(MemeDetailOpened(output.memeId), context)
                    findNavController().navigate(ListFragmentDirections.toDetailFragment(output.memeId))
                }
            }
        }
    }
}