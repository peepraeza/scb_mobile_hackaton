package com.example.myapplication.utilites

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myapplication.R
import kotlinx.android.synthetic.main.custom_tab_layout.view.*
import scb.NongThanchai.utilites.ServiceFragment


class PagerAdapter(private val context: Context, fm: FragmentManager, val intent: Intent) :
    FragmentPagerAdapter(fm) {

    private val PAGES: Int = 2

    private val TAB_TITLES = arrayOf("User", "Service")

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> {
                LoadUserFragment()
            }

            else -> {
                ServiceFragment()
            }
        }

    }

    override fun getCount(): Int {
        return PAGES
    }

    //     custom tab
    fun getTabView(position: Int): View {
        return LayoutInflater.from(context).inflate(R.layout.custom_tab_layout, null).apply {
            title.text = TAB_TITLES[position]
        }
    }
}

