package kh.edu.rupp.ite.boxify.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kh.edu.rupp.ite.boxify.repository.UserRepository

class RegistrationViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _registrationResult = MutableLiveData<Boolean>()
    val registrationResult: LiveData<Boolean> = _registrationResult

    fun registerUser(name: String, email: String, password: String) {
        if (isNameValid(name) && isEmailValid(email) && isPasswordValid(password)) {
            // All fields are valid, proceed with registration
            userRepository.registerUser(name, email, password) { isSuccess ->
                _registrationResult.value = isSuccess
            }
        } else {
            // Validation failed, update UI or show an error message
            _registrationResult.value = false
        }
    }

    private fun isNameValid(name: String): Boolean {
        return name.isNotEmpty()
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        // Add your password validation logic here (e.g., minimum length, strong criteria)
        return password.length >= 6
    }
}
