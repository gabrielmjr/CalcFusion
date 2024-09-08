package com.mjrfusion.app.calcfusion.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.mjrfusion.app.calcfusion.R
import com.mjrfusion.app.calcfusion.adapter.OperatorsAdapter
import com.mjrfusion.app.calcfusion.calculator.Calculator
import com.mjrfusion.app.calcfusion.core.fragment.BaseFragment
import com.mjrfusion.app.calcfusion.databinding.FragmentCalculatorBinding
import com.mjrfusion.app.calcfusion.utils.Utils.EXPRESSION
import com.mjrfusion.app.calcfusion.utils.Utils.RESULT
import com.mjrfusion.app.calcfusion.viewmodel.CalculatorViewModel

class CalculatorFragment : BaseFragment(), Calculator.HintHelper,
    Calculator.ExpressionValidation {
    private lateinit var binding: FragmentCalculatorBinding
    private lateinit var calculator: Calculator

    override fun onCreateView(layoutInflater: LayoutInflater): View {
        binding = FragmentCalculatorBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initializeAttributes(view: View, savedInstanceState: Bundle?) {
        binding.operationsViewPager.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = OperatorsAdapter(childFragmentManager, lifecycle)
        }
        calculator = Calculator.getInstance()
        calculator.hintHelper = this
        calculator.expressionValidation = this
        restoreSavedInstanceState(savedInstanceState)
        setExpressionObserver()
    }

    private fun setExpressionObserver() {
        calculator.calculatorViewModel =
            ViewModelProvider(this)[CalculatorViewModel::class.java].apply {
                expressionViewModel.observeForever {
                    if (calculator.isInvalidExpression)
                        stopInvalidExpression()
                    binding.expression.text = it
                }

                result.observeForever {
                    binding.result.text = it
                }
            }
    }

    private fun restoreSavedInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.apply {
            binding.let {
                calculator.postExpression()
                it.result.text = getString(RESULT)
                Log.d(mLocalTag, "restoreSavedInstanceState: Restoring saved instance state: " + it.result.text.toString())
            }
        }
    }

    override fun setListeners() {
        setButtonsClickListener()
    }

    private fun setButtonsClickListener() {
        let {
            binding.apply {
                del.apply {
                    setOnClickListener { calculator.removeLastChar() }
                    setOnLongClickListener {
                        calculator.cleanAll()
                        true
                    }
                }
                multiplication.setOnClickListener { calculator.addSignalIfPossible('×') }
                division.setOnClickListener { calculator.addSignalIfPossible('÷') }
                subtraction.setOnClickListener { calculator.addSignalIfPossible('-') }
                addition.setOnClickListener { calculator.addSignalIfPossible('+') }
            }
        }
    }

    override fun onHintTextChanged(hint: String) {
        binding.expressionHint.text = hint
    }

    override fun onExpressionInvalid() {
        binding.result.apply {
            setTextColor(ResourcesCompat.getColor(resources, R.color.red, null))
            setText(R.string.invalid_expression)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun stopInvalidExpression() {
        calculator.isInvalidExpression = false
        binding.result.apply {
            text = ""
            setTextColor(ResourcesCompat.getColor(resources, R.color.expressionTextColor, null))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.apply {
            putString(EXPRESSION, binding.expression.text.toString())
            putString(RESULT, binding.result.text.toString())
        }
    }
}