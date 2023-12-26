package kh.edu.rupp.ite.boxify.internet.interceptor

import kh.edu.rupp.ite.boxify.internet.client.ApiClient
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = ApiClient.getToken()
        val authModified = chain.request().newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
        return chain.proceed(authModified)
    }
}