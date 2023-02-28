package com.xiaopo.flying.sticker

import android.content.Context
import android.graphics.Bitmap
import android.provider.MediaStore
import android.content.Intent
import android.graphics.RectF
import android.net.Uri
import android.util.Log
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.lang.IllegalStateException
import kotlin.math.roundToInt

internal object StickerUtils {
    private const val TAG = "StickerView"
    @JvmStatic
    fun saveImageToGallery(file: File, bmp: Bitmap): File {
        try {
            val fos = FileOutputStream(file)
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.flush()
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        Log.e(TAG, "saveImageToGallery: the path of bmp is " + file.absolutePath)
        return file
    }

    @JvmStatic
    fun notifySystemGallery(context: Context, file: File) {
        require(file.exists()) { "bmp should not be null" }
        try {
            MediaStore.Images.Media.insertImage(
                context.contentResolver,
                file.absolutePath,
                file.name,
                null
            )
        } catch (e: FileNotFoundException) {
            throw IllegalStateException("File couldn't be found")
        }
        context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)))
    }

    @JvmStatic
    fun trapToRect(r: RectF, array: FloatArray) {
        r[Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY] =
            Float.NEGATIVE_INFINITY
        var i = 1
        while (i < array.size) {
            val x = (array[i - 1] * 10).roundToInt() / 10f
            val y = (array[i] * 10).roundToInt() / 10f
            r.left = if (x < r.left) x else r.left
            r.top = if (y < r.top) y else r.top
            r.right = if (x > r.right) x else r.right
            r.bottom = if (y > r.bottom) y else r.bottom
            i += 2
        }
        r.sort()
    }
}