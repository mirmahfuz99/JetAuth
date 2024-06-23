package com.jetauth.features.home.presentation.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jetauth.core.Result
import com.jetauth.features.home.data.model.Product
import com.jetauth.features.home.presentation.viewmodel.HomeViewModel
import com.jetauth.ui.theme.JetAuthTheme

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
){
    val productsResult by homeViewModel.products.collectAsState()

    Scaffold (
        content = {innerPadding ->
            Box (
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ){
                when (productsResult) {
                    is Result.Success -> {
                        val products = (productsResult as Result.Success<List<Product>>).data

                        LazyColumn {
                            items(products.size) { index ->
                                products[index].name?.let { Text(it) }
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
