package com.example.myapplication.utilites


import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.util.Log.i
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
import androidx.core.view.get
import com.example.myapplication.HomePage
import com.example.myapplication.RegisterPage
import com.example.myapplication.models.LocationModelBody
import com.example.myapplication.models.LocationModelRes
import com.google.android.gms.common.api.Api
import kotlinx.android.synthetic.main.custom_info.*
import kotlinx.android.synthetic.main.custom_info.view.cardview


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
        val  prefs_user = getActivity()!!.getSharedPreferences("keepUserData", Context.MODE_PRIVATE)
        i("Pee", "Id "+prefs_user.getString("citizenId", null))
        val citizenId = prefs_user.getString("citizenId", null)
        val  prefs_latlong = getActivity()!!.getSharedPreferences("keepLatLong", Context.MODE_PRIVATE)
        val lat = prefs_latlong.getString("Lat", null)
        val long = prefs_latlong.getString("Long", null)

        i("Pee", "id from pref :"+citizenId)
        i("Pee", "lat from pref :"+lat)
        i("Pee", "long from pref :"+long)

        // Inflate the layout for this fragment
        val _view = inflater.inflate(com.example.myapplication.R.layout.fragment_load_user, container, false)

        _view.recyclerView.let{
            it.layoutManager = LinearLayoutManager(context)

            it.adapter = adapter
        }

        feedData(citizenId, lat, long)
        return _view
    }

    private fun feedData(citizenId: String, lat:String, long: String) {

        i("Pee", "Id "+citizenId)
        i("Pee", "Lat "+ lat)
        i("Pee", "Long "+ long)
        val userBody = ListUserAreaBody(citizenId,lat.toDouble(),long.toDouble(),0, true)

//        SharedPreferences sp  =  ("keepCitizenId", Context.)
//        i("Pee", "feed Data"+prefs.getString("citizenId", ))

        i("Pee", "get Feed Data From Load User")
        ApiManager.userArea.getAllNearUser(userBody).enqueue(object :Callback<ListUserAreaReq>{
            override fun onResponse(call: Call<ListUserAreaReq>, response: Response<ListUserAreaReq>) {
//                i("Pee", "After Api Call "+response.code())
                i("Pee", "Status Code : "+response.code().toString())
                Log.i("Pee", "Near me"+ response.body()!!.toString())
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
            val atmCitizenId = item.citizenId

            holder.cardview.setOnClickListener {
//
                Log.d("noi","eiei")
                val intent = Intent(context, UserEnterAmount::class.java)
                intent.putExtra("name",holder.alias.text.toString())
                intent.putExtra("distance",holder.distance.text.toString())
                intent.putExtra("atmCitizenId", atmCitizenId)
                startActivity(intent)
            }
        }

    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val alias = itemView.titleTextView
        val distance = itemView.distance
        val cardview = itemView.cardview


    }

}


