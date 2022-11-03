package uz.gita.noteapp.ui.viewModel

import androidx.lifecycle.LiveData
import uz.gita.noteapp.data.entity.TasksEntity

interface AddTasksScreenViewModel {
    val backToMain: LiveData<Unit>
    val messageToUser: LiveData<String>
    val task: LiveData<TasksEntity>
    val changeTaskLiveData: LiveData<Pair<Int, TasksEntity>>
    val deleteTaskLiveData: LiveData<Int>

    fun changeTask(index: Int, task: TasksEntity)
    fun deleteTask(index: Int)
    fun addSubTask(subtask: TasksEntity)
    fun saveTasks(oldDate: String, date: String, category: String, tasks: List<TasksEntity>)
    fun backToMain()
}