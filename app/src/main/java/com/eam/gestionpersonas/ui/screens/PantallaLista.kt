package com.eam.gestionpersonas.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eam.gestionpersonas.data.model.Persona
import com.eam.gestionpersonas.ui.components.ItemPersona
import com.eam.gestionpersonas.viewmodel.PersonaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaLista(
    navController: NavController,
    viewModel: PersonaViewModel
) {
    val context = LocalContext.current
    var mostrarDialogo by remember { mutableStateOf(false) }
    var personaAEliminar by remember { mutableStateOf<Persona?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Personas", color = Color.White)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
        }
    ) { paddingInterno ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingInterno)
                .padding(16.dp)
        ) {
            item {
                Text("Lista Personas!")
                Spacer(modifier = Modifier.height(16.dp))
            }
            items(viewModel.listaPersonas, key = { it.telefono }) { persona ->
                ItemPersona(
                    persona = persona,
                    onClick = {
                        println("Click en: ${persona.nombre}")

                        Toast.makeText(
                            context,

                            "Click en: ${persona.nombre}",
                            Toast.LENGTH_SHORT

                        ).show()

                        navController.navigate("detalle/${persona.telefono}")

                    },
                    onDelete = {
                    // viewModel.eliminarPersona(persona)
                        personaAEliminar = persona
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