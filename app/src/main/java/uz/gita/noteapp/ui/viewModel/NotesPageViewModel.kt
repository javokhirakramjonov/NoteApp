package uz.gita.noteapp.ui.viewModel

import androidx.lifecycle.LiveData
import uz.gita.noteapp.data.entity.NoteEntity

interface NotesPageViewModel {
    val notesLiveData: LiveData<List<NoteEntity>>
    val goEditorScreen: LiveData<NoteEntity>

    fun loadNotes()
    fun updateNote(note: NoteEntity)
    fun deleteNote(note: NoteEntity)
}