package com.example.movieappclean.data.constants

import java.util.concurrent.TimeUnit

object Constants {
    const val CACHE_SIZE: Long = (10 * 1024 * 1024).toLong()// 10 MB
    const val pageSize: Int = 20

    val setSize = TimeUnit.SECONDS.toMillis(1L)
    val max = TimeUnit.SECONDS.toMillis(3L)
}