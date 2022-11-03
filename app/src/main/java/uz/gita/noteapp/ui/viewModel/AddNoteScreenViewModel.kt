package uz.gita.noteapp.ui.viewModel

import androidx.lifecycle.LiveData
import uz.gita.noteapp.data.entity.NoteEntity

interface AddNoteScreenViewModel {
    val backToMain: LiveData<Unit>
    val messageToUser: LiveData<String>

    fun saveNote(note: NoteEntity)
    fun backToMain()
}