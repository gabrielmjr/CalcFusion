package com.mjrfusion.app.calcfusion.extensions

fun String.isLastItemBasicTrigonometry(): Boolean {
    var isBasicTrigonometricContained = false
    if (this.length < 3)
        return false
    for (basicTrigonometric in arrayOf("sin", "cos", "tan"))
        if (getLastBasicTrigonometryFunction().contains(basicTrigonometric))
            isBasicTrigonometricContained = true
    return isBasicTrigonometricContained
}

fun String.getLastBasicTrigonometryFunction(): String {
    val length = toString().length
    return this.substring(length - 3, length)
}

fun String.removeBasicTrigonometry(): String {
    return this.substring(0, this.length - 3)
}
