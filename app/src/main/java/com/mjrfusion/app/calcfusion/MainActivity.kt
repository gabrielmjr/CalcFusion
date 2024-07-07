package com.mjrfusion.app.calcfusion

import com.mjrfusion.app.calcfusion.core.activity.BaseActivity
import com.mjrfusion.app.calcfusion.databinding.ActivityMainBinding
import com.mjrfusion.app.calcfusion.fragment.CalculatorFragment

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