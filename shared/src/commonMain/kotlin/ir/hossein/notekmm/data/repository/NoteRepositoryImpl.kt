package ir.hossein.notekmm.data.repository

import app.cash.sqldelight.coroutines.asFlow
import ir.hossein.notekmm.data.local.NoteDatabaseDriverFactory
import ir.hossein.notekmm.database.NoteDatabase
import ir.hossein.notekmm.domain.model.Note
import ir.hossein.notekmm.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(driver: NoteDatabaseDriverFactory) : NoteRepository {

    private val database = NoteDatabase(driver = driver.createDriver())
    private val dbQuery = database.noteDatabaseQueries

    override suspend fun addNote(note: Note) {
        dbQuery.transaction {
            dbQuery.addNote(title = note.title, content = note.content)
        }
    }

    override suspend fun getNotes(): Flow<List<Note>> =
        dbQuery.getNotes(::mapToDomain).asFlow().map { it.executeAsList() }

    private fun mapToDomain(
        id: Long,
        title: String,
        content: String
    ): Note = Note(id = id.toInt(), title = title, content = content)
}