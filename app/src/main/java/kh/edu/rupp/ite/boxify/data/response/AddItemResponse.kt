package kh.edu.rupp.ite.boxify.data.response

import com.google.gson.annotations.SerializedName

data class AddItemResponse(
    @SerializedName("name")
    val name : String? = null,
    @SerializedName("image")
    val image : String? = null,
    @SerializedName("price")
    val price : Double? = null,
    @SerializedName("unit")
    val unit : String? = null,
    @SerializedName("description")
    val description : String? = null,
    @SerializedName("quantity")
    val quantity : Int? = null,
)
