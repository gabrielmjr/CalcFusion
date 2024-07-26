package com.mjrfusion.app.calcfusion.calculator

import com.ezylang.evalex.Expression
import com.mjrfusion.app.calcfusion.viewmodel.CalculatorViewModel
import kotlin.properties.Delegates

class Calculator {
    private var expression = ""
    private var canAddDot by Delegates.notNull<Boolean>()
    lateinit var calculatorViewModel: CalculatorViewModel

    fun addNumber(charNumber: Char) {
        expression += charNumber
        calculatorViewModel.expressionViewModel.postValue(expression)
    }

    fun evaluate() {
        val temp = expression.replace('×', '*').replace('÷', '/')
        calculatorViewModel.result.postValue(Expression(temp).evaluate().value.toString())
    }

    fun addDotIfPossible() {
        if (canAddDot) {
            expression += if (expression.isEmpty()) "0."
            else when (expression[expression.length - 1]) {
                '×', '÷', '+', '-' -> "0."
                else -> "."
            }
            canAddDot = false
            calculatorViewModel.expressionViewModel.postValue(expression)
        }
    }

    fun removeLastChar() {
        if (expression.isNotEmpty()) {
            expression = expression.substring(0, expression.length - 1)
            calculatorViewModel.expressionViewModel.postValue(expression)
        }
    }

    fun addSignalIfPossible(signal: Char) {
        when (signal) {
            '+', '-' -> {
                expression += signal
                canAddDot = true
                calculatorViewModel.expressionViewModel.postValue(expression)
                return
            }
        }
        if (canSignalBeAdded(signal) && !isLastCharASignal()) {
            expression += signal
            canAddDot = true
        }
        calculatorViewModel.expressionViewModel.postValue(expression)
    }

    private fun canSignalBeAdded(signal: Char): Boolean {
        return if (expression.isEmpty()) false
        else if (signal == '×' || signal == '÷') when (expression[expression.length - 1]) {
            '+', '-' -> false
            else -> true
        }
        else true
    }

    private fun isLastCharASignal(): Boolean {
        return if (expression.isEmpty()) false
        else when (expression[expression.length - 1]) {
            '÷', '×' -> true
            else -> false
        }
    }

    fun cleanAll() {
        expression = ""
        canAddDot = true
        calculatorViewModel.expressionViewModel.postValue(expression)
        calculatorViewModel.result.postValue(expression)
    }

    companion object {
        private var instance: Calculator? = null

        @JvmStatic
        fun getInstance(): Calculator {
            if (instance == null) instance = Calculator()
            return instance!!
        }
    }
}