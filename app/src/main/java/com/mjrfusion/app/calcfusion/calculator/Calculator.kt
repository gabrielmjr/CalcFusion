package com.mjrfusion.app.calcfusion.calculator

import com.ezylang.evalex.Expression
import com.mjrfusion.app.calcfusion.extensions.isLastItemBasicTrigonometry
import com.mjrfusion.app.calcfusion.extensions.removeBasicTrigonometry
import com.mjrfusion.app.calcfusion.viewmodel.CalculatorViewModel
import java.math.MathContext
import java.math.RoundingMode

class Calculator {
    lateinit var calculatorViewModel: CalculatorViewModel
    lateinit var hintHelper: HintHelper
    lateinit var expressionValidation: ExpressionValidation
    var isInvalidExpression = false

    private var expression = ""
    private var canAddDot = true
    private var openedBrackets = 0
    private var hint = ""

    fun addNumber(charNumber: Char) {
        expression += charNumber
        calculatorViewModel.expressionViewModel.postValue(expression)
    }

    fun evaluate() {
        try {
            if (expression.isEmpty()) {
                calculatorViewModel.result.postValue("0")
                return
            }
            for (i in 1..openedBrackets)
                closeBracket()
            val temp = expression.replace('×', '*')
                .replace('÷', '/')
                .replace("π", "PI")
                .replace('e', 'E')
                .replace("√", "SQRT")

            calculatorViewModel.result.postValue(
                removeLastZero(
                    Expression(temp).evaluate().numberValue.round(
                        MathContext(15, RoundingMode.HALF_UP)
                    ).toString()
                )
            )
        } catch (_: Exception) {
            isInvalidExpression = true
            expressionValidation.onExpressionInvalid()
        }
    }

    private fun removeLastZero(result: String): String {
        val length = result.length - 2
        if (length > 0 && result[length] == '0')
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
            normalizeIfLastCharIsBracket()
            expression = if (expression.isLastItemBasicTrigonometry())
                expression.removeBasicTrigonometry()
            else if (expression.last() == '√')
                expression.substring(0, expression.length - 1)
            else
                expression.substring(0, expression.length - 1)
            calculatorViewModel.expressionViewModel.postValue(expression)
        }
    }

    private fun normalizeIfLastCharIsBracket() {
        if (expression.last() == ')') {
            hint += ")"
            openedBrackets++
            hintHelper.onHintTextChanged(hint)
        } else if (expression.last() == '(') {
            hint = hint.substring(0, hint.length - 1)
            openedBrackets--
            hintHelper.onHintTextChanged(hint)
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

    fun addPi() {
        expression += "π"
        calculatorViewModel.expressionViewModel.postValue(expression)
    }

    fun addEuler() {
        expression += "e"
        calculatorViewModel.expressionViewModel.postValue(expression)
    }

    fun addExponent() {
        expression += "^"
        calculatorViewModel.expressionViewModel.postValue(expression)
    }

    fun addSquareRoot() {
        expression += "√"
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

    fun setPercentage() {
        expression += "%"
        calculatorViewModel.expressionViewModel.postValue(expression)
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

    fun interface HintHelper {
        fun onHintTextChanged(hint: String)
    }

    interface ExpressionValidation {
        fun onExpressionInvalid()
    }
}
