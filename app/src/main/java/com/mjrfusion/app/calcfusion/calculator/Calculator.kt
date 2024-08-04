package com.mjrfusion.app.calcfusion.calculator

import com.ezylang.evalex.Expression
import com.mjrfusion.app.calcfusion.viewmodel.CalculatorViewModel
import java.math.MathContext
import java.math.RoundingMode
import kotlin.properties.Delegates

class Calculator {
    private var expression = ""
    private var canAddDot by Delegates.notNull<Boolean>()
    lateinit var calculatorViewModel: CalculatorViewModel
    private var openedBrackets = 0
    lateinit var hintHelper: HintHelper
    private var hint = ""

    fun addNumber(charNumber: Char) {
        expression += charNumber
        calculatorViewModel.expressionViewModel.postValue(expression)
    }

    fun evaluate() {
        for (i in 1..openedBrackets)
            closeBracket()
        val temp = expression.replace('×', '*').replace('÷', '/')
        calculatorViewModel.result.postValue(
            removeLastZero(
                Expression(temp).evaluate().numberValue.round(
                    MathContext(15, RoundingMode.HALF_UP)
                ).toString()
            )
        )
    }

    private fun removeLastZero(result: String): String {
        if (result[result.length - 2] == '0')
            return removeLastZero(result.substring(0, result.length - 1))
        return result
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
            if (expression.last() == ')') {
                hint += "$hint)"
                openedBrackets++
                hintHelper.onHintTextChanged(hint)
            } else if (expression.last() == '(') {
                hint = hint.substring(0, hint.length - 1)
                openedBrackets--
                hintHelper.onHintTextChanged(hint)
            }
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

    fun addSin() {
        expression += "sin"
        openBracket()
    }

    fun addCos() {
        expression += "cos"
        openBracket()
    }

    fun addTangent() {
        expression += "tan"
        openBracket()
    }

    fun openBracket() {
        expression += "("
        openedBrackets++
        hint += ")"
        hintHelper.onHintTextChanged(hint)
        calculatorViewModel.expressionViewModel.postValue(expression)
    }

    fun closeBracket() {
        if (openedBrackets > 0) {
            expression += ")"
            openedBrackets--
            hint = hint.substring(0, hint.length - 1)
            hintHelper.onHintTextChanged(hint)
            calculatorViewModel.expressionViewModel.postValue(expression)
        }
    }

    fun cleanAll() {
        expression = ""
        canAddDot = true
        calculatorViewModel.expressionViewModel.postValue(expression)
        calculatorViewModel.result.postValue(expression)
        hint = ""
        hintHelper.onHintTextChanged(hint)
    }

    companion object {
        private var instance: Calculator? = null

        @JvmStatic
        fun getInstance(): Calculator {
            if (instance == null) instance = Calculator()
            return instance!!
        }
    }

    interface HintHelper {
        fun onHintTextChanged(hint: String)
    }
}