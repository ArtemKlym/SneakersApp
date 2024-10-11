package com.artemklymenko.sneakersapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.artemklymenko.sneakersapp.datastore.TokenUserPreferencesRepository
import com.artemklymenko.sneakersapp.datastore.UserPreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_preferences")

@Module
@InstallIn(SingletonComponent::class)
abstract class UserPreferencesModule {

    @Binds
    @Singleton
    abstract fun bindUserPreferencesRepository(
        tokenUserPreferencesRepository: TokenUserPreferencesRepository
    ): UserPreferencesRepository

    companion object{

        @Provides
        @Singleton
        fun provideUserDataStorePreferences(
            @ApplicationContext applicationContext: Context
        ) : DataStore<Preferences>{
            return applicationContext.dataStore
        }
    }
}