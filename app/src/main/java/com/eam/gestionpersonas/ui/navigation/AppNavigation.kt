package com.eam.gestionpersonas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eam.gestionpersonas.ui.screens.PantallaConsulta
import com.eam.gestionpersonas.ui.screens.PantallaDetalle
import com.eam.gestionpersonas.ui.screens.PantallaLista
import com.eam.gestionpersonas.ui.screens.PantallaPrincipal
import com.eam.gestionpersonas.ui.screens.PantallaRegistro
import com.eam.gestionpersonas.viewmodel.PersonaViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: PersonaViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = "principal"
    ) {
        composable("principal") {
            PantallaPrincipal(navController)
        }
        composable("registro") {
            PantallaRegistro(viewModel)
        }
        composable("lista") {
            PantallaLista(navController, viewModel)
        }
        composable("consulta") {
            PantallaConsulta(navController, viewModel)
        }
        composable("detalle/{telefono}") { backStackEntry ->
            val telefono = backStackEntry.arguments?.getString("telefono") ?: ""
            PantallaDetalle(navController, viewModel, telefono)
        }
    }
}