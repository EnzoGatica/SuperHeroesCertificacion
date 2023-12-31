package com.example.superheroescertificacion.data.remote

import com.google.gson.annotations.SerializedName

/*
{
  "id": 1,
  "nombre": "BATMAN",
  "origen": "Gotham City",
  "imagenLink": "https://cloudfront-us-east-1.images.arcpublishing.com/metroworldnews/OMMXHLDAABDBVHRUH2FPDLVZCY.jpg",
  "poder": "No tiene super poderes, recurre a su intelecto",
  "año_creacion": 1939,
  "color": "negro",
  "traduccion": "true"
}
 */

data class SuperHeroeDetalle(
    val id: Int,
    val nombre: String,
    val origen: String,
    @SerializedName("imagenLink") val link: String,
    val poder: String,
    @SerializedName("año_creacion") val creacion: Int,
    val color: String,
    val traduccion: Boolean
)
