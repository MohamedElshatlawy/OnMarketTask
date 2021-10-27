package com.example.movieappclean.presentation.ui.popular.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.movieappclean.data.dataSource.remote.models.PopularResponseResult
import com.example.movieappclean.domain.entities.local.PopularLocal

object PopularResponseDataDC : DiffUtil.ItemCallback<PopularLocal>() {
    override fun areItemsTheSame(oldItem: PopularLocal, newItem: PopularLocal): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PopularLocal, newItem: PopularLocal): Boolean =
        oldItem == newItem

}