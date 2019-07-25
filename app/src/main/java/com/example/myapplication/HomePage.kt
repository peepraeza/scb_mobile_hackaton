package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.models.ProfileParcel
import com.example.myapplication.models.UserRegisParcel
import kotlinx.android.synthetic.main.home_page.*

class HomePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)

        val userProfile = intent.getParcelableExtra<ProfileParcel>("userProfile")

        val(id, name, lastName, phoneNumber, gender, alias, point)= userProfile
//
        name_homepage_text.setText("${name} ${lastName}" )
        point_txt.setText(point)

        reward_btn.setOnClickListener{
            val intent = Intent(this@HomePage, RewardPage::class.java)
            val userProfile = ProfileParcel(id, name, lastName, phoneNumber, gender, alias, point.toString())
            intent.putExtra("userProfile", userProfile)
            startActivity(intent)
        }

        cardless_btn.setOnClickListener{
            val intent = Intent(this@HomePage, Details::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {

    }
}
