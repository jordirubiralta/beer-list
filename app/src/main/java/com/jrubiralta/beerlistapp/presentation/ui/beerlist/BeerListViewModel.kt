package com.jrubiralta.beerlistapp.presentation.ui.beerlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jrubiralta.beerlistapp.R
import com.jrubiralta.beerlistapp.domain.model.BeerModel
import com.jrubiralta.beerlistapp.domain.usecases.GetBeerListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BeerListViewModel @Inject constructor(private val getBeerListUseCase: GetBeerListUseCase) : ViewModel() {

    private val _event = MutableSharedFlow<Event>()
    val event: SharedFlow<Event> = _event.asSharedFlow()

    private val _state: MutableStateFlow<BeerListState> = MutableStateFlow(BeerListState(true))
    val state: StateFlow<BeerListState> = _state.asStateFlow()

    init {
        getBeerList(null)
    }

    fun getBeerList(search: String?) {
        viewModelScope.launch {
            try {
                getBeerListUseCase.invoke(search)
                    .cachedIn(viewModelScope)
                    .collectLatest { pagingData ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                beerList = pagingData,
                                error = null
                            )
                        }
                    }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = R.string.unknown_error
                    )
                }
            }
        }
    }

    fun navigateToBeerDetails(model: BeerModel) {
        viewModelScope.launch {
            _event.emit(
                Event.ProceedToBeerDetails(model)
            )
        }
    }

    data class BeerListState(
        val isLoading: Boolean,
        val beerList: PagingData<BeerModel>? = null,
        val error: Int? = null
    )

    sealed class Event {
        data class ProceedToBeerDetails(val model: BeerModel) : Event()
    }
}
