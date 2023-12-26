package kh.edu.rupp.ite.boxify.helper

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import kh.edu.rupp.ite.boxify.R
import kh.edu.rupp.ite.boxify.databinding.CustomDialogLayoutBinding


class CustomDialog(context: Context, private val dialogType : DialogType) : Dialog(context){

    private var title: String? = null
    private var message: String? = null
    private var showCancelButton : Boolean = false
    private var negativeButtonText : String? = null
    private var positiveButtonText : String? = null
    private var onDialogClickListener: OnDialogClickListener? = null

    interface OnDialogClickListener {
        fun onClick(dialog: CustomDialog)
    }
    private lateinit var binding : CustomDialogLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CustomDialogLayoutBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)
        setCanceledOnTouchOutside(false)
        binding.titleText.text = title ?: ""
        binding.descriptionText.text = message ?: ""
        binding.positiveButton.text = positiveButtonText ?: context.getString(R.string.yes)
        binding.negativeButton.text = negativeButtonText ?: context.getString(R.string.no)
        binding.negativeButton.visibility = if (showCancelButton) View.VISIBLE else View.GONE
        binding.buttonSpacing.visibility = if (showCancelButton) View.VISIBLE else View.GONE
        binding.titleText.visibility = if(title?.isEmpty() == true) View.GONE else View.VISIBLE
        when (dialogType){
             DialogType.SUCCESS_TYPE -> {
                 binding.imageView.setImageResource(R.drawable.success_icon)
             } DialogType.ERROR_TYPE ->{
                 binding.imageView.setImageResource(R.drawable.error_icon)
             }
        }
        binding.positiveButton.setOnClickListener {
            if (onDialogClickListener!= null){
                onDialogClickListener?.onClick(this)
            }
        }
        binding.negativeButton.setOnClickListener {
            dismiss()
        }
    }

    fun setTitle(title : String) : CustomDialog{
        this.title = title
        return this
    }
    fun setContentMessage(message : String) : CustomDialog{
        this.message = message
        return this
    }
    fun showCancelButton(isShow : Boolean) : CustomDialog{
        showCancelButton = isShow
        return this
    }
    fun setPositiveButtonText(text : String) : CustomDialog{
        positiveButtonText = text
        return  this
    }
    fun setNegativeButtonText(text: String) : CustomDialog {
        negativeButtonText = text
        return this
    }

    fun setCancelButtonListener(onDialogClickListener: OnDialogClickListener) : CustomDialog{
        this.onDialogClickListener = onDialogClickListener
        return this
    }
    fun setSuccessButtonListener(onDialogClickListener: OnDialogClickListener) : CustomDialog{
        this.onDialogClickListener = onDialogClickListener
        return this
    }


    enum class DialogType{
        ERROR_TYPE,
        SUCCESS_TYPE
    }
}
