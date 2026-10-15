package com.eam.gestionpersonas.data.repository

import com.eam.gestionpersonas.data.model.Persona

object PersonaRepository {
    var listaPersonas = mutableListOf(
        Persona("Carlos", "3001234567", "25", "M"),
        Persona("Ana", "3019876543", "22","F")
    )
}