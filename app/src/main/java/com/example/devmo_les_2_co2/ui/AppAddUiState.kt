package com.example.devmo_les_2_co2.ui

/**
 * Data class that represents the app UI state
 */
data class AppUiState(
    val currentQuantity: String = "0.0",
    val currentEmissionFactor: String = "0.0",
    val currentCount: String = "1",
    val score: Double = 0.0,
    val currentInfo: String = "I am here\n"
)
