package com.example.movieappclean.presentation.ui.toprated.state


sealed class TopRatedViewStates {
    object Loaded : TopRatedViewStates()
    object Loading : TopRatedViewStates()
    object Empty : TopRatedViewStates()
    data class Error(val throwable: Throwable) : TopRatedViewStates()
}

