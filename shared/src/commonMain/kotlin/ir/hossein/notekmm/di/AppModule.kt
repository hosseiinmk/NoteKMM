package ir.hossein.notekmm.di

import ir.hossein.notekmm.data.repository.NoteRepositoryImpl
import ir.hossein.notekmm.domain.repository.NoteRepository
import ir.hossein.notekmm.domain.usecase.AddNoteUseCase
import ir.hossein.notekmm.domain.usecase.GetDocUseCase
import ir.hossein.notekmm.domain.usecase.GetNotesUseCase
import ir.hossein.notekmm.domain.usecase.implementation.AddNoteUseCaseImpl
import ir.hossein.notekmm.domain.usecase.implementation.GetDocUseCaseImpl
import ir.hossein.notekmm.domain.usecase.implementation.GetNotesUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::NoteRepositoryImpl) {bind<NoteRepository>()}
    factoryOf(::GetNotesUseCaseImpl)
    factoryOf(::AddNoteUseCaseImpl)
    factoryOf(::GetDocUseCaseImpl)
}

val useCaseModule = module {
    singleOf(::GetNotesUseCaseImpl) {bind<GetNotesUseCase>()}
    singleOf(::AddNoteUseCaseImpl) {bind<AddNoteUseCase>()}
    singleOf(::GetDocUseCaseImpl) {bind<GetDocUseCase>()}
}