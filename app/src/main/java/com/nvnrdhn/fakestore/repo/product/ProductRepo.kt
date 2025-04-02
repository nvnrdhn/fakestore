package com.nvnrdhn.fakestore.repo.product

import com.nvnrdhn.fakestore.datamodel.ProductDataModel
import kotlinx.coroutines.flow.Flow

interface ProductRepo {
    fun getProductList(): Flow<List<ProductDataModel>>
    fun getProductDetail(productId: Int): Flow<ProductDataModel>
}