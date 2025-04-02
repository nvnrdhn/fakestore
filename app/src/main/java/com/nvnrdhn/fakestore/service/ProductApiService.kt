package com.nvnrdhn.fakestore.service

import com.nvnrdhn.fakestore.datamodel.ProductDataModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {
    @GET("products")
    suspend fun getProductList(): List<ProductDataModel>

    @GET("products/{id}")
    suspend fun getProductDetail(@Path("id") productId: Int): ProductDataModel
}