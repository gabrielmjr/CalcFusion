package com.mjrvision.app.calcvision

import androidx.activity.enableEdgeToEdge
import com.mjrvision.app.calcfusion.core.activity.BaseActivity
import com.mjrvision.app.calcvision.databinding.ActivityMainBinding
import com.mjrvision.app.calcvision.fragment.CalculatorFragment

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun initializeActivity() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initializeAttributes() {
        replaceFragmentBy(R.id.fragment_container, CalculatorFragment::class.java)
    }

    override fun setListeners() {
    }
}