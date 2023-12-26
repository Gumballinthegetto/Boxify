package kh.edu.rupp.ite.boxify.data.response

data class BaseModelWrapper<T>(
    val success : Boolean,
    val message : String,
    val data : T?
)


