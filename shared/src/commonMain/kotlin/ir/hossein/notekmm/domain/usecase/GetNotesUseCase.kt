package ir.hossein.notekmm.domain.usecase

import ir.hossein.notekmm.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface GetNotesUseCase {

    suspend operator fun invoke(): Flow<List<Note>>
}