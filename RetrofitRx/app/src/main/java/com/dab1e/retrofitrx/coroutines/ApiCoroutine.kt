package com.dab1e.retrofitrx.coroutines

import com.dab1e.retrofitrx.Dog
import com.dab1e.retrofitrx.Tivi
import com.dab1e.retrofitrx.User
import io.reactivex.Observable

import retrofit2.http.GET
import retrofit2.http.Url

interface ApiCoroutine{

    @GET("/dab1e/json/user")
    suspend fun getUser():List<User>

    @GET("/woof.json")
    suspend fun getDog(): Dog

    @GET("/dab1e/json/TV")
    suspend fun getTV(): List<Tivi>
}
