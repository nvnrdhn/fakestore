package com.nvnrdhn.fakestore.repo.product

import com.nvnrdhn.fakestore.datamodel.ProductDataModel
import com.nvnrdhn.fakestore.service.ProductApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepoImpl @Inject constructor(
    private val apiService: ProductApiService
) : ProductRepo {
    override fun getProductList(): Flow<List<ProductDataModel>> = flow {
        emit(apiService.getProductList())
    }

    override fun getProductDetail(productId: Int): Flow<ProductDataModel> = flow {
        emit(apiService.getProductDetail(productId))
    }
}