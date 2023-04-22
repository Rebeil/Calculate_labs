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
    fun showGraphActivity(view: View) {
        val intent = Intent(this, GraphActivity::class.java)
        startActivity(intent)
    }
    fun exit(view: View) {
        AlertDialogCustom().show(supportFragmentManager, "dialog")
    }

}