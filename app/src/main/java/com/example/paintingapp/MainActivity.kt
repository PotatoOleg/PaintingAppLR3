package com.example.paintingapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.paintingapp.PaintView.Companion.brushType
import com.example.paintingapp.PaintView.Companion.colorList
import com.example.paintingapp.PaintView.Companion.currentBrush
import com.example.paintingapp.PaintView.Companion.pathList
import com.example.paintingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    companion object{
        var path = Path()
        var paintBrush = Paint()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()

        setContentView(binding.root)

        val redBtn = binding.redColor
        val greenBtn = binding.greenColor
        val blackBtn = binding.blackColor
        val whiteBtn = binding.whiteColor

        redBtn.setOnClickListener{
            paintBrush.setColor(Color.RED)
            currentColor(paintBrush.color)
            brushType = BrushType.RED_BRUSH

        }


        greenBtn.setOnClickListener{
            paintBrush.setColor(Color.GREEN)
            currentColor(paintBrush.color)
            brushType = BrushType.GREEN_BRASH
        }

        blackBtn.setOnClickListener{
            paintBrush.setColor(Color.BLACK)
            currentColor(paintBrush.color)
            brushType = BrushType.BLACK_BRUSH
        }

        whiteBtn.setOnClickListener{
            pathList.clear()
            colorList.clear()
            path.reset()
        }

        binding.nextActivityButton.setOnClickListener{
            val intent = Intent(this,SurfaceViewActivity::class.java)
            startActivity(intent)
        }

    }

    private fun currentColor(color:Int){
        currentBrush = color
        path = Path()
    }
}