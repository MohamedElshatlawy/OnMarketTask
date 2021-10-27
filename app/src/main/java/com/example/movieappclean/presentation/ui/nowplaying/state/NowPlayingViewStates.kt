package com.example.movieappclean.presentation.ui.nowplaying.state


sealed class NowPlayingViewStates {
    object Loaded : NowPlayingViewStates()
    object Loading : NowPlayingViewStates()
    object Empty : NowPlayingViewStates()
    data class Error(val throwable: Throwable) : NowPlayingViewStates()
}

