package uz.gita.noteapp.ui.viewModel

import androidx.lifecycle.LiveData
import uz.gita.noteapp.data.entity.EditorEntity

interface MainScreenViewModel {
    val pageLiveData: LiveData<Int>
    val editorLiveData: LiveData<EditorEntity>

    fun openPage(page: Int)
    fun openEditor(entity: EditorEntity)
}