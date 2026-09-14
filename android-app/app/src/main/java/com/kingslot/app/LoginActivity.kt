package com.kingslot.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.kingslot.app.models.Player
import com.kingslot.app.utils.FirebaseManager

class LoginActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var etMobileNumber: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth = FirebaseAuth.getInstance()

        initViews()
        setupClickListeners()

        // Check if already logged in
        if (firebaseAuth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun initViews() {
        etMobileNumber = findViewById(R.id.etMobileNumber)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegister = findViewById(R.id.btnRegister)
    }

    private fun setupClickListeners() {
        btnLogin.setOnClickListener {
            loginUser()
        }

        btnRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun loginUser() {
        val mobileNumber = etMobileNumber.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (mobileNumber.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val email = "$mobileNumber@kingslot.com"

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Login Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser() {
        val mobileNumber = etMobileNumber.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (mobileNumber.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (mobileNumber.length != 10) {
            Toast.makeText(this, "Please enter valid 10-digit mobile number", Toast.LENGTH_SHORT).show()
            return
        }

        val email = "$mobileNumber@kingslot.com"

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val userId = firebaseAuth.currentUser?.uid ?: return@addOnCompleteListener
                val playerId = "PLAYER_${System.currentTimeMillis()}"

                val player = Player(
                    uid = userId,
                    mobileNumber = mobileNumber,
                    playerId = playerId,
                    balance = 1000,
                    totalWinnings = 0.0,
                    totalLosses = 0.0,
                    createdAt = System.currentTimeMillis(),
                    lastLogin = System.currentTimeMillis()
                )

                FirebaseManager.savePlayerData(userId, player) { success ->
                    if (success) {
                        Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Failed to save player data", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Registration Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
