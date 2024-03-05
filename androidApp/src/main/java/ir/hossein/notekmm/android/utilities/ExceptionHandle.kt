package ir.hossein.notekmm.android.utilities

fun runInTryCatch(block: () -> Unit) {
    try {
        block()
    } catch (e: Exception) {
        println(e.printStackTrace())
    }
}