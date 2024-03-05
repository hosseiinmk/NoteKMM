package ir.hossein.notekmm.android.core

import kotlinx.coroutines.flow.MutableStateFlow

data class GlobalState(
    val darkTheme: Boolean = false
)

private val globalMutableStateFlow = MutableStateFlow(GlobalState())

fun globalStateValue() = globalMutableStateFlow.value

fun setGlobalState(newState: GlobalState) {
    globalMutableStateFlow.value = newState
}
