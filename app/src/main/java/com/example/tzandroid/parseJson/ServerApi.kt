package com.example.tzandroid.parseJson

import android.util.Log
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//http://gitlab.65apps.com/65gb/static/raw/master/testTask.json
interface ServerApi {
    @GET("testTask.json")
    fun moodel(): Single<Result>

    companion object Factory {
        fun create(): ServerApi {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://gitlab.65apps.com/65gb/static/raw/master/")
                .build()

            return retrofit.create(ServerApi::class.java);
        }
    }
}