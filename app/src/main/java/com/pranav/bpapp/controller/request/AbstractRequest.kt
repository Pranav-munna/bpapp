package com.pranav.bpapp.controller.request

import android.content.Context
import android.util.Log
import com.pranav.bpapp.controller.AppInterphase
import com.pranav.bpapp.controller.ProcessResponceInterphase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

abstract class AbstractRequest<T>(
    protected var context: Context, private val responcehandler: ProcessResponceInterphase<T>
) :
    Callback<T> {
    protected lateinit var appInterphase: AppInterphase
    private var base_url = "https://demo/"
    val appToken =
        "Bearer " + context.getSharedPreferences("Tokens", Context.MODE_PRIVATE).getString(
            "TOKEN",
            ""
        )
    val appJson = "application/json"

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .client(
                OkHttpClient.Builder().addNetworkInterceptor(loggingInterceptor)
                    .connectTimeout(500, TimeUnit.SECONDS)
                    .readTimeout(500, TimeUnit.SECONDS)
                    .build()
            )
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
        appInterphase = retrofit.create(AppInterphase::class.java)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            Log.e("response", "  ok")
            processResponse(response.body())
        } else {
            Log.e("response", "  ok alla")
            responcehandler!!.processResponceError(response.body())
        }
        Log.e("response", response.body().toString())
    }

    override fun onFailure(call: Call<T>, t: Throwable?) {
        Log.e("response ", t!!.message + "  poe")
        processResponse(null)
    }

    private fun processResponse(response: T?) {
        if (response != null) {
            responcehandler?.processResponce(response)
        }
    }
}