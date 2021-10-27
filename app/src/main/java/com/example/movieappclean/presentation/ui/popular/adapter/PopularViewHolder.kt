package com.example.movieappclean.presentation.ui.popular.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappclean.data.dataSource.remote.models.PopularResponseResult
import com.example.movieappclean.databinding.PopularListItemBinding
import com.example.movieappclean.domain.entities.local.PopularLocal

class PopularViewHolder(
    private val binding: PopularListItemBinding,
    private val listener: PopularItemListener,
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        @JvmStatic
        fun create(
            parent: ViewGroup,
            listener: PopularItemListener
        ): PopularViewHolder {
            return PopularViewHolder(
                binding = PopularListItemBinding
                    .inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                listener = listener
            )
        }
    }
    fun bind(item: PopularLocal) {
        binding.item = item
        binding.clickListener = listener
        binding.executePendingBindings()
    }
}