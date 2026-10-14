package com.eam.gestionpersonas.ui.theme.navigation

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