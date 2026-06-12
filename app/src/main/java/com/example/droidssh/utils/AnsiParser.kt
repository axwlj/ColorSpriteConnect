package com.example.droidssh.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight

object AnsiParser {
    private val ANSI_COLOR_MAP = mapOf(
        "30" to Color.Black,
        "31" to Color.Red,
        "32" to Color.Green,
        "33" to Color.Yellow,
        "34" to Color.Blue,
        "35" to Color.Magenta,
        "36" to Color.Cyan,
        "37" to Color.White,
        "90" to Color.Gray,
        "0"  to null // Reset
    )

    fun parse(text: String): AnnotatedString {
        val parts = text.split("\u001B[")
        if (parts.size == 1) return AnnotatedString(text)

        return buildAnnotatedString {
            append(parts[0])
            for (i in 1 until parts.size) {
                val part = parts[i]
                val mIndex = part.indexOf('m')
                if (mIndex != -1) {
                    val code = part.substring(0, mIndex)
                    val content = part.substring(mIndex + 1)
                    val color = ANSI_COLOR_MAP[code]

                    if (color != null) {
                        pushStyle(SpanStyle(color = color))
                        append(content)
                        pop()
                    } else if (code == "1") {
                        pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                        append(content)
                        pop()
                    } else {
                        append(content)
                    }
                } else {
                    append(part)
                }
            }
        }
    }
}
