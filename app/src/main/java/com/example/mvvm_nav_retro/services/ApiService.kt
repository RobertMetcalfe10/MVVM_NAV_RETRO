package com.example.mvvm_nav_retro.services

import com.example.mvvm_nav_retro.services.ApiService.jwtToken
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


private const val BASE_URL = "http://api.robs.ninja/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofitLogin = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

private val httpClientWithToken = OkHttpClient().newBuilder().addInterceptor { chain ->
    val request = chain.request().newBuilder()
        .addHeader("Authorization", "Bearer $jwtToken").build()
    chain.proceed(request)
}.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()

private val retrofitHome = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(httpClientWithToken)
    .build()

interface LoginApiService {
    @POST("auth/local")
    fun login(@Body loginBody: LoginBody): Deferred<LoginResult>
}

interface HomeApiService {
    @GET("restaurants")
    fun getRestaurants(): Deferred<List<Restaurant>>

    @POST("restaurants")
    fun addRestaurant(@Body restaurant: RestaurantToAdd): Deferred<Restaurant>
}

object ApiService {
    val loginApiService: LoginApiService by lazy {
        retrofitLogin.create(LoginApiService::class.java)
    }

    val homeApiService: HomeApiService by lazy {
        retrofitHome.create(HomeApiService::class.java)
    }

    var jwtToken = ""
}