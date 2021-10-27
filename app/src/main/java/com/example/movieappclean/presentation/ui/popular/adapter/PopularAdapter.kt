package com.example.movieappclean.presentation.ui.popular.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.movieappclean.data.dataSource.remote.models.PopularResponseResult
import com.example.movieappclean.domain.entities.local.PopularLocal

class PopularAdapter(
    private val listener: PopularItemListener
) : PagingDataAdapter<PopularLocal, PopularViewHolder>(PopularResponseDataDC) {

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.bind(getItem(position) ?: return)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder.create(parent, listener)
    }

}
