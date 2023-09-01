package com.example.superheroescertificacion.vistas

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.superheroescertificacion.data.Repositorio
import com.example.superheroescertificacion.data.local.HeroeDataBase
import com.example.superheroescertificacion.data.remote.SuperHeroeRetrofit
import kotlinx.coroutines.launch

class HeroeViewModel(application: Application): AndroidViewModel(application) {

    private lateinit var repositorio: Repositorio

    init {
        val api = SuperHeroeRetrofit.getRetroFitSuperHeroe()
        val database = HeroeDataBase.getDatabase(application).getHeroeDao()
        repositorio = Repositorio(api,database)
    }

    //List Heroes
    fun heroesLiveData() = repositorio.obtenerHeroesEntity()

    fun getAllHeroes() = viewModelScope.launch {
        repositorio.cargarHeroes()
    }

    //Detalle Heroe
    fun detalleLiveData(id:Int) = repositorio.cargarDetalleID(id)

    fun getDetalleHeroe(id: Int) = viewModelScope.launch {
        repositorio.cargarDetalleHeroe(id)
    }

}