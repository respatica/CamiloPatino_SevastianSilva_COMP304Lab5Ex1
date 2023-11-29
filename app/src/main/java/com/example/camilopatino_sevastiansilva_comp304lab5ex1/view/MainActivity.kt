package com.example.camilopatino_sevastiansilva_comp304lab5ex1.view

import android.content.Context
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.camilopatino_sevastiansilva_comp304lab5ex1.databinding.ActivityMainBinding
import com.example.camilopatino_sevastiansilva_comp304lab5ex1.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback

class MainActivity : AppCompatActivity() , OnMapReadyCallback {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var currentLocation: Location? = null
    private var map: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        val prefs = getSharedPreferences("MAPS_INFO", Context.MODE_PRIVATE)
        val isMapsPermissionGranted = prefs.getBoolean("IS_MAP_PERMISSION_GRANTED", false)
        val isFineLocationPermissionGranted = prefs.getBoolean("IS_FINE_PERMISSION_GRANTED", false)



        if (isMapsPermissionGranted) {
            if(!isFineLocationPermissionGranted) {
                locationPermissionRequest.launch(
                    arrayOf(
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    )
                )
            }
        } else {
            locationPermissionRequest.launch(
                arrayOf(
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            )
        }
    }
    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(android.Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                updateSharedPrefs(isGranted = true, false)
            }
            permissions.getOrDefault(android.Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                updateSharedPrefs(isGranted = true, true)
            } else -> {
            updateSharedPrefs(isGranted = false, isFineLocation = false)
        }
        }
    }

    private fun updateSharedPrefs(isGranted: Boolean, isFineLocation: Boolean) {
        val prefs = getSharedPreferences("MAPS_INFO", Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putBoolean("IS_MAP_PERMISSION_GRANTED", isGranted)
            putBoolean("IS_FINE_PERMISSION_GRANTED", isFineLocation)
            apply()
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        TODO("Not yet implemented")
    }
}