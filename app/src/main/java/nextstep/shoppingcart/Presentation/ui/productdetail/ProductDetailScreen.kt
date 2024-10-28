package nextstep.shoppingcart.Presentation.ui.productdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import nextstep.shoppingcart.Presentation.common.currency
import nextstep.shoppingcart.Presentation.component.BackButtonTopBar
import nextstep.shoppingcart.Presentation.ui.theme.Blue50
import nextstep.shoppingcart.Presentation.ui.theme.ShoppingCartTheme
import nextstep.shoppingcart.data.model.Product
import nextstep.shoppingcart.data.repository.ProductRepository
import nextstep.signup.R

@Composable
fun ProductDetailScreen(
    id: Long,
    onBack: () -> Unit,
) {
    val product = ProductRepository.findProductById(id)

    Scaffold(
        topBar = {
            BackButtonTopBar(
                title = stringResource(id = R.string.product_detail_title),
                onBack = onBack,
            )
        },
    ) { paddingValues ->
        ProductDetailContent(
            product = product,
            modifier = Modifier.padding(paddingValues = paddingValues),
        )
    }
}

@Composable
private fun ProductDetailContent(
    product: Product,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    Column {
        AsyncImage(
            modifier =
                modifier
                    .wrapContentWidth()
                    .aspectRatio(1f),
            contentScale = ContentScale.Crop,
            model = product.imageUrl,
            contentDescription = null,
            error = painterResource(R.drawable.ic_launcher_background),
        )

        Text(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(all = 18.dp),
            text = product.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )

        HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp)

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(all = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = stringResource(R.string.price),
                fontSize = 20.sp,
            )

            Text(
                text = product.price.currency(context),
                fontSize = 20.sp,
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier.fillMaxWidth().height(54.dp),
            colors = ButtonDefaults.buttonColors(Blue50),
            shape = RectangleShape,
            onClick = {},
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = "장바구니",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }
    }
}

@Preview
@Composable
private fun ProductDetailScreenPreview() {
    ShoppingCartTheme {
        ProductDetailScreen(id = 0L, onBack = {})
    }
}