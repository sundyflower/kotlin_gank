package com.bz.net

import com.bz.base.BuildConfig
import com.bz.common.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiRetrofit private constructor() {

    private val DEFAULT_TIMEOUT: Long = 15
    companion object {
        val instance: ApiRetrofit by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ApiRetrofit()
        }
    }

    fun initRetrofit():Retrofit{
        val intIterator=HttpLoggingInterceptor()
        if(BuildConfig.DEBUG){
            intIterator.level = HttpLoggingInterceptor.Level.BODY
        }else{
            intIterator.level=(HttpLoggingInterceptor.Level.NONE)
        }

        var client=OkHttpClient.Builder()
                .addInterceptor(intIterator)
                .retryOnConnectionFailure(true)
                .build()
        return Retrofit.Builder()
                .baseUrl(Api.baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

    }

}