package kh.edu.rupp.ite.boxify.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kh.edu.rupp.ite.boxify.internet.client.SessionManager
import kh.edu.rupp.ite.boxify.internet.service.ApiService

class ViewModelFactory(
    private val apiService: ApiService,
    private val sessionManager: SessionManager
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegistrationViewModel(apiService) as T
        } else if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(apiService, sessionManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
