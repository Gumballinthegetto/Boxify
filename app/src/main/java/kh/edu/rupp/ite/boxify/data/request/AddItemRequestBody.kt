package kh.edu.rupp.ite.boxify.data.request

data class AddItemRequestBody(
    val name : String,
    val image : String,
    val price : Double,
    val unit : String,
    val description : String,
    val quantity : Int
)
