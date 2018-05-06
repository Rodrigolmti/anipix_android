package com.rodrigolmti.anipix.model.api

import com.rodrigolmti.anipix.model.utils.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
class RetrofitService {

    fun retrofitInstance(): Retrofit {

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(LoggingInterceptor())

        return Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.LOCAL_URL_CONNECTION)
                .client(httpClient.build())
                .build()
    }

    internal class LoggingInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            println("|------>> Request: " + request.url())
            val response = chain.proceed(request)
            val body = response.body()!!.string()
            println("<<------| Response: $body")
            return response.newBuilder().body(ResponseBody
                    .create(response.body()!!.contentType(), body)).build()
        }
    }
}