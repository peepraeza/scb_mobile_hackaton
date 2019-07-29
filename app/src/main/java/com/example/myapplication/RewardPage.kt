package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.models.ProfileParcel
import com.example.myapplication.models.ResponseEntity
import com.example.myapplication.models.RewardEntity
import com.example.myapplication.services.ApiManager
import kotlinx.android.synthetic.main.activity_show_reward.*
import kotlinx.android.synthetic.main.home_page.name_homepage_text
import kotlinx.android.synthetic.main.home_page.point_txt
import kotlinx.android.synthetic.main.reward_page.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class RewardPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reward_page)
        val userProfile = intent.getParcelableExtra<ProfileParcel>("userProfile")
        var (id, name, lastName, phoneNumber, gender, alias, point) = userProfile
        val intent = Intent(this@RewardPage, ShowRewardActivity::class.java)
        intent.putExtra("userProfile", userProfile)

        ApiManager.tanJaiService.getUser(id.toString()).enqueue(object : Callback<ResponseEntity> {
            override fun onResponse(call: Call<ResponseEntity>, response: Response<ResponseEntity>) {
                if(response.body()!!.status.code == 1000){
                    val res = response.body()!!.data
                     name = res.name
                     lastName = res.lastName
                     phoneNumber = res.phoneNumber
                     gender = res.gender
                     id = res.citizenId
                     point = res.point.toString()
                     alias = res.alias
                }
            }

            override fun onFailure(call: Call<ResponseEntity>, t: Throwable) {

            }

        })





        //card_lotus_reward
        card_lotus_reward.setOnClickListener {

            ApiManager.tanJaiService.getReward(1).enqueue(object : Callback<RewardEntity> {
                override fun onResponse(call: Call<RewardEntity>, response: Response<RewardEntity>) {
                    val res = response.body()!!.data
                    val rewardName = res.rewardName
                    val rewardCode = res.rewardCode
                    val rewardId = res.rewardId
                    val rewardPoint = res.rewardPoint
                    val rewardDescription = res.rewardDescription

                    val builder = AlertDialog.Builder(this@RewardPage)


                    if (point!!.toInt() < rewardPoint) {
                        // Initialize a new instance of

                        builder.setTitle("Point less than reward point")
                        builder.setMessage("Get more point.")

                        builder.setPositiveButton("Cancel") { _, _ ->
                        }
                    } else {
                        // Initialize a new instance of

                        builder.setTitle("Confirm use reward")
                        builder.setMessage("The system will automatically deductions the point.")
                        builder.setPositiveButton("YES") { dialog, which ->
                            intent.putExtra("rewardName", rewardName)
                            intent.putExtra("rewardCode", rewardCode)
                            intent.putExtra("rewardDescription", rewardDescription)
                            intent.putExtra("rewardPoint", rewardPoint.toString())
                            startActivity(intent)
                        }

                        builder.setNeutralButton("NO") { _, _ ->
                        }


                    }
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }

                override fun onFailure(call: Call<RewardEntity>, t: Throwable) {

                }

            })


        }

// card_central_reward
        card_central_reward.setOnClickListener {
            ApiManager.tanJaiService.getReward(2).enqueue(object : Callback<RewardEntity> {
                override fun onResponse(call: Call<RewardEntity>, response: Response<RewardEntity>) {
                    val res = response.body()!!.data
                    val rewardName = res.rewardName
                    val rewardCode = res.rewardCode
                    val rewardId = res.rewardId
                    val rewardPoint = res.rewardPoint
                    val rewardDescription = res.rewardDescription

                    val builder = AlertDialog.Builder(this@RewardPage)


                    if (point!!.toInt() < rewardPoint) {
                        // Initialize a new instance of

                        builder.setTitle("Point less than reward point")
                        builder.setMessage("Get more point.")

                        builder.setPositiveButton("Cancel") { _, _ ->
                        }
                    } else {
                        // Initialize a new instance of

                        builder.setTitle("Confirm use reward")
                        builder.setMessage("The system will automatically deductions the point.")
                        builder.setPositiveButton("YES") { dialog, which ->
                            intent.putExtra("rewardName", rewardName)
                            intent.putExtra("rewardCode", rewardCode)
                            intent.putExtra("rewardDescription", rewardDescription)
                            intent.putExtra("rewardPoint", rewardPoint.toString())
                            startActivity(intent)
                        }

                        builder.setNeutralButton("NO") { _, _ ->
                        }


                    }
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }

                override fun onFailure(call: Call<RewardEntity>, t: Throwable) {

                }

            })
        }

// chesters_reward

        chesters_reward.setOnClickListener {
            ApiManager.tanJaiService.getReward(3).enqueue(object : Callback<RewardEntity> {
                override fun onResponse(call: Call<RewardEntity>, response: Response<RewardEntity>) {
                    val res = response.body()!!.data
                    val rewardName = res.rewardName
                    val rewardCode = res.rewardCode
                    val rewardId = res.rewardId
                    val rewardPoint = res.rewardPoint
                    val rewardDescription = res.rewardDescription


                    val builder = AlertDialog.Builder(this@RewardPage)


                    if (point!!.toInt() < rewardPoint) {
                        // Initialize a new instance of

                        builder.setTitle("Point less than reward point")
                        builder.setMessage("Get more point.")

                        builder.setPositiveButton("Cancel") { _, _ ->
                        }
                    } else {
                        // Initialize a new instance of

                        builder.setTitle("Confirm use reward")
                        builder.setMessage("The system will automatically deductions the point.")
                        builder.setPositiveButton("YES") { dialog, which ->
                            intent.putExtra("rewardName", rewardName)
                            intent.putExtra("rewardCode", rewardCode)
                            intent.putExtra("rewardPoint", rewardPoint.toString())
                            intent.putExtra("rewardDescription", rewardDescription)

                            startActivity(intent)
                        }

                        builder.setNeutralButton("NO") { _, _ ->
                        }


                    }
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }

                override fun onFailure(call: Call<RewardEntity>, t: Throwable) {

                }

            })
        }

