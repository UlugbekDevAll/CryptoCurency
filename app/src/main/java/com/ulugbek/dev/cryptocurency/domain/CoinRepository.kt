package com.ulugbek.dev.cryptocurency.domain

import com.ulugbek.dev.cryptocurency.common.Resource
import com.ulugbek.dev.cryptocurency.data.CoinModel

interface CoinRepository {

    suspend fun getCoinList():Resource<List<CoinModel>>
}