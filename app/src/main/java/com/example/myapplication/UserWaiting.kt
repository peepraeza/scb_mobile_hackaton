package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log.d
import android.util.Log.i
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.models.PendingBody
import com.example.myapplication.models.PendingRes
import com.example.myapplication.models.ResponseEntity
import com.example.myapplication.services.ApiManager
import kotlinx.android.synthetic.main.user_waiting.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserWaiting : AppCompatActivity() {
    val handler: Handler = Handler()
    var pendingId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_waiting)

        val name = intent.getStringExtra("name")
        val distance = intent.getStringExtra("distance")
        val price = intent.getStringExtra("price")
        val atmCitizenId = intent.getStringExtra("atmCitizenId")
        val citizenId = intent.getStringExtra("citizenId")

        name_destination.text = name
        distance_destination.text = distance
        txt_price.text = price

        i("Pee", price)
        val amount = price!!.toInt()
//
        val body = PendingBody(citizenId, atmCitizenId, amount)
        ApiManager.tanJaiService.createPending(body).enqueue(object : Callback<PendingRes> {
            override fun onResponse(call: Call<PendingRes>, response: Response<PendingRes>) {
                i("Pee", "createPending Real Code : " + response.code())
                i("Pee", "createPending Status Code : " + response.code())
                if (response.body()!!.status.code == 1000) {
                    i("Pee", "Pass")
                    pendingId = response.body()!!.data.pendingId
                    i("Pee", "createPending PendingID : "+pendingId )
                    handler.post(object : Runnable {
                        override fun run() {

                            ApiManager.tanJaiService.checkStatusPending(pendingId)
                                .enqueue(object : Callback<ResponseEntity> {
                                    override fun onResponse(
                                        call: Call<ResponseEntity>,
                                        response: Response<ResponseEntity>
                                    ) {
                                        i("Pee", "checkStatusPending Status Code"+response.body()!!.status.code.toString())
                                        if (response.body()!!.status.code == 1000) {
                                            handler.removeCallbacksAndMessages(null);

                                            var intent = Intent(this@UserWaiting, NaviPage::class.java)
                                            intent.putExtra("atmCitizenId",atmCitizenId)
                                            startActivity(intent)
                                        }
                                    }

                                    override fun onFailure(call: Call<ResponseEntity>, t: Throwable) {

                                    }

                                })
                            handler.postDelayed(this, 2000)
                        }
                    })
                }
            }

            override fun onFailure(call: Call<PendingRes>, t: Throwable) {

            }

        })

        cancel_pending_btn.setOnClickListener {
            i("Pee", "PendingId : "+pendingId.toString())
            ApiManager.tanJaiService.cancelPending(pendingId)
                .enqueue(object : Callback<ResponseEntity> {
                    override fun onResponse(
                        call: Call<ResponseEntity>,
                        response: Response<ResponseEntity>
                    ) {
                        i("Pee", "Status Code Cancel : "+response.code())
                        i("Pee", "code : "+response.body()!!.status.code)
                        if(response.body()!!.status.code == 1599){
                            handler.removeCallbacksAndMessages(null);
                            val intent = Intent(this@UserWaiting, DashboardActivity::class.java)
                            startActivity(intent)
                        }
                    }

                    override fun onFailure(call: Call<ResponseEntity>, t: Throwable) {

                    }

                })

        }
    }

    override fun onBackPressed() {

    }
}