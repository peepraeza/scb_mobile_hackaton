package com.example.myapplication

import android.os.Bundle
import android.os.Parcel
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.models.ProfileParcel
import com.example.myapplication.models.ResponseEntity
import com.example.myapplication.services.ApiManager
import com.example.myapplication.utilites.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_success.*
import kotlinx.android.synthetic.main.home_page.*
import kotlinx.android.synthetic.main.home_page.point_txt
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DashboardActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)
        d("Pee", "Come to Dashboard")
        val userProfile = intent.getParcelableExtra<ProfileParcel>("userProfile")
        val (id, name, lastName, phoneNumber, gender, alias, point) = userProfile


        name_dashboard_text.setText("${name} ${lastName}")
        point_dashboard_txt.setText(point)
        d("Pee", "Parcel Get")
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, intent)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        d("Pee", "TabLayout")
        // custom tabs
        for (i in 0 until tabs.tabCount) {
            val tab: TabLayout.Tab? = tabs.getTabAt(i)
            tab!!.customView = sectionsPagerAdapter.getTabView(i)
        }


    }

    override fun onResume() {
        super.onResume()

        val userProfile = intent.getParcelableExtra<ProfileParcel>("userProfile")
        var (id, name, lastName, phoneNumber, gender, alias, point) = userProfile

        ApiManager.tanJaiService.getUser(id.toString()).enqueue(object : Callback<ResponseEntity> {
            override fun onResponse(call: Call<ResponseEntity>, response: Response<ResponseEntity>) {
                if(response.body()!!.status.code == 1000){
                    val res = response.body()!!.data
                    val name = res.name
                    val lastName = res.lastName
                    val phoneNumber = res.phoneNumber
                    val gender = res.gender
                    val id = res.citizenId
                    val point = res.point.toString()
                    val alias = res.alias
                    name_dashboard_text.setText("${name} ${lastName}")
                    point_dashboard_txt.setText(point)
                }
            }

            override fun onFailure(call: Call<ResponseEntity>, t: Throwable) {

            }

        })

    }


}