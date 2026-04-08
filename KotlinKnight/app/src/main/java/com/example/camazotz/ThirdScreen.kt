package com.example.camazotz

import android.os.Bundle
import android.view.KeyEvent
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Context
import android.content.Intent
import android.text.TextWatcher
import android.text.Editable
import com.example.camazotz.MainActivity

class ThirdScreen : AppCompatActivity() {
    private lateinit var etNote: EditText
    private lateinit var tvNotes: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.orderpage)

        val imageView = findViewById<ImageView>(R.id.imageView2)
        val textView = findViewById<TextView>(R.id.textView6)
        val imageRes = intent.getIntExtra("image", 0)

        val Image = findViewById<ImageView>(R.id.imageView2)
        val Name = findViewById<TextView>(R.id.textView6)
        val Price = findViewById<TextView>(R.id.textView7)
        val notesEditText = findViewById<EditText>(R.id.etNote)

        val bookId = intent.getIntExtra("ID", 0)
        val name = intent.getStringExtra("NAME")
        val price = intent.getStringExtra("Price")
        val image = intent.getIntExtra("IMAGE", 0)

        Name.text = name
        Price.text = price
        Image.setImageResource(image)

        val sharedPref = getSharedPreferences("BookNotes", Context.MODE_PRIVATE)
        val noteKey = "notes_book_$bookId"

        // Load saved note
        notesEditText.setText(sharedPref.getString(noteKey, ""))

        // Auto-save while typing
        notesEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                sharedPref.edit().putString(noteKey, s.toString()).apply()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        val back =findViewById<ImageView>(R.id.backBtn)
        back.setOnClickListener {
            val intent= Intent(this, SecondScreen::class.java)
            startActivity(intent)
        }

    }


}