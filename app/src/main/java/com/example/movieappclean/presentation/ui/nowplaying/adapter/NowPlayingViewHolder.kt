package com.example.movieappclean.presentation.ui.nowplaying.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappclean.databinding.NowPlayingListItemBinding
import com.example.movieappclean.domain.entities.local.NowPlayingLocal

class NowPlayingViewHolder(
    private val binding: NowPlayingListItemBinding,
    private val listener: NowPlayingItemListener,
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        @JvmStatic
        fun create(
            parent: ViewGroup,
            listener: NowPlayingItemListener
        ): NowPlayingViewHolder {
            return NowPlayingViewHolder(
                binding = NowPlayingListItemBinding
                    .inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                listener = listener
            )
        }
    }

    fun bind(item: NowPlayingLocal) {
        binding.item = item
        binding.clickListener = listener
        binding.executePendingBindings()
    }
}