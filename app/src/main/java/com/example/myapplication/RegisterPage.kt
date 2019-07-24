package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.models.*
import com.example.myapplication.services.ApiManager
import kotlinx.android.synthetic.main.register_page.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_page)

        val userRegis = intent.getParcelableExtra<UserProfileParcel>("userRegis")
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
                firstname.toString(), lastname.toString(), phoneNumber.toString(),
                gender.toString(), password, id.toString(), alias
            )
            ApiManager.tanJaiService.postUser(bodyUserRegis).enqueue(object : Callback<ResponseEntity> {
                override fun onResponse(call: Call<ResponseEntity>, response: Response<ResponseEntity>) {
                    d("Pee", "Pass")
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