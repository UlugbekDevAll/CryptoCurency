package com.ulugbek.dev.cryptocurency.di

import com.ulugbek.dev.cryptocurency.data.dto.CoinApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getCoinApi():CoinApi =Retrofit
        .Builder()
        .baseUrl("https://api.coinpaprika.com/v1/")
        .build()
        .create()


}