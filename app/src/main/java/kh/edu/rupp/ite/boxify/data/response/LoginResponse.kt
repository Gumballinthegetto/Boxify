package kh.edu.rupp.ite.boxify.data.response

import com.google.gson.annotations.SerializedName
import kh.edu.rupp.ite.boxify.data.model.User

data class LoginResponse(
    @SerializedName("token")
    val accessToken: String? = null,
    @SerializedName("user")
    val user: User?
)
