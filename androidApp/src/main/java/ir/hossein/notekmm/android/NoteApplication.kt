package ir.hossein.notekmm.android

import android.app.Application
import ir.hossein.notekmm.android.di.androidModule
import ir.hossein.notekmm.di.platformModule
import ir.hossein.notekmm.di.repositoryModule
import ir.hossein.notekmm.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NoteApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@NoteApplication)
            modules(androidModule, repositoryModule, useCaseModule, platformModule())
        }
    }
}