package com.example.calc

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.res.Resources
import android.database.sqlite.SQLiteDatabase
import android.graphics.*
import android.util.Log
import android.util.TypedValue
import android.view.View
import kotlin.math.ceil
import kotlin.math.sin

class PaintView(context: Context?, db: SQLiteDatabase) : View(context) {
    private var graphPaint: Paint
    private var linePaint: Paint
    private var db: SQLiteDatabase

    private val TABLE = "points"

    init {
        this.db = db

        linePaint = Paint()
        linePaint.color = Color.BLACK
        linePaint.strokeWidth = 1.toPx
        linePaint.style = Paint.Style.STROKE

        graphPaint = Paint()
        graphPaint.color = Color.BLUE
        graphPaint.strokeWidth = 3.toPx
        graphPaint.style = Paint.Style.STROKE
        graphPaint.pathEffect = CornerPathEffect(50f)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.WHITE)


        db.execSQL("CREATE TABLE IF NOT EXISTS $TABLE ( _id INTEGER PRIMARY KEY AUTOINCREMENT, x  REAL,  y REAL);");
        db.execSQL("DELETE FROM $TABLE");

        var index = 0
        val width = display.width
        val height = display.height * 0.7
        val wCount = ceil(((width / 30.toPx).toDouble())).toInt()
        val hCount = ceil(height / 30.toPx).toInt()

        val linesCount = (wCount + hCount) * 4

        var lines = FloatArray(linesCount * 4)

        for (i in 0 until wCount) {
            lines[index++] = 30.toPx * i       //x1
            lines[index++] = 0f                //y1
            lines[index++] = 30.toPx * i       //x2
            lines[index++] = height.toFloat()  //y2
        }

        for (i in 0 until hCount) {
            lines[index++] = 0f                //x1
            lines[index++] = 30.toPx * i       //y1
            lines[index++] = width.toFloat()   //x2
            lines[index++] = 30.toPx * i       //y2
        }

        canvas.drawLines(lines, linePaint)

        val path = Path()
        path.moveTo(0.toPx, (height / 2).toFloat());

        var x = (Math.PI / 4).toFloat()
        val const = (Math.PI / 4).toFloat()
        val pointCount = (width / (const * 30)).toInt()
        var list = mutableListOf<PointF>()

        for (i in 0..pointCount) {
            val point = PointF(x * 30, (height / 2 + sin(x) * 100).toFloat())
            path.lineTo(point.x, point.y)
            list.add(point)
            x += const
        }
        canvas.drawPath(path, graphPaint)
        savePoints(list)

        graphPaint.color = Color.RED
        graphPaint.strokeWidth = 10.toPx
        for (point in findExtremes())
            canvas.drawPoint(point.x, point.y, graphPaint)
    }


    private fun savePoints(list: Collection<PointF>) {
        for (point in list) {
            var values = ContentValues()
            values.put("x", point.x)
            values.put("y", point.y)
            db.insert(TABLE, null, values)
        }
    }

    private fun findExtremes(): Collection<PointF> {
        var query = db.rawQuery("SELECT * FROM $TABLE;", null)
        var list = mutableListOf<PointF>()
        var max = Float.MIN_VALUE
        var min = Float.MAX_VALUE

        if (query.moveToFirst()) {
            while (query.moveToNext()) {
                val x = query.getFloat(1)
                val y = query.getFloat(2)
                if (y >= max) {
                    max = y
                }
                if (y <= min) {
                    min = y
                }
                list.add(PointF(x, y))
            }
        }
        query.close()
        return list.filter { p: PointF -> p.y.equals(max) or p.y.equals(min) }
    }

    private val Number.toPx
        get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        )

}