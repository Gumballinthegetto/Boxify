package kh.edu.rupp.ite.boxify.repository

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    companion object {
        const val KEY_EMAIL = "email"
        const val KEY_PASSWORD = "password"
        // Add other keys as needed (e.g., authentication token)
    }

    fun saveUserSession(email: String, password: String) {
        editor.putString(KEY_EMAIL, email)
        editor.putString(KEY_PASSWORD, password)
        editor.apply()
    }

    fun getUserEmail(): String? {
        return sharedPreferences.getString(KEY_EMAIL, null)
    }

    fun getUserPassword(): String? {
        return sharedPreferences.getString(KEY_PASSWORD, null)
    }

    // Add methods for handling authentication token or other session data
}
