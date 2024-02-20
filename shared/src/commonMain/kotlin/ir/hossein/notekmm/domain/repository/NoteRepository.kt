package ir.hossein.notekmm.domain.repository

import ir.hossein.notekmm.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun addNote(note: Note)

    suspend fun getNotes(): Flow<List<Note>>

    suspend fun getDoc(): String
}