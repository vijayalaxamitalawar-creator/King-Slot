package com.kingslot.app.models

data class Transaction(
    val transactionId: String = "",
    val playerId: String = "",
    val gameId: Int = 0,
    val amount: Int = 0,
    val type: String = "", // win, loss, bet
    val timestamp: Long = 0
)
