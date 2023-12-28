package kh.edu.rupp.ite.boxify.helper

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

object Helper {
    fun convertToBase64(
        bitmap: Bitmap,
    ): String? {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
    }
}