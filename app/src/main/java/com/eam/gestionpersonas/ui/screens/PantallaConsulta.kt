package com.eam.gestionpersonas.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eam.gestionpersonas.data.model.Persona
import com.eam.gestionpersonas.ui.components.ItemPersona
import com.eam.gestionpersonas.viewmodel.PersonaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaConsulta(
    navController: NavController,
    viewModel: PersonaViewModel
) {
    var telefono by remember { mutableStateOf("") }
    var persona by remember { mutableStateOf<Persona?>(null) }
    // Estado para diálogo
    var mostrarDialogo by remember { mutableStateOf(false) }
    var personaAEliminar by remember { mutableStateOf<Persona?>(null) }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Consulta de Personas", color = Color.White)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = { Text("Teléfono") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    persona = viewModel.buscarPorTelefono(telefono)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Consultar")
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Mostrar resultado
            persona?.let {
                ItemPersona(
                    persona = it,
                    onClick = {
                        navController.navigate("detalle/${it.telefono}")
                    },
                    onDelete = {
                        personaAEliminar = it
                        mostrarDialogo = true

                    }
                )
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

                        persona = null
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