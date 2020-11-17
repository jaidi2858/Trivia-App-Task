package com.trivia.questions.models.source.serverConnection

import android.content.Context
import androidx.multidex.BuildConfig
import com.trivia.questions.utils.generalUtils.AppConstants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClientInstance(ctx: Context) {
    private var retrofit: Retrofit? = null
    private val httpClient = OkHttpClient.Builder()
    var context: Context = ctx

    init {
        if (retrofit == null) {
            initRetrofit()
        }
    }

    fun initRetrofit() {
        var retrofitBuilder = retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
        httpClient.callTimeout(120, TimeUnit.SECONDS).connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
        val loggingIntercepter = getLoggingInterceptor()
        loggingIntercepter.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(loggingIntercepter)
        retrofitBuilder.client(httpClient.build())
        retrofit = retrofitBuilder.build()
    }

    fun getService(): ApiService {
        return retrofit!!.create<ApiService>(ApiService::class.java!!)
    }


    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingIntercepter = HttpLoggingInterceptor()
        loggingIntercepter.level=HttpLoggingInterceptor.Level.BODY
        return loggingIntercepter
    }


    fun getRetrofit(): Retrofit? {
        return retrofit
    }

    companion object {
        var singleInstance: RetrofitClientInstance? = null

        fun getInstance(context: Context): RetrofitClientInstance? {
            if (singleInstance == null)
                singleInstance =
                    RetrofitClientInstance(
                        context
                    )

            return singleInstance
        }
    }


}