package kh.edu.rupp.ite.boxify.helper

import android.content.Context

object MessageUtils {
    fun showError(context: Context, title : String? , message : String?){
        val dialog = CustomDialog(context, CustomDialog.DialogType.ERROR_TYPE)
        dialog.setTitle(title?: "")
            .setContentMessage(message?: "")
            .showCancelButton(isShow = false)
        dialog.show()
    }

    fun showSuccess(context: Context, title : String? , message : String?, onDialogClickListener: CustomDialog.OnDialogClickListener){
        val dialog = CustomDialog(context, CustomDialog.DialogType.SUCCESS_TYPE)
        dialog.setTitle(title?: "")
            .setContentMessage(message?: "")
            .showCancelButton(isShow = false)
            .setSuccessButtonListener(onDialogClickListener)
        dialog.show()
    }

}