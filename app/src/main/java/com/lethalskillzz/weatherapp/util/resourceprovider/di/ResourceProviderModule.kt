package com.lethalskillzz.weatherapp.util.resourceprovider.di

import android.content.Context
import com.lethalskillzz.weatherapp.util.resourceprovider.ResourceProvider
import com.lethalskillzz.weatherapp.util.resourceprovider.impl.ResourceProviderImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ResourceProviderModule {

    @Provides
    @Singleton
    fun provideResourceProvider(
        @ApplicationContext applicationContext: Context
    ): ResourceProviderImpl = ResourceProviderImpl(applicationContext)
}

@Module
@InstallIn(SingletonComponent::class)
interface ResourceProviderBindingModule {

    @Binds
    @Singleton
    fun bindResourceProvider(resourceProviderImpl: ResourceProviderImpl): ResourceProvider
}
