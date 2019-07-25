package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.models.ProfileParcel
import kotlinx.android.synthetic.main.home_page.*

class RewardPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reward_page)
        val userProfile = intent.getParcelableExtra<ProfileParcel>("userProfile")
        val(id, name, lastName, phoneNumber, gender, alias, point)= userProfile

        name_homepage_text.setText("${name} ${lastName}" )
        point_txt.setText(point)

    }
}
