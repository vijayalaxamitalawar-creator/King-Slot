package com.kingslot.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.kingslot.app.utils.FirebaseManager
import com.kingslot.app.utils.SoundManager

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var soundManager: SoundManager
    private lateinit var btnStartGame: Button
    private lateinit var btnProfile: Button
    private lateinit var btnLogout: Button
    private lateinit var tvPlayerName: TextView
    private lateinit var tvBalance: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAuth = FirebaseAuth.getInstance()
        soundManager = SoundManager(this)

        initViews()
        checkUserLogin()
        loadPlayerData()
        setupClickListeners()
        soundManager.playBackgroundMusic()
    }

    private fun initViews() {
        btnStartGame = findViewById(R.id.btnStartGame)
        btnProfile = findViewById(R.id.btnProfile)
        btnLogout = findViewById(R.id.btnLogout)
        tvPlayerName = findViewById(R.id.tvPlayerName)
        tvBalance = findViewById(R.id.tvBalance)
    }

    private fun checkUserLogin() {
        val currentUser = firebaseAuth.currentUser
        if (currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun loadPlayerData() {
        val currentUser = firebaseAuth.currentUser
        currentUser?.let { user ->
            FirebaseManager.getPlayerData(user.uid) { player ->
                if (player != null) {
                    tvPlayerName.text = "Welcome, ${player.playerId}"
                    tvBalance.text = "Balance: ${player.balance} Coins"
                }
            }
        }
    }

    private fun setupClickListeners() {
        btnStartGame.setOnClickListener {
            soundManager.playSoundEffect("spin")
            startActivity(Intent(this, GameActivity::class.java))
        }

        btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        btnLogout.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        soundManager.release()
    }
}
