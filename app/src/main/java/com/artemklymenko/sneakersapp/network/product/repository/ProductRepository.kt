package com.artemklymenko.sneakersapp.network.product.repository

import com.artemklymenko.sneakersapp.domain.models.network.product.Product
import com.artemklymenko.sneakersapp.network.product.ProductApi
import com.artemklymenko.sneakersapp.domain.models.network.product.Products
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