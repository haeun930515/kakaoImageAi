package com.example.kakaoimageai.di

import android.app.Application
import android.content.Context
import androidx.annotation.StringRes
import androidx.core.content.res.TypedArrayUtils.getString
import com.example.kakaoimageai.R
import com.example.kakaoimageai.data.remote.DallE
import com.example.kakaoimageai.data.repository.DallERepositoryImpl
import com.example.kakaoimageai.domain.repository.DallERepository
import dagger.Binds
import dagger.Provides
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().client(provideOKHttpClient(AppIntercepter()))
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.openai.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    @Singleton
    @Provides
    fun provideOKHttpClient(intercepter: AppIntercepter): OkHttpClient
        = OkHttpClient.Builder().run{
            addInterceptor(intercepter)
                .build()
    }


    @Singleton
    @Provides
    fun provideDallEImageService(retrofit: Retrofit): DallE {
        return retrofit.create(DallE::class.java)
    }
    class AppIntercepter : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response = with(chain){

            val newRequest = request().newBuilder()
                .addHeader("Authorization","Bearer "+"sk-AcwP45bMlZ7KBnaMgLrvT3"+"BlbkFJBAVj2TdqL8fhnz3SA570")
                .addHeader("Content-Type","application/json")
                .build()
            proceed(newRequest)
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindDallERepository(dallERepositoryImpl: DallERepositoryImpl): DallERepository
}


