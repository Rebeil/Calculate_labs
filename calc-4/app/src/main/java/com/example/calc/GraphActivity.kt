package com.example.calc

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout

class GraphActivity : AppCompatActivity() {

    private lateinit var layout: ConstraintLayout
    private lateinit var db: SQLiteDatabase
    val TABLE = "points"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)


        db = openOrCreateDatabase("calc.db", MODE_PRIVATE, null)


        layout = findViewById(R.id.graphLayout)
        val paintView = PaintView(this, db)

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

    override fun onResume() {
        super.onResume()


    }

    override fun onDestroy() {
        super.onDestroy()
        db.execSQL("DROP TABLE IF EXISTS $TABLE");
        db.close()

    }

    fun back(view: View) {
        this.finish()
    }
}