package com.example.movieappclean.presentation.ui.toprated.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.movieappclean.domain.entities.local.TopRatedLocal

class TopRatedAdapter(
    private val listener: TopRatedItemListener
) : PagingDataAdapter<TopRatedLocal, TopRatedViewHolder>(TopRatedResponseDataDC) {

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        holder.bind(getItem(position) ?: return)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        return TopRatedViewHolder.create(parent, listener)
    }

}
