package com.example.catdigital_yardclub.network

import com.example.catdigital_yardclub.utils.YardClubConstants.YARD_BASE_URL
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private val TAG = RetrofitClient::class.java.simpleName

    private fun createRetrofitClient(): Retrofit {
        val okHttpClient = OkHttpClient.Builder().readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return Retrofit.Builder()
            .baseUrl(YARD_BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @JvmStatic
    fun<S> getClient(serviceClass : Class<S>) : S {
        return createRetrofitClient().create(serviceClass)
    }
}


