package com.example.shop.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.*


object BitmapHandler {

    //压缩图片像素
    @Throws(FileNotFoundException::class, IOException::class)
    fun getBitmapFormUri(uri: Uri?, context: Context?): Bitmap? {
        var input: InputStream? = uri?.let { context?.contentResolver?.openInputStream(it) }

        //这一段代码是不加载文件到内存中也得到bitmap的真是宽高，主要是设置inJustDecodeBounds为true
        val onlyBoundsOptions = BitmapFactory.Options()
        onlyBoundsOptions.inJustDecodeBounds = true //不加载到内存
        onlyBoundsOptions.inDither = true //optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.RGB_565 //optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions)
        input?.close()

        val originalWidth = onlyBoundsOptions.outWidth
        val originalHeight = onlyBoundsOptions.outHeight
        if (originalWidth == -1 || originalHeight == -1) return null

        //图片分辨率以480x800为标准
        val hh = 800f // 这里设置高度为800f
        val ww = 480f // 这里设置宽度为480f

        // 缩放比，由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        var scale = 1 // scale = 1表示不缩放
        if (originalWidth > originalHeight && originalWidth > ww) {
            // 如果宽度大的话根据宽度固定大小缩放
            scale = (originalWidth / ww).toInt()
        } else if (originalWidth < originalHeight && originalHeight > hh) {
            // 如果高度高的话根据宽度固定大小缩放
            scale = (originalHeight / hh).toInt()
        }
        if (scale <= 0) scale = 1

        //比例压缩
        val bitmapOptions = BitmapFactory.Options()
        bitmapOptions.inSampleSize = scale //设置缩放比例
        bitmapOptions.inDither = true
        bitmapOptions.inPreferredConfig = Bitmap.Config.RGB_565

        input = uri?.let { context?.contentResolver?.openInputStream(it) }
        val bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions)
        input?.close()
        return compressImage(bitmap) //再进行质量压缩
    }

    private fun compressImage(image: Bitmap?): Bitmap? {
        val baos = ByteArrayOutputStream()
        image?.compress(Bitmap.CompressFormat.JPEG, 100, baos) //质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        var options = 100
        while (baos.toByteArray().size / 1024 > 100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset() // 重置baos即清空baos
            //第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差  ，第三个参数：保存压缩后的数据的流
            image?.compress(
                    Bitmap.CompressFormat.JPEG,
                    options,
                    baos
            ) //这里压缩options，把压缩后的数据存放到baos中
            options -= 10 //每次都减少10
            if (options <= 0) break
        }
        val isBm = ByteArrayInputStream(baos.toByteArray()) //把压缩后的数据baos存放到ByteArrayInputStream中
        return BitmapFactory.decodeStream(isBm, null, null)
    }


}
