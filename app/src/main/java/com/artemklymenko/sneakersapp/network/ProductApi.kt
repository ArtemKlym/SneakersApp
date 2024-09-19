package com.artemklymenko.sneakersapp.network

import com.artemklymenko.sneakersapp.domain.models.network.Product
import com.artemklymenko.sneakersapp.domain.models.network.Products
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET("product/{id}")
    suspend fun fetchShoes(@Path("id") id: Int): Product

    @GET("products/category/mens-shoes")
    suspend fun getAllShoes(): Products
}