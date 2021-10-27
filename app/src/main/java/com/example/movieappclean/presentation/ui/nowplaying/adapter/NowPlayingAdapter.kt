package com.example.movieappclean.presentation.ui.nowplaying.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.movieappclean.data.dataSource.remote.models.NowPlayingResponseResult
import com.example.movieappclean.domain.entities.local.NowPlayingLocal

class NowPlayingAdapter(
    private val listener: NowPlayingItemListener
) : PagingDataAdapter<NowPlayingLocal, NowPlayingViewHolder>(NowPlayingResponseDataDC) {

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        holder.bind(getItem(position) ?: return)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        return NowPlayingViewHolder.create(parent, listener)
    }

}
