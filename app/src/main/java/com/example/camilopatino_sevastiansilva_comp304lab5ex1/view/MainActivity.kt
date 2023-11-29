package com.example.camilopatino_sevastiansilva_comp304lab5ex1.view

import android.content.Context
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.camilopatino_sevastiansilva_comp304lab5ex1.databinding.ActivityMainBinding
import com.example.camilopatino_sevastiansilva_comp304lab5ex1.R
import com.example.camilopatino_sevastiansilva_comp304lab5ex1.adapters.BuildingTypeAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        val recyclerView = findViewById<RecyclerView>(R.id.building_types_view)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val types = resources.getStringArray(R.array.building_types)

        val adapter = BuildingTypeAdapter(types)
        recyclerView.adapter = adapter

    }




}