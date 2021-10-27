package com.example.movieappclean.presentation.ui.nowplaying.adapter

import com.example.movieappclean.domain.entities.local.NowPlayingLocal
interface NowPlayingItemListener {
    fun onItemClick(item: NowPlayingLocal)
}

