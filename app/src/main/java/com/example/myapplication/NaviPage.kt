package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.i
import com.example.myapplication.models.LocationModelRes
import com.example.myapplication.services.ApiManager
import kotlinx.android.synthetic.main.activity_navigator.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NaviPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigator)
        val atmCitizenId = intent.getStringExtra("atmCitizenId")
        Log.i("Pee", "NavigatorActivity : " + atmCitizenId)
//        ApiManager.userArea.getLatLong(atmCitizenId).enqueue(object : Callback<LocationModelRes> {
//            override fun onResponse(call: Call<LocationModelRes>, response: Response<LocationModelRes>) {
//
                nav_btn.setOnClickListener{
                    i("Pee", "nav btn clicked")
                    var lat_des = "12.7849303"//response.body()!!.data.latitude
                    var long_des = "100.9060172"//response.body()!!.data.longitude
//                    var perf = getSharedPreferences("keepLatLong", MODE_PRIVATE)
                    var lat_ori = "100.9059872"//perf.getString("Lat", null)
                    var long_ori = "12.7849503" //perf.getString("Long", null)
                    val origin = "${lat_ori},${long_ori}";
                    val destination = "${lat_des},${long_des}";
                    val travelMode = "walking";

                    val uri = String.format(
                        "https://www.google.com/maps/dir/?api=1&origin=%s&destination=%s&travelmode=%s&dir_action=navigate",
                        origin,
                        destination,
                        travelMode
                    );
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                    startActivity(intent)
                }
//
//
//                payment_btn.setOnClickListener{
//                    val intent_payment = Intent(Intent.ACTION_VIEW,
//                        Uri.parse("scbeasysim://login/"))
//                    startActivity(intent_payment)
//                }
//
//            }
//
//            override fun onFailure(call: Call<LocationModelRes>, t: Throwable) {
//
//            }
//
//        })
        payment_btn.setOnClickListener{
            i("Pee", "payment btn clicked")
                                val intent_payment = Intent(Intent.ACTION_VIEW,
                        Uri.parse("scbeasysim://login/"))
                    startActivity(intent_payment)
                }

            }
    }

