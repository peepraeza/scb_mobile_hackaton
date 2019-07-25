package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.models.AuthAuthorize
import com.example.myapplication.models.ProfileParcel
import com.example.myapplication.models.ResponseEntity
import com.example.myapplication.services.ApiManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val err = intent.getStringExtra("userRegis")
        if(err != null){
            Toast.makeText(applicationContext, err, Toast.LENGTH_LONG).show()
        }

        login_btn.setOnClickListener {
            val id = citizen_id_login_text.text.toString()
            val password = password_login_text.text.toString()
            goLogin(id, password)

        }

//        locationBtn.setOnClickListener {
////            val intent = Intent(this, Location2Page::class.java)
////            startActivity(intent)
//            val origin = "49.2346493,28.3295511";
//            val destination = "50.4021368,30.2525068";
//            val travelMode = "driving";
//
//            val uri = String.format("https://www.google.com/maps/dir/?api=1&origin=%s&destination=%s&travelmode=%s&dir_action=navigate", origin, destination, travelMode);
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
//            intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
//            startActivity(intent);
//        }

        register_btn.setOnClickListener {
            getDeeplink()
//            val intent = Intent(this@MainActivity, HomePage::class.java)
//            startActivity(intent)

        }

    }

    private val authAuthorizeCallback = object : Callback<AuthAuthorize> {
        override fun onResponse(call: Call<AuthAuthorize>, response: Response<AuthAuthorize>) {
            val responseData = response.body() ?: return
            intoDeepLink(responseData)
        }

        override fun onFailure(call: Call<AuthAuthorize>, t: Throwable) {
            Log.e("networking", "Can not call authorize", t)
        }
    }

    private fun intoDeepLink(responseData: AuthAuthorize) {
        val deepLink = responseData.data.callbackUrl
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(deepLink)
        startActivity(intent)
    }

    private fun getDeeplink() {
        ApiManager.scbService.authorize().enqueue(authAuthorizeCallback)
    }

    private fun goLogin(id: String, password: String){
        ApiManager.tanJaiService.login(id, password).enqueue(object: Callback<ResponseEntity>{
            override fun onResponse(call: Call<ResponseEntity>, response: Response<ResponseEntity>) {
                if(response.body()!!.status.code == 1000){
                    val res = response.body()!!.data
                    val name = res.name
                    val lastName = res.lastName
                    val phoneNumber = res.phoneNumber
                    val gender = res.gender
                    val id = res.citizenId
                    val point = res.point
                    val alias = res.alias

                    val intent = Intent(this@MainActivity, HomePage::class.java)
                    val userProfile = ProfileParcel(id, name, lastName, phoneNumber, gender, alias, point.toString())
                    intent.putExtra("userProfile", userProfile)
                    Log.d("Pee", "Login to Dashboard")
                    startActivity(intent)
                }else{

                    Toast.makeText(this@MainActivity, "Cannot Login", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponseEntity>, t: Throwable) {

            }

        })
    }

    override fun onBackPressed() {

    }

}
