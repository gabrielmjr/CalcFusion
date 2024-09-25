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
fun RowScope.AdditionalOperationsFragment(
    modifier: Modifier,
    arrangement: Arrangement.Horizontal
) {
    Column(Modifier.weight(1F)) {
        FirstLineOperations(modifier, arrangement)
        SecondLineOperations(modifier, arrangement)
        ThirdLineOperations(modifier, arrangement)
        FourthLineOperations(modifier, arrangement)
    }
}

@Composable
fun FirstLineOperations(
    modifier: Modifier,
    arrangement: Arrangement.Horizontal
) {
    Row(modifier = modifier,
        horizontalArrangement = arrangement
    ) {
        calculator.apply {
            CalculatorButton(stringResource(R.string.open_bracket)) { openBracket() }
            CalculatorButton(stringResource(R.string.close_bracket)) { closeBracket() }
            CalculatorButton(stringResource(R.string.percentage)) { addPercentage() }
        }
    }
}

@Composable
fun SecondLineOperations(
    modifier: Modifier,
    arrangement: Arrangement.Horizontal
) {
    Row(modifier = modifier,
        horizontalArrangement = arrangement
    ) {
        calculator.apply {
            CalculatorButton(stringResource(R.string.sin)) { addSin() }
            CalculatorButton(stringResource(R.string.cos)) { addCos() }
            CalculatorButton(stringResource(R.string.tangent)) { addTangent() }
        }
    }
}

@Composable
fun ThirdLineOperations(
    modifier: Modifier,
    arrangement: Arrangement.Horizontal
) {
    Row(modifier = modifier,
        horizontalArrangement = arrangement
    ) {
        calculator.apply {
            CalculatorButton(stringResource(R.string.pi)) { addPi() }
            CalculatorButton(stringResource(R.string.euler)) { addEuler() }
            CalculatorButton(stringResource(R.string.exponent)) { addExponent() }
        }
    }
}

@Composable
fun FourthLineOperations(
    modifier: Modifier,
    arrangement: Arrangement.Horizontal
) {
    Row(modifier = modifier,
        horizontalArrangement = arrangement
    ) {
        calculator.apply {
            CalculatorButton(stringResource(R.string.inverse)) {  }
            CalculatorButton(stringResource(R.string.radians)) {  }
            CalculatorButton(stringResource(R.string.square_root)) { addSquareRoot() }
        }
    }
}