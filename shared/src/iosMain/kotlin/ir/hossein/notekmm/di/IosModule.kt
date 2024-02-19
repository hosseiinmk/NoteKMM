package ir.hossein.notekmm.di

import ir.hossein.notekmm.data.local.NoteDatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    singleOf(::NoteDatabaseDriverFactory)
}