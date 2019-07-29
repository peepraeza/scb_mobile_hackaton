package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.models.ProfileParcel
import kotlinx.android.synthetic.main.user_typeprice.*

class UserEnterAmount : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_typeprice)

        val name_des = intent.getStringExtra("name")
        val distance_des = intent.getStringExtra("distance")
        val atmCitizenId = intent.getStringExtra("atmCitizenId")

        val citizenId = "2615654232234"


        name_destination.text= name_des
        distance_destination.text = distance_des

        confirm_btn.setOnClickListener {

            val intent = Intent(applicationContext, UserWaiting::class.java)
            intent.putExtra("name", name_des)
            intent.putExtra("distance",distance_des)
            intent.putExtra("price",edit_price.text.toString())
            intent.putExtra("atmCitizenId",atmCitizenId)
            intent.putExtra("citizenId",citizenId)
//            intent.putExtra("atmCitizenId", )
            startActivity(intent)
        }
    }
}