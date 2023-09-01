package com.example.superheroescertificacion.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HeroeDao {

    //Listado todo los heroes
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertALLHeroe(heroeList: List<HeroeEntity>)

    @Query("SELECT * FROM heroes_table ORDER BY id ASC")
    fun getALLHeroe(): LiveData<List<HeroeEntity>>

    //Detalle de heroe

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailHeroe(heroeDetalleEntity: HeroeDetalleEntity)

    @Query("SELECT * FROM detalle_table WHERE id = :id")
    fun getOneHeroID(id:Int): LiveData<HeroeDetalleEntity>

}