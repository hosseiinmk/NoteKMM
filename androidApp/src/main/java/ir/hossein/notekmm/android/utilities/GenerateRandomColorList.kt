package ir.hossein.notekmm.android.utilities

import androidx.compose.ui.graphics.Color

fun generateRandomColorList(itemsSize: Int): List<Color> {
    val list = mutableListOf<Color>()
    repeat(itemsSize) {
        list.add(randomColor())
    }
    return list
}