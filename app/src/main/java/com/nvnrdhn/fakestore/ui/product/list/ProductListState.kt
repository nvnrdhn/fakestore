package com.nvnrdhn.fakestore.ui.product.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.nvnrdhn.fakestore.model.ProductModel

class ProductListState {
    var isProfileSheetVisible by mutableStateOf(false)
    var productList = listOf<ProductModel>()
    val displayList = mutableStateListOf<Any>()
    var sortBy by mutableStateOf(SortBy.Name)

    enum class SortBy {
        Name, Category, Price, Rating
    }
}