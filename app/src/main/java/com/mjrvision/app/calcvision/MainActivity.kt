package com.mjrvision.app.calcvision

import androidx.activity.enableEdgeToEdge
import com.mjrvision.app.calcfusion.core.activity.BaseActivity
import com.mjrvision.app.calcvision.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun initializeActivity() {
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initializeAttributes() {
    }

    override fun setListeners() {
    }
}