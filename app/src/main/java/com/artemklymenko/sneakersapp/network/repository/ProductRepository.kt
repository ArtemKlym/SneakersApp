package com.artemklymenko.sneakersapp.network.repository

import com.artemklymenko.sneakersapp.domain.models.network.Product
import com.artemklymenko.sneakersapp.network.ProductApi
import com.artemklymenko.sneakersapp.domain.models.network.Products
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productApi: ProductApi
) {
    suspend fun fetchShoes(id: Int): Product {
        return productApi.fetchShoes(id)
    }

    suspend fun getAllShoes(): Products {
        return productApi.getAllShoes()
    }
}