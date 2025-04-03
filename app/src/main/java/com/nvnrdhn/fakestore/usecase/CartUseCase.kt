package com.nvnrdhn.fakestore.usecase

import com.nvnrdhn.fakestore.model.CartItemModel
import com.nvnrdhn.fakestore.model.ProductModel
import javax.inject.Inject

class CartUseCase @Inject constructor() {
    fun convertProductToCartItem(product: ProductModel) = CartItemModel(
        productId = product.id,
        productName = product.title,
        productPrice = product.price.value,
        productImage = product.image,
        productQuantity = 1,
        currency = product.price.currency
    )
}