package ir.hossein.notekmm.android.utilities

import androidx.compose.ui.graphics.Color
import ir.hossein.notekmm.android.core.globalStateValue

private val list = mutableListOf<Color>()

fun generateColorList(size: Int): List<Color> {
    list.clear()
    repeat(size) {
        list.add(randomColor())
    }
    return list
}

fun randomColor(): Color = (when (globalStateValue().darkTheme) {
    true -> {
        listOf(
            Color(0xFF678283),
            Color(0xFF297550),
            Color(0xFF53754C),
            Color(0xFF7E7449),
            Color(0xFF7C6C57)
        )
    }

    else -> {
        listOf(
            Color(0xFFACDDDE),
            Color(0xFFCAF1DE),
            Color(0xFFE1F8DC),
            Color(0xFFFEF8DD),
            Color(0xFFFFE7C7)
        )
    }
}).random()