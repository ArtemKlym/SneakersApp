package com.artemklymenko.sneakersapp.di

import com.artemklymenko.sneakersapp.network.ProductApi
import com.artemklymenko.sneakersapp.network.repository.ProductRepository
import com.artemklymenko.sneakersapp.utils.Constants.PRODUCT_API_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ProductApiModule {

    @Provides
    @Singleton
    fun provideProductInterceptorOkHttpClient(): OkHttpClient{
        return OkHttpClient
            .Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(PRODUCT_API_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideProductApi(retrofit: Retrofit): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(productApi: ProductApi): ProductRepository {
        return ProductRepository(productApi)
    }
}