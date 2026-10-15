package com.eam.gestionpersonas.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPrincipal(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestión de Personas", color = Color.White)
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
            Button(onClick = { navController.navigate("registro") },modifier = Modifier.fillMaxWidth()) {
                Text("Registro")

            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = { navController.navigate("consulta") },modifier = Modifier.fillMaxWidth()) {
                Text("Consulta individual")
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = { navController.navigate("lista") },modifier = Modifier.fillMaxWidth()) {
                Text("Lista de personas")
            }
        }
    }
}