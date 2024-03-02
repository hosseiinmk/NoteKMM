package ir.hossein.notekmm.android.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<T>(uiState: T) : ViewModel() {

    private val stateFlow = MutableStateFlow(uiState)

    fun state(): StateFlow<T> = stateFlow

    fun stateValue(): T = state().value

    fun updateState(newState: T.() -> T) { stateFlow.value = newState(stateValue()) }

    fun baseViewModelScope(
        dispatcher: CoroutineDispatcher = Dispatchers.Main,
        block: suspend () -> Unit
    ) {
        viewModelScope.launch(dispatcher) { block() }
    }
}