package com.example.devmo_les_2_co2.ui

data class AppUiState(
    val name: String = "Default",
    val currentQuantity: Double = 0.0,
    val currentEmissionFactor: Double = 0.0,
    val totalScore: Double = 0.0,
    // Chaine de caractères par défaut pour voir son emplacement
    val currentInfo: String = "I am here\n"
)
