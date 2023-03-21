package com.example.paintingapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.paintingapp.MainActivity.Companion.paintBrush
import com.example.paintingapp.MainActivity.Companion.path
import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

class PaintView:View {

    var params:ViewGroup.LayoutParams?=null

    companion object{
        var pathList = ArrayList<Path>()
        var colorList = ArrayList<Int>()
        var currentBrush = Color.BLACK
        var brushType:BrushType = BrushType.BLACK_BRUSH
    }

    constructor(context:Context):this(context,null){
        init()
    }
    constructor(context: Context,attrs:AttributeSet?):this(context,attrs,0){
        init()
    }
    constructor(context: Context,attrs: AttributeSet?,defStyleAttr:Int):super(context,attrs,defStyleAttr){
        init()
    }

    private fun init(){
        paintBrush.isAntiAlias=true
        paintBrush.color = currentBrush
        paintBrush.style = Paint.Style.STROKE
        paintBrush.strokeJoin = Paint.Join.ROUND
        paintBrush.strokeWidth = 8f

        params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var x = event.x
        var y = event.y
        var r = 50f



        when(event.action){
            MotionEvent.ACTION_DOWN->{
                path.moveTo(x,y)
                Painting(x,y,r,true, brushType)
                return true
            }
            MotionEvent.ACTION_MOVE ->{
                /*path.lineTo(x,y)
                pathList.add(path)
                colorList.add(currentBrush)*/
            }
            MotionEvent.ACTION_UP->{

            Painting(x,y,r,false, brushType)

            }

            else->return false
        }

        postInvalidate()

        return false
    }

    override fun onDraw(canvas: Canvas) {
        for(i in pathList.indices){

            paintBrush.setColor(colorList[i])
            canvas.drawPath(pathList[i], paintBrush)
            invalidate()

        }
    }

