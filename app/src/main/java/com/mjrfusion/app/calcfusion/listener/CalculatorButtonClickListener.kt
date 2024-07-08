package com.mjrfusion.app.calcfusion.listener

import android.view.View
import com.mjrfusion.app.calcfusion.R
import com.mjrfusion.app.calcfusion.databinding.FragmentCalculatorBinding

interface CalculatorButtonClickListener : View.OnClickListener {
    var expression: String

    override fun onClick(view: View) {
        when (view.id) {
            R.id.num_0 -> expression += "0"
            R.id.num_1 -> expression += "1"
            R.id.num_2 -> expression += "2"
            R.id.num_3 -> expression += "3"
            R.id.num_4 -> expression += "4"
            R.id.num_5 -> expression += "5"
            R.id.num_6 -> expression += "6"
            R.id.num_7 -> expression += "7"
            R.id.num_8 -> expression += "8"
            R.id.num_9 -> expression += "9"
            R.id.comma -> expression += ","
            R.id.equals -> TODO()
            R.id.del -> expression.substring(0, expression.length-2)
            R.id.multiplication -> expression += "×"
            R.id.division -> expression += "÷"
            R.id.subtraction -> expression += "-"
            R.id.addition -> expression += "+"
        }
        onExpressionChanged(expression)
    }

     fun onExpressionChanged(newExpression: String)
}
