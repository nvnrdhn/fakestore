package com.nvnrdhn.fakestore.ui.product.detail

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.nvnrdhn.fakestore.R
import com.nvnrdhn.fakestore.base.BaseScreen
import com.nvnrdhn.fakestore.base.BaseScreen_Preview
import com.nvnrdhn.fakestore.model.PriceModel
import com.nvnrdhn.fakestore.model.ProductModel

@Composable
fun ProductDetailScreen(
    vm: ProductDetailVM = viewModel(),
    productId: Int
) {
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    LaunchedEffect(vm, productId) {
        vm.fetchProductDetail(productId)
    }

    BaseScreen(
        vm = vm,
        topBar = {
            ProductDetailTopBar(
                onBackPressed = { onBackPressedDispatcher?.onBackPressed() },
                onCartClicked = { vm.onCartClicked() }
            )
        }
    ) { innerPadding ->
        ProductDetailContent(
            modifier = Modifier
                .fillMaxSize(),
            state = vm.state
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductDetailContent(
    modifier: Modifier = Modifier,
    state: ProductDetailState = ProductDetailState()
) {
    val scrollState = rememberScrollState()

    Column (
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(
                    state = scrollState
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(235.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(color = Color.White)
                ) {
                    GlideImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        model = state.product.image,
                        contentScale = ContentScale.Fit,
                        contentDescription = null
                    )
                }

                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = state.product.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )

                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = state.product.price.toDisplayString(),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.tertiary
                    )

                    Text(
                        text = stringResource(
                            id = R.string.product_rating_text,
                            state.product.rating.rate,
                            state.product.rating.count
                        )
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(top = 2.dp)
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(8.dp),
                            brush = SolidColor(MaterialTheme.colorScheme.tertiary)
                        )
                ) {
                    Text(
                        modifier = Modifier
                            .padding(
                                horizontal = 6.dp,
                                vertical = 2.dp
                            ),
                        text = state.product.category,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
                
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = stringResource(R.string.product_desc_title),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    modifier = Modifier.padding(top = 2.dp),
                    text = state.product.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.background
                )
        ) {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                color = MaterialTheme.colorScheme.onBackground
            )

            Column(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {}
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = null
                        )

                        Text(
                            text = stringResource(R.string.add_to_cart_button_text),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductDetailTopBar(
    onCartClicked: () -> Unit = {},
    onBackPressed: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp
            ),
        title = {
            Text(
                text = stringResource(id = R.string.title_activity_product_detail)
            )
        },
        actions = {
            IconButton(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(32.dp),
                onClick = onCartClicked
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = null
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = onBackPressed
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )
}

@Preview
@Composable
fun ProductDetailContent_Preview() {
    BaseScreen_Preview(
        topBar = {
            ProductDetailTopBar()
        }
    ) {
        ProductDetailContent(
            state = ProductDetailState().apply {
                product = ProductModel(
                    title = "Test product",
                    description = "tests ts dgf g erg drgdfgd sdfsf",
                    price = PriceModel(
                        value = 512.41
                    ),
                    category = "Clothing"
                )
            }
        )
    }
}