package com.jetauth.features.home.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("date_created") val dateCreated: String? = null,
    @SerializedName("price") val price: String? = null,
    @SerializedName("regular_price") val regularPrice: String? = null,
    @SerializedName("sale_price") val salePrice: String? = null,
    @SerializedName("total_sales") val totalSales: Int? = null,
    @SerializedName("rating_count") val ratingCount: Int? = null,
    @SerializedName("images") val images: List<Image>? = null
)

data class Image(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("src") val src: String? = null,
    @SerializedName("name") val name: String? = null
)