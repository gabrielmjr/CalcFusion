package com.mjrfusion.app.calcfusion.fragment

import android.view.View
import com.mjrfusion.app.calcfusion.core.fragment.BaseFragment
import com.mjrfusion.app.calcfusion.R
import com.mjrfusion.app.calcfusion.databinding.FragmentCalculatorBinding
import com.mjrfusion.app.calcfusion.listener.CalculatorButtonClickListener

class CalculatorFragment : BaseFragment(R.layout.fragment_calculator), CalculatorButtonClickListener {
    private lateinit var binding: FragmentCalculatorBinding
    override var expression: String = ""
    override var canAddComma = true

    override fun initializeAttributes(view: View) {
        binding = FragmentCalculatorBinding.bind(view)
    }

    override fun setListeners() {
        setButtonsClickListener()
        setClearButtonLongClickListener()
    }

    private fun setButtonsClickListener() {
        this.let {
            binding.apply {
                num0.setOnClickListener(it)
                num1.setOnClickListener(it)
                num2.setOnClickListener(it)
                num3.setOnClickListener(it)
                num4.setOnClickListener(it)
                num5.setOnClickListener(it)
                num6.setOnClickListener(it)
                num7.setOnClickListener(it)
                num8.setOnClickListener(it)
                num9.setOnClickListener(it)
                comma.setOnClickListener(it)
                equals.setOnClickListener(it)
                del.setOnClickListener(it)
                multiplication.setOnClickListener(it)
                division.setOnClickListener(it)
                subtraction.setOnClickListener(it)
                addition.setOnClickListener(it)
            }
        }
    }

    private fun setClearButtonLongClickListener() {
        binding.del.setOnLongClickListener(this)
    }

    override fun onExpressionChanged(newExpression: String) {
        binding.expression.text = newExpression
    }
}