package uz.gita.noteapp.domain.repotiroyImpl

import uz.gita.noteapp.data.dao.NoteDao
import uz.gita.noteapp.data.database.AppDatabase
import uz.gita.noteapp.data.entity.NoteEntity
import uz.gita.noteapp.domain.NotesRepository
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val notes: NoteDao
) : NotesRepository {

    override suspend fun getNotes(): List<NoteEntity> {
        val data = ArrayList<NoteEntity>()
        val cursor = notes.getNotes()
        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                cursor.apply {
                    data.add(NoteEntity(getInt(0), getString(1) ?: "", getString(2) ?: "", getString(3) ?: ""))
                    moveToNext()
                }
            }
        }
        cursor.close()
        return data
    }

    override suspend fun addNote(note: NoteEntity) {
        notes.insert(note)
    }

    override suspend fun delete(note: NoteEntity) {
        notes.delete(note)
    }
}