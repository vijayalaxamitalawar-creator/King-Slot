package com.kingslot.app.utils

object Constants {
    const val TOTAL_GAMES = 150
    const val DEFAULT_COINS = 1000
    const val MIN_BET = 10
    const val MAX_BET = 1000

    object GameDifficulty {
        const val EASY = "EASY"
        const val NORMAL = "NORMAL"
        const val HARD = "HARD"
    }

    object TransactionType {
        const val BET = "bet"
        const val WIN = "win"
        const val LOSS = "loss"
    }

    object WithdrawalStatus {
        const val PENDING = "pending"
        const val APPROVED = "approved"
        const val REJECTED = "rejected"
    }
}
