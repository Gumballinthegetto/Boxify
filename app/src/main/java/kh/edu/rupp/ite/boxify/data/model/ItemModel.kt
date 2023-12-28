package kh.edu.rupp.ite.boxify.data.model

import com.google.gson.annotations.SerializedName

data class ItemModel(
    @SerializedName("id")
    val id : String? = null,
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
