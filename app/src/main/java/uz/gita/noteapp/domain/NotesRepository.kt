package uz.gita.noteapp.domain

import uz.gita.noteapp.data.entity.NoteEntity

interface NotesRepository {
    suspend fun getNotes(): List<NoteEntity>
    suspend fun addNote(note: NoteEntity)
    suspend fun delete(note: NoteEntity)
}