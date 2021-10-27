package com.example.movieappclean.presentation.ui.toprated.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.movieappclean.domain.entities.local.TopRatedLocal


object TopRatedResponseDataDC : DiffUtil.ItemCallback<TopRatedLocal>() {
    override fun areItemsTheSame(oldItem: TopRatedLocal, newItem: TopRatedLocal): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: TopRatedLocal, newItem: TopRatedLocal): Boolean =
        oldItem == newItem

}