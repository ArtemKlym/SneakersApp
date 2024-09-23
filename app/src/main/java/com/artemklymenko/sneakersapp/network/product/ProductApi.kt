package com.artemklymenko.sneakersapp.network.product

import com.artemklymenko.sneakersapp.domain.models.network.product.Product
import com.artemklymenko.sneakersapp.domain.models.network.product.Products
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET("product/{id}")
    suspend fun fetchShoes(@Path("id") id: Int): Product

    @GET("products/category/mens-shoes")
    suspend fun getAllShoes(): Products
}