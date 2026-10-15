package com.eam.gestionpersonas.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eam.gestionpersonas.R
import com.eam.gestionpersonas.data.model.Persona

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