package com.nvnrdhn.fakestore.datamodel

data class ProductDataModel(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val image: String,
    val rating: ProductRatingDataModel,
    val category: String
)