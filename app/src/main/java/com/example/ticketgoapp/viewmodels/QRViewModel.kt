package com.example.ticketgoapp.viewmodels

import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

class QRViewModel : ViewModel() {

    private val size = 512
    private val writer = QRCodeWriter()
    private lateinit var bmp: Bitmap

    fun encodeQR(value: String): Bitmap {
        try {
            val bitMatrix = writer.encode(value, BarcodeFormat.QR_CODE, size, size)
            bmp = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565)
            for (x in 0 until size) {
                for (y in 0 until size) {
                    bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.WHITE else Color.BLACK)
                }
            }
        } catch (e: WriterException) {
            Log.d("QR Exception", e.message.toString())
        }

        return bmp
    }
}