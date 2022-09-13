package com.jrubiralta.beerlistapp.presentation.ui.beerdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jrubiralta.beerlistapp.databinding.FragmentBeerDetailBinding
import com.jrubiralta.beerlistapp.domain.model.BeerModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerDetailFragment : Fragment() {

    private lateinit var binding: FragmentBeerDetailBinding

    private val args: BeerDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBeerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderDetails(args.model)
    }

    private fun renderDetails(model: BeerModel) = with(binding) {
        composeView.setContent {
            MaterialTheme {
                BeerDetails(model, ::onBackClicked)
            }
        }
    }

    private fun onBackClicked() {
        findNavController().navigateUp()
    }


}