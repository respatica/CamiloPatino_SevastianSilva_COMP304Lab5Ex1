package com.example.camilopatino_sevastiansilva_comp304lab5ex1.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.camilopatino_sevastiansilva_comp304lab5ex1.R

class BuildingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buildings)

        val recyclerView = findViewById<RecyclerView>(R.id.buildings_list_view)


    }
}