package kh.edu.rupp.ite.boxify.internet.service

import kh.edu.rupp.ite.boxify.data.request.LoginRequest
import kh.edu.rupp.ite.boxify.data.response.LoginResponse
import kh.edu.rupp.ite.boxify.data.request.RegisterBodyRequest
import kh.edu.rupp.ite.boxify.data.response.BaseModelWrapper
import kh.edu.rupp.ite.boxify.data.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/register")
    suspend fun registerUser(@Body registrationRequest: RegisterBodyRequest): Response<BaseModelWrapper<RegisterResponse>>

    @POST("api/login")
    suspend fun loginUser(@Body request: LoginRequest): Response<BaseModelWrapper<LoginResponse>>
}