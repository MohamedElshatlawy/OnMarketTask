package com.example.movieappclean.di

import android.content.Context
import com.example.movieappclean.BuildConfig
import com.example.movieappclean.data.constants.Constants.CACHE_SIZE
import com.example.movieappclean.data.dataSource.remote.factory.NetworkResponseFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    //Hilt Provide Network Cache
    @Provides
    @Singleton
    fun provideCache(@ApplicationContext context: Context): Cache =
        Cache(context.cacheDir, CACHE_SIZE)

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(
        moshi: Moshi
    ): Converter.Factory {
        return MoshiConverterFactory.create(
            moshi
        )
    }

    @Singleton
    @Provides
    fun provideHttpLogging(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
        }
    }

    @HttpHeaderInterceptor
    @Singleton
    @Provides
    fun provideHeaderInterceptor(
    ): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val request = originalRequest.newBuilder().apply {
                addHeader("Accept", "application/json")
                addHeader("Content-Type", "application/json")
                addHeader("lang", "en")
            }

            chain.proceed(request.build())
        }
    }

    @HttpQueryInterceptor
    @Singleton
    @Provides
    fun provideHttpQueryInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original: Request = chain.request()
            val originalHttpUrl: HttpUrl = original.url
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.apiKey)
                .build()
            val requestBuilder = original.newBuilder().url(url)
            chain.proceed(requestBuilder.build())
        }
    }

    @Singleton
    @Provides
    fun provideOkHttp(
        cache: Cache,
        loggingInterceptor: HttpLoggingInterceptor,
        @HttpHeaderInterceptor headerInterceptor: Interceptor,
        @HttpQueryInterceptor httpQueryInterceptor: Interceptor

    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(httpQueryInterceptor)
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .cache(cache)
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        convertFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.baseUrl)
            .addConverterFactory(convertFactory)
            .addCallAdapterFactory(NetworkResponseFactory())
            .build()
    }


}


