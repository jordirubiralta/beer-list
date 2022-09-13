package com.jrubiralta.beerlistapp.presentation.ui.beerlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jrubiralta.beerlistapp.R
import com.jrubiralta.beerlistapp.databinding.FragmentBeerListBinding
import com.jrubiralta.beerlistapp.domain.model.BeerModel
import com.jrubiralta.beerlistapp.presentation.commons.collectEvent
import com.jrubiralta.beerlistapp.presentation.commons.collectState
import com.jrubiralta.beerlistapp.presentation.ui.beerlist.adapter.BeerListAdapter
import com.jrubiralta.beerlistapp.presentation.ui.beerlist.adapter.BeerLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerListFragment : Fragment() {

    private val viewModel by viewModels<BeerListViewModel>()

    private lateinit var binding: FragmentBeerListBinding

    private val beerListAdapter by lazy {
        BeerListAdapter {
            onItemClick(it)
        }
    }

    private val beerListRecyclerView
        get() = binding.recycler

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentBeerListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(requireActivity().getString(R.string.list_title))
        setUpRecyclerView()
        initObservers()
        initListeners()
    }

    private fun setUpRecyclerView() {
        with(beerListRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = beerListAdapter.withLoadStateHeaderAndFooter(
                header = BeerLoadStateAdapter { beerListAdapter.retry() },
                footer = BeerLoadStateAdapter { beerListAdapter.retry() }
            )
            setHasFixedSize(true)
        }
    }

    private fun initObservers() {
        collectState(viewModel.state) { renderState(it) }
        collectEvent(viewModel.event) { launchEvent(it) }
    }

    private fun initListeners() {
        binding.etSearch.doOnTextChanged { text, _, _, _ ->
            viewModel.getBeerList(
                if (!text.isNullOrBlank()) {
                    text.toString()
                } else {
                    null
                }
            )
        }
    }

    private fun renderState(state: BeerListViewModel.BeerListState) {
        showLoading(state.isLoading)
        showErrorMessage(state.error)
        state.beerList?.let {
            beerListAdapter.submitData(lifecycle, state.beerList)
        }
    }

    private fun launchEvent(event: BeerListViewModel.Event) {
        when (event) {
            is BeerListViewModel.Event.ProceedToBeerDetails -> {
                findNavController().navigate(
                    BeerListFragmentDirections.actionGoToBeerDetail(event.model)
                )
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initToolbar(toolbarTitle: String) {
        binding.centeredToolbar.title = toolbarTitle
    }

    private fun onItemClick(model: BeerModel) {
        viewModel.navigateToBeerDetails(model)
    }

    private fun showErrorMessage(error: Int?) {
        error?.let {
            Toast.makeText(requireContext(), getString(error), Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLoading(visibility: Boolean) {
        binding.progressBar.isVisible = visibility
    }
}