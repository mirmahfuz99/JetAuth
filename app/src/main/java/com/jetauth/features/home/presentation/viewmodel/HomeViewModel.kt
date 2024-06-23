package com.jetauth.features.home.presentation.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetauth.core.Result
import com.jetauth.features.home.data.model.Product
import com.jetauth.features.home.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor (
    private val productRepository: ProductRepository,
    private val applicationContext: Context
): ViewModel() {

    private val _products = MutableStateFlow<Result<List<Product>>>(Result.Loading)
    val products = _products

    var isLoading by mutableStateOf(false)
        private set

    init {
        fetchProducts()
    }
    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                val result = productRepository.getProducts(applicationContext)
                _products.value = result

            } catch (e: Exception){
                _products.value = Result.Error(e)
            }
        }
    }
}


