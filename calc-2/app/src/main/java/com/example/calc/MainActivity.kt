package com.example.calc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showCalcActivity(view: View) {
        val intent = Intent(this, CalcActivity::class.java)
        startActivity(intent)
    }
    fun exit(view: View) {
        AlertDialogCustom().show(supportFragmentManager, "dialog")
    }

    /* fun sendMessage(view: View) {
         val editText: EditText = findViewById(R.id.editText)
         val intent = Intent(this, MessageActivity::class.java)
         System.err.println(editText.text)
         intent.putExtra("message", editText.text.toString())
         startActivity(intent)
     }*/
}