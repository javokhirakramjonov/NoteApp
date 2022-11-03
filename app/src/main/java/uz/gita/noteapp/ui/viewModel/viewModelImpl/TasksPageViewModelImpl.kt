package uz.gita.noteapp.ui.viewModel.viewModelImpl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.noteapp.data.entity.TasksEntity
import uz.gita.noteapp.ui.viewModel.TasksPageViewModel
import uz.gita.noteapp.useCase.TasksPageUseCase
import javax.inject.Inject

@HiltViewModel
class TasksPageViewModelImpl @Inject constructor(
    private val useCase: TasksPageUseCase
) : ViewModel(), TasksPageViewModel {

    override val tasksLiveData = MutableLiveData<List<List<TasksEntity>>>()
    override val goEditorScreen = MutableLiveData<List<TasksEntity>>()

    override fun loadTasks() {
        useCase.getTasks().onEach {
            it.onSuccess { tasks ->
                tasksLiveData.value = tasks
            }
        }.launchIn(viewModelScope)
    }

    override fun updateTasks(task: List<TasksEntity>) {
        goEditorScreen.value = task
    }

    override fun deleteTasks(task: List<TasksEntity>) {
        useCase.deleteTask(task).onEach {
            it.onSuccess {

            }
        }.launchIn(viewModelScope)
    }

}