package com.example.movieappclean.presentation.ui.nowplaying.state

interface NowPlayingViewStateHandler {
    fun refresh()
    fun retry()
    fun isLoading(state: NowPlayingViewStates?): Boolean
    fun isLoaded(state: NowPlayingViewStates?): Boolean
    fun isError(state: NowPlayingViewStates?): Boolean
    fun isEmpty(state: NowPlayingViewStates?): Boolean
    fun hasState(state: NowPlayingViewStates?): Boolean
    fun getErrorText(state: NowPlayingViewStates?): String?
}