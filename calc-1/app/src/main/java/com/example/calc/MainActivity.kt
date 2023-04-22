package com.example.calc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, CalcActivity::class.java)
        startActivity(intent)
    }

   /* fun sendMessage(view: View) {
        val editText: EditText = findViewById(R.id.editText)
        val intent = Intent(this, MessageActivity::class.java)
        System.err.println(editText.text)
        intent.putExtra("message", editText.text.toString())
        startActivity(intent)
    }*/
}