package com.example.logincompose.di

import com.example.logincompose.BuildConfig
import com.example.logincompose.data.repository.AuthRepository
import com.example.logincompose.data.repository.AuthRepositoryImpl
import com.example.logincompose.domain.useCases.GetMainUseCase
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit



val appModule = module {
    single { getInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get ()) }


}

private fun getInterceptor() : HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}


private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
    OkHttpClient.Builder()
        .followRedirects(false)
        .addInterceptor { chain ->
            val original: Request = chain.request()
            val request: Request.Builder = original.newBuilder()
            request.apply {
                header("Accept", "application/json").method(original.method, original.body)
                header("Cache-Control", "public, max-age=" + 600)
            }
            chain.proceed(request.build())
        }
        .addInterceptor(interceptor)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .build()

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .addConverterFactory(
        GsonConverterFactory.create(
            GsonBuilder()
                .setLenient()
                .create()
        )
    )
    .client(okHttpClient)
    .build()