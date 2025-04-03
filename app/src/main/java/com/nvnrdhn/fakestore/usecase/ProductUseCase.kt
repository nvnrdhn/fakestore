package com.nvnrdhn.fakestore.usecase

import com.nvnrdhn.fakestore.datamodel.ProductDataModel
import com.nvnrdhn.fakestore.model.PriceModel
import com.nvnrdhn.fakestore.model.ProductModel
import com.nvnrdhn.fakestore.model.ProductRatingModel
import javax.inject.Inject

class ProductUseCase @Inject constructor() {
    fun mapProductResponse(response: List<ProductDataModel>): List<ProductModel> = response.map { mapProductItem(it) }

    fun mapProductItem(dataModel: ProductDataModel) = ProductModel(
        id = dataModel.id,
        title = dataModel.title,
        description = dataModel.description,
        price = PriceModel(
            value = dataModel.price
        ),
        image = dataModel.image,
        rating = ProductRatingModel(
            rate = dataModel.rating.rate,
            count = dataModel.rating.count
        ),
        category = dataModel.category
    )
}