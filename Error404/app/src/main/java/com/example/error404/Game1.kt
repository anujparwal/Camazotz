package com.example.error404

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Game1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activtiy_game1)

        val playOfflineButton = findViewById<Button>(R.id.play_offline_btn)

        playOfflineButton.setOnClickListener {

            startActivity(Intent(this, GameActivity::class.java))
        }
    }
}