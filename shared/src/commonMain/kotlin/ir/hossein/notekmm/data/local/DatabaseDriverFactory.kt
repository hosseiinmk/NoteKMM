package ir.hossein.notekmm.data.local

import app.cash.sqldelight.db.SqlDriver

expect class NoteDatabaseDriverFactory {

    fun createDriver(): SqlDriver
}