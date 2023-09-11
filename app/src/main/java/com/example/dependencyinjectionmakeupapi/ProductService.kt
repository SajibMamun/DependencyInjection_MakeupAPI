package com.example.dependencyinjectionmakeupapi

import com.example.dependencyinjectionmakeupapi.dataclass.ResponseProduct
import com.example.dependencyinjectionmakeupapi.dataclass.ResponseProductItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    @GET("products.json")
    fun getAllProducts():Call<ResponseProduct>


    //for getspecific product
    @GET("products/{pid}.json")
    fun getProductsById(@Path("pid") pid:Int):Call<ResponseProductItem>
}