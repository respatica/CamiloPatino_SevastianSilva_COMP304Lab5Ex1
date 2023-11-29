package com.example.camilopatino_sevastiansilva_comp304lab5ex1.view

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.camilopatino_sevastiansilva_comp304lab5ex1.R
import com.example.camilopatino_sevastiansilva_comp304lab5ex1.adapters.BuildingTypeAdapter

import com.example.camilopatino_sevastiansilva_comp304lab5ex1.databinding.ActivityMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.Locale

class MapInteracionActivity : AppCompatActivity() , OnMapReadyCallback {
    private lateinit var binding: ActivityMapsBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var currentLocation: Location? = null
    private var map: GoogleMap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this)
        
        val prefs = getSharedPreferences("MAPS_INFO", MODE_PRIVATE)
        val isMapsPermissionGranted = prefs.getBoolean("IS_MAP_PERMISSION_GRANTED", false)
        val isFineLocationPermissionGranted = prefs.getBoolean("IS_FINE_PERMISSION_GRANTED", false)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_view)  as SupportMapFragment
        mapFragment.getMapAsync(this)

        if (isMapsPermissionGranted) {
            if(!isFineLocationPermissionGranted) {
                locationPermissionRequest.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                )
            }
        } else {
            locationPermissionRequest.launch(
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            )
        }
        if (isMapsPermissionGranted) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null)
        }

        binding.normalMapType.setOnClickListener {
            map?.mapType = GoogleMap.MAP_TYPE_NORMAL
        }

        binding.satelliteMapType.setOnClickListener {
            map?.mapType = GoogleMap.MAP_TYPE_SATELLITE
        }


    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            currentLocation = locationResult.lastLocation
            binding.locationTextView.text =
                "Latitude = ${currentLocation?.latitude}, Longitude = ${currentLocation?.longitude}"

            val latLng = LatLng(locationResult.lastLocation?.latitude!!, locationResult.lastLocation?.longitude!!)
            map?.addMarker(MarkerOptions().position(latLng).title("Current Location"))
            map?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(latLng, 15f, 0f, 0f)))

            val geocoder = Geocoder(this@MapInteracionActivity, Locale.getDefault())
            if (locationResult.lastLocation != null) {
                val result = geocoder.getFromLocation(
                    locationResult.lastLocation!!.latitude,
                    locationResult.lastLocation!!.longitude,
                    1
                )

                binding.locationTextView.text = result?.get(0)?.countryName ?: "USA"
            }

            val result = geocoder.getFromLocationName("Centennial College", 1)
            binding.locationTextView.text = "${result?.get(0)?.latitude}, ${result?.get(0)?.longitude}"
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
    private val locationRequest = LocationRequest
        .Builder(Priority.PRIORITY_HIGH_ACCURACY, 60).build()
    private fun updateSharedPrefs(isGranted: Boolean, isFineLocation: Boolean) {
        val prefs = getSharedPreferences("MAPS_INFO", Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putBoolean("IS_MAP_PERMISSION_GRANTED", isGranted)
            putBoolean("IS_FINE_PERMISSION_GRANTED", isFineLocation)
            apply()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val lat = 43.7851271
        val lng = -79.2282955

        val latLng = LatLng(lat, lng)
        map?.addMarker(MarkerOptions().position(latLng).title("Centennial College"))
        map?.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(latLng, 20.0f, 0.0f, 0.0f)))

        map?.isBuildingsEnabled = true
        map?.isIndoorEnabled = true
    }
}