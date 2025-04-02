package com.nvnrdhn.fakestore.model

data class ProductModel(
    val id: Int = 0,
    val title: String = "",
    val price: Double = .0,
    val description: String = "",
    val image: String = "",
    val rating: ProductRatingModel = ProductRatingModel()
)
