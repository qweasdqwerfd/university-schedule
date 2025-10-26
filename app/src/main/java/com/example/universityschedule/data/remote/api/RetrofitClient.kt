package com.example.universityschedule.data.remote.api

import com.example.universityschedule.data.remote.service.DictApiService
import com.example.universityschedule.data.remote.service.LessonsApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


object RetrofitClient {
    const val BASE_URL = "https://timetable.bstu.ru/api/public/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    private val client: OkHttpClient
        get() = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

    var gson: Gson = GsonBuilder()  //fix
        .setLenient()
        .create()


    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val lessonsApiService: LessonsApiService by lazy {
        retrofit.create(LessonsApiService::class.java)
    }

    val dictApiService: DictApiService by lazy {
        retrofit.create(DictApiService::class.java)
    }

}