package com.example.movieappclean.presentation.ui.nowplaying

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.example.movieappclean.domain.usecases.NowPlayingUseCase
import com.example.movieappclean.presentation.ui.nowplaying.state.NowPlayingViewStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NowPlayingViewModel @Inject constructor(
    private val  nowPlayingUseCase: NowPlayingUseCase
) : ViewModel() {
    private val _viewState = MutableLiveData<NowPlayingViewStates>()
    val viewState: LiveData<NowPlayingViewStates> get() = _viewState

    fun setStateList(state: CombinedLoadStates, itemCount: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            val viewState = when {
                state.source.refresh is LoadState.Loading -> {
                    NowPlayingViewStates.Loading
                }
                state.source.refresh is LoadState.Error -> {
                    val error = (state.source.refresh as LoadState.Error).error
                    NowPlayingViewStates.Error(error)
                }
                state.source.refresh is LoadState.NotLoading &&
                        state.append.endOfPaginationReached &&
                        itemCount < 1 -> {
                    NowPlayingViewStates.Empty
                }
                else -> NowPlayingViewStates.Loaded
            }
            _viewState.postValue(viewState)

        }
    }

    val data = nowPlayingUseCase.getNowPaying(scope = viewModelScope)

}
