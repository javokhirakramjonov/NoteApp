package uz.gita.noteapp.ui.viewModel

import androidx.lifecycle.LiveData
import uz.gita.noteapp.data.entity.TasksEntity

interface TasksPageViewModel {
    val tasksLiveData: LiveData<List<List<TasksEntity>>>
    val goEditorScreen: LiveData<List<TasksEntity>>

    fun loadTasks()
    fun updateTasks(task: List<TasksEntity>)
    fun deleteTasks(task: List<TasksEntity>)
}