package ir.hossein.notekmm.android.di

import ir.hossein.notekmm.android.presentation.addNote.AddNoteViewModel
import ir.hossein.notekmm.android.presentation.note.NotesViewModel
import ir.hossein.notekmm.android.presentation.tvShow.TvShowsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val androidModule = module {
    viewModelOf(::NotesViewModel)
    viewModelOf(::AddNoteViewModel)
    viewModelOf(::TvShowsViewModel)
}