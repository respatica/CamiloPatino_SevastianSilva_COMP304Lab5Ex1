package com.example.camilopatino_sevastiansilva_comp304lab5ex1

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.camilopatino_sevastiansilva_comp304lab5ex1.dao.LandmarksDao
import com.example.camilopatino_sevastiansilva_comp304lab5ex1.model.Landmarks

@Database(entities = [Landmarks::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun landmaksDao(): LandmarksDao

}