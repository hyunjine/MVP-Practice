package com.hyunjine.myapplication.data.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hyunjine.myapplication.util.Constant.API_KEY
import com.hyunjine.myapplication.util.Constant.APP_NAME
import com.hyunjine.myapplication.util.Constant.APP_OS
import com.hyunjine.myapplication.util.Constant.BASE_URL
import com.hyunjine.myapplication.util.Constant.NUM_OF_ROWS
import com.hyunjine.myapplication.util.Constant.PAGE_NO
import com.hyunjine.myapplication.util.Constant.TYPE
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class API {

    private val baseInterceptor = Interceptor { chain ->
        val url = chain.request()
            .url
            .newBuilder()
            .addEncodedQueryParameter("ServiceKey", API_KEY)
            .addQueryParameter("MobileOS", APP_OS)
            .addQueryParameter("MobileApp", APP_NAME)
            .addQueryParameter("_type", TYPE)
            .addQueryParameter("pageNo", PAGE_NO)
            .addQueryParameter("numOfRows", NUM_OF_ROWS)
            .build()

        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()

        return@Interceptor chain.proceed(request)
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(baseInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    private val gson: Gson by lazy { GsonBuilder().setLenient().create() }

    val server: Service by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(Service::class.java)
    }
}