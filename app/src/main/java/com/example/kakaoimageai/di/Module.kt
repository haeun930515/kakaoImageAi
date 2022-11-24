package com.example.kakaoimageai.di

import com.example.kakaoimageai.data.remote.DallE
import com.example.kakaoimageai.data.repository.DallERepositoryImpl
import com.example.kakaoimageai.domain.repository.DallERepository
import dagger.Binds
import dagger.Provides
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.openai.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideDallEImageService(retrofit: Retrofit): DallE {
        return retrofit.create(DallE::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindDallERepository(dallERepositoryImpl: DallERepositoryImpl): DallERepository
}
