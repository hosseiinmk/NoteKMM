package ir.hossein.notekmm.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import ir.hossein.notekmm.database.NoteDatabase

actual class NoteDatabaseDriverFactory {

    actual fun createDriver(): SqlDriver = NativeSqliteDriver(
        NoteDatabase.Schema, "note_database.db"
    )
}