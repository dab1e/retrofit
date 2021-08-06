package com.dab1e.retrofitrx.rx

import com.dab1e.retrofitrx.Dog
import com.dab1e.retrofitrx.Tivi
import com.dab1e.retrofitrx.User
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class ApiClientRx(url: String) {
    private val userService = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(getHttpClient())
        .build()
        .create(ApiServer::class.java)

    private fun getHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        return httpClient.build()
    }

    fun getApiUser(): Observable<List<User>> {
        return this.userService.getUserApiSever()
    }

    fun getApiDog(): Observable<Dog> {
        return this.userService.getDog()
    }

    fun getApiTV(): Observable<List<Tivi>> {
        return this.userService.getTV()
    }
}