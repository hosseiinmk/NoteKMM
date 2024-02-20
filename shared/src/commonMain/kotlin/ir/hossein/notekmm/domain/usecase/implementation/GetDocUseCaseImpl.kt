package ir.hossein.notekmm.domain.usecase.implementation

import ir.hossein.notekmm.domain.repository.NoteRepository
import ir.hossein.notekmm.domain.usecase.GetDocUseCase

class GetDocUseCaseImpl(private val noteRepository: NoteRepository) : GetDocUseCase {

    override suspend operator fun invoke() = noteRepository.getDoc()
}