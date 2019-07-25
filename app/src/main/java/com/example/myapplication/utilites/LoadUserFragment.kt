package com.example.myapplication.utilites


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DashboardActivity
import com.example.myapplication.UserEnterAmount
import com.example.myapplication.models.ListUserAreaBody
import com.example.myapplication.models.ListUserAreaReq
import com.example.myapplication.services.ApiManager
import kotlinx.android.synthetic.main.custom_info.view.*
import kotlinx.android.synthetic.main.fragment_load_user.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.core.content.ContextCompat.startActivity
import com.example.myapplication.RegisterPage


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class LoadUserFragment : Fragment() {

    val adapter = CustomAdapter(ArrayList<ListUserAreaReq.Data>())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val _view = inflater.inflate(com.example.myapplication.R.layout.fragment_load_user, container, false)

        _view.recyclerView.let{
            it.layoutManager = LinearLayoutManager(context)

            it.adapter = adapter
        }

        feedData()
        return _view
    }

    private fun feedData() {

        val userBody = ListUserAreaBody("1234567891234",13.758435,100.566316,500, true)

        ApiManager.userArea.getAllNearUser(userBody).enqueue(object :Callback<ListUserAreaReq>{
            override fun onResponse(call: Call<ListUserAreaReq>, response: Response<ListUserAreaReq>) {
                Log.d("Pee", response.body()!!.toString())
                adapter.mDataArray.addAll(response.body()!!.data)
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ListUserAreaReq>, t: Throwable) {

            }

        })


    }

    inner class CustomAdapter(val mDataArray: ArrayList<ListUserAreaReq.Data>) : RecyclerView.Adapter<CustomViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, index: Int): CustomViewHolder {
            val layout = LayoutInflater.from(parent.context).inflate(com.example.myapplication.R.layout.custom_info, null, false)
            return CustomViewHolder(layout)
        }

        override fun getItemCount(): Int {
            // แสดงผล data ตาม mDataArray หมายถึงจะแสดงแถวทั้งหมดที่มีใน mDataArray
            return mDataArray.size
        }

        override fun onBindViewHolder(holder: CustomViewHolder, index: Int) {
            val item = mDataArray[index]
            holder.alias.text = item.alias
            holder.distance.text = item.distance.toString()
        }

    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val alias = itemView.titleTextView
        val distance = itemView.distance
        val cardview = itemView.cardview

        init {

            cardview.setOnClickListener {
                var id = it.getTag(com.example.myapplication.R.id.cardview)
//                val intent = Intent(DashboardActivity(), UserEnterAmount::class.java)
//                startActivity(intent)

                val intent = Intent(DashboardActivity(), UserEnterAmount::class.java)
                startActivity(intent)
//                startActivity(Intent(applicationContext, UserEnterAmount::class.java))

            }
        }

    }

}


