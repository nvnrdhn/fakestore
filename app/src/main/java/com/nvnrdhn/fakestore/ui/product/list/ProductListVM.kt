package com.nvnrdhn.fakestore.ui.product.list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductListVM @Inject constructor() : ViewModel() {
    val state = ProductListState()

    fun fetchProductList() {

    }

    fun toggleProfileSheet() {
        state.isProfileSheetVisible = !state.isProfileSheetVisible
    }
}