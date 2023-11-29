package com.example.camilopatino_sevastiansilva_comp304lab5ex1.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.camilopatino_sevastiansilva_comp304lab5ex1.R
import com.example.camilopatino_sevastiansilva_comp304lab5ex1.view.BuildingsActivity

class BuildingTypeAdapter(private var types: Array<String>) :  RecyclerView.Adapter<BuildingTypeAdapter.BuildingTypeViewHolder>(){

    class BuildingTypeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            textView = view.findViewById(R.id.building_type_text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingTypeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.building_type, parent, false)
        return BuildingTypeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return types.size
    }

    override fun onBindViewHolder(holder: BuildingTypeViewHolder, position: Int) {
        holder.textView.text = types[position]
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, BuildingsActivity::class.java)
            intent.putExtra("type", types[position])
            it.context.startActivity(intent)
        }
    }

}