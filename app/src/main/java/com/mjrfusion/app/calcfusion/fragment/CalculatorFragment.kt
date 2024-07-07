package com.mjrfusion.app.calcfusion.fragment

import android.view.View
import com.mjrfusion.app.calcfusion.core.fragment.BaseFragment
import com.mjrfusion.app.calcfusion.R
import com.mjrfusion.app.calcfusion.databinding.FragmentCalculatorBinding

class CalculatorFragment: BaseFragment(R.layout.fragment_calculator) {
    private lateinit var binding: FragmentCalculatorBinding

    override fun initializeAttributes(view: View) {
        binding = FragmentCalculatorBinding.bind(view)
    }
}