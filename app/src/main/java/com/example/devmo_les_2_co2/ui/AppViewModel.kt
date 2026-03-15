package com.example.devmo_les_2_co2.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel containing the app data and methods to process the data
 */
class AppViewModel : ViewModel() {

    // App UI state
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    var userQuantity by mutableStateOf("")
        private set
    var userFactor by mutableStateOf("")
        private set
    var userCount by mutableStateOf("")
        private set
    var userScore: Double = 0.0


    init {
        resetGame()
    }

    fun resetGame() {
        _uiState.value = AppUiState()
        changeEmission(0.0, 0.0, "Default")
    }

    fun addEmission() {
        if (userScore != 0.0) {
            val tmp = "Emission: %.2f (qty: %.2f, fac: %.2f, cnt: %d, name: %s)\n"
            val qty = userQuantity.toDoubleOrNull() ?: 0.0
            val fac = userFactor.toDoubleOrNull() ?: 0.0
            val cnt =  userCount.toIntOrNull() ?: 0

            _uiState.update { currentState ->
                currentState.copy(
                    name = "Default",
                    currentQuantity = 0.0,
                    currentEmissionFactor = 0.0,
                    totalScore = currentState.totalScore.plus(userScore),
                    currentInfo = currentState.currentInfo + tmp.format(userScore, qty, fac, cnt, currentState.name)
                )
            }
        }
    }

    fun updateEmission() {
        val quantity = userQuantity.toDoubleOrNull() ?: 0.0
        val factor = userFactor.toDoubleOrNull() ?: 0.0
        val count = userCount.toDoubleOrNull() ?: 0.0
        userScore = quantity * factor * count
    }

    fun changeEmission(quantity: Double, factor: Double, name: String) {
        _uiState.update { currentState ->
            currentState.copy(
                name = name,
                currentQuantity = quantity,
                currentEmissionFactor = factor,
                totalScore = currentState.totalScore,
                currentInfo = currentState.currentInfo
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
