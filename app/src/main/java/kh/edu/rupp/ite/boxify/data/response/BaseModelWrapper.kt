package kh.edu.rupp.ite.boxify.data.response

import com.google.gson.annotations.SerializedName

data class BaseModelWrapper<T>(
    @SerializedName("status")
    val success : Boolean,
    @SerializedName("message")
    val message : String,
    @SerializedName("data")
    val data : T?
)


