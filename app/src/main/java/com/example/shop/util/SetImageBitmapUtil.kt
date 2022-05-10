package com.example.shop.util

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.ImageView
import androidx.core.content.ContentProviderCompat.requireContext
import java.io.FileNotFoundException
import java.io.IOException

object SetImageBitmapUtil {
    fun setBitmap(uri:String,imageView:ImageView,context: Context) {
        val uri: Uri = Uri.parse(uri)
        L.d("uri:${uri}")
        var bm: Bitmap? = null
        if (uri != null) {
            try { bm = BitmapHandler.getBitmapFormUri(uri, context)}
            catch (e: FileNotFoundException) { }
            catch (e: IOException) { }
        } else {

        }
        imageView.setImageBitmap(bm)
    }

}