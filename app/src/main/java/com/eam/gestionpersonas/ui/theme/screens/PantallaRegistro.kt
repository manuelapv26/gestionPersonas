package com.eam.gestionpersonas.ui.theme.screens

@Composable
fun PantallaRegistro(viewModel: PersonaViewModel) {
    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Registro de Personas", color = Color.White)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
        }
    ) { paddingInterno ->
        Column(modifier = Modifier
            .padding(paddingInterno)
            .padding(16.dp)
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = { Text("Teléfono") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = edad,
                onValueChange = { edad = it },
                label = { Text("Edad") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = genero,
                onValueChange = { genero = it },
                label = { Text("Género (M/F)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = {
                    val nuevaPersona = Persona(nombre, telefono, edad, genero)
                    viewModel.agregarPersona(nuevaPersona)
                    mensaje = "Registro exitoso:\nNombre: $nombre\nTeléfono: $telefono"
                    nombre = ""
                    telefono = ""
                    edad = ""
                    genero = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar Persona")
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (mensaje.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Text(
                        text = mensaje,
                        modifier = Modifier.padding(12.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}