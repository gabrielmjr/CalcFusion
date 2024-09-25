package com.mjrfusion.app.calcfusion.box

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mjrfusion.app.calcfusion.R
import com.mjrfusion.app.calcfusion.calculator
import com.mjrfusion.app.calcfusion.ui.CalculatorButton

@Composable
fun RowScope.NumbersBox(
    modifier: Modifier,
    arrangement: Arrangement.Horizontal
) {
    Column(Modifier.weight(1f)) {
        FirstLineNumbers(modifier, arrangement)
        SecondLineNumbers(modifier, arrangement)
        ThirdLineNumbers(modifier, arrangement)
        FourthLine(modifier, arrangement)
    }
}

@Composable
fun FirstLineNumbers(
    modifier: Modifier, arrangement: Arrangement.Horizontal
) {
    Row(
        modifier = modifier, horizontalArrangement = arrangement
    ) {
       calculator.apply {
            CalculatorButton(stringResource(R.string.num_7)) { addNumber('7') }
            CalculatorButton(stringResource(R.string.num_8)) { addNumber('8') }
            CalculatorButton(stringResource(R.string.num_9)) { addNumber('9') }
        }
    }
}

@Composable
fun SecondLineNumbers(
    modifier: Modifier, arrangement: Arrangement.Horizontal
) {
    Row(
        modifier = modifier, horizontalArrangement = arrangement
    ) {
        calculator.apply {
            CalculatorButton(stringResource(R.string.num_4)) { addNumber('4') }
            CalculatorButton(stringResource(R.string.num_5)) { addNumber('5') }
            CalculatorButton(stringResource(R.string.num_6)) { addNumber('6') }
        }
    }
}

@Composable
fun ThirdLineNumbers(
    modifier: Modifier, arrangement: Arrangement.Horizontal
) {
    Row(
        modifier = modifier, horizontalArrangement = arrangement
    ) {
        calculator.apply {
            CalculatorButton(stringResource(R.string.num_1)) { addNumber('1') }
            CalculatorButton(stringResource(R.string.num_2)) { addNumber('2') }
            CalculatorButton(stringResource(R.string.num_3)) { addNumber('3') }
        }
    }
}

@Composable
fun FourthLine(
    modifier: Modifier, arrangement: Arrangement.Horizontal
) {
    Row(
        modifier = modifier, horizontalArrangement = arrangement
    ) {
        calculator.apply {
            CalculatorButton(stringResource(R.string.dot)) { addDotIfPossible() }
            CalculatorButton(stringResource(R.string.num_0)) { addNumber('0') }
            CalculatorButton(stringResource(R.string.equals)) { evaluate(false) }
        }
    }
}