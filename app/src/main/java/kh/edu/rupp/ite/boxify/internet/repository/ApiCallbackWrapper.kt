package kh.edu.rupp.ite.boxify.internet.repository

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import kh.edu.rupp.ite.boxify.data.ResultWrapper
import okhttp3.ResponseBody
import java.io.IOException
import java.io.InputStreamReader
import java.io.Reader
import java.nio.charset.StandardCharsets

class ApiCallbackWrapper{

    fun catchError(e: Throwable) : ResultWrapper.OnError{
        when(e){
            is IOException ->{
                val message = NETWORK_ERROR_MESSAGE
                return ResultWrapper.OnError(-1,message)
            }

            else -> {
                val responseBody = e.message
                return ResultWrapper.OnError(-1, responseBody.toString())
            }
        }
    }
    fun catchHttpError(code: Int, message: String, resBody: ResponseBody?) : ResultWrapper.OnError{
            when(code){
                ErrorCode.BadRequest ->{
                    resBody?.apply {
                        val message = catchErrorMessage(this)
                        return ResultWrapper.OnError(ErrorCode.BadRequest, message)
                    }
                    //ResultWrapper.OnError(_root_ide_package_.kh.edu.rupp.ite.boxify.data.ResultWrapperWrapper.OnErrorStatus.BAD_REQUEST, respondBody)
                }
                ErrorCode.Unauthorized ->{
//                        ResultWrapper.OnError(_root_ide_package_.kh.edu.rupp.ite.boxify.data.ResultWrapperWrapper.OnErrorStatus.UNAUTHORIZED, respondBody)
                    resBody?.apply {
                        val message = catchErrorMessage(this)
                        return ResultWrapper.OnError(ErrorCode.Unauthorized, message)
                    }
                }
                ErrorCode.NotFound ->{
                    resBody?.apply {
                        val message = catchErrorMessage(this)
                        return ResultWrapper.OnError(ErrorCode.NotFound, message)
                    }
                }
                ErrorCode.MethodNotAllowed ->{
                    return ResultWrapper.OnError(ErrorCode.MethodNotAllowed, message)
                }
                ErrorCode.RequestEntityTooLarge ->{
                    return ResultWrapper.OnError(ErrorCode.RequestEntityTooLarge , message)
                }
                ErrorCode.InternalServerError ->{
//                        return ResultWrapper.OnError(_root_ide_package_.kh.edu.rupp.ite.boxify.data.ResultWrapperWrapper.OnErrorStatus.ON_ERROR, message)
                    return ResultWrapper.OnError(ErrorCode.InternalServerError, "Internal Server Error")
                }
                ErrorCode.BadGateway ->{
//                        return ResultWrapper.OnError(_root_ide_package_.kh.edu.rupp.ite.boxify.data.ResultWrapperWrapper.OnErrorStatus.ON_ERROR, message)
                    return ResultWrapper.OnError(ErrorCode.BadGateway, "Bad Gateway")
                }
                ErrorCode.GatewayTimeout ->{
//                        return ResultWrapper.OnError(_root_ide_package_.kh.edu.rupp.ite.boxify.data.ResultWrapperWrapper.OnErrorStatus.ON_ERROR, respondBody)
                    return ResultWrapper.OnError(ErrorCode.GatewayTimeout, "Gateway Timeout")
                }
                ErrorCode.UnProcessableEntity ->{
                    resBody?.apply {
                        return ResultWrapper.OnError(ErrorCode.UnProcessableEntity, catchErrorMessage(this))
                    }
                }
                else -> {
                    return ResultWrapper.OnError(
                        -1,
                        "Error code: $code"
                    )
                }
            }
        return ResultWrapper.OnError(-1, resBody?.string() ?: "")
    }

    companion object{
        const val NETWORK_ERROR_MESSAGE = "No Internet Connection"
        const val SERVER_ERROR_MESSAGE = "We sorry your connection timeout, please try again later!"
    }


    private fun catchErrorMessage(responseBody: ResponseBody?) : String{

        try {
            val message = StringBuilder()
            var messageMap : HashMap<String, String>? = HashMap()

            messageMap = getErrorMessages(responseBody, "\n")

            messageMap?.let {
                message.append(messageMap["message"])
                return message.toString()
            }
        }catch (e : java.lang.Exception){
            e.printStackTrace()
            return ""
        }
        return ""
    }



