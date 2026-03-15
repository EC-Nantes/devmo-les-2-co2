package com.example.devmo_les_2_co2.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    var userQuantity by mutableStateOf("")
        private set
    var userFactor by mutableStateOf("")
        private set
    var userCount by mutableStateOf("")
        private set
    
    // Changement : userScore est maintenant un état observé par Compose
    var userScore by mutableStateOf(0.0)
        private set


    init {
        resetGame()
    }

    fun resetGame() {
        _uiState.value = AppUiState()
        changeEmission(0.0, 0.0, "Nouvelle émission")
    }

    fun addEmission() {
        if (userScore != 0.0) {
            val tmp = "Ajout : %.2f kg (q: %s, f: %s, n: %s)\n"
            _uiState.update { currentState ->
                currentState.copy(
                    totalScore = currentState.totalScore + userScore,
                    currentInfo = currentState.currentInfo + tmp.format(userScore, userQuantity, userFactor, userCount)
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
        userQuantity = if (quantity == 0.0) "" else quantity.toString()
        userFactor = if (factor == 0.0) "" else factor.toString()
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
