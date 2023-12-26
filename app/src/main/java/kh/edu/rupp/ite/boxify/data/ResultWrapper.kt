package kh.edu.rupp.ite.boxify.data

import okhttp3.ResponseBody

sealed class ResultWrapper<out R> {
    data class Success<out T>(val data : T) : ResultWrapper<T>()
    data class OnError(val code : Int? = null, val message : String?) : ResultWrapper<Nothing>()
}