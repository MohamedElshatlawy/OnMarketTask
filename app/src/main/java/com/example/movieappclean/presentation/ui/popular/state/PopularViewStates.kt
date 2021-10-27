package com.example.movieappclean.presentation.ui.popular.state


sealed class PopularViewStates {
    object Loaded : PopularViewStates()
    object Loading : PopularViewStates()
    object Empty : PopularViewStates()
    data class Error(val throwable: Throwable) : PopularViewStates()
}

