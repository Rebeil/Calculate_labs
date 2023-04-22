package com.example.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout

class GraphActivity : AppCompatActivity() {

    private lateinit var layout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)

        layout = findViewById(R.id.graphLayout)
        val paintView = PaintView(this)
        var layoutParams = ConstraintLayout
            .LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
        layoutParams.bottomToTop = R.id.backBtn
        layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        paintView.layoutParams = layoutParams
        layout.addView(paintView)
    }

    fun back(view: View) {
        this.finish()
    }
}