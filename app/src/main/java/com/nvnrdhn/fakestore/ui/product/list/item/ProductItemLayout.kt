package com.nvnrdhn.fakestore.ui.product.list.item

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nvnrdhn.fakestore.model.ProductModel

@Composable
fun ProductItemLayout(
    item: ProductModel
) {
    Column {
        Text(
            text = item.title
        )

        Text(
            text = item.description
        )

        Text(
            text = item.price.toString()
        )
    }
}

@Preview
@Composable
fun ProductItem_Preview() {
    ProductItemLayout(
        item = ProductModel(
            title = "Test",
            description = "Test test est test",
            price = 512.53
        )
    )
}