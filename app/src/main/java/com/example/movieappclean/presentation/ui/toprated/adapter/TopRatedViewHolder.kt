package com.example.movieappclean.presentation.ui.toprated.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappclean.databinding.TopRatedListItemBinding
import com.example.movieappclean.domain.entities.local.TopRatedLocal

class TopRatedViewHolder(
    private val binding: TopRatedListItemBinding,
    private val listener: TopRatedItemListener,
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        @JvmStatic
        fun create(
            parent: ViewGroup,
            listener: TopRatedItemListener
        ): TopRatedViewHolder {
            return TopRatedViewHolder(
                binding = TopRatedListItemBinding
                    .inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                listener = listener
            )
        }
    }
    fun bind(item: TopRatedLocal) {
        binding.item = item
        binding.clickListener = listener
        binding.executePendingBindings()
    }
}