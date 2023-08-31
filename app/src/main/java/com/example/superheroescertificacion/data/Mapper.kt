package com.example.superheroescertificacion.data

import com.example.superheroescertificacion.data.local.HeroeEntity
import com.example.superheroescertificacion.data.remote.SuperHeroe

fun SuperHeroe.transformar(): HeroeEntity = HeroeEntity(
    this.id,
    this.nonbre,
    this.origen,
    this.link,
    this.poder,
    this.creacion
)