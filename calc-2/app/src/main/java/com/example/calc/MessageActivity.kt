package com.example.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        var  text:String? = intent.getStringExtra("message")
        var textView : TextView
        textView = findViewById(R.id.messageText)
    println("Hne: $text")
        textView.text = text
    }
}