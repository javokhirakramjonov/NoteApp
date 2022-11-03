package uz.gita.noteapp.ui.viewModel.viewModelImpl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.noteapp.data.entity.EditorEntity
import uz.gita.noteapp.ui.viewModel.MainScreenViewModel


class MainScreenViewModelImpl : ViewModel(), MainScreenViewModel {
    override val pageLiveData = MutableLiveData<Int>()
    override val editorLiveData = MutableLiveData<EditorEntity>()

    override fun openPage(page: Int) {
        pageLiveData.value = page
    }

    override fun openEditor(entity: EditorEntity) {
        editorLiveData.value = entity
    }
}