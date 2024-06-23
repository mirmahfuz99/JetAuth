package com.jetauth.features.home.data.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jetauth.core.Result
import com.jetauth.features.home.data.model.Product
import com.jetauth.features.home.domain.ProductRepository
import okio.IOException
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(

) : ProductRepository() {
    override suspend fun getProducts(context: Context): Result<List<Product>> {
        return try {
           var jsonString = context.assets.open("response.json").bufferedReader().use { it.readText() }
            Log.i("data", jsonString)

            val gson = Gson()
            val listProductType = object : TypeToken<List<Product>>() {}.type

            var products: List<Product> = gson.fromJson(jsonString, listProductType)


            Result.Success(products)

        }catch (ioException: IOException){
            Result.Error(Exception("Unknown error: ${ioException.message}"))
        }catch (e: Exception) {
            Result.Error(e)
        }
    }

}