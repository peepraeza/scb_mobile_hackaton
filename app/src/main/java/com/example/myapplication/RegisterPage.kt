package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.models.*
import com.example.myapplication.services.ApiManager
import kotlinx.android.synthetic.main.register_page.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class RegisterPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_page)

        val userRegis = intent.getParcelableExtra<UserRegisParcel>("userRegis")
        var (firstname, lastname, phoneNumber, gender, id) = userRegis

        name_text.setText(firstname + " " + lastname)
        phone_text.setText(phoneNumber)
        gender_text.setText(gender)
        citizen_id_text.setText(id)

        register_submit_btn.setOnClickListener {
            var password = password_text.text.toString()
            phoneNumber = phone_text.text.toString()
            var alias = alias_text.text.toString()
            val bodyUserRegis = UserRegis(
                id.toString(), firstname.toString(), lastname.toString(),
                phoneNumber.toString(),  gender.toString(), password, alias
            )
            println(bodyUserRegis)
            ApiManager.tanJaiService.register(bodyUserRegis).enqueue(object : Callback<ResponseEntity> {
                override fun onResponse(call: Call<ResponseEntity>, response: Response<ResponseEntity>) {
                    d("Pee", response.body()!!.status.code.toString())
                    try {
                        if (response.body()!!.status.code == 1000) {
                            val res = response.body()!!.data
                            val name = res.name
                            val lastName = res.lastName
                            val phoneNumber = res.phoneNumber
                            val gender = res.gender
                            val id = res.citizenId
                            val point = res.point
                            val alias = res.alias
                            val intent = Intent(this@RegisterPage, HomePage::class.java)
                            val userProfile = ProfileParcel(id, name, lastName, phoneNumber, gender, alias, point.toString())
                            intent.putExtra("userProfile", userProfile)
                            startActivity(intent)
                        }
                    } catch (e: Exception) {
                        Toast.makeText(applicationContext, "Cannot Register", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<ResponseEntity>, t: Throwable) {
                    d("Pee", "Not Pass")
                }
            })
        }

        cancel_btn.setOnClickListener{
            val intent = Intent(this@RegisterPage, MainActivity::class.java)
            startActivity(intent)
        }

    }


    override fun onBackPressed() {

    }


}