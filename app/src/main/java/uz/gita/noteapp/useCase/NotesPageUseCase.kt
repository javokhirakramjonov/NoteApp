package uz.gita.noteapp.useCase

import kotlinx.coroutines.flow.Flow
import uz.gita.noteapp.data.entity.NoteEntity
import uz.gita.noteapp.domain.NotesRepository
import javax.inject.Inject

interface NotesPageUseCase {
    fun getNotes() : Flow<Result<List<NoteEntity>>>
    fun deleteNote(note: NoteEntity) : Flow<Result<Unit>>
}