package com.nvnrdhn.fakestore.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartItemModel(
    @PrimaryKey var productId: Int,
    var productName: String,
    var productPrice: Double,
    var productQuantity: Int,
    var productImage: String,
    var currency: String
)
