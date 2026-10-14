package com.eam.gestionpersonas.ui.theme.screens

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