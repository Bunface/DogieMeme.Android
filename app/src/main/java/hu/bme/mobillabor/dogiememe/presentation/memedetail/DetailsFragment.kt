package hu.bme.mobillabor.dogiememe.presentation.memedetail

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.mobillabor.dogiememe._util.loadUrl
import hu.bme.mobillabor.dogiememe._util.loadUrlCropCenter
import hu.bme.mobillabor.dogiememe.databinding.FragmentDetailsBinding

@AndroidEntryPoint
class DetailsFragment: Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val viewModel: DetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContent()
        setBackButton()
        refreshPage()
    }

    private fun refreshPage(){
        viewModel.fetchMeme()
    }

    private fun setContent(){
        viewModel.meme.observe(viewLifecycleOwner){ meme ->
            binding.memeImage.loadUrl(meme.imageUrl)
            binding.memeTitle.text = meme.title
            binding.memeCreator.text = "created by ${meme.creator}"
            binding.memeLinkButton.setOnClickListener {
                context?.let { ctx ->
                    val clipboard =
                        ContextCompat.getSystemService(ctx, ClipboardManager::class.java);
                    val clip = ClipData.newPlainText("location", meme.redditLink)
                    clipboard?.setPrimaryClip(clip)
                    Toast.makeText(context, "Link copied to clipboard!", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun setBackButton(){
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
