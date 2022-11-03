package uz.gita.noteapp.useCase

import kotlinx.coroutines.flow.Flow
import uz.gita.noteapp.data.entity.TasksEntity

interface AddTasksScreenUseCase {
    fun saveTasks(oldDate: String, task: List<TasksEntity>): Flow<Result<Unit>>
}