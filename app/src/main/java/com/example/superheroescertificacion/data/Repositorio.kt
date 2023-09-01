package com.example.superheroescertificacion.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.superheroescertificacion.data.local.HeroeDao
import com.example.superheroescertificacion.data.local.HeroeDetalleEntity
import com.example.superheroescertificacion.data.local.HeroeEntity
import com.example.superheroescertificacion.data.remote.SuperHeroeAPI
import com.example.superheroescertificacion.data.remote.SuperHeroeDetalle

class Repositorio(private val superHeroeAPI: SuperHeroeAPI,private val heroeDao: HeroeDao) {

    //Listado Heroes

    fun obtenerHeroesEntity(): LiveData<List<HeroeEntity>> = heroeDao.getALLHeroe()

    suspend fun cargarHeroes(){
        try {
            val respuesta = superHeroeAPI.getDataHeroes()
            if (respuesta.isSuccessful){
                val resp = respuesta.body()
                resp?.let {
                    val heroeEntity = it.map { it.transformar() }
                    heroeDao.insertALLHeroe(heroeEntity)
                }
            }else{
                Log.e("repositorio", respuesta.errorBody().toString())
            }

        }
        catch(exception: Exception){
            Log.e("catch", exception.toString())
        }

    }

    //Detalle Heroe

    fun cargarDetalleID(id:Int):LiveData<HeroeDetalleEntity> = heroeDao.getOneHeroID(id)

    suspend fun cargarDetalleHeroe(id: Int){
        try {
            val respuesta = superHeroeAPI.getDetailHeroe(id)
            if (respuesta.isSuccessful){
                val resp = respuesta.body()
                resp?.let {
                    val heroDetalle = it.detailTransform()
                    heroeDao.insertDetailHeroe(heroDetalle)
                }
            }else{
                Log.e("repositorio", respuesta.errorBody().toString())
            }
        }catch(exception: Exception){
            Log.e("catch", exception.toString())
        }
    }

}