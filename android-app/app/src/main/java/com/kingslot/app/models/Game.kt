package com.kingslot.app.models

data class Game(
    val gameId: Int = 0,
    val gameName: String = "",
    val difficulty: String = "NORMAL", // EASY, NORMAL, HARD
    val payoutPercentage: Double = 0.0,
    val minBet: Int = 10,
    val maxBet: Int = 1000,
    val symbols: List<String> = emptyList(),
    val active: Boolean = true
)
