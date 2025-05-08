package com.nvnrdhn.fakestore.usecase

import com.nvnrdhn.fakestore.datamodel.ProductDataModel
import com.nvnrdhn.fakestore.model.PriceModel
import com.nvnrdhn.fakestore.model.ProductModel
import com.nvnrdhn.fakestore.model.ProductRatingModel
import com.nvnrdhn.fakestore.ui.product.list.ProductListState
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

    fun getSortedProductList(
        productList: List<ProductModel>,
        sortBy: ProductListState.SortBy
    ): List<Any> {
        return when (sortBy) {
            ProductListState.SortBy.Name -> productList.sortedBy { it.title }
            ProductListState.SortBy.Price -> productList.sortedBy { it.price.value }
            ProductListState.SortBy.Rating -> productList.sortedByDescending { it.rating.rate }
            ProductListState.SortBy.Category -> {
                val list = mutableListOf<Any>()

                productList.sortedBy { it.category }
                    .groupBy { it.category }
                    .forEach { (category, products) ->
                        list.add(category)
                        list.addAll(products)
                    }

                list
            }
        }
    }

}