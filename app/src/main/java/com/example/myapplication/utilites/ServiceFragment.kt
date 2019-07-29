package scb.NongThanchai.utilites

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.util.Log.i
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.ShowRewardActivity
import com.example.myapplication.models.*
import com.example.myapplication.services.ApiManager
import kotlinx.android.synthetic.main.fragment_service.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ServiceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_service, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val handler: Handler = Handler()
        val prefs_user = getActivity()!!.getSharedPreferences("keepUserData", Context.MODE_PRIVATE)
        val prefs_latlong = getActivity()!!.getSharedPreferences("keepLatLong", Context.MODE_PRIVATE)
        val citizenId = prefs_user.getString("citizenId", null)
        val lat = prefs_latlong.getString("Lat", null)
        val long = prefs_latlong.getString("Long", null)
        toggle_btn.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val price = edit_price.text.toString()
                if (price != "0" && price != null) {
                    i("Pee", price)
                    i("Pee", "Send API check")
                    edit_price.isEnabled = false

                    handler.post(object : Runnable {
                        override fun run() {

                            var body =
                                LocationModelBody(citizenId, lat.toDouble(), long.toDouble(), price.toInt(), true)
                            ApiManager.userArea.createLatLong(body).enqueue(object : Callback<ResponseEntity> {
                                override fun onResponse(
                                    call: Call<ResponseEntity>,
                                    response: Response<ResponseEntity>
                                ) {
                                    i("Pee", "createLatLong Real Code" + response.code())
                                    i("Pee", "createLatLong Status Code : " + response.body()!!.status.code)
                                    ApiManager.tanJaiService.getAtmPending(citizenId)
                                        .enqueue(object : Callback<PendingRes> {
                                            override fun onResponse(
                                                call: Call<PendingRes>,
                                                response: Response<PendingRes>
                                            ) {
                                                i("Pee", "getAtmPending Real Code :" + response.code().toString())
                                                i(
                                                    "Pee",
                                                    "getAtmPending Status Code : :" + response.body()!!.status.code
                                                )
                                                if (response.body()!!.status.code == 1000) {

                                                    var amount = response.body()!!.data.amount.toString()
                                                    handler.removeCallbacksAndMessages(null)
                                                    ApiManager.tanJaiService.deletePending(response.body()!!.data.pendingId.toLong())
                                                        .enqueue(object : Callback<ResponseEntity> {
                                                            override fun onResponse(
                                                                call: Call<ResponseEntity>,
                                                                response: Response<ResponseEntity>
                                                            ) {
                                                                if (response.body()!!.status.code == 1000) {
                                                                    var intent =
                                                                        Intent(context, ShowRewardActivity::class.java)
                                                                    intent.putExtra("amount", amount)
                                                                    startActivity(intent)
                                                                }
                                                            }

                                                            override fun onFailure(
                                                                call: Call<ResponseEntity>,
                                                                t: Throwable
                                                            ) {

                                                            }

                                                        })
//                                                Toast.makeText(context, "Found!!", Toast.LENGTH_LONG).show()


//                                                    val builder = AlertDialog.Builder(context!!.applicationContext)
//
//                                                    builder.setTitle("Confirm use reward")
//                                                    builder.setMessage("The system will automatically deductions the point.")
//                                                    builder.setPositiveButton("YES"){dialog, which ->
//
//                                                        Toast.makeText(context, "Found!!", Toast.LENGTH_LONG).show()
//                                                        var intent = Intent(context, ShowRewardActivity::class.java)
//                                                        intent.putExtra("amount", response.body()!!.data.amount.toString())
//                                                        startActivity(intent)
//                                                    }
//
//                                                    builder.setNeutralButton("NO"){_,_ ->
//                                                    }
//
//                                                    val dialog: AlertDialog = builder.create()
//                                                    dialog.show()
//
                                                }
                                            }

                                            override fun onFailure(call: Call<PendingRes>, t: Throwable) {

                                            }

                                        })
                                }

                                override fun onFailure(call: Call<ResponseEntity>, t: Throwable) {

                                }

                            })
                            handler.postDelayed(this, 2000)
                        }
                    })


                }
            } else {
                i("Pee", "unchecked")
                edit_price.isEnabled = true

                ApiManager.userArea.deleteLocation(false, citizenId).enqueue(object : Callback<ResponseEntity> {
                    override fun onResponse(call: Call<ResponseEntity>, response: Response<ResponseEntity>) {
                        i("Pee", "deleteLocation Real Code : " + response.code().toString())
                        i("Pee", "deleteLocation Status Code : " + response.body()!!.status.code.toString())
                        if (response.body()!!.status.code == 1000) {
                            handler.removeCallbacksAndMessages(null)
                        }
                    }

                    override fun onFailure(call: Call<ResponseEntity>, t: Throwable) {

                    }

                })

            }

        }
    }
}






