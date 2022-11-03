package uz.gita.noteapp.useCase.useCaseImpl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.noteapp.data.entity.NoteEntity
import uz.gita.noteapp.domain.NotesRepository
import uz.gita.noteapp.useCase.NotesPageUseCase
import javax.inject.Inject

class NotesPageUseCaseImpl @Inject constructor(
    private val repository: NotesRepository
) : NotesPageUseCase {
    override fun getNotes() = flow<Result<List<NoteEntity>>> {
        val data = repository.getNotes()
        emit(Result.success(data))
    }

    override fun deleteNote(note: NoteEntity) = flow<Result<Unit>> {
        repository.delete(note)
        emit(Result.success(Unit))
    }.flowOn(Dispatchers.IO)

}