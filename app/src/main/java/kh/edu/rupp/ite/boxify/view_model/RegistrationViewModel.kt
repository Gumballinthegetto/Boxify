package kh.edu.rupp.ite.boxify.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kh.edu.rupp.ite.boxify.base.BaseViewModel
import kh.edu.rupp.ite.boxify.data.ResultWrapper
import kh.edu.rupp.ite.boxify.data.request.RegisterBodyRequest
import kh.edu.rupp.ite.boxify.data.response.BaseModelWrapper
import kh.edu.rupp.ite.boxify.data.response.RegisterResponse
import kh.edu.rupp.ite.boxify.internet.client.ApiClient
import kh.edu.rupp.ite.boxify.internet.repository.Repository
import kotlinx.coroutines.launch


class RegistrationViewModel(private val repository: Repository) : BaseViewModel() {

    private val _registerResponseLiveData = MutableLiveData<BaseModelWrapper<RegisterResponse>>()
    val registerResponseLiveData: MutableLiveData<BaseModelWrapper<RegisterResponse>> get() = _registerResponseLiveData

    fun registerUser(name: String, email: String, password: String) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val bodyRequest = RegisterBodyRequest(name, email, password)
                Log.d("RegistrationViewModel", "Request Body: $bodyRequest")

                val response = repository.register(bodyRequest)
                when(response){
                    is ResultWrapper.Success -> {
                        isLoading.value = false
                        response.data.let{
                            _registerResponseLiveData.value = it
                        }
                    }
                    is ResultWrapper.OnError -> {
                        isLoading.value = false
                        errorMessage.value = response.message
                    }
                }

//                if (response.isSuccessful) {
//                    val registrationResponse = response.body()
//
//                    if (registrationResponse != null) {
//                        if (registrationResponse.success) {
//                            _registrationResult.value = registrationResponse
//                        } else {
//                            // Handle cases where success is false
//                            _registrationResult.value =
//                                RegisterResponse(false, registrationResponse.message)
//                        }
//                    } else {
//                        // Handle null response body
//                        _registrationResult.value =
//                            RegisterResponse(false, "Registration failed. Please try again.")
//                    }
//                } else {
//                    // Handle other status codes or error conditions
//                    val errorBody = response.errorBody()?.string()
//                    Log.e("RegistrationViewModel", "Unsuccessful response: ${response.code()}, $errorBody")
//                    _registrationResult.value =
//                        RegisterResponse(false, "Registration failed. Please try again.")
//                }

            } catch (e: Exception) {
                Log.e("RegistrationViewModel", "Error during registration", e)
                //_registrationResult.value = RegisterResponse(false, "An unexpected error occurred: ${e.message}")
                errorMessage.value = e.message ?: "error"

            } finally {
                isLoading.value = false
            }
        }
    }
}
