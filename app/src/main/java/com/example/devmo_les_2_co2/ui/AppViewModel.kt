package com.example.devmo_les_2_co2.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import androidx.compose.runtime.mutableDoubleStateOf


class AppViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    var userQuantity by mutableStateOf("")
        private set
    var userFactor by mutableStateOf("")
        private set
    var userCount by mutableStateOf("")
        private set
    var userScore by mutableDoubleStateOf(0.0)
        private set


    init {
        resetGame()
    }

    fun resetGame() {
        _uiState.value = AppUiState()
        // L'objectif serait de mettre l'émission la plus utilisée par défaut
        changeEmission(0.0, 0.0, "Nouvelle émission")
    }

    fun addEmission() {
        updateEmission()
        if (userScore != 0.0) {
            val tmp = "Emission: %.2f (qty: %.2f, fac: %.2f, cnt: %d, name: %s)\n"
            val qty = userQuantity.toDoubleOrNull() ?: 0.0
            val fac = userFactor.toDoubleOrNull() ?: 0.0
            val cnt =  userCount.toIntOrNull() ?: 0

            _uiState.update { currentState ->
                currentState.copy(
                    totalScore = currentState.totalScore + userScore,
                    currentInfo = currentState.currentInfo + tmp.format(userScore, qty, fac, cnt, currentState.name)
                )
            }
            userCount = "0"
        }
    }

    fun updateEmission() {
        val quantity = userQuantity.toDoubleOrNull() ?: 0.0
        val factor = userFactor.toDoubleOrNull() ?: 0.0
        val count = userCount.toDoubleOrNull() ?: 1.0
        userScore = quantity * factor * count
    }

    fun changeEmission(quantity: Double, factor: Double, name: String) {
        _uiState.update { currentState ->
            currentState.copy(
                name = name,
                currentQuantity = quantity,
                currentEmissionFactor = factor
            )
        }
        userQuantity = quantity.toString()
        userFactor = factor.toString()
        userCount = "1"
        updateEmission()
    }

    fun updateQuantity(quantity: String){
        userQuantity = quantity.replace(",", ".")
        updateEmission()
    }

    fun updateFactor(factor: String){
        userFactor = factor.replace(",", ".")
        updateEmission()
    }

    fun updateCount(count: String){
        userCount = count.replace(",", ".")
        updateEmission()
    }
}
