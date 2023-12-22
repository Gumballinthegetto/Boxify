package kh.edu.rupp.ite.boxify.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kh.edu.rupp.ite.boxify.repository.UserRepository

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    fun loginUser(email: String, password: String) {
        userRepository.loginUser(email, password) { isSuccess ->
            _loginResult.value = isSuccess
        }
    }
}
