package uz.gita.noteapp.useCase.useCaseImpl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.noteapp.data.entity.NoteEntity
import uz.gita.noteapp.domain.NotesRepository
import uz.gita.noteapp.useCase.AddNoteScreenUseCase
import javax.inject.Inject

class AddNoteScreenUseCaseImpl @Inject constructor(
    private val repository: NotesRepository
) : AddNoteScreenUseCase {

    override fun saveNote(note: NoteEntity) = flow<Result<Unit>> {
        repository.addNote(note)
        emit(Result.success(Unit))
    }.flowOn(Dispatchers.IO)
}