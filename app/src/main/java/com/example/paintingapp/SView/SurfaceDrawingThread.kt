package com.example.paintingapp.SView

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder

class SurfaceDrawingThread(private val surfaceHolder: SurfaceHolder):Thread() {

    private val firstPainter = Paint().apply {
        color = Color.rgb(37,150,190)
        style = Paint.Style.FILL
        strokeWidth = 20f
        isAntiAlias = true
    }

    private val secondPainter = Paint().apply {
        color = Color.BLUE
        strokeWidth = 30f
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val thirdPainter = Paint().apply {
        color = Color.CYAN
        strokeWidth = 30f
        style = Paint.Style.FILL
        isAntiAlias = true
    }



    override fun run() {
        val canvas : Canvas? = surfaceHolder.lockCanvas()
        canvas?.apply {
            val centerX = (width/2).toFloat()
            val centerY = (height/2).toFloat()
            drawColor(Color.WHITE)

            drawRect(centerX-100,centerY-200,centerX+100,centerY+200,thirdPainter)
            drawRoundRect(centerX-200,centerY,centerX-100,centerY+275,30f,30f,firstPainter)
            drawRoundRect(centerX+200,centerY,centerX+100,centerY+275,30f,30f,firstPainter)




            drawLine(centerX-100,centerY-200,centerX+8,centerY-300,firstPainter)
            drawLine(centerX+100,centerY-200,centerX-8,centerY-300,firstPainter)


            drawCircle(centerX,centerY-100, ELUM_RADIUS,secondPainter)
            drawCircle(centerX,centerY, ELUM_RADIUS,secondPainter)
            drawCircle(centerX,centerY+100, ELUM_RADIUS,secondPainter)


            //drawLine(centerX-130,centerY+130, centerX+130,centerY+130,secondPainter)

        }
        surfaceHolder.unlockCanvasAndPost(canvas)

        this.join()
    }


   private companion object{
       private const val ELUM_RADIUS=35f
   }

}