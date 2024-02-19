package ir.hossein.notekmm.data.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import ir.hossein.notekmm.database.NoteDatabase

actual class NoteDatabaseDriverFactory(private val context: Context) {

    actual fun createDriver(): SqlDriver = AndroidSqliteDriver(
        NoteDatabase.Schema, context, "note_database.db"
    )
}