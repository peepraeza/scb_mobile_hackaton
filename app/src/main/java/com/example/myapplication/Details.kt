package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_details.*

class Details : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        mRecyclerView.adapter = CustomerAdapter()
        mRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    inner class CustomerAdapter : RecyclerView.Adapter<CustomerHolder>() {
        override fun onCreateViewHolder(parentView: ViewGroup, option: Int): CustomerHolder {
            val view = LayoutInflater.from(applicationContext).inflate(R.layout.list_item, parentView, false)
            return CustomerHolder(view)
        }

        override fun getItemCount(): Int {
            return 20
        }

        override fun onBindViewHolder(p0: CustomerHolder, position: Int) {

        }

    }

    inner class CustomerHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }
}
