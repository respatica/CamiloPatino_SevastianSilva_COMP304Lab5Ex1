package com.example.camilopatino_sevastiansilva_comp304lab5ex1.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.camilopatino_sevastiansilva_comp304lab5ex1.model.Landmarks

@Dao
interface LandmarksDao {
    @Query("SELECT * FROM landmarks")
    fun getAll(): List<Landmarks>

    @Query("SELECT * FROM landmarks WHERE landmarksID = :id")
    fun getLandmarkPositionById(id: Int): List<Landmarks>

    @Query("SELECT * FROM landmarks WHERE type = :type")
    fun getLandmarkByType(type: String): List<Landmarks>

    @Insert
    fun insertAll(vararg landmarks: Landmarks)

}