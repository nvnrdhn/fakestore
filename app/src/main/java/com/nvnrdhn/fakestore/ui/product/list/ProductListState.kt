package com.nvnrdhn.fakestore.ui.product.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.nvnrdhn.fakestore.model.ProductModel

class ProductListState {
    var isProfileSheetVisible by mutableStateOf(false)
    val productList = mutableStateListOf<ProductModel>()
}