package kh.edu.rupp.ite.boxify.internet.service

import kh.edu.rupp.ite.boxify.data.LoginRequest
import kh.edu.rupp.ite.boxify.data.LoginResponse
import kh.edu.rupp.ite.boxify.data.RegistrationRequest
import kh.edu.rupp.ite.boxify.data.RegistrationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/register")
    suspend fun registerUser(@Body registrationRequest: RegistrationRequest): Response<RegistrationResponse>

    @POST("api/login")
    suspend fun loginUser(@Body request: LoginRequest): Response<LoginResponse>
}