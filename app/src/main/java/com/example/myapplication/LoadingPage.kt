package com.example.myapplication

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.myapplication.models.*
import com.example.myapplication.services.ApiManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoadingPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loading_page)

        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
        val message = dialogView.findViewById<TextView>(R.id.message)
        message.text = "Downloading..."
        builder.setView(dialogView)
        builder.setCancelable(false)
        val dialog = builder.create()
        dialog.show()
        println("Loading")
        val uri = intent.data!!

        val authCode = uri.getQueryParameter("code").toString()
        getToken(authCode)
    }

    private fun getToken(authCode: String) {
        var body = AuthTokenBody(
            "l72eb1a5af8392429690292e82bda0574e",
            "d49a07dae6684b65a7d193718d5325dc",
            authCode
        )
        Log.d("Pee", authCode)

        ApiManager.scbService.postKey(body).enqueue(object : Callback<AuthToken> {
            override fun onResponse(call: Call<AuthToken>, response: Response<AuthToken>) {
                Log.d("Pee", "${response.code()}")
                Log.d("Pee", "${response.body()!!.data.accessToken}")
                Log.d("Pee", response.headers().get("resourceownerid").toString())

                val accessToken = "Bearer " + response.body()!!.data.accessToken
                val resourceId = response.headers().get("resourceownerid").toString()

                ApiManager.scbService.getCustomer(accessToken, resourceId).enqueue(object : Callback<CustomerProfile> {
                    override fun onResponse(call: Call<CustomerProfile>, response: Response<CustomerProfile>) {
                        val firstname = response.body()!!.data.profile.engFirstName
                        val lastname = response.body()!!.data.profile.engLastName
                        val id = response.body()!!.data.profile.citizenID
                        var phonenum = response.body()!!.data.profile.mobile
                        var gender = response.body()!!.data.profile.genderCode
                        if(gender == "M"){
                            gender = "Male"
                        }else{
                            gender = "Female"
                        }

                        var phonenum_plaintext = ""
                        for (index in phonenum.indices){
                             if(phonenum[index].isDigit()){
                                 phonenum_plaintext = phonenum_plaintext+phonenum[index]
                             }
                        }

                        Log.d("Pee", "id : " + id)
                        ApiManager.tanJaiService.verifyUser(id).enqueue(object : Callback<ResponseEntity> {
                            override fun onResponse(call: Call<ResponseEntity>, response: Response<ResponseEntity>) {
                                Log.d("Pee", response.code().toString())
                                Log.d("Pee", response.body()!!.toString())

                                if (response.body()!!.data.status) {
                                    AlertDialog.Builder(this@LoadingPage).create().dismiss()
                                    val intent = Intent(this@LoadingPage, RegisterPage::class.java)
                                    val userRegis = UserRegisParcel(firstname, lastname, phonenum_plaintext, gender, id)
                                    intent.putExtra("userRegis", userRegis)
                                    startActivity(intent)
                                }else{
                                    AlertDialog.Builder(this@LoadingPage).create().dismiss()
                                    val intent = Intent(this@LoadingPage, MainActivity::class.java)
                                    intent.putExtra("userRegis", "Cannot Register")
                                    startActivity(intent)
                                }
                            }

                            override fun onFailure(call: Call<ResponseEntity>, t: Throwable) {
//                                d("Pee", "onFailure")
                            }

                        })


                    }

                    override fun onFailure(call: Call<CustomerProfile>, t: Throwable) {

                    }

                })
//

            }

            override fun onFailure(call: Call<AuthToken>, t: Throwable) {

            }

        })
    }
}
