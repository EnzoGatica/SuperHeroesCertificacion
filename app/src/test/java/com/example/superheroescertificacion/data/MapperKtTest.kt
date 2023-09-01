package com.example.superheroescertificacion.data

import com.example.superheroescertificacion.data.remote.SuperHeroe
import com.example.superheroescertificacion.data.remote.SuperHeroeDetalle
import org.junit.Assert.*

import org.junit.Test

class MapperKtTest {

    @Test
    fun transformar() {

        //Given
        val unHeroe = SuperHeroe(1,"chapulin","Mexico","www.url.com","Super chipote",1980)

        //When
        val result = unHeroe.transformar()

        //then
        assertEquals(unHeroe.id, result.id)
        assertEquals(unHeroe.nombre, result.nombre)
        assertEquals(unHeroe.origen, result.origen)
        assertEquals(unHeroe.link, result.link)
        assertEquals(unHeroe.poder, result.poder)
        assertEquals(unHeroe.creacion, result.creacion)
    }

    @Test
    fun detailTransform() {
        //Given
        val unDetalle = SuperHeroeDetalle(1,"chapulin","Mexico","www.url.com","Super chipote",1980,"rojo,amarillo",true)

        //When
        val resultDetalle = unDetalle.detailTransform()

        //Then
        assertEquals(unDetalle.id, resultDetalle.id)
        assertEquals(unDetalle.nombre, resultDetalle.nombre)
        assertEquals(unDetalle.origen, resultDetalle.origen)
        assertEquals(unDetalle.link, resultDetalle.link)
        assertEquals(unDetalle.poder, resultDetalle.poder)
        assertEquals(unDetalle.creacion, resultDetalle.creacion)
        assertEquals(unDetalle.color, resultDetalle.color)
        assertEquals(unDetalle.traduccion, resultDetalle.traduccion)

    }
}