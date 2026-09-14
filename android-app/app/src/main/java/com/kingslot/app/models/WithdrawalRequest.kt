package com.kingslot.app.models

data class WithdrawalRequest(
    val withdrawalId: String = "",
    val playerId: String = "",
    val mobileNumber: String = "",
    val amount: Int = 0,
    val status: String = "pending", // pending, approved, rejected
    val requestedAt: Long = 0,
    val processedAt: Long = 0
)
