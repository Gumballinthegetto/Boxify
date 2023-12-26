package kh.edu.rupp.ite.boxify.internet.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header("app-type" , "")
            .build()
        return chain.proceed(request)
    }
}