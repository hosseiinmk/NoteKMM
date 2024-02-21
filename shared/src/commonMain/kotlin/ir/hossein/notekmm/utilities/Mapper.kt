package ir.hossein.notekmm.utilities

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class Mapper<M, P> {
    abstract fun map(model: M): P
    fun map(values: List<M>): Flow<List<P>> = flow { emit(values.map { map(it) }) }
}