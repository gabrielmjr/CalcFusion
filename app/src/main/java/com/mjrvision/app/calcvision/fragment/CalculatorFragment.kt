package com.mjrvision.app.calcvision.fragment

import android.view.View
import com.mjrvision.app.calcfusion.core.fragment.BaseFragment
import com.mjrvision.app.calcvision.R
import com.mjrvision.app.calcvision.databinding.FragmentCalculatorBinding

class CalculatorFragment: BaseFragment(R.layout.fragment_calculator) {
    private lateinit var binding: FragmentCalculatorBinding

    override fun initializeAttributes(view: View) {
        binding = FragmentCalculatorBinding.bind(view)
    }
}