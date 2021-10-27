package com.example.movieappclean.presentation.ui.details

import androidx.lifecycle.*
import com.example.movieappclean.domain.entities.MovieRequest
import com.example.movieappclean.domain.entities.common.Status
import com.example.movieappclean.domain.entities.local.MovieLocal
import com.example.movieappclean.domain.usecases.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val movieUseCase: MovieUseCase
) : ViewModel() {
val id =savedStateHandle.get<Int>("movie_id")?:0

    val status = movieUseCase.data
    private val _mutableStatus = MutableLiveData<Status<MovieLocal>>()
    val isLoading: LiveData<Boolean> get() = _mutableStatus.map { it.loading() }
    val data: LiveData<MovieLocal?> get() = _mutableStatus.map { it.data() }
    val error: LiveData<Status.Error?> get() = _mutableStatus.map { it.error() }
    val isError: LiveData<Boolean> get() = _mutableStatus.map { it.isError() }

    init {
        fetchData()
    }


    fun fetchData() {
        viewModelScope.launch {
            movieUseCase(
                MovieRequest(
                    movieID = id
                )
            )
        }
    }


    fun updateStatus(status: Status<MovieLocal>) {
        _mutableStatus.postValue(status)
    }


}