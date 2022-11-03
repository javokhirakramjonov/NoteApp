package uz.gita.noteapp.ui.viewModel.viewModelImpl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.noteapp.data.entity.NoteEntity
import uz.gita.noteapp.ui.viewModel.AddNoteScreenViewModel
import uz.gita.noteapp.useCase.AddNoteScreenUseCase
import javax.inject.Inject

@HiltViewModel
class AddNoteScreenViewModelImpl @Inject constructor(
    private val useCase: AddNoteScreenUseCase
) : ViewModel(), AddNoteScreenViewModel {

    override val backToMain = MutableLiveData<Unit>()
    override val messageToUser = MutableLiveData<String>()

    override fun saveNote(note: NoteEntity) {
        useCase.saveNote(note).onEach {
            it.onSuccess {
                messageToUser.value = "Saved successfully"
            }
        }.launchIn(viewModelScope)
    }

    override fun backToMain() {
        backToMain.value = Unit
    }
}