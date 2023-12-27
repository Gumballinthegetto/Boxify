package kh.edu.rupp.ite.boxify.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kh.edu.rupp.ite.boxify.base.BaseViewModel
import kh.edu.rupp.ite.boxify.data.ResultWrapper
import kh.edu.rupp.ite.boxify.data.request.LoginRequest
import kh.edu.rupp.ite.boxify.data.response.BaseModelWrapper
import kh.edu.rupp.ite.boxify.data.response.LoginResponse
import kh.edu.rupp.ite.boxify.internet.repository.Repository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: Repository,
) : BaseViewModel() {

    private val _loginResponseLiveData = MutableLiveData<BaseModelWrapper<LoginResponse>>()
    val loginResponseLiveData: MutableLiveData<BaseModelWrapper<LoginResponse>> get() = _loginResponseLiveData

    fun loginUser(email: String, password: String) {
        isLoading.value = true
        // Log the request parameters
        Log.d("LoginViewModel", "Request Parameters - Email: $email, Password: $password")
        viewModelScope.launch {
            try {
                val loginBody = LoginRequest(email, password)
                Log.d("LoginViewModel", "Raw API Response: $loginBody")
                val response = repository.login(loginBody)
                when(response){
                    is ResultWrapper.Success -> {
                        isLoading.value = false
                        response.data.let {
                            _loginResponseLiveData.value = it
                        }
                    }
                    is ResultWrapper.OnError -> {
                        isLoading.value = false
                        errorMessage.value = response.message
                    }
                }
            } catch (e: Exception) {
                isLoading.value = false
                // Log the exception for debuggin
                errorMessage.value = e.message ?: "error"
            } finally {
                isLoading.value = false
            }
        }

        // Function to log out the user
//        fun logoutUser() {
//            // Clear authentication token
//            sessionManager.clearAuthToken()
//            // Set login result to null (indicating user is logged out)
//            _loginResult.value = null
//        }
    }
}
