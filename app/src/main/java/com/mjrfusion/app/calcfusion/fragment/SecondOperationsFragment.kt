package com.mjrfusion.app.calcfusion.fragment

import android.view.LayoutInflater
import android.view.View
import com.mjrfusion.app.calcfusion.calculator.Calculator
import com.mjrfusion.app.calcfusion.core.fragment.BaseFragment
import com.mjrfusion.app.calcfusion.databinding.FragmentSecondOperationsBinding

class SecondOperationsFragment : BaseFragment() {
    private lateinit var binding: FragmentSecondOperationsBinding
    private lateinit var calculator: Calculator

    override fun onCreateView(layoutInflater: LayoutInflater): View {
        binding = FragmentSecondOperationsBinding.inflate(layoutInflater)
        return binding.root
    }

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
            percentage.setOnClickListener { calculator.setPercentage() }
            sin.setOnClickListener { calculator.addSin() }
            cos.setOnClickListener { calculator.addCos() }
            tangent.setOnClickListener { calculator.addTangent() }
            pi.setOnClickListener { calculator.addPi() }
            euler.setOnClickListener { calculator.addEuler() }
            exponent.setOnClickListener { calculator.addExponent() }
            squareRoot.setOnClickListener { calculator.addSquareRoot() }
        }
    }
}