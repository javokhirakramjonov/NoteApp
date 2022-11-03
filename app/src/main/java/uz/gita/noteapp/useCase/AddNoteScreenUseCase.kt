package uz.gita.noteapp.useCase

import kotlinx.coroutines.flow.Flow
import uz.gita.noteapp.data.entity.NoteEntity

interface AddNoteScreenUseCase {
    fun saveNote(note: NoteEntity): Flow<Result<Unit>>
}