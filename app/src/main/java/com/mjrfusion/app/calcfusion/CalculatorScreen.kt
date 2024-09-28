package com.mjrfusion.app.calcfusion

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mjrfusion.app.calcfusion.calculator.Calculator
import com.mjrfusion.app.calcfusion.box.AdditionalOperationsFragment
import com.mjrfusion.app.calcfusion.box.NumbersBox
import com.mjrfusion.app.calcfusion.ui.CalculatorButton
import com.mjrfusion.app.calcfusion.ui.theme.CalcFusionTheme
import com.mjrfusion.app.calcfusion.utils.ThreadUtils.threadPools
import kotlin.concurrent.thread

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
                NavSwipeScreen(
                    Modifier
                        .weight(1f)
                        .padding(horizontal = 6.dp),
                    arrangement = Arrangement.spacedBy(2.dp)
                )
                OperatorsColumn(
                    Modifier.padding(horizontal = 16.dp), arrangement = Arrangement.spacedBy(16.dp)
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
    Row {
        Text(
            modifier = Modifier.padding(16.dp),
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.White,
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append(expression)
                }
            })
        Text(
            modifier = Modifier.padding(16.dp),
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.LightGray,
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize
                    )
                ) {
                    append(hintText)
                }
            }
        )
    }
    calculator.apply {
        threadPools.execute {
            hintHelper = Calculator.HintHelper { hint ->
                hintText = hint
            }
        }
        calculatorViewModel.expressionViewModel.observeForever {
            threadPools.execute {
                expression = it
                previewResult()
            }
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
    calculator.calculatorViewModel.result.observeForever {
        threadPools.execute {
            color = if (calculator.isResultPreview) Color.LightGray
            else Color.White
            result = it
        }
    }
}

@Composable
fun RowScope.NavSwipeScreen(
    modifier: Modifier,
    arrangement: Arrangement.Horizontal
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { 2 }
    )
    HorizontalPager(
        modifier = modifier,
        state = pagerState
    ) { page ->
        if (page == 0) NumbersBox(
            modifier = modifier,
            arrangement = arrangement
        )
        else AdditionalOperationsFragment(
            modifier = modifier,
            arrangement = arrangement
        )
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
            CalculatorButton(
                stringResource(R.string.division),
                Modifier.combinedClickable { addSignalIfPossible('÷') })
            CalculatorButton(stringResource(R.string.multiplication), Modifier.combinedClickable {
                addSignalIfPossible(
                    '×'
                )
            })
            CalculatorButton(
                stringResource(R.string.subtraction),
                Modifier.combinedClickable { addSignalIfPossible('-') })
            CalculatorButton(
                stringResource(R.string.addition),
                Modifier.combinedClickable { addSignalIfPossible('+') })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorButtonPreview() {
    CalcFusionTheme {
        CalculatorScreen()
    }
}