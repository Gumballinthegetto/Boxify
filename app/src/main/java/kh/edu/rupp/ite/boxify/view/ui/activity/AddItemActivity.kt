package kh.edu.rupp.ite.boxify.view.ui.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ThemedSpinnerAdapter
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import kh.edu.rupp.ite.boxify.base.BaseActivity
import kh.edu.rupp.ite.boxify.data.request.AddItemRequestBody
import kh.edu.rupp.ite.boxify.databinding.ActivityAddItemBinding
import kh.edu.rupp.ite.boxify.helper.Helper
import kh.edu.rupp.ite.boxify.helper.MessageUtils
import kh.edu.rupp.ite.boxify.helper.PermissionHelper
import kh.edu.rupp.ite.boxify.view.ui.fragment.MainItemFragment
import kh.edu.rupp.ite.boxify.view_model.AddItemViewModel
import kh.edu.rupp.ite.boxify.view_model.LoginViewModel
import kh.edu.rupp.ite.boxify.view_model.ViewModelFactory

class AddItemActivity : BaseActivity<ActivityAddItemBinding>(ActivityAddItemBinding::inflate) {

    private lateinit var mViewModel : AddItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mViewModel = ViewModelProvider(this, ViewModelFactory(this))[AddItemViewModel::class.java]
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            PermissionHelper.requestMultiPermission(this@AddItemActivity, arrayOf(Manifest.permission.READ_MEDIA_IMAGES)){ hasPermission ->
                if (hasPermission){
                    binding.actionAddPhoto.setOnClickListener{
                        chooseFromGallery()
                    }
                }
            }
        }else{
            PermissionHelper.requestMultiPermission(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)){ hasPermission ->
                if (hasPermission){
                    binding.actionAddPhoto.setOnClickListener{
                        chooseFromGallery()
                    }
                }
            }
        }
        initAction()
        doObserve()
    }
    private fun initAction(){
        binding.itemPopupMenuAddItemCloseBtn.setOnClickListener {
            finish()
        }

        binding.actionSave.setOnClickListener {
            checkValidation()
        }
    }

    private fun doObserve(){
        mViewModel.isLoading.observe(this){
            binding.loadingView.root.visibility = if (it) View.VISIBLE else View.GONE
        }
        mViewModel.errorMessage.observe(this){
            MessageUtils.showError(this, "Error", it)
        }
    }
    private fun checkValidation(){
        when {
            binding.cardViewImageWrapper.visibility == View.GONE ->{
                MessageUtils.showError(this, "Invalid", "Image is required!")
            }
            binding.itemNameEdt.text.toString().isEmpty() -> {
                MessageUtils.showError(this, "Invalid", "Please enter item name!")
            }
            binding.quantityEdt.text.toString().isEmpty() -> {
                MessageUtils.showError(this, "Invalid", "Please enter quantity!")
            }
            binding.priceEdt.text.toString().isEmpty() -> {
                MessageUtils.showError(this, "Invalid", "Please enter price!")
            }
            else -> {
                val name = binding.itemNameEdt.text.toString()
                val price = binding.priceEdt.text.toString().toDouble()
                val quantity = binding.quantityEdt.text.toString().toInt()
                val imageBitmap = (binding.itemImageTv.drawable as BitmapDrawable).bitmap
                //val imageBase64 = String.format("data:image/jpeg;base64,%s", Helper.convertToBase64(imageBitmap))
                val bodyRequest = AddItemRequestBody(name, "", price, unit = "", "", quantity)
                mViewModel.addFolder(bodyRequest)
            }
        }
    }

    private fun chooseFromGallery(){
        val builder: ImagePicker.Builder =
            ImagePicker.Builder(this).galleryOnly().crop(0.9f, 0.6f)
        builder.start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val uri: Uri = data?.data!!
            Glide.with(this).load(uri).into(binding.itemImageTv)
            binding.cardViewImageWrapper.visibility = View.VISIBLE
        }
    }
}