package com.example.camilopatino_sevastiansilva_comp304lab5ex1.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.camilopatino_sevastiansilva_comp304lab5ex1.R
import com.example.camilopatino_sevastiansilva_comp304lab5ex1.view.MainActivity
import com.example.camilopatino_sevastiansilva_comp304lab5ex1.view.MapActivity
import com.example.camilopatino_sevastiansilva_comp304lab5ex1.view.MapInteracionActivity

class BuildingAdapter(private var buildingData: Array<String>): RecyclerView.Adapter<BuildingAdapter.BuildingViewHolder>() {

    class BuildingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val buildingText: TextView
        init {
            buildingText = view.findViewById(R.id.building_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.building_button, parent, false)
        return BuildingViewHolder(view)
    }

    override fun getItemCount(): Int = buildingData.size


    override fun onBindViewHolder(holder: BuildingViewHolder, position: Int) {
        holder.buildingText.text = buildingData[position]
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, MapInteracionActivity::class.java)
            intent.putExtra("building", buildingData[position])
            it.context.startActivity(intent)
        }
    }
}