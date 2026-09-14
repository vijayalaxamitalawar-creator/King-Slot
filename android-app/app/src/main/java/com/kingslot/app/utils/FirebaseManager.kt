package com.kingslot.app.utils

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.kingslot.app.models.Player
import com.kingslot.app.models.Transaction
import com.kingslot.app.models.WithdrawalRequest

object FirebaseManager {

    private val database = FirebaseDatabase.getInstance()
    private val firestore = FirebaseFirestore.getInstance()
    private val playersRef = database.getReference("players")
    private val gamesRef = database.getReference("games")
    private val transactionsRef = database.getReference("transactions")
    private val withdrawalsRef = database.getReference("withdrawals")

    // Player Operations
    fun savePlayerData(userId: String, player: Player, callback: (Boolean) -> Unit) {
        playersRef.child(userId).setValue(player).addOnCompleteListener { task ->
            callback(task.isSuccessful)
        }
    }

    fun getPlayerData(userId: String, callback: (Player?) -> Unit) {
        playersRef.child(userId).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback(task.result.getValue(Player::class.java))
            } else {
                callback(null)
            }
        }
    }

    fun updatePlayerBalance(userId: String, newBalance: Int, callback: (Boolean) -> Unit) {
        playersRef.child(userId).child("balance").setValue(newBalance).addOnCompleteListener { task ->
            callback(task.isSuccessful)
        }
    }

    fun getPlayerByMobileNumber(mobileNumber: String, callback: (Player?) -> Unit) {
        playersRef.orderByChild("mobileNumber").equalTo(mobileNumber).get().addOnCompleteListener { task ->
            if (task.isSuccessful && task.result.childrenCount > 0) {
                for (snapshot in task.result.children) {
                    val player = snapshot.getValue(Player::class.java)
                    callback(player)
                    return@addOnCompleteListener
                }
            }
            callback(null)
        }
    }

    // Transaction Operations
    fun saveTransaction(transaction: Transaction, callback: (Boolean) -> Unit) {
        val transactionId = transaction.transactionId.ifEmpty { System.currentTimeMillis().toString() }
        transactionsRef.child(transactionId).setValue(transaction).addOnCompleteListener { task ->
            callback(task.isSuccessful)
        }
    }

    fun getPlayerTransactions(playerId: String, callback: (List<Transaction>) -> Unit) {
        transactionsRef.orderByChild("playerId").equalTo(playerId).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val transactions = mutableListOf<Transaction>()
                for (snapshot in task.result.children) {
                    val transaction = snapshot.getValue(Transaction::class.java)
                    if (transaction != null) {
                        transactions.add(transaction)
                    }
                }
                callback(transactions)
            } else {
                callback(emptyList())
            }
        }
    }

    // Withdrawal Operations
    fun createWithdrawalRequest(request: WithdrawalRequest, callback: (Boolean) -> Unit) {
        val withdrawalId = "WD_${System.currentTimeMillis()}"
        withdrawalsRef.child(withdrawalId).setValue(request.copy(withdrawalId = withdrawalId)).addOnCompleteListener { task ->
            callback(task.isSuccessful)
        }
    }

    fun getWithdrawalRequests(callback: (List<WithdrawalRequest>) -> Unit) {
        withdrawalsRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val requests = mutableListOf<WithdrawalRequest>()
                for (snapshot in task.result.children) {
                    val request = snapshot.getValue(WithdrawalRequest::class.java)
                    if (request != null) {
                        requests.add(request)
                    }
                }
                callback(requests)
            } else {
                callback(emptyList())
            }
        }
    }

    // Admin Settings
    fun getAdminSettings(callback: (String?) -> Unit) {
        database.getReference("admin_settings/whatsappNumber").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback(task.result.value as? String)
            } else {
                callback(null)
            }
        }
    }
}
