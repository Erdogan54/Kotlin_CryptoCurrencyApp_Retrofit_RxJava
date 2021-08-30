package com.ozgurerdogan.kotlin_cryptocurrencyapp_retrofit_rxjava.service

import com.ozgurerdogan.kotlin_cryptocurrencyapp_retrofit_rxjava.model.CryptoModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import java.util.*

interface CryptoAPI {

    // GET,POST, UPDATE, DELETE

    // https://api.nomics.com/v1/
    // API key: prices?key=540212a3d3731d18fdc2586b44c753b5741c13fe

    @GET("prices?key=540212a3d3731d18fdc2586b44c753b5741c13fe")

    fun getData() :Observable<List<CryptoModel>>

    //fun getData() :Call<List<CryptoModel>>
}