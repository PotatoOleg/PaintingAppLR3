package com.example.paintingapp.SView

import android.content.Context
import android.view.SurfaceHolder
import android.view.SurfaceView

class CustomSurfaceView(context:Context):SurfaceView(context),SurfaceHolder.Callback{

    private val drawingThread = SurfaceDrawingThread(holder)

    init {
        holder.addCallback(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        drawingThread.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        try {
            drawingThread.join()
        }catch (e:java.lang.Exception){

        }
    }
}