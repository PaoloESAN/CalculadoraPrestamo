package com.paoloesan.calculadoraprestamo.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.pow

class CalculadoraViewModel : ViewModel() {
    var monto by mutableStateOf("")
    var plazo by mutableStateOf("")
    var tasa by mutableStateOf("")
    var esAnual by mutableStateOf(true)

    var cuotaMensual by mutableDoubleStateOf(0.0)
    var interesTotal by mutableDoubleStateOf(0.0)
    var montoPrestamo by mutableDoubleStateOf(0.0)

    fun calcular() {
        val montoNumerico = monto.toDoubleOrNull() ?: 0.0
        val plazoNumerico = plazo.toDoubleOrNull() ?: 0.0
        val tasaAnual = tasa.toDoubleOrNull() ?: 0.0

        if (montoNumerico <= 0 || plazoNumerico <= 0) return

        val meses = if (esAnual) plazoNumerico * 12 else plazoNumerico
        val tasaMensual = tasaAnual / 12 / 100

        montoPrestamo = montoNumerico

        if (tasaMensual == 0.0) {
            cuotaMensual = montoNumerico / meses
        } else {
            val factor = (1 + tasaMensual).pow(meses)
            cuotaMensual = montoNumerico * (tasaMensual * factor) / (factor - 1)
        }

        val totalPagado = cuotaMensual * meses
        interesTotal = totalPagado - montoNumerico
    }

    fun puedeCalcular(): Boolean {
        return monto.isNotBlank() && plazo.isNotBlank() && tasa.isNotBlank() &&
               monto.toDoubleOrNull() != null && plazo.toDoubleOrNull() != null && tasa.toDoubleOrNull() != null
    }
}