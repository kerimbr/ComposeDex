package com.kerimbr.compokedex.di

import com.kerimbr.compokedex.BuildConfig
import com.kerimbr.compokedex.core.constants.AppConstants
import com.kerimbr.compokedex.data.remote.PokeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun providePokeApi(client: OkHttpClient): PokeApi {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(AppConstants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
        builder.readTimeout(AppConstants.READ_TIMEOUT, TimeUnit.SECONDS)
        builder.writeTimeout(AppConstants.WRITE_TIMEOUT, TimeUnit.SECONDS)
        builder.addInterceptor {
            val request = it
                .request()
                .newBuilder()
                .build()
            it.proceed(request)
        }
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

}