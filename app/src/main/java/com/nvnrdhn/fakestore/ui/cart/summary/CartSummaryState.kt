package com.nvnrdhn.fakestore.ui.cart.summary

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import com.nvnrdhn.fakestore.model.CartItemModel

class CartSummaryState {
    val cartItems = mutableStateListOf<CartItemModel>()

    val totalPrice by derivedStateOf {
        cartItems.sumOf { it.productPrice * it.productQuantity }
    }
}