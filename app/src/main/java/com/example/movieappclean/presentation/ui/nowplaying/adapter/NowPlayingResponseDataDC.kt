package com.example.movieappclean.presentation.ui.nowplaying.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.movieappclean.domain.entities.local.NowPlayingLocal


object NowPlayingResponseDataDC : DiffUtil.ItemCallback<NowPlayingLocal>() {
    override fun areItemsTheSame(
        oldItem: NowPlayingLocal,
        newItem: NowPlayingLocal
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: NowPlayingLocal,
        newItem: NowPlayingLocal
    ): Boolean =
        oldItem == newItem

}