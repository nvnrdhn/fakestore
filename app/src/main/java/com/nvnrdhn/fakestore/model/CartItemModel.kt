package com.nvnrdhn.fakestore.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartItemModel(
    @PrimaryKey var productId: Int = 0,
    var productName: String = "",
    var productPrice: Double = .0,
    var productQuantity: Int = 0,
    var productImage: String = "",
    var currency: String = "$"
)
