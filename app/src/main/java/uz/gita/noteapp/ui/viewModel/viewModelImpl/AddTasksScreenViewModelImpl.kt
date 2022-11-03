package uz.gita.noteapp.ui.viewModel.viewModelImpl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.noteapp.data.entity.TasksEntity
import uz.gita.noteapp.ui.viewModel.AddTasksScreenViewModel
import uz.gita.noteapp.useCase.AddTasksScreenUseCase
import javax.inject.Inject

@HiltViewModel
class AddTasksScreenViewModelImpl @Inject constructor(
    private val useCase: AddTasksScreenUseCase
) : ViewModel(), AddTasksScreenViewModel {

    override val backToMain = MutableLiveData<Unit>()
    override val messageToUser = MutableLiveData<String>()
    override val task = MutableLiveData<TasksEntity>()
    override val changeTaskLiveData = MutableLiveData<Pair<Int, TasksEntity>>()
    override val deleteTaskLiveData = MutableLiveData<Int>()

    override fun changeTask(index: Int, task: TasksEntity) {
        changeTaskLiveData.value = Pair(index, task)
    }

    override fun deleteTask(index: Int) {
        deleteTaskLiveData.value = index
    }

    override fun addSubTask(subtask: TasksEntity) {
        task.value = subtask
    }


    override fun saveTasks(oldDate: String, date: String, category: String, tasks: List<TasksEntity>) {
        for (i in tasks.indices) {
            tasks[i].category = category
            tasks[i].date = date
        }
        useCase.saveTasks(oldDate, tasks).onEach {
            it.onSuccess {
                messageToUser.value = "Saved successfully"
                backToMain()
            }
        }.launchIn(viewModelScope)
    }

    override fun backToMain() {
        backToMain.value = Unit
    }
}