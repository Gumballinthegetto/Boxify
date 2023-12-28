package kh.edu.rupp.ite.boxify.helper

import android.content.Context

object SharedPreferencesManager{
    fun setStringPreference(context: Context, value : String , key : String){
        val sharedPreferences = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(key, value).apply()
    }
    fun getStringPreference(context: Context,key: String) : String{
        val sharedPreferences = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, "") ?: ""
    }

    object AuthManager{
        const val TOKEN_KEY = "token_key"
        const val NEW_USER_KEY = "new_user_key"
        const val newUser = "new_user"
        const val oldUser = "old_user"

        fun setToOldUser(context: Context){
            setStringPreference(context, oldUser, NEW_USER_KEY)
        }
        fun isNewUser(context: Context) : Boolean{
            return getStringPreference(context, NEW_USER_KEY) != oldUser
        }
        fun saveAuthToken(context: Context,token: String) {
            setStringPreference(context, token, TOKEN_KEY)
        }

        fun getToken(context: Context): String {
            return getStringPreference(context, TOKEN_KEY)
        }

        fun clearSecession(context: Context) {
            saveAuthToken(context,"")
        }
    }


}
