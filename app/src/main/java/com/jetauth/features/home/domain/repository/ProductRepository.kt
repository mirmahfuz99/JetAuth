package com.jetauth.features.home.domain

import android.content.Context
import com.jetauth.core.Result
import com.jetauth.features.home.data.model.Product

abstract class ProductRepository {
    abstract suspend fun getProducts(context: Context): Result<List<Product>>
}