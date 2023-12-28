package kh.edu.rupp.ite.boxify.helper

import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContracts
import kh.edu.rupp.ite.boxify.base.BetterActivityResult
import java.util.concurrent.atomic.AtomicInteger

object PermissionHelper {
    fun requestMultiPermission(context: ActivityResultCaller, permissionList: Array<String>, callback : (Boolean)-> Unit) {
        val sizeOfPermissionRequest = AtomicInteger()
        val activityMultiPermission: BetterActivityResult<Array<String>, Map<String, Boolean>> = BetterActivityResult.registerForActivityResult(
            context,
            ActivityResultContracts.RequestMultiplePermissions(),object :
                BetterActivityResult.OnActivityResult<Map<String, Boolean>> {
                override fun onActivityResult(result: Map<String, Boolean>) {
                    result.forEach { (_, hasPermission) ->
                        if(hasPermission){
                            sizeOfPermissionRequest.getAndIncrement()
                        }
                    }
                    val isHasPermission = sizeOfPermissionRequest.toInt() == permissionList.size
                    callback.invoke(isHasPermission)
                }

            })
        activityMultiPermission.launch(permissionList)
    }
}