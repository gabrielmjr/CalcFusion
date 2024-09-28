package com.mjrfusion.app.calcfusion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.mjrfusion.app.calcfusion.calculator.Calculator
import com.mjrfusion.app.calcfusion.ui.theme.CalcFusionTheme
import com.mjrfusion.app.calcfusion.viewmodel.CalculatorViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Calculator.getInstance().calculatorViewModel = ViewModelProvider(this)[CalculatorViewModel::class.java]
        setContent {
            CalcFusionTheme {
                CalculatorScreen()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CalculatorScreenPreview() {
    CalcFusionTheme {
        Column (Modifier
            .fillMaxSize()
            .background(Color.Green)) {
            Text(text = "asfdsfdsfdsfsdfdsffdsf")
        }
    }
}