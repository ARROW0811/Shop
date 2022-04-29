package com.example.shop.minePack.presenter

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.shop.fragment.PublishFragment
import com.example.shop.util.BitmapHandler
import com.example.shop.util.L
import java.io.FileNotFoundException
import java.io.IOException

class PublishPresenter (val view: PublishFragment){
    fun requestPermissionAndTryOpen() {
        requestWriteExternalStoragePermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        L.d("打开相册成功")
    }

    private val requestPickImageLauncher =
            view.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    decodeBitmapAndSet(result)
                }
            }

    private val requestWriteExternalStoragePermissionLauncher =
            view.registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
                if (granted) {
                    tryOpenSystemAlbum()
                } else {
                    Toast.makeText(view.requireContext(), "没有权限无法打开相册噢", Toast.LENGTH_LONG).show()
                }
            }

    private fun tryOpenSystemAlbum() {
        val intent = Intent(Intent.ACTION_PICK).apply {
            setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        }
        if (intent.resolveActivity(view.requireActivity().packageManager) != null) {
            requestPickImageLauncher.launch(intent)
        } else {
            Toast.makeText(view.requireContext(), "您的手机没有手机相册噢", Toast.LENGTH_LONG).show()
        }
    }

    private fun decodeBitmapAndSet(result: ActivityResult) {
        val uri = result.data?.data
        var bm: Bitmap? = null
        if (uri != null) {
            try { bm = BitmapHandler.getBitmapFormUri(uri, view.requireContext()) }
            catch (e: FileNotFoundException) { }
            catch (e: IOException) { }
        } else {
            val bundleExtras = result.data?.extras
            bundleExtras?.let {
                bm = bundleExtras.getParcelable("data")
            }
        }
        view.mIvGoodsImg.setImageBitmap(bm)

    }
}