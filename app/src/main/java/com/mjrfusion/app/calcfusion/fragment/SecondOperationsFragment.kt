package com.mjrfusion.app.calcfusion.fragment

import android.view.View
import com.mjrfusion.app.calcfusion.R
import com.mjrfusion.app.calcfusion.calculator.Calculator
import com.mjrfusion.app.calcfusion.core.fragment.BaseFragment
import com.mjrfusion.app.calcfusion.databinding.FragmentSecondOperationsBinding

class SecondOperationsFragment : BaseFragment(R.layout.fragment_second_operations) {
    private lateinit var binding: FragmentSecondOperationsBinding
    private lateinit var calculator: Calculator

    override fun initializeAttributes(view: View) {
        binding = FragmentSecondOperationsBinding.bind(view)
        calculator = Calculator.getInstance()
    }

    override fun setListeners() {
        setButtonsClickListener()
    }

    private fun setButtonsClickListener() {
        binding.apply {
            openBracket.setOnClickListener { calculator.openBracket() }
            closeBracket.setOnClickListener { calculator.closeBracket() }
        }
    }
}