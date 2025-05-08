package com.nvnrdhn.fakestore.ui.product.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.nvnrdhn.fakestore.model.ProductModel
import com.nvnrdhn.fakestore.model.ProfileModel

class ProductListState {
    var isProfileSheetVisible by mutableStateOf(false)
    var productList = listOf<ProductModel>()
    val displayList = mutableStateListOf<Any>()
    var sortBy by mutableStateOf(SortBy.Name)
    var profileData by mutableStateOf(ProfileModel())
    var profileLoading by mutableStateOf(false)

    enum class SortBy {
        Name, Category, Price, Rating
    }
}