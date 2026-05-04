package com.paoloesan.calculadoraprestamo.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.paoloesan.calculadoraprestamo.viewModels.CalculadoraViewModel
import com.paoloesan.calculadoraprestamo.screens.InicioScreen
import com.paoloesan.calculadoraprestamo.screens.ResultadoScreen

@Composable
fun Navigator() {
    val navController = rememberNavController()
    val viewModel: CalculadoraViewModel = viewModel()

    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") {
            InicioScreen(
                viewModel = viewModel
            ) {
                navController.navigate("resultado")
            }
        }
        composable("resultado") {
            ResultadoScreen(
                viewModel = viewModel
            ) {
                navController.popBackStack()
            }
        }
    }
}