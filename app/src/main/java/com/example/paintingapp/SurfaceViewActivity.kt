package com.example.paintingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.paintingapp.SView.CustomSurfaceView
import com.example.paintingapp.databinding.ActivitySurfaceViewBinding

class SurfaceViewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(CustomSurfaceView(this))
    }
}