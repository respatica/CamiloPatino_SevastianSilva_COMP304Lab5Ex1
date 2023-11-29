package com.example.camilopatino_sevastiansilva_comp304lab5ex1.model

import androidx.room.ColumnInfo
import androidx.room.Entity

import androidx.room.PrimaryKey
@Entity
data class Landmarks (
    @PrimaryKey val landmarksID: Int,
    @ColumnInfo(name = "name") val Name: String,
    @ColumnInfo(name = "latitude") val Latitude: Double,
    @ColumnInfo(name = "longitude") val Longitude: Double,
    @ColumnInfo(name = "type") val type: String?
    )