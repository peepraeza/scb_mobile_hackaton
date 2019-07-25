package com.example.myapplication

import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.utilites.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout


class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)
        d("Pee", "Come to Dashboard")
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, intent)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        // custom tabs
        for (i in 0 until tabs.tabCount) {
            val tab: TabLayout.Tab? = tabs.getTabAt(i)
            tab!!.customView = sectionsPagerAdapter.getTabView(i)
        }


    }


}