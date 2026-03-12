package com.example.devmo_les_2_co2.ui

/**
 * Data class that represents the app UI state
 */
data class AppUiState(
    val current_qty: Double = 0.0,
    val current_emission_factor: Double = 0.0,
    val current_count: Int = 1,
    val score: Double = 0.0,
    val current_info: String = "I am here\n"
)
