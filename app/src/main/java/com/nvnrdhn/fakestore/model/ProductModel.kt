package com.nvnrdhn.fakestore.model

data class ProductModel(
    val id: Int = 0,
    val title: String = "",
    val price: PriceModel = PriceModel(),
    val description: String = "",
    val image: String = "",
    val rating: ProductRatingModel = ProductRatingModel()
)
