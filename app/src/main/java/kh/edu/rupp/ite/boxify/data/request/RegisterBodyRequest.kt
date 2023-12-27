package kh.edu.rupp.ite.boxify.data.request

data class RegisterBodyRequest(
    val name: String,
    val email: String,
    val password: String
)