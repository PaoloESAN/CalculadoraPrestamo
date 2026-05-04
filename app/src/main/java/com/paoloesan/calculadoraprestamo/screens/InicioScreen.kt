package com.paoloesan.calculadoraprestamo.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paoloesan.calculadoraprestamo.viewModels.CalculadoraViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(viewModel: CalculadoraViewModel, onCalculate: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calculadora de Préstamo") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = viewModel.monto,
                onValueChange = { viewModel.monto = it },
                label = { Text("Monto del préstamo") },
                prefix = { Text("$ ") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = viewModel.plazo,
                    onValueChange = { viewModel.plazo = it },
                    label = { Text("Plazo") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = viewModel.esAnual,
                            onClick = { viewModel.esAnual = true }
                        )
                        Text("Años")
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = !viewModel.esAnual,
                            onClick = { viewModel.esAnual = false }
                        )
                        Text("Meses")
                    }
                }
            }

            OutlinedTextField(
                value = viewModel.tasa,
                onValueChange = { viewModel.tasa = it },
                label = { Text("Tasa de interés anual (%)") },
                suffix = { Text("%") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    viewModel.calcular()
                    onCalculate()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = viewModel.puedeCalcular(),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Calcular", fontSize = 18.sp, modifier = Modifier.padding(8.dp))
            }
        }
    }
}