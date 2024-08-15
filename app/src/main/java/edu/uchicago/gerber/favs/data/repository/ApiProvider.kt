package edu.uchicago.gerber.favs.data.repository

import edu.uchicago.gerber.favs.common.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider {

    fun yelpApi(): YelpApi {
        return Retrofit.Builder()
            .baseUrl(Constants.yelpUrl)  // Update this to Yelp's API base URL
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YelpApi::class.java)
    }



}private fun getOkHttpClient() = OkHttpClient.Builder()
    .addInterceptor(getLoggingInterceptor())
    .addInterceptor { chain ->
        val original = chain.request()
        val request = original.newBuilder()
            .header("Authorization", "HzoAPGR5m_Ef6kAKdFEQAsUgfeHL0g9NUyyLMHNyTiQJkpsEdniT6U0OxVnfksLom5xKDluMFU4FV2BFx7HZx6a1KfesQy8OFtUXAA_zXqmKvwx4EkR7ixYNpRGPZnYx")
            .method(original.method, original.body)
            .build()
        chain.proceed(request)
    }
    .build()

private fun getLoggingInterceptor() =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
