package com.mjrfusion.app.calcfusion.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class CalculatorViewModel(application: Application) : AndroidViewModel(application) {
    val expressionViewModel: MutableLiveData<String> = MutableLiveData()
    val result: MutableLiveData<String> = MutableLiveData()
}
