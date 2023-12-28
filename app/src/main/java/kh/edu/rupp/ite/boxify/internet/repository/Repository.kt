package kh.edu.rupp.ite.boxify.internet.repository

import kh.edu.rupp.ite.boxify.data.ResultWrapper
import kh.edu.rupp.ite.boxify.data.model.ItemModel
import kh.edu.rupp.ite.boxify.data.request.AddItemRequestBody
import kh.edu.rupp.ite.boxify.data.request.LoginRequest
import kh.edu.rupp.ite.boxify.data.request.RegisterBodyRequest
import kh.edu.rupp.ite.boxify.data.response.AddItemResponse
import kh.edu.rupp.ite.boxify.data.response.BaseModelWrapper
import kh.edu.rupp.ite.boxify.data.response.LoginResponse
import kh.edu.rupp.ite.boxify.data.response.RegisterResponse
import kh.edu.rupp.ite.boxify.internet.client.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class Repository {
    suspend fun login(body : LoginRequest) : ResultWrapper<BaseModelWrapper<LoginResponse>>{
       return withContext(Dispatchers.IO){
           requestApi {
               ApiClient.apiService.loginUser(body)
           }
       }
    }
    suspend fun register(body : RegisterBodyRequest) : ResultWrapper<BaseModelWrapper<RegisterResponse>>{
        return withContext(Dispatchers.IO){
            requestApi {
                ApiClient.apiService.registerUser(body)
            }
        }
    }
    suspend fun addFolder(body : AddItemRequestBody) : ResultWrapper<BaseModelWrapper<ItemModel>>{
        return withContext(Dispatchers.IO){
            requestApi {
                ApiClient.apiService.addFolder(body)
            }
        }
    }

    suspend fun getListFolder() : ResultWrapper<BaseModelWrapper<ArrayList<ItemModel>>>{
        return withContext(Dispatchers.IO){
            requestApi {
                ApiClient.apiService.getListFolder()
            }
        }
    }
    private suspend fun <T: Any> requestApi(call : suspend() -> Response<T> ) : ResultWrapper<T>{
        val response : Response<T>
        try {
            response = call.invoke()
        }catch (t : Throwable){
            return ApiCallbackWrapper().catchError(t)
        }
        return if(response.isSuccessful){
             ResultWrapper.Success(response.body()!!)
        }else {
             ApiCallbackWrapper().catchHttpError(response.code(), response.message(), response.errorBody())
        }

    }

}