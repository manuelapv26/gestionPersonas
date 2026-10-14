package com.eam.gestionpersonas.ui.theme.screens

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
    ) {
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