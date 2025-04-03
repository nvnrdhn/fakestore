package com.nvnrdhn.fakestore.ui.cart.detail.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.nvnrdhn.fakestore.model.CartItemModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CartDetailItemLayout(
    item: CartItemModel,
    onItemAdd: (CartItemModel) -> Unit = {},
    onItemRemove: (CartItemModel) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(116.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                color = MaterialTheme.colorScheme.background
            ),
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            GlideImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp)
                    .clip(
                        shape = RoundedCornerShape(16.dp)
                    )
                    .background(
                        color = Color.White
                    ),
                model = item.productImage,
                contentScale = ContentScale.Fit,
                contentDescription = null
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = item.productName,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleMedium
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "${item.currency}${item.productPrice}",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Row(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.tertiary,
                                shape = RoundedCornerShape(6.dp)
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        IconButton(
                            modifier = Modifier
                                .size(20.dp)
                                .padding(start = 4.dp),
                            onClick = { onItemRemove(item) }
                        ) {
                            Icon(
                                imageVector = if (item.productQuantity > 1)
                                    Icons.AutoMirrored.Default.KeyboardArrowLeft
                                else Icons.Default.Delete,
                                contentDescription = null
                            )
                        }

                        Text(
                            modifier = Modifier.padding(vertical = 4.dp),
                            text = item.productQuantity.toString()
                        )

                        IconButton(
                            modifier = Modifier
                                .size(20.dp)
                                .padding(end = 4.dp),
                            onClick = { onItemAdd(item) }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CartDetailItemLayout_Preview() {
    CartDetailItemLayout(
        item = CartItemModel(
            productName = "Test",
            productPrice = 412.42,
            productQuantity = 2
        )
    )
}