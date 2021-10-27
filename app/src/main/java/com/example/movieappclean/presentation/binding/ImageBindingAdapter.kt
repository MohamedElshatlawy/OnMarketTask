package com.example.movieappclean.presentation.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.imageLoader
import coil.request.ImageRequest
import com.example.movieappclean.BuildConfig
import com.facebook.shimmer.ShimmerFrameLayout


@BindingAdapter(value = ["loadImage", "loadShimmer"], requireAll = false)
fun ImageView.loadImage(
    imagePath: String?,
    shimmer: ShimmerFrameLayout? = null
) {
// Coil
    context.imageLoader.enqueue(request = ImageRequest.Builder(context)
        .data(BuildConfig.imageBaseUrl+imagePath.toString())
        .target(
            onStart = { shimmer?.loadShimmerView(isLoading = true) },
            onSuccess = { result ->
                shimmer?.loadShimmerView(isLoading = false)
                this.setImageDrawable(result)
            },
            onError = { shimmer?.loadShimmerView(isLoading = false) }
        ).build())
}

