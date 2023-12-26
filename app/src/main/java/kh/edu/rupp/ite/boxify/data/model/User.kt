package kh.edu.rupp.ite.boxify.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Int,
    @SerializedName("user_name")
    val userName: String,
    @SerializedName("email")
    val email: String
)