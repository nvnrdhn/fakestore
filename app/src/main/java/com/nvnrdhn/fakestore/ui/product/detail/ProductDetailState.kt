package com.nvnrdhn.fakestore.ui.product.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.nvnrdhn.fakestore.model.ProductModel

class ProductDetailState {
    var product by mutableStateOf(ProductModel())
}