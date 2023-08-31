package com.example.superheroescertificacion.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//https://y-mariocanedo.vercel.app/superheroes/
class SuperHeroeRetrofit {

    companion object{

        private const val BASE_URL = "https://y-mariocanedo.vercel.app/"

        fun getRetroFitSuperHeroe(): SuperHeroeAPI{

            val mRetrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return mRetrofit.create(SuperHeroeAPI::class.java)

        }
    }

}