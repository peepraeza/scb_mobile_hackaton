package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.models.*
import com.example.myapplication.services.ApiManager
import kotlinx.android.synthetic.main.activity_show_reward.*
import kotlinx.android.synthetic.main.activity_show_reward.point_txt
import kotlinx.android.synthetic.main.qr_code.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class ShowRewardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qr_code)

        val amount = intent.getStringExtra("amount")
        val prefs_user = getSharedPreferences("keepUserData", Context.MODE_PRIVATE)
        val citizenId = prefs_user.getString("citizenId", null)
//        val amount = "200"
        Log.i("Pee", "amount" + amount)
        val body = GenQrCodeReqBody("PP", "BILLERID", "475237345537890", amount,
            "REFERENCE1","REFERENCE2","KPC")
        ApiManager.scbService.generateQrCode("Bearer 614946fd-074c-455d-8302-2dece8194297", body).enqueue(object :
            Callback<GenQrCodeResponse> {
            override fun onResponse(call: Call<GenQrCodeResponse>, response: Response<GenQrCodeResponse>) {
                Log.i("Pee", "generateQrCode Real Code: " + response.code().toString())
                Log.i("Pee", response.body()!!.data.qrImage)
                val img = response.body()!!.data.qrImage
                val decodedString = Base64.decode(img, Base64.DEFAULT)
                val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                money.setText("Amount "+ amount+ " Baht")
                img_qr.setImageBitmap(decodedByte)

                confirm_btn.setOnClickListener{
                    ApiManager.tanJaiService.addPoint(citizenId).enqueue(object :Callback<ResponseEntity>{
                        override fun onResponse(call: Call<ResponseEntity>, response: Response<ResponseEntity>) {
                            var intent =
                                Intent(this@ShowRewardActivity, HomePage::class.java)
                            startActivity(intent)

                        }

                        override fun onFailure(call: Call<ResponseEntity>, t: Throwable) {

                        }

                    })
                }
            }

            override fun onFailure(call: Call<GenQrCodeResponse>, t: Throwable) {

            }

        })
    }
}
