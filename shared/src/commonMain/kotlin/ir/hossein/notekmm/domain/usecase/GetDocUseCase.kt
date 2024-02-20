package ir.hossein.notekmm.domain.usecase

import ir.hossein.notekmm.domain.model.Note

interface GetDocUseCase {

    suspend operator fun invoke(): String
}