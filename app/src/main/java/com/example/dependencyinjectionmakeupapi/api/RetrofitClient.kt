package com.example.dependencyinjectionmakeupapi.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(Singleton::class)
@Module
object RetrofitClient {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit = Retrofit.Builder()
        .baseUrl("https://makeup-api.herokuapp.com/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    @Singleton
    @Provides
fun provideProductService(retrofit:Retrofit): ProductService{
    return    retrofit.create(ProductService::class.java)
}

}