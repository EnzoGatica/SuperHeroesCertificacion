package com.example.superheroescertificacion.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Heroes_table")
data class HeroeEntity (
        @PrimaryKey val id: Int,
        val nombre: String,
        val origen: String,
        val link: String,
        val poder: String,
        val creacion: Int
        )