package com.example.movieappclean.presentation.ui.toprated.state

interface TopRatedViewStateHandler {
    fun refresh()
    fun retry()
    fun isLoading(state: TopRatedViewStates?): Boolean
    fun isLoaded(state: TopRatedViewStates?): Boolean
    fun isError(state: TopRatedViewStates?): Boolean
    fun isEmpty(state: TopRatedViewStates?): Boolean
    fun hasState(state: TopRatedViewStates?): Boolean
    fun getErrorText(state: TopRatedViewStates?): String?
}