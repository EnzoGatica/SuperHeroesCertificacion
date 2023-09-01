package com.example.superheroescertificacion.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

//https://y-mariocanedo.vercel.app/superheroes/
interface SuperHeroeAPI {

    @GET("superheroes/")
    suspend fun getDataHeroes(): Response<List<SuperHeroe>>

    @GET("superheroes/{id}")
    suspend fun getDetailHeroe(@Path("id") id: Int):Response<SuperHeroeDetalle>

}