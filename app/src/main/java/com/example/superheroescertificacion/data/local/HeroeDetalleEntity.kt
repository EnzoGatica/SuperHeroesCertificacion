package com.example.superheroescertificacion.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detalle_table")
data class HeroeDetalleEntity (
    @PrimaryKey val id: Int,
    val nombre: String,
    val origen: String,
    val link: String,
    val poder: String,
    val creacion: Int,
    val color: String,
    val traduccion: Boolean
        )