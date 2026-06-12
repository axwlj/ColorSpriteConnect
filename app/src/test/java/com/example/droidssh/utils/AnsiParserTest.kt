package com.example.droidssh.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class AnsiParserTest {
    @Test
    fun testParseSimpleColor() {
        val input = "\u001B[31mRed Text"
        val result = AnsiParser.parse(input)
        assertEquals("Red Text", result.text)
    }

    @Test
    fun testParseReset() {
        val input = "Normal \u001B[0mReset"
        val result = AnsiParser.parse(input)
        assertEquals("Normal Reset", result.text)
    }
}
