package com.example.camilopatino_sevastiansilva_comp304lab5ex1.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.camilopatino_sevastiansilva_comp304lab5ex1.R
import com.example.camilopatino_sevastiansilva_comp304lab5ex1.adapters.BuildingAdapter

class BuildingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buildings)

        val recyclerView = findViewById<RecyclerView>(R.id.buildings_list_view)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        var buildingsArray = arrayOf<String>()

        var buildingType = intent.getStringExtra("type")

        when(buildingType){
            "Old Buildings"-> {
                val oldBuildings = resources.getStringArray(R.array.old_buildings)
                buildingsArray = oldBuildings
            }
            "Museums"-> {
                buildingsArray = resources.getStringArray(R.array.museums)
            }
            "Stadiums" -> {
                buildingsArray = resources.getStringArray(R.array.stadiums)
            }
            "Attractions"-> {
                buildingsArray = resources.getStringArray(R.array.attractions)
            }
            else -> {}
        }

        val adapter = BuildingAdapter(buildingsArray)
        recyclerView.adapter = adapter

    }
}