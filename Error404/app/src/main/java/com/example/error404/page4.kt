package com.example.error404

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class page4 : AppCompatActivity() {

    private var clickCount = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page4)

        val btnConfirm = findViewById<Button>(R.id.btnConfirm)
        val btnCancel = findViewById<Button>(R.id.btnCancel)
        val btnAccept = findViewById<Button>(R.id.btnAccept)
        val btnReject = findViewById<Button>(R.id.btnReject)

        btnConfirm.setOnClickListener {
            startActivity(Intent(this, page3::class.java))
        }
        btnReject.setOnClickListener {
            clickCount++
            if (clickCount >= 5) {
                startActivity(Intent(this, Game1::class.java))
                clickCount = 0
            }
        }
        btnCancel.setOnClickListener {
            startActivity(Intent(this, page4::class.java))
        }
        btnAccept.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}