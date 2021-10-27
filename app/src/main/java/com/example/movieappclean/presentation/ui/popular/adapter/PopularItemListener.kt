package com.example.movieappclean.presentation.ui.popular.adapter

import com.example.movieappclean.domain.entities.local.PopularLocal

interface PopularItemListener {
    fun onItemClick(item: PopularLocal)
}

