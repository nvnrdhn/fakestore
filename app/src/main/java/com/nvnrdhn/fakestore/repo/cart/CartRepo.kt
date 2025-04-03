package com.nvnrdhn.fakestore.repo.cart

import com.nvnrdhn.fakestore.model.CartItemModel
import kotlinx.coroutines.flow.Flow

interface CartRepo {
    fun fetchCartItems(): Flow<List<CartItemModel>>
    fun getCartItem(productId: Int): Flow<CartItemModel>
    fun updateCart(cartItem: CartItemModel): Flow<Boolean>
    fun deleteCart(cartItem: CartItemModel): Flow<Boolean>
    fun insertCart(cartItem: CartItemModel): Flow<Boolean>
}