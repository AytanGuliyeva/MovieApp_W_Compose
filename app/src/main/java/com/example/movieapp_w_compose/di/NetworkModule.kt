package com.example.movieapp_w_compose.di

import com.example.movieapp_w_compose.data.ConstValues
import com.example.movieapp_w_compose.retrofit.HttpClient
import com.example.movieapp_w_compose.retrofit.Repository
import com.example.movieapp_w_compose.retrofit.TmdbService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ConstValues.BASE_URL)
            .client(HttpClient.okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideTmdbService(retrofit: Retrofit): TmdbService {
        return retrofit.create(TmdbService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: TmdbService): Repository {
        return Repository(api)
    }
}
