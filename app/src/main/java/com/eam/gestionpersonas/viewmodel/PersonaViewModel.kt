package com.eam.gestionpersonas.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.eam.gestionpersonas.data.model.Persona
import com.eam.gestionpersonas.data.repository.PersonaRepository

class PersonaViewModel : ViewModel() {

    var listaPersonas by mutableStateOf(
        PersonaRepository.listaPersonas.toList()
    )

        private set
    fun agregarPersona(persona: Persona) {
        PersonaRepository.listaPersonas.add(persona)
        listaPersonas = PersonaRepository.listaPersonas.toList()
    }
    fun eliminarPersona(persona: Persona) {
        PersonaRepository.listaPersonas.removeAll {
            it.telefono == persona.telefono
        }
        listaPersonas = PersonaRepository.listaPersonas.toList()
    }
    fun actualizarPersona(personaActualizada: Persona) {
        PersonaRepository.listaPersonas = PersonaRepository.listaPersonas.map {
            if (it.telefono == personaActualizada.telefono) personaActualizada else it
        }.toMutableList()
        listaPersonas = PersonaRepository.listaPersonas.toList()
    }
    fun buscarPorTelefono(telefono: String): Persona? {
        return PersonaRepository.listaPersonas.find { it.telefono == telefono }
    }
}