    private fun Painting(x:Float,y:Float,r:Float, onClick:Boolean,brushType: BrushType){

        if(onClick){
            when (brushType){
                BrushType.BLACK_BRUSH->{

                    var xVel = cos((120+(x/100)).toDouble())*(r+20)
                    var yVel = sin((120+(y/100)).toDouble())*(r+20)

                    path.moveTo(xVel.toFloat()+x, yVel.toFloat()+y)

                    /*CoroutineScope(Dispatchers.IO).launch {
                        for(i in 1..12){
                            delay(1000L)
                            xVel = cos((i*120+(x/100)).toDouble())*(r+20)
                            yVel = sin((i*120+(y/100)).toDouble())*(r+20)

                            path.lineTo((xVel+x).toFloat(), (yVel+y).toFloat())
                            pathList.add(path)
                            colorList.add(currentBrush)
                        }
                        joinAll()
                    }*/

                    Executors.newSingleThreadExecutor().execute{

                        for(i in 1..12){

                            xVel = cos((i*120+(x/100)).toDouble())*(r+20)
                            yVel = sin((i*120+(y/100)).toDouble())*(r+20)

                            path.lineTo((xVel+x).toFloat(), (yVel+y).toFloat())
                            pathList.add(path)
                            colorList.add(currentBrush)
                        }
                    }

                    xVel = cos((120+(x/100)).toDouble())*(r+20)
                    yVel = sin((120+(y/100)).toDouble())*(r+20)

                    path.lineTo((xVel+x).toFloat(), (yVel+y).toFloat())
                    pathList.add(path)
                    colorList.add(currentBrush)

                    path.moveTo(x, y)

                }
                BrushType.RED_BRUSH->{

                    var xVel = cos((60+(x/100)).toDouble())*(r+20)
                    var yVel = sin((60+(y/100)).toDouble())*(r+20)

                    path.moveTo(xVel.toFloat()+x, yVel.toFloat()+y)

                    Executors.newSingleThreadExecutor().execute{
                        for(i in 1..12){

                            xVel = cos((i*60+(x/100)).toDouble())*(r+20)
                            yVel = sin((i*60+(y/100)).toDouble())*(r+20)

                            path.lineTo((xVel+x).toFloat(), (yVel+y).toFloat())
                            pathList.add(path)
                            colorList.add(currentBrush)
                        }
                    }

                    xVel = cos((60+(x/100)).toDouble())*(r+20)
                    yVel = sin((60+(y/100)).toDouble())*(r+20)

                    path.lineTo((xVel+x).toFloat(), (yVel+y).toFloat())
                    pathList.add(path)
                    colorList.add(currentBrush)

                    path.moveTo(x, y)

                }
                BrushType.GREEN_BRASH->{

                    var xVel = cos((180+(x/100)).toDouble())*(r+20)
                    var yVel = sin((180+(y/100)).toDouble())*(r+20)

                    path.moveTo(xVel.toFloat()+x, yVel.toFloat()+y)

                    Executors.newSingleThreadExecutor().execute{
                        for(i in 1..3){

                            xVel = cos((i*180+(x/100)).toDouble())*(r+20)
                            yVel = sin((i*180+(y/100)).toDouble())*(r+20)

                            path.lineTo((xVel+x).toFloat(), (yVel+y).toFloat())
                            pathList.add(path)
                            colorList.add(currentBrush)
                        }

                        xVel = cos((180+(x/100)).toDouble())*(r+20)
                        yVel = sin((180+(y/100)).toDouble())*(r+20)

                        path.lineTo((xVel+x).toFloat(), (yVel+y).toFloat())
                        pathList.add(path)
                        colorList.add(currentBrush)

                        path.moveTo(x, y)

                    }
                }
            }
        }else{
            when (brushType){
                BrushType.BLACK_BRUSH->{

                    var xVel = cos((10+(x/100)).toDouble())*(r+20)
                    var yVel = sin((10+(y/100)).toDouble())*(r+20)

                    path.moveTo(xVel.toFloat()+x, yVel.toFloat()+y)

                    Executors.newSingleThreadExecutor().execute{
                        for(i in 1..6){

                            xVel = cos(((i*10)+(x/100)).toDouble())*(r+10)
                            yVel = sin(((i*10)+(y/100)).toDouble())*(r+10)

                            path.lineTo((xVel+x).toFloat(), (yVel+y).toFloat())
                            pathList.add(path)
                            colorList.add(currentBrush)
                        }

                        xVel = cos(((10)+(x/100)).toDouble())*(r+10)
                        yVel = sin(((10)+(y/100)).toDouble())*(r+10)

                        path.lineTo((xVel+x).toFloat(), (yVel+y).toFloat())
                        pathList.add(path)
                        colorList.add(currentBrush)

                        path.moveTo(x, y)
                    }
                }
                BrushType.RED_BRUSH->{

                    var xVel = cos((360+(x/100)).toDouble())*(r+20)
                    var yVel = sin((360+(y/100)).toDouble())*(r+20)

                    path.moveTo(xVel.toFloat()+x, yVel.toFloat()+y)

                    Executors.newSingleThreadExecutor().execute{
                        for(i in 1..12){

                            xVel = cos((i*360+(x/100)).toDouble())*(r+20)
                            yVel = sin((i*360+(y/100)).toDouble())*(r+50)

                            path.lineTo((xVel+x).toFloat(), (yVel+y).toFloat())
                            pathList.add(path)
                            colorList.add(currentBrush)
                        }
                    }

                    xVel = cos(((360)+(x/100)).toDouble())*(r+10)
                    yVel = sin(((360)+(y/100)).toDouble())*(r+10)

                    path.lineTo((xVel+x).toFloat(), (yVel+y).toFloat())
                    pathList.add(path)
                    colorList.add(currentBrush)

                    path.moveTo(x, y)

                }
                BrushType.GREEN_BRASH->{

                    var xVel = cos((-180+(x/100)).toDouble())*(r+20)
                    var yVel = sin((-180+(y/100)).toDouble())*(r+20)

                    path.moveTo(xVel.toFloat()+x, yVel.toFloat()+y)

                    Executors.newSingleThreadExecutor().execute{
                        for(i in 1..3){

                            var xVel = cos((-i*180+(x/100)).toDouble())*(r+20)
                            var yVel = sin((-i*180+(y/100)).toDouble())*(r+20)

                            path.lineTo((xVel+x).toFloat(), (yVel+y).toFloat())
                            pathList.add(path)
                            colorList.add(currentBrush)
                        }
                    }

                    xVel = cos(((-180)+(x/100)).toDouble())*(r+10)
                    yVel = sin(((-180)+(y/100)).toDouble())*(r+10)

                    path.lineTo((xVel+x).toFloat(), (yVel+y).toFloat())
                    pathList.add(path)
                    colorList.add(currentBrush)

                    path.moveTo(x, y)
                }
            }
        }


    }

}