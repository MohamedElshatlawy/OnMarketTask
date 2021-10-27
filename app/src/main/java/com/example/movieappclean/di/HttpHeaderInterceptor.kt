package com.example.movieappclean.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HttpHeaderInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HttpQueryInterceptor
