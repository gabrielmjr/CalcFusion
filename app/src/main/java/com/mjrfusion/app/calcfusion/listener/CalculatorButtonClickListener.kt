package com.mjrfusion.app.calcfusion.listener

import android.view.View
import com.mjrfusion.app.calcfusion.R

interface CalculatorButtonClickListener : View.OnClickListener, View.OnLongClickListener {
    var expression: String
    var canAddComma: Boolean

    override fun onClick(view: View) {
        when (view.id) {
            R.id.num_0 -> addNumber('0')
            R.id.num_1 -> addNumber('1')
            R.id.num_2 -> addNumber('2')
            R.id.num_3 -> addNumber('3')
            R.id.num_4 -> addNumber('4')
            R.id.num_5 -> addNumber('5')
            R.id.num_6 -> addNumber('6')
            R.id.num_7 -> addNumber('7')
            R.id.num_8 -> addNumber('8')
            R.id.num_9 -> addNumber('9')
            R.id.comma -> addCommaIfPossible()
            R.id.equals -> TODO()
            R.id.del -> removeLastChar()
            R.id.multiplication -> addSignalIfPossible('×')
            R.id.division -> addSignalIfPossible('÷')
            R.id.subtraction -> addSignalIfPossible('-')
            R.id.addition -> addSignalIfPossible('+')
        }
        onExpressionChanged(expression)
    }

    override fun onLongClick(view: View): Boolean {
        if (view.id == R.id.del) {
            expression = ""
            canAddComma = true
            onExpressionChanged(expression)
            return true
        }
        return false
    }

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

    fun addNumber(charNumber: Char) {
        expression += charNumber
    }

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

    fun onExpressionChanged(newExpression: String)
}