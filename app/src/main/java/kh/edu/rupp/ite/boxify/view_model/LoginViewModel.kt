package kh.edu.rupp.ite.boxify.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.edu.rupp.ite.boxify.data.LoginRequest
import kh.edu.rupp.ite.boxify.data.LoginResponse
import kh.edu.rupp.ite.boxify.data.RegistrationRequest
import kh.edu.rupp.ite.boxify.data.RegistrationResponse
import kh.edu.rupp.ite.boxify.internet.client.SessionManager
import kh.edu.rupp.ite.boxify.internet.service.ApiService
import kotlinx.coroutines.launch

class LoginViewModel(
    private val apiService: ApiService,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _loginResult = MutableLiveData<LoginResponse?>()
    val loginResult: MutableLiveData<LoginResponse?> get() = _loginResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun loginUser(email: String, password: String) {
        _isLoading.value = true

        // Log the request parameters
        Log.d("LoginViewModel", "Request Parameters - Email: $email, Password: $password")


        viewModelScope.launch {
            try {
                val user = LoginRequest(email, password)
                Log.d("LoginViewModel", "Raw API Response: $user")

                _loginResult.value = LoginResponse(true, "Login successful", null)

                val response = apiService.loginUser(user)

                if (response.isSuccessful) {
                    val loginResponse = response.body()

                    if (loginResponse != null) {
                        if (loginResponse.success) {
                            _loginResult.value = loginResponse
                        } else {
                            // Handle cases where success is false
                            _loginResult.value =
                                LoginResponse(false, loginResponse.message, null)
                        }
                    } else {
                        // Handle unexpected null response body
                        Log.e("LoginViewModel", "Unexpected null response body")
                        _loginResult.value =
                            LoginResponse(false, "Login failed. Please try again.", null)
                    }
                } else {
                    // Handle other status codes or error conditions
                    val errorBody = response.errorBody()?.string()
                    Log.e("LoginViewModel", "Unsuccessful response: ${response.code()}, $errorBody")
                    _loginResult.value =
                        LoginResponse(false, "Login failed. Please try again.", null)
                }

            } catch (e: Exception) {
                // Log the exception for debugging
                Log.e("LoginViewModel", "Error during login", e)

                // Provide a more descriptive error message
                _loginResult.value =
                    LoginResponse(false, "An unexpected error occurred: ${e.message}", null)
            } finally {
                _isLoading.value = false
            }
        }

        // Function to log out the user
        fun logoutUser() {
            // Clear authentication token
            sessionManager.clearAuthToken()
            // Set login result to null (indicating user is logged out)
            _loginResult.value = null
        }
    }
}
