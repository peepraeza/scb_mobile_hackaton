package com.example.myapplication

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Base64
import android.util.Log.d
import android.widget.Toast
import androidx.core.graphics.createBitmap
import com.example.myapplication.models.GenQrCodeReqBody
import com.example.myapplication.models.GenQrCodeResponse
import com.example.myapplication.services.ApiManager
import kotlinx.android.synthetic.main.activity_get_data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class GetData : AppCompatActivity() {

    val handler: Handler = Handler()
    var i = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_data)

        var body = GenQrCodeReqBody(
            "200", "475237345537890", "BILLERID",
            "PP", "REFERENCE1", "REFERENCE2", "SCB"
        )
        ApiManager.scbService.generateQrCode(
            "Bearer e29579f3-9646-4034-8bc8-f0802fdaca62",
            body
        ).enqueue(object : Callback<GenQrCodeResponse> {
            override fun onResponse(call: Call<GenQrCodeResponse>, response: Response<GenQrCodeResponse>) {
                val img = response.body()!!.data.qrImage
                d("Pee", response.code().toString())
                d("Pee", img)
                val imageBytes = Base64.decode(img, 0)
                val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                imageView.setImageBitmap(image)
            }

            override fun onFailure(call: Call<GenQrCodeResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }


//    val runnableCode = object: Runnable {
//        override fun run() {
//            i = i+1
//            if(i >= 10){
//                Toast.makeText(applicationContext, "i = ${i}", Toast.LENGTH_LONG).show()
////                handler.removeCallbacks(this)
//            }else{
//                Toast.makeText(applicationContext, "i = ${i}", Toast.LENGTH_LONG).show()
//                handler.postDelayed(this, 5000)
//            }
//
//        }
//    }

}

