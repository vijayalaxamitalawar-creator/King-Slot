package com.kingslot.app.models

data class Player(
    val uid: String = "",
    val mobileNumber: String = "",
    val playerId: String = "",
    val balance: Int = 0,
    val totalWinnings: Double = 0.0,
    val totalLosses: Double = 0.0,
    val createdAt: Long = 0,
    val lastLogin: Long = 0
)
