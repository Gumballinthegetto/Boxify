package kh.edu.rupp.ite.boxify.repository

import android.util.Log
import kh.edu.rupp.ite.boxify.internet.response.LoginResponse
import kh.edu.rupp.ite.boxify.internet.response.RegistrationResponse
import kh.edu.rupp.ite.boxify.internet.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(private val apiService: ApiService, private val sessionManager: SessionManager) {
    fun registerUser(name: String, email: String, password: String, callback: (Boolean) -> Unit) {
        val call: Call<RegistrationResponse> = apiService.registerUser(name, email, password)

        call.enqueue(object : Callback<RegistrationResponse> {
            override fun onResponse(call: Call<RegistrationResponse>, response: Response<RegistrationResponse>) {
                if (response.isSuccessful) {
                    val registrationResponse = response.body()
                    val isSuccess = registrationResponse?.success ?: false
                    callback(isSuccess)
                } else {
                    // Log the error response
                    Log.e("UserRepository", "Register API error: ${response.code()} - ${response.errorBody()?.string()}")
                    callback(false)
                }
            }

            override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                // Handle network error
                Log.e("UserRepository", "Network request failed", t)
                callback(false)
            }
        })
    }

    fun loginUser(email: String, password: String, callback: (Boolean) -> Unit) {
        val call: Call<LoginResponse> = apiService.loginUser(email, password)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()

                    // Check if login was successful
                    if (loginResponse?.success == true) {
                        // Save user session or handle authentication token
                        sessionManager.saveUserSession(email, password)

                        // Notify success
                        callback(true)
                    } else {
                        // Login failed
                        callback(false)
                    }
                } else {
                    // Log the error response
                    Log.e("UserRepository", "Login API error: ${response.code()} - ${response.errorBody()?.string()}")
                    callback(false)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // Handle network error
                Log.e("UserRepository", "Login API error: ${t.message}", t)
                callback(false)
            }
        })
    }

}
