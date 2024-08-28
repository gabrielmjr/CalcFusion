package com.mjrfusion.app.calcfusion.extensions

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * This class is used to test the CalculatorExtensions.kt class
 */
class CalculatorExtensionTest {
    private val string1 = "cos(45)+sin"
    private val string2 = "tan(45)-cos"
    private val string3 = "tan(60)-tan"
    private val string4 = "tan(45)-cos(360)"
    /**
     * This method is used to test the isLastItemBasicTrigonometry method
     * The method being tested is used to check if the lasts chars inside a
     * String are basic trigonometric (sin, cos, tan) functions
     */
    @Test
    fun isLastItemBasicTrigonometryTest() {
        assertEquals(string1.isLastItemBasicTrigonometry(), true)
        assertEquals(string2.isLastItemBasicTrigonometry(), true)
        assertEquals(string3.isLastItemBasicTrigonometry(), true)
        assertEquals(string4.isLastItemBasicTrigonometry(), false)
    }

    @Test
    fun getLastBasicTrigonometry() {
        assertEquals(string1.getLastBasicTrigonometryFunction(), "sin")
        assertEquals(string2.getLastBasicTrigonometryFunction(), "cos")
        assertEquals(string3.getLastBasicTrigonometryFunction(), "tan")
    }

    @Test
    fun removeBasicTrigonometry() {
        assertEquals(string1.removeBasicTrigonometry(), "cos(45)+")
        assertEquals(string2.removeBasicTrigonometry(), "tan(45)-")
        assertEquals(string3.removeBasicTrigonometry(), "tan(60)-")
    }
}
