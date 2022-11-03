package uz.gita.noteapp.ui.viewModel.viewModelImpl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.noteapp.data.entity.NoteEntity
import uz.gita.noteapp.ui.viewModel.NotesPageViewModel
import uz.gita.noteapp.useCase.NotesPageUseCase
import javax.inject.Inject

@HiltViewModel
class NotesPageViewModelImpl @Inject constructor(
    private val useCase: NotesPageUseCase
): ViewModel(), NotesPageViewModel {
    override val notesLiveData = MutableLiveData<List<NoteEntity>>()
    override val goEditorScreen = MutableLiveData<NoteEntity>()

    override fun loadNotes() {
        useCase.getNotes().onEach {
            it.onSuccess { notes ->
                notesLiveData.value = notes
            }
        }.launchIn(viewModelScope)
    }

    override fun updateNote(note: NoteEntity) {
        goEditorScreen.value = note
    }

    override fun deleteNote(note: NoteEntity) {
        useCase.deleteNote(note).onEach {
            it.onSuccess {

            }
        }.launchIn(viewModelScope)
    }
}