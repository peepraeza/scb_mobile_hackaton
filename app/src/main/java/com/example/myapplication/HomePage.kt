package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.models.ProfileParcel
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
            startActivity(intent)
        }

        cardless_btn.setOnClickListener{
            val intent = Intent(this@HomePage, DashboardActivity::class.java)
            startActivity(intent)
//            val origin = "49.2346493,28.3295511";
//            val destination = "50.4021368,30.2525068";
//            val travelMode = "walking";
//
//            val uri = String.format("https://www.google.com/maps/dir/?api=1&origin=%s&destination=%s&travelmode=%s&dir_action=navigate", origin, destination, travelMode);
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
//            intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
//            startActivity(intent);

//            val action = Intent()
//            action.data = Uri.parse("sosapp://sosapp/hashcode/ghfhgfhgf?")
//            startActivity(action)
        }
    }

    override fun onBackPressed() {

    }
}
