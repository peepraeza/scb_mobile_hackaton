package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.models.UserProfileParcel

class HomePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)
        val userRegis = intent.getParcelableExtra<UserProfileParcel>("userRegis")
        val(firstname, lastname, phoneNumber, gender, id)= userRegis
        Toast.makeText(applicationContext, "Name: ${firstname} ${lastname}", Toast.LENGTH_LONG).show()
    }
}