    private fun getErrorMessages(
        responseBody: ResponseBody?,
        separator: String?
    ): HashMap<String, String>? {
        val hashMap = HashMap<String, String>()
        if (responseBody != null) {
            var errorMsg = String.format("%s","Something when wrong")
            var parseJsonObject: JsonObject? = null
            try {
                parseJsonObject = Gson().fromJson(responseBody.charStream(), JsonObject::class.java)
            } catch (ignored: Exception) {
            }
            //String jsonStr = "{\"success\":false,\"code\":400,\"message\":\"user_withdraw\",\"errors\":{\"user_withdraw\":[\"Pleasewait4minutesforthecurrentprocessingwithdraw\"]}}";
            //String jsonError = "{\"success\":false,\"code\":400,\"message\":\"Yourphoneisnotyetregistered!\",\"error\":[\"Yourphoneisnotyetregistered!\"]}";
            var jsonError: JsonObject? = null
            var errorMessage = ""
            //error with array [errors]
            try {
                val validateError =
                    parseJsonObject?.getAsJsonArray("errors") != null && parseJsonObject.getAsJsonArray(
                        "errors"
                    ).size() > 0
                if (validateError) {
                    errorMessage = parseJsonObject!!.getAsJsonArray("errors")[0].asString
                    hashMap["message"] = errorMessage
                    return hashMap
                }
            } catch (ignored: Exception) {
            }
            //error with array [error]
            try {
                val validateError =
                    parseJsonObject?.getAsJsonArray("error") != null && parseJsonObject.getAsJsonArray(
                        "error"
                    ).size() > 0
                if (validateError) {
                    errorMessage = parseJsonObject!!.getAsJsonArray("error")[0].asString
                    hashMap["message"] = errorMessage
                    return hashMap
                }
            } catch (ignored: Exception) {
            }
            //error with object [error,errors]
            try {
                if (parseJsonObject != null) {
                    if (parseJsonObject.getAsJsonObject("errors") != null) {
                        jsonError = parseJsonObject.getAsJsonObject("errors")
                    }
                    if (parseJsonObject.getAsJsonObject("error") != null) {
                        jsonError = parseJsonObject.getAsJsonObject("error")
                    }
                    if (jsonError != null) {
                        for ((_, value) in jsonError.entrySet()) {
                            if (value.asString.isNotEmpty()) {
                                errorMsg = value.asString
                                hashMap["message"] = errorMsg
                            }
                        }
                        return hashMap
                    } else {
                        val apiError: ApiError =
                            Gson().fromJson(parseJsonObject.toString(), ApiError::class.java)
                        if (apiError != null) {
                            if (apiError.message == null) {
                                hashMap["message"] =
                                    "Something when wrong -----!"
                            } else {
                                hashMap["message"] = apiError.message
                            }
                            return hashMap
                        }
                    }
                }
            } catch (ignored: Exception) {
            }
            val readerStream: Reader =
                InputStreamReader(responseBody.byteStream(), StandardCharsets.UTF_8)
            val type = object : TypeToken<ResponseErrorBody<JsonElement?>?>() {}.type
            try {
                val errorBody: ResponseErrorBody<JsonElement> = Gson().fromJson(readerStream, type)
                if (errorBody == null) {
                    hashMap["message"] = "Something when dddd!"
                    return hashMap
                }
                try {
                    if (errorBody.error != null) {
                    }
                } catch (ex: JsonSyntaxException) {
                    ex.printStackTrace()
                }
                val errorElm: JsonElement = errorBody.error
                hashMap["error.body.message"] = errorBody.message
                if (errorElm is JsonObject) {
                    val errorObj = errorElm.getAsJsonObject()
                    val msg = StringBuilder()
                    for ((key1, value) in errorObj.entrySet()) {
                        val tmpMsg = StringBuilder()
                        if (value is JsonPrimitive) {
                            val jsonPrimitive = value as JsonPrimitive
                            tmpMsg.append(jsonPrimitive.asString)
                        } else if (value is JsonArray) {
                            val jsonArray = value as JsonArray
                            for (elm in jsonArray) {
                                tmpMsg.append(elm.asString)
                                tmpMsg.append(separator)
                            }
                        } else if (value is JsonObject) {
                            val jsonObject = value as JsonObject
                            tmpMsg.append(jsonObject.asString)
                        }
                        hashMap[key1] = tmpMsg.toString()
                        msg.append(tmpMsg)
                        msg.append(separator)
                    }
                    hashMap["message"] = msg.toString()
                } else if (errorElm is JsonArray) {
                    val errorArr = errorElm.getAsJsonArray()
                    val msg = StringBuilder()
                    for (elm in errorArr) {
                        msg.append(elm.asString)
                        msg.append(separator)
                    }
                    hashMap["message"] = msg.toString()
                } else {
                    hashMap["message"] = errorMsg
                }
            } catch (e: JsonSyntaxException) {
                hashMap["message"] = "Something when eeee!"
            }
        }
        return hashMap
    }

    data class ApiError(
        val message: String,
        val status: Int
    )
    class ResponseErrorBody<T>(
        val code: Int,
        val error: T,
        val message: String,
        val success: Boolean
    )

    object ErrorCode {
        const val BadRequest = 400
        const val Unauthorized = 401
        const val Forbidden = 403
        const val NotFound = 404
        const val MethodNotAllowed = 405
        const val RequestEntityTooLarge = 413
        const val UnProcessableEntity = 422
        const val InternalServerError = 500
        const val BadGateway = 502
        const val GatewayTimeout = 504
    }
}