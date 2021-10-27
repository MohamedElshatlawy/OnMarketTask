package com.example.movieappclean.presentation.ui.popular.state

interface PopularViewStateHandler {
    fun refresh()
    fun retry()
    fun isLoading(state: PopularViewStates?): Boolean
    fun isLoaded(state: PopularViewStates?): Boolean
    fun isError(state: PopularViewStates?): Boolean
    fun isEmpty(state: PopularViewStates?): Boolean
    fun hasState(state: PopularViewStates?): Boolean
    fun getErrorText(state: PopularViewStates?): String?
}