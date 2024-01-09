package com.ulugbek.dev.cryptocurency.domain

import com.ulugbek.dev.cryptocurency.common.Resource
import com.ulugbek.dev.cryptocurency.data.CoinModel
import com.ulugbek.dev.cryptocurency.data.dto.CoinApi
import java.lang.Exception
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor (
    val api: CoinApi
):CoinRepository {
    override suspend fun getCoinList(): Resource<List<CoinModel>> {
        return try {
            val result=api.getCoinList()
            if(result.isSuccessful && result.body()!=null){
                Resource.Success(result.body())
            }else{
                Resource.Error(message = result.message())
            }
        }catch (e:Exception){
            Resource.Error(message = e.message)
        }
    }
}