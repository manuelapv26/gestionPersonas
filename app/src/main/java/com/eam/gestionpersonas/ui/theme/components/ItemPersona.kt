package com.eam.gestionpersonas.ui.theme.components

@Composable
fun ItemPersona(
    persona: Persona,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    val imagen = if (persona.genero == "M") {
        R.drawable.hombre_user
    } else {
        R.drawable.mujer_user
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = imagen),
                contentDescription = "Avatar",
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Nombre: ${persona.nombre}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = "Teléfono: ${persona.telefono}")
                Text(text = "Edad: ${persona.edad}")
            }
// Acción eliminar
            Text(
                text = "Eliminar",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.clickable {
                    onDelete()
                }
            )
        }
    }
}