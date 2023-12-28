package kh.edu.rupp.ite.boxify.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kh.edu.rupp.ite.boxify.base.BaseViewModel
import kh.edu.rupp.ite.boxify.data.ResultWrapper
import kh.edu.rupp.ite.boxify.data.model.ItemModel
import kh.edu.rupp.ite.boxify.data.request.AddItemRequestBody
import kh.edu.rupp.ite.boxify.data.response.AddItemResponse
import kh.edu.rupp.ite.boxify.data.response.BaseModelWrapper
import kh.edu.rupp.ite.boxify.data.response.LoginResponse
import kh.edu.rupp.ite.boxify.internet.repository.Repository
import kotlinx.coroutines.launch

class AddItemViewModel(
    private val repository: Repository,
) : BaseViewModel(){
    private val _addItemResponseLiveData = MutableLiveData<BaseModelWrapper<ItemModel>>()
    private val _getListFolderResponseLiveData = MutableLiveData<BaseModelWrapper<ArrayList<ItemModel>>>()
    val getListFolderResponseLiveData: MutableLiveData<BaseModelWrapper<ArrayList<ItemModel>>> get() = _getListFolderResponseLiveData
    val addItemResponseLiveData: MutableLiveData<BaseModelWrapper<ItemModel>> get() = _addItemResponseLiveData

    fun addFolder(body: AddItemRequestBody){
        isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.addFolder(body)
                when(response){
                    is ResultWrapper.Success -> {
                        isLoading.value = false
                        response.data.let {
                            _addItemResponseLiveData.value = it
                        }
                    }
                    is ResultWrapper.OnError -> {
                        isLoading.value = false
                        errorMessage.value = response.message?: "error"
                    }
                }
            } catch (e : Exception){
                isLoading.value = false
                errorMessage.value = e.message ?: "error"
            }
        }
    }
    fun getListFolder(){
        isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.getListFolder()
                when(response){
                    is ResultWrapper.Success -> {
                        isLoading.value = false
                        response.data.let {
                            _getListFolderResponseLiveData.value = it
                        }
                    }
                    is ResultWrapper.OnError -> {
                        isLoading.value = false
                        errorMessage.value = response.message?: "error"
                    }
                }
            } catch (e : Exception){
                isLoading.value = false
                errorMessage.value = e.message ?: "error"
            }
        }
    }

}