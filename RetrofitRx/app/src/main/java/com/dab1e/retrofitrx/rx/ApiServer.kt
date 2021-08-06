package com.dab1e.retrofitrx.rx

import com.dab1e.retrofitrx.Dog
import com.dab1e.retrofitrx.Tivi
import com.dab1e.retrofitrx.User
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiServer {
    @GET("/dab1e/json/user")
    fun getUserApiSever():Observable<List<User>>

    @GET("/woof.json")
    fun getDog():Observable<Dog>

    @GET("/dab1e/json/TV")
    fun getTV(): Observable<List<Tivi>>
}