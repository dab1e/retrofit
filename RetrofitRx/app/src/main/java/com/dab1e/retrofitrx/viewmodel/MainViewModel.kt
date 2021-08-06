package com.dab1e.retrofitrx.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dab1e.retrofitrx.Dog
import com.dab1e.retrofitrx.Tivi
import com.dab1e.retrofitrx.coroutines.RetrofitCoroutines
import com.dab1e.retrofitrx.coroutines.URL.Companion.URL_DOG

import com.dab1e.retrofitrx.coroutines.URL.Companion.URL_GIT
import com.dab1e.retrofitrx.rx.ApiClientRx
import com.dab1e.retrofitrx.model.DataZip
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.dab1e.retrofitrx.User
import io.reactivex.Observable

import io.reactivex.Observer as Observer

//https://random.dog/woof.json
//https://my-json-server.typicode.com
class MainViewModel: ViewModel() {



    private val muZip = MutableLiveData<DataZip>()
    fun zip(): MutableLiveData<DataZip> {
        val dog = ApiClientRx(URL_DOG).getApiDog()
        val user = ApiClientRx(URL_GIT).getApiUser()
        val tv = ApiClientRx(URL_GIT).getApiTV()

        Observable.zip(user,tv,dog, Function3<List<User>,List<Tivi>, Dog, DataZip>{
            user,tv,dog -> return@Function3 DataZip(user,tv,dog).also { muZip.postValue(it) }
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
        return muZip
    }

    fun merge(){
        Observable.merge(ApiClientRx(URL_GIT).getApiUser(),
            ApiClientRx(URL_GIT).getApiTV(),
            ApiClientRx(URL_DOG).getApiDog())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }


    private val RxmLDBuSer: MutableLiveData<List<User>> = MutableLiveData()
    fun getRxUser():MutableLiveData<List<User>>{
        val apiClient = ApiClientRx(URL_GIT)
        apiClient.getApiUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :Observer<List<User>> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(t: List<User>) {
                    RxmLDBuSer.postValue(t)
                }
                override fun onError(e: Throwable) {}
                override fun onComplete() {}
                
            })
        return RxmLDBuSer
    }

    private val RxmLDDog = MutableLiveData<Dog>()
    fun getRxDog():MutableLiveData<Dog>{
        val apiClient = ApiClientRx(URL_DOG)
        apiClient.getApiDog()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Dog> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(t: Dog) {
                    RxmLDDog.postValue(t)
                }

                override fun onError(e: Throwable) {}
                override fun onComplete() {}
            })
        return RxmLDDog
    }

    private val RxmLDTV = MutableLiveData<List<Tivi>>()
    fun getRxTV() : MutableLiveData<List<Tivi>>{
        val apiClient = ApiClientRx(URL_GIT)
        apiClient.getApiTV()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Tivi>> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(t: List<Tivi>) {
                    RxmLDTV.postValue(t)
                }

                override fun onError(e: Throwable) {}
                override fun onComplete() {}
            })
        return RxmLDTV
    }



    private val comuld = MutableLiveData<List<User>>()
    fun getUserCoro(): MutableLiveData<List<User>> {
        GlobalScope.launch(Dispatchers.Main) {
            comuld.value = RetrofitCoroutines.retrofit.getUser()
        }
        return comuld
    }

    private val comuldog = MutableLiveData<Dog>()
    fun getDogCoro(): MutableLiveData<Dog> {
        GlobalScope.launch(Dispatchers.Main) {
            comuldog.value = RetrofitCoroutines.retrofit.getDog()
        }
        return comuldog
    }

    private val comultv= MutableLiveData<List<Tivi>>()
    fun getTVCoro(): MutableLiveData<List<Tivi>> {
        GlobalScope.launch(Dispatchers.Main) {
            comultv.value = RetrofitCoroutines.retrofit.getTV()
        }
        return comultv
    }



}










