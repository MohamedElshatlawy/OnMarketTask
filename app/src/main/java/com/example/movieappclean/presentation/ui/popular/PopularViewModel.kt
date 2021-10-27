package com.example.movieappclean.presentation.ui.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.example.movieappclean.domain.usecases.PopularUseCase
import com.example.movieappclean.presentation.ui.popular.state.PopularViewStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
   private val  popularUseCase: PopularUseCase
) : ViewModel() {
    private val _viewState = MutableLiveData<PopularViewStates>()
    val viewState: LiveData<PopularViewStates> get() = _viewState
//    init {
//        popularUseCase.getPopularNetwork(scope = viewModelScope)
//    }
    fun setStateList(state: CombinedLoadStates, itemCount: Int) {
        viewModelScope.launch(Default) {
            val viewState = when {
                state.source.refresh is LoadState.Loading -> {
                    PopularViewStates.Loading
                }
                state.source.refresh is LoadState.Error -> {
                    val error = (state.source.refresh as LoadState.Error).error
                    PopularViewStates.Error(error)
                }
                state.source.refresh is LoadState.NotLoading &&
                        state.append.endOfPaginationReached &&
                        itemCount < 1 -> {
                    PopularViewStates.Empty
                }
                else -> PopularViewStates.Loaded
            }
            _viewState.postValue(viewState)

    }
    }

    val data = popularUseCase.getPopularNetwork(scope = viewModelScope)

}
