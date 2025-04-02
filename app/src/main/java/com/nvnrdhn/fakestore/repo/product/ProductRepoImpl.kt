package com.nvnrdhn.fakestore.repo.product

import com.nvnrdhn.fakestore.datamodel.ProductDataModel
import com.nvnrdhn.fakestore.service.ProductApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepoImpl @Inject constructor(
    private val apiService: ProductApiService
) : ProductRepo {
    override fun getProductList(): Flow<List<ProductDataModel>> {
        TODO("Not yet implemented")
    }

    override fun getProductDetail(productId: Int): Flow<ProductDataModel> {
        TODO("Not yet implemented")
    }

}