package com.example.error404

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnConfirm = findViewById<Button>(R.id.btnConfirm)
        val btnCancel = findViewById<Button>(R.id.btnCancel)
        val btnAccept = findViewById<Button>(R.id.btnAccept)
        val btnReject = findViewById<Button>(R.id.btnReject)

        // Proper navigation flow
        btnConfirm.setOnClickListener {
            startActivity(Intent(this, page2::class.java))
        }

        btnAccept.setOnClickListener {
            startActivity(Intent(this, page3::class.java))
        }

        btnCancel.setOnClickListener {
            // Cancel usually clears or goes back; here it resets the screen
            recreate()
        }

        btnReject.setOnClickListener {
            startActivity(Intent(this, page4::class.java))
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}