package com.jetauth.features.home.presentation.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jetauth.core.Result
import com.jetauth.features.home.data.model.Product
import com.jetauth.features.home.presentation.viewmodel.HomeViewModel
import com.jetauth.ui.theme.JetAuthTheme
import coil.compose.AsyncImage
import com.jetauth.R
import com.jetauth.features.home.presentation.components.RatingBar
import com.jetauth.ui.theme.stronglyDeemphasizedAlpha
import androidx.compose.material3.Icon
import androidx.compose.ui.res.stringResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
){
    val productsResult by homeViewModel.products.collectAsState()

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                ),
                title = {
                    Text(
                        stringResource(id = R.string.productList),
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                },
                actions = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "")
                }
            )
        },
        content = {innerPadding ->
            Box (
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ){
                when (productsResult) {
                    is Result.Success -> {
                        val products = (productsResult as Result.Success<List<Product>>).data

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),

                            ) {
                            items(products.size) { index ->
                                products[index].let {
                                    Card(
                                        modifier = Modifier
                                            .padding(horizontal = 10.dp, vertical = 10.dp)
                                            .height(250.dp)

                                    ) {
                                        Column {
                                            AsyncImage(
                                                alignment = Alignment.TopCenter,
                                                placeholder = painterResource(id = R.drawable.placeholder),
                                                model = it.images?.elementAt(0)?.src, contentDescription = "",
                                                error = painterResource(id = R.drawable.placeholder),
                                                modifier = Modifier.height(150.dp)
                                                )
                                            Column (
                                                verticalArrangement = Arrangement.SpaceBetween,
                                                modifier = Modifier.padding(horizontal = 8.dp)
                                            ){
                                                it.name?.let { it1 ->
                                                    Box(
                                                        modifier = Modifier.height(40.dp)
                                                    ) {
                                                        Text(
                                                            it1,
                                                            style = MaterialTheme.typography.bodyLarge,
                                                        )
                                                    } }
                                                Spacer(modifier = Modifier.height(8.dp))
                                                Row {
                                                    it.regularPrice?.let { it1 -> Text(
                                                        "$$it1",
                                                        style = MaterialTheme.typography.bodyLarge.copy(textDecoration = TextDecoration.LineThrough),
                                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = stronglyDeemphasizedAlpha),


                                                    ) }
                                                    Spacer(modifier = Modifier.width(7.dp))
                                                    it.salePrice?.let { it1 -> Text(
                                                        "$$it1",
                                                        style = MaterialTheme.typography.bodyLarge.copy(
                                                            fontWeight = FontWeight.Bold
                                                        )) }
                                                }
                                                Spacer(modifier = Modifier.height(6.dp))
                                                it.ratingCount?.let { it1 -> RatingBar(
                                                    rating = it.ratingCount.toDouble(),
                                                ) }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    is Result.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                    is Result.Error -> {
                        Text(
                            text = "Error: ${(productsResult as Result.Error).exception.message}",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }

        }
    )
}


@Preview(name = "HomeScreen light theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Preview(name = "Sign in dark theme", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomePreview() {
    JetAuthTheme {
        HomeScreen()
    }
}
