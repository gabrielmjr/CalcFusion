package com.mjrfusion.app.calcfusion.fragment

import android.view.View
import com.mjrfusion.app.calcfusion.R
import com.mjrfusion.app.calcfusion.calculator.Calculator
import com.mjrfusion.app.calcfusion.core.fragment.BaseFragment
import com.mjrfusion.app.calcfusion.databinding.FragmentFirstOperationsBinding

class FirstOperationFragment : BaseFragment(R.layout.fragment_first_operations) {
    private lateinit var binding: FragmentFirstOperationsBinding
    private lateinit var calculator: Calculator

    override fun initializeAttributes(view: View) {
        binding = FragmentFirstOperationsBinding.bind(view)
        calculator = Calculator.getInstance()
    }

    override fun setListeners() {
        setButtonsClickListener()
    }

    private fun setButtonsClickListener() {
        this.let {
            binding.apply {
                num0.setOnClickListener { calculator.addNumber('0') }
                num1.setOnClickListener { calculator.addNumber('1') }
                num2.setOnClickListener { calculator.addNumber('2') }
                num3.setOnClickListener { calculator.addNumber('3') }
                num4.setOnClickListener { calculator.addNumber('4') }
                num5.setOnClickListener { calculator.addNumber('5') }
                num6.setOnClickListener { calculator.addNumber('6') }
                num7.setOnClickListener { calculator.addNumber('7') }
                num8.setOnClickListener { calculator.addNumber('8') }
                num9.setOnClickListener { calculator.addNumber('9') }
                dot.setOnClickListener { calculator.addDotIfPossible() }
                equals.setOnClickListener { calculator.evaluate() }
            }
        }
    }
}