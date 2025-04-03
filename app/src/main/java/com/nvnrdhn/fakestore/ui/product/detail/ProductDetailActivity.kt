package com.nvnrdhn.fakestore.ui.product.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailActivity : ComponentActivity() {
    companion object {
        const val PRODUCT_ID_EXTRA = "PRODUCT_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val productId = intent.getIntExtra(PRODUCT_ID_EXTRA, 0)

        enableEdgeToEdge()
        setContent {
            ProductDetailScreen(
                productId = productId
            )
        }
    }
}