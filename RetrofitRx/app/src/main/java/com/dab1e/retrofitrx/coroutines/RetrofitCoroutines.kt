package com.dab1e.retrofitrx.coroutines

import com.dab1e.retrofitrx.coroutines.URL.Companion.URL_GIT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCoroutines {

    val retrofit = Retrofit.Builder()
        .baseUrl(URL_GIT)
        .client(getHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiCoroutine::class.java)

    private fun getHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        return httpClient.build()
    }

}