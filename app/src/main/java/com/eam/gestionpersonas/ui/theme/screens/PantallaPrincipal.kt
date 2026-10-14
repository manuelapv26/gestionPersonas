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
    ) {
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Button(onClick = { navController.navigate("registro") },modifier = Modifier.fillM
                    axWidth()) {
                Text("Registro")

            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = { navController.navigate("consulta") },modifier = Modifier.fillM
                    axWidth()) {
                Text("Consulta individual")
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = { navController.navigate("lista") },modifier = Modifier.fillMaxW
                    idth()) {
                Text("Lista de personas")
            }
        }
    }
}