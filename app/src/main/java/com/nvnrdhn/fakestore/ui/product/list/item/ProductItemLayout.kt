package com.nvnrdhn.fakestore.ui.product.list.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.Placeholder
import com.bumptech.glide.integration.compose.placeholder
import com.nvnrdhn.fakestore.R
import com.nvnrdhn.fakestore.model.PriceModel
import com.nvnrdhn.fakestore.model.ProductModel
import com.nvnrdhn.fakestore.model.ProductRatingModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductItemLayout(
    item: ProductModel
) {
    Column(
        modifier = Modifier
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
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(
                    color = Color.White
                )
        ) {
            GlideImage(
                model = item.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                contentScale = ContentScale.Fit,
                loading = placeholder(ColorPainter(MaterialTheme.colorScheme.primary)),
                transition = CrossFade
            )
        }

        Column(
            modifier = Modifier
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 8.dp
                )
        ) {
            Text(
                text = item.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,
            )

            Text(
                modifier = Modifier
                    .padding(top = 4.dp),
                text = item.price.toDisplayString(),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    modifier = Modifier.size(12.dp),
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiary
                )

                Text(
                    text = stringResource(
                        id = R.string.product_rating_text,
                        item.rating.rate,
                        item.rating.count
                    ),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Preview
@Composable
fun ProductItem_Preview() {
    ProductItemLayout(
        item = ProductModel(
            title = "Test",
            description = "Test test est test",
            price = PriceModel(
                value = 512.53
            ),
            rating = ProductRatingModel(
                rate = 4.2,
                count = 51
            )
        )
    )
}