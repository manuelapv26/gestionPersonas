package com.eam.gestionpersonas.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eam.gestionpersonas.R
import com.eam.gestionpersonas.data.model.Persona
import com.eam.gestionpersonas.viewmodel.PersonaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDetalle(
    navController: NavController,
    viewModel: PersonaViewModel,
    telefono: String
) {
    val persona = viewModel.buscarPorTelefono(telefono)
    var nombre by remember { mutableStateOf(persona?.nombre ?: "") }
    var telefono by remember { mutableStateOf(persona?.telefono ?: "") }
    var edad by remember { mutableStateOf(persona?.edad ?: "") }
    var genero by remember { mutableStateOf(persona?.genero ?: "") }
    val imagen = if (persona?.genero == "M") {
        R.drawable.hombre_user
    } else {
        R.drawable.mujer_user
    }
    var mostrarDialogo by remember { mutableStateOf(false) }
    var personaAEliminar by remember { mutableStateOf<Persona?>(null) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle Persona", color = Color.White)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
        }
    ) { paddingInterno ->
        Column(
            modifier = Modifier
                .padding(paddingInterno)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = imagen),
                contentDescription = "Avatar",
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") }
            )
            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = { Text("Teléfono") }
            )
            OutlinedTextField(
                value = edad,
                onValueChange = { edad = it },
                label = { Text("Edad") }
            )
            OutlinedTextField(
                value = genero,
                onValueChange = { genero = it },
                label = { Text("Género") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Button(
                    onClick = {
                        val personaActualizada = Persona(
                            nombre,
                            telefono,
                            edad,
                            genero

                        )
                        viewModel.actualizarPersona(
                            personaActualizada
                        )
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Actualizar")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        personaAEliminar = persona
                        mostrarDialogo = true

                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Eliminar")
                }
            }
        }
    }

    // Diálogo de confirmación
    if (mostrarDialogo && personaAEliminar != null) {
        AlertDialog(
            onDismissRequest = {
                mostrarDialogo = false
            },
            title = {
                Text("Confirmar eliminación")
            },
            text = {
                Text("¿Deseas eliminar a ${personaAEliminar?.nombre}?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.eliminarPersona(personaAEliminar!!)

                        navController.popBackStack()
                        mostrarDialogo = false
                        personaAEliminar = null

                    }
                ) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        mostrarDialogo = false
                        personaAEliminar = null

                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}