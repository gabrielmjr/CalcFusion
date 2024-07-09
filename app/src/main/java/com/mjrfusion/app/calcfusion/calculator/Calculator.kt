package com.mjrfusion.app.calcfusion.calculator

import android.util.Log
import com.ezylang.evalex.Expression

interface Calculator {
    var expression: String
    var canAddComma: Boolean

    fun addNumber(charNumber: Char) {
        expression += charNumber
    }

    fun evaluate() {
        val temp = expression.replace('×', '*').replace('÷', '/')
        onExpressionEvaluated(Expression(temp).evaluate().value.toString())
    }

    fun onExpressionEvaluated(result: String)

    fun addCommaIfPossible() {
        if (canAddComma) {
            expression +=
                if (expression.isEmpty())
                    "0,"
                else
                    when (expression[expression.length - 1]) {
                        '×', '÷', '+', '-' -> "0,"
                        else -> ","
                    }
            canAddComma = false
        }
    }

    fun removeLastChar() {
        if (expression.isNotEmpty())
            expression = expression.substring(0, expression.length - 1)
    }

    fun onExpressionChanged(newExpression: String)

    fun addSignalIfPossible(signal: Char) {
        when (signal) {
            '+', '-' -> {
                expression += signal
                canAddComma = true
                return
            }
        }
        if (canSignalBeAdded(signal) && !isLastCharASignal()) {
            expression += signal
            canAddComma = true
        }
    }

    fun canSignalBeAdded(signal: Char): Boolean {
        return if (expression.isEmpty()) false
        else if (signal == '×' || signal == '÷')
            when (expression[expression.length - 1]) {
                '+', '-' -> false
                else -> true
            }
        else true
    }

    fun isLastCharASignal(): Boolean {
        return if (expression.isEmpty()) false
        else
            when (expression[expression.length - 1]) {
                '÷', '×' -> true
                else -> false
            }
    }

    fun cleanAll() {
        expression = ""
        canAddComma = true
        onExpressionChanged(expression)
    }
}