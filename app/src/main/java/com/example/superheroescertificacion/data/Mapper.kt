package com.example.superheroescertificacion.data

import com.example.superheroescertificacion.data.local.HeroeDetalleEntity
import com.example.superheroescertificacion.data.local.HeroeEntity
import com.example.superheroescertificacion.data.remote.SuperHeroe
import com.example.superheroescertificacion.data.remote.SuperHeroeDetalle

fun SuperHeroe.transformar(): HeroeEntity = HeroeEntity(
    this.id,
    this.nombre,
    this.origen,
    this.link,
    this.poder,
    this.creacion
)

fun SuperHeroeDetalle.detailTransform(): HeroeDetalleEntity = HeroeDetalleEntity(
    this.id,
    this.nombre,
    this.origen,
    this.link,
    this.poder,
    this.creacion,
    this.color,
    this.traduccion
)