package com.aochdjp.playwithgura_san.model.repositories.abstracts

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    var retrofit: Retrofit? = null
        get() {
            field = field ?: setUpRetrofit()
            return field
        }

    private fun setUpRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .addNetworkInterceptor(HttpLoggingInterceptor { Log.d("OkHttp:", it) })
                    .build()
            )
            .baseUrl("http://aochd.jp/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}