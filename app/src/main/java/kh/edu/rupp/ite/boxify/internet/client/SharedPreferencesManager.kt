package kh.edu.rupp.ite.boxify.internet.client

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
