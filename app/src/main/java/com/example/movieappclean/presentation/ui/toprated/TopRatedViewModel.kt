package com.example.movieappclean.presentation.ui.toprated

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.example.movieappclean.domain.usecases.TopRatedUseCase
import com.example.movieappclean.presentation.ui.toprated.state.TopRatedViewStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TopRatedViewModel @Inject constructor(
    private val topRatedUseCase: TopRatedUseCase
) : ViewModel() {
    private val _viewState = MutableLiveData<TopRatedViewStates>()
    val viewState: LiveData<TopRatedViewStates> get() = _viewState

    fun setStateList(state: CombinedLoadStates, itemCount: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            val viewState = when {
                state.source.refresh is LoadState.Loading -> {
                    TopRatedViewStates.Loading
                }
                state.source.refresh is LoadState.Error -> {
                    val error = (state.source.refresh as LoadState.Error).error
                    TopRatedViewStates.Error(error)
                }
                state.source.refresh is LoadState.NotLoading &&
                        state.append.endOfPaginationReached &&
                        itemCount < 1 -> {
                    TopRatedViewStates.Empty
                }
                else -> TopRatedViewStates.Loaded
            }
            _viewState.postValue(viewState)

        }
    }

    val data = topRatedUseCase.getTopRated(scope = viewModelScope)

}

