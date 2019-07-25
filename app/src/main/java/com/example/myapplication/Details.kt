package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.models.ListUser
import com.example.myapplication.services.ApiManager
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.list_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Details : AppCompatActivity() {

    private val dataList: MutableList<ListUser.Data> = mutableListOf()
    private lateinit var customerAdapter: CustomerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        mRecyclerView.adapter = CustomerAdapter(dataList)
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        ApiManager.getUser.getAllUser().enqueue(object : Callback<ListUser> {
            override fun onResponse(call: Call<ListUser>, response: Response<ListUser>) {
                d("Pee", "Come to Details Page")
                d("Pee", response.body()!!.data.toString())
                dataList.addAll(response.body()!!.data)
                customerAdapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<ListUser>, t: Throwable) {

            }

        })

    }

    private lateinit var context: Context

    inner class CustomerAdapter(private val dataList: MutableList<ListUser.Data>) :
        RecyclerView.Adapter<CustomerHolder>() {
        override fun onCreateViewHolder(parentView: ViewGroup, option: Int): CustomerHolder {
            context = parent.applicationContext
            val view = LayoutInflater.from(context).inflate(R.layout.list_item, parentView, false)
            return CustomerHolder(view)
        }

        override fun getItemCount(): Int = dataList.size

        override fun onBindViewHolder(holder: CustomerHolder, position: Int) {
            val data = dataList[position]
            val userFullName = holder.itemView.mNameTextView
            val email = holder.itemView.mEmailTextView

            var fullName = "${data.firstName} ${data.lastName}"
            userFullName.text = fullName
            email.text = data.email
        }

    }

    inner class CustomerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}
