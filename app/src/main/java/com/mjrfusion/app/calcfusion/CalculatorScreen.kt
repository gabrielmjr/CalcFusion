package com.mjrfusion.app.calcfusion

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mjrfusion.app.calcfusion.calculator.Calculator
import com.mjrfusion.app.calcfusion.ui.theme.CalcFusionTheme
import kotlin.properties.Delegates

val calculator = Calculator.getInstance()

@Composable
fun CalculatorScreen() {
    CalcFusionTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End,
            ) {
                ExpressionView()
            }
            Row(
                modifier = Modifier.weight(5f),
            ) {
                NumbersBox(
                    Modifier
                        .weight(1f)
                        .padding(horizontal = 6.dp),
                    arrangement = Arrangement.spacedBy(2.dp)
                )
                OperatorsColumn(
                    Modifier.padding(horizontal = 8.dp), arrangement = Arrangement.spacedBy(8.dp)
                )
            }
        }
    }
}

@Composable
fun ExpressionView() {
    ExpressionLabel()
    ResultLabel()
}

@Composable
fun ExpressionLabel() {
    var expression by remember { mutableStateOf("") }
    var hintText by remember { mutableStateOf("") }
    Text(modifier = Modifier.padding(16.dp), text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.White,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                fontWeight = FontWeight.SemiBold
            )
        ) {
            append(expression)
        }
        withStyle(
            style = SpanStyle(
                color = Color.LightGray,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                fontWeight = FontWeight.SemiBold
            )
        ) {
            append(hintText)
        }
    })
    calculator.apply {
        hintHelper = Calculator.HintHelper { hint ->
            hintText = hint
        }
        calculatorViewModel.expressionViewModel.observeForever {
            expression = it
            previewResult()
        }
    }
}

@Composable
fun ResultLabel() {
    var result by remember { mutableStateOf("") }
    var color by remember { mutableStateOf(Color.Transparent) }
    Text(
        modifier = Modifier
            .padding(16.dp),
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = color,
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append(result)
            }
        }
    )
    calculator.apply {
        calculatorViewModel.result.observeForever {
            color = if (calculator.isResultPreview) Color.LightGray
            else Color.White
            result = it
        }
    }
}

@Composable
fun RowScope.NumbersBox(
    modifier: Modifier, arrangement: Arrangement.Horizontal
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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OperatorsColumn(
    modifier: Modifier, arrangement: Arrangement.Vertical
) {
    Column(
        modifier = modifier, verticalArrangement = arrangement
    ) {
        calculator.apply {
            CalculatorButton(
                stringResource(R.string.del),
                Modifier.combinedClickable(onClick = { removeLastChar() },
                    onLongClick = { cleanAll() })
            )
            CalculatorButton(stringResource(R.string.division),
                Modifier.combinedClickable { addSignalIfPossible('÷') })
            CalculatorButton(stringResource(R.string.multiplication), Modifier.combinedClickable {
                addSignalIfPossible(
                    '×'
                )
            })
            CalculatorButton(stringResource(R.string.subtraction),
                Modifier.combinedClickable { addSignalIfPossible('-') })
            CalculatorButton(stringResource(R.string.addition),
                Modifier.combinedClickable { addSignalIfPossible('+') })
        }
    }
}

@Composable
fun RowScope.CalculatorButton(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.DarkGray,
    textStyle: TextStyle = TextStyle.Default,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(48.dp))
            .background(color)
            .aspectRatio(1F)
            .weight(1F)
            .clickable(onClick = onClick)
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = textStyle,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}

@Composable
fun ColumnScope.CalculatorButton(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.DarkGray,
    textStyle: TextStyle = TextStyle.Default,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(48.dp))
            .background(color)
            .aspectRatio(1F)
            .weight(1F), contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = textStyle,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorButtonPreview() {
    CalcFusionTheme {
        CalculatorScreen()
    }
}