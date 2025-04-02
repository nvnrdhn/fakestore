package com.nvnrdhn.fakestore.model

data class ProductModel(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val image: String,
    val rating: ProductRatingModel
)
