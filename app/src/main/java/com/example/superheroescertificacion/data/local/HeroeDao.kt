package com.example.superheroescertificacion.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HeroeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertALLHeroe(heroeList: List<HeroeEntity>)

    @Query("SELECT * FROM heroes_table ORDER BY id ASC")
    fun getALLHeroe(): LiveData<List<HeroeEntity>>

}