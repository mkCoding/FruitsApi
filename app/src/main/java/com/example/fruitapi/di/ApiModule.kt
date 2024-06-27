package com.example.fruitapi.di

import com.example.fruitapi.data.api.ApiDetails
import com.example.fruitapi.data.api.ApiEndpoints
import com.example.fruitapi.data.repository.ApiRepository
import com.example.fruitapi.data.repository.ApiRepositoryImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    //Add dependencies that will be injected throughout the app

    //Retrofit
    @Provides
    fun providesRetrofit():Retrofit{
     val okHttpClient = OkHttpClient.Builder()
         .addInterceptor(HttpLoggingInterceptor().apply {
             level = HttpLoggingInterceptor.Level.BODY
         }).build()

        return Retrofit.Builder()
            .baseUrl(ApiDetails.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()


    }

    //ApiEndpoints
    @Provides
    fun providesApiEndpoints(retrofit: Retrofit):ApiEndpoints{
      return retrofit.create(ApiEndpoints::class.java)
    }

    //ApiRepository
    @Provides
    fun providesApiRepository(apiEndpoints: ApiEndpoints):ApiRepository{
        return ApiRepositoryImpl(apiEndpoints)
    }

}