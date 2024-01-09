package com.ulugbek.dev.cryptocurency.data.dto

import com.ulugbek.dev.cryptocurency.data.CoinModel
import retrofit2.Response
import retrofit2.http.GET

interface CoinApi {

    @GET("coins")
    suspend fun getCoinList():Response<List<CoinModel>>
}