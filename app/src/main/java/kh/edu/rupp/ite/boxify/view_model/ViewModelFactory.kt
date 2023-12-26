package kh.edu.rupp.ite.boxify.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kh.edu.rupp.ite.boxify.internet.client.SessionManager
import kh.edu.rupp.ite.boxify.internet.repository.Repository
import kh.edu.rupp.ite.boxify.internet.service.ApiService
import retrofit2.Retrofit

class ViewModelFactory(
    val context: Context
) : ViewModelProvider.Factory {
    private val repository = Repository()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegistrationViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