// arabitia_reward
        arabitia_reward.setOnClickListener {
            ApiManager.tanJaiService.getReward(4).enqueue(object : Callback<RewardEntity> {
                override fun onResponse(call: Call<RewardEntity>, response: Response<RewardEntity>) {
                    val res = response.body()!!.data
                    val rewardName = res.rewardName
                    val rewardCode = res.rewardCode
                    val rewardId = res.rewardId
                    val rewardPoint = res.rewardPoint
                    val rewardDescription = res.rewardDescription


                    val builder = AlertDialog.Builder(this@RewardPage)


                    if (point!!.toInt() < rewardPoint) {
                        // Initialize a new instance of

                        builder.setTitle("Point less than reward point")
                        builder.setMessage("Get more point.")

                        builder.setPositiveButton("Cancel") { _, _ ->
                        }
                    } else {
                        // Initialize a new instance of

                        builder.setTitle("Confirm use reward")
                        builder.setMessage("The system will automatically deductions the point.")
                        builder.setPositiveButton("YES") { dialog, which ->
                            intent.putExtra("rewardName", rewardName)
                            intent.putExtra("rewardCode", rewardCode)
                            intent.putExtra("rewardPoint", rewardPoint.toString())
                            intent.putExtra("rewardDescription", rewardDescription)

                            startActivity(intent)
                        }

                        builder.setNeutralButton("NO") { _, _ ->
                        }


                    }
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }

                override fun onFailure(call: Call<RewardEntity>, t: Throwable) {

                }

            })
        }
// dioro_reward
        dioro_reward.setOnClickListener {
            ApiManager.tanJaiService.getReward(5).enqueue(object : Callback<RewardEntity> {
                override fun onResponse(call: Call<RewardEntity>, response: Response<RewardEntity>) {
                    val res = response.body()!!.data
                    val rewardName = res.rewardName
                    val rewardCode = res.rewardCode
                    val rewardId = res.rewardId
                    val rewardPoint = res.rewardPoint
                    val rewardDescription = res.rewardDescription


                    val builder = AlertDialog.Builder(this@RewardPage)


                    if (point!!.toInt() < rewardPoint) {
                        // Initialize a new instance of

                        builder.setTitle("Point less than reward point")
                        builder.setMessage("Get more point.")

                        builder.setPositiveButton("Cancel") { _, _ ->
                        }
                    } else {
                        // Initialize a new instance of

                        builder.setTitle("Confirm use reward")
                        builder.setMessage("The system will automatically deductions the point.")
                        builder.setPositiveButton("YES") { dialog, which ->
                            intent.putExtra("rewardName", rewardName)
                            intent.putExtra("rewardCode", rewardCode)
                            intent.putExtra("rewardPoint", rewardPoint.toString())
                            intent.putExtra("rewardDescription", rewardDescription)

                            startActivity(intent)
                        }

                        builder.setNeutralButton("NO") { _, _ ->
                        }


                    }
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }

                override fun onFailure(call: Call<RewardEntity>, t: Throwable) {

                }

            })
        }

// caffe_muan_chon_reward
        caffe_muan_chon_reward.setOnClickListener {
            ApiManager.tanJaiService.getReward(6).enqueue(object : Callback<RewardEntity> {
                override fun onResponse(call: Call<RewardEntity>, response: Response<RewardEntity>) {
                    val res = response.body()!!.data
                    val rewardName = res.rewardName
                    val rewardCode = res.rewardCode
                    val rewardId = res.rewardId
                    val rewardPoint = res.rewardPoint
                    val rewardDescription = res.rewardDescription


                    val builder = AlertDialog.Builder(this@RewardPage)


                    if (point!!.toInt() < rewardPoint) {
                        // Initialize a new instance of

                        builder.setTitle("Point less than reward point")
                        builder.setMessage("Get more point.")

                        builder.setPositiveButton("Cancel") { _, _ ->
                        }
                    } else {
                        // Initialize a new instance of

                        builder.setTitle("Confirm use reward")
                        builder.setMessage("The system will automatically deductions the point.")
                        builder.setPositiveButton("YES") { dialog, which ->
                            intent.putExtra("rewardName", rewardName)
                            intent.putExtra("rewardCode", rewardCode)
                            intent.putExtra("rewardPoint", rewardPoint.toString())
                            intent.putExtra("rewardDescription", rewardDescription)

                            startActivity(intent)
                        }

                        builder.setNeutralButton("NO") { _, _ ->
                        }


                    }
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }

                override fun onFailure(call: Call<RewardEntity>, t: Throwable) {

                }

            })
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
                    name_homepage_text.setText("${name} ${lastName}")
                    point_txt.setText(point)
                }
            }

            override fun onFailure(call: Call<ResponseEntity>, t: Throwable) {

            }

        })

    }

}
