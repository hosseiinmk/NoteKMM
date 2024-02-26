package ir.hossein.notekmm.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import ir.hossein.notekmm.data.remote.TvShowApiClient
import ir.hossein.notekmm.data.remote.TvShowApiClientImpl
import ir.hossein.notekmm.data.repository.NoteRepositoryImpl
import ir.hossein.notekmm.data.repository.TvShowRepositoryImpl
import ir.hossein.notekmm.domain.repository.NoteRepository
import ir.hossein.notekmm.domain.repository.TvShowRepository
import ir.hossein.notekmm.domain.usecase.AddNoteUseCase
import ir.hossein.notekmm.domain.usecase.GetNotesUseCase
import ir.hossein.notekmm.domain.usecase.GetTvShowsUseCase
import ir.hossein.notekmm.domain.usecase.implementation.AddNoteUseCaseImpl
import ir.hossein.notekmm.domain.usecase.implementation.GetNotesUseCaseImpl
import ir.hossein.notekmm.domain.usecase.implementation.GetTvShowsUseCaseImpl
import ir.hossein.notekmm.utilities.TvShowMapper
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val remoteModule = module {
    singleOf(::TvShowApiClientImpl) {bind<TvShowApiClient>()}
    singleOf(::TvShowMapper)
    factoryOf(::TvShowRepositoryImpl)
}

val repositoryModule = module {
    singleOf(::NoteRepositoryImpl) {bind<NoteRepository>()}
    singleOf(::TvShowRepositoryImpl) {bind<TvShowRepository>()}
    factoryOf(::GetNotesUseCaseImpl)
    factoryOf(::AddNoteUseCaseImpl)
    factoryOf(::GetTvShowsUseCaseImpl)
}

val useCaseModule = module {
    singleOf(::GetNotesUseCaseImpl) {bind<GetNotesUseCase>()}
    singleOf(::AddNoteUseCaseImpl) {bind<AddNoteUseCase>()}
    singleOf(::GetTvShowsUseCaseImpl) {bind<GetTvShowsUseCase>()}
}

val httpClientModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        useAlternativeNames = false
                        coerceInputValues = true
                    }
                )
            }
        }
    }
}