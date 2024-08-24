package com.mjrfusion.app.calcfusion.extensions

fun String.isLastItemBasicTrigonometry(): Boolean {
    var isBasicTrigonometricContained = false
    if (this.length < 3)
        return false
    for (basicTrigonometric in arrayOf("sin", "cos", "tan"))
        if (getLastBasicTrigonometry().contains(basicTrigonometric))
            isBasicTrigonometricContained = true
    return isBasicTrigonometricContained
}

private fun String.getLastBasicTrigonometry(): String {
    val length = toString().length
    return this.substring(length - 3, length)
}

fun String.removeBasicTrigonometry(): String {
    return this.substring(0, this.length - 3)
}
