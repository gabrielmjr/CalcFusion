package com.mjrfusion.app.calcfusion.listener

import android.view.View
import com.mjrfusion.app.calcfusion.R
import com.mjrfusion.app.calcfusion.calculator.Calculator

interface CalculatorButtonClickListener : View.OnClickListener, View.OnLongClickListener,
    Calculator {

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
            R.id.equals -> evaluate()
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
            cleanAll()
            return true
        }
        return false
    }
}