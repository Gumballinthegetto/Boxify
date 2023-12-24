package kh.edu.rupp.ite.boxify.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.edu.rupp.ite.boxify.data.RegistrationRequest
import kh.edu.rupp.ite.boxify.data.RegistrationResponse
import kh.edu.rupp.ite.boxify.internet.service.ApiService
import kotlinx.coroutines.launch


class RegistrationViewModel(private val apiService: ApiService) : ViewModel() {

    private val _registrationResult = MutableLiveData<RegistrationResponse?>()
    val registrationResult: MutableLiveData<RegistrationResponse?> get() = _registrationResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun registerUser(name: String, email: String, password: String) {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val user = RegistrationRequest(name, email, password)
                Log.d("RegistrationViewModel", "Request Body: $user")
                _registrationResult.value = RegistrationResponse(true, "Registration successful")
                // Check if user is null

                val response = apiService.registerUser(user)

                if (response.isSuccessful) {
                    val registrationResponse = response.body()

                    if (registrationResponse != null) {
                        if (registrationResponse.success) {
                            _registrationResult.value = registrationResponse
                        } else {
                            // Handle cases where success is false
                            _registrationResult.value =
                                RegistrationResponse(false, registrationResponse.message)
                        }
                    } else {
                        // Handle null response body
                        _registrationResult.value =
                            RegistrationResponse(false, "Registration failed. Please try again.")
                    }
                } else {
                    // Handle other status codes or error conditions
                    val errorBody = response.errorBody()?.string()
                    Log.e("RegistrationViewModel", "Unsuccessful response: ${response.code()}, $errorBody")
                    _registrationResult.value =
                        RegistrationResponse(false, "Registration failed. Please try again.")
                }


            } catch (e: Exception) {
                Log.e("RegistrationViewModel", "Error during registration", e)
                _registrationResult.value = RegistrationResponse(false, "An unexpected error occurred: ${e.message}")

            } finally {
                _isLoading.value = false
            }
        }
    }
}
