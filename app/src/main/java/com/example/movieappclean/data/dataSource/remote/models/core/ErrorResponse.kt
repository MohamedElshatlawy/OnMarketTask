package com.example.movieappclean.data.dataSource.remote.models.core

import androidx.annotation.Keep
import com.squareup.moshi.Json
// customizable based on how server error responses are, this is a simple example!!
@Keep
data class ErrorResponse(
    @Json(name = "success") val status: Any? = null, // false
    @Json(name = "status_message") val msg: String? = null // Unauthorized
): IError