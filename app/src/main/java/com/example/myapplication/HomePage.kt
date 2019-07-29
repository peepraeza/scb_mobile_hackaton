package com.example.myapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.util.Log.d
import android.util.Log.i
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.models.LocationModelBody
import com.example.myapplication.models.ProfileParcel
import com.example.myapplication.models.ResponseEntity
import com.example.myapplication.services.ApiManager
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.home_page.*
import kotlinx.android.synthetic.main.location_page.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("DEPRECATION")
class HomePage : AppCompatActivity(), com.google.android.gms.location.LocationListener {
    private var REQUEST_LOCATION_CODE = 101
    private var mGoogleApiClient: GoogleApiClient? = null
    private var mLocation: Location? = null
    private var mLocationRequest: LocationRequest? = null
    private val UPDATE_INTERVAL = (1000).toLong()  /* 10 secs */
    private val FASTEST_INTERVAL: Long = 2000 /* 2 sec */

    var id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)
        buildGoogleApiClient()
        val userProfile = intent.getParcelableExtra<ProfileParcel>("userProfile")

        var(id, name, lastName, phoneNumber, gender, alias, point)= userProfile

        val prefs =
            getSharedPreferences("keepUserData", MODE_PRIVATE).edit()
        prefs.putString("citizenId", id.toString())
        prefs.putString("fullname", "${name} ${lastName}")
        prefs.putString("alias", alias)
        prefs.apply()


        i("Pee", "ID JA : "+ id)
//
        name_homepage_text.setText("${name} ${lastName}" )
        point_txt.setText(point)

        reward_btn.setOnClickListener{
            val intent = Intent(this@HomePage, RewardPage::class.java)
            val userProfile = ProfileParcel(id, name, lastName, phoneNumber, gender, alias, point.toString())
            intent.putExtra("userProfile", userProfile)
            startActivity(intent)
        }

        cardless_btn.setOnClickListener{
            Log.i("Pee", "On Click Ja")

            var prefs = getSharedPreferences("keepUserData", Context.MODE_PRIVATE)

            val citizenId = prefs.getString("citizenId", null)
            i("Pee", "id from pref :"+ citizenId)

//            i("Pee", "ID CARDLESS: "+id )
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    //Location Permission already granted
                    Log.d("Pee", "Come first if")
                    getLocation(id.toString());
                } else {
                    //Request Location Permission
                    Log.d("Pee", "checkLocationPermission")
                    checkLocationPermission()
                }
            } else {
                Log.i("Pee", "Come Else")
                getLocation(id.toString());
            }
            val intent = Intent(this@HomePage, DashboardActivity::class.java)
            val userProfile = ProfileParcel(id, name, lastName, phoneNumber, gender, alias, point.toString())
            intent.putExtra("userProfile", userProfile)
            startActivity(intent)

        }
    }
    override fun onLocationChanged(location: Location?) {
        // You can now create a LatLng Object for use with maps
        // val latLng = LatLng(location.latitude, location.longitude)
    }



    @SuppressLint("MissingPermission")
    private fun getLocation(id: String) {
        i("Pee", "getLocation")
        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        Log.i("Pee", "mLocation")
//        println(mLocation)
        if (mLocation == null) {

            startLocationUpdates();
        }
        if (mLocation != null) {

            val lat = mLocation!!.latitude
            val long = mLocation!!.longitude
            val prefs =
                getSharedPreferences("keepLatLong", MODE_PRIVATE).edit()
            prefs.putString("Lat", lat.toString())
            prefs.putString("Long", long.toString())
            prefs.apply()
            Log.i("Pee", "Latitude : " + mLocation!!.latitude.toString())
            Log.i("Pee", "Longitude : " + mLocation!!.longitude.toString())

            i("Pee", "citizenId : "+id)

        } else {
            Toast.makeText(this, "Location not Detected", Toast.LENGTH_SHORT).show();
        }
    }

    private fun startLocationUpdates() {
        // Create the location request
        mLocationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(UPDATE_INTERVAL)
            .setFastestInterval(FASTEST_INTERVAL)
        // Request location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this)
    }

    @Synchronized
    private fun buildGoogleApiClient() {
        Log.d("Pee", "buildGoogleApiClient")
        mGoogleApiClient = GoogleApiClient.Builder(this)
            .addApi(LocationServices.API)
            .build()

        mGoogleApiClient!!.connect()
    }

    private fun checkGPSEnabled(): Boolean {
        if (!isLocationEnabled())
            showAlert()
        return isLocationEnabled()
    }

    private fun showAlert() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Enable Location")
            .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " + "use this app")
            .setPositiveButton("Location Settings") { paramDialogInterface, paramInt ->
                val myIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(myIntent)
            }
            .setNegativeButton("Cancel") { paramDialogInterface, paramInt -> }
        dialog.show()
    }

    private fun isLocationEnabled(): Boolean {
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager!!.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER)
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                AlertDialog.Builder(this)
                    .setTitle("Location Permission Needed")
                    .setMessage("This app needs the Location permission, please accept to use location functionality")
                    .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_CODE)
                    })
                    .create()
                    .show()

            } else ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_LOCATION_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "permission granted", Toast.LENGTH_LONG).show()
                    }
                } else {
                    // permission denied, boo! Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }

    override fun onStart() {
        super.onStart()
        mGoogleApiClient?.connect()
    }

    override fun onStop() {
        super.onStop()
        if (mGoogleApiClient!!.isConnected()) {
            mGoogleApiClient!!.disconnect()
        }
    }

    override fun onBackPressed() {

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
