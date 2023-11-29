package com.example.camilopatino_sevastiansilva_comp304lab5ex1.repo

import android.util.Log
import com.example.camilopatino_sevastiansilva_comp304lab5ex1.AppDatabase
import com.example.camilopatino_sevastiansilva_comp304lab5ex1.model.Landmarks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.UnknownHostException

class LandmarkRepo (private val database: AppDatabase){
    suspend fun getLandmaks(): List<Landmarks>? {
        return withContext(Dispatchers.IO) {
            try {
                database.landmaksDao().getAll()
            }
            catch (ex: UnknownHostException) {
                return@withContext null
            }
            catch (ex: Exception) {
                Log.e("Repo", ex.message.toString())
                return@withContext null
            }
        }
    }


    suspend fun insertAll(landmark: Landmarks) {
        withContext(Dispatchers.IO){
            try {
                database.landmaksDao().insertAll(landmark)
            }
            catch (ex: Exception) {
                Log.e("Repo", ex.message.toString())
            }
        }
    }
}