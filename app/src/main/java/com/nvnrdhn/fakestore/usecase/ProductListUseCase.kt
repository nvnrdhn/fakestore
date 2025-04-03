package com.nvnrdhn.fakestore.usecase

import com.nvnrdhn.fakestore.datamodel.ProductDataModel
import com.nvnrdhn.fakestore.model.PriceModel
import com.nvnrdhn.fakestore.model.ProductModel
import com.nvnrdhn.fakestore.model.ProductRatingModel
import javax.inject.Inject

class ProductListUseCase @Inject constructor() {
    fun mapProductResponse(response: List<ProductDataModel>): List<ProductModel> = response.map {
        ProductModel(
            id = it.id,
            title = it.title,
            description = it.description,
            price = PriceModel(
                value = it.price
            ),
            image = it.image,
            rating = ProductRatingModel(
                rate = it.rating.rate,
                count = it.rating.count
            )
        )
    }
}