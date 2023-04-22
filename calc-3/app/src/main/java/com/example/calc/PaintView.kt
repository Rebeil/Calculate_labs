package com.example.calc

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.Log
import android.util.TypedValue
import android.view.View
import kotlin.math.ceil
import kotlin.math.sin

class PaintView(context: Context?) : View(context) {
    private var graphPaint: Paint
    private var linePaint: Paint

    init {
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

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.WHITE)


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

        /*repeat((width / PI.toPx).toInt()) { _ ->
            path.rQuadTo((PI / 2).toPx * 10, (-90).toPx, PI.toPx * 10, 0.toPx);
            path.rQuadTo((PI / 2).toPx * 10, 90.toPx, PI.toPx * 10, 0.toPx);
        }*/

        var x = (Math.PI / 4).toFloat()
        val const = (Math.PI / 4).toFloat()
        val pointCount = (width / (const * 30)).toInt()

        for (i in 0..pointCount) {
            path.lineTo(x * 30, (height / 2 + sin(x) * 100).toFloat())
            x += const
        }
        /*while (x * 30 <= width) {
            val y = sin(x.toDouble()).toFloat()
            path.lineTo(x * 30,  (height/2 + y * 100).toFloat())
            x += (Math.PI / 4).toFloat()
        }*/


        canvas.drawPath(path, graphPaint)
    }

    private val Number.toPx
        get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        )

}