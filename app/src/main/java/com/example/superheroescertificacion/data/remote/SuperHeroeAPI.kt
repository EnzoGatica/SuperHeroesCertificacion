package com.example.superheroescertificacion.data.remote

import retrofit2.Response
import retrofit2.http.GET
//https://y-mariocanedo.vercel.app/superheroes/
interface SuperHeroeAPI {

    @GET("superheroes/")
    suspend fun getDataHeroes(): Response<List<SuperHeroe>>

}