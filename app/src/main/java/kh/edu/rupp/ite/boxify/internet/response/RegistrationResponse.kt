package kh.edu.rupp.ite.boxify.internet.response

import com.google.gson.annotations.SerializedName

data class RegistrationResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String
)
