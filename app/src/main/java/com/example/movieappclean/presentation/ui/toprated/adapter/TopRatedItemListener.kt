package com.example.movieappclean.presentation.ui.toprated.adapter

import com.example.movieappclean.domain.entities.local.TopRatedLocal

interface TopRatedItemListener {
    fun onItemClick(item: TopRatedLocal)
}

