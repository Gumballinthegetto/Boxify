package kh.edu.rupp.ite.boxify.internet.client

import kh.edu.rupp.ite.boxify.internet.interceptor.AuthInterceptor
import kh.edu.rupp.ite.boxify.internet.service.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://boxify.item2.pro/"
    private var token : String = ""

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService by lazy { retrofit.create(ApiService::class.java) }
    fun setToken(token : String){
        this.token = token
    }
    fun getToken() : String{
        return this.token
    }
}