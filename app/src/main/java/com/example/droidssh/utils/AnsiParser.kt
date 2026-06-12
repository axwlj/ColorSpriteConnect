package com.example.droidssh.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight

object AnsiParser {
    private val ANSI_COLOR_MAP = mapOf(
        "30" to Color(0xFF000000), "31" to Color(0xFFCD3131), "32" to Color(0xFF0DBC79),
        "33" to Color(0xFFE5E510), "34" to Color(0xFF2472C8), "35" to Color(0xFFBC3FBC),
        "36" to Color(0xFF11A8CD), "37" to Color(0xFFE5E5E5), "90" to Color(0xFF666666),
        "0"  to null // Reset
    )

    fun parse(text: String): AnnotatedString {
        // 增强正则：匹配 SGR (m) 序列以及基本的移动序列 (A,B,C,D,H,J,K)
        val regex = Regex("\u001B\\[([0-9;]*)([mABCDEHJK])")
        val matches = regex.findAll(text)

        return buildAnnotatedString {
            var lastIndex = 0
            matches.forEach { match ->
                append(text.substring(lastIndex, match.range.first))
                val params = match.groupValues[1]
                val command = match.groupValues[2]

                if (command == "m") {
                    applySgr(params)
                }
                // 其他命令（如 J 清屏）在简易版中暂不渲染样式，仅作为占位
                lastIndex = match.range.last + 1
            }
            append(text.substring(lastIndex))
        }
    }

    private fun AnnotatedString.Builder.applySgr(params: String) {
        val codes = params.split(";")
        codes.forEach { code ->
            when {
                code == "0" || code == "" -> { /* Reset style logic */ }
                code == "1" -> pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                ANSI_COLOR_MAP.containsKey(code) -> {
                    ANSI_COLOR_MAP[code]?.let { pushStyle(SpanStyle(color = it)) }
                }
                // 支持 256 色基础处理 (38;5;n)
                params.startsWith("38;5;") -> {
                    val colorIndex = params.split(";").getOrNull(2)?.toIntOrNull()
                    if (colorIndex != null) {
                        pushStyle(SpanStyle(color = get256Color(colorIndex)))
                    }
                }
            }
        }
    }

    private fun get256Color(index: Int): Color {
        // 简化的 256 色映射逻辑
        return when (index) {
            in 0..15 -> Color.Gray
            else -> Color(0xFF0DBC79) // 默认绿色
        }
    }
}
