package com.example.superheroescertificacion.data.remote

import com.google.gson.annotations.SerializedName

/*
{
    "id": 1,
    "nombre": "BATMAN",
    "origen": "Gotham City",
    "imagenLink": "https://cloudfront-us-east-1.images.arcpublishing.com/metroworldnews/OMMXHLDAABDBVHRUH2FPDLVZCY.jpg",
    "poder": "No tiene super poderes, recurre a su intelecto",
    "Año_creacion": 1939
},
*/
data class SuperHeroe (
    val id: Int,
    val nombre: String,
    val origen: String,
    @SerializedName("imagenLink") val link: String,
    val poder: String,
    @SerializedName("Año_creacion") val creacion: Int
)